package animations;
import biuoop.DrawSurface;
/**
 * interface Animation.
 * @author Michael Ternovsky
 */
public interface Animation {
    /**
     * doOneFrame - the function runs one frame of the animation.
     * @param d - the surface.
     */
    void doOneFrame(DrawSurface d);
    /**
     * shouldStop - when the animation should stop.
     * @return boolean - true if the animation should stop and else otherwise.
     */
    boolean shouldStop();
}
