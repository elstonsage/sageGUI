package sage;

import javax.swing.table.*;

public class MyTableModel extends DefaultTableModel {

  boolean editable=false;

  public boolean isCellEditable(int nRow, int nCol) {
    return editable;
  }

}
