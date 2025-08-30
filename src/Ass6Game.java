//318910890 Ido Haver

import GUI.Animation.AnimationRunner;
import GUI.GameFlow;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * class that run the game.
 */
public class Ass6Game {
    /**
     * coverts the string args to numbers in int.
     *
     * @param inputs the args from the user
     * @return return a list with the correct numbers of levels.
     */
    public static List<Integer> stringsToArray(String[] inputs) {
        if (inputs == null) {
            return null;
        }
        //build the new arr
        List<Integer> numbers = new ArrayList<>();
        /*
         *convert each cell to int
         */
        for (int i = 0; i < inputs.length; i++) {
            try {
                int num = Integer.parseInt(inputs[i]);
                if (num == 1 || num == 2 || num == 3) {
                    numbers.add(num);
                }
            } catch (NumberFormatException e) {

            }
        }
        return numbers;
    }

    /**
     * run the game.
     *
     * @param args the arguments from the user
     */
    public static void main(String[] args) {
        List<Integer> inputs = stringsToArray(args);
        AnimationRunner animationRunner1 = new AnimationRunner();
        GameFlow gameFlow = new GameFlow(animationRunner1);
        List<LevelInformation> levels = new ArrayList<>();
        LevelInformation level1 = new Level1();
        LevelInformation level2 = new Level2();
        LevelInformation level3 = new Level3();
        if (inputs.isEmpty()) {
            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
        } else {
            for (int num : inputs) {
                if (num == 1) {
                    levels.add(level1);
                } else if (num == 2) {
                    levels.add(level2);
                } else if (num == 3) {
                    levels.add(level3);
                }
            }
        }
        gameFlow.runLevels(levels);
    }
}
