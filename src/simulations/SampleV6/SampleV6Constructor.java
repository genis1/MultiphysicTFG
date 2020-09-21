package simulations.SampleV6;

import custom.objects.dimensions1.Vector;
import simulations.SampleV5.SampleV5Constructor;

public class SampleV6Constructor {

    public static final Vector SUPPORT_HEIGHT = new Vector(0, 0, -100e-9);

    public static void createSampleV6() {
        SampleV5Constructor.supportHeight = SUPPORT_HEIGHT;
        SampleV5Constructor.createSampleV5();
    }
}
