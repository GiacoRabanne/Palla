package palla;
import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int lunghezza = 600;
        int altezza = lunghezza;
        
        JFrame frame = new JFrame("Palla");
        frame.setBackground(Color.white);
        frame.setVisible(true);
        frame.setSize(lunghezza, altezza);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Palla palla = new Palla(lunghezza, altezza);
        frame.add(palla);
        frame.pack();
        palla.requestFocus();
    }
}
