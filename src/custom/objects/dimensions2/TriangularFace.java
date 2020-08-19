package custom.objects.dimensions2;

import custom.objects.dimensions3.TriangularPyramid;

public class TriangularFace extends Face {

    public TriangularFace(Triangle polygon) {
        super(polygon);
    }

    public TriangularFace(TriangularPyramid triangularPyramid, Triangle triangle) {
        super(triangle);
        this.setParentPolyhedron0(triangularPyramid);
    }

    @Override
    public Triangle getPolygon() {
        return Triangle.class.cast(super.getPolygon());
    }

    @Override
    public void print() {
        System.out.println("Printing 3 points of triangular face:");
        this.getPolygon().getPoint0().print();
        this.getPolygon().getPoint1().print();
        this.getPolygon().getPoint2().print();
        System.out.println("    ----    ");
    }

    @Override
    public int compareTo(Face face) {
        return this.getPolygon().compareTo(face.getPolygon());
    }
}
