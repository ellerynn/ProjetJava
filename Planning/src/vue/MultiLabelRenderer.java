/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Camille
 */
public class MultiLabelRenderer implements TableCellRenderer {
    private JPanel panel;
    private JLabel red;

    public MultiLabelRenderer()
    {
        panel = new JPanel(new BorderLayout());
        red = new JLabel();
        red.setForeground(Color.RED);
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

            red.setText(text); //Design
            red.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.red)); //Design} 
            red.setBackground(new Color(255, 204, 204));
            red.setOpaque(true);

            if(column != 0) {
                panel.add(red);
            }

        return panel;
    }
}
