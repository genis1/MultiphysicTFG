package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions3.Polyhedron;
import custom.objects.dimensions3.SquarePyramid;
import custom.objects.dimensions3.TriangularPyramid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Eucliden3DSplitter {

    public static Edge findLongestEdge() {
        Optional<Edge> edge = Euclidean3DSpace.getEdges().stream()
                .reduce((edge1, edge2) -> {
                    if (edge1.getLength() > edge2.getLength()) {
                        return edge1;
                    } else {
                        return edge2;
                    }
                });
        return edge.orElseThrow(() -> new UnsupportedOperationException("There is no edge in the space"));
    }

    public static void splitThroughEdge(Edge edge) {
        Point centroid = edge.getCentroid();

        List<TriangularPyramid> triangularPyramidsToRemove = edge.getAdjacentFaces().stream()
                .flatMap(face -> face.getParentPolyhedra().stream())
                .distinct()
                .map(polyhedron -> {
                    if (polyhedron.type == Polyhedron.Type.TRIANGULAR_PYRAMID) {
                        return TriangularPyramid.class.cast(polyhedron);
                    } else {
                        throw new UnsupportedOperationException("Not implemented");
                    }
                })
                .distinct()
                .collect(Collectors.toList());

        List<Edge> edgesForNewPolyhedra = triangularPyramidsToRemove.stream()
                .flatMap(triangularPyramid -> triangularPyramid.getEdges().stream())
                .distinct()
                //Extract contorn edges to be used in creating the splitted triangular pyramids
                .filter(edge1 -> edge1.getStartPoint() != edge.getEndPoint()
                        && edge1.getStartPoint() != edge.getStartPoint()
                        && edge1.getEndPoint() != edge.getEndPoint()
                        && edge1.getEndPoint() != edge.getStartPoint())
                //.map(edge1 -> new Point[]{edge1.getStartPoint(), edge1.getEndPoint()})
                .collect(Collectors.toList());


        //Remove old pyramids
        triangularPyramidsToRemove.forEach(Euclidean3DSpace::removeTriangularPyramid);

        //Add new pyramids
        edgesForNewPolyhedra
                .forEach(edge1 -> {
                    Euclidean3DSpace.getOrCreateTriangularPyramid(edge1.getStartPoint(), edge1.getEndPoint(), centroid, edge.getStartPoint());
                    Euclidean3DSpace.getOrCreateTriangularPyramid(edge1.getStartPoint(), edge1.getEndPoint(), centroid, edge.getEndPoint());
                });
    }

    /**
     * This method assumes the Euclidean3DSpace is only composed of square and triangular pyramids
     */
    public static void splitAdjacentSquarePyramids() {
        List<Face> facesToBeRemoved = Euclidean3DSpace.getFaces().stream()
                .filter(face -> face.getNumberOfPoints() == 4)
                .collect(Collectors.toList());
        //Splitted to avoid concurrent modifications
        facesToBeRemoved
                .forEach(Eucliden3DSplitter::splitAdjacentSquarePyramid);
    }

    private static void splitAdjacentSquarePyramid(Face face) {
        List<Point> basePoints = face.getPoints();

        List<SquarePyramid> pyramidsToBeRemoved = face.getParentPolyhedra().stream()
                .map(polyhedron -> {
                    if (polyhedron.type != Polyhedron.Type.SQUARE_PYRAMID)
                        throw new IllegalStateException("Space is containing strange solids");
                    return SquarePyramid.class.cast(polyhedron);
                })
                .collect(Collectors.toList());
        List<Point> apexs = pyramidsToBeRemoved.stream()
                .map(SquarePyramid::getApex)
                .collect(Collectors.toList());

        for (SquarePyramid aPyramidsToBeRemoved : pyramidsToBeRemoved) {
            Euclidean3DSpace.removeSquarePyramid(aPyramidsToBeRemoved);
        }
        for (Point apex : apexs) {
            Euclidean3DSpace.getOrCreateTriangularPyramid(basePoints.get(0), basePoints.get(1), basePoints.get(2), apex);
            Euclidean3DSpace.getOrCreateTriangularPyramid(basePoints.get(0), basePoints.get(3), basePoints.get(2), apex);

        }
    }
}
