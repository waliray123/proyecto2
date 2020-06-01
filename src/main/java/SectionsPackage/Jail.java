/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SectionsPackage;

/**
 *
 * @author user-ubunto
 */
public class Jail extends Section{
    public int quantityTurns;
    
    public Jail(int rowposition, int columnPosition, int quantityTurns) {
        super(rowposition, columnPosition);
        this.quantityTurns = quantityTurns;
    }

    public int getQuantityTurns() {
        return quantityTurns;
    }
        
    
    
}
