public class Box {

    private double xmin, ymin, xmax, ymax;

    // constructor for Box that consists of a single point
    public Box(Point p) {
        xmin = p.returnX();
        xmax = p.returnX();
        ymin = p.returnY();
        ymax = p.returnY();
    }

    // constructor for the Box containing two initial points
    public Box(Point a, Point b) {
        if(a.returnX() >= b.returnX()){
            xmin = b.returnX();
            xmax = a.returnX();
        }
        else{
            xmin = a.returnX();
            xmax = b.returnX();
        }
        if(a.returnY() >= b.returnY()){
            ymin = b.returnY();
            ymax = a.returnY();
        }
        else{
            ymin = a.returnY();
            ymax = b.returnY();
        }
    }

    // growBy(p) expands the bounding box (if needed) to include point p
    public void growBy(Point p) {
        if(p.returnX() > xmax)
        {
            xmax = p.returnX();
        }
        if(p.returnX() < xmin)
        {
            xmin = p.returnX();
        }
        if(p.returnY() > ymax)
        {
            ymax = p.returnY();
        }
        if(p.returnY() < ymin)
        {
            ymin = p.returnY();
        }
    }

    // given point p in the bounding box, returns associated point in the
    // unit square (see notes); returns null if p is not inside the bounding box.
    public Point mapIntoUnitSquare(Point p) {
        if(p.returnX() > xmax || p.returnX() < xmin || p.returnY() > ymax || p.returnY() < ymin){
            System.out.println("error");
            return null; 
        }
        double x1, y1;
        x1 = (p.returnX() - xmin)/(xmax-xmin);
        y1 = (p.returnY() - ymin)/(ymax-ymin);
        
        Point pointy = new Point(x1, y1);
        System.out.println(pointy.returnX() + " " + pointy.returnY());
        return pointy;
    }

    // returns string representation like: 2.0 < x < 9.0, 3.0 < y < 7.0
    public String toString() {
        String rep = xmin + " < x < " + xmax + ", " + ymin + " < y < " + ymax;
        return rep;
    }

    public static void main(String[] args){
        Point a = new Point(1.0, 2.0);
        Point b = new Point(1.3, 2.5);
        Point c = new Point(0.5, 2.3);
        Point d = new Point(1.2, 2.3);
        Point e = new Point(0.1, 7.5);

        Box one = new Box(a);
        String boxone = one.toString();
        System.out.println(boxone);

        Box two = new Box(a, b);
        String boxtwo = two.toString();
        System.out.println(boxtwo);

        two.growBy(c);
        String boxthree = two.toString();
        System.out.println(boxthree);

        Point f = two.mapIntoUnitSquare(d);
        
        Point g = two.mapIntoUnitSquare(e);
    }
}