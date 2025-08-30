package Levels;

import Backgrounds.BackgroundLevel1;
import GUI.Collidable.Block;
import GUI.Shapes.Point;
import GUI.Shapes.Rectangle;
import GUI.Sprites.Sprite;
import GUI.Sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * represent level 1 information.
 */
public class Level1 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<>();
        Velocity v = Velocity.fromAngleAndSpeed(0, 5);
        ballVelocity.add(v);
        return ballVelocity;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Yam Pam Puz";
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundLevel1();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Block block1 = new Block(new Rectangle(new Point(387, 155), 30, 20, Color.RED));
        blockList.add(block1);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
