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
 * Class WideEasyLevel.
 * @author Michael Ternovsky
 */
public class WideEasyLevel implements LevelInformation {
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
     * WideEasyLevel - the constructor of the class.
     */
    public WideEasyLevel() {
        this.numberOfBalls = 10;
        this.levelName = "Wide Easy";
        this.numberOfBlocksToRemove = 15;
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
        Velocity ballVelocity1 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity1);
        Velocity ballVelocity2 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity2);
        Velocity ballVelocity3 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity3);
        Velocity ballVelocity4 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity4);
        Velocity ballVelocity5 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity5);
        Velocity ballVelocity6 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity6);
        Velocity ballVelocity7 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity7);
        Velocity ballVelocity8 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity8);
        Velocity ballVelocity9 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity9);
        Velocity ballVelocity10 = new Velocity(0, 6);
        this.initialBallVelocities.add(ballVelocity10);
        return this.initialBallVelocities;
    }
    /**
     * initialBallStartingPoint - the function initials the list of starting Points
     * and returns it.
     * @return List<Point> - the list.
     */
    public List<Point> initialBallStartingPoint() {
        Point ballStartingPoint1 = new Point(200, 500);
        this.initialBallStartingPoint.add(ballStartingPoint1);
        Point ballStartingPoint2 = new Point(245, 460);
        this.initialBallStartingPoint.add(ballStartingPoint2);
        Point ballStartingPoint3 = new Point(290, 420);
        this.initialBallStartingPoint.add(ballStartingPoint3);
        Point ballStartingPoint4 = new Point(335, 380);
        this.initialBallStartingPoint.add(ballStartingPoint4);
        Point ballStartingPoint5 = new Point(380, 340);
        this.initialBallStartingPoint.add(ballStartingPoint5);
        Point ballStartingPoint6 = new Point(425, 340);
        this.initialBallStartingPoint.add(ballStartingPoint6);
        Point ballStartingPoint7 = new Point(470, 380);
        this.initialBallStartingPoint.add(ballStartingPoint7);
        Point ballStartingPoint8 = new Point(515, 420);
        this.initialBallStartingPoint.add(ballStartingPoint8);
        Point ballStartingPoint9 = new Point(560, 460);
        this.initialBallStartingPoint.add(ballStartingPoint9);
        Point ballStartingPoint10 = new Point(605, 500);
        this.initialBallStartingPoint.add(ballStartingPoint10);
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
        this.paddleWidth = 500;
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
        this.background = (Sprite) new WideEasyLevelImage();
        return this.background;
    }
    /**
     * blocks - returns list of blocks in the game.
     * @return List<Block> - the list of the block.
     */
    public List<Block> blocks() {
        // Creating the block we want to destroy
        int differenceX = 0, differenceY = 0;
        int numOfBlocksRow = 1;
        int numOfBlocksCol = 15;
        // Creating random color
        java.awt.Color[] color = {java.awt.Color.ORANGE, java.awt.Color.YELLOW };
        for (int i = 0; i < numOfBlocksRow; i++) {
            for (int j = 0; j < numOfBlocksCol - i; j++) {
                Point newPoint = new Point(719 - differenceX, 220 + differenceY);
                Rectangle newRectangle = new Rectangle(newPoint, 49, 25);
                Block newBlock = new Block(newRectangle, 3, color[j % 2]);
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
