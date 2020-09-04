package knapsack.problems;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import knapsack.model.Solution;
import knapsack.operators.FitnessEvaluator;

public class KnapsackFitnessEvaluator implements FitnessEvaluator {

	private Item[] items;

	public KnapsackFitnessEvaluator(Item[] items) {
		this.items = items;
	}

	@Override
	public void evaluate(List<Solution> population) {
		// TODO Auto-generated method stub
		// We find the best solution for the generation by sorting the list using streams.max(Comparator);
		Optional<Solution> best = population.stream().max(Comparator.comparingInt(o -> ((KnapsackSolution) o).getValue()));
		System.out.println("Fittest for the generation => " + ((KnapsackSolution)best.get()).value);
	}

}
