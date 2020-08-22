package tests.space;

import custom.objects.dimensions0.Point;
import custom.space.Euclidean3DSpace;

public class E3DSAdditionSubstractionTest {
    public static void main(String[] args) {
        //testFaceAdjacency();
        //testEdgeAdjacency();
        //testSquareFaceAdjacency();
        testTriangularFaceAdjacencyOfSquarePyramid();
        //testElimination();
        //testCompleteElimination();
        //testCompleteEliminationOfSquarePyramid();
        //testPartialEliminationOfSquarePyramid();
    }

    private static void testFaceAdjacency() {
        Point sharedPoint0 = new Point(0, 0, 0);
        Point sharedPoint1 = new Point(1, 0, 0);
        Point sharedPoint2 = new Point(0, 1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedPoint0, sharedPoint1, sharedPoint2, positive);
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedPoint0, sharedPoint1, sharedPoint2, negative);
        Euclidean3DSpace.printShapes();
    }

    private static void testEdgeAdjacency() {
        Point planePoint0 = new Point(1, 0, 0);
        Point planePoint1 = new Point(0, 1, 0);
        Point planePoint2 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 10);
        Point negative = new Point(0, 0, -10);

        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint1, positive, negative);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint2, positive, negative);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint1, planePoint2, positive, negative);

        Euclidean3DSpace.printShapes();
    }

    private static void testSquareFaceAdjacency() {
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point base2 = new Point(-1, 1, 0);
        Point base3 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, positive);
        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, negative);

        Euclidean3DSpace.printShapes();
    }

    private static void testTriangularFaceAdjacencyOfSquarePyramid() {
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point sharedBase0 = new Point(-1, -1, 0);
        Point sharedBase1 = new Point(-1, 1, 0);
        Point sharedApex = new Point(0, 0, 1);
        Point base2 = new Point(-2, 1, 0);
        Point base3 = new Point(-2, -1, 0);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, sharedBase0, sharedBase1, sharedApex);
        Euclidean3DSpace.getOrCreateSquarePyramid(sharedBase0, sharedBase1, base2, base3, sharedApex);

        Euclidean3DSpace.printShapes();
    }

    private static void testElimination() {
        Point planePoint0 = new Point(1, 0, 0);
        Point planePoint1 = new Point(0, 1, 0);
        Point planePoint2 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint1, positive, negative);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint2, positive, negative);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint1, planePoint2, positive, negative);
        Euclidean3DSpace.removeTriangularPyramid(planePoint0, planePoint1, positive, negative);
        Euclidean3DSpace.removeTriangularPyramid(planePoint0, planePoint2, positive, negative);

        Euclidean3DSpace.printShapes();
    }

    private static void testCompleteElimination() {
        Point planePoint0 = new Point(1, 0, 0);
        Point planePoint1 = new Point(0, 1, 0);
        Point planePoint2 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint1, positive, negative);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint2, positive, negative);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint1, planePoint2, positive, negative);
        Euclidean3DSpace.removeTriangularPyramid(planePoint0, planePoint1, positive, negative);
        Euclidean3DSpace.removeTriangularPyramid(planePoint0, planePoint2, positive, negative);
        Euclidean3DSpace.removeTriangularPyramid(planePoint1, planePoint2, positive, negative);

        Euclidean3DSpace.printShapes();
    }

    private static void testCompleteEliminationOfSquarePyramid(){
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point base2 = new Point(-1, 1, 0);
        Point base3 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, positive);
        Euclidean3DSpace.removeSquarePyramid(base0, base1, base2, base3, positive);

        Euclidean3DSpace.printShapes();
    }
    private static void testPartialEliminationOfSquarePyramid(){
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point base2 = new Point(-1, 1, 0);
        Point base3 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, positive);
        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, negative);
        Euclidean3DSpace.removeSquarePyramid(base0, base1, base2, base3, positive);

        Euclidean3DSpace.printShapes();
    }
}
