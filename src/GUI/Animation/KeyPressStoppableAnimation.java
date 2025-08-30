//318910890 Ido Haver
package GUI.Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * in charge of all the waiting key press animations.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * constructor.
     *
     * @param sensor    the keyboard sensor
     * @param key       the key that we wait for
     * @param animation the given animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!sensor.isPressed(key) && isAlreadyPressed) {
            isAlreadyPressed = false;
        }
        animation.doOneFrame(d);
        if (sensor.isPressed(key) && !isAlreadyPressed) {
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}

