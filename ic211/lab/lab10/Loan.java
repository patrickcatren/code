import javax.swing.*;
import java.math.*;

public class Loan{
    private double amount;
    private double rate;
    private double monthly;
    public Loan(double amt, double rte, double mon){
        amount = amt;
        rate = rte;
        monthly = mon;
    }
    public int months(){
        int result = 0;
        double balance = amount;
        while(balance > 0){
            double interest = (balance * rate)/1200;
            balance = balance + interest - monthly;
            result++;
        }
        return result;
    }
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
    
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public double cost(){
        //cost = total amount paid - amount of overpayment repaid - original loan amount
        int months = 0;
        double balance = amount;
        while(balance > 0){
            double interest = (balance * rate)/1200;
            balance = balance + interest - monthly;
            months++;
        }
        double cost = monthly * months;
        cost = cost + balance - amount;
        cost = round(cost, 2);
        return cost;
    }

    public static void main(String[] args) {
        Frame f = new Frame();
        f.setTitle("Catren, Patrick - m240954");
        f.setVisible(true);
        // double amt = Double.parseDouble(args[0]);
        // double rte = Double.parseDouble(args[1]);
        // double mon = Double.parseDouble(args[2]);

        // Loan A = new Loan((amt), rte, mon);
        // int b = A.months();
        // double c = A.cost();
        // System.out.println(b);
        // System.out.println(c);
    }
}