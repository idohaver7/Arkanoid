//318910890 Ido Haver
package GUI.Collidable;

import Listeners.HitListener;
import Listeners.HitNotifier;
import GUI.Shapes.Point;
import GUI.Shapes.Rectangle;
import GUI.Sprites.Ball;
import GUI.Sprites.Sprite;
import GUI.Sprites.Velocity;
import biuoop.DrawSurface;
import Levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * present a block object in the surface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param rectangle the shape of the block
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<>();
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        if (rectangle.upper().isExist(collisionPoint) || rectangle.downer().isExist(collisionPoint)) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        if (rectangle.left().isExist(collisionPoint) || rectangle.right().isExist(collisionPoint)) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;

    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.rectangle.getColor());
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove this block from the given game.
     *
     * @param gameLevel the given game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
