1. went back and got my sources for my academic integrity statement (included in analyzer folder)
2. consolidated both looping methods in FileAnalysis.readInputFile(String inputFile) to processTokens(String[] arrayOfWords) in order to improve efficiency
3. inside FileSummaryAnalyzer.createOutputFile(String inputFile, String outputFile) I replaced .toGMTString() with .toString(), which removed warnings at compile
4. Added System.out.println() to all catches
