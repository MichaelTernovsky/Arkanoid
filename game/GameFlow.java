package game;
import java.io.File;
import java.io.IOException;
import java.util.List;
import animations.AnimationRunner;
import animations.EndScreen;
import animations.HighScoresAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import components.Counter;
import score.HighScoresTable;
import score.ScoreInfo;
/**
 * GameFlow class.
 * @author Michael Ternovsky 316534809
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private final int maxScoresSize = 5;
    private final int maxLives = 7;
    /**
     * GameFlow - the constructor of the class.
     * @param animationRunner - the animation runner.
     * @param keyboardSensor  - the keyboard sensor.
     * @param gui             - the gui.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, GUI gui) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.gui = gui;
    }
    /**
     * addNewScore - checks if the player's score is good enough to enter him to the
     * scores table.
     * @param scores       - scores current table.
     * @param scoreCounter - player's score.
     * @param f            - the file.
     * @return HighScoresTable - the new score table.
     */
    public HighScoresTable addNewScore(HighScoresTable scores, Counter scoreCounter, File f) {
        // Check if the player's score is good enough to enter him to the scores table
        if (scores.getRank(scoreCounter.getValue()) <= scores.size()) {
            // the table is full
            if (scores.getHighScores().size() == scores.size()) {
                // create a copy of the table
                List<ScoreInfo> updatedTable = scores.getHighScores();
                // remove the last cell of the sorted table
                updatedTable.remove(maxScoresSize - 1);
                // update the scores table accordingly - now there is a new free cell
                scores.updateScoresTable(updatedTable);
            }
            // get the player's name
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            // add new name to the scores table
            scores.add(new ScoreInfo(name, scoreCounter.getValue()));
            // save the new scores table
            try {
                scores.save(f);
            } catch (IOException e) {
                System.out.println("problem writing the file");
            }
        }
        return scores;
    }
    /**
     * displayScoresAnimationLoop - display the scores table.
     * @param scores - the updated table of scores.
     */
    public void displayScoresAnimationLoop(HighScoresTable scores) {
        // run the scores table display
        HighScoresAnimation scoreDisplay = new HighScoresAnimation(scores);
        KeyPressStoppableAnimation kpsScoreDisplay = new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, scoreDisplay);
        this.animationRunner.run(kpsScoreDisplay);
    }
    /**
     * runLevels - the function runs the levels.
     * @param levels - the list of levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // Score table
        HighScoresTable scores = new HighScoresTable(maxScoresSize);
        // Creating the file
        File f = new File("highscores");
        try {
            // Check if the file already exist
            if (!f.exists()) {
                // if not - we create one
                f.createNewFile();
                scores.save(f);
            } else {
                // we load the scores existing table
                scores = new HighScoresTable();
                scores.load(f);
            }
        } catch (IOException e) {
            System.out.println("problem creating the file");
        }
        boolean win = true;
        // The number of lives
        Counter livesCounter = new Counter(this.maxLives);
        // The score counter
        Counter scoreCounter = new Counter();
        for (int i = 0; i < levels.size(); i++) {
            GameLevel level = new GameLevel(levels.get(i), this.keyboardSensor, this.animationRunner, livesCounter,
                    scoreCounter);
            level.initialize();
            // while there are still lives and blocks, continue
            while (level.getblockCounter() != 0 && level.getLivesCounter() != 0) {
                level.playOneTurn();
                // update the number of lives
                livesCounter = new Counter(level.getLivesCounter());
                // update the score number
                scoreCounter = new Counter(level.getScoreCounter());
            }
            // checking if there are no more lives
            if (level.getLivesCounter() == 0) {
                win = false;
                // add new score
                scores = addNewScore(scores, scoreCounter, f);
                // End screen object - you lose
                EndScreen endScreen = new EndScreen(scoreCounter.getValue(), win);
                KeyPressStoppableAnimation kps = new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, endScreen);
                this.animationRunner.run(kps);
                // run the scores table animation
                displayScoresAnimationLoop(scores);
                break;
            } else if (level.getblockCounter() == 0 && i == levels.size() - 1) {
                // add new score
                scores = addNewScore(scores, scoreCounter, f);
                // End screen object - you win
                EndScreen endScreen = new EndScreen(scoreCounter.getValue(), win);
                KeyPressStoppableAnimation kps = new KeyPressStoppableAnimation(this.keyboardSensor,
                        KeyboardSensor.SPACE_KEY, endScreen);
                this.animationRunner.run(kps);
                // run the scores table animation
                displayScoresAnimationLoop(scores);
            }
        }
    }
}