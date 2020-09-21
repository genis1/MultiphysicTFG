package simulations.SampleV6;

import simulations.SampleV3.SampleV3Constructor;
import simulations.SampleV3.SampleV3Reader;

public class SampleV6Reader extends SampleV3Reader {

    public SampleV6Reader(String resumeName) {
        super(resumeName);
        //Used for selection. ET selection although not implemented is different.
        SampleV3Constructor.supportHeight = SampleV6Constructor.SUPPORT_HEIGHT;

    }
}
