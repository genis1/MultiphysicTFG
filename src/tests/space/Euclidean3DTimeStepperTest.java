package tests.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.temperature.diffusion.Materials;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DSplitter;
import custom.space.Euclidean3DTimeStepper;
import custom.utils.VectorUtils;
import tests.utils.ConstantUtils;

public class Euclidean3DTimeStepperTest extends ConstantUtils {

    public static void main(String[] args) {
        //Euclidean3DTimeStepperTest.testCubeEvolution();
        Euclidean3DTimeStepperTest.testCalculatedValue();
        Euclidean3DTimeStepperTest.testCalculatedValueWithDifferentSizesAndMaterials();
        Euclidean3DTimeStepperTest.testCalculatedValueTranslated();
        Euclidean3DTimeStepperTest.testCalculatedValueTranslatedAndRotated();
    }

    private static void testCubeEvolution() {
        //Create mesh and print initial state
        Euclidean3DSpace.clean();
        Euclidean3DSpace.getOrCreateParallelepiped(
                new Point(0, 0, 0),
                new Vector(0.1, 0, 0),
                new Vector(0, 0.1, 0),
                new Vector(0, 0, 0.1),
                material,
                temperature
        );
        Euclidean3DSplitter.simplifyGrid();
        Euclidean3DSplitter.splitLongestEdges(6);
        Euclidean3DSpace.getPolyhedra().iterator().next().setTemperature(400);
        Euclidean3DSpace.printPolyhedron();
        Euclidean3DSpace.getPolyhedra().forEach(temperatureContainer -> System.out.println(temperatureContainer.getVolume()));

        //Evolution and printing
        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 0.01);

