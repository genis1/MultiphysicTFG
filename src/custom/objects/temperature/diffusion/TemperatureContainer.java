package custom.objects.temperature.diffusion;

import custom.objects.dimensions3.Polyhedron;

public abstract class TemperatureContainer extends Polyhedron {

    private final Materials.TemperatureDiffusion material;
    private double temperature;
    private double dT;

    //Cached
    private Double heatCapacity;

    public TemperatureContainer(Type type, Materials.TemperatureDiffusion material, double temperature) {
        super(type);
        this.material = material;
        this.temperature = temperature;
        resetDeltaT();
    }

    public Materials.TemperatureDiffusion getMaterial() {
        return material;
    }

    public double getHeatCapacity() {
        if (this.heatCapacity == null) {
            this.heatCapacity = this.getVolume() * this.getMaterial().getDensity() * this.getMaterial().getSpecificHeatCapacity();
        }
        return heatCapacity;
    }

    public void add_dT(double dT) {
        this.dT += dT;
    }

    private void resetDeltaT() {
        this.dT = 0;
    }

    public double getDelataT() {
        return this.dT;
    }

    public void computeNewTemperature() {
        this.temperature += dT;
        resetDeltaT();
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
