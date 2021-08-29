package com.othello.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoundButton extends JButton {
    private Color color;
    protected boolean disabled;
    private int i, j;

    public RoundButton(int i, int j) {
        super();
        this.color = Color.LIGHT_GRAY;
        this.i = i;
        this.j = j;
        this.setBackground(Color.WHITE);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
        setFocusPainted(false);
        disabled = true;
        super.addActionListener(new ButtonListener());
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)  g.create();
        Color color = null;
        if(!disabled)
            color = Color.YELLOW;
        else
            color = this.color;

        g2.setColor(color);
        g2.setBackground(color);
        g2.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paint(g2);
        super.repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)  g.create();

        Color color = null;
        if(!disabled)
            color = Color.YELLOW;
        else
            color = this.color;

        g2.setBackground(color);
        g2.setColor(color);
        g2.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g2);
        super.repaint();

    }

    protected void paintBorder(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void toggleButton(){
        disabled = !disabled;
    }

    public void toBlack(){
        setColor(Color.BLACK);
    }

    public void toWhite(){
        setColor(Color.WHITE);
    }
    private class ButtonListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            if(!disabled){
                Board board = Board.getInstance();
                board.makeMove(j, i);



            }
        }
    }

}
