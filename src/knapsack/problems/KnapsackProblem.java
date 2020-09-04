package knapsack.problems;

import knapsack.model.NoSolutionException;
import knapsack.model.Problem;
import knapsack.model.Solution;

import java.util.Random;

public class KnapsackProblem implements Problem {

    private static int countGenerator = 0;
    private Item[] items;
    private int capacity;

    public KnapsackProblem(Item[] items, int capacity) {
        super();
        this.items = items;
        this.capacity = capacity;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getItemsLength() {
        return this.items.length;
    }

    public void display() {
        System.out.println("Knapsack problem");
        System.out.println("Capacity:" + capacity);
        System.out.println("Items :");
        for (Item item : items) {
            System.out.println("- " + item.toString());
        }
    }

    // we create a new solution completely random recursively ( if it fails to satisfy the capacity requirements we manually
    // adjust the weight to become lower iteratively )
    public Solution createNewSolution() throws NoSolutionException {
        int[] state = new int[items.length];
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < items.length; i++) {
            state[i] = rand.nextInt(2);
        }
		if (countGenerator > 10) {
			for (int i = 0; i < items.length; i++) {
				if (state[i] == 1) {
					// try to substract
					state[i] = 0;
					if(Util.isEligible(items, state, capacity)){
						KnapsackSolution solution = new KnapsackSolution(this);
						solution.setBackPackState(state);
						solution.setValue(Util.calculateValue(items, state));
						countGenerator = 0;
						return solution;
					}
				}
			}
		}
        if (Util.isEligible(items, state, capacity)) {
            KnapsackSolution solution = new KnapsackSolution(this);
            solution.setBackPackState(state);
			solution.setValue(Util.calculateValue(items, state));
            countGenerator = 0;
            return solution;
        } else {
            countGenerator++;
            createNewSolution();
        }
        return new KnapsackSolution(this);
    }

}
