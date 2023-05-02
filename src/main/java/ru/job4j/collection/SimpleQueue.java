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
     * 1. Если в коллекции нет элементов, то выбрасывается исключение "Queue is empty"
     * 2. Если элементов нет в out (countOut == 0), то происходит поочерёдное перемещение
     * ВСЕХ элементов из in в out, до тех пор пока не будут перемещены ВСЕ элементы (countIn == 0)
     * 3. Возвращается элемент "сверху стопки" out
     *
     * @return T - возвращаемый элемент
     */
    public T poll() {
        if (countIn + countOut == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (countOut == 0) {
            while (countIn > 0) {
                countIn--;
                countOut++;
                out.push(in.pop());
            }
        }
        countOut--;
        return out.pop();
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
