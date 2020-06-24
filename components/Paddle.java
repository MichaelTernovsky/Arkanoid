package components;
import java.awt.Color;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Collidable;
import interfaces.Sprite;
/**
 * Class Paddle.
 * @author Michael Ternovsky 316534809
 */
public class Paddle implements Sprite, Collidable {
    // Fields
    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Rectangle shape;
    private int width;
    private int paddleSpeed;
    /**
     * Paddle - the constructor of the class.
     * @param shape       - the shape of the paddle.
     * @param color       - the color of the paddle.
     * @param newKeyboard - the keyboardsensor for the paddle.
     * @param width       - the width of the paddle.
     * @param paddleSpeed - the speed of the paddle.
     */
    public Paddle(Rectangle shape, Color color, KeyboardSensor newKeyboard, int width, int paddleSpeed) {
        this.shape = shape;
        this.color = color;
        this.keyboard = newKeyboard;
        this.width = width;
        this.paddleSpeed = paddleSpeed;
    }
    /**
     * timePassed - the function check the movement of the paddel by using the
     * keyboard sensor.
     */
    public void timePassed() {
        // Left key is pressed
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        // Right key is pressed
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * moveRight - the function moves the paddle to right side by changing its X
     * point.
     */
    public void moveRight() {
        // The velocity of the paddle
        double xAfterChange = this.shape.getUpperLeft().getX() + this.paddleSpeed;
        // Limit case - the edge
        if (xAfterChange >= 774 - this.width) {
            xAfterChange = 774 - this.width;
            // Creating the new rectangle after the movement with the same size as before
            Point newP = new Point(xAfterChange, this.shape.getUpperLeft().getY());
            Rectangle newRect = new Rectangle(newP, this.width, 15);
            this.shape = newRect;
        }
        Point newP = new Point(xAfterChange, this.shape.getUpperLeft().getY());
        Rectangle newRect = new Rectangle(newP, this.width, 15);
        this.shape = newRect;
    }
    /**
     * moveLeft - the function moves the paddle to left side by changing its X
     * point.
     */
    public void moveLeft() {
        // The velocity of the paddle
        double xAfterChange = this.shape.getUpperLeft().getX() - this.paddleSpeed;
        // Limit case - the edge
        if (xAfterChange <= 26) {
            xAfterChange = 26;
            // Creating the new rectangle after the movement with the same size as before
            Point newP = new Point(xAfterChange, this.shape.getUpperLeft().getY());
            Rectangle newRect = new Rectangle(newP, this.width, 15);
            this.shape = newRect;
        }
        Point newP = new Point(xAfterChange, this.shape.getUpperLeft().getY());
        Rectangle newRect = new Rectangle(newP, this.width, 15);
        this.shape = newRect;
    }
    /**
     * drawOn - the function draws the paddle.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        Point p = this.shape.getUpperLeft();
        double widthCpy = this.shape.getWidth();
        double heightCpy = this.shape.getHeight();
        // Drawing the paddle
        d.drawRectangle((int) p.getX(), (int) p.getY(), (int) widthCpy, (int) heightCpy);
        d.fillRectangle((int) p.getX(), (int) p.getY(), (int) widthCpy, (int) heightCpy);
    }
    /**
     * getCollisionRectangle - the function returns the shape of the paddle return.
     * @return Rectangle - the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    /**
     * hit - notify the object that we collided with it at collisionPoint with a
     * given velocity. The return is the new velocity expected after the hit (based
     * on the force the object inflicted on us).
     * @param hitter          - the ball that hit the paddle.
     * @param collisionPoint  - the collision Point
     * @param currentVelocity - the current Velocity
     * @return Velocity - the new velocity of the block
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Create the 3 other Points of the Rectangle
        Point upperRight = new Point(this.shape.getUpperLeft().getX() + this.width, this.shape.getUpperLeft().getY());
        Point downLeft = new Point(this.shape.getUpperLeft().getX(),
                this.shape.getUpperLeft().getY() + this.shape.getHeight());
        Point downRight = new Point(upperRight.getX(), downLeft.getY());
        // Creating the 4 lines of the Rectangle
        Line upperLine = new Line(this.shape.getUpperLeft(), upperRight);
        Line downLine = new Line(downLeft, downRight);
        Line rightLine = new Line(upperRight, downRight);
        Line leftLine = new Line(this.shape.getUpperLeft(), downLeft);
        double sizeOfEachPart = (this.width / 5);
        // The current speed values
        double dx = currentVelocity.getDX();
        double dy = currentVelocity.getDY();
        // The new speed will be calculated by the old one
        double speed = Math.sqrt((dx * dx) + (dy * dy));
        // The new Velocity
        Velocity newVelocity = null;
        // Special cases - we hit the paddle in the up/down side
        if (upperLine.ifPointInLine(collisionPoint) || downLine.ifPointInLine(collisionPoint)) {
            // First of the 5 parts with - 300 angle
            if ((collisionPoint.getX() - this.shape.getUpperLeft().getX()) <= sizeOfEachPart
                    && (collisionPoint.getX() - this.shape.getUpperLeft().getX()) >= 0) {
                newVelocity = Velocity.fromAngleAndSpeed(300, speed);
                newVelocity.setDY(-1 * newVelocity.getDY());
                return newVelocity;
            }
            // Second part of the 5 parts - 330 angle
            if ((collisionPoint.getX() - this.shape.getUpperLeft().getX()) <= sizeOfEachPart * 2
                    && (collisionPoint.getX() - this.shape.getUpperLeft().getX()) >= sizeOfEachPart) {
                // Creating the new Velocity
                newVelocity = Velocity.fromAngleAndSpeed(330, speed);
                newVelocity.setDY(-1 * newVelocity.getDY());
                return newVelocity;
            }
            // In case we hit id the middle (third part) the hit is as usual - no need to
            if ((collisionPoint.getX() - this.shape.getUpperLeft().getX()) <= sizeOfEachPart * 3
                    && (collisionPoint.getX() - this.shape.getUpperLeft().getX()) >= sizeOfEachPart * 2) {
                // Creating the new Velocity
                newVelocity = currentVelocity;
                newVelocity.setDY(-1 * newVelocity.getDY());
                return newVelocity;
            }
            // change the velocity again
            // Forth part of the 5 parts - 30 angle
            if ((collisionPoint.getX() - this.shape.getUpperLeft().getX()) <= sizeOfEachPart * 4
                    && (collisionPoint.getX() - this.shape.getUpperLeft().getX()) >= sizeOfEachPart * 3) {
                // Creating the new Velocity
                newVelocity = Velocity.fromAngleAndSpeed(30, speed);
                newVelocity.setDY(-1 * newVelocity.getDY());
                return newVelocity;
            }
            // Fifth part of the 5 parts - 60 angle
            if ((collisionPoint.getX() - this.shape.getUpperLeft().getX()) <= sizeOfEachPart * 5
                    && (collisionPoint.getX() - this.shape.getUpperLeft().getX()) >= sizeOfEachPart * 4) {
                // Creating the new Velocity
                newVelocity = Velocity.fromAngleAndSpeed(60, speed);
                newVelocity.setDY(-1 * newVelocity.getDY());
                return newVelocity;
            }
        }
        // Regular case - we hit the paddle in the right/left side
        if (rightLine.ifPointInLine(collisionPoint) || leftLine.ifPointInLine(collisionPoint)) {
            // Change the x value to the opposite side
            newVelocity = new Velocity(-1 * (currentVelocity.getDX()), currentVelocity.getDY());
            return newVelocity;
        }
        // Else - return null
        return null;
    }
    /**
     * addToGame - the function adds the paddle to the game.
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * removeFromGame - the function removes the paddle to the game.
     * @param g - the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeCollidable(this);
        g.removeSprite(this);
    }
}