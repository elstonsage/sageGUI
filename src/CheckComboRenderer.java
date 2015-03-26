package sage;

import javax.swing.*;
import java.awt.*;

public class CheckComboRenderer extends JCheckBox implements ListCellRenderer{
  Color back =  Color.white;
  Color fore =  Color.black;

  public CheckComboRenderer() {
    setBackground(back);
    setForeground(fore);
  }

  public Component getListCellRendererComponent(JList list, Object value,
             int index, boolean isSelected, boolean hasFocus) {
    setSelected(((CheckableItem)value).isSelected());
    setText(value.toString());
    return this;
  }

}
