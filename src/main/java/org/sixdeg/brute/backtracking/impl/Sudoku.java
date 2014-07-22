package org.sixdeg.brute.backtracking.impl;

import java.io.PrintStream;

import org.sixdeg.brute.backtracking.Backtracker;

public class Sudoku extends Backtracker {
	
	private Integer[][] numbers;
	private boolean[][] isFixed;
	private int upToX;
	private int upToY;

	public Sudoku(Integer[][] numbers) {
		
		isFixed = new boolean[9][9];
		this.numbers = numbers;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (numbers[i][j] == 0) {
					numbers[i][j] = null;
				}
				else {
					isFixed[i][j] = true;
				}
			}
		} 
		
		// initialise with 1st solution attempt
		upToX = 0;
		upToY = 0;
		
		if (!isFixed[0][0]) {
			numbers[0][0] = 1;
		}
	}
	
	Integer[] sliceRow(int rowNum) {
		return numbers[rowNum];
	}
	
	Integer[] sliceColumn(int colNum) {
		Integer[] col = new Integer[9];
		for (int i = 0; i < 9; i++){
			col[i] = numbers[i][colNum];
		}
		return col;
	}
	
	Integer[] sliceSquare(int squareX, int squareY){
		int xStart = squareX * 3;
		int yStart = squareY * 3;
		Integer[] square = new Integer[9];
		int count = 0;
		for (int i = xStart; i < xStart +3; i++) {
			for (int j = yStart; j < yStart + 3; j++) {
				square[count] = numbers[i][j];
				count++;
			}
			
		}
		return square;
	}
	
    boolean isDistinct(Integer[] nums) {
        boolean[] flags = new boolean[9];
            for (Integer n : nums) {
                if (n != null) {
                if (flags[n-1]) {
                    return false;
                }
                else {
                    flags[n-1] = true;
                }
                }
            }
        
        return true;

    }
	
	@Override
	protected boolean isValid() {
		for (int i = 0; i < 9; i++) {
			if (!isDistinct(sliceRow(i))) {
				return false;
			}
		}
		for (int j = 0; j < 9; j++) {
			if (!isDistinct(sliceColumn(j))) {
				return false;
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!isDistinct(sliceSquare(i,j))) {
					return false;
				}
			}
		}
		
		return true;
	}

	boolean isComplete(Integer[] nums) {
		for (Integer n : nums) {
			if (n == null) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected boolean isFinished() {
		for (int i = 0; i < 9; i++) {
			if (!isComplete(numbers[i])) {
				return false;
			}
		}
		return true;
	}

	public void print(PrintStream out) {
		
		for (Integer[] row : numbers) {
			String rowStr = "";
			for (Integer n : row){
				rowStr += "" + n + " ";
			}
			out.println(rowStr);
			
		}
		
	}
	
	@Override
	protected void down() {
		int nextUpToX = ++upToX % 9;
		int nextUpToY = nextUpToX == 0 ? upToY+1 : upToY;
		upToX = nextUpToX;
		upToY = nextUpToY;
		
		if (!isFixed[nextUpToX][nextUpToY]) {
			numbers[nextUpToX][nextUpToY] = 1;
		}
		
	}

	@Override
	protected boolean sideways() {

		if (isFixed[upToX][upToY]) {
			return false;
		}
		
		if (numbers[upToX][upToY] == 9) {
			return false;
		}
		
		numbers[upToX][upToY]++;
		return true;
	}

	@Override
	protected boolean up() {
		if (upToX == 0 && upToY == 0) {
			return false;
		}
		int nextUpToX = upToX - 1;
		if (nextUpToX == -1) {
			nextUpToX = 9 - 1;
		}
		int nextUpToY = nextUpToX == (9-1) ? upToY-1 : upToY;
		
		if (!isFixed[upToX][upToY]){
		numbers[upToX][upToY] = null;
		}
		upToX = nextUpToX;
		upToY = nextUpToY;
		return true;
	}
	

}
