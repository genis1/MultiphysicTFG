package custom.objects.dimensions3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions2.Triangle;
import custom.objects.dimensions2.TriangularFace;
import custom.utils.Centroid;

import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class TriangularPyramid extends Polyhedron {

    private final Point[] points;
    private final TriangularFace[] faces;


    public TriangularPyramid(Point point0, Point point1, Point point2, Point point3) {
        this.points = new Point[]{
                point0, point1, point2, point3
        };
        Triangle triangle0 = new Triangle(point0, point1, point2);
        Triangle triangle1 = new Triangle(point1, point2, point3);
        Triangle triangle2 = new Triangle(point2, point3, point0);
        Triangle triangle3 = new Triangle(point3, point0, point1);

        this.faces = new TriangularFace[]{
                new TriangularFace(this, triangle0),
                new TriangularFace(this, triangle1),
                new TriangularFace(this, triangle2),
                new TriangularFace(this, triangle3)
        };
    }

    public TriangularPyramid(TriangularFace base, Point apex) {
        this(base.getPolygon().getPoint0(), base.getPolygon().getPoint1(), base.getPolygon().getPoint2(), apex);
    }


    public Point[] getPoints() {
        return this.points;
    }


    public Point getCentroid() {
        return Centroid.getFrom(this);
    }


    public TriangularFace[] getFaces() {
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
        if (this.getNumberOfPoints() != o.getNumberOfPoints()) return this.getNumberOfPoints() - o.getNumberOfPoints();
        else if (this.getNumberOfFaces() != o.getNumberOfFaces()) return this.getNumberOfFaces() - o.getNumberOfFaces();
        else {
            TriangularPyramid otherPyramid = TriangularPyramid.class.cast(o);

            TreeSet<Face> thisFaces = new TreeSet<>(Arrays.asList(this.faces));
            Iterator<Face> thisIterator = thisFaces.iterator();

            TreeSet<Face> otherFaces = new TreeSet<>(Arrays.asList(otherPyramid.faces));
            Iterator<Face> otherIterator = otherFaces.iterator();

            while (thisIterator.hasNext()) {
                int result = thisIterator.next().compareTo(otherIterator.next());
                if (result != 0) return result;
            }
            return 0;
        }
    }
}
