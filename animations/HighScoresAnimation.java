package animations;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import biuoop.DrawSurface;
import score.HighScoresTable;
import score.ScoreInfo;
/**
 * HighScoresAnimation class.
 * @author Michael Ternovsky
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable scores;
    private final int maxScoresSize = 5;
    /**
     * HighScoresAnimation - constructor of the class. load the file into a
     * HighScoresTable object and than build HighScoresAnimation object.
     */
    public HighScoresAnimation() {
        this.stop = false;
        // call the building function
        this.buildHighScoreAnimation();
    }
    /**
     * HighScoresAnimation - constructor of the class. get scores table and build
     * HighScoresAnimation object.
     * @param scores1 - the scores table.
     */
    public HighScoresAnimation(HighScoresTable scores1) {
        this.stop = false;
        this.scores = scores1;
    }
    /**
     * buildHighScoreAnimation - the function load the file into a HighScoresTable
     * object and than build HighScoresAnimation object.
     * @return HighScoresAnimation - the HighScoresAnimation object.
     */
    private HighScoresAnimation buildHighScoreAnimation() {
        // Score table
        HighScoresTable scores1 = null;
        // Creating the file
        File f = new File("highscores");
        try {
            // Check if the file already exist
            if (!f.exists()) {
                // if not - we create one
                f.createNewFile();
                scores1 = new HighScoresTable(maxScoresSize);
                scores1.save(f);
            } else {
                // we load the scores existing table
                scores1 = new HighScoresTable();
                scores1.load(f);
            }
        } catch (IOException e) {
            System.out.println("problem creating the file");
        }
        this.scores = scores1;
        return new HighScoresAnimation(scores1);
    }
    /**
     * doOneFrame - the function runs one frame of the animation.
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 800);
        d.setColor(Color.white);
        d.drawText(200, 100, "Name", 30);
        d.drawText(500, 100, "Score", 30);
        d.drawLine(180, 110, 600, 110);
        int y = 0;
        for (ScoreInfo playerScore : this.scores.getHighScores()) {
            // name
            String name = playerScore.getName();
            d.drawText(200, 200 + y, name, 25);
            // score
            String score = Integer.toString(playerScore.getScore());
            d.drawText(520, 200 + y, score, 25);
            y += 28;
        }
        d.setColor(java.awt.Color.green);
        d.drawText(250, 550, "press space to continue", 30);
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
