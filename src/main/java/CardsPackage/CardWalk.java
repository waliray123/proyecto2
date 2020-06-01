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
public class CardWalk extends Card{
    int quantitySteps;
    
    public CardWalk(String description, String name, int copies,int quantitySteps) {
        super(description, name, copies);
        this.quantitySteps = quantitySteps;
    }

    public int getQuantitySteps() {
        return quantitySteps;
    }
    
    
        
    
}
