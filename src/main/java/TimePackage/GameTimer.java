/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimePackage;

import javax.swing.JLabel;

/**
 *
 * @author user-ubunto
 */
public class GameTimer extends Thread{
    private JLabel labelTime;
    private boolean stop;
    
    public GameTimer(JLabel labelTime) {
        this.labelTime = labelTime;
        this.stop=false;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
        
    
    @Override
    public void run(){
        try{
            //init the timer
            int time = Integer.parseInt(labelTime.getText());
            while(this.stop == false && time != 0){
                //wait one sec to change
                Thread.sleep(1000);
                time--;                
                this.labelTime.setText(String.valueOf(time));
            }
        }catch(Exception e){
            
        }
    }
    
}
