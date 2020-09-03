package knapsack.model;

import knapsack.problems.Item;

import java.util.ArrayList;
import java.util.List;

public abstract class Solution {
	
	private Problem problem;
	// list of items to put in the bag to have the maximal value
	protected List<Item> backPackItems;
	private double fitness;
	// maximal value possible
	protected int value;
	protected   int capacity;
	protected   Item[] items;
	
	public Solution(Problem problem) {
		this.problem = problem;
		this.backPackItems = new ArrayList<>();
		this.capacity = problem.getCapacity();
		this.items = problem.getItems();
	}
	
	public Solution(Solution toCopy) {
		this.problem = toCopy.getProblem();
		this.fitness = toCopy.getFitness();
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

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public Problem getProblem() {
		return problem;
	}	
}
