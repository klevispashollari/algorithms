package knapsack.model;

public interface Problem {
	public Solution createNewSolution() throws NoSolutionException;
}
