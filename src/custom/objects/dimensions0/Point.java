package custom.objects.dimensions0;

import custom.objects.dimensions1.Edge;
import custom.objects.dimensions1.Vector;

import java.util.TreeSet;

public class Point implements Comparable<Point> {
    private final double[] coordinates;

    private TreeSet<Edge> adjacentEdges = new TreeSet<>();

    /**
     * Creating an Point object does not save it in the Euclidean3DSpace.
     * To save the object use Euclidean3DSpace::getOrCreatePoint
     *
     * @param xCoordinate x coordinate
     * @param yCoordinate y coordinate
     * @param zCoordinate z coordinate
     */
    public Point(double xCoordinate, double yCoordinate, double zCoordinate) {
        this.coordinates = new double[]{xCoordinate, yCoordinate, zCoordinate};
    }

    public double getXCoordinate() {
        return this.coordinates[0];
    }

    public double getYCoordinate() {
        return this.coordinates[1];
    }

    public double getZCoordinate() {
        return this.coordinates[2];
    }

    public void addAdjacentEdge(Edge edge) {
        this.adjacentEdges.add(edge);
    }

    public void removeAdjacentEdge(Edge edge) {
        this.adjacentEdges.remove(edge);
    }

    public TreeSet<Edge> getAdjacentEdges() {
        return this.adjacentEdges;
    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "(" + this.getXCoordinate() + "," + this.getYCoordinate() + "," + this.getZCoordinate() + ")";
    }

    public Point add(Vector vector) {
        return new Point(this.getXCoordinate() + vector.getXCoordinate(),
                this.getYCoordinate() + vector.getYCoordinate(),
                this.getZCoordinate() + vector.getZCoordinate());
    }

    @Override
    public int compareTo(Point o) {
        if (this.getXCoordinate() != o.getXCoordinate()) {
            if (this.getXCoordinate() > o.getXCoordinate()) return 1;
            else return -1;
        } else if (this.getYCoordinate() != o.getYCoordinate()) {
            if (this.getYCoordinate() > o.getYCoordinate()) return 1;
            else return -1;
        } else if (this.getZCoordinate() != o.getZCoordinate()) {
            if (this.getZCoordinate() > o.getZCoordinate()) return 1;
            else return -1;
        } else {
            return 0;
        }
    }
}