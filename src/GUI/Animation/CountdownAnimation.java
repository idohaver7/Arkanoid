//318910890 Ido Haver
package GUI.Animation;

import GUI.Sprites.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * create countdown before each level.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int counter;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Sleeper sleeper;

    /**
     * constructor.
     *
     * @param numOfSeconds the num of seconds that the animation will run
     * @param countFrom    count down from this number
     * @param gameScreen   the screen look
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.counter = countFrom;
        this.sleeper = new Sleeper();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (counter == 0) {
            this.stop = true;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(350, 100, "" + counter, 50);
        sleeper.sleepFor((long) (numOfSeconds * 1000) / countFrom);
        counter--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}