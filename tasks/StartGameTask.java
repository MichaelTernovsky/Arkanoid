package tasks;
import java.util.ArrayList;
import java.util.List;
import animations.AnimationRunner;
import basiclevels.DirectHitLevel;
import basiclevels.FinalFourLevel;
import basiclevels.Green3Level;
import basiclevels.WideEasyLevel;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.LevelInformation;
/**
 * Class StartGameTask.
 * @author Michael Ternovsky 316534809
 */
public class StartGameTask implements Task<Void> {
    private AnimationRunner runner;
    private GUI gui;
    private KeyboardSensor keyboard;
    private List<LevelInformation> levels;
    /**
     * StartGameTask - the constructor of the class.
     * @param runner   - the animation runner.
     * @param keyboard - the keyboard.
     * @param gui      - the gui.
     * @param levels   - the levels array.
     */
    public StartGameTask(AnimationRunner runner, KeyboardSensor keyboard, GUI gui, List<LevelInformation> levels) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.gui = gui;
        this.levels = levels;
    }
    /**
     * run - the function runs the animation.
     * @return Void - return null by default.
     */
    public Void run() {
        // Create the game flow object
        GameFlow game = new GameFlow(this.runner, this.keyboard, this.gui);
        // the list of levels
        List<LevelInformation> defultLevels = null;
        if (this.levels != null) {
            defultLevels = this.levels;
            game.runLevels(defultLevels);
            return null;
        }
        if (this.levels == null) {
            // Add the default levels
            defultLevels = new ArrayList<LevelInformation>();
            defultLevels.add(new DirectHitLevel());
            defultLevels.add(new WideEasyLevel());
            defultLevels.add(new Green3Level());
            defultLevels.add(new FinalFourLevel());
            game.runLevels(defultLevels);
            return null;
        }
        return null;
    }
}