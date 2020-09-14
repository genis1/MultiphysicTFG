package custom.objects.temperature.diffusion;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;
import custom.objects.dimensions2.Face;
import custom.objects.dimensions3.Polyhedron;
import custom.objects.dimensions3.TriangularPyramid;
import custom.utils.VectorUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

public class TemperatureInterface extends Face {

    private Double averageThermalConductivity;
    private Double effectiveDistance;
    private Double area;

    public TemperatureInterface(Point... points) {
        super(points);
    }

    private Vector getVectorFromCentroidOfBaseToCentroidOfPolyehdron(Polyhedron polyhedron) {
        if (polyhedron.type != Polyhedron.Type.TRIANGULAR_PYRAMID) throw new NotImplementedException();
        Point polyehdronCentroid = TriangularPyramid.class.cast(polyhedron).getCentroid();
        Point baseCentroid = this.getCentroid();
        return VectorUtils.subtraction(polyehdronCentroid, baseCentroid);
    }

    /**
     * @return average thermal conductivity weighted by distance in W/m*K
     */
    public Double getAverageThermalConductivity() {
        if (averageThermalConductivity == null) computeCachedVariables();
        return averageThermalConductivity;
    }

    public Double getEffectiveDistanceBetweenParentPolyhedra() {
        if (effectiveDistance == null) computeCachedVariables();
        return effectiveDistance;
    }

    @Override
    public double getArea() {
        if (this.area == null) this.area = super.getArea();
        return this.area;
    }

    private void computeCachedVariables() {
        Iterator<Polyhedron> iterator = this.getParentPolyhedra().iterator();
        Polyhedron parentPolyhedron0 = iterator.next();
        Polyhedron parentPolyhedron1 = iterator.next();
        if (!TemperatureContainer.class.isInstance(parentPolyhedron0) || !TemperatureContainer.class.isInstance(parentPolyhedron1))
            throw new NotImplementedException();


        //Compute effective distance
        Vector vector0 = this.getVectorFromCentroidOfBaseToCentroidOfPolyehdron(parentPolyhedron0);
        Vector vector1 = this.getVectorFromCentroidOfBaseToCentroidOfPolyehdron(parentPolyhedron1);
        Vector normal = this.getNormal().getUnitVector();
        double d0 = Math.abs(VectorUtils.dotProduct(normal, vector0));
        double d1 = Math.abs(VectorUtils.dotProduct(normal, vector1));
        this.effectiveDistance = d1 + d0;


        //Compute average thermal conductivity
        TemperatureContainer container0 = TemperatureContainer.class.cast(parentPolyhedron0);
        TemperatureContainer container1 = TemperatureContainer.class.cast(parentPolyhedron1);
        double thermalConductivity0 = container0.getMaterial().getThermalConductivity();
        double thermalConductivity1 = container1.getMaterial().getThermalConductivity();
        averageThermalConductivity = (d0 * thermalConductivity0 + d1 * thermalConductivity1) / this.getEffectiveDistanceBetweenParentPolyhedra();
    }

    public void addHeatTransferedToParentPolyehdra(double deltaTime) {
        Iterator<Polyhedron> iterator = this.getParentPolyhedra().iterator();
        Polyhedron parentPolyhedron0 = iterator.next();
        if (!iterator.hasNext()) return;
        Polyhedron parentPolyhedron1 = iterator.next();
        if (!TemperatureContainer.class.isInstance(parentPolyhedron0) || !TemperatureContainer.class.isInstance(parentPolyhedron1))
            throw new NotImplementedException();
        TemperatureContainer container0 = TemperatureContainer.class.cast(parentPolyhedron0);
        TemperatureContainer container1 = TemperatureContainer.class.cast(parentPolyhedron1);

        double T0 = container0.getTemperature();
        double T1 = container1.getTemperature();
        if (T0 == T1) return;
        double c = this.getAverageThermalConductivity() * this.getArea() / this.getEffectiveDistanceBetweenParentPolyhedra();
        double H0 = container0.getHeatCapacity();
        double c0 = c / H0;
        double H1 = container1.getHeatCapacity();
        double c1 = c / H1;

        double expFactor = 1 - Math.pow(Math.E, -(c0 + c1) * deltaTime);
        double dT0 = (T1 - T0) * H1 * expFactor / (H0 + H1);
        double dT1 = (T0 - T1) * H0 * expFactor / (H0 + H1);
        if (expFactor > 1d / 4) {
            throw new IllegalStateException("Time discratization is too big for model");
        }
        container0.add_dT(dT0);
        container1.add_dT(dT1);
    }
}
