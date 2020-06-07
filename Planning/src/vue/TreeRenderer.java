package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class TreeRenderer implements TableCellRenderer {
    private JPanel panel;
    private JLabel label;
    private Icon icon;
    private Icon icon2;
    
    /**
     * constructeur
     */
    public TreeRenderer() {
        panel = new JPanel(new BorderLayout());
        label = new JLabel();
        icon = new ImageIcon("images\\icon_afficher.png");
        icon2 = new ImageIcon("images\\icon_fleche.png");
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
            
            if(column != 0) {
                String text = value.toString(); //Si on setValueAt, récupère la String envoyée dans la cellule
                label.setText(" " + text); //Design
                label.setForeground(Color.black);
                panel.add(this.label);
            }
            
            else {
                int p1, p2;
                String text = value.toString();
                
                p1 = text.indexOf("[");
                String str = text.substring(0, p1);
                text = text.substring(p1);
                
                DefaultMutableTreeNode tree = new DefaultMutableTreeNode(str); 
                                   
                p1 = text.indexOf("[");
                p2 = text.indexOf("]");
                while(p1 != -1) {
                    str = text.substring(p1+1, p2);
                    text = text.substring(p2+1);
                    DefaultMutableTreeNode noeud = new DefaultMutableTreeNode(str);   
                    tree.add(noeud); 
                    p1 = text.indexOf("[");
                    p2 = text.indexOf("]");
                }
                
                JTree jt = new JTree(tree);
                
                DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
                renderer.setOpenIcon(this.icon);
                renderer.setLeafIcon(this.icon2);
                jt.setCellRenderer(renderer);
                
                panel.add(jt);
            }
        return panel;
    }
}
