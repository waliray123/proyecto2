/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardsPackage;

import SectionsPackage.Section;

/**
 *
 * @author user-ubunto
 */
public class CardPersonalized extends Card{
    double quantityWin;
    boolean goJail;
    int turnsLost;
    double payToPlayers;
    double quantityPay;
    Section sectionToMove;
    int stepsToMove;

    public CardPersonalized(double quantityWin, boolean goJail, int turnsLost, double payToPlayers, double quantityPay, Section sectionToMove, int stepsToMove, String description, String name, int copies) {
        super(description, name, copies);
        this.quantityWin = quantityWin;
        this.goJail = goJail;
        this.turnsLost = turnsLost;
        this.payToPlayers = payToPlayers;
        this.quantityPay = quantityPay;
        this.sectionToMove = sectionToMove;
        this.stepsToMove = stepsToMove;
    }

    public double getQuantityWin() {
        return quantityWin;
    }

    public boolean isGoJail() {
        return goJail;
    }

    public int getTurnsLost() {
        return turnsLost;
    }

    public double getPayToPlayers() {
        return payToPlayers;
    }

    public double getQuantityPay() {
        return quantityPay;
    }

    public Section getSectionToMove() {
        return sectionToMove;
    }

    public int getStepsToMove() {
        return stepsToMove;
    }
    
    
    
    
}
