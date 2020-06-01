/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExceptionsGame;

/**
 *
 * @author user-ubunto
 */
public class ExceptionGame extends Exception{
    
    public ExceptionGame() {
        
    }

    public ExceptionGame(String message) {        
        super(message);
    }
}
