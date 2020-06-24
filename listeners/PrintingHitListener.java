package listeners;
import components.Ball;
import components.Block;
import hitinterfaces.HitListener;
/**
 * Class PrintingHitListener.
 * @author Michael Ternovsky 316534809
 */
public class PrintingHitListener implements HitListener {
    /**
     * hitEvent - Blocks that are hit and reach 0 hit-points should be removed from
     * the game. Remember to remove this listener from the block that is being
     * removed from the game.
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}