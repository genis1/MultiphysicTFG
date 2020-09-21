package simulations.SampleV3;

import custom.objects.dimensions3.TriangularPyramid;
import custom.space.Euclidean3DInputOutput;
import simulations.SampleV1Reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Predicate;

public class SampleV3Reader {
    private String resumeName;


    public SampleV3Reader(String resumeName) {
        this.resumeName = resumeName;
    }


    public void writeLineResume(double currentTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%.2E", currentTime));
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideR0);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideR1);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideR2);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideR3);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideC1);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideC2);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideC3);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideC4);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isPointInsideC5);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isCentroidInsideT1);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isCentroidInsideT2);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isCentroidInsideT3);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isCentroidInsideT4);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isCentroidInsideT5);
        appendTemperatureOfRegion(sb, SampleV3Constructor::isCentroidInsideT6);
        try {
            BufferedWriter titledFile = getTitledFile();
            titledFile.write(sb.toString());
            titledFile.newLine();
            titledFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void appendTemperatureOfRegion(StringBuilder sb, Predicate<TriangularPyramid> condition) {
        sb.append("\t");
        sb.append(SampleV1Reader.getTemperature(condition));
    }

    private BufferedWriter getTitledFile() throws IOException {
        File file = new File(Euclidean3DInputOutput.root + resumeName + Euclidean3DInputOutput.extension);
        if (file.exists()) {
            FileWriter fileWriter = new FileWriter(file, true);
            return new BufferedWriter(fileWriter);
        } else {
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter result = new BufferedWriter(fileWriter);
            result.write("t\t\t" +
                    "R0\t\t\t" +
                    "R1\t\t\t" +
                    "R2\t\t\t" +
                    "R3\t\t\t" +
                    "C1\t\t\t" +
                    "C2\t\t\t" +
                    "C3\t\t\t" +
                    "C4\t\t\t" +
                    "C5\t\t\t" +
                    "T1\t\t\t" +
                    "T2\t\t\t" +
                    "T3\t\t\t" +
                    "T4\t\t\t" +
                    "T5\t\t\t" +
                    "T6\t\t\t");
            result.newLine();
            result.flush();
            return result;
        }
    }
}
