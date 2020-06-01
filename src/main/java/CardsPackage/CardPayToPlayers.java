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
public class CardPayToPlayers extends Card{
    double quantityToPay;
    public CardPayToPlayers(String description, String name, int copies,double quantityToPay) {
        super(description, name, copies);
        this.quantityToPay = quantityToPay;
    }

    public double getQuantityToPay() {
        return quantityToPay;
    }

    
    
    
}
