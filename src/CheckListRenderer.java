package sage;

import javax.swing.*;
import java.awt.*;

class CheckListRenderer extends JCheckBox implements ListCellRenderer, java.io.Serializable {
  Color back =  Color.white;
  Color fore =  Color.black;
 public CheckListRenderer() {
    setBackground(back);
    setForeground(fore);
  }

  public Component getListCellRendererComponent(JList list, Object value,
             int index, boolean isSelected, boolean hasFocus) {
    setEnabled(list.isEnabled());
    setSelected(((CheckableItem)value).isSelected());
    setFont(list.getFont());
    setText(value.toString());
    return this;
  }
}


