//318910890 Ido Haver
package GUI.Sprites;

import java.util.LinkedList;
import java.util.List;

import biuoop.DrawSurface;


/**
 * hold sprites collection.
 */
public class SpriteCollection {
    private java.util.List<Sprite> spriteList;

    /**
     * constructor.
     * create new list
     */
    public SpriteCollection() {
        this.spriteList = new LinkedList<>();
    }

    /**
     * add new sprite.
     *
     * @param s the given sprite
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * remove the given sprite,if existed.
     *
     * @param s the given sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteList1 = new LinkedList<>(this.spriteList);
        for (Sprite s : spriteList1) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the surface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spriteList1 = new LinkedList<>(this.spriteList);
        for (Sprite s : spriteList1) {
            s.drawOn(d);
        }
    }
}