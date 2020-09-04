package knapsack.model;

public abstract class Solution {

    private Problem problem;
    private double fitness;

    public Solution(Problem problem) {
        this.problem = problem;
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

    @Override
    public String toString() {
        return "Solution{" +
                "problem=" + problem +
                ", fitness=" + fitness +
                '}';
    }
}
