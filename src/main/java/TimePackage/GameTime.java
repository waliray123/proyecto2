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
public class GameTime extends Thread{
    private JLabel labelTime;
    private boolean stopTime;
    
    public GameTime(JLabel labelTime) {
        this.labelTime = labelTime;
        this.stopTime = false;
    }

    public void setStopTime(boolean stopTime) {
        this.stopTime = stopTime;
    }
    
    
    @Override
    public void run(){
        try{
            //init the time
            int time = Integer.parseInt(labelTime.getText());
            while(this.stopTime == false && time != -1){
                //wait one sec to change
                Thread.sleep(1000);
                time++;                
                this.labelTime.setText(String.valueOf(time));
            }
        }catch(Exception e){
            
        }
    }
    
    
    
}
