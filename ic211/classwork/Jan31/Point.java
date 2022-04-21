public class Point {

    private double x, y;

    public Point(double px, double py) {
        x = px;
        y = py;
    }

    //use an instance method when modifying the object
    public void reflectXAxis() {y=-y}
    public double getX() {return x;}

    //or when creating objects
    public static Point randomPointInUnitSquare(Random rand) {
        return new Point(rand.NextDouble(),rand.NextDouble());
    }

    //either way
    //Operations on two points
    
}