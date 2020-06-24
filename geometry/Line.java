package geometry;
import java.util.List;
/**
 * Class Line.
 * @author Michael Ternovsky 316534809
 */
public class Line {
    // Fields
    private Point start;
    private Point end;
    // constructors
    /**
     * Line - the constructor of the class.
     * @param start - starting Point of the the line.
     * @param end   - ending Point of the the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Line - the constructor of the class.
     * @param x1 - x value of the starting Point.
     * @param x2 - x value of the ending Point.
     * @param y1 - y value of the starting Point.
     * @param y2 - y value of the ending Point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }
    /**
     * length - the function returns the length of the line.
     * @return double: the length of the line.
     */
    public double length() {
        // Variables
        double distance;
        // The distance
        distance = this.start.distance(this.end);
        // Returning the distance
        return distance;
    }
    /**
     * middle - the function returns the middle point of the line.
     * @return Point: the middle Point of the line;
     */
    public Point middle() {
        // Variables
        double middleX, middleY;
        double sumX, sumY;
        // The sum of the x and y values of the point
        sumX = this.start.getX() + this.end.getX();
        sumY = this.start.getY() + this.end.getY();
        // The middle values of x and y
        middleX = sumX / 2;
        middleY = sumY / 2;
        // Declaration of new point
        Point middlePoint = new Point(middleX, middleY);
        // Returning the middle point
        return middlePoint;
    }
    /**
     * start - the function returns the start point of the line.
     * @return Point - the starting Point of the line.
     */
    public Point start() {
        return this.start;
    }
    /**
     * start - the function returns the end point of the line.
     * @return Point - the ending Point of the line.
     */
    public Point end() {
        return this.end;
    }
    /**
     * equals - the function returns true if the lines are equal, false otherwise.
     * @param other - the Line that we intend to check if is equals to our line.
     * @return boolean - true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        // X and Y values of the start point of our line
        double lineStartX = this.start.getX();
        double lineStartY = this.start.getY();
        // X and Y values of the end point of our line
        double lineEndX = this.end.getX();
        double lineEndY = this.end.getY();
        // X and Y values of the start point of the other line
        double otherLineStartX = other.start().getX();
        double otherLineStartY = other.start().getY();
        // X and Y values of the end point of the other line
        double otherLineEndX = other.end().getX();
        double otherLineEndY = other.end().getY();
        return (lineStartX == otherLineStartX && lineStartY == otherLineStartY && lineEndX == otherLineEndX
                && lineEndY == otherLineEndY);
    }
    /**
     * equals - the function returns the slope of the Line.
     * @return double - the slope of the Line.
     */
    private double slope() {
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }
    /**
     * casesToCheck - the function calculates the intersection Point in which other
     * line and the other line.
     * @param other - the other line we want to check.
     * @return Point - the expected intersection Point.
     */
    public Point casesToCheck(Line other) {
        // Checking if the lines are equal, if they are, return the starting point of
        // the lines
        if (this.equals(other)) {
            Point intersection = new Point(this.start.getX(), this.start.getY());
            return intersection;
        }
        // Checking if the Lines intersect exactly at edge
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            // Checking if our Line is a Point, instead of a line that is defined by two
            // points
            if (this.start.getX() == this.end.getX() && this.start.getY() == this.end.getY()) {
                Point intersection = new Point(this.start.getX(), this.start.getY());
                return intersection;
            }
            // Checking if the other Line is a Point, instead of a line that is defined by
            // two points
            if (other.start.getX() == other.end.getX() && other.start.getY() == other.end.getY()) {
                Point intersection = new Point(other.start.getX(), other.start.getY());
                return intersection;
            }
            // The lines intersect exactly at the edge
            if (this.start.getX() == other.start.getX()) {
                if (this.start.getY() == other.start.getY() || other.start.getY() == other.end.getY()) {
                    Point intersection = new Point(this.start.getX(), this.start.getY());
                    return intersection;
                }
                if (this.end.getY() == other.start.getY() || this.end.getY() == other.end.getY()) {
                    Point intersection = new Point(this.start.getX(), this.end.getY());
                    return intersection;
                }
            }
            return null;
        }
        // Checking if our line is not a function
        if (this.start.getX() == this.end.getX() && other.start.getX() != other.end.getX()) {
            double ourLineX = this.start.getX();
            double m1 = other.slope();
            double b1 = other.start.getY() - (m1 * other.start.getX());
            double ourLineY = (m1 * ourLineX) + b1;
            Point intersection = new Point(ourLineX, ourLineY);
            return intersection;
        }
        // Checking if the other line is not a function
        if (this.start.getX() != this.end.getX() && other.start.getX() == other.end.getX()) {
            double otherLineX = other.start.getX();
            double m1 = this.slope();
            double b1 = this.start.getY() - (m1 * this.start.getX());
            double otherLineY = (m1 * otherLineX) + b1;
            Point intersection = new Point(otherLineX, otherLineY);
            return intersection;
        }
        // Checking if both Lines are not functions
        if (this.start.getX() == this.end.getX() && other.start.getX() == other.end.getX()) {
            // Checking if the Lines intersect at edge of segment
            if (this.start.getX() == other.start.getX()) {
                if (this.start.getY() == other.start.getY() || this.start.getY() == other.end.getY()) {
                    Point intersection = new Point(this.start.getX(), this.start.getY());
                    return intersection;
                }
                if (this.end.getY() == other.start.getY() || this.end.getY() == other.end.getY()) {
                    Point intersection = new Point(this.start.getX(), this.end.getY());
                    return intersection;
                }
                if (this.start.getX() == this.end.getX() && this.start.getY() == this.end.getY()) {
                    // Checking if our Line is a Point
                    Point intersection = new Point(this.start.getX(), this.start.getY());
                    return intersection;
                }
                if (other.start.getX() == other.end.getX() && other.start.getY() == other.end.getY()) {
                    // Checking if other Line is a Point
                    Point intersection = new Point(other.start.getX(), other.start.getY());
                    return intersection;
                }
            }
            return null;
        }
        // The slope of our line
        double m1 = this.slope();
        // The slope of the other line
        double m2 = other.slope();
        // If the slopes are equals, the lines are parallel
        if (m1 == m2) {
            return null;
        } else {
            // X and Y values of the start point of our line
            double lineStartX = this.start.getX();
            double lineStartY = this.start.getY();
            // X and Y values of the start point of the other line
            double otherLineStartX = other.start().getX();
            double otherLineStartY = other.start().getY();
            // Finding the n value of the first line
            double n1 = lineStartY - (m1 * lineStartX);
            // Finding the n value of the second line
            double n2 = otherLineStartY - (m2 * otherLineStartX);
            // Finding the x value of the intersect point
            double intersectX = (n2 - n1) / (m1 - m2);
            // Finding the y value of the intersect point
            double intersectY = (m1 * intersectX) + n1;
            Point intersection = new Point(intersectX, intersectY);
            return intersection;
        }
    }
    /**
     * ifPointInLine - the function return true if the Point is in Line, and false
     * otherwise.
     * @param p - the Point we want to check if in Line.
     * @return boolean - true if the Point is in Line, and false otherwise.
     */
    public boolean ifPointInLine(Point p) {
        // We will calculate the distance between the start to the point we
        // want to check, and than from the end to the point we want to check. The sum
        // of those two distances should be equal to the length of the line.
        Point p1 = new Point(this.start().getX(), this.start().getY());
        Point p2 = new Point(this.end().getX(), this.end().getY());
        // The length of the lines segments
        double distanceAB = p1.distance(p2);
        double distanceAC = p1.distance(p);
        double distanceBC = p2.distance(p);
        if (Math.abs(distanceAB - distanceAC - distanceBC) <= 0.000000001) {
            return true;
        }
        return false;
    }
    /**
     * intersectionWith - the function returns the intersection point if the lines
     * intersect, and null otherwise.
     * @param other - the Line we intend to check the intersection with.
     * @return Point - the intersection point if the lines intersect, and null
     *         otherwise.
     */
    public Point intersectionWith(Line other) {
        // Checking if there is intersection Point (not null) and that Point in in the
        // two Lines
        Point intersectionPoint = this.casesToCheck(other);
        if (intersectionPoint != null && this.ifPointInLine(intersectionPoint)
                && other.ifPointInLine(intersectionPoint)) {
            return intersectionPoint;
        }
        return null;
    }
    /**
     * isIntersecting - the function returns true if the lines intersect, false
     * otherwise.
     * @param other - the Line we intend to check if it intersect with ours.
     * @return boolean - true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point intersectionPoint = intersectionWith(other);
        return intersectionPoint != null;
    }
    /**
     * closestIntersectionToStartOfLine - the function returns the closest
     * intersection point to the start of the line. If this line does not intersect
     * with the rectangle, return null.
     * @param rect - the Rectangle we intend to check the intersection with.
     * @return Point - the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        int theClosetIntersectionPoint = 0;
        double min = this.start().distance(intersectionPoints.get(0));
        for (int i = 0; i < intersectionPoints.size(); i++) {
            double temp = this.start().distance((Point) intersectionPoints.get(i));
            if (temp < min) {
                min = temp;
                theClosetIntersectionPoint = i;
            }
        }
        return (Point) intersectionPoints.get(theClosetIntersectionPoint);
    }
}