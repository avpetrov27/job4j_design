package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * hasNext - проверяет, если ли следующий элемент.
     * <p>
     * Метод возвращает true, если существует элемент верхнего массива(row),
     * и имеющий для дальнейшего перебора хотя бы 1 элемент внутреннего массива(column).
     * ОБРАТИТЕ ВНИМАНИЕ: в этом методе меняется значения row и column,
     * в тех случаях когда row больше НЕ СОДЕРЖИТ элементов(или row изначально пустой)
     */
    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == column) {
            row++;
            column = 0;
        }
        return row < data.length;
    }

    /**
     * next - возвращает элемент и сдвигает указатель итератора на следующий элемент.
     * <p>
     * ОБРАТИТЕ ВНИМАНИЕ: Обработка случая, когда в row элементы закончились(или их вообще нет)
     * осуществляется в {@link #hasNext}
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
