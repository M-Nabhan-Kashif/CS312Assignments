import java.util.Scanner;
import java.io.*;

public class FleschIndex {

    public static final String SENTENCE_DELIMITERS = "!:;?.";
    public static final String WORD_DELIMITERS = "\t\n .;:!?";
    public static final String SYLLABLE_DELIMETERS = "aeio";

    public static void main(String[] args) throws FileNotFoundException {
        //"FleschInpu.txt" replaced with "FleschInput.txt"
        Scanner inputFile = new Scanner(new File("FleschInput.txt"));
        int sampleCount = 1;
        int lineCount = 0;
        String sample = "", author = "";
        int numSentences = 0, numWords = 0, numSyllables = 0;
        double index = 0.0;
        System.out.println("*** Flesch Reading Ease Test ***\n");
        while (inputFile.hasNext()) {
            //read passage info from file
            lineCount = Integer.parseInt(inputFile.nextLine());
            sample = getSample (inputFile, lineCount);
            author = inputFile.nextLine();
            // get counts needed for the index
            numSentences = getSentenceCount(sample);
            numWords = getWordCount(sample);
            numSyllables = getSyllableCount(sample);
            index = getIndex (numSentences, numWords, numSyllables);
            printResults (sampleCount, lineCount, sample, author, numSentences, numSyllables, numWords, index);
            sampleCount++;
        }
        inputFile.close();
    }

    // Pulls the reading sample of a given number of lines from the file
    // and creates one string with each line separated by a newline.
    public static String getSample(Scanner inputFile, int lineCount) {
        String sample = "";
        for (int i = 1; i < lineCount; i++) {
            sample += inputFile.nextLine();
        }
        // Semicolon was missing for return function
        return sample;
    }

    // Counts the number of sentence occurrences in the text input.
    // Length of text controls the amount of iterations.
    // For loop looks for needed character at each iteration.
    // Counter will increment if condition is met.
    public static int getSentenceCount(String sampleText) {
        int sentenceCount = 0;
        int sampleLength = sampleText.length();
        for (int i = 0; i < sampleLength; i++) {
            char currentChar = sampleText.charAt(i);
            // indexOfcurrentChar should be replaced with indexOf(currentChar), parantheses issue
            if (SENTENCE_DELIMITERS.indexOf(currentChar) != -1) {
                sentenceCount++;
            }
        }
        return sentenceCount;
    }

    // Method counts the number of words in the text input.
    // Length of text control amount of iterations.
    // For loop compares current and previous character based on delimiters and the
    // conditional.
    // Counter will increment if condition is met.
    public static int getWordCount(String sampleText) {
        int wordCount = 0;
        sampleText = " " + sampleText; // add leading space to simplify loop
        for (int i = 0; i < sampleText.length(); i++) {
            char previousChar = sampleText.charAt(i);
            char currentChar = sampleText.charAt(i + 1);
            if (WORD_DELIMITERS.indexOf(currentChar) != -1 && WORD_DELIMITERS.indexOf(previousChar) > 0) {
                wordCount++;
            }
        }
        return wordCount;
    }


    // Method counts the number of syllables based on the text input.
    // Length of text controls the amount of iterations.
    // For loop will do similar comparison to wordCounter method, but has delimiters
    // that must meet two different conditions.
    public static int getSyllableCount(String sampleText) {
        int syllableCount = 0;
        // add leading space to simplify loop and make lowercase
        sampleText = " " + sampleText.toLowerCase();
        for (int i = 0; i < sampleText.length()-1; i++) {
            char previousChar = sampleText.charAt(i);
            char currentChar = sampleText.charAt(i +1);
            if (SYLLABLE_DELIMETERS.indexOf(currentChar) == -1
                    && SYLLABLE_DELIMETERS.indexOf(previousChar) != -1) {
                syllableCount++;
            }
        }
        return syllableCount;
    }

    // Method uses all of the analyzed text mehods to create a readability score.
    // All of the numbers must be casted as integers before being used in the
    // equation.
    // Must be rounded to the nearest tenth, will occur in printf statement.
    public static double getIndex(int sentenceCount, int wordCount, int syllableCount) {
        if (sentenceCount == 0 || wordCount == 0) return 0.0;
        double index = 206.835 - (1.015 * (wordCount / sentenceCount))
                - (84.6 * (syllableCount / wordCount));
        return index;
    }


    // Print passage, author, statistics and index to the console.
    public static void printResults (int sampleCount, int sampleNum, String sample, String author,
                                     int numSentences, int numWords, int numSyllables, double index) {
        System.out.println ("====== Sample #" + sampleCount);
        System.out.println ("Sample \n" + sample);
        System.out.println ("Author/Source\n " + author);
        System.out.println ("\nStatistics");
        System.out.println ("  Sentences: " + numSentences);
        System.out.println ("  Words: " + numWords);
        System.out.println ("  Syllables: " + numSyllables);
        System.out.printf ("  Index: %.1f\n\n", index);
    }
}