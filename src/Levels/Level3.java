//318910890 Ido Haver
package Levels;

import Backgrounds.BackgroundLevel3;
import GUI.Collidable.AddBallBlock;
import GUI.Collidable.Block;
import GUI.Collidable.DeadBlock;
import GUI.Shapes.Point;
import GUI.Shapes.Rectangle;
import GUI.Sprites.Sprite;
import GUI.Sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * represent the information about level3.
 */
public class Level3 implements LevelInformation {
    static final Point LEFT_BLOCK_POINT = new Point(0, 20);
    static final Point RIGHT_BLOCK_POINT = new Point(780, 20);
    static final Point DEAD_BLOCK_POINT = new Point(0, 600);
    static final Point TOP_BLOCK_POINT = new Point(0, 10);
    static final int GUI_WIDTH = 800;
    static final Color BACKGROUNDCOLOR = new Color(0, 0, 70);
    static final double DEFAULT_SIZE = 20;
    static final double LEFT_BLOCK_HEIGHT = 580;
    static final double BOTTOM_BLOCK_WIDTH = 800;
    static final int BLOCK_LINES = 6;
    static final int STARTING_X_BLOCKS = 180;
    static final int SPACE_BETWEEN_BLOCKS = 50;
    static final int BLOCKS_IN_A_ROW = 12;
    static final int BLOCKS_WIDTH = 50;
    static final int BLOCKS_HEIGHT = 20;
    static final int STARTING_Y_BLOCKS = 150;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity v1 = Velocity.fromAngleAndSpeed(30, 4);
        Velocity v2 = Velocity.fromAngleAndSpeed(20, 4);
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(v1);
        velocities.add(v2);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "You Can't Win Here";
    }

    @Override
    public Sprite getBackground() {
        return new  BackgroundLevel3();

    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] color = new Color[BLOCK_LINES];
        color[0] = Color.darkGray;
        color[1] = Color.red;
        color[2] = Color.yellow;
        color[3] = Color.blue;
        color[4] = Color.PINK;
        color[5] = Color.green;
        double y = STARTING_Y_BLOCKS;
        for (int i = 0; i < BLOCK_LINES; i++) {
            double x = STARTING_X_BLOCKS + i * SPACE_BETWEEN_BLOCKS;
            for (int j = 0; j < BLOCKS_IN_A_ROW - i; j++) {
                //create the dead block
                if (i == 3 && j == 5) {
                    Rectangle rect = new Rectangle(new Point(x, y), BLOCKS_WIDTH, BLOCKS_HEIGHT, Color.black);
                    Block deadBlock = new DeadBlock(rect);
                    blocks.add(deadBlock);
                    x += SPACE_BETWEEN_BLOCKS;
                    continue;
                    //create the add ball block
                }
                if (i == 0 && j == 5) {
                    Rectangle rect = new Rectangle(new Point(x, y), BLOCKS_WIDTH, BLOCKS_HEIGHT, Color.white);
                    AddBallBlock addBallBlock = new AddBallBlock(rect);
                    blocks.add(addBallBlock);
                    x += SPACE_BETWEEN_BLOCKS;
                    continue;
                }
                Rectangle rect = new Rectangle(new Point(x, y), BLOCKS_WIDTH, BLOCKS_HEIGHT, color[i]);
                Block block = new Block(rect);
                blocks.add(block);
                x += SPACE_BETWEEN_BLOCKS;
            }
            y += BLOCKS_HEIGHT;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 56;
    }
}
