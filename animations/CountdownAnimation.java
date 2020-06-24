package animations;
import java.awt.Color;
import biuoop.DrawSurface;
import components.SpriteCollection;
import game.LevelInformation;
/**
 * Class CountdownAnimation.
 * @author Michael Ternovsky 316534809
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int framesCounter;
    private int maxFrame;
    private LevelInformation levelInfo;
    /**
     * CountdownAnimation - the constructor of the class.
     * @param numOfSeconds - the number of seconds.
     * @param countFrom    - the starting value of the counter.
     * @param gameScreen   - the game screen.
     * @param levelInfo    - the information of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
            LevelInformation levelInfo) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.framesCounter = 0;
        this.maxFrame = (int) (this.numOfSeconds * 60 / this.countFrom);
        this.levelInfo = levelInfo;
    }
    /**
     * doOneFrame - the function runs one frame of the class.
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        // The background
        this.levelInfo.getBackground().drawOn(d);
        // draw the game
        gameScreen.drawAllOn(d);
        // draw the count down text
        String countFromStr = Integer.toString(this.countFrom);
        d.drawText(380, 310, countFromStr, 100);
        // timing for the count down
        if (this.framesCounter == this.maxFrame) {
            this.framesCounter = 0;
            this.countFrom -= 1;
        }
        if (this.countFrom == 0) {
            this.stop = true;
        }
        // counter++
        this.framesCounter += 1;
    }
    /**
     * shouldStop - returns true if the animation should stop, and false otherwise.
     * @return boolean - true if the animation should stop, and false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}