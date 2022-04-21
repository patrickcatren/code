import java.util.*;

public class Lab1b {   
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = in.nextLine();
        System.out.print("Please input an integer: ");
        int n = in.nextInt();
        System.out.print("Please input a second integer: ");
        int k = in.nextInt();
        int numerator = n;
        for(int i = 1; i < k; i++)
        {
            numerator = numerator * (n - i);
        }
        int denominator = k;
        for(int j = (k - 1); j > 0; j--)
        {
            denominator = denominator * j;
        }
        int oddsnum = numerator;
        int oddsden = denominator;
        int lcd = oddsden;

        for(int i = oddsden; i < oddsnum; i++)
        {
            if((oddsnum % lcd == 0) && (oddsden % lcd == 0))
            {
                oddsnum = oddsnum / lcd;
                oddsden = oddsden / lcd;
                break;
            }
        }
        
        double oddsdouble = (double)oddsden/(double)oddsnum;

        System.out.println("The two ints were " + n + " and " + k);
        System.out.println("numerator = " + numerator);
        System.out.println("denominator = " + denominator);
        System.out.println("odds = " + oddsden + " in " + oddsnum + " = " + oddsdouble);
        System.out.println("Goodbye " + name + ".");
    }
}