package basiclevels;
import java.util.ArrayList;
import java.util.List;
import components.Block;
import components.Velocity;
import game.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import interfaces.Sprite;
/**
 * Class Blue3Level.
 * @author Michael Ternovsky
 */
public class Green3Level implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private List<Point> initialBallStartingPoint;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    /**
     * Blue3Level - the constructor of the class.
     */
    public Green3Level() {
        this.numberOfBalls = 2;
        this.levelName = "Blue 3";
        this.numberOfBlocksToRemove = 27;
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.initialBallStartingPoint = new ArrayList<Point>();
        this.blocks = new ArrayList<Block>();
    }
    /**
     * numberOfBalls - returns the number of balls.
     * @return int - the number of blocks.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * initialBallVelocities - the function initials the list of velocities and
     * returns it.
     * @return List<Velocity> - the list.
     */
    public List<Velocity> initialBallVelocities() {
        Velocity ballVelocity1 = new Velocity(-6, -6);
        Velocity ballVelocity2 = new Velocity(6, -6);
        this.initialBallVelocities.add(ballVelocity1);
        this.initialBallVelocities.add(ballVelocity2);
        return this.initialBallVelocities;
    }
    /**
     * initialBallStartingPoint - the function initials the list of starting Points
     * and returns it.
     * @return List<Point> - the list.
     */
    public List<Point> initialBallStartingPoint() {
        Point ballStartingPoint = new Point(400, 550);
        this.initialBallStartingPoint.add(ballStartingPoint);
        return this.initialBallStartingPoint;
    }
    /**
     * paddleSpeed - returns the speed of the paddle.
     * @return int - the speed.
     */
    public int paddleSpeed() {
        this.paddleSpeed = 20;
        return this.paddleSpeed;
    }
    /**
     * paddleWidth - returns the width of the paddle.
     * @return int - the width.
     */
    public int paddleWidth() {
        this.paddleWidth = 120;
        return this.paddleWidth;
    }
    /**
     * levelName - returns the level name.
     * @return String - the name.
     */
    public String levelName() {
        return this.levelName;
    }
    /**
     * getBackground - returns the background of the level.
     * @return Sprite - the background.
     */
    public Sprite getBackground() {
        this.background = (Sprite) new Green3LevelImage();
        return this.background;
    }
    /**
     * blocks - returns list of blocks in the game.
     * @return List<Block> - the list of the block.
     */
    public List<Block> blocks() {
        // Creating the block we want to destroy
        int differenceX = 0, differenceY = 0;
        int numOfBlocksRow = 6;
        int numOfBlocksCol = 7;
        // Creating random color
        java.awt.Color[] color = {java.awt.Color.blue, java.awt.Color.red, java.awt.Color.yellow,
                java.awt.Color.magenta, java.awt.Color.green, java.awt.Color.pink };
        for (int i = 0; i < numOfBlocksRow; i++) {
            for (int j = 0; j < numOfBlocksCol - i; j++) {
                Point newPoint = new Point(699 - differenceX, 120 + differenceY);
                Rectangle newRectangle = new Rectangle(newPoint, 70, 30);
                Block newBlock = new Block(newRectangle, 1, (color[i % 6]));
                this.blocks.add(newBlock);
                differenceX += 70;
            }
            differenceX = 0;
            differenceY += 30;
        }
        return this.blocks;
    }
    /**
     * numberOfBlocksToRemove - returns the number of blocks that we need to remove
     * during the game.
     * @return int - the number of balls.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
