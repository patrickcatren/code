import java.util.*;

public class Lab03{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int count = 0;
        Box boxy = null;
        String input = in.next();
        while(!(input.equals("done"))){
            if(input.equals("add")){
                Point a = Point.read(in);
                if(count == 0){
                   boxy = new Box(a); 
                }
                else{
                    boxy.growBy(a);
                }
                count++;
            }
            else if(input.equals("box")){
                System.out.println(boxy.toString());
                count++;
            }
            else if(input.equals("map")){
                Point a = Point.read(in);
                Point b = boxy.mapIntoUnitSquare(a);
                count++;
            }
            else
            {
                System.out.println("Error! Unknown command " + '"' + input + '"' + "!");
            }
            input = in.next();
        }
    }
}