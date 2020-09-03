package knapsack.problems;

import knapsack.operators.EvolutionException;
import knapsack.operators.EvolutionaryOperator;

import java.util.List;
import java.util.stream.Collectors;

public class EvolutionaryOperatorKnapsack implements EvolutionaryOperator<KnapsackSolution> {

    @Override
    public List<KnapsackSolution> evolvePopulation(List<KnapsackSolution> population) throws EvolutionException {
        return population.stream().map(KnapsackSolution::mutate).map(KnapsackSolution::new).collect(Collectors.toList());
    }
}
