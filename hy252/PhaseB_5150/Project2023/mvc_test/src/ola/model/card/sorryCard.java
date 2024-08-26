package ola.model.card;

import ola.model.board;
import ola.model.pawn;
import ola.model.pawnLocation;

public class sorryCard extends card{
    public sorryCard(String image,String cardName) {
        super(image,cardName);
    }
    @Override
    public void movePawn(pawn Pawn,board Board) {
        Pawn.setOneDimensionCord(1);
    }
    @Override
    public String getImage(){
        return null;
    }
    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public String toString() {
        return "Sorry";
    }

}
