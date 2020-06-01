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
public class Trap extends Section{
    private int typeTrap;
    private int quantityTurns;
    private double quantityPay;
    private Section sectionToMove;

    public Trap(int typeTrap, int quantityTurns, double quantityPay, Section sectionToMove, int rowposition, int columnPosition) {
        super(rowposition, columnPosition);
        this.typeTrap = typeTrap;
        this.quantityTurns = quantityTurns;
        this.quantityPay = quantityPay;
        this.sectionToMove = sectionToMove;
    }

    public int getTypeTrap() {
        return typeTrap;
    }

    public int getQuantityTurns() {
        return quantityTurns;
    }

    public double getQuantityPay() {
        return quantityPay;
    }

    public Section getSectionToMove() {
        return sectionToMove;
    }
    
    
    
    
}
