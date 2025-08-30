//318910890 Ido Haver
package Backgrounds;

import GUI.Sprites.Sprite;
import Levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Background of level 2.
 */
public class BackgroundLevel2 implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        //drawing the screen color
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //drawing the sun
        d.setColor(Color.decode("#efe7b0"));
        d.fillCircle(200, 80, 60);
        //drawing the lines of the sun
        for (int i = 0; i < 800; i = i + 10) {
            d.drawLine(200, 100, i, 200);
        }
        d.setColor(Color.decode("#ecd749"));
        d.fillCircle(200, 80, 50);
        d.setColor(Color.decode("#ffe118"));
        d.fillCircle(200, 80, 40);

    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


}
