//318910890 Ido Haver
package Backgrounds;

import GUI.Sprites.Sprite;
import Levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * the level 1 Background.
 */
public class BackgroundLevel1 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        //drawing the circles of the target
        d.drawCircle(400, 162, 60);
        d.drawCircle(400, 162, 90);
        d.drawCircle(400, 162, 120);
        //drawing the lines of the target
        d.drawLine(400, 182, 400, 302);
        d.drawLine(420, 162, 540, 162);
        d.drawLine(380, 162, 260, 162);
        d.drawLine(400, 152, 400, 22);

    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
