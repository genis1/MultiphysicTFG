package custom.objects.dimensions3;

import custom.objects.temperature.diffusion.TemperatureInterface;

import java.util.TreeSet;

public abstract class Polyhedron implements Comparable<Polyhedron> {

    public final Type type;

    private final TreeSet<TemperatureInterface> faces = new TreeSet<>();

    protected Polyhedron(Type type) {
        this.type = type;
    }

    public TreeSet<TemperatureInterface> getFaces() {
        return faces;
    }

    public abstract int getNumberOfPoints();

    public abstract int getNumberOfFaces();

    public abstract void print();

    public abstract double getVolume();

    public enum Type {
        TRIANGULAR_PYRAMID(1),
        SQUARE_PYRAMID(2),
        TRIANGULAR_PRISM(3),
        QUADRATIC_PRISM(4);

        private final int order;

        Type(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }
    }
}
