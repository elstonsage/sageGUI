package sage;


import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.*;

public class RunJobDisplay extends DefaultTableModel {
    public static int[] columnWidth = new int[] {100, 60, 60, 60, 60};

    static int[] alignment = new int[] {JLabel.LEFT, JLabel.LEFT, JLabel.RIGHT, JLabel.RIGHT, JLabel.RIGHT};

    public RunJobDisplay(Vector data, Vector field) {
        super(data, field);
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass().getSuperclass();
    }

    public boolean isCellEditable(int row, int col)
    {
      return false;
    }
}
