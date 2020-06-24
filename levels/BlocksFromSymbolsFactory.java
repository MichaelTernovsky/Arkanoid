package levels;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import components.Block;
/**
 * class BlocksFromSymbolsFactory.
 * @author Michael Ternovsky 316534809
 */
public class BlocksFromSymbolsFactory {
    private List<String> blockDefinitions;
    private Map<String, BlockCreator> blockCreators;
    private Map<String, Integer> spacerWidths;
    private Map<Integer, Color> colorFill = new HashMap<Integer, Color>();
    private Map<Integer, Color> defultColorFill = colorFill;
    private Map<Integer, Image> imageFill = new HashMap<Integer, Image>();
    private Map<Integer, Image> defultImageFill = imageFill;
    /**
     * BlocksFromSymbolsFactory - the constructor of the class.
     * @param blockDefinitions - the block definitions url.
     */
    public BlocksFromSymbolsFactory(List<String> blockDefinitions) {
        this.blockDefinitions = blockDefinitions;
        this.blockCreators = new HashMap<String, BlockCreator>();
        this.spacerWidths = new HashMap<String, Integer>();
        this.handleList();
    }
    /**
     * fillDefault - part of handleList.
     * @param part2 - the second part of the string.
     */
    private void fillDefault(String part2) {
        if (part2.startsWith("color")) {
            ColorsParser color = new ColorsParser(part2.substring(6, part2.length() - 1));
            Color defualtFillColor = color.fromColorToString();
            colorFill.put(-1, defualtFillColor);
        } else if (part2.startsWith("image")) {
            Image img = null;
            try {
                String imageURL = part2.substring(6, part2.length() - 1);
                ClassLoader c = ClassLoader.getSystemClassLoader();
                InputStream is = c.getResourceAsStream(imageURL);
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println("can't load the image");
            }
            Image defualtFillImage = img;
            imageFill.put(-1, defualtFillImage);
        }
    }
    /**
     * fillKdefualt - part of handleList.
     * @param part1 - the first part of the string.
     * @param part2 - the second part of the string.
     */
    private void fillKdefualt(String part1, String part2) {
        if (part1.startsWith("fill") && !part1.equals("fill")) {
            String[] splittesFill = part1.split("-"); // finding k
            int k = Integer.parseInt(splittesFill[1]);
            if (part2.startsWith("color")) {
                ColorsParser color = new ColorsParser(part2.substring(6, part2.length() - 1));
                Color fillColor = color.fromColorToString();
                colorFill.put(k, fillColor);
            } else if (part2.startsWith("image")) {
                Image img = null;
                try {
                    String imageURL = part2.substring(6, part2.length() - 1);
                    ClassLoader c = ClassLoader.getSystemClassLoader();
                    InputStream is = c.getResourceAsStream(imageURL);
                    img = ImageIO.read(is);
                } catch (IOException e) {
                    System.out.println("can't load the image");
                }
                Image fillImage = img;
                imageFill.put(k, fillImage);
            }
        }
    }
    /**
     * bdefFill - part of handleList.
     * @param part2 - the string.
     */
    private void bdefFill(String part2) {
        if (part2.startsWith("color")) {
            ColorsParser color = new ColorsParser(part2.substring(6, part2.length() - 1));
            Color fillColor = color.fromColorToString();
            colorFill.put(-1, fillColor);
        } else if (part2.startsWith("image")) {
            Image img = null;
            try {
                String imageURL = part2.substring(6, part2.length() - 1);
                ClassLoader c = ClassLoader.getSystemClassLoader();
                InputStream is = c.getResourceAsStream(imageURL);
                img = ImageIO.read(is);
            } catch (IOException e) {
                System.out.println("can't load the image");
            }
            Image fillImage = img;
            imageFill.put(-1, fillImage);
        }
    }
    /**
     * handleList - the function runs over the list and part it into 3 different
     * parts.
     */
    private void handleList() {
        int k = 0, height = 0, defualtHeight = 0, width = 0, defualtWidth = 0, hitPoints = 0, defualtHitPoints = 0;
        Color strokeColor = null, defualtStrokeColor = null, fillColor = null;
        Image fillImage = null;
        String subStr, symbol = "";
        String[] strParts;
        for (String str : this.blockDefinitions) {
            if (str.startsWith("default")) {
                subStr = str.substring(8, str.length());
                strParts = subStr.split(" ");
                for (int i = 0; i < strParts.length; i++) {
                    String part1 = strParts[i].split(":")[0], part2 = strParts[i].split(":")[1];
                    switch (part1) {
                    case "height":
                        defualtHeight = Integer.parseInt(part2);
                        break;
                    case "width":
                        defualtWidth = Integer.parseInt(part2);
                        break;
                    case "stroke":
                        if (part2.startsWith("color")) {
                            ColorsParser color = new ColorsParser(part2.substring(6, part2.length() - 1));
                            defualtStrokeColor = color.fromColorToString();
                        }
                        break;
                    case "hit_points":
                        defualtHitPoints = Integer.parseInt(part2);
                        break;
                    case "fill":
                        fillDefault(part2);
                        break;
                    default:
                        fillKdefualt(part1, part2);
                        break;
                    }
                }
                defultColorFill = colorFill;
                defultImageFill = imageFill;
            }
            if (str.startsWith("bdef")) {
                subStr = str.substring(5, str.length());
                strParts = subStr.split(" ");
                for (int i = 0; i < strParts.length; i++) {
                    String part1 = strParts[i].split(":")[0], part2 = strParts[i].split(":")[1];
                    switch (part1) {
                    case "symbol":
                        symbol = part2;
                        break;
                    case "height":
                        height = Integer.parseInt(part2);
                        break;
                    case "width":
                        width = Integer.parseInt(part2);
                        break;
                    case "stroke":
                        if (part2.startsWith("color")) {
                            ColorsParser color = new ColorsParser(part2.substring(6, part2.length() - 1));
                            strokeColor = color.fromColorToString();
                        }
                        break;
                    case "hit_points":
                        hitPoints = Integer.parseInt(part2);
                        break;
                    case "fill":
                        bdefFill(part2);
                        break;
                    default:
                        if (part1.startsWith("fill") && !part1.equals("fill")) {
                            String[] splittesFill = part1.split("-"); // finding k
                            k = Integer.parseInt(splittesFill[1]);
                            if (part2.startsWith("color")) {
                                ColorsParser color = new ColorsParser(part2.substring(6, part2.length() - 1));
                                fillColor = color.fromColorToString();
                                if (colorFill.containsKey(k)) {
                                    colorFill.remove(k);
                                    colorFill.put(k, fillColor);
                                } else {
                                    colorFill.put(k, fillColor);
                                }
                            } else if (part2.startsWith("image")) {
                                Image img = null;
                                try {
                                    String imageURL = part2.substring(6, part2.length() - 1);
                                    ClassLoader c = ClassLoader.getSystemClassLoader();
                                    InputStream is = c.getResourceAsStream(imageURL);
                                    img = ImageIO.read(is);
                                } catch (IOException e) {
                                    System.out.println("can't load the image");
                                }
                                fillImage = img;
                                if (imageFill.containsKey(k)) {
                                    imageFill.remove(k);
                                    imageFill.put(k, fillImage);
                                } else {
                                    imageFill.put(k, fillImage);
                                }
                            }
                        }
                        break;
                    }
                }
                if (height == 0) {
                    height = defualtHeight;
                }
                if (width == 0) {
                    width = defualtWidth;
                }
                if (strokeColor == null) {
                    strokeColor = defualtStrokeColor;
                }
                if (hitPoints == 0) {
                    hitPoints = defualtHitPoints;
                }
                if (colorFill.isEmpty()) {
                    colorFill = defultColorFill;
                }
                if (imageFill.isEmpty()) {
                    imageFill = defultImageFill;
                }
                BlockCreatorClass newBlock = new BlockCreatorClass(height, width, hitPoints, strokeColor, colorFill,
                        imageFill);
                this.blockCreators.put(symbol, newBlock);
                height = 0;
                width = 0;
                hitPoints = 0;
                strokeColor = null;
                colorFill = new HashMap<Integer, Color>();
                imageFill = new HashMap<Integer, Image>();
            }
            if (str.startsWith("sdef")) {
                subStr = str.substring(12, str.length());
                strParts = subStr.split(" ");
                String mapStr = strParts[0];
                String mapInt = strParts[1].split(":")[1];
                this.spacerWidths.put(mapStr, Integer.parseInt(mapInt));
            }
        }
    }
    /**
     * isBlockSymbol - returns true if 's' is a valid space symbol.
     * @param s - the String.
     * @return boolean - true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) {
        return (this.spacerWidths.containsKey(s));
    }
    /**
     * isBlockSymbol - returns true if 's' is a valid block symbol.
     * @param s - the String.
     * @return boolean - true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return (this.blockCreators.containsKey(s));
    }
    /**
     * getBlock - Return a block according to the definitions associated with symbol
     * s. The block will be located at position (xpos, ypos).
     * @param s - the symbol.
     * @param x - x position.
     * @param y - y position.
     * @return Block - the block.
     */
    public Block getBlock(String s, int x, int y) {
        if (this.isBlockSymbol(s)) {
            return this.blockCreators.get(s).create(x, y);
        }
        return null;
    }
    /**
     * getSpaceWidth - Returns the width in pixels associated with the given
     * spacer-symbol.
     * @param s - the spacer.
     * @return int - the width of the spacer.
     */
    public int getSpaceWidth(String s) {
        if (this.isSpaceSymbol(s)) {
            return this.spacerWidths.get(s);
        }
        return -1;
    }
}