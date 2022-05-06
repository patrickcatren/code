import java.util.Random;
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

public class Shark extends Fish {
    private Random rand;
    private int width = 180;
    public Shark(double x, double y, Random r){
        super(x, y, r);
        rand = r;
        this.changeRadius(12.5);
        this.setGoal(rand.nextInt(1040), rand.nextInt(1240));
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
        this.setPlace(x, y);
        if(ifStops(goaly, goalx, x, y)){
            goalx = rand.nextInt(1040);
            goaly = rand.nextInt(1040);
            this.setGoal(goalx, goaly);
            if((goalx < getX()) && (width > 0)){
                width = width * -1;
            }
            if((goalx > getX()) && (width < 0)){
                width = width * -1;
            }
        }
    }
    private boolean ifStops(double goaly, double goalx, double x, double y) {
        return (x == 160) || (y == 160) || (x == 1040) || (y == 1040) 
        || (x == goalx) || (y == goaly);
    }

    public void paint(Graphics2D g) {
        BufferedImage img = null;
        try {
        img = ImageIO.read(new File("shark.png"));
        } catch (IOException e) {}
        g.drawImage(img, (int)getX(), (int)getY(), width, 160, null);
    }
}