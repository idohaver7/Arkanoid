//318910890 Ido Haver
package Listeners;

import GUI.Collidable.Block;
import Levels.GameLevel;
import GUI.Sprites.Ball;

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    static final int ONE_BLOCK_REMOVAL = 1;
    private GameLevel gameLevel;
    private Counter remainingBlocks;


    /**
     * constructor.
     *
     * @param gameLevel          the given game
     * @param removedBlocks the given counter
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        remainingBlocks.decrease(ONE_BLOCK_REMOVAL);
        beingHit.removeHitListener(this);
    }
}