import javax.swing.*;
import java.awt.event.*;

public class Mystery implements ActionListener {
  private JLabel label;
  private Thread t = new Thread();
  public Mystery(JLabel label) {
    this.label = label;
  }

  public void actionPerformed(ActionEvent e) {
    if(!t.isAlive()){    
        t.start();
        CChange.changeColor(label);
    }
  }
}
