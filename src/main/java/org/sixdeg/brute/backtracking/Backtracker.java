package org.sixdeg.brute.backtracking;

public abstract class Backtracker {

    // Class that models trial and backtracking over a graph of 
    // possible solutions to a problem.  We start off at one 
    // solution (the 'top' of the graph), and the complete valid
    // solution is at the 'bottom'.  We can get from the top
    // to the bottom via a series of moves through 'valid'
    // solutions (the definition of valid of course depends on the
    // problem being solved).  We can move down, sideways or up.
    // When we reach a valid solution that is also 'complete',
    // we terminate.
    
	// 
	protected abstract boolean isValid();
	
	// subject to this being valid, is it finished
	protected abstract boolean isFinished();
	
	// I'm in a valid state, so make a step down to the next layer
	protected abstract void down();
	
	// I'm in an invalid state so explore the next state in this layer
	protected abstract boolean sideways();
	
	// I'm in an invalid state and want to backtrack up a layer
	protected abstract boolean up();
	
	void next() {
		boolean carryOn = true;
		while (carryOn) {
		boolean sidewaysAttempt = sideways();
		if (sidewaysAttempt) {
			carryOn = false;
			continue;
		}
		boolean upAttempt = up();
		
		if (upAttempt) {
			continue;
		}
		
		throw new RuntimeException("Run out of options!");
		}
	}
	
	public void attempt() {
		boolean carryOn = true;
		while (carryOn) {
		if (isValid()) {
			if (isFinished()) {
				return;
			}
			else {
				down();
			}
		}
		else {
				next();
		}
		}
	}
	
}
