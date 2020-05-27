//https://coderanch.com/t/614645/java/adding-components-cell-jtable

package vue;

import javax.swing.*;
 
public class TableTreeRendererPanel extends JScrollPane {   
    private TreeRenderer renderer;
    
    public TableTreeRendererPanel(JTable table)
    {
        this.setViewportView(table);
        renderer = new TreeRenderer();
        table.setDefaultRenderer(Object.class, renderer);
    }
}
