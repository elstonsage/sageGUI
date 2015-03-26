package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog13_Ascer
    extends JDialog
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
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

  String[] option = {
      "Actual", "By onset"};
  String[] option_real = {
      "actual", "by_onset"};
  String[] cond_set = {
      "None", "Founders", "Proband sampling frame(psf)", "Founders plus psf"};
  String[] cond_set_resl = {
      "none", "founders", "psf", "founders_plus_psf"};
  String[] cond_val = {
      "Condition on actual value", "Condition being greater than threshold",
      "Conditing being less than threshold", "Use threshold indicator"};
  String[] cond_val_resl = {
      "actual", "gte_thresh", "lte_thresh", "thresh_indic"};

  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  JLabel jLabel1 = new JLabel();
  JComboBox jComboBox1 = new JComboBox(cond_set);
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JComboBox jComboBox2 = new JComboBox(cond_val);
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel11 = new JLabel();
  JComboBox jComboBox3 = new JComboBox(option);
  JLabel jLabel12 = new JLabel();
  JComboBox jComboBox4 = new JComboBox();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JComboBox jComboBox5 = new JComboBox();
  JButton jButton2 = new JButton();

  JTextField resulttextfield;
  JLabel resultlabel;

  public SEGREG_Dialog13_Ascer(String title) {
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
    titledBorder1 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 460));
    this.setResizable(false);

    jButton1.setEnabled(true);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(330, 350));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jBottomPanel.setLayout(xYLayout3);

    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jLabel3.setText("Specifies the pedigree ascertainment options.");
    jLabel1.setToolTipText(
        "<html>Specifies the subset of persons on whom conditioning<br>will be performed.");
    jLabel1.setText("Conditional set");
    jLabel2.setToolTipText("Specifies the proband sampling frame indicator.");
    jLabel2.setText("Proband sampling frame(psf) indicator");
    jLabel4.setToolTipText(
        "<html>Value of the proband sampling frame indicator that is interpreted " +
        "<br>to mean an individual is included in the proband sampling frame.");
    jLabel4.setText("Proband sampling frame indicator value");
    jTextField1.setEnabled(false);
    jTextField1.setText("1");
    jTextField1.addMouseListener(new SEGREG_Dialog13_Ascer_jTextField1_mouseAdapter(this));
    jLabel5.setToolTipText(
        "<html>Specifies how a trait value is used to determine" +
        "<br>the conditioning on a person\'s phenotype.");
    jLabel5.setText("Option");
    jLabel9.setToolTipText("Specifies the threshold indicator variable.");
    jLabel9.setText("Threshold indicator variable");
    jLabel10.setToolTipText(
        "Specifies the cutoff value when using threshold indicator.");
    jLabel10.setText("Threshold indicator value");
    jLabel11.setToolTipText(
        "<html>Specifies the type of conditioning when a binary trait with " +
        "<br>variable age of onset is being analyzed.");
    jLabel11.setText("Onset option");
    jTextField2.setEnabled(false);
    jTextField2.setText("0");
    jTextField2.addMouseListener(new SEGREG_Dialog13_Ascer_jTextField2_mouseAdapter(this));
    jLabel12.setToolTipText(
        "<html>Value of the proband sampling frame indicator that is interpreted " +
        "<br>to mean an individual is included in the proband sampling frame.");
    jLabel12.setText("");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel8.setToolTipText(
        "<html>Specifies the value for the less-than-or-equal-to threshold " +
        "<br>if conditional value is set to threshold indicator.");
    jLabel8.setText("Lower than");
    jLabel6.setToolTipText(
        "<html>Threshold value to be used if conditional value is" +
        "<br>greater than threshold or less than threshold.");
    jLabel6.setText("Threshold");
    jLabel7.setToolTipText(
        "<html>Specifies the value for the greater-than-or-equal-to threshold " +
        "<br>if conditional value is set to threshold indicator.");
    jLabel7.setText("Higher than");
    jTextField3.setEnabled(false);
    jTextField3.setText("");
    jTextField3.addMouseListener(new SEGREG_Dialog13_Ascer_jTextField3_mouseAdapter(this));
    jTextField4.setEnabled(false);
    jTextField4.setText("");
    jTextField4.addMouseListener(new SEGREG_Dialog13_Ascer_jTextField4_mouseAdapter(this));
    jTextField5.setEnabled(false);
    jTextField5.addMouseListener(new SEGREG_Dialog13_Ascer_jTextField5_mouseAdapter(this));
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jComboBox5.addMouseListener(new SEGREG_Dialog13_Ascer_jComboBox5_mouseAdapter(this));
    jComboBox4.addMouseListener(new SEGREG_Dialog13_Ascer_jComboBox4_mouseAdapter(this));
    jComboBox1.addMouseListener(new SEGREG_Dialog13_Ascer_jComboBox1_mouseAdapter(this));
    jComboBox3.addMouseListener(new SEGREG_Dialog13_Ascer_jComboBox3_mouseAdapter(this));
    jComboBox2.addMouseListener(new SEGREG_Dialog13_Ascer_jComboBox2_mouseAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 292, 30));
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jCenterPanel.add(jLabel2, new XYConstraints(15, 15, 209, 20));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 135, 295, 162));
    jPanel1.add(jLabel9, new XYConstraints(5, 5, 139, 20));
    jPanel1.add(jLabel7, new XYConstraints(5, 95, 67, 20));
    jPanel1.add(jLabel6, new XYConstraints(5, 65, 70, 20));
    jPanel1.add(jLabel10, new XYConstraints(5, 35, 144, 20));
    jPanel1.add(jComboBox5, new XYConstraints(175, 5, 100, 20));
    jPanel1.add(jTextField2, new XYConstraints(175, 35, 100, 20));
    jPanel1.add(jTextField3, new XYConstraints(175, 65, 100, -1));
    jPanel1.add(jLabel8, new XYConstraints(5, 125, 65, 20));
    jPanel1.add(jTextField4, new XYConstraints(175, 95, 100, -1));
    jPanel1.add(jTextField5, new XYConstraints(175, 125, 100, -1));
    jCenterPanel.add(jLabel12, new XYConstraints(15, 52, -1, -1));
    jCenterPanel.add(jComboBox1, new XYConstraints(105, 75, 205, 20));
    jCenterPanel.add(jComboBox4, new XYConstraints(235, 15, 75, 20));
    jCenterPanel.add(jLabel4, new XYConstraints(15, 45, 204, 20));
    jCenterPanel.add(jTextField1, new XYConstraints(235, 45, 75, 20));
    jCenterPanel.add(jLabel11, new XYConstraints(15, 105, 66, 20));
    jCenterPanel.add(jLabel1, new XYConstraints(15, 75, 84, 20));
    jCenterPanel.add(jComboBox3, new XYConstraints(105, 105, 205, 20));
    jCenterPanel.add(jComboBox2, new XYConstraints(105, 308, 205, 20));
    jCenterPanel.add(jLabel5, new XYConstraints(18, 308, 83, 20));

    jComboBox1.setFocusable(false);
    jComboBox2.setFocusable(false);
    jComboBox3.setFocusable(false);

    jComboBox2.addItemListener(this);
    jComboBox5.addItemListener(this);
    jComboBox4.addItemListener(this);

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

  public void save_init_state()
  {
    Datamodel.setValue("ascer_cond_set",
                       cond_set_resl[jComboBox1.getSelectedIndex()]);
    if (jComboBox4.getSelectedItem().toString().length() > 0) {
      Datamodel.setValue("ascer_psf_indic",
                         jComboBox4.getSelectedItem().toString());
      if (jTextField1.getText().length() > 0)
        Datamodel.setValue("ascer_psf_indic_include", jTextField1.getText());
    }

    if (jComboBox5.getSelectedItem().toString().length() > 0) {
      Datamodel.setValue("ascer_thresh_indic",
                         jComboBox5.getSelectedItem().toString());
      if (jTextField2.getText().length() > 0)
        Datamodel.setValue("ascer_thresh_indic_thresh", jTextField2.getText());
    }
    Datamodel.setValue("ascer_onset_option",
                       option_real[jComboBox3.getSelectedIndex()]);

    int index = jComboBox2.getSelectedIndex();
    String val = cond_val_resl[jComboBox2.getSelectedIndex()];
    switch (index) {
      case 0:
        Datamodel.setValue("ascer_cond_val", val);
        break;
      case 1:
        Datamodel.setValue("ascer_cond_val", val);
        if (jTextField3.getText().length() > 0)
          Datamodel.setValue("ascer_cond_val_thresh", jTextField3.getText());
        break;
      case 2:
        Datamodel.setValue("ascer_cond_val", val);
        if (jTextField3.getText().length() > 0)
          Datamodel.setValue("ascer_cond_val_thresh", jTextField3.getText());
        break;
      case 3:
        Datamodel.setValue("ascer_cond_val", val);
        if (jTextField4.getText().length() > 0)
          Datamodel.setValue("ascer_cond_val_thresh_high",
                             jTextField4.getText());
        if (jTextField5.getText().length() > 0)
          Datamodel.setValue("ascer_cond_val_thresh_low", jTextField5.getText());
        break;
    }
    Datamodel.setValue("ascertainment_info", "use");
    Datamodel.setValue("ascertainment", "use");

    resulttextfield.setText("Specified");
    resultlabel.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jButton1) {
      save_init_state();
      dispose();
    }
    else if (e.getSource() == jButton2) {
       dispose();
    }

  }

  public void changedUpdate(DocumentEvent event) {

  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if (ob == jComboBox2) {
      int index = jComboBox2.getSelectedIndex();
      switch (index) {
        case 1:
        case 2:
          jTextField3.setEnabled(true);
          jTextField4.setEnabled(false);
          jTextField5.setEnabled(false);
          break;
        case 3:
          jTextField4.setEnabled(true);
          jTextField5.setEnabled(true);
          jTextField3.setEnabled(false);
          break;
      }

    }
    if (ob == jComboBox4) {
      if (jComboBox4.getSelectedIndex() == 0) {
        jTextField1.setEnabled(false);
      }
      else {
        jTextField1.setEnabled(true);
      }
    }

    if (ob == jComboBox5) {
      if (jComboBox5.getSelectedIndex() == 0) {
        jTextField2.setEnabled(false);
      }
      else {
        jTextField2.setEnabled(true);
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("psf_indic", false, 174);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("cond_set", false, 174);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("psf_indic", false, 174);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("psf_indic_include", false, 174);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("cond_val", false, 174);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("thresh", false, 175);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("thresh_indic_high", false, 175);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("thresh_indic_low", false, 175);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("thresh_indic", false, 175);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("thresh_indic", false, 175);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("onset_option", false, 175);
  }
}

class SEGREG_Dialog13_Ascer_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jComboBox4_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class SEGREG_Dialog13_Ascer_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jTextField1_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog13_Ascer_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jComboBox1_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog13_Ascer_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jComboBox2_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}
class SEGREG_Dialog13_Ascer_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jComboBox3_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class SEGREG_Dialog13_Ascer_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jComboBox5_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}

class SEGREG_Dialog13_Ascer_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jTextField2_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SEGREG_Dialog13_Ascer_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jTextField3_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG_Dialog13_Ascer_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jTextField4_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class SEGREG_Dialog13_Ascer_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog13_Ascer adaptee;

  SEGREG_Dialog13_Ascer_jTextField5_mouseAdapter(SEGREG_Dialog13_Ascer adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}
