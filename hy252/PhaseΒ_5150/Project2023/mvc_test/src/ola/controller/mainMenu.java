package ola.controller;

import ola.model.*;
import ola.model.card.card;
import ola.model.card.deck;
import ola.model.square.*;
import ola.view.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class mainMenu{
    private deck Deck;
    public View v;
    public square[] squares = new square[76];
    public player player1;/*red*/
    public player player2;/*yellow*/
    public boolean turn = true;
    public boolean isOver = false;/*tirisi siras*/
    public pawn[] Pawns=new pawn[4];
    public card currentPlayedCard;
    public boolean fold = true;
    public boolean cardfold = false;/*gia na mh mporei na kanei askopa fold*/
    board Board = new board();
    private boolean pawnMoved = true;/*oti pioni kounithike*/
    private boolean canPlayTurn = true;/*otan mporei na kounisei pioni*/

    /**
     * transformer
     * initGame
     */
    public void endGame(){
        if(Pawns[0].getLocation()==pawnLocation.home&&Pawns[1].getLocation()==pawnLocation.home){
            JOptionPane.showMessageDialog(null,"Νίκησε o παίκτης 1(red)!");
            isOver=true;
            //System.exit(0);
        } else if(Pawns[2].getLocation()==pawnLocation.home&&Pawns[3].getLocation()==pawnLocation.home){
            JOptionPane.showMessageDialog(null,"Νίκησε o παίκτης 2(yellow)!");
            isOver=true;
            //System.exit(0);
        }
    }
    public void initGame(){
        /*init players*/
        for(int i=0;i<4;i++){
            if(i<2){
                Pawns[i] = new pawn(0,0,pawnLocation.start);/*red*/
                Pawns[i].setOneDimensionCord(4);
                Pawns[i].setColor(Color.red);
            } else{
                Pawns[i] = new pawn(1,1,pawnLocation.start);/*yellow*/
                Pawns[i].setOneDimensionCord(34);
                Pawns[i].setColor(Color.yellow);
            }
        }
        player1 = new player(Color.red,Pawns[0],Pawns[1]);
        player2 = new player(Color.yellow,Pawns[2],Pawns[3]);
    }

    /**
     * transformer
     * init new game
     */
    public void newGame(){
        Deck = new deck();
        v = new View();
        v.initComponents();
        initGame();
        setListener();
        v.setVisible(true);
    }/**
     * transformer
     * Listener for fold button
     */
    public class cardListener implements ActionListener{
        /**
         * transformer
         * @param e the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(fold){
                fold = false;
                canPlayTurn = true;
                pawnMoved = false;
                cardfold = true;
                if (Deck.getCapacity() == 0) {
                    Deck = new deck();
                    Collections.shuffle(Deck.getDeck());
                }
                card c = Deck.foldCard();
                currentPlayedCard = c;
                if (turn) {
                    v.updateInfobox("Player 1(red) Played Card: " + c.toString() + "\nAvailable Cards:" + Deck.getCapacity());
                } else {
                    v.updateInfobox("Player 2(yellow) Played Card: " + c.toString() + "\nAvailable Cards:" + Deck.getCapacity());
                }
                v.updateCard(c);
            }
        }
    }

    /**
     * transformer
     * Listener for fold button
     */
    public class foldListener implements ActionListener{
        /**
         * transformer
         * @param e the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(cardfold){
                System.out.println(turn);
                nextTurn();
                fold = true;
                cardfold = false;
                String temp = turn?"1(red)":"2(yellow)";
                v.updateInfobox("Παίκτη "+temp+" \nτράβηξε νέα κάρτα");
            }
        }
    }

    /**
     * transformer
     * Listener for menu
     */
    public class newGameListener implements ActionListener{
        /**
         * transforme
         * @param e the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            /*new game, load game, save game, exit*/
            if(e.getSource()==v.newGame){
                newGame();
            }
            else{
                System.exit(0);
            }
        }
    }

    /**
     * transformer
     * Main method for game to run
     */
    public class pawnListener implements ActionListener{
        /**
         * transformer
         * Pawn moved
         * @param e the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean boolUpdateCard;
            /*canPlayTurn = !canPlayTurn;
            pawnMoved = !pawnMoved;
            nextTurn();*/
            JButton b=(JButton)e.getSource();
            String[] options = {"1", "2"};
            int cardN;
            if(!Objects.equals(currentPlayedCard.toString(), "Sorry")){
                cardN = Integer.parseInt(currentPlayedCard.toString());
            }else{
                cardN=-1;
            }
            if(turn){
                if(b==v.pawnsRed[0]&&canPlayTurn){
                    boolUpdateCard = true;
                    System.out.println("Pre:"+Pawns[0].getOneDimensionCord());
                    switch (cardN){
                        case 1:
                            if(Pawns[0].getLocation()==pawnLocation.start){
                                Pawns[0].setOneDimensionCord(0);/*start point*/
                                Pawns[0].setLocation(pawnLocation.regular);
                                System.out.println(Pawns[0].getLocation());
                            }else{
                                checkBounds(Pawns[0],Pawns[0].getOneDimensionCord(),1);
                                Pawns[0].setOneDimensionCord(1);
                            }
                            break;
                        case 2:
                            nextTurn();
                            if(Pawns[0].getLocation()==pawnLocation.start){
                                Pawns[0].setOneDimensionCord(0);/*start point*/
                                Pawns[0].setLocation(pawnLocation.regular);
                                System.out.println(Pawns[0].getLocation());
                            }else{
                                checkBounds(Pawns[0],Pawns[0].getOneDimensionCord(),2);
                                Pawns[0].setOneDimensionCord(2);
                            }
                            break;
                        case 3,5:
                            if(Pawns[0].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[0],Pawns[0].getOneDimensionCord(),cardN);
                                Pawns[0].setOneDimensionCord(cardN);
                            }else boolUpdateCard=false;
                            if(Pawns[1].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[1],Pawns[1].getOneDimensionCord(),cardN);
                                Pawns[1].setOneDimensionCord(cardN);
                                v.updatePawnNoButt(Pawns[1].getOneDimensionCord(),1);
                            }break;
                        case 4:
                            if(Pawns[0].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[0],Pawns[0].getOneDimensionCord(),-4);
                                Pawns[0].setOneDimensionCord(-4);
                            }else boolUpdateCard=false;
                            break;
                        case 7:
                            int choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 7 θέσεις στο επιλεγμένο πίονι, αν θες να τις μοιράσεις πάτησε 2:"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(Pawns[0].getLocation()!=pawnLocation.start){
                                if(choice == 0) {
                                    checkBounds(Pawns[0],Pawns[0].getOneDimensionCord(),7);
                                    Pawns[0].setOneDimensionCord(7);
                                }else if(choice==1){
                                    int windInp = Integer.parseInt(JOptionPane.showInputDialog("", null));
                                    if(windInp<0||windInp>7){
                                        boolUpdateCard=false;
                                    }else{
                                        checkBounds(Pawns[0],Pawns[0].getOneDimensionCord(),windInp);
                                        Pawns[0].setOneDimensionCord(windInp);
                                        if(Pawns[1].getLocation()!=pawnLocation.start) {
                                            checkBounds(Pawns[1], Pawns[1].getOneDimensionCord(), 7 - windInp);
                                            Pawns[1].setOneDimensionCord(7 - windInp);
                                            v.updatePawnNoButt(Pawns[1].getOneDimensionCord(), 1);
                                        }
                                    }
                                }
                            }else boolUpdateCard=false;
                            break;
                        case 8,12:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις "+cardN+" θέσεις στο επιλεγμένο πίονι\nαν θες να τραβήξεις νεά κάρτα πάτα 2:"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice == 0) {
                                if (Pawns[0].getLocation() != pawnLocation.start) {
                                    checkBounds(Pawns[0], Pawns[0].getOneDimensionCord(), cardN);
                                    Pawns[0].setOneDimensionCord(cardN);
                                }else boolUpdateCard = false;
                            }else if(choice==1){
                                nextTurn();
                                boolUpdateCard = false;
                            }
                            break;
                        case 10:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 10 θέσεις στο επιλεγμένο πίονι\nΠάτησε 2 αν θες να παίξεις μια θέση πίσω :"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice==0){
                                if(Pawns[0].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[0], Pawns[0].getOneDimensionCord(), 10);
                                    Pawns[0].setOneDimensionCord(10);
                                }else boolUpdateCard = false;
                            }else if(choice==1){
                                if(Pawns[0].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[0], Pawns[0].getOneDimensionCord(), -1);
                                    Pawns[0].setOneDimensionCord(-1);
                                }else boolUpdateCard = false;
                            }
                            break;
                        case 11:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 11 θέσεις στο επιλεγμένο πίονι\nΠάτησε 2 αν θες να αλλάξεις θέση με πίονι αντιπάλου :"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice==0){
                                if(Pawns[0].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[0], Pawns[0].getOneDimensionCord(), 11);
                                    Pawns[0].setOneDimensionCord(11);
                                }else boolUpdateCard = false;
                            }else if(choice ==1){
                                int seChoice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να αλλάξεις με το 1\nΠάτησε 2 αν θες να αλλάξεις θέση με το 2:"
                                        ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if(seChoice==0){
                                    if(Pawns[2].getLocation()!=pawnLocation.start){
                                        int temp = Pawns[0].getOneDimensionCord();
                                        Pawns[0].setOneDimensionCord(-temp);
                                        Pawns[0].setOneDimensionCord(Pawns[2].getOneDimensionCord());
                                        Pawns[2].setOneDimensionCord(-Pawns[2].getOneDimensionCord());
                                        Pawns[2].setOneDimensionCord(temp);
                                        v.updatePawnNoButt(Pawns[2].getOneDimensionCord(),2);
                                    }else boolUpdateCard=false;
                                }else if(seChoice == 1){
                                    if(Pawns[3].getLocation()!=pawnLocation.start){
                                        int temp = Pawns[0].getOneDimensionCord();
                                        Pawns[0].setOneDimensionCord(-temp);
                                        Pawns[0].setOneDimensionCord(Pawns[3].getOneDimensionCord());
                                        Pawns[3].setOneDimensionCord(-Pawns[3].getOneDimensionCord());
                                        Pawns[3].setOneDimensionCord(temp);
                                        v.updatePawnNoButt(Pawns[3].getOneDimensionCord(),3);
                                    }else boolUpdateCard=false;
                                }
                            }
                            break;
                        case -1:
                            if(Pawns[0].getLocation()==pawnLocation.start){
                                Pawns[0].setLocation(pawnLocation.regular);
                                choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να αλλάξεις με το 1\nΠάτησε 2 αν θες να αλλάξεις θέση με το 2:"
                                        ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if(choice==1){
                                    if(Pawns[3].getLocation()==pawnLocation.regular){
                                        int temp = Pawns[0].getOneDimensionCord();
                                        Pawns[0].setOneDimensionCord(-temp);
                                        Pawns[0].setOneDimensionCord(Pawns[3].getOneDimensionCord());
                                        Pawns[3].setOneDimensionCord(-Pawns[3].getOneDimensionCord());
                                        Pawns[3].setOneDimensionCord(34);
                                        Pawns[3].setLocation(pawnLocation.start);
                                        v.updatePawnNoButt(78,3);
                                    }else boolUpdateCard=false;
                                }else if(choice == 0){
                                    if(Pawns[2].getLocation()==pawnLocation.regular){
                                        int temp = Pawns[0].getOneDimensionCord();
                                        Pawns[0].setOneDimensionCord(-temp);
                                        Pawns[0].setOneDimensionCord(Pawns[2].getOneDimensionCord());
                                        Pawns[2].setOneDimensionCord(-Pawns[2].getOneDimensionCord());
                                        Pawns[2].setOneDimensionCord(34);
                                        Pawns[2].setLocation(pawnLocation.start);
                                        v.updatePawnNoButt(77,2);
                                    }else boolUpdateCard=false;
                                }
                            }else boolUpdateCard=false;
                            break;
                        default:
                            if(Pawns[0].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[0],Pawns[0].getOneDimensionCord(),cardN);
                                Pawns[0].setOneDimensionCord(cardN);
                            }else boolUpdateCard=false;
                    }
                    System.out.println("post:"+Pawns[0].getOneDimensionCord());
                    if(boolUpdateCard){
                        v.updatePawn(Pawns[0].getOneDimensionCord(),b);
                    }
                    canPlayTurn = false;
                    pawnMoved = true;
                }
                else if(b==v.pawnsRed[1]&&canPlayTurn){
                    boolUpdateCard = true;
                    System.out.println("Pre:"+Pawns[1].getOneDimensionCord());
                    switch (cardN){
                        case 1:
                            if(Pawns[1].getLocation()==pawnLocation.start){
                                Pawns[1].setOneDimensionCord(0);/*start point*/
                                Pawns[1].setLocation(pawnLocation.regular);
                                System.out.println(Pawns[1].getLocation());
                            }else{
                                checkBounds(Pawns[1],Pawns[1].getOneDimensionCord(),1);
                                Pawns[1].setOneDimensionCord(1);
                            }
                            break;
                        case 2:
                            nextTurn();
                            if(Pawns[1].getLocation()==pawnLocation.start){
                                Pawns[1].setOneDimensionCord(0);/*start point*/
                                Pawns[1].setLocation(pawnLocation.regular);
                                System.out.println(Pawns[1].getLocation());
                            }else{
                                checkBounds(Pawns[1],Pawns[1].getOneDimensionCord(),2);
                                Pawns[1].setOneDimensionCord(2);
                            }
                            break;
                        case 3,5:
                            if(Pawns[1].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[1],Pawns[1].getOneDimensionCord(),cardN);
                                Pawns[1].setOneDimensionCord(cardN);
                            }else boolUpdateCard=false;
                            if(Pawns[0].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[0],Pawns[0].getOneDimensionCord(),cardN);
                                Pawns[0].setOneDimensionCord(cardN);
                                v.updatePawnNoButt(Pawns[0].getOneDimensionCord(),0);
                            }
                            break;
                        case 4:
                            if(Pawns[1].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[1],Pawns[1].getOneDimensionCord(),-4);
                                Pawns[1].setOneDimensionCord(-4);
                            }else boolUpdateCard=false;
                            break;
                        case 7:
                            int choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 7 θέσεις στο επιλεγμένο πίονι, αν θες να τις μοιράσεις πάτησε 2:"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(Pawns[1].getLocation()!=pawnLocation.start){
                                if(choice == 0) {
                                    checkBounds(Pawns[1],Pawns[1].getOneDimensionCord(),7);
                                    Pawns[1].setOneDimensionCord(7);
                                }else if(choice==1){
                                    int windInp = Integer.parseInt(JOptionPane.showInputDialog("", null));
                                    if(windInp<0||windInp>7){
                                        boolUpdateCard=false;
                                    }else{
                                        checkBounds(Pawns[1],Pawns[1].getOneDimensionCord(),windInp);
                                        Pawns[1].setOneDimensionCord(windInp);
                                        if(Pawns[0].getLocation()!=pawnLocation.start) {
                                            checkBounds(Pawns[0], Pawns[0].getOneDimensionCord(), 7 - windInp);
                                            Pawns[0].setOneDimensionCord(7 - windInp);
                                            v.updatePawnNoButt(Pawns[0].getOneDimensionCord(), 0);
                                        }
                                    }
                                }
                            }else boolUpdateCard=false;
                            break;
                        case 8,12:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις"+cardN+"θέσεις στο επιλεγμένο πίονι\nαν θες να τραβήξεις νεά κάρτα πάτα 2:"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice == 0) {
                                if (Pawns[1].getLocation() != pawnLocation.start) {
                                    checkBounds(Pawns[1], Pawns[1].getOneDimensionCord(), cardN);
                                    Pawns[1].setOneDimensionCord(cardN);
                                }else boolUpdateCard = false;
                            }else if(choice==1){
                                nextTurn();
                                boolUpdateCard = false;
                            }
                            break;
                        case 10:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 10 θέσεις στο επιλεγμένο πίονι\nΠάτησε 2 αν θες να παίξεις μια θέση πίσω :"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice==0){
                                if(Pawns[1].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[1], Pawns[1].getOneDimensionCord(), 10);
                                    Pawns[1].setOneDimensionCord(10);
                                }else boolUpdateCard = false;
                            }else if(choice==1){
                                if(Pawns[1].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[1], Pawns[1].getOneDimensionCord(), -1);
                                    Pawns[1].setOneDimensionCord(-1);
                                }else boolUpdateCard = false;
                            }
                            break;
                        case 11:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 11 θέσεις στο επιλεγμένο πίονι\nΠάτησε 2 αν θες να αλλάξεις θέση με πίονι αντιπάλου :"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice==0){
                                if(Pawns[1].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[1], Pawns[1].getOneDimensionCord(), 11);
                                    Pawns[1].setOneDimensionCord(11);
                                }else boolUpdateCard = false;
                            }else if(choice ==1){
                                int seChoice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να αλλάξεις με το 1\nΠάτησε 2 αν θες να αλλάξεις θέση με το 2:"
                                        ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if(seChoice==0){
                                    if(Pawns[2].getLocation()!=pawnLocation.start){
                                        int temp = Pawns[1].getOneDimensionCord();
                                        Pawns[1].setOneDimensionCord(-temp);
                                        Pawns[1].setOneDimensionCord(Pawns[2].getOneDimensionCord());
                                        Pawns[2].setOneDimensionCord(-Pawns[2].getOneDimensionCord());
                                        Pawns[2].setOneDimensionCord(temp);
                                        v.updatePawnNoButt(Pawns[2].getOneDimensionCord(),2);
                                    }else boolUpdateCard=false;
                                }else if(seChoice == 1){
                                    if(Pawns[3].getLocation()!=pawnLocation.start){
                                        int temp = Pawns[1].getOneDimensionCord();
                                        Pawns[1].setOneDimensionCord(-temp);
                                        Pawns[1].setOneDimensionCord(Pawns[3].getOneDimensionCord());
                                        Pawns[3].setOneDimensionCord(-Pawns[3].getOneDimensionCord());
                                        Pawns[3].setOneDimensionCord(temp);
                                        v.updatePawnNoButt(Pawns[3].getOneDimensionCord(),3);
                                    }else boolUpdateCard=false;
                                }
                            }
                            break;
                        case -1:
                            if(Pawns[1].getLocation()==pawnLocation.start){
                                Pawns[1].setLocation(pawnLocation.regular);
                                choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να αλλάξεις με το 1\nΠάτησε 2 αν θες να αλλάξεις θέση με το 2:"
                                        ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if(choice==1){
                                    if(Pawns[3].getLocation()==pawnLocation.regular){
                                        int temp = Pawns[1].getOneDimensionCord();
                                        Pawns[1].setOneDimensionCord(-temp);
                                        Pawns[1].setOneDimensionCord(Pawns[3].getOneDimensionCord());
                                        Pawns[3].setOneDimensionCord(-Pawns[3].getOneDimensionCord());
                                        Pawns[3].setOneDimensionCord(34);
                                        Pawns[3].setLocation(pawnLocation.start);
                                        v.updatePawnNoButt(78,3);
                                    }else boolUpdateCard=false;
                                }else if(choice == 0){
                                    if(Pawns[2].getLocation()==pawnLocation.regular){
                                        int temp = Pawns[1].getOneDimensionCord();
                                        Pawns[1].setOneDimensionCord(-temp);
                                        Pawns[1].setOneDimensionCord(Pawns[2].getOneDimensionCord());
                                        Pawns[2].setOneDimensionCord(-Pawns[2].getOneDimensionCord());
                                        Pawns[2].setOneDimensionCord(34);
                                        Pawns[2].setLocation(pawnLocation.start);
                                        v.updatePawnNoButt(77,2);
                                    }else boolUpdateCard=false;
                                }
                            }else boolUpdateCard=false;
                            break;
                        default:
                            if(Pawns[1].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[1],Pawns[1].getOneDimensionCord(),cardN);
                                Pawns[1].setOneDimensionCord(cardN);
                            }else boolUpdateCard=false;
                    }
                    System.out.println("post:"+Pawns[1].getOneDimensionCord());
                    if(boolUpdateCard){
                        v.updatePawn(Pawns[1].getOneDimensionCord(),b);
                    }
                    canPlayTurn = false;
                    pawnMoved = true;
                }
            }else {
                if(b==v.pawnsYellow[0]&&canPlayTurn){
                    boolUpdateCard = true;
                    System.out.println("Pre:"+Pawns[2].getOneDimensionCord());
                    switch (cardN){
                        case 1:
                            if(Pawns[2].getLocation()==pawnLocation.start){
                                Pawns[2].setOneDimensionCord(0);/*start point*/
                                Pawns[2].setLocation(pawnLocation.regular);
                                System.out.println(Pawns[2].getLocation());
                            }else{
                                checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),1);
                                Pawns[2].setOneDimensionCord(1);
                            }
                            break;
                        case 2:
                            nextTurn();
                            if(Pawns[2].getLocation()==pawnLocation.start){
                                Pawns[2].setOneDimensionCord(0);/*start point*/
                                Pawns[2].setLocation(pawnLocation.regular);
                                System.out.println(Pawns[2].getLocation());
                            }else{
                                checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),2);
                                Pawns[2].setOneDimensionCord(2);
                            }
                            break;
                        case 3,5:
                            if(Pawns[2].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),cardN);
                                Pawns[2].setOneDimensionCord(cardN);
                            }else boolUpdateCard=false;
                            if(Pawns[3].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),cardN);
                                Pawns[3].setOneDimensionCord(cardN);
                                v.updatePawnNoButt(Pawns[3].getOneDimensionCord(),3);
                            }
                            break;
                        case 4:
                            if(Pawns[2].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),-4);
                                Pawns[2].setOneDimensionCord(-4);
                            }else boolUpdateCard=false;
                            break;
                        case 7:
                            int choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 7 θέσεις στο επιλεγμένο πίονι, αν θες να τις μοιράσεις πάτησε 2:"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(Pawns[2].getLocation()!=pawnLocation.start){
                                if(choice == 0) {
                                    checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),7);
                                    Pawns[2].setOneDimensionCord(7);
                                }else if(choice==1){
                                    int windInp = Integer.parseInt(JOptionPane.showInputDialog("", null));
                                    if(windInp<0||windInp>7){
                                        boolUpdateCard=false;
                                    }else{
                                        checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),windInp);
                                        Pawns[2].setOneDimensionCord(windInp);
                                        if(Pawns[3].getLocation()!=pawnLocation.start){
                                            checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),7-windInp);
                                            Pawns[3].setOneDimensionCord(7-windInp);
                                            v.updatePawnNoButt(Pawns[3].getOneDimensionCord(),3);
                                        }
                                    }
                                }
                            }else boolUpdateCard=false;
                            break;
                        case 8,12:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις "+cardN+" θέσεις στο επιλεγμένο πίονι\nαν θες να τραβήξεις νεά κάρτα πάτα 2:"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice == 0) {
                                if (Pawns[2].getLocation() != pawnLocation.start) {
                                    checkBounds(Pawns[2], Pawns[2].getOneDimensionCord(), cardN);
                                    Pawns[2].setOneDimensionCord(cardN);
                                }else boolUpdateCard = false;
                            }else if(choice==1){
                                nextTurn();
                                boolUpdateCard = false;
                            }
                            break;
                        case 10:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 10 θέσεις στο επιλεγμένο πίονι\nΠάτησε 2 αν θες να παίξεις μια θέση πίσω :"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice==0){
                                if(Pawns[2].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[2], Pawns[2].getOneDimensionCord(), 10);
                                    Pawns[2].setOneDimensionCord(10);
                                }else boolUpdateCard = false;
                            }else if(choice==1){
                                if(Pawns[2].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[2], Pawns[2].getOneDimensionCord(), -1);
                                    Pawns[2].setOneDimensionCord(-1);
                                }else boolUpdateCard = false;
                            }
                            break;
                        case 11:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 11 θέσεις στο επιλεγμένο πίονι\nΠάτησε 2 αν θες να αλλάξεις θέση με πίονι αντιπάλου :"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice==0){
                                if(Pawns[2].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[2], Pawns[2].getOneDimensionCord(), 11);
                                    Pawns[2].setOneDimensionCord(11);
                                }else boolUpdateCard = false;
                            }else if(choice ==1){
                                int seChoice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να αλλάξεις με το 1\nΠάτησε 2 αν θες να αλλάξεις θέση με το 2:"
                                        ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if(seChoice==0){
                                    if(Pawns[0].getLocation()!=pawnLocation.start){
                                        int temp = Pawns[2].getOneDimensionCord();
                                        Pawns[2].setOneDimensionCord(-temp);
                                        Pawns[2].setOneDimensionCord(Pawns[0].getOneDimensionCord());
                                        Pawns[0].setOneDimensionCord(-Pawns[0].getOneDimensionCord());
                                        Pawns[0].setOneDimensionCord(temp);
                                        v.updatePawnNoButt(Pawns[0].getOneDimensionCord(),0);
                                    }else boolUpdateCard=false;
                                }else if(seChoice == 1){
                                    if(Pawns[1].getLocation()!=pawnLocation.start){
                                        int temp = Pawns[2].getOneDimensionCord();
                                        Pawns[2].setOneDimensionCord(-temp);
                                        Pawns[2].setOneDimensionCord(Pawns[1].getOneDimensionCord());
                                        Pawns[1].setOneDimensionCord(-Pawns[1].getOneDimensionCord());
                                        Pawns[1].setOneDimensionCord(temp);
                                        v.updatePawnNoButt(Pawns[1].getOneDimensionCord(),1);
                                    }else boolUpdateCard=false;
                                }
                            }
                            break;
                        case -1:
                            if(Pawns[2].getLocation()==pawnLocation.start){
                                Pawns[2].setLocation(pawnLocation.regular);
                                choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να αλλάξεις με το 1\nΠάτησε 2 αν θες να αλλάξεις θέση με το 2:"
                                        ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if(choice==1){
                                    if(Pawns[1].getLocation()==pawnLocation.regular){
                                        int temp = Pawns[2].getOneDimensionCord();
                                        Pawns[2].setOneDimensionCord(-temp);
                                        Pawns[2].setOneDimensionCord(Pawns[1].getOneDimensionCord());
                                        Pawns[1].setOneDimensionCord(-Pawns[1].getOneDimensionCord());
                                        Pawns[1].setOneDimensionCord(4);
                                        Pawns[1].setLocation(pawnLocation.start);
                                        v.updatePawnNoButt(76,1);
                                    }else boolUpdateCard=false;
                                }else if(choice == 0){
                                    if(Pawns[0].getLocation()==pawnLocation.regular){
                                        int temp = Pawns[2].getOneDimensionCord();
                                        Pawns[2].setOneDimensionCord(-temp);
                                        Pawns[2].setOneDimensionCord(Pawns[0].getOneDimensionCord());
                                        Pawns[0].setOneDimensionCord(-Pawns[0].getOneDimensionCord());
                                        Pawns[0].setOneDimensionCord(4);
                                        Pawns[0].setLocation(pawnLocation.start);
                                        v.updatePawnNoButt(75,0);
                                    }else boolUpdateCard=false;
                                }
                            }else boolUpdateCard=false;
                            break;
                        default:
                            if(Pawns[2].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),cardN);
                                Pawns[2].setOneDimensionCord(cardN);
                            }else boolUpdateCard=false;
                    }
                    System.out.println("post:"+Pawns[2].getOneDimensionCord());
                    if(boolUpdateCard){
                        v.updatePawn(Pawns[2].getOneDimensionCord(),b);
                    }
                    canPlayTurn = false;
                    pawnMoved = true;
                }
                else if(b==v.pawnsYellow[1]&&canPlayTurn){
                    boolUpdateCard = true;
                    System.out.println("Pre:"+Pawns[3].getOneDimensionCord());
                    switch (cardN){
                        case 1:
                            if(Pawns[3].getLocation()==pawnLocation.start){
                                Pawns[3].setOneDimensionCord(0);/*start point*/
                                Pawns[3].setLocation(pawnLocation.regular);
                                System.out.println(Pawns[3].getLocation());
                            }else{
                                checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),1);
                                Pawns[3].setOneDimensionCord(1);
                            }
                            break;
                        case 2:
                            nextTurn();
                            if(Pawns[3].getLocation()==pawnLocation.start){
                                Pawns[3].setOneDimensionCord(0);/*start point*/
                                Pawns[3].setLocation(pawnLocation.regular);
                                System.out.println(Pawns[3].getLocation());
                            }else{
                                checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),2);
                                Pawns[3].setOneDimensionCord(2);
                            }
                            break;
                        case 3,5:
                            if(Pawns[3].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),cardN);
                                Pawns[3].setOneDimensionCord(cardN);
                            }else boolUpdateCard=false;
                            if(Pawns[2].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),cardN);
                                Pawns[2].setOneDimensionCord(cardN);
                                v.updatePawnNoButt(Pawns[2].getOneDimensionCord(),2);
                            }break;
                        case 4:
                            if(Pawns[3].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),-4);
                                Pawns[3].setOneDimensionCord(-4);
                            }else boolUpdateCard=false;
                            break;
                        case 7:
                            int choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 7 θέσεις στο επιλεγμένο πίονι, αν θες να τις μοιράσεις πάτησε 2:"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(Pawns[3].getLocation()!=pawnLocation.start){
                                if(choice == 0) {
                                    checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),7);
                                    Pawns[3].setOneDimensionCord(7);
                                }else if(choice==1){
                                    int windInp = Integer.parseInt(JOptionPane.showInputDialog("", null));
                                    if(windInp<0||windInp>7){
                                        boolUpdateCard=false;
                                    }else{
                                        checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),windInp);
                                        Pawns[3].setOneDimensionCord(windInp);
                                        if(Pawns[2].getLocation()!=pawnLocation.start){
                                            checkBounds(Pawns[2],Pawns[2].getOneDimensionCord(),7-windInp);
                                            Pawns[2].setOneDimensionCord(7-windInp);
                                            v.updatePawnNoButt(Pawns[2].getOneDimensionCord(),2);
                                        }
                                    }
                                }
                            }else boolUpdateCard=false;
                            break;
                        case 8,12:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις"+cardN+" θέσεις στο επιλεγμένο πίονι\nαν θες να τραβήξεις νεά κάρτα πάτα 2:"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice == 0) {
                                if (Pawns[3].getLocation() != pawnLocation.start) {
                                    checkBounds(Pawns[3], Pawns[3].getOneDimensionCord(), 8);
                                    Pawns[3].setOneDimensionCord(8);
                                }else boolUpdateCard = false;
                            }else if(choice==1){
                                nextTurn();
                                boolUpdateCard = false;
                            }
                            break;
                        case 10:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 10 θέσεις στο επιλεγμένο πίονι\nΠάτησε 2 αν θες να παίξεις μια θέση πίσω :"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice==0){
                                if(Pawns[3].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[3], Pawns[3].getOneDimensionCord(), 10);
                                    Pawns[3].setOneDimensionCord(10);
                                }else boolUpdateCard = false;
                            }else if(choice==1){
                                if(Pawns[3].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[3], Pawns[3].getOneDimensionCord(), -1);
                                    Pawns[3].setOneDimensionCord(-1);
                                }else boolUpdateCard = false;
                            }
                            break;
                        case 11:
                            choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να παίξεις 11 θέσεις στο επιλεγμένο πίονι\nΠάτησε 2 αν θες να αλλάξεις θέση με πίονι αντιπάλου :"
                                    ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                            if(choice==0){
                                if(Pawns[3].getLocation()!=pawnLocation.start){
                                    checkBounds(Pawns[3], Pawns[3].getOneDimensionCord(), 11);
                                    Pawns[3].setOneDimensionCord(11);
                                }else boolUpdateCard = false;
                            }else if(choice ==1){
                                int seChoice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να αλλάξεις με το 1\nΠάτησε 2 αν θες να αλλάξεις θέση με το 2:"
                                        ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if(seChoice==0){
                                    if(Pawns[0].getLocation()!=pawnLocation.start){
                                        int temp = Pawns[3].getOneDimensionCord();
                                        Pawns[3].setOneDimensionCord(-temp);
                                        Pawns[3].setOneDimensionCord(Pawns[0].getOneDimensionCord());
                                        Pawns[0].setOneDimensionCord(-Pawns[0].getOneDimensionCord());
                                        Pawns[0].setOneDimensionCord(temp);
                                        v.updatePawnNoButt(Pawns[0].getOneDimensionCord(),0);
                                    }else boolUpdateCard=false;
                                }else if(seChoice == 1){
                                    if(Pawns[1].getLocation()!=pawnLocation.start){
                                        int temp = Pawns[3].getOneDimensionCord();
                                        Pawns[3].setOneDimensionCord(-temp);
                                        Pawns[3].setOneDimensionCord(Pawns[1].getOneDimensionCord());
                                        Pawns[1].setOneDimensionCord(-Pawns[1].getOneDimensionCord());
                                        Pawns[1].setOneDimensionCord(temp);
                                        v.updatePawnNoButt(Pawns[1].getOneDimensionCord(),1);
                                    }else boolUpdateCard=false;
                                }
                            }
                            break;
                        case -1:
                            if(Pawns[3].getLocation()==pawnLocation.start){
                                Pawns[3].setLocation(pawnLocation.regular);
                                choice = JOptionPane.showOptionDialog(null,"Πάτησε 1 αν θες να αλλάξεις με το 1\nΠάτησε 2 αν θες να αλλάξεις θέση με το 2:"
                                        ,"Option Dialog", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                                if(choice==1){
                                    if(Pawns[1].getLocation()==pawnLocation.regular){
                                        int temp = Pawns[3].getOneDimensionCord();
                                        Pawns[3].setOneDimensionCord(-temp);
                                        Pawns[3].setOneDimensionCord(Pawns[1].getOneDimensionCord());
                                        Pawns[1].setOneDimensionCord(-Pawns[1].getOneDimensionCord());
                                        Pawns[1].setOneDimensionCord(4);
                                        Pawns[1].setLocation(pawnLocation.start);
                                        v.updatePawnNoButt(76,1);
                                    }else boolUpdateCard=false;
                                }else if(choice == 0){
                                    if(Pawns[0].getLocation()==pawnLocation.regular){
                                        int temp = Pawns[3].getOneDimensionCord();
                                        Pawns[3].setOneDimensionCord(-temp);
                                        Pawns[3].setOneDimensionCord(Pawns[0].getOneDimensionCord());
                                        Pawns[0].setOneDimensionCord(-Pawns[0].getOneDimensionCord());
                                        Pawns[0].setOneDimensionCord(4);
                                        Pawns[0].setLocation(pawnLocation.start);
                                        v.updatePawnNoButt(75,0);
                                    }else boolUpdateCard=false;
                                }
                            }else boolUpdateCard=false;
                            break;
                        default:
                            if(Pawns[3].getLocation()!=pawnLocation.start){
                                checkBounds(Pawns[3],Pawns[3].getOneDimensionCord(),cardN);
                                Pawns[3].setOneDimensionCord(cardN);
                            }else boolUpdateCard=false;
                    }
                    System.out.println("post:"+Pawns[3].getOneDimensionCord());
                    if(boolUpdateCard){
                    v.updatePawn(Pawns[3].getOneDimensionCord(),b);}
                    canPlayTurn = false;
                    pawnMoved = true;
                }
            }
        endGame();
        }
    }

    /**
     * Sets the listeners for the buttons
     */
    public void setListener(){
        v.foldB.addActionListener(new foldListener());
        for(int i=0;i<2;i++){
            v.pawnsRed[i].addActionListener(new pawnListener());
            v.pawnsYellow[i].addActionListener(new pawnListener());
        }
        v.receiveCard.addActionListener(new cardListener());
        v.menuBar.getMenu(0).getItem(0).addActionListener(new newGameListener());
    }

    /**
     * Checks if the pawn is out of bounds and sets it to the correct position, or if it is in the safe zone/home
     * @param Pawn
     * @param pre
     * @param fwrd
     */
    void checkBounds(pawn Pawn,int pre,int fwrd){
        int post = pre+fwrd;
        if(post>59){
            /*if(Pawn.getColor()==Color.red&&post>=63&&post<=67){
                Pawn.setLocation(pawnLocation.safe);
            }else if(Pawn.getColor()==Color.red&&post==68){
                Pawn.setLocation(pawnLocation.home);
                Pawn.setImmovable();
            }*/
            Pawn.setOneDimensionCord(-59);
        }/*
        if(Pawn.getColor()==Color.red&&post>=3&&post<=7&&pre<4){/*pre <4 , shmainei oti exei kanei hdh ena loop
            Pawn.setOneDimensionCord(60);
            Pawn.setLocation(pawnLocation.safe);
        }else if(Pawn.getColor()==Color.red&&post==8&&pre<4){
            Pawn.setOneDimensionCord(60);
            Pawn.setLocation(pawnLocation.home);
            Pawn.setImmovable();
        }*/
        if(post<0){
            Pawn.setOneDimensionCord(60);
        }/*
        if(Pawn.getColor()==Color.yellow&&post>=33&&post<=37&&pre<34) {
            Pawn.setOneDimensionCord(68);
            Pawn.setLocation(pawnLocation.safe);
        }*/
    }
    /**
     * transformer
     * Next turn
     */
    void nextTurn(){
        this.turn=!this.turn;
    }
    /**
     * accessor
     * @return turn h seira
     */
    boolean getTurn(){
        return this.turn;
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args){
        mainMenu menu = new mainMenu();
        menu.newGame();
    }
    public class tests{
        @Test
        public void testCheckBounds(){
            pawn Pawn = new pawn(0,0,pawnLocation.regular);
            Pawn.setOneDimensionCord(0);
            checkBounds(Pawn,Pawn.getOneDimensionCord(),1);
            assertEquals(1,Pawn.getOneDimensionCord());
            assertEquals(pawnLocation.regular,Pawn.getLocation());
        }
    }
}