package simulations.SampleV5;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.Parallelepiped;
import custom.objects.dimensions3.Polyhedron;
import custom.objects.dimensions3.TriangularPrism;
import custom.objects.dimensions3.TriangularPyramid;
import custom.objects.temperature.diffusion.Materials;
import custom.space.Euclidean3DSelector;
import custom.space.Euclidean3DSpace;
import custom.utils.VectorUtils;
import simulations.SampleV1Constructor;

import java.util.Collection;
import java.util.stream.Collectors;

public class SampleV5Constructor {

    private static double T = 298d;
    private static Vector height = new Vector(0, 0, 2.7e-9);
    private static double lengthBar = 30e-6;
    private static double widthBar = 4e-6;
    private static double regionX = 60e-6;
    private static double regionY = 12e-6;
    private static double regionZ = 1e-6;
    private static double height1 = 38.8385e-6;
    private static double width1 = 15.8817e-6;
    private static double width2 = 31.5209e-6;

    public static Vector supportHeight = new Vector(0, 0, -regionZ);


    //----------------- 12C ----------------
    private static Point C11 = new Point(-lengthBar * 3 / 2 - widthBar, widthBar / 2, 0);
    private static Point C12 = new Point(-lengthBar / 2 - widthBar, widthBar / 2, 0);
    private static Point C13 = new Point(-lengthBar / 2, widthBar / 2, 0);
    private static Point C14 = new Point(lengthBar / 2, widthBar / 2, 0);
    private static Point C15 = new Point(lengthBar / 2 + widthBar, widthBar / 2, 0);
    private static Point C16 = new Point(lengthBar * 3 / 2 + widthBar, widthBar / 2, 0);

    private static Point C21 = new Point(-lengthBar * 3 / 2 - widthBar, -widthBar / 2, 0);
    private static Point C22 = new Point(-lengthBar / 2 - widthBar, -widthBar / 2, 0);
    private static Point C23 = new Point(-lengthBar / 2, -widthBar / 2, 0);
    private static Point C24 = new Point(lengthBar / 2, -widthBar / 2, 0);
    private static Point C25 = new Point(lengthBar / 2 + widthBar, -widthBar / 2, 0);
    private static Point C26 = new Point(lengthBar * 3 / 2 + widthBar, -widthBar / 2, 0);


    //----------------- 16P ----------------
    private static Point P13 = new Point(-width1, regionY, 0);
    private static Point P14 = new Point(-width2, regionY, 0);
    private static Point P15 = new Point(-regionX, regionY, 0);

    private static Point P23 = new Point(width1, regionY, 0);
    private static Point P24 = new Point(width2, regionY, 0);
    private static Point P25 = new Point(regionX, regionY, 0);

    private static Point P33 = new Point(width1, -regionY, 0);
    private static Point P34 = new Point(width2, -regionY, 0);
    private static Point P35 = new Point(regionX, -regionY, 0);

    private static Point P43 = new Point(-width1, -regionY, 0);
    private static Point P44 = new Point(-width2, -regionY, 0);
    private static Point P45 = new Point(-regionX, -regionY, 0);

    private static Point P52 = new Point(regionX, height1, 0);
    private static Point P53 = new Point(regionX, -height1, 0);

    private static Point P62 = new Point(-regionX, height1, 0);
    private static Point P63 = new Point(-regionX, -height1, 0);

    //----------------- 6A ----------------
    private static Point A1 = VectorUtils.meanPoint(P13, P14);
    private static Point A2 = VectorUtils.meanPoint(P23, P24);
    private static Point A3 = VectorUtils.meanPoint(P33, P34);
    private static Point A4 = VectorUtils.meanPoint(P43, P44);
    private static Point A5 = new Point(regionX, 0, 0);
    private static Point A6 = new Point(-regionX, 0, 0);

    //----------------- 2EA ----------------
    private static Point EA5 = new Point(0, regionY, 0);
    private static Point EA6 = new Point(0, -regionY, 0);


