package knapsack.operators;


import java.util.List;

import knapsack.model.Solution;

public interface SelectionOperator {
	public List<Solution> selectPopulation(List<Solution> candidates, int populationSize);
}
