package com.othello.board;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Grid {

    private Color[][] board;


    private Set<Pair<Integer, Integer>> white;
    private Set<Pair<Integer, Integer>> black;

    private static Grid instance = null;

    public Grid(){
        board = new Color[8][8];
        white = new HashSet<>();
        black = new HashSet<>();
    }
    public void init(){



        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                board[i][j] = Color.NONE;
            }
        }
        board[3][3] = Color.BLACK;
        board[4][4] = Color.BLACK;
        board[3][4] = Color.WHITE;
        board[4][3] = Color.WHITE;
        white.clear();
        black.clear();

        white.add(new Pair<>(4, 3));
        white.add(new Pair<>(3, 4));
        black.add(new Pair<>(3, 3));
        black.add(new Pair<>(4, 4));
    }

    public static Grid getInstance(){
        if(instance == null)
            instance = new Grid();
        return instance;
    }

    public boolean isOutOfBounds(int x, int y){
        return (x >= 8 || x < 0 || y >= 8 || y < 0);
    }

    public void swap(int x, int y, Color color){
        if(!isOutOfBounds(x, y)){
            if(color == Color.BLACK){
                white.remove(new Pair<>(y, x));
                black.add(new Pair<>(y, x));
            }else{
                black.remove(new Pair<>(y, x));
                white.add(new Pair<>(y, x));
            }

            board[y][x] = color;
        }



    }

    public Color getColor(int x, int y){
        if(x >= 8 || x < 0 || y >= 8 || y < 0)
            return Color.NONE;
        else
            return board[y][x];
    }

    public boolean isOccupied(int x, int y){
        if(x >= 8 || x < 0 || y >= 8 || y < 0)
            return true;
        return board[y][x] != Color.NONE;

    }

    public Set<Pair<Integer, Integer>> getWhite() {
        return white;
    }


    public Set<Pair<Integer, Integer>> getBlack() {
        return black;
    }


}
