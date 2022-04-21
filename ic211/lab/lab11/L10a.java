import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class L10a {
  public static void main(String[] args) {
    if( args.length == 0 ) {
      System.out.println("Run with argument 0 or 1!");
      System.exit(0);
    }
    boolean flag = args[0].equals("1");

    JLabel label = new JLabel(" LOVE ");
    label.setForeground(Color.RED);

    JButton b1 = new JButton("click to toggle");
    b1.addActionListener(new Toggler(label));

    JButton b2 = new JButton("mystery");

    if( flag )
      b2.addActionListener(new Mystery(label));

    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(label, BorderLayout.WEST);
    f.add(b1, BorderLayout.CENTER);
    f.add(b2, BorderLayout.EAST);
    f.pack();
    f.setVisible(true);

    if( !flag ) {
      while (true) {
        CChange.changeColor(label);
      }
    }
  }
}