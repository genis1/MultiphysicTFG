package custom.objects.dimensions2;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions3.Polyhedron;
import custom.space.Euclidean3DSpace;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Face implements Comparable<Face> {
    private final TreeSet<Point> points = new TreeSet<>();
    private final TreeSet<Edge> edges = new TreeSet<>();
    private final TreeSet<Polyhedron> parentPolyhedra = new TreeSet<>();

    /**
     * Should only by used by Euclidean3DSpace::getOrCreate*Face.
     *
     * @param points points that constituted the face, mus be introduced ordered.
     */
    public Face(Point... points) {

        //Gets or creates edges and set edges to be related to this face.
        // It also creates a resume of edges.
        for (int i = 0; i < points.length - 1; i++) {
            this.addEdge(points[i], points[i + 1]);
        }
        this.addEdge(points[points.length - 1], points[0]);

        //Creates a resume of points
        this.edges
                .forEach(edge -> Collections.addAll(this.points, edge.getStartPoint(), edge.getEndPoint()));
    }

    private void addEdge(Point point1, Point point2) {
        Edge edge = Euclidean3DSpace.getOrCreateEdge(point1, point2);
        edge.addAdjacentFace(this);
        edges.add(edge);
    }

    public int getNumberOfPoints() {
        return this.getPoints().size();
    }

    public TreeSet<Point> getPoints() {
        return points;
    }


    public void addParentPolyhedron(Polyhedron polyhedron) {
        this.parentPolyhedra.add(polyhedron);
        if (parentPolyhedra.size() > 2) throw new IllegalStateException("A face can only form part of 2 polyhedron");
    }

    public void removeParentPolyhedron(Polyhedron polyhedron) {
        this.parentPolyhedra.remove(polyhedron);
    }


    public Set<Edge> getEdges() {
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
    public int compareTo(Face face) {
        if (this.getNumberOfPoints() != face.getNumberOfPoints()) {
            return this.getNumberOfPoints() - face.getNumberOfPoints();
        } else {

            return compareTo(face.getPoints());
        }
    }

    public int compareTo(TreeSet<Point> otherPoints) {
        TreeSet<Point> thisPoints = this.getPoints();

        Iterator<Point> thisIterator = thisPoints.iterator();
        Iterator<Point> otherIterator = otherPoints.iterator();

        while (thisIterator.hasNext()) {
            int result = thisIterator.next().compareTo(otherIterator.next());
            if (result != 0) return result;

        }
        return 0;
    }
}