    public static void createSampleV5() {
        //552 tetrahedron
        createSampleLikeRegion(height, T, Materials.TemperatureDiffusion.LaAlO3); //204 tetrahedron
        createSampleLikeRegion(supportHeight, T, Materials.TemperatureDiffusion.SrTiO3); //204 tetrahedron
        createExteriorOfSampleLikeRegion(supportHeight, T, Materials.TemperatureDiffusion.SrTiO3); //144 tetrahedron
    }

    //204 tetrahedron
    private static void createSampleLikeRegion(Vector height, double temperature, Materials.TemperatureDiffusion material) {
        //----------------- 18T ----------------
        //54 square pyramids 36 tetrahedron
        //144 tetrahedron
        TriangularPrism T11 = Euclidean3DSpace.getOrCreateTriangularPrism(P13, C13, A1, height, material, temperature);
        TriangularPrism T12 = Euclidean3DSpace.getOrCreateTriangularPrism(C13, A1, C12, height, material, temperature);
        TriangularPrism T13 = Euclidean3DSpace.getOrCreateTriangularPrism(A1, C12, P14, height, material, temperature);

        TriangularPrism T21 = Euclidean3DSpace.getOrCreateTriangularPrism(P23, C14, A2, height, material, temperature);
        TriangularPrism T22 = Euclidean3DSpace.getOrCreateTriangularPrism(C14, A2, C15, height, material, temperature);
        TriangularPrism T23 = Euclidean3DSpace.getOrCreateTriangularPrism(A2, C15, P24, height, material, temperature);

        TriangularPrism T31 = Euclidean3DSpace.getOrCreateTriangularPrism(P33, C24, A3, height, material, temperature);
        TriangularPrism T32 = Euclidean3DSpace.getOrCreateTriangularPrism(C24, A3, C25, height, material, temperature);
        TriangularPrism T33 = Euclidean3DSpace.getOrCreateTriangularPrism(A3, C25, P34, height, material, temperature);

        TriangularPrism T41 = Euclidean3DSpace.getOrCreateTriangularPrism(P43, C23, A4, height, material, temperature);
        TriangularPrism T42 = Euclidean3DSpace.getOrCreateTriangularPrism(C23, A4, C22, height, material, temperature);
        TriangularPrism T43 = Euclidean3DSpace.getOrCreateTriangularPrism(A4, C22, P44, height, material, temperature);

        TriangularPrism T51 = Euclidean3DSpace.getOrCreateTriangularPrism(P52, C16, A5, height, material, temperature);
        TriangularPrism T52 = Euclidean3DSpace.getOrCreateTriangularPrism(C16, A5, C26, height, material, temperature);
        TriangularPrism T53 = Euclidean3DSpace.getOrCreateTriangularPrism(A5, C26, P53, height, material, temperature);

        TriangularPrism T61 = Euclidean3DSpace.getOrCreateTriangularPrism(P62, C11, A6, height, material, temperature);
        TriangularPrism T62 = Euclidean3DSpace.getOrCreateTriangularPrism(C11, A6, C21, height, material, temperature);
        TriangularPrism T63 = Euclidean3DSpace.getOrCreateTriangularPrism(A6, C21, P63, height, material, temperature);

        //----------------- 5PC ----------------
        //60 tetrahedron
        Parallelepiped PC1 = SampleV1Constructor.getOrCreateParallelepiped(C11, C12, C21, height, material, temperature);
        Parallelepiped PC2 = SampleV1Constructor.getOrCreateParallelepiped(C12, C13, C22, height, material, temperature);
        Parallelepiped PC3 = SampleV1Constructor.getOrCreateParallelepiped(C13, C14, C23, height, material, temperature);
        Parallelepiped PC4 = SampleV1Constructor.getOrCreateParallelepiped(C14, C15, C24, height, material, temperature);
        Parallelepiped PC5 = SampleV1Constructor.getOrCreateParallelepiped(C15, C16, C25, height, material, temperature);


    }

