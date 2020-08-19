package custom.utils;

import custom.objects.dimensions1.Vector;

public class VectorUtils {

    public static Vector crossProduct(Vector vector1, Vector vector2){
        double xCoordinate=vector1.getYCoordinate()*vector2.getZCoordinate()-vector1.getZCoordinate()*vector2.getYCoordinate();
        double yCoordinate=vector1.getXCoordinate()*vector2.getZCoordinate()-vector1.getZCoordinate()*vector2.getXCoordinate();
        double zCoordinate=vector1.getXCoordinate()*vector2.getYCoordinate()-vector1.getYCoordinate()*vector2.getXCoordinate();
        return new Vector(xCoordinate,yCoordinate,zCoordinate);
    }
}
