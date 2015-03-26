package sage;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class MyJobTable extends JTable {
  private DefaultTableCellRenderer Redrenderer = new DefaultTableCellRenderer();
  private DefaultTableCellRenderer Bluerenderer = new DefaultTableCellRenderer();

  public MyJobTable() {
      super();
  }

  public TableCellRenderer getCellRenderer(int row, int column)
  {
      Redrenderer.setForeground(Color.red);
      Redrenderer.setHorizontalAlignment(JLabel.RIGHT);
      Bluerenderer.setForeground(Color.blue);
      Bluerenderer.setHorizontalAlignment(JLabel.RIGHT);

      if(column==4)
      {
        String status = this.getValueAt(row, column).toString();
        if(status.compareTo("Running")==0)
          return Bluerenderer;
        else
          return Redrenderer;
      }
      else
          return super.getCellRenderer(row, column);
  }
}
