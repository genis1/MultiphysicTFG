package simulations;

import custom.objects.dimensions1.Edge;
import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DSplitter;
import custom.space.Euclidean3DTimeStepper;

public class SampleV1Simulator {
    static String SAMPLE_V1_INITIAL_MESH = "sampleV1InitialMesh";
    static String SAMPLE_V1_SPLITTED_MESH = "sampleV1SplittedMesh";
    private static double heatPower = 0.2e6 * 500e-9;

    public static void main(String[] args) {

        //createInitialMesh();

        splitMesh();


        System.out.println("execution done");
    }

    public static void createInitialMesh() {
        SampleV1Constructor.createSampleV1();

        Euclidean3DSplitter.simplifyGrid();

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 1e-9);
        Euclidean3DInputOutput.save(SAMPLE_V1_INITIAL_MESH);
    }

    public static void splitMesh() {
        Euclidean3DSpace.clean();
        Euclidean3DInputOutput.read(SAMPLE_V1_INITIAL_MESH);

        while (SampleV1Constructor.selectR0().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(triangularPyramid -> SampleV1Constructor.isPointInsideR0(triangularPyramid.getCentroid()));
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R0 splitted");
        while (SampleV1Constructor.selectR1().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(triangularPyramid -> SampleV1Constructor.isPointInsideR1(triangularPyramid.getCentroid()));
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R1 splitted");
        while (SampleV1Constructor.selectR2().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(triangularPyramid -> SampleV1Constructor.isPointInsideR2(triangularPyramid.getCentroid()));
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R2 splitted");
        while (SampleV1Constructor.selectR3().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(triangularPyramid -> SampleV1Constructor.isPointInsideR3(triangularPyramid.getCentroid()));
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R3 splitted");
        while (SampleV1Constructor.selectR4().size() < 2000) {
            Edge longestEdge = Euclidean3DSplitter.findLongestEdgeWithParentPolyhedraSatisfying(triangularPyramid -> SampleV1Constructor.isPointInsideR4(triangularPyramid.getCentroid()));
            Euclidean3DSplitter.splitThroughEdge(longestEdge);
        }
        System.out.println("R4 splitted");

        Euclidean3DTimeStepper.setInitialTimeAndTimeStep(0d, 1e-9);
        Euclidean3DInputOutput.save(SAMPLE_V1_SPLITTED_MESH);
    }
}
