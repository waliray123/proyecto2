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
public class BasicService extends Property{
    private double serviceCost;

    public BasicService(double serviceCost, String name, String owner, double purchasePrice, double mortgagePrice, boolean condition, int rowposition, int columnPosition) {
        super(name, owner, purchasePrice, mortgagePrice, condition, rowposition, columnPosition);
        this.serviceCost = serviceCost;
    }

    

    

    public double getServiceCost() {
        return serviceCost;
    }        
    
    
}
