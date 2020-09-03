package knapsack.problems;

public class Item {

	private String name;
	private int value;
	private int weight;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Item(String name, int value, int weight) {
		super();
		this.name = name;
		this.value = value;
		this.weight = weight;
	}

	public Item() {
		super();
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", value=" + value + ", weight=" + weight + "]";
	}

}
