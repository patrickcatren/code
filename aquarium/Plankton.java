import java.awt.geom.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class Plankton extends Fish {
    private Rectangle2D box;
    private int type = 0;
    private int health = 10000;
    private int metabolism = 15;
    private boolean display = false;
    public Plankton(double x, double y, Random r, boolean p){
        super(x, 0, r, p);
        int gy = r.nextInt(1200);
        this.setGoal(x, gy);
        changeRadius(4);
        box = new Rectangle2D.Double(x, y, 12, 12);
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
          box = new Rectangle2D.Double(x, y, 12, 12);
        }
      }
    public void changeGoal(){}
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
