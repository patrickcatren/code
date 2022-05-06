import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class DrawArea extends JComponent implements Runnable {
  private Random rand = new Random(System.currentTimeMillis());
  private ArrayList<Fish> fishes = new ArrayList<Fish>();

  public DrawArea() {
    super();
    setPreferredSize(new Dimension(1200, 1200));
    addPlankton(0);
    addShark(2);
    addCarp(2);
    addBass(2);
    setSpeed(3);
  }

  public void setSpeed(double speed){
    for(int i = 0; i < fishes.size(); i++){
      fishes.get(i).changeSpeed(speed);
    }
  }
  public void addPlankton(int size){
    for(int i = 0; i < size; i++){
      Plankton a = new Plankton(rand.nextInt(1200), 0, rand);
      fishes.add(a);
    }
  }
  public void addShark(int size){
    for(int i = 0; i < size; i++){
      Shark a = new Shark(0, rand.nextInt(1200), rand);
      fishes.add(a);
    }
  }
  public void addBass(int size){
    for(int i = 0; i < size; i++){
      Bass a = new Bass(0, rand.nextInt(700) + 500, rand);
      fishes.add(a);
    }
  }
  public void addCarp(int size){
    for(int i = 0; i < size; i++){
      Carp a = new Carp(0, rand.nextInt(550), rand);
      fishes.add(a);
    }
  }
  public void step() {
    for(int i = 0; i < fishes.size(); i++){
      fishes.get(i).step();
    }
  }

  public void run() {
    while( true ) {
      try {
        Thread.sleep(20);
      } catch (Exception e) {}
      step();
      repaint();
    }
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;

    // This voodoo makes the output prettier
    g2.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(
      RenderingHints.KEY_RENDERING,
      RenderingHints.VALUE_RENDER_QUALITY);
    

    BufferedImage img = null;
    try {
      img = ImageIO.read(new File("background.png"));
    } catch (IOException e) {}
    Image nImg = img.getScaledInstance(1200, 1200, Image.SCALE_DEFAULT);
    g.drawImage(nImg, (int)getX(), (int)getY(), null);
    // Here is the code for our ball.
    for(int i = 0; i < fishes.size(); i++){
      fishes.get(i).paint(g2);
    }
    

    // This is an unfortunate necessity
    // that forces the underlying OS
    // windowing system to actually
    // show the updates we've made
    Toolkit.getDefaultToolkit().sync();
  }
}