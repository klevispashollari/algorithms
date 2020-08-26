package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class ImageDisplay extends JFrame {
    private static final long serialVersionUID = 1L;
    private final String fileName;

    public ImageDisplay(String filename) {
        this.setSize(300, 300);
        this.setVisible(true);
        this.fileName = filename;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)  {
        new ImageDisplay("C:\\Users\\kpashollari\\Desktop\\test.bmp");
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g2D = (Graphics2D) graphics;
        BufferedImage image;
        try {
            image = ImageIO.read(new File(fileName));
            g2D.drawImage(image, 0, 0, 300, 300, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

