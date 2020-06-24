package game;
import java.util.List;
import components.Block;
import components.Velocity;
import geometry.Point;
import interfaces.Sprite;
/**
 * Class LevelInformation.
 * @author Michael Ternovsky 316534809
 */
public interface LevelInformation {
    /**
     * numberOfBalls - returns the number of balls in the level.
     * @return int - the number of balls in the level.
     */
    int numberOfBalls();
    /**
     * initialBallVelocities - the initial velocity of each ball. Note that
     * initialBallVelocities().size() == numberOfBalls().
     * @return List<Velocity> - the list of Velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * initialBallStartingPoint - the starting Points of each ball.
     * @return List<Point> - the list of starting Points.
     */
    List<Point> initialBallStartingPoint();
    /**
     * paddleSpeed - returns the speed of the paddle.
     * @return int - the speed of the paddle.
     */
    int paddleSpeed();
    /**
     * paddleWidth - returns the width of the paddle.
     * @return int - the width of the paddle.
     */
    int paddleWidth();
    /**
     * levelName - the level name will be displayed at the top of the screen.
     * @return String - the level name.
     */
    String levelName();
    /**
     * getBackground - returns a sprite with the background of the level.
     * @return Sprite - the background.
     */
    Sprite getBackground();
    /**
     * blocks - the Blocks that make up this level, each block contains its size,
     * color and location.
     * @return List<Block> - the list of the blocks.
     */
    List<Block> blocks();
    /**
     * numberOfBlocksToRemove - Number of levels that should be removed before the
     * level is considered to be "cleared". This number should be <= blocks.size();
     * @return int - the number of blocks.
     */
    int numberOfBlocksToRemove();
}