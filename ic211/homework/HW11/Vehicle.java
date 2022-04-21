public class Vehicle {
    private String type;  // truck, car, motorcycle, plane
    private String model;
    private int mpg, numWheels;
    private boolean canfly;
  
    public Vehicle(String type, String model, int mpg) {
      this.type = type;
      this.model = model;
      this.mpg = mpg;
      this.canfly = type.equals("plane");
      this.numWheels = 4;
      if( type.equals("plane") )
        this.numWheels = 3;
      else if( type.equals("motorcycle") )
        this.numWheels = 2;
      }
  
    public boolean doesItFly() { return canfly; }
  
    public String noise() {
      if( type.equals("truck") )
        return "bumbumbum";
      else if( type.equals("car") )
        return "vroom";
      else if( type.equals("motorcycle") )
        return "errrrrrm";
      else if( type.equals("plane") )
        return "woooosh";
      else
        return "hmmmm";
    }
  
    public String toString() {
      return model + " " + noise() + " does" + (doesItFly() ? "" : " not") + " fly with " + numWheels + " wheels.";
    }
  }