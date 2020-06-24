package interfaces;
import components.Ball;
import components.Velocity;
import geometry.Point;
import geometry.Rectangle;
/**
 * interface Collidable - the interface will be used things that can be collided
 * with.
 * @author Michael Ternovsky 316534809
 */
public interface Collidable {
    /**
     * getCollisionRectangle - return the "collision shape" of the object.
     * @return Rectangle - the collision Rectangle.
     */
    Rectangle getCollisionRectangle();
    /**
     * hit - notify the object that we collided with it at collisionPoint with a
     * given velocity. The return is the new velocity expected after the hit (based
     * on the force the object inflicted on us).
     * @param hitter          - the ball that hit the collidable.
     * @param collisionPoint  - the collision Point.
     * @param currentVelocity - the current Velocity of the ball.
     * @return Velocity - the new Velocity after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}