package ola.model.square;

import ola.model.pawn;
import ola.model.pawnLocation;
import ola.model.typeSquare;

import java.awt.*;

public class safetyZoneSquare extends square{
    /**
     * Constructor
     * @param squareType o typosSquare
     * @param color to xrwma pou to antiprwsopevei
     * @param x syntetagmenh x
     * @param y syntetagmenh y
     * @param Pawn pioni
     */
    public safetyZoneSquare(typeSquare squareType, Color color, int x, int y, pawn Pawn) {
        super(squareType, color, x, y, Pawn);
    }

    /**
     * Transformer orizei oti to pioni einai se thesh asfalias
     * @param Pawn pioni
     */
    public void setSafe(pawn Pawn){
        Pawn.setSafe(true);
    }
    @Override
    public void changeLocation(pawn Pawn) {
        super.changeLocation(Pawn);
        Pawn.setLocation(pawnLocation.safe);
        setSafe(Pawn);

    }

}
