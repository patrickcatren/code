import java.awt.geom.*;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The Fish class is an abrstract class to include methods used for plankton, carp, bass, and sharks.
 * @author Patrick Catren
 */
public abstract class Fish implements MouseListener {
    private double goalx, goaly;
    private double x, y, r, delta;
    private Random rand;
    private boolean paused;
    /**
     * The constructor for a Fish.
     * @param x The x-value for it's position.
     * @param y The y-value for it's position.
     * @param r The random to be used for it's position and movement.
     * @param p The paused value.
     */
    public Fish(double x, double y, Random ra, boolean p) {
      this.x = x;
      this.y = y;
      r = 8;
      rand = ra;
      delta = 3;
      paused = p;
    }
    /**
     * An abstract method to allow fish to change their health.
     * @param a The new amount for health to be set at.
     */
    public abstract void changeHealth(double a);
  
    
    /** 
     * @param rad Adjust the radius of a plankton.
     */
    public void changeRadius(double rad){
      r = rad;
    }
    
    /** 
     * @return double Gets the x goal avlue.
     */
    public double getGoalX(){
      return goalx;
    }
    
    /** 
     * @return double gets the Y goal value.
     */
    public double getGoalY(){
      return goaly;
    }
    
    /** 
     * @param x Sets the speed at value x.
     */
    public void changeSpeed(double x){
      delta = x;
    }
    
    /** 
     * @return double returns the speed value.
     */
    public double getSpeed(){
      return delta;
    }
    
    /** 
     * @return double returns the x value.
     */
    public double getX() {
      return x;
    }
    
    /** 
     * @return double returns the y value.
     */
    public double getY() {
      return y;
    }
    
    /** 
     * @param gx The new x goal.
     * @param gy The new Y goal.
     */
    public void setGoal(double gx, double gy) {
      goalx = gx;
      goaly = gy;
    }

    
    /** 
     * @param A The fish to be tested.
     * @return boolean if the two intersects.
     */
    public boolean doesIntersect(Fish A){
      if((A.x == this.x) && (A.y == this.y)){
        return true;
      }
      else{
        return false;
      }
    }

    /**
     * The method to move the fish.
     */
    public void step() {
      if (Math.sqrt(Math.pow(goaly - y, 2) + Math.pow(goalx - x, 2)) < delta){
        x = goalx;
        y = goaly;
      } else{
        double a = Math.atan2(goaly - y, goalx - x);
        x += delta * Math.cos(a);
        y += delta * Math.sin(a);
      }
    }

    
    /** 
     * @param xN The new x value.
     * @param yN the new y value.
     */
    public void setPlace(double xN, double yN){
      x = xN;
      y = yN;
    }
  
    
    /** 
     * @param g The graphics to put the dot on.
     */
    public void paint(Graphics2D g) {
      g.fill(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
    }
  
    public abstract int getType();
    public abstract void age();
    public abstract int getHealth();
    public abstract void addHealth(int a);
    public abstract double getMetabolism();

    
    /** 
     * @return boolean The boolean value of paused.
     */
    public boolean isPaused(){
      return paused;
    }

    
    /** 
     * @param p The value to adjust paused to.
     */
    public void setPaused(boolean p){
      paused = p;
    }
    public abstract boolean getDisplayed();
    public abstract void changeDisplayed();
    public abstract Rectangle2D getBox();
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
    public void mousePressed(MouseEvent e)  {}
    public void mouseReleased(MouseEvent e) {}

}
