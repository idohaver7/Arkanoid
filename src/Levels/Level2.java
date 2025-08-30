//318910890 Ido Haver
package Levels;

import Backgrounds.BackgroundLevel2;
import GUI.Collidable.Block;
import GUI.Shapes.Point;
import GUI.Shapes.Rectangle;
import GUI.Sprites.Sprite;
import GUI.Sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * all the information methods about level 2.
 */
public class Level2 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        double angle = 30;
        double speed = 4;
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            velocities.add(v);
            angle = angle + 10;
            if (angle == 70) {
                angle = 300;
            }
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 400;
    }

    @Override
    public String levelName() {
        return "Mid Level";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundLevel2();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double width = 740.0 / 15;
        int height = 20;
        double leftX = 20;
        Color color = Color.RED;
        for (int i = 0; i < 15; i++) {
            if (i >= 2 && i < 4) {
                color = Color.green;
            }
            if (i >= 4 && i < 6) {
                color = Color.pink;
            }
            if (i >= 6 && i < 9) {
                color = Color.yellow;
            }
            if (i >= 9 && i < 11) {
                color = Color.blue;
            }
            if (i >= 11 && i < 13) {
                color = Color.white;
            }
            if (i >= 13) {
                color = Color.cyan;
            }
            Block block = new Block(new Rectangle(new Point(leftX, 200), width, height, color));
            blocks.add(block);
            leftX += width;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
