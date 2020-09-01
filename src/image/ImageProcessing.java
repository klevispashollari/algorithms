package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class ImageProcessing {

	private static final String OUTPUT_GREY_IMAGE = "./docs/images/GrayScale.png";
	private static final String OUTPUT_FILTERED_IMAGE = "./docs/images/filtered.png";
	private static final String OUTPUT_CONVOLVE_IMAGE = "./docs/images/convolve.png";
	private static final String INPUT_IMAGE = "./docs/Step1/assets/Tiles/lock_blue.png";

	/**
	 * Converts the input RGB image to a single-channel gray scale array.
	 *
	 * @param img The input RGB image
	 * @return A 2-D array with intensities
	 */
	private static int[][] convertToGrayScaleArray(BufferedImage img) {
		int[][] pixels = new int[img.getHeight()][img.getWidth()];

		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				Color color = new Color(img.getRGB(i, j));
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				pixels[i][j] = (int) (0.299 * red + 0.587 * green + 0.144 * blue);
			}
		}

		return pixels;
	}

	/**
	 * Converts a single-channel (gray scale) array to an RGB image.
	 *
	 * @param img
	 * @return BufferedImage
	 */
	private static BufferedImage convertToBufferedImage(int[][] img) {

		// TODO
		BufferedImage bufferedImage = new BufferedImage(img.length, img[0].length, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[i].length; j++) {
				int a = img[i][j];
				int p1 = a << 8;
				int p2 = p1 >>> 8;
				Color newColor = new Color(p2, p2, p2);
				bufferedImage.setRGB(j, i, newColor.getRGB());
			}
		}
		try {
			File output = new File(OUTPUT_GREY_IMAGE);
			ImageIO.write(bufferedImage, "png", output);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e.getCause());
		}

		return bufferedImage;
	}

	/**
	 * Converts input image to gray scale and applies the kernel.
	 *
	 * @param img    RGB input image
	 * @param kernel
	 * @return convolved gray-scale image
	 */
	private static BufferedImage filter(BufferedImage img, Kernel kernel) throws IOException {
		int width = img.getWidth();
		int height = img.getHeight();
		int kLength = kernel.getWidth();
		double[][] kMatrix = kernel.getK();
		double multiFactor = 1.0;
		BufferedImage output = new BufferedImage(width, height, img.getType());
		if (img == null) {
			throw new NullPointerException("src image is null");
		}
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				float red = 0f, green = 0f, blue = 0f;
				for (int i = 0; i < kLength; i++) {
					for (int j = 0; j < kLength; j++) {
						// Calculating X and Y coordinates of the pixel to be multiplied with current kernel element
						// In case of edges of image the '% WIDTH' wraps the image and the pixel from opposite edge is used
						int imageX = (x - kLength / 2 + i + width) % width;
						int imageY = (y - kLength / 2 + j + height) % height;

						int RGB = img.getRGB(imageX, imageY);
						int R = (RGB >> 16) & 0xff; // Red Value
						int G = (RGB >> 8) & 0xff;    // Green Value
						int B = (RGB) & 0xff;        // Blue Value

						// The RGB is multiplied with current kernel element and added on to the variables red, blue and green
						red += (R * kMatrix[i][j]);
						green += (G * kMatrix[i][j]);
						blue += (B * kMatrix[i][j]);
					}
				}
				int outR, outG, outB;
				// The value is truncated to 0 and 255 if it goes beyond
				outR = Math.min(Math.max((int) (red * multiFactor), 0), 255);
				outG = Math.min(Math.max((int) (green * multiFactor), 0), 255);
				outB = Math.min(Math.max((int) (blue * multiFactor), 0), 255);
				// Pixel is written to output image
				output.setRGB(x, y, new Color(outR, outG, outB).getRGB());
			}
		}
		ImageIO.write(output, "PNG", new File(OUTPUT_FILTERED_IMAGE));
		return output;
	}

	public static void main(String[] args) throws IOException {
		double[][] k = new double[][]{{0, 0, 1}, {0, 1, 0}, {1, 0, 0}};
		BufferedImage image = ImageIO.read(new File(INPUT_IMAGE));
		System.out.println(image.getRGB(0, 0));
		convertToBufferedImage(convertToGrayScaleArray(image));
		pixelsToImage(new Kernel(k).convolve(imageToPixels(image),image.getWidth(),image.getHeight()));
		BufferedImage imageFiltered = filter(image, Kernels.MotionBlur());

	}

	public static int[][] imageToPixels(BufferedImage image) {
		if (image == null) {
			throw new IllegalArgumentException();
		}

		int width = image.getWidth();
		int height = image.getHeight();
		int[][] pixels = new int[height][width];
		for (int row = 0; row < height; row++) {
			image.getRGB(0, row, width, 1, pixels[row], 0, width);
		}
		return pixels;
	}
	public static BufferedImage pixelsToImage(int[][] pixels) throws IOException {

		if (pixels == null) {
			throw new IllegalArgumentException();
		}

		int width = pixels[0].length;
		int height = pixels.length;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int row = 0; row < height; row++) {
			image.setRGB(0, row, width, 1, pixels[row], 0, width);
		}
		ImageIO.write(image,"PNG",new File(OUTPUT_CONVOLVE_IMAGE));
		return image;
	}

}
