package sage;

import javax.swing.table.*;
import java.util.Vector;
import javax.swing.SwingUtilities;

public class MyDynamicTableModel extends DefaultTableModel {
    public synchronized void delRow(final int row)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dataVector.removeElementAt(row);
                fireTableRowsDeleted(row, row);
            }
        });
    }

    public synchronized void addRow(final int row, final Vector rowData)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                dataVector.insertElementAt(rowData, row);
                justifyRows(row, row+1);
                fireTableRowsInserted(row, row);
            }
        });
    }

    private void justifyRows(int from, int to) {
        dataVector.setSize(getRowCount());

        for (int i = from; i < to; i++) {
            if (dataVector.elementAt(i) == null) {
                dataVector.setElementAt(new Vector(), i);
            }
            ((Vector)dataVector.elementAt(i)).setSize(getColumnCount());
        }
    }
}
