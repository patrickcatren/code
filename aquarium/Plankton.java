import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Plankton extends Fish {
    public Plankton(double x, double y, Random r){
        super(x, 0, r);
        int gy = r.nextInt(1200);
        this.setGoal(x, gy);
        changeRadius(4);
    }
    public void changeGoal(){}
}
