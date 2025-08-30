//318910890 Ido Haver
package GUI.Collidable;

import GUI.Shapes.Point;

/**
 * save the information about the collision.
 */
public class CollisionInfo {
    private Collidable collidable;
    private Point collision;

    /**
     * constructor.
     *
     * @param collidable the collidable
     * @param collision  the collision point
     */
    public CollisionInfo(Collidable collidable, Point collision) {
        this.collidable = collidable;
        this.collision = collision;
    }

    /**
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * @return the object
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}