/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BoardPackage;

import CardsPackage.Card;
import CardsPackage.GroupCards;
import SectionsPackage.Section;
import java.io.Serializable;

/**
 *
 * @author user-ubunto
 */
public class Board implements Serializable{
    private Section[][] sectionsBoard;
    private GroupCards[] groupsCards;
    private int quantityRows;
    private int quantityColumns;

    public Board(int rows,int columns) {
        this.sectionsBoard = new Section[rows][columns];
        this.groupsCards = new GroupCards[0];
        this.quantityRows = rows;
        this.quantityColumns = columns;
    }

    public Section[][] getSectionsBoard() {
        return sectionsBoard;        
    }

    public GroupCards[] getGroupsCards() {
        return groupsCards;
    }

    public void setSectionsBoard(Section[][] sectionsBoard) {
        this.sectionsBoard = sectionsBoard;
    }

    public void setGroupsCards(GroupCards groupsCardsInsert) {
        GroupCards[] groupCards2 = this.groupsCards;
        this.groupsCards = new GroupCards[groupCards2.length+1];
        for (int i = 0; i < groupCards2.length; i++) {
            this.groupsCards[i] = groupCards2[i];            
        }
        this.groupsCards[groupCards2.length] = groupsCardsInsert;
    }

    public int getQuantityRows() {
        return quantityRows;
    }

    public int getQuantityColumns() {
        return quantityColumns;
    }
    
    
    
}
