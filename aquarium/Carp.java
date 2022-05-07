import java.util.Random;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class Carp extends Fish {
    private Random rand;
    private int width = 60;
    private Rectangle2D box;
    private int type = 1;
    private int health = 10000;
    private int metabolism = 12;
    private boolean display = false;
    public Carp(double x, double y, Random r, boolean p){
        super(x, y, r,p);
        rand = r;
        this.changeRadius(12.5);
        box = new Rectangle2D.Double(x, y, 55, 55);
    }
    public void step() {
        double goaly = getGoalY();
        double goalx = getGoalX();
        double x = getX();
        double y = getY();
        double delta = getSpeed();
        if (Math.sqrt(Math.pow(goaly - y, 2) + Math.pow(goalx - x, 2)) < delta){
          x = goalx;
          y = goaly;
        } else{
          double a = Math.atan2(goaly - y, goalx - x);
          x += delta * Math.cos(a);
          y += delta * Math.sin(a);
        }
        if(!isPaused()){
            this.setPlace(x, y);
            if(ifStops(goaly, goalx, x, y)){
                goalx = rand.nextInt(1180) + 60;
                goaly = rand.nextInt(490) + 60;
                this.setGoal(goalx, goaly);
                if((goalx < getX()) && (width > 0)){
                    width = width * -1;
                }
                if((goalx > getX()) && (width < 0)){
                    width = width * -1;
                }
            }
            box = new Rectangle2D.Double(x, y, 55, 55);
        }
    }
    private boolean ifStops(double goaly, double goalx, double x, double y) {
        return (x <= 60) || (y <= 60) || (x >= 1140) || (y >= 430) 
        || (x == goalx) || (y == goaly);
    }

    public void paint(Graphics2D g) {
        BufferedImage img = null;
        try {
        img = ImageIO.read(new File("carp.png"));
        } catch (IOException e) {}
        g.drawImage(img, (int)getX(), (int)getY(), width, 60, null);
    }
    public int getType(){
        return type;
    }
    public Rectangle2D getBox(){
        return box;
    }
    public void age() {
		health = health - metabolism;		
	}
    public int getHealth(){
        return health;
    }
    public void addHealth(int a) {
        health = health + a;
    }
    public boolean getDisplayed(){
        return display;
      }
      public void changeDisplayed(){
        display = false;
      }
    public void mouseClicked(MouseEvent e) {
        if(box.contains(e.getX(), e.getY())){
            display = true;
        }
    }
    public double getMetabolism() {
        return metabolism;
    }
    public void changeHealth(double a){
        health = (int)a;
    }
}

