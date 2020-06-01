/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user-ubunto
 */
public class ChangeCellColor extends JLabel implements TableCellRenderer{
    int Row;
    int Column;

    public void setRow(int Row) {
        this.Row = Row;
    }

    public void setColumn(int Column) {
        this.Column = Column;
    }
    
    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if((row == Row) && (column == Column)){
            setBackground(Color.GREEN);
            setText(String.valueOf(value));
        }
        if((row != Row)&& (column==Column)){
            setBackground(Color.white);
            setText(String.valueOf(value));
        }
        return this;
    }
    
    
    
}
