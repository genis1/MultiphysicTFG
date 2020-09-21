package custom.space;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions3.TriangularPyramid;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public class Euclidean3DVerticalSampler {

    private Vector deviceHeigth;
    private Vector horizontalDivisonDepth;
    private Point point0;
    private Point point1;
    private Point point2;
    private int numberOfDivisons;


    private BufferedWriter writer;


    public Euclidean3DVerticalSampler(Point point0, Point point1, Point point2, Vector deviceHeigth, Vector sampleDepth, int numberOfDivisons, String fileName) {
        this.point0 = point0;
        this.point1 = point1;
        this.point2 = point2;
        this.deviceHeigth = deviceHeigth;
        this.numberOfDivisons = numberOfDivisons;
        this.horizontalDivisonDepth = sampleDepth.divide(numberOfDivisons);
        this.writer = getFile(fileName);

    }


    private Collection<TriangularPyramid> selectFirstDivison() {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(triangularPyramid -> Euclidean3DSelector.isPointInsideCube(point0, point1, point2, deviceHeigth, triangularPyramid.getCentroid()))
                .collect(Collectors.toList());
    }

    public void writeLineResume() {
        try {
            writer.write(this.toLine());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedWriter getFile(String fileName) {
        BufferedWriter result = null;
        File file = new File(Euclidean3DInputOutput.root + fileName + "VerticalSummary" + Euclidean3DInputOutput.extension);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();

                file.createNewFile();

            }
            FileWriter fileWriter = new FileWriter(file, true);
            result = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String toLine() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Euclidean3DTimeStepper.getTime());
        stringBuilder.append("\t");
        for (int i = 0; i <= numberOfDivisons; i++) {
            stringBuilder.append(getTemperatureOfHorizonatlDivison(i));
            stringBuilder.append("\t");
        }
        return stringBuilder.toString();
    }

    private double getTemperatureOfHorizonatlDivison(int i) {
        Collection<TriangularPyramid> temperatureContainers;
        double totalHeatCapacity = 0;
        double totalHeat = 0;
        if (i == 0) {
            temperatureContainers = selectFirstDivison();
        } else {
            temperatureContainers = selectHorizontalDivison(i);
        }

        if (temperatureContainers.size() == 0)
            return 0;

        for (TriangularPyramid triangularPyramid : temperatureContainers) {
            totalHeatCapacity += triangularPyramid.getHeatCapacity();
            totalHeat += triangularPyramid.getHeatCapacity() * triangularPyramid.getTemperature();
        }

        return totalHeat / totalHeatCapacity;
    }

    private Collection<TriangularPyramid> selectHorizontalDivison(int i) {
        return Euclidean3DSpace.getPolyhedra().stream()
                .map(TriangularPyramid.class::cast)
                .filter(temperatureContainer -> Euclidean3DSelector.isPointInsideCube(
                        point0.add(horizontalDivisonDepth.multiply(i - 1)),
                        point1.add(horizontalDivisonDepth.multiply(i - 1)),
                        point2.add(horizontalDivisonDepth.multiply(i - 1)),
                        horizontalDivisonDepth,
                        temperatureContainer.getCentroid()))
                .collect(Collectors.toList());
    }
}
