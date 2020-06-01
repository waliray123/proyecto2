/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SectionsPackage;

import java.io.Serializable;

/**
 *
 * @author user-ubunto
 */
public class Section implements Serializable{
    private int rowposition;
    private int columnPosition;
    private int turnPosition;

    public Section(int rowposition, int columnPosition) {
        this.rowposition = rowposition;
        this.columnPosition = columnPosition;
        this.turnPosition = -1;
    }

    public int getRowposition() {
        return rowposition;
    }

    public int getColumnPosition() {
        return columnPosition;
    }

    public int getTurnPosition() {
        return turnPosition;
    }

    public void setTurnPosition(int turnPosition) {
        this.turnPosition = turnPosition;
    }        
    
}
