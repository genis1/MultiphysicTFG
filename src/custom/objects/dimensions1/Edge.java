package custom.objects.dimensions1;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions2.Face;
import custom.space.Euclidean3DSpace;
import custom.utils.D1Utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Edge implements Comparable<Edge> {

    private final Point startPoint;
    private final Point endPoint;

    private Set<Face> adjacentFaces = new TreeSet<>();

    private Vector vector;
    private double length;

    /**
     * Should only by used by Euclidean3DSpace::getOrCreateEdge.
     *
     * Does not have a direction.
     *
     * @param startPoint start point
     * @param endPoint end point
     */
    public Edge(Point startPoint, Point endPoint) {
        startPoint = Euclidean3DSpace.getOrCreatePoint(startPoint);
        endPoint = Euclidean3DSpace.getOrCreatePoint(endPoint);
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

    public void addAdjacentFace(Face face) {
        this.adjacentFaces.add(face);
    }

    public void removeAdjacentFace(Face face) {
        this.adjacentFaces.remove(face);
    }

    public Set<Face> getAdjacentFaces() {
        return adjacentFaces;
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

    @Override
    public int compareTo(Edge o) {

        Point otherStartPoint = o.getStartPoint();
        Point otherEndPoint = o.getEndPoint();
        return compareTo(otherStartPoint, otherEndPoint);
    }

    public int compareTo(Point otherStartPoint, Point otherEndPoint) {
        TreeSet<Point> otherPoints = new TreeSet<>(Arrays.asList(otherStartPoint, otherEndPoint));
        TreeSet<Point> thisPoints = new TreeSet<>(Arrays.asList(this.getStartPoint(), this.getEndPoint()));

        Iterator<Point> thisIterator = thisPoints.iterator();
        Iterator<Point> otherIterator = otherPoints.iterator();

        while (thisIterator.hasNext()) {
            int result = thisIterator.next().compareTo(otherIterator.next());
            if (result != 0) return result;

        }
        return 0;
    }
}
