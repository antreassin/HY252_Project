package ola.model.card;

import ola.model.board;
import ola.model.pawn;

public class numberTenCard extends numberCard{
    public numberTenCard(String image,String cardName) {
        super(image,cardName);
        this.number =10;
    }
    @Override
    public void movePawn(pawn Pawn,board Board) {
        super.movePawn(Pawn,Board);
    }
}
