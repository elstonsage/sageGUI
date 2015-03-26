package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class CheckRenderer extends JPanel implements ListCellRenderer {
  protected JCheckBox check1= new JCheckBox();
  protected JCheckBox check2= new JCheckBox();
  JList list;

  public CheckRenderer() {
    setLayout(null);
    add(check1);
    add(check2);
    check1.setBackground(UIManager.getColor("Tree.textBackground"));
    check2.setForeground(UIManager.getColor("Tree.textForeground"));
  }

  public Component getListCellRendererComponent(JList list, Object value,
                                                int index, boolean isSelected,
                                                boolean hasFocus) {
    this.list = list;
    setEnabled(list.isEnabled());
    check1.setSelected( ( (CheckableItem) value).isSelected());
    check1.setFont(list.getFont());
    check1.setText(value.toString());
    check2.setSelected( ( (CheckableItem) value).isSelected2());
    check2.setText( ( (CheckableItem) value).toString2());
    check2.setFont(list.getFont());

    return this;
  }

  public Dimension getPreferredSize() {
    Dimension d_check = check1.getPreferredSize();
    Dimension d_label = check2.getPreferredSize();
    Dimension d = list.getSize();

    return new Dimension(d.width,(d_check.height < d_label.height ? d_label.height : d_check.height));
  }

  public void doLayout() {
    Dimension d_check = check1.getPreferredSize();
    Dimension d_label = check2.getPreferredSize();
    Dimension d = list.getSize();
    double h1 = d.getHeight();
    double w1 = d.getWidth();

    int y_check = 0;
    int y_label = 0;
    if (d_check.height < d_label.height) {
      y_check = (d_label.height - d_check.height) / 2;
    }
    else {
      y_label = (d_check.height - d_label.height) / 2;
    }
    check1.setLocation(0, y_check);
    check1.setBounds(0, y_check, d_check.width, d_check.height);
    check2.setLocation(d.width / 2, y_label);
    check2.setBounds(d.width / 2, y_label, d_label.width, d_label.height);
  }

  public void setBackground(Color color) {
    if (color instanceof ColorUIResource)
      color = null;
    super.setBackground(color);
  }
}
