//318910890 Ido Haver
package GUI.Sprites;

import GUI.Collidable.Collidable;
import GUI.Shapes.Rectangle;
import Levels.GameLevel;
import GUI.Shapes.Point;
import GUI.Shapes.Line;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;

/**
 * represent the paddle in the game.
 */
public class Paddle implements Sprite, Collidable {
    static final int STARTING_LOCATION_IN_Y = 560;
    static final double PADDLE_WIDTH = 80;
    static final double PADDLE_HEIGHT = 20;
    static final double STEP_SIZE = 10;
    static final int MIDDLE = 400;
    static final double MAX_MOVEMENT_TO_LEFT = 30;
    static final double MAX_MOVEMENT_TO_RIGHT = 770;
    static final double PARTS = 5;
    static final double REGION_ONE_ANGLE = 300;
    static final double REGION_TWO_ANGLE = 330;
    static final double REGION_THREE_ANGLE = 0;
    static final double REGION_FOUR_ANGLE = 60;
    static final double REGION_FIVE_ANGLE = 30;
    private biuoop.KeyboardSensor keyboard;
    private int speed;
    private Rectangle rectangle;

    /**
     * constructor.
     * @param keyboard the given keyboard sensor
     * @param width the width of the paddle
     * @param speed the speed of the paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, int speed) {
        this.keyboard = keyboard;
        this.rectangle = new Rectangle(new Point(MIDDLE - width / 2, STARTING_LOCATION_IN_Y), width, PADDLE_HEIGHT,
                Color.yellow);
        this.speed = speed;
    }

    /**
     * move the paddle coordinates one step to the left.
     */
    public void moveLeft() {
        double x = this.rectangle.getUpperLeft().getX();
        if (x >= MAX_MOVEMENT_TO_LEFT) {
            this.rectangle.getUpperLeft().setX(x - this.speed);
        }
    }

    /**
     * move the paddle coordinates one step to the right.
     */
    public void moveRight() {
        double x = this.rectangle.getUpperLeft().getX();
        if (x <= MAX_MOVEMENT_TO_RIGHT - this.rectangle.getWidth()) {
            this.rectangle.getUpperLeft().setX(x + this.speed);
        }
    }

    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //check if the collision is in the upper side
        if (this.rectangle.upper().isExist(collisionPoint)) {
            double part = this.rectangle.getWidth() / PARTS;
            double leftX = this.rectangle.getUpperLeft().getX();
            double y = this.rectangle.getUpperLeft().getY();
            //set the parts
            Line region1 = new Line(this.rectangle.getUpperLeft(), new Point(leftX + part, y));
            Line region2 = new Line(region1.end(), new Point(leftX + (2 * part), y));
            Line region3 = new Line(region2.end(), new Point(leftX + (3 * part), y));
            Line region4 = new Line(region3.end(), new Point(leftX + (4 * part), y));
            Line region5 = new Line(region4.end(), new Point(leftX + (5 * part), y));
            //check in which part the collision occurred and act in accordance
            double dx = currentVelocity.getDx();
            double dy = currentVelocity.getDy();
            double v = Math.sqrt(dx * dx + dy * dy);
            if (region1.isExist(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(REGION_ONE_ANGLE, v);
            } else if (region2.isExist(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(REGION_TWO_ANGLE, v);
            } else if (region3.isExist(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(REGION_THREE_ANGLE, v);
            } else if (region4.isExist(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(REGION_FOUR_ANGLE, v);
            } else if (region5.isExist(collisionPoint)) {
                return Velocity.fromAngleAndSpeed(REGION_FIVE_ANGLE, v);
            }
        }
        return currentVelocity;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}