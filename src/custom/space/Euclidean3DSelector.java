package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.TriangularPyramid;
import custom.utils.VectorUtils;

import java.util.Collection;
import java.util.stream.Collectors;

public class Euclidean3DSelector {


    public static Collection<TriangularPyramid> selectPolyhedronWithCentroidInsideSquare(Point point0, Vector vector0, Vector vector1, Vector vector2) {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> {
                    Point centroid = triangularPyramid.getCentroid();
                    return isCentroidInsideSquare(point0, vector0, vector1, vector2, centroid);
                })
                .collect(Collectors.toList());

    }

    public static boolean isCentroidInsideSquare(Point point0, Vector vector0, Vector vector1, Vector vector2, Point centroid) {
        Vector centroidVector = VectorUtils.subtraction(centroid, point0);
        double result0 = VectorUtils.dotProduct(centroidVector, vector0) / Math.pow(vector0.getLength(), 2);
        boolean condition0 = 0 < result0 && result0 < 1;
        double result1 = VectorUtils.dotProduct(centroidVector, vector1) / Math.pow(vector1.getLength(), 2);
        boolean condition1 = 0 < result1 && result1 < 1;
        double result2 = VectorUtils.dotProduct(centroidVector, vector2) / Math.pow(vector2.getLength(), 2);
        boolean condition2 = 0 < result2 && result2 < 1;
        return condition0 && condition1 && condition2;
    }
}
