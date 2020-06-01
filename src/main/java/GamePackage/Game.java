/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePackage;

import BoardPackage.Board;
import SectionsPackage.Neutro;
import SectionsPackage.Place;
import SectionsPackage.Property;
import SectionsPackage.Section;
import SectionsPackage.Start;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author user-ubunto
 */
public class Game implements Serializable{
    private int turnPlayer;
    private int numberDices;
    private Player[] gamePlayers;
    private String[][] groupsProperty;
    private double initialMoney;
    private double lapMoney;
    private int limitHouses;
    private int limitHotels;
    private double percentageMortgage;
    private Board gameBoard;
    private String nameGame;

    /**
     * Cronstructor Method
     * @param numberDices
     * @param initialMoney
     * @param lapMoney
     * @param gameBoard
     * @param limitHotels
     * @param limitHouses
     * @param percentageMortgage
     * @param nameGame
     */
    public Game(int numberDices, double initialMoney, double lapMoney, int limitHouses, int limitHotels, int percentageMortgage,Board gameBoard, String nameGame) {
        this.numberDices = numberDices;
        this.initialMoney = initialMoney;
        this.lapMoney = lapMoney;
        this.limitHouses = limitHouses;
        this.limitHotels = limitHotels;
        this.percentageMortgage = (percentageMortgage*1.00)/100;  
        this.gameBoard = gameBoard;
        this.nameGame = nameGame;
        this.groupsProperty = new String[0][0];
    }

        
    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void setNumberDices(int numberDices) {
        this.numberDices = numberDices;
    }

    public void setInitialMoney(double initialMoney) {
        this.initialMoney = initialMoney;
    }

    public void setLapMoney(double lapMoney) {
        this.lapMoney = lapMoney;
    }

    public void setLimitHouses(int limitHouses) {
        this.limitHouses = limitHouses;
    }

    public void setLimitHotels(int limitHotels) {
        this.limitHotels = limitHotels;
    }

