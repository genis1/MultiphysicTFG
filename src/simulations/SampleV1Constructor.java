package simulations;

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

import java.util.Collection;
import java.util.stream.Collectors;

public class SampleV1Constructor {

    private static double T = 298d;
    public static Vector height = new Vector(0, 0, 2.7e-9);
    private static Vector supportHeight = new Vector(0, 0, -0.5e-3);

    private static double padSide = 800e-6;
    private static double padToCenter = 1000e-6;
    private static double padToCenterY = padToCenter * Math.sqrt(3) / 2;
    private static double padToCenterX = padToCenter / 2;
    public static double lengthBar = 30e-6;
    public static double widthBar = 4e-6;
    private static double xSpacing = padToCenter / 2;
    private static double ySpacing = xSpacing;

    private static double sampleWidth = padToCenter + padSide / 2;
    private static double sampleHeight = padToCenterY + padSide / 2;
    private static Vector topMargin = new Vector(0, 5e-3 - sampleHeight, 0);
    private static Vector bottomMargin = new Vector(0, -(5e-3 - sampleHeight), 0);
    private static Vector leftMargin = new Vector(-(5e-3 - sampleWidth), 0, 0);
    private static Vector rightMargin = new Vector(5e-3 - sampleWidth, 0, 0);

    //----------------- 28 P ----------------
    private static Point P11 = new Point(-padToCenterX - padSide / 2, padToCenterY + padSide / 2, 0);
    private static Point P12 = new Point(-padToCenterX + padSide / 2, padToCenterY + padSide / 2, 0);
    private static Point P13 = new Point(-padToCenterX + padSide / 2, padToCenterY - padSide / 2 + ySpacing, 0);
    private static Point P14 = new Point(-padToCenterX + padSide / 2 - xSpacing, padToCenterY - padSide / 2, 0);
    private static Point P15 = new Point(-padToCenterX - padSide / 2, padToCenterY - padSide / 2, 0);

    private static Point P21 = new Point(padToCenterX + padSide / 2, padToCenterY + padSide / 2, 0);
    private static Point P22 = new Point(padToCenterX - padSide / 2, padToCenterY + padSide / 2, 0);
    private static Point P23 = new Point(padToCenterX - padSide / 2, padToCenterY - padSide / 2 + ySpacing, 0);
    private static Point P24 = new Point(padToCenterX - padSide / 2 + xSpacing, padToCenterY - padSide / 2, 0);
    private static Point P25 = new Point(padToCenterX + padSide / 2, padToCenterY - padSide / 2, 0);

    private static Point P31 = new Point(padToCenterX + padSide / 2, -padToCenterY - padSide / 2, 0);
    private static Point P32 = new Point(padToCenterX - padSide / 2, -padToCenterY - padSide / 2, 0);
    private static Point P33 = new Point(padToCenterX - padSide / 2, -padToCenterY + padSide / 2 - ySpacing, 0);
    private static Point P34 = new Point(padToCenterX - padSide / 2 + xSpacing, -padToCenterY + padSide / 2, 0);
    private static Point P35 = new Point(padToCenterX + padSide / 2, -padToCenterY + padSide / 2, 0);

    private static Point P41 = new Point(-padToCenterX - padSide / 2, -padToCenterY - padSide / 2, 0);
    private static Point P42 = new Point(-padToCenterX + padSide / 2, -padToCenterY - padSide / 2, 0);
    private static Point P43 = new Point(-padToCenterX + padSide / 2, -padToCenterY + padSide / 2 - ySpacing, 0);
    private static Point P44 = new Point(-padToCenterX + padSide / 2 - xSpacing, -padToCenterY + padSide / 2, 0);
    private static Point P45 = new Point(-padToCenterX - padSide / 2, -padToCenterY + padSide / 2, 0);

    private static Point P51 = new Point(padToCenter + padSide / 2, padSide / 2, 0);
    private static Point P52 = new Point(padToCenter - padSide / 2, padSide / 2, 0);
    private static Point P53 = new Point(padToCenter - padSide / 2, -padSide / 2, 0);
    private static Point P54 = new Point(padToCenter + padSide / 2, -padSide / 2, 0);

    private static Point P61 = new Point(-padToCenter - padSide / 2, padSide / 2, 0);
    private static Point P62 = new Point(-padToCenter + padSide / 2, padSide / 2, 0);
    private static Point P63 = new Point(-padToCenter + padSide / 2, -padSide / 2, 0);
    private static Point P64 = new Point(-padToCenter - padSide / 2, -padSide / 2, 0);

