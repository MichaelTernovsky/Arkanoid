package animations;
/**
 * interface Menu.
 * @author Michael Ternovsky 316534809
 * @param <T> - parameter.
 */
public interface Menu<T> extends Animation {
    /**
     * addSelection - add a selection to the menu.
     * @param key       - the key of the selection.
     * @param message   - the message of the selection.
     * @param returnVal - the returning value of the selection.
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * getStatus - returns the status of the menu.
     * @return T - the generic status.
     */
    T getStatus();
    /**
     * addSubMenu - adding a sub menu to the menu.
     * @param key     - the key of the selection.
     * @param message - the message of the selection.
     * @param subMenu - the sub menu we want to add.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
