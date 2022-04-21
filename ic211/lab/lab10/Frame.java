import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
  private JTextField loanAmount;
  private JTextField monthlyPayment;
  private JComboBox<Double> interestRate;
  private JTextField payoff;
  private JTextField cost;
  public  Loan A;
  private final Double[] units = { 3.5, 3.75, 4.0, 4.25, 4.5, 4.75, 5.0, 5.25, 5.5, 5.75, 6.0, 6.25, 6.5, 6.75, 7.0, 7.25, 7.5};


  // Sets focus to the loanAmount text field
  public void resetFocus() {
    loanAmount.requestFocus();
  }

  public void createLoan(){
      //create a loan
      double AAmt = Double.parseDouble(loanAmount.getText());
      double AUnit = (Double)interestRate.getSelectedItem();
      double AMon = Double.parseDouble(monthlyPayment.getText());
      A = new Loan(AAmt, AUnit, AMon);
  }
  public void calculate(){
    payoff.setText(Integer.toString(A.months()));
    cost.setText(Double.toString(A.cost()));
  }

  // constructor
  public Frame() {
    // create components
    JPanel p         = new JPanel(new FlowLayout());
    JPanel q         = new JPanel(new FlowLayout());
    JPanel s         = new JPanel(new FlowLayout());
    JPanel t         = new JPanel(new FlowLayout());
    JLabel amountLabel = new JLabel("loan amount ");
    JLabel rateLabel = new JLabel(" interest rate ");
    JLabel monthlyLabel   = new JLabel(" monthly payment ");
    JButton calc = new JButton("calculate");
    JLabel untilPayoffLabel = new JLabel("months to payoff  ");
    JLabel costLabel = new JLabel("cost ");

    loanAmount = new JTextField(10);
    monthlyPayment   = new JTextField(10);
    payoff = new JTextField(10);
    payoff.setEditable(false);
    cost = new JTextField(10);
    cost.setEditable(false);
    interestRate = new JComboBox<Double>(units);
    Responder r = new Responder(this);

    // add action listeners
    calc.addActionListener(r);

    // add components to frame & ready for display
    add(p, BorderLayout.NORTH);
    p.add(amountLabel);
    p.add(loanAmount);
    p.add(rateLabel);
    p.add(interestRate);
    p.add(monthlyLabel);
    p.add(monthlyPayment);
    add(q, BorderLayout.EAST);
    q.add(calc);
    add(s, BorderLayout.CENTER);
    s.add(untilPayoffLabel);
    s.add(payoff);
    add(t, BorderLayout.SOUTH);
    t.add(costLabel);
    t.add(cost);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    resetFocus();
    pack();
  }
}
