package tasks;
import animations.AnimationRunner;
import animations.Menu;
/**
 * Class SubMenuTask<T>.
 * @author Michael Ternovsky 316534809
 * @param <T> - the parameter.
 */
public class SubMenuTask<T> implements Task<Void> {
    private AnimationRunner runner;
    private Menu<T> menu;
    /**
     * SubMenuTask - the constructor of the class.
     * @param runner - the animation runner.
     * @param menu   - the menu.
     */
    public SubMenuTask(AnimationRunner runner, Menu<T> menu) {
        this.runner = runner;
        this.menu = menu;
    }
    /**
     * run - run the sub menu.
     * @return Void - void.
     */
    public Void run() {
        this.runner.run(this.menu);
        @SuppressWarnings("unchecked")
        Task<T> task = (Task<T>) this.menu.getStatus();
        task.run();
        return null;
    }
}