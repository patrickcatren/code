import java.util.Random;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

/**
 * The shark is an extension of Fish, and it represents a shark that moves in random directions.
 * @author Patrick Catren
 */
public class Shark extends Fish {
    private Random rand;
    private int width = 180;
    private Rectangle2D box;
    private int type = 3;
    private int health = 10000;
    private int metabolism = 8;
    private boolean display = false;
    /**
     * The constructor for a shark, which calls the super fish constructor and creats the box to represent the shark's position.
     * @param x The x-value for it's position.
     * @param y The y-value for it's position.
     * @param r The random to be used for it's position and movement.
     * @param p The paused value.
     */
    public Shark(double x, double y, Random r, boolean p){
        super(x, y, r, p);
        rand = r;
        this.changeRadius(12.5);
        this.setGoal(rand.nextInt(840)+180, rand.nextInt(840)+180);
        box = new Rectangle2D.Double(x, y, 175, 175);
    }
    /**
     * Moves the fish and it's box to a random position, stopping at the boundaries of the tank.
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
                goalx = rand.nextInt(840)+180;
                goaly = rand.nextInt(840)+180;
                this.setGoal(goalx, goaly);
                if((goalx < getX()) && (width > 0)){
                    width = width * -1;
                }
                if((goalx > getX()) && (width < 0)){
                    width = width * -1;
                }
            }
            box = new Rectangle2D.Double(x, y, 175, 175);
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
        return (x == 180) || (y == 180) || (x == 1020) || (y == 1020) 
        || (x == goalx) || (y == goaly);
    }

    
    /** 
     * @param g The graphics 2D on which to put the shark image.
     */
    public void paint(Graphics2D g) {
        BufferedImage img = null;
        try {
        img = ImageIO.read(new File("shark.png"));
        } catch (IOException e) {}
        g.drawImage(img, (int)getX(), (int)getY(), width, 180, null);
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
     * @return doubleReturns metabolism.
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