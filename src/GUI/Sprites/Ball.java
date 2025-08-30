//318910890 Ido Haver
package GUI.Sprites;

import GUI.Collidable.CollisionInfo;
import GUI.Collidable.GameEnvironment;
import GUI.Shapes.Point;
import GUI.Collidable.Collidable;
import GUI.Shapes.Line;
import Levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * an object that represent ball in coordinate system.
 */
public class Ball implements Sprite {
    static final int INITIALIZE_VELOCITY = 0;
    private GameEnvironment gameEnvironment;
    private int radius;
    private Point center;
    private java.awt.Color color;
    private Velocity velocity;


    /**
     * constructor.
     *
     * @param x               the x value of the center point
     * @param y               the y value of the center point
     * @param r               the size
     * @param color           the color
     * @param gameEnvironment the environment that the ball will move in
     */

    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(INITIALIZE_VELOCITY, INITIALIZE_VELOCITY);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * constructor.
     *
     * @param center          the center point
     * @param r               the size of the radius
     * @param color           the color
     * @param gameEnvironment the environment that the ball move in
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(INITIALIZE_VELOCITY, INITIALIZE_VELOCITY);
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @return the x value of the center point in int.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return the y value of the center point in int.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return the size of the radius.
     */

    public int getSize() {
        return this.radius;
    }

    /**
     * @return the color of the ball.
     */

    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * draw the ball on the given surface.
     *
     * @param surface the drawing surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * set the velocity to the given value.
     *
     * @param v the new velocity
     */

    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set velocity by the dx and dy.
     *
     * @param dx the velocity by the x line
     * @param dy the velocity by the y line
     */

    public void setVelocity(double dx, double dy) {
        this.velocity.setDx(dx);
        this.velocity.setDy(dy);

    }

    /**
     * control the movement of the ball in the terms of the collidables in the game and its velocity.
     */
    public void moveOneStep() {
        //the ball movement line
        Line trajectory = new Line(this.center.getX(), this.center.getY(),
                this.center.getX() + this.velocity.getDx(), this.center.getY() + this.velocity.getDy());
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        if (collision != null) {
            this.center.setX(collision.collisionPoint().getX() - this.velocity.getDx());
            this.center.setY(collision.collisionPoint().getY() - this.velocity.getDy());
            Collidable collidable = collision.collisionObject();
            this.velocity = collidable.hit(this, collision.collisionPoint(), this.velocity);
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove this ball from the given game.
     *
     * @param g the given game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}