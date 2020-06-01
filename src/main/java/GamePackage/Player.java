/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePackage;

import CardsPackage.CardOutJail;
import SectionsPackage.BasicService;
import SectionsPackage.Place;
import SectionsPackage.Property;
import SectionsPackage.Station;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author user-ubunto
 */
public class Player implements Serializable{
    private String name;
    private int number;
    private double amountMoney;
    private Property[] properties;
    private boolean bankruptcy; 
    private String color;
    private int positionSection;
    private int quantityTurnsLost;
    private CardOutJail cardOutJailPlayer;
    private boolean win;

    public Player(String name, int number, double amountMoney, String color) {
        this.name = name;
        this.number = number;
        this.amountMoney = amountMoney;
        this.properties = new Property[0];
        this.bankruptcy = false;
        this.color = color;
        this.quantityTurnsLost = 0;
        this.cardOutJailPlayer = null;
        this.win = false;
    }        
    
    /**
     * This method is used to set a new
     * property to the player.
     */
    public void setNewProperty(Property newProperty){
        Property[] properties2 = this.properties;
        this.properties = new Property[properties2.length+1];
        for (int i = 0; i < properties2.length; i++) {
            this.properties[i] = properties2[i];
        }
        this.properties[properties2.length] = newProperty;
    }
    
    public void setPropertiesNull(){
        this.properties = new Property[0];
    }

    public String getColor() {
        return color;
    }    
    
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public double getAmountMoney() {
        return amountMoney;
    }

    public Property[] getProperties() {
        return properties;
    }

    public void setBankruptcy(boolean bankruptcy) {
        this.bankruptcy = bankruptcy;
    }        
    
    public boolean isBankruptcy() {
        return bankruptcy;
    }

    public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
    }
    
    public void setColor(String color) {
        this.color = color;
    }

    public int getPositionSection() {
        return positionSection;
    }

    public void setPositionSection(int positionSection) {
        this.positionSection = positionSection;
    }

    public void setQuantityTurnsLost(int quantityTurnsLost) {
        this.quantityTurnsLost = quantityTurnsLost;
    }

    public int getQuantityTurnsLost() {
        return quantityTurnsLost;
    }

    public CardOutJail getCardOutJailPlayer() {
        return cardOutJailPlayer;
    }

    public void setCardOutJailPlayer(CardOutJail cardOutJailPlayer) {
        this.cardOutJailPlayer = cardOutJailPlayer;
    }

    /**
     * This method is used to get the richness
     * of the player.
     */
    public double getRichness(){
        double richness = this.amountMoney;
        for (int i = 0; i < this.properties.length; i++) {
            richness += (this.properties[i].getPurchasePrice());
            if (this.properties[i] instanceof Place) {
                richness += ((Place)this.properties[i]).getPriceHousesHotels();
            }
        }
        return richness;
    }    

    public void setWin(boolean win) {
        this.win = win;
    }       

    public boolean isWin() {
        return win;
    }
    
    
}
