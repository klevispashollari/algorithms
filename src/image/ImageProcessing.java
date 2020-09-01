package image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessing {

	private static final String OUTPUT_GREY_IMAGE = "./docs/images/GrayScale.jpg";
	private static final String INPUT_IMAGE = "./docs/Step1/assets/Tiles/grassMid.png";

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
		BufferedImage bufferedImage = new BufferedImage(img.length, img[0].length, BufferedImage.TYPE_INT_RGB);
		for(int i=0; i<img.length; i++) {
			for(int j=0; j<img[i].length; j++) {
				int a = img[i][j];
				Color newColor = new Color(a,a,a);
				bufferedImage.setRGB(j,i,newColor.getRGB());
			}
		}
		try {
			File output = new File(OUTPUT_GREY_IMAGE);
			ImageIO.write(bufferedImage, "jpg", output);
		}catch (IOException e){
			System.out.println(e.getMessage());
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
	private static BufferedImage filter(BufferedImage img, Kernel kernel) {

		// TODO

		return null;
	}

	public static void main(String[] args) throws IOException {

		BufferedImage image = ImageIO.read(new File(INPUT_IMAGE));
		int mask = (1 << 24) - 1;
		int p = image.getRGB(0, 0) ;
		//System.out.println(Integer.toBinaryString(p));
		int p1 = p >>> 24;
		int p3 = p1 << 24;
		int p4 = p1^p3;
        int p2 = p & p3;
		System.out.println(Integer.toBinaryString(p3));
		System.out.println(Integer.toBinaryString(p4&mask));
		System.out.println(Integer.toBinaryString(p1));
		System.out.println(Integer.toBinaryString(~p2));
		//convertToBufferedImage(convertToGrayScaleArray(image));

	}

}
