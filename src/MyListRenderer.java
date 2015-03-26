package sage;

import javax.swing.ListCellRenderer;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class MyListRenderer extends JLabel implements ListCellRenderer {
  File file;
  public MyListRenderer() {
  }
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

    IconNode current = (IconNode)value;
    NodeInfo node = (NodeInfo)current.getUserObject();

    file = (File)node.file;
    setText(file.getAbsolutePath());

    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    }
    else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }
    setEnabled(list.isEnabled());
    setFont(list.getFont());
    setOpaque(true);

    return this;
  }

  public String getToolTipText(MouseEvent event) {
    return file.getAbsolutePath();
  }


}
