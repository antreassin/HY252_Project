package ola.model.square;

import ola.model.pawn;
import ola.model.pawnLocation;
import ola.model.typeSquare;

import java.awt.*;

public class square{
    /**
     * Constructor
     * @param squareType o typosSquare
     * @param color to xrwma pou to antiprwsopevei
     * @param x syntetagmenh x
     * @param y syntetagmenh y
     * @param Pawn pioni
     */
    public square(typeSquare squareType,Color color,int x,int y,pawn Pawn){
        this.squareType=squareType;
        this.color = color;
        this.x = x;
        this.y = y;
        this.Pawn = Pawn;
    }
    private boolean state=false;
    private typeSquare squareType;
    private Color color;
    private int x,y;
    private pawn Pawn = null;

    /**
     * Accessor Gyriszei to an einai kapoio pioni panw se auth thn thesh
     * @return true/false true = occupied, false = empty
     */
    public boolean getState(){
        return state;
    }

    /**
     * Transformer
     * @param state true an einai kapoio pioni panw, false an efyge to pioni
     */
    public void setState(boolean state){
        this.state = state;
    }

    /**
     * Asseccor epistrefei to xrwma pou anhkei to square
     * @return color
     */
    public Color getColor(){
        return color;
    }

    /**
     * Accessor Epistrefei tis suntetagmenes
     * @return X
     */
    public int getX(){
        return x;
    }
    /**
     * Accessor Epistrefei tis suntetagmenes
     * @return X
     */
    public int getY(){
        return y;
    }

    /**
     * Accessor epistrefei poio poini kathete panw, an kathete kapoio
     * @return pioni
     */
    public pawn getPawn(){
        return Pawn;
    }

    /**
     * Transformer
     * orizei poio pionikathete panw
     * @param Pawn to pioni pou tha einai sto square
     */
    public void setPawn(pawn Pawn){
        this.Pawn = Pawn;
        state = true;
    }
    public void removePawn(){
        this.Pawn = null;
        state = false;
    }
    /**
     * Accessor kanei return ton typi tou square
     * @return typeSquare
     */
    public typeSquare getSquareType() {
        return squareType;
    }

    /**
     * Transformer Allazei to pedio topothesias sto pioni analoga to square
     * @param Pawn
     */
    public void changeLocation(pawn Pawn){
        Pawn.setLocation(pawnLocation.regular);
    }
}
