package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    /**
     * diff - Метод определяет разницу между двумя множествами previous м current.
     * Возвращается объект Info, хранящий три параметра типа int:
     * added - количество добавленных элементов(элемент есть в current, но нет в previous);
     * changed - количество изменённых элементов(элемент есть в обоих множествах, при этом:
     * User.id совпадает, User.name не совпадает);
     * deleted - количество удалённых элементов(элемента нет в current, но есть в previous).
     * Смотри {@link User}, {@link Info}
     * <p>
     * Метод состоит из двух циклов
     * 1. В первом проверяется вхождение элементов previous в current.
     * 2. Во втором проверяется вхождение элементов current в previous.
     *
     * @param previous - входящее множество("старое" состояние)
     * @param current  - входящее множество("текущее" состояние)
     * @return - объект {@link Info}
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0, changed = 0, deleted = 0;
        Map<Integer, String> mapEqKey = new HashMap<>();
        for (User userPrev : previous) {
            if (current.contains(userPrev)) {
                mapEqKey.put(userPrev.hashCode(), userPrev.getName());
            } else {
                deleted++;
            }
        }
        for (User userCur : current) {
            if (previous.contains(userCur)) {
                if (!mapEqKey.get(userCur.hashCode()).equals(userCur.getName())) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        return new Info(added, changed, deleted);
    }
}
