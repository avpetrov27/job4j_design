package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private final Properties properties;

    public TableEditor(Properties properties) throws
            SQLException,
            IOException,
            ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws IOException, ClassNotFoundException, SQLException {
        try (InputStream in =
                     TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws SQLException {
        statementExecute(String.format(
                "CREATE TABLE IF NOT EXISTS %s();",
                tableName
        ));
    }

    public void dropTable(String tableName) throws SQLException {
        statementExecute(String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        ));
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        statementExecute(String.format(
                "ALTER TABLE IF EXISTS %s ADD COLUMN IF NOT EXISTS %s %s;",
                tableName,
                columnName,
                type
        ));
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        statementExecute(String.format(
                "ALTER TABLE IF EXISTS %s DROP COLUMN IF EXISTS %s;",
                tableName,
                columnName
        ));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws SQLException {
        statementExecute(String.format(
                "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        ));
    }

    private void statementExecute(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws SQLException {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws
            SQLException,
            IOException,
            ClassNotFoundException {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("super_table");
        System.out.println(tableEditor.getTableScheme("super_table"));
        tableEditor.addColumn("super_table", "super_column", "text");
        System.out.println(tableEditor.getTableScheme("super_table"));
        tableEditor.renameColumn("super_table", "super_column", "dummy_column");
        System.out.println(tableEditor.getTableScheme("super_table"));
        tableEditor.dropColumn("super_table", "dummy_column");
        System.out.println(tableEditor.getTableScheme("super_table"));
        tableEditor.dropTable("super_table");
        tableEditor.close();
    }
}
