//318910890 Ido Haver
package GUI.Collidable;

import GUI.Shapes.Point;
import biuoop.DrawSurface;
import GUI.Shapes.Line;

import java.util.LinkedList;
import java.util.List;

/**
 * list of collidables.
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidables;

    /**
     * constructor.
     * initialize new list
     */
    public GameEnvironment() {
        this.collidables = new LinkedList<Collidable>();
    }

    /**
     * add collidable to the list.
     *
     * @param c the new collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable from the list,if existed.
     *
     * @param c the given collidable
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * getting a line movement of an object.
     * find the closest collision (to the start of the line) with the collidables in the game and return the
     * information about it
     *
     * @param trajectory the movement method of the object
     * @return RECTANGLE object about the closest collide
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidables1 = new LinkedList<>(this.collidables);
        if (collidables1 == null || collidables1.isEmpty()) {
            return null;
        }
        Collidable closest = null;
        //run all over the list and check the collisions
        for (Collidable c : collidables1) {
            Point collision = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            //collision with this object exist
            if (collision != null) {
                //in case that it's the first collision occurs in the list
                if (closest == null) {
                    closest = c;
                } else {
                    //check if it's closer than the closest that we found until this iteration
                    if (collision.distance(trajectory.start())
                            < trajectory.start().distance(trajectory.closestIntersectionToStartOfLine(
                            closest.getCollisionRectangle()))) {
                        closest = c;
                    }

                }
            }
        }
        if (closest != null) {
            CollisionInfo info = new CollisionInfo(closest, trajectory.
                    closestIntersectionToStartOfLine(closest.getCollisionRectangle()));
            return info;
        }
        return null;
    }

    /**
     * draw all the colidables.
     *
     * @param surface the given surface
     */
    public void drawOn(DrawSurface surface) {
        List<Collidable> collidables1 = new LinkedList<>(this.collidables);
        for (Collidable c : collidables1) {
            c.drawOn(surface);
        }
    }

}
