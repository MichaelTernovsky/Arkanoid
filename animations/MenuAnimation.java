package animations;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 * MenuAnimation<T> class.
 * @author Michael Ternovsky
 * @param <T> - parameter.
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<String> keys;
    private List<String> messages;
    private List<T> returnVals;
    private biuoop.KeyboardSensor keyboard;
    /**
     * MenuAnimation<T> - the constructor of the class.
     * @param keyboard - the keyboard.
     */
    public MenuAnimation(biuoop.KeyboardSensor keyboard) {
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.returnVals = new ArrayList<T>();
        this.keyboard = keyboard;
    }
    /**
     * addSelection - add new selection to the menu.
     * @param key       - the key of the selection.
     * @param message   - the message of the selection.
     * @param returnVal - the returning value of the selection.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.returnVals.add(returnVal);
    }
    /**
     * doOneFrame - the loop that draws the menu.
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 800);
        d.setColor(Color.white);
        d.drawText(295, 120, "Arkanoid", 50);
        int y = 0;
        for (int i = 0; i < this.keys.size(); i++) {
            d.drawText(285, 265 + y, this.keys.get(i), 35);
            d.drawText(335, 265 + y, this.messages.get(i), 32);
            y += 50;
        }
    }
    /**
     * shouldStop - true if the animation should stop, false otherwise.
     * @return boolean - true if the animation should stop, false otherwise.
     */
    public boolean shouldStop() {
        // if any key was pressed - stop the animation
        return (this.getStatus() != null);
    }
    /**
     * getStatus - return the status of the selection.
     * @return T - the status of the selection.
     */
    public T getStatus() {
        // run over the keys list
        for (int i = 0; i < this.keys.size(); i++) {
            // if the key is pressed, return his function
            if (this.keyboard.isPressed(this.keys.get(i))) {
                return this.returnVals.get(i);
            }
        }
        return null;
    }
    /**
     * addSubMenu - the function adds new sub menu to the prime menu.
     * @param key     - the key.
     * @param message - the message.
     * @param subMenu - sub menu.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
    }
}
