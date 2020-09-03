package custom.objects.dimensions2;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.Polyhedron;
import custom.space.Euclidean3DSpace;
import custom.utils.Centroid;
import custom.utils.VectorUtils;

import java.util.*;

/**
 * The points of a face should preserve its order except for faces with only 3 points which is not important.
 */
public class Face implements Comparable<Face> {
    private final List<Point> points = new ArrayList<>();
    private TreeSet<Edge> edges = new TreeSet<>();
    private TreeSet<Polyhedron> parentPolyhedra = new TreeSet<>();

    /**
     * Should only by used by Euclidean3DSpace::getOrCreate*Face.
     *
     * @param points points that constitute the face, must be introduced ordered.
     */
    public Face(Point... points) {

        //Creates a resume of points
        for (Point point : points) {
            this.points.add(Euclidean3DSpace.getOrCreatePoint(point));
        }

        //Gets or creates edges and set edges to be related to this face.
        // It also creates a resume of edges.
        for (int i = 0; i < points.length - 1; i++) {
            this.addEdge(points[i], points[i + 1]);
        }
        this.addEdge(points[points.length - 1], points[0]);
    }

    private void addEdge(Point point1, Point point2) {
        Edge edge = Euclidean3DSpace.getOrCreateEdge(point1, point2);
        edge.addAdjacentFace(this);
        edges.add(edge);
    }

    public int getNumberOfPoints() {
        return this.getPoints().size();
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addParentPolyhedron(Polyhedron polyhedron) {
        this.parentPolyhedra.add(polyhedron);
        if (parentPolyhedra.size() > 2) throw new IllegalStateException("A face can only form part of 2 polyhedron");
    }

    public TreeSet<Polyhedron> getParentPolyhedra() {
        return parentPolyhedra;
    }

    public void removeParentPolyhedron(Polyhedron polyhedron) {
        this.parentPolyhedra.remove(polyhedron);
    }


    public TreeSet<Edge> getEdges() {
        return edges;
    }

    public void print() {
        System.out.println("Printing face with " + this.getNumberOfPoints() + " vertices:");
        for (Point point : this.getPoints()) {
            point.print();
        }
        System.out.println("    ----    ");
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Point point : this.getPoints()) {
            string.append(point.toString());

        }
        return string.toString();
    }

    @Override
    public int compareTo(Face face) {
        if (this.getNumberOfPoints() != face.getNumberOfPoints()) {
            return this.getNumberOfPoints() - face.getNumberOfPoints();
        } else if (this.getNumberOfPoints() == 3) {
            return compareTriangularFaceToPoints(face.getPoints());
        } else {
            return compareToEdges(face.getEdges());
        }
    }

    /**
     * Before using it should be checked that the face is a square face.
     */
    public int compareToPoints(Point point0, Point point1, Point point2, Point point3) {
        TreeSet<Edge> otherEdges = new TreeSet<>();
        Collections.addAll(otherEdges,
                Euclidean3DSpace.getOrCreateEdge(point0, point1),
                Euclidean3DSpace.getOrCreateEdge(point1, point2),
                Euclidean3DSpace.getOrCreateEdge(point2, point3),
                Euclidean3DSpace.getOrCreateEdge(point3, point0));
        return this.compareToEdges(otherEdges);
    }

    private int compareToEdges(TreeSet<Edge> otherEdges) {
        Set<Edge> thisEdges = this.getEdges();

        Iterator<Edge> thisIterator = thisEdges.iterator();
        Iterator<Edge> otherIterator = otherEdges.iterator();

        while (thisIterator.hasNext()) {
            int result = thisIterator.next().compareTo(otherIterator.next());
            if (result != 0) return result;

        }
        return 0;
    }

    public int compareTriangularFaceToPoints(Point point0, Point point1, Point point2) {
        List<Point> points = new ArrayList<>();
        Collections.addAll(points, point0, point1, point2);
        return compareTriangularFaceToPoints(points);
    }

    private int compareTriangularFaceToPoints(List<Point> otherPoints) {
        List<Point> thisPoints = this.getPoints();
        thisPoints.sort(Point::compareTo);
        otherPoints.sort(Point::compareTo);

        Iterator<Point> thisIterator = thisPoints.iterator();
        Iterator<Point> otherIterator = otherPoints.iterator();

        while (thisIterator.hasNext()) {
            int result = thisIterator.next().compareTo(otherIterator.next());
            if (result != 0) return result;

        }
        return 0;
    }

    public Vector getNormal() {
        if (this.getPoints().size() != 3)
            throw new UnsupportedOperationException("Face.getNormal() is only available for triangular faces");
        Vector vector1 = VectorUtils.subtraction(this.getPoints().get(1), this.getPoints().get(0));
        Vector vector2 = VectorUtils.subtraction(this.getPoints().get(2), this.getPoints().get(0));
        return VectorUtils.crossProduct(vector1, vector2).divide(2);
    }

    public double getArea() {
        return this.getNormal().getLength();
    }

    public Point getCentroid(){
        return Centroid.getFrom3Points(this);
    }
}
