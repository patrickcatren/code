import java.util.*;

public class Point {
    private double x, y;
    public Point(double x1, double y1){
        x = x1;
        y = y1;
    }
    public static Point read(Scanner sc){
        Double x2 = sc.nextDouble();
        Double y2 = sc.nextDouble();
        Point p = new Point(x2, y2);
        return p;
    }
    public String toString(){
        String s = x + " " + y;
        return s;
    }
    public double returnX(){
        return x;
    }
    public double returnY(){
        return y;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Point p = read(sc);
        String s = p.toString();
        System.out.println(s);
    }
}