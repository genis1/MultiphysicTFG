package simulations.SampleV5;

import custom.objects.dimensions1.Edge;
import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DSplitter;
import custom.space.Euclidean3DTimeStepper;
import simulations.SampleV3.SampleV3Simulator;

public class SampleV5Simulator {

    private static String SAMPLE_V5_INITIAL_MESH = "sampleV5InitialMesh";
    private static String SAMPLE_V5_SPLITTED_MESH = "sampleV5SplittedMesh";
    private static String SAMPLE_V5_SIMULATION = "sampleV5";
    private static double timeStep = 1e-15;

    public static void main(String[] args) {


        createInitialMesh();
        System.out.println("Initial grid created");
        //One trigonal prism was removed manually

        splitMesh();
        System.out.println("Grid splitted");

        Euclidean3DSpace.clean();
        runSimulation();

        System.out.println("execution done");
    }

    private static void createInitialMesh() {
        SampleV5Constructor.createSampleV5();

        Euclidean3DSplitter.simplifyGrid();

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, timeStep);
        Euclidean3DInputOutput.save(SAMPLE_V5_INITIAL_MESH);
    }

    private static void splitMesh() {
        Euclidean3DSpace.clean();
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V5_INITIAL_MESH);

        while (Euclidean3DSpace.getPolyhedra().size() < 1e4) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdge();
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("Splitted");

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        Euclidean3DInputOutput.save(SAMPLE_V5_SPLITTED_MESH);
    }

    private static void runSimulation() {
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V5_SPLITTED_MESH);
        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        int counter = 0;
        long startTime = System.nanoTime();
        SampleV5Reader sampleV5Reader = new SampleV5Reader(SAMPLE_V5_SIMULATION);
        while (Euclidean3DTimeStepper.getTime() < 100) {
            Euclidean3DTimeStepper.computeTemperatureStepWithHeatSource(SampleV3Simulator::heatSource);
            counter++;
            currentTime = Euclidean3DTimeStepper.getTime();
            if ((counter <= 1e1) ||
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
                Euclidean3DInputOutput.save(SAMPLE_V5_SIMULATION);
                sampleV5Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            }
        }
    }
}
