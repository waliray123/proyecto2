/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardsPackage;

import SectionsPackage.Section;

/**
 *
 * @author user-ubunto
 */
public class CardMoveToSection extends Card{
    Section sectionToMove;
    
    public CardMoveToSection(String description, String name, int copies, Section sectionToMove) {
        super(description, name, copies);
        this.sectionToMove = sectionToMove;
    }

    public Section getSectionToMove() {
        return sectionToMove;
    }
        
    
}
