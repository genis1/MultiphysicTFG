package custom.utils;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions3.TriangularPyramid;

import java.util.List;

public class Centroid {

    public static Point getFrom(TriangularPyramid triangularPyramid) {
        Point[] points = triangularPyramid.getPoints().toArray(new Point[0]);

        double xCoordinate = (points[0].getXCoordinate() + points[1].getXCoordinate() + points[2].getXCoordinate() + points[3].getXCoordinate()) / 4;
        double yCoordinate = (points[0].getYCoordinate() + points[1].getYCoordinate() + points[2].getYCoordinate() + points[3].getYCoordinate()) / 4;
        double zCoordinate = (points[0].getZCoordinate() + points[1].getZCoordinate() + points[2].getZCoordinate() + points[3].getZCoordinate()) / 4;

        return new Point(xCoordinate, yCoordinate, zCoordinate);
    }

    public static Point getFrom(Edge edge) {
        Point[] points = new Point[]{edge.getStartPoint(), edge.getEndPoint()};

        double xCoordinate = (points[0].getXCoordinate() + points[1].getXCoordinate()) / 2;
        double yCoordinate = (points[0].getYCoordinate() + points[1].getYCoordinate()) / 2;
        double zCoordinate = (points[0].getZCoordinate() + points[1].getZCoordinate()) / 2;

        return new Point(xCoordinate, yCoordinate, zCoordinate);
    }

    public static Point getFrom3Points(Face face) {
        List<Point> points = face.getPoints();
        if (points.size() != 3) throw new IllegalArgumentException("Face doesn't have 3 points.");

        double xCoordinate = (points.get(0).getXCoordinate() + points.get(1).getXCoordinate() + points.get(2).getXCoordinate()) / 3;
        double yCoordinate = (points.get(0).getYCoordinate() + points.get(1).getYCoordinate() + points.get(2).getYCoordinate()) / 3;
        double zCoordinate = (points.get(0).getZCoordinate() + points.get(1).getZCoordinate() + points.get(2).getZCoordinate()) / 3;

        return new Point(xCoordinate, yCoordinate, zCoordinate);
    }
}