    //----------------- 6A ----------------
    private static Point A1 = new Point(-padToCenterX + padSide / 2, padToCenterY - padSide / 2, 0);
    private static Point A2 = new Point(padToCenterX - padSide / 2, padToCenterY - padSide / 2, 0);
    private static Point A3 = new Point(padToCenterX - padSide / 2, -padToCenterY + padSide / 2, 0);
    private static Point A4 = new Point(-padToCenterX + padSide / 2, -padToCenterY + padSide / 2, 0);
    private static Point A5 = new Point(padToCenter - padSide / 2, 0, 0);
    private static Point A6 = new Point(-padToCenter + padSide / 2, 0, 0);

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

    //----------------- 6 EA ----------------
    private static Point EA1 = new Point(-padToCenter - padSide / 2, padToCenterY + padSide / 2, 0);
    private static Point EA2 = new Point(padToCenter + padSide / 2, padToCenterY + padSide / 2, 0);
    private static Point EA3 = new Point(padToCenter + padSide / 2, -padToCenterY - padSide / 2, 0);
    private static Point EA4 = new Point(-padToCenter - padSide / 2, -padToCenterY - padSide / 2, 0);
    private static Point EA5 = new Point(0, padToCenterY - padSide / 2 + ySpacing, 0);
    private static Point EA6 = new Point(0, -padToCenterY + padSide / 2 - ySpacing, 0);

    public static void createSampleV1() {
        //1080 tetrahedron
        createSampleLikeRegion(height, T, Materials.TemperatureDiffusion.LaAlO3); //276 tetrahedron
        createSampleLikeRegion(supportHeight, T, Materials.TemperatureDiffusion.SrTiO3); //276 tetrahedron
        createExteriorOfSampleLikeRegion(supportHeight, T, Materials.TemperatureDiffusion.SrTiO3); //528 tetrahedron
    }


    //276 tetrahedron
    private static void createSampleLikeRegion(Vector height, double temperature, Materials.TemperatureDiffusion material) {
        //----------------- 6Q ----------------
        //36 square pyramids
        //72 tetrahedron
        Parallelepiped Q1 = getOrCreateParallelepiped(P11, P12, P15, height, material, temperature);
        Parallelepiped Q2 = getOrCreateParallelepiped(P21, P22, P25, height, material, temperature);
        Parallelepiped Q3 = getOrCreateParallelepiped(P31, P32, P35, height, material, temperature);
        Parallelepiped Q4 = getOrCreateParallelepiped(P41, P42, P45, height, material, temperature);
        Parallelepiped Q5 = getOrCreateParallelepiped(P51, P52, P54, height, material, temperature);
        Parallelepiped Q6 = getOrCreateParallelepiped(P61, P62, P64, height, material, temperature);

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
        Parallelepiped PC1 = getOrCreateParallelepiped(C11, C12, C21, height, material, temperature);
        Parallelepiped PC2 = getOrCreateParallelepiped(C12, C13, C22, height, material, temperature);
        Parallelepiped PC3 = getOrCreateParallelepiped(C13, C14, C23, height, material, temperature);
        Parallelepiped PC4 = getOrCreateParallelepiped(C14, C15, C24, height, material, temperature);
        Parallelepiped PC5 = getOrCreateParallelepiped(C15, C16, C25, height, material, temperature);


    }

