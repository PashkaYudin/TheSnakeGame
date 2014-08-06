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
    protected void paintComponent(Graphics g)
{
    super.paintComponent(g);
    
    for (int i = 0; i < 15; i++)
    {
      for (int j = 0; j < 10; j++)
       {
          if (m[i][j])
          {     
              g.setColor(Color.green);
              g.fillRect(j*20, i*20, 20, 20);
              g.setColor(Color.black);
              g.drawRect(j*20, i*20, 20, 20);
          } 
       }
    }
      
}
public void changeBorderSize(int size) 
{
    setBorder(BorderFactory.createLineBorder(Color.CYAN, size));
    
}           


public class PaintDemo
{
      
    JMenuBar jMenuBar1 = new JMenuBar();
   
    JMenu jMenu1 = new JMenu("Начать");
    JMenuItem jMenuItem1 = new JMenuItem("Новая");
    JMenuItem jMenuItem2 = new JMenuItem("Отмена");
    
    
    JLabel jlab; 
    PaintPanel pp = new PaintPanel(200, 300); 
    int index;
    boolean status = false;
    boolean statusGame = false;
    public long start;
    public Timer swTimer;
}
    
    ActionListener timerAL = new ActionListener()
    {
        public void actionPerformed(ActionEvent ae)
        {
            updateTime();
        }
    }

    boolean big;

PaintDemo()
{
    
}
