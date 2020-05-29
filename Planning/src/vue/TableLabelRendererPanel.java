package vue;
 
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * https://coderanch.com/t/614645/java/adding-components-cell-jtable
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class TableLabelRendererPanel extends JScrollPane {   
    private LabelRenderer renderer;
    
    /**
     * constructeur
     * @param table
     */
    public TableLabelRendererPanel(JTable table)
    {
        this.setViewportView(table);
        renderer = new LabelRenderer();
        table.setDefaultRenderer(Object.class, renderer);
    }
}
