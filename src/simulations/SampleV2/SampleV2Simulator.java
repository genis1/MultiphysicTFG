package simulations.SampleV2;

import custom.objects.dimensions1.Edge;
import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DSplitter;
import custom.space.Euclidean3DTimeStepper;
import simulations.SampleV1Constructor;
import simulations.SampleV1Reader;
import simulations.SampleV1Simulator;

public class SampleV2Simulator {

    private static double timeStep = 1e-14;
    static String SAMPLE_V2_SPLITTED_MESH = "sampleV2SplittedMesh";
    static String SAMPLE_V2_SIMULATION_TIMEe0 = "sampleV2e0";
    static String SAMPLE_V2_SIMULATION_TIMEe1 = "sampleV2e1";
    static String SAMPLE_V2_SIMULATION_TIMEe2 = "sampleV2e2";
    static String SAMPLE_V2_SIMULATION_TIMEe3 = "sampleV2e3";
    static String SAMPLE_V2_SIMULATION_TIMEe4 = "sampleV2e4";
    static String SAMPLE_V2_SIMULATION_TIMEe5 = "sampleV2e5";
    static String SAMPLE_V2_SIMULATION = "sampleV2";

    public static void main(String[] args) {

        //splitMesh();
        Euclidean3DSpace.clean();
        runSimulation();
        System.out.println("execution done");
    }

    public static void splitMesh() {
        Euclidean3DSpace.clean();
        double currentTime = Euclidean3DInputOutput.read(SampleV1Simulator.SAMPLE_V1_INITIAL_MESH);

        System.out.println("R0 not splitted");
        while (SampleV1Constructor.selectR1().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(SampleV1Constructor::isPointInsideR1);
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R1 splitted");
        while (SampleV1Constructor.selectR2().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(SampleV1Constructor::isPointInsideR2);
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R2 splitted");
        while (SampleV1Constructor.selectR3().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(SampleV1Constructor::isPointInsideR3);
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R3 splitted");
        while (SampleV1Constructor.selectR4().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(SampleV1Constructor::isPointInsideR4);
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R4 splitted");

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        Euclidean3DInputOutput.save(SAMPLE_V2_SPLITTED_MESH);
    }

    public static void runSimulation() {
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V2_SPLITTED_MESH);
        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        int counter = 0;
        long startTime = System.nanoTime();
        SampleV1Reader sampleV2Reader = new SampleV1Reader(SAMPLE_V2_SIMULATION);
        while (Euclidean3DTimeStepper.getTime() < 100) {
            Euclidean3DTimeStepper.computeTemperatureStepWithHeatSource(SampleV1Simulator::heatSource);
            counter++;
            currentTime = Euclidean3DTimeStepper.getTime();
            if (currentTime <= timeStep * 1e1) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe0);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e2 && counter % 1e1 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe1);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e3 && counter % 1e2 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe2);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e4 && counter % 1e3 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe3);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e5 && counter % 1e4 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe4);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e6 && counter % 1e5 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe5);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e7 && counter % 1e6 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe5);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e8 && counter % 1e7 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe5);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e9 && counter % 1e8 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V2_SIMULATION_TIMEe5);
                sampleV2Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            }
        }
    }

}
