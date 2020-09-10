package simulations;

import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSplitter;
import custom.space.Euclidean3DTimeStepper;

public class SampleV1Simulator {
    private static String SAMPLE_V1_INITIAL_MESH = "sampleV1InitialMesh";

    public static void main(String[] args) {

        createInitialMesh();
        long startTime = System.nanoTime();
        Euclidean3DInputOutput.read(SAMPLE_V1_INITIAL_MESH);
        double timeToLoad = (System.nanoTime() - startTime) * 1e-9;

        System.out.println("test");
    }

    private static void createInitialMesh() {
        SampleV1Constructor.createSampleV1();

        Euclidean3DSplitter.simplifyGrid();

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 1e-9);
        Euclidean3DInputOutput.save(SAMPLE_V1_INITIAL_MESH);
    }
}
