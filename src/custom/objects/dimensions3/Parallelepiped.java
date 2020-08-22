package custom.objects.dimensions3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions2.Face;
import custom.space.Euclidean3DSpace;
import custom.utils.VectorUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Parallelepiped extends Polyhedron {

    private final TreeSet<Point> points = new TreeSet<>();
    private final TreeSet<Edge> edges = new TreeSet<>();
    private final TreeSet<Face> faces = new TreeSet<>();
    private final TreeSet<Vector> vectors = new TreeSet<>();
    private final Point origin;


    public Parallelepiped(Point origin, Vector direction1, Vector direction2, Vector direction3) {
        super(Type.QUADRATIC_PRISM);

        //Directions
        Collections.addAll(this.vectors, direction1, direction2, direction3);
        this.origin = origin;

        //Points
        Point point000 = Euclidean3DSpace.getOrCreatePoint(origin);
        Point point001 = Euclidean3DSpace.getOrCreatePoint(origin.add(direction1));
        Point point010 = Euclidean3DSpace.getOrCreatePoint(origin.add(direction2));
        Point point011 = Euclidean3DSpace.getOrCreatePoint(origin.add(direction2).add(direction1));
        Point point100 = Euclidean3DSpace.getOrCreatePoint(origin.add(direction3));
        Point point101 = Euclidean3DSpace.getOrCreatePoint(origin.add(direction3).add(direction1));
        Point point110 = Euclidean3DSpace.getOrCreatePoint(origin.add(direction3).add(direction2));
        Point point111 = Euclidean3DSpace.getOrCreatePoint(origin.add(direction3).add(direction2).add(direction1));
        Collections.addAll(this.points,
                point000,
                point001,
                point010,
                point011,
                point100,
                point101,
                point110,
                point111
        );

        //Faces
        Face base = Euclidean3DSpace.getOrCreateSquareFace(point000, point001, point011, point010);
        Face top = Euclidean3DSpace.getOrCreateSquareFace(point100, point101, point111, point110);
        Face xPositive = Euclidean3DSpace.getOrCreateSquareFace(point001, point101, point111, point011);
        Face xNegative = Euclidean3DSpace.getOrCreateSquareFace(point000, point100, point110, point010);
        Face yPositive = Euclidean3DSpace.getOrCreateSquareFace(point010, point110, point111, point011);
        Face yNegative = Euclidean3DSpace.getOrCreateSquareFace(point000, point100, point101, point001);
        Collections.addAll(this.faces, base, top, xNegative, xPositive, yNegative, yPositive);

        base.addParentPolyhedron(this);
        top.addParentPolyhedron(this);
        yNegative.addParentPolyhedron(this);
        yPositive.addParentPolyhedron(this);
        xNegative.addParentPolyhedron(this);
        xPositive.addParentPolyhedron(this);

        //Edges
        this.faces.forEach(face -> this.edges.addAll(face.getEdges()));
    }

    public double getVolume() {
        TreeSet<Vector> vectors = this.vectors;
        Iterator<Vector> iterator = vectors.iterator();

        return Parallelepiped.computeVolume(iterator.next(), iterator.next(), iterator.next());
    }

    /**
     * @param vector0 vector0
     * @param vector1 vector1
     * @param vector2 vector2
     * @return volume
     */
    public static double computeVolume(Vector vector0, Vector vector1, Vector vector2) {
        Vector areaVector = VectorUtils.crossProduct(vector0, vector1);
        double volume = VectorUtils.dotProduct(areaVector, vector2);
        return Math.abs(volume);
    }

    public Set<Point> getPoints() {
        return points;
    }

    public Point getOrigin() {
        return origin;
    }

    public TreeSet<Edge> getEdges() {
        return edges;
    }

    public TreeSet<Vector> getVectors() {
        return vectors;
    }

    public TreeSet<Face> getFaces() {
        return faces;
    }

    @Override
    public int getNumberOfPoints() {
        return 8;
    }

    @Override
    public int getNumberOfFaces() {
        return 6;
    }

    @Override
    public void print() {
        System.out.println("Printing 8 points of parallelepipde:");
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
        if (o.type != Type.QUADRATIC_PRISM) return Type.QUADRATIC_PRISM.getOrder() - o.type.getOrder();
        else {
            Parallelepiped otherParallelopiped = Parallelepiped.class.cast(o);
            Iterator<Vector> vectorIterator = otherParallelopiped.getVectors().iterator();
            return this.compareToOriginAndVectors(otherParallelopiped.getOrigin(), vectorIterator.next(), vectorIterator.next(), vectorIterator.next());
        }
    }

    public int compareToOriginAndVectors(Point origin, Vector vector0, Vector vector1, Vector vector2) {
        double volumeDifference = this.getVolume() - Parallelepiped.computeVolume(vector0, vector1, vector2);

        if (volumeDifference > 0) {
            return 1;
        } else if (volumeDifference < 0) {
            return -1;
        } else {
            TreeSet<Point> newPoints = new TreeSet<>();
            Collections.addAll(newPoints,
                    origin,
                    origin.add(vector2),
                    origin.add(vector1),
                    origin.add(vector1).add(vector2),
                    origin.add(vector0),
                    origin.add(vector0).add(vector2),
                    origin.add(vector0).add(vector1),
                    origin.add(vector0).add(vector1).add(vector2)
            );
            return compareToPoints(newPoints);
        }
    }

    public int compareToPoints(Set<Point> otherPoints) {
        Set<Point> thisPoints = this.getPoints();

        Iterator<Point> thisIterator = thisPoints.iterator();
        Iterator<Point> otherIterator = otherPoints.iterator();

        while (thisIterator.hasNext()) {
            int result = thisIterator.next().compareTo(otherIterator.next());
            if (result != 0) return result;
        }
        return 0;

    }

    public Point getCentroid() {
        Double sumX = this.getPoints().stream()
                .map(Point::getXCoordinate)
                .reduce((aDouble, aDouble2) -> aDouble + aDouble2)
                .orElseThrow(() -> new IllegalStateException("Empty parallelopiped"));
        Double sumY = this.getPoints().stream()
                .map(Point::getYCoordinate)
                .reduce((aDouble, aDouble2) -> aDouble + aDouble2)
                .orElseThrow(() -> new IllegalStateException("Empty parallelopiped"));
        Double sumZ = this.getPoints().stream()
                .map(Point::getZCoordinate)
                .reduce((aDouble, aDouble2) -> aDouble + aDouble2)
                .orElseThrow(() -> new IllegalStateException("Empty parallelopiped"));
        return new Point(sumX / 8, sumY / 8, sumZ / 8);
    }
}
