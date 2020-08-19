package custom.objects.dimensions1;

public class UnitVector extends Vector {
    UnitVector(Vector vector) {
        super(vector.getXCoordinate(), vector.getYCoordinate(), vector.getZCoordinate());
    }
}
