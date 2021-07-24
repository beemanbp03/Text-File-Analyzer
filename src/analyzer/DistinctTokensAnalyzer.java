package analyzer;
import java.io.*;
import java.util.*;

/**
 * This class handles all processing and file output for unique words found
 * inside an input file. The output will display each element in a set on its
 * own line.
 */
public class DistinctTokensAnalyzer implements TokenAnalyzer {

    //Declare instance variable
    private Set<String> distinctTokens;
    private Properties properties;

    /**
     * This is a set method for the distinctTokens instance variable
     * @return distinctTokens Set instance variable
     */
    public Set<String> getDistinctTokens() {
        return distinctTokens;
    }

    /**
     * This constructor method instantiates a new TreeSet array upon creation.
     *
     */
    public DistinctTokensAnalyzer() {
        distinctTokens = new TreeSet<>();
    }

    public DistinctTokensAnalyzer(Properties properties) {
        this();
        this.properties = properties;
    }

    //This is a test method to check if we can call methods from this
    //class when it is instantiated inside the FileAnalysis class
    public void testMethod() {
            for (String element : distinctTokens) {
                System.out.println(element);
            }
    }

    /**
     * This method takes in a String of a single word from the line of text
     * being read in FileAnalysis.readInputFile() then adds it to the
     * distinctTokens set.
     * @param token String word passed from FileAnalysis
     */
    public void processToken(String token) {
            distinctTokens.add(token);
    }

    /**
     * This method creates a distinct_tokens.txt file and displays all contents
     * by taking in the inputFile path, and the desired outputFile path, opens
     * up a PrintWriter object, then writes each element in the distinctTokens
     * set on its own line.
     * @param inputFilePath  a String representing the input file's path
     */
    public void generateOutputFile(String inputFilePath) {
        //System.out.println(inputFilePath);
        //System.out.println(outputFilePath);
        PrintWriter outputWriter = null;
        try {
            outputWriter = new PrintWriter(new BufferedWriter(new
                    FileWriter(properties.getProperty("output.directory")
                            + properties.getProperty("output.file.distinct"))));
            System.out.println("Reached: DistinctTokensAnalyzer."
                    + "generateOutputFile(String inputFilePath)");
            //output the distinct tokens to the terminal. Change System.out.
            //println to outputWriter.println() in future
            //outputWriter.println("|----------------------|");
            //outputWriter.println("     Distinct Tokens\n  ");
            for (String element : distinctTokens) {
                outputWriter.println(element);
            }
            //outputWriter.println("|----------------------|\n");

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
