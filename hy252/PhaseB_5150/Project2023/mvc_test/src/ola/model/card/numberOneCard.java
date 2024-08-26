package ola.model.card;

import ola.model.board;
import ola.model.pawn;
import ola.model.pawnLocation;

import java.awt.datatransfer.Clipboard;

public class numberOneCard extends numberCard{
    public numberOneCard(String image,String cardName) {
        super(image,cardName);
        this.number =1;
    }
    @Override
    public void movePawn(pawn Pawn,board Board) {
        if(Pawn.getLocation()== pawnLocation.start&&!Board.board[Board.getStart(Pawn.getColor())].getState()){
                Pawn.setOneDimensionCord(Board.getStart(Pawn.getColor()));
                Pawn.setLocation(pawnLocation.regular);
                Board.board[Pawn.getOneDimensionCord()].setPawn(Pawn);
        }
        else{
                Board.board[Pawn.getOneDimensionCord()].removePawn();
                Pawn.setOneDimensionCord(Pawn.getOneDimensionCord()+1);
                Board.board[Pawn.getOneDimensionCord()].setPawn(Pawn);
        }
    }
}
