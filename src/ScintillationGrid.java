import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

/**
 * CS312 Assignment 3.
 *
 * Replace <NAME> with your name, stating on your honor you completed this
 * assignment on your own and that you didn't share your code with other
 * students.
 * 
 * On my honor, Mohammad Nabhan Kashif, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to print out various scintillation grids and a student designed drawing. 
 *
 *  email address: mohammadnkashif@utexas.edu
 *  UTEID: mnk665
 *  Number of slip days I am using on this assignment: 0
 */

public class ScintillationGrid {

    // Main method that creates the DrawingPanel with scintillation grids.
    // Restricted to chapters 1 - 3 of Building Java Programs
    public static void main(String[] args) {
        /* In the final version of the program DO NOT call method drawingOne 
           from main or anywhere else in the program */
        final int WIDTH = 950;
        final int HEIGHT = 650;
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        //Drawing panel created
        Graphics gr = dp.getGraphics();
        dp.setBackground(Color.CYAN);
        //Background set to Cyan color
        
        // CS312 Students add your four methods calls to draw the four
        // required scintillation grids here.
        createPanel(gr, 0, 0, 348, 75, 3, 16);
        createPanel(gr, 400, 50, 422, 50, 6, 12);
        createPanel(gr, 50, 400, 220, 100, 1, 20);
        createPanel(gr, 500, 500, 148, 15, 7, 4);
        // CS312 students, do not alter the following line of code.
        saveDrawingPanel(dp, "grid.png");
    }

    
    // Save the current drawing panel to the given file. 
    // CS312 Students, do not alter this method.
    public static void saveDrawingPanel(DrawingPanel dp, String fileName) {
        try {
            dp.save(fileName);
        } catch (IOException e) {
            System.out.println("Unable to save DrawingPanel");
        }
    }

    //Following method inputs all required values and creates scintillation grids
    public static void createPanel(Graphics gr, int x, int y, int L_SQUARE_SIZE,
                                   int S_SQUARE_SIZE, int LINE_COUNT, int LINE_THICKNESS){
        gr.setColor(Color.BLACK);
        gr.fillRect(x, y, L_SQUARE_SIZE, L_SQUARE_SIZE);
        //Black rectangle printed as background of scintillation grid

        horizontalLines(gr, x, y, L_SQUARE_SIZE, S_SQUARE_SIZE, LINE_COUNT, LINE_THICKNESS);
        //Function call to print horizontal lines within black rectangle
        verticalLines(gr, x, y, L_SQUARE_SIZE, S_SQUARE_SIZE, LINE_COUNT, LINE_THICKNESS);
        //Function call to print vertical lines within black rectangle
        drawCircles(gr, x, y, S_SQUARE_SIZE, LINE_COUNT, LINE_THICKNESS);
        //Function call to print circles in desired locations
    }

    private static void horizontalLines(Graphics gr, int x, int y, int L_SQUARE_SIZE,
                                        int S_SQUARE_SIZE, int LINE_COUNT, int LINE_THICKNESS) {
        //For loop uses LINE_COUNT as limit to print rectangles serving as horizontal lines
        for (int i = 1; i <= LINE_COUNT; i++){
            int x1 = (x + (S_SQUARE_SIZE * i) + (LINE_THICKNESS * (i-1)));
            //Distance between each line is calculated and added to x
            //Each time, we add distance of square size and thickness of line to x
            //No line thickness present for first iteration so (i-1) is used
            gr.setColor(Color.GRAY);
            gr.fillRect(x1, y, LINE_THICKNESS, L_SQUARE_SIZE);
            //Gray rectangle printed with desired location and size
        }
    }

    private static void verticalLines(Graphics gr, int x, int y, int L_SQUARE_SIZE,
                                      int S_SQUARE_SIZE, int LINE_COUNT, int LINE_THICKNESS) {
        //For loop uses LINE_COUNT as limit to print rectangles serving as vertical lines
        for (int i = 1; i <= LINE_COUNT; i++){
            int y1 = (y + (S_SQUARE_SIZE * i) + (LINE_THICKNESS * (i-1)));
            //Distance between each line is calculated and added to y
            //Each time, we add distance of square size and thickness of line to y
            //No line thickness present for first iteration so (i-1) is used
            gr.setColor(Color.GRAY);
            gr.fillRect(x,y1, L_SQUARE_SIZE, LINE_THICKNESS);
            //Gray rectangle printed with desired location and size
        }
    }

    private static void drawCircles(Graphics gr, int x, int y, int S_SQUARE_SIZE,
                                    int LINE_COUNT, int LINE_THICKNESS) {
        //For loop that prints 1 column of circles at a time using nested loop
        //LINE_COUNT input used to limit iterations
        for (int i = 1; i <= LINE_COUNT; i++){
            int diaCalc = (int)(LINE_THICKNESS * 1.4);
            //Circle diameter defined as 1.4 times the line thickness
            int dia = Math.max(8, diaCalc);
            //Circle diameter exception: minimum of 8 diameter regardless of diaCalc calculation
            int starting_X = (x + (S_SQUARE_SIZE * i) + (LINE_THICKNESS * (i-1))
                    - (dia - LINE_THICKNESS)/2);
            //Location of circle is defined within outer loop, using i in calculation to change x position each time as needed
            int starting_Y = y + S_SQUARE_SIZE - (dia - LINE_THICKNESS)/2;
            //Y location of circle is constant for outer loop iteration, resets each time
            for (int j = 1; j <= LINE_COUNT; j++) {
                gr.setColor(Color.WHITE);
                gr.fillOval(starting_X, starting_Y, dia, dia);
                starting_Y += (S_SQUARE_SIZE + LINE_THICKNESS);
                //Y location of circle changes only within nested loop, providing proper circle placement in columns
            }
         }
    }

}

