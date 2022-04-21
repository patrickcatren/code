import java.util.*;
/**
 * Class BlueDot extends MovingDot to create a dot that moves in a random
 * direction every 10 steps.
 * 
 * @author Patrick Catren
 */
public class BlueDot extends MovingDot{
    private int count;
    /**
     * Creates a BlueDot and initializes count at 0.
     * @param r rows
     * @param c columns
     */
    public BlueDot(int r, int c){
        super(r,c);
        count = 0;
    }
    /**
     * For BlueDot, uses a super call of step and random from java.util to mov
     * the dot in a random direction every ten steps, measured using count
     * and the bool checkCount.
     */
    public void step(){
        Random rn = new Random();
        int dir = rn.nextInt(3);
        if(checkCount()){
            setDir(dir);
            super.step();
        }
        incCount();        
    }
    /**
     * Uses a super call to string to print the resulting dot.
     * @return xvalue + yvalue + color designator
     */
    public String toString() {
        return super.toString() + " b";
    }
    /**
     * Checks if count is at an increment of 10.
     * @return true if it is an increment of ten, otherwise return false.
     */
    public boolean checkCount(){
        if(count%10 == 0){
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

