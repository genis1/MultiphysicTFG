package simulations.SampleV3;

import custom.objects.dimensions1.Edge;
import custom.objects.dimensions3.TriangularPyramid;
import custom.objects.temperature.diffusion.Materials;
import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DSplitter;
import custom.space.Euclidean3DTimeStepper;
import simulations.SampleV1Constructor;

public class SampleV3Simulator {

    private static String SAMPLE_V3_INITIAL_MESH = "sampleV3InitialMesh";
    private static String SAMPLE_V3_SPLITTED_MESH = "sampleV3SplittedMesh";
    private static String SAMPLE_V3_SIMULATION = "sampleV3";
    private static double timeStep = 1e-15;
    private static double heatPower = 0.2e6 * 500e-9;


    public static void main(String[] args) {


        //createInitialMesh();
        //System.out.println("Initial grid created");
        //One trigonal prism was removed manually

        //splitMesh();
        //System.out.println("Grid splitted");

        Euclidean3DSpace.clean();
        runSimulation();

        System.out.println("execution done");
    }

    public static void createInitialMesh() {
        SampleV3Constructor.createSampleV3();

        Euclidean3DSplitter.simplifyGrid();

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, timeStep);
        Euclidean3DInputOutput.save(SAMPLE_V3_INITIAL_MESH);
    }

    public static void splitMesh() {
        Euclidean3DSpace.clean();
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V3_INITIAL_MESH);

        while (Euclidean3DSpace.getPolyhedra().size() < 1e4) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdge();
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("Splitted");

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        Euclidean3DInputOutput.save(SAMPLE_V3_SPLITTED_MESH);
    }

    private static void runSimulation() {
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V3_SPLITTED_MESH);
        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        int counter = 0;
        long startTime = System.nanoTime();
        SampleV3Reader sampleV3Reader = new SampleV3Reader(SAMPLE_V3_SIMULATION);
        while (Euclidean3DTimeStepper.getTime() < 100) {
            Euclidean3DTimeStepper.computeTemperatureStepWithHeatSource(SampleV3Simulator::heatSource);
            counter++;
            currentTime = Euclidean3DTimeStepper.getTime();
            if (counter <= 1e1 ||
                    (counter <= 1e2 && counter % 1e1 == 0) ||
                    (counter <= 1e3 && counter % 1e2 == 0) ||
                    (counter <= 1e4 && counter % 1e3 == 0) ||
                    (counter <= 1e4 && counter % 1e3 == 0) ||
                    (counter <= 1e5 && counter % 1e4 == 0) ||
                    (counter <= 1e6 && counter % 1e5 == 0) ||
                    (counter <= 1e7 && counter % 1e6 == 0) ||
                    (counter <= 1e8 && counter % 1e7 == 0) ||
                    (counter <= 1e9 && counter % 1e8 == 0)) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V3_SIMULATION);
                sampleV3Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            }
        }
    }

    public static void heatSource(Double timeStep) {
        Double hallBarVolume = SampleV3Constructor.selectC3().stream().map(TriangularPyramid.class::cast)
                .map((triangularPyramid) -> {
                    if (triangularPyramid.getMaterial() != Materials.TemperatureDiffusion.LaAlO3)
                        throw new IllegalStateException("C3 should be LaAlO3");
                    return triangularPyramid.getVolume();
                })
                .reduce((aDouble, aDouble2) -> aDouble + aDouble2)
                .orElseThrow(() -> new IllegalStateException("C3 should have volume"));
        SampleV1Constructor.selectR0().stream().map(TriangularPyramid.class::cast).forEach(triangularPyramid ->
                triangularPyramid.add_dT(triangularPyramid.getVolume() * heatPower * timeStep / (hallBarVolume * triangularPyramid.getHeatCapacity())));
    }
}
