import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Aquarium extends JFrame{

  private static String[] A = {"plankton", "carp", "bass", "shark"};
  private static String[] B = {"Carp Speed", "Carp Health", "Bass Speed",
  "Bass Health" , "Shark Speed", "Shark Health"};
  private String whatToAdd = null;
  private static JFrame   f = new JFrame();
  private static DrawArea d = new DrawArea();
  private static JButton addFishButton = new JButton("Add");
  private static JComboBox selectFishType = new JComboBox<String>(A);
  private static JPanel a = new JPanel(new FlowLayout());
  private boolean paused = false;
  private static JButton pauseButton = new JButton("Pause");
  private static JTextField speed = new JTextField(10);
  private static JTextField health = new JTextField(10);
  private static JTextField metabolism = new JTextField(10);
  private static JLabel speedLabel = new JLabel("Speed: ");
  private static JLabel healthLabel = new JLabel("Health: ");
  private static JLabel metabolismLabel = new JLabel("Metabolism: ");
  private static JComboBox speciesOptions = new JComboBox<String>(B);
  private static JTextField entryPoint = new JTextField(10);

  class CustomActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      whatToAdd = (String) selectFishType.getSelectedItem();
      if(whatToAdd.equals(null)){
        return;
      }
      else{
        if(whatToAdd.equals("plankton")){
          d.addPlankton(1);
        }
        if(whatToAdd.equals("carp")){
          d.addCarp(1);
        }
        if(whatToAdd.equals("bass")){
          d.addBass(1);
        }
        if(whatToAdd.equals("shark")){
          d.addShark(1);
        }
      }
      
    }
  }
  class CustomActionListener2 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(paused){
        paused = false;
        d.setDAPause(paused);
      }
      else{
        paused = true;
        d.setDAPause(paused);
      }
    }
  }
  class CustomActionListener3 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if(speciesOptions.getSelectedItem().equals(B[0])){
        d.carpSpeed(Double.parseDouble(entryPoint.getText()));
      }
      if(speciesOptions.getSelectedItem().equals(B[1])){
        d.carpHealth(Double.parseDouble(entryPoint.getText()));
      }
      if(speciesOptions.getSelectedItem().equals(B[2])){
        d.bassSpeed(Double.parseDouble(entryPoint.getText()));
      }
      if(speciesOptions.getSelectedItem().equals(B[3])){
        d.bassHealth(Double.parseDouble(entryPoint.getText()));
      }
      if(speciesOptions.getSelectedItem().equals(B[4])){
        d.sharkSpeed(Double.parseDouble(entryPoint.getText()));
      }
      if(speciesOptions.getSelectedItem().equals(B[5])){
        d.sharkHealth(Double.parseDouble(entryPoint.getText()));
      }

    }
  }

  public Aquarium(){
    ActionListener aList = new CustomActionListener();
    ActionListener bList = new CustomActionListener2();
    ActionListener cList = new CustomActionListener3();

    addFishButton.addActionListener(aList);
    pauseButton.addActionListener(bList);
    entryPoint.addActionListener(cList);
    f.add(a, BorderLayout.SOUTH);
    a.add(selectFishType, BorderLayout.CENTER);
    a.add(addFishButton, BorderLayout.CENTER);
    a.add(pauseButton, BorderLayout.CENTER);
    a.add(speedLabel, BorderLayout.CENTER);
    a.add(speed, BorderLayout.CENTER);
    a.add(healthLabel, BorderLayout.CENTER);
    a.add(health, BorderLayout.CENTER);
    a.add(metabolismLabel, BorderLayout.CENTER);
    a.add(metabolism, BorderLayout.CENTER);
    a.add(speciesOptions, BorderLayout.CENTER);
    a.add(entryPoint, BorderLayout.CENTER);
    f.add(d, BorderLayout.CENTER);
    f.pack();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setTitle("Catren, Patrick -240954");
    f.setVisible(true);

    Thread t = new Thread(d);
    t.start();
  }
  public static void main(String[] args) {
    Aquarium aqua = new Aquarium();
    aqua.setVisible(true);
  }
  public static Fish getFishDisplayed(Fish a){
    return a;
  }
  public static void setFishDisplayed(Fish a){
    speed.setText(String.valueOf(a.getSpeed()));
    health.setText(String.valueOf(a.getHealth()));
    metabolism.setText(String.valueOf(a.getMetabolism()));
  }
  public boolean getPaused(){
    return paused;
  }
}