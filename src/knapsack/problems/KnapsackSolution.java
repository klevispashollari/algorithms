package knapsack.problems;

import knapsack.model.Problem;
import knapsack.model.Solution;

import java.util.Arrays;

public class KnapsackSolution extends Solution implements Cloneable {

    private int[] backPackState;

    int value;

    public KnapsackSolution(Problem problem) {
        super(problem);
        backPackState = new int[((KnapsackProblem) problem).getItemsLength()];
    }

    public int[] getBackPackState() {
        return backPackState;
    }

    public void setBackPackState(int[] solution) {
        this.backPackState = solution;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KnapsackSolution{" +
                "solution=" + Arrays.toString(backPackState) +
                '}';
    }

    public Object clone() throws CloneNotSupportedException {
        KnapsackSolution result = new KnapsackSolution(this.getProblem());
        result.setBackPackState(Arrays.copyOf(this.backPackState, this.backPackState.length));
        result.setValue(Util.calculateValue( ((KnapsackProblem)this.getProblem()).getItems(), backPackState));
        return result;
    }
}