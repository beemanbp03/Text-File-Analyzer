package analyzer;


/**
 * This is an interface for both analyzer classes. It provides a blueprint
 * for methods that are common between both classes.
 */
public interface TokenAnalyzer {

    /**
     * This method is an interface method for processing a token
     * @param token the token that will be passed through to this method
     */
    void processToken(String token);

    /**
     * This method is an interface method for generating an output file
     * @param inputFilePath the file path of the input file
     */
    void generateOutputFile(String inputFilePath);
}
