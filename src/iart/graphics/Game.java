package iart.graphics;

import java.awt.*;

import javax.swing.*;

public class Game extends JFrame {

    public Game(){
        super("Hopeless");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,400);
        CenterPanel centerPanel = new CenterPanel();
        setLayout(new BorderLayout());
        NorthPanel north = new NorthPanel();
        Panel east = new Panel();
        east.add(Box.createHorizontalStrut(100));
        WestPanel west = new WestPanel();
        Panel south = new Panel();
        south.add(Box.createVerticalStrut(100));
        getContentPane().add(north, BorderLayout.PAGE_START);
        getContentPane().add(west, BorderLayout.WEST);
        getContentPane().add(south, BorderLayout.SOUTH);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(east, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Game();
    }

}
