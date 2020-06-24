package levels;
import java.awt.Color;
/**
 * class ColorsParser.
 * @author Michael Ternovsky 316534809
 */
public class ColorsParser {
    private String color;
    /**
     * ColorsParser - the constructor.
     * @param color - the color.
     */
    public ColorsParser(String color) {
        this.color = color;
    }
    /**
     * fromColorToString - the function takes color string and returns color object.
     * @return Color - the color object.
     */
    public Color fromColorToString() {
        switch (this.color) {
        case "BLACK":
            return Color.black;
        case "black":
            return Color.black;
        case "BLUE":
            return Color.blue;
        case "blue":
            return Color.blue;
        case "CYAN":
            return Color.cyan;
        case "cyan":
            return Color.cyan;
        case "GRAY":
            return Color.gray;
        case "gray":
            return Color.gray;
        case "LIGHTGRAY":
            return Color.lightGray;
        case "lightGray":
            return Color.lightGray;
        case "GREEN":
            return Color.green;
        case "green":
            return Color.green;
        case "ORANGE":
            return Color.orange;
        case "orange":
            return Color.orange;
        case "PINK":
            return Color.pink;
        case "pink":
            return Color.pink;
        case "RED":
            return Color.red;
        case "red":
            return Color.red;
        case "WHITE":
            return Color.white;
        case "white":
            return Color.white;
        case "YELLOW":
            return Color.yellow;
        case "yellow":
            return Color.yellow;
        default:
            // it is RGB
            String[] rgb = color.substring(4, color.length() - 1).split(",");
            int colo1 = Integer.parseInt(rgb[0]);
            int colo2 = Integer.parseInt(rgb[1]);
            int colo3 = Integer.parseInt(rgb[2]);
            return new Color(colo1, colo2, colo3);
        }
    }
}
