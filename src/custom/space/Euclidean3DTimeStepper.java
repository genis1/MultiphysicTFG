package custom.space;

import custom.objects.temperature.diffusion.TemperatureContainer;

import java.util.function.Consumer;

public class Euclidean3DTimeStepper {

    private static Double timeStep;

    private static Double time;

    public static void setInitialTimeAndTimeStep(Double time, Double timeStep) {
        Euclidean3DTimeStepper.timeStep = timeStep;
        Euclidean3DTimeStepper.time = time;
    }

    public static Double getTime() {
        return time;
    }

    private static Double getTimeStep() {
        return timeStep;
    }

    public static void computeTimeStep() {
        Euclidean3DSpace.getFaces().forEach(temperatureInterface -> temperatureInterface.addHeatTransferedToParentPolyehdra(Euclidean3DTimeStepper.getTimeStep()));
        Euclidean3DSpace.getPolyhedra().forEach(TemperatureContainer::computeNewTemperature);
        time = Euclidean3DTimeStepper.getTime() + Euclidean3DTimeStepper.getTimeStep();
    }

    public static void computeTemperatureStepWithHeatSource(Consumer<Double> heatSource) {
        Euclidean3DSpace.getFaces().forEach(temperatureInterface -> temperatureInterface.addHeatTransferedToParentPolyehdra(Euclidean3DTimeStepper.getTimeStep()));
        heatSource.accept(timeStep);
        Euclidean3DSpace.getPolyhedra().forEach(TemperatureContainer::computeNewTemperature);
        time = Euclidean3DTimeStepper.getTime() + Euclidean3DTimeStepper.getTimeStep();
    }
}
