package knapsack.problems;

import knapsack.model.NoSolutionException;
import knapsack.model.Problem;
import knapsack.model.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		return new KnapsackSolution(this,4);
	}



	public static void main(String[] args) throws NoSolutionException {
		// we take the same instance of the problem displayed in the image
		Item[] items = { new Item("g1", 10, 5),
				         new Item("g2", 8, 3),
				         new Item("g4", 6, 2),
				         new Item("g1", 4, 3)
		               };

		KnapsackProblem knapsack = new KnapsackProblem(items, 8);
		knapsack.display();
        knapsack.createNewSolution();
//		solution.display();


	}
}
