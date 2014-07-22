package org.sixdeg.brute;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.sixdeg.brute.backtracking.impl.Griddler;
import org.sixdeg.brute.backtracking.impl.Magic;
import org.sixdeg.brute.backtracking.impl.Sudoku;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }


    public void testMagic() {
    	Magic m = new Magic(3);
    	m.attempt();
    	m.print(System.out);
    }
    
    public void testSudoku() {
    	Integer[][] numbers = {
    			{0,9,0,0,0,8,0,0,6},
    			{7,0,2,0,1,0,4,0,0},
    			{0,1,0,0,0,2,0,3,0},
    			{0,0,0,0,0,0,5,0,8},
    			{0,4,0,0,8,0,0,2,0},
    			{6,0,1,0,0,0,0,0,0},
    			{0,3,0,4,0,0,0,6,0},
    			{0,0,9,0,7,0,1,0,3},
    			{5,0,0,2,0,0,0,4,0}};
    	Sudoku s = new Sudoku(numbers);
    	s.attempt();
    	s.print(System.out);
    	
    }
    
    
    
    public void testGriddler() {
        Integer[][] rowHints = {{17},
                                {25},
                                {27},
                                {29},
                                {8,9},
                                {5,5},
                                {5,5},
                                {4,3,4},
                                {4,4,4},
                                {4,4,4},
                                {4,4,4},
                                {4,3,4},
                                {4,3,3,4,4},
                                {4,3,4,4,4},
                                {4,3,4,4,4},
                                {4,3,2,2,4},
                                {4,3,3,2,4},
                                {4,3,4,2,4},
                                {4,3,3,2,4},
                                {4,3,2,2,4},
                                {4,5,4,2,4},
                                {4,5,4,2,4},
                                {4,5,3,2,4},
                                {4,4},
                                {4,4,4},
                                {4,5,4},
                                {4,5,4},
                                {5,10},
                                {5,9},
                                {8,8},
                                {29},
                                {29},
                                {28},
                                {17,6},
                                {5}};
        
        Integer[][] colHints = {{28},
                                {30},
                                {32},
                                {32},
                                {6,6},
                                {4,4},
                                {5,5},
                                {5,5},
                                {4,3,3,4},
                                {4,16,4},
                                {4,16,4},
                                {4,16,4},
                                {4,3,4},
                                {4,4},
                                {4,5,3,4},
                                {4,7,3,4},
                                {4,3,7,4},
                                {4,3,5,4},
                                {4,4},
                                {4,3,4,4},
                                {5,11,10},
                                {5,11,10},
                                {5,3,10},
                                {4,8},
                                {6,7},
                                {34},
                                {34},
                                {33},
                                {26,5},
                                {4}
                                };
        Griddler g = new Griddler(rowHints, colHints);
        g.attempt();
        g.print(System.out);
    }
    
}
