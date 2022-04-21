import javax.swing.*;
import java.awt.*;

public class CChange {
  public static void changeColor(JLabel label) {
    System.out.println("Thread ID: " + Thread.currentThread().getId());
    System.out.print("new color: ");
    String s = System.console().readLine();
    Color  c = label.getForeground();

    if( s.equals("red") )
      c = Color.RED;
    else if( s.equals("blue") )
      c = Color.BLUE;
    else if( s.equals("green") )
      c = Color.GREEN;
    else if( s.equals("cyan") )
      c = Color.CYAN;
    else
      System.out.println("Unknown color!");

    label.setForeground(c);
  }
  
}