package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Platformer extends JPanel {
    private static final long serialVersionUID = 5736902251450559962L;

    private BufferedImage levelImg;
    private int xOffset = 20;
    private int xPos = 0;
    private int camX = 0;

    public Platformer() {

    }

   @Override
   public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        MyGame.getInstance().repaint(graphics);

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

}
