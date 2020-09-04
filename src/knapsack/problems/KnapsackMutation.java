package knapsack.problems;

import knapsack.model.Solution;
import knapsack.operators.EvolutionException;
import knapsack.operators.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KnapsackMutation implements EvolutionaryOperator {

    private final Item[] items;
    private final int capacity;

    public KnapsackMutation(Item[] items, int capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    @Override
    public List<Solution> evolvePopulation(List<Solution> population) throws EvolutionException, CloneNotSupportedException {
        List<Solution> result = new ArrayList<>();
        // for each existing solution we try to evolve a new solution by applying
        // 1. AddingMutator -> which adds a random item in the bag which is not picked
        // 2. RemovingMutator -> which removes a random item from the bag which is picked
        // if neither of these operations return a valid BAG ( total weight <= capacity) we throw exception
        for (Solution solution : population) {
            int notFoundCount = 0;
                AddingMutator addingMutator = new AddingMutator(solution);
                Solution mutatedSolution = addingMutator.mutate(items, capacity);
                if (mutatedSolution == null) {
                    notFoundCount++;
                    // try remove if add does not work
                    RemovingMutator removingMutator = new RemovingMutator(solution);
                     mutatedSolution = removingMutator.mutate(items, capacity);
                    if (mutatedSolution == null) {
                        notFoundCount++;
                    } else {
                        result.add(mutatedSolution);
                    }
                } else {
                    result.add(mutatedSolution);
                }
            if ( notFoundCount == 2) {
                throw new EvolutionException("Cannot evolve!");
            }
        }
        return result;
    }

    // this is an inner class which takes a solution in the constructor arg and mutates this solution
    // mutation is of type add
    private class AddingMutator {

        private final KnapsackSolution solution;
        private int count = 0;

        public AddingMutator(Solution solution) throws CloneNotSupportedException {
            this.solution = (KnapsackSolution) ((KnapsackSolution) solution).clone();
        }

        public Solution mutate(Item[] items, int capacity) {
            if (solution != null) {
                int[] state = solution.getBackPackState();
                int index = Util.tryMutation(0, state, items, capacity);
                if(count>10){
                    return null;
                }
                if (index == -1) {
                    count++;
                    return mutate(items, capacity);
                }
                state[index] = 1;
                this.solution.setBackPackState(state);
                this.solution.setValue(Util.calculateValue(items, state));
                return this.solution;
            }
            return null;
        }
    }

    // this is an inner class which takes a solution in the constructor arg and mutates this solution
    // mutation is of type remove
    private class RemovingMutator {

        private final KnapsackSolution solution;
        private int count = 0;

        public RemovingMutator(Solution solution) throws CloneNotSupportedException {
            this.solution = (KnapsackSolution) ((KnapsackSolution) solution).clone();

        }

        public Solution mutate(Item[] items, int capacity) {
            if (solution != null) {
                int[] state = solution.getBackPackState();
                int index = Util.tryMutation(1, state, items, capacity);
                if(count>10){
                    return null;
                }
                if (index == -1) {
                    count++;
                    return mutate(items, capacity);
                }
                state[index] = 0;
                this.solution.setBackPackState(state);
                this.solution.setValue(Util.calculateValue(items, state));
                return this.solution;
            }
            return null;
        }
    }

}
