package tasks;
/**
 * Class Task<T>.
 * @author Michael Ternovsky 316534809
 * @param <T> - the parameter.
 */
public interface Task<T> {
    /**
     * run - run.
     * @return T - the parameter.
     */
    T run();
}