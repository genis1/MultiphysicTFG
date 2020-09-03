package custom.objects.dimensions3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.temperature.diffusion.Materials;
import custom.objects.temperature.diffusion.TemperatureContainer;
import custom.objects.temperature.diffusion.TemperatureInterface;
import custom.space.Euclidean3DSpace;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class SquarePyramid extends TemperatureContainer {

    /**
     * The first 4 points conform the base, in order, and the last one the apex.
     */
    private final List<Point> points = new ArrayList<>();
    private final TreeSet<Edge> edges = new TreeSet<>();

    public SquarePyramid(Point point0, Point point1, Point point2, Point point3, Point apex, Materials.TemperatureDiffusion material, double temperature) {
        super(Type.SQUARE_PYRAMID, material, temperature);

        Collections.addAll(this.points,
                Euclidean3DSpace.getOrCreatePoint(point0),
                Euclidean3DSpace.getOrCreatePoint(point1),
                Euclidean3DSpace.getOrCreatePoint(point2),
                Euclidean3DSpace.getOrCreatePoint(point3),
                Euclidean3DSpace.getOrCreatePoint(apex)
        );

        //Gets or creates faces
        TemperatureInterface base = Euclidean3DSpace.getOrCreateSquareFace(point0, point1, point2, point3);
        TemperatureInterface triangularFace0 = Euclidean3DSpace.getOrCreateTriangularFace(point0, point1, apex);
        TemperatureInterface triangularFace1 = Euclidean3DSpace.getOrCreateTriangularFace(point1, point2, apex);
        TemperatureInterface triangularFace2 = Euclidean3DSpace.getOrCreateTriangularFace(point2, point3, apex);
        TemperatureInterface triangularFace3 = Euclidean3DSpace.getOrCreateTriangularFace(point3, point0, apex);
        Collections.addAll(this.getFaces(), base, triangularFace0, triangularFace1, triangularFace2, triangularFace3);

        //Set faces to be related to this polyhedron
        base.addParentPolyhedron(this);
        triangularFace0.addParentPolyhedron(this);
        triangularFace1.addParentPolyhedron(this);
        triangularFace2.addParentPolyhedron(this);
        triangularFace3.addParentPolyhedron(this);

        //Creates a resume of constituents
        this.getFaces().forEach(face -> this.edges.addAll(face.getEdges()));
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
                TemperatureInterface thisBase = this.getBase();
                TemperatureInterface otherBase = otherSquarePyramid.getBase();

                return thisBase.compareTo(otherBase);
            }
        }
    }

    public TemperatureInterface getBase() {
        return this.getFaces().stream()
                .filter(face -> face.getNumberOfPoints() == 4)
                .findFirst().orElseThrow(() -> new IllegalStateException("Found squre pyrmaid without base"));
    }

    public int compareApex(Point otherApex) {
        return this.points.get(4).compareTo(otherApex);
    }

    @Override
    public double getVolume() {
        throw new NotImplementedException();
    }
}
