//318910890 Ido Haver
package GUI;

import GUI.Animation.AnimationRunner;
import GUI.Animation.EndScreen;
import GUI.Animation.KeyPressStoppableAnimation;
import Levels.GameLevel;
import Levels.LevelInformation;
import Listeners.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * organized the game flow.
 */
public class GameFlow {
    static final int HIT_DEAD_BLOCK = -1;
    static final int END_OF_GAME = 0;
    private AnimationRunner animationRunner;

    /**
     * constructor.
     *
     * @param ar the animation runner
     */
    public GameFlow(AnimationRunner ar) {
        this.animationRunner = ar;
    }

    /**
     * run the given levels.
     *
     * @param levels the given levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter();
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(this.animationRunner, levelInfo, score);
            level.initialize();

            while (level.shouldStop()) {
                level.run();
                break;
            }
            //game over
            if (level.getBallsNumber().getValue() == END_OF_GAME || level.getDeadBlock().getValue() == HIT_DEAD_BLOCK) {
                this.animationRunner.run(new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor(),
                        KeyboardSensor.SPACE_KEY, new EndScreen(
                        score.getValue(), this.animationRunner.getGui(), false)));
                this.animationRunner.getGui().close();
                break;
            }
        }
        //win the game
        this.animationRunner.run(new KeyPressStoppableAnimation(animationRunner.getGui().getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY, new EndScreen(
                score.getValue(), this.animationRunner.getGui(), true)));
        this.animationRunner.getGui().close();
    }
}
