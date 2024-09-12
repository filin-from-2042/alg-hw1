package org.example;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;


/**
 * Реализация базовой структуры односвязного списка
 * При необходимости, можно доработать реализацию
 *
 * @param <T>
 */
public class Node<T> {

    private final T value;

    private Node<T> next;

    public Node(@NotNull T value) {
        this.value = Objects.requireNonNull(value, "value can't be null");
    }

    public Node<T> add(@NotNull T value) {
        Objects.requireNonNull(value, "value can't be null");

        Node<T> emptyNode = this;
        while (emptyNode.getNext() != null) {
            emptyNode = emptyNode.getNext();
        }
        Node<T> newNode = new Node<>(value);
        emptyNode.setNext(newNode);
        return newNode;
    }

    public @NotNull T getValue() {
        return value;
    }

    public @Nullable Node<T> getNext() {
        return next;
    }

    public void setNext(@Nullable Node<T> next) {
        this.next = next;
    }
}
