import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
/**
 * This class creates the DrawArea, and contains the necessary methods for it to render fish and alter their details.
 * @author Patrick Catren
 */
public class DrawArea extends JComponent implements Runnable {
  private Random rand = new Random(System.currentTimeMillis());
  private ArrayList<Fish> fishes = new ArrayList<Fish>();
  private boolean paused = false;
  private Fish displayedFish = null;
  /**
   * The contructor for the DrawArea.
   */
  public DrawArea() {
    super();
    setPreferredSize(new Dimension(1200, 1200));
    addPlankton(0);
    addShark(0);
    addCarp(1);
    addBass(0);
  }

  
  /** 
   * @param speed Set the speed for all fish in the DrawArea.
   */
  public void setSpeed(double speed){
    for(int i = 0; i < fishes.size(); i++){
      fishes.get(i).changeSpeed(speed);
    }
  }
  
  /** 
   * @param size The number of plankton to add.
   */
  public void addPlankton(int size){
    for(int i = 0; i < size; i++){
      Plankton a = new Plankton(rand.nextInt(1200), 0, rand, paused);
      fishes.add(a);
    }
  }
  
  /** 
   * @param size The number of sharks to add to the drawArea.
   */
  public void addShark(int size){
    for(int i = 0; i < size; i++){
      Shark a = new Shark(0, rand.nextInt(1200), rand, paused);
      fishes.add(a);
    }
  }
  
  /** 
   * @param size The number of bass to add to the draw area.
   */
  public void addBass(int size){
    for(int i = 0; i < size; i++){
      Bass a = new Bass(0, rand.nextInt(700) + 500, rand, paused);
      fishes.add(a);
    }
  }
  
  /** 
   * @param size The number of carps to add to the draw area.
   */
  public void addCarp(int size){
    for(int i = 0; i < size; i++){
      Carp a = new Carp(0, rand.nextInt(550), rand, paused);
      fishes.add(a);
    }
  }

  
  /** 
   * @param a The speed to be set for every instance of shark in the draw area.
   */
  public void sharkSpeed(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 3){
        fishes.get(i).changeSpeed(a);
      }
    }
  }
  
  /** 
   * @param a The health to be set for every instance of shark in the draw area.
   */
  public void sharkHealth(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 3){
        fishes.get(i).changeHealth(a);
      }
    }
  }
  
  /** 
   * @param a The speed to be set for every instance of a carp in the draw area.
   */
  public void carpSpeed(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 1){
        fishes.get(i).changeSpeed(a);
      }
    }
  }
  
  /** 
   * @param a The health to be set for every instance of carp in the draw area.
   */
  public void carpHealth(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 1){
        fishes.get(i).changeHealth(a);
      }
    }
  }
  
  /** 
   * @param a The speed to be set for every instance of bass in the draw area.
   */
  public void bassSpeed(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 2){
        fishes.get(i).changeSpeed(a);
      }
    }
  }
  
  /** 
   * @param a The health to be set for every instance of shark in the draw area.
   */
  public void bassHealth(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 2){
        fishes.get(i).changeHealth(a);
      }
    }
  }

  /**
   * The method that is called to move every fish, age them, and allow them to eat each other.
   */
  public void step() {
    for(int i = 0; i < fishes.size(); i++){
      addMouseListener(fishes.get(i));
    }
    for(int i = 0; i < fishes.size(); i++){
      if(!paused){
        fishes.get(i).age();
      }
      fishes.get(i).setPaused(paused);
      if(0 >= fishes.get(i).getHealth()){
        fishes.remove(i);
      }
    }
    for(int i = 0; i < fishes.size(); i++){
      fishes.get(i).step();
    }
    for(int i = 0; i < fishes.size(); i++){
      Rectangle2D box1 = fishes.get(i).getBox();
      for(int j = 0; j < fishes.size(); j++){
        if(j != i){
          boolean intersects = box1.intersects(fishes.get(j).getBox());
          if(intersects){
            boolean jEats = false;
            boolean iEats = false;
            if((fishes.get(i).getType() == 0)){
              if((fishes.get(j).getType() == 1) || (fishes.get(j).getType() == 2)){
                jEats = true;
                fishes.get(j).addHealth(300);
              }
            }
            if((fishes.get(i).getType() == 1) || (fishes.get(i).getType() == 2)){
              if(fishes.get(j).getType() ==3){
                jEats = true;
                fishes.get(j).addHealth(300);
              }
              if(fishes.get(j).getType() == 0){
                iEats = true;
                fishes.get(i).addHealth(300);
              }
            }
            if(fishes.get(i).getType() == 3){
              if((fishes.get(j).getType() == 1) || (fishes.get(j).getType() == 2)){
                iEats = true;
                fishes.get(i).addHealth(300);
              }
            }
  
            if(jEats){
              fishes.remove(i);
            }
            if(iEats){
              fishes.remove(j);
            }
          }
        }
      }
    }
    if(paused){
      for(int i = 0; i < fishes.size(); i++){
        boolean b = fishes.get(i).getDisplayed();
        if(b){
          Aquarium.setFishDisplayed(fishes.get(i));
        }
      }
    }
  }

  /**
   * Controls the thread and runs step every twenty milliseconds.
   */
  public void run() {
    while( true ) {
      try {
        Thread.sleep(20);
      } catch (Exception e) {}
      step();
      repaint();
    }
  }

  
  /** 
   * @param p The pause status tpo be set for the draw area.
   */
  public void setDAPause(boolean p){
    paused = p;
  }

  
  /** 
   * @param g Renders the graphics of the draw area, and sets the background image.
   */
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