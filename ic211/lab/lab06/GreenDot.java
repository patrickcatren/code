import java.util.*;
/**
 * Class GReenDot extends MovingDot to create a dot that moves in a random
 * diagonal direction every 5 steps.
 * 
 * @author Patrick Catren
 */
public class GreenDot extends MovingDot{
    private int count;
    /**
     * Creates a GReenDot, and initializes count at 0.
     * @param r row
     * @param c column
     */
    public GreenDot(int r, int c){
        super(r,c);
        int count = 0;
    }
    /**
     * For GReenDot, uses a super call of step and random from java.util to mov
     * the dot in a random diagonal direction every five steps, measured using 
     * count and the bool checkCount.
     */
    public void step(){
        Random rn = new Random();
        int dir = rn.nextInt(1);
        if(checkCount()){
            if(dir == 0){
                setDir(4);
            }
            else{
                setDir(5);
            }
            super.step();
        }
        incCount();        
    }
    /**
     * Uses a super call to string to print the resulting dot.
     * @return xvalue + yvalue + color designator
     */
    public String toString() {
        return super.toString() + " g";
    }
    /**
     * Checks if count is at an increment of 10.
     * @return true if it is an increment of ten, otherwise return false.
     */
    public boolean checkCount(){
        if(count%5 == 0){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Increments count by 1.
     */
    public void incCount(){
        count++;
    }
}

