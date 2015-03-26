package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog11_Geno
    extends JDialog
    implements java.awt.event.ItemListener, ActionListener, DocumentListener {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton okButton = new JButton();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;

  DataCollectionModel Datamodel;
  String[] option = {
      "HWE", "No HWE"};
  String[] option_real = {
      "hwe", "nhwe"};
  String[] probs = {
      "", "AA", "AB", "BB"};

  JLabel jLabel1 = new JLabel();
  JComboBox optionComboBox = new JComboBox(option);
  JLabel jLabel4 = new JLabel();
  JComboBox prob1ComboBox = new JComboBox(probs);
  JComboBox prob2ComboBox = new JComboBox(probs);
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JTextField prob1valTextField = new JTextField();
  JTextField prob2valTextField = new JTextField();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel8 = new JLabel();
  JTextField freqAvalTextField = new JTextField();
  JButton cancelButton = new JButton();

  JTextField resulttextfield;
  JLabel resultlabel1;
  JLabel resultlabel2;
  JLabel jLabel2 = new JLabel();
  JCheckBox freqACheckBox = new JCheckBox();
  JCheckBox probsfixedCheckBox = new JCheckBox("True");

  public SEGREG_Dialog11_Geno(String title) {
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
    this.setSize(new Dimension(330, 360));
    this.setResizable(false);

    okButton.setEnabled(true);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(330, 280));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jBottomPanel.setLayout(xYLayout3);

    okButton.setText("OK");
    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jLabel3.setText("Specifies the founder genotype frequency model.");
    jLabel1.setToolTipText(
        "<html>Specifies whether Hardy Weinberg equilibrium proportions" +
        "<br>are to be assumed.");
    jLabel1.setText("Option");
    jLabel4.setEnabled(false);
    jLabel4.setToolTipText("Specifies probability of a given genotype.");
    jLabel4.setText("Genotype probability");
    jLabel5.setEnabled(false);
    jLabel5.setToolTipText("Specifies probability of a given genotype.");
    jLabel5.setText("Genotype probability");
    jLabel6.setEnabled(false);
    jLabel6.setToolTipText("Specifies value for given probability type.");
    jLabel6.setText("Value");
    jLabel7.setEnabled(false);
    jLabel7.setToolTipText("Specifies value for given probability type.");
    jLabel7.setText("Value");
    prob1valTextField.setEnabled(false);
    prob1valTextField.setText("");
    prob1valTextField.addMouseListener(new SEGREG_Dialog11_Geno_jTextField1_mouseAdapter(this));
    prob2valTextField.setEnabled(false);
    prob2valTextField.setText("");
    prob2valTextField.addMouseListener(new SEGREG_Dialog11_Geno_jTextField2_mouseAdapter(this));

    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel8.setToolTipText("Specifies value for the allele frequency.");
    jLabel8.setText("Value");
    freqAvalTextField.setText("");
    freqAvalTextField.addMouseListener(new SEGREG_Dialog11_Geno_jTextField3_mouseAdapter(this));
    cancelButton.setText("Cancel");
    cancelButton.setMargin(new Insets(2, 2, 2, 2));
    optionComboBox.addMouseListener(new SEGREG_Dialog11_Geno_jComboBox1_mouseAdapter(this));
    prob1ComboBox.addMouseListener(new SEGREG_Dialog11_Geno_jComboBox3_mouseAdapter(this));
    prob2ComboBox.addMouseListener(new SEGREG_Dialog11_Geno_jComboBox4_mouseAdapter(this));
    prob1ComboBox.setEnabled(false);
    prob2ComboBox.setEnabled(false);
    jLabel2.setText("Other options");
    freqACheckBox.setToolTipText("<html>Specifies the relative frequency of allele A.<br>" +
        "Requires HWE and no genotype probability specified.");
    freqACheckBox.setMargin(new Insets(2, 0, 2, 2));
    freqACheckBox.setSelected(true);
    freqACheckBox.setText("Allele A frequency");
    freqACheckBox.addMouseListener(new SEGREG_Dialog11_Geno_jCheckBox1_mouseAdapter(this));
    freqACheckBox.addItemListener(this);
    freqACheckBox.setFocusable(false);
    probsfixedCheckBox.setToolTipText("<html>Option to fix or not fix genotype probabilities or" +
    "<br>the probability (relative frequency) of allele A.");
    probsfixedCheckBox.setMargin(new Insets(2, 0, 2, 2));
    probsfixedCheckBox.setText("Fixed probabilities");
    probsfixedCheckBox.setFocusable(false);
    probsfixedCheckBox.addMouseListener(new SEGREG_Dialog11_Geno_jComboBox2_mouseAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 295, 30));
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(okButton, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(cancelButton, new XYConstraints(243, 7, 65, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jCenterPanel.add(jLabel1, new XYConstraints(15, 15, 52, 20));
    jCenterPanel.add(optionComboBox,  new XYConstraints(120, 15, 195, 20));
    jCenterPanel.add(jLabel4,  new XYConstraints(15, 45, 104, 20));
    jCenterPanel.add(jLabel5,  new XYConstraints(15, 75, 104, 20));
    jCenterPanel.add(prob1valTextField,   new XYConstraints(250, 45, 65, 20));
    jCenterPanel.add(prob2valTextField,   new XYConstraints(250, 75, 65, 20));
    jPanel1.add(freqACheckBox,      new XYConstraints(2, 32, 120, 20));
    jPanel1.add(freqAvalTextField,         new XYConstraints(100, 62, 80, 20));
    jPanel1.add(jLabel8,       new XYConstraints(20, 62, 50, 20));
    jPanel1.add(probsfixedCheckBox,     new XYConstraints(2, 2, 168, 20));
    jCenterPanel.add(jLabel2,  new XYConstraints(15, 105, 97, 20));
    jCenterPanel.add(jLabel7,   new XYConstraints(210, 75, 43, 20));
    jCenterPanel.add(jLabel6,   new XYConstraints(210, 45, 44, 20));
    jCenterPanel.add(prob2ComboBox,    new XYConstraints(120, 75, 80, 20));
    jCenterPanel.add(prob1ComboBox,    new XYConstraints(120, 45, 80, 20));
    jCenterPanel.add(jPanel1,              new XYConstraints(120, 105, 195, 100));

    optionComboBox.setFocusable(false);
    prob1ComboBox.setFocusable(false);
    prob2ComboBox.setFocusable(false);

    optionComboBox.addItemListener(this);
    prob1ComboBox.addItemListener(this);
    prob2ComboBox.addItemListener(this);

    freqAvalTextField.getDocument().addDocumentListener(this);

    okButton.setEnabled(false);

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
    Datamodel.setValue("geno_option", option_real[optionComboBox.getSelectedIndex()]);
    if (prob1ComboBox.getSelectedIndex() > 0) {
      Datamodel.setValue("geno_prob1", prob1ComboBox.getSelectedItem().toString());
      if (prob1valTextField.getText().length() > 0)
        Datamodel.setValue("geno_prob1_val", prob1valTextField.getText());
    }
    if (prob2ComboBox.getSelectedIndex() > 0) {
      Datamodel.setValue("geno_prob2", prob2ComboBox.getSelectedItem().toString());
      if (prob2valTextField.getText().length() > 0)
        Datamodel.setValue("geno_prob2_val", prob2valTextField.getText());
    }
    if(probsfixedCheckBox.isSelected())
      Datamodel.setValue("geno_prob_fixed","true");
    else
      Datamodel.setValue("geno_prob_fixed","false");

    if (freqACheckBox.isSelected()) {
      Datamodel.setValue("geno_allele_freq_A", "use");
      if (freqAvalTextField.getText().length() > 0)
        Datamodel.setValue("geno_allele_freq_A_val", freqAvalTextField.getText());
    }

    Datamodel.setValue("geno_freq_info", "use");
    Datamodel.setValue("geno_freq", "use");

    resulttextfield.setText("Specified");
    resultlabel1.setFont(new java.awt.Font("Dialog", 1, 11));
    resultlabel2.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  void setOKbutton()
  {
      if (freqACheckBox.isSelected()) {
          if (freqAvalTextField.getText().length() <= 0) {
              okButton.setEnabled(false);
          } else {
              okButton.setEnabled(true);
          }
      }
      else
          okButton.setEnabled(true);

  }

  public void changedUpdate(DocumentEvent event) {
      Document document = event.getDocument();
      if (document == freqAvalTextField.getDocument()) {
          setOKbutton();
      }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == okButton) {
      save_init_state();
      dispose();
    }
    else if (e.getSource() == cancelButton) {
       dispose();
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if (ob == freqACheckBox) {
      if (freqACheckBox.isSelected()) {
        freqAvalTextField.setEnabled(true);

      }
      else {
        freqAvalTextField.setEnabled(false);
      }

      setOKbutton();
    }

    else if (ob == optionComboBox) {
      if (optionComboBox.getSelectedIndex() == 0) //hwe
      {
        jLabel4.setEnabled(false);
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        jLabel7.setEnabled(false);
        prob1valTextField.setEnabled(false);
        prob2valTextField.setEnabled(false);
        prob1ComboBox.setEnabled(false);
        prob2ComboBox.setEnabled(false);
        freqACheckBox.setSelected(true);
      }
      else //no hwe
      {
        jLabel4.setEnabled(true);
        jLabel5.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel7.setEnabled(true);
        prob1ComboBox.setEnabled(true);
        prob2ComboBox.setEnabled(true);
        freqACheckBox.setSelected(false);
      }
    }

    else if (ob == prob1ComboBox) {
      if (prob1ComboBox.getSelectedIndex() == 0) {
        prob1valTextField.setEnabled(false);
      }
      else {
        prob1valTextField.setEnabled(true);
        prob1valTextField.requestFocus(true);
      }
    }

    else if (ob == prob2ComboBox) {
      if (prob2ComboBox.getSelectedIndex() == 0) {
        prob2valTextField.setEnabled(false);
      }
      else {
        prob2valTextField.setEnabled(true);
        prob2valTextField.requestFocus(true);
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 170);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 170);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("probs_fixed", false, 170);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("prob", false, 170);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("prob", false, 170);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("prob", false, 170);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("prob", false, 170);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("freq_A", false, 170);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("freq_A", false, 170);
  }


}

class SEGREG_Dialog11_Geno_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog11_Geno adaptee;

  SEGREG_Dialog11_Geno_jComboBox1_mouseAdapter(SEGREG_Dialog11_Geno adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog11_Geno_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog11_Geno adaptee;

  SEGREG_Dialog11_Geno_jComboBox3_mouseAdapter(SEGREG_Dialog11_Geno adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class SEGREG_Dialog11_Geno_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog11_Geno adaptee;

  SEGREG_Dialog11_Geno_jTextField1_mouseAdapter(SEGREG_Dialog11_Geno adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog11_Geno_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog11_Geno adaptee;

  SEGREG_Dialog11_Geno_jComboBox4_mouseAdapter(SEGREG_Dialog11_Geno adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class SEGREG_Dialog11_Geno_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog11_Geno adaptee;

  SEGREG_Dialog11_Geno_jTextField2_mouseAdapter(SEGREG_Dialog11_Geno adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SEGREG_Dialog11_Geno_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog11_Geno adaptee;

  SEGREG_Dialog11_Geno_jTextField3_mouseAdapter(SEGREG_Dialog11_Geno adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG_Dialog11_Geno_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog11_Geno adaptee;

  SEGREG_Dialog11_Geno_jCheckBox1_mouseAdapter(SEGREG_Dialog11_Geno adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog11_Geno_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog11_Geno adaptee;

  SEGREG_Dialog11_Geno_jComboBox2_mouseAdapter(SEGREG_Dialog11_Geno adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}
