package ru.job4j.generic;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("administrator");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        store.add(new Role("1", "admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("administrator");
    }

    @Test
    void whenReplaceThenRoleNameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        store.replace("1", new Role("1", "admin"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("admin");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        store.replace("10", new Role("10", "user"));
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("administrator");
        result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenRoleNameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName()).isEqualTo("administrator");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        boolean rsl = store.replace("1", new Role("1", "administrator"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        boolean rsl = store.replace("10", new Role("10", "user"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "administrator"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}