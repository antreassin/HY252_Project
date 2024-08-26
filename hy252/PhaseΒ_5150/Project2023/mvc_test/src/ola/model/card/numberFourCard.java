package ola.model.card;

import ola.model.board;
import ola.model.pawn;

public class numberFourCard extends numberCard{
    public numberFourCard(String image,String cardName) {
        super(image,cardName);
        this.number =4;
    }
    @Override
    public void movePawn(pawn Pawn,board Board) {
        super.movePawn(Pawn,Board);
    }
}
