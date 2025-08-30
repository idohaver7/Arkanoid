//318910890 Ido Haver
package Listeners;
import GUI.Collidable.Block;
import GUI.Sprites.Ball;

/**
 * the basis methods for all the listeners.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the object that going to be hit
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}

