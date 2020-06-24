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
 * Class DirectHitLevel.
 * @author Michael Ternovsky 316534809
 */
public class DirectHitLevel implements LevelInformation {
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
     * DirectHitLevel - the constructor of the class.
     */
    public DirectHitLevel() {
        this.numberOfBalls = 1;
        this.levelName = "Direct Hit";
        this.numberOfBlocksToRemove = 1;
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
        Velocity ballVelocity = new Velocity(0, -5);
        this.initialBallVelocities.add(ballVelocity);
        return this.initialBallVelocities;
    }
    /**
     * initialBallStartingPoint - the function initials the list of starting Points
     * and returns it.
     * @return List<Point> - the list.
     */
    public List<Point> initialBallStartingPoint() {
        Point ballStartingPoint = new Point(400, 570);
        this.initialBallStartingPoint.add(ballStartingPoint);
        return this.initialBallStartingPoint;
    }
    /**
     * paddleSpeed - returns the speed of the paddle.
     * @return int - the speed.
     */
    public int paddleSpeed() {
        this.paddleSpeed = 10;
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
        this.background = (Sprite) new DirectHitLevelImage();
        return this.background;
    }
    /**
     * blocks - returns list of blocks in the game.
     * @return List<Block> - the list of the block.
     */
    public List<Block> blocks() {
        // Creating the block we want to destroy
        Rectangle destoryRect = new Rectangle(new Point(388, 138), 25, 25);
        Block destoryBlock = new Block(destoryRect, 1, java.awt.Color.RED);
        this.blocks.add(destoryBlock);
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