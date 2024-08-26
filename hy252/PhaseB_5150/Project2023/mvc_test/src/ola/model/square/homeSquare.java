package ola.model.square;

import ola.model.pawn;
import ola.model.player;
import ola.model.typeSquare;

import java.awt.*;

public class homeSquare extends safetyZoneSquare {
    /**
     * Constructor
     * @param squareType o typosSquare
     * @param color to xrwma pou to antiprwsopevei
     * @param x syntetagmenh x
     * @param y syntetagmenh y
     * @param Pawn pioni
     */
    public homeSquare(typeSquare squareType, Color color, int x, int y, pawn Pawn) {
        super(squareType, color, x, y, Pawn);
    }

    /**
     *  Transformer, orizei pws to pioni eftase sto HomeSquare
     * @param Pawn to pioni tou paikth
     * @param Player
     */
    void setImmovable(pawn Pawn, player Player){
        Pawn.setImmovable();
        Player.decreaseMovablePawns();
    }
}
