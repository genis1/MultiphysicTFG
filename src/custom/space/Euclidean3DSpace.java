package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions3.Polyhedron;
import custom.objects.dimensions3.TriangularPyramid;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Euclidean3DSapace manages the creation of objects through the methods getOrCreate
 * <p>
 * Before creating an object it checks if it exists.
 * If the object does not exist it creates the object.
 * Creating an object will cause all its parts to be added to the Euclidean3DSpace, in case of a Polyhedron, the faces, edges and points will be added.
 */
public class Euclidean3DSpace {

    private static final Set<Point> points = new TreeSet<>();
    private static final Set<Edge> edges = new TreeSet<>();
    private static final Set<Face> faces = new TreeSet<>();
    private static final Set<Polyhedron> polyhedra = new TreeSet<>();

    private static void addPoints(Point... points) {
        Collections.addAll(Euclidean3DSpace.points, points);
    }

    public static Set<Point> getPoints() {
        return Euclidean3DSpace.points;
    }

    public static Point getOrCreatePoint(Point newPoint) {
        //Try finding point
        Optional<Point> anyPointFound = findPoint(newPoint);
        return anyPointFound
                //If point is not found, add it to the space
                .orElseGet(() -> {
                    Euclidean3DSpace.addPoints(newPoint);
                    return newPoint;
                });
    }

    private static Optional<Point> findPoint(Point newPoint) {
        Set<Point> anyFoundPoint = Euclidean3DSpace.points.stream()
                .filter(existingPoint -> existingPoint.compareTo(newPoint) == 0)
                .collect(Collectors.toSet());
        if (anyFoundPoint.size() > 1) throw new IllegalStateException("A point is duplicated");
        return anyFoundPoint.stream().findFirst();
    }

    private static void addEdges(Edge... edges) {
        Collections.addAll(Euclidean3DSpace.edges, edges);
    }

    public static Set<Edge> getEdges() {
        return Euclidean3DSpace.edges;
    }

    public static Edge getOrCreateEdge(Point startPoint, Point endPoint) {
        //Try finding it
        Optional<Edge> anyFoundEdge = findEdge(startPoint, endPoint);
        return anyFoundEdge
                //If edge is not found create a new one.
                .orElseGet(() -> {
                    Edge newEdge = new Edge(startPoint, endPoint);
                    Euclidean3DSpace.addEdges(newEdge);
                    return newEdge;
                });
    }

    private static Optional<Edge> findEdge(Point startPoint, Point endPoint) {
        Set<Edge> anyFoundEdge = Euclidean3DSpace.getEdges().stream()
                .filter(existingEdge -> existingEdge.compareTo(startPoint, endPoint) == 0)
                .collect(Collectors.toSet());
        if (anyFoundEdge.size() > 1) throw new IllegalStateException("A point is duplicated");
        return anyFoundEdge.stream().findFirst();
    }

    private static void addFaces(Face... faces) {
        Collections.addAll(Euclidean3DSpace.faces, faces);
    }

    public static Set<Face> getFaces() {
        return Euclidean3DSpace.faces;
    }

    public static Face getOrCreateTriangularFace(Point point0, Point point1, Point point2) {
        //Try finding it
        Optional<Face> anyFaceFound = findFace(point0, point1, point2);

        return anyFaceFound
                //If face is not found create a new one
                .orElseGet(() -> {
                    Face newTriangularFace = new Face(point0, point1, point2);
                    Euclidean3DSpace.addFaces(newTriangularFace);
                    return newTriangularFace;
                });
    }

    private static Optional<Face> findFace(Point point0, Point point1, Point point2) {
        TreeSet<Point> points = new TreeSet<>();
        Collections.addAll(points, point0, point1, point2);

        Set<Face> anyFoundFace = Euclidean3DSpace.getFaces().stream()
                .filter(existingFace -> existingFace.getNumberOfPoints() == 3)
                .filter(existingFace -> existingFace.compareTo(points) == 0)
                .collect(Collectors.toSet());
        if (anyFoundFace.size() > 1) throw new IllegalStateException("A face is duplicated");
        return anyFoundFace.stream().findFirst();
    }

    private static void addPolyhedron(Polyhedron polyhedron) {
        Euclidean3DSpace.polyhedra.add(polyhedron);
    }

