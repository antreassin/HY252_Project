package ola.model.square;

import ola.model.pawn;
import ola.model.typeSquare;

import java.awt.*;

public class internalSlideSquare extends slideSquare{
    /**
     * Constructor
     * @param squareType o typosSquare
     * @param color to xrwma pou to antiprwsopevei
     * @param x syntetagmenh x
     * @param y syntetagmenh y
     * @param Pawn pioni
     */
    public internalSlideSquare(typeSquare squareType, Color color, int x, int y, pawn Pawn) {
        super(squareType, color, x, y, Pawn);
    }
}
