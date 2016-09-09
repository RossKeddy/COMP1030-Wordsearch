/*
 * ******************************************************
 * ********* This Program Creates a Word Search
 * ********* 01/12/2015
 * ********* Created By Ross Keddy.             *********
 * ******************************************************
 */
package word.search;

/**
 *
 * @author Ross
 */
public class WordSearch {

    /**
     * Main method calls everything from Puzzle
     * Outputs as a string and then asks if the
     * user wants to print to a file
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Puzzle myPuzzle = new Puzzle();
        myPuzzle.getWords();
        myPuzzle.fillEmpties();
        System.out.print(myPuzzle.toString());
        
        //bonus
        myPuzzle.printToFile();
    }// End of main method
}//End of class