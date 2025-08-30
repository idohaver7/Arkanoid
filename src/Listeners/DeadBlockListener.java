//318910890 Ido Haver
package Listeners;

import GUI.Collidable.Block;
import Levels.GameLevel;
import GUI.Sprites.Ball;

/**
 * in charge of the listener to the dead block.
 */
public class DeadBlockListener implements HitListener {
    static final int HIT_DEAD_BALL = 1;
    private Counter deadBlock;
    private GameLevel gameLevel;

    /**
     * constructor.
     * @param gameLevel the given game
     * @param deadBlock flag of hitting the dead block
     */
    public DeadBlockListener(GameLevel gameLevel, Counter deadBlock) {
        this.deadBlock = deadBlock;
        this.gameLevel = gameLevel;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.deadBlock.decrease(HIT_DEAD_BALL);
    }
}
