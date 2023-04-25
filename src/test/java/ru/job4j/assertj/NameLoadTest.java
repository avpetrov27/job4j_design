package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("collection contains no data");
    }

    @Test
    void checkParseAndGetMapIsOk() {
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse("key=value", "key=value2");
        assertThat(nameLoad.getMap().get("key")).isEqualTo("value+value2");
    }

    @Test
    void checkNoStrings() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Names array is empty");
    }

    @Test
    void checkNoSignOfEqualityIn1st() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("abracadabra", "anotherSimpleText"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: abracadabra does not contain the symbol \"=\"");
    }

    @Test
    void checkNoSignOfEqualityIn2nd() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key=value", "anotherSimpleText"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: anotherSimpleText does not contain the symbol \"=\"");
    }

    @Test
    void checkStringStartsWithSignOfEquality1st() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=key=value", "anotherSimpleText"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: =key=value does not contain a key");
    }

    @Test
    void checkStringStartsWithSignOfEquality2nd() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key=value", "=anotherSimpleText"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: =anotherSimpleText does not contain a key");
    }

    @Test
    void checkFirstSignOfEqualityInEnds1st() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("keyvalue=", "anotherSimpleText"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: keyvalue= does not contain a value");
    }

    @Test
    void checkFirstSignOfEqualityInEnds2nd() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("key=value", "anotherSimpleText="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("this name: anotherSimpleText= does not contain a value");
    }
}
