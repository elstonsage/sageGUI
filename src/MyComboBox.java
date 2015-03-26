package sage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

public class MyComboBox extends JComboBox{
  static final long serialVersionUID = 7917968344860800289L;
  int width;
  JList jList1 = new JList();
  int show_count = 0;

  CheckableItem[] ListData= new CheckableItem[0];

  public void setWidth(int width) {
    this.width = width;
  }

  public void setData(CheckableItem[] input) {
    ListData = input;
  }

  public void setSelectedItem(Object item) {
    removeAllItems();
    addItem(item);
    super.setSelectedItem(item);
  }

  public void updateUI() {
    ComboBoxUI cui = (ComboBoxUI) UIManager.getUI(this);
    cui = new WindowsDateComboBoxUI();
    setUI(cui);
  }

  // Inner classes are used purely to keep DateComboBox component in one file
  //////////////////////////////////////////////////////////////
  // UI Inner classes -- one for each supported Look and Feel
  //////////////////////////////////////////////////////////////

  class WindowsDateComboBoxUI extends WindowsComboBoxUI {
    protected ComboPopup createPopup() {
      return new DatePopup(comboBox);
    }
  }

  //////////////////////////////////////////////////////////////
  // DatePopup inner class
  //////////////////////////////////////////////////////////////

