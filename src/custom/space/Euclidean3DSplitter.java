package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions3.*;
import custom.objects.temperature.diffusion.TemperatureInterface;

import java.util.*;
import java.util.stream.Collectors;

public class Euclidean3DSplitter {

    public static void simplifyGrid() {
        Euclidean3DSplitter.splitParallelopipeds();
        Euclidean3DSplitter.splitTriangularPrisms();
        //This method must be last
        Euclidean3DSplitter.splitAdjacentSquarePyramids();
    }

    public static void splitLongestEdges(int i) {
        findLongestEdges(i).forEach(Euclidean3DSplitter::splitThroughEdge);
    }

    private static Collection<Edge> findLongestEdges(int i) {
        return Euclidean3DSpace.getEdges().stream()
                .sorted((edge0, edge1) -> {
                    double edgeDifference = edge0.getLength() - edge1.getLength();
                    if (edgeDifference > 0) return 1;
                    else if (edgeDifference < 0) return -1;
                    else return 0;
                })
                .limit(i)
                .collect(Collectors.toList());
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

        TreeMap<Edge, TriangularPyramid> edgeTriangularPyramidTreeMap = new TreeMap<>();
        triangularPyramidsToRemove
                .forEach(triangularPyramid -> {
                    Edge edgeForNewPolyhedra = triangularPyramid.getEdges().stream()
                            .filter(edge1 -> edge1.getStartPoint() != edge.getEndPoint()
                                    && edge1.getStartPoint() != edge.getStartPoint()
                                    && edge1.getEndPoint() != edge.getEndPoint()
                                    && edge1.getEndPoint() != edge.getStartPoint())
                            .findFirst().orElseThrow(() -> new IllegalAccessError("Edge of polyhedranot found"));

                    edgeTriangularPyramidTreeMap.put(edgeForNewPolyhedra, triangularPyramid);
                });


        //Remove old pyramids
        triangularPyramidsToRemove.forEach(Euclidean3DSpace::removeTriangularPyramid);

        //Add new pyramids
        edgeTriangularPyramidTreeMap.forEach((edge1, triangularPyramid) -> {
            Euclidean3DSpace.getOrCreateTriangularPyramid(edge1.getStartPoint(), edge1.getEndPoint(), centroid, edge.getStartPoint(), triangularPyramid.getMaterial(), triangularPyramid.getTemperature());
            Euclidean3DSpace.getOrCreateTriangularPyramid(edge1.getStartPoint(), edge1.getEndPoint(), centroid, edge.getEndPoint(), triangularPyramid.getMaterial(), triangularPyramid.getTemperature());
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
                .forEach(Euclidean3DSplitter::splitAdjacentSquarePyramid);
    }

    private static void splitAdjacentSquarePyramid(Face face) {
        List<Point> basePoints = face.getPoints();

        face.getParentPolyhedra().stream()
                .map(polyhedron -> {
                    if (polyhedron.type != Polyhedron.Type.SQUARE_PYRAMID)
                        throw new IllegalStateException("Space is containing strange solids");
                    return SquarePyramid.class.cast(polyhedron);
                })
                .collect(Collectors.toList())
                .forEach(squarePyramid -> {
                    Euclidean3DSpace.removeSquarePyramid(squarePyramid);
                    Point apex = squarePyramid.getApex();
                    Euclidean3DSpace.getOrCreateTriangularPyramid(basePoints.get(0), basePoints.get(1), basePoints.get(2), apex, squarePyramid.getMaterial(), squarePyramid.getTemperature());
                    Euclidean3DSpace.getOrCreateTriangularPyramid(basePoints.get(0), basePoints.get(3), basePoints.get(2), apex, squarePyramid.getMaterial(), squarePyramid.getTemperature());
                });

    }

    public static void splitParallelopipeds() {
        List<Parallelepiped> parallelepipedsToBeRemoved = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedron -> polyhedron.type == Polyhedron.Type.QUADRATIC_PRISM)
                .map(Parallelepiped.class::cast)
                .collect(Collectors.toList());
        parallelepipedsToBeRemoved.forEach(Euclidean3DSplitter::splitParallelopiped);
    }

    private static void splitParallelopiped(Parallelepiped parallelepiped) {
        TreeSet<TemperatureInterface> faces = parallelepiped.getFaces();
        Point centroid = parallelepiped.getCentroid();

        Euclidean3DSpace.removeParallelepiped(parallelepiped);
        faces.forEach(face -> {
            Iterator<Point> facePointIterator = face.getPoints().iterator();
            Euclidean3DSpace.getOrCreateSquarePyramid(
                    facePointIterator.next(),
                    facePointIterator.next(),
                    facePointIterator.next(),
                    facePointIterator.next(),
                    centroid,
                    parallelepiped.getMaterial(),
                    parallelepiped.getTemperature()
            );
        });
    }

    public static void splitTriangularPrisms() {
        List<TriangularPrism> triangularPrisms = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedron -> polyhedron.type == Polyhedron.Type.TRIANGULAR_PRISM)
                .map(TriangularPrism.class::cast)
                .collect(Collectors.toList());
        triangularPrisms.forEach(Euclidean3DSplitter::splitTriangularPrism);
    }

    private static void splitTriangularPrism(TriangularPrism triangularPrism) {
        Point centroid = triangularPrism.getCentroid();
        TreeSet<TemperatureInterface> faces = triangularPrism.getFaces();

        Euclidean3DSpace.removeTriangularPrism(triangularPrism);
        faces.forEach(face -> {
            List<Point> points = face.getPoints();
            if (points.size() == 4) {
                Euclidean3DSpace.getOrCreateSquarePyramid(
                        points.get(0),
                        points.get(1),
                        points.get(2),
                        points.get(3),
                        centroid,
                        triangularPrism.getMaterial(),
                        triangularPrism.getTemperature()
                );
            } else if (points.size() == 3) {
                Euclidean3DSpace.getOrCreateTriangularPyramid(
                        points.get(0),
                        points.get(1),
                        points.get(2),
                        centroid,
                        triangularPrism.getMaterial(),
                        triangularPrism.getTemperature()
                );
            } else {
                throw new IllegalStateException("Face of a triangular prism isn't a triangular nor a square");
            }

        });

    }
}
