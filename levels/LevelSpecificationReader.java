package levels;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import game.LevelInformation;
/**
 * Class LevelSpecificationReader.
 * @author Michael Ternovsky 316534809
 */
public class LevelSpecificationReader {
    /**
     * levelPartition - the method gets levels file and returns a list of String,
     * while each String represent the information about each level.
     * @param reader - the reader of the file.
     * @return List<String> - the levels information.
     */
    public List<String> levelPartition(java.io.Reader reader) {
        List<String> levelsInfoList = new ArrayList<String>();
        BufferedReader br = new BufferedReader(reader);
        String currentLevel = "";
        try {
            // open file connection
            String currentLine = br.readLine();
            while (currentLine != null) {
                // while we are still at the same level info block
                while (currentLine != null && !currentLine.equals("END_LEVEL")) {
                    if (!currentLine.equals("START_LEVEL") && !currentLine.equals("\n") && !currentLine.equals("")) {
                        // reading lines from the file and adding them to the level string
                        currentLevel += currentLine;
                        currentLevel += "\n";
                        currentLine = br.readLine();
                    } else {
                        currentLine = br.readLine();
                    }
                }
                // add the level info the the list of levels info
                if (!currentLevel.equals("")) {
                    levelsInfoList.add(currentLevel);
                }
                currentLevel = "";
                currentLine = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("There was a problem finding the file");
        }
        return levelsInfoList;
    }
    /**
     * converLevelInfoStrToObject - the function gets a list of Strings that
     * represents the info of each level, and returns a list of LevelInformation
     * objects, that are the actual levels of the game.
     * @param reader - the reader.
     * @return List<LevelInformation> - a list of LevelInformation objects, that are
     *         the actual levels of the game.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<String> levelsInfoList = this.levelPartition(reader);
        int fieldsCounter = 0;
        String levelName = "", ballVelocities = "", background = "", paddleSpeed = "", paddleWidth = "",
                blockDefinitions = "", blocksStartX = "", blocksStartY = "", rowHeight = "", numBlocks = "";
        List<String> blocksDetails = new ArrayList<String>(); // the list of details
        List<LevelInformation> levelsList = new ArrayList<LevelInformation>();
        for (String currentLevelStr : levelsInfoList) {
            String[] splittedString = currentLevelStr.split("\n");
            for (String str : splittedString) {
                String[] splittedFieldLine = str.split(":");
                switch (splittedFieldLine[0]) {
                case "level_name":
                    levelName = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "ball_velocities":
                    ballVelocities = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "background":
                    background = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "paddle_speed":
                    paddleSpeed = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "paddle_width":
                    paddleWidth = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "block_definitions":
                    blockDefinitions = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "blocks_start_x":
                    blocksStartX = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "blocks_start_y":
                    blocksStartY = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "row_height":
                    rowHeight = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "num_blocks":
                    numBlocks = splittedFieldLine[1];
                    fieldsCounter++;
                    break;
                case "START_BLOCKS":
                    break;
                default:
                    break;
                }
            }
            for (int i = 0; i < splittedString.length; i++) {
                if (splittedString[i].equals("START_BLOCKS")) {
                    while (!splittedString[i].equals("END_BLOCKS")) {
                        blocksDetails.add(splittedString[i + 1]);
                        i++;
                    }
                }
            }
            blocksDetails.remove(blocksDetails.size() - 1);
            if (fieldsCounter == 10) {
                LevelForm newLevel = new LevelForm(ballVelocities, background, blockDefinitions, blocksStartX,
                        blocksStartY, rowHeight, blocksDetails);
                newLevel.setPaddleSpeed(paddleSpeed);
                newLevel.setPaddleWidth(paddleWidth);
                newLevel.setLevelName(levelName);
                newLevel.setNumBlocks(numBlocks);
                levelsList.add(newLevel);
            } else {
                System.out.println("There are missing fields in the level");
            }
            fieldsCounter = 0;
            levelName = "";
            ballVelocities = "";
            background = "";
            paddleSpeed = "";
            paddleWidth = "";
            blockDefinitions = "";
            blocksStartX = "";
            blocksStartY = "";
            rowHeight = "";
            numBlocks = "";
            blocksDetails = new ArrayList<String>();
        }
        return levelsList;
    }
}