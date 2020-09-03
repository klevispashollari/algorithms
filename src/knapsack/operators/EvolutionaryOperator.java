package knapsack.operators;

import java.util.List;

import knapsack.model.Solution;

public interface EvolutionaryOperator {
	public List<Solution> evolvePopulation(List<Solution> population) 
			throws EvolutionException;
}
