package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Level extends JFrame {
	private static final long serialVersionUID = 1L;
	private final String tileFileName;
	private final String levelFileName;
	private final String seaFileName;

	public Level(String tileFileName, String levelFileName, String seaFileName) {
		this.setSize(3500, 350);
		this.setVisible(true);
		this.levelFileName = levelFileName;
		this.tileFileName = tileFileName;
		this.seaFileName = seaFileName;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Level("./docs/Step1/assets/Tiles/grassMid.png", "./docs/Step1/level1.bmp",
				"./docs/Step1/assets/Tiles/liquidWaterTop_mid.png");
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g2D = (Graphics2D) graphics;
		BufferedImage image;
		BufferedImage seaImage;
		BufferedImage tileImage;
		try {
			image = ImageIO.read(new File(levelFileName));
			g2D.drawImage(image, 0, 0, 3500, 350, null);
			tileImage = ImageIO.read(new File(tileFileName));
			seaImage = ImageIO.read(new File(seaFileName));
			for(int y = 0; y< image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					Color color = new Color(image.getRGB(x, y));
					if(color.equals(Color.BLACK)) {
						g2D.drawImage(tileImage, x*70, y*70,null);
					}
					if(color.equals(Color.BLUE)) {
						g2D.drawImage(seaImage, x*70, y*70,null);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
