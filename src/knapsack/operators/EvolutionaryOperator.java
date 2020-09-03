package knapsack.operators;

import java.util.List;

import knapsack.model.Solution;

public interface EvolutionaryOperator<T extends Solution> {
	public List<T> evolvePopulation(List<T> population) throws EvolutionException;
}
