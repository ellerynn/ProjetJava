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
     * @param table la JTable en question
     */
    public TableLabelRendererPanel(JTable table)
    {
        this.setViewportView(table);
        renderer = new LabelRenderer();
        table.setDefaultRenderer(Object.class, renderer);
    }
    
    /**
     * set Alea
     * @param alea l'alea souhaité
     */
    public void setAlea(int alea) {
        this.renderer.setAlea(alea);
    }
}
