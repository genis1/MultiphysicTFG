package tests.dimension3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.Parallelepiped;
import custom.space.Euclidean3DSpace;
import tests.utils.ConstantUtils;

public class ParallelopipedTest extends ConstantUtils {
    public static void main(String[] args) {
        testParallelopipedComparision();
    }

    private static void testParallelopipedComparision() {
        Vector i = new Vector(1, 0, 0);
        Vector j = new Vector(0, 1, 0);
        Vector k = new Vector(0, 0, 1);
        Point origin1 = new Point(0, 0, 0);


        Parallelepiped parallelepiped = Euclidean3DSpace.getOrCreateParallelepiped(origin1, i, j, k,material,temperature);
        System.out.println(parallelepiped.compareToOriginAndVectors(origin1, i, j, k));

    }
}