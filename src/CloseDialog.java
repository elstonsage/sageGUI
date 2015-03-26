package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Title: NEW GUI for SAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author suna
 * @version 1.0
 */

public class CloseDialog extends JDialog implements ActionListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jBottomPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  CompoundBorder Bottomborder = new CompoundBorder(new EmptyBorder(0,0,1,0), new BottomBorder());

  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JLabel jLabel4 = new JLabel();

  IconNode jobnode;
  JScrollPane jScrollPane1 = new JScrollPane();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();
  BorderLayout borderLayout3 = new BorderLayout();
  JList jList1 = new JList();
  CheckListRenderer ch = new CheckListRenderer();
  ListMouseListener ml = new ListMouseListener();

  boolean ok = false;

  public CloseDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public CloseDialog() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    this.setSize(new Dimension(300, 200));
    panel1.setLayout(borderLayout1);
    jTopPanel.setPreferredSize(new Dimension(300, 30));
    jTopPanel.setBorder(new EmptyBorder(10,10,0,10));
    jBottomPanel.setPreferredSize(new Dimension(300, 40));
    jBottomPanel.setLayout(xYLayout1);
    jBottomPanel.setBorder(new EmptyBorder(0,10,10,10));
    jCenterPanel.setPreferredSize(new Dimension(300,130));
    jCenterPanel.setLayout(borderLayout2);
    jCenterPanel.setBorder(new EmptyBorder(10,10,10,10));

    jList1.setCellRenderer(ch);
    jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jList1.addMouseListener(ml);

    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("OK");
    jButton1.addActionListener(this);

    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Cancel");
    jButton2.addActionListener(this);

    jButton3.addActionListener(this);
    jButton4.addActionListener(this);

    jTopPanel.setLayout(borderLayout3);
    jLabel4.setText("Select project you want to save.");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("All");
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton4.setText("None");
    getContentPane().add(panel1);
    panel1.add(jBottomPanel,  BorderLayout.SOUTH);
    jBottomPanel.add(jButton1,       new XYConstraints(175, 5, 50, 25));
    jBottomPanel.add(jButton2,     new XYConstraints(230, 5, 50, 25));
    jBottomPanel.add(jButton3,      new XYConstraints(0, 5, 50, 25));
    jBottomPanel.add(jButton4,     new XYConstraints(55, 5, 50, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jCenterPanel.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jList1, null);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel4,  BorderLayout.CENTER);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if(ob == jButton1)//OK
      jButton1_actionPerformed(e);
    if(ob == jButton2)//Cancel
      jButton2_actionPerformed(e);
    if(ob == jButton3)//All
      jButton3_actionPerformed(e);
    if(ob == jButton4)//None
      jButton4_actionPerformed(e);

 }

  void jButton1_actionPerformed(ActionEvent e) {
    ok = true;
    dispose();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    ok = false;
    dispose();
  }

  void jButton3_actionPerformed(ActionEvent e) {
    int listsize = jList1.getModel().getSize();
    for (int i = 0; i < listsize; i++) {
      CheckableItem item = (CheckableItem) jList1.getModel().getElementAt(i);
      item.setSelected(true);
    }
    jList1.repaint();
  }

  void jButton4_actionPerformed(ActionEvent e) {
    int listsize = jList1.getModel().getSize();
    for (int i = 0; i < listsize; i++) {
      CheckableItem item = (CheckableItem) jList1.getModel().getElementAt(i);
      item.setSelected(false);
    }
    jList1.repaint();
  }

  class ListMouseListener extends MouseAdapter {
     public void mouseClicked(MouseEvent e) {
       int index = jList1.locationToIndex(e.getPoint());

       Object sitem = jList1.getModel().getElementAt(index);

       if (sitem instanceof CheckableItem) {
         CheckableItem item = (CheckableItem) jList1.getModel().getElementAt(index);
         item.setSelected(!item.isSelected());
         Rectangle rect = jList1.getCellBounds(index, index);
         jList1.repaint(rect);
       }
     }
   }
}
