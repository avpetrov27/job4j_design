package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * hasNext - проверяет, если ли следующий элемент.
     * <p>
     * Метод возвращает true, если существует чётный элемент
     * ОБРАТИТЕ ВНИМАНИЕ: в этом методе возможно изменение значения index,
     * для тех случаев когда проверяемый элемент нечётный
     * Это сделано в целях оптимизации, чтобы повторно не перебирать пустые row
     * в методе {@link #next}
     */
    @Override
    public boolean hasNext() {
        while (index < data.length && data[index] % 2 != 0) {
            index++;
        }
        return index < data.length;
    }

    /**
     * next - возвращает элемент и сдвигает указатель итератора на следующий элемент.
     * <p>
     * Переход к следующему чётному элементу выполняется в {@link #hasNext}
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }
}
