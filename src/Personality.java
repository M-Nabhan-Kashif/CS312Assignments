/**
 * CS312 Assignment 7 - Personality Quiz
 * 
 * On my honor, Mohammad Nabhan Kashif, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to read a file with raw data from a Keirsey personality test
 * and print out the results.
 *
 *  email address: mohammadnkashif@utexas.edu
 *  UTEID: mnk665
 *  TA name: Yundi Li
 *  Number of slip days used on this assignment: 1
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Personality {
    // The main method to process the data from the personality tests
    public static void main(String[] args) throws FileNotFoundException {
        // do not make any other Scanners connected to System.in
        Scanner keyboard = new Scanner(System.in);
        Scanner fileScanner = getFileScanner(keyboard);
        // String created to read in first line, which holds an invaluable number.
        String uselessNum = fileScanner.nextLine();
        // While loop used to repeat analyzing process for each line of data.
        while (fileScanner.hasNextLine()) {
            // Personality test for one user run using runTest method.
            runTest(fileScanner);
        }
        fileScanner.close();
        keyboard.close();
    }

    // Method to run personality test analysis using data of one user at a time.
    private static void runTest(Scanner fileScanner) {
        // Name of user read into string variable.
        String name = fileScanner.nextLine();
        // Answers of user read into separate string variable,
        String answers = fileScanner.nextLine();

        // findAnswer function used to create arrays containing user answers to each question
        // category. Separate arrays created for A and B answers.
        int[] aAnswers = findAnswer(answers, 'A');
        int[] bAnswers = findAnswer(answers, 'B');
        // findPercent function used to find percentage of B answers for each question category
        // and store percentages in an array.
        int[] percentValues = findPercent(aAnswers, bAnswers);
        // findResult function used to identify 4 letters for personality type based on
        // percentages of each question category.
        String[] result = findResult(percentValues);
        // Percentage and personality type results printed to user using printResult method.
        printResult(name, percentValues, result);
    }

    // Method to count A and B responses for each question category
    private static int[] findAnswer(String answers, char letterTest) {
        // 4 number array created
        int[] answersFound = new int [4];
        // For loop used to iterate through every letter in user answers.
        for (int i = 0; i < answers.length(); i++) {
            // Current Letter separated and capitalized for test case.
            char currentAnswer = answers.toUpperCase().charAt(i);
            // If statement used to match current Letter with the test letter (A or B).
            if (currentAnswer == letterTest) {
                // Question categories identified using modulus 7, trend found in question sequence
                if (i % 7 == 0) answersFound[0]++;
                else if (i % 7 == 1 || i % 7 == 2) answersFound[1]++;
                else if (i % 7 == 3 || i % 7 == 4) answersFound[2]++;
                else if (i % 7 == 5 || i % 7 == 6) answersFound[3]++;
            }
        }
        // Array returned to runTest method..
        return answersFound;
    }

    // Method created to find percentages of B answers for each of 4 categories.
    private static int[] findPercent(int[] aAnswers, int[] bAnswers) {
        // New 4 number array created
        int[] percents = new int [4];
        // For loop used to find percentage of each question category and store in new array.
        for (int i = 0; i < 4; i++) {
            double total = (double)(aAnswers[i] + bAnswers[i]);
            double doubleValue = ((double)bAnswers[i] / total) * 100.0;
            percents[i] = (int)(Math.round(doubleValue));
        }
        // Array returned to runTest method.
        return percents;
    }

    // Method used to identify letters based on percentage results of each question category.
    private static String[] findResult(int[] percentValues) {
        // String array created to store/return 4 letters.
        String[] result = new String [4];
        // For loop used to iterate through each question category.
        for (int i = 0; i < 4; i++) {
            // Outer if statement tests to see whether B is in majority or minority.
            if (percentValues[i] > 50) {
                // Inner if statements assign appropriate letter based on question category.
                if (i == 0) result[0] = "I";
                else if (i == 1) result[1] = "N";
                else if (i == 2) result[2] = "F";
                else if (i == 3) result[3] = "P";
            }
            else if (percentValues[i] < 50 && percentValues[i] > 0) {
                // Inner if statements assign appropriate letter based on question category.
                if (i == 0) result[0] = "E";
                else if (i == 1) result[1] = "S";
                else if (i == 2) result[2] = "T";
                else if (i == 3) result[3] = "J";
            }
            // Statement to address possibility of perfect balance in responses
            else if (percentValues[i] == 50) result[i] = "X";
            // Statement to address possibility of user skipping all questions.
            else if (percentValues[i] == 0) result[i] = "-";
        }
        // result Array returned to runTest method.
        return result;
    }

    // Method used to print results to user. printf statements used to format output.
    private static void printResult(String name, int[] percentValues, String[] result) {
        System.out.printf("%30s", name);
        System.out.print(":");
        for (int n: percentValues) {
            if (n != 0) System.out.printf("%11s", n);
            else System.out.printf("%11s", "NO ANSWERS");
        }
        System.out.printf("%2s", " = ");
        for (String n: result) System.out.printf(n);
        System.out.print("\n");
    }
    
    // Method to choose a file.
    // Asks user for name of file. 
    // If file not found create a Scanner hooked up to a dummy set of data
    // Example use: 
    public static Scanner getFileScanner(Scanner keyboard){
        Scanner result = null;
        try {
            System.out.print("Enter the name of the file with"
                    + " the personality data: ");
            String fileName = keyboard.nextLine().trim();
            System.out.println();
            result = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("Problem creating Scanner: " + e);
            System.out.println("Creating Scanner hooked up to default data "
                    + e);
            String defaultData = "1\nDEFAULT DATA\n"
                + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
            result = new Scanner(defaultData);
        }
        return result;
    }
}
