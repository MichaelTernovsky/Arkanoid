package listeners;
import components.Ball;
import components.Block;
import components.Counter;
import game.GameLevel;
import hitinterfaces.HitListener;
/**
 * Class BallRemover.
 * @author Michael Ternovsky 316534809
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    /**
     * BlockRemover - constructor of the class.
     * @param game         - the current game.
     * @param removedBalls - the removed balls.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    /**
     * hitEvent - the function removes balls that hit the "death region".
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Check if there are still some balls remained
        if (this.remainingBalls.getValue() != 0) {
            // remove the ball from the game
            hitter.removeFromGame(game);
            // decrease the counter
            this.remainingBalls.decrease(1);
        }
    }
}