package levels;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import components.Block;
import components.Velocity;
import game.LevelInformation;
import geometry.Point;
import interfaces.Sprite;
/**
 * class LevelForm.
 * @author Michael Ternovsky 316534809
 */
public class LevelForm implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private List<Point> initialBallStartingPoint;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    /**
     * buildListOfVelocisties - the function is responsible for building the list of
     * velocities.
     * @param ballVelocities - the String of the velocities.
     */
    private void buildListOfVelocisties(String ballVelocities) {
        // split the ball_velocities array
        String[] splittedBallVelocities = ballVelocities.split(" ");
        // we infer the number of balls from the balls initial velocities
        this.numberOfBalls = splittedBallVelocities.length;
        // run over the splitted array
        for (String s : splittedBallVelocities) {
            // for each speed - create new velocity by speed and angle
            String[] splittedVelocity = s.split(",");
            double angle = Integer.parseInt(splittedVelocity[0]);
            double speed = Integer.parseInt(splittedVelocity[1]);
            this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(angle, speed));
        }
    }
    /**
     * buildListOfStartingPoints - the function is responsible for building the list
     * of starting points. Notice that the balls have the same starting points.
     */
    private void buildListOfStartingPoints() {
        // the size of the array
        int size = this.initialBallVelocities.size();
        // set the same starting point to all of the balls
        this.initialBallStartingPoint = new ArrayList<Point>(size);
        for (int i = 0; i < size; i++) {
            this.initialBallStartingPoint.add(new Point(400, 570));
        }
    }
    /**
     * buildBackground - the function is responsible for building the background by
     * the input String that represents the background. the function create a
     * LevelFormBackground object while the object get a String that represents the
     * background and make from it a Sprite background.
     * @param background1 - the new background.
     */
    private void buildBackground(String background1) {
        LevelFormBackground newBackground = new LevelFormBackground(background1);
        this.background = (Sprite) newBackground;
    }
    /**
     * buildBlocks - the function is responsible for building the list of blocks.
     * @param blocksDetails    - the list of blocks details.
     * @param blockDefinitions - the block definitions.
     * @param blocksStartX     - the blocks start x.
     * @param blocksStartY     - the blocks start y.
     * @param rowHeight        - row height.
     */
    private void buildBlocks(List<String> blocksDetails, String blockDefinitions, String blocksStartX,
            String blocksStartY, String rowHeight) {
        InputStream tempIs = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDefinitions);
        java.io.Reader reader = new LineNumberReader(new BufferedReader(new InputStreamReader(tempIs)));
        // read the file and get the factory object
        BlocksFromSymbolsFactory newFactoryObj = BlocksDefinitionReader.fromReader(reader);
        // run over the details of the block
        int startY = Integer.parseInt(blocksStartY);
        for (int j = 0; j < blocksDetails.size(); j++) {
            startY += j * (Integer.parseInt(rowHeight));
            int startX = Integer.parseInt(blocksStartX);
            for (int i = 0; i < blocksDetails.get(j).length(); i++) {
                // read each char of the line
                Character c = blocksDetails.get(j).charAt(i);
                if (newFactoryObj.isSpaceSymbol(c.toString())) {
                    // it is a spacer
                    startX += newFactoryObj.getSpaceWidth(c.toString());
                } else if (newFactoryObj.isBlockSymbol(c.toString())) {
                    // it is a block
                    Block newBlock = newFactoryObj.getBlock(c.toString(), startX, startY);
                    // the space in x
                    startX += newBlock.getCollisionRectangle().getWidth();
                    // add the new block to the list
                    this.blocks.add(newBlock);
                }
            }
            startY = Integer.parseInt(blocksStartY);
        }
    }
    /**
     * levelForm - the constructor of the class.
     * @param ballVelocities   - the velocities of the ball.
     * @param background       - the background.
     * @param blockDefinitions - the block definitions.
     * @param blocksStartX     - the staring x point of the blocks.
     * @param blocksStartY     - the staring y point of the blocks.
     * @param rowHeight        - the height of the rows.
     * @param blocksDetails    - the list of the details.
     */
    public LevelForm(String ballVelocities, String background, String blockDefinitions, String blocksStartX,
            String blocksStartY, String rowHeight, List<String> blocksDetails) {
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.blocks = new ArrayList<Block>();
        // call the function that responsible for building the list of velocities
        this.buildListOfVelocisties(ballVelocities);
        // call the function that responsible for building the list of starting points
        this.buildListOfStartingPoints();
        // call the function that responsible for building the background
        this.buildBackground(background);
        // call the function that responsible for building the blocks list
        this.buildBlocks(blocksDetails, blockDefinitions, blocksStartX, blocksStartY, rowHeight);
    }
    /**
     * setNumBlocks - sets the number of blocks.
     * @param numBlocks1 - the number of blocks.
     */
    public void setNumBlocks(String numBlocks1) {
        this.numberOfBlocksToRemove = Integer.parseInt(numBlocks1);
    }
    /**
     * setLevelName - sets the level name.
     * @param levelName1 - the level name.
     */
    public void setLevelName(String levelName1) {
        this.levelName = levelName1;
    }
    /**
     * setPaddleSpeed - set the speed of the paddle.
     * @param paddleSpeed1 - the speed of the paddle.
     */
    public void setPaddleSpeed(String paddleSpeed1) {
        this.paddleSpeed = Integer.parseInt(paddleSpeed1);
    }
    /**
     * setPaddleWidth - set the width of the paddle.
     * @param paddleWidth1 - the width of the paddle.
     */
    public void setPaddleWidth(String paddleWidth1) {
        this.paddleWidth = Integer.parseInt(paddleWidth1);
    }
    /**
     * numberOfBalls - return the number of balls in the level.
     * @return int - the number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    /**
     * initialBallVelocities - return the list of balls initial velocities in the
     * level.
     * @return List<Velocity> - the list of balls initial velocities in the level.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }
    /**
     * initialBallStartingPoint - return the list of balls initial Points in the
     * level.
     * @return List<Point> - the list of balls initial Points in the level.
     */
    public List<Point> initialBallStartingPoint() {
        return this.initialBallStartingPoint;
    }
    /**
     * paddleSpeed - return the speed of the paddle.
     * @return int - the speed of the paddle.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    /**
     * paddlpaddleWidtheSpeed - return the width of the paddle.
     * @return int - the width of the paddle.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }
    /**
     * levelName - return the name of the level.
     * @return String - the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }
    /**
     * getBackground - return the background of the level.
     * @return Sprite - the background of the level.
     */
    public Sprite getBackground() {
        return this.background;
    }
    /**
     * blocks - return the list of the block in the level.
     * @return List<Block> - the list of the block in the level.
     */
    public List<Block> blocks() {
        return this.blocks;
    }
    /**
     * numberOfBlocksToRemove - return the number of blocks we need to remove in the
     * level.
     * @return int - the number of blocks we need to remove in the level.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}