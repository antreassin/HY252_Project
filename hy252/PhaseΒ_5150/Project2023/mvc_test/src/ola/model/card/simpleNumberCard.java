package ola.model.card;

import ola.model.board;
import ola.model.pawn;
import ola.model.pawnLocation;

public class simpleNumberCard extends numberCard{
    public simpleNumberCard(String image,String cardName) {
        super(image,cardName);
        this.number = Integer.parseInt(cardName);
    }
    @Override
    public void movePawn(pawn Pawn,board Board) {
            Pawn.setOneDimensionCord(number);
    }
}
