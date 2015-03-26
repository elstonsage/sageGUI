package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DECIPHER_Dialog2_Tasks
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

  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel10 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel11 = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JButton jButton2 = new JButton();

  JLabel jLabel13 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JCheckBox jComboBox1 = new JCheckBox("True");
  JCheckBox jComboBox6 = new JCheckBox("True");
  JCheckBox jComboBox3 = new JCheckBox("True");
  JCheckBox jComboBox4 = new JCheckBox("True");
  JCheckBox jComboBox5 = new JCheckBox("True");

  DECIPHER2 parent;

  public DECIPHER_Dialog2_Tasks(DECIPHER2 parent, String title) {
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
    panel1.setLayout(borderLayout1);

    jComboBox1.setFocusPainted(false);
    jComboBox6.setFocusPainted(false);
    jComboBox3.setFocusPainted(false);
    jComboBox4.setFocusPainted(false);
    jComboBox5.setFocusPainted(false);

    this.setSize(new Dimension(330, 450));
    this.setResizable(false);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(new XYLayout());
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(new XYLayout());
    jCenterPanel.setPreferredSize(new Dimension(330, 370));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setLayout(new XYLayout());


    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jLabel3.setText("Specifies the tasks.");
    jTextField1.setText("0.001");
    jTextField1.addMouseListener(new DECIPHER_Dialog2_Tasks_jTextField1_mouseAdapter(this));
    jLabel10.setEnabled(false);
    jLabel10.setToolTipText(
      "Specifies an exact number of permutations to be performed.");
    jLabel10.setText("Permutations");
    jLabel11.setToolTipText(
            "<html>Specifies the minimum frequency threshold value"+
            "<br>for displaying haplotype frequencies.");
    jLabel11.setText("Cutoff");
    jTextField2.setEnabled(false);
    jTextField2.setText("");
    jTextField2.addMouseListener(new DECIPHER_Dialog2_Tasks_jTextField2_mouseAdapter(this));
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel8.setEnabled(false);
    jLabel8.setToolTipText(
            "<html>Specifies the confidence with which an empirical P-value is" +
            "<br>required to be within a relative interval of its true value.");
    jLabel8.setText("Confidence");
    jLabel6.setEnabled(false);
    jLabel6.setToolTipText(
      "Specifies the maximum number of permutations that should be performed.");
    jLabel6.setText("Max permutations");
    jLabel7.setEnabled(false);
    jLabel7.setToolTipText(
      "Specifies the relative precision of the empirical P-value.");
    jLabel7.setText("Width of P-values");
    jTextField3.setEnabled(false);
    jTextField3.setText("10000");
    jTextField3.addMouseListener(new DECIPHER_Dialog2_Tasks_jTextField3_mouseAdapter(this));
    jTextField4.setEnabled(false);
    jTextField4.setText("0.2");
    jTextField4.addMouseListener(new DECIPHER_Dialog2_Tasks_jTextField4_mouseAdapter(this));
    jTextField5.setEnabled(false);
    jTextField5.setText("0.95");
    jTextField5.addMouseListener(new DECIPHER_Dialog2_Tasks_jTextField5_mouseAdapter(this));
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jLabel13.setEnabled(false);
    jLabel13.setToolTipText(
            "<html>specifies numimum haplotype combination posterior"+
            "<br>probability threshold value for display.");
    jLabel13.setText("Cutoff");
    jTextField6.setEnabled(false);
    jTextField6.setText("0.05");
    jTextField6.addMouseListener(new DECIPHER_Dialog2_Tasks_jTextField6_mouseAdapter(this));
    jComboBox1.setToolTipText("Estimate population haplotype frequencies.");
    jComboBox1.setMargin(new Insets(2, 0, 2, 2));
    jComboBox1.setSelected(true);
    jComboBox1.setText("Estimate population haplotype frequencies");
    jComboBox1.setFocusable(false);
    jComboBox1.addMouseListener(new DECIPHER_Dialog2_Tasks_jComboBox1_mouseAdapter(this));
    jComboBox4.addMouseListener(new DECIPHER_Dialog2_Tasks_jComboBox4_mouseAdapter(this));
    jComboBox6.addMouseListener(new DECIPHER_Dialog2_Tasks_jComboBox6_mouseAdapter(this));
    jComboBox3.addMouseListener(new DECIPHER_Dialog2_Tasks_jComboBox3_mouseAdapter(this));
    jComboBox3.setToolTipText("Option to display all haplotype combinations for each analysis unit.");
    jComboBox3.setMargin(new Insets(2, 0, 2, 2));
    jComboBox3.setText("Display all possible haplotype combinations ");
    jComboBox4.setToolTipText("<html>Option to display the most likely haplotype combinations<br>for each analysis unit.");
    jComboBox4.setMargin(new Insets(2, 0, 2, 2));
    jComboBox4.setText("Display most likely haplotype combinations");
    jComboBox6.setToolTipText("Option for perform likelihood ratio test.");
    jComboBox6.setMargin(new Insets(2, 0, 2, 2));
    jComboBox6.setText("Perform likelihood ratio test");
    jComboBox5.setToolTipText("Compute empirical P-values by permutation.");
    jComboBox5.setMargin(new Insets(2, 0, 2, 2));
    jComboBox5.setText("Compute empirical P-value");
    jComboBox5.addMouseListener(new DECIPHER_Dialog2_Tasks_jComboBox5_mouseAdapter(this));
    jComboBox5.addItemListener(this);
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3,  new XYConstraints(15, 3, 292, 30));
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jPanel1.add(jLabel7,    new XYConstraints(5, 65, 139, 20));
    jPanel1.add(jLabel6,   new XYConstraints(5, 35, 160, 20));
    jPanel1.add(jLabel10,  new XYConstraints(5, 5, 144, 20));
    jPanel1.add(jTextField2,    new XYConstraints(173, 5, 110, 20));
    jPanel1.add(jTextField3,     new XYConstraints(173, 35, 110, -1));
    jPanel1.add(jLabel8,  new XYConstraints(5, 95, 65, 20));
    jPanel1.add(jTextField4,    new XYConstraints(173, 65, 110, -1));
    jPanel1.add(jTextField5,    new XYConstraints(173, 95, 110, 20));
    jCenterPanel.add(jTextField6,       new XYConstraints(200, 135, 110, 20));
    jCenterPanel.add(jLabel13,    new XYConstraints(35, 135, 63, 20));
    jCenterPanel.add(jPanel1,       new XYConstraints(15, 225, 300, 133));
    jCenterPanel.add(jComboBox4,      new XYConstraints(15, 105, 300, 20));
    jCenterPanel.add(jLabel11,     new XYConstraints(35, 45, 66, 20));
    jCenterPanel.add(jTextField1,    new XYConstraints(200, 45, 110, 20));
    jCenterPanel.add(jComboBox1,      new XYConstraints(15, 15, 300, 20));
    jCenterPanel.add(jComboBox3,      new XYConstraints(15, 75, 300, 20));
    jCenterPanel.add(jComboBox6,         new XYConstraints(15, 165, 300, 20));
    jCenterPanel.add(jComboBox5,        new XYConstraints(15, 195, 200, 20));

    jComboBox1.addItemListener(this);
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
    if (jComboBox1.isSelected())
    {
      Datamodel.setValue("pop_freq", "true");
      if (jTextField1.getText().length() > 0)
        Datamodel.setValue("pop_freq_cutoff", jTextField1.getText());
    }
    else
    {
        Datamodel.setValue("pop_freq", "false");
    }

    if(jComboBox3.isSelected())
      Datamodel.setValue("all_possible_diplotypes_table", "true");
    else
      Datamodel.setValue("all_possible_diplotypes_table", "false");


    if(jComboBox4.isSelected())
    {
      Datamodel.setValue("most_likely_diplotypes", "true");
      if(jTextField6.getText().length()>0)
        Datamodel.setValue("most_likely_cutoff", jTextField6.getText());
    }
    else
      Datamodel.setValue("most_likely_diplotypes", "false");

    if(jComboBox6.isSelected())
      Datamodel.setValue("likelihood_ratio_test", "true");
    else
      Datamodel.setValue("likelihood_ratio_test", "false");

    if(jComboBox5.isSelected())
    {
      Datamodel.setValue("compute_p_value", "true");
      if (jTextField2.getText().length() > 0)
        Datamodel.setValue("permutations", jTextField2.getText());
      if (jTextField3.getText().length() > 0)
        Datamodel.setValue("max_permutations", jTextField3.getText());
      if (jTextField4.getText().length() > 0)
        Datamodel.setValue("width", jTextField4.getText());
      if (jTextField5.getText().length() > 0)
        Datamodel.setValue("confidence", jTextField5.getText());
    }
    else
      Datamodel.setValue("compute_p_value", "false");

    Datamodel.setValue("tasks", "use");
    parent.jTextField1.setText("Specified");
    parent.jLabel4.setFont(new java.awt.Font("Dialog", 1, 11));
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
    if(ob == jComboBox1)
    {
      if(jComboBox1.isSelected())
      {
        jLabel11.setEnabled(true);
        jTextField1.setEnabled(true);
      }
      else
      {
        jLabel11.setEnabled(false);
        jTextField1.setEnabled(false);
      }
    }
    if(ob == jComboBox4)
    {
      if(jComboBox4.isSelected())
      {
        jLabel13.setEnabled(true);
        jTextField6.setEnabled(true);
      }
      else
      {
        jLabel13.setEnabled(false);
        jTextField6.setEnabled(false);
      }
    }

    if (ob == jComboBox5) {
      if(jComboBox5.isSelected())//true
      {
        jTextField2.setEnabled(true);
        jTextField3.setEnabled(true);
        jTextField4.setEnabled(true);
        jTextField5.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel7.setEnabled(true);
        jLabel8.setEnabled(true);
        jLabel10.setEnabled(true);
      }
      else//false
      {
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jTextField5.setEnabled(false);
        jLabel6.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel8.setEnabled(false);
        jLabel10.setEnabled(false);
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("pop_freq", false, 415);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pop_freq", false, 415);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pop_freq", false, 415);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("all_possible_combinations_table", false, 415);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("most_likely_diplotypes", false, 415);
  }

  void jTextField6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("most_likely_diplotypes", false, 415);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("likelihood_ratio_tes", false, 415);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("compute_empirical_pvalue", false, 416);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", permutation", false, 416);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("max_permutation", false, 416);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", width ", false, 416);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("confidenc", false, 416);
  }
}

class DECIPHER_Dialog2_Tasks_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jComboBox1_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jComboBox3_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jComboBox4_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jTextField6_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jTextField6_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField6_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jComboBox6_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jComboBox6_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jTextField1_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jTextField2_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jTextField3_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jTextField4_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jTextField5_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class DECIPHER_Dialog2_Tasks_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog2_Tasks adaptee;

  DECIPHER_Dialog2_Tasks_jComboBox5_mouseAdapter(DECIPHER_Dialog2_Tasks adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}
