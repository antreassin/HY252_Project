package ola.controller;

import ola.model.card.deck;
import ola.model.pawn;
import ola.model.player;

import java.awt.*;

public class dataBase {
    public player player1, player2;
    private deck Deck;
    public player newPlayer(String name){
        Color color = null;
        pawn Pawn1 = null;
        pawn Pawn2=null;
        player p =  new player(color,Pawn1,Pawn2);
        return p;
    }
    public void setPlayer1(){
        /*pare kapws to onoma kai bale to mesa*/
        String name = null;
        player p = newPlayer(name);
        player1 = p;
    }
    public void setPlayer2(){
        /*pare kapws to onoma kai bale to mesa*/
        String name = null;
        player p = newPlayer(name);
        player2 = p;
    }
    public void makeDeck(){
        Deck = new deck();
    }
    public void foldForPlayer(player p){
    }
    public deck getDeck(){
        return Deck;
    }
}
