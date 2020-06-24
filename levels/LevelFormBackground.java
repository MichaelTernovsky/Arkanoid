package levels;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import biuoop.DrawSurface;
import interfaces.Sprite;
/**
 * Class LevelFormBackground.
 * @author Michael Ternovsky 316534809
 */
public class LevelFormBackground implements Sprite {
    private String background;
    private Image img;
    private Color color;
    /**
     * LevelFormBackground - the constructor of the class.
     * @param background - the background.
     */
    public LevelFormBackground(String background) {
        this.img = null;
        this.color = null;
        this.background = background;
        String inputBackground = background.substring(6, background.length() - 1);
        if (background.startsWith("image")) {
            ClassLoader c = ClassLoader.getSystemClassLoader();
            InputStream is = c.getResourceAsStream(inputBackground);
            try {
                this.img = ImageIO.read(is);
                // this.img = ImageIO.read(new FileInputStream("resources/" + inputBackground));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (background.startsWith("color")) {
            ColorsParser colorDef = new ColorsParser(inputBackground);
            this.color = colorDef.fromColorToString();
        }
    }
    /**
     * drawOn - the functions draws the background of the level.
     * @param d - the surface.
     */
    public void drawOn(DrawSurface d) {
        if (this.background.startsWith("image")) {
            d.drawImage(10, 20, this.img); // draw the image at location 10, 20.
        } else if (this.background.startsWith("color")) {
            d.setColor(this.color);
            d.fillRectangle(0, 0, 800, 800);
        }
    }
    /**
     * timePassed - part of the Sprite interface.
     */
    public void timePassed() {
    }
}