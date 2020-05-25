//https://coderanch.com/t/614645/java/adding-components-cell-jtable

package vue;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.table.*;
 
public class TableRendererPanel extends JScrollPane {   
    private MultiLabelRenderer renderer;
    
    public TableRendererPanel(JTable table)
    {
        this.setViewportView(table);
        renderer = new MultiLabelRenderer();
        table.setDefaultRenderer(Object.class, renderer);
    }
}
