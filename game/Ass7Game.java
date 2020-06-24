package game;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import animations.AnimationRunner;
import animations.Menu;
import animations.MenuAnimation;
import animations.HighScoresAnimation;
import biuoop.GUI;
import levels.LevelSpecificationReader;
import tasks.QuitTask;
import tasks.ShowHiScoresTask;
import tasks.StartGameTask;
import tasks.SubMenuTask;
import tasks.Task;
/**
 * Class GameLevel.
 * @author Michael Ternovsky 316534809
 */
public class Ass7Game {
    /**
     * The main of the game.
     * @param args - array of strings.
     * @throws FileNotFoundException - the exception.
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Create a new GUI
        GUI gui = new GUI("Arkanoid", 800, 600);
        // Create the animation runner
        AnimationRunner ar = new AnimationRunner(gui, 60);
        // Create the keyboard
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        String path = "";
        List<String> levelSetList = new ArrayList<String>();
        if (args.length != 0) {
            path = args[0];
        } else {
            path = "level_sets.txt";
        }
        BufferedReader br = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(path)));
        String currentLevel = "";
        
        try {
            // open file connection
            String currentLine = br.readLine();
            while (currentLine != null) {
                if (!currentLine.equals("")) {
                    currentLevel += currentLine;
                    // add the level info the the list of blocks info
                    levelSetList.add(currentLevel);
                    currentLevel = "";
                }
                currentLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("There was a problem finding the file");
        } catch (IOException e) {
            System.out.println("There was a problem reading the file");
        }
        // for each button that is pressed, execute its method
        while (true) {
            ShowHiScoresTask highScoreTask = new ShowHiScoresTask(ar, new HighScoresAnimation(), keyboard);
            QuitTask quitTask = new QuitTask();
            Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(keyboard);
            // create the sub menu
            Menu<Task<Void>> subMenuDifficulty = new MenuAnimation<Task<Void>>(keyboard);
            // creating the sub menu
            SubMenuTask<Task<Void>> subMenuTask = new SubMenuTask<Task<Void>>(ar, subMenuDifficulty);
            menu.addSelection("s", "(s) Start game", subMenuTask);
            menu.addSelection("h", "(h) Highscores", highScoreTask);
            menu.addSelection("q", "(q) Quit", quitTask);
            String key = "";
            String message = "";
            for (int i = 0; i < levelSetList.size(); i++) {
                // run over the list
                List<LevelInformation> defultLevels = new ArrayList<LevelInformation>();
                StartGameTask startGameTask = null;
                if (i % 2 == 0) {
                    // get the key and the title of the line in the menu
                    String[] str = levelSetList.get(i).split(":");
                    key = str[0];
                    message = str[1];
                } else {
                    // the path of the level
                    String url = levelSetList.get(i);
                    // read the details of the level from the url
                    LevelSpecificationReader levelReader = new LevelSpecificationReader();
                    InputStreamReader reader = new InputStreamReader(
                            ClassLoader.getSystemClassLoader().getResourceAsStream(url));
                    // Add the levels from the file
                    defultLevels = new ArrayList<LevelInformation>();
                    defultLevels = levelReader.fromReader(reader);
                    // creating the startGameTask with those levels
                    startGameTask = new StartGameTask(ar, keyboard, gui, defultLevels);
                    // add the difficulty to the menu
                    subMenuDifficulty.addSelection(key, message, startGameTask);
                }
            }
            // run the menu animation
            ar.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}