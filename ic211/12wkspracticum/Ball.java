import java.util.*;

/**
 * @author Patrick Catren
 * This class reprsents a type of piece Ball. 
 */
public class Ball extends Piece {
    /**
     * Shake does nothing for ball due to it being round.
     * @param r Is unused.
     */
    public void shake(Random r){
        ;
    }
    /**
     * A constructor.
     * @param b Sets boolean black.
     */
    public Ball(boolean b){
        super(b);
    }
}
