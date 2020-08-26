package game;

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
	private final String levelFilename;

	public Level(String tileFileName, String levelFilename) {
		this.setSize(3500, 350);
		this.setVisible(true);
		this.levelFilename = levelFilename;
		this.tileFileName = tileFileName;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Level("C:\\Users\\kpashollari\\Desktop\\grassMid.png", "C:\\Users\\kpashollari\\Desktop\\level1.bmp");
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g2D = (Graphics2D) graphics;
		BufferedImage image;
		try {
			image = ImageIO.read(new File(levelFilename));
			g2D.drawImage(image, 0, 0, 3500, 350, null);
			for (int i = 0; i < 70; i++) {
				image = ImageIO.read(new File(tileFileName));
				int index = 0;
				while(index <= 8) {
					g2D.drawImage(image, index*70, 210, 70, 70, null);
					g2D.drawImage(image, (index+7)*70, 140, 70, 70, null);
					g2D.drawImage(image, 17*70, 70, 70, 70, null);
					g2D.drawImage(image, 16*70, 70, 70, 70, null);
					index++;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
