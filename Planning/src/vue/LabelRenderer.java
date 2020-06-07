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
     * set Alea
     * @param alea l'alea souhaité
     */
    public void setAlea(int alea) {
        this.alea = alea;
    }
}
