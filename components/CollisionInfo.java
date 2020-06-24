package components;
import geometry.Point;
import interfaces.Collidable;
/**
 * CollisionInfo Block.
 * @author Michael Ternovsky 316534809
 */
public class CollisionInfo {
    // Fields
    private Point collisionPoint;
    private Collidable collisionObject;
    // Constructor
    /**
     * CollisionInfo - the constructor of the class.
     * @param collisionPoint  - the collision Point of the object.
     * @param collisionObject - the object that is colliding.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    /**
     * closestIntersectionToStartOfLine - the function returns the point at which
     * the collision occurs.
     * @return Point - the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * closestIntersectionToStartOfLine - the function returns the collidable object
     * involved in the collision.
     * @return Collidable - the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}