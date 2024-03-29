package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    }

    @Test
    void whenRemoveIfElementIsEven() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeIf(input, el -> el % 2 == 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenReplaceIfElementIsMoreThan2() {
        ListUtils.replaceIf(input, el -> el > 2, 1);
        assertThat(input).hasSize(2).containsSequence(1, 1);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeAll(input, new ArrayList<>(Arrays.asList(1, 3)));
        assertThat(input).hasSize(1).containsSequence(2);
    }
}
