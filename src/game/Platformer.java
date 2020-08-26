package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Platformer extends JFrame {
    private static final long serialVersionUID = 5736902251450559962L;

    BufferedImage levelImg;

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

            this.setBounds(0, 0, levelImg.getWidth() + 16, levelImg.getHeight() + 39);
            this.setSize(1000, 350);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            addKeyAdapter(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Platformer();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(levelImg, 8, 31, this);
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
    }

    private void handleLeftKeyEvent() {
        System.out.println("left arrow ");
    }


}