    //528 tetrahedron
    private static void createExteriorOfSampleLikeRegion(Vector height, double temperature, Materials.TemperatureDiffusion material) {
        //----------------- 6x6 ET ----------------
        //108 square pyramids 72 tetrahedron
        //288 tetrahedron
        TriangularPrism ET11 = Euclidean3DSpace.getOrCreateTriangularPrism(C12, C11, P14, height, material, temperature);
        TriangularPrism ET12 = Euclidean3DSpace.getOrCreateTriangularPrism(C11, P14, P62, height, material, temperature);
        TriangularPrism ET13 = Euclidean3DSpace.getOrCreateTriangularPrism(P14, P62, P15, height, material, temperature);
        TriangularPrism ET14 = Euclidean3DSpace.getOrCreateTriangularPrism(P62, P15, P61, height, material, temperature);
        TriangularPrism ET15 = Euclidean3DSpace.getOrCreateTriangularPrism(P15, P61, P11, height, material, temperature);
        TriangularPrism ET16 = Euclidean3DSpace.getOrCreateTriangularPrism(P61, P11, EA1, height, material, temperature);

        TriangularPrism ET21 = Euclidean3DSpace.getOrCreateTriangularPrism(C15, C16, P24, height, material, temperature);
        TriangularPrism ET22 = Euclidean3DSpace.getOrCreateTriangularPrism(C16, P24, P52, height, material, temperature);
        TriangularPrism ET23 = Euclidean3DSpace.getOrCreateTriangularPrism(P24, P52, P25, height, material, temperature);
        TriangularPrism ET24 = Euclidean3DSpace.getOrCreateTriangularPrism(P52, P25, P51, height, material, temperature);
        TriangularPrism ET25 = Euclidean3DSpace.getOrCreateTriangularPrism(P25, P51, P21, height, material, temperature);
        TriangularPrism ET26 = Euclidean3DSpace.getOrCreateTriangularPrism(P51, P21, EA2, height, material, temperature);

        TriangularPrism ET31 = Euclidean3DSpace.getOrCreateTriangularPrism(C25, C26, P34, height, material, temperature);
        TriangularPrism ET32 = Euclidean3DSpace.getOrCreateTriangularPrism(C26, P34, P53, height, material, temperature);
        TriangularPrism ET33 = Euclidean3DSpace.getOrCreateTriangularPrism(P34, P53, P35, height, material, temperature);
        TriangularPrism ET34 = Euclidean3DSpace.getOrCreateTriangularPrism(P53, P35, P54, height, material, temperature);
        TriangularPrism ET35 = Euclidean3DSpace.getOrCreateTriangularPrism(P35, P54, P31, height, material, temperature);
        TriangularPrism ET36 = Euclidean3DSpace.getOrCreateTriangularPrism(P54, P31, EA3, height, material, temperature);

        TriangularPrism ET41 = Euclidean3DSpace.getOrCreateTriangularPrism(C22, C21, P44, height, material, temperature);
        TriangularPrism ET42 = Euclidean3DSpace.getOrCreateTriangularPrism(C21, P44, P63, height, material, temperature);
        TriangularPrism ET43 = Euclidean3DSpace.getOrCreateTriangularPrism(P44, P63, P45, height, material, temperature);
        TriangularPrism ET44 = Euclidean3DSpace.getOrCreateTriangularPrism(P63, P45, P64, height, material, temperature);
        TriangularPrism ET45 = Euclidean3DSpace.getOrCreateTriangularPrism(P45, P64, P41, height, material, temperature);
        TriangularPrism ET46 = Euclidean3DSpace.getOrCreateTriangularPrism(P64, P41, EA4, height, material, temperature);


        TriangularPrism ET51 = Euclidean3DSpace.getOrCreateTriangularPrism(P13, C13, EA5, height, material, temperature);
        TriangularPrism ET52 = Euclidean3DSpace.getOrCreateTriangularPrism(C13, C14, EA5, height, material, temperature);
        TriangularPrism ET53 = Euclidean3DSpace.getOrCreateTriangularPrism(C14, P23, EA5, height, material, temperature);

        TriangularPrism ET54 = Euclidean3DSpace.getOrCreateTriangularPrism(P13, P12, EA5, height, material, temperature);
        TriangularPrism ET55 = Euclidean3DSpace.getOrCreateTriangularPrism(P12, P22, EA5, height, material, temperature);
        TriangularPrism ET56 = Euclidean3DSpace.getOrCreateTriangularPrism(P22, P23, EA5, height, material, temperature);

        TriangularPrism ET61 = Euclidean3DSpace.getOrCreateTriangularPrism(P43, C23, EA6, height, material, temperature);
        TriangularPrism ET62 = Euclidean3DSpace.getOrCreateTriangularPrism(C23, C24, EA6, height, material, temperature);
        TriangularPrism ET63 = Euclidean3DSpace.getOrCreateTriangularPrism(C24, P33, EA6, height, material, temperature);

        TriangularPrism ET64 = Euclidean3DSpace.getOrCreateTriangularPrism(P43, P42, EA6, height, material, temperature);
        TriangularPrism ET65 = Euclidean3DSpace.getOrCreateTriangularPrism(P42, P32, EA6, height, material, temperature);
        TriangularPrism ET66 = Euclidean3DSpace.getOrCreateTriangularPrism(P32, P33, EA6, height, material, temperature);

        //240 tetrahedron
        createSupportOfSample(height, temperature, material);
    }

