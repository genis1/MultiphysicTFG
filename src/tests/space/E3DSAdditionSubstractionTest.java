package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.TriangularPrism;
import custom.objects.temperature.diffusion.Materials;
import custom.space.Euclidean3DSpace;
import external.Color;
import tests.utils.ConstantUtils;

public class E3DSAdditionSubstractionTest extends ConstantUtils {
    public static void main(String[] args) {
        //testFaceAdjacency();
        //testEdgeAdjacency();
        //testSquareFaceAdjacency();
        //testTriangularFaceAdjacencyOfSquarePyramid();
        //testElimination();
        //testCompleteElimination();
        //testCompleteEliminationOfSquarePyramid();
        //testPartialEliminationOfSquarePyramid();
        //testAddingTwoSquares();
        //testSubtractingSquares();
        testRemovingAPrism();
    }

    private static void testFaceAdjacency() {
        Point sharedPoint0 = new Point(0, 0, 0);
        Point sharedPoint1 = new Point(1, 0, 0);
        Point sharedPoint2 = new Point(0, 1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedPoint0, sharedPoint1, sharedPoint2, positive, material, temperature);
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedPoint0, sharedPoint1, sharedPoint2, negative, material, temperature);
        Euclidean3DSpace.printShapes();
    }

    private static void testEdgeAdjacency() {
        Point planePoint0 = new Point(1, 0, 0);
        Point planePoint1 = new Point(0, 1, 0);
        Point planePoint2 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 10);
        Point negative = new Point(0, 0, -10);

        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint1, positive, negative, material, temperature);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint2, positive, negative, material, temperature);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint1, planePoint2, positive, negative, material, temperature);

        Euclidean3DSpace.printShapes();
    }

    private static void testSquareFaceAdjacency() {
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point base2 = new Point(-1, 1, 0);
        Point base3 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, positive, material, temperature);
        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, negative, material, temperature);

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

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, sharedBase0, sharedBase1, sharedApex, material, temperature);
        Euclidean3DSpace.getOrCreateSquarePyramid(sharedBase0, sharedBase1, base2, base3, sharedApex, material, temperature);

        Euclidean3DSpace.printShapes();
    }

    private static void testElimination() {
        Point planePoint0 = new Point(1, 0, 0);
        Point planePoint1 = new Point(0, 1, 0);
        Point planePoint2 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint1, positive, negative, material, temperature);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint2, positive, negative, material, temperature);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint1, planePoint2, positive, negative, material, temperature);
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

        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint1, positive, negative,material,temperature);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint0, planePoint2, positive, negative,material,temperature);
        Euclidean3DSpace.getOrCreateTriangularPyramid(planePoint1, planePoint2, positive, negative,material,temperature);
        Euclidean3DSpace.removeTriangularPyramid(planePoint0, planePoint1, positive, negative);
        Euclidean3DSpace.removeTriangularPyramid(planePoint0, planePoint2, positive, negative);
        Euclidean3DSpace.removeTriangularPyramid(planePoint1, planePoint2, positive, negative);

        Euclidean3DSpace.printShapes();
    }

    private static void testCompleteEliminationOfSquarePyramid() {
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point base2 = new Point(-1, 1, 0);
        Point base3 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, positive,material,temperature);
        Euclidean3DSpace.removeSquarePyramid(base0, base1, base2, base3, positive);

        Euclidean3DSpace.printShapes();
    }

    private static void testPartialEliminationOfSquarePyramid() {
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point base2 = new Point(-1, 1, 0);
        Point base3 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, positive,material,temperature);
        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, negative,material,temperature);
        Euclidean3DSpace.removeSquarePyramid(base0, base1, base2, base3, positive);

        Euclidean3DSpace.printShapes();
    }

    private static void testAddingTwoSquares() {
        Vector i = new Vector(1, 0, 0);
        Vector j = new Vector(0, 1, 0);
        Vector k = new Vector(0, 0, 1);
        Point origin1 = new Point(0, 0, 0);
        Point origin2 = new Point(1, 0, 0);

        Euclidean3DSpace.getOrCreateParallelepiped(origin1, i, j, k,material,temperature);
        Euclidean3DSpace.getOrCreateParallelepiped(origin2, i, j, k,material,temperature);

        Euclidean3DSpace.printShapes();
    }

    private static void testSubtractingSquares() {
        Vector i = new Vector(1, 0, 0);
        Vector j = new Vector(0, 1, 0);
        Vector k = new Vector(0, 0, 1);
        Point origin1 = new Point(0, 0, 0);
        Point origin2 = new Point(1, 0, 0);

        Euclidean3DSpace.getOrCreateParallelepiped(origin1, i, j, k,material,temperature);
        Euclidean3DSpace.getOrCreateParallelepiped(origin2, i, j, k,material,temperature);
        Euclidean3DSpace.removeParallelepiped(origin2, i, j, k);
        Euclidean3DSpace.removeParallelepiped(origin1, i, j, k);

        Euclidean3DSpace.printShapes();
    }

    private static TriangularPrism[] testAddingTwoPrism() {
        Point pointNegative = new Point(-1, 0, 0);
        Point pointPositive = new Point(1, 0, 0);
        Point shared0 = new Point(0, 0, 0);
        Point shared1 = new Point(0, 1, 0);
        Vector direction = new Vector(1, 1, 1);

        TriangularPrism prism1 = Euclidean3DSpace.getOrCreateTriangularPrism(pointNegative, shared0, shared1, direction,material,temperature);
        TriangularPrism prism2 = Euclidean3DSpace.getOrCreateTriangularPrism(pointPositive, shared0, shared1, direction,material,temperature);

        if (Euclidean3DSpace.getPoints().size() == 8 && Euclidean3DSpace.getEdges().size() == 14 && Euclidean3DSpace.getFaces().size() == 9 && Euclidean3DSpace.getPolyhedra().size() == 2) {
            System.out.println(Color.GREEN + "Adding two prisms succeed" + Color.RED);
        } else {
            System.out.println(Color.RED + "Adding two prisms failed" + Color.RESET);
        }
        return new TriangularPrism[]{prism1, prism2};
    }

    private static void testRemovingAPrism() {
        TriangularPrism[] triangularPrisms = testAddingTwoPrism();
        Euclidean3DSpace.removeTriangularPrism(triangularPrisms[0]);
        if (Euclidean3DSpace.getPoints().size() == 6 && Euclidean3DSpace.getEdges().size() == 9 && Euclidean3DSpace.getFaces().size() == 5 && Euclidean3DSpace.getPolyhedra().size() == 1) {
            System.out.println(Color.GREEN + "Removing first prism succeed" + Color.RESET);
        } else {
            System.out.println(Color.RED + "Removing first prism failed" + Color.RESET);
        }

        Euclidean3DSpace.removeTriangularPrism(triangularPrisms[1]);
        if (Euclidean3DSpace.getPoints().size() == 0 && Euclidean3DSpace.getEdges().size() == 0 && Euclidean3DSpace.getFaces().size() == 0 && Euclidean3DSpace.getPolyhedra().size() == 0) {
            System.out.println(Color.GREEN + "Removing second prism succeed" + Color.RESET);
        } else {
            System.out.println(Color.RED + "Removing second prism failed" + Color.RESET);
        }
    }
}
