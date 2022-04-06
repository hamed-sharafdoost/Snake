package snake;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class Frame1 extends JPanel implements KeyListener
{
    JFrame frame = new JFrame();
    public char direction = 'd';
    private int bodylength=6;
    private int unit = 30;
    private int unit1 =30;
    private final int sizex = 30;
    private final int sizey = 30;
    private int applex =0;
    private int appley =0;
    private final int[] x = new int[100];
    private final int[] y = new int[100];
    JLabel label = new JLabel();
    Timer timer;
    Random random;
    Myaction action = new Myaction();
    Frame1()
    {
        random = new Random();
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(600,600));
        frame.add(this);
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener((KeyListener)this);
        timer = new Timer(85,action);
        timer.start();
        frame.pack();
        frame.setVisible(true);
    }
    public void move()
    {
       for(int j = bodylength;j >0;j--)
        {
            x[j] = x[j-1];
            y[j] = y[j-1];
        }
       
        switch(direction)
        {
            case 'w':
                y[0]-=sizex;
                break;
            case 's':
                y[0]+=sizex;
                break;
            case 'd':
                x[0]+=sizex;
                break;
            case 'a':
                x[0]-=sizex;
                break;
        }
        
        if(x[0] >= 600)
        {
            x[0]=0;
        }
        else if(x[0] < 0)
        {
            x[0]=600;
        }
        else if(y[0] >= 600)
        {
            y[0]=0;
        }
        else if(y[0] < 0)
        {
            y[0]=600;
        }
         
    }
    public void collision()
    {
        for(int i=1;i<bodylength;i++)
        {
            if(x[0] == x[i] && y[0] == y[i])
            {
                timer.stop();
            }
            
        }
    }
    public void apple()
    {
       
        applex = ((int) (Math.random() * 20))*30;
        appley = ((int) (Math.random() * 20))*30;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        direction = e.getKeyChar();
    }
    @Override
    public void paintComponent(Graphics g)
    {
         super.paintComponent(g);
         for(int h = 0; h <20;h++)
         {
             g.drawLine(unit+=30, 0, unit, 600);
             g.drawLine(0, unit1+=30, 600, unit);
         }
         unit=0;
         unit1=0;
         for(int i = 0;i<bodylength;i++)
        {
            if(i==0)
            {
                g.setColor(Color.green);
                g.fillRect(x[i],y[i],sizex,sizey);
            }
            else
            {
            g.setColor(new Color(0,200,0));
            g.fillRect(x[i],y[i],sizex,sizey);
            }
        }
         if(x[0] == applex && y[0] == appley)
         {
             apple();
             bodylength++;
         }
        
         g.setColor(Color.red);
         g.fillOval(applex,appley,20,20);
    }
   private class Myaction implements ActionListener
   {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            
            move();
            collision();
            repaint();
        }
       
   }
}
