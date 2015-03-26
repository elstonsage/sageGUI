package sage;

import javax.swing.table.*;
import java.util.Vector;

public class StringTableModel extends AbstractTableModel {

    String[] columnNames;
    Vector data;

    public StringTableModel(Vector data, String[] columnNames) {
        this.data = data;
        this.columnNames = columnNames;
    }

    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    /**
     * Returns the number of columns in the model.
     *
     * @return the number of columns in the model
     * @todo Implement this javax.swing.table.TableModel method
     */
    public int getColumnCount() {
        if(columnNames != null)
            return columnNames.length;
        else
            return 0;
    }

    /**
     * Returns the number of rows in the model.
     *
     * @return the number of rows in the model
     * @todo Implement this javax.swing.table.TableModel method
     */
    public int getRowCount() {
        return data.size();
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     * @todo Implement this javax.swing.table.TableModel method
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
            Object o = data.elementAt(rowIndex);
            String[] data = (String[])o;
            if (data != null) {
                return data[columnIndex];
            } else
                return "";
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex)
    {
        Object o = data.elementAt(rowIndex);
        String[] data = (String[])o;
        data[columnIndex] = value.toString();
    }

    public boolean isCellEditable(int row, int column) {
        if(column == 498 || row == 998)
            return false;
        return true;
    }

}
