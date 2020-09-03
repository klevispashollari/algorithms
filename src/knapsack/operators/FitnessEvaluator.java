package knapsack.operators;


import java.util.List;

import knapsack.model.Solution;

public interface FitnessEvaluator<T extends Solution> {
	public void evaluate(List<T> population);
}
