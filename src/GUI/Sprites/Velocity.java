//318910890 Ido Haver
package GUI.Sprites;

import GUI.Shapes.Point;

/**
 * Circle.Basic.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor with the dx and dy.
     *
     * @param dx double,movement in x
     * @param dy double,movement in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor from angle and main velocity,split them to dx and dy.
     *
     * @param angle the angle of the movement
     * @param speed the value in velocity in this angle
     * @return the velocity in dx,dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = Math.sin(radians) * speed;
        double dy = -Math.cos(radians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * change the value of the dx.
     *
     * @param dx the new dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * change the value of the dy.
     *
     * @param dy the new dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point.
     * with position (x+dx, y+dy)
     *
     * @param p specified point
     * @return the next point in the movement
     **/
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

}