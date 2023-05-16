package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/tests/Config/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("lang")).isEqualTo("RU");
        assertThat(config.value("port")).isEqualTo("443");
        assertThat(config.value("ssh")).isEqualTo("true");
        assertThat(config.value("https")).isEqualTo("true");
    }

    @Test
    public void whenExistsCommentsAndWhitespaces() {
        String path = "./data/tests/Config/comments_and_whitespaces.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("hibernate.connection.url")).isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
        assertThat(config.value("hibernate.connection.driver_class")).isEqualTo("org.postgresql.Driver");
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    public void whenDoesNotContainSymbolEquals() {
        String path = "./data/tests/Config/does_not_contain=.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: ssh does not contain the symbol \"=\"");
    }

    @Test
    public void whenDoesNotKey() {
        String path = "./data/tests/Config/does_not_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: =10 does not contain a key");
    }

    @Test
    public void whenDoesNotValue() {
        String path = "./data/tests/Config/does_not_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: v= does not contain a value");
    }

    @Test
    public void whenMultipleEquals() {
        String path = "./data/tests/Config/multiple_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("lang")).isEqualTo("RU=EN");
        assertThat(config.value("port")).isEqualTo("443=9443");
        assertThat(config.value("ssh")).isEqualTo("true=");
        assertThat(config.value("https")).isEqualTo("true=");
    }
}
