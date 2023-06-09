package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
        ListUtils.addBefore(input, 2, 4);
        ListUtils.addBefore(input, 0, 0);
        assertThat(input).hasSize(5).containsSequence(0, 1, 2, 4, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 0, 0);
        assertThat(input).hasSize(5).containsSequence(1, 0, 2, 3, 4);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
        ListUtils.removeIf(input, el -> el % 2 == 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
        ListUtils.replaceIf(input, el -> el % 2 == 0, 10);
        assertThat(input).hasSize(3).containsSequence(1, 10, 3);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
        ListUtils.removeAll(input, new ArrayList<>(Arrays.asList(1, 3)));
        assertThat(input).hasSize(1).containsSequence(2);
    }
}
