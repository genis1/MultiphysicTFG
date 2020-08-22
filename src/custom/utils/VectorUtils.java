package custom.utils;

import custom.objects.dimensions1.Vector;

public class VectorUtils {

    public static Vector crossProduct(Vector vector1, Vector vector2) {
        double xCoordinate = vector1.getYCoordinate() * vector2.getZCoordinate() - vector1.getZCoordinate() * vector2.getYCoordinate();
        double yCoordinate = vector1.getXCoordinate() * vector2.getZCoordinate() - vector1.getZCoordinate() * vector2.getXCoordinate();
        double zCoordinate = vector1.getXCoordinate() * vector2.getYCoordinate() - vector1.getYCoordinate() * vector2.getXCoordinate();
        return new Vector(xCoordinate, yCoordinate, zCoordinate);
    }

    public static double dotProduct(Vector vector1, Vector vector2) {
        double xContribution = vector1.getXCoordinate() * vector2.getXCoordinate();
        double yContribution = vector1.getYCoordinate() * vector2.getYCoordinate();
        double zContribution = vector1.getZCoordinate() * vector2.getZCoordinate();
        return xContribution + yContribution + zContribution;
    }
}
