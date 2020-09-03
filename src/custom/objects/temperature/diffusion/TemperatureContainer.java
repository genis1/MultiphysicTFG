package custom.objects.temperature.diffusion;

import custom.objects.dimensions3.Polyhedron;

public abstract class TemperatureContainer extends Polyhedron {

    private final Materials.TemperatureDiffusion material;
    private double temperature;
    private double heatingPower;

    //Cached
    private Double heatCapacity;

    public TemperatureContainer(Type type, Materials.TemperatureDiffusion material, double temperature) {
        super(type);
        this.material = material;
        this.temperature = temperature;
        resetHeatingPower();
    }

    public Materials.TemperatureDiffusion getMaterial() {
        return material;
    }

    private double getHeatCapacity() {
        if (this.heatCapacity == null) {
            this.heatCapacity = this.getVolume() * this.getMaterial().getDensity() * this.getMaterial().getSpecificHeatCapacity();
        }
        return heatCapacity;
    }

    public void addHeatingPower(double heatingPower) {
        this.heatingPower += heatingPower;
    }

    private void resetHeatingPower() {
        this.heatingPower = 0;
    }

    public double getHeatingPower() {
        return heatingPower;
    }

    public void computeNewTemperature(double time) {
        temperature += this.heatingPower * time / this.getHeatCapacity();
        resetHeatingPower();
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
