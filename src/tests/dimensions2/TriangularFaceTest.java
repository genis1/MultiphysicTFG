package tests.dimensions2;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions2.Face;
import custom.space.Euclidean3DSpace;

public class TriangularFaceTest {

    public static void main(String[] args) {
        testComparatorEqual();
        testComparatorDifferent();
    }

    private static void testComparatorEqual() {
        Face face1 = Euclidean3DSpace.getOrCreateTriangularFace(
                new Point(0, 0, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1)
        );

        //duplicate
        Face face2 = Euclidean3DSpace.getOrCreateTriangularFace(
                new Point(0, 0, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1)
        );
        System.out.println(face1.compareTo(face2));
    }

    private static void testComparatorDifferent() {
        Face face1 = Euclidean3DSpace.getOrCreateTriangularFace(
                new Point(0, 0, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1)
        );

        Face face2 = Euclidean3DSpace.getOrCreateTriangularFace(
                new Point(0, 1, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1)
        );
        System.out.println(face1.compareTo(face2));
    }
}
