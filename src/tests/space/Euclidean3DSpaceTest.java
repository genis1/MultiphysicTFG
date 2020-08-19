package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions3.TriangularPyramid;
import custom.space.Euclidean3DSpace;

public class Euclidean3DSpaceTest {
    public static void main(String[] args) {
        //testAddingPyramid();
        testCotainsPyramid();
    }

    private static void testCotainsPyramid() {
        TriangularPyramid pyramid1 = new TriangularPyramid(new Point(0, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0),
                new Point(1, 1, 1));
        TriangularPyramid pyramid2 = new TriangularPyramid(new Point(0, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0),
                new Point(1, 1, 1));
        Euclidean3DSpace.addPyramid(pyramid1);
        System.out.println(Euclidean3DSpace.getPolyhedron().contains(pyramid2));
    }

    private static void testAddingPoints() {
        Euclidean3DSpace.addPoint(new Point(0, 1, 2));
        Euclidean3DSpace.addPoint(new Point(0, 1, 2)); //Duplicate
        Euclidean3DSpace.addPoint(new Point(1, -1, 3));
        Euclidean3DSpace.addPoint(new Point(1.5, 1, 2));
        Euclidean3DSpace.addPoint(new Point(1.5, 2, 2));
        Euclidean3DSpace.addPoint(new Point(2, 1, 2));
        Euclidean3DSpace.addPoint(new Point(1.5, 1, 3));
        Euclidean3DSpace.addPoint(new Point(1 - 1, 1, 2));//Duplicate
        Euclidean3DSpace.addPoint(new Point(1 - 1, 1, 3));
        Euclidean3DSpace.printPoints();
    }

    private static void testAddingPyramid() {
        Euclidean3DSpace.addPyramid(
                new Point(0, 0, 0),
                new Point(0, 1, 0),
                new Point(1, 1, 0),
                new Point(1, 1, 1)
        );
        Euclidean3DSpace.printShapes();
    }
}
