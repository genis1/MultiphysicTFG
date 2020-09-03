package tests.io;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions3.TriangularPyramid;
import external.Color;
import tests.utils.ConstantUtils;

public class ObjectsParsingTest extends ConstantUtils {


    public static void main(String[] args) {
        testPointParsing();
        testTetrahedronParsing();
    }

    private static void testPointParsing() {
        Point point = new Point(0.2, -1.3, 0);
        Point newPoint = Point.parse(point.toString());
        int areEqual = point.compareTo(newPoint);
        if ((areEqual == 0)) System.out.println(Color.GREEN + "Point parsing correct" + Color.RESET);
        else System.out.println(Color.RED + "Point parsing incorrect" + Color.RESET);
    }

    private static void testTetrahedronParsing() {
        Point point0 = new Point(0, 0, 0);
        Point point1 = new Point(1, 0, 0);
        Point point2 = new Point(0, 1, 0);
        Point point3 = new Point(0, 0, 1);
        TriangularPyramid triangularPyramid = new TriangularPyramid(point0, point1, point2, point3, material, temperature);

        TriangularPyramid newTriangularPyramid = TriangularPyramid.parse(triangularPyramid.toString());

        boolean arePyramidsEqual = arePyramidsEqual(triangularPyramid, newTriangularPyramid);
        if (arePyramidsEqual)
            System.out.println(Color.GREEN + "Tetrahedron parsing correct" + Color.RESET);
        else System.out.println(Color.RED + "Tetrahedron parsing incorrect" + Color.RESET);
    }

    static boolean arePyramidsEqual(TriangularPyramid triangularPyramid, TriangularPyramid newTriangularPyramid) {
        int doHaveSameLocation = triangularPyramid.compareTo(newTriangularPyramid);
        boolean doHaveSameTemperature = triangularPyramid.getTemperature() == newTriangularPyramid.getTemperature();
        boolean doHaveSameMaterial = triangularPyramid.getMaterial() == newTriangularPyramid.getMaterial();
        return doHaveSameLocation == 0 && doHaveSameTemperature && doHaveSameMaterial;
    }
}