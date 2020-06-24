package components;
/**
 * Class Counter.
 * @author Michael Ternovsky 316534809
 */
public class Counter {
    private int counter;
    /**
     * Counter - the constructor of the class.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * Counter - the constructor of the class.
     * @param num - the number to initialize the counter.
     */
    public Counter(int num) {
        this.counter = num;
    }
    /**
     * increase - add number to current count.
     * @param number - the number we want to add.
     */
    public void increase(int number) {
        this.counter += number;
    }
    /**
     * increase - subtract number from current count.
     * @param number - the number we want to sub.
     */
    public void decrease(int number) {
        this.counter -= number;
    }
    /**
     * getValue - get current count.
     * @return int - the value of the counter.
     */
    public int getValue() {
        return this.counter;
    }
}