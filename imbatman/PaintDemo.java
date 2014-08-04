package paintdemo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

class PaintPanel extends JPanel
{
    Insets ins;
    Random rand;
    boolean [][] m = new boolean[16][10]; 
    int [] zmei = new int [100];
    String napravlenie = "vniz";
    int dlina;
   
    PaintPanel(int w, int h)
    {
        setOpaque(true);
        
        setBorder(BorderFactory.createLineBorder(Color.red, 1));
        
        setPreferredSize(new Dimension(w, h));
      
        rand = new Random();
    }
public class PaintDemo
{

}
