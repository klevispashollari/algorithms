package knapsack.operators;


import java.util.List;

import knapsack.model.Solution;

public interface FitnessEvaluator {
	public void evaluate(List<Solution> population);
}
