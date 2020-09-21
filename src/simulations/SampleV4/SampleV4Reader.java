package simulations.SampleV4;

import simulations.SampleV3.SampleV3Constructor;
import simulations.SampleV3.SampleV3Reader;

public class SampleV4Reader extends SampleV3Reader {

    public SampleV4Reader(String resumeName) {
        super(resumeName);
        SampleV3Constructor.supportHeight = SampleV4Constructor.SUPPORT_HEIGHT;
    }
}