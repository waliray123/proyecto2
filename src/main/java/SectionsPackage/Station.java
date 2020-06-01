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
public class Station extends Property{
    private double usageCost;

    public Station(double usageCost, String name, String owner, double purchasePrice, double mortgagePrice, boolean condition, int rowposition, int columnPosition) {
        super(name, owner, purchasePrice, mortgagePrice, condition, rowposition, columnPosition);
        this.usageCost = usageCost;
    }

     

    

    

    public double getUsageCost() {
        return usageCost;
    }           
    
}
