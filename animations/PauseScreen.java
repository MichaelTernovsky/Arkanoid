package animations;
import biuoop.DrawSurface;
/**
 * PauseScreen class.
 * @author Michael Ternovsky 316534809
 */
public class PauseScreen implements Animation {
    private boolean stop;
    /**
     * PauseScreen - the constructor of the class.
     */
    public PauseScreen() {
        this.stop = false;
    }
    /**
     * doOneFrame - the function runs one frame of the animation.
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
//        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
//            this.stop = true;
//        }
    }
    /**
     * shouldStop - the function returns true if the animation should stop and false
     * otherwise.
     * @return boolean - true if the animation should stop and false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}