import java.util.*;

public class RedDot extends MovingDot{
    public RedDot(int r, int c){
        super(r,c);
    }

    public void step(){
        Random rn = new Random();
        int dir = rn.nextInt(3);
        super.step();
        setDir(dir);        
    }

    public String toString() {
        return super.toString() + " r";
    }
}
