package tests.dimension3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.TriangularPrism;
import custom.space.Euclidean3DSpace;

public class TriangularPrismTest {

    public static void main(String[] args) {
        isPrismEqual();
        isPyramidEqualWithOppositeVector();
    }

    private static void isPrismEqual() {
        Point point0 = new Point(0, 0, 0);
        Point point1 = new Point(0, 2, 0);
        Point point2 = new Point(1, 1, 0);
        Vector direction = new Vector(0, 0, 1);

        TriangularPrism triangularPrism = Euclidean3DSpace.getOrCreateTriangularPrism(point0, point1, point2, direction);

        int result = triangularPrism.compareToPointsAndDirection(point0, point1, point2, direction);
        System.out.println(result);
    }

    private static void isPyramidEqualWithOppositeVector() {
        TriangularPrism thisTriangularPrism = Euclidean3DSpace.getOrCreateTriangularPrism(
                new Point(0, 0, 0),
                new Point(0, 2, 0),
                new Point(1, 1, 0),
                new Vector(0, 0, 1)
        );

        int result = thisTriangularPrism.compareToPointsAndDirection(
                new Point(0, 0, 1),
                new Point(1, 1, 1),
                new Point(0, 2, 1),
                new Vector(0, 0, -1)
        );

        System.out.println(result);
    }
}