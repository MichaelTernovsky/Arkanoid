package indicators;
import java.awt.Color;
import biuoop.DrawSurface;
import components.Counter;
import game.GameLevel;
import interfaces.Sprite;
/**
 * Class LivesIndicator.
 * @author Michael Ternovsky 316534809
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;
    /**
     * LivesIndicator - the constructor of the class.
     * @param livesCounter - the score counter.
     */
    public LivesIndicator(Counter livesCounter) {
        this.livesCounter = livesCounter;
    }
    /**
     * drawOn - the function draws the ball on the given DrawSurface.
     * @param d - the surface in which we want to draw the ball.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        // Draw the number on the Block
        String scoreInString = Integer.toString(this.livesCounter.getValue());
        String newStr = "Lives: " + scoreInString;
        d.drawText(15, 19, newStr, 16);
    }
    /**
     * addToGame - the function adds the ball to the game.
     * @param g - the game we want the add to ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * timePassed - the function notifies the sprite that time has passed.
     */
    public void timePassed() {
    }
}