/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author user-ubunto
 */
public class PlayerChip {
    
    private JLabel chip;
    private ImageIcon imageIcon;

    public PlayerChip(String color, String number) {
        this.chip = new JLabel();
        this.chip.setText(number);
        this.chip.setOpaque(true);
        setIconChip(color);        
    }

    public JLabel getChip() {
        return chip;
    }
        
    /**
     * This method is used to set the color of the chip.
     */
    public void setIconChip(String color){ 
        if (color.equalsIgnoreCase("Amarillo")) {
            this.chip.setBackground(Color.YELLOW);                      
        }else if(color.equalsIgnoreCase("Rojo")){
            this.chip.setBackground(Color.RED);
        }else if(color.equalsIgnoreCase("Azul")){
            this.chip.setBackground(Color.BLUE);
        }else if(color.equalsIgnoreCase("Verde")){
            this.chip.setBackground(Color.GREEN);
        }else if(color.equalsIgnoreCase("Naranja")){
            this.chip.setBackground(Color.ORANGE);
        }else if(color.equalsIgnoreCase("Rosado")){
            this.chip.setBackground(Color.PINK);
        }
    }
    
    public void setBoundsChip(int row, int column){
        this.chip.setBounds(row*75, column*105, 20, 20);
    }
    
    
}
