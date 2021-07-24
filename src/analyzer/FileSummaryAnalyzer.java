package analyzer;
import java.io.*;
import java.util.*;

/**
 * This class handles all processing and file output for a given input file.
 * The output file for this class will display a summary about the input file.
 */
public class FileSummaryAnalyzer implements TokenAnalyzer {

    private int totalTokensCount;
    private Properties properties;


    /**
     * This constructor method sets a value to the declared instance variable
     * totalTokensCount.
     *
     */
    public FileSummaryAnalyzer() {
        totalTokensCount = 0;
    }

    /**
     * Constructor with one Properties param
     * @param properties Properties object that is being passed through
     */
    public FileSummaryAnalyzer(Properties properties) {
        this();
        this.properties = properties;
    }

    /**
     * This method GETS the totalTokensCount instance variable by returning it
     * @return the value of instance variable totalTokensCount
     */
    public int getTotalTokensCount() {
        return totalTokensCount;
    }

    /**
     * This is a test method to ensure methods can be called in a given scope
     * @return String
     */
    public String testMethod() {
        return "This method inside " + this + " was successfully called.";
    }

    /**
     * This method takes a single word from the line that is being read in
     * FileAnalysis.readInputFile(), and if it is not null,
     * it adds 1 to the totalTokensCount instance variable
     * @param tokenArrayElement single String word passed from FileAnalysis
     */
    public void processToken(String tokenArrayElement) {
        if (tokenArrayElement != null) {
            this.totalTokensCount += 1;
        }
    }

    /**
     * This method creates a summary.txt file and displays the file's attributes
     * by taking in the inputFile path, and the desired outputFile path, opens
     * up a PrintWriter object, creates File and Date objects,
     * source - [went on a search to figure out how to output the date in proper format, led me to the Date JDK webpage] https://docs.oracle.com/javase/8/docs/api/java/sql/Date.html
     * source - [this is where I figured out how to get file attributres when using java.io.* package] https://stackoverflow.com/questions/10824027/get-the-metadata-of-a-file
     * source - [once I knew File could get file attributes, I went to the JDK] https://docs.oracle.com/javase/8/docs/api/java/io/File.html
     * source - https://stackoverflow.com/questions/5525854/how-to-convert-the-file-last-modified-timestamp-to-a-date
     * @param inputFilePath  a String representing the input file's path
     */
    public void generateOutputFile(String inputFilePath) {

        PrintWriter outputWriter = null;
        File inputFile = new File(inputFilePath);
        Date dateAnalysis = new Date();
        Date dateModified = new Date(inputFile.lastModified());

        try {
            outputWriter = new PrintWriter(new BufferedWriter(new
                    FileWriter(properties.getProperty("output.directory")
                            + properties.getProperty("output.file.summary"))));
            System.out.println("Reached: FileSummaryAnalyzer."
                    + "generateOutputFile(String inputFilePath)");

            outputWriter.println("|----------------------|"
                    + "----------------------|----------------------|");
            outputWriter.println("\t\t\t\t\t\t\t File Summary\n");
            outputWriter.println("Application: "
                    + properties.getProperty("application.name")
                    + "\nAuthor: " + properties.getProperty("author")
                    + "\nAuthor email: " + properties.
                            getProperty("author.email.address")
                    + "\nFile: " + inputFile.getAbsolutePath()
                    + "\nDate of Analysis: " + dateAnalysis.toString()
                    + "\nLast Modified: " + dateModified.toString()
                    + "\nFile Size: " + inputFile.length()
                    + "\nFile URI: " + inputFile.toURI().toString()
                    + "\nTotal Tokens: " + totalTokensCount);
            outputWriter.println("\n|----------------------|"
                    + "----------------------|----------------------|\n");

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
