package custom.utils;

import custom.objects.dimensions1.Edge;
import custom.objects.dimensions1.Vector;

public class D1Utils {

    private static double length(double[] coordiantes) {
        if (coordiantes.length != 3) throw new IllegalArgumentException("Unexpected non-3D coordinates");
        return Math.pow(Math.pow(coordiantes[0], 2) + Math.pow(coordiantes[1], 2) + Math.pow(coordiantes[2], 2), 0.5);
    }

    public static double length(Vector vector) {
        return D1Utils.length(new double[]{vector.getXCoordinate(), vector.getYCoordinate(), vector.getZCoordinate()});
    }

    public static double length(Edge edge) {
        return D1Utils.length(edge.getVector());
    }


}
