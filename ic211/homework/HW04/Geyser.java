import java.util.*;

public class Geyser{
    private String name;
    private double baseProbFactor;
    private int    daysSinceLastBlow;

    public Geyser(String n, double b, int d){
        name = n;
        baseProbFactor = b;
        daysSinceLastBlow = d;
    }

    public static Geyser read(Scanner sc) {
        String na = "";
        na = sc.next();
        double ba = 0.0;
        ba = 1.0 - sc.nextDouble();
        int da = 0;
        da = sc.nextInt();
        Geyser g = new Geyser(na, ba, da);
        return g;
    }

  public int  simDay(Random r) {
    int tmp         = ++this.daysSinceLastBlow;
    double blowProb = 1 - Math.pow(this.baseProbFactor, tmp);

    if (r.nextDouble() <= blowProb) {
      this.daysSinceLastBlow = 0;
    } else {
      tmp = -1;
    }
    return tmp;
  }

  /**
   * Simulate day d for array G of geysers & output results
   */
  public static void simDay(int a, Geyser G[], Random r) {
    int k = 0;

    System.out.print("Day " + a + ":");

    for (int i = 0; i < G.length; i++) {
      if (G[i].simDay(r) != -1) {
        System.out.print((k++ > 0 ? ", " : " ") + G[i].name);
      }
    }
    System.out.println();
  }
}