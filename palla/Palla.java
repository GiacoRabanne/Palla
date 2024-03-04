package palla;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Random.*;

public class Palla extends JPanel implements ActionListener, MouseListener, KeyListener{
    int altezza;
    int lunghezza;
    Timer ballLoop;
    int raggio = 50;
    double velX;
    double v0y;
    double velY = v0y;    
    int lato = 400;
    int colore = 0;
    double t0 = System.currentTimeMillis();
    double tempo;
    int delay = 10;
    double accTerra = (double) 1022*delay/1000;
    int mouseX, mouseY;
    boolean start = false;
    

    private class Punto {
        int x;
        int y;

        Punto(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Punto palla;

    Palla(int lunghezza, int altezza) {
        this.altezza = altezza;
        this.lunghezza = lunghezza;
        setPreferredSize(new Dimension(this.lunghezza, this.altezza));
        setFocusable(true);
        setBackground(Color.white);
        this.addMouseListener(this);
        this.addKeyListener(this);
        ballLoop = new Timer(delay, this);
        palla = new Punto(lunghezza/2 - raggio/2, altezza/2 - raggio/2);
        Label label = new Label("Premi le freccette", SwingConstants.CENTER);
        label.setVisible(true);
        label.setBounds(195, altezza/2 + 225, lunghezza, 50);  
        this.setLayout(null);
        label.setFont(new Font("SF Pro Display", Font.BOLD, 25));
        this.add(label);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {


        g.setColor(Color.BLACK);
        g.drawOval(palla.x, palla.y, raggio, raggio);
        g.setFont(new Font("SF Pro Display", Font.BOLD, 25));
        g.drawString("Tempo: " + (int) tempo + " s", lunghezza/2 - 68, 60);
        g.drawRect(lunghezza/2 - lato/2, altezza/2 - 200, lato, lato);
        g.setColor(new Color(0xa4a0a0));
        
        g.fillRect(lunghezza/2 - lato/2, altezza/2 - 200, lato, lato);
        

        if(collisioneX(palla, lunghezza) && Math.abs(velX) > 0.8) {
            Random rand = new Random();
            colore = rand.nextInt(5);
        }

        if(colore == 0) {
            g.setColor(Color.RED);
        }
        if(colore == 1) {
            g.setColor(Color.BLUE);
        }
        if(colore == 2) {
            g.setColor(Color.GREEN);
        }
        if(colore == 3) {
            g.setColor(Color.PINK);
        }
        if(colore == 4) {
            g.setColor(Color.ORANGE);
        }
        g.fillOval(palla.x, palla.y, raggio, raggio);
        
    }

    boolean collisioneX(Punto p, int lunghezza) {
        return (palla.x >= lunghezza/2 + lato/2 - raggio || palla.x <= lunghezza/2 - lato/2);
    }

    boolean collisioneY(Punto p, int altezza) {
        return (palla.y >= altezza/2 + lato/2 - raggio || palla.y <= altezza/2 - lato/2) ;
    }

    public void moove() {

        if(Math.abs(velX) < 0.5) {
            velX = 0;
        }
        if(Math.abs(velY) < 0.4 && palla.y > altezza/2 + lato/2 - raggio - 5) {
            velY = 0;
            palla.y = altezza/2 + lato/2 - raggio;
        } else {
            velY += 0.9;
        }
        

        
        palla.x += velX;
        palla.y += velY;

        if(collisioneX(palla, lunghezza)) {
            if(palla.x >= lunghezza/2 + lato/2 - raggio) {
                palla.x = lunghezza/2 + lato/2 - raggio;
            } else if (palla.x <= lunghezza/2 - lato/2) {
                palla.x = lunghezza/2 - lato/2;
            }
            velX *= -1;
        }

        if(collisioneY(palla, altezza)) {
            if(palla.y >= altezza/2 + lato/2 - raggio) {
                palla.y = altezza/2 + lato/2 - raggio;
            } else {
                palla.y = altezza/2 - lato/2;
            }
            
            velY *= -0.7;
            velX *= 0.95;
            raggio *= 1.01;
            
            
        }
        
        //System.out.println(palla.y);
        //System.out.println(altezza/2 + lato/2 - 50);
        //System.out.println(velX);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moove();
        repaint();
        tempo = (System.currentTimeMillis() - t0)/1000;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       /*  if(!start) {
            mouseX = e.getX();
            mouseY = e.getY();
            velX = (mouseX - lunghezza/2)/20;
            v0y = (mouseY - altezza/2)/20;
            velY = v0y;
            start = true;
        } */
        
        
        //System.out.println(velX);
        //System.out.println(v0y);
        //ballLoop.start();
    }

    

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            velY = -20;
            ballLoop.start();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            velY = 20;
            ballLoop.start();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            velX = -10;
            ballLoop.start();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            velX = +10;
            ballLoop.start();
        }
    }

    //action listener non sfruttati:

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    
}
