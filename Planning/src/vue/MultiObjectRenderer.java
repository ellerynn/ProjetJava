/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class MultiObjectRenderer implements TableCellRenderer {
    private JPanel panel;
    private JLabel label;
    private ImageIcon icon;

    public MultiObjectRenderer() {
        panel = new JPanel(new BorderLayout());
        label = new JLabel();
        icon = new ImageIcon("images\\icon_afficher.png");
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
                     
            label.setText(" " + text); //Design
            label.setForeground(Color.black);

            if(column == 0) {
                JLabel pic = new JLabel(icon);
                panel.add(pic, BorderLayout.WEST);
            }
            
            panel.add(label);

        return panel;
    }
}
