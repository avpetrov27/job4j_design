package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isEqualToIgnoringCase("sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .isEqualToIgnoringCase("tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isEqualToIgnoringCase("cube");
    }

    @Test
    void isThisUnknownVertexIs2() {
        Box box = new Box(2, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isEqualToIgnoringCase("unknown object");
    }

    @Test
    void isThisUnknownEdgeIsNegative() {
        Box box = new Box(4, -5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isEqualToIgnoringCase("unknown object");
    }

    @Test
    void numberOfVerticesSphere() {
        Box box = new Box(0, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(0)
                .isZero();
    }

    @Test
    void numberOfVerticesTetrahedron() {
        Box box = new Box(4, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(4)
                .isPositive()
                .isEven();
    }

    @Test
    void numberOfVerticesCube() {
        Box box = new Box(8, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(8)
                .isPositive()
                .isEven();
    }

    @Test
    void numberOfVerticesUnknown() {
        Box box = new Box(2, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1)
                .isNegative()
                .isOdd();
    }

    @Test
    void numberOfVerticesWhenEdgeIsNegative() {
        Box box = new Box(0, -1);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1)
                .isNegative()
                .isOdd();
    }

    @Test
    void isExistOrNotExist() {
        assertThat(new Box(0, 5).isExist()).isTrue();
        assertThat(new Box(4, 5).isExist()).isTrue();
        assertThat(new Box(8, 5).isExist()).isTrue();
        assertThat(new Box(2, 5).isExist()).isFalse();
        assertThat(new Box(0, -5).isExist()).isFalse();
    }

    @Test
    void areaOfSphere() {
        Box box = new Box(0, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(314.1593d, withPrecision(0.0001d))
                .isCloseTo(314.1593d, Percentage.withPercentage(.01d));
    }

    @Test
    void areaOfTetrahedron() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(43.3013d, withPrecision(0.0001d))
                .isCloseTo(43.3013d, Percentage.withPercentage(.01d));
    }

    @Test
    void areaOfCube() {
        Box box = new Box(8, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(150.0d, withPrecision(0.0001d))
                .isCloseTo(150.0d, Percentage.withPercentage(.01d));
    }

    @Test
    void areaOfUnknown() {
        Box box = new Box(2, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(0.0d, withPrecision(0.0001d))
                .isCloseTo(0.0d, Percentage.withPercentage(.00001d));
    }

    @Test
    void areaWhenEdgeIsNegative() {
        Box box = new Box(8, -5);
        double area = box.getArea();
        assertThat(area).isEqualTo(0.0d, withPrecision(0.0000001d))
                .isCloseTo(0.0d, Percentage.withPercentage(.00001d));
    }
}
