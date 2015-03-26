package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class AGEON_Dialog5_Pool
    extends JDialog
    implements ActionListener{
  JPanel panel1 = new JPanel();

  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;

  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel6 = new JLabel();
  AGEON2 parent;
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();

  String pool_stat1 = new String();
  String pool_stat2 = new String();
  String pool_stat3 = new String();


  ButtonGroup bp1 = new ButtonGroup();
  ButtonGroup bp2 = new ButtonGroup();
  ButtonGroup bp3 = new ButtonGroup();

  String pool = new String();
  JLabel jLabel1 = new JLabel();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  JRadioButton jRadioButton3 = new JRadioButton();
  JRadioButton jRadioButton4 = new JRadioButton();
  JRadioButton jRadioButton5 = new JRadioButton();
  JRadioButton jRadioButton6 = new JRadioButton();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  XYLayout xYLayout6 = new XYLayout();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JRadioButton jRadioButton7 = new JRadioButton();
  JRadioButton jRadioButton8 = new JRadioButton();
  JRadioButton jRadioButton9 = new JRadioButton();
  JRadioButton jRadioButton10 = new JRadioButton();
  JRadioButton jRadioButton11 = new JRadioButton();
  JRadioButton jRadioButton12 = new JRadioButton();

  public AGEON_Dialog5_Pool(AGEON2 parent, String title) {
    super(Frame1.mainFrame1, title, true);
    this.parent = parent;

    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {

    titledBorder1 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 460));
    this.setResizable(false);

    jRadioButton1.setFocusPainted(false);
    jRadioButton2.setFocusPainted(false);
    jRadioButton3.setFocusPainted(false);
    jRadioButton4.setFocusPainted(false);
    jRadioButton5.setFocusPainted(false);
    jRadioButton6.setFocusPainted(false);
    jRadioButton7.setFocusPainted(false);
    jRadioButton8.setFocusPainted(false);
    jRadioButton9.setFocusPainted(false);
    jRadioButton10.setFocusPainted(false);
    jRadioButton11.setFocusPainted(false);
    jRadioButton12.setFocusPainted(false);

    jTopPanel.setPreferredSize(new Dimension(40, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(327, 380));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(40, 40));
    jBottomPanel.setLayout(xYLayout3);

    jButton1.setText("OK");

    jLabel3.setText("<html>Specifies the <i>pool</i> option.");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel6.setForeground(Color.black);
    jLabel6.setToolTipText("Both parents unknown");
    jLabel6.setText("?? ");

    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));

    jLabel1.setText("is reassigned to");
    jRadioButton1.setToolTipText("Both parents unknown");
    jRadioButton1.setSelected(true);
    jRadioButton1.setText("None");
    jRadioButton1.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton1_mouseAdapter(this));
    jRadioButton2.setToolTipText("One parent unknown and the other affected");
    jRadioButton2.setText("?A");
    jRadioButton2.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton2_mouseAdapter(this));
    jRadioButton3.setToolTipText("One parent unknown and the other unaffected");
    jRadioButton3.setText("?U");
    jRadioButton3.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton3_mouseAdapter(this));
    jRadioButton4.setToolTipText("Both parents affected");
    jRadioButton4.setText("AA");
    jRadioButton4.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton4_mouseAdapter(this));
    jRadioButton5.setToolTipText("One parent affected and the other unaffected");
    jRadioButton5.setText("AU");
    jRadioButton5.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton5_mouseAdapter(this));
    jRadioButton6.setToolTipText("Both parents unaffected");
    jRadioButton6.setText("UU");
    jRadioButton6.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton6_mouseAdapter(this));
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout6);
    jPanel3.setBorder(titledBorder1);
    jPanel3.setLayout(xYLayout5);
    jLabel2.setToolTipText("One parent unknown and the other affected");
    jLabel2.setText("?A");
    jLabel5.setText("is reassigned to");
    jLabel4.setToolTipText("One parent unknown and the other unaffected");
    jLabel4.setText("?U");
    jLabel7.setText("is reassigned to");
    jRadioButton7.setSelected(true);
    jRadioButton7.setText("None");
    jRadioButton7.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton7_mouseAdapter(this));
    jRadioButton8.setToolTipText("One parent affected and the other unaffected");
    jRadioButton8.setText("AU");
    jRadioButton8.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton8_mouseAdapter(this));
    jRadioButton9.setToolTipText("Both parents affected");
    jRadioButton9.setText("AA");
    jRadioButton9.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton9_mouseAdapter(this));
    jRadioButton10.setSelected(true);
    jRadioButton10.setText("None");
    jRadioButton10.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton10_mouseAdapter(this));
    jRadioButton11.setToolTipText("One parent affected and the other unaffected");
    jRadioButton11.setText("AU");
    jRadioButton11.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton11_mouseAdapter(this));
    jRadioButton12.setToolTipText("Both parents unaffected");
    jRadioButton12.setText("UU");
    jRadioButton12.addMouseListener(new AGEON_Dialog5_Pool_jRadioButton12_mouseAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);

    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 288, 30));
    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));
    jCenterPanel.add(jPanel1,                   new XYConstraints(15, 11, 300, 160));
    jPanel1.add(jLabel6,           new XYConstraints(30, 0, 20, 20));
    jPanel1.add(jLabel1,          new XYConstraints(80, 0, 90, 20));
    jPanel1.add(jRadioButton1,        new XYConstraints(200, 0, 50, 20));
    jPanel1.add(jRadioButton2,            new XYConstraints(200, 25, 50, 20));
    jPanel1.add(jRadioButton3,           new XYConstraints(200, 50, 50, 20));
    jPanel1.add(jRadioButton4,           new XYConstraints(200, 75, 50, 20));
    jPanel1.add(jRadioButton5,           new XYConstraints(200, 100, 50, 20));
    jPanel1.add(jRadioButton6,           new XYConstraints(200, 125, 50, 20));
    jCenterPanel.add(jPanel2,         new XYConstraints(15, 183, 300, 85));
    jPanel2.add(jLabel2,   new XYConstraints(30, 0, 20, 20));
    jPanel2.add(jLabel5,    new XYConstraints(80, 0, 90, 20));
    jPanel2.add(jRadioButton7,       new XYConstraints(200, 0, 50, 20));
    jPanel2.add(jRadioButton8,          new XYConstraints(200, 25, 50, 20));
    jPanel2.add(jRadioButton9,          new XYConstraints(200, 50, 50, 20));
    jCenterPanel.add(jPanel3,      new XYConstraints(15, 280, 300, 85));
    jPanel3.add(jLabel4,   new XYConstraints(30, 0, 20, 20));
    jPanel3.add(jLabel7,   new XYConstraints(80, 0, 90, 20));
    jPanel3.add(jRadioButton10,      new XYConstraints(200, 0, 50, 20));
    jPanel3.add(jRadioButton11,      new XYConstraints(200, 25, 50, 20));
    jPanel3.add(jRadioButton12,      new XYConstraints(200, 50, 50, 20));

    jButton1.addActionListener(this);
    jButton2.addActionListener(this);

    jRadioButton1.addActionListener(this);
    jRadioButton2.addActionListener(this);
    jRadioButton3.addActionListener(this);
    jRadioButton4.addActionListener(this);
    jRadioButton5.addActionListener(this);
    jRadioButton6.addActionListener(this);
    jRadioButton7.addActionListener(this);
    jRadioButton8.addActionListener(this);
    jRadioButton9.addActionListener(this);
    jRadioButton10.addActionListener(this);
    jRadioButton11.addActionListener(this);
    jRadioButton12.addActionListener(this);

    bp1.add(jRadioButton1);
    bp1.add(jRadioButton2);
    bp1.add(jRadioButton3);
    bp1.add(jRadioButton4);
    bp1.add(jRadioButton5);
    bp1.add(jRadioButton6);
    bp3.add(jRadioButton12);
    bp3.add(jRadioButton11);
    bp3.add(jRadioButton10);
    bp2.add(jRadioButton7);
    bp2.add(jRadioButton8);
    bp2.add(jRadioButton9);

    this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1Pressed");

    this.getRootPane().getActionMap().
            put("F1Pressed", new AbstractAction("F1Pressed") {
        public void actionPerformed(ActionEvent actionEvent) {
            onPressedF1();
        }
      });
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
  }

  public void showDialog() {
    setLocationRelativeTo(Frame1.mainFrame1.activeinframe);
      this.setVisible(true);
  }

  public void save_init_state()
  {
    Datamodel.setValue("pool_info", pool);
    if (Datamodel.hasValue("pool_info")) {
      parent.jTextField6.setText("Specified");
      parent.jLabel14.setFont(new java.awt.Font("Dialog", 1, 11));
    }
  }

  public void clear_init_state()
  {
    jRadioButton1.setSelected(true);
    jRadioButton7.setSelected(true);
    jRadioButton10.setSelected(true);
    pool = "";
  }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();

    if (button == jButton1) {
      save_init_state();
      dispose();
    }
    else if (button == jButton2) {
      dispose();
    }
    else if (button == jRadioButton1 || button == jRadioButton2
             || button == jRadioButton3 || button == jRadioButton4
             || button == jRadioButton5 || button == jRadioButton6)
    {
      pool = "";
      JRadioButton t = (JRadioButton)button;
      if (jRadioButton1.isSelected()) {
        pool_stat1 = "";
      }

      else {
        pool_stat1 = "?? = "+t.getActionCommand();
      }
    }
    else if (button == jRadioButton7 || button == jRadioButton8
             || button == jRadioButton9)
    {
      pool = "";
      JRadioButton t = (JRadioButton)button;
      if (jRadioButton7.isSelected()) {
        pool_stat2 = "";
      }
      else {
        pool_stat2 = "?A = "+t.getActionCommand();
      }
    }

    else if (button == jRadioButton10 || button == jRadioButton11
             || button == jRadioButton12)
    {
      pool = "";
      JRadioButton t = (JRadioButton)button;
      if (jRadioButton10.isSelected()) {
        pool_stat3 = "";
      }
      else {
        pool_stat3 = "?U = "+t.getActionCommand();
      }
    }

    if(pool_stat1.length()>0)
    {
      pool = pool_stat1;

      if(pool_stat2.length()>0 || pool_stat3.length()>0)
        pool = pool + ", ";
    }

    if(pool_stat2.length()>0)
    {
      pool = pool + pool_stat2;

      if(pool_stat3.length()>0)
        pool = pool + ", ";
    }

    if(pool_stat3.length()>0)
      pool = pool + pool_stat3;
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("pool ", false, 378);
  }

  void jRadioButton1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("?? ", false, 378);
  }

  void jRadioButton2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("?A ", false, 378);
  }

  void jRadioButton3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("?U", false, 378);
  }

  void jRadioButton4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("AA ", false, 378);
  }

  void jRadioButton5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("AU ", false, 378);
  }

  void jRadioButton6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("UU ", false, 378);
  }

  void jRadioButton7_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("?A ", false, 378);
  }

  void jRadioButton8_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("AU ", false, 378);
  }

  void jRadioButton9_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("AA ", false, 378);
  }

  void jRadioButton10_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("?U", false, 378);
  }

  void jRadioButton11_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("AU ", false, 378);
  }

  void jRadioButton12_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("UU ", false, 378);
  }
}

class AGEON_Dialog5_Pool_jRadioButton1_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton1_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton1_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton2_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton2_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton2_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton3_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton3_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton3_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton4_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton4_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton4_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton5_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton5_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton5_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton6_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton6_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton6_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton7_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton7_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton7_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton8_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton8_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton8_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton9_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton9_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton9_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton10_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton10_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton10_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton11_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton11_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton11_mouseClicked(e);
  }
}

class AGEON_Dialog5_Pool_jRadioButton12_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog5_Pool adaptee;

  AGEON_Dialog5_Pool_jRadioButton12_mouseAdapter(AGEON_Dialog5_Pool adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton12_mouseClicked(e);
  }
}
