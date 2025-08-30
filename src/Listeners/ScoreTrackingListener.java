//318910890 Ido Haver
package Listeners;

import GUI.Collidable.Block;
import GUI.Sprites.Ball;

/**
 * in charge of the score counting.
 */
public class ScoreTrackingListener implements HitListener {
    static final int BLOCK_REMOVAL_SCORE = 5;
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter the given counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(BLOCK_REMOVAL_SCORE);
        beingHit.removeHitListener(this);
    }
}