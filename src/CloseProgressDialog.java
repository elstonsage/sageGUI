package sage;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

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

public class CloseProgressDialog extends JInternalFrame implements ActionListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jBottomPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  CompoundBorder Bottomborder = new CompoundBorder(new EmptyBorder(0,0,1,0), new BottomBorder());

  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JLabel jLabel4 = new JLabel();

  BorderLayout borderLayout3 = new BorderLayout();

  JProgressBar jProgressBar1 = new JProgressBar();

  MyInternalFrame inframe;

  public CloseProgressDialog(String title) {
    super(title);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public void showDialog() {
    jProgressBar1.setValue(0);
    int xlocation = (int)(Frame1.mainFrame1.getWidth()/2 - this.getWidth()/2);
    int ylocation = (int)(Frame1.mainFrame1.getHeight()/2 - this.getHeight());

      this.setLocation(xlocation, ylocation);
      this.setVisible(true);
  }

  private void jbInit() throws Exception {
    this.setSize(new Dimension(230, 100));
    panel1.setLayout(borderLayout1);
    jTopPanel.setPreferredSize(new Dimension(250, 30));
    jTopPanel.setBorder(new EmptyBorder(10,10,0,10));
    jBottomPanel.setPreferredSize(new Dimension(250, 40));
    jBottomPanel.setLayout(xYLayout1);
    jBottomPanel.setBorder(new EmptyBorder(0,10,10,10));
    jCenterPanel.setPreferredSize(new Dimension(250,30));
    jCenterPanel.setLayout(borderLayout2);
    jCenterPanel.setBorder(new EmptyBorder(10,20,5,20));

    jButton1.setEnabled(false);
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("OK");
    jButton1.addActionListener(this);

    jProgressBar1.setValue(0);

    //We call setStringPainted, even though we don't want the
    //string to show up until we switch to determinate mode,
    //so that the progress bar height stays the same whether
    //or not the string is shown.
   // jProgressBar1.setStringPainted(true); //get space for the string
   // jProgressBar1.setString("");
   // jProgressBar1.setIndeterminate(false);

    jTopPanel.setLayout(borderLayout3);
    jLabel4.setText("Please wait while your project is being opened.");
    getContentPane().add(panel1);
    panel1.add(jBottomPanel,  BorderLayout.SOUTH);
    jBottomPanel.add(jButton1,        new XYConstraints(150, 5, 50, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jCenterPanel.add(jProgressBar1, BorderLayout.CENTER);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel4,  BorderLayout.NORTH);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if(ob == jButton1)//OK
      jButton1_actionPerformed();
 }

  public void jButton1_actionPerformed() {
    Frame1.desktop.remove(this);
    Frame1.desktop.updateUI();

    JRadioButtonMenuItem item = inframe.m_frameMenuItem;
    Frame1.mainFrame1.m_windowMenu.add(item);
    Frame1.mainFrame1.m_windowButtonGroup.add(item);
    item.setSelected(true);

    inframe.addInternalFrameListener(inframe.new FrameListener());
    Frame1.mainFrame1.activateInternalFrame(inframe);


    try{
    inframe.setSelected(true);
    }catch(Exception ex)
    {

    }
  }
}
