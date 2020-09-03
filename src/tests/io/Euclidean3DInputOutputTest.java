package tests.io;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions3.TriangularPyramid;
import custom.objects.temperature.diffusion.TemperatureContainer;
import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DTimeStepper;
import external.Color;
import tests.utils.ConstantUtils;

import java.util.Iterator;

public class Euclidean3DInputOutputTest extends ConstantUtils {

    public static void main(String[] args) {
        testWritingReading();
    }

    private static void testWritingReading() {
        //Create object
        Euclidean3DSpace.clean();
        TriangularPyramid pyramid1 = Euclidean3DSpace.getOrCreateTriangularPyramid(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1),
                new Point(0, 0, 0),
                material,
                temperature
        );
        TriangularPyramid pyramid2 = Euclidean3DSpace.getOrCreateTriangularPyramid(
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(0, 0, 1),
                new Point(1, 1, 1),
                material,
                temperature
        );
        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 0.1);

        //Save
        Euclidean3DInputOutput.delete("test");
        Euclidean3DInputOutput.save("test");


        //Read
        Euclidean3DSpace.clean();
        Euclidean3DInputOutput.read("test");


        //Assert process
        Iterator<TemperatureContainer> iterator = Euclidean3DSpace.getPolyhedra().iterator();
        TriangularPyramid newPyramid1 = TriangularPyramid.class.cast(iterator.next());
        TriangularPyramid newPyramid2 = TriangularPyramid.class.cast(iterator.next());

        boolean arePyramids1Equal = ObjectsParsingTest.arePyramidsEqual(pyramid1, newPyramid1);
        boolean arePyramids2Equal = ObjectsParsingTest.arePyramidsEqual(pyramid2, newPyramid2);

        if (arePyramids1Equal && arePyramids2Equal)
            System.out.println(Color.GREEN + "File parsing correct" + Color.RESET);
        else System.out.println(Color.RED + "File parsing incorrect" + Color.RESET);
    }

}
