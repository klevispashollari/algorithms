package knapsack.problems;

import java.util.List;

import knapsack.model.Solution;
import knapsack.operators.FitnessEvaluator;

public class KnapsackFitnessEvaluator implements FitnessEvaluator<KnapsackSolution>{

	@Override
	public void evaluate(List<KnapsackSolution> population) {
		population.stream().map(KnapsackSolution::calculateFitness);
		
	}

}
