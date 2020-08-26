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
		new Level("./docs/Step1/assets/Tiles/grassMid.png", "./docs/Step1/level1.bmp", "./docs/Step1/assets/Tiles/liquidWaterTop_mid.png");
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g2D = (Graphics2D) graphics;
		BufferedImage image;
		BufferedImage seaImage;
		try {
			image = ImageIO.read(new File(levelFileName));
			g2D.drawImage(image, 0, 0, 3500, 350, null);
            g2D.setColor(Color.BLACK);
            g2D.fillRect(0,0,3500,350);
			for (int i = 0; i < 70; i++) {
				image = ImageIO.read(new File(tileFileName));
				seaImage = ImageIO.read(new File(seaFileName));
				int index = 0;
				while(index <= 8) {
					g2D.drawImage(image, index*70, 210, 70, 70, null);
					g2D.drawImage(image, (index+8)*70, 140, 70, 70, null);
					g2D.drawImage(image, 17*70, 70, 70, 70, null);
					g2D.drawImage(image, 18*70, 70, 70, 70, null);
					g2D.drawImage(seaImage, index*70, 280, 70, 70, null);
					index++;
				}
				while(index <=19) {
					g2D.drawImage(seaImage, index*70, 280, 70, 70, null);
					index++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
