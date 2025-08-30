//318910890 Ido Haver
package Listeners;

/**
 * object that use to count things.
 */
public class Counter {
    static final int INITIALIZE = 0;
    private int counter;

    /**
     * constructor.
     */
    public Counter() {
        this.counter = INITIALIZE;
    }

    /**
     * add number to current count.
     *
     * @param number the adding number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the decrease number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get the current value.
     *
     * @return the counter value
     */
    public int getValue() {
        return counter;
    }
}