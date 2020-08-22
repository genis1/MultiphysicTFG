package custom.objects.dimensions1;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions2.Face;
import custom.space.Euclidean3DSpace;
import custom.utils.Centroid;
import custom.utils.D1Utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Edge implements Comparable<Edge> {

    private final TreeSet<Point> points = new TreeSet<>();

    private Set<Face> adjacentFaces = new TreeSet<>();

    private Vector vector;
    private double length;

    /**
     * Should only by used by Euclidean3DSpace::getOrCreateEdge.
     * <p>
     * Does not have a direction.
     *
     * @param startPoint start point
     * @param endPoint   end point
     */
    public Edge(Point startPoint, Point endPoint) {

        startPoint = Euclidean3DSpace.getOrCreatePoint(startPoint);
        endPoint = Euclidean3DSpace.getOrCreatePoint(endPoint);
        points.add(startPoint);
        points.add(endPoint);
        startPoint.addAdjacentEdge(this);
        endPoint.addAdjacentEdge(this);
        this.length = D1Utils.length(this);
    }

    public Point getStartPoint() {
        return points.first();
    }

    public Point getEndPoint() {
        return points.last();
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

    public Point getCentroid() {
        return Centroid.getFrom(this);
    }

    public double getLength() {
        return this.length;
    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return this.getStartPoint().toString() + " -> " + this.getEndPoint().toString() + "  l: " + this.getLength();
    }

    @Override
    public int compareTo(Edge o) {
        return compareTo(o.points);
    }

    public int compareTo(Point otherStartPoint, Point otherEndPoint) {
        TreeSet<Point> otherPoints = new TreeSet<>(Arrays.asList(otherStartPoint, otherEndPoint));
        return compareTo(otherPoints);
    }

    private int compareTo(TreeSet<Point> otherPoints) {
        Iterator<Point> thisIterator = this.points.iterator();
        Iterator<Point> otherIterator = otherPoints.iterator();

        while (thisIterator.hasNext()) {
            int result = thisIterator.next().compareTo(otherIterator.next());
            if (result != 0) return result;

        }
        return 0;
    }
}
