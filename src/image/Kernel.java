package image;

/**
 * A kernel is a 2D-array. The array is transposed after initialization which
 * enables a more intuitive way of initializing a kernel. E.g a non-symmetric
 * kernel can be initialized by Kernel({{0,0,1} {0,1,0} {1,0,0}}) although the
 * array dimensions are actually [height][width].
 */
public class Kernel {

    private double[][] k;
    private int height;
    private int width;

    /**
     * Initializes the kernel by its transpose.
     *
     * @param k
     */
    Kernel(double[][] k) {
        // transpose
        this.k = new double[k.length][k[0].length]; // swap
        for (int x = 0; x < k[0].length; x++)
            for (int y = 0; y < k.length; y++)
                this.k[y][x] = k[x][y];
        this.width = this.k.length;
        this.height = this.k[0].length;

        if (this.width % 2 != 1 || this.height % 2 != 1)
            throw new IllegalArgumentException("Kernel size need to be odd-numbered");
        if (this.width < 3 || this.height < 3)
            throw new IllegalArgumentException("Minimum dimension is 3");
    }

    /**
     * Convolves a single-channel image with the kernel.
     *
     * @param img A single-channel image
     * @return The convolved image
     */
    public int[][] convolve(int[][] img,int width ,int height) {
        int smallWidth = width - getWidth() + 1;
        int smallHeight = height - getHeight() + 1;
        int[][] output = new int[smallWidth][smallHeight];
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = 0;
            }
        }
        for (int i = 0; i < smallWidth; ++i) {
            for (int j = 0; j < smallHeight; ++j) {
                output[i][j] = singlePixelConvolution(img, i, j, k, getWidth(), getHeight());
            }
        }
        return output;
    }

    public static int singlePixelConvolution(int[][] input,
                                             int x, int y,
                                             double[][] k,
                                             int kernelWidth,
                                             int kernelHeight) {
        int output = 0;
        for (int i = 0; i < kernelWidth; ++i) {
            for (int j = 0; j < kernelHeight; ++j) {
                output = output + (int) (input[x + i][y + j] * k[i][j]);
            }
        }
        return output;
    }

    public double[][] getK() {
        return k;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
