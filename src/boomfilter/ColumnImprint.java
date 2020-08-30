package boomfilter;

public class ColumnImprint extends BloomFilter<Double> {
    // TODO

    public ColumnImprint(int numBits, double min, double max) {
        super(numBits,DefaultHashFunctionGenerator.hashFunctions(2));
    }



}