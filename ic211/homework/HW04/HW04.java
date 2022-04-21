import java.util.*;

public class HW04 {

  /**
   * This program can be run in one of two ways, either as
   * java HW06    -or-   java HW06 <seedval>
   * The first ways is the usual, the seed for the random
   * number generator is the current time.  The second way
   * is good for testing, since the same seed should always
   * yield the same output.
   */
  public static void main(String[] args) {
    //  initialize and read in Geysers
    long seed = System.currentTimeMillis();

    if (args.length > 0) {
      seed = Integer.parseInt(args[0]);
    }
    Random  r  = new Random(seed);
    Scanner in = new Scanner(System.in);
    int     N  = in.nextInt();
    Geyser[] G = new Geyser[N];
    for(int i = 0; i < N; i++)
    {
        G[i] = Geyser.read(in);
    }


    // Simulate 20 days
    for (int d = 1; d <= 20; d++) {
      Geyser.simDay(d, G, r);
    }
  }
}