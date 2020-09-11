package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.TriangularPyramid;
import custom.utils.VectorUtils;

import java.util.Collection;
import java.util.stream.Collectors;

public class Euclidean3DSelector {


    public static Collection<TriangularPyramid> selectPolyhedronWithCentroidInsideParallelepiped(Point point0, Vector vector0, Vector vector1, Vector vector2) {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> {
                    Point centroid = triangularPyramid.getCentroid();
                    return isPointInsideCube(point0, vector0, vector1, vector2, centroid);
                })
                .collect(Collectors.toList());

    }

    public static boolean isPointInsideCube(Point point0, Point point1, Point point2, Vector height, Point centroid) {
        return isPointInsideCube(point0, VectorUtils.subtraction(point1, point0), VectorUtils.subtraction(point2, point0), height, centroid);
    }

    public static boolean isPointInsideCube(Point point0, Vector vector0, Vector vector1, Vector vector2, Point centroid) {
        Vector centroidVector = VectorUtils.subtraction(centroid, point0);
        double result0 = VectorUtils.dotProduct(centroidVector, vector0) / Math.pow(vector0.getLength(), 2);
        boolean condition0 = 0 < result0 && result0 < 1;
        double result1 = VectorUtils.dotProduct(centroidVector, vector1) / Math.pow(vector1.getLength(), 2);
        boolean condition1 = 0 < result1 && result1 < 1;
        double result2 = VectorUtils.dotProduct(centroidVector, vector2) / Math.pow(vector2.getLength(), 2);
        boolean condition2 = 0 < result2 && result2 < 1;
        return condition0 && condition1 && condition2;
    }

    public static boolean isCentroidInsideTriangle(Point point0, Point point1, Point point2, Point centroid) {
        Vector vector10 = VectorUtils.subtraction(point1, point0);
        Vector vector20 = VectorUtils.subtraction(point2, point0);
        Vector vectorC0 = VectorUtils.subtraction(centroid, point0);
        Vector vectorC1 = VectorUtils.subtraction(centroid, point1);
        Vector vector01 = VectorUtils.subtraction(point0, point1);
        Vector vector21 = VectorUtils.subtraction(point2, point1);

        Vector tempSign0 = VectorUtils.crossProduct(vector20, vector10);
        Vector tempSign1 = VectorUtils.crossProduct(vectorC0, vector10);
        boolean firstLinecondition = VectorUtils.dotProduct(tempSign0, tempSign1) > 0;

        tempSign0 = VectorUtils.crossProduct(vector10, vector20);
        tempSign1 = VectorUtils.crossProduct(vectorC0, vector20);
        boolean secondLinecondition = VectorUtils.dotProduct(tempSign0, tempSign1) > 0;

        tempSign0 = VectorUtils.crossProduct(vector01, vector21);
        tempSign1 = VectorUtils.crossProduct(vectorC1, vector21);
        boolean thirdLinecondition = VectorUtils.dotProduct(tempSign0, tempSign1) > 0;


        return firstLinecondition && secondLinecondition && thirdLinecondition;
    }

    public static boolean isCentroidInsideVerticalTriangularPrism(Point point0, Point point1, Point point2, Vector height, Point centroid) {
        Vector v = VectorUtils.subtraction(centroid, point0);
        double resultHeight = VectorUtils.dotProduct(v, height) / Math.pow(height.getLength(), 2);
        boolean isCentroidInsideHeight = 0 < resultHeight && resultHeight < 1;

        return isCentroidInsideHeight && isCentroidInsideTriangle(point0, point1, point2, centroid);
    }
}
