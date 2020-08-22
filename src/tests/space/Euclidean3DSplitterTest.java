package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions1.Vector;
import custom.space.Euclidean3DSpace;
import custom.space.Eucliden3DSplitter;

public class Euclidean3DSplitterTest {

    public static void main(String[] args) {
        //testSplitting3pyramids();
        //testMultipleSplitting();
        //testSplitingAdjacentSquarePyramid();
        testSplittingAdjacentSquares();
    }

    public static void testSplitting3pyramids() {
        E3DSAdditionSubstractionTest.main(null);
        Eucliden3DSplitter.splitThroughEdge(Eucliden3DSplitter.findLongestEdge());
        Euclidean3DSpace.printShapes();
    }

    public static void testSplitingAdjacentSquarePyramid() {
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point base2 = new Point(-1, 1, 0);
        Point base3 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, positive);
        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, negative);

        Eucliden3DSplitter.splitAdjacentSquarePyramids();
        Euclidean3DSpace.printShapes();
    }

    //4400
    public static void testMultipleSplitting() {
        long initialTime = System.currentTimeMillis();
        Euclidean3DSpace.getOrCreateTriangularPyramid(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 100)
        );
        Edge longestEdge = Eucliden3DSplitter.findLongestEdge();
        while (longestEdge.getLength() > 5) {
            Eucliden3DSplitter.splitThroughEdge(longestEdge);
            longestEdge = Eucliden3DSplitter.findLongestEdge();
        }

        Euclidean3DSpace.printShapes();
        System.out.println(Euclidean3DSpace.getPolyhedra().size());
        System.out.println((System.currentTimeMillis() - initialTime) + " milliseconds");

    }

    public static void testSplittingAdjacentSquares(){
        Vector i = new Vector(1, 0, 0);
        Vector j = new Vector(0, 1, 0);
        Vector k = new Vector(0, 0, 1);
        Point origin1 = new Point(0, 0, 0);
        Point origin2 = new Point(1, 0, 0);

        Euclidean3DSpace.getOrCreateParallelepiped(origin1, i, j, k);
        Euclidean3DSpace.getOrCreateParallelepiped(origin2, i, j, k);
        Eucliden3DSplitter.splitParallelopipeds();

        Euclidean3DSpace.printShapes();
    }
}
