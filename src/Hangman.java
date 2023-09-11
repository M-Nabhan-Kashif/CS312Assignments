/**
 * CS312 Assignment 6
 *
 * On my honor, Mohammad Nabhan Kashif, this programming assignment is my own work
 * and I have not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 * Email address: mohammadnkashif@utexas.edu
 * UTEID: mnk665
 * Unique 5 digit course ID: 52495
 * Number of slip days used on this assignment: 0
 * 
 * Estimated hours for this assignment:
 * Actual hours for this assignment: 
 */

import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {

        // Load phrases from a file into the phrases variable
        PhraseBank phrases = buildPhraseBank(args);
        //Scanner created to read user input
        Scanner keyboard = new Scanner(System.in);
        // Boolean created that will run the game if true
        boolean keepPlaying = true;

        // Print the intro to the program.
        intro();
        System.out.println();

        // While loop ensures game continues running while keepPlaying bool is true.
        // While loop contains runGame method, which runs 1 complete hangman game.
        while (keepPlaying == true) {
            runGame(keyboard, phrases);
            // User is asked if they want to play another game.
            System.out.println("Do you want to play again?");
            System.out.print("Enter 'Y' or 'y' to play again: ");
            // User input is read in and capitalized to help with if statement.
            char decision = keyboard.next().toUpperCase().charAt(0);
            // keepPlaying boolean value is updated based on user response.
            if (decision == 'Y') keepPlaying = true;
            else keepPlaying = false;
            System.out.println();
        }

        keyboard.close();
    }

    private static void runGame(Scanner keyboard,PhraseBank phrases) {
        // Topic and phrase strings created.
        // PhraseBank from main function used to get strings.
        String topic = phrases.getTopic();
        String phrase = phrases.getNextPhrase();
        // String created to store letter options that haven't been entered by user.
        String letterChoice = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // Topic from PhraseBank is printed out to user
        System.out.println("I am thinking of a " + topic + " ...\n");

        // Method used to create duplicate phrase that will be printed to user and used to
        // slowly unveil the phrase as the user successfully guesses letters.
        String revealingPhrase = createRevealingPhrase(phrase, letterChoice);

        // Method containing while loop that inputs user guesses until
        // either 5 wrong guesses or the word is successfully guessed
        userGuesses(keyboard, phrase, letterChoice, revealingPhrase);
    }

    private static String createRevealingPhrase(String phrase, String letterChoice) {
        // Phrase string is duplicated
        String revealingPhrase = phrase;
        // Letters replaced with * and spaces replaced with _ using a for loop
        for (int i = 0; i < phrase.length(); i++) {
            // nextLetter variable defined as next character in each loop using i
            char nextLetter = revealingPhrase.charAt(i);
            // If statement replaces all letters with *
            if (letterChoice.contains("" + nextLetter))
                revealingPhrase = revealingPhrase.replace(nextLetter, '*');
        }
        // Returns masked version of the selected phrase to runGame method.
        return revealingPhrase;
    }

    private static void userGuesses(Scanner keyboard, String phrase,
                                    String letterChoice, String revealingPhrase) {
        // Variable tracking number of wrong guesses initiated to 0
        int numFails = 0;
        // Boolean created to track whether input is valid or not
        Boolean valid = true;

        // While statement will continue to ask user to guess letters until
        // either 5 wrong guesses or the word is completed.
        while (numFails < 5 && !phrase.equals(revealingPhrase)) {
            // intro method prints current phrase and remaining letters to user
            intro(letterChoice, revealingPhrase, valid);
            // User's input received through getGuess function
            String completeGuess = getGuess(keyboard);
            // First letter extracted from user input as accepted guess
            char guess = completeGuess.toUpperCase().charAt(0);
            // User's guess is converted to a string value for ease of testing
            String strGuess = "" + guess;

            // If statement ensures game only continues if the guess is from letter options
            if (letterChoice.contains(strGuess)) {
                // User's guess is printed
                System.out.println("You guessed " + strGuess + ".\n");
                // Input verified as valid
                valid = true;

                // If the guessed letter is in the phrase, user receives message
                // and revealing phrase is updated to show the letter.
                if (phrase.contains(strGuess)) {
                    System.out.println("That is present in the secret phrase.\n");
                    revealingPhrase = updateRevealingPhrase
                            (phrase, revealingPhrase, guess, strGuess);
                }
                // If guessed letter isn't present, message is printed to user
                // and numFails is increased to track wrong guesses.
                else {
                    numFails++;
                    System.out.println("That is not present in the secret phrase.\n");
                }

                // The letter guessed by user is removed from letterChoice
                letterChoice = updateLetterChoice(letterChoice, strGuess);
                // Wrong guess count printed to user
                System.out.println("Number of wrong guesses so far: " + numFails);
            }
            // If user input doesn't start with a valid letter,
            // input is returned to user with following message
            else {
                System.out.println(completeGuess + " is not a valid guess.");
                valid = false;
            }
        }
        // Method called to reveal phrase and state if user won or lost.
        endResult(phrase, numFails);

    }

    private static void intro(String letterChoice, String revealingPhrase, Boolean valid) {
        // Updated revealing phrase is printed to user
        if (valid) {
            System.out.println("The current phrase is " + revealingPhrase + "\n");
        }
        // Remaining letter choices printed to user
        System.out.println("The letters you have not guessed yet are: ");
        // First letter individually printed
        System.out.print(letterChoice.charAt(0));
        // For loop prints letters with -- between each letter
        for (int i = 1; i < letterChoice.length(); i++) {
            System.out.print("--");
            System.out.print(letterChoice.charAt(i));
        }
    }

    private static String getGuess(Scanner keyboard) {
        System.out.println();
        // User prompted to enter their guess
        System.out.print("\nEnter your next guess: ");
        // User guess accepted as a String
        String completeGuess = keyboard.next();
        // Second input function accounts for any remaining text in user input
        // Added step due to debugging issues with running 2nd round of game
        String remainingGuess = keyboard.nextLine();
        completeGuess = completeGuess + remainingGuess;
        System.out.println();
        // guess is returned to userGuesses function
        return completeGuess;
    }
    private static String updateRevealingPhrase(String phrase, String revealingPhrase,
                                                char guess, String strGuess) {
        // For loop runs through every letter in the phrase
        for (int i = 0; i < phrase.length(); i++) {
            // Each letter defined to letter variable
            char letter = phrase.charAt(i);
            // Letter converted to string
            String strLetter = "" + letter;
            // If statement adds letter to the revealing phrase only if
            // the letter from the phrase matches the user's guess.
            if (letter == guess) {
                revealingPhrase = revealingPhrase.substring(0, i) + strGuess
                        + revealingPhrase.substring(i + 1);
            }
        }

        return revealingPhrase;
    }

    private static String updateLetterChoice(String letterChoice, String strGuess) {
        // letterChoice is returned after finding position of user's correct guess
        // and removing that letter from the string
        int letterChoicePosition = letterChoice.indexOf(strGuess);
        letterChoice = letterChoice.substring(0, letterChoicePosition)
                + letterChoice.substring(letterChoicePosition + 1, letterChoice.length());
        return letterChoice;
    }

    private static void endResult(String phrase, int numFails) {
        // If user hasn't failed 5 times when game completes, win message is printed
        if (numFails < 5) {
            System.out.println("The phrase is " + phrase + ".");
            System.out.println("You win!!");
        }
        // If user has 5 failed attempts, loss message is printed.
        else System.out.println("You lose. The secret phrase was " + phrase);
    }

    // Build the PhraseBank.
    // === When you run this program, there won't be any String[] args sent to main,
    // so this line - result =  new PhraseBank(); - will create a bank of phrases
    // that 1) uses the default file "HangmanMovies.txt" and 2) uses fixed responses
    // that allow the test script to work. 
    // === When the test script runs this program, it will call the main method 
    // multiple times, with different arguments for different input filenames. 
    // === In both of the previous cases, the PhraseBank constructor with only one 
    // argument is used,so the responses from the PhraseBank won't be random, again, 
    // so the test script will work.
    public static PhraseBank buildPhraseBank(String[] args) {
        PhraseBank result;
        if (args == null || args.length == 0
                        || args[0] == null || args[0].length() == 0) {
            result =  new PhraseBank();
            /* CS312 students, uncomment the following line if you
             * would like less predictable behavior from the PhraseBank
             * during testing. NOTE, this line MUST be commented out
             * in the version of the program you turn in
             * or your will fail all tests.
             */
            // result = new PhraseBank("HangmanMovies.txt", true);
            // MUST be commented out in version you submit.
        } else {
            result = new PhraseBank(args[0]);
        }

        return result;
    }


    // Print the intro to the program.
    public static void intro() {
        System.out.println("This program plays the game of hangman.");
        System.out.println();
        System.out.println("The computer will pick a random phrase.");
        System.out.println("Enter letters for your guess.");
        System.out.println("After 5 wrong guesses you lose.");
    }
}
