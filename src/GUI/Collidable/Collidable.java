//318910890 Ido Haver
package GUI.Collidable;

import GUI.Shapes.Rectangle;
import GUI.Sprites.Ball;
import GUI.Sprites.Velocity;
import GUI.Shapes.Point;
import biuoop.DrawSurface;

/**
 * interface that represent all the collidables object in the game.
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  the collision point with the object
     * @param currentVelocity current velocity of the object
     * @param hitter          the hitting ball
     * @return new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * draw the collidable on the surface.
     *
     * @param d the given surface
     */
    void drawOn(DrawSurface d);
}