  class DatePopup implements ComboPopup, MouseMotionListener,
      MouseListener, KeyListener, PopupMenuListener {

    protected JComboBox comboBox;
    protected JPopupMenu popup;
    protected JPanel days = null;

    protected Color selectedBackground;
    protected Color selectedForeground;
    protected Color background;
    protected Color foreground;

    public DatePopup(JComboBox comboBox) {
      this.comboBox = comboBox;
      // check Look and Feel
      background = UIManager.getColor("ComboBox.background");
      foreground = UIManager.getColor("ComboBox.foreground");
      selectedBackground = UIManager.getColor("ComboBox.selectionBackground");
      selectedForeground = UIManager.getColor("ComboBox.selectionForeground");

      initializePopup();
    }


    //========================================
    // begin ComboPopup method implementations
    //
    public void show() {
      updatePopup();
      show_count++;
      popup.setPopupSize(width, 200);
      popup.show(comboBox, 0, comboBox.getHeight());
    }

    public void hide() {
      MenuSelectionManager.defaultManager().clearSelectedPath();
      popup.setVisible(false);
    }

    protected JList list = new JList();
    public JList getList() {
      return list;
    }

    public MouseListener getMouseListener() {
      return this;
    }

    public MouseMotionListener getMouseMotionListener() {
      return this;
    }

    public KeyListener getKeyListener() {
      return this;
    }

    public boolean isVisible() {
      return popup.isVisible();
    }

    public void uninstallingUI() {
      popup.removePopupMenuListener(this);
    }

    //
    // end ComboPopup method implementations
    //======================================



    //===================================================================
    // begin Event Listeners
    //

    // MouseListener

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    // something else registered for MousePressed
    public void mouseClicked(MouseEvent e) {
      if (!SwingUtilities.isLeftMouseButton(e)) {
        return;
      }
      if (!comboBox.isEnabled()) {
        return;
      }
      if (comboBox.isEditable()) {
        comboBox.getEditor().getEditorComponent().requestFocus();
      }
      else {
        comboBox.requestFocus();
      }
      togglePopup();
    }

    protected boolean mouseInside = false;
    public void mouseEntered(MouseEvent e) {
      mouseInside = true;
    }

    public void mouseExited(MouseEvent e) {
      mouseInside = false;
    }

    // MouseMotionListener
    public void mouseDragged(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    // KeyListener
    public void keyPressed(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_SPACE ||
          e.getKeyCode() == KeyEvent.VK_ENTER) {
        togglePopup();
      }
    }

    /**
     * Variables hideNext and mouseInside are used to
     * hide the popupMenu by clicking the mouse in the JComboBox
     */
    public void popupMenuCanceled(PopupMenuEvent e) {}

    protected boolean hideNext = false;
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
      hideNext = mouseInside;
    }

    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}

    //
    // end Event Listeners
    //=================================================================

    //===================================================================
    // begin Utility methods
    //

    protected void togglePopup() {
      if (isVisible() || hideNext) {
        hide();
      } else {
        show();
      }
      hideNext = false;
    }

    //
    // end Utility methods
    //=================================================================

    // Note *** did not use JButton because Popup closes when pressed
    protected JLabel createUpdateButton() {
      final JLabel label = new JLabel();
      final Border selectedBorder = new EtchedBorder();
      final Border unselectedBorder = new EmptyBorder(selectedBorder.getBorderInsets(new JLabel()));
      label.setBorder(unselectedBorder);
      label.setForeground(foreground);
      label.addMouseListener(new MouseAdapter() {
        public void mouseReleased(MouseEvent e) {
          int listsize = jList1.getModel().getSize();

          if (label.getText().compareTo("Select all") == 0) {
            for (int i = 0; i < listsize; i++) {
              CheckableItem item = (CheckableItem) jList1.getModel().getElementAt(i);
              item.setSelected(true);
            }
            label.setText("Deselect all");
          }
          else { //deselect all
            for (int i = 0; i < listsize; i++) {
              CheckableItem item = (CheckableItem) jList1.getModel().getElementAt(i);
              item.setSelected(false);
            }
            label.setText("Select all");
          }
          jList1.repaint();
          String list = new String();
          for (int i = 0; i < listsize; i++) {
            CheckableItem item = (CheckableItem) jList1.getModel().
                                 getElementAt(i);
            if (item.isSelected())
              list = list + ", " + item;
          }
          list = list.replaceFirst(",", "");
          list = list.trim();

          comboBox.setSelectedItem(list);
        }

        public void mouseEntered(MouseEvent e) {
          label.setBorder(selectedBorder);
        }

        public void mouseExited(MouseEvent e) {
          label.setBorder(unselectedBorder);
        }
      });
      return label;
    }

    protected void initializePopup() {
      JPanel header = new JPanel(); // used Box, but it wasn't Opaque
      header.setLayout(new BorderLayout());
      header.setBackground(background);
      header.setOpaque(true);

      JLabel label;
      label = createUpdateButton();
      label.setText("Select all");

      header.add(BorderLayout.CENTER, label);

      popup = new JPopupMenu();
      popup.setBorder(BorderFactory.createLineBorder(Color.black));
      popup.setLayout(new BorderLayout());
      popup.setBackground(background);
      popup.addPopupMenuListener(this);
      popup.add(BorderLayout.NORTH, header);
    }

    protected void updatePopup() {
      if (days != null) {
        popup.remove(days);
      }
      days = new JPanel(new BorderLayout());
      days.setBackground(background);
      days.setOpaque(true);

      JScrollPane jScrollPane1 = new JScrollPane();
      jScrollPane1.getViewport().add(jList1, null);

      if(show_count==0)
      {
        CheckListRenderer ch = new CheckListRenderer();
        EmptyBorder empbor = new EmptyBorder(0, 4, 0, 0);
        ListMouseListener2 ml = new ListMouseListener2(comboBox);

        jList1.setCellRenderer(ch);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setBorder(empbor);
        jList1.addMouseListener(ml);
      }

      jList1.setListData(ListData);

      days.add(jScrollPane1, BorderLayout.CENTER);
      popup.add(BorderLayout.CENTER, days);
      popup.pack();
    }
  }


  class ListMouseListener2 extends MouseAdapter {
    JComboBox mycombobox;

    public ListMouseListener2(JComboBox box) {
      mycombobox = box;
    }

    public void mouseClicked (MouseEvent e) {
      int index = jList1.locationToIndex(e.getPoint());

      Object sitem = ListData[index];

      if (sitem instanceof CheckableItem) {
        CheckableItem item = (CheckableItem)sitem;
        item.setSelected(!item.isSelected());
        jList1.repaint();
        Rectangle rect = jList1.getCellBounds(index, index);
        jList1.repaint(rect);
      }

      int listsize = ListData.length;
      String list = new String();
      for (int i = 0; i < listsize; i++) {
        CheckableItem item = (CheckableItem) ListData[i];
        if (item.isSelected())
          list = list + ", " + item;
      }
      list = list.replaceFirst(",", "");
      list = list.trim();
      mycombobox.setSelectedItem(list);
    }
  }
}
