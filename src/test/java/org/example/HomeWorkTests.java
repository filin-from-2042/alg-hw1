package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HomeWorkTests {
    private final HomeWork hw = new HomeWork();

    @ParameterizedTest
    @MethodSource("validPartitionByArguments")
    public <T> void should_ValidPartitionBy(List<T> listValues, Predicate<T> predicate, int expectedCount) {
        Node<T> list = getLinkedList(listValues);

        int firstWithTrueCount = hw.partitionBy(list, predicate);

        assertEquals(expectedCount, firstWithTrueCount);
    }

    @Test
    public void when_listNull_then_throwNullPointerException() {
        Node<Integer> list = null;
        Predicate<Integer> predicate = integer -> integer < 5;

        assertThrows(NullPointerException.class, () -> hw.partitionBy(list, predicate));
    }

    @Test
    public void when_predicateNull_then_throwNullPointerException() {
        Node<Integer> list = getLinkedList(List.of(1, 2, 3, 4, 5));
        Predicate<Integer> predicate = null;

        assertThrows(NullPointerException.class, () -> hw.partitionBy(list, predicate));
    }

    @ParameterizedTest
    @MethodSource("validFindNthElementArguments")
    public <T> void when_NPositionInBound_then_returnValue(List<T> listValues, int n, T expected) {
        Node<T> list = getLinkedList(listValues);

        T found = hw.findNthElement(list, n);

        assertEquals(expected, found);
    }

    @Test
    public void when_NPositionGreaterTopBound_then_throwIndexOutOfBoundsException() {
        Node<Integer> list = getLinkedList(List.of(1, 2, 3, 4, 5));

        assertThrows(IndexOutOfBoundsException.class, () -> hw.findNthElement(list, 5));
    }

    @Test
    public void when_NPositionNegative_then_throwIllegalArgumentException() {
        Node<Integer> list = getLinkedList(List.of(1, 2, 3, 4, 5));

        assertThrows(IllegalArgumentException.class, () -> hw.findNthElement(list, -1));
    }

    private static Stream<Arguments> validFindNthElementArguments() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5), 0, 1),
                Arguments.of(List.of(1, 2, 3, 4, 5), 2, 3),
                Arguments.of(List.of(1, 2, 3, 4, 5), 4, 5)
        );
    }

    private static Stream<Arguments> validPartitionByArguments() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5), (Predicate<Integer>) integer -> integer < 5, 4),
                Arguments.of(List.of("1", "20", "300", "4000", "50000"), (Predicate<String>) string -> string.length() < 3, 2),
                Arguments.of(List.of(1, 2, 3, 4, 5), (Predicate<Integer>) integer -> integer < 4, 3),
                Arguments.of(List.of(1, 2, 3, 4, 5), (Predicate<Integer>) integer -> integer < 0, 0)
        );
    }

    private <T> Node<T> getLinkedList(List<T> values) {
        Node<T> node = new Node<>(values.get(0));
        for (int index = 1; index < values.size(); index++) {
            node.add(values.get(index));
        }
        return node;
    }
}
