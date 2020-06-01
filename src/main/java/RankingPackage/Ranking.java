/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RankingPackage;

import GamePackage.Player;
import java.io.Serializable;
import java.text.Collator;

/**
 *
 * @author user-ubunto
 */
public class Ranking implements Serializable{
    private Player[] players;
    
    

    public Player[] getPlayers() {
        return players;
    }
    
    /**
     * This method is used to set new players to ranking.
     */
    public void setNewPlayers(Player[] playersInsert) {
        Player[] players2 = this.players;
        this.players =new Player[players2.length + playersInsert.length];
        for (int i = 0; i < (players2.length); i++) {
              this.players[i] = players2[i];
        }
        for (int i = (players2.length) ; i < (players2.length + playersInsert.length); i++) {
            this.players[i] = playersInsert[i-(players2.length)];
        }        
    }
    
    /**
     * This method is used to set the ranking to zero.
     */
    public void setPlayersZero(){
        this.players =new Player[0];
    }
    
    //each sort is made with the selection algorithm.
    
    public Player[] sortByTypePlayer(String type){
        Player[] playersTypePlayer =new Player[0];
        if(type.equalsIgnoreCase("All")){
            return this.players;
        }else{
            for (int i = 0; i < this.players.length; i++) {
                if (type.equalsIgnoreCase("Win")) {
                    if (this.players[i].isWin() == true) {
                        playersTypePlayer = redimPlayers(playersTypePlayer,this.players[i]);
                    }
                }else if (type.equalsIgnoreCase("Lose")) {
                    if (this.players[i].isWin() == false) {
                        playersTypePlayer = redimPlayers(playersTypePlayer,this.players[i]);
                    }
                }
            
            }
        }        
        return playersTypePlayer;        
    }
    
    /**
     * This method is used to resize the vector
     * of the players in the ranking when
     * set new players.
     */
    public Player[] redimPlayers(Player[] players, Player playerAdd){
        Player[] players2 = players;
        players = new Player[players2.length + 1];
        for (int i = 0; i < players2.length; i++) {
            players[i] = players2[i];            
        }
        players[players2.length] = playerAdd;
        return players;
    }    
    
    public Player[] sortBy(String typePlayer, String typeOrder,String typeSort){        
        Player[] playersSortByRich = sortByTypePlayer(typePlayer);        
        int lenghtPlayers = playersSortByRich.length;
        Collator comparator = Collator.getInstance();
        comparator.setStrength(Collator.TERTIARY);
        for (int i = 0; i < lenghtPlayers; i++) {
            int minVal = i;
            for (int j = minVal+1; j < lenghtPlayers; j++) {
                double firstPlayer = 0.01;
                double secondPlayer = 0.00;
                if (typeSort.equalsIgnoreCase("Name")) {
                    int compare = comparator.compare(playersSortByRich[j].getName(), playersSortByRich[minVal].getName());
                    secondPlayer = (compare)*1.00;
                    //revisar
                }else if(typeSort.equalsIgnoreCase("Rich")){
                    firstPlayer = playersSortByRich[j].getRichness();
                    secondPlayer = playersSortByRich[minVal].getRichness();
                }
                if (typeOrder.equalsIgnoreCase("Up")) {
                    if (firstPlayer < secondPlayer) {
                        minVal = j;
                    }
                }else if(typeOrder.equalsIgnoreCase("Down")){
                    if (firstPlayer > secondPlayer) {
                        minVal = j;
                    }
                }                
            }
            Player tempPlayer = playersSortByRich[i];
            playersSortByRich[i] = playersSortByRich[minVal];
            playersSortByRich[minVal] = tempPlayer;
        }
        return playersSortByRich;
    }
    
}
