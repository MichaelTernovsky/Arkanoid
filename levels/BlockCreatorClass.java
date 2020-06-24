package levels;
import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;
import components.Block;
import geometry.Point;
import geometry.Rectangle;
/**
 * class BlockCreatorClass.
 * @author Michael Ternovsky 316534809
 */
public class BlockCreatorClass implements BlockCreator {
    private int height;
    private int width;
    private int hitPoints;
    private Color stroke;
    private Map<Integer, Color> colorFill = new HashMap<Integer, Color>();
    private Map<Integer, Image> imageFill = new HashMap<Integer, Image>();
    /**
     * BlockCreatorClass - the constructor of the class.
     * @param height    - the height of the block.
     * @param width     - the width of the block.
     * @param hitPoints - the hit points of the block.
     * @param stroke    - the stroke of the block.
     * @param colorFill - the mapping of the colors to fill.
     * @param imageFill - the mapping of the images to fill.
     */
    public BlockCreatorClass(int height, int width, int hitPoints, Color stroke, Map<Integer, Color> colorFill,
            Map<Integer, Image> imageFill) {
        this.height = height;
        this.width = width;
        this.hitPoints = hitPoints;
        this.stroke = stroke;
        this.colorFill = colorFill;
        this.imageFill = imageFill;
    }
    /**
     * Block create - the function creates new block.
     * @param xpos - the x position of the block.
     * @param ypos - the y position of the block.
     * @return Block - the new block.
     */
    public Block create(int xpos, int ypos) {
        Rectangle rect = new Rectangle(new Point(xpos, ypos), (double) this.width, (double) this.height);
        Block newBlock = new Block(rect, this.hitPoints, this.stroke);
        // set the maps of the blocks
        newBlock.setColorFillMap(this.colorFill);
        newBlock.setImageFillMap(this.imageFill);
        return newBlock;
    }
}