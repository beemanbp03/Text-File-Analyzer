package analyzer;
import java.io.*;
import java.util.*;

/**
 * This is the main class for Unit 1 Project 1. It instantiates a FileAnalysis
 * class object then calls its main method to analyze input files.
 */
public class Driver {

    public static void main(String[] args) {
        FileAnalysis analyzer = new FileAnalysis();

        analyzer.analyze(args);
    }
}
