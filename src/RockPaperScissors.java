import java.util.Scanner;

/**
 *  CS312 Assignment 4.
 *
 *  On my honor, Mohammad N Kashif, this programming assignment is my own work and I have
 *  not shared my solution with any other student in the class.
 *
 *  A program to play Rock Paper Scissors
 *
 *  Name: Mohammad Nabhan Kashif
 *  email address: mohammadnkashif@utexas.edu
 *  UTEID: mnk665
 *  Section 5 digit ID: 52495
 *  Grader name:
 *  Number of slip days used on this assignment: 0
 */

public class RockPaperScissors {

    //Three global constants created to define integer values to each option in the game
    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;

    /* A program to allow a human player to play rock - paper - scissors
     * against the computer. If args.length != 0 then we assume
     * the first element of args can be converted to an int
     */
    public static void main(String[] args) {
        // CS312 Students. Do not change the following line.
        RandomPlayer computerPlayer = buildRandomPlayer(args);

        // CS312 students do no change the following line. 
        // Do not create any other Scanners.
        Scanner keyboard = new Scanner(System.in);

        String name = getName(keyboard);
        // A method that asks for the player's name and stores it as a string.
        int rounds = numRounds(keyboard, name);
        // A method that asks the player how many rounds should be played and stores integer value.
        roundReps(keyboard, name, rounds, computerPlayer);
        // A final method that plays the game in a loop based on the desired number of rounds
        // and displays the final results of played games at the end. Multiple helper methods used.

        keyboard.close();
    }

    // Method defined to input and return user name
    private static String getName(Scanner keyboard) {
        System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
        System.out.print("Please type in your name and press return: ");
        // User is prompted to type in their name.
        String name = keyboard.next();
        // Name is received as a string in Java.
        System.out.println();
        System.out.print("Welcome " + name + ".");
        // Welcome message printed using the name.
        System.out.println();
        System.out.println();
        return name;
        // User name returned to main function
    }

    // Method defined to input and return desired number of rounds
    private static int numRounds(Scanner keyboard, String name) {
        System.out.println("All right " + name + ". How many rounds would you like to play?");
        System.out.print("Enter the number of rounds you want to play and press return: ");
        // User prompted to enter how many rounds they want to play.
        int rounds = keyboard.nextInt();
        // Number of rounds is received as an integer in Java.
        System.out.println("");

        return rounds;
        //Number of rounds returned to main function
    }

    // Method runs loop with external method for each round and tracks wins/losses/draws
    private static void roundReps(Scanner keyboard, String name, int rounds, RandomPlayer pcPlayer) {
        int numWins = 0;
        int numLosses = 0;
        int numDraws = 0;
        // Variables defined to keep track of wins, losses, and draws.

        // Loop runs the game based on desired number of rounds.
        for (int i=1; i <= rounds; i ++) {
            int roundStatus = getRoundStatus(keyboard, name, pcPlayer, i);
            // Result of each round is formed through helper method, stored as an integer
            if (roundStatus == 1) numWins++;
            else if (roundStatus == 2) numLosses++;
            else if (roundStatus == 3) numDraws++;
            // roundStatus value helps determine total count of wins, losses, and draws using if statements
            System.out.println();
        }
        printResults(name, numWins, numLosses, numDraws);
        // Helper method used to output results after all rounds are finished using results tracked in the for loop.
    }

    // Method that runs 1 single round, including user input and computer choice,
    // and returns the result from an external method as an integer
    private static int getRoundStatus(Scanner keyboard, String name, RandomPlayer pcPlayer, int i) {
        System.out.println("Round " + i + ".");
        //Round number displayed, tracked using increasing i value for the loop.
        System.out.println(name + ", please enter your choice for this round.");
        System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
        // User prompted for their choice in the game.
        int userPick = keyboard.nextInt();
        // User choice stored as integer value
        int pcPick = pcPlayer.getComputerChoice();
        // PC choice comes from getComputerChoice function, stored as integer.
        printInput(name, userPick, pcPick);
        //Choice of User and Pc printed using helper method
        System.out.println();
        int roundStatus = runRound(keyboard, name, userPick, pcPick);
        // runRound function called to print results and return int value to help track results
        return roundStatus;
    }

