package interfaces;
import biuoop.DrawSurface;
/**
 * interface Sprite - the interface will be used by object that knows to draw
 * themselves and to know the time that passed, and the behavior when the time
 * is passed.
 * @author Michael Ternovsky 316534809
 */
public interface Sprite {
    /**
     * drawOn - the function draws the sprite to the screen.
     * @param d - the surface
     */
    void drawOn(DrawSurface d);
    /**
     * timePassed - the function notifies the sprite that time has passed.
     */
    void timePassed();
}