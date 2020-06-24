package game;
import java.util.ArrayList;
import java.util.List;
import components.CollisionInfo;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

/**
 * GameEnvironment class.
 * @author Michael Ternovsky 316534809
 */
public class GameEnvironment {
    // Fields
    private List<Collidable> collidables;
    /**
     * GameEnvironment - empty constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * addCollidable - the function adds the given collidable to the environment.
     * @param c - Collidable environment we want to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * addCollidable - assume an object moving from line.start() to line.end(). If
     * this object will not collide with any of the collidables in this collection,
     * return null. Else, return the information about the closest collision that is
     * going to occur.
     * @param trajectory - the line we want to check the collision with.
     * @return CollisionInfo - the CollisionInfo.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Checking if the list is empty
        if (collidables == null) {
            return null;
        }
        Point intersectionPoint;
        Collidable closestCollidable = null;
        // Default case
        Point closestPoint = trajectory.end();
        for (Collidable c : this.collidables) {
            // Get the collision Point
            intersectionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersectionPoint != null) {
                if (trajectory.start().distance(intersectionPoint) < trajectory.start().distance(closestPoint)) {
                    closestCollidable = c;
                    closestPoint = intersectionPoint;
                }
            }
        }
        if (closestPoint.equals(trajectory.end())) {
            return null;
        }
        // Return the CollisionInfo of the object we found
        CollisionInfo info = new CollisionInfo(closestPoint, closestCollidable);
        return info;
    }
    /**
     * removeCollidable - the function remove the given collidable from the
     * environment.
     * @param c - Collidable environment we want to add.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}