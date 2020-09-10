package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.space.Euclidean3DSelector;
import custom.utils.VectorUtils;
import external.Color;

public class Euclidean3DSelectorTest {

    public static void main(String[] args) {
        testPoint();
        testRotatedPoint();
    }

    private static void testPoint() {
        Point testPoint = new Point(1d / 2, 1d / 2, 1d / 2);
        boolean centroidInsideSquare = Euclidean3DSelector.isCentroidInsideSquare(
                new Point(0, 0, 0),
                new Vector(1, 0, 0),
                new Vector(0, 1, 0),
                new Vector(0, 0, 1),
                testPoint
        );
        if (centroidInsideSquare)
            System.out.println(Color.GREEN + "Point is inside square detected correctly" + Color.RESET);
        else System.out.println(Color.RED + "Point is inside square detection failed" + Color.RESET);
    }

    private static void testRotatedPoint() {


        Point testPoint = new Point(1d / 2, 1d / 2, 1d / 2);
        boolean centroidInsideSquare = Euclidean3DSelector.isCentroidInsideSquare(
                VectorUtils.rotate3Angles(new Point(0.2, -0.2, 0.1)),
                VectorUtils.rotate3Angles(new Vector(1, 0, 0)),
                VectorUtils.rotate3Angles(new Vector(0, 1, 0)),
                VectorUtils.rotate3Angles(new Vector(0, 0, 1)),
                VectorUtils.rotate3Angles(testPoint)
        );
        if (centroidInsideSquare)
            System.out.println(Color.GREEN + "Point is inside square detected correctly" + Color.RESET);
        else System.out.println(Color.RED + "Point is inside square detection failed" + Color.RESET);
    }
}
