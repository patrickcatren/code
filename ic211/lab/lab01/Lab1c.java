import java.util.*;

public class Lab1c {
    public static void main(String[] args) {
        Random rand = new Random(42);
        Scanner in = new Scanner(System.in);
        int value = rand.nextInt(11);
        System.out.print("Guess a number between 0 and 10: ");
        int guess = in.nextInt();
        int count = 1;
        if(guess != value)
        {
            while(guess != value)
            {
                System.out.print("Wrong! Guess again: ");
                guess = in.nextInt();
                count++;
            }
        }
        System.out.print("Right after " + count + " guesses!");

    }
}
