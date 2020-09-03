package knapsack.problems;

import knapsack.model.NoSolutionException;
import knapsack.model.Problem;
import knapsack.model.Solution;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class KnapsackSolution extends Solution {

	public KnapsackSolution(Problem problem) {
		super(problem);
	}

	public Solution solve() throws NoSolutionException {

		checkIfCanAddItems();
		int nbItems = items.length;
		// we use a matrix to store the max value at each n-th item
		int[][] matrix = new int[nbItems + 1][capacity + 1];

		// first line is initialized to 0
		for (int i = 0; i <= capacity; i++)
			matrix[0][i] = 0;

		// we iterate on items
		for (int i = 1; i <= nbItems; i++) {
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

		int res = matrix[nbItems][capacity];
		int w = capacity;

		for (int i = nbItems; i > 0 && res > 0; i--) {
			if (res != matrix[i - 1][w]) {
				backPackItems.add(items[i - 1]);
				value+=items[i-1].getValue();
				// we remove items value and weight
				res -= items[i - 1].getValue();
				w -= items[i - 1].getWeight();
			}
		}

		return this;
	}


	private void checkIfCanAddItems() throws NoSolutionException {
		Predicate<Integer> lessWeightItemsThanBackPacCapacity = (w)-> w<=this.capacity;
		if(!Stream.of(items).map(Item::getWeight).anyMatch(lessWeightItemsThanBackPacCapacity)){
			throw  new NoSolutionException(String.format("No item found with lower weight than backpack capacity: %d",capacity));
		}
	}

	public List<Item> getBackPackItems() {
		return backPackItems;
	}
}