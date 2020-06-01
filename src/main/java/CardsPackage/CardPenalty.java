/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardsPackage;

/**
 *
 * @author user-ubunto
 */
public class CardPenalty extends Card{
    double quantityToPay;
    public CardPenalty(String description, String name, int copies,double quantityToPay) {
        super(description, name, copies);
        this.quantityToPay = quantityToPay;
    }

    public double getQuantityToPay() {
        return quantityToPay;
    }
    
    
    
}
