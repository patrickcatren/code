import java.util.*;

/**
 * @author Patrick Catren
 * An abstract class containing methods shared across all types of pieces. 
 */
public abstract class Piece{
    private boolean black;

    /**
     * A constructor.
     * @param b Represents the given black boolean.
     */
    public Piece(boolean b){
        black = b;
    }

    /**
     * An abstract method for shake, which varies for different types of pieces.
     * @param r The random object used to "shake" the pieces.
     */
    public abstract void shake(Random r);
    /**
     * Allows viewing of private bool black.
     * @return The black boolean.
     */
    public boolean getB(){
        return black;
    }
    /**
     * Allows changing of private bool black.
     * @param b The new value for black.
     */
    public void setB(boolean b){
        black = b;
    }
}