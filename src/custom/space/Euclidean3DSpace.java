package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions3.Polyhedron;
import custom.objects.dimensions3.TriangularPyramid;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Euclidean3DSpace {

    private static final Set<Point> points = new TreeSet<>();
    private static final Set<Face> faces = new TreeSet<>();
    private static final Set<Polyhedron> polyhedrons = new TreeSet<>();

    public static void addPoint(Point point) {
        Euclidean3DSpace.points.add(point);
    }

    public static void addPoints(Point... points) {
        for (Point point : points) {
            Euclidean3DSpace.addPoint(point);
        }
    }

    public static void addFace(Face face) {
        Euclidean3DSpace.faces.add(face);
    }

    public static void addFaces(Face... faces) {
        for (Face face : faces) {
            Euclidean3DSpace.addFace(face);
        }
    }

    public static void addPolyhedron(Polyhedron polyhedron) {
        Euclidean3DSpace.polyhedrons.add(polyhedron);
    }

    public static Set<Point> getPoints(){
        return Euclidean3DSpace.points;
    }

    public static Set<Face> getFaces(){
        return Euclidean3DSpace.faces;
    }

    public static Set<Polyhedron> getPolyhedron(){
        return Euclidean3DSpace.polyhedrons;
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
        for (Polyhedron polyhedron : Euclidean3DSpace.polyhedrons) {
            polyhedron.print();
        }
    }

    public static void printShapes() {
        System.out.println("Printing points");
        Euclidean3DSpace.printPoints();
        System.out.println("Printing faces");
        Euclidean3DSpace.printFaces();
        System.out.println("Printing polyhedrons");
        Euclidean3DSpace.printPolyhedron();
    }

    public static void addPyramid(Point point0, Point point1, Point point2, Point point3) {
        TriangularPyramid triangularPyramid = new TriangularPyramid(point0, point1, point2, point3);

        addPyramid(triangularPyramid);
    }

    public static void addPyramid(TriangularPyramid triangularPyramid) {
        Euclidean3DSpace.addPoints(triangularPyramid.getPoints());
        Euclidean3DSpace.addFaces(triangularPyramid.getFaces());
        Euclidean3DSpace.addPolyhedron(triangularPyramid);
    }


}
