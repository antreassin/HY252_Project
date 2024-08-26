package ola.model.card;

import java.util.Collections;
import java.util.Stack;

public class deck {
    private Stack<card> Deck;
    /**
     * Constructor
     * etoimazei mia stoiva h opoia tha exei mesa 44 kartes gia to painxidi
     */
    public deck(){
        Deck = new Stack<>();
        for(int j=1;j<=4;j++){
            for(int i=1;i<=12;i++){
                if(i==1)
                    Deck.push(new numberOneCard("resources/cards/card1.png","1"));
                else if(i==2)
                    Deck.push(new numberTwoCard("resources/cards/card2.png","2"));
                else if(i==4)
                    Deck.push(new numberFourCard("resources/cards/card4.png","4"));
                else if(i==7)
                    Deck.push(new numberSevenCard("resources/cards/card7.png","7"));
                else if(i==10)
                    Deck.push(new numberTenCard("resources/cards/card10.png","10"));
                else if(i==11)
                    Deck.push(new numberElevenCard("resources/cards/card11.png","11"));
                else if(i==6||i==9){}
                else{
                    Deck.push(new numberCard("resources/cards/card"+i+".png",Integer.toString(i)));
                }
            }
        }
        for(int i=0;i<4;i++){
            Deck.push(new sorryCard("resources/cards/cardSorry.png","Sorry"));
        }
        Collections.shuffle(Deck);
        capacity=44;
    }
    private int capacity = 44;
    /**
     * Accessor
     * Paizetai mia karta
     * @return card epistrefei thn karta pou exei paixtei
     */
    public card foldCard(){
        capacity--;
        return Deck.pop();
        /*pane meta kai kane thn karta oit den mporei na paixtei*/
    }

    /**
     * Transformer
     * meiwnei to capacity gia na gnwrizoume pote exoun teleiwsei oi kartes
     */
    public void decreaseCapacity(){
        capacity--;
    }
    public String toString(){
        return Deck.toString();
    }
    /**
     * Dinei poses kartes mporun na paixtoun
     * @return capacity of cards
     */
    public int getCapacity(){
        return capacity;
    }
    public static void main(String[] args){
        deck d = new deck();
        int j=0;
        for (card c : d.Deck) {
            System.out.println(c.toString());
            j++;
        }
        System.out.println("\n"+j+" cards");
    }
    public Stack<card> getDeck(){
        return Deck;
    }
}
