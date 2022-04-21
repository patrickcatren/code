import java.util.Random;

public abstract class Expense extends Transaction{
    public Expense(int f, MyDate sD, String n, boolean m, double p, Random r, MyDate l){
        super(f, sD, n, m, p, r, l);
    }
}
