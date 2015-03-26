package sage;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

class GenomeTableData extends AbstractTableModel
{
  public static ImageIcon COLUMN_UP;
  public static ImageIcon COLUMN_DOWN;

  static final public ColumnData m_columns[] = {
    new ColumnData( "Marker", 100, JLabel.LEFT ),
    new ColumnData( "Position", 100, JLabel.RIGHT ),
    new ColumnData( "Region", 100, JLabel.RIGHT ),
  };

  public int     m_sortCol = 0;
  public boolean m_sortAsc = true;

  protected Vector m_vector;

  public GenomeTableData() {
    m_vector = new Vector();
    setDefaultData();
    try{
        COLUMN_UP = new ImageIcon(GenomeTableData.class.getResource("SortUp.gif"));
        COLUMN_DOWN = new ImageIcon(GenomeTableData.class.getResource("SortDown.gif"));
    }catch(Exception ex)
    {
        ex.printStackTrace();
    }
  }

  public void setDefaultData() {
    m_vector.removeAllElements();
  }

  public void addData(GenomeData in)
  {
    m_vector.addElement(in);
  }

  public int getRowCount() {
    return m_vector==null ? 0 : m_vector.size();
  }

  public int getColumnCount() {
    return m_columns.length;
  }

  public String getColumnName(int column) {
    return m_columns[column].m_title;
  }

  public boolean isCellEditable(int nRow, int nCol) {
    return false;
  }

  public Object getValueAt(int nRow, int nCol) {
    if (nRow < 0 || nRow>=getRowCount())
      return "";
    GenomeData row = (GenomeData)m_vector.elementAt(nRow);
    switch (nCol) {
      case 0: return row.m_name;
      case 1: return row.m_distance;
      case 2: return row.m_region;
    }
    return "";
  }

  public void setValueAt(String value, int nRow, int nCol)
  {
    if (nRow < 0 || nRow>=getRowCount())
      return;
    GenomeData row = (GenomeData)m_vector.elementAt(nRow);
    switch (nCol) {
      case 0: row.m_name = value;
      case 1: row.m_distance = value;
      case 2: row.m_region = value;
    }
  }

  public Icon getColumnIcon(int column) {
          if (column==m_sortCol)
                  return m_sortAsc ? COLUMN_UP : COLUMN_DOWN;
          return null;
  }

  public void setSort(int col, boolean sort)
  {
    m_sortCol = col;
    m_sortAsc = sort;
  }

  public void sortData() {
    Collections.sort(m_vector, new GenomeComparator(m_sortCol, m_sortAsc));
  }
}

class GenomeComparator implements Comparator {

  protected int	m_sortCol;
  protected boolean m_sortAsc;

  public GenomeComparator(int sortCol, boolean sortAsc) {
    m_sortCol = sortCol;
    m_sortAsc = sortAsc;
  }

  public int compare(Object o1, Object o2) {
    if(!(o1 instanceof GenomeData) || !(o2 instanceof GenomeData))
      return 0;
    GenomeData s1 = (GenomeData)o1;
    GenomeData s2 = (GenomeData)o2;
    int result = 0;
    double d1, d2;
    switch (m_sortCol) {
      case 0:		// name
        result = s1.m_name.compareTo(s2.m_name);
        break;
      case 1:		// distance
        d1 = Double.parseDouble(s1.m_distance);
        d2 = Double.parseDouble(s2.m_distance);
        result = d1<d2 ? -1 : (d1>d2 ? 1 : 0);
        break;
      case 2:		// region
        result = s1.m_region.compareTo(s2.m_region);
        break;
    }

    if (!m_sortAsc)
      result = -result;
    return result;
  }

  public boolean equals(Object obj) {
    if (obj instanceof GenomeComparator) {
      GenomeComparator compObj = (GenomeComparator)obj;
      return (compObj.m_sortCol==m_sortCol) && (compObj.m_sortAsc==m_sortAsc);
    }
    return false;
  }
}

class ColumnData
{
  public String  m_title;
  public int     m_width;
  public int     m_alignment;

  public ColumnData(String title, int width, int alignment) {
    m_title = title;
    m_width = width;
    m_alignment = alignment;
  }
}

