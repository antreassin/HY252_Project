package ola.controller;
import ola.model.pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ola.model.*;
import ola.model.card.card;
import ola.model.card.deck;
import ola.model.square.*;
import ola.view.View;


import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class mainMenuTest {
    @Test
    public void testMainMenu(){
        mainMenu mainMenu = new mainMenu();
        assertNotNull(mainMenu);
    }
    @Test
    public void testPawn(){ pawn Pawn = new pawn(0,0,pawnLocation.regular);
        Pawn.setOneDimensionCord(4);
        Pawn.setLocation(pawnLocation.regular);
        Pawn.setOneDimensionCord(1);
        Assertions.assertEquals(5, Pawn.getOneDimensionCord());
        Assertions.assertEquals(pawnLocation.regular, Pawn.getLocation());
    }
    @Test
    public void testEnd(){
        mainMenu a = new mainMenu();
        a.newGame();
        a.Pawns[0].setLocation(pawnLocation.home);
        a.Pawns[1].setLocation(pawnLocation.home);
        a.endGame();
        assertTrue(a.isOver);
    }
    @Test
    public void cardTest(){
        deck Deck = new deck();
        pawn Pawn = new pawn(0,0,pawnLocation.regular);
        card C = Deck.foldCard();int cardN;
        if(!Objects.equals(C.toString(), "Sorry")){
            cardN = Integer.parseInt(C.toString());
        }else{
            cardN=-1;
        }
        Pawn.setOneDimensionCord(cardN);
        Assertions.assertEquals(Pawn.getOneDimensionCord(), cardN);
    }
}