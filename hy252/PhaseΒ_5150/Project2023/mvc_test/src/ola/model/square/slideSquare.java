package ola.model.square;

import ola.model.pawn;
import ola.model.typeSquare;

import java.awt.*;

public class slideSquare extends square{
    /**
     * Constructor
     * @param squareType o typosSquare
     * @param color to xrwma pou to antiprwsopevei
     * @param x syntetagmenh x
     * @param y syntetagmenh y
     * @param Pawn pioni
     */
    slideSquare(typeSquare squareType, Color color, int x, int y, pawn Pawn) {
        super(squareType, color, x, y, Pawn);
    }
}
