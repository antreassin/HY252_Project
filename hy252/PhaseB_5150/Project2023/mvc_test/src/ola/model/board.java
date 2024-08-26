package ola.model;

import ola.model.square.*;

import java.awt.*;

public class board{
    /**
     * oi pinakes antiprwsopevoun to eidos twn square
     */
    private final int redStart = 4;
    private final int yellowStart = 34;
    public square[] board = new square[76];
    public board(){
        for(int i=0;i<15;i++){
            if(i==1||i==9){
                board[i]=new startSlideSquare(typeSquare.slide,Color.red,i,0,null);
                board[i+15]=new startSlideSquare(typeSquare.slide,Color.blue,15,i,null);
                board[i+30]=new startSlideSquare(typeSquare.slide,Color.yellow,14,15-i,null);
                board[i+45]=new startSlideSquare(typeSquare.slide,Color.yellow,i,14,null);
            }
            else if(i==4||i==13){
                board[i]=new endSlideSquare(typeSquare.slide,Color.red,i,0,null);
                board[i+15]=new endSlideSquare(typeSquare.slide,Color.blue,15,i,null);
                board[i+30]=new endSlideSquare(typeSquare.slide,Color.yellow,14,15-i,null);
                board[i+45]=new endSlideSquare(typeSquare.slide,Color.yellow,i,14,null);
            }
            else if(i==2||i==3||i==10||i==11|i==12){
                board[i]=new internalSlideSquare(typeSquare.slide,Color.red,i,0,null);
                board[i+15]=new internalSlideSquare(typeSquare.slide,Color.blue,15,i,null);
                board[i+30]=new internalSlideSquare(typeSquare.slide,Color.yellow,14,15-i,null);
                board[i+45]=new internalSlideSquare(typeSquare.slide,Color.yellow,i,14,null);
            }
            else{
                board[i]=new square(typeSquare.normal,Color.red,i,0,null);
                board[i+15]=new square(typeSquare.slide,Color.blue,15,i,null);
                board[i+30]=new square(typeSquare.slide,Color.yellow,14,15-i,null);
                board[i+45]=new square(typeSquare.slide,Color.yellow,i,14,null);
            }
        }
        for(int i=0;i<5;i++){
            board[i+60]=new safetyZoneSquare(typeSquare.safeZone,Color.red,0,0,null);
            board[i+66]=new safetyZoneSquare(typeSquare.safeZone,Color.yellow,1,1,null);
        }
        board[65]=new homeSquare(typeSquare.home,Color.red,0,0,null);
        board[71]=new homeSquare(typeSquare.home,Color.yellow,1,1,null);
        board[72]=new startSquare(typeSquare.start,Color.red,0,0,null);
        board[73]=new startSquare(typeSquare.start,Color.red,0,0,null);
        board[74]=new startSquare(typeSquare.start,Color.yellow,1,1,null);
        board[75]=new startSquare(typeSquare.start,Color.yellow,1,1,null);
    }
    public int getStart(Color color){
        if(color == Color.red){
            return redStart;
        }
        else{
            return yellowStart;
        }
    }
}
