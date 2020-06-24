package game;
import java.util.List;
import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import components.Ball;
import components.Block;
import components.Counter;
import components.Paddle;
import components.SpriteCollection;
import components.Velocity;
import geometry.Point;
import geometry.Rectangle;
import indicators.LevelNameIndicator;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import interfaces.Collidable;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
/**
 * Class GameLevel.
 * @author Michael Ternovsky 316534809
 */
public class GameLevel implements Animation {
    // Fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter livesCounter;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation levelInfo;
    /**
     * GameLevel - the constructor of the class.
     * @param levelInfo       - the level information.
     * @param keyboardSensor  - the keyboard sensor.
     * @param animationRunner - the object that runs the animation loop.
     * @param livesCounter    - the number of lives.
     * @param score           - the score counter.
     */
    public GameLevel(LevelInformation levelInfo, biuoop.KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
            Counter livesCounter, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.livesCounter = livesCounter;
        this.running = false;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.levelInfo = levelInfo;
        this.score = score;
    }
    /**
     * addCollidable - the function adds a collidable object to the list of
     * collidables - the Collidable object.
     * @param c - the
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * addSprite - the function adds a sprite object to the list of sprites.
     * @param s - the Sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * initialize - the function initializes a new game - create the Blocks and Ball
     * (and Paddle) and add them to the game.
     */
    public void initialize() {
        // Creating score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        this.sprites.addSprite(scoreIndicator);
        // Creating lives indicator
        LivesIndicator livesIndicator = new LivesIndicator(this.livesCounter);
        this.sprites.addSprite(livesIndicator);
        // Creating level name indicator
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(this.levelInfo.levelName());
        this.sprites.addSprite(levelNameIndicator);
        // Creating block remover object
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        // Creating ball remover object
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        // Creating score tracking object
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);
        // Creating the frame blocks
        Rectangle frameRectangleUp = new Rectangle(new Point(0, 20), 800, 25);
        Block b1 = new Block(frameRectangleUp, 0, java.awt.Color.GRAY);
        b1.addToGame(this);
        Rectangle frameRectangleLeft = new Rectangle(new Point(0, 20), 25, 580);
        Block b2 = new Block(frameRectangleLeft, 0, java.awt.Color.GRAY);
        b2.addToGame(this);
        Rectangle frameRectangletDown = new Rectangle(new Point(0, 600), 800, 25);
        Block b3 = new Block(frameRectangletDown, 0, java.awt.Color.GRAY);
        b3.addToGame(this);
        // frameRectangletDown - the "death region"
        b3.addHitListener(ballRemover);
        Rectangle frameRectangleRight = new Rectangle(new Point(775, 20), 25, 580);
        Block b4 = new Block(frameRectangleRight, 0, java.awt.Color.GRAY);
        b4.addToGame(this);
        // Creating the game blocks
        List<Block> blocksList = this.levelInfo.blocks();
        if (blocksList != null) {
            for (Block b : blocksList) {
                Block newBlock = b;
                this.blockCounter.increase(1);
                newBlock.addToGame(this);
                newBlock.addHitListener(blockRemover);
                newBlock.addHitListener(scoreTracker);
            }
        }
    }
    /**
     * createBallsOnThePaddle - the function creates the balls of the game.
     */
    public void createBallsOnThePaddle() {
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(this.levelInfo.initialBallStartingPoint().get(i), 6, java.awt.Color.WHITE);
            ball.setGameEnvironment(this.environment);
            ball.setTheFrame(new Point(0, 0), new Point(800, 800));
            Velocity v1 = this.levelInfo.initialBallVelocities().get(i);
            ball.setVelocity(v1);
            ball.addToGame(this);
            this.ballCounter.increase(1);
        }
    }
    /**
     * creatPaddle - the function creates the paddle.
     */
    public void creatPaddle() {
        // Adding the paddle to the game
        Rectangle rectangle = new Rectangle(new Point(400 - (this.levelInfo.paddleWidth() / 2), 584),
                this.levelInfo.paddleWidth(), 15);
        Paddle newPaddle = new Paddle(rectangle, new java.awt.Color(51, 51, 51), this.keyboard,
                this.levelInfo.paddleWidth(), this.levelInfo.paddleSpeed());
        newPaddle.addToGame(this);
        // update the field
        this.paddle = newPaddle;
    }
    /**
     * shouldStop - the function returns true if the game should run and false
     * otherwise.
     * @return boolean - true if the game should run and false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * doOneFrame - the function runs one frame of the animation.
     * @param d - the surface in which we want to draw the animation.
     */
    public void doOneFrame(DrawSurface d) {
        // Draw the background
        this.levelInfo.getBackground().drawOn(d);
        // draw the objects
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // checking if there are still blocks left - if not +100 points to the score
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        // Checking if there are still balls is the game
        if (this.ballCounter.getValue() == 0) {
            this.livesCounter.decrease(1);
            // remove the paddle from the game
            this.paddle.removeFromGame(this);
            this.running = false;
        }
        // pause key
        if (this.keyboard.isPressed("p")) {
            PauseScreen pauseScreen = new PauseScreen();
            KeyPressStoppableAnimation kps = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    pauseScreen);
            this.runner.run(kps);
        }
    }
    /**
     * playOneTurn - the function runs one turn of the game - starts the animation
     * loop.
     */
    public void playOneTurn() {
        this.creatPaddle();
        this.createBallsOnThePaddle();
        CountdownAnimation n = new CountdownAnimation(2, 3, this.sprites, this.levelInfo);
        this.runner.run(n);
        this.running = true;
        this.runner.run(this);
    }
    /**
     * run - the function runs the game - starts the animation loop.
     */
    public void run() {
        // Play the first round
        this.playOneTurn();
        // while there are still lives and blocks, continue
        while (this.livesCounter.getValue() != 0 && this.blockCounter.getValue() != 0) {
            this.playOneTurn();
        }
        gui.close();
        return;
    }
    /**
     * removeCollidable - the function removes the Collidable object from the
     * environment.
     * @param c - Collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * removeSprite - the function removes the Sprite object from the sprites.
     * @param s - Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * getLivesCounter - the function returns the live counter.
     * @return int - the live counter.
     */
    public int getLivesCounter() {
        return this.livesCounter.getValue();
    }
    /**
     * getLivesCounter - the function returns the block counter.
     * @return int - the block counter.
     */
    public int getblockCounter() {
        return this.blockCounter.getValue();
    }
    /**
     * getScoreCounter - the function returns the score counter.
     * @return int - the score counter.
     */
    public int getScoreCounter() {
        return this.score.getValue();
    }
}