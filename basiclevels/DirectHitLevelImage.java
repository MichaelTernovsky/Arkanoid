package basiclevels;
import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * Class Blue3LevelImage.
 * @author Michael Ternovsky 316534809
 */
public class DirectHitLevelImage implements Sprite {
    /**
     * drawOn - the functions draws the background of the level.
     * @param d - the sueface.
     */
    public void drawOn(DrawSurface d) {
        // The background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 800);
        // The circles of the "target"
        d.setColor(Color.BLUE);
        d.drawCircle(400, 150, 50);
        d.drawCircle(400, 150, 80);
        d.drawCircle(400, 150, 110);
        // The lines of the "target"
        d.drawLine(240, 150, 570, 150);
        d.drawLine(400, 5, 400, 300);
    }
    /**
     * timePassed - part of the Sprite interface.
     */
    public void timePassed() {
    }
}