package custom.objects.dimensions1;

import custom.objects.dimensions0.Point;
import custom.utils.D1Utils;

public class Segment {

    private final Point startPoint;
    private final Point endPoint;

    private Vector vector;
    private double length;

    public Segment(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = D1Utils.length(this);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Vector getVector() {
        if (this.vector == null) {
            this.vector = new Vector(
                    this.getEndPoint().getXCoordinate() - this.getStartPoint().getXCoordinate(),
                    this.getEndPoint().getYCoordinate() - this.getStartPoint().getYCoordinate(),
                    this.getEndPoint().getZCoordinate() - this.getStartPoint().getZCoordinate());
        }
        return this.vector;
    }

    public double getLength() {
        return this.length;
    }
}
