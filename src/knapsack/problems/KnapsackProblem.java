package knapsack.problems;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {

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

	public KnapsackSolution createNewSolution() {
		int NB_ITEMS = items.length;
		// we use a matrix to store the max value at each n-th item
		int[][] matrix = new int[NB_ITEMS + 1][capacity + 1];

		// first line is initialized to 0
		for (int i = 0; i <= capacity; i++)
			matrix[0][i] = 0;

		// we iterate on items
		for (int i = 1; i <= NB_ITEMS; i++) {
			// we iterate on each capacity
			for (int j = 0; j <= capacity; j++) {
				if (items[i - 1].getWeight() > j)
					matrix[i][j] = matrix[i - 1][j];
				else
					// we maximize value at this rank in the matrix
					matrix[i][j] = Math.max(matrix[i - 1][j],
							matrix[i - 1][j - items[i - 1].getWeight()] + items[i - 1].getValue());
			}
		}

		int res = matrix[NB_ITEMS][capacity];
		int w = capacity;
		List<Item> itemsSolution = new ArrayList<>();

		for (int i = NB_ITEMS; i > 0 && res > 0; i--) {
			if (res != matrix[i - 1][w]) {
				itemsSolution.add(items[i - 1]);
				// we remove items value and weight
				res -= items[i - 1].getValue();
				w -= items[i - 1].getWeight();
			}
		}

		return new KnapsackSolution(itemsSolution, matrix[NB_ITEMS][capacity]);
	}

	public static void main(String[] args) {
		// we take the same instance of the problem displayed in the image
		Item[] items = { new Item("Elt1", 4, 12), new Item("Elt2", 2, 1), new Item("Elt3", 2, 2),
				new Item("Elt4", 1, 1), new Item("Elt5", 10, 4) };

		KnapsackProblem knapsack = new KnapsackProblem(items, 15);
		knapsack.display();
		KnapsackSolution solution = knapsack.createNewSolution();
		solution.display();
	}
}
