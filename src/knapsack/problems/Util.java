package knapsack.problems;

import java.util.Random;

public class Util {

    public static int tryMutation(int value, int[] array, Item[] items, int capacity) {
        int randomIndex = new Random().nextInt(array.length);
        if (array[randomIndex] == value) {
            int tmp = array[randomIndex];
            // will return 1 or 0 opposite
            array[randomIndex] = Math.abs(1 - value);
            if (isEligible(items, array, capacity)) {
                return randomIndex;
            }
        }
        return -1;
    }

    public static boolean isEligible(Item[] items, int[] state, int capacity) {
        int count = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 1)
                count += items[i].getWeight();
        }
        return capacity >= count;
    }

    public static int calculateValue(Item[] items, int[] state){
        int value = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 1)
                value += items[i].getValue();
        }
        return value;
    }

}
