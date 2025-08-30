//318910890 Ido Haver
package Levels;

import GUI.Animation.Animation;
import GUI.Animation.KeyPressStoppableAnimation;
import GUI.Animation.AnimationRunner;
import GUI.Animation.PauseScreen;
import GUI.Animation.CountdownAnimation;
import GUI.Collidable.AddBallBlock;
import GUI.Collidable.Collidable;
import GUI.Collidable.GameEnvironment;
import GUI.Collidable.Block;
import GUI.Collidable.DeadBlock;
import GUI.Shapes.Point;
import GUI.Shapes.Rectangle;
import GUI.Sprites.Paddle;
import GUI.Sprites.SpriteCollection;
import GUI.Sprites.Ball;
import GUI.Sprites.Sprite;
import GUI.Sprites.Velocity;
import GUI.Sprites.ScoreIndicator;
import Listeners.AddBall;
import Listeners.Counter;
import Listeners.DeadBlockListener;
import Listeners.ScoreTrackingListener;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Manual the current level.
 */
public class GameLevel implements Animation {
    //magic numbers
    static final int END_OF_GAME = 0;
    static final int PLUS_ONE = 1;
    static final int DESTROY_ALL_BLOCKS_SCORE = 100;
    static final Color BACKGROUNDCOLOR = new Color(0, 0, 70);
    static final double DEFAULT_SIZE = 20;
    static final double LEFT_BLOCK_HEIGHT = 580;
    static final double BOTTOM_BLOCK_WIDTH = 800;
    static final Point LEFT_BLOCK_POINT = new Point(0, 20);
    static final Point RIGHT_BLOCK_POINT = new Point(780, 20);
    static final Point DEAD_BLOCK_POINT = new Point(0, 600);
    static final Point TOP_BLOCK_POINT = new Point(0, 10);
    static final int GUI_WIDTH = 800;
    static final int BALL_RADIUS = 5;
    static final double SPEED_BALL = 4;
    static final double STARTING_ANGLE = 30;
    static final int HIT_DEAD_BLOCK = -1;
    //members
    private SpriteCollection sprites;
    private Counter deadBlock;
    private GameEnvironment environment;
    private Counter blocksNumber;
    private Counter ballsNumber;
    private Counter score;
    private LevelInformation levelInformation;
    private AnimationRunner runner;
    private boolean running;

    /**
     * constructor.
     *
     * @param runner           the animation runner
     * @param levelInformation the specific level information
     * @param score            the score
     */
    public GameLevel(AnimationRunner runner, LevelInformation levelInformation, Counter score) {
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksNumber = new Counter();
        this.ballsNumber = new Counter();
        this.score = score;
        this.deadBlock = new Counter();
        this.runner = runner;
    }

    /**
     * add collidable to the environment.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add new sprite to the sprites list.
     *
     * @param s the new sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove the given collidable from the list.
     *
     * @param c the given collidable
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * remove the given sprite from the list.
     *
     * @param s the given collidable
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * add ball to the game.
     */
    public void addBall() {
        Ball ball = new Ball(new Point(500, 50), BALL_RADIUS, Color.PINK, this.environment);
        Velocity v1 = Velocity.fromAngleAndSpeed(STARTING_ANGLE, SPEED_BALL);
        ball.setVelocity(v1);
        ball.addToGame(this);
        ballsNumber.increase(PLUS_ONE);
    }

    /**
     * Initialize a new game: create the Blocks and Ball RECTANGLE and Paddle and add them to the game.
     */
    public void initialize() {
        DeadBlockListener deadBlockListener = new DeadBlockListener(this, this.deadBlock);
        AddBall addBall = new AddBall(this);
        BlockRemover blockRemover = new BlockRemover(this, blocksNumber);
        BallRemover ballRemover = new BallRemover(this, ballsNumber);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        //initialize blocks
        levelInformation.getBackground().addToGame(this);
        blocksNumber.increase(levelInformation.numberOfBlocksToRemove());
        for (Block block : levelInformation.blocks()) {
            block.addToGame(this);
            if (block instanceof DeadBlock) {
                block.addHitListener(deadBlockListener);
                continue;
            }
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            if (block instanceof AddBallBlock) {
                block.addHitListener(addBall);
            }
        }
        //initialize balls
        ballsNumber.increase(levelInformation.numberOfBalls());
        for (Velocity v : levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(new Point((double) this.runner.getGui().getDrawSurface().getWidth() / 2, 400),
                    BALL_RADIUS, Color.white, this.environment);
            ball.setVelocity(v);
            ball.addToGame(this);
        }
        //initialize bounds
        Block leftBlock = new Block(new Rectangle(LEFT_BLOCK_POINT, DEFAULT_SIZE, LEFT_BLOCK_HEIGHT, Color.gray));
        Block upBlock = new Block(new Rectangle(TOP_BLOCK_POINT, BOTTOM_BLOCK_WIDTH, DEFAULT_SIZE, Color.gray));
        Block rightBlock = new Block(new Rectangle(RIGHT_BLOCK_POINT, DEFAULT_SIZE, LEFT_BLOCK_HEIGHT, Color.gray));
        Block deathBlock = new Block(new Rectangle(DEAD_BLOCK_POINT, GUI_WIDTH, DEFAULT_SIZE, BACKGROUNDCOLOR));
        deathBlock.addHitListener(ballRemover);
        leftBlock.addToGame(this);
        upBlock.addToGame(this);
        deathBlock.addToGame(this);
        rightBlock.addToGame(this);
        //initialize the paddle
        Paddle paddle = new Paddle(this.runner.getGui().getKeyboardSensor(), levelInformation.paddleWidth(),
                levelInformation.paddleSpeed());
        paddle.addToGame(this);
        //initialize the score display
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, levelInformation.levelName());
        scoreIndicator.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.runner.getGui().getKeyboardSensor().isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(runner.getGui().getKeyboardSensor(),
                    KeyboardSensor.SPACE_KEY, new PauseScreen(this.runner.getGui().getKeyboardSensor())));
        }
    }

    @Override
    public boolean shouldStop() {
        if (ballsNumber.getValue() == END_OF_GAME) {
            this.running = false;
        }
        if (blocksNumber.getValue() == END_OF_GAME) {
            score.increase(DESTROY_ALL_BLOCKS_SCORE);
            this.running = false;
        }
        if (this.deadBlock.getValue() == HIT_DEAD_BLOCK) {
            this.running = false;
        }
        return !this.running;
    }

    /**
     * @return the balls number
     */
    public Counter getBallsNumber() {
        return ballsNumber;
    }

    /**
     * @return the dead block flag
     */
    public Counter getDeadBlock() {
        return deadBlock;
    }


}
