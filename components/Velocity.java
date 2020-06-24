package components;
import geometry.Point;
/**
 * Class Velocity.
 * @author Michael Ternovsky 316534809
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    // Fields
    private double dx;
    private double dy;
    // constructors
    /**
     * Velocity - the empty constructor of the class.
     */
    public Velocity() {
    }
    /**
     * Velocity - the constructor of the class.
     * @param dx - the change in x axis.
     * @param dy - the change in y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * fromAngleAndSpeed - the function sets the velocity according to angle and
     * speed instead of a change in x and y axis.
     * @param angle - the angle of the ball.
     * @param speed - the speed of the ball.
     * @return Velocity - the new velocity representation.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        if (angle < 0) {
            angle += 360;
        }
        if (angle == 90) {
            return new Velocity(speed, 0);
        }
        if (angle == 0) {
            return new Velocity(0, speed);
        }
        if (angle == 180) {
            return new Velocity(0, -speed);
        }
        if (angle == 270) {
            return new Velocity(-speed, 0);
        }
        double dx = speed * (Math.sin(Math.toRadians(angle)));
        double dy = speed * (Math.cos(Math.toRadians(angle)));
        Velocity newVelocity = new Velocity(dx, dy);
        return newVelocity;
    }
    /**
     * getDX - the function returns the change in x axis.
     * @return double - the change in x axis.
     */
    public double getDX() {
        return this.dx;
    }
    /**
     * getDY - the function returns the change in y axis.
     * @return double - the change in y axis.
     */
    public double getDY() {
        return this.dy;
    }
    /**
     * setDX - the function sets the change in x axis.
     * @param newDx - the change we want to set in x axis.
     */
    public void setDX(double newDx) {
        this.dx = newDx;
    }
    /**
     * setDY - the function sets the change in y axis.
     * @param newDy - the change we want to set in y axis.
     */
    public void setDY(double newDy) {
        this.dy = newDy;
    }
    /**
     * applyToPoint - the function takes a point with position (x,y) and return a
     * new point with position (x+dx, y+dy).
     * @param p - the original point.
     * @return Point - the point after the change.
     */
    public Point applyToPoint(Point p) {
        // Calculate the new values of x and y
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        // Creating new point with the new coordinates
        Point newP = new Point(newX, newY);
        // Returning the new point
        return newP;
    }
}