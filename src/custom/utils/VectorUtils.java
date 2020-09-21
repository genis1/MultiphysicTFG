package custom.utils;

import custom.objects.dimensions0.Point;
import custom.objects.dimensions1.Vector;

public class VectorUtils {

    public static Vector crossProduct(Vector vector1, Vector vector2) {
        double xCoordinate = vector1.getYCoordinate() * vector2.getZCoordinate() - vector1.getZCoordinate() * vector2.getYCoordinate();
        double yCoordinate = vector1.getZCoordinate() * vector2.getXCoordinate() - vector1.getXCoordinate() * vector2.getZCoordinate();
        double zCoordinate = vector1.getXCoordinate() * vector2.getYCoordinate() - vector1.getYCoordinate() * vector2.getXCoordinate();
        return new Vector(xCoordinate, yCoordinate, zCoordinate);
    }

    public static double dotProduct(Vector vector1, Vector vector2) {
        double xContribution = vector1.getXCoordinate() * vector2.getXCoordinate();
        double yContribution = vector1.getYCoordinate() * vector2.getYCoordinate();
        double zContribution = vector1.getZCoordinate() * vector2.getZCoordinate();
        return xContribution + yContribution + zContribution;
    }

    public static double determinant(Vector vector0, Vector vector1, Vector vector2) {
        return VectorUtils.dotProduct(vector0, VectorUtils.crossProduct(vector1, vector2));
    }

    public static Vector subtraction(Point point0, Point point1) {
        return new Vector(point0.getXCoordinate() - point1.getXCoordinate(),
                point0.getYCoordinate() - point1.getYCoordinate(),
                point0.getZCoordinate() - point1.getZCoordinate()
        );
    }

    private static Point rotateXAxis(Point point, double angleRadian) {
        Vector vector = subtraction(point, new Point(0, 0, 0));
        Vector vectorRotated = rotateXAxis(vector, angleRadian);
        return new Point(vectorRotated.getXCoordinate(), vectorRotated.getYCoordinate(), vectorRotated.getZCoordinate());
    }

    private static Vector rotateXAxis(Vector vector, double angleRadian) {
        double yCoordinate = dotProduct(vector, new Vector(0, Math.cos(angleRadian), Math.sin(angleRadian)));
        double zCoordinate = dotProduct(vector, new Vector(0, -Math.sin(angleRadian), Math.cos(angleRadian)));
        return new Vector(vector.getXCoordinate(), yCoordinate, zCoordinate);
    }

    private static Point rotateYAxis(Point point, double angleRadian) {
        Vector vector = subtraction(point, new Point(0, 0, 0));
        Vector vectorRotated = rotateYAxis(vector, angleRadian);
        return new Point(vectorRotated.getXCoordinate(), vectorRotated.getYCoordinate(), vectorRotated.getZCoordinate());
    }

    private static Vector rotateYAxis(Vector vector, double angleRadian) {
        double xCoordinate = dotProduct(vector, new Vector(Math.cos(angleRadian), 0, -Math.sin(angleRadian)));
        double zCoordinate = dotProduct(vector, new Vector(Math.sin(angleRadian), 0, Math.cos(angleRadian)));
        return new Vector(xCoordinate, vector.getYCoordinate(), zCoordinate);
    }

    private static Point rotateZAxis(Point point, double angleRadian) {
        Vector vector = subtraction(point, new Point(0, 0, 0));
        Vector vectorRotated = rotateZAxis(vector, angleRadian);
        return new Point(vectorRotated.getXCoordinate(), vectorRotated.getYCoordinate(), vectorRotated.getZCoordinate());
    }

    private static Vector rotateZAxis(Vector vector, double angleRadian) {
        double xCoordinate = dotProduct(vector, new Vector(Math.cos(angleRadian), Math.sin(angleRadian), 0));
        double yCoordinate = dotProduct(vector, new Vector(-Math.sin(angleRadian), Math.cos(angleRadian), 0));
        return new Vector(xCoordinate, yCoordinate, vector.getZCoordinate());
    }

    public static Point rotate3Angles(Point point) {
        point = rotateXAxis(point, 5);
        point = rotateYAxis(point, 10);
        return rotateZAxis(point, 20);
    }

    public static Vector rotate3Angles(Vector vector) {
        vector = rotateXAxis(vector, 5);
        vector = rotateYAxis(vector, 10);
        return rotateZAxis(vector, 20);
    }

    public static Point meanPoint(Point point0, Point point1) {
        double xCoordinate = (point0.getXCoordinate() + point1.getXCoordinate()) / 2;
        double yCoordinate = (point0.getYCoordinate() + point1.getYCoordinate()) / 2;
        double zCoordinate = (point0.getZCoordinate() + point1.getZCoordinate()) / 2;
        return new Point(xCoordinate, yCoordinate, zCoordinate);
    }
}
