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
  private boolean paused = false;
  private Fish displayedFish = null;
  public DrawArea() {
    super();
    setPreferredSize(new Dimension(1200, 1200));
    addPlankton(0);
    addShark(0);
    addCarp(1);
    addBass(0);
  }

  public void setSpeed(double speed){
    for(int i = 0; i < fishes.size(); i++){
      fishes.get(i).changeSpeed(speed);
    }
  }
  public void addPlankton(int size){
    for(int i = 0; i < size; i++){
      Plankton a = new Plankton(rand.nextInt(1200), 0, rand, paused);
      fishes.add(a);
    }
  }
  public void addShark(int size){
    for(int i = 0; i < size; i++){
      Shark a = new Shark(0, rand.nextInt(1200), rand, paused);
      fishes.add(a);
    }
  }
  public void addBass(int size){
    for(int i = 0; i < size; i++){
      Bass a = new Bass(0, rand.nextInt(700) + 500, rand, paused);
      fishes.add(a);
    }
  }
  public void addCarp(int size){
    for(int i = 0; i < size; i++){
      Carp a = new Carp(0, rand.nextInt(550), rand, paused);
      fishes.add(a);
    }
  }

  public void sharkSpeed(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 3){
        fishes.get(i).changeSpeed(a);
      }
    }
  }
  public void sharkHealth(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 3){
        fishes.get(i).changeHealth(a);
      }
    }
  }
  public void carpSpeed(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 1){
        fishes.get(i).changeSpeed(a);
      }
    }
  }
  public void carpHealth(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 1){
        fishes.get(i).changeHealth(a);
      }
    }
  }
  public void bassSpeed(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 2){
        fishes.get(i).changeSpeed(a);
      }
    }
  }
  public void bassHealth(double a){
    for(int i = 0; i < fishes.size(); i++){
      if(fishes.get(i).getType() == 2){
        fishes.get(i).changeHealth(a);
      }
    }
  }
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

  public void run() {
    while( true ) {
      try {
        Thread.sleep(20);
      } catch (Exception e) {}
      step();
      repaint();
    }
  }

  public void setDAPause(boolean p){
    paused = p;
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