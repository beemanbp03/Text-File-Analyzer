package analyzer;
import java.io.*;
import java.util.*;

/**
 * This class counts each distinct word and creates a map to store its key and
 * value, then it outputs each key and value to a file called distinct_counts.txt
 */
public class DistinctTokenCountsAnalyzer implements TokenAnalyzer {

    //Declare instance variable
    private Properties properties;
    private Map<String, Integer> distinctTokenCounts;


    /**
     * This constructor method instantiates a new TreeMap array upon creation.
     *
     */
    public DistinctTokenCountsAnalyzer() {
        distinctTokenCounts = new TreeMap<>();
    }

    /**
     * This constructor sets the instance variable properties to the properties
     * parameter
     * @param properties a properties object is passed through as a param
     */
    public DistinctTokenCountsAnalyzer(Properties properties) {
        this();
        this.properties = properties;
    }

    /**
     * this is a get method for the distinctTokensCount instance variable
     * @return distinctTokenCounts instance variable
     */
    public Map<String, Integer> getDistinctTokenCounts() {
        return distinctTokenCounts;
    }

    /**
     * This method takes in a String of a single word from the line of text
     * being read in FileAnalysis.readInputFile() then adds it to the
     * distinctTokens TreeMap.
     * @param token String word passed from FileAnalysis
     */
    public void processToken(String token) {
        if (distinctTokenCounts.get(token) != null) {
            int mapValue = distinctTokenCounts.get(token);
            distinctTokenCounts.put(token, mapValue + 1);
        } else {
            distinctTokenCounts.put(token, 1);
        }
    }

    /**
     * This method creates a distinct_counts.txt file and displays all contents
     * by taking in the inputFile path, and opens up a PrintWriter object,
     * then writes each element in the distinctTokens set on its own line.
     * @param inputFilePath  a String representing the input file's path
     */
    public void generateOutputFile(String inputFilePath) {
        //System.out.println(inputFilePath);
        //System.out.println(outputFilePath);
        PrintWriter outputWriter = null;
        try {
            outputWriter = new PrintWriter(new BufferedWriter(new
                    FileWriter(properties.getProperty("output.directory")
                            + properties.getProperty("output.file.distinct.counts"))));

            /*System.out.println("Reached: DistinctTokenCountsAnalyzer."
                    + "generateOutputFile(String inputFilePath)");
            */

            //output the distinct tokens to the terminal. Change System.out.
            //println to outputWriter.println() in future
            for (Map.Entry<String, Integer> element : distinctTokenCounts.entrySet()) {
                outputWriter.println(element.getKey() + "\t" + element.getValue());
            }


        } catch (FileNotFoundException fnfe) {
            System.out.println("No file found when executing generateOutputFile");
            fnfe.printStackTrace();
        } catch (IOException ioException) {
            System.out.println("There was a I/O issue with generateOutputFile");
            ioException.printStackTrace();
        } finally {
            try {
                if (outputWriter != null) {
                    outputWriter.close();
                }
            } catch (Exception exception) {
                System.out.println("Something went wrong...");
                exception.printStackTrace();
            }
        }
    }

}
