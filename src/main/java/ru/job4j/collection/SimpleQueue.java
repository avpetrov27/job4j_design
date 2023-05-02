package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    /**
     * Входной стек.
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    /**
     * Выходной стек.
     * В выходном стеке элементы располагаются в обратном порядке относительно входного.
     */
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countIn;
    private int countOut;

    /**
     * poll - возвращает первый элемент(в смысле FIFO) и удаляет его из коллекции.
     * <p>
     * Метод работает следующим образом:
     * 1. Если есть элементы в out (countOut > 0) - то они возвращаются поочерёдно "сверху стопки"
     * 2. Если элементов нет в out (countOut == 0), то происходит поочерёдное перемещение
     * ВСЕХ элементов из in в out, до тех пор пока не будут перемещены ВСЕ элементы (countIn == 0),
     * после этого возвращается элемент "сверху стопки" out, если он там есть(countOut > 0)
     * 3. Если в предыдущем пункте фактически перемещать было нечего(т.е. countIn == 0 и countOut == 0),
     * то выбрасывается исключение "Queue is empty"
     *
     * @return T - возвращаемый элемент
     */
    public T poll() {
        if (countOut > 0) {
            countOut--;
            return out.pop();
        }
        while (countIn > 0) {
            countIn--;
            countOut++;
            out.push(in.pop());
        }
        if (countOut > 0) {
            countOut--;
            return out.pop();
        }
        throw new NoSuchElementException("Queue is empty");
    }

    /**
     * push - метод добавляет элемент в коллекцию (in)
     *
     * @param value - добавляемый элемент
     */
    public void push(T value) {
        in.push(value);
        countIn++;
    }
}
