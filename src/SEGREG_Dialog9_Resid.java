package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog9_Resid
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

  DataCollectionModel Datamodel;

  String[] option = {
      "Parent-offspring and sib-sib correlations are equal",
      "Mother-offspring and father-offspring correlations are equal",
      "All correlations are functionally independent"};
  String[] option_real = {
      "equal_po_ss", "equal_po", "arb"};

  JLabel jLabel1 = new JLabel();
  JComboBox jComboBox1 = new JComboBox(option);

  JButton jButton2 = new JButton();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JCheckBox jCheckBox4 = new JCheckBox();
  JCheckBox jComboBox4 = new JCheckBox("True");
  JCheckBox jComboBox3 = new JCheckBox("True");
  JCheckBox jCheckBox3 = new JCheckBox();
  JCheckBox jComboBox5 = new JCheckBox("True");
  JCheckBox jCheckBox1 = new JCheckBox();
  JCheckBox jComboBox2 = new JCheckBox("True");
  JCheckBox jCheckBox2 = new JCheckBox();

  JTextField resulttextfield;
  JLabel resultlabel;

  public SEGREG_Dialog9_Resid(String title) {
    super(Frame1.mainFrame1, title, true);

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

    jCheckBox4.setFocusPainted(false);
    jComboBox4.setFocusPainted(false);
    jComboBox3.setFocusPainted(false);
    jCheckBox3.setFocusPainted(false);
    jComboBox5.setFocusPainted(false);
    jCheckBox1.setFocusPainted(false);
    jComboBox2.setFocusPainted(false);
    jCheckBox2.setFocusPainted(false);

    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(400, 360));
    this.setResizable(false);

    jTopPanel.setPreferredSize(new Dimension(400, 40));
    jCenterPanel.setPreferredSize(new Dimension(400, 280));
    jBottomPanel.setPreferredSize(new Dimension(400, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setLayout(xYLayout3);

    jButton1.setEnabled(true);

    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jLabel3.setText(
        "Specifies the residual correlations.");
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel1.setToolTipText("Specifies residual familial correlations.");
    jLabel1.setText("Option");
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(xYLayout4);
    jLabel4.setToolTipText("Specifies value of the residual correlation.");
    jLabel4.setText("Value");
    jCheckBox4.setToolTipText("Specifies the correlation between the residuals of siblings.");
    jCheckBox4.setText("Sib / Sib");
    jCheckBox4.addMouseListener(new SEGREG_Dialog9_Resid_jCheckBox4_mouseAdapter(this));
    jCheckBox4.addItemListener(this);
    jComboBox4.setEnabled(false);
    jComboBox4.setToolTipText("Option to fix the given value.");
    jComboBox4.setText("Fixed");
    jComboBox4.setFocusable(false);
    jComboBox4.addMouseListener(new SEGREG_Dialog9_Resid_jComboBox4_mouseAdapter(this));
    jTextField3.setEnabled(false);
    jTextField3.setText("0");
    jTextField3.addMouseListener(new SEGREG_Dialog9_Resid_jTextField3_mouseAdapter(this));
    jComboBox3.setEnabled(false);
    jComboBox3.setToolTipText("Option to fix the given value.");
    jComboBox3.setText("Fixed");
    jComboBox3.setFocusable(false);
    jComboBox3.addMouseListener(new SEGREG_Dialog9_Resid_jComboBox3_mouseAdapter(this));
    jCheckBox3.setToolTipText("<html>Specifies initial correlation between the residuals of father " +
    "and offspring.");
    jCheckBox3.setText("Father / Offspring");
    jCheckBox3.addMouseListener(new SEGREG_Dialog9_Resid_jCheckBox3_mouseAdapter(this));
    jCheckBox3.addItemListener(this);
    jLabel2.setToolTipText("Specifies value of the residual correlation.");
    jLabel2.setText("Value");
    jComboBox2.setEnabled(false);
    jComboBox2.setToolTipText("Option to fix the given value.");
    jComboBox2.setSelected(true);
    jComboBox2.setText("Fixed");
    jComboBox2.setFocusable(false);
    jComboBox2.addMouseListener(new SEGREG_Dialog9_Resid_jComboBox2_mouseAdapter(this));
    jTextField2.setEnabled(false);
    jTextField2.setText("0");
    jTextField2.addMouseListener(new SEGREG_Dialog9_Resid_jTextField2_mouseAdapter(this));
    jCheckBox2.setToolTipText("Specifies the correlation between the residuals of mother and offspring.");
    jCheckBox2.setText("Mother / Offspring");
    jCheckBox2.addMouseListener(new SEGREG_Dialog9_Resid_jCheckBox2_mouseAdapter(this));
    jCheckBox2.addItemListener(this);
    jLabel6.setToolTipText("Specifies value of the residual correlation.");
    jLabel6.setText("Value");
    jTextField1.setEnabled(false);
    jTextField1.setText("0");
    jTextField1.addMouseListener(new SEGREG_Dialog9_Resid_jTextField1_mouseAdapter(this));
    jCheckBox1.setToolTipText("Specifies the correlation between the residuals of father and mother.");
    jCheckBox1.setSelected(false);
    jCheckBox1.setText("Spousal");
    jCheckBox1.addMouseListener(new SEGREG_Dialog9_Resid_jCheckBox1_mouseAdapter(this));
    jCheckBox1.addItemListener(this);
    jTextField4.setEnabled(false);
    jTextField4.setText("0");
    jTextField4.addMouseListener(new SEGREG_Dialog9_Resid_jTextField4_mouseAdapter(this));
    jComboBox5.setEnabled(false);
    jComboBox5.setToolTipText("Option to fix the given value.");
    jComboBox5.setText("Fixed");
    jComboBox5.setFocusable(false);
    jComboBox5.addMouseListener(new SEGREG_Dialog9_Resid_jComboBox5_mouseAdapter(this));
    jLabel5.setToolTipText("Specifies value of the residual correlation.");
    jLabel5.setText("Value");
    jComboBox1.addMouseListener(new SEGREG_Dialog9_Resid_jComboBox1_mouseAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 301, 30));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1,  new XYConstraints(227, 7, 65, 25));
    jBottomPanel.add(jButton2,     new XYConstraints(305, 7, 65, 25));

    jCenterPanel.add(jLabel1,   new XYConstraints(15, 15, 40, 20));
    jCenterPanel.add(jComboBox1,         new XYConstraints(60, 15, 330, 20));
    jCenterPanel.add(jPanel1,          new XYConstraints(15, 50, 370, 140));

    jPanel1.add(jCheckBox1,              new XYConstraints(5, 5, 120, 20));
    jPanel1.add(jLabel2,            new XYConstraints(155, 5, 40, 20));
    jPanel1.add(jTextField1,                new XYConstraints(200, 5, 70, 20));
    jPanel1.add(jComboBox2,             new XYConstraints(295, 5, 65, 20));

    jPanel1.add(jCheckBox2,          new XYConstraints(5, 35, 150, 20));
    jPanel1.add(jLabel4,          new XYConstraints(155, 35, 40, 20));
    jPanel1.add(jTextField2,            new XYConstraints(200, 35, 70, 20));
    jPanel1.add(jComboBox3,          new XYConstraints(295, 35, 65, 20));

    jPanel1.add(jCheckBox3,          new XYConstraints(5, 65, 150, 20));
    jPanel1.add(jLabel5,          new XYConstraints(155, 65, 40, 20));
    jPanel1.add(jTextField3,            new XYConstraints(200, 65, 70, 20));
    jPanel1.add(jComboBox4,          new XYConstraints(295, 65, 65, 20));

    jPanel1.add(jCheckBox4,         new XYConstraints(5, 95, 120, 20));
    jPanel1.add(jLabel6,          new XYConstraints(155, 95, 40, 20));
    jPanel1.add(jTextField4,            new XYConstraints(200, 95, 70, 20));
    jPanel1.add(jComboBox5,          new XYConstraints(295, 95, 65, 20));

    jComboBox1.setFocusable(false);

    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);
    jComboBox3.addItemListener(this);
    jComboBox4.addItemListener(this);
    jComboBox5.addItemListener(this);
    jButton2.addActionListener(this);
    jTextField3.getDocument().addDocumentListener(this);
    jTextField2.getDocument().addDocumentListener(this);
    jTextField1.getDocument().addDocumentListener(this);
    jTextField4.getDocument().addDocumentListener(this);

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
    Datamodel.setValue("resid_fm_val", "0");
    Datamodel.setValue("resid_mo_val", "0");
    Datamodel.setValue("resid_fo_val", "0");
    Datamodel.setValue("resid_ss_val", "0");
    Datamodel.setValue("resid_fm_fixed", "true");
    Datamodel.setValue("resid_mo_fixed", "false");
    Datamodel.setValue("resid_fo_fixed", "false");
    Datamodel.setValue("resid_ss_fixed", "false");
    Datamodel.setValue("resid_option", "equal_po_ss");

    jCheckBox2.setEnabled(false);
    jCheckBox3.setEnabled(false);
  }

  public void save_init_state()
  {
    Datamodel.setValue("resid_info", "use");
    Datamodel.setValue("resid", "use");
    resulttextfield.setText("Specified");
    resultlabel.setFont(new java.awt.Font("Dialog", 1, 11));
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
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      if (jTextField1.getText().length() <= 0) {
        jComboBox2.setEnabled(false);
        Datamodel.setValue("resid_fm_val", "0");
      }
      else {
        jComboBox2.setEnabled(true);
        Datamodel.setValue("resid_fm_val", jTextField1.getText());
      }
    }
    if (document == jTextField2.getDocument()) {
      if (jTextField2.getText().length() <= 0) {
        jComboBox3.setEnabled(false);
        Datamodel.setValue("resid_mo_val", "0");
      }
      else {
        jComboBox3.setEnabled(true);
        Datamodel.setValue("resid_mo_val", jTextField2.getText());
      }
    }
    if (document == jTextField3.getDocument()) {
      if (jTextField3.getText().length() <= 0) {
        jComboBox4.setEnabled(false);
        Datamodel.setValue("resid_fo_val", "0");
      }
      else {
        jComboBox4.setEnabled(true);
        Datamodel.setValue("resid_fo_val", jTextField3.getText());
      }
    }
    if (document == jTextField4.getDocument()) {
      if (jTextField4.getText().length() <= 0) {
        jComboBox5.setEnabled(false);
        Datamodel.setValue("resid_ss_val", "0");
      }
      else {
        jComboBox5.setEnabled(true);
        Datamodel.setValue("resid_ss_val", jTextField4.getText());
      }
    }

  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if (ob == jComboBox1) {
      Datamodel.setValue("resid_option",
                         option_real[jComboBox1.getSelectedIndex()]);
      int index = jComboBox1.getSelectedIndex();

      switch (index) {
        case 0: //equal_po_ss
          jCheckBox2.setEnabled(false);
          jCheckBox3.setEnabled(false);
          jCheckBox4.setEnabled(true);
          break;
        case 1: //equal_po
          jCheckBox2.setEnabled(true);
          jCheckBox3.setEnabled(false);
          jCheckBox4.setEnabled(true);
          break;
        case 2: //arb
          jCheckBox2.setEnabled(true);
          jCheckBox3.setEnabled(true);
          jCheckBox4.setEnabled(true);
          break;
      }
    }

    if (ob == jComboBox2) {
      if(jComboBox2.isSelected())
        Datamodel.setValue("resid_fm_fixed","true");
      else
        Datamodel.setValue("resid_fm_fixed","false");
    }
    if (ob == jComboBox3) {
      if(jComboBox3.isSelected())
        Datamodel.setValue("resid_mo_fixed","true");
      else
        Datamodel.setValue("resid_mo_fixed","false");
    }
    if (ob == jComboBox4) {
      if(jComboBox4.isSelected())
        Datamodel.setValue("resid_fo_fixed","true");
      else
        Datamodel.setValue("resid_fo_fixed","false");
    }
    if (ob == jComboBox5) {
      if(jComboBox5.isSelected())
        Datamodel.setValue("resid_ss_fixed","true");
      else
        Datamodel.setValue("resid_ss_fixed","false");
    }

    if (ob == jCheckBox1) {
      if (jCheckBox1.isSelected()) {
        jTextField1.setEnabled(true);
        jComboBox2.setEnabled(true);
        Datamodel.setValue("resid_fm", "use");
      }
      else {
        jTextField1.setEnabled(false);
        jComboBox2.setEnabled(false);
        Datamodel.removeValue("resid_fm");
      }
    }

    if (ob == jCheckBox2) {
      if (jCheckBox2.isSelected()) {
        jTextField2.setEnabled(true);
        jComboBox3.setEnabled(true);
        Datamodel.setValue("resid_mo", "use");
      }
      else {
        jTextField2.setEnabled(false);
        jComboBox3.setEnabled(false);
        Datamodel.removeValue("resid_mo");
      }
    }

    if (ob == jCheckBox3) {
      if (jCheckBox3.isSelected()) {
        jTextField3.setEnabled(true);
        jComboBox4.setEnabled(true);
        Datamodel.setValue("resid_fo", "use");
      }
      else {
        jTextField3.setEnabled(false);
        jComboBox4.setEnabled(false);
        Datamodel.removeValue("resid_fo");
      }
    }

    if (ob == jCheckBox4) {
      if (jCheckBox4.isSelected()) {
        jTextField4.setEnabled(true);
        jComboBox5.setEnabled(true);
        Datamodel.setValue("resid_ss", "use");
      }
      else {
        jTextField4.setEnabled(false);
        jComboBox5.setEnabled(false);
        Datamodel.removeValue("resid_ss");
      }
    }

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 165);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 165);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fm", false, 165);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fm", false, 165);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fm", false, 165);
  }

  void jCheckBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("mo", false, 165);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("mo", false, 165);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("mo", false, 165);
  }

  void jCheckBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fo", false, 166);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fo", false, 166);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fo", false, 166);
  }

  void jCheckBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("ss", false, 166);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("ss", false, 166);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("ss", false, 166);
  }
}

class SEGREG_Dialog9_Resid_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jComboBox1_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jCheckBox1_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jTextField1_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jComboBox2_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jCheckBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jCheckBox2_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox2_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jTextField2_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jComboBox3_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jCheckBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jCheckBox3_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox3_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jTextField3_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jComboBox4_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jCheckBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jCheckBox4_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox4_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jTextField4_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class SEGREG_Dialog9_Resid_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog9_Resid adaptee;

  SEGREG_Dialog9_Resid_jComboBox5_mouseAdapter(SEGREG_Dialog9_Resid adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}
