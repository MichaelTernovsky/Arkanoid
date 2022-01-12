package basiclevels;
import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * Class WideEasyLevelImage.
 * @author Michael Ternovsky
 */
public class WideEasyLevelImage implements Sprite {
    /**
     * drawOn - the functions draws the background of the level.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        // The background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 800);
        // The circles
        d.setColor(Color.YELLOW);
        d.drawCircle(100, 120, 45);
        d.fillCircle(100, 120, 45);
        d.setColor(Color.red);
        d.drawCircle(100, 120, 50);
        d.setColor(Color.YELLOW);
        d.drawCircle(100, 120, 55);
        int startX = 70, startY = 100, endX = 110, endY = 400;
        for (int i = 0; i < 30; i++) {
            d.setColor(Color.YELLOW.brighter());
            d.drawLine(startX, startY, endX, endY);
            endX += 25;
            endY += 5;
            startX += 0.5;
            startY += 0.5;
        }
    }
    /**
     * timePassed - part of the Sprite interface.
     */
    public void timePassed() {
    }
}
