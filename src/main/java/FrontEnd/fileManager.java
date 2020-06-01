/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import ExceptionsGame.ExceptionGame;
import GamePackage.Game;
import GamePackage.controlGame;
import RankingPackage.Ranking;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author user-ubunto
 */
public class fileManager {
    
    public void saveBoard(String path, Game game) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(path));
            salida.writeObject(game);
            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game readBoard(String path)throws ExceptionGame{
        Game game;
        try {
            ObjectInputStream flujoSalida = new ObjectInputStream(new FileInputStream(path));
            Object salida = flujoSalida.readObject();
            game = (Game) salida;
            return game;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionGame("Error al cargar el tablero");
        }
    }
    
    public Ranking loadRanking(){
        Ranking ranking = null;
        try{
            FileInputStream fileInput = new FileInputStream("Ranking.bin");
            ObjectInputStream inputObject = new ObjectInputStream(fileInput);
            if (inputObject != null) {
                ranking = (Ranking) inputObject.readObject();
                inputObject.close();
            }
            return ranking;
        }catch(Exception e){
                        
        }
        return ranking;
    }
    
    public void saveRanking(Ranking ranking){
        try {
            FileOutputStream fileOut = new FileOutputStream("Ranking.bin");
            ObjectOutputStream outObject = new ObjectOutputStream(fileOut);
            if (outObject != null) {
                outObject.writeObject(ranking);
                outObject.close();
            }            
        } catch (IOException e) {
            e.printStackTrace();
        }                
        
    }
        
    public void saveControlGame(String path, controlGame game) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(path));
            salida.writeObject(game);
            salida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public controlGame loadControlGame(String path)throws ExceptionGame{
        controlGame game;
        try {
            ObjectInputStream flujoSalida = new ObjectInputStream(new FileInputStream(path));
            Object salida = flujoSalida.readObject();
            game = (controlGame) salida;
            return game;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionGame("Error al cargar el juego");
        }
        
    }
    
}
