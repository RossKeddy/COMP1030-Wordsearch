/*
 * ******************************************************
 * ********* Description.
 * ********* Date.
 * ********* Created By Ross Keddy.             *********
 * ******************************************************
 */
package word.search;

import java.util.ArrayList;

/**
 *
 * @author Ross
 */
public class Movie {
    ArrayList<String> alname;
    ArrayList<Integer> alyear;
    ArrayList<Integer> allength;
    
    public Movie(String name, int year, int length){
        alname.add(name);
        alyear.add(year);
        allength.add(length);
    }//end of Student
}
