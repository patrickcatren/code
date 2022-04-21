import javax.swing.*;
import java.awt.event.*;

public class Toggler implements ActionListener {
  private JLabel label;
  public Toggler(JLabel label) {
    this.label = label;
  }

  public void actionPerformed(ActionEvent e) {
    label.setText(label.getText().equals(" LOVE ") ? " HATE " : " LOVE ");
  }
}