    private static void createSupportOfSample(Vector height, double temperature, Materials.TemperatureDiffusion material) {
        //----------------- 20 EQ ----------------
        //120 square pyramids
        //240 tetrahedron

        //----------------- Top Row ----------------
        Parallelepiped EQ1 = Euclidean3DSpace.getOrCreateParallelepiped(EA1, leftMargin, topMargin, height, material, temperature);
        Parallelepiped EQ2 = Euclidean3DSpace.getOrCreateParallelepiped(EA1, VectorUtils.subtraction(P11, EA1), topMargin, height, material, temperature);
        Parallelepiped EQ3 = Euclidean3DSpace.getOrCreateParallelepiped(P11, VectorUtils.subtraction(P12, P11), topMargin, height, material, temperature);
        Parallelepiped EQ4 = Euclidean3DSpace.getOrCreateParallelepiped(P12, VectorUtils.subtraction(P22, P12), topMargin, height, material, temperature);
        Parallelepiped EQ5 = Euclidean3DSpace.getOrCreateParallelepiped(P22, VectorUtils.subtraction(P21, P22), topMargin, height, material, temperature);
        Parallelepiped EQ6 = Euclidean3DSpace.getOrCreateParallelepiped(P21, VectorUtils.subtraction(EA2, P21), topMargin, height, material, temperature);
        Parallelepiped EQ7 = Euclidean3DSpace.getOrCreateParallelepiped(EA2, rightMargin, topMargin, height, material, temperature);

        //----------------- Bottom Row ----------------
        Parallelepiped EQ11 = Euclidean3DSpace.getOrCreateParallelepiped(EA3, rightMargin, bottomMargin, height, material, temperature);
        Parallelepiped EQ12 = Euclidean3DSpace.getOrCreateParallelepiped(EA3, VectorUtils.subtraction(P31, EA3), bottomMargin, height, material, temperature);
        Parallelepiped EQ13 = Euclidean3DSpace.getOrCreateParallelepiped(P31, VectorUtils.subtraction(P32, P31), bottomMargin, height, material, temperature);
        Parallelepiped EQ14 = Euclidean3DSpace.getOrCreateParallelepiped(P32, VectorUtils.subtraction(P42, P32), bottomMargin, height, material, temperature);
        Parallelepiped EQ15 = Euclidean3DSpace.getOrCreateParallelepiped(P42, VectorUtils.subtraction(P41, P42), bottomMargin, height, material, temperature);
        Parallelepiped EQ16 = Euclidean3DSpace.getOrCreateParallelepiped(P41, VectorUtils.subtraction(EA4, P41), bottomMargin, height, material, temperature);
        Parallelepiped EQ17 = Euclidean3DSpace.getOrCreateParallelepiped(EA4, leftMargin, bottomMargin, height, material, temperature);

        //----------------- Right Row ----------------
        Parallelepiped EQ8 = Euclidean3DSpace.getOrCreateParallelepiped(EA2, VectorUtils.subtraction(P51, EA2), rightMargin, height, material, temperature);
        Parallelepiped EQ9 = Euclidean3DSpace.getOrCreateParallelepiped(P51, VectorUtils.subtraction(P54, P51), rightMargin, height, material, temperature);
        Parallelepiped EQ10 = Euclidean3DSpace.getOrCreateParallelepiped(P54, VectorUtils.subtraction(EA3, P54), rightMargin, height, material, temperature);

        //----------------- Left Row ----------------
        Parallelepiped EQ18 = Euclidean3DSpace.getOrCreateParallelepiped(EA4, VectorUtils.subtraction(P64, EA4), leftMargin, height, material, temperature);
        Parallelepiped EQ19 = Euclidean3DSpace.getOrCreateParallelepiped(P64, VectorUtils.subtraction(P61, P64), leftMargin, height, material, temperature);
        Parallelepiped EQ20 = Euclidean3DSpace.getOrCreateParallelepiped(P61, VectorUtils.subtraction(EA1, P61), leftMargin, height, material, temperature);
    }

    private static Parallelepiped getOrCreateParallelepiped(Point c11, Point c12, Point c21, Vector height, Materials.TemperatureDiffusion laAlO3, double t) {
        return Euclidean3DSpace.getOrCreateParallelepiped(c11, VectorUtils.subtraction(c12, c11), VectorUtils.subtraction(c21, c11), height, laAlO3, t);
    }

