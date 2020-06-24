package animations;
import biuoop.DrawSurface;
import biuoop.GUI;
/**
 * Class AnimationRunner.
 * @author Michael Ternovsky 316534809
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    /**
     * AnimationRunner - the constructor of the class.
     */
    public AnimationRunner() {
    }
    /**
     * AnimationRunner - the constructor of the class.
     * @param gui             - the GUI object.
     * @param framesPerSecond - the number of frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }
    /**
     * run - the function is responsible for running one animation.
     * @param animation - the animation we want to run.
     */
    public void run(Animation animation) {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}