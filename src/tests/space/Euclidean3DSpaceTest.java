package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions3.TriangularPyramid;
import custom.space.Euclidean3DSpace;
import tests.utils.ConstantUtils;

public class Euclidean3DSpaceTest extends ConstantUtils {
    public static void main(String[] args) {
        //testAddingPoints();
        //testAddingPyramid();
        testContainsPyramid();
    }

    private static void testContainsPyramid() {
        TriangularPyramid pyramid1 = Euclidean3DSpace.getOrCreateTriangularPyramid(new Point(0, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0),
                new Point(1, 1, 1),
                material,temperature);
        TriangularPyramid pyramid2 = Euclidean3DSpace.getOrCreateTriangularPyramid(new Point(0, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0),
                new Point(1, 1, 1),
                material,temperature);
        System.out.println(Euclidean3DSpace.getPolyhedra().size() == 1);
    }

    private static void testAddingPoints() {
        Euclidean3DSpace.getOrCreatePoint(new Point(0, 1, 2));
        Euclidean3DSpace.getOrCreatePoint(new Point(0, 1, 2)); //Duplicate
        Euclidean3DSpace.getOrCreatePoint(new Point(1, -1, 3));
        Euclidean3DSpace.getOrCreatePoint(new Point(1.5, 1, 2));
        Euclidean3DSpace.getOrCreatePoint(new Point(1.5, 2, 2));
        Euclidean3DSpace.getOrCreatePoint(new Point(2, 1, 2));
        Euclidean3DSpace.getOrCreatePoint(new Point(1.5, 1, 3));
        Euclidean3DSpace.getOrCreatePoint(new Point(1 - 1, 1, 2));//Duplicate
        Euclidean3DSpace.getOrCreatePoint(new Point(1 - 1, 1, 3));
        Euclidean3DSpace.printPoints();
    }

    private static void testAddingPyramid() {
        Euclidean3DSpace.getOrCreateTriangularPyramid(
                new Point(0, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0),
                new Point(1, 1, 1),
                material,temperature
        );
        Euclidean3DSpace.printShapes();
    }
}
