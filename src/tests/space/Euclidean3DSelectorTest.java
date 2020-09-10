package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.space.Euclidean3DSelector;
import custom.utils.VectorUtils;
import external.Color;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Euclidean3DSelectorTest {

    public static void main(String[] args) {
        testPointInsideCube();
        testRotatedPointInsideCube();
        testPointsInsideTriangle();
    }

    private static void testPointInsideCube() {
        Point testPoint = new Point(1d / 2, 1d / 2, 1d / 2);
        boolean centroidInsideSquare = Euclidean3DSelector.isPointInsideCube(
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

    private static void testRotatedPointInsideCube() {


        Point testPoint = new Point(1d / 2, 1d / 2, 1d / 2);
        boolean centroidInsideSquare = Euclidean3DSelector.isPointInsideCube(
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

    private static void testPointsInsideTriangle() {
        Point face1 = new Point(0, 0, 0);
        Point face2 = new Point(1, 1, 0);
        Point face3 = new Point(2, -1, 0);

        Point outside0 = new Point(-0.2, 1.2, 1.2);
        Point outside1 = new Point(0.1, 1.2, 0.9);
        Point outside2 = new Point(0.6, 1.2, 0.3);
        Point outside3 = new Point(0.8, 1.2, -0.3);
        Point outside4 = new Point(1.2, 1.2, -0.5);
        Point outside5 = new Point(1.6, 1.2, -1.1);
        Point outside6 = new Point(1.9, 1.2, -2.1);
        Point outside7 = new Point(2.1, 1.2, -2.2);

        Point outside8 = new Point(-0.2, 0.9, 1.2);
        Point outside9 = new Point(0.1, 0.9, 0.9);
        Point outside10 = new Point(0.6, 0.9, 0.3);
        Point outside11 = new Point(0.8, 0.9, -0.3);
        Point i0 = new Point(1.0, 0.9, -0.5);
        Point outside12 = new Point(1.6, 0.9, -1.1);
        Point outside13 = new Point(1.9, 0.9, -2.1);
        Point outside14 = new Point(2.1, 0.9, -2.2);

        Point outside15 = new Point(-0.2, 0.1, 1.2);
        Point outside16 = new Point(0.1, 0.1, 0.9);
        Point i1 = new Point(0.6, 0.1, 0.3);
        Point i2 = new Point(0.82, 0.1, -0.3);
        Point i3 = new Point(1.2, 0.1, -0.5);
        Point outside17 = new Point(1.6, 0.1, -1.1);
        Point outside18 = new Point(1.9, 0.1, -2.1);
        Point outside19 = new Point(2.1, 0.1, -2.2);

        Point outside20 = new Point(-0.2, -0.4, 1.2);
        Point outside21 = new Point(0.1, -0.4, 0.9);
        Point outside22 = new Point(0.6, -0.4, 0.3);
        Point i4 = new Point(0.82, -0.4, -0.3);
        Point i5 = new Point(1.2, -0.4, -0.5);
        Point i6 = new Point(1.6, -0.4, -1.1);
        Point outside23 = new Point(1.9, -0.4, -2.1);
        Point outside24 = new Point(2.1, -0.4, -2.2);

        Point outside25 = new Point(-0.2, -0.9, 1.2);
        Point outside26 = new Point(0.1, -0.9, 0.9);
        Point outside27 = new Point(0.6, -0.9, 0.3);
        Point outside28 = new Point(0.8, -0.9, -0.3);
        Point outside29 = new Point(1.2, -0.9, -0.5);
        Point outside30 = new Point(1.6, -0.9, -1.1);
        Point i7 = new Point(1.9, -0.9, -2.1);
        Point outside31 = new Point(2.1, -0.9, -2.2);

        Point outside32 = new Point(-0.2, -2.1, 1.2);
        Point outside33 = new Point(0.1, -2.1, 0.9);
        Point outside34 = new Point(0.6, -2.1, 0.3);
        Point outside35 = new Point(0.8, -2.1, -0.3);
        Point outside36 = new Point(1.2, -2.1, -0.5);
        Point outside37 = new Point(1.6, -2.1, -1.1);
        Point outside38 = new Point(1.9, -2.1, -2.1);
        Point outside39 = new Point(2.1, -2.1, -2.2);

        List<Point> insidePoints = Arrays.asList(i0, i1, i2, i3, i4, i5, i6, i7);
        List<Point> outsidePoints = Arrays.asList(outside0, outside1, outside2, outside3, outside4, outside5, outside6, outside7, outside8, outside9, outside10,
                outside11, outside12, outside13, outside14, outside15, outside16, outside17, outside18, outside19, outside20,
                outside21, outside22, outside23, outside24, outside25, outside26, outside27, outside28, outside29, outside30,
                outside31, outside32, outside33, outside34, outside35, outside36, outside37, outside38, outside39);

        List<Point> failedInsidePoints = insidePoints.stream().filter(insidePoint -> {
            boolean shouldBeTrue = Euclidean3DSelector.isCentroidInsideTriangle(face1, face2, face3, insidePoint);
            if (!shouldBeTrue) {
                System.out.println(Color.RED + "Inside triangle test failed for point " + insidePoint.toString() + " should be inside" + Color.RESET);
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        List<Point> failedOutsidePoints = outsidePoints.stream().filter(outsidePoint -> {
            boolean shouldBeFalse = Euclidean3DSelector.isCentroidInsideTriangle(face1, face2, face3, outsidePoint);
            if (shouldBeFalse) {
                System.out.println(Color.RED + "Inside triangle test failed for point " + outsidePoint.toString() + " should be outside" + Color.RESET);
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        if (failedInsidePoints.isEmpty() && failedOutsidePoints.isEmpty())
            System.out.println(Color.GREEN + "Inside triangle test suceed" + Color.RESET);
    }
}
