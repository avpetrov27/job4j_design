package ru.job4j.set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {
    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenAddNullandSomeElementsNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.contains(8)).isFalse();
        assertThat(set.add(8)).isTrue();
        assertThat(set.add(8)).isFalse();
        assertThat(set.contains(8)).isTrue();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
        assertThat(set.contains(0)).isFalse();
        assertThat(set.add(0)).isTrue();
        assertThat(set.add(0)).isFalse();
        assertThat(set.contains(0)).isTrue();
        assertThat(set.contains(-5)).isFalse();
        assertThat(set.add(-5)).isTrue();
        assertThat(set.add(-5)).isFalse();
        assertThat(set.contains(-5)).isTrue();
    }
}
