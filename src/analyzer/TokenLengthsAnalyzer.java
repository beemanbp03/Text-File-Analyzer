package analyzer;

import java.util.*;
import java.io.*;
import utilities.PropertiesLoader;

/**
 * This class determines the length distribution of each token in the input file
 * and displays the distribution in two formats
 */

public class TokenLengthsAnalyzer implements TokenAnalyzer{

    //Declare instance variables
    private Map<Integer, Integer> tokenLength; //key=lengthOfToken | value=numberOfToken
    private Properties properties;

    /**
     * Simple class constructor
     */
    public TokenLengthsAnalyzer() {
        tokenLength = new TreeMap<>();
    }

    /**
     * class constructor that takes a properties file as a param and sets
     * it to the instance properties field
     * @param properties properties file passed to the constructor
     */
    public TokenLengthsAnalyzer(Properties properties) {
        this();
        this.properties = properties;
    }

    /**
     * GET method for tokenLength instance variable
     * @return length of token
     */
    public Map<Integer, Integer> getTokenLengths() {
        return tokenLength;
    }

    /**
     * This method is an interface method for processing a token
     * @param token the token that will be passed through to this method
     */
    public void processToken(String token) {
        //https://docs.oracle.com/javase/8/docs/api/java/util/Map.html - Map docs
        if (tokenLength.get(token.length()) != null) {
            Integer mapValue = tokenLength.get(token.length());
            tokenLength.put(token.length(), mapValue + 1);
        } else {
            tokenLength.put(token.length(), 1);
        }
    }


    public String outputHistogramStars(int stars) {
        String starOutput = "*";
        for (int i=0; i < stars; i++) {
            starOutput += "*";
        }
        return starOutput;
    }
    /**
     * This method is an interface method for generating an output file
     * @param inputFilePath the file path of the input file
     */
    public void generateOutputFile(String inputFilePath) {
        //System.out.println("Reached: TokenLengthAnalyzer."
        //        + "generateOutputFile(String inputFilePath)");
        PrintWriter outputWriter = null;
        try {
            //Create the output writer
            outputWriter = new PrintWriter(new BufferedWriter(new
                    FileWriter(properties.getProperty("output.directory")
                            + properties.getProperty("output.file.token.lengths"))));


            //https://www.geeksforgeeks.org/iterate-map-java/ - how to iterate
            for (Map.Entry<Integer, Integer> entry : tokenLength.entrySet()) {
                outputWriter.println(entry.getKey()
                        + "\t" + entry.getValue());
            }

            //output file formatting
            outputWriter.println("\n");

            //Set up Histogram range
            int lineMax = 75;
            int mapMax = 0;
            for (Map.Entry<Integer, Integer> entry : tokenLength.entrySet()) {
                if (entry.getValue() > mapMax) {
                    mapMax = entry.getValue();
                }
            }
            //output histogram
            for (Map.Entry<Integer, Integer> entry : tokenLength.entrySet()) {
                int mapValue = entry.getValue();
                double valueDistPercentage = Double.valueOf(mapValue) / mapMax;
                double starDistDouble = lineMax * valueDistPercentage;
                int numberOfStars = (int)starDistDouble;
                outputWriter.println(entry.getKey() + ":\t"
                        + outputHistogramStars(numberOfStars));
            }


            /*System.out.println("Reached: try/catch of TokenLengthAnalyzer."
                    + "generateOutputFile(String inputFilePath)");*/

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
