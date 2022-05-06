import java.util.Random;

public class Carp extends Fish {
    private Random rand;
    public Carp(double x, double y, Random r){
        super(x, y, r);
        rand = r;
        this.changeSpeed(12.5);
        this.changeRadius(2.5);
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
            goalx = rand.nextInt(1200);
            goaly = rand.nextInt(500);
            this.setGoal(goalx, goaly);
        }
    }
    private boolean ifStops(double goaly, double goalx, double x, double y) {
        return (x == 0) || (y == 0) || (x == 1200) || (y == 500) 
        || (x == goalx) || (y == goaly);
    }
}
