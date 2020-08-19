package custom.objects.dimensions0;

import custom.objects.dimensions1.Vector;

public class Point implements Comparable<Point> {
    private final double[] coordinates;

    public Point(double xCoordinate, double yCoordinate, double zCoordinate) {
        this.coordinates = new double[]{xCoordinate, yCoordinate, zCoordinate};
    }

    public double getXCoordinate() {
        return this.coordinates[0];
    }

    public double getYCoordinate() {
        return this.coordinates[1];
    }

    public double getZCoordinate() {
        return this.coordinates[2];
    }

    public void print() {
        System.out.println("(" + this.getXCoordinate() + "," + this.getYCoordinate() + "," + this.getZCoordinate() + ")");
    }

    public Point add(Vector vector) {
        return new Point(this.getXCoordinate() + vector.getXCoordinate(),
                this.getYCoordinate() + vector.getYCoordinate(),
                this.getZCoordinate() + vector.getZCoordinate());
    }

    @Override
    public int compareTo(Point o) {
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
}
