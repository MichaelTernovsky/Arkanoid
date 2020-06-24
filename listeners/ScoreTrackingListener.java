package listeners;
import components.Ball;
import components.Block;
import components.Counter;
import hitinterfaces.HitListener;
/**
 * Class ScoreTrackingListener.
 * @author Michael Ternovsky 316534809
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * ScoreTrackingListener - the function initializing the score counter.
     * @param scoreCounter - the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * hitEvent - the function removes balls that hit the "death region".
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // if we are going to destroy the block
        if (beingHit.getHitPoints() == 1) {
            this.currentScore.increase(10);
        }
        this.currentScore.increase(5);
    }
}