import java.util.Scanner;
import java.io.*;

public class HW11 {

  /**
   * Open a file and read its vehicles. Assume 10 in the file.
   */
  public static Vehicle[] readFile(String path) {
    Scanner sc = null;
    try {
      sc = new Scanner(new BufferedReader(new FileReader(path)));
    }
    catch(FileNotFoundException fe) {
      System.out.println("File " + path + " not found!");
      return null;
    }

    Vehicle[] vehicles = new Vehicle[10];
    int i = 0;
    while( sc.hasNext() )
      vehicles[i++] = new Vehicle(sc.next(), sc.next(), sc.nextInt());
    return vehicles;
  }

  /**
   * Main method, requires one arg (file path)
   */
  public static void main(String[] args) {
    if( args.length != 1 ) {
      System.out.println("HW11 <file>");
      System.exit(1);
    }

    Vehicle[] vs = readFile(args[0]);
    for( int i = 0; i < vs.length; i++ )
      System.out.println(vs[i]);
    }
}