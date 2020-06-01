/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardsPackage;

import java.io.Serializable;

/**
 *
 * @author user-ubunto
 */
public class GroupCards implements Serializable{
    private Card[] groupCards;
    private int cardsOutJailMiss;

    /**
     * This method is the constructor.
     */
    public GroupCards(Card[] groupCards) {
        this.groupCards = groupCards;
        this.cardsOutJailMiss = 0;
    }

    public Card[] getGroupCards() {
        return groupCards;
    }
    
    /**
     * This method is used to set first card in the last position.
     */
    public void putCardInLast(){
        Card cardInFirst = this.groupCards[0];
        for (int i = 1; i < groupCards.length; i++) {
            this.groupCards[i-1] = this.groupCards[i];            
        }
        this.groupCards[this.groupCards.length-1] = cardInFirst;
    }
    
    /**
     * This method is used to go of the card OutJail.
     */
    public void useCardOutJail(){
        Card[] groupCards2 = this.groupCards;
        this.groupCards = new Card[groupCards2.length - 1]; 
        for (int i = 0; i < this.groupCards.length; i++) {
            this.groupCards[i] = groupCards2[i];            
        }
        this.cardsOutJailMiss ++;
    }
    
    /**
     * This method is used to get back the card out of jail.
     */
    public void cardOutJailBack(Card cardOutJail){
        Card[] groupCards2 = this.groupCards;
        this.groupCards = new Card[groupCards2.length +1]; 
        for (int i = 0; i < groupCards2.length; i++) {
            this.groupCards[i] = groupCards2[i];            
        }
        this.groupCards[groupCards2.length] = cardOutJail;
        this.cardsOutJailMiss --;
    }

    public int getCardsOutJailMiss() {
        return cardsOutJailMiss;
    }
        
}