    //144 tetrahedron
    private static void createExteriorOfSampleLikeRegion(Vector height, double temperature, Materials.TemperatureDiffusion material) {
        //----------------- 18 ET ----------------
        //54 square pyramids 36 tetrahedron
        //144 tetrahedron
        TriangularPrism ET11 = Euclidean3DSpace.getOrCreateTriangularPrism(C12, C11, P14, height, material, temperature);
        TriangularPrism ET12 = Euclidean3DSpace.getOrCreateTriangularPrism(C11, P14, P62, height, material, temperature);
        TriangularPrism ET13 = Euclidean3DSpace.getOrCreateTriangularPrism(P14, P62, P15, height, material, temperature);

        TriangularPrism ET21 = Euclidean3DSpace.getOrCreateTriangularPrism(C15, C16, P24, height, material, temperature);
        TriangularPrism ET22 = Euclidean3DSpace.getOrCreateTriangularPrism(C16, P24, P52, height, material, temperature);
        TriangularPrism ET23 = Euclidean3DSpace.getOrCreateTriangularPrism(P24, P52, P25, height, material, temperature);

        TriangularPrism ET31 = Euclidean3DSpace.getOrCreateTriangularPrism(C25, C26, P34, height, material, temperature);
        TriangularPrism ET32 = Euclidean3DSpace.getOrCreateTriangularPrism(C26, P34, P53, height, material, temperature);
        TriangularPrism ET33 = Euclidean3DSpace.getOrCreateTriangularPrism(P34, P53, P35, height, material, temperature);

        TriangularPrism ET41 = Euclidean3DSpace.getOrCreateTriangularPrism(C22, C21, P44, height, material, temperature);
        TriangularPrism ET42 = Euclidean3DSpace.getOrCreateTriangularPrism(C21, P44, P63, height, material, temperature);
        TriangularPrism ET43 = Euclidean3DSpace.getOrCreateTriangularPrism(P44, P63, P45, height, material, temperature);


        TriangularPrism ET51 = Euclidean3DSpace.getOrCreateTriangularPrism(P13, C13, EA5, height, material, temperature);
        TriangularPrism ET52 = Euclidean3DSpace.getOrCreateTriangularPrism(C13, C14, EA5, height, material, temperature);
        TriangularPrism ET53 = Euclidean3DSpace.getOrCreateTriangularPrism(C14, P23, EA5, height, material, temperature);

        TriangularPrism ET61 = Euclidean3DSpace.getOrCreateTriangularPrism(P43, C23, EA6, height, material, temperature);
        TriangularPrism ET62 = Euclidean3DSpace.getOrCreateTriangularPrism(C23, C24, EA6, height, material, temperature);
        TriangularPrism ET63 = Euclidean3DSpace.getOrCreateTriangularPrism(C24, P33, EA6, height, material, temperature);

    }

