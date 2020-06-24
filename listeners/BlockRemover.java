package listeners;
import components.Ball;
import components.Block;
import components.Counter;
import game.GameLevel;
import hitinterfaces.HitListener;
/**
 * Class BlockRemover.
 * @author Michael Ternovsky 316534809
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * BlockRemover - constructor of the class.
     * @param game          - the current game.
     * @param removedBlocks - the removed blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    /**
     * hitEvent - Blocks that are hit and reach 0 hit-points should be removed from
     * the game. Remember to remove this listener from the block that is being
     * removed from the game.
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Check if there are still some blocks remained
        if (this.remainingBlocks.getValue() != 0) {
            if (beingHit.getHitPoints() <= 1) {
                beingHit.removeHitListener(this);
                // remove the block from the game
                beingHit.removeFromGame(this.game);
                // decrease the counter
                this.remainingBlocks.decrease(1);
            }
        }
    }
}