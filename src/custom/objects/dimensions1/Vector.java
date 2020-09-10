package custom.objects.dimensions1;

import custom.utils.D1Utils;

public class Vector implements Comparable<Vector> {
    private final double[] coordinates;
    private final double length;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Vector(double xCoordinate, double yCoordinate, double zCoordinate) {
        this.coordinates = new double[]{xCoordinate, yCoordinate, zCoordinate};
        this.length = D1Utils.length(this);
    }

    public double getXCoordinate() {
        return coordinates[0];
    }

    public double getYCoordinate() {
        return coordinates[1];
    }

    public double getZCoordinate() {
        return coordinates[2];
    }

    public double getLength() {
        return this.length;
    }

    public UnitVector getUnitVector() {
        double length = this.getLength();
        if (this.getLength() == 0) return new UnitVector(this);
        return new UnitVector(this.divide(length));
    }

    public void print() {
        System.out.println("(" + this.getXCoordinate() + "," + this.getYCoordinate() + "," + this.getZCoordinate() + ")");
    }

    public Vector divide(double divisor) {
        return new Vector(this.getXCoordinate() / divisor, this.getYCoordinate() / divisor, this.getZCoordinate() / divisor);
    }

    @Override
    public int compareTo(Vector o) {
        if (this.getXCoordinate() != o.getXCoordinate()) {
            if (this.getXCoordinate() > o.getXCoordinate()) return 1;
            else return -1;
        } else if (this.getYCoordinate() != o.getYCoordinate()) {
            if (this.getYCoordinate() > o.getYCoordinate()) return 1;
            else return -1;
        } else if (this.getZCoordinate() != o.getZCoordinate()) {
            if (this.getZCoordinate() > o.getZCoordinate()) return 1;
            else return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "(" + this.getXCoordinate() + "," + this.getYCoordinate() + "," + this.getZCoordinate() + ")";
    }
}
