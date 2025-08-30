//318910890 Ido Haver
package GUI.Shapes;

import java.awt.Color;
import java.util.LinkedList;


/**
 * create an object that represent rectangle in coordinate system.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private java.awt.Color color;

    /**
     * constructor.
     *
     * @param upperLeft the upper left point in the rectangle
     * @param width     the width of the rectangle
     * @param height    the height in the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * constructor.
     *
     * @param upperLeft the upper left point in the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * get a line and find the intersection points with the rectangle.
     *
     * @param line the given line
     * @return a list of the intersection points,possibly empty
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new LinkedList<Point>();
        double upperRightX = this.upperLeft.getX() + this.width;
        double downLeftY = this.upperLeft.getY() + this.height;
        //create points to all the points
        Point upperRight = new Point(upperRightX, upperLeft.getY());
        Point downLeft = new Point(this.upperLeft.getX(), downLeftY);
        Point downRight = new Point(upperRightX, downLeftY);
        //the lines of the rectangle sides
        Line l1 = new Line(this.upperLeft, upperRight);
        Line l2 = new Line(this.upperLeft, downLeft);
        Line l3 = new Line(downRight, downLeft);
        Line l4 = new Line(upperRight, downRight);
        //find the intersection points
        if (l1.intersectionWith(line) != null) {
            list.add(l1.intersectionWith(line));
        }
        if ((l2.intersectionWith(line) != null)) {
            list.add(l2.intersectionWith(line));
        }
        if (l3.intersectionWith(line) != null) {
            list.add(l3.intersectionWith(line));
        }
        if (l4.intersectionWith(line) != null) {
            list.add(l4.intersectionWith(line));
        }
        return list;
    }

    /**
     * get the width.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * get the height.
     *
     * @return the height of the rectangle
     */

    public double getHeight() {
        return height;
    }

    /**
     * get the upper left point.
     *
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * @return he color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return the line of the upper side
     */
    public Line upper() {
        return new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
    }

    /**
     * @return the line of the downer side
     */
    public Line downer() {
        return new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY() + height);
    }

    /**
     * @return the line of the left side
     */
    public Line left() {
        return new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
    }

    /**
     * @return the line of the right side
     */
    public Line right() {
        return new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width,
                upperLeft.getY() + height);
    }


}