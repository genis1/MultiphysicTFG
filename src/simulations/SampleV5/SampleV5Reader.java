package simulations.SampleV5;

import simulations.SampleV3.SampleV3Constructor;
import simulations.SampleV3.SampleV3Reader;

public class SampleV5Reader extends SampleV3Reader {

    public SampleV5Reader(String resumeName) {
        super(resumeName);
        //Used for selection. ET selection although not implemented is different.
        SampleV3Constructor.supportHeight = SampleV5Constructor.supportHeight;
    }
}