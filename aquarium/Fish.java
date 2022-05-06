import java.awt.geom.*;
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public abstract class Fish implements MouseListener {
    private double goalx, goaly;
    private double x, y, r, delta;
    private double dirX, dirY;
    private Random rand;
  
    public Fish(double x, double y, Random ra) {
      this.x = x;
      this.y = y;
      r = 8;
      rand = ra;
      delta = 3;
    }
  
    public void changeRadius(double rad){
      r = rad;
    }
    public double getGoalX(){
      return goalx;
    }
    public double getGoalY(){
      return goaly;
    }
    public void changeSpeed(double x){
      delta = x;
    }
    public double getSpeed(){
      return delta;
    }
    public double getX() {
      return x;
    }
    public double getY() {
      return y;
    }
    public void setGoal(double gx, double gy) {
      goalx = gx;
      goaly = gy;
    }

    public boolean doesIntersect(Fish A){
      if((A.x == this.x) && (A.y == this.y)){
        return true;
      }
      else{
        return false;
      }
    }

  
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

    public void setPlace(double xN, double yN){
      x = xN;
      y = yN;
    }
  
    public void paint(Graphics2D g) {
      g.fill(new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r));
    }
  
    public void mouseClicked(MouseEvent e) {
      setGoal(e.getX(), e.getY());
    }
  
    public void mouseEntered(MouseEvent e)  {}
    public void mouseExited(MouseEvent e)   {}
    public void mousePressed(MouseEvent e)  {}
    public void mouseReleased(MouseEvent e) {}
}