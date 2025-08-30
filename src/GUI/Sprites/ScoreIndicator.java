//318910890 Ido Haver
package GUI.Sprites;

import Levels.GameLevel;
import GUI.Shapes.Point;
import GUI.Shapes.Rectangle;
import Listeners.Counter;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * in charge to display and update the score of the game.
 */
public class ScoreIndicator implements Sprite {
    static final Point LEFT_POINT = new Point(0, 0);
    static final int WIDTH = 800;

    static final int SIZE = 30;
    private Counter score;
    private Rectangle rectangle;
    private String levelName;

    /**
     * constructor.
     * @param score the score counter
     * @param levelName the level name
     */
    public ScoreIndicator(Counter score, String levelName) {
        this.rectangle = new Rectangle(LEFT_POINT, WIDTH, SIZE, Color.DARK_GRAY);
        this.score = score;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.white);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.drawText(WIDTH / 2 - 100, 25, "score: " + this.score.getValue() + "", 20);
        d.drawText(WIDTH - 300, 25, "Level Name: " + this.levelName, 20);
    }

    @Override
    public void timePassed() {
        return;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}
