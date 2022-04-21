import java.util.Scanner;

public class Exam2 {

    public static void main(String[] args) {
        Queue craftlist = new Queue();
        Scanner sc = new Scanner(System.in);
        String input;
        int cond = 1;
        while(cond == 1){
            input = sc.nextLine();
            String[] parsedInput = input.split(" ", 2);
            if(parsedInput[0].equals("add")){
                Aircraft A = new Aircraft(parsedInput[1]);
                craftlist.enqueue(A);
            }
            else{
                Queue C = craftlist.filterForRange(Integer.parseInt(parsedInput[1]));
                C.printAll();
                cond = 0;
            }
        }
    }
}