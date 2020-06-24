package indicators;
import java.awt.Color;
import biuoop.DrawSurface;
import game.GameLevel;
import interfaces.Sprite;
/**
 * Class NameIndicator.
 * @author Michael Ternovsky 316534809
 */
public class LevelNameIndicator implements Sprite {
    private String levelName;;
    /**
     * NameIndicator - the constructor of the class.
     * @param levelName - level name.
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }
    /**
     * drawOn - the function draws the ball on the given DrawSurface.
     * @param d - the surface in which we want to draw the ball.
     */
    public void drawOn(DrawSurface d) {
        // The name of the stage
        d.setColor(Color.DARK_GRAY);
        // Draw the number on the Block
        String newStr = "Level Name: " + this.levelName;
        // int xPosition = 800 - (newStr.length() * 8);
        int xPosition = 548;
        d.drawText(xPosition, 19, newStr, 16);
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