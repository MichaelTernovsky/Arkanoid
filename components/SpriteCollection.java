package components;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * Class SpriteCollection - the collection of sprite objects.
 * @author Michael Ternovsky 316534809
 */
public class SpriteCollection {
    // Fields
    private List<Sprite> spriteCollection;
    /**
     * SpriteCollection - the constructor of the class.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<Sprite>();
    }
    /**
     * addSprite - the function adds a new sprite object.
     * @param s - the sprite obejct.
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }
    /**
     * notifyAllTimePassed - the function calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Creating backup of the sprite collection
        List<Sprite> sBackup = new ArrayList<Sprite>(this.spriteCollection);
        for (Sprite s : sBackup) {
            s.timePassed();
        }
    }
    /**
     * drawAllOn - the function calls drawOn(d) on all sprites.
     * @param d - the surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.spriteCollection) {
            s.drawOn(d);
        }
    }
    /**
     * removeSprite - the function removes the given sprite object.
     * @param s - the sprite obejct.
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }
}