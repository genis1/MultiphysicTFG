package custom.objects.dimensions1;

import custom.objects.dimensions2.Face;

import java.util.Set;
import java.util.TreeSet;

public class Edge {

    private final Segment segment;

    private Set<Face> adjacentFaces = new TreeSet<>();

    public Edge(Segment segment) {
        this.segment = segment;
    }

    public Segment getSegment() {
        return segment;
    }

    public void addAdjacentFace(Face face) {
        this.adjacentFaces.add(face);
    }

    public void removeAdjacentFace(Face face) {
        this.adjacentFaces.remove(face);
    }

    public Set<Face> getAdjacentFaces() {
        return adjacentFaces;
    }
}
