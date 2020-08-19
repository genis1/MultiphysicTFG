package custom.objects.dimensions3;

public abstract class Polyhedron implements Comparable<Polyhedron> {

    public abstract int getNumberOfPoints();
    public abstract int getNumberOfFaces();

    public abstract void print();
}
