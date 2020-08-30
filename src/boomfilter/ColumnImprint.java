package boomfilter;

public class ColumnImprint extends BloomFilter<Double>  implements HashFunction<Double>{

	private double min;
	private double max;
	private double bucketSize;
	
    public ColumnImprint(int numBits, double min, double max) {
        super(numBits,DefaultHashFunctionGenerator.hashFunctions(2));
        this.min = min;
        this.max = max;
        this.bucketSize = numBits;
    }

	@Override
	public int hash(Double element) {

		if(element >= this.min && element < this.min+this.bucketSize ) {
			return 0;
		} else if (element >=this.max - this.bucketSize && element < this.max ) {
			//TODO
			return 0;
		} else if (element > this.min + this.bucketSize && element < this.max - this.bucketSize) {
			return (int) Math.abs((element - this.min)/this.bucketSize);
		} else {
			return 0;
		}
		
		
	}



}