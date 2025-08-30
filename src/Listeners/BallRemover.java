//318910890 Ido Haver
package Listeners;

import GUI.Collidable.Block;
import Levels.GameLevel;
import GUI.Sprites.Ball;

/**
 * a BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    static final int ONE_BALL_REMOVAL = 1;
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param gameLevel           the given game
     * @param remainingBalls the given counter
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        remainingBalls.decrease(ONE_BALL_REMOVAL);
    }
}

