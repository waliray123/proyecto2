/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import SectionsPackage.Section;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author user-ubunto
 */
public class CreateGameBoard {
    
    private JPanel panel;
    private JLabel name;
    private JLabel cost;

    public CreateGameBoard() {
        this.panel = new JPanel();
        this.name = new JLabel();
        this.cost = new JLabel();
        this.panel.setOpaque(true);
        this.cost.setText("");
        this.name.setText("");
        this.panel.add(this.name);
        this.panel.add(this.cost);
    }
    
    public void setColor(Color color){
        this.panel.setBackground(color);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setCost(String cost) {
        this.cost.setText(cost);
    }

    
    public JPanel getPanel() {
        return panel;
    }

    public JLabel getName() {
        return name;
    }

    public JLabel getCost() {
        return cost;
    }
        
    
    public void setBounds(int row,int column){
        int yPosition = 70*row;
        int xPosition = 100 * column;
        this.panel.setBounds(xPosition, yPosition, 100, 70);
        this.name.setBounds(0, 0, 80, 25);
        this.cost.setBounds(0, 35, 80, 25);
    }
    
    
}
