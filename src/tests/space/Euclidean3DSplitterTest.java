package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.space.Euclidean3DSpace;
import custom.space.Eucliden3DSplitter;

public class Euclidean3DSplitterTest {

    public static void main(String[] args) {
        //testSplitting3pyramids();
        testMultipleSplitting();
    }

    public static void testSplitting3pyramids() {
        E3DSAdditionSubstractionTest.main(null);
        Eucliden3DSplitter.splitThroughEdge(Eucliden3DSplitter.findLongestEdge());
        Euclidean3DSpace.printShapes();
    }

    public static void testMultipleSplitting() {
        Euclidean3DSpace.getOrCreateTriangularPyramid(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 100)
        );
        Edge longestEdge = Eucliden3DSplitter.findLongestEdge();
        while (longestEdge.getLength() > 1) {
            Eucliden3DSplitter.splitThroughEdge(longestEdge);
            longestEdge = Eucliden3DSplitter.findLongestEdge();
        }

        //Euclidean3DSpace.printShapes();
        System.out.println(Euclidean3DSpace.getPolyhedra().size());

    }
}
