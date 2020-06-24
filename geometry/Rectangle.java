package geometry;
import java.util.ArrayList;
import java.util.List;
/**
 * Class Rectangle.
 * @author Michael Ternovsky 316534809
 */
public class Rectangle {
    // Fields
    private Point upperLeft;
    private double width;
    private double height;
    // constructors
    /**
     * Rectangle - the constructor of the class.
     * @param upperLeft - the upperLeft Point of the Rectangle.
     * @param width     - the width value of the Rectangle.
     * @param height    - the height value of the Rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * getWidth - the function returns the width and width of the rectangle.
     * @return double - the width and width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * getHeight - the function returns the height and height of the rectangle.
     * @return double - the width and height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * getUpperLeft - the function returns the upper - left point of the rectangle.
     * @return Point - the upper - left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * intersectionPoints - the function returns a (possibly empty) List of
     * intersection points with the specified line.
     * @param line - the line we want to check the intersection with.
     * @return java.util.List - the list of intersection Points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // Create the new List
        List<Point> intersectionList = new ArrayList<Point>();
        // Create the 3 other Points of the Rectangle
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point downRight = new Point(upperRight.getX(), downLeft.getY());
        // Creating the 4 lines of the Rectangle
        Line upperLine = new Line(this.upperLeft, upperRight);
        Line downLine = new Line(downLeft, downRight);
        Line rightLine = new Line(upperRight, downRight);
        Line leftLine = new Line(this.upperLeft, downLeft);
        // There is at least one intersection Point
        if (line.isIntersecting(upperLine)) {
            Point upIntersectionPoint = line.intersectionWith(upperLine);
            intersectionList.add(upIntersectionPoint);
        }
        if (line.isIntersecting(downLine)) {
            Point downIntersectionPoint = line.intersectionWith(downLine);
            intersectionList.add(downIntersectionPoint);
        }
        if (line.isIntersecting(rightLine)) {
            Point rightIntersectionPoint = line.intersectionWith(rightLine);
            intersectionList.add(rightIntersectionPoint);
        }
        if (line.isIntersecting(leftLine)) {
            Point leftIntersectionPoint = line.intersectionWith(leftLine);
            intersectionList.add(leftIntersectionPoint);
        }
        // Else - return null
        return intersectionList;
    }
}