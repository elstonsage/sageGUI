package sage;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.ListSelectionModel;
import java.util.Vector;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.BorderFactory;

public class MyTable extends JTable {
    private DefaultTableCellRenderer Grayrenderer = new DefaultTableCellRenderer();
    boolean renderer = false;

    public MyTable() {
        super();
    }

    public MyTable(TableModel dm) {
        super(dm);
    }

    public MyTable(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
    }

    public MyTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);
    }

    public MyTable(int numRows, int numColumns) {
        super(numRows, numColumns);
    }

    public MyTable(Vector rowData, Vector columnNames) {
        super(rowData, columnNames);
    }

    public MyTable(Object[][] rowData, Object[] columnNames) {
        super(rowData, columnNames);
    }

    public TableCellRenderer getCellRenderer(int row, int column)
    {
        Grayrenderer.setBackground(new Color(216,228,248));
        Grayrenderer.setBorder(BorderFactory.createRaisedBevelBorder());

        if(renderer && row==0)
        {
          return Grayrenderer;
        }
        else
            return super.getCellRenderer(row, column);

    }

    public String getToolTipText(MouseEvent evt) {
      if (columnAtPoint(evt.getPoint()) == -1)
        return null;
      return Integer.toString(columnAtPoint(evt.getPoint())+1);
    }
}
