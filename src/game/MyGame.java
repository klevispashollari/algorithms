package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGame implements ActionListener {

    private static final Integer WIDTH = 1100;
    private static final Integer HEIGHT = 700;
    private static final Integer PLAYER_INIT_POS_X = 100;
    private static final Integer PLAYER_INIT_POS_Y = 100;
    private static MyGame myGame;
    private Platformer platformer;
    private Rectangle player;

    public MyGame() {
        Timer timer = new Timer(20, this);
        platformer = new Platformer();
        player = new Rectangle(PLAYER_INIT_POS_X, PLAYER_INIT_POS_Y, 20, 20);
        JFrame jFrame = new JFrame();
        jFrame.add(platformer);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        timer.start();
    }

    public void repaint(Graphics graphics) {
        System.out.println("Repaint");
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        graphics.setColor(Color.decode("#32CD32"));
        graphics.fillRect(player.x, player.y, player.width, player.height);
    }

    public static void main(String[] args) {
        myGame = new MyGame();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        platformer.repaint();
    }

    public static MyGame getInstance() {
        if (myGame == null) {
            myGame = new MyGame();
        }
        return myGame;
    }
}
