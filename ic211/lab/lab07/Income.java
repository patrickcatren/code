import java.util.Random;

public abstract class Income extends Transaction{
    public Income(int f, MyDate sD, String n, boolean m, double p, Random r, MyDate l){
        super(f, sD, n, m, p, r, l);
    }
}