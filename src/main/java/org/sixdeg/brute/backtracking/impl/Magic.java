package org.sixdeg.brute.backtracking.impl;

import java.io.PrintStream;

import org.sixdeg.brute.backtracking.Backtracker;

public class Magic extends Backtracker {

	Integer[][] numbers;
	
	// coords of last attempted number
	int upToX = 0;
	int upToY = 0;
	
	private int size;
	public Magic(int size) {
		super();
		this.size = size;
		numbers = new Integer[size][size];
		for (int i = 0; i < size; i++){ 
			for (int j = 0; j < size; j++) {
				numbers[i][j] = null;
			}
		}
		
		//initialize with first attempt
		numbers[0][0] = 1;
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
	
	boolean isDistinct(Integer[][] nums) {
		boolean[] flags = new boolean[size*size];
		for (Integer[] nums2 : nums) {
			for (Integer n : nums2) {
				if (n != null) {
				if (flags[n-1]) {
					return false;
				}
				else {
					flags[n-1] = true;
				}
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
	
	int sumOf(Integer[] nums) {
		Integer sum = 0;
		for (Integer n : nums) {
			if (n != null) {
				sum += n;
			}
		}
		return sum;
	}
	
	Integer[] sliceRow(int rowNum) {
		return numbers[rowNum];
	}
	
	Integer[] sliceColumn(int colNum) {
		Integer[] col = new Integer[size];
		for (int i = 0; i < size; i++){
			col[i] = numbers[i][colNum];
		}
		return col;
	}
	
	Integer[] sliceLeftDiag() {
		Integer[] col = new Integer[size];
		for (int i = 0; i < size; i++){
			col[i] = numbers[i][i];
		}
		return col;
	}
	
	Integer[] sliceRightDiag() {
		Integer[] col = new Integer[size];
		for (int i = 0; i < size; i++){
			col[i] = numbers[i][size-1-i];
		}
		return col;
	}
	
	@Override
	protected boolean isValid() {
		
		// has no repeated numbers
		if (!isDistinct(numbers)) {
			return false;
		}
		
		// all finished rows/cols/diagonals sum to same amount
		
		Integer completedSum = null;
		
		for (int i = 0; i < size; i++){
			Integer[] row = sliceRow(i);
			if (isComplete(row)) {
				int sum = sumOf(row);
				if (completedSum != null && !completedSum.equals(sum)) {
					return false;
				}
				else {
					completedSum = sum;
				}
			}
			
			Integer[] column = sliceColumn(i);
			if (isComplete(column)) {
				int sum = sumOf(column);
				if (completedSum != null && !completedSum.equals(sum)) {
					return false;
				}
				else {
					completedSum = sum;
				}
			}
		}
		
		Integer[] leftDiag = sliceLeftDiag();
		if (isComplete(leftDiag)) {
			int sum = sumOf(leftDiag);
			if (completedSum != null && !completedSum.equals(sum)) {
				return false;
			}
			else {
				completedSum = sum;
			}
		}
		
		Integer[] rightDiag = sliceRightDiag();
		if (isComplete(rightDiag)) {
			int sum = sumOf(rightDiag);
			if (completedSum != null && !completedSum.equals(sum)) {
				return false;
			}
			else {
				completedSum = sum;
			}
		}
		

		
		
		
		return true;
	}

	@Override
	protected boolean isFinished() {
		for (int i = 0; i < size; i++) {
			if (!isComplete(sliceRow(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void down() {
		
		int nextUpToX = ++upToX % size;
		int nextUpToY = nextUpToX == 0 ? upToY+1 : upToY;
		upToX = nextUpToX;
		upToY = nextUpToY;
		numbers[nextUpToX][nextUpToY] = 1;
	}

	@Override
	protected boolean sideways() {

		if ((numbers[upToX][upToY]).equals(size*size)) {
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
			nextUpToX = size - 1;
		}
		int nextUpToY = nextUpToX == (size-1) ? upToY-1 : upToY;
		
		numbers[upToX][upToY] = null;
		upToX = nextUpToX;
		upToY = nextUpToY;
		return true;
	}

}
