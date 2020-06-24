package score;
import java.io.Serializable;
/**
 * Class ScoreInfo.
 * @author Michael Ternovsky 316534809
 */
public class ScoreInfo implements Serializable, Comparable<ScoreInfo> {
    private static final long serialVersionUID = 1L;
    private String name;
    private int score;
    /**
     * ScoreInfo - the constructor of the class.
     * @param name  - the name of the player.
     * @param score - his score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }
    /**
     * getName - returns the name of the player.
     * @return String - the name.
     */
    public String getName() {
        return this.name;
    }
    /**
     * getScore - returns the score of the player.
     * @return int - the score.
     */
    public int getScore() {
        return this.score;
    }
    /**
     * compareTo - the function overrides the compareTo function.
     * @param s - the score info.
     * @return int - according to the compare value;
     */
    public int compareTo(ScoreInfo s) {
        if (this.getScore() > s.getScore()) {
            return 1;
        }
        if (this.getScore() < s.getScore()) {
            return -1;
        }
        return 0;
    }
}
