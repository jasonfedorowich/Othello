package main.java.com.othello;

import main.java.com.othello.board.Color;
import main.java.com.othello.board.Grid;
import main.java.com.othello.board.Move;
import main.java.com.othello.gui.Board;
import main.java.com.othello.player.Player;
import javafx.util.Pair;

import java.util.Map;
import java.util.Set;

public class Game {
    Board board;
    Grid grid;
    Player[] players;
    int turn;

    public Game(){
        board = Board.getInstance();
        grid = Grid.getInstance();
       // board.init();
        grid.init();

        players = new Player[2];
        players[0] = new Player(Color.BLACK);
        players[1] = new Player(Color.WHITE);
        turn = 0;
    }

    public void changeTurn(){
        turn = turn == 0 ? 1 : 0;
    }

    public static void main(String[] args) {

        Game game = new Game();
        String str = "";


        while(true){
            game.board.toggleWaiting();
            Color color = game.players[game.turn].getColor();
            Map<Pair<Integer, Integer>, Move> moveSet = Move.getMoves(color);
           /* if(moveSet.isEmpty()){
                game.grid.init();
                game.board.init();
            }*/



            game.board.addMoves(moveSet);
            if(moveSet.isEmpty()){
                game.changeTurn();
                break;
            }


            while(game.board.waitingForMove());

            System.out.println(game.grid.getBlack().size() + game.grid.getWhite().size());
            //System.out.println(game.grid.getWhite().size());
            game.changeTurn();


        }


    }
}
