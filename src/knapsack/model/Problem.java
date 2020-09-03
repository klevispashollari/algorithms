package knapsack.model;

import knapsack.problems.Item;

public interface Problem {
	public Solution createNewSolution() throws NoSolutionException;
}
