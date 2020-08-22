package custom.objects.dimensions3;

public abstract class Polyhedron implements Comparable<Polyhedron> {

    public final Type type;

    protected Polyhedron(Type type) {
        this.type = type;
    }

    public abstract int getNumberOfPoints();

    public abstract int getNumberOfFaces();

    public abstract void print();


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
