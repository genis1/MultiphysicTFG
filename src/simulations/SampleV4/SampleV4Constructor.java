package simulations.SampleV4;

import custom.objects.dimensions1.Vector;
import simulations.SampleV3.SampleV3Constructor;

public class SampleV4Constructor extends SampleV3Constructor {

    public static final Vector SUPPORT_HEIGHT = new Vector(0, 0, -10e-9);

    public static void createSampleV4() {
        SampleV3Constructor.supportHeight = SUPPORT_HEIGHT;
        SampleV3Constructor.createSampleV3();
    }
}