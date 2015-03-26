package sage;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.*;

import java.awt.event.*;
import javax.swing.border.*;

public class MyJobFrame extends JFrame implements ActionListener{
  JScrollPane jScrollPane1 = new JScrollPane();
  MyJobTable jTable1 = new MyJobTable();
  Vector v_data;
  Frame1 frame;
  Border border1;
  JPopupMenu jPopupMenu1 = new JPopupMenu();
  JMenuItem jMenuDelete = new JMenuItem();

  public MyJobFrame(Frame1 inframe) {
    frame = inframe;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    Toolkit t = Toolkit.getDefaultToolkit();
    border1 = BorderFactory.createEmptyBorder(0,3,3,3);
    this.setIconImage(t.createImage(MyJobFrame.class.getResource("Task.png")));
    setSize(new Dimension(600, 600));
    setTitle("Tasks");

    mylistener wl = new mylistener();
    addWindowListener(wl);

    jScrollPane1.getViewport().setBackground(Color.white);
    jScrollPane1.setBorder(border1);
    jScrollPane1.setDebugGraphicsOptions(0);
    jMenuDelete.setText("Delete");
    this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTable1, null);
    jPopupMenu1.add(jMenuDelete);

    v_data = new Vector();

    Vector v_field = new Vector();
    v_field.addElement("Job");
    v_field.addElement("Type");
    v_field.addElement("Start Time");
    v_field.addElement("Time Running");
    v_field.addElement("Status");

    jTable1.setAutoCreateColumnsFromModel(false);
    jTable1.setModel(new RunJobDisplay(v_data, v_field));
    jTable1.setGridColor(Color.white);
    jTable1.setFocusable(false);

    for (int i = 0; i < 5; i++) {
        DefaultTableCellRenderer tr = new DefaultTableCellRenderer();
        tr.setToolTipText("Select and right-click to delete");
        tr.setHorizontalAlignment(RunJobDisplay.alignment[i]);
        TableColumn column = new TableColumn(i, RunJobDisplay.columnWidth[i], tr, null);

        jTable1.addColumn(column);
    }

    MouseListener popupListener = new MyTableMouseListener();
    jTable1.addMouseListener(popupListener);
    jMenuDelete.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e)
  {
    Object source = e.getSource();
    if(source == jMenuDelete)
    {
      int tablerow = jTable1.getSelectedRow();
      MyJobFrame.this.v_data.remove(tablerow);
      MyJobFrame.this.v_data.trimToSize();
      jTable1.repaint();
    }
  }

  class mylistener extends WindowAdapter
  {
    public void windowClosing(WindowEvent e)
    {
      frame.jCheckBoxMenuItem2.setSelected(false);
      MyJobFrame.this.dispose();
    }
  }

  class MyTableMouseListener
      extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
      int tablerow = jTable1.getSelectedRow();
      if ((tablerow != -1) && (e.isPopupTrigger())) {
        jPopupMenu1.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }

}
