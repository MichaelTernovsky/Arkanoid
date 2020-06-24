package components;
import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import interfaces.Sprite;
/**
 * Class Ball.
 * @author Michael Ternovsky 316534809
 */
public class Ball implements Sprite {
    // Fields
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v; // Velocity addition
    private Point startOfFrame;
    private Point endOfFrame;
    private GameEnvironment gameEnvironment; // GameEnvironment addition
    // constructors
    /**
     * Ball - the constructor of the class.
     * @param center - the center point of the ball.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }
    /**
     * Ball - the constructor of the class.
     * @param x     - the x value of the center of the ball.
     * @param y     - the y value of the center of the ball.
     * @param r     - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        Point p = new Point(x, y);
        this.center = p;
        this.r = r;
        this.color = color;
    }
    /**
     * setTheFrame - the function sets the start and the end of the frame in which
     * the ball can move.
     * @param start - the start of the frame.
     * @param end   - the end of the frame.
     */
    public void setTheFrame(Point start, Point end) {
        this.startOfFrame = start;
        this.endOfFrame = end;
    }
    /**
     * setGameEnvironment - the function sets the the game Environment of the ball.
     * @param newGameEnvironment - the game Environment of the ball.
     */
    public void setGameEnvironment(GameEnvironment newGameEnvironment) {
        this.gameEnvironment = newGameEnvironment;
    }
    /**
     * getX - the function returns the x value of the center of the ball.
     * @return int - the x value of the center of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * getY - the function returns the y value of the center of the ball.
     * @return int - the y value of the center of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * getSize - the function returns the radius of the center of the ball.
     * @return int - the radius of the center of the ball.
     */
    public int getSize() {
        return this.r;
    }
    /**
     * getSize - the function returns the color of the ball.
     * @return java.awt.Color - the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * setVelocity - the function sets the velocity of the ball by a velocity object
     * we get.
     * @param newV - the velocity we want to set.
     */
    public void setVelocity(Velocity newV) {
        this.v = newV;
    }
    /**
     * setVelocity - the function sets the velocity of the ball by dx and dy values
     * that represents the x and y axis.
     * @param dx - movement in a x axis.
     * @param dy - movement in a y axis.
     */
    public void setVelocity(double dx, double dy) {
        Velocity newV = new Velocity(dx, dy);
        this.v = newV;
    }
    /**
     * getVelocity - the function returns the velocity of the ball.
     * @return Velocity - the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }
    /**
     * endOfTrajectory - the function calculates the end Point of the trajectory.
     * @return Point - the end Point of the trajectory.
     */
    private Point endOfTrajectory() {
        // The center of the ball
        double endX = this.center.getX();
        double endY = this.center.getY();
        // While the ball is not at the frame
        while (endX < this.endOfFrame.getX() && endX > this.startOfFrame.getX() && endY > this.startOfFrame.getY()
                && endY < this.endOfFrame.getY()) {
            // The ball keeps moving as usual
            endX = endX + this.v.getDX();
            endY = endY + this.v.getDY();
        }
        // Minus the current velocity
        endX = endX - this.v.getDX();
        endY = endY - this.v.getDY();
        return new Point(endX, endY);
    }
    /**
     * whereItHit - semi function that helps moveOneStep to know in which direction
     * (side) the ball hits.
     * @param centerAfterMovement - the center of the ball after the expected
     *                            movement.s
     * @param info                - the collision info.
     */
    private void whereItHit(Point centerAfterMovement, CollisionInfo info) {
        /*
         * Note - we want the ball to hit slightly before the collision Point, so we
         * will add/reduce the ball radius in order to make the hit look as closest as
         * possible to the collision Point. We will reduce the radius when the ball hits
         * the collidable object from down and left sides (opposite to x axis) and we
         * will add the radius when the ball hits the object from up and right sides
         * (Opposite to y axis).
         */
        // Assisting variable - flag that helps know if there was already a hit
        boolean flag = true;
        // The collision is in the up side of the rectangle
        if (this.v.getDX() == 0 && this.v.getDY() > 0 && flag) {
            if (centerAfterMovement.getY() + this.r >= info.collisionPoint().getY()) {
                this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                flag = false;
            }
        }
        // The collision is in the down side of the rectangle
        if (this.v.getDX() == 0 && this.v.getDY() < 0 && flag) {
            if (centerAfterMovement.getY() - this.r <= info.collisionPoint().getY()) {
                this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                flag = false;
            }
        }
        // The collision is in the left side of the rectangle
        if (this.v.getDX() > 0 && this.v.getDY() == 0 && flag) {
            if (centerAfterMovement.getX() + this.r >= info.collisionPoint().getX()) {
                this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                flag = false;
            }
        }
        // The collision is in the right side of the rectangle
        if (this.v.getDX() < 0 && this.v.getDY() == 0 && flag) {
            if (centerAfterMovement.getX() - this.r <= info.collisionPoint().getX()) {
                this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                flag = false;
            }
        }
        // The collision is in the up right side of the rectangle
        if (this.v.getDX() < 0 && this.v.getDY() > 0 && flag) {
            if (centerAfterMovement.getY() + this.r >= info.collisionPoint().getY()
                    || centerAfterMovement.getX() - this.r <= info.collisionPoint().getX()) {
                this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                flag = false;
            }
        }
        // The collision is in the up left side of the rectangle
        if (this.v.getDX() > 0 && this.v.getDY() > 0 && flag) {
            if (centerAfterMovement.getY() + this.r >= info.collisionPoint().getY()
                    || centerAfterMovement.getX() + this.r >= info.collisionPoint().getX()) {
                this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                flag = false;
            }
        }
        // The collision is in the down right side of the rectangle
        if (this.v.getDX() < 0 && this.v.getDY() < 0 && flag) {
            if (centerAfterMovement.getX() - this.r <= info.collisionPoint().getX()
                    || centerAfterMovement.getY() - this.r <= info.collisionPoint().getY()) {
                this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                flag = false;
            }
        }
        // The collision is in the down left side of the rectangle
        if (this.v.getDX() > 0 && this.v.getDY() < 0 && flag) {
            if (centerAfterMovement.getY() - this.r <= info.collisionPoint().getY()
                    || centerAfterMovement.getX() + this.r >= info.collisionPoint().getX()) {
                this.v = info.collisionObject().hit(this, info.collisionPoint(), this.v);
                flag = false;
            }
        }
    }
    /**
     * moveOneStep - the function executes one step move of the ball by its velocity
     * inside the limit frame.
     */
    public void moveOneStep() {
        // Assisting variable that helps us know when the ball needs to move regularly
        boolean isMoveRegular = false;
        // Creating info object
        CollisionInfo info = null;
        // Limit case - there is no speed
        if (this.v == null) {
            return;
        }
        // Simulates the expected movement of the ball
        Point newCenter = this.v.applyToPoint(this.center);
        if (this.gameEnvironment != null) {
            // Expected trajectory of the ball by the start and end Point.
            Point endPoint = endOfTrajectory();
            Line trajectory = new Line(this.center, endPoint); // creating the trajectory
            // Checking if the trajectory will meet an obstacle
            info = this.gameEnvironment.getClosestCollision(trajectory);
            // There is a collision
            if (info != null) {
                // Summoning function that checking the cases of collision
                whereItHit(newCenter, info);
                // Executing the real movement after calculating the velocity
                this.center = this.getVelocity().applyToPoint(this.center);
            } else {
                // There is no collision at all
                isMoveRegular = true;
            }
        } else {
            // There is no game environment
            isMoveRegular = true;
        }
        if (isMoveRegular) {
            // Checking if the ball is in the range of the width - left and right
            if ((newCenter.getX() - this.r > this.endOfFrame.getX())
                    || (newCenter.getX() - this.r < this.startOfFrame.getX())) {
                this.setVelocity(-1 * (this.v.getDX()), this.v.getDY());
            }
            // Checking if the ball is in the range of the height - up and down
            if ((newCenter.getY() - this.r > this.endOfFrame.getX())
                    || (newCenter.getY() - this.r < this.startOfFrame.getX())) {
                this.setVelocity(this.v.getDX(), -1 * (this.v.getDY()));
            }
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }
    /**
     * drawOn - the function draws the ball on the given DrawSurface.
     * @param surface - the surface in which we want to draw the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), r);
    }
    /**
     * timePassed - the function notifies the sprite that time has passed.
     */
    public void timePassed() {
        moveOneStep();
    }
    /**
     * addToGame - the function adds the ball to the game.
     * @param g - the game we want the add to ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * removeFromGame - the function removes the ball from the game.
     * @param g - the game we want to remove the ball from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}