import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * CS312 Assignment 6
 * 
 * On my honor, Mohammad N Kashif, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play determine the extend of home field advantage in sports.
 *
 *  email address: mohammadnkashif@utexas.edu
 *  UTEID: mnk665
 *  Unique 5 digit course ID: 52495
 *  Grader name:
 *  Number of slip days used on this assignment: 0
 */
 
 /**
 * Analysis: After running all the input files, we see a clear home field advantage. This advantage
  * isn't great enough to make it unfair, but is always present. This is evidenced by the fact that
  * all the input data files had a home team win percentage between 50% and 65%. Since home team
  * always won more than 50%, which would be the statistical probability, a small advantage is
  * present. Also, 10/13 input files have an average margin between 4 and 6. This proves that in
  * most cases, a clear home team advantage is present and results in greater scoring. I find this
  * advantage isn't enough to be considered unfair, but the average margin and win percentage both
  * prove the average home team has a slight edge or advantage when analyzing the given data.
 */
 
public class HomeField {

    // Ask the user for the name of a data file and process
    // it until they want to quit.
    public static void main(String[] args) throws IOException{
        System.out.println("A program to analyze home field advantage in sports.");
        System.out.println();
        // CS312 students. Do not create any other Scanners connected to System.in.
        // Pass keyboard as a parameter to all the methods that need it. 
        Scanner keyboard = new Scanner(System.in);

        Boolean newSet = true;
        // While loop keeps running as long as user wants to analyze a new file.
        // getFile method asks user for file and processes it.
        // askForNextFile method checks if user wants to analyze new file.
        while (newSet) {
            getFile(keyboard);
            newSet = askForNextFile(keyboard);
        }
        
        keyboard.close();
    }

    // Method receives valid file name from user and runs it through the processFile method
    // for data processing.
     private static void getFile(Scanner keyboard) throws FileNotFoundException {
         Boolean validName = false;
         while (!validName) {
             System.out.print("Enter the file name: ");
             String fileString = keyboard.nextLine();
             File file = new File(fileString);

             if (file.exists()) {
                 Scanner readFile = new Scanner(file);
                 processFile(file, readFile);
                 validName = true;
             }
             else {
                 System.out.println("Sorry, that file does not exist");
                 validName = false;
             }
         }
     }

     // Asks user if they want to analyze a new file and returns Boolean value.
     private static Boolean askForNextFile(Scanner keyboard) {
         Boolean newSet = true;
         System.out.println("\nDo you want to check another data set?");
         System.out.print("Enter Y or y to analyze another file, anything else to quit: ");
         char newSetInput = keyboard.next().toUpperCase().charAt(0);
         System.out.println();
         if (newSetInput != 'Y') newSet = false;
         keyboard.nextLine();
         return newSet;
     }

     // Method to process file data. Contains variables tracking scores and victories from each line.
     // While loop used to iterate through each line of data, scanner method created in loop for
     // each line. Data fed into printResults method.
     private static void processFile(File file, Scanner readFile) {
        //Variables initialized for all components we need to track in data
        int gameCount = 0;
        int gamesWithHomeTeam = 0;
        int homeTeamVictories = 0;
        int marginCalculator = 0;

        String sport = readFile.nextLine();
        String season = readFile.nextLine();
        while (readFile.hasNextLine()) {
            // Next line of data read in, scanner created to find data
            String nextLine = readFile.nextLine();
            Scanner line = new Scanner(nextLine);

            // All needed data read in from file, getCompleteString function used for team names.
            String date = line.next();
            String team1 = getCompleteString(line);
            double score1 = line.nextDouble();
            String team2 = getCompleteString(line);
            double score2 = line.nextDouble();

            // Home team and away team scores identified using external methods.
            double homeTeamScore = getHomeTeamScore(team1, score1, team2, score2);
            double awayTeamScore = getAwayTeamScore(team1, score1, team2, score2);

            // All 4 variables updated based on inputted data.
            gameCount++;
            if (team1.charAt(0) == '@' || team2.charAt(0) == '@') gamesWithHomeTeam++;
            if (homeTeamScore > awayTeamScore) homeTeamVictories++;
            marginCalculator += (homeTeamScore - awayTeamScore);
        }
        // Results calculated and printed using external method
        printResults(sport, season, gameCount, gamesWithHomeTeam,
                homeTeamVictories, marginCalculator);
    }

    //Method to ensure complete string is inputted as team name instead of first word.
     private static String getCompleteString(Scanner line) {
         String completeStr = line.next();
         while (!line.hasNextInt()) completeStr += line.next();
         return completeStr;
     }

     // Method to assign correct score to homeTeamScore variable.
     private static double getHomeTeamScore(String team1, double score1,
                                            String team2, double score2) {
         double homeTeamScore = 0;
         if (team1.charAt(0) == '@') homeTeamScore = score1;
         else if (team2.charAt(0) == '@') homeTeamScore = score2;
         return homeTeamScore;
     }

     // Method to assign correct score to awayTeamScore variable.
     private static double getAwayTeamScore(String team1, double score1,
                                            String team2, double score2) {
         double awayTeamScore = 0;
         if (team1.charAt(0) == '@') awayTeamScore = score2;
         else if (team2.charAt(0) == '@') awayTeamScore = score1;
         return awayTeamScore;
     }

     // Method to perform final calculations and print results to user.
     private static void printResults(String sport, String season, int gameCount,
                                      int gamesWithHomeTeam, int homeTeamVictories,
                                      int marginCalculator) {
        // Percent games with home team calculated
         double percentWithHomeTeams = (double) gamesWithHomeTeam / gameCount * 100.0;
         // Home team win percentage calculated
         double winPercentage = (double) homeTeamVictories / gamesWithHomeTeam * 100.0;
         // Average margin calculated using marginCalculator variable
         double avgMargin = (double) marginCalculator / gamesWithHomeTeam;

         // Results of file analysis printed to user
         System.out.println("\n**********   " + sport + " --- " + season + "   **********\n");
         System.out.println("HOME FIELD ADVANTAGE RESULTS");
         System.out.printf("\nTotal number of games: %,d" , gameCount);
         System.out.printf("\nNumber of games with a home team: %,d" , gamesWithHomeTeam);
         System.out.printf("\nPercentage of games with a home team: %.1f%%" , percentWithHomeTeams);
         System.out.printf("\nNumber of games the home team won: %,d" , homeTeamVictories);
         System.out.printf("\nHome team win percentage: %.1f%%" , winPercentage);
         System.out.printf("\nHome team average margin: %.2f\n" , avgMargin);
     }
}
