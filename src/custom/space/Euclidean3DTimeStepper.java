package custom.space;

import custom.objects.temperature.diffusion.TemperatureInterface;

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
        Euclidean3DSpace.getFaces().forEach(TemperatureInterface::addHeatTransferedToParentPolyehdra);
        Euclidean3DSpace.getPolyhedra().forEach(temperatureContainer -> temperatureContainer.computeNewTemperature(Euclidean3DTimeStepper.getTimeStep()));
    }
}
