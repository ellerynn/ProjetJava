package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class LabelRenderer implements TableCellRenderer {
    private JPanel panel;
    private JLabel label;

    /**
     * constructeur
     */
    public LabelRenderer() {
        panel = new JPanel(new BorderLayout());
        label = new JLabel();
    }

    /**
     *
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return
     */
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

            if(text.equals("ANNULEE")) {
                Font bold = new Font(label.getFont().getFontName(), Font.BOLD, label.getFont().getSize());
                label.setFont(bold);
                label.setForeground(Color.yellow);
            }

            else {
                Font bold = new Font(label.getFont().getFontName(), Font.PLAIN, label.getFont().getSize());
                label.setFont(bold);
                if(column != 0)
                    label.setForeground(Color.red);
                else {
                    label.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.white)); //Design} 
                    label.setOpaque(false);
                }
                
            }

            panel.add(label);

        return panel;
    }
}
