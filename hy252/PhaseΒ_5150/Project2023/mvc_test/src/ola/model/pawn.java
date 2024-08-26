package ola.model;

import java.awt.*;

public class pawn {
    /**
     * Constructor
     * @param x syntetagmenh x
     * @param y syntetagmenh y
     * @param location to eidos topothesias
     */
    public pawn(int x, int y, pawnLocation location){
        this.y=y;
        this.x=x;
        this.location = location;
    }
    /*to oneD mporw na to dw san na exw thn 15*15 periferia tou kiklou kai na thn exw kanei
    * x'x 0 mexri 60 anti x kai y {-15,15}*/
    private int oneDimensionCord=0;
    Color color;
    private int y , x;
    private boolean isSafe = false;
    private boolean movable = true;
    private pawnLocation location;

    /**
     * Transformer orizei to color
     * @param color
     */
    public void setColor(Color color){
        this.color = color;
    }

    /**
     * Transformer
     * @return color
     */
    public Color getColor(){
        return color;
    }
    /**
     * Transformer
     * @param isSafe
     * orizei an einai se SafeZone
     */
    public void setSafe(boolean isSafe){
        this.isSafe = isSafe;
    }

    /**
     * Accessor Epistrevei an einai se safeZone
     * @return isSafe
     */
    public boolean getSafe(){
        return isSafe;
    }

    /**
     * Transformer
     * Orizei to eidos topothesias tou pioniou
     * @param location
     */
    public void setLocation(pawnLocation location){
        this.location = location;
    }
    /**
     * Accessor
     * @return location
     */
    public pawnLocation getLocation(){
        return location;
    }

    /**
     * Transformer
     * otan to pioni ftasei sto spiti kai den mporei na kounithei
     */
    public void setImmovable(){
        movable = false;
    }

    /**
     * Accessor
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Accessor
     * @return y
     */
    public int getY() {
        return y;
    }
    /**
     * Transformer orizei thn topothesia
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Transformer orizei thn topothesia
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    public void setOneDimensionCord(int oneDimensionCord) {
        this.oneDimensionCord += oneDimensionCord;
    }
    public int getOneDimensionCord() {
        return oneDimensionCord;
    }
}
