//318910890 Ido Haver
package Levels;

import GUI.Collidable.Block;
import GUI.Sprites.Sprite;
import GUI.Sprites.Velocity;

import java.util.List;

/**
 * represent all the information of each level.
 */
public interface LevelInformation {
    /**
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return the list with all the velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * @return the level name.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return list of the blocks in the specific level.
     */
    List<Block> blocks();

    /**
     * @return the number of blocks that need to remove.
     */
    int numberOfBlocksToRemove();
}