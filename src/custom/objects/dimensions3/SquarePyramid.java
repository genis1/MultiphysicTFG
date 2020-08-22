package custom.objects.dimensions3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions2.Face;
import custom.space.Euclidean3DSpace;

import java.util.*;

public class SquarePyramid extends Polyhedron {

    /**
     * The first 4 points conform the base, in order, and the last one the apex.
     */
    private final List<Point> points = new ArrayList<>();
    private final TreeSet<Edge> edges = new TreeSet<>();
    private final TreeSet<Face> faces = new TreeSet<>();

    public SquarePyramid(Point point0, Point point1, Point point2, Point point3, Point apex) {
        super(Type.SQUARE_PYRAMID);

        Collections.addAll(this.points,
                Euclidean3DSpace.getOrCreatePoint(point0),
                Euclidean3DSpace.getOrCreatePoint(point1),
                Euclidean3DSpace.getOrCreatePoint(point2),
                Euclidean3DSpace.getOrCreatePoint(point3),
                Euclidean3DSpace.getOrCreatePoint(apex)
        );

        //Gets or creates faces
        Face base = Euclidean3DSpace.getOrCreateSquareFace(point0, point1, point2, point3);
        Face triangularFace0 = Euclidean3DSpace.getOrCreateTriangularFace(point0, point1, apex);
        Face triangularFace1 = Euclidean3DSpace.getOrCreateTriangularFace(point1, point2, apex);
        Face triangularFace2 = Euclidean3DSpace.getOrCreateTriangularFace(point2, point3, apex);
        Face triangularFace3 = Euclidean3DSpace.getOrCreateTriangularFace(point3, point0, apex);
        Collections.addAll(this.faces, base, triangularFace0, triangularFace1, triangularFace2, triangularFace3);

        //Set faces to be related to this polyhedron
        base.addParentPolyhedron(this);
        triangularFace0.addParentPolyhedron(this);
        triangularFace1.addParentPolyhedron(this);
        triangularFace2.addParentPolyhedron(this);
        triangularFace3.addParentPolyhedron(this);

        //Creates a resume of constituents
        this.faces.forEach(face -> this.edges.addAll(face.getEdges()));
    }

    public List<Point> getPoints() {
        return points;
    }

    public Point getApex() {
        return this.getPoints().get(4);
    }

    public TreeSet<Edge> getEdges() {
        return edges;
    }

    public TreeSet<Face> getFaces() {
        return faces;
    }

    @Override
    public int getNumberOfPoints() {
        return 5;
    }

    @Override
    public int getNumberOfFaces() {
        return 5;
    }

    @Override
    public void print() {
        System.out.println("Printing 5 points of square pyramid, last one is the apex:");
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
    public int compareTo(Polyhedron o) {
        if (o.type != Type.SQUARE_PYRAMID) return Type.SQUARE_PYRAMID.getOrder() - o.type.getOrder();
        else {
            SquarePyramid otherSquarePyramid = SquarePyramid.class.cast(o);


            Point otherApex = otherSquarePyramid.getPoints().get(4);
            int apexComparision = compareApex(otherApex);
            if (apexComparision != 0) return apexComparision;
            else {
                Face thisBase = this.getBase();
                Face otherBase = otherSquarePyramid.getBase();

                return thisBase.compareTo(otherBase);
            }
        }
    }

    public Face getBase() {
        return this.getFaces().stream()
                .filter(face -> face.getNumberOfPoints() == 4)
                .findFirst().orElseThrow(() -> new IllegalStateException("Found squre pyrmaid without base"));
    }

    public int compareApex(Point otherApex) {
        return this.points.get(4).compareTo(otherApex);
    }
}
