package simulations;

import custom.objects.dimensions1.Edge;
import custom.objects.dimensions3.TriangularPyramid;
import custom.objects.temperature.diffusion.Materials;
import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DSplitter;
import custom.space.Euclidean3DTimeStepper;

public class SampleV1Simulator {
    public static String SAMPLE_V1_INITIAL_MESH = "sampleV1InitialMesh";
    static String SAMPLE_V1_SPLITTED_MESH = "sampleV1SplittedMesh";
    static String SAMPLE_V1_SIMULATION = "sampleV1";
    static String SAMPLE_V1_SIMULATION_TIMEe0 = "sampleV1e0";
    static String SAMPLE_V1_SIMULATION_TIMEe1 = "sampleV1e1";
    static String SAMPLE_V1_SIMULATION_TIMEe2 = "sampleV1e2";
    static String SAMPLE_V1_SIMULATION_TIMEe3 = "sampleV1e3";
    static String SAMPLE_V1_SIMULATION_TIMEe4 = "sampleV1e4";
    static String SAMPLE_V1_SIMULATION_TIMEe5 = "sampleV1e5";
    private static double heatPower = 0.2e6 * 500e-9;
    private static double timeStep = 1e-16;


    public static void main(String[] args) {

        //createInitialMesh();

        //splitMesh();
        Euclidean3DSpace.clean();
        runSimulation();

        System.out.println("execution done");
    }

    public static void createInitialMesh() {
        SampleV1Constructor.createSampleV1();

        Euclidean3DSplitter.simplifyGrid();

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, timeStep);
        Euclidean3DInputOutput.save(SAMPLE_V1_INITIAL_MESH);
    }

    public static void splitMesh() {
        Euclidean3DSpace.clean();
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V1_INITIAL_MESH);

        while (SampleV1Constructor.selectR0().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(SampleV1Constructor::isPointInsideR0);
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R0 splitted");
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
        Euclidean3DInputOutput.save(SAMPLE_V1_SPLITTED_MESH);
    }

    public static void runSimulation() {
        double currentTime = Euclidean3DInputOutput.read(SAMPLE_V1_SPLITTED_MESH);
        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, timeStep);
        int counter = 0;
        long startTime = System.nanoTime();
        SampleV1Reader sampleV1Reader = new SampleV1Reader(SAMPLE_V1_SIMULATION);
        while (Euclidean3DTimeStepper.getTime() < 100) {
            Euclidean3DTimeStepper.computeTemperatureStepWithHeatSource(SampleV1Simulator::heatSource);
            counter++;
            currentTime = Euclidean3DTimeStepper.getTime();
            if (counter <= 1e1) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe0);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e2 && counter%1e1 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe1);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e3 && counter%1e2 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe2);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e4 && counter%1e3 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe3);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e5 && counter%1e4 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe4);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e6 && counter%1e5 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe5);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e7 && counter%1e6 == 0){
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe5);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e8 && counter%1e7 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe5);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            } else if (counter <= 1e9 && counter%1e8 == 0) {
                System.out.println("Computed " + counter + " simulation steps, took " + ((System.nanoTime() - startTime) * 1e-9) + " seconds.");
                Euclidean3DInputOutput.save(SAMPLE_V1_SIMULATION_TIMEe5);
                sampleV1Reader.writeLineResume(currentTime);
                startTime = System.nanoTime();
            }
        }
    }

    public static void heatSource(Double timeStep) {
        Double regionVolume = SampleV1Constructor.selectC3().stream().map(TriangularPyramid.class::cast)
                .map((triangularPyramid) -> {
                    if (triangularPyramid.getMaterial() != Materials.TemperatureDiffusion.LaAlO3)
                        throw new IllegalStateException("C3 should be LaAlO3");
                    return triangularPyramid.getVolume();
                })
                .reduce((aDouble, aDouble2) -> aDouble + aDouble2)
                .orElseThrow(() -> new IllegalStateException("C3 should have volume"));
        SampleV1Constructor.selectC3().stream().map(TriangularPyramid.class::cast).forEach(triangularPyramid ->
                triangularPyramid.add_dT(triangularPyramid.getVolume() * heatPower * timeStep / (regionVolume * triangularPyramid.getHeatCapacity())));
    }
}
