package custom.objects.dimensions3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions2.Face;
import custom.space.Euclidean3DSpace;
import custom.utils.Centroid;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class TriangularPyramid extends Polyhedron {

    private final TreeSet<Point> points = new TreeSet<>();
    private final TreeSet<Edge> edges = new TreeSet<>();
    private final TreeSet<Face> faces = new TreeSet<>();

    /**
     * Should only be used by Euclidean3DSpace::getOrCreateTriangularPyramid
     */
    public TriangularPyramid(Point point0, Point point1, Point point2, Point point3) {

        super(Type.TRIANGULAR_PYRAMID);

        Collections.addAll(this.points,
                point0,
                Euclidean3DSpace.getOrCreatePoint(point0),
                Euclidean3DSpace.getOrCreatePoint(point1),
                Euclidean3DSpace.getOrCreatePoint(point2),
                Euclidean3DSpace.getOrCreatePoint(point3)
        );

        //Gets or creates faces
        Face triangularFace0 = Euclidean3DSpace.getOrCreateTriangularFace(point0, point1, point2);
        Face triangularFace1 = Euclidean3DSpace.getOrCreateTriangularFace(point1, point2, point3);
        Face triangularFace2 = Euclidean3DSpace.getOrCreateTriangularFace(point2, point3, point0);
        Face triangularFace3 = Euclidean3DSpace.getOrCreateTriangularFace(point3, point0, point1);

        //Set faces to be related to this polyhedron
        triangularFace0.addParentPolyhedron(this);
        triangularFace1.addParentPolyhedron(this);
        triangularFace2.addParentPolyhedron(this);
        triangularFace3.addParentPolyhedron(this);

        //Creates a resume of constituents
        Collections.addAll(this.faces, triangularFace0, triangularFace1, triangularFace2, triangularFace3);
        this.faces.forEach(face -> this.edges.addAll(face.getEdges()));
    }

    public TreeSet<Point> getPoints() {
        return this.points;
    }


    public Point getCentroid() {
        return Centroid.getFrom(this);
    }

    public TreeSet<Edge> getEdges() {
        return edges;
    }

    public TreeSet<Face> getFaces() {
        return this.faces;
    }

    @Override
    public int getNumberOfPoints() {
        return 4;
    }

    @Override
    public int getNumberOfFaces() {
        return 4;
    }

    @Override
    public void print() {
        System.out.println("Printing 4 points of triangular pyramid:");
        for (Point point : this.getPoints()) {
            point.print();
        }
        System.out.println("    ----    ");
    }

    @Override
    public int compareTo(Polyhedron o) {
        if (o.type != Type.TRIANGULAR_PYRAMID) return Type.TRIANGULAR_PYRAMID.getOrder() - o.type.getOrder();
        else {
            TriangularPyramid otherPyramid = TriangularPyramid.class.cast(o);

            TreeSet<Point> otherPoints = otherPyramid.getPoints();
            return this.compareTo(otherPoints);
        }
    }

    public int compareTo(TreeSet<Point> otherPoints) {
        Iterator<Point> otherIterator = otherPoints.iterator();

        for (Point thisPoint : this.getPoints()) {
            int result = thisPoint.compareTo(otherIterator.next());
            if (result != 0) return result;
        }
        return 0;
    }
}
