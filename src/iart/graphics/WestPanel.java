package iart.graphics;

import iart.algorithms.AStarSearch;
import iart.game.Hopeless;
import iart.utilities.*;
import sun.nio.cs.ext.MacCentralEurope;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by inesa on 19/05/3016.
 */
public class WestPanel extends JPanel {

    private JButton dfs;
    private JButton bfs;
    private JButton iddfs;
    private JButton greedy;
    private JButton aStar;
    private JButton idaStar;
    Hopeless hopeAStar3;
    JLabel jlabel;

    public static final int PREF_W = 200;
    public static final int PREF_H = 300;


    public WestPanel() {
        add(Box.createHorizontalStrut(100));
        //setBorder(BorderFactory.createLineBorder(Color.black));

        dfs = new JButton("DFS");
        bfs = new JButton("BFS");
        iddfs = new JButton("IDDFS");
        greedy = new JButton("GREEDY");
        aStar = new JButton("A*");
        idaStar = new JButton("IDASTAR");

        add(dfs);
        dfs.setPreferredSize(new Dimension(100, 30));
        add(bfs);
        bfs.setPreferredSize(new Dimension(100, 30));
        add(iddfs);
        iddfs.setPreferredSize(new Dimension(100, 30));
        add(aStar);
        aStar.setPreferredSize(new Dimension(100, 30));
        add(idaStar);
        idaStar.setPreferredSize(new Dimension(100, 30));
        add(greedy);
        greedy.setPreferredSize(new Dimension(100, 30));


        jlabel = new JLabel("Score: " + Game.score);
        jlabel.setFont(new Font("Verdana", 1, 15));
        jlabel.setHorizontalAlignment(0);
        jlabel.setVerticalAlignment(0);
        jlabel.setPreferredSize(new Dimension(200, 60));
        add(jlabel);

        buttons();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public void buttons() {

        aStar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ArrayList<Integer> initialTable = new ArrayList<>(Game.hopeAStar3.table);
                AStarSearch rip = new AStarSearch(Game.hopeAStar3, true);
                Thread t = new Thread(rip);
                t.start();

                try {
                    Thread thread = new Thread() {
                        public void run() {
                            rip.setAccelarator(true);
                        }
                    };

                    thread.start();
                    t.join();
                    thread.interrupt();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                ArrayList<iart.utilities.Point> bestMoves = rip.getAStarMoves();

                Game.hopeAStar3.table = initialTable;

                if (rip.getBestScore() != 0)
                    for (iart.utilities.Point move : bestMoves) {
                        Game.score += Game.hopeAStar3.makePlay(move, new ArrayList<iart.utilities.Point>());

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        Game.centerPanel.paintImmediately(move.getCol() * Game.centerPanel.REC_WITH, move.getRow() * Game.centerPanel.REC_WITH, Game.centerPanel.REC_WITH, Game.centerPanel.REC_WITH);
                        jlabel.setText("Score: " + Game.score);

                        System.out.println("Move ----" + move.getRow() + " " + move.getCol());
                        Game.hopeAStar3.print();

                    }
                System.out.println("Final ----");
                Game.hopeAStar3.print();


                System.out.println("Moves = " + bestMoves);
                System.out.println("A* Score = " + rip.getBestScore());

                System.out.println("A* Sum Of Points = " + Game.score);


            }
        });
    }
}