    private static void removePolyhedron(Polyhedron polyhedron) {
        Euclidean3DSpace.polyhedra.remove(polyhedron);
    }

    public static Set<Polyhedron> getPolyhedra() {
        return Euclidean3DSpace.polyhedra;
    }

    public static TriangularPyramid getOrCreateTriangularPyramid(Point point0, Point point1, Point point2, Point point3) {
        Optional<TriangularPyramid> anyFoundPyramid = findTriangularPyramid(point0, point1, point2, point3);

        return anyFoundPyramid
                //If pyramid is not found create a new one
                .orElseGet(() -> {
                    TriangularPyramid triangularPyramid = new TriangularPyramid(point0, point1, point2, point3);
                    Euclidean3DSpace.addPolyhedron(triangularPyramid);
                    return triangularPyramid;
                });
    }

    /**
     * Searches the Euclidean3DSpace for a triangular pyramid with the specified points
     *
     * @param point0 point0
     * @param point1 point1
     * @param point2 point2
     * @param point3 point3
     * @return A TriangularPyramid or an empty set
     */
    private static Optional<TriangularPyramid> findTriangularPyramid(Point point0, Point point1, Point point2, Point point3) {
        TreeSet<Point> points = new TreeSet<>();
        Collections.addAll(points, point0, point1, point2, point3);

        List<TriangularPyramid> anyFoundPyramid = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedron -> polyhedron.type == Polyhedron.Type.TRIANGULAR_PYRAMID)
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> triangularPyramid.compareTo(points) == 0)
                .collect(Collectors.toList());
        if (anyFoundPyramid.size() > 1) throw new IllegalStateException("A triangular pyramid is duplicated");

        return anyFoundPyramid.stream().findFirst();
    }

    public static void removeTriangularPyramid(Point point0, Point point1, Point point2, Point point3) {
        TriangularPyramid triangularPyramid = findTriangularPyramid(point0, point1, point2, point3).orElseThrow(() -> new IllegalArgumentException("Triangular pyramid to be removed not found"));
        Euclidean3DSpace.removePolyhedron(triangularPyramid);
        triangularPyramid.getFaces().forEach(face -> Euclidean3DSpace.removeFace(face, triangularPyramid));
    }

    private static void removeFace(Face face, Polyhedron polyhedron) {
        face.removeParentPolyhedron(polyhedron);
        if (face.getParentPolyhedra().isEmpty()) {
            Euclidean3DSpace.faces.remove(face);
            face.getEdges().forEach(edge -> Euclidean3DSpace.removeEdge(edge, face));
        }
    }

    private static void removeEdge(Edge edge, Face face) {
        edge.removeAdjacentFace(face);
        if (edge.getAdjacentFaces().isEmpty()) {
            Euclidean3DSpace.edges.remove(edge);
            Euclidean3DSpace.removePoint(edge.getStartPoint(), edge);
            Euclidean3DSpace.removePoint(edge.getEndPoint(), edge);
        }
    }

    private static void removePoint(Point point, Edge edge) {
        point.removeAdjacentEdge(edge);
        if (point.getAdjacentEdges().isEmpty()) {
            Euclidean3DSpace.points.remove(point);
        }
    }

    public static void printShapes() {
        Euclidean3DSpace.printPoints();
        Euclidean3DSpace.printEdges();
        Euclidean3DSpace.printFaces();
        Euclidean3DSpace.printPolyhedron();
    }

    public static void printPoints() {
        Set<Point> points = Euclidean3DSpace.getPoints();
        System.out.println("Printing " + points.size() + " points");
        for (Point point : points) {
            point.print();
        }
    }

    public static void printEdges() {
        Set<Edge> edges = Euclidean3DSpace.getEdges();
        System.out.println("Printing " + edges.size() + " edges");
        for (Edge edge : edges) {
            edge.print();
        }
    }

    public static void printFaces() {
        Set<Face> faces = Euclidean3DSpace.getFaces();
        System.out.println("Printing " + faces.size() + " faces");
        for (Face face : faces) {
            face.print();
        }
    }

    public static void printPolyhedron() {
        Set<Polyhedron> polyhedra = Euclidean3DSpace.getPolyhedra();
        System.out.println("Printing " + polyhedra.size() + " polyhedra");
        for (Polyhedron polyhedron : polyhedra) {
            polyhedron.print();
        }
    }
}