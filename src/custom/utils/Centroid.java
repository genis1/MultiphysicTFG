package custom.utils;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions3.TriangularPyramid;

public class Centroid {

    public static Point getFrom(TriangularPyramid triangularPyramid) {
        Point[] points = triangularPyramid.getPoints();

        double xCoordinate = (points[0].getXCoordinate() + points[1].getXCoordinate() + points[2].getXCoordinate() + points[3].getXCoordinate()) / 4;
        double yCoordinate = (points[0].getYCoordinate() + points[1].getYCoordinate() + points[2].getYCoordinate() + points[3].getYCoordinate()) / 4;
        double zCoordinate = (points[0].getZCoordinate() + points[1].getZCoordinate() + points[2].getZCoordinate() + points[3].getZCoordinate()) / 4;

        return new Point(xCoordinate, yCoordinate, zCoordinate);
    }
}