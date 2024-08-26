package ola.view;

import ola.model.card.card;
import ola.model.card.numberCard;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class View extends JFrame{

    private JLabel[] safeRed = new JLabel[5];
    private JLabel[] safeYellow = new JLabel[5];
    private static int[] cordX = new int[79];
    private static int[] cordY = new int[79];
    private static final int[] RED_START_X = new int[2];
    private static final int[] RED_START_Y = new int[2];
    private static final int[] YELLOW_START_X = new int[2];
    private static final int[] YELLOW_START_Y = new int [2];
    private JLabel[] sqrCord = new JLabel[60];
    public JLayeredPaneExtension back;
    private JTextArea infoBox;
    private ClassLoader cldr;
    private JLabel sorryBack;
    private JLabel cyanBack;
    private JLabel currentCard;
    public JMenu menu;
    public JMenuItem newGame,loadGame,exit,saveGame;
    /*make receive card buttin not Jlabel*/

    public JButton receiveCard;
    public JButton foldB;
    public JMenuBar menuBar;
    private JLabel redHome;
    private JLabel yellowHome;
    private JLabel redStart;
    private JLabel yellowStart;
    public JButton[] pawnsRed=new JButton[2];
    public JButton[] pawnsYellow=new JButton[2];
    private int[] RED_SAFE_X = new int[5];
    private int[] RED_SAFE_Y = new int[5];
    private int[] YELLOW_SAFE_X = new int[5];
    private int[] YELLOW_SAFE_Y = new int[5];
    private JLayeredPaneExtension currentCardImg;
    private JLayeredPaneExtension receiveCardImg;
    private int[] lastX1=new int[2];
    private int[] lastY1=new int [2];
    private int[] lastX2=new int[2];
    private int[] lastY2=new int [2];

    /**
     * transformer
     * view constructor
     * sets the title of the window, the size of the window and the close operation
     */
    public View() {
        cldr = this.getClass().getClassLoader();
        this.setResizable(false);
        this.setTitle("Sorry game");
        this.setPreferredSize(new Dimension(1024, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * transformer
     * updates the infobox with the given string
     * @param s the new string to be displayed
     */
    public void updateInfobox(String s){
        infoBox.setText(s);
        back.repaint();
    }
    /**
     * transformer
     * updates the current card image
     * @param C the new card to be displayed
     */
    public void updateCard(card C) {
        String cardImg = C.toString();
        URL imageURL = cldr.getResource("resources/cards/card" + cardImg + ".png");
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(100, 140, java.awt.Image.SCALE_SMOOTH);
        currentCardImg = new JLayeredPaneExtension(image);
        currentCardImg.setBounds(900, 180, 100, 140);
        currentCardImg.setOpaque(true);
        back.add(currentCardImg, 0);
        back.repaint();
    }

    /**
     * transformer
     * updates the pawns position
     * @param position the new position of the pawn
     * @param pawn the pawn to be updated
     */
    public void updatePawn(int position,JButton pawn){
        pawn.setBounds(cordX[position], cordY[position], 45, 45);
        back.add(pawn, 0);
        back.repaint();

    }
    /**
     * transformer
     * Updates the pawns position, without any buttons(for cards that move two pawns)
     * @param position the new position of the pawn
     * @param index the index of the pawn to be updated
     */
    public void updatePawnNoButt(int position,int index){
        switch (index){
            case 0:
                pawnsRed[0].setBounds(cordX[position], cordY[position], 45, 45);
                back.add(pawnsRed[0], 0);
                back.repaint();
                break;
            case 1:
                pawnsRed[1].setBounds(cordX[position], cordY[position], 45, 45);
                back.add(pawnsRed[1], 0);
                back.repaint();
                break;
            case 2:
                pawnsYellow[0].setBounds(cordX[position], cordY[position], 45, 45);
                back.add(pawnsRed[1], 0);
                back.repaint();
                break;
            case 3:
                pawnsYellow[1].setBounds(cordX[position], cordY[position], 45, 45);
                back.add(pawnsYellow[1], 0);
                back.repaint();
                break;
        }
    }

    /**
     * transformer
     *inintializes the components of the view
     */
    public void initComponents(){
        /*background*/
        URL imageURL = cldr.getResource("resources/back/background.png");
        Image image = new ImageIcon(imageURL).getImage();
        back = new JLayeredPaneExtension(image);
        /*board*/
        cyanBack = new JLabel();
        cyanBack.setBackground(Color.CYAN);
        cyanBack.setBounds(0, 0, 720, 720);
        cyanBack.setOpaque(true);
        sorryBack = new JLabel();
        URL URLsorry = cldr.getResource("resources/back/sorryImage.png");
        Image sorryImg = new ImageIcon(URLsorry).getImage();
        sorryImg = sorryImg.getScaledInstance(300, 100,  java.awt.Image.SCALE_SMOOTH);
        sorryBack.setIcon(new ImageIcon(sorryImg));
        sorryBack.setBounds(200, 330, 300, 100);
        sorryBack.setOpaque(true);
        back.add(cyanBack, 0);
        back.add(sorryBack, 0);
        /*cards on top of infobox*/
        currentCard = new JLabel();
        currentCard.setText("Current Card");
        currentCard.setBounds(900, 300, 100, 70);
        back.add(currentCard, 0);
        /*transparent receive card button*/
        receiveCard = new JButton();
        receiveCard.setText("Receive Card");
        receiveCard.setBounds(720, 300, 200, 70);
        receiveCard.setOpaque(false);
        receiveCard.setContentAreaFilled(false);
        receiveCard.setBorderPainted(false);
        back.add(receiveCard, 0);
        /*infobox*/
        infoBox = new JTextArea();
        infoBox.setBounds(800, 450, 200, 200);
        infoBox.setBackground(Color.WHITE);
        infoBox.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        infoBox.setOpaque(true);
        infoBox.setText("Info Box\n\nTurn:Player 1(Red)\nCards Left:44");
        infoBox.setEditable(false);
        back.add(infoBox, 0);
        /*fold button*/
        foldB = new JButton();
        foldB.setText("Fold Button");
        foldB.setBounds(800, 390, 200, 50);
        foldB.setBackground(Color.RED);
        foldB.setOpaque(true);
        back.add(foldB, 0);
        /*manu for new game,load game,exit*/
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        newGame = new JMenuItem("New Game");
        loadGame = new JMenuItem("Load Game");
        exit = new JMenuItem("Exit");
        saveGame = new JMenuItem("Save Game");
        menu.add(newGame);
        menu.add(loadGame);
        menu.add(saveGame);
        menu.add(exit);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        /*start point for pawns*/
        for(int i=0;i<2;i++){
            lastX1[i]=4;
            lastY1[i]=4;
            lastX2[i]=34;
            lastY2[i]=34;
        }
        /*init cordinates x and y for squares*/
        URL[] squareImg = new URL[4];/*for each color*/
        for(int i = 0;i<15;i++){
            cordX[i] = i*45;
            cordY[i] = 0;
            cordX[i+15] = 15*45;
            cordY[i+15] = i*45;
            cordX[i+30] = (15-i)*45;
            cordY[i+30] = 15*45;
            cordX[i+45] = 0;
            cordY[i+45] = (15-i)*45;
            if(i==1||i==9){
                squareImg[0] = cldr.getResource("resources/slides/redSlideStart.png");
                squareImg[1] = cldr.getResource("resources/slides/blueSlideStart.png");
                squareImg[2] = cldr.getResource("resources/slides/yellowSlideStart.png");
                squareImg[3] = cldr.getResource("resources/slides/greenSlideStart.png");
            }
            else if(i==2||i==3||i==10||i==11|i==12){
                squareImg[0] = cldr.getResource("resources/slides/redSlideMedium.png");
                squareImg[1] = cldr.getResource("resources/slides/blueSlideMedium.png");
                squareImg[2] = cldr.getResource("resources/slides/yellowSlideMedium.png");
                squareImg[3] = cldr.getResource("resources/slides/greenSlideMedium.png");
            }
            else if(i==4||i==13){
                squareImg[0] = cldr.getResource("resources/slides/redSlideEnd.png");
                squareImg[1] = cldr.getResource("resources/slides/blueSlideEnd.png");
                squareImg[2] = cldr.getResource("resources/slides/yellowSlideEnd.png");
                squareImg[3] = cldr.getResource("resources/slides/greenSlideEnd.png");
            }
            else{
                squareImg[0] = cldr.getResource("resources/slides/SimpleSquare.png");
                squareImg[1] = cldr.getResource("resources/slides/SimpleSquare.png");
                squareImg[2] = cldr.getResource("resources/slides/SimpleSquare.png");
                squareImg[3] = cldr.getResource("resources/slides/SimpleSquare.png");
            }
            sqrCord[i] = new JLabel();
            sqrCord[i].setIcon(new ImageIcon(squareImg[0]));
            sqrCord[i].setBounds(cordX[i], cordY[i], 45, 45);
            sqrCord[i].setOpaque(true);
            sqrCord[i+15] = new JLabel();
            sqrCord[i+15].setIcon(new ImageIcon(squareImg[1]));
            sqrCord[i+15].setBounds(cordX[i+15], cordY[i+15], 45, 45);
            sqrCord[i+15].setOpaque(true);
            sqrCord[i+30] = new JLabel();
            sqrCord[i+30].setIcon(new ImageIcon(squareImg[2]));
            sqrCord[i+30].setBounds(cordX[i+30], cordY[i+30], 45, 45);
            sqrCord[i+30].setOpaque(true);
            sqrCord[i+45] = new JLabel();
            sqrCord[i+45].setIcon(new ImageIcon(squareImg[3]));
            sqrCord[i+45].setBounds(cordX[i+45], cordY[i+45], 45, 45);
            sqrCord[i+45].setOpaque(true);
            back.add(sqrCord[i], 0);
            back.add(sqrCord[i+15], 0);
            back.add(sqrCord[i+30], 0);
            back.add(sqrCord[i+45], 0);
        }
        cordX[60] = cordX[0];/*ta xreiazomai gia to loop ston paikth 1*/
        cordY[60] = cordY[0];
        cordX[61] = cordX[1];
        cordY[61] = cordY[1];
        cordX[62] = cordX[2];
        cordY[62] = cordY[2];
        for(int i=0;i<5;i++){
            cordX[i+63] = RED_SAFE_X[i];
            cordY[i+63] = RED_SAFE_Y[i];
            cordX[i+69] = YELLOW_SAFE_X[i];
            cordY[i+69] = YELLOW_SAFE_Y[i];
        }
        cordX[68]= cordX[2]-45+15;
        cordY[68]= cordY[54];
        cordX[74]= cordX[15]-(45*3)+15;
        cordY[74]= cordY[23];
        YELLOW_START_X[0]=cordX[34]-30;
        YELLOW_START_X[1]=cordX[34]+15;
        YELLOW_START_Y[0]=cordY[30]-45;
        YELLOW_START_Y[1]=cordY[30]-45;
        RED_START_X[0]=cordX[4]-30;
        RED_START_X[1]=cordX[4]+15;
        RED_START_Y[0]=cordY[59];
        RED_START_Y[1]=cordY[59];
        /*red cord x and y for start*/
        cordX[75]=cordX[4]-30;
        cordY[75]=cordY[59];
        cordX[76]=cordX[4]+15;
        cordY[76]=cordY[59];
        /*yellow cord x and y for start*/
        cordX[77]=cordX[34]-30;
        cordY[77]=cordY[30]-45;
        cordX[78]=cordX[34]+15;
        cordY[78]=cordY[30]-45;
        /*red and yellow safezone*/
        for(int i =0;i<5;i++){
            safeRed[i] = new JLabel();
            safeRed[i].setIcon(new ImageIcon(cldr.getResource("resources/slides/safeRedZone.png")));
            safeRed[i].setBounds(cordX[2],cordY[59-i], 45, 45);
            RED_SAFE_X[i] = cordX[2];
            RED_SAFE_Y[i] = cordY[59-i];
            safeRed[i].setOpaque(true);
            safeYellow[i] = new JLabel();
            safeYellow[i].setIcon(new ImageIcon(cldr.getResource("resources/slides/safeYellowZone.png")));
            safeYellow[i].setBounds(cordX[32], cordY[29-i], 45, 45);
            YELLOW_SAFE_X[i] = cordX[32];
            YELLOW_SAFE_Y[i] = cordY[29-i];
            safeYellow[i].setOpaque(true);
            back.add(safeRed[i], 0);
            back.add(safeYellow[i], 0);
        }
        /*red and yellow start and home*/
        redStart = new JLabel();
        URL URLredStart = cldr.getResource("resources/slides/startRed.png");
        Image redStartImg = new ImageIcon(URLredStart).getImage();
        redStartImg = redStartImg.getScaledInstance(110, 90,  java.awt.Image.SCALE_SMOOTH);
        redStart.setIcon(new ImageIcon(redStartImg));
        redStart.setBounds(cordX[4]-30, cordY[59], 110, 90);
        redStart.setOpaque(true);
        yellowStart = new JLabel();
        URL URLyellowStart = cldr.getResource("resources/slides/startYellow.png");
        Image yellowStartImg = new ImageIcon(URLyellowStart).getImage();
        yellowStartImg = yellowStartImg.getScaledInstance(110, 90,  java.awt.Image.SCALE_SMOOTH);
        yellowStart.setIcon(new ImageIcon(yellowStartImg));
        yellowStart.setBounds(cordX[34]-30, cordY[29]-45, 110, 90);
        yellowStart.setOpaque(true);
        redHome = new JLabel();
        URL URLredHome = cldr.getResource("resources/slides/homeRed.png");
        Image redHomeImg = new ImageIcon(URLredHome).getImage();
        redHomeImg = redHomeImg.getScaledInstance(110, 90,  java.awt.Image.SCALE_SMOOTH);
        redHome.setIcon(new ImageIcon(redHomeImg));
        redHome.setBounds(cordX[2]-45+15, cordY[54], 110, 90);
        redHome.setOpaque(true);
        yellowHome = new JLabel();
        URL URLyellowHome = cldr.getResource("resources/slides/homeYellow.png");
        Image yellowHomeImg = new ImageIcon(URLyellowHome).getImage();
        yellowHomeImg = yellowHomeImg.getScaledInstance(110, 90,  java.awt.Image.SCALE_SMOOTH);
        yellowHome.setIcon(new ImageIcon(yellowHomeImg));
        yellowHome.setBounds(cordX[15]-(45*3)+15, cordY[23], 110, 90);
        yellowHome.setOpaque(true);
        back.add(redStart, 0);
        back.add(yellowStart, 0);
        back.add(redHome, 0);
        back.add(yellowHome, 0);
        /*init pawns*/
        for(int i =0;i<2;i++){
        pawnsRed[i] = new JButton();
        URL URLpawnRed = cldr.getResource("resources/pawns/redPawn"+(i+1)+".png");
        Image pawnRedImg = new ImageIcon(URLpawnRed).getImage();
        pawnRedImg = pawnRedImg.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
        pawnsRed[i].setIcon(new ImageIcon(pawnRedImg));
        pawnsRed[i].setBounds(RED_START_X[i],RED_START_Y[i], 45, 45);
        pawnsRed[i].setOpaque(true);
        pawnsYellow[i] = new JButton();
        URL URLpawnYellow = cldr.getResource("resources/pawns/yellowPawn"+(i+1)+".png");
        Image pawnYellowImg = new ImageIcon(URLpawnYellow).getImage();
        pawnYellowImg = pawnYellowImg.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH);
        pawnsYellow[i].setIcon(new ImageIcon(pawnYellowImg));
        pawnsYellow[i].setBounds(YELLOW_START_X[i],YELLOW_START_Y[i], 45, 45);
        pawnsYellow[i].setOpaque(true);
        back.add(pawnsRed[i], 0);
        back.add(pawnsYellow[i], 0);
        }
        URL recURL = cldr.getResource("resources/cards/backCard.png");
        Image imageRec = new ImageIcon(recURL).getImage();
        imageRec = imageRec.getScaledInstance(100, 140,  java.awt.Image.SCALE_SMOOTH);
        receiveCardImg = new JLayeredPaneExtension(imageRec);
        receiveCardImg.setBounds(780, 180, 100, 140);
        receiveCardImg.setOpaque(true);
        back.add(receiveCardImg, 0);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(back, GroupLayout.PREFERRED_SIZE, 1024, GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(back, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE));
        this.pack();
        this.back.repaint();
    }
}


