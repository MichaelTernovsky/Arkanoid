package basiclevels;
import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * Class FinalFourLevelImage.
 * @author Michael Ternovsky 316534809
 */
public class FinalFourLevelImage implements Sprite {
    /**
     * drawOn - the functions draws the background of the level.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        // The background
        d.setColor(new Color(51, 153, 255));
        d.fillRectangle(0, 0, 800, 800);
        // The left rain
        int startX = 100, startY = 350, endX = 40, endY = 600;
        for (int i = 0; i < 10; i++) {
            d.setColor(Color.WHITE.brighter());
            d.drawLine(startX, startY, endX, endY);
            startX += 8;
            endX += 8;
        }
        // The right rain
        startX = 620;
        startY = 400;
        endX = 580;
        endY = 600;
        for (int i = 0; i < 10; i++) {
            d.setColor(Color.WHITE.brighter());
            d.drawLine(startX, startY, endX, endY);
            startX += 8;
            endX += 8;
        }
        // The left could
        d.setColor(Color.GRAY.brighter());
        d.drawCircle(105, 330, 23);
        d.fillCircle(105, 330, 23);
        d.setColor(Color.GRAY);
        d.drawCircle(135, 330, 24);
        d.fillCircle(135, 330, 24);
        d.setColor(Color.GRAY.brighter());
        d.drawCircle(168, 330, 22);
        d.fillCircle(168, 330, 22);
        d.setColor(Color.GRAY);
        d.drawCircle(150, 350, 22);
        d.fillCircle(150, 350, 22);
        d.setColor(Color.GRAY.brighter());
        d.drawCircle(120, 355, 21);
        d.fillCircle(120, 355, 21);
        // The right could
        int x = 520;
        int y = 60;
        d.setColor(Color.GRAY.brighter());
        d.drawCircle(105 + x, 330 + y, 23);
        d.fillCircle(105 + x, 330 + y, 23);
        d.setColor(Color.GRAY);
        d.drawCircle(135 + x, 330 + y, 24);
        d.fillCircle(135 + x, 330 + y, 24);
        d.setColor(Color.GRAY.brighter());
        d.drawCircle(168 + x, 330 + y, 22);
        d.fillCircle(168 + x, 330 + y, 22);
        d.setColor(Color.GRAY);
        d.drawCircle(150 + x, 350 + y, 22);
        d.fillCircle(150 + x, 350 + y, 22);
        d.setColor(Color.GRAY.brighter());
        d.drawCircle(120 + x, 355 + y, 21);
        d.fillCircle(120 + x, 355 + y, 21);
    }
    /**
     * timePassed - part of the Sprite interface.
     */
    public void timePassed() {
    }
}