    public static Collection<Polyhedron> selectR0() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> {
                    Point centroid = triangularPyramid.getCentroid();
                    return isPointInsideR0(centroid);
                })
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectR1() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> {
                    Point centroid = triangularPyramid.getCentroid();
                    return isPointInsideR1(centroid);
                })
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectR2() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> {
                    Point centroid = triangularPyramid.getCentroid();
                    return isPointInsideR2(centroid);
                })
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectR3() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> {
                    Point centroid = triangularPyramid.getCentroid();
                    return isPointInsideR3(centroid);
                })
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectR4() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> {
                    Point centroid = triangularPyramid.getCentroid();
                    return isPointInsideR4(centroid);
                })
                .collect(Collectors.toList());
    }

    public static boolean isPointInsideR0(Point centroid) {
        return Euclidean3DSelector.isPointInsideCube(C21, C11, C26, height, centroid);
    }

    private static boolean isPointInsideR1AndR0(Point centroid) {
        return Euclidean3DSelector.isPointInsideCube(EA1, EA2, EA4, height, centroid
        );
    }

    public static boolean isPointInsideR1(Point centroid) {
        boolean isPointInsideR0 = isPointInsideR0(centroid);
        boolean isPointInsideR1AndR0 = isPointInsideR1AndR0(centroid);
        return isPointInsideR1AndR0 && !isPointInsideR0;
    }

    public static boolean isPointInsideR2(Point centroid) {
        return Euclidean3DSelector.isPointInsideCube(C21, C11, C26, supportHeight.divide(2), centroid);
    }

    public static boolean isPointInsideR3AndR2(Point centroid) {
        return Euclidean3DSelector.isPointInsideCube(EA1, EA2, EA4, supportHeight.divide(2), centroid);
    }

    public static boolean isPointInsideR3(Point centroid) {
        boolean isPointInsideR2 = isPointInsideR2(centroid);
        boolean isPointInsideR3AndR2 = isPointInsideR3AndR2(centroid);
        if (!isPointInsideR3AndR2 && isPointInsideR2)
            throw new IllegalStateException("Region 32 should contain region2");
        return isPointInsideR3AndR2 && !isPointInsideR2;
    }

    public static boolean isPointInsideR4AndR3AndR2(Point centroid) {
        return Euclidean3DSelector.isPointInsideCube(EA1.add(leftMargin).add(topMargin), EA2.add(topMargin).add(rightMargin), EA4.add(leftMargin).add(bottomMargin), supportHeight, centroid);
    }

    public static boolean isPointInsideR4(Point centroid) {
        boolean isPointInsideR3AndR2 = isPointInsideR3AndR2(centroid);
        boolean isPointInsideR4AndR3AndR2 = isPointInsideR4AndR3AndR2(centroid);
        if (isPointInsideR3AndR2 && !isPointInsideR4AndR3AndR2)
            throw new IllegalStateException("Region 432 should contain region32");
        return isPointInsideR4AndR3AndR2 && !isPointInsideR3AndR2;
    }


    public static Collection<Polyhedron> selectT1() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> isCentroidInsideTX(triangularPyramid.getCentroid(), P13, C13, A1, C12, P14, height))
                .collect(Collectors.toList());
    }


    public static Collection<Polyhedron> selectT2() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> isCentroidInsideTX(triangularPyramid.getCentroid(), P23, C14, A2, C15, P24, height))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectT3() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> isCentroidInsideTX(triangularPyramid.getCentroid(), P33, C24, A3, C25, P34, height))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectT4() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> isCentroidInsideTX(triangularPyramid.getCentroid(), P43, C23, A4, C22, P44, height))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectT5() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> isCentroidInsideTX(triangularPyramid.getCentroid(), P52, C16, A5, C26, P53, height))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectT6() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> isCentroidInsideTX(triangularPyramid.getCentroid(), P62, C11, A6, C21, P63, height))
                .collect(Collectors.toList());
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

    public static Collection<Polyhedron> selectQ1() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(P11, P12, P15, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectQ2() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(P21, P22, P25, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectQ3() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(P31, P32, P35, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectQ4() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(P41, P42, P45, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectQ5() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(P51, P52, P54, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectQ6() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(P61, P62, P64, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }

    public static Collection<Polyhedron> selectC1() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(C11, C12, C21, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }
    public static Collection<Polyhedron> selectC2() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(C12, C13, C22, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }
    public static Collection<Polyhedron> selectC3() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(C13, C14, C23, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }
    public static Collection<Polyhedron> selectC4() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(C14, C15, C24, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }
    public static Collection<Polyhedron> selectC5() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(C15, C16, C25, height, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }

}
