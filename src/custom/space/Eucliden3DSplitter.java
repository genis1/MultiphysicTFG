package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions3.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Eucliden3DSplitter {

    public static void simplifyGrid(){
        Eucliden3DSplitter.splitParallelopipeds();
        Eucliden3DSplitter.splitTriangularPrisms();
        Eucliden3DSplitter.splitAdjacentSquarePyramids();
    }

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

    public static void splitParallelopipeds() {
        List<Parallelepiped> parallelepipedsToBeRemoved = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedron -> polyhedron.type == Polyhedron.Type.QUADRATIC_PRISM)
                .map(Parallelepiped.class::cast)
                .collect(Collectors.toList());
        parallelepipedsToBeRemoved.forEach(Eucliden3DSplitter::splitParallelopiped);
    }

    private static void splitParallelopiped(Parallelepiped parallelepiped) {
        TreeSet<Face> faces = parallelepiped.getFaces();
        Point centroid = parallelepiped.getCentroid();

        Euclidean3DSpace.removeParallelepiped(parallelepiped);
        faces.forEach(face -> {
            Iterator<Point> facePointIterator = face.getPoints().iterator();
            Euclidean3DSpace.getOrCreateSquarePyramid(
                    facePointIterator.next(),
                    facePointIterator.next(),
                    facePointIterator.next(),
                    facePointIterator.next(),
                    centroid
            );
        });
    }

    public static void splitTriangularPrisms() {
        List<TriangularPrism> triangularPrisms = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedron -> polyhedron.type == Polyhedron.Type.TRIANGULAR_PRISM)
                .map(TriangularPrism.class::cast)
                .collect(Collectors.toList());
        triangularPrisms.forEach(Eucliden3DSplitter::splitTriangularPrism);
    }

    private static void splitTriangularPrism(TriangularPrism triangularPrism) {
        Point centroid = triangularPrism.getCentroid();
        TreeSet<Face> faces = triangularPrism.getFaces();

        Euclidean3DSpace.removeTriangularPrism(triangularPrism);
        faces.forEach(face -> {
            List<Point> points = face.getPoints();
            if (points.size() == 4) {
                Euclidean3DSpace.getOrCreateSquarePyramid(
                        points.get(0),
                        points.get(1),
                        points.get(2),
                        points.get(3),
                        centroid
                );
            } else if (points.size() == 3) {
                Euclidean3DSpace.getOrCreateTriangularPyramid(
                        points.get(0),
                        points.get(1),
                        points.get(2),
                        centroid
                );
            } else {
                throw new IllegalStateException("Face of a triangular prism isn't a triangular nor a square");
            }

        });

    }
}
