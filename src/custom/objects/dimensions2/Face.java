package custom.objects.dimensions2;

import custom.objects.dimensions3.Polyhedron;

public abstract class Face implements Comparable<Face>{
    private final Polygon polygon;

    private Polyhedron parentPolyhedron0;
    private Polyhedron parentPolyhedron1;

    public Face(Polygon polygon) {
        this.polygon = polygon;
    }

    public void setParentPolyhedron0(Polyhedron parentPolyhedron0) {
        this.parentPolyhedron0 = parentPolyhedron0;
    }

    public void setParentPolyhedron1(Polyhedron parentPolyhedron1) {
        this.parentPolyhedron1 = parentPolyhedron1;
    }

    public Polyhedron getParentPolyhedron0() {
        return parentPolyhedron0;
    }

    public Polyhedron getParentPolyhedron1() {
        return parentPolyhedron1;
    }

    public Polygon getPolygon() {
        return this.polygon;
    }

    public abstract void print();
}
