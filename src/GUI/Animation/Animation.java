//318910890 Ido Haver
package GUI.Animation;

import biuoop.DrawSurface;

/**
 * interface that will be in charge of the actions in the animation.
 */
public interface Animation {
    /**
     * all the logic in one frame.
     * @param d the surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * control the stopping conditions.
     * @return true if the animation should stop
     */
    boolean shouldStop();
}