    public void setPercentageMortgage(double percentageMortgage) {
        this.percentageMortgage = percentageMortgage;
    }

    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }   
    
    public String[][] getGroupsProperty() {
        return groupsProperty;
    }
    
    /**
     * This method is used to set a new property group in the vector
     * @param nameGroup
     */
    public void setNewGroupProperty(String nameGroup){
        String[][] groups2 = this.groupsProperty;
        this.groupsProperty = new String[groups2.length+1][2];
        for (int i = 0; i < groups2.length; i++) {
            for (int j = 0; j <= 1; j++) {
                this.groupsProperty[i][j] = groups2[i][j];
            }            
        }
        this.groupsProperty[groups2.length][0] = nameGroup;
        this.groupsProperty[groups2.length][1] = "";
    }
    
    /**
     * This method is used to add in the group of places one place
     * @param name
     */
    public void addPlaceToGroup(String name){
        int row = 0;
        for (int i = 0; i < this.groupsProperty.length; i++) {
            if (this.groupsProperty[i][0].equalsIgnoreCase(name)) {
                row = i;
            }            
        }
        if (this.groupsProperty[row][1].equalsIgnoreCase("")) {
            this.groupsProperty[row][1] = "1";
        }else{
            int quantityPlaces = Integer.parseInt(this.groupsProperty[row][1]);
            quantityPlaces++;
            this.groupsProperty[row][1] = String.valueOf(quantityPlaces);
        }
    }
    
    /**
     * This method is used to buy houses and hotels for an specific place
     * @param placeBuild
     * @param quantity
     * @param type
     */
    public void buildHousesHotels(Place placeBuild,int quantity, String type){
        if (type.equalsIgnoreCase("house")) {
            if ((placeBuild.getNumberHouses() < this.limitHouses)) {
                if ((quantity+placeBuild.getNumberHouses()<= this.limitHouses)) {
                    double money = this.gamePlayers[this.turnPlayer].getAmountMoney();
                    money -= (placeBuild.getPriceHouse()*quantity);
                    if (money >=0) {
                        this.gamePlayers[this.turnPlayer].setAmountMoney(money);
                        placeBuild.setNumberHouses(quantity+placeBuild.getNumberHouses());
                        JOptionPane.showMessageDialog(null,"Has comprado: "+ String.valueOf(quantity) + " casas");
                    }else{
                        JOptionPane.showMessageDialog(null,"No puedes comprar porque no tienes suficiente dinero");
                    }                    
                }else{
                    //cannot build beacuse the quantity you want exced the limit
                    JOptionPane.showMessageDialog(null,"No puedes Construir porque la cantidad excede el limite");
                }
            }else{
                // cannot build because you are in the limit
                JOptionPane.showMessageDialog(null,"No puedes Construir porque estas al limite");
            }
        }else if(type.equalsIgnoreCase("hotel")){
            if ((placeBuild.getNumberHotels() < this.limitHotels)) {
                if ((quantity+placeBuild.getNumberHotels()<= this.limitHotels)) {
                    buyHotelsReview(placeBuild);
                    double money = this.gamePlayers[this.turnPlayer].getAmountMoney();
                    money -= (placeBuild.getPriceHotel()*quantity);
                    if (money >= 0) {
                        this.gamePlayers[this.turnPlayer].setAmountMoney(money);
                        placeBuild.setNumberHotels(quantity+placeBuild.getNumberHotels());
                        JOptionPane.showMessageDialog(null,"Has comprado: "+ String.valueOf(quantity) + " hoteles");
                    }else{
                        JOptionPane.showMessageDialog(null,"No puedes comprar porque no tienes suficiente dinero");
                    }                    
                }else{
                    //cannot build beacuse the quantity you want exced the limit
                    JOptionPane.showMessageDialog(null,"No puedes Construir porque la cantidad excede el limite");
                }
            }else{
                // cannot build because you are in the limit
                JOptionPane.showMessageDialog(null,"No puedes Construir porque estas al limite");
            }
        }
    }
    
    
    /**
     * This method is used to buy houses and hotels for an specific place
     * @param placeBuild
     * @param quantity
     * @param type
     */
    public void sellHotelsHouses(Place placeBuild,int quantity, String type){
        if (type.equalsIgnoreCase("house")) {            
            if ((placeBuild.getNumberHouses() - quantity) >= 0) {
                double money = this.gamePlayers[this.turnPlayer].getAmountMoney();
                money += (placeBuild.getPriceHouse()*quantity);
                this.gamePlayers[this.turnPlayer].setAmountMoney(money);
                placeBuild.setNumberHouses(placeBuild.getNumberHouses()-quantity);
                JOptionPane.showMessageDialog(null,"Has vendido: "+ String.valueOf(quantity) + " casas");
            }else{
                //cannot sell beacuse the quantity you want exced the limit
                JOptionPane.showMessageDialog(null,"No puedes vender porque la cantidad excede la cantidad actual");
            }            
        }else if(type.equalsIgnoreCase("hotel")){
            if ((placeBuild.getNumberHotels()-quantity)>= 0) {
                double money = this.gamePlayers[this.turnPlayer].getAmountMoney();
                money += (placeBuild.getPriceHotel()*quantity);
                this.gamePlayers[this.turnPlayer].setAmountMoney(money);
                placeBuild.setNumberHotels(placeBuild.getNumberHotels()- quantity);
                JOptionPane.showMessageDialog(null,"Has vendido: "+ String.valueOf(quantity) + " hoteles");
            }else{
                //cannot sell beacuse the quantity you want exced the limit
                JOptionPane.showMessageDialog(null,"No puedes vender porque la cantidad excede la cantidad actual");
            }
        }
    }
    
    /**
     * This method is used to review if the place where going to build
     * a hotel doesnt have houses
     * 
     * @param placeBuild
     */
    public void buyHotelsReview(Place placeBuild){
        int quantityHouses = placeBuild.getNumberHouses();
        if (quantityHouses > 0) {
            JOptionPane.showMessageDialog(null,"Al comprar hoteles tus casas estan siendo vendidas");
            double moneyOfHouses = placeBuild.getPriceHouse()*quantityHouses;
            double money = (this.gamePlayers[this.turnPlayer].getAmountMoney()+moneyOfHouses);
            placeBuild.setNumberHouses(0);
            this.gamePlayers[turnPlayer].setAmountMoney(money);
            JOptionPane.showMessageDialog(null,"Se han vendido tus casa y has obtenido: "+ moneyOfHouses);
        }        
    }
    
    /**
     * This method is used to mortgage a property
     * 
     * @param propertyMortgage
     */
    public void mortgageProperty(Property propertyMortgage){
        if(propertyMortgage.isCondition() == false){
           propertyMortgage.setCondition(true);
           double money = this.gamePlayers[turnPlayer].getAmountMoney();
           money += propertyMortgage.getPurchasePrice();
           this.gamePlayers[turnPlayer].setAmountMoney(money);
           JOptionPane.showMessageDialog(null,"Tu propiedad ha sido hipotecada y has obtenido: " + String.valueOf(propertyMortgage.getPurchasePrice()));
        }else{
            JOptionPane.showMessageDialog(null,"Esta propiedad ya esta hipotecada");
        }
    }
    
    /**
     * This method is used to buy a propety who has been mortgage
     * 
     * @param propertyReBuy
     */
    public void rebuyProperty(Property propertyReBuy){
        double money = this.gamePlayers[turnPlayer].getAmountMoney();
        money -= (propertyReBuy.getMortgagePrice() + propertyReBuy.getPurchasePrice());
        if (money < 0) {
            JOptionPane.showMessageDialog(null,"No puedes comprar una propiedad porque no tienes suficiente dinero");
        }else{
            this.gamePlayers[turnPlayer].setAmountMoney(money);
            propertyReBuy.setCondition(false);
            JOptionPane.showMessageDialog(null,"Tu propiedad ha sido devuelta");
        }                
    }
    
    public Property[] seeProperties(){
        return gamePlayers[turnPlayer].getProperties();
    }

    
    public void throwDice(){
        int[] quantity = new int[0];
        boolean repeatThrow = false;
        int total = 0;
        for (int i = 0; i < this.numberDices; i++) {
            quantity[i]=(int)(Math.random()*7+1);
            total += quantity[i];
        }        
        try{
            if(quantity[0]==quantity[1]){
                repeatThrow = true;
            }else if(quantity[0]==quantity[2]){
                repeatThrow = true;
            }else if(quantity[1]==quantity[2]){
                repeatThrow = true;
            }            
        }catch(NullPointerException e){
            
        }     
    }

    public Player[] getGamePlayers() {
        return gamePlayers;
    }

    public double getPercentageMortgage() {
        return percentageMortgage;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public double getInitialMoney() {
        return initialMoney;
    }

    public int getNumberDices() {
        return numberDices;
    }

    public String getNameGame() {
        return nameGame;
    }

    public int getTurnPlayer() {
        return turnPlayer;
    }

    public double getLapMoney() {
        return lapMoney;
    }

    public int getLimitHouses() {
        return limitHouses;
    }

    public int getLimitHotels() {
        return limitHotels;
    }

    public void setTurnPlayer(int turnPlayer) {
        this.turnPlayer = turnPlayer;
    }
        
    
    /**
     * This method is used to set all sections in the map
     * the turn equals to zero
     * 
     */
    public void setTurnSectionsEmpty(){
        Section[][] sectionsCreated = this.gameBoard.getSectionsBoard();
        for (int i = 0; i < this.gameBoard.getQuantityRows(); i++) {
            for (int j = 0; j < this.gameBoard.getQuantityRows(); j++) {
                try{
                    sectionsCreated[i][j].setTurnPosition(-1);                    
                }catch(NullPointerException e){
                    
                }                
            }            
        }
    }
    
    /**
     * This method is used to set all sections in the map
     * where is going to pass the chip a turn
     * 
     */
    public boolean reviewMap(){
        boolean isReady = false;
        Section[][] sectionsCreated = this.gameBoard.getSectionsBoard();
        Section startSection = null;
        int turnPosition = 1;
        for (int i = 0; i < this.gameBoard.getQuantityRows(); i++) {
            for (int j = 0; j < this.gameBoard.getQuantityColumns(); j++) {                
                try{
                    if (sectionsCreated[i][j] instanceof Start) {
                        startSection = sectionsCreated[i][j];
                        startSection.setTurnPosition(turnPosition);
                    }
                }catch(NullPointerException e){
                    
                }
            }            
        }       
        if(startSection != null){
            isReady = true;
            Section sectionReview = startSection;        
            int quantitySections = (this.gameBoard.getQuantityRows()*this.gameBoard.getQuantityColumns());
            for (int i = 0; i < quantitySections-1; i++) {
                sectionReview = reviewNearbySections(sectionReview,sectionsCreated ,turnPosition);                                
                if (sectionReview == null) {
                    isReady = false;
                    break;
                }
                if (sectionReview instanceof Start) {
                    break;
                }
                turnPosition++;
                sectionReview.setTurnPosition(turnPosition);                
            }
        }
        return isReady;
    }
    
    
    /**
     * This method is used to review what section
     * is going to be the next who set the turn
     * @param sectionReview
     * @param sectionsCreated
     * @param turnPosition
     * @return Section
     * @return null
     * 
     */
    private Section reviewNearbySections(Section sectionReview,Section[][] sectionsCreated ,int turnPosition){
        int row = sectionReview.getRowposition();
        int column = sectionReview.getColumnPosition();               
        try{
            if ((column+1) <= this.gameBoard.getQuantityColumns()-1 && sectionsCreated[row][column+1] != null) {                
                if (sectionsCreated[row][column+1].getTurnPosition() != turnPosition-1 && sectionsCreated[row][column+1].getTurnPosition() == -1) {                    
                    return sectionsCreated[row][column+1];
                }else if(sectionsCreated[row][column+1] instanceof Start == true){
                    return sectionsCreated[row][column+1];
                }               
            }
            if ((row+1) <= this.gameBoard.getQuantityRows()-1 && sectionsCreated[row+1][column] != null) {
                if (sectionsCreated[row+1][column].getTurnPosition() != turnPosition-1 && sectionsCreated[row+1][column].getTurnPosition() == -1) {
                    return sectionsCreated[row+1][column];
                }else if(sectionsCreated[row+1][column] instanceof Start == true){
                    return sectionsCreated[row+1][column];
                }
            }
            
            if ((column-1) >= 0 && sectionsCreated[row][column-1] != null) {
                if (sectionsCreated[row][column-1].getTurnPosition() != turnPosition-1 && sectionsCreated[row][column-1].getTurnPosition() == -1) {
                    return sectionsCreated[row][column-1];
                }else if(sectionsCreated[row][column-1] instanceof Start == true){
                    return sectionsCreated[row][column-1];
                }
            }
            
            if ((row-1) >= 0 && sectionsCreated[row-1][column] != null) {
                if (sectionsCreated[row-1][column].getTurnPosition() != turnPosition-1 && sectionsCreated[row-1][column].getTurnPosition() == -1) {
                    return sectionsCreated[row-1][column];
                }else if(sectionsCreated[row-1][column] instanceof Start == true){
                    return sectionsCreated[row-1][column];
                }
            }
            
        }catch(ArrayIndexOutOfBoundsException e){
            
        }
        return null;
    }

    public void setGamePlayers(Player[] gamePlayers) {
        this.gamePlayers = gamePlayers;
    }
}
