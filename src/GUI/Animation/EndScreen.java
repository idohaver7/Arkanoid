//318910890 Ido Haver
package GUI.Animation;

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * the end screen animation.
 */
public class EndScreen implements Animation {
    private int finalScore;
    private boolean isWin;
    private GUI gui;
    private boolean stop;

    /**
     * constructor.
     * @param finalScore the final score
     * @param gui the given gui
     * @param isWin bollen to see if win or loose
     */
    public EndScreen(int finalScore, GUI gui, boolean isWin) {
        this.gui = gui;
        this.finalScore = finalScore;
        this.isWin = isWin;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(10, 0, 1000, 1000);
        d.setColor(Color.RED);
        if (isWin) {
            d.drawText(150, 300, "You Win! Your score is " + this.finalScore, 40);
        } else {
            d.drawText(150, 300, "Game Over. Your score is " + this.finalScore, 40);
        }
        d.drawText(150, 500, "--press space to close the game", 30);
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}

