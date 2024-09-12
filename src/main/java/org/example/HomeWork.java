package org.example;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Сигнатуры методов в данном классе не менять
 */
public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Реализовать метод, который подсчитывает количество подходящих по условию узлов от 0 до N.
     * <br/>
     * Пример 1:
     * список 1, 2, 3, 4, 5
     * условие x < 4
     * ответ 3
     * <br/>
     * Пример 2:
     * список 1, 2, 3, 4, 5
     * условие x < 0
     * ответ 0
     *
     * @param list односвязный список
     * @param pred условие
     * @param <T>  - тип хранимых значений в списке
     * @return количество узлов от 0 до N, где N позиция на которой первый раз условие вернуло false
     */
    public <T> int partitionBy(Node<T> list, Predicate<T> pred) {
        Objects.requireNonNull(list, "list is null");
        Objects.requireNonNull(pred, "pred is null");

        Node<T> current = list;
        int counter = 0;
        while (current != null) {
            if (pred.test(current.getValue())) {
                counter++;
            } else {
                break;
            }
            current = current.getNext();
        }
        return counter;
    }

    /**
     * <h1>Задание 2.</h1>
     * Реализовать метод поиска элемента на позиции N в переданном односвязном списке.
     *
     * @param list односвязный список
     * @param n    позиция искомого элемента
     * @param <T>
     * @return сам элемент
     */
    public <T> T findNthElement(Node<T> list, int n) {
        Objects.requireNonNull(list, "list не должен быть null");
        if(n < 0){
            throw new IllegalArgumentException("n должен быть больше 0");
        }

        int index = 0;
        Node<T> currentNode = list;
        while (currentNode != null && index < n) {
            currentNode = currentNode.getNext();
            index++;
        }

        if (index < n || currentNode == null) {
            throw new IndexOutOfBoundsException("Не найден элемент на позиции N");
        }

        return currentNode.getValue();
    }
}
