package simulations.SampleV7;

import custom.objects.dimensions1.Vector;
import simulations.SampleV5.SampleV5Constructor;

public class SampleV7Constructor {

    public static final Vector SUPPORT_HEIGHT = new Vector(0, 0, -10e-9);

    public static void createSampleV7() {
        SampleV5Constructor.supportHeight = SUPPORT_HEIGHT;
        SampleV5Constructor.createSampleV5();
    }
}
