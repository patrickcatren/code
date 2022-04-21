public class Aircraft{
    private String name;
    private int weight;
    private int speed;
    private int seats;
    private int range;

    public Aircraft(String s) {
        String [] splitstring = s.split(" ", 5);
        name = splitstring[0];
        weight = Integer.parseInt(splitstring[1]);
        speed= Integer.parseInt(splitstring[2]);
        seats = Integer.parseInt(splitstring[3]);
        range = Integer.parseInt(splitstring[4]);
    }
    public String toString(){
        String A = this.name + ": " + this.weight + " lbs, " + this.speed + 
        " knots, " + this.seats + " seats, " + this.range + " nm";
        return A;
    }
    public String returnName(){
        return this.name;
    }
    public int returnWeight(){
        return this.weight;
    }
    public int returnSpeed(){
        return this.speed;
    }
    public int returnSeats(){
        return this.seats;
    }
    public int returnRange(){
        return this.range;
    }
}