package sage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ListMouseListener extends MouseAdapter {
    JList eachList;
    public ListMouseListener(MyComboBox combo) {
        eachList = combo.jList1;
    }

    public void mouseClicked(MouseEvent e) {
        int index = eachList.locationToIndex(e.getPoint());

        Object sitem = eachList.getModel().getElementAt(index);

        if (sitem instanceof CheckableItem) {
            CheckableItem item = (CheckableItem) eachList.getModel().
                                 getElementAt(index);
            item.setSelected(!item.isSelected());
            Rectangle rect = eachList.getCellBounds(index, index);
            eachList.repaint(rect);
        }
    }


}
