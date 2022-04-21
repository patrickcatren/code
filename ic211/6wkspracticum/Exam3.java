import java.util.Scanner;

public class Exam3 {

    public static void main(String[] args) {
        Queue craftlist = new Queue();
        Scanner sc = new Scanner(System.in);
        String input;
        input = sc.nextLine();
        while(!(input.equals("quit"))){
            String[] parsedInput = input.split(" ", 2);
            if(parsedInput[0].equals("add")){
                Aircraft A = new Aircraft(parsedInput[1]);
                craftlist.enqueue(A);
            }
            else{
                if(parsedInput[0].equals("remove")){
                    craftlist.remove(parsedInput[1]);
                }
                else{
                    Queue C = craftlist.filterForRange(Integer.parseInt(parsedInput[1]));
                    C.printAll();
                }
            }
            input = sc.nextLine();
        }
        System.out.println("Goodbye!");
    }
}