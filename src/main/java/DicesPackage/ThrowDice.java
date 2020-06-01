/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DicesPackage;

/**
 *
 * @author user-ubunto
 */
public class ThrowDice extends Thread{
    private int quantity;

    public ThrowDice(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public void run(){
        try{            
            while(this.quantity == 0){
                this.quantity = (int)(Math.random()*6)+1;
                this.quantity = (int)(Math.random()*6)+1;                                
            }
        }catch(Exception e){
            
        }
    }
    
    
}
