package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRoleIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        Role result = store.findById("1");
        assertThat(result.getPost()).isEqualTo("Administrator");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoleIsJavaDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Java Developer"));
        store.add(new Role("1", "Python Developer"));
        Role result = store.findById("1");
        assertThat(result.getPost()).isEqualTo("Java Developer");
    }

    @Test
    void whenReplaceThenPostIsPythonDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Java Developer"));
        store.replace("1", new Role("1", "Python Developer"));
        Role result = store.findById("1");
        assertThat(result.getPost()).isEqualTo("Python Developer");
    }

    @Test
    void whenNoReplaceRoleThenNoChangePost() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Java Developer"));
        store.replace("10", new Role("10", "Python Developer"));
        Role result = store.findById("1");
        assertThat(result.getPost()).isEqualTo("Java Developer");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Java Developer"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenPostIsJavaDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Java Developer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getPost()).isEqualTo("Java Developer");
    }
}