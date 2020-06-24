package tasks;
import animations.Animation;
import animations.AnimationRunner;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
/**
 * Class ShowHiScoresTask.
 * @author Michael Ternovsky 316534809
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor keyboard;
    /**
     * ShowHiScoresTask - the constructor of the class.
     * @param runner              - the animation runner.
     * @param highScoresAnimation - the animation.
     * @param keyboard            - the keyboard.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor keyboard) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboard = keyboard;
    }
    /**
     * run - the function runs the animation.
     * @return Void - return null by default.
     */
    public Void run() {
        KeyPressStoppableAnimation kpsScoreDisplay = new KeyPressStoppableAnimation(this.keyboard,
                KeyboardSensor.SPACE_KEY, this.highScoresAnimation);
        this.runner.run(kpsScoreDisplay);
        return null;
    }
}