package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Platformer extends JFrame {
    private static final long serialVersionUID = 5736902251450559962L;

    private BufferedImage levelImg;
    private int xOffset = 20;
    private int xPos = 0;
    private int camX = 0;

    public Platformer() {
        // exit program when window is closed
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("./"));
        fc.setDialogTitle("Please input image (.bmp)");
        FileFilter filter = new FileNameExtensionFilter("image", "bmp");
        fc.setFileFilter(filter);
        int result = fc.showOpenDialog(this);
        File selectedFile = new File("");

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            dispose();
            System.exit(0);
        }

        try {
            levelImg = ImageIO.read(selectedFile);

            this.setBounds(800, 1000, levelImg.getWidth() + 1000, levelImg.getHeight() + 39);
            this.setSize(150, 350);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setContentPane(new DrawingPanel());
            addKeyAdapter(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Platformer();
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
        camX +=10;
        repaint();
    }

    private void handleLeftKeyEvent() {
        System.out.println("left arrow ");
        camX -=10;
    }


    class DrawingPanel extends JPanel {

        int recCenterX;

        public DrawingPanel() {
            ActionListener al = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    recCenterX = (100 + camX);
                    repaint();
                }
            };
            Timer timer = new Timer(15, al);
            timer.start();

        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D gg = (Graphics2D) g;
            gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gg.translate(camX, 0);
            gg.drawImage(levelImg, -300, 20, null);
        }

    }
}
