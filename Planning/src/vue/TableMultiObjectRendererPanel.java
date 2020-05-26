//https://coderanch.com/t/614645/java/adding-components-cell-jtable

package vue;

import javax.swing.*;
 
public class TableMultiObjectRendererPanel extends JScrollPane {   
    private MultiObjectRenderer renderer;
    
    public TableMultiObjectRendererPanel(JTable table)
    {
        this.setViewportView(table);
        renderer = new MultiObjectRenderer();
        table.setDefaultRenderer(Object.class, renderer);
    }
}
