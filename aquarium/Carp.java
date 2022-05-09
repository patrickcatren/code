import java.util.Random;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

/**
 * The carp is an extension of Fish, and it represents a shallow water fish that moves in random directions.
 * @author Patrick Catren
 */
public class Carp extends Fish {
    private Random rand;
    private int width = 60;
    private Rectangle2D box;
    private int type = 1;
    private int health = 10000;
    private int metabolism = 12;
    private boolean display = false;
    /**
     * The constructor for a carp, which calls the super fish constructor and creats the box to represent the carp's position.
     * @param x The x-value for it's position.
     * @param y The y-value for it's position.
     * @param r The random to be used for it's position and movement.
     * @param p The paused value.
     */
    public Carp(double x, double y, Random r, boolean p){
        super(x, y, r,p);
        rand = r;
        this.changeRadius(12.5);
        box = new Rectangle2D.Double(x, y, 55, 55);
    }
    /**
     * Moves the fish and it's box to a random position, stopping at a certain depth of water or the boundaries of the tank.
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
    
    /** 
     * @param goaly The y goal for the fish.
     * @param goalx The x goal for the fish.
     * @param x The x position of the fish.
     * @param y The y position of the fish.
     * @return boolean if it stops.
     */
    private boolean ifStops(double goaly, double goalx, double x, double y) {
        return (x <= 60) || (y <= 60) || (x >= 1140) || (y >= 430) 
        || (x == goalx) || (y == goaly);
    }

    
    /** 
     * @param g The graphics 2D on which to put the carp image.
     */
    public void paint(Graphics2D g) {
        BufferedImage img = null;
        try {
        img = ImageIO.read(new File("carp.png"));
        } catch (IOException e) {}
        g.drawImage(img, (int)getX(), (int)getY(), width, 60, null);
    }
    
    /** 
     * @return int represents the type of fish.
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
     * @return int Returns health.
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

