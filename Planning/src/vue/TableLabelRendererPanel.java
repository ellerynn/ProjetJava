//https://coderanch.com/t/614645/java/adding-components-cell-jtable

package vue;

import javax.swing.*;
 
public class TableLabelRendererPanel extends JScrollPane {   
    private LabelRenderer renderer;
    
    public TableLabelRendererPanel(JTable table)
    {
        this.setViewportView(table);
        renderer = new LabelRenderer();
        table.setDefaultRenderer(Object.class, renderer);
    }
}
