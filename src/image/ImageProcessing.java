package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing {

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
				pixels[i][j] = (int) (0.299*red + 0.587*green + 0.144*blue);
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

		return null;
	}

	/**
	 * Converts input image to gray scale and applies the kernel.
	 * 
	 * @param img    RGB input image
	 * @param kernel
	 * @return convolved gray-scale image
	 */
	private static BufferedImage filter(BufferedImage img, Kernel kernel) {

		// TODO

		return null;
	}

	public static void main(String[] args) throws IOException {
		String fileName = "./docs/Step1/assets/Tiles/grassMid.png";
		BufferedImage immage = ImageIO.read(new File(fileName));
		System.out.println(immage.getRGB(0, 0));

	}

}
