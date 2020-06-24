package levels;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * class BlocksDefinitionReader.
 * @author Michael Ternovsky 316534809
 */
public class BlocksDefinitionReader {
    /**
     * fromReader - the function reads the file using the reader and return a block
     * symbol factory.
     * @param reader - the reader.
     * @return BlocksFromSymbolsFactory - block symbol factory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        BufferedReader br = new BufferedReader(reader);
        List<String> blocksInfoList = new ArrayList<String>();
        String currentLevel = "";
        try {
            // open file connection
            String currentLine = br.readLine();
            while (currentLine != null) {
                if (!currentLine.startsWith("#") && !currentLine.equals("")) {
                    currentLevel += currentLine;
                    // add the level info the the list of blocks info
                    blocksInfoList.add(currentLevel);
                    currentLevel = "";
                }
                currentLine = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("There was a problem finding the file");
        } catch (IOException e) {
            System.out.println("There was a problem reading the file");
        }
        // creating the factory object and return it
        BlocksFromSymbolsFactory newFactory = new BlocksFromSymbolsFactory(blocksInfoList);
        return newFactory;
    }
}