        for (int i = 0; i < 100; i++) {
            //Repetition
            Euclidean3DTimeStepper.computeTimeStep();
            Euclidean3DSpace.printPolyhedron();
        }

    }

    /**
     * Caclulated values
     * Base centroid (1/3,1/3,0)
     * T4+ centroid (1/4,1/4,1/4)
     * T4- centroid (1/4,1/4,-1/4)
     * s+ (-1/12,-1/12,1/4)
     * s- (1/12,1/12,1/4)
     * effective distance 1/4+1/4
     * Area 1/2
     * Volume (both) 1/6
     * Power: +-1500
     * C=4.27*10^5
     * Delta T 3.51*10-4
     */
    private static void testCalculatedValue() {
        Point sharedBase0 = new Point(0, 0, 0);
        Point sharedBase1 = new Point(0, 1, 0);
        Point sharedBase2 = new Point(1, 0, 0);
        Point apexP = new Point(0, 0, 1);
        Point apexN = new Point(0, 0, -1);

        Euclidean3DSpace.clean();
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedBase0, sharedBase1, sharedBase2, apexP, material, 400);
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedBase0, sharedBase1, sharedBase2, apexN, material, 200);

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 0.1);
        Euclidean3DTimeStepper.computeTimeStep();
        Euclidean3DSpace.printPolyhedron();
    }

    /**
     * Caclulated values
     * Base centroid (1/3,1/3,0)
     * T4+ centroid (1/4,1/4,1/4)
     * T4- centroid (1/4,1/4,-1/8)
     * s+ (-1/12,-1/12,1/4)
     * s- (1/12,1/12,1/8)
     * effective distance 1/4+1/8
     * Area 1/2
     * Volume+ 1/6
     * Volume- 1/12
     * Power: +-2800
     * C+=4.52*10^5
     * C-=2.14*10^5
     * Delta T1 -5.307*10-4
     * Delta T2 1.124*10-3
     */
    private static void testCalculatedValueWithDifferentSizesAndMaterials() {
        Point sharedBase0 = new Point(0, 0, 0);
        Point sharedBase1 = new Point(0, 1, 0);
        Point sharedBase2 = new Point(1, 0, 0);
        Point apexP = new Point(0, 0, 1);
        Point apexN = new Point(0, 0, -1d/2);

        Euclidean3DSpace.clean();
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedBase0, sharedBase1, sharedBase2, apexP,  Materials.TemperatureDiffusion.SrTiO3, 400);
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedBase0, sharedBase1, sharedBase2, apexN, Materials.TemperatureDiffusion.LaAlO3, 200);

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 0.1);
        Euclidean3DTimeStepper.computeTimeStep();
        Euclidean3DSpace.printPolyhedron();
    }

    private static void testCalculatedValueTranslated() {
        Point sharedBase0 = new Point(0 + 0.23, 0 - 0.5, 0 + 0.1);
        Point sharedBase1 = new Point(0 + 0.23, 1 - 0.5, 0 + 0.1);
        Point sharedBase2 = new Point(1 + 0.23, 0 - 0.5, 0 + 0.1);
        Point apexP = new Point(0 + 0.23, 0 - 0.5, 1 + 0.1);
        Point apexN = new Point(0 + 0.23, 0 - 0.5, -1 + 0.1);

        Euclidean3DSpace.clean();
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedBase0, sharedBase1, sharedBase2, apexP, material, 400);
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedBase0, sharedBase1, sharedBase2, apexN, material, 200);

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 0.1);
        Euclidean3DTimeStepper.computeTimeStep();
        Euclidean3DSpace.printPolyhedron();
    }

    private static void testCalculatedValueTranslatedAndRotated() {
        Point sharedBase0 = rotate3Angles(new Point(0 + 0.23, 0 - 0.5, 0 + 0.1));
        Point sharedBase1 = rotate3Angles(new Point(0 + 0.23, 1 - 0.5, 0 + 0.1));
        Point sharedBase2 = rotate3Angles(new Point(1 + 0.23, 0 - 0.5, 0 + 0.1));
        Point apexP = rotate3Angles(new Point(0 + 0.23, 0 - 0.5, 1 + 0.1));
        Point apexN = rotate3Angles(new Point(0 + 0.23, 0 - 0.5, -1 + 0.1));

        Euclidean3DSpace.clean();
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedBase0, sharedBase1, sharedBase2, apexP, material, 400);
        Euclidean3DSpace.getOrCreateTriangularPyramid(sharedBase0, sharedBase1, sharedBase2, apexN, material, 200);

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 0.1);
        Euclidean3DTimeStepper.computeTimeStep();
        Euclidean3DSpace.printPolyhedron();
    }

    private static Point rotateXAxis(Point point, double angleRadian) {
        Vector vector = VectorUtils.subtraction(point, new Point(0, 0, 0));
        double yCoordinate = VectorUtils.dotProduct(vector, new Vector(0, Math.cos(angleRadian), Math.sin(angleRadian)));
        double zCoordinate = VectorUtils.dotProduct(vector, new Vector(0, -Math.sin(angleRadian), Math.cos(angleRadian)));
        return new Point(vector.getXCoordinate(), yCoordinate, zCoordinate);
    }

    private static Point rotateYAxis(Point point, double angleRadian) {
        Vector vector = VectorUtils.subtraction(point, new Point(0, 0, 0));
        double xCoordinate = VectorUtils.dotProduct(vector, new Vector(Math.cos(angleRadian), 0, -Math.sin(angleRadian)));
        double zCoordinate = VectorUtils.dotProduct(vector, new Vector(Math.sin(angleRadian), 0, Math.cos(angleRadian)));
        return new Point(xCoordinate, vector.getYCoordinate(), zCoordinate);
    }

    private static Point rotateZAxis(Point point, double angleRadian) {
        Vector vector = VectorUtils.subtraction(point, new Point(0, 0, 0));
        double xCoordinate = VectorUtils.dotProduct(vector, new Vector(Math.cos(angleRadian), Math.sin(angleRadian), 0));
        double yCoordinate = VectorUtils.dotProduct(vector, new Vector(-Math.sin(angleRadian), Math.cos(angleRadian), 0));
        return new Point(xCoordinate, yCoordinate, vector.getZCoordinate());
    }

    private static Point rotate3Angles(Point point){
        point = rotateXAxis(point, 5);
        point = rotateYAxis(point, 10);
        return rotateZAxis(point, 20);
    }
}
