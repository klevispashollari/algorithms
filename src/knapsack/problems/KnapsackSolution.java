package knapsack.problems;

import knapsack.model.NoSolutionException;
import knapsack.model.Problem;
import knapsack.model.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KnapsackSolution extends Solution {
    // maximal value possible
    private int value;
    private int capacity;
    private Item[] items;
    private int itemsSize;
    // list of items to put in the bag to have the maximal value
    private List<Item> backPackItems;
    private List<int[]> candidateSolutions;
    private int[] solution;
    private int candidateNumber;
    private double mutationProbability = 0.015;
    private double crossOverProbability = 0.6;

    public KnapsackSolution(Problem problem, int candidateNumber) throws NoSolutionException {
        super(problem);
        this.backPackItems = new ArrayList<>();
        this.capacity = ((KnapsackProblem) problem).getCapacity();
        this.items = ((KnapsackProblem) problem).getItems();
        this.itemsSize = items.length;
        solution= new int[itemsSize];
        candidateSolutions = new ArrayList<>();
        this.candidateNumber = candidateNumber;
        // Initialize first generation
        generateSolutions(candidateNumber);

        // Run the GA until required fitness achieved or
        for(int k = 0; k < 10 ; k++){
            System.out.print("Generation: " + (k+1));
            System.out.print(" " + "Best solution:" + Arrays.toString(getBestSolution()));
            System.out.println(" " + "Best value:" + getFitness());
            newGen();
        }
    }

    public KnapsackSolution(KnapsackSolution toCopy) {
        super(toCopy);
        this.solution = toCopy.getSolution();
    }

    public void display() {
        if (backPackItems != null && !backPackItems.isEmpty()) {
            System.out.println("\nKnapsack solution");
            System.out.println("Value = " + value);
            System.out.println("Items to pick :");

            for (Item item : backPackItems) {
                System.out.println("- " + item.toString());
            }
        }
    }

    private void generateSolutions(int candidateNumber) {
        // We initialize a new Random object
        Random rand = new Random(System.currentTimeMillis());
        int[] candidate;
        // For each candidate solution
        for (int i = 0; i < candidateNumber; i++) {
            candidate = new int[itemsSize];
            // Randomly assign 1 or 0 to the item to take in the knapsack
            // 1 => the respective item is taken
            // 0 => we leave the respective item
            for (int j = 0; j < itemsSize; j++) {
                candidate[j] = rand.nextInt(2);
            }
            // Add the randomly formed candidate solution to the generation
            candidateSolutions.add(candidate);
        }
    }

    // This method returns the best solution out of the current generation
    private int[] getBestSolution() throws NoSolutionException {
        double bestFit = -1;
        int[] bestSolution = null;

        // Iterate over all the generation
        for (int[] c : candidateSolutions) {
            double newFit = calculateFitness(c);
            if (newFit != -1 && newFit >= bestFit) {
                // If a better fit found
                // update bestSol variable
                    bestSolution = c;
                    bestFit = newFit;
            }
        }
        if(bestFit==-1){
            throw new NoSolutionException("No solution found!");
        }
        setFitness(bestFit);
        // Return the best candidate solution
        return bestSolution;
    }


    // This method calculates the fitness of a given candidate solution
    public double calculateFitness(int[] solution) {
        double fit = 0;
        int weight = 0;
        // We iterate over all the candidate solution genes.
        for (int i = 0; i < solution.length; i++) {
            // If gene is 1, respective item is taken into the knapsack
            if (solution[i] == 1) {
                // Increment the weight and the value of taking this
                // item in the sack
                weight += items[i].getWeight();
                fit += items[i].getValue();
            }
        }
        // If we did not surpass the knapsack size
        // return the fitness for this candidate solution
        // OTHERWISE return -1
        return weight <= capacity ? fit : -1;
    }

    // This method calculates the fitness of a given candidate solution
    public double calculateFitness() {
        double fit = 0;
        int weight = 0;
        // We iterate over all the candidate solution genes.
        for (int i = 0; i < solution.length; i++) {
            // If gene is 1, respective item is taken into the knapsack
            if (solution[i] == 1) {
                // Increment the weight and the value of taking this
                // item in the sack
                weight += items[i].getWeight();
                fit += items[i].getValue();
            }
        }
        // If we did not surpass the knapsack size
        // return the fitness for this candidate solution
        // OTHERWISE return -1
        return weight <= capacity ? fit : -1;
    }

    // This method takes the 1st and 2nd best candidate solutions
    // and creates a new generation through crossovering them
    // and mutating the new generation
    private void newGen() throws NoSolutionException {
        // Get first best candidate solution
        int[] can1 = getBestSolution();

        // Remove it from the list of solutions so we can select the 2nd best one
        candidateSolutions.remove(can1);

        // Get first best candidate solution
        int[] can2 = getBestSolution();

        // Create new generation
        candidateSolutions = new ArrayList<>();

        // Add the best solution thus far
        candidateSolutions.add(can1);

        // Create all new off springs from crossover and mutation
        for (int i = 1; i < candidateNumber; i++) {
            candidateSolutions.add(mutate(crossOver(can1, can2)));
        }
    }

    // This method generates new offsprings from two potentially best solutions
    private int[] crossOver(int[] candidate1, int[] candidate2) {
        // This will hold the resulting crossed over offspring
        int[] crossedOver = new int[itemsSize];

        // Iterate over the genes of a chromosome
        for (int i = 0; i < candidate1.length; i++) {
            // Checking randomly whether to add to the resulting offspring
            // a gene from candidate1 or from candidate2
            if (Math.random() >= crossOverProbability) {
                crossedOver[i] = candidate1[i];
            } else {
                crossedOver[i] = candidate2[i];
            }
        }

        return crossedOver;
    }

    // This method mutates the genes of the given candidate solution
    // based on the mutation probability
    public int[] mutate(int[] candidate) {
        // Iterate over the genes of the candidate
        for (int i = 0; i < candidate.length; i++) {
            // Checking randomly whether to mutate the current
            // gene of the iteration
            if (Math.random() <= mutationProbability)
                candidate = changeBit(i, candidate);
        }

        // Return the mutated candidate solution
        return candidate;
    }

    // This method mutates the genes of the given candidate solution
    // based on the mutation probability
    public KnapsackSolution mutate() {
        // Iterate over the genes of the candidate
        for (int i = 0; i < solution.length; i++) {
            // Checking randomly whether to mutate the current
            // gene of the iteration
            if (Math.random() <= mutationProbability) {
                solution = changeBit(i, solution);
            }
        }

        // Return the mutated candidate solution
        return this;
    }

    private int[] changeBit(int idx, int[] candidate) {
        int[] result = new int[itemsSize];
        for (int i = 0; i < candidate.length; i++) {
            if (i == idx) {
                if (candidate[i] == 1) {
                    result[i] = 0;
                } else {
                    result[i] = 1;
                }
            } else {
                result[i] = candidate[i];
            }
        }
        return result;
    }

    public List<Item> getBackPackItems() {
        return backPackItems;
    }

    public int[] getSolution() {
        return solution;
    }
}