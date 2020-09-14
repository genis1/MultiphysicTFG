package simulations;

import custom.objects.dimensions3.TriangularPyramid;
import custom.space.Euclidean3DInputOutput;
import custom.space.Euclidean3DSpace;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public class SampleV1Reader {
    private String resumeName;


    public SampleV1Reader(String resumeName) {
        this.resumeName = resumeName;
    }


    private static double getTemperature(Predicate<TriangularPyramid> isSelected) {
        double regionHeatCapcity = 0;
        double temperatureTimesHeatCapacity = 0;

        Iterator<TriangularPyramid> iterator = Euclidean3DSpace.getPolyhedra().stream().map(TriangularPyramid.class::cast)
                .filter(isSelected)
                .iterator();
        while (iterator.hasNext()) {
            TriangularPyramid triangularPyramid = iterator.next();
            double heatCapacity = triangularPyramid.getHeatCapacity();
            double temperature = triangularPyramid.getTemperature();
            regionHeatCapcity += heatCapacity;
            temperatureTimesHeatCapacity += temperature * heatCapacity;
        }

        return temperatureTimesHeatCapacity / regionHeatCapcity;
    }

    public void createResume() {
        File file = new File(Euclidean3DInputOutput.root + resumeName + "e");
        Arrays.stream(Objects.requireNonNull(file.getParentFile().listFiles()))
                .filter(file1 -> file1.getName().startsWith(resumeName + "e"))
                .sorted(Comparator.comparing(File::getName))
                .forEach(file1 -> {
                    Euclidean3DSpace.clean();
                    double currentTime = Euclidean3DInputOutput.readFile(file1);
                    writeLineResume(currentTime);
                });

    }

    public void writeLineResume(double currentTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%.2E", currentTime));
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideR0);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideR1);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideR2);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideR3);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideR4);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideC1);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideC2);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideC3);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideC4);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideC5);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isCentroidInsideT1);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isCentroidInsideT2);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isCentroidInsideT3);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isCentroidInsideT4);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isCentroidInsideT5);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isCentroidInsideT6);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideQ1);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideQ2);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideQ3);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideQ4);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideQ5);
        appendTemperatureOfRegion(sb, SampleV1Constructor::isPointInsideQ6);
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
        sb.append(getTemperature(condition));
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
                    "R4\t\t\t" +
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
                    "T6\t\t\t" +
                    "Q1\t\t\t" +
                    "Q2\t\t\t" +
                    "Q3\t\t\t" +
                    "Q4\t\t\t" +
                    "Q5\t\t\t" +
                    "Q6\t\t\t");
            result.newLine();
            result.flush();
            return result;
        }
    }
}
