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
public class Property extends Section{
    private String name;
    private String owner;
    private double purchasePrice;
    private double mortgagePrice;
    private boolean condition;

    public Property(String name, String owner, double purchasePrice, double mortgagePrice, boolean condition, int rowposition, int columnPosition) {
        super(rowposition, columnPosition);
        this.name = name;
        this.owner = owner;
        this.purchasePrice = purchasePrice;
        this.mortgagePrice = this.purchasePrice*(mortgagePrice);
        this.condition = condition;
    }

    

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getMortgagePrice() {
        return mortgagePrice;
    }

    public boolean isCondition() {
        return condition;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }
        

    
}
