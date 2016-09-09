/*
 * ******************************************************
 * ********* Generates a Word Search Puzzle
 * ********* 07/12/2015
 * ********* Created By Ross Keddy.             *********
 * ******************************************************
 */
package word.search;
import java.util.Scanner;
import java.util.Random;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;



/**
 *
 * @author Ross
 */
public class Puzzle {
        private int puzzleSize; // The size of the puzzle
        private char[][] grid; // 2D Array for the x + y size of the puzzle
        private String[] wordList; // User's Inputted Data
        
    /**
     * On the seventh day Ross created the
     * Puzzle Constructor to declare the main variables!
     */
    public Puzzle(){
        puzzleSize = getSize(); // Gets user's input
        grid = new char[puzzleSize][puzzleSize]; // Set's puzzle grid
        wordList = new String[puzzleSize]; // Updates maximum number of words
    } // End of Puzzle constructor
    
    //************************************************************************
    
    /**
     * Asks the user for as many words as equal to the 
     * size they wanted to fill the puzzle, transforms 
     * the words to uppercase then verifies that the word
     * will fit within the puzzle
     */
    public void getWords(){
        Scanner keyboard = new Scanner(System.in);
        
        //======Gets the words======
        int counter = 0; // Counts the number of verified words
        do{
            System.out.print("Please enter a word to add: ");
            String tempWord = keyboard.nextLine().toUpperCase(); // Stores the users input temporarily
            
            if(tempWord.length() > puzzleSize){ // Invalid Word
                System.out.println("Please enter a VALID word ..."); 
            } else if(tempWord.length() <= puzzleSize){ // Valid Word
                wordList[counter] = tempWord; // Adds Word
                counter = counter + 1; // +1
            }
        }while(counter < puzzleSize); //Loops until enough words are stored
        
    }//End of getWords
    
    /**
     * Ask for the users input on how big they'd like
     * their puzzle to be. Checks to make sure the user
     * didn't enter a negative or a large number(for beta testing)
     */
    private int getSize(){
        Scanner keyboard = new Scanner(System.in);
        //======Gets the size======
        boolean validSize; // Verification to check that the size is valid
        int tempSize; // Temporarily stores the users input
        
        do{
            System.out.print("How big would you like your puzzle to be? ");
            tempSize = keyboard.nextInt(); // Collects input
            
            if(tempSize <= 0 || tempSize >= 26){ // Invalid Size
                System.out.print("Please enter a valid size... ");
                validSize = false;
            } else {                             // Valid Size
                validSize = true;
            }
        }while(validSize == false); //Loops until size is acceptable
        
        return tempSize;
    }//End of getSize    
    
    /**
     * Fills the puzzle first with blanks, then takes each
     * word and converts them to a char and inserts them into the
     * grid. Once a the words have been added it fills the puzzle 
     * with random characters called from getCharacter()
     */
    public void fillEmpties(){
        Random random = new Random();
        //Declarations
        String word;
        int counter = 0;
        
        for(int row = 0; row < grid.length; row++){ // Row
            word = wordList[counter]; //Pulls a word
            //Declares the variable rndStart for random starting positions
            int total = (puzzleSize - word.length()) + 1; 
            int rndStart = random.nextInt(total);
            
            for(int col = 0; col < grid[row].length; col++){ // Column
                grid[row][col] = ' '; // Fills in with blanks
                int counter2 = 0; //Counter Reset
                
                //Takes the word and creates characters
                for(int i = rndStart; i < (word.length() + rndStart); i++){
                    grid[row][i] = word.charAt(counter2);
                    counter2 = counter2 + 1;
                }//=====================================
                
                // Fills empty spaces with a random character
                if(grid[row][col] == ' '){
                    grid[row][col] = getCharacter(); // Fills empty spaces
                }//==========================================
            }
            counter = counter + 1; //counter++ for the next words
        }
    }//End of fillEmpties
    
    /**
     * Creates a char array of letters which it 
     * then randomizes and returns to the user.
     * @return
     */
    private char getCharacter(){
        Random random = new Random(); //declares random selector
        char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
                           'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
                           'U', 'V', 'W', 'X', 'Y', 'Z'}; //list of characters
        int rnd = random.nextInt(26); //select a random character
	return letters[rnd]; //returns a random character
    }// End of getCharacter
    
    /**
     * converts the grid[][] to a string then
     * Formats and prints the puzzle
     * @return
     */
    public String toString(){
        String puzzle = "";
        int counter = 0;
        puzzle += "\n";
        
        //Formats the puzzle
        for (char[] grid1 : grid) {
            for (int col = 0; col < grid1.length; col++) {
                puzzle = puzzle + String.format("%3s", grid1[col]);
            }
            puzzle += "\n\n";
        }
        
        //Displays the users words
        puzzle = puzzle + "Here are the words to find! ";
        for(String wordList1 : wordList){
            puzzle = puzzle + wordList1;
            //Removes the last comma
            if(counter != wordList.length - 1){
                puzzle = puzzle + ", ";
            }
            counter = counter + 1; //counter++
        }
        puzzle += "\n";
        
        return puzzle;
    }//End of toString
    
    /**
     * Takes the current output and creates a .txt
     * for the user
     */
    public void printToFile(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println();
        //Declarations
        Path path;
        String fileName;
         
        do{
            System.out.printf("What would you like the file to be called? (Please enter a unique filename) ");
            fileName = keyboard.nextLine() + ".txt";
             
            path = Paths.get(fileName);
        }while(Files.exists(path));
         
        try{
            try (Formatter outputStream = new Formatter(fileName)){
                outputStream.format(toString());
            }
        }catch(FileNotFoundException e){
            System.err.print("Sorry that file is not valid");
        }
    }// End of printToFile
}// End of class
