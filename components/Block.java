package components;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import hitinterfaces.HitListener;
import hitinterfaces.HitNotifier;
import interfaces.Collidable;
import interfaces.Sprite;
/**
 * Class Block.
 * @author Michael Ternovsky 316534809
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // Fields
    private Rectangle shape;
    private int numOfHit;
    private java.awt.Color colorStroke;
    private List<HitListener> hitListeners;
    private Map<Integer, Color> colorFill;
    private Map<Integer, Image> imageFill;
    /**
     * Block - the constructor of the class.
     * @param shape - the shape of the Block.
     */
    public Block(Rectangle shape) {
        this.shape = shape;
        this.hitListeners = new ArrayList<HitListener>();
        this.colorFill = new HashMap<Integer, Color>();
        this.imageFill = new HashMap<Integer, Image>();
    }
    /**
     * Block - the constructor of the class.
     * @param shape       - the shape of the Block.
     * @param numOfHit    - the number of hits we want that the block will have.
     * @param colorStroke - the color of the block
     */
    public Block(Rectangle shape, int numOfHit, java.awt.Color colorStroke) {
        this.shape = shape;
        this.numOfHit = numOfHit;
        this.colorStroke = colorStroke;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * getCollisionRectangle - return the "collision shape" of the object.
     * @return Rectangle - the shape of the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    /**
     * getHitPoints - the function returns the number of hits.
     * @return int - the number of hits.
     */
    public int getHitPoints() {
        return this.numOfHit;
    }
    /**
     * hit - notify the object that we collided with it at collisionPoint with a
     * given velocity. The return is the new velocity expected after the hit (based
     * on the force the object inflicted on us).
     * @param hitter          - the ball that hit the block.
     * @param collisionPoint  - the collision Point
     * @param currentVelocity - the current Velocity
     * @return Velocity - the new velocity of the block
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // The new addition
        this.notifyHit(hitter);
        // Calculating the number of hits
        if (this.numOfHit > 0) {
            this.numOfHit = this.numOfHit - 1;
        } else {
            this.numOfHit = 0;
        }
        // Local copies
        Point upperLeft = this.shape.getUpperLeft();
        double height = this.shape.getHeight();
        double width = this.shape.getWidth();
        // Create the 3 other Points of the Rectangle
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point downRight = new Point(upperRight.getX(), downLeft.getY());
        // Creating the 4 lines of the Rectangle
        Line upperLine = new Line(upperLeft, upperRight);
        Line downLine = new Line(downLeft, downRight);
        Line rightLine = new Line(upperRight, downRight);
        Line leftLine = new Line(upperLeft, downLeft);
        // The hit is on the up/down side
        if (upperLine.ifPointInLine(collisionPoint) || downLine.ifPointInLine(collisionPoint)) {
            Velocity newVelocity = new Velocity(currentVelocity.getDX(), -1 * (currentVelocity.getDY()));
            return newVelocity;
        }
        // The hit is on the left/right side
        if (leftLine.ifPointInLine(collisionPoint) || rightLine.ifPointInLine(collisionPoint)) {
            Velocity newVelocity = new Velocity(-1 * (currentVelocity.getDX()), currentVelocity.getDY());
            return newVelocity;
        }
        // The hit is on the up left side
        if (upperLine.ifPointInLine(collisionPoint) && leftLine.ifPointInLine(collisionPoint)) {
            if (currentVelocity.getDX() > 0) {
                Velocity v = new Velocity(-currentVelocity.getDX(), currentVelocity.getDY());
                return v;
            }
            if (currentVelocity.getDY() > 0) {
                Velocity v = new Velocity(currentVelocity.getDX(), -1 * (currentVelocity.getDY()));
                return v;
            }
        }
        // The hit is on the up right side
        if (upperLine.ifPointInLine(collisionPoint) && rightLine.ifPointInLine(collisionPoint)) {
            if (currentVelocity.getDX() < 0) {
                Velocity v = new Velocity(-1 * (currentVelocity.getDX()), currentVelocity.getDY());
                return v;
            }
            if (currentVelocity.getDY() > 0) {
                Velocity v = new Velocity(currentVelocity.getDY(), -1 * (currentVelocity.getDY()));
                return v;
            }
        }
        // The hit is on the down left side
        if (upperLine.ifPointInLine(collisionPoint) && leftLine.ifPointInLine(collisionPoint)) {
            if (currentVelocity.getDX() > 0) {
                Velocity v = new Velocity(-1 * (currentVelocity.getDX()), currentVelocity.getDY());
                return v;
            }
            if (currentVelocity.getDY() < 0) {
                Velocity v = new Velocity(currentVelocity.getDX(), -1 * (currentVelocity.getDY()));
                return v;
            }
        }
        // The hit is on the down right side
        if (downLine.ifPointInLine(collisionPoint) && rightLine.ifPointInLine(collisionPoint)) {
            if (currentVelocity.getDX() < 0) {
                Velocity v = new Velocity(-1 * (currentVelocity.getDX()), currentVelocity.getDY());
                return v;
            }
            if (currentVelocity.getDY() < 0) {
                Velocity v = new Velocity(currentVelocity.getDX(), -1 * (currentVelocity.getDY()));
                return v;
            }
        }
        // else - return null
        return null;
    }
    /**
     * setColorFillMap - set the colors map.
     * @param colorFill1 - the colors map.
     */
    public void setColorFillMap(Map<Integer, Color> colorFill1) {
        this.colorFill = colorFill1;
    }
    /**
     * setImageFillMap - set the images map.
     * @param imageFill1 - the images map.
     */
    public void setImageFillMap(Map<Integer, Image> imageFill1) {
        this.imageFill = imageFill1;
    }
    /**
     * drawOn - the function draws the sprite to the screen.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        // The data of the shape
        double xValue = this.shape.getUpperLeft().getX();
        double yValue = this.shape.getUpperLeft().getY();
        double width = this.shape.getWidth();
        double height = this.shape.getHeight();
        // Fill the shape - Image/Color
        if (this.colorFill != null && this.colorFill.containsKey(this.numOfHit)) { // the fill is color
            Color c = this.colorFill.get(this.numOfHit);
            // Draw the shape
            d.setColor(c); // block fill color
            d.fillRectangle((int) xValue, (int) yValue, (int) width, (int) height);
            if (this.colorStroke != null) {
                d.setColor(this.colorStroke); // stroke color
            }
            d.drawRectangle((int) xValue, (int) yValue, (int) width, (int) height);
        } else if (this.imageFill != null && this.imageFill.containsKey(this.numOfHit)) { // the fill is image
            Image img = this.imageFill.get(this.numOfHit);
            // Draw the image on a DrawSurface
            d.drawImage((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(), img);
        } else if (this.colorFill != null && this.colorFill.containsKey(-1)) {
            // use the default values
            Color c = this.colorFill.get(-1);
            // Draw the shape
            d.setColor(c); // block fill color
            d.fillRectangle((int) xValue, (int) yValue, (int) width, (int) height);
            if (this.colorStroke != null) {
                d.setColor(this.colorStroke); // stroke color
            }
            d.drawRectangle((int) xValue, (int) yValue, (int) width, (int) height);
        } else if (this.imageFill != null && this.imageFill.containsKey(-1)) {
            Image img = this.imageFill.get(-1);
            // Draw the image on a DrawSurface
            d.drawImage((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(), img);
        } else if (this.colorFill == null && this.imageFill == null) {
            // case for the 4 background shapes rectangles
            if (this.colorStroke != null) {
                d.setColor(this.colorStroke);
            }
            d.fillRectangle((int) xValue, (int) yValue, (int) width, (int) height);
            d.setColor(java.awt.Color.BLACK);
            d.drawRectangle((int) xValue, (int) yValue, (int) width, (int) height);
        }
    }
    /**
     * timePassed - the function notifies the sprite that time has passed.
     */
    public void timePassed() {
    }
    /**
     * addToGame - the function adds the block to the game.
     * @param g - the game we want to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * removeFromGame - the function removes the current Block (this) from the given
     * game.
     * @param game - the given game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * addHitListener - Add hl as a listener to hit events.
     * @param hl - the hit HitListener we want to add the block to.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * removeHitListener - Remove hl from the list of listeners to hit events.
     * * @param hl - the hit HitListener we want to remove the block from.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * notifyHit - the function notify that there was a hit with the block.
     * @param hitter - the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}