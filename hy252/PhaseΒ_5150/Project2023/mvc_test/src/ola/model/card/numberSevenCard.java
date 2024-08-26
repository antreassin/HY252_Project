package ola.model.card;

import ola.model.board;
import ola.model.pawn;

public class numberSevenCard extends numberCard{
    public numberSevenCard(String image,String cardName) {
        super(image,cardName);
        this.number =7;
    }
    @Override
    public void movePawn(pawn Pawn,board Board) {
        super.movePawn(Pawn,Board);
    }
}
