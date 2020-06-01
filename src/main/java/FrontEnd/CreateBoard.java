/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;


import java.awt.Color;
import javax.swing.JButton;

public class CreateBoard {

    public final int ESPACIO_INGRESO_PIXELES = 40;
    public final int ESPACIO_INTERMEDIO = 7;
    public final int HIGH_BUTTON = 70;
    public final int WIDTH_BUTTON = 80;

    private JButton temp;
    private boolean isEmpty = true;

    public CreateBoard(int row, int column) {
        this.temp = new JButton(row + "," + column);
        this.temp.setBackground(Color.WHITE);
        this.temp.setForeground(Color.WHITE);
    }

    public JButton getTempButton() {
        return temp;
    }

    public void setBoundsButtons(int row, int column) {
        int yPosition = (this.HIGH_BUTTON * row);
        int xPosition = (this.WIDTH_BUTTON * column);
        temp.setBounds(xPosition, yPosition, this.WIDTH_BUTTON, this.HIGH_BUTTON);
    }
    
    public void setTextWithColor(String text,Color colorToUse){
        this.temp.setText(text);
        this.temp.setForeground(colorToUse);
    }
    
    public void setEmpity(boolean isEmpty){
        this.isEmpty = isEmpty;
    }

    public boolean isIsEmpty() {
        return this.isEmpty;
    }
        
}

