package custom.objects.dimensions2;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Segment;

import java.util.*;

public class Triangle extends Polygon {

    private final Point[] points;

    public Point getPoint0() {
        return points[0];
    }

    public Point getPoint1() {
        return points[1];
    }

    public Point getPoint2() {
        return points[2];
    }

    public Triangle(Point point1, Point point2, Point point3) {
        this.points = new Point[]{point1, point2, point3};

    }

    public Triangle(Segment segment, Point point) {
        this(segment.getStartPoint(), segment.getEndPoint(), point);
    }

    @Override
    public int compareTo(Polygon polygon) {
        if (this.getNumberOfPoints() != polygon.getNumberOfPoints()) {
            return this.getNumberOfPoints() - polygon.getNumberOfPoints();
        } else {
            Triangle triangle = Triangle.class.cast(polygon);

            TreeSet<Point> thisPoints = new TreeSet<>(Arrays.asList(this.points));

            TreeSet<Point> otherPoints = new TreeSet<>(Arrays.asList(triangle.points));

            Iterator<Point> thisIterator = thisPoints.iterator();
            Iterator<Point> otherIterator = otherPoints.iterator();

            while (thisIterator.hasNext()) {
                int result = thisIterator.next().compareTo(otherIterator.next());
                if (result != 0) return result;

            }
            return 0;
        }
    }

    @Override
    public int getNumberOfPoints() {
        return 3;
    }
}
