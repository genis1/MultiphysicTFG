package custom.objects.dimensions3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions2.Face;
import custom.space.Euclidean3DSpace;
import custom.utils.VectorUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Points are ordered first the 3 points of the base and then the 3 of the top.
 */
public class TriangularPrism extends Polyhedron {

    private final List<Point> points = new ArrayList<>();
    private final TreeSet<Edge> edges = new TreeSet<>();
    private final TreeSet<Face> faces = new TreeSet<>();

    public TriangularPrism(Point point0, Point point1, Point point2, Vector direction) {
        super(Type.TRIANGULAR_PRISM);

        //Points
        Collections.addAll(points,
                Euclidean3DSpace.getOrCreatePoint(point0),
                Euclidean3DSpace.getOrCreatePoint(point1),
                Euclidean3DSpace.getOrCreatePoint(point2),
                Euclidean3DSpace.getOrCreatePoint(point0).add(direction),
                Euclidean3DSpace.getOrCreatePoint(point1).add(direction),
                Euclidean3DSpace.getOrCreatePoint(point2).add(direction)
        );

        //Faces
        Face base = Euclidean3DSpace.getOrCreateTriangularFace(
                points.get(0),
                points.get(1),
                points.get(2));
        Face top = Euclidean3DSpace.getOrCreateTriangularFace(
                points.get(3),
                points.get(4),
                points.get(5));
        Face lateralFace0 = Euclidean3DSpace.getOrCreateSquareFace(
                points.get(0),
                points.get(1),
                points.get(4),
                points.get(3)
        );
        Face lateralFace1 = Euclidean3DSpace.getOrCreateSquareFace(
                points.get(1),
                points.get(2),
                points.get(5),
                points.get(4)
        );
        Face lateralFace2 = Euclidean3DSpace.getOrCreateSquareFace(
                points.get(2),
                points.get(0),
                points.get(3),
                points.get(5)
        );
        Collections.addAll(this.faces, base, top, lateralFace0, lateralFace1, lateralFace2);

        //Set faces to be related to this polyhedron
        base.addParentPolyhedron(this);
        top.addParentPolyhedron(this);
        lateralFace0.addParentPolyhedron(this);
        lateralFace1.addParentPolyhedron(this);
        lateralFace2.addParentPolyhedron(this);

        //Creates a resume of constituents
        this.faces.forEach(face -> this.edges.addAll(face.getEdges()));
    }

    public List<Point> getPoints() {
        return points;
    }

    public TreeSet<Edge> getEdges() {
        return edges;
    }

    public TreeSet<Face> getFaces() {
        return faces;
    }

    public Vector getDirection() {
        List<Point> thisPoints = this.getPoints();
        return VectorUtils.subtraction(thisPoints.get(3), thisPoints.get(0));
    }

    public List<Face> getBaseAndTop() {
        return getFaces().stream()
                .filter(face -> face.getNumberOfPoints() == 3)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfPoints() {
        return 6;
    }

    @Override
    public int getNumberOfFaces() {
        return 5;
    }

    @Override
    public void print() {
        System.out.println("Printing 6 points of triangular prism, first 3 belonging to the base");
        for (int i = 0; i < 6; i++) {
            this.getPoints().get(i).print();
        }
        System.out.println("    ----    ");
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Point> points = this.getPoints();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(points.get(i).toString());
        }
        return stringBuilder.toString();

    }

    @Override
    public int compareTo(Polyhedron o) {
        if (o.type != Type.TRIANGULAR_PRISM) return Type.TRIANGULAR_PRISM.getOrder() - o.type.getOrder();
        else {
            List<Point> otherPrismPoints = TriangularPrism.class.cast(o).getPoints();
            Point otherPoint0 = otherPrismPoints.get(0);
            Point otherPoint1 = otherPrismPoints.get(1);
            Point otherPoint2 = otherPrismPoints.get(2);
            Vector direction = VectorUtils.subtraction(otherPrismPoints.get(3), otherPrismPoints.get(0));
            return this.compareToPointsAndDirection(otherPoint0, otherPoint1, otherPoint2, direction);
        }
    }

    public int compareToPointsAndDirection(Point point0, Point point1, Point point2, Vector direction) {
        //Given that the smaller face of a prism is the most important regarding order:
        //If the smaller face (base) of this prism is bigger than any other's face, this prism is bigger.
        //If the smaller face of this prism matches any of the other faces, the biggest prism will be decided by the not yet compared faces.
        int baseComparedToPoints0 = this.compareBaseToPoints(
                point0,
                point1,
                point2);

        if (baseComparedToPoints0 > 0) return 1;
        else if (baseComparedToPoints0 == 0) {
            return this.compareTopToPoints(
                    point0.add(direction),
                    point1.add(direction),
                    point2.add(direction));
        }

        int baseComparedToPoints1 = this.compareBaseToPoints(
                point0.add(direction),
                point1.add(direction),
                point2.add(direction));

        if (baseComparedToPoints1 > 0) return 1;
        else if (baseComparedToPoints1 == 0) {
            return this.compareTopToPoints(
                    point0,
                    point1,
                    point2);
        }

        //If the smaller face of this prism is smaller than both faces of the other prism, the prism is smaller.
        return -1;
    }

    private int compareBaseToPoints(Point otherPoint0, Point otherPoint1, Point otherPoint2) {
        List<Point> thisPoints = this.getPoints();
        TreeSet<Point> thisBasePoints = new TreeSet<>();
        Collections.addAll(thisBasePoints, thisPoints.get(0), thisPoints.get(1), thisPoints.get(2));

        TreeSet<Point> otherBasePoints = new TreeSet<>();
        Collections.addAll(otherBasePoints, otherPoint0, otherPoint1, otherPoint2);

        return compareTriangularFaces(thisBasePoints, otherBasePoints);
    }

    private int compareTopToPoints(Point otherPoint0, Point otherPoint1, Point otherPoint2) {
        List<Point> thisPoints = this.getPoints();
        TreeSet<Point> thisTopPoints = new TreeSet<>();
        Collections.addAll(thisTopPoints, thisPoints.get(3), thisPoints.get(4), thisPoints.get(5));

        TreeSet<Point> otherBasePoints = new TreeSet<>();
        Collections.addAll(otherBasePoints, otherPoint0, otherPoint1, otherPoint2);

        return compareTriangularFaces(thisTopPoints, otherBasePoints);
    }

    private int compareTriangularFaces(TreeSet<Point> thisPoints, TreeSet<Point> otherPoints) {
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
                .orElseThrow(() -> new IllegalStateException("Empty triangular prism"));
        Double sumY = this.getPoints().stream()
                .map(Point::getYCoordinate)
                .reduce((aDouble, aDouble2) -> aDouble + aDouble2)
                .orElseThrow(() -> new IllegalStateException("Empty triangular prism"));
        Double sumZ = this.getPoints().stream()
                .map(Point::getZCoordinate)
                .reduce((aDouble, aDouble2) -> aDouble + aDouble2)
                .orElseThrow(() -> new IllegalStateException("Empty triangular prism"));
        return new Point(sumX / 6, sumY / 6, sumZ / 6);
    }
}
