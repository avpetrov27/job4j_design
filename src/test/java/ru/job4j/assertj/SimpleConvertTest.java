package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .containsOnly("five", "first", "second", "three", "four")
                .containsExactly("first", "second", "three", "four", "five")
                .containsExactlyInAnyOrder("five", "first", "second", "three", "four")
                .startsWith("first", "second", "three")
                .endsWith("three", "four", "five")
                .containsSequence("three", "four")
                .isNotEmpty()
                .isNotNull()
                .allSatisfy(s -> {
                    assertThat(s.length()).isLessThan(7);
                    assertThat(s.length()).isGreaterThan(3);
                })
                .anySatisfy(s -> {
                    assertThat(s).matches(".*f.*");
                    assertThat(s).isEqualTo("five");
                })
                .allMatch(s -> s.length() < 10)
                .anyMatch(s -> s.equals("second"))
                .noneMatch(s -> s.equals("abracadabra"))
                .filteredOn(s -> s.length() == 4).anyMatch(s -> s.equals("four"))
                .filteredOnAssertions(e -> assertThat(e).isEqualTo("first")).hasSize(0);
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five", "three");
        assertThat(list).hasSize(6)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .containsOnly("five", "first", "second", "three", "four")
                .containsExactly("first", "second", "three", "four", "five", "three")
                .containsExactlyInAnyOrder("three", "five", "first", "second", "three", "four")
                .startsWith("first", "second", "three")
                .endsWith("three", "four", "five", "three")
                .containsSequence("three", "four")
                .isNotEmpty()
                .isNotNull()
                .allSatisfy(s -> {
                    assertThat(s.length()).isLessThan(7);
                    assertThat(s.length()).isGreaterThan(3);
                })
                .anySatisfy(s -> {
                    assertThat(s).matches(".*f.*");
                    assertThat(s).isEqualTo("five");
                })
                .allMatch(s -> s.length() < 10)
                .anyMatch(s -> s.equals("second"))
                .noneMatch(s -> s.equals("abracadabra"))
                .filteredOn(s -> s.length() == 4).anyMatch(s -> s.equals("four"))
                .filteredOnAssertions(e -> assertThat(e).isEqualTo("first")).hasSize(0);
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(1).isEqualTo("second");
        assertThat(list).last().isEqualTo("three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "three");
        assertThat(set).hasSize(5)
                .contains("second")
                .contains("first", "second")
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("abracadabra", "abracadabra2")
                .containsOnly("five", "first", "second", "three", "four")
                .containsExactlyInAnyOrder("three", "five", "first", "second", "four")
                .isNotEmpty()
                .isNotNull()
                .allSatisfy(s -> {
                    assertThat(s.length()).isLessThan(7);
                    assertThat(s.length()).isGreaterThan(3);
                })
                .anySatisfy(s -> {
                    assertThat(s).matches(".*f.*");
                    assertThat(s).isEqualTo("five");
                })
                .allMatch(s -> s.length() < 10)
                .anyMatch(s -> s.equals("second"))
                .noneMatch(s -> s.equals("abracadabra"))
                .filteredOn(s -> s.length() == 4).anyMatch(s -> s.equals("four"))
                .filteredOnAssertions(e -> assertThat(e).isEqualTo("first")).hasSize(0);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five", "three");
        assertThat(map).hasSize(5)
                .containsKeys("four", "five", "first", "second", "three")
                .containsValues(3, 1, 2, 4, 0)
                .doesNotContainKey("abracadabra")
                .doesNotContainValue(5)
                .containsEntry("three", 2)
                .isNotEmpty()
                .isNotNull();
    }
}
