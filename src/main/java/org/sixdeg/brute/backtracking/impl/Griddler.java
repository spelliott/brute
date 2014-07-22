package org.sixdeg.brute.backtracking.impl;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sixdeg.brute.backtracking.Backtracker;


public class Griddler extends Backtracker {

    private static final Integer[] INT_ARRAY = new Integer[0];
    Integer[][] rowHints;
    Integer[][] colHints;
    
    Boolean[][] numbers;
    
    int height; 
    // num rows
    
    int width; 
    // num cols
    
    int upToCol = 0;
    int upToRow = 0;
    
    public Griddler(Integer[][] rowHints, Integer[][] colHints) {
        this.rowHints = rowHints;
        this.colHints = colHints;
        this.height = rowHints.length;
        this.width = colHints.length;
        
        numbers = new Boolean[height][width];
        
        // initialise with 1st attempt
        numbers[0][0] = false;
    }
    
    public void print(PrintStream out) {
        for (int row = 0; row < height; row++) {
            String rowStr = "";
            for (int col = 0; col < width; col++) {
                if (numbers[row][col] == null) {
                    rowStr += "?";
                }
                else {
                rowStr += (numbers[row][col] ? "#" : " ");
                }
            }
            out.println(rowStr);
        }
    }
    Boolean[] sliceRow(int rowNum) {
        return numbers[rowNum];
    }
    
    Boolean[] sliceColumn(int colNum) {
        Boolean[] col = new Boolean[height];
        for (int i = 0; i < height; i++){
            col[i] = numbers[i][colNum];
        }
        return col;
    }
    
    Integer[] summarise(Boolean[] cells) {
        List<Integer> summary = new ArrayList<Integer>();
        int next = 0;
        for (Boolean b : cells) {
            
                if (b != null && b ) {
                    next++;
                }
                else {
                    if (next > 0) {
                        
                    
                    summary.add(next);
                    next = 0;
                    }
                }
            
        }
        if (next > 0) {
            
            
        summary.add(next);
        }
        return summary.toArray(INT_ARRAY);
    }
    
    boolean satisfiesLastBlockMaybeIncomplete(Integer[] summary, Integer[] actual) {
        if (actual.length > summary.length) {
            return false;
        }
        for (int i = 0; i < actual.length; i++) {
            if (i == actual.length-1) {
                return actual[i] <= summary[i];
            }
            else {
                if (!summary[i].equals(actual[i])) {
                    return false;
                }
            }

        }
        
        assert false : "Should be unreachable";
        return false;
    }
    
    boolean satisfies(Integer[] summary, Integer[] actual) {
        if (actual.length > summary.length) {
            return false;
        }
        for (int i = 0; i < actual.length; i++) {
            if (!summary[i].equals(actual[i])) {
                return false;
            }
        }
        return true;
    }
    
    boolean lastNonNullElement(Boolean[] arr) {
        Boolean result = false;
        for (Boolean b : arr) {
            if (b == null) {
                return result;
            }
            result = b;
        }
        return result;
    }
    
    boolean isComplete(Boolean[] arr) {
        for (Boolean b : arr) {
            if (b == null) {
                return false;
            }
        }
        return true;
    }
    

    @Override
    protected boolean isValid() {
        
        for (int rowNum = 0; rowNum < height; rowNum++) {
            Boolean[] row = sliceRow(rowNum);
            Integer[] rowSummary = summarise(row);
            Integer[] expectedRowSummay = rowHints[rowNum];
            
            boolean rowValid;
            if (isComplete(row)) {
                
                rowValid = Arrays.equals(expectedRowSummay, rowSummary);
            }
            else {
                boolean last = lastNonNullElement(row);
                if (last) {
                    rowValid = satisfiesLastBlockMaybeIncomplete(expectedRowSummay, rowSummary);
                }
                else {
                    rowValid = satisfies(expectedRowSummay, rowSummary);
                }
            }
            
            if (!rowValid) {
                
                return false;
            }
        }
        for (int colNum = 0; colNum < width; colNum++) {
            Boolean[] col = sliceColumn(colNum);
            Integer[] colSummary = summarise(col);
            Integer[] expectedColSummary = colHints[colNum];
            
            boolean colValid;
            if (isComplete(col)) {
                colValid = Arrays.equals(expectedColSummary, colSummary);
            }
            else {
                boolean last = lastNonNullElement(col);
                if (last) {
                    colValid = satisfiesLastBlockMaybeIncomplete(expectedColSummary, colSummary);
                }
                else {
                    colValid = satisfies(expectedColSummary, colSummary);
                }
            }
            
            if (!colValid) {
                return false;
            }

        }
        return true;
    }


    @Override
    protected boolean isFinished() {
        for (int i = 0 ; i < height; i++){
            Boolean[] row = sliceRow(i);
            if (!isComplete(row)) {
                return false;
            }
        }
        return true;
    }


    @Override
    protected void down() {
        int nextUpToCol = ++upToCol % width;
        int nextUpToRow = nextUpToCol == 0 ? upToRow+1 : upToRow;
        upToCol = nextUpToCol;
        upToRow = nextUpToRow;
        numbers[upToRow][upToCol] = false;
        
    }


    @Override
    protected boolean sideways() {
        if (numbers[upToRow][upToCol]) {
            return false;
        }
        numbers[upToRow][upToCol] = true;
        return true;
    }


    @Override
    protected boolean up() {
        if (upToRow == 0 && upToCol == 0) {
            return false;
        }
        int nextUpToCol = upToCol - 1;
        if (nextUpToCol == -1) {
            nextUpToCol = width-1;
        }
        
        int nextUpToRow = nextUpToCol == (width-1) ? upToRow-1 : upToRow;
        
        numbers[upToRow][upToCol] = null;
        upToRow = nextUpToRow;
        upToCol = nextUpToCol;
        return true;
    }

}
