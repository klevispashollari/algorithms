package game;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Platformer extends JFrame implements ActionListener {
    public static final String BasePath = "./docs/Step3/assets/";
    private static final long serialVersionUID = 5736902251450559962L;

    private int ticks;
    private int yMotion;
    private Player p = null;
    private Level l = null;
    BufferStrategy bufferStrategy;

    public Platformer() {

        //exit program when window is closed
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        try {
            Timer timer = new Timer(20, this);
            l = new Level(fileChooser().getAbsolutePath());
            p = new Player(l);
            l.player = p;

            this.setBounds(0, 0, 1000, 12 * 70);
            this.setVisible(true);
            timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private File fileChooser(){
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("./"));
        fc.setDialogTitle("Please select level input image (.bmp)");
        FileFilter filter = new FileNameExtensionFilter("Level image", "bmp");
        fc.setFileFilter(filter);
        int result = fc.showOpenDialog(this);
        File selectedFile = new File("");
        addKeyListener(new AL(this));
        createBufferStrategy(2);
        bufferStrategy = this.getBufferStrategy();


        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fc.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            return selectedFile;
        } else {
            dispose();
            System.exit(0);
        }
        return null;
    }

    private void restart() throws IOException {
        p.pos.x = 0;
        p.pos.y = 0;
        l.offsetX = 0;
        l.initLevel();
    }

    private void updateGameStateAndRepaint() {
        l.update();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = null;

        try {
            g2 = (Graphics2D) bufferStrategy.getDrawGraphics();
            draw(g2);

        } finally {
            g2.dispose();
        }
        bufferStrategy.show();
    }

    private void draw(Graphics2D g2d) {
        BufferedImage level = (BufferedImage) l.getResultingImage();
        if (l.offsetX > level.getWidth() - 1000)
            l.offsetX = level.getWidth() - 1000;
        BufferedImage bi = level.getSubimage((int) l.offsetX, 0, 1000, level.getHeight());
        g2d.drawImage(bi, 0, 0, this);
        g2d.drawImage(getPlayer().getPlayerImage(), (int) (getPlayer().pos.x - l.offsetX), (int) getPlayer().pos.y, this);
    }

    public Player getPlayer() {
        return this.p;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(!checkCollision()){
            updateGravity();
        }
        updateGameStateAndRepaint();

    }

    private void updateGravity(){
        ticks++;
        if(ticks%2==0 && yMotion<15){
            yMotion+=1;
        }
        getPlayer().move(0,yMotion);
    }

    private void jump(){

    }

    public Boolean checkCollision(){

        ///System.out.println(l.levelImg.getWidth()+" : "+l.levelImg.getHeight());
        for (Tile tile : l.tiles){
            //System.out.println(tile.getBoundingBox());
           //System.out.println("tile "+tile.getPos());
          //  System.out.println("player "+p.pos);
            //System.out.println("player "+p.tilesWalk.get(0).getHeight()+" : "+p.tilesWalk.get(0).getWidth());
            //System.out.println("tile "+l.tileImages.get(0).getHeight()+" : "+l.tileImages.get(0).getWidth());
            BoundingBox pb =p.boundingBox;
            BoundingBox tb =tile.getBoundingBox();
            if(pb.intersect(tb)){
                Vec2 vec2 = pb.overlapSize(tb);
                System.out.println(vec2);
                if(vec2.x >0 && vec2.y>0){
                    System.out.println("below");
                }
                if(vec2.x <0 && vec2.y>0){
                    System.out.println("left");
                }
                if(vec2.x >0 && vec2.y<0){
                    System.out.println("right");
                }
                if(vec2.x <0 && vec2.y<0){
                   System.out.println("above");
                }
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public class AL extends KeyAdapter {
        Platformer p;

        public AL(Platformer p) {
            super();
            this.p = p;
        }

        @Override
        public void keyPressed(KeyEvent event) {
            int keyCode = event.getKeyCode();
            Player player = p.getPlayer();

            if (keyCode == KeyEvent.VK_ESCAPE) {
                dispose();
            }

            if (keyCode == KeyEvent.VK_UP) {
                if(yMotion>0) {
                   yMotion=0;
                }else {
                    yMotion-=10;
                    player.move(0,yMotion);
                }
            }

            if (keyCode == KeyEvent.VK_DOWN) {
                    player.move(0, 1);
            }

            if (keyCode == KeyEvent.VK_LEFT) {
                player.move(-1, 0);
            }

            if (keyCode == KeyEvent.VK_RIGHT) {
                player.move(5, 0);
            }

            if (keyCode == KeyEvent.VK_R) {
                try {
                    restart();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            updateGameStateAndRepaint();
        }
    }
}
