import java.awt.geom.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * The plankton is an extension of Fish, and it represents a plankton that moves straight down to a random depth.
 * @author Patrick Catren
 */
public class Plankton extends Fish {
    private Rectangle2D box;
    private int type = 0;
    private int health = 10000;
    private int metabolism = 15;
    private boolean display = false;
    /**
     * The constructor for a plankton, which calls the super fish constructor and creates the box to represent the plankton's position.
     * @param x The x-value for it's position.
     * @param y The y-value for it's position.
     * @param r The random to be used for it's position and movement.
     * @param p The paused value.
     */
    public Plankton(double x, double y, Random r, boolean p){
        super(x, 0, r, p);
        int gy = r.nextInt(1200);
        this.setGoal(x, gy);
        changeRadius(4);
        box = new Rectangle2D.Double(x, y, 12, 12);
    }
    /**
     * Moves the plankton straight down to the goal y value.
     */
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
    
    /** 
     * @return int returns the type.
     */
    public int getType(){
        return type;
    }
    
    /** 
     * @return Rectangle2D returns the rectangle that represents the position of the fish.
     */
    public Rectangle2D getBox(){
        return box;
    }
    /**
     * Removes metabolism from health every time a step occurs.
     */
    public void age() {
		health = health - metabolism;		
	}
    
    /** 
     * @return int Returns health
     */
    public int getHealth(){
        return health;
    }
    
    /** 
     * @param a The amount to add to health.
     */
    public void addHealth(int a) {
        health = health + a;
    }
    
    /** 
     * @return boolean Whether or not the fish is the one who's stats should be displayed.
     */
    public boolean getDisplayed(){
      return display;
    }
    /**
     * Set the display value to false.
     */
    public void changeDisplayed(){
      display = false;
    }
    
    /** 
     * @param e If the fish is the one clicked on set it to be displayed.
     */
    public void mouseClicked(MouseEvent e) {
      if(box.contains(e.getX(), e.getY())){
        display = true;
      }
    }

    
    /** 
     * @return double Returns metabolism.
     */
    public double getMetabolism() {
      return metabolism;
    }
    
    /** 
     * @param a The new amount for health to be set at.
     */
    public void changeHealth(double a){
      health = (int)a;
    }
}
