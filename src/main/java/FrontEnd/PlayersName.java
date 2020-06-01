/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import java.text.NumberFormat;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author user-ubunto
 */
public class PlayersName {
    
    public final int ESPACIO_INGRESO_PIXELES = 40;
    public final int ESPACIO_INTERMEDIO = 7;
    public final int ALTO_TEXT = 30;
    public final int ANCHO_TEXT = 125;

    private JLabel player;
    private JTextField name;
    private JComboBox combo;

    public PlayersName(int Posicion) {
        this.player = new JLabel("Jugador " + (Posicion + 1));
        this.name = new JTextField();
        this.combo = new JComboBox();
        addColors();
    }
    
    public void addColors(){
        this.combo.addItem("Azul");
        this.combo.addItem("Amarillo");
        this.combo.addItem("Verde");
        this.combo.addItem("Rojo");
        this.combo.addItem("Naranja");
        this.combo.addItem("Rosado");
    }
    
    public JLabel getJugador() {
        return player;
    }

    public JTextField getNombre() {
        return name;
    }
    
    public JComboBox getCombo(){
        return this.combo;
    }
    

    public void setBounds(int position) {
        int yPosition = (ESPACIO_INGRESO_PIXELES * position) + ESPACIO_INTERMEDIO;        
        player.setBounds(ESPACIO_INTERMEDIO, yPosition, 75, ALTO_TEXT);
        name.setBounds(ESPACIO_INTERMEDIO * 2 + 75, yPosition, ANCHO_TEXT, ALTO_TEXT);
        combo.setBounds(ESPACIO_INTERMEDIO * 3 + ANCHO_TEXT +75, yPosition, 90, ALTO_TEXT);
    }
}
