package ola.model;

import java.awt.*;

public class player {
    /**
     * Constructor
     * Epishs paei sto kathe Pawn kai tou allazei to xrwma
     * @param color
     * @param Pawn1
     * @param Pawn2
     */
    public player(Color color, pawn Pawn1, pawn Pawn2){
        this.color = color;
        this.Pawn1 = Pawn1;
        this.Pawn2 = Pawn2;
        this.Pawn1.setColor(color);
        this.Pawn2.setColor(color);
    }
    private Color color;
    private pawn Pawn1, Pawn2;
    private int movablePawns = 2;
    private boolean winner = false;

    /**
     * transformer
     * otan ena pioni tou ftasei to home tote miwnei ta movable pawns tou, an den exei movable pawns tote nikhse
     */
    public void decreaseMovablePawns(){
        movablePawns--;
        if(movablePawns == 0){
            winner = true;
        }
    }

    /**
     *Accessor
     * @return winner
     */
    public boolean isWinner(){
        return winner;
    }

    /**
     * epilegei me poio apo ta duo tou piona tha paiksei
     * @return me poio pioni tha paiksei
     */
    public pawn selectPawn(){
        return this.Pawn1;
    }
    public void getPawn1(){
        this.Pawn1 = Pawn1;
    }
    public void getPawn2(){
        this.Pawn2 = Pawn2;
    }
}
