package levels;
import components.Block;
/**
 * interface BlockCreator.
 * @author Michael Ternovsky 316534809
 */
public interface BlockCreator {
    /**
     * create - create a block at the specified location.
     * @param xpos - the x position.
     * @param ypos - the y position.
     * @return Block - returns the new block.
     */
    Block create(int xpos, int ypos);
}