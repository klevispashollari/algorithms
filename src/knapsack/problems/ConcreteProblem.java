package knapsack.problems;

import knapsack.model.NoSolutionException;
import knapsack.model.Solution;
import knapsack.operators.EvolutionException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ConcreteProblem {

    private final Item[] items;

    private final int capacity;

    public ConcreteProblem(Item[] items, int capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    // Ausführung einer Optimierung
    // this is the main entry point where we create the data -> 4 items as below
    // we instantiate a new Concrete Problem and apply run method with number of iterations = 10 as requested
    public static void main(String[] args) throws NoSolutionException, EvolutionException, CloneNotSupportedException {
        // we take the same instance of the problem displayed in the image
        Item[] items = {new Item("g1", 10, 5), new Item("g2", 8, 3), new Item("g3", 5, 3),
                new Item("g4", 6, 2)};
        ConcreteProblem concreteProblem = new ConcreteProblem(items, 11);
        concreteProblem.run(10, 4);
    }

    // we initialize 3 objects => the mutator, fitness evaluator and the Knapsack problem
    public void run(int iterations, int populationSize) throws NoSolutionException, EvolutionException, CloneNotSupportedException {
        // if there is no way to add items throw exception ( interrupt everything)
        checkIfCanAddItems();

        KnapsackMutation knapsackMutation = new KnapsackMutation(items, capacity);
        KnapsackProblem knapsack = new KnapsackProblem(items, capacity);
        KnapsackFitnessEvaluator evaluator = new KnapsackFitnessEvaluator(items);
        knapsack.display();

        List<Solution> solutions = new ArrayList<>();

        // we find an initial solution and add it to the solutions list
        Solution solution = knapsack.createNewSolution();
        solutions.add(solution);
        for (int i = 0; i < iterations; i++) {
            // we evolve solutions and add them to the current solutions list
            List<Solution> currentSolutions = knapsackMutation.evolvePopulation(solutions);
            // find the fittest solution of this generation i
            evaluator.evaluate(currentSolutions);
            // add newly found solutions to the existing solutions
            solutions.addAll(currentSolutions);
            System.out.println(solutions);
        }
    }

    private void checkIfCanAddItems() throws NoSolutionException {
        // we check every item if it has less capacity than max capacity given
        // if none is found we throw no solution exception
        Predicate<Integer> lessWeightItemsThanBackPacCapacity = (w) -> w <= this.capacity;
        if (Stream.of(items).map(Item::getWeight).noneMatch(lessWeightItemsThanBackPacCapacity)) {
            throw new NoSolutionException(String.format("No item found with lower weight than backpack capacity: %d", capacity));
        }
    }

}
