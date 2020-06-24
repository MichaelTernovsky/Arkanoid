package score;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Class HighScoresTable.
 * @author Michael Ternovsky 316534809
 */
public class HighScoresTable implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<ScoreInfo> scores;
    private int size;
    private final transient Comparator<ScoreInfo> comp = new Comparator<ScoreInfo>() {
        /**
         * compare - override the compare method.
         * @return int - according to the compare value;
         */
        public int compare(ScoreInfo p1, ScoreInfo p2) {
            if (p1.getScore() > p2.getScore()) {
                return 1;
            }
            if (p2.getScore() > p1.getScore()) {
                return -1;
            }
            return 0;
        }
    };
    /**
     * HighScoresTable - empty constructor.
     */
    public HighScoresTable() {
        this.scores = new ArrayList<ScoreInfo>();
        this.size = 0;
    }
    /**
     * HighScoresTable - Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     * @param size - the max size of the table.
     */
    public HighScoresTable(int size) {
        this.scores = new ArrayList<ScoreInfo>();
        this.size = size;
    }
    /**
     * add - Add a high-score.
     * @param score1 - the score we want to add.
     */
    public void add(ScoreInfo score1) {
        this.scores.add(score1);
    }
    /**
     * size - Return table size.
     * @return int - the size of the table.
     */
    public int size() {
        return this.size;
    }
    /**
     * getHighScores - Return the current high scores. The list is sorted such that
     * the highest scores come first.
     * @return List<ScoreInfo> - the list of scores.
     */
    public List<ScoreInfo> getHighScores() {
        if (this.scores != null) {
            Collections.sort(this.scores, Collections.reverseOrder(comp));
        }
        return this.scores;
    }
    /**
     * getRank - return the rank of the current score: where will it be on the list
     * if added? Rank 1 means the score will be highest on the list. Rank `size`
     * means the score will be lowest. Rank > `size` means the score is too low and
     * will not be added to the list.
     * @param score1 - the score.
     * @return int - the rank.
     */
    public int getRank(int score1) {
        List<ScoreInfo> sortedTable = this.getHighScores();
        if (sortedTable.isEmpty()) {
            return 1;
        }
        int rank = 1;
        for (int i = 0; i < sortedTable.size(); i++) {
            // if the score is not found yet, the rank keeps growing
            if (sortedTable.get(i).getScore() >= score1) {
                rank++;
            }
        }
        return rank;
    }
    /**
     * clear - Clears the table.
     */
    public void clear() {
        this.scores.clear();
    }
    /**
     * load - Load table data from file. Current table data is cleared.
     * @param filename - the name of the file.
     * @throws IOException - exception of the file.
     */
    public void load(File filename) throws IOException {
        // clear the old table
        this.scores.clear();
        FileInputStream input = null;
        ObjectInputStream obj = null;
        try {
            input = new FileInputStream(filename);
            obj = new ObjectInputStream(input);
            HighScoresTable newScoreTable = (HighScoresTable) (obj.readObject());
            this.size = newScoreTable.size();
            this.scores = newScoreTable.getHighScores();
        } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (obj != null) {
                try {
                    obj.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
    }
    /**
     * save - Save table data to the specified file.
     * @param filename - the name of the file.
     * @throws IOException - exception of the file.
     */
    public void save(File filename) throws IOException {
        FileOutputStream output = null;
        ObjectOutputStream obj = null;
        try {
            output = new FileOutputStream(filename);
            obj = new ObjectOutputStream(output);
            obj.writeObject(this);
        } catch (IOException e) {
            System.out.println(" Something went wrong while writing !");
        } finally {
            if (obj != null) {
                try {
                    obj.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
    }
    /**
     * HighScoresTable - Read a table from file and return it. If the file does not
     * exist, or there is a problem with reading it, an empty table is returned.
     * @param filename - the name of the file.
     * @return HighScoresTable - the scores table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        FileInputStream input = null;
        ObjectInputStream obj = null;
        HighScoresTable newTable = new HighScoresTable();
        try {
            input = new FileInputStream(filename);
            obj = new ObjectInputStream(input);
            newTable = (HighScoresTable) obj.readObject();
        } catch (IOException e) {
            return newTable;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (obj != null) {
                try {
                    obj.close();
                } catch (IOException e) {
                    return newTable;
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    return newTable;
                }
            }
        }
        return newTable;
    }
    /**
     * updateScoresTable - updates the scores table according to the given input.
     * @param scores1 - the new scores table.
     */
    public void updateScoresTable(List<ScoreInfo> scores1) {
        this.scores = scores1;
    }
}