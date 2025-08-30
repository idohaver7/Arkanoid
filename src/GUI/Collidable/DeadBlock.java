//318910890 Ido Haver
package GUI.Collidable;

import GUI.Shapes.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * represent the dead block.
 */
public class DeadBlock extends Block {
    /**
     * constructor.
     *
     * @param rectangle the shape of the block
     */
    public DeadBlock(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getCollisionRectangle().getColor());
        surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                (int) this.getCollisionRectangle().getUpperLeft().getY(),
                (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        surface.setColor(Color.RED);
        surface.drawText((int) this.getCollisionRectangle().getUpperLeft().getX() + 4,
                (int) this.getCollisionRectangle().getUpperLeft().getY() + 12, "deadblock", 10);
    }
}
