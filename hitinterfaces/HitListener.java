package hitinterfaces;
import components.Ball;
import components.Block;
/**
 * interface HitListener.
 * @author Michael Ternovsky 316534809
 */
public interface HitListener {
    /**
     * hitEvent - This method is called whenever the beingHit object is hit. The
     * hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}