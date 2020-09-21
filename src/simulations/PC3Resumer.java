package simulations;

import custom.objects.dimensions1.Vector;
import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSpace;
import custom.space.Euclidean3DTimeStepper;
import custom.space.Euclidean3DVerticalSampler;
import simulations.SampleV3.SampleV3Constructor;
import simulations.SampleV4.SampleV4Constructor;
import simulations.SampleV5.SampleV5Constructor;
import simulations.SampleV6.SampleV6Constructor;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class PC3Resumer {

    public static void main(String[] args) {
        createResume("sampleV4", SampleV4Constructor.SUPPORT_HEIGHT);
        createResume("sampleV5", SampleV5Constructor.supportHeight);
        createResume("sampleV6", SampleV6Constructor.SUPPORT_HEIGHT);
    }

    public static void createResume(String sampleName, Vector supportHeight) {
        Euclidean3DVerticalSampler sampleSampler = new Euclidean3DVerticalSampler(
                SampleV3Constructor.C13,
                SampleV3Constructor.C14,
                SampleV3Constructor.C23,
                SampleV3Constructor.height,
                supportHeight,
                10,
                sampleName);
        Collection<File> files = findFilesToSummarize(sampleName);
        files.forEach(file -> {
            Euclidean3DSpace.clean();
            double currentTime = Euclidean3DInputOutput.readFile(file);
            Euclidean3DTimeStepper.setInitialTimeAndTimeStep(currentTime, 0d);
            sampleSampler.writeLineResume();
        });
    }


    public static Collection<File> findFilesToSummarize(String sampleName) {
        File rootFile = new File(Euclidean3DInputOutput.root);
        return Arrays.stream(Objects.requireNonNull(rootFile.listFiles()))
                .filter(file -> {
                    String[] split = file.getName().split(Euclidean3DInputOutput.SEPARATOR);
                    if (split.length != 2) return false;
                    else {
                        return split[0].equals(sampleName);
                    }
                })
                .sorted((file1, file2) -> {
                    String time1asString = file1.getName().split(Euclidean3DInputOutput.SEPARATOR)[1].split(Euclidean3DInputOutput.extension)[0];
                    double time1 = Double.parseDouble(time1asString);
                    String time2asString = file2.getName().split(Euclidean3DInputOutput.SEPARATOR)[1].split(Euclidean3DInputOutput.extension)[0];
                    double time2 = Double.parseDouble(time2asString);
                    return Double.compare(time1, time2);
                })
                .collect(Collectors.toList());
    }
}
