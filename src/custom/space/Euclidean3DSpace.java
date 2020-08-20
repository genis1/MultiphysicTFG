package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions3.Polyhedron;
import custom.objects.dimensions3.TriangularPyramid;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
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
        Set<Point> existingPoints = Euclidean3DSpace.points.stream()
                .filter(existingPoint -> existingPoint.compareTo(newPoint) == 0)
                .collect(Collectors.toSet());
        if (existingPoints.size() > 1) throw new IllegalStateException("A point is duplicated");
        else if (existingPoints.size() == 1) {
            //Point found
            return existingPoints.iterator().next();
        } else {
            //Point not found, creating one
            Euclidean3DSpace.addPoints(newPoint);
            return newPoint;
        }
    }

    private static void addEdges(Edge... edges) {
        Collections.addAll(Euclidean3DSpace.edges, edges);
    }

    public static Set<Edge> getEdges() {
        return Euclidean3DSpace.edges;
    }

    public static Edge getOrCreateEdge(Point startPoint, Point endPoint) {
        //Try finding it
        Set<Edge> anyEdgefound = Euclidean3DSpace.getEdges().stream()
                .filter(existingEdge -> existingEdge.compareTo(startPoint, endPoint) == 0)
                .collect(Collectors.toSet());
        if (anyEdgefound.size() > 1) throw new IllegalStateException("A point is duplicated");
        else if (anyEdgefound.size() == 1) {
            //Edge found
            return anyEdgefound.iterator().next();

        } else {
            //Edge not found, creating a new one. Its points are also created
            Edge newEdge = new Edge(startPoint, endPoint);
            Euclidean3DSpace.addEdges(newEdge);
            return newEdge;
        }
    }

    private static void addFaces(Face... faces) {
        Collections.addAll(Euclidean3DSpace.faces, faces);
    }

    public static Set<Face> getFaces() {
        return Euclidean3DSpace.faces;
    }

    public static Face getOrCreateTriangularFace(Point point0, Point point1, Point point2) {
        //Try finding it
        TreeSet<Point> points = new TreeSet<>();
        Collections.addAll(points, point0, point1, point2);

        Set<Face> anyFaceFound = Euclidean3DSpace.getFaces().stream()
                .filter(existingFace -> existingFace.getNumberOfPoints() == 3)
                .filter(existingFace -> existingFace.compareTo(points) == 0)
                .collect(Collectors.toSet());
        if (anyFaceFound.size() > 1) throw new IllegalStateException("A point is duplicated");
        else if (anyFaceFound.size() == 1) {
            //Triangular face found
            return anyFaceFound.iterator().next();
        } else {
            //Triangular face not found, creating a new one.
            Face newTriangularFace = new Face(point0, point1, point2);
            Euclidean3DSpace.addFaces(newTriangularFace);
            return newTriangularFace;
        }
    }

    private static void addPolyhedron(Polyhedron polyhedron) {
        Euclidean3DSpace.polyhedra.add(polyhedron);
    }


    public static Set<Polyhedron> getPolyhedra() {
        return Euclidean3DSpace.polyhedra;
    }

    public static TriangularPyramid getOrCreateTriangularPyramid(Point point0, Point point1, Point point2, Point point3) {
        //Try finding it
        TreeSet<Point> points = new TreeSet<>();
        Collections.addAll(points, point0, point1, point2, point3);

        List<TriangularPyramid> anyFoundPyramid = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedron -> polyhedron.type == Polyhedron.Type.TRIANGULAR_PYRAMID)
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> triangularPyramid.compareTo(points) == 0)
                .collect(Collectors.toList());

        if (anyFoundPyramid.size() > 1) throw new IllegalStateException("A triangular pyramid is duplicated");
        else if (anyFoundPyramid.size() == 1) {
            //Pyramid found
            return anyFoundPyramid.iterator().next();
        } else {
            //Pyramid not found, it creates a new one.
            TriangularPyramid triangularPyramid = new TriangularPyramid(point0, point1, point2, point3);
            Euclidean3DSpace.addPolyhedron(triangularPyramid);
            return triangularPyramid;
        }
    }

    public static void printPoints() {
        for (Point point : Euclidean3DSpace.points) {
            point.print();
        }
    }

    public static void printFaces() {
        for (Face face : Euclidean3DSpace.faces) {
            face.print();
        }
    }

    public static void printPolyhedron() {
        for (Polyhedron polyhedron : Euclidean3DSpace.polyhedra) {
            polyhedron.print();
        }
    }

    public static void printShapes() {
        System.out.println("Printing points");
        Euclidean3DSpace.printPoints();
        System.out.println("Printing faces");
        Euclidean3DSpace.printFaces();
        System.out.println("Printing polyhedra");
        Euclidean3DSpace.printPolyhedron();
    }


}
