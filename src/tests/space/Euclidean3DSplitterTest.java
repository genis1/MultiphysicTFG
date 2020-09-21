package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Edge;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.Polyhedron;
import custom.objects.dimensions3.TriangularPrism;
import custom.objects.dimensions3.TriangularPyramid;
import custom.objects.temperature.diffusion.TemperatureContainer;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DSplitter;
import external.Color;
import tests.utils.ConstantUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Euclidean3DSplitterTest extends ConstantUtils {

    public static void main(String[] args) {
        //testSplitting3pyramids();
        //testMultipleSplitting();
        //testSplitingAdjacentSquarePyramid();
        //testSplittingAdjacentSquares();
        testSplittingAdjacentTriangularPrisms();
        //testSplittingMantainsMaterial();
    }

    public static void testSplitting3pyramids() {
        E3DSAdditionSubstractionTest.main(null);
        Euclidean3DSplitter.splitThroughEdge(Euclidean3DSplitter.findLongestEdge());
        Euclidean3DSpace.printShapes();
    }

    public static void testSplitingAdjacentSquarePyramid() {
        Point base0 = new Point(1, 1, 0);
        Point base1 = new Point(1, -1, 0);
        Point base2 = new Point(-1, 1, 0);
        Point base3 = new Point(-1, -1, 0);
        Point positive = new Point(0, 0, 1);
        Point negative = new Point(0, 0, -1);

        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, positive, material, temperature);
        Euclidean3DSpace.getOrCreateSquarePyramid(base0, base1, base2, base3, negative, material, temperature);

        Euclidean3DSplitter.splitAdjacentSquarePyramids();
        Euclidean3DSpace.printShapes();
    }

    //4400
    public static void testMultipleSplitting() {
        long initialTime = System.currentTimeMillis();
        Euclidean3DSpace.getOrCreateTriangularPyramid(
                new Point(0, 0, 0),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 100),
                material, temperature
        );
        Edge longestEdge = Euclidean3DSplitter.findLongestEdge();
        while (longestEdge.getLength() > 5) {
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
            longestEdge = Euclidean3DSplitter.findLongestEdge();
        }

        Euclidean3DSpace.printShapes();
        System.out.println(Euclidean3DSpace.getPolyhedra().size());
        System.out.println((System.currentTimeMillis() - initialTime) + " milliseconds");

    }

    public static void testSplittingAdjacentSquares() {
        Vector i = new Vector(1, 0, 0);
        Vector j = new Vector(0, 1, 0);
        Vector k = new Vector(0, 0, 1);
        Point origin1 = new Point(0, 0, 0);
        Point origin2 = new Point(1, 0, 0);

        Euclidean3DSpace.getOrCreateParallelepiped(origin1, i, j, k, material, temperature);
        Euclidean3DSpace.getOrCreateParallelepiped(origin2, i, j, k, material, temperature);
        Euclidean3DSplitter.splitParallelopipeds();

        Euclidean3DSpace.printShapes();
    }

    private static void testSplittingAdjacentTriangularPrisms() {
        Point pointNegative = new Point(-1, 0, 0);
        Point pointPositive = new Point(1, 0, 0);
        Point shared0 = new Point(0, 0, 0);
        Point shared1 = new Point(0, 1, 0);
        Vector direction = new Vector(1, 1, 1);

        TriangularPrism prism1 = Euclidean3DSpace.getOrCreateTriangularPrism(pointNegative, shared0, shared1, direction, material, temperature);
        TriangularPrism prism2 = Euclidean3DSpace.getOrCreateTriangularPrism(pointPositive, shared0, shared1, direction, material, temperature);

        Euclidean3DSplitter.splitTriangularPrisms();

        boolean numberOfPolyhedraCorrect = Euclidean3DSpace.getPolyhedra().size() == 10;
        boolean numberOfFacesCorrect = Euclidean3DSpace.getFaces().size() == 27;
        boolean numberOfEdgesCorrect = Euclidean3DSpace.getEdges().size() == 26;
        boolean numberOfPointsCorrect = Euclidean3DSpace.getPoints().size() == 10;
        boolean numberOfTriangularPyramidsCorrect = Euclidean3DSpace.getPolyhedra().stream().filter(polyhedron -> polyhedron.type == Polyhedron.Type.TRIANGULAR_PYRAMID).count() == 4;
        boolean numberOfSquarePyramidsCorrect = Euclidean3DSpace.getPolyhedra().stream().filter(polyhedron -> polyhedron.type == Polyhedron.Type.SQUARE_PYRAMID).count() == 6;
        if (numberOfPolyhedraCorrect && numberOfFacesCorrect && numberOfEdgesCorrect && numberOfPointsCorrect && numberOfTriangularPyramidsCorrect && numberOfSquarePyramidsCorrect) {
            System.out.println(Color.GREEN + "Adjacent triangular prisms split correctly" + Color.RESET);
        } else {
            System.out.println(Color.RED + "Adjacent triangular prisms failed to split" + Color.RESET);

        }
    }

    private static void testSplittingMantainsMaterial() {
        Point point0 = new Point(0, 0, 0);
        Point pointY = new Point(0, 1, 0);
        Point pointX = new Point(1, 0, 0);
        Point pointXY = new Point(1, 1, 0);
        Point pointZ = new Point(0, 0, 1);
        Vector vectorY = new Vector(0, 1, 0);
        Vector vectorX = new Vector(1, 0, 0);
        Vector vectorZ = new Vector(0, 0, 1);

        TriangularPyramid triangularPyramid = Euclidean3DSpace.getOrCreateTriangularPyramid(point0, pointX, pointY, pointZ, material, temperature);
        Euclidean3DSplitter.splitThroughEdge(triangularPyramid.getEdges().first());
        List<TemperatureContainer> wrongPolyhedra = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedra -> polyhedra.getTemperature() != temperature || polyhedra.getMaterial() != material)
                .collect(Collectors.toList());
        if (wrongPolyhedra.size() == 0) {
            System.out.println(Color.GREEN + "Edge splitting does preserve temperature or material" + Color.RESET);
        } else {
            System.out.println(Color.RED + "Edge pyramid splitting doesn't preserve temperature or material" + Color.RESET);
        }
        Euclidean3DSpace.clean();

        Euclidean3DSpace.getOrCreateSquarePyramid(point0, pointX, pointXY, pointY, pointZ, material, temperature);
        Euclidean3DSplitter.splitAdjacentSquarePyramids();
        wrongPolyhedra = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedra -> polyhedra.getTemperature() != temperature || polyhedra.getMaterial() != material)
                .collect(Collectors.toList());
        if (wrongPolyhedra.size() == 0) {
            System.out.println(Color.GREEN + "Square pyramid splitting does preserve temperature or material" + Color.RESET);
        } else {
            System.out.println(Color.RED + "Square pyramid splitting doesn't preserve temperature or material" + Color.RESET);
        }
        Euclidean3DSpace.getPolyhedra().stream().map(TriangularPyramid.class::cast)
                .collect(Collectors.toList())
                .forEach(Euclidean3DSpace::removeTriangularPyramid);

        Euclidean3DSpace.getOrCreateParallelepiped(point0, vectorX, vectorY, vectorZ, material, temperature);
        Euclidean3DSplitter.splitParallelopipeds();
        wrongPolyhedra = Euclidean3DSpace.getPolyhedra().stream()
                .filter(polyhedra -> polyhedra.getTemperature() != temperature || polyhedra.getMaterial() != material)
                .collect(Collectors.toList());
        if (wrongPolyhedra.size() == 0) {
            System.out.println(Color.GREEN + "Parallelepiped splitting does preserve temperature or material" + Color.RESET);
        } else {
            System.out.println(Color.RED + "Parallelepiped splitting doesn't preserve temperature or material" + Color.RESET);
        }

    }
}
