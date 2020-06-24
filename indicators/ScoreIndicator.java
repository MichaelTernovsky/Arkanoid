package indicators;
import java.awt.Color;
import biuoop.DrawSurface;
import components.Counter;
import game.GameLevel;
import interfaces.Sprite;
/**
 * Class ScoreIndicator.
 * @author Michael Ternovsky 316534809
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    /**
     * ScoreIndicator - the constructor of the class.
     * @param score - the score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }
    /**
     * drawOn - the function draws the ball on the given DrawSurface.
     * @param d - the surface in which we want to draw the ball.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.DARK_GRAY);
        String scoreInString = Integer.toString(this.score.getValue());
        String newStr = "Score: " + scoreInString;
        d.drawText(370, 19, newStr, 16);
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