    public static Collection<Polyhedron> selectR0() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideR0)
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectR1() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideR1)
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectR2() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideR2)
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectR3() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideR3)
                .collect(Collectors.toList());
    }

    public static boolean isPointInsideR0(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(C21, C11, C26, height, triangularPyramid.getCentroid());
    }

    private static boolean isPointInsideR1AndR0(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(A1, A2, A4, height, triangularPyramid.getCentroid());
    }

    public static boolean isPointInsideR1(TriangularPyramid triangularPyramid) {
        boolean isPointInsideR0 = isPointInsideR0(triangularPyramid);
        boolean isPointInsideR1AndR0 = isPointInsideR1AndR0(triangularPyramid);
        return isPointInsideR1AndR0 && !isPointInsideR0;
    }

    public static boolean isPointInsideR2(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(C21, C11, C26, supportHeight, triangularPyramid.getCentroid());
    }

    public static boolean isPointInsideR3AndR2(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(A1, A2, A4, supportHeight, triangularPyramid.getCentroid());
    }

    public static boolean isPointInsideR3(TriangularPyramid triangularPyramid) {
        boolean isPointInsideR2 = isPointInsideR2(triangularPyramid);
        boolean isPointInsideR3AndR2 = isPointInsideR3AndR2(triangularPyramid);
        if (!isPointInsideR3AndR2 && isPointInsideR2)
            throw new IllegalStateException("Region 32 should contain region2");
        return isPointInsideR3AndR2 && !isPointInsideR2;
    }

    public static Collection<Polyhedron> selectT1() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isCentroidInsideT1)
                .collect(Collectors.toList());
    }

    public static boolean isCentroidInsideT1(TriangularPyramid triangularPyramid) {
        return isCentroidInsideTX(triangularPyramid.getCentroid(), P13, C13, A1, C12, P14, height);
    }


    public static Collection<Polyhedron> selectT2() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isCentroidInsideT2)
                .collect(Collectors.toList());
    }

    public static boolean isCentroidInsideT2(TriangularPyramid triangularPyramid) {
        return isCentroidInsideTX(triangularPyramid.getCentroid(), P23, C14, A2, C15, P24, height);
    }

    public static Collection<Polyhedron> selectT3() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isCentroidInsideT3)
                .collect(Collectors.toList());
    }

    public static boolean isCentroidInsideT3(TriangularPyramid triangularPyramid) {
        return isCentroidInsideTX(triangularPyramid.getCentroid(), P33, C24, A3, C25, P34, height);
    }

    public static Collection<Polyhedron> selectT4() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isCentroidInsideT4)
                .collect(Collectors.toList());
    }

    public static boolean isCentroidInsideT4(TriangularPyramid triangularPyramid) {
        return isCentroidInsideTX(triangularPyramid.getCentroid(), P43, C23, A4, C22, P44, height);
    }

    public static Collection<Polyhedron> selectT5() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isCentroidInsideT5)
                .collect(Collectors.toList());
    }

    public static boolean isCentroidInsideT5(TriangularPyramid triangularPyramid) {
        return isCentroidInsideTX(triangularPyramid.getCentroid(), P52, C16, A5, C26, P53, height);
    }

    public static Collection<Polyhedron> selectT6() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isCentroidInsideT6)
                .collect(Collectors.toList());
    }

    public static boolean isCentroidInsideT6(TriangularPyramid triangularPyramid) {
        return isCentroidInsideTX(triangularPyramid.getCentroid(), P62, C11, A6, C21, P63, height);
    }

    private static boolean isCentroidInsideTX(Point centroid, Point point0, Point point1, Point point2, Point point3, Point point4, Vector height) {
        boolean isInsideT11 = Euclidean3DSelector.isCentroidInsideVerticalTriangularPrism(
                point0,
                point1,
                point2,
                height,
                centroid
        );
        boolean isInsideT12 = Euclidean3DSelector.isCentroidInsideVerticalTriangularPrism(
                point1,
                point2,
                point3,
                height,
                centroid
        );
        boolean isInsideT13 = Euclidean3DSelector.isCentroidInsideVerticalTriangularPrism(
                point2,
                point3,
                point4,
                height,
                centroid
        );
        return isInsideT11 || isInsideT12 || isInsideT13;
    }

    public static Collection<Polyhedron> selectC1() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideC1)
                .collect(Collectors.toList());
    }

    public static boolean isPointInsideC1(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(C11, C12, C21, height, triangularPyramid.getCentroid());
    }

    public static Collection<Polyhedron> selectC2() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideC2)
                .collect(Collectors.toList());
    }

    public static boolean isPointInsideC2(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(C12, C13, C22, height, triangularPyramid.getCentroid());
    }

    public static Collection<Polyhedron> selectC3() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideC3)
                .collect(Collectors.toList());
    }

    public static boolean isPointInsideC3(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(C13, C14, C23, height, triangularPyramid.getCentroid());
    }

    public static Collection<Polyhedron> selectC4() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideC4)
                .collect(Collectors.toList());
    }

    public static boolean isPointInsideC4(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(C14, C15, C24, height, triangularPyramid.getCentroid());
    }

    public static Collection<Polyhedron> selectC5() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(SampleV5Constructor::isPointInsideC5)
                .collect(Collectors.toList());
    }

    public static boolean isPointInsideC5(TriangularPyramid triangularPyramid) {
        return Euclidean3DSelector.isPointInsideCube(C15, C16, C25, height, triangularPyramid.getCentroid());
    }
}
