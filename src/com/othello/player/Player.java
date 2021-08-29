package com.othello.player;

import com.othello.board.Color;

public class Player {

    Color color;

    public Player(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
