package simulations.SampleV7;

import custom.objects.dimensions1.Edge;
import custom.space.*;
import simulations.SampleV3.SampleV3Constructor;
import simulations.SampleV3.SampleV3Simulator;

public class SampleV7Simulator {
    private static String SAMPLE_V7_INITIAL_MESH = "sampleV7InitialMesh";
    private static String SAMPLE_V7_SPLITTED_MESH = "sampleV7SplittedMesh";
    private static String SAMPLE_V7_SIMULATION = "sampleV7";
    private static double timeStep = 1e-15;

    private static Euclidean3DVerticalSampler euclidean3DVerticalSampler = new Euclidean3DVerticalSampler(
            SampleV3Constructor.C13,
            SampleV3Constructor.C14,
            SampleV3Constructor.C23,
            SampleV3Constructor.height,
            SampleV7Constructor.SUPPORT_HEIGHT,
            10,
            SAMPLE_V7_SIMULATION
    );


    public static void main(String[] args) {


        //createInitialMesh();
        //System.out.println("Initial grid created");
        //One trigonal prism was removed manually

        //splitMesh();
        //System.out.println("Grid splitted");


        Euclidean3DSpace.clean();
        runSimulation(); //Region temperature resume and evertic PC3 resume.

        System.out.println("execution done");
    }

    public static void createInitialMesh() {
        SampleV7Constructor.createSampleV7();

        Euclidean3DSplitter.simplifyGrid();

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, timeStep);
        Euclidean3DInputOutput.save(SAMPLE_V7_INITIAL_MESH);
    }

    public static void splitMesh() {
        Euclidean3DSpace.clean();
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V7_INITIAL_MESH);

        while (Euclidean3DSpace.getPolyhedra().size() < 1e4) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdge();
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("Splitted");

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        Euclidean3DInputOutput.save(SAMPLE_V7_SPLITTED_MESH);
    }

    private static void runSimulation() {
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V7_SPLITTED_MESH);
        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        int counter = 0;
        long startTime = System.nanoTime();
        SampleV7Reader sampleV7Reader = new SampleV7Reader(SAMPLE_V7_SIMULATION);
        while (Euclidean3DTimeStepper.getTime() < 1.01e-9) {
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
                Euclidean3DInputOutput.save(SAMPLE_V7_SIMULATION);
                sampleV7Reader.writeLineResume(currentTime);
                euclidean3DVerticalSampler.writeLineResume();
                startTime = System.nanoTime();
            }
        }
    }
}
