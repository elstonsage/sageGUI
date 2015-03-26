package sage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyConsoleFrame extends JFrame implements ActionListener{
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  Frame1 frame;
  private MyConsoleFrame myself;
  JPopupMenu jPopupMenuClose = new JPopupMenu();
  JMenuItem jCloseMenuItem1 = new JMenuItem("Close");

  public MyConsoleFrame(Frame1 inframe) {
    frame = inframe;
    myself = this;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {

    Toolkit t = Toolkit.getDefaultToolkit();

    this.setIconImage(t.createImage(MyConsoleFrame.class.getResource("console.PNG")));
    setSize(new Dimension(600, 600));
    setTitle("Console");

    jPopupMenuClose.add(jCloseMenuItem1);
    jCloseMenuItem1.addActionListener(this);
    MouseListener popupListener = new PopupListener();
    jTabbedPane1.addMouseListener(popupListener);

    mylistener wl = new mylistener();

    addWindowListener(wl);
    jTabbedPane1.setBackground(Color.white);
    this.getContentPane().add(jTabbedPane1, BorderLayout.CENTER);

  }

  public void actionPerformed(ActionEvent e)
  {
    Object ob = e.getSource();
    if(ob == jCloseMenuItem1)
    {
      jTabbedPane1.remove(jTabbedPane1.getSelectedIndex());
    }

  }

  class mylistener extends WindowAdapter
  {
    public void windowClosing(WindowEvent e)
    {
      frame.jCheckBoxMenuItem3.setSelected(false);
      myself.dispose();
    }
  }

  class PopupListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
      if ((e.isPopupTrigger()))
      {
        Component c = getComponentAt(e.getPoint());
        jPopupMenuClose.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }
}
