/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePackage;

import BoardPackage.Board;
import CardsPackage.Card;
import CardsPackage.CardGoJail;
import CardsPackage.CardLostTurn;
import CardsPackage.CardMoveToSection;
import CardsPackage.CardOutJail;
import CardsPackage.CardPayToPlayers;
import CardsPackage.CardPenalty;
import CardsPackage.CardPersonalized;
import CardsPackage.CardPrize;
import CardsPackage.CardWalk;
import CardsPackage.GroupCards;
import DicesPackage.ThrowDice;
import FrontEnd.CardFrame;
import FrontEnd.PropertiesFrame;
import FrontEnd.buyHousesHotelsFrame;
import FrontEnd.fileManager;
import RankingPackage.Ranking;
import SectionsPackage.BasicService;
import SectionsPackage.Jail;
import SectionsPackage.Place;
import SectionsPackage.Property;
import SectionsPackage.Section;
import SectionsPackage.Start;
import SectionsPackage.Station;
import SectionsPackage.TakeOne;
import SectionsPackage.Trap;
import java.io.Serializable;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class controlGame implements Serializable{
    private Board board;
    private Game gamePlayed;
    private Section[] sectionsPerTurn;
    private int timer;
    private int time;
    private boolean gameFinish;
    
    /**
     * Cronstructor Method
     * @param gamePlayed
     */
    public controlGame(Game gamePlayed) {
        this.gamePlayed = gamePlayed;
        this.board = this.gamePlayed.getGameBoard();
        this.gameFinish = false;
        this.timer = -1;
        this.time = -1;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    

    public void setGamePlayed(Game gamePlayed) {
        this.gamePlayed = gamePlayed;
    }        

    public Game getGamePlayed() {
        return gamePlayed;
    }

    public Section[] getSectionsPerTurn() {
        return sectionsPerTurn;
    }        
    
    /**
     * This method is used to throw all dices
     * 
     * @return int[] dices
     */
    public int[] throwDices(){
        int[] dices = new int[this.gamePlayed.getNumberDices()];        
        for (int i = 0; i < this.gamePlayed.getNumberDices(); i++) {
            dices[i] = (int)(Math.random()*6)+1;
        }        
        return dices;
    }    
    
    /**
     * This method is used to get the sections by their turns
     * this is usefull later to get a section to move the chip.
     */
    public void obtainSectionsPerTurn(){
        Section[][] sections=this.board.getSectionsBoard();        
        int contTurn = 1;
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < (this.board.getQuantityRows()*this.board.getQuantityColumns()); y++) {
                for (int i = 0; i < this.board.getQuantityRows(); i++) {
                    for (int j = 0; j < this.board.getQuantityColumns(); j++) {
                        try{
                            if (sections[i][j].getTurnPosition() == contTurn) {
                                if (x != 0) {
                                    this.sectionsPerTurn[contTurn-1] = sections[i][j];
                                }                        
                                contTurn ++;
                            }                           
                        }catch(NullPointerException e){
                        
                        }                    
                    }
                }
            }
            
            if (x == 0) {
                this.sectionsPerTurn =new Section[contTurn];
                contTurn = 1;
            }            
        }        
    }
    
    /**
     * This method is used to get the section where is going to move 
     * the chip of the player in his turn
     * 
     * @param totalSteps
     * @return Section
     */
    public Section moveChipPlayer(int totalSteps){
        int turn = this.gamePlayed.getTurnPlayer();
        Player[] allPlayers= this.gamePlayed.getGamePlayers();
        int positionSection =allPlayers[turn].getPositionSection();
        int newPosition = positionSection+totalSteps;        
        if (newPosition >= this.sectionsPerTurn.length-1) {
            int passLimit = Math.floorDiv(newPosition,(this.sectionsPerTurn.length-1));
            newPosition = newPosition - (this.sectionsPerTurn.length-1)*passLimit;
            passStartSection();
        }
        allPlayers[turn].setPositionSection(newPosition);
        verifySection();
        return this.sectionsPerTurn[newPosition];
    }
    
    /**
     * This method is used to set the player 
     * who throw three times the same number of dices
     * in a section of the jail and lost turns .
     */
    public void threeTimesDoubles(){
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        Section section = this.sectionsPerTurn[allPlayers[this.gamePlayed.getTurnPlayer()].getPositionSection()];
        int stepsToMoveToJail = goJail(section, allPlayers[this.gamePlayed.getTurnPlayer()]);            
        moveChipPlayer(stepsToMoveToJail);
    }
    
    /**
     * This method is used to change to the next player
     * This method change the turn of the player 
     * is usefull later in the GameFrame to get the turn
     * and move the chip.
     * 
     */
    public void endTurnPlayer(){
        int turn = this.gamePlayed.getTurnPlayer();        
        Player[] players = this.gamePlayed.getGamePlayers();
        turn++;
        if (turn >= players.length) {
            turn = 0;
        }        
        this.gamePlayed.setTurnPlayer(turn);
        if(players[turn].isBankruptcy() == true){
            endTurnPlayer();
        }
        int turnToLostPlayer = players[turn].getQuantityTurnsLost();
        if (turnToLostPlayer > 0) {
            //The player is going to lose turn
            JOptionPane.showMessageDialog(null,"El jugador " + players[turn].getName() + " tiene que perder turno");
            players[turn].setQuantityTurnsLost(turnToLostPlayer-1);
            endTurnPlayer();
        }
    }
    
    /**
     * This method is used to set the player in turn in bankruptcy
     */
    public void bankruptcy(){
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        allPlayers[this.gamePlayed.getTurnPlayer()].setBankruptcy(true);
        allPlayers[this.gamePlayed.getTurnPlayer()].setPropertiesNull();
        JOptionPane.showMessageDialog(null,"Has sido declarado en Bancarrota");
    }    
    
    /**
     * This method is used to set a property
     * for the player in a mortgage property.
     * 
     */
    public void mortgageProperty(){
        Object[] options = {"Si", "No"};        
        JComboBox comboProperties = new JComboBox();
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        Property[] propertyPlayer = allPlayers[this.gamePlayed.getTurnPlayer()].getProperties();
        for (int i = 0; i < propertyPlayer.length; i++) {
            if (propertyPlayer[i].isCondition() == false) {
                comboProperties.addItem(propertyPlayer[i].getName());
            }
        }
        int n = JOptionPane.showOptionDialog(null, comboProperties, "Ingrese una propiedad",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[1]);
        if(n == JOptionPane.OK_OPTION){ 
            String property = (String)comboProperties.getSelectedItem();
            Property propertyMortgage = null;
            for (int i = 0; i < propertyPlayer.length; i++) {            
                if (propertyPlayer[i].getName().equalsIgnoreCase(property)) {
                    propertyMortgage = propertyPlayer[i];
                    break;
                }
            }
            if (propertyMortgage instanceof Place) {
                JOptionPane.showMessageDialog(null,"Tus casas y/o hoteles de todo el grupo de propiedades seran Vendidas");
                String nameGroup = ((Place) propertyMortgage).getGroupPlace();
                for (int i = 0; i < propertyPlayer.length; i++) {
                    if (propertyPlayer[i] instanceof Place) {
                        if (((Place)propertyPlayer[i]).getGroupPlace().equalsIgnoreCase(nameGroup)) {
                            int numberHouses = ((Place)propertyPlayer[i]).getNumberHouses();
                            int numberHotels = ((Place)propertyPlayer[i]).getNumberHotels();
                            if (numberHouses > 0) {                    
                                double money = allPlayers[this.gamePlayed.getTurnPlayer()].getAmountMoney();
                                money = money + (((Place) propertyPlayer[i]).getPriceHouse()*numberHouses);
                                allPlayers[this.gamePlayed.getTurnPlayer()].setAmountMoney(money);
                            }
                            if(numberHotels > 0){
                                double money = allPlayers[this.gamePlayed.getTurnPlayer()].getAmountMoney();
                                money = money + (((Place) propertyPlayer[i]).getPriceHotel()*numberHotels);
                                allPlayers[this.gamePlayed.getTurnPlayer()].setAmountMoney(money);
                                
                            }
                            ((Place)propertyPlayer[i]).setNumberHotels(0);
                            ((Place)propertyPlayer[i]).setNumberHouses(0);
                        }
                    }                                        
                }                                
            }            
            this.gamePlayed.mortgageProperty(propertyMortgage);            
        }
        if(n == JOptionPane.NO_OPTION){
            
        }
        if(n == JOptionPane.CLOSED_OPTION){
            
        }   
    }
    
    /**
     * This method is used to rebuy a property who has been mortgage.
     */
    public void payMortgage(){
        Object[] options = {"Si", "No"};
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        JComboBox comboProperties = new JComboBox();
        Property[] propertiesPlayer = allPlayers[this.gamePlayed.getTurnPlayer()].getProperties();
        for (int i = 0; i < propertiesPlayer.length; i++) {
            if (propertiesPlayer[i].isCondition() == true) {
                comboProperties.addItem(propertiesPlayer[i].getName());
            }            
        }
        
        int n = JOptionPane.showOptionDialog(null, comboProperties, "Escoge una propiedad a pagar la hipoteca",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[1]);
        if(n == JOptionPane.OK_OPTION){ 
            String nameProperty = (String)comboProperties.getSelectedItem();
            Property propertyRebuy = null;
            for (int i = 0; i < propertiesPlayer.length; i++) {            
                if (propertiesPlayer[i].getName().equalsIgnoreCase(nameProperty)) {
                    propertyRebuy = propertiesPlayer[i];
                    break;
                }
            }
            if (propertyRebuy != null) {
                this.gamePlayed.rebuyProperty(propertyRebuy);
            }                         
        }
        
    }
    
    /**
     * This method is used to call frame where the buy of properties
     * is done.
     * In the frame call the method in Game to build or sell Houses and Hotels
     * 
     */
    public void constructInProperty(){
        Object[] options = {"Si", "No"};        
        JComboBox comboProperties = new JComboBox();
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        Property[] propertyPlayer = allPlayers[this.gamePlayed.getTurnPlayer()].getProperties();
        for (int i = 0; i < propertyPlayer.length; i++) {
            if (propertyPlayer[i] instanceof Place && reviewAllPlacesInAGroup((Place)(propertyPlayer[i]),propertyPlayer)==true && propertyPlayer[i].isCondition()==false) {
                comboProperties.addItem(propertyPlayer[i].getName());
            }
        }
        int n = JOptionPane.showOptionDialog(null, comboProperties, "Ingrese una propiedad",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[1]);
        if(n == JOptionPane.OK_OPTION){ 
            String property = (String)comboProperties.getSelectedItem();
            Place propertyConstruct = null;
            for (int i = 0; i < propertyPlayer.length; i++) {            
                if (propertyPlayer[i].getName().equalsIgnoreCase(property)) {
                    propertyConstruct = (Place) propertyPlayer[i];
                    break;
                }
            }
            if (propertyConstruct != null) {
                buyHousesHotelsFrame buyHouseOrHotelFrame = new buyHousesHotelsFrame(this.gamePlayed , propertyConstruct);
                buyHouseOrHotelFrame.setVisible(true);
            }            
        }
    }
    
    /**
     * This method is used to review if the place have 
     * all the places of the group to set in the combobox 
     * to show
     * 
     * @param placeToBuild
     * @param propertyPlayer
     * @return 
     */
    public boolean reviewAllPlacesInAGroup(Place placeToBuild , Property[] propertyPlayer){
        String nameGroup = placeToBuild.getGroupPlace();
        String[][] groupsPlace = this.gamePlayed.getGroupsProperty();
        int quantityPlacesInGroup = 0;
        for (int i = 0; i < groupsPlace.length; i++) {
            if (groupsPlace[i][0].equalsIgnoreCase(nameGroup)) {
                quantityPlacesInGroup = Integer.parseInt(groupsPlace[i][1]);
                break;
            }            
        }
        int quantityPlacesPlayer = 0;
        for (int i = 0; i < propertyPlayer.length; i++) {
            if (propertyPlayer[i] instanceof Place) {
                if (((Place)propertyPlayer[i]).getGroupPlace().equalsIgnoreCase(nameGroup)) {
                    quantityPlacesPlayer++;
                }
            }            
        }
        if (quantityPlacesPlayer == quantityPlacesInGroup) {
            return true;
        }else{
            return false;
        }
        
    }
    
    /**
     * This method is used to call frame where is
     * the information of all the properties of the
     * player in turn.
     * 
     */
    public void seeProperties(){
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        Property[] propertyPlayer = allPlayers[this.gamePlayed.getTurnPlayer()].getProperties();
        for (int i = 0; i < propertyPlayer.length; i++) {
            if (propertyPlayer[i].isCondition() == false) {
                PropertiesFrame properties = new PropertiesFrame(propertyPlayer[i]);
                properties.setVisible(true);
            }            
        }        
    }
    
    /**
     * This method is used to give a player the Lapmoney
     * when he pass the start section.
     * 
     */
    public void passStartSection(){
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        double money = allPlayers[this.gamePlayed.getTurnPlayer()].getAmountMoney();
        double moneyPerLap = this.gamePlayed.getLapMoney();
        money += moneyPerLap;
        allPlayers[this.gamePlayed.getTurnPlayer()].setAmountMoney(money);
        JOptionPane.showMessageDialog(null,"Has pasado por el Inicio recibe: " + moneyPerLap);
    }
    
    /**
     * This method is used to verify in what section
     * is the chip of the player.
     * This method make the action of the sections or call a 
     * diferent method who is in charge to do the action
     * 
     */
    public void verifySection(){
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        Section section = this.sectionsPerTurn[allPlayers[this.gamePlayed.getTurnPlayer()].getPositionSection()];
        if (section instanceof Start) {

        }else if(section instanceof Jail){
            JOptionPane.showMessageDialog(null,"Caiste en una carcel");
        }else if(section instanceof TakeOne){
            stepInTakeOne((TakeOne)section ,allPlayers[this.gamePlayed.getTurnPlayer()]);
        }else if(section instanceof Trap){
            stepInTrap((Trap)section ,allPlayers[this.gamePlayed.getTurnPlayer()]);                        
        }else if(section instanceof Property){
            boolean sectionFound = false;
            Section[] propertiesPlayer = allPlayers[this.gamePlayed.getTurnPlayer()].getProperties();
            for (int i = 0; i < propertiesPlayer.length; i++) {
                if (section == propertiesPlayer[i]) {
                    sectionFound = true;
                    break;
                }                
            }
            if (sectionFound == false) {
                Player playerPossession = null;
                for (int i = 0; i < allPlayers.length; i++) {
                    Section[] propertiesOtherPlayer = allPlayers[i].getProperties();
                    for (int j = 0; j < propertiesOtherPlayer.length; j++) {
                        if (propertiesOtherPlayer[j] == section) {
                            playerPossession = allPlayers[i];
                            break;
                        }                        
                    }
                }
                if (playerPossession == null) {
                    //Player can buy the section
                    buyPropertySection(section,allPlayers[this.gamePlayed.getTurnPlayer()]);
                }else if(playerPossession != null){
                    //Player need to pay other player
                    if (((Property) section).isCondition() == false) {
                        payToPlayerPropertySection(section,allPlayers[this.gamePlayed.getTurnPlayer()],playerPossession);
                    }                    
                }
            }
        }
        
    }
    
    /**
     * This method is used when the chip of the player
     * pissed of a property that anyone buy it. 
     * 
     */
    public void buyPropertySection(Section section, Player playerToBuy){
        String name = ((Property)section).getName();
        double quantity = ((Property)section).getPurchasePrice();
        if (JOptionPane.showConfirmDialog(null, "Deseas Comprar la propiedad: "+name + ".Por "+ quantity, "COMPRA", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            double money = playerToBuy.getAmountMoney();
            money -= quantity;
            if (money >= 0) {
                playerToBuy.setAmountMoney(money);
                playerToBuy.setNewProperty((Property)section);
            }else{
                JOptionPane.showMessageDialog(null,"No puedes comprar esta propiedad porque no tienes suficiente dinero");
            }            
        } else {
            // no option
        }
 
    }
    
    /**
     * This method is used to pay to the player who is the property
     * where is the chip of the player in turn.
     * 
     */
    public void payToPlayerPropertySection(Section section, Player playerToPay, Player playerCollect){
        double quantity = 0;
        if (section instanceof Place) {
            Property[] propertyPlayer = playerCollect.getProperties();        
            if (section instanceof Place && reviewAllPlacesInAGroup((Place)(section),propertyPlayer)==true && ((Property)section).isCondition()==false) {
                quantity = (((Place)section).getStayCost())*2;
            }else{
                quantity = ((Place)section).getStayCost();
            }                                      
        }else if (section instanceof BasicService) {
            quantity = ((BasicService)section).getServiceCost();
            int quantityBasicServices = 0;
            Section[] propertiesPlayerCollect = playerCollect.getProperties();
            for (int j = 0; j < propertiesPlayerCollect.length; j++) {
                if (propertiesPlayerCollect[j] instanceof BasicService) {
                    quantityBasicServices++;
                }                        
            }
            quantity = quantity *quantityBasicServices;
            JOptionPane.showMessageDialog(null,"El servicio aumento a: " + String.valueOf(quantity));
            int[] dices = throwDices();
            int total = 0;
            for (int i = 0; i < dices.length; i++) {
                total = dices[i];                
            }
            quantity = quantity*total;
            JOptionPane.showMessageDialog(null,"Se lanzaron los dados y se obtuvieron " + total);            
        }else if (section instanceof Station) {            
            quantity = ((Station)section).getUsageCost();
            int quantityStations = 0;
            Section[] propertiesPlayerCollect = playerCollect.getProperties();
            for (int j = 0; j < propertiesPlayerCollect.length; j++) {
                if (propertiesPlayerCollect[j] instanceof Station) {
                    quantityStations++;
                }                        
            }
            quantity = (quantity)*(2*(quantityStations-1));
            if (quantity == 0) {
                quantity = ((Station)section).getUsageCost();
            }
        }
         playerPayMoney(quantity,playerToPay);
         playerWinMoney(quantity,playerCollect);
        JOptionPane.showMessageDialog(null,"Tienes que pagar: " + String.valueOf(quantity) + " a " + playerCollect.getName());        
    }
    
    /**
     * This method is used when the chip of the player
     * pissed of a section TakeOne
     * this method make actions for all cards exception the personalized
     * who has his own method
     * 
     * @param sectionTake
     * @param playerInSection
     */
    public void stepInTakeOne(TakeOne sectionTake , Player playerInSection){
        GroupCards groupCards = sectionTake.getGroupCards();
        Card[] allCards = groupCards.getGroupCards();
        if (allCards.length >0) {
            
        Card cardTake = allCards[0];
        groupCards.putCardInLast();
        CardFrame cardFrame = new CardFrame(cardTake);
        cardFrame.setVisible(true);
        if (cardTake instanceof CardGoJail) { 
            int stepsToMove = goJail((Section) sectionTake, playerInSection);            
            moveChipPlayer(stepsToMove);
        }else if(cardTake instanceof CardLostTurn){
            int turnsToLost = ((CardLostTurn) cardTake).getQuantityTurns();
            int turnsPlayer = playerInSection.getQuantityTurnsLost();
            turnsPlayer += turnsToLost;
            playerInSection.setQuantityTurnsLost(turnsPlayer);
        }else if(cardTake instanceof CardMoveToSection){
            Section sectionToMove = ((CardMoveToSection) cardTake).getSectionToMove();
            Section playerSection = this.sectionsPerTurn[playerInSection.getPositionSection()];
            int stepsToMove = sectionToMove.getTurnPosition()- playerSection.getTurnPosition();
            if (stepsToMove < 0) {
                stepsToMove = (this.sectionsPerTurn.length-1) + stepsToMove;                
            }       
            moveChipPlayer(stepsToMove);
        }else if(cardTake instanceof CardOutJail){
            playerInSection.setCardOutJailPlayer((CardOutJail)cardTake);
            groupCards.useCardOutJail();
        }else if(cardTake instanceof CardPayToPlayers){
            double quantityToPay = ((CardPayToPlayers) cardTake).getQuantityToPay();
            Player[] playersToPay = this.gamePlayed.getGamePlayers();
            playerPayMoney(quantityToPay*(playersToPay.length-1),playerInSection);
            for (int i = 0; i < this.gamePlayed.getGamePlayers().length; i++) {
                if (playersToPay[i] != playerInSection) {
                    playerWinMoney(quantityToPay,playersToPay[i]);
                }                
            }
        }else if(cardTake instanceof CardPenalty){
            double moneyToPay = ((CardPenalty) cardTake).getQuantityToPay();
            playerPayMoney(moneyToPay,playerInSection);
        }else if(cardTake instanceof CardPersonalized){
            cardPersonalized((CardPersonalized)cardTake ,playerInSection,sectionTake);            
        }else if(cardTake instanceof CardPrize){
            double prize = ((CardPrize) cardTake).getQuantityPrize();
            playerWinMoney(prize,playerInSection);
        }else if(cardTake instanceof CardWalk){
            int steps = ((CardWalk) cardTake).getQuantitySteps();
            moveChipPlayer(steps);
        }
        
        }                                
    }
    
    /**
     * This method is used to pay one player money
     * if the money of the player to pay is not enough
     * the player is going to bankruptcy.
     * 
     * @param moneyToPay
     * @param playerToPay
     */
    public void playerPayMoney(double moneyToPay, Player playerToPay){
        double money = playerToPay.getAmountMoney();
        money -= moneyToPay;
        if (money <= 0) {            
            playerToPay.setAmountMoney(0);
            bankruptcy();
        }else{
            playerToPay.setAmountMoney(money);
        }        
    }
    
    /**
     * This method is used to set more money to the player 
     * 
     * @param moneyToWin
     * @param playerToWin
     */
    public void playerWinMoney(double moneyToWin, Player playerToWin){
        double money = playerToWin.getAmountMoney();
        money += moneyToWin;
        playerToWin.setAmountMoney(money);
    }
    
    
    /**
     * This method is used when chip of the player in turn
     * pissed of a section Trap
     * 
     * @param sectionTrap
     * @param playerInSection
     */
    public void stepInTrap(Trap sectionTrap , Player playerInSection){        
        int typeTrap = sectionTrap.getTypeTrap();
        if (typeTrap == 1) {
            //Pay Penalty
            double moneyToPay = sectionTrap.getQuantityPay();
            playerPayMoney(moneyToPay, playerInSection);
            JOptionPane.showMessageDialog(null,"Has caido en una trampa. Tienes que pagar: " + moneyToPay);
        }else if(typeTrap == 2){
            //Change Section
            Section sectionToMove = sectionTrap.getSectionToMove();
            Section playerSection = this.sectionsPerTurn[playerInSection.getPositionSection()];
            int stepsToMove = sectionToMove.getTurnPosition()- playerSection.getTurnPosition();
            if (stepsToMove < 0) {
                stepsToMove = (this.sectionsPerTurn.length-1) + stepsToMove;                
            }
            JOptionPane.showMessageDialog(null,"Has caido en una trampa. Te moveras: " + stepsToMove + "pasos" );
            if (sectionToMove instanceof Jail) {                
                int turnsLost =((Jail) sectionToMove).getQuantityTurns();
                playerInSection.setQuantityTurnsLost(turnsLost);
            }
            moveChipPlayer(stepsToMove);            
        }else if(typeTrap == 3){
            //Lost Turns
            int quantityTurns = sectionTrap.getQuantityTurns();
            playerInSection.setQuantityTurnsLost(quantityTurns);
            JOptionPane.showMessageDialog(null,"Has caido en una trampa. Perderas " + quantityTurns +" turnos");
        }        
    }
    
    
    /**
     * This method is used to make the actions 
     * for the Personalized card
     * 
     * @param card
     * @param player
     * @param sectionTake
     */
    public void cardPersonalized(CardPersonalized card , Player player, Section sectionTake){
        double quantityWin = card.getQuantityWin();
        boolean goJail = card.isGoJail();
        int turnsLost = card.getTurnsLost();
        double payToPlayers = card.getPayToPlayers();
        double quantityPay = card.getQuantityPay();
        Section sectionToMove = card.getSectionToMove();
        int stepsToMove = card.getStepsToMove();
        if (quantityWin > 0) {
            playerWinMoney(quantityWin,player);
        }
        if(turnsLost > 0 ){
            player.setQuantityTurnsLost(turnsLost);
        }
        if(payToPlayers > 0 ){
            Player[] playersToPay = this.gamePlayed.getGamePlayers();
            playerPayMoney(payToPlayers*(playersToPay.length),player);
            for (int i = 0; i < this.gamePlayed.getGamePlayers().length; i++) {
                if (playersToPay[i] != player) {
                    playerWinMoney(payToPlayers,playersToPay[i]);
                }                
            }
        }
        if (quantityPay > 0) {
            playerPayMoney(quantityPay,player);
        }
        if (stepsToMove > 0) {
            moveChipPlayer(stepsToMove);
        }
        if (sectionToMove != null ){
            Section playerSection = this.sectionsPerTurn[player.getPositionSection()];
            int stepsToMoveSection = (sectionToMove.getTurnPosition()- playerSection.getTurnPosition());
            
            if (stepsToMoveSection < 0) {
                stepsToMoveSection = this.sectionsPerTurn.length -stepsToMoveSection;                
            }            
            moveChipPlayer(stepsToMove);
        }        
        if(goJail == true){            
            int stepsToMoveToJail = goJail(sectionTake, player);
            
            moveChipPlayer(stepsToMoveToJail);
        }
        
    }
    
    /**
     * This method is used to set a chip in a jail
     * 
     * @param sectionInPosition
     * @param player
     * @return 
     */
    public int goJail(Section sectionInPosition, Player player){
        Section sectionToMove = null;
        int stepsToMove = 0;
        
        int positionSectionPlayer = ((Section)sectionInPosition).getTurnPosition();
        
        for (int i = positionSectionPlayer; i < this.sectionsPerTurn.length; i++) {
            if (this.sectionsPerTurn[i] instanceof Jail) {
                sectionToMove = this.sectionsPerTurn[i];
                break;
            }
            if ((positionSectionPlayer-i) >=0) {
                if (this.sectionsPerTurn[positionSectionPlayer-i] instanceof Jail) {
                    sectionToMove = this.sectionsPerTurn[positionSectionPlayer-i];
                    break;
                } 
            }                               
        }
        if (sectionToMove == null) {
            for (int i = positionSectionPlayer; i >= 0; i--) {
                if (this.sectionsPerTurn[i] instanceof Jail){ 
                    sectionToMove = this.sectionsPerTurn[i];
                    break;
                }                            
            }
        }        
        try{
            stepsToMove = sectionToMove.getTurnPosition()- sectionInPosition.getTurnPosition();        
            if (stepsToMove < 0) {
                stepsToMove = (this.sectionsPerTurn.length-1) + stepsToMove;                
            }
            if (player.getCardOutJailPlayer() != null) {                
                JOptionPane.showMessageDialog(null,"Te salvaste de esta. Usaras tu carta Salir de la carcel");
                getBackCardOutJail(player.getCardOutJailPlayer());
                player.setCardOutJailPlayer(null);                
            }else{
                int turnsLost =((Jail) sectionToMove).getQuantityTurns();
                player.setQuantityTurnsLost(turnsLost);
            }
        }catch(NullPointerException e){
            
        }
        return stepsToMove;
    }
    
    /**
     * This method is used to return
     * the CardOit jail in his group
     * 
     * @param cardOutJail
     */
    public void getBackCardOutJail(Card cardOutJail){
        for (int i = 0; i < sectionsPerTurn.length; i++) {
            if (sectionsPerTurn[i] instanceof TakeOne) {
                GroupCards groupCards = ((TakeOne)sectionsPerTurn[i]).getGroupCards();
                if (groupCards.getCardsOutJailMiss() > 0) {
                    groupCards.cardOutJailBack(cardOutJail);
                    break;
                }
            }            
        }                
    }
    
    /**
     * This method is used to
     * to review if the game is over
     * 
     * @return 
     */
    public boolean reviewWinGame(){
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        reviewPlayerPropertiesBanckruptcy(allPlayers);        
        return this.gameFinish;
    }
    
    /**
     * This method is used to review if all players are in bankruptcy
     * except one who is going to win
     * 
     * @param allPlayers
     */
    public void reviewPlayerPropertiesBanckruptcy(Player[] allPlayers){
        Player playerWin = null;
        int playersBankruptcy = 0;
        for (int i = 0; i < allPlayers.length; i++) {
            if (allPlayers[i].getProperties().length == this.sectionsPerTurn.length) {
                playerWin = allPlayers[i];                
                break;
            }
            if (allPlayers[i].isBankruptcy() == true) {
                playersBankruptcy++;
            }
        }        
        if (playersBankruptcy >= (allPlayers.length-1)) {
            for (int i = 0; i < allPlayers.length; i++) {
                if (allPlayers[i].isBankruptcy() == false) {
                    playerWin = allPlayers[i];
                }
            }
        }
        if (playerWin != null) {
            playerWin.setWin(true);
            this.gameFinish = true;
            saveInRanking();
        }        
    }
    
    /**
     * This method is used to finish the game
     * and set a winner(more richess)
     * 
     */
    public void endGame(){
        Player[] allPlayers = this.gamePlayed.getGamePlayers();
        double[] richnessPlayer =new double[allPlayers.length];
        Player playerWin = allPlayers[0];
        for (int i = 0; i < allPlayers.length; i++) {
            richnessPlayer[i] = allPlayers[i].getRichness();
            if (richnessPlayer[i]> playerWin.getRichness()) {
                playerWin = allPlayers[i];
            }
        }
        playerWin.setWin(true);
        this.gameFinish = true;
        saveInRanking();
    }
    
    /**
     * This method is used to save the players who ends
     * the game in the ranking
     * 
     */
    public void saveInRanking(){
        fileManager controlLoad = new fileManager();
        Ranking ranking= new Ranking();
        ranking = controlLoad.loadRanking();
        if (ranking == null){
            ranking= new Ranking();
            ranking.setPlayersZero();
        }
        ranking.setNewPlayers(this.gamePlayed.getGamePlayers());
        controlLoad.saveRanking(ranking);
    }
    
    
}
