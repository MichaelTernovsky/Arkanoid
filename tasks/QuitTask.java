package tasks;
/**
 * Class QuitTask.
 * @author Michael Ternovsky 316534809
 */
public class QuitTask implements Task<Void> {
    /**
     * quitTask - the constructor of the class.
     */
    public QuitTask() {
    }
    /**
     * run - the function ends the game.
     * @return Void - return null by default.
     */
    public Void run() {
        System.exit(1);
        return null;
    }
}