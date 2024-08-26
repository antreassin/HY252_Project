package ola.model.card;

import ola.model.board;
import ola.model.pawn;
import ola.model.pawnLocation;

public class numberTwoCard extends numberCard{
    public numberTwoCard(String image,String cardName) {
        super(image,cardName);
        this.number =2;
    }
    @Override
    public void movePawn(pawn Pawn,board Board) {
        if(Pawn.getLocation()== pawnLocation.start){
            Pawn.setOneDimensionCord(Board.getStart(Pawn.getColor()));
            Pawn.setLocation(pawnLocation.regular);
        }
        else{
            Pawn.setOneDimensionCord(number);
        }
    }
}
