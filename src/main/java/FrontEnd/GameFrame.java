/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import BoardPackage.Board;
import GamePackage.Game;
import GamePackage.Player;
import GamePackage.controlGame;
import SectionsPackage.BasicService;
import SectionsPackage.Jail;
import SectionsPackage.Neutro;
import SectionsPackage.Place;
import SectionsPackage.Property;
import SectionsPackage.Section;
import SectionsPackage.Start;
import SectionsPackage.Station;
import SectionsPackage.TakeOne;
import SectionsPackage.Trap;
import TimePackage.GameTime;
import TimePackage.GameTimer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.HeadlessException;
import java.util.Random;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author user-ubunto
 */
public class GameFrame extends javax.swing.JFrame {

    private controlGame control;
    private Game game;
    private Board board;
    private String[] groupsPlace = new String[0];
    private Color[] colorsPlace = new Color[0];
    private JLabel[] playerChips;
    private int quantityThrowAgain;
    GameTime time;
    GameTimer timer;
    private int timeLimit;
    /**
     * Creates new form GameFrame
     * @param control
     * @param timeLimit
     * @param time
     */
    public GameFrame(controlGame control, int timeLimit, int time) {
        initComponents();
        this.control = control;
        this.game = this.control.getGamePlayed();
        this.board = this.game.getGameBoard();        
        this.control.obtainSectionsPerTurn(); 
        this.quantityThrowAgain = 0;
//        int turnPlayer = (int)Math.random()*this.game.getGamePlayers().length;        
        this.game.setTurnPlayer(0);
        // set chips
        setChips();
        // initialize the board
        initBoard();        
        //set the chips in their positions
        setInitialPositionChip();        
        //set turn of the player
        setTextTurnPlayer();        
        if (timeLimit != 0) {
            // Set timer 
            this.LabelTime.setText(String.valueOf(timeLimit));
            this.timeLimit = timeLimit;
            initTimer();
        }else{
            //Set time forever
            this.LabelTime.setText(String.valueOf(time));
            initTime();
            this.timeLimit = 0;
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        LabelTurn = new javax.swing.JLabel();
        ButtonThrow = new javax.swing.JButton();
        ButtonSeeProperties = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        LabelTime = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ButtonMortgage = new javax.swing.JButton();
        ButtonConstruct = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        ButtonCondition = new javax.swing.JButton();
        ButtonEndGame = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        LabelMoney = new javax.swing.JLabel();
        ButtonPayMortgage = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Turno del Jugador");

        LabelTurn.setText("turno");

        ButtonThrow.setText("Lanzar Dados");
        ButtonThrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonThrowActionPerformed(evt);
            }
        });

        ButtonSeeProperties.setText("Ver Propiedades");
        ButtonSeeProperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSeePropertiesActionPerformed(evt);
            }
        });

        jLabel2.setText("Tiempo:");

        LabelTime.setText("0");

        ButtonMortgage.setText("Hipotecar");
        ButtonMortgage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonMortgageActionPerformed(evt);
            }
        });

        ButtonConstruct.setText("Construir");
        ButtonConstruct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonConstructActionPerformed(evt);
            }
        });

        jButton4.setText("Terminar turno");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        ButtonCondition.setText("Bancarrota");
        ButtonCondition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonConditionActionPerformed(evt);
            }
        });

        ButtonEndGame.setText("Terminar Juego");
        ButtonEndGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEndGameActionPerformed(evt);
            }
        });

        jLabel3.setText("Dinero:");

        LabelMoney.setText("jLabel4");

        ButtonPayMortgage.setText("Pagar Hipoteca");
        ButtonPayMortgage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPayMortgageActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar Juego");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ButtonSeeProperties))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonThrow, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ButtonMortgage, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonConstruct, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ButtonEndGame))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ButtonPayMortgage, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ButtonCondition, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(LabelTurn)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(LabelMoney)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel2)
                        .addGap(27, 27, 27)
                        .addComponent(LabelTime)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelTurn)
                        .addComponent(jLabel3)
                        .addComponent(LabelMoney)
                        .addComponent(jLabel2)
                        .addComponent(LabelTime))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonPayMortgage)
                    .addComponent(ButtonMortgage)
                    .addComponent(ButtonThrow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonCondition)
                    .addComponent(jButton1))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(ButtonSeeProperties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonConstruct)
                    .addComponent(ButtonEndGame))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonThrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonThrowActionPerformed
        //throw the dices
        int[] dices = this.control.throwDices();
        boolean throwAgain;
        int totalSteps = 0;
        String separatedSteps = "";
        if (this.game.getNumberDices() == 1) {
            throwAgain= false;
        }else{
            throwAgain = true;
        }        
        for (int i = 0; i < this.game.getNumberDices(); i++) {
            separatedSteps += ", " + String.valueOf(dices[i]);
            totalSteps += dices[i];
            if (i <= this.game.getNumberDices()-2) {
                if (dices[i] != dices[i+1]) {
                    throwAgain = false;
                }
            }            
        }
        // show the value of the dices
        JOptionPane.showMessageDialog(null, "Has obtenido"+ separatedSteps + ". Que suman: "+ totalSteps);
        if (throwAgain == true) {
            this.quantityThrowAgain++;
            if (this.quantityThrowAgain != 3) {
                JOptionPane.showMessageDialog(null,"Tienes todos iguales, vuelve a tirar");
                this.ButtonThrow.setEnabled(true);
                moveChip(totalSteps);
            }else{
                this.control.threeTimesDoubles();
            }                        
        }else{
            this.quantityThrowAgain = 0;
            this.ButtonThrow.setEnabled(false);
            moveChip(totalSteps);
        }
        verifySection();
    }//GEN-LAST:event_ButtonThrowActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //End turn
        //First review if the game is over
        boolean finishGame = this.control.reviewWinGame();
        if (this.timer != null) {
            if (Integer.parseInt(this.LabelTime.getText()) <= 0) {
                finishGame = true;
                this.control.endGame();                
            }
        }
        if (finishGame == true) {
            if (this.time != null) {
                this.time.setStopTime(true);
            }else if (this.timer != null){
                this.timer.setStop(true);
            }
            enableComponents(this.jPanel1,false);
        }else{
            this.quantityThrowAgain = 0;
            endTurn();
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ButtonSeePropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSeePropertiesActionPerformed
        this.control.seeProperties();        
    }//GEN-LAST:event_ButtonSeePropertiesActionPerformed

    private void ButtonConditionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConditionActionPerformed
        this.control.bankruptcy();        
    }//GEN-LAST:event_ButtonConditionActionPerformed

    private void ButtonMortgageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonMortgageActionPerformed
        this.control.mortgageProperty();
        setTextTurnPlayer();
    }//GEN-LAST:event_ButtonMortgageActionPerformed

    private void ButtonConstructActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConstructActionPerformed
        this.control.constructInProperty();
    }//GEN-LAST:event_ButtonConstructActionPerformed

    private void ButtonEndGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEndGameActionPerformed
        Object[] options = { "Yes", "No" };
        int n = JOptionPane.showOptionDialog(null,"Realmente deseas terminar el juego?", "Terminar Juego",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[1]);
        if(n == JOptionPane.OK_OPTION){ // Afirmative
            this.control.endGame();
            enableComponents(this.jPanel1,false);
            if (this.time != null) {
                this.time.setStopTime(true);
            }else if (this.timer != null){
                this.timer.setStop(true);
            }
        }                
    }//GEN-LAST:event_ButtonEndGameActionPerformed

    private void ButtonPayMortgageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPayMortgageActionPerformed
        this.control.payMortgage();
        setTextTurnPlayer();
    }//GEN-LAST:event_ButtonPayMortgageActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        saveGame();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * This method is used to save the game in the Computer.
     * This creates a .game
     * 
     */
    public void saveGame(){
        boolean gameSaved = false;
        fileManager fileM = new fileManager();        
            try {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("control.game", "game"));
                fileChooser.setAcceptAllFileFilterUsed(false);
                int seleccion = fileChooser.showSaveDialog(this);
                if (seleccion == APPROVE_OPTION) {
                    
                    
                    if (this.timeLimit != 0) {
                        int timer = Integer.parseInt(this.LabelTime.getText());
                        this.control.setTimer(timer);
                    }else{
                        int time = Integer.parseInt(this.LabelTime.getText());
                        this.control.setTime(time);
                    }
                    fileM.saveControlGame(fileChooser.getSelectedFile().getPath()+".game", this.control);
                    gameSaved = true;
                }
            } catch (HeadlessException  e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Info", JOptionPane.ERROR_MESSAGE);
            }
            if (gameSaved) {
                JOptionPane.showMessageDialog(null, "Tablero Creado con Exito");
            }
        
    }
    
    /**
     * This method is used to verify if the 
     * section where is pissed of the chip
     * are the same of the section of the player.
     * 
     */
    public void verifySection(){
        Player[] allPlayers = this.game.getGamePlayers();
        int sectionPositionTurn = allPlayers[this.game.getTurnPlayer()].getPositionSection();
        Section[] allSectionsPerTurn = this.control.getSectionsPerTurn();
        Section sectionInPosition = allSectionsPerTurn[sectionPositionTurn];
        int row = sectionInPosition.getRowposition();
        int column = sectionInPosition.getColumnPosition();
        positionChip(this.game.getTurnPlayer(),row,column);        
    }
    
    /**
     * This method is used to set the text to the label
     * of the player who is in turn.
     * 
     */
    public void setTextTurnPlayer(){
        Player[] allPlayers = this.game.getGamePlayers();
        this.LabelTurn.setText(allPlayers[this.game.getTurnPlayer()].getName());
        this.LabelMoney.setText(String.valueOf(allPlayers[this.game.getTurnPlayer()].getAmountMoney()));
    }
    
    /**
     * This method is used to end the turn
     * change the player in turn.
     * 
     */
    public void endTurn(){
        this.control.endTurnPlayer();
        this.ButtonThrow.setEnabled(true);
        setTextTurnPlayer();
    }
    
    /**
     * This method is used to set enable
     * the components of the containner
     * 
     * @param container
     * @param enable
     */
    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container)component, enable);
            }
        }
    }
    
    /**
     * This method is used to call in the control game
     * to move the chip.
     * 
     */
    public void moveChip(int totalSteps){        
        Section sectionInPosition = this.control.moveChipPlayer(totalSteps);
        int row = sectionInPosition.getRowposition();
        int column = sectionInPosition.getColumnPosition();
        positionChip(this.game.getTurnPlayer(),row,column);
        setTextTurnPlayer();
    }
       
    /**
     * This method is used to init the time
     * the GameTime is a thread.
     */
    public void initTime(){
        time = new GameTime(this.LabelTime);
        time.start();
    }
    
    /**
     * This method is used to init the timer
     * the GameTimer is a thread.
     */
    public void initTimer(){
        this.timer = new GameTimer(this.LabelTime);
        timer.start();
        if (Integer.parseInt(this.LabelTime.getText()) <= 0) {
            this.control.endGame();
            enableComponents(this.jPanel1,false);
        }
    }
    
    /**
     * This method is used to show the board.
     * 
     */
    public void initBoard(){        
        int rows = this.board.getQuantityRows();
        int columns = this.board.getQuantityColumns();
        Section[][] sections = this.board.getSectionsBoard();
        for (int i = 0; i < rows; i++) {            
            for (int j = 0; j < columns; j++) {                
                CreateGameBoard temp = new CreateGameBoard();
                this.jPanel2.add(temp.getPanel());
                try{                    
                    temp.setColor(setColorPanel(sections[i][j],temp));
                    if (sections[i][j] instanceof Property) {
                        temp.setCost(String.valueOf(((Property)sections[i][j]).getPurchasePrice()));                        
                    }
                }catch(NullPointerException e){
                    temp.setColor(Color.WHITE);
                }
                temp.setBounds(i, j);
            }
        }
    }
    
    public Color setColorPanel(Section sectionCreate, CreateGameBoard temp){        
        Color color = Color.WHITE;
        if (sectionCreate instanceof Start) {
            color = Color.GREEN;
            temp.setName("Inicio");
        }else if (sectionCreate instanceof Jail) {
            color = Color.RED;
            temp.setName("Carcel");
        }else if (sectionCreate instanceof BasicService) {
            color = Color.CYAN;
            temp.setName(((BasicService)sectionCreate).getName());
        }else if (sectionCreate instanceof Station) {
            color = Color.DARK_GRAY;
            temp.setName(((Station)sectionCreate).getName());
        }else if (sectionCreate instanceof Trap) {
            color = Color.GRAY;
            temp.setName("Trampa");
        }else if (sectionCreate instanceof TakeOne) {
            color = Color.LIGHT_GRAY;
            temp.setName("Toma una");
        }else if (sectionCreate instanceof Place) {
            temp.setName(((Place)sectionCreate).getName());
            color = selectColorPlace(sectionCreate);            
        }else if (sectionCreate instanceof Neutro) {            
            color = new Color(124,189,160);
        }
        return color;
    }
    
    public Color selectColorPlace(Section sectionCreate){
        Color color = Color.WHITE;
        int numberColor = -1;
        for (int i = 0; i < this.groupsPlace.length; i++) {
            if (this.groupsPlace[i].equalsIgnoreCase(((Place)sectionCreate).getGroupPlace())) {
                numberColor = i;
            }
        }        
        if (numberColor == -1) {
            Random rand = new Random();
            float r = rand.nextFloat(); 
            float g = rand.nextFloat(); 
            float b = rand.nextFloat();  
            color = new Color(r,g,b);
            String[] groupsPlace2 = this.groupsPlace;
            this.groupsPlace = new String[groupsPlace2.length+1];
            for (int i = 0; i < groupsPlace2.length; i++) {
                this.groupsPlace[i] = groupsPlace2[i];                
            }
            this.groupsPlace[groupsPlace2.length] = ((Place)sectionCreate).getGroupPlace();
            Color[] colorsPlace2 = this.colorsPlace;
            this.colorsPlace = new Color[colorsPlace2.length+1];
            for (int i = 0; i < colorsPlace2.length; i++) {
                this.colorsPlace[i] = colorsPlace2[i];                
            }
            this.colorsPlace[colorsPlace2.length] = color;                        
        }else{
            color = this.colorsPlace[numberColor];
        }        
        return color;
    }
    
    /**
     * This method is used to set the chips 
     * in the initial position.
     */
    public void setInitialPositionChip(){
        try{
            this.playerChips[0].setLocation(0,20);
            this.playerChips[1].setLocation(0,50);
            this.playerChips[2].setLocation(25,20);
            this.playerChips[3].setLocation(25,50);
            this.playerChips[4].setLocation(50,20);
            this.playerChips[5].setLocation(50,50);            
        }catch(Exception e){
            
        }
        Section[] sectionStart = this.control.getSectionsPerTurn();
        Player[] allPlayers = this.game.getGamePlayers();
        for (int i = 0; i < allPlayers.length; i++) {
            int row = sectionStart[allPlayers[i].getPositionSection()].getRowposition();
            int column = sectionStart[allPlayers[i].getPositionSection()].getColumnPosition();
            positionChip(i,row,column);
        }
    }
    
    public void positionChip(int turnPlayer,int row, int column){
        if (turnPlayer == 0) {
            this.playerChips[0].setLocation((column*100),(row*70) +20);
        }else if (turnPlayer == 1) {
            this.playerChips[1].setLocation((column*100), (row*70)+50);
        }else if (turnPlayer == 2) {
            this.playerChips[2].setLocation((column*100)+25, (row*70)+20);
        }else if (turnPlayer == 3) {
            this.playerChips[3].setLocation((column*100)+25, (row*70)+50);
        }else if (turnPlayer == 4) {
            this.playerChips[4].setLocation((column*100)+50, (row*70)+20);
        }else if (turnPlayer == 5) {
            this.playerChips[5].setLocation((column*100)+50, (row*70)+50);
        }                
    }
    
    /**
     * This method is show the chips of the players.
     */
    public void setChips(){
        Player[] players = this.game.getGamePlayers();
        this.playerChips = new JLabel[players.length];
        Section[] sectionStart = this.control.getSectionsPerTurn();
        int row = sectionStart[0].getRowposition();
        int column = sectionStart[0].getColumnPosition();
        for (int i = 0; i < players.length; i++) {            
            PlayerChip chip = new PlayerChip(players[i].getColor(), String.valueOf(i+1));
            this.playerChips[i] = chip.getChip();
            this.jPanel2.add(this.playerChips[i]);
            chip.setBoundsChip(row,column);
        }
    }
    
    public void rezisePanel(){
        int rows = this.game.getGameBoard().getQuantityRows();
        int columns = this.game.getGameBoard().getQuantityColumns();
        if (rows *70 > 317 || columns*100 > 661) {
            this.jPanel2.setSize(rows*70, columns*100);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonCondition;
    private javax.swing.JButton ButtonConstruct;
    private javax.swing.JButton ButtonEndGame;
    private javax.swing.JButton ButtonMortgage;
    private javax.swing.JButton ButtonPayMortgage;
    private javax.swing.JButton ButtonSeeProperties;
    private javax.swing.JButton ButtonThrow;
    private javax.swing.JLabel LabelMoney;
    private javax.swing.JLabel LabelTime;
    private javax.swing.JLabel LabelTurn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}