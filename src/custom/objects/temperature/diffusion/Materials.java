package custom.objects.temperature.diffusion;

public class Materials {

    public enum TemperatureDiffusion {
        LaAlO3(7.5, 6000, 427),
        SrTiO3(12, 5120, 530);
        private final double thermalConductivity;
        private final double density;
        private final double specificHeatCapacity;

        TemperatureDiffusion(double thermalConductivity, double density, double specificHeatCapacity) {
            this.thermalConductivity = thermalConductivity;
            this.density = density;
            this.specificHeatCapacity = specificHeatCapacity;
        }

        /**
         * @return thermal conductivity in W/m*K
         */
        public double getThermalConductivity() {
            return thermalConductivity;
        }

        /**
         * @return density in kg/m^3
         */
        public double getDensity() {
            return density;
        }

        /**
         *
         * @return specific heat capacity in J/kg*K
         */
        public double getSpecificHeatCapacity() {
            return specificHeatCapacity;
        }
    }
}
