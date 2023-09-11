/*
 *  CS312 Assignment 2.
 *  On my honor, Mohammad N Kashif, this programming assignment is my own work.
 *
 *  A program to print out the UT Tower in ASCII art form.
 *
 *  Name: Mohammad N Kashif
 *  email address: mohammadnkashif@utexas.edu
 *  UTEID: mnk665
 *  Section 5 digit ID: 52495
 *  Grader name:
 *  Number of slip days used on this assignment: 1
 */

public class Tower {

    // CS312 students, DO NOT ALTER THE FOLLOWING LINE except for the
    // value of the literal int.
    // You may change the literal int assigned to SIZE to any value from 2 to 100.
    // In the final version of the program you submit set the SIZE to 3.

    public static final int SIZE = 3;
    public static final int TOP_WIDTH = (SIZE * 2) - 1;
    public static final int TOP_LENGTH = (SIZE * 2) - 2;
    public static final int MID_WIDTH = (SIZE * 2) + 3;
    public static final int MID_LENGTH = (SIZE * SIZE) * 2;
    public static final int MID_SPACE = (SIZE * 4);
    public static final int BOTTOM_SPACE = MID_WIDTH + (MID_SPACE * 2);
    public static final int SLOPE_LENGTH = (SIZE + 2) / 2;
    public static final int SLOPE_REPS = (BOTTOM_SPACE - 3) / 2;
    // CS312 students, if you add any constants DO NOT include the word SIZE
    // (including any variations on capitalizations such as sIZE, etc.) in
    // the name of that constant. So for example nothing like:
    //
    // public static final int BASE_SIZE2 = 5;
    //
    // If you do, you will flummox our grading script
    // and lose correctness points.

    public static void main(String[] args) {
        //Calling separate methods for top of building, middle, and bottom
        top();
        middle();
        bottom();
    }
    public static void top(){
        //Function for the top of the tower with proper scaling based on size
        top1();
        top2();
        top1();
    }
    public static void middle(){
        //Function for the middle of the tower with proper scaling based on size
        for (int i=1; i <=(MID_LENGTH / 2); i++){
            mid1();
            mid2();
        }
        mid1();
    }
    public static void bottom(){
        //Function for the bottom section, calling two sub-functions defined below
        bottomSlope();
        lowerBottom();
    }
    private static void top1(){
        topSpacing();
        //Prints line of '#' with length based on scaling
        for (int i=1; i <= TOP_WIDTH; i++){
            System.out.print("#");
        }
        System.out.print("\n");
    }
    //Prints line of '|' with length based on scaling
    private static void top2(){
        //For loop, as well as nested loop, produces lines of '|' based on needed scaling
        for (int i=1; i <= TOP_LENGTH; i++){
            topSpacing();
            for (int j=1; j <= TOP_WIDTH; j++){
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }
    private static void topSpacing(){
        //Provides proper spacing to top of tower based on scaling
        //Scale for top spacing is size times 4 plus 2, as called in for function
        for (int i=1; i <= ((SIZE * 4) + 2);i++){
            System.out.print(" ");
        }
    }
    private static void mid1(){
        //Function used to print the lines of "~" found in building middle
        midSpacing();
        for (int i=1; i <= MID_WIDTH; i++){
            System.out.print("~");
        }
        System.out.print("\n");
    }
    private static void mid2(){
        //Function used to print the lines of "-O-" found in building middle
        midSpacing();
        System.out.print("|-");
        for (int i=1;i <= ((TOP_WIDTH+1) / 2); i++){
            System.out.print("O-");
        }
        System.out.print("|\n");
    }
    private static void midSpacing(){
        //Provides proper spacing to top of tower based on scaling
        ////Middle portion spacing is 2 less than top spacing
        for (int i=1; i <= (MID_SPACE);i++){
            System.out.print(" ");
        }
    }
    private static void bottomSlope(){
        //Function used for constructing the sloped function of building
        //Outer loop runs based on sizing, ends with new line
        for (int i = SLOPE_LENGTH; i >= 1; i--){
            //Nested loop provides reduced spacing in each repetition
            for (int j = ((i * 3)-3); j >= 1; j--){
                System.out.print(" ");
            }
            System.out.print("/\"");
            //For loop uses SLOPE_REPS variable to fill in slope section of tower
            //with proper spacing to maintain slope and requirements
            for (int j = 1; j <= ((SLOPE_REPS+3) - 3*i); j++) {
                System.out.print("\'\"");
            }

            System.out.print("\\");
            System.out.print("\n");
        }
    }
    private static void lowerBottom(){
        //Function prints lower portion of bottom of building
        //SIZE directly defines number of lines needed in lower bottom portion
        for (int i=1; i <= SIZE; i++){
            System.out.print("/\"");
            for (int j=1; j <= ((BOTTOM_SPACE-3)/2);j++){
                System.out.print("O\"");
            }
            System.out.print("\\\n");
        }

    }
}
