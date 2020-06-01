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
public class CardPrize extends Card{
    double quantityPrize;
    
    public CardPrize(String description, String name, int copies,double quantityPrize) {
        super(description, name, copies);
        this.quantityPrize = quantityPrize;
    }

    public double getQuantityPrize() {
        return quantityPrize;
    }
    
    
    
}
