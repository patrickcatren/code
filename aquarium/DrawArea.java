import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class DrawArea extends JComponent implements Runnable {
  private Random rand = new Random(System.currentTimeMillis());
  private ArrayList<Fish> fishes = new ArrayList<Fish>();

  public DrawArea() {
    super();
    setPreferredSize(new Dimension(1200, 1200));
    addPlankton(20);
    addShark(5);
    addCarp(12);
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
  public void addCarp(int size){
    for(int i = 0; i < size; i++){
      Carp a = new Carp(0, rand.nextInt(1200), rand);
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