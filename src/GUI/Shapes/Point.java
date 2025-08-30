//318910890 Ido Haver
package GUI.Shapes;

/**
 * Create an object that present point in coordinate system.
 */
public class Point {
    //solution for the threshold
    static final double EPSILON = 0.000001d;
    private double x;
    private double y;

    /**
     * constructor for point.
     *
     * @param x the x value in double
     * @param y the y value in double
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculate the distance between two points.
     *
     * @param other the other point.
     * @return the distance between the points in double
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * check if two points are equal.
     *
     * @param other other point
     * @return true if equal,false otherwise
     */
    public boolean equals(Point other) {
        return ((Math.abs(this.x - other.x) < EPSILON) && (Math.abs(this.y - other.y) < EPSILON));
    }

    /**
     * @return the x value of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y value of the point
     */

    public double getY() {
        return this.y;
    }

    /**
     * set the x value.
     *
     * @param x the new value
     */

    public void setX(double x) {
        this.x = x;
    }

    /**
     * set the y value.
     *
     * @param y the new value
     */
    public void setY(double y) {
        this.y = y;
    }
}