    // Method that uses the user input and computer choice to print results for given round and return results
    // 1 for Computer win, 2 for computer loss, 3 for draw
    private static int runRound(Scanner keyboard, String name, int userPick, int pcPick) {
        int returnValue = 0;
        // Return value initiated

        //Each if/ else if statement prints the results of the game and modifies the return value.
        // The return value is used to return whether the game was a win, loss, or draw.
        if (userPick == pcPick) {
            System.out.println("We picked the same thing! This round is a draw.");
            returnValue = 3;
        }
        else if (userPick == ROCK && pcPick == PAPER) {
            System.out.println("PAPER covers ROCK. I win.");
            returnValue = 1;
        }
        else if (userPick == ROCK && pcPick == SCISSORS) {
            System.out.println("ROCK breaks SCISSORS. You win.");
            returnValue = 2;
        }
        else if (userPick == PAPER && pcPick == ROCK) {
            System.out.println("PAPER covers ROCK. You win.");
            returnValue = 2;
        }
        else if (userPick == PAPER && pcPick == SCISSORS) {
            System.out.println("SCISSORS cut PAPER. I win.");
            returnValue = 1;
        }
        else if (userPick == SCISSORS && pcPick == ROCK) {
            System.out.println("ROCK breaks SCISSORS. I win.");
            returnValue = 1;
        }
        else if (userPick == SCISSORS && pcPick == PAPER) {
            System.out.println("SCISSORS cut PAPER. You win.");
            returnValue = 2;
        }
        return returnValue;
    }

    private static void printInput (String name, int userInput, int pcInput) {
        // Method prints a line that tells the user their selection as well as the computer's selection
        // Integer values for rock paper or scissors are converted to string for both pc pick and user pick
        String user = "text";
        String pc = "text";
        if (userInput == ROCK) user = "ROCK";
        else if (userInput == PAPER) user = "PAPER";
        else if (userInput == SCISSORS) user = "SCISSORS";

        if (pcInput == ROCK) pc = "ROCK";
        else if (pcInput == PAPER) pc = "PAPER";
        else if (pcInput == SCISSORS) pc = "SCISSORS";

        System.out.println("Computer picked " + pc + ", " + name + " picked " + user + ".");
    }

    private static void printResults (String name, int numWins, int numLosses, int numDraws) {
        int numGames = numWins + numLosses + numDraws;
        // Total number of games calculated
        System.out.println();
        System.out.println("Number of games of ROCK PAPER SCISSORS: " + numGames);
        System.out.println("Number of times Computer won: " + numWins);
        System.out.println("Number of times " + name + " won: " + numLosses);
        System.out.println("Number of draws: " + numDraws);
        // Values that were tracked in roundReps method are printed for user
        if (numWins > numLosses) {
            System.out.println("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
        }
        else if (numLosses > numWins) {
            System.out.println("You, " + name + ", are a master at ROCK, PAPER, SCISSORS.");
        }
        else {
            System.out.println("We are evenly matched.");
        }
        // Based on who won more games, either the computer or user is titled master
    }

    /*
     * Build the random player. If args is length 0 then the
     * default RandomPlayer is built that follows a predictable
     * sequence. If args.length > 0 then we assume we can
     * convert the first element to an int and build the
     * RandomPlayer with that initial value.
     */
    public static RandomPlayer buildRandomPlayer(String[] args) {
        if(args.length == 0) {
            return new RandomPlayer();
        } else {
            int seed = Integer.parseInt(args[0]);
            return new RandomPlayer(seed);
        }
    }
}
