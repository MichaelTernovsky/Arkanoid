package basiclevels;
import java.awt.Color;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * Class Blue3LevelImage.
 * @author Michael Ternovsky
 */
public class Green3LevelImage implements Sprite {
    /**
     * drawOn - the functions draws the background of the level.
     * @param d - the sueface.
     */
    public void drawOn(DrawSurface d) {
        // The background
        d.setColor(new Color(102, 255, 102));
        d.fillRectangle(0, 0, 800, 800);
        // The circles
        d.setColor(Color.orange);
        d.drawCircle(120, 153, 12);
        d.fillCircle(120, 153, 12);
        d.setColor(Color.white);
        d.drawCircle(120, 153, 4);
        d.fillCircle(120, 153, 4);
        d.setColor(Color.YELLOW);
        d.drawCircle(120, 153, 15);
        d.setColor(Color.RED);
        d.drawCircle(120, 153, 17);
        // The shape
        d.setColor(Color.DARK_GRAY);
        d.drawRectangle(60, 380, 125, 220);
        d.fillRectangle(60, 380, 125, 220);
        d.setColor(new Color(204, 204, 204));
        d.drawRectangle(70, 390, 105, 200);
        d.fillRectangle(70, 390, 105, 200);
        d.setColor(Color.DARK_GRAY);
        d.drawRectangle(105, 330, 30, 50);
        d.fillRectangle(105, 330, 30, 50);
        d.drawRectangle(115, 170, 10, 160);
        d.fillRectangle(115, 170, 10, 160);
        int startX = 70;
        int startY = 425;
        int endX = 185;
        int endY = 425;
        for (int i = 0; i <= 4; i++) {
            d.drawLine(startX, startY, endX, endY);
            startY += 35;
            endY += 35;
        }
        startX = 70;
        startY = 380;
        endX = 70;
        endY = 600;
        for (int i = 0; i < 4; i++) {
            d.drawLine(startX, startY, endX, endY);
            startX += 35;
            endX += 35;
        }
    }
    /**
     * timePassed - part of the Sprite interface.
     */
    public void timePassed() {
    }
}
