import java.awt.event.*;

public class Responder implements ActionListener {
  private Frame f;
  public Responder(Frame f) {
    this.f = f;
  }

  public void actionPerformed(ActionEvent e) {
    //make loan object, calculate info
    f.createLoan();
    f.calculate();
    f.resetFocus();
  }
}
