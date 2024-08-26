package ola.model.card;

import ola.model.board;
import ola.model.pawn;
import ola.model.pawnLocation;

public class numberCard extends card{
    int number;
    private boolean interactive;
    public boolean getInteractive(){
        return interactive;
    }
    /**
     * constructor
     * @param image h eikona gia to gui
     */
    public numberCard(String image,String cardName) {
        super(image,cardName);
        this.number = Integer.parseInt(cardName);
    }

    /**
     ** accessor
     * @return thn eikona gia to ola.view
     */
    @Override
    public String getImage() {
        return null;
    }

    /**
     * accessor
     * @return an h karta einai active h oxi
     */
    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void movePawn(pawn Pawn,board Board) {
            Pawn.setOneDimensionCord(number);
    }
    /**
     * transformer
     * @return override string
     */
    @Override
    public String toString() {
        return cardName;
    }

}
