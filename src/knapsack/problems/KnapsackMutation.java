package knapsack.problems;

import java.util.ArrayList;
import java.util.List;

import knapsack.model.Solution;
import knapsack.operators.EvolutionException;
import knapsack.operators.EvolutionaryOperator;

public class KnapsackMutation implements EvolutionaryOperator {


	@Override
	public List<Solution> evolvePopulation(List<Solution> population) throws EvolutionException {
		List<Solution> addedSolutions = new ArrayList<>();
		
		for (Solution solution : population) {
			
		}
		
		return population;
	}

}
