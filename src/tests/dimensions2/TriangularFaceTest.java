package tests.dimensions2;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions2.Triangle;
import custom.objects.dimensions2.TriangularFace;

public class TriangularFaceTest {

    public static void main(String[] args) {
        //testComparatorEqual();
        testComparatorDiferent();
    }

    private static void testComparatorEqual() {
        TriangularFace face1 = new TriangularFace(new Triangle(
                new Point(0, 0, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1)
        ));

        //duplicate
        TriangularFace face2 = new TriangularFace(new Triangle(
                new Point(0, 0, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1)
        ));
        System.out.println(face1.compareTo(face2));
    }

    private static void testComparatorDiferent() {
        TriangularFace face1 = new TriangularFace(new Triangle(
                new Point(0, 0, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1)
        ));

        TriangularFace face2 = new TriangularFace(new Triangle(
                new Point(0, 1, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1)
        ));
        System.out.println(face1.compareTo(face2));
    }
}
