package vue;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * https://coderanch.com/t/614645/java/adding-components-cell-jtable
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class TableTreeRendererPanel extends JScrollPane {   
    private TreeRenderer renderer;
    
    /**
     * constructeur
     * @param table la JTable en quesion
     */
    public TableTreeRendererPanel(JTable table)
    {
        this.setViewportView(table);
        renderer = new TreeRenderer();
        table.setDefaultRenderer(Object.class, renderer);
    }
}
