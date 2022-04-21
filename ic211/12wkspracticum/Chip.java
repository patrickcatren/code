import java.util.*;

/**
 * @author Patrick Catren
 * This class represents a type of piece called chip, with one white side and one black.
 */
public class Chip extends Piece {
    /**
     * Shake the piece randomly and adjust black to reflect which side is now up.
     * @param r Randomizes the result.
     */
    public void shake(Random r){
        this.setB(r.nextBoolean());
    }
    /**
     * A constructor
     * @param b Sets the initial black state.
     */
    public Chip(boolean b){
        super(b);
    }
}
