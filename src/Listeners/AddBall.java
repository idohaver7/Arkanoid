//318910890 Ido Haver
package Listeners;

import GUI.Collidable.Block;
import Levels.GameLevel;
import GUI.Sprites.Ball;

/**
 * in charge of know when to add a ball to the game.
 */
public class AddBall implements HitListener {
    private GameLevel gameLevel;

    /**
     * constructor.
     *
     * @param gameLevel the given game
     */
    public AddBall(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        gameLevel.addBall();
    }
}
