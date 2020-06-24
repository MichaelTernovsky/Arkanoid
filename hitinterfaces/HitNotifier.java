package hitinterfaces;
/**
 * interface HitNotifier.
 * @author Michael Ternovsky 316534809
 */
public interface HitNotifier {
    /**
     * addHitListener - Add hl as a listener to hit events.
     * @param hl - the listener we want to add.
     */
    void addHitListener(HitListener hl);
    /**
     * removeHitListener - Remove hl from the list of listeners to hit events.
     * @param hl - the listener we want to remove.
     */
    void removeHitListener(HitListener hl);
}