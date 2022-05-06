import java.awt.*;
import javax.swing.*;

public class Aquarium extends JFrame{

  public static void main(String[] args) {
    JFrame   f = new JFrame();
    DrawArea d = new DrawArea();

    f.add(d);
    f.pack();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
    
    Thread t = new Thread(d);
    t.start();
  }
}