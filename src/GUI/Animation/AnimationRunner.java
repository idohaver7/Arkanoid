//318910890 Ido Haver
package GUI.Animation;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * runs all the animations.
 */
public class AnimationRunner {
    static final int MILLISECONDS_PER_FRAME = 1000;
    static final int FRAME_PER_SECONDS = 60;
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;

    /**
     * constructor.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.framesPerSecond = FRAME_PER_SECONDS;
        this.sleeper = new Sleeper();

    }

    /**
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * run the animation.
     * @param animation the given animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = MILLISECONDS_PER_FRAME / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}