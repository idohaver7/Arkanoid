//318910890 Ido Haver
package GUI.Shapes;

/**
 * create an object that present line in coordinate system.
 */
public class Line {
    static final double EPSILON = 0.000001d; //threshold solution
    static final double TWO = 2;
    static final int FIRST = 0;
    private Point start;
    private Point end;


    /**
     * constructor that gets two points.
     *
     * @param start the start point
     * @param end   the end point
     **/
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

    }

    /**
     * constructor that gets 2 x and 2 y and create two points the start one and the end.
     *
     * @param x1 double of x1
     * @param y1 double of y1
     * @param x2 double of x2
     * @param y2 double of y3
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        this.start = start;
        this.end = end;
    }

    /**
     * calculate the length of the line.
     *
     * @return the length
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * find the middle point of the line.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / TWO;
        double y = (this.start.getY() + this.end.getY()) / TWO;
        return new Point(x, y);
    }

    /**
     * @return the start point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * @return the incline of the line
     */
    private double findIncline() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * @return true if the line is parallel to Y,false otherwise
     */
    private boolean isInfinityIncline() {
        return (Math.abs(this.start.getX() - this.end.getX()) < EPSILON);
    }

    /**
     * @return the intersection point with Y
     */
    private double yAxisIntersection() {
        double m = findIncline();
        return this.start.getY() - m * this.start.getX();
    }

    /**
     * get another lind and find if the lines intersect.
     *
     * @param other the other line
     * @return true if intersected,false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.equals(other)) {
            return true;
        }
        if (this.linesComingTogether(other)) {
            return true;
        }
        return this.intersectionWith(other) != null;
    }

    /**
     * get another lind and find the intersection point between them.
     *
     * @param other the other line
     * @return the intersection point or null if its doesn't exist
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        if (this.isInfinityIncline() || other.isInfinityIncline()) {
            return this.infinityInclineIntersection(other);
        }
        double thisIncline = this.findIncline();
        double otherIncline = other.findIncline();
        double thisYAxis = this.yAxisIntersection();
        double otherYAxis = other.yAxisIntersection();
        //same line equation
        if ((Math.abs(thisIncline - otherIncline) < EPSILON)) {
            if ((Math.abs(thisYAxis - otherYAxis) < EPSILON)) {
                if (this.linesComingTogether(other)) {
                    return null;
                } else {
                    return this.sameIncline(other);
                }
            }
            return null;
        }
        //base case: get the intersection by the solution of the equation
        double x = (otherYAxis - thisYAxis) / (thisIncline - otherIncline);
        double y = thisIncline * x + thisYAxis;
        Point intersection = new Point(x, y);
        if (this.isExist(intersection) && other.isExist(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * check if there is intersection in case that at least one line parallel to the Y.
     *
     * @param other the other line
     * @return the intersection point or null if it doesn't exist
     */
    private Point infinityInclineIntersection(Line other) {
        if (this.isInfinityIncline() && other.isInfinityIncline()) {
            if (this.linesComingTogether(other)) {
                return null;
            }
            //same line but not coming together
            return this.sameIncline(other);
        } else if (this.isInfinityIncline()) {
            //find the point by the solution of the equation
            double x = this.start.getX();
            double y = other.findIncline() * x + other.yAxisIntersection();
            Point intersection = new Point(x, y);
            if (this.isExist(intersection) && other.isExist(intersection)) {
                return intersection;
            }
            return null;
        }
        //find the point by the solution of the equation
        double x = other.start.getX();
        double y = this.findIncline() * x + this.yAxisIntersection();
        Point intersection = new Point(x, y);
        if (this.isExist(intersection) && other.isExist(intersection)) {
            return intersection;
        }
        return null;
    }

    /**
     * check if point is in the range of the line but not include the start and the end points.
     * but just after that we found that the point appropriate to its equation
     *
     * @param p1 the search point
     * @return true if its in range,false otherwise
     */
    private boolean isExistWithOutBorders(Point p1) {
        double x = p1.getX();
        double y = p1.getY();
        if (x < Math.max(this.start.getX(), this.end.getX()) && x > Math.min(this.start.getX(), this.end.getX())) {
            if (y < Math.max(this.start.getY(), this.end.getY()) && y > Math.min(this.start.getY(), this.end.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if the lines are coming together.
     *
     * @param other the other line
     * @return true if there are coming together false otherwise
     */
    private boolean linesComingTogether(Line other) {
        if (this.isExistWithOutBorders(other.start()) || this.isExistWithOutBorders(other.end())
                || other.isExistWithOutBorders(this.start())
                || other.isExistWithOutBorders(this.end())) {
            return true;
        }
        return false;
    }

    /**
     * check if point is in the range of the line  include the start and the end points.
     * but just after that we found that the point appropriate to its equation
     *
     * @param p1 the search point
     * @return true if its in range,false otherwise
     */

    public boolean isExist(Point p1) {
        double x = p1.getX();
        double y = p1.getY();
        if (x - EPSILON <= Math.max(this.start.getX(), this.end.getX())
                && x + EPSILON >= Math.min(this.start.getX(), this.end.getX())) {
            if (y - EPSILON <= Math.max(this.start.getY(), this.end.getY()) && y + EPSILON
                    >= Math.min(this.start.getY(), this.end.getY())) {
                return true;
            }
        }
        return false;
    }

    /**
     * get two lines with same incline and find if there is same edge point.
     *
     * @param other the other line
     * @return the same edge point if exist null otherwise
     */
    private Point sameIncline(Line other) {
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        return null;
    }

    /**
     * get another line and find if the lines are equal.
     *
     * @param other other line
     * @return true if equals false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && this.end.equals(other.end))
                || (this.end.equals(other.start()) && this.start.equals(other.end));
    }

    /**
     * getting a rectangle and return the closest intersection point with the rectangle to the start point of the line.
     *
     * @param rect the check rectangle
     * @return the closest intersection point to the starting point or null if there is no intersection between them
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> list = rect.intersectionPoints(this);
        Point closest;
        if (list.isEmpty()) {
            return null;
        } else {
            closest = list.get(FIRST);
            for (Point point : list) {
                if (closest.distance(this.start) > this.start.distance(point)) {
                    closest = point;
                }
            }
        }
        return closest;
    }
}