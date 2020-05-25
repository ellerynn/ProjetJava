/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Camille
 */
public class MultiLabelRenderer implements TableCellRenderer {
    private JPanel panel;
    private JLabel label;

    public MultiLabelRenderer()
    {
        panel = new JPanel(new BorderLayout());
        label = new JLabel();
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected,
        boolean hasFocus, final int row, final int column)
    {
            panel.removeAll(); //Si on l'enlève on édite toute une ligne, on ne veut que la cellule selectionnée

            if (isSelected)
                panel.setBackground(table.getSelectionBackground());
            else
                panel.setBackground(table.getBackground());

            if (value == null ||  value.toString().length() == 0) //Nécessaire, certaines de nos cases sont vides
                return panel;

            String text = value.toString(); //Si on setValueAt, récupère la String envoyée dans la cellule
          
            label.setText(text); //Design
            label.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.red)); //Design} 
            label.setBackground(new Color(255, 204, 204));
            label.setOpaque(true);

            /* ON ENTRE JAMAIS DANS CE CAS VU QU'ON MODIFIE JAMAIS LA PREMIERE COLONNE
                if(column == 0) { //On va rendre certaines heures blanches pour ne pas les voir, mais pouvoir les recupérer si besoin
                System.out.println("colonne 0 !");
                if(row%2 != 0)
                    label.setForeground(Color.white);
                else
                    label.setForeground(Color.red);
            }*/
            
            if(column != 0) {
                if(text.equals("ANNULEE")) {
                    Font bold = new Font(label.getFont().getFontName(), Font.BOLD, label.getFont().getSize());
                    label.setFont(bold);
                    label.setForeground(Color.yellow);
                }
                
                else {
                    Font bold = new Font(label.getFont().getFontName(), Font.PLAIN, label.getFont().getSize());
                    label.setFont(bold);
                    label.setForeground(Color.red);
                }
                    
                panel.add(label);
            }

        return panel;
    }
}
