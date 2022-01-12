package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * Class KeyPressStoppableAnimation.
 * @author Michael Ternovsky
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;
    /**
     * KeyPressStoppableAnimation - the constructor of the class.
     * @param sensor    - the keyboard
     * @param key       - the key that make the behavior begin.
     * @param animation - the animation that runs.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    /**
     * doOneFrame - executes one frame of the animation.
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
    /**
     * shouldStop - return true if the animation should stop and else otherwise.
     * @return boolean - true if the animation should stop and else otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
