import java.util.*;

/**
 * @author Patrick Catren
 * This class represents a type of piece Die.
 */
public class Die extends Piece {
    private int numBlack;
    private int numWhite;
    /**
     * Shakes the dice and changes boolean black to reflect the changed status.
     * @param r The random to be used in shaking it.
     */
    public void shake(Random r){
        int k = r.nextInt(this.numBlack + this.numWhite);
        this.setB(k < this.numBlack);
    }
    /**
     * A constructor.
     * @param b If it is initially a black side facing up.
     * @param nB Number of black sides.
     * @param nW Number of white sides.
     */
    public Die(boolean b, int nB, int nW){
        super(b);
        numBlack = nB;
        numWhite = nW;
    }
}