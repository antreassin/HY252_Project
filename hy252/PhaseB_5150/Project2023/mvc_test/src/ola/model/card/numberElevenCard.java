package ola.model.card;

import ola.model.board;
import ola.model.pawn;

public class numberElevenCard extends numberCard{

    public numberElevenCard(String image,String cardName) {
        super(image,cardName);
        this.number =11;
    }
    @Override
    public void movePawn(pawn Pawn,board Board) {
        super.movePawn(Pawn,Board);
    }
}