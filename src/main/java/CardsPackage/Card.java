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
public class Card implements Serializable{
    private String name;
    private int numberCopies;
    private String description;

    public Card(String description,String name,int copies) {
        this.description = description;
        this.name = name;
        this.numberCopies = copies;
    }
    
    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
    
    
}
