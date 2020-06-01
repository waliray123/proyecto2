/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SectionsPackage;

import CardsPackage.GroupCards;

/**
 *
 * @author user-ubunto
 */
public class TakeOne extends Section{
    private GroupCards groupCards;

    public TakeOne(GroupCards groupCards, int rowposition, int columnPosition) {
        super(rowposition, columnPosition);
        this.groupCards = groupCards;
    }

    public GroupCards getGroupCards() {
        return groupCards;
    }
    
    
    
}
