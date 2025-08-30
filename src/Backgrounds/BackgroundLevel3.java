//318910890 Ido Haver
package Backgrounds;

import GUI.Sprites.Sprite;
import Levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Background level 3.
 */
public class BackgroundLevel3 implements Sprite {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.green.darker().darker());
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        //drawing the antenna
        d.setColor(Color.gray);
        d.fillRectangle(110, 200, 10, 200);
        d.setColor(Color.darkGray);
        d.fillRectangle(95, 400, 40, 50);
        //drawing the building
        d.setColor(Color.black);
        d.fillRectangle(55, 440, 120, 200);
        //drawing the antenna light
        d.setColor(Color.decode("#d8ac66"));
        d.fillCircle(115, 200, 15);
        d.setColor(Color.red);
        d.fillCircle(115, 200, 10);
        //drawing the building's windows
        d.setColor(Color.WHITE);
        d.fillCircle(115, 200, 4);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(65 + (20 * j), 450 + (30 * i), 15, 20);
            }
        }
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
