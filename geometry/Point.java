package geometry;
/**
 * Class Point.
 * @author Michael Ternovsky 316534809
 */
public class Point {
    // Fields
    private double x;
    private double y;
    // constructors
    /**
     * Point- the constructor of the class.
     */
    // Empty constructor
    public Point() {
    }
    /**
     * Point- the constructor of the class.
     * @param x - the x value of the point.
     * @param y - the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * distance - the function returns the distance between this point and the other
     * point.
     * @param other - the other point we want to calculate the distance from.
     * @return distance - double number that represents the distance between the
     *         points.
     */
    public double distance(Point other) {
        // Variables
        double distance;
        double sum;
        // Calculating the sum
        sum = ((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y));
        // Calculating the distance
        distance = (Math.sqrt(sum));
        // Return the distance
        return distance;
    }
    /**
     * equals - the function returns true is the points are equal, false otherwise.
     * @param other - Point.
     * @return boolean - true if our point and the other point are equal, false
     *         otherwise.
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }
    /**
     * getX - the function returns the x value of the point.
     * @return double: the x value of the point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * getY: the function returns the y value of the point.
     * @return double: the y value of the point.
     */
    public double getY() {
        return this.y;
    }
}