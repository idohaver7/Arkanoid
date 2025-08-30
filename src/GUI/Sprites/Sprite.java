//318910890 Ido Haver
package GUI.Sprites;
import Levels.GameLevel;
import biuoop.DrawSurface;

/**
 * interface that represent the sprite in the game.
 */
public interface Sprite {
    /**
     * draw the sprite in the surface.
     * @param d the surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite the time is passed.
     */
    void timePassed();

    /**
     * add the sprite to the game.
     * @param g the game reference
     */
    void addToGame(GameLevel g);
}