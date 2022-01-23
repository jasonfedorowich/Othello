package main.java.com.othello.board;

import javafx.util.Pair;

import java.util.*;

public class Move {


    private Pair<Integer, Integer> p;
    private Color color;

    public Move(Pair<Integer, Integer> p, Color color){
        this.p = p;
        this.color = color;
    }

    public Pair<Integer, Integer> getPair() {
        return p;
    }

    public void setP(Pair<Integer, Integer> p) {
        this.p = p;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public void makeMove(){

        for(Direction d: Direction.values()){
            makeMove(p.getValue() + d.pair.getKey(), p.getKey() + d.pair.getValue(), color, d);
        }
        Grid board = Grid.getInstance();
        board.swap(p.getValue(), p.getKey(), color);





    }
    private boolean makeMove(int x, int y, Color color, Direction d){
        Grid board = Grid.getInstance();
        if(board.isOutOfBounds(x, y))
            return false;
        if(!board.isOccupied(x, y))
            return false;


        if(board.getColor(x, y) == color){
            return true;
        }

        if(makeMove(x + d.pair.getKey(), y  + d.pair.getValue(), color, d)){

            board.swap(x, y, color);
            return true;
        }
        return false;

    }


    public static boolean findColor(Pair<Integer, Integer> p, Direction d, Color c){
        int y = p.getKey();
        int x = p.getValue();

        Grid board = Grid.getInstance();
        //count number before reaching same color if number  == 1 false else return true

        Color oppositeColor = c == Color.BLACK ? Color.WHITE : Color.BLACK;
        int count1 = 0, count2 = 0, count3 = 0;


        while(!board.isOutOfBounds(x, y) ){

            Color cc = board.getColor(x, y);

            if(cc == c){
                count2++;
                break;
            }
            else if(cc == oppositeColor)
                count1++;
            else
                count3++;

            x += d.pair.getKey();
            y += d.pair.getValue();


        }

        return count1 >= 1 && count2 == 1 && count3 == 1;

    }

    public static Map<Pair<Integer, Integer>, Move> getMoves(Color color){

        Set<Pair<Integer, Integer>> set;
        Grid instance = Grid.getInstance();
        if(color == Color.BLACK)
            set = instance.getWhite();
        else
            set = instance.getBlack();

        Set<Pair<Integer, Integer>> potentialMoves = new HashSet<>();

        for(Pair<Integer, Integer> p : set){
            for(Direction direction: Direction.values()){
                int x = p.getValue()+ direction.pair.getKey();
                int y = p.getKey() + direction.pair.getValue();
                if(!instance.isOccupied(x, y)){
                    potentialMoves.add(new Pair<>(y, x));
                }

            }

        }

        Map<Pair<Integer, Integer>, Move> moves = new HashMap<>();

        for(Pair<Integer, Integer> p: potentialMoves){

            for(Direction d: Direction.values()){
                if(findColor(p, d, color)){
                    moves.put(p, new Move(p, color));
                    break;
                }

            }

        }

        return moves;





    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return Objects.equals(p, move.p) && color == move.color;
    }

    @Override
    public int hashCode() {
        return p.hashCode();
    }
}
