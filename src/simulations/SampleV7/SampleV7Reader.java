package simulations.SampleV7;

import simulations.SampleV3.SampleV3Constructor;
import simulations.SampleV3.SampleV3Reader;

public class SampleV7Reader extends SampleV3Reader {

    public SampleV7Reader(String resumeName) {
        super(resumeName);
        SampleV3Constructor.supportHeight = SampleV7Constructor.SUPPORT_HEIGHT;
    }
}
