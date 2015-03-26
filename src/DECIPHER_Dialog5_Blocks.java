package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DECIPHER_Dialog5_Blocks
    extends JDialog
    implements DocumentListener, ItemListener, ActionListener {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;

  DataCollectionModel Datamodel;
  String[] trans = {
      "George and Elston", "Box and Cox", "None"};
  String[] realtrans={"george_elston", "box_cox", "none"};

  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  JButton jButton2 = new JButton();
  DECIPHER2 parent;
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JCheckBox jCheckBox1 = new JCheckBox();
  JCheckBox jCheckBox2 = new JCheckBox();
  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout6 = new XYLayout();
  JCheckBox jCheckBox3 = new JCheckBox();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField3 = new JTextField();

  public DECIPHER_Dialog5_Blocks(DECIPHER2 parent, String title) {
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

  public void showDialog() {
      setLocationRelativeTo(Frame1.mainFrame1.activeinframe);
      this.setVisible(true);
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    jButton1.setEnabled(true);
    jCheckBox1.setFocusPainted(false);
    jCheckBox2.setFocusPainted(false);
    jCheckBox3.setFocusPainted(false);
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 360));
    this.setResizable(false);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(330, 280));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jBottomPanel.setLayout(xYLayout3);

    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jLabel3.setText("Specifies the haplotype block determination.");
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel1.setEnabled(false);
    jLabel1.setToolTipText("Specifies width of the sliding window as number of markers.");
    jLabel1.setText("Width");
    jTextField1.setEnabled(false);
    jTextField1.setText("3");
    jTextField1.addMouseListener(new DECIPHER_Dialog5_Blocks_jTextField1_mouseAdapter(this));
    jCheckBox1.setToolTipText("Specifies use of a sliding window.");
    jCheckBox1.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox1.setText("Use sliding window");
    jCheckBox1.addItemListener(this);
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout5);
    jLabel2.setEnabled(false);
    jLabel2.setToolTipText(
            "<html>Specifies the frequency threshold used in applying<br>the four gamete rule.");
    jLabel2.setText("Threshold");
    jTextField2.setEnabled(false);
    jTextField2.setText(".01");
    jTextField2.addMouseListener(new DECIPHER_Dialog5_Blocks_jTextField2_mouseAdapter(this));
    jCheckBox2.setToolTipText(
            "<html>Determine haplotype blocks using the four gamete rule," +
            "<br>and perform the specified analysis for each block.");
    jCheckBox2.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox2.setText("Use four gamete rule");
    jCheckBox2.addItemListener(this);
    jPanel3.setBorder(titledBorder1);
    jPanel3.setLayout(xYLayout6);
    jCheckBox3.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox3.setText("Use linkage disequilibrium");
    jCheckBox3.setToolTipText(
            "<html>Determine haplotype blocks by applying linkage disequilibrium<br>"+
            "Threshold (D’)to pairs of adjacent markers and perform the <br>specified analysis for each block.");
    jCheckBox3.addItemListener(this);
    jLabel4.setEnabled(false);
    jLabel4.setText("Threshold");
    jLabel4.setToolTipText(
            "<html>Specifies the frequency threshold used in determining"
            +"<br>haplotype blocks via linkage disequilibrium.");
    jTextField3.setEnabled(false);
    jTextField3.setText(".8");
    jTextField3.addMouseListener(new DECIPHER_Dialog5_Blocks_jTextField3_mouseAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 266, 30));
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1,  new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2,  new XYConstraints(243, 7, 65, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jPanel1.add(jTextField1,             new XYConstraints(100, 32, 185, 20));
    jPanel1.add(jLabel1,    new XYConstraints(25, 32, 52, 20));
    jPanel1.add(jCheckBox1,   new XYConstraints(2, 2, 195, 20));
    jCenterPanel.add(jPanel2,     new XYConstraints(15, 93, 300, 68));
    jCenterPanel.add(jPanel1,   new XYConstraints(15, 15, 300, 68));
    jPanel2.add(jLabel2,    new XYConstraints(25, 32, 52, 20));
    jPanel2.add(jTextField2,      new XYConstraints(100, 32, 185, 20));
    jPanel2.add(jCheckBox2,   new XYConstraints(2, 2, 195, 20));
    jCenterPanel.add(jPanel3,      new XYConstraints(15, 171, 300, 68));
    jPanel3.add(jCheckBox3,    new XYConstraints(2, 2, 195, 20));
    jPanel3.add(jLabel4,    new XYConstraints(25, 32, 52, 20));
    jPanel3.add(jTextField3,      new XYConstraints(100, 32, 185, 20));

    this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1Pressed");

    this.getRootPane().getActionMap().
            put("F1Pressed", new AbstractAction("F1Pressed") {
        public void actionPerformed(ActionEvent actionEvent) {
            onPressedF1();
        }
    });
    jTextField1.getDocument().addDocumentListener(this);
    jTextField2.getDocument().addDocumentListener(this);
    jTextField3.getDocument().addDocumentListener(this);
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
    Datamodel.setValue("sliding_window", "false");
    Datamodel.setValue("four_gamete_rule", "false");
    Datamodel.setValue("linkage_dis", "false");
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if(ob == jCheckBox1)
    {
      Frame1.mainFrame1.pdfframe.setTextonPage("sliding_window ", false, 402);
      if(jCheckBox1.isSelected())
      {
        Datamodel.setValue("sliding_window", "true");
        jTextField1.setEnabled(true);
        jLabel1.setEnabled(true);
      }
      else
      {
        Datamodel.setValue("sliding_window", "false");
        jTextField1.setEnabled(false);
        jLabel1.setEnabled(false);
      }
    }
    else if(ob == jCheckBox2)
    {
      Frame1.mainFrame1.pdfframe.setTextonPage("four_gamete_rule ", false, 402);
      if(jCheckBox2.isSelected())
      {
        Datamodel.setValue("four_gamete_rule", "true");
        jTextField2.setEnabled(true);
        jLabel2.setEnabled(true);
      }
      else
      {
        Datamodel.setValue("four_gamete_rule", "false");
        jTextField2.setEnabled(false);
        jLabel2.setEnabled(false);
      }
    }
    else if(ob == jCheckBox3)
    {
      Frame1.mainFrame1.pdfframe.setTextonPage("ld ", false, 402);
      if(jCheckBox3.isSelected())
      {
        Datamodel.setValue("linkage_dis", "true");
        jTextField3.setEnabled(true);
        jLabel4.setEnabled(true);
      }
      else
      {
        Datamodel.setValue("linkage_dis", "false");
        jTextField3.setEnabled(false);
        jLabel4.setEnabled(false);
      }
    }
  }


  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if (ob == jButton1) {
      save_init_state();
      dispose();
    }
    else if (ob == jButton2) {
      dispose();
    }
  }

  public void save_init_state()
  {
    Datamodel.setValue("blocks_info", "use");
    parent.jTextField8.setText("Specified");
    parent.jLabel18.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      if (jTextField1.getText().length() <= 0) {
        if(Datamodel.hasValue("sliding_window_width"))
          Datamodel.removeValue("sliding_window_width");
      }
      else {
        Datamodel.setValue("sliding_window_width", jTextField1.getText());
      }
    }
    if (document == jTextField2.getDocument()) {
      if (jTextField2.getText().length() <= 0) {
        if(Datamodel.hasValue("four_gamete_rule_thresh"))
          Datamodel.removeValue("four_gamete_rule_thresh");
      }
      else {
        Datamodel.setValue("four_gamete_rule_thresh", jTextField2.getText());
      }
    }
    if (document == jTextField3.getDocument()) {
      if (jTextField3.getText().length() <= 0) {
        if(Datamodel.hasValue("linkage_dis_thresh"))
          Datamodel.removeValue("linkage_dis_thresh");
      }
      else {
        Datamodel.setValue("linkage_dis_thresh", jTextField3.getText());
      }
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("sliding_window ", false, 407);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("width", false, 407);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(",threshold", false, 407);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(",threshold", false, 407);
  }
}

class DECIPHER_Dialog5_Blocks_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog5_Blocks adaptee;

  DECIPHER_Dialog5_Blocks_jTextField1_mouseAdapter(DECIPHER_Dialog5_Blocks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class DECIPHER_Dialog5_Blocks_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog5_Blocks adaptee;

  DECIPHER_Dialog5_Blocks_jTextField2_mouseAdapter(DECIPHER_Dialog5_Blocks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class DECIPHER_Dialog5_Blocks_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog5_Blocks adaptee;

  DECIPHER_Dialog5_Blocks_jTextField3_mouseAdapter(DECIPHER_Dialog5_Blocks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

