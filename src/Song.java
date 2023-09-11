/*
 * CS312 Assignment 1
 * On my honor, Mohammad N Kashif, this programming assignment is my own work.
 *
 * A program to print out the lyrics to the
 * children's song, "There was an old woman".
 *
 *  Name: Mohammad N Kashif
 *  email address: mohammadnkashif@utexas.edu
 *  UTEID: mnk665
 *  Section 5-digit ID: 52495
 *  I estimate this assignment will take this many hours: 1
 *  This assignment took this many hours: 1
 *  Grader name:
 */
//System.out.println("");
public class Song {
    // This code will print out the children's rhyme "I Know an Old Lady"

    public static void main(String[] args) {
        //Calling methods created for each paragraph
        intro();
        paragraph2();
        paragraph3();
        paragraph4();
        paragraph5();
        paragraph6();
        paragraph7();
        outro();
    }
    private static void intro(){
        //Printing Intro Paragraph, calling rep1 function for repeated lines
        System.out.println("There was an old woman who swallowed a fly.");
        rep1();
    }
    //Each paragraph function prints unique lines and calls rep function for repeated lines
    private static void paragraph2(){
        //Printing second paragraph, calling rep2 function for repeated lines
        System.out.println("There was an old woman who swallowed a spider,");
        System.out.println("That wriggled and iggled and jiggled inside her.");
        rep2();
    }
    private static void paragraph3(){
        //Printing third paragraph, calling rep3 function for repeated lines
        System.out.println("There was an old woman who swallowed a bird,");
        System.out.println("How absurd to swallow a bird.");
        rep3();
    }
    private static void paragraph4(){
        //Printing fourth paragraph, calling rep4 function for repeated lines
        System.out.println("There was an old woman who swallowed a cat,");
        System.out.println("Imagine that to swallow a cat.");
        rep4();
    }
    private static void paragraph5(){
        //Printing fifth paragraph, calling rep5 function for repeated lines
        System.out.println("There was an old woman who swallowed a dog,");
        System.out.println("What a hog to swallow a dog.");
        rep5();
    }
    private static void paragraph6(){
        //Printing sixth paragraph, calling rep6 function for repeated lines
        System.out.println("There was an old woman who swallowed a goat,");
        System.out.println("She just opened her throat to swallow a goat.");
        rep6();
    }
    private static void paragraph7(){
        //Printing seventh paragraph, calling rep7 function for repeated lines
        System.out.println("There was an old woman who swallowed a cow,");
        System.out.println("I don't know how she swallowed a cow.");
        rep7();
    }
    private static void outro(){
        //Printing last paragraph
        System.out.println("There was an old woman who swallowed a horse,");
        System.out.println("She died of course.");
    }
    //rep functions each add 1 line to the text repeated in each paragraph
    private static void rep1(){
        //Function for repeated lines
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.\n");
    }
    private static void rep2(){
        //Function for repeated lines with addition of 1 line
        System.out.println("She swallowed the spider to catch the fly,");
        rep1();
    }
    private static void rep3(){
        //Function for repeated lines with addition of 1 line
        System.out.println("She swallowed the bird to catch the spider,");
        rep2();
    }
    private static void rep4(){
        //Function for repeated lines with addition of 1 line
        System.out.println("She swallowed the cat to catch the bird,");
        rep3();
    }
    private static void rep5(){
        //Function for repeated lines with addition of 1 line
        System.out.println("She swallowed the dog to catch the cat,");
        rep4();
    }
    private static void rep6(){
        //Function for repeated lines with addition of 1 line
        System.out.println("She swallowed the goat to catch the dog,");
        rep5();
    }
    private static void rep7(){
        //Function for repeated lines with addition of 1 line
        System.out.println("She swallowed the cow to catch the goat,");
        rep6();
    }
}
