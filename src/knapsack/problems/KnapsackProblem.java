package knapsack.problems;

import knapsack.model.NoSolutionException;
import knapsack.model.Problem;
import knapsack.model.Solution;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem implements Problem {

	private Item[] items;
	private int capacity;

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

	public KnapsackProblem(Item[] items, int capacity) {
		super();
		this.items = items;
		this.capacity = capacity;
	}

	public void display() {
		System.out.println("Knapsack problem");
		System.out.println("Capacity:" + capacity);
		System.out.println("Items :");

		for (Item item : items) {
			System.out.println("- " + item.toString());
		}
	}

	public Solution createNewSolution() throws NoSolutionException {
		return new KnapsackSolution(this).solve();
	}

	public static void main(String[] args) throws NoSolutionException {
		// we take the same instance of the problem displayed in the image
		Item[] items = { new Item("Elt1", 4, 12), new Item("Elt2", 2, 10), new Item("Elt3", 2, 20),
				new Item("Elt4", 1, 10), new Item("Elt5", 10, 40) };

		KnapsackProblem knapsack = new KnapsackProblem(items, 19);
		knapsack.display();
		Solution solution = knapsack.createNewSolution();
		solution.display();
	}
}
