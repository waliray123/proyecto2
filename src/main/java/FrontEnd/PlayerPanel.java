/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author user-ubunto
 */
public class PlayerPanel {
    
     private JPanel chip;

    public PlayerPanel(String color) {
        this.chip = new JPanel();
        Border borderChip = new TitledBorder(new EtchedBorder());
        this.chip.setBorder(borderChip);
        setIconChip(color);        
    }

    public JPanel getChip() {
        return chip;        
    }
        
    
    public void setIconChip(String color){ 
        //this.imageIcon = new ImageIcon(getClass().getResource("/Images/ficha1")).getImage();        
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
        this.chip.setBounds(row*75, column*105, 20, 30);
    }
    
    
}
