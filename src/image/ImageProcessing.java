package image;


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
		
		// TODO
		
		return null;
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
