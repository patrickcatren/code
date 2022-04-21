import java.util.*;

/**
 * @author Patrick Catren
 * This class is the body of the game, and provides methods to do so. 
 */
public class Game {
    /**
     * This method plays a n rounds of the game.
     * @param n The given number of rounds.
     * @param P The array of pieces to play with.
     * @param r The Random object used to make the game random.
     */
    public static void playGame(int n, Piece[] P, Random r){
        for( int j = 0; j < n; j++ ) {
            for( int i = 0; i < P.length; i++ ) {
              P[i].shake(r);
              if( P[i].getB() )
                System.out.print("B");
              else
                System.out.print("W");
            }
            System.out.println();
          }
    }
    public static void main(String[] args) {
        int seed = 0;
        if( args.length == 1 )
        seed = Integer.parseInt(args[0]);

        Random r = new Random(seed);
        Piece[] P = new Piece[3];
        P[0] = new Ball(r.nextBoolean());
        P[1] = new Chip(false);
        P[2] = new Die(false, 2, 4);

        playGame(10, P, r);
    }
}