package image;

import org.junit.Assert;
import org.junit.Test;

public class ImageProcesingTest {


    @Test
    public void convolveTest(){
        int[][] I = new int[][]{
                {0,1, 1, 1,0,0,0},
                {0,0, 1, 1,1,0,0},
                {0,0, 0, 1,1,1,0},
                {0,0, 0, 1,1,0,0},
                {0,0, 1, 1,0,0,0},
                {0,1, 1, 0,0,0,0},
                {1,1, 0, 0,0,0,0}};
        double[][] k = new double[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};
        int[][] result = new Kernel(k).convolve(I,7,7);
        Assert.assertEquals(result[0][2],3);
        Assert.assertEquals(result[0][3],4);
        Assert.assertEquals(result[0][0],1);
        Assert.assertEquals(result[4][4],0);
    }
}
