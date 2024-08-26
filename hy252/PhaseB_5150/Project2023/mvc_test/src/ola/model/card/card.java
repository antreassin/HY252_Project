package ola.model.card;

import ola.model.board;
import ola.model.pawn;

abstract public class card {
    /**
     * Constructor
     * @param image h eikona pou tha thn antiprosopevei sto GUI
     */
    public card(String image,String name){
        this.image = image;
        this.cardName = name;
    }
    String cardName;
    boolean active = true;
    public String image;

    /**
     * Accessor
     * @return dinei thn eikona ths
     */
    abstract public String getImage();

    /**
     * accessor
     * @return an exei paixtei h oxi h karta
     */
    abstract public boolean isActive();

    /**
     *
     * @param Pawn to pioni pou tha xrisimopoihsei thn karta
     * @param Board to tablo pou tha xrisimopoihsei thn karta
     */
    abstract public void movePawn(pawn Pawn,board Board);

    /**
     *
     * @return ena string pou tha perigrafei thn karta
     */
    @Override
    abstract public String toString();
}
