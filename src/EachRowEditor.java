package sage;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;

public class EachRowEditor implements TableCellEditor {
  protected Hashtable editors;
  protected TableCellEditor editor, defaultEditor;
  JTable table;
  int real_col_size;

  public EachRowEditor(JTable table, int real_col_size) {
    this.table = table;
    this.real_col_size = real_col_size;
    editors = new Hashtable();
    defaultEditor = new DefaultCellEditor(new JTextField());
  }

  /**
   * @param row    table row
   * @param editor table cell editor
   */
  public void setEditorAt(int row, TableCellEditor editor) {
    editors.put(new Integer(row),editor);
  }

  public Component getTableCellEditorComponent(JTable table,
      Object value, boolean isSelected, int row, int column) {
       if(row==0)
      {
        JComboBox jb = (JComboBox)(editor.getTableCellEditorComponent(table,value, isSelected, row, column));
        jb.setSelectedIndex(0);
      }

    return editor.getTableCellEditorComponent(table,value, isSelected, row, column);
  }

  public Object getCellEditorValue() {
    return editor.getCellEditorValue();
  }
  public boolean stopCellEditing() {
    return editor.stopCellEditing();
  }
  public void cancelCellEditing() {
    editor.cancelCellEditing();
  }
  public boolean isCellEditable(EventObject anEvent) {
    selectEditor((MouseEvent)anEvent);
    return editor.isCellEditable(anEvent);
  }
  public void addCellEditorListener(CellEditorListener l) {
    editor.addCellEditorListener(l);
  }
  public void removeCellEditorListener(CellEditorListener l) {
    editor.removeCellEditorListener(l);
  }
  public boolean shouldSelectCell(EventObject anEvent) {
    selectEditor((MouseEvent)anEvent);
    return editor.shouldSelectCell(anEvent);
  }

  protected void selectEditor(MouseEvent e) {
    int row;
    if (e == null) {
      row = table.getSelectionModel().getAnchorSelectionIndex();
    } else {
      row = table.rowAtPoint(e.getPoint());
    }
    editor = (TableCellEditor)editors.get(new Integer(row));
    if (editor == null) {
      editor = defaultEditor;
    }
  }
}


