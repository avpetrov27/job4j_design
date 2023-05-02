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

    /**
     * poll - возвращает первый элемент(в смысле FIFO) и удаляет его из коллекции.
     * <p>
     * Метод работает следующим образом:
     * 1. Если есть элементы в out - то они возвращаются поочерёдно "сверху стопки"
     * 2. Если элементов нет в out (exception eOut), то происходит поочерёдное перемещение
     * ВСЕХ элементов из in в out, до тех пор пока не будут перемещены ВСЕ элементы(exception eIn),
     * после этого возвращается первый элемент out ("сверху стопки")
     * 3. Если в предыдущем пункте перемещать нечего(т.е. in пуст и out пуст), то выбрасывается
     * исключение (eAtAll) "Queue is empty"
     *
     * @return T - возвращаемый элемент
     */
    public T poll() {
        try {
            return out.pop();
        } catch (NoSuchElementException eOut) {
            while (true) {
                try {
                    out.push(in.pop());
                } catch (NoSuchElementException eIn) {
                    try {
                        return out.pop();
                    } catch (NoSuchElementException eAtAll) {
                        throw new NoSuchElementException("Queue is empty");
                    }
                }
            }
        }
    }

    /**
     * push - метод добавляет элемент в коллекцию (in)
     *
     * @param value - добавляемое значение
     */
    public void push(T value) {
        in.push(value);
    }
}
