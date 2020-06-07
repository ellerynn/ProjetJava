package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
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
    private ArrayList<Color> colFond;
    private ArrayList<Color> colBord;
    private int alea;

    /**
     * constructeur
     */
    public LabelRenderer() {
        alea = 0;
        panel = new JPanel(new BorderLayout());
        label = new JLabel();
        colFond = new ArrayList<>();
        colFond.add(new Color(160, 224, 255));
        colFond.add(new Color(160, 255, 160));
        colFond.add(new Color(255, 192, 96));
        colFond.add(new Color(255, 192, 192));
        colFond.add(new Color(255, 255, 192));
        colFond.add(new Color(255, 204, 204));
        
        colBord = new ArrayList<>();
        colBord.add(Color.BLUE);
        colBord.add(Color.GREEN);
        colBord.add(Color.ORANGE);
        colBord.add(Color.PINK);
        colBord.add(Color.YELLOW);
        colBord.add(Color.RED);
    }

    /**
     * https://docs.oracle.com/javase/7/docs/api/javax/swing/table/TableCellRenderer.html
     * @param table the JTable that is asking the renderer to draw; can be null
     * @param value the value of the cell to be rendered. It is up to the specific renderer to interpret and draw the value. For example, if value is the string "true", it could be rendered as a string or it could be rendered as a check box that is checked. null is a valid value
     * @param isSelected true if the cell is to be rendered with the selection highlighted; otherwise false
     * @param hasFocus if true, render cell appropriately. For example, put a special border on the cell, if the cell can be edited, render in the color used to indicate editing
     * @param row the row index of the cell being drawn. When drawing the header, the value of row is -1
     * @param column the column index of the cell being drawn
     * @return the component used for drawing the cell.
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
            label.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, colBord.get(alea))); //Design} 
            label.setBackground(colFond.get(alea));
            label.setOpaque(true);

            if(text.equals("ANNULEE")) {
                Font bold = new Font(label.getFont().getFontName(), Font.BOLD, label.getFont().getSize());
                label.setFont(bold);
                label.setForeground(Color.red);
            }

            else {
                Font bold = new Font(label.getFont().getFontName(), Font.PLAIN, label.getFont().getSize());
                label.setFont(bold);
                if(column != 0)
                    label.setForeground(Color.black);
                else {
                    label.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.white)); //Design} 
                    label.setOpaque(false);
                }
                
            }

            panel.add(label);

        return panel;
    }
    
    /**
     * set Alea pour la couleur des component
     * @param alea l'alea souhaité
     */
    public void setAlea(int alea) {
        this.alea = alea;
    }
}
