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
     * содержащий внутри себя хотя бы 1 элемент внутреннего массива(column).
     * ОБРАТИТЕ ВНИМАНИЕ: в этом методе возможно изменение значения row,
     * для тех случаев когда row НЕ содержит элементов
     * Это сделано для оптимизации, чтобы повторно не перебирать пустые row
     * в методе {@link #next}
     */
    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        return row < data.length;
    }

    /**
     * next - возвращает элемент и сдвигает указатель итератора на следующий элемент.
     * <p>
     * Если в row ещё существуют элементы, то переход к следующему элементу внутри row
     * Если в row элементы закончились, то переход к следующему row
     * Пропуск row, не содержащих элементов осуществляется в {@link #hasNext}
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int r = row;
        int c = column;
        if (column < data[row].length - 1) {
            column++;
        } else {
            row++;
            column = 0;
        }
        return data[r][c];
    }
}
