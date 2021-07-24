package analyzer;
import java.io.*;
import java.util.*;
import utilities.PropertiesLoader;

public class FileAnalysis implements PropertiesLoader {

    public static final int VALID_ARGUMENTS = 2;

    private List<TokenAnalyzer> analyzers;

    /**
     * This method is responsible for verifying correct number of command line
     * arguments and passing them to readInputFile() and writeOutputFiles()
     * @param arguments command line arguments
     */
    public void analyze(String[] arguments){

        if (arguments.length != VALID_ARGUMENTS) {
            System.out.println("You must pass 2 arguments to run this program.");
        } else {
            try {
                String propertiesFilePath = arguments[1];
                Properties properties = loadProperties(propertiesFilePath);
                System.out.println("Properties File Path: " + propertiesFilePath);
                System.out.println("\n|----------------------|----------------------|"
                        + "\n           " + properties.getProperty("file.name")
                        + " FILE\n"
                        + "\nAuthor: " + properties.getProperty("author")
                        + "\nApplication Name: "
                        + properties.getProperty("application.name")
                        + "\nOutput Directory: "
                        + properties.getProperty("output.directory")
                        + "\n|----------------------|----------------------|\n");
                addAnalyzers(properties);
                readInputFile(arguments);
            } catch (Exception exception) {
                System.out.println("Exception caught inside FileAnalysis.analyze");
                exception.printStackTrace();
            }
        }
    }

    /**
     * This method creates instance variable objects for FileSummaryANalyzer
     * and distinctTokensAnalyzer classes
     * @param properties a properties object is passed in as a param
     */
    public void addAnalyzers(Properties properties) {
            analyzers = new ArrayList<TokenAnalyzer>();
            analyzers.add(new FileSummaryAnalyzer(properties));
            analyzers.add(new DistinctTokensAnalyzer(properties));
            analyzers.add(new LargestTokensAnalyzer(properties));
            analyzers.add(new DistinctTokenCountsAnalyzer(properties));
            analyzers.add(new TokenLengthsAnalyzer(properties));
            //analyzers.add(new TokenLocationSearchAnalyzer(properties));
    }

    /**
     * This method calls both methods to create output files for this program.
     * @param inputFile command line arguments
     */
    public void writeOutputFiles(String inputFile) {
        System.out.println("Reached: FileAnalysis.writeOutputFiles"
                + "(String inputFile)");
        for (TokenAnalyzer element : analyzers) {
            element.generateOutputFile(inputFile);
        }
    }


    /**
     * This method takes a string and splits up each individual word, then
     * puts all words into an arrayList
     *
     * source - https://javarevisited.blogspot.com/2016/10/how-to-split-string-in-java-by-whitespace-or-tabs.html#:~:text=Usually%2C%20words%20are%20separated%20by,and%20split%20the%20string%20accordingly.
     * source - https://regexr.com/ -needed a refresher on character classes
     *
     *  @param line line that gets passed from the inputReader
     *  @return String[] of the line being read from the input file
     */
    public String[] parseLineOfTextFile(String line) {
        String[] tempWordList = line.split("\\W");
        return tempWordList;
    }

    /**
     * This method takes the arrayOfWords that was parsed from a single line
     * of the inputFile, loops through it, checks if the array element is empty,
     * then calls loopToProcessTokens() method
     * @param arrayOfWords array of words parsed from a single line of inputFile
     */
    public void processTokens(String[] arrayOfWords) {
        for (String token : arrayOfWords) {
            if (token.isEmpty()) {
                continue;
            } else {
                loopToProcessTokens(token);
            }
        }
    }

    /**
     * This method loops through the analyzers list and calls the processTokens()
     * method on each list element.
     * @param token string to be passed into each method on each element in analyzers List
     */
    public void loopToProcessTokens(String token) {
        for (TokenAnalyzer element : analyzers) {
            element.processToken(token);
        }
    }

    /**
     * This method opens up a BufferedReader, reads the input file line by line
     * converting each line into a String array of words, then calls processing
     * methods that pass this String array on to each analyzer class.
     * @param inputArgs command line arguments
     */
    public void readInputFile(String[] inputArgs) {
        System.out.println("Reached: FileAnalysis.readInputFile"
                + "(String[] inputArgs)");
        String inputFile = inputArgs[0];
        BufferedReader inputReader = null;
        try {
            inputReader = new BufferedReader(new FileReader(inputFile));
            while(inputReader.ready()) {
                String lineOfTextFile = inputReader.readLine();
                String[] arrayOfWords = parseLineOfTextFile(lineOfTextFile);
                processTokens(arrayOfWords);
            }
            writeOutputFiles(inputFile);

        } catch (FileNotFoundException fnfe) {
            System.out.println("No such file exists");
            fnfe.printStackTrace();
        } catch (IOException IOexception) {
            System.out.println("There was a problem reading the file");
            IOexception.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception exception) {
                System.out.println("Something went wrong...");
                exception.printStackTrace();
            }
        }

    }


}
