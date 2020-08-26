package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MyGame implements ActionListener {

    private static final Integer WIDTH = 1100;
    private static final Integer HEIGHT = 700;
    private static final Integer PLAYER_INIT_POS_X = 100;
    private static final Integer PLAYER_INIT_POS_Y = 100;
    private static MyGame myGame;
    private Platformer platformer;
    private Rectangle player;
    private ArrayList<Rectangle> columns;
    private Random random;

    public MyGame() {
        Timer timer = new Timer(20, this);
        platformer = new Platformer();
        player = new Rectangle(PLAYER_INIT_POS_X, PLAYER_INIT_POS_Y, 20, 20);
        columns = new ArrayList<>();
        random = new Random();
        addColumns();
        JFrame jFrame = new JFrame();
        jFrame.add(platformer);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        timer.start();
    }

    private void addColumns(){
        addColumn();
        addColumn();
        addColumn();
        addColumn();
        addColumn();
    }
    private void addColumn(){
        int space = 300;
        int width = 100;
        int height = 50 + random.nextInt(300);
        columns.add(new Rectangle(width,HEIGHT-height-450));
        columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 250, width, HEIGHT-height-450));
    }

    public void printColumn(Graphics graphics,Rectangle rectangle){
        graphics.setColor(Color.decode("#a35103"));
        graphics.fillRect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }
    public void printColumns(Graphics graphics){
        for (Rectangle r : columns){
            printColumn(graphics,r);
        }
    }

    public void repaint(Graphics graphics) {
        System.out.println("Repaint");

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        graphics.setColor(Color.ORANGE);
        graphics.fillRect(0,HEIGHT-150,WIDTH,150);

        graphics.setColor(Color.GREEN);
        graphics.fillRect(0,HEIGHT-150,WIDTH,20);

        graphics.setColor(Color.decode("#32CD32"));
        graphics.fillRect(player.x, player.y, player.width, player.height);

        printColumns(graphics);
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
