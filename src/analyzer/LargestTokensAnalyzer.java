package analyzer;

import java.io.*;
import java.util.*;
import utilities.PropertiesLoader;

/**
 * This class only stores unique tokens that are greater than 14 characters
 */
public class LargestTokensAnalyzer implements TokenAnalyzer {

    //Declare instance variable
    private Set<String> largestTokens;
    private Properties properties;
    private int minimumTokenLength;


    /**
     * This constructor method instantiates a new TreeSet array upon creation.
     *
     */
    public LargestTokensAnalyzer() {
        largestTokens = new TreeSet<>();
    }

    /**
     * This constructor method takes in a param and sets the properties
     * instance variable as well as the minimum token length instance variable
     * @param properties Properties object being passed through
     */
    public LargestTokensAnalyzer(Properties properties) {
        this();
        this.properties = properties;
        this.minimumTokenLength = Integer.parseInt
                (properties.getProperty("largest.words.minimum.length"));
    }

    /**
     * This is a set method for the distinctTokens instance variable
     * @return return the distinctTokens Set
     */
    public Set<String> getLargestTokens() {
        return largestTokens;
    }

    //This is a test method to check if we can call methods from this
    //class when it is instantiated inside the FileAnalysis class
    public void testMethod() {
            for (String element : largestTokens) {
                System.out.println(element);
            }
    }

    /**
     * This method takes in a String of a single word, determines if it is over
     * 14 characters, then adds it to the largestTokens TreeSet.
     * @param token String word passed from FileAnalysis
     */
    public void processToken(String token) {
        if (token.length() >= minimumTokenLength) {
            largestTokens.add(token);
        }
    }

    /**
     * This method creates a largest_words.txt file and displays all contents
     * by taking in the inputFile path, opens up a PrintWriter object,
     * then writes each element in the distinctTokens
     * set on its own line.
     * @param inputFilePath  a String representing the input file's path
     */
    public void generateOutputFile(String inputFilePath) {
        System.out.println("Reached: LargestTokensAnalyzer."
                + "generateOutputFile(String inputFilePath)");
        PrintWriter outputWriter = null;
        try {
            //Create the output writer
            outputWriter = new PrintWriter(new BufferedWriter(new
                    FileWriter(properties.getProperty("output.directory")
                            + properties.getProperty("output.file.largest.words"))));


            //output the distinct tokens to the terminal. Change System.out.
            //println to outputWriter.println() in future
            for (String element : largestTokens) {
                outputWriter.println(element);
            }
            //System.out.println("There are " + largestTokens.size()
                    //+ " tokens larger than 14 characters.");

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
