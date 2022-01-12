package animations;
import biuoop.DrawSurface;
/**
 * PauseScreen class.
 * @author Michael Ternovsky
 */
public class EndScreen implements Animation {
    private boolean stop;
    private int currentScore;
    private boolean win;
    /**
     * EndScreen - the constructor of the class.
     * @param currentScore - the current score of the user.
     * @param win          - boolean parameter that tells us if the user won or
     *                     lose.
     */
    public EndScreen(int currentScore, boolean win) {
        this.stop = false;
        this.currentScore = currentScore;
        this.win = win;
    }
    /**
     * doOneFrame - the function runs one frame of the animation.
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        String newStr;
        // checking if the user win or lose
        if (this.win) {
            d.setColor(java.awt.Color.green);
            newStr = "You Win. Your score is " + Integer.toString(this.currentScore);
        } else {
            d.setColor(java.awt.Color.red);
            newStr = "Game Over. Your score is " + Integer.toString(this.currentScore);
        }
        d.drawText(180, d.getHeight() / 2, newStr, 32);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }
    }
    /**
     * shouldStop - the function returns true if the animation should stop and false
     * otherwise.
     * @return boolean -true if the animation should stop and false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
