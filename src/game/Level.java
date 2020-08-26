package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Level extends JFrame {
    private static final long serialVersionUID = 1L;
    private final String tileFileName;
    private final String levelFileName;
    private final String seaFileName;
    private static int camX = 0;

    public Level(String tileFileName, String levelFileName, String seaFileName) {
        this.setSize(1000, 400);
        this.setVisible(true);
        this.levelFileName = levelFileName;
        this.tileFileName = tileFileName;
        this.seaFileName = seaFileName;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyAdapter(this);
    }

    public static void main(String[] args) {
        Level level = new Level("./docs/Step1/assets/Tiles/grassMid.png", "./docs/Step1/level1.bmp", "./docs/Step1/assets/Tiles/liquidWaterTop_mid.png");
        level.add(level.new DrawingPanel());
    }

    public void addKeyAdapter(JFrame frame) {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                handleKeyPressedEvent(keyEvent);
            }
        });
    }

    public void handleKeyPressedEvent(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                handleLeftKeyEvent();
                break;
            case KeyEvent.VK_RIGHT:
                handleRightKeyEvent();
                break;
        }
    }

    private void handleRightKeyEvent() {
        System.out.println("right arrow ");
        camX += 10;
        repaint();
    }

    private void handleLeftKeyEvent() {
        System.out.println("left arrow ");
        camX -= 10;
        repaint();
    }

    class MyGraphics extends JComponent {

        private static final long serialVersionUID = 1L;

        MyGraphics() {
            setPreferredSize(new Dimension(3500, 8000));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            BufferedImage image;
            BufferedImage seaImage;
            try {
                image = ImageIO.read(new File(levelFileName));
                g.drawImage(image, 0, 0, 3500, 350, null);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, 3500, 350);
                for (int i = 0; i < 70; i++) {
                    image = ImageIO.read(new File(tileFileName));
                    seaImage = ImageIO.read(new File(seaFileName));
                    int index = 0;
                    while (index <= 8) {
                        g.drawImage(image, index * 70, 210, 70, 70, null);
                        g.drawImage(image, (index + 8) * 70, 140, 70, 70, null);
                        g.drawImage(image, 17 * 70, 70, 70, 70, null);
                        g.drawImage(image, 18 * 70, 70, 70, 70, null);
                        g.drawImage(seaImage, index * 70, 280, 70, 70, null);
                        index++;
                    }
                    while (index <= 19) {
                        g.drawImage(seaImage, index * 70, 280, 70, 70, null);
                        index++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class DrawingPanel extends JPanel {
        public DrawingPanel() {
            this.setSize(500, 100);
            this.add(new MyGraphics());
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D gg = (Graphics2D) g;
            super.paintComponent(gg);
            gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gg.translate(camX, 0);
        }

    }

}
