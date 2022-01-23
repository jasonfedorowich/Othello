package main.java.com.othello.gui;


import main.java.com.othello.board.Grid;
import main.java.com.othello.board.Move;
import javafx.util.Pair;


import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Board {
    private JButton[][] buttons;
    private JFrame frame;
    private JPanel panel;
    private AtomicBoolean waiting;
    private Map<Pair<Integer, Integer>, Move> moves;

    private static Board instance;


    private Board(){
        frame = new JFrame("Othello");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        init();
        frame.setVisible(true);
        frame.add(panel);
        waiting = new AtomicBoolean();
        waiting.set(false);




    }

    public static Board getInstance(){
        if(instance == null)
            instance = new Board();
        return instance;

    }
    public void init(){
        reset();

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                panel.add(buttons[i][j]);
            }
        }
    }

    public void reset(){
        buttons = new JButton[8][8];

        for (int i = 0; i < 8; i++){
            buttons[i] = new JButton[8];

            for(int j = 0; j < 8; j++){
                buttons[i][j] = new RoundButton(i, j);


            }
        }

        ((RoundButton)buttons[3][3]).setColor(Color.BLACK);
        ((RoundButton)buttons[4][4]).setColor(Color.BLACK);
        ((RoundButton)buttons[3][4]).setColor(Color.WHITE);
        ((RoundButton)buttons[4][3]).setColor(Color.WHITE);



    }

    public void activate(Pair<Integer, Integer> p){
        ((RoundButton)buttons[p.getKey()][p.getValue()]).toggleButton();
    }
    public void toggleWaiting(){
        boolean w = waiting.get();
        waiting.set(!w);
    }
    public boolean waitingForMove(){
        return waiting.get();
    }

    public void addMoves(Map<Pair<Integer, Integer>, Move> moveSet) {
        for(Pair<Integer, Integer> p: moveSet.keySet()){
            activate(p);
        }
        moves = moveSet;
    }

    public void makeMove(int x, int y) {
        Pair<Integer, Integer> p = new Pair<>(y, x);
        Move move = moves.get(p);
        move.makeMove();
        Grid grid = Grid.getInstance();

        for(int i = 0; i < 8; i++){

            for(int j = 0; j < 8; j++){

                if(grid.getColor(j, i) == main.java.com.othello.board.Color.BLACK){
                    ((RoundButton)buttons[i][j]).toBlack();
                }else if(grid.getColor(j, i) == main.java.com.othello.board.Color.WHITE){
                    ((RoundButton)buttons[i][j]).toWhite();
                }
            }
        }

        for(Pair<Integer, Integer> pa : moves.keySet()){
            activate(pa);
        }

        waiting.set(false);

    }
}
