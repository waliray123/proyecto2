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
public class CardLostTurn extends Card{
    int quantityTurns;
    public CardLostTurn(String description, String name, int copies, int quantityTurns) {
        super(description, name, copies);
        this.quantityTurns = quantityTurns;
    }

    public int getQuantityTurns() {
        return quantityTurns;
    }
    
    
    
}
