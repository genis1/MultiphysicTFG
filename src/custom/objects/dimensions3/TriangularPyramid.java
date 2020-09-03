package custom.objects.dimensions3;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions1.Vector;
import custom.objects.temperature.diffusion.Materials;
import custom.objects.temperature.diffusion.TemperatureContainer;
import custom.objects.temperature.diffusion.TemperatureInterface;
import custom.space.Euclidean3DSpace;
import custom.utils.Centroid;
import custom.utils.VectorUtils;

import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.TreeSet;

public class TriangularPyramid extends TemperatureContainer {

    private final TreeSet<Point> points = new TreeSet<>();
    private final TreeSet<Edge> edges = new TreeSet<>();

    //Cached variables
    private Double volume;

    /**
     * Should only be used by Euclidean3DSpace::getOrCreateTriangularPyramid
     */
    public TriangularPyramid(Point point0, Point point1, Point point2, Point point3, Materials.TemperatureDiffusion material, double temperature) {

        super(Type.TRIANGULAR_PYRAMID, material, temperature);

        Collections.addAll(this.points,
                point0,
                Euclidean3DSpace.getOrCreatePoint(point0),
                Euclidean3DSpace.getOrCreatePoint(point1),
                Euclidean3DSpace.getOrCreatePoint(point2),
                Euclidean3DSpace.getOrCreatePoint(point3)
        );

        //Gets or creates faces
        TemperatureInterface triangularFace0 = Euclidean3DSpace.getOrCreateTriangularFace(point0, point1, point2);
        TemperatureInterface triangularFace1 = Euclidean3DSpace.getOrCreateTriangularFace(point1, point2, point3);
        TemperatureInterface triangularFace2 = Euclidean3DSpace.getOrCreateTriangularFace(point2, point3, point0);
        TemperatureInterface triangularFace3 = Euclidean3DSpace.getOrCreateTriangularFace(point3, point0, point1);

        //Set faces to be related to this polyhedron
        triangularFace0.addParentPolyhedron(this);
        triangularFace1.addParentPolyhedron(this);
        triangularFace2.addParentPolyhedron(this);
        triangularFace3.addParentPolyhedron(this);

        //Creates a resume of constituents
        Collections.addAll(this.getFaces(), triangularFace0, triangularFace1, triangularFace2, triangularFace3);
        this.getFaces().forEach(face -> this.edges.addAll(face.getEdges()));
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
        System.out.print("Tetrahedron:");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String arrayOfPoints = this.getPoints().stream().map(Point::toString)
                .reduce((a, b) -> a + "\t" + b)
                .orElseThrow(() -> new IllegalStateException("Empty tetrahedron"));
        stringBuilder.append(arrayOfPoints);
        stringBuilder.append("\t");
        stringBuilder.append(this.getMaterial());
        stringBuilder.append("\t");
        stringBuilder.append(this.getTemperature());
        return stringBuilder.toString();
    }

    public static TriangularPyramid parse(String string) {
        String[] split = string.split("\t");
        Point point0 = Point.parse(split[0]);
        Point point1 = Point.parse(split[1]);
        Point point2 = Point.parse(split[2]);
        Point point3 = Point.parse(split[3]);
        Materials.TemperatureDiffusion material = Materials.TemperatureDiffusion.valueOf(split[4]);
        double temperature = Double.parseDouble(split[5]);
        return Euclidean3DSpace.getOrCreateTriangularPyramid(point0, point1, point2, point3, material, temperature);
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

    @Override
    public double getVolume() {
        if (this.volume == null) {
            Iterator<Point> pointIterator = this.getPoints().iterator();
            Point point0 = pointIterator.next();
            Vector vector0 = VectorUtils.subtraction(pointIterator.next(), point0);
            Vector vector1 = VectorUtils.subtraction(pointIterator.next(), point0);
            Vector vector2 = VectorUtils.subtraction(pointIterator.next(), point0);
            this.volume = Math.abs(VectorUtils.determinant(vector0, vector1, vector2) / 6);
        }
        return this.volume;
    }
}
