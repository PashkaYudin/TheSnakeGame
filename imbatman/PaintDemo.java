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
            JFrame jfrm = new JFrame("Змейка");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(240, 400);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jlab = new JLabel("Статус игры");
        
        
                     jMenuItem1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
  
                        swTimer = new Timer(500, timerAL); 
                jlab.setText("Змейка поползла=)"); 
               
                for (int i = 0; i < 15; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        pp.m[i][j] = false;
                    }
                }
                for (int i = 0; i < 100; i++)
                { pp.zmei[i] = -1;}
                pp.m[0][3] = true;
                pp.m[0][4] = true;
                pp.m[0][5] = true;
                pp.dlina = 3;
                pp.zmei[0] = 1;
                pp.zmei[1] = 2;
                pp.zmei[2] = 3;
                pp.napravlenie = "vniz";
                status = true;
                statusGame =true;
               
                swTimer.start(); 
            }
        });
        jMenuItem2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae)
            {
                swTimer.stop();
                statusGame = false;
                if (!big) pp.changeBorderSize(5);
                else pp.changeBorderSize(1);
                big = !big;
            }
        });
        jfrm.addKeyListener(new KeyAdapter() 
        {

            public void keyReleased(KeyEvent e) 
            {

                if (statusGame){
                if (40 == e.getKeyCode() && !"vverh".equals(pp.napravlenie))
                { pp.napravlenie = "vniz"; }
                if (37 == e.getKeyCode()&& !"vpravo".equals(pp.napravlenie))
                { pp.napravlenie = "vlevo"; }
                if (39 == e.getKeyCode()&& !"vlevo".equals(pp.napravlenie))
                { pp.napravlenie = "vpravo"; }
                if (38 == e.getKeyCode() && !"vniz".equals(pp.napravlenie))
                { pp.napravlenie = "vverh"; }
             pp.repaint();   
            }   
            }
        });
jMenuBar1.setLocation(0, 0);
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);
        jMenuBar1.add(jMenu1);
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jMenuBar1);
        jfrm.getContentPane().add(pp);
        jfrm.setVisible(true);
        
        
    }

void updateTime()
{   
    boolean en = false, hh = false;
    
    if (status)
    {  
        if ("vniz".equals(pp.napravlenie)) 
        {
           
            for (int i = 10; i >= 0; i-- )
            {
                pp.zmei[i+1] = pp.zmei[i];
            }
            pp.zmei[0] += 10;
            if (pp.m[pp.zmei[0]/10][pp.zmei[0]%10]) hh =true;
            if (pp.zmei[0]/10 == 15) en = true;
        }
        
        if ("vverh".equals(pp.napravlenie)) {
           
            for (int i = 10; i >= 0; i-- )
            {
                pp.zmei[i+1] = pp.zmei[i];
            }
            pp.zmei[0] -= 10;
            if (pp.zmei[0]/10 < 0) {en = true;}
           
            try {
            if (pp.m[pp.zmei[0]/10][pp.zmei[0]%10]) hh =true;}
            catch (java.lang.ArrayIndexOutOfBoundsException ex)
            {
                jlab.setText("ВЫ ПРОИГРАЛИ");
            }
            
        } 
        
        if ("vlevo".equals(pp.napravlenie)) {
           
            for (int i = 10; i >= 0; i-- )
            {
                pp.zmei[i+1] = pp.zmei[i];
            }
            pp.zmei[0] -= 1; 
            if (pp.m[pp.zmei[0]/10][pp.zmei[0]%10]) hh =true;
            if (pp.zmei[0]%10 == 9) en = true;
        } 
     
        if ("vpravo".equals(pp.napravlenie)) {
           
            for (int i = 10; i >= 0; i-- )
            {
                pp.zmei[i+1] = pp.zmei[i];
            }
            pp.zmei[0] += 1;
            if (pp.m[pp.zmei[0]/10][pp.zmei[0]%10]) hh =true;
            if (pp.zmei[0]%10 == 0) en = true;
        } 
        
        for (int i = 0; i < 10; i++ )
            {
                
                if (pp.zmei[i]== -1 && !hh) pp.zmei[i-1] = -1;
                
            }
        
          for (int i = 0; i < 15; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        pp.m[i][j] = false;
                    }
                }
          
          if (hh) { 
              Random rand = new Random();
              index = rand.nextInt(149); 
             
              pp.dlina++;
           
              if (pp.dlina == 10) 
              {
                  
                 for (int i = 0; i < 15; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        pp.m[i][j] = false;
                    }
                }
                for (int i = 0; i < 100; i++)
                { pp.zmei[i] = -1;}
                pp.m[0][3] = true;
                pp.m[0][4] = true;
                pp.m[0][5] = true;
                pp.dlina = 3;
                index = 18;
                pp.zmei[0] = 3;
                pp.zmei[1] = 4;
                pp.zmei[2] = 5;
                for (int i = 0; i < 10; i++)
                {
                   pp.m[15][i] = true; 
                }
                pp.napravlenie = "vniz";
                status = true;
                statusGame =true;
                int g = swTimer.getDelay();
                swTimer.setDelay(g-50);
              }
          }
          pp.m[index/10][index%10] = true; 
                for (int i = 0; i < 15; i++)
                {
                    for (int j = 0; j < 10; j++)
                    {
                        for (int k = 0; k < 10; k++){
                        if ((i*10+j) == pp.zmei[k])
                        {pp.m[i][j] = true;}}
                    }
                }
    if (!en) pp.repaint();    
    }
  
    if (en) {swTimer.stop(); statusGame = false; status = false; jlab.setText("ВЫ ПРОИГРАЛИ");
    } 
    
}

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new PaintDemo();
            }
        });
       
    }
}


