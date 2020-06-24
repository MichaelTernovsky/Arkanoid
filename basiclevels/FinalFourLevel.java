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
 * Class FinalFourLevel.
 * @author Michael Ternovsky 316534809
 */
public class FinalFourLevel implements LevelInformation {
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
     * FinalFourLevel - the constructor of the class.
     */
    public FinalFourLevel() {
        this.numberOfBalls = 3;
        this.levelName = "Final Four";
        this.numberOfBlocksToRemove = 105;
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
        Velocity ballVelocity1 = new Velocity(5, -6);
        this.initialBallVelocities.add(ballVelocity1);
        Velocity ballVelocity2 = new Velocity(0, -7);
        this.initialBallVelocities.add(ballVelocity2);
        Velocity ballVelocity3 = new Velocity(-5, -6);
        this.initialBallVelocities.add(ballVelocity3);
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
        this.paddleSpeed = 12;
        return this.paddleSpeed;
    }
    /**
     * paddleWidth - returns the width of the paddle.
     * @return int - the width.
     */
    public int paddleWidth() {
        this.paddleWidth = 100;
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
        this.background = (Sprite) new FinalFourLevelImage();
        return this.background;
    }
    /**
     * blocks - returns list of blocks in the game.
     * @return List<Block> - the list of the block.
     */
    public List<Block> blocks() {
        // Creating the block we want to destroy
        int differenceX = 0, differenceY = 0;
        int numOfBlocksRow = 7;
        int numOfBlocksCol = 15;
        // Creating random color
        java.awt.Color[] color = {java.awt.Color.GRAY, java.awt.Color.RED, java.awt.Color.YELLOW, java.awt.Color.GREEN,
                java.awt.Color.WHITE, java.awt.Color.PINK, java.awt.Color.DARK_GRAY };
        for (int i = 0; i < numOfBlocksRow; i++) {
            for (int j = 0; j < numOfBlocksCol; j++) {
                Point newPoint = new Point(719 - differenceX, 100 + differenceY);
                Rectangle newRectangle = new Rectangle(newPoint, 49, 25);
                Block newBlock = new Block(newRectangle, 1, color[i % 7]);
                this.blocks.add(newBlock);
                differenceX += 49;
            }
            differenceX = 0;
            differenceY += 25;
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