package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog8_Fpmm
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

  String[] type = {
      "Age of onset depends on type", "Susceptibility depends on type"};
  String[] type_real = {
      "A", "S"};
  String[] multi = {
      "No polygenic comp", "Age of onset has polygenic comp",
      "Susceptibility has polygenic comp"};
  String[] multi_real = {
      "N", "A", "S"};

  JTextField jTextField1 = new JTextField();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JComboBox jComboBox2 = new JComboBox(type);
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JComboBox jComboBox3 = new JComboBox(multi);
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JCheckBox jCheckBox1 = new JCheckBox();
  JLabel jLabel4 = new JLabel();
  JButton jButton2 = new JButton();
  JComboBox jComboBox5 = new JComboBox();
  JComboBox jComboBox6 = new JComboBox();

  JTextField resulttextfield;
  JLabel resultlabel;
  JCheckBox jComboBox1 = new JCheckBox("True");
  JTextField jTextField3 = new JTextField();

  public SEGREG_Dialog8_Fpmm(String title) {
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
    this.setSize(new Dimension(330, 380));
    this.setResizable(false);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(330, 300));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jBottomPanel.setLayout(xYLayout3);
    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jLabel3.setText("Details for the FPMM model.");
    jLabel1.setToolTipText("Specifies number of polygenic loci.");
    jLabel1.setText("Number of Loci");
    jTextField1.setText("3");
    jTextField2.setText("0.5");
    jTextField2.addMouseListener(new SEGREG_Dialog8_Fpmm_jTextField2_mouseAdapter(this));
    jLabel2.setToolTipText("Specifies allele frequency of polygenic loci.");
    jLabel2.setText("Frequency");
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout5);
    jLabel6.setToolTipText("Specifies what is dependent on type.");
    jLabel6.setText("Type dependents");
    jLabel7.setToolTipText("Specifies what is dependent on a polygenic component.");
    jLabel7.setText("Multi dependents");
    jLabel9.setForeground(Color.red);
    jLabel9.setToolTipText("Specifies the age of onset.");
    jLabel9.setText("Age onset");
    jLabel10.setForeground(Color.red);
    jLabel10.setToolTipText("Specifies the age at exam.");
    jLabel10.setText("Age exam");
    jCheckBox1.setToolTipText("Start a sub-block for a binary trait with variable age of onset.");
    jCheckBox1.setSelected(false);
    jCheckBox1.setText("Onset");
    jCheckBox1.setFocusPainted(false);
    jCheckBox1.addMouseListener(new SEGREG_Dialog8_Fpmm_jCheckBox1_mouseAdapter(this));
    jLabel4.setToolTipText("Specifies the value of the polygenic variance.");
    jLabel4.setText("Variance");
    jComboBox2.setEnabled(false);
    jComboBox2.addMouseListener(new SEGREG_Dialog8_Fpmm_jComboBox2_mouseAdapter(this));
    jComboBox3.setEnabled(false);
    jComboBox3.addMouseListener(new SEGREG_Dialog8_Fpmm_jComboBox3_mouseAdapter(this));
    jComboBox5.setEnabled(false);
    jComboBox5.addMouseListener(new SEGREG_Dialog8_Fpmm_jComboBox5_mouseAdapter(this));
    jComboBox6.setEnabled(false);
    jComboBox6.addMouseListener(new SEGREG_Dialog8_Fpmm_jComboBox6_mouseAdapter(this));

    jTextField1.addMouseListener(new SEGREG_Dialog8_Fpmm_jTextField1_mouseAdapter(this));
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jComboBox1.setEnabled(false);
    jComboBox1.setToolTipText("Specifies option to fix the given value.");
    jComboBox1.setMargin(new Insets(2, 0, 2, 2));
    jComboBox1.setText("Fixed");
    jComboBox1.setFocusPainted(false);
    jComboBox1.addMouseListener(new SEGREG_Dialog8_Fpmm_jComboBox1_mouseAdapter(this));
    jComboBox1.addItemListener(this);
    jTextField3.setText("");
    jTextField3.addMouseListener(new SEGREG_Dialog8_Fpmm_jTextField3_mouseAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3,  new XYConstraints(15, 3, 302, 30));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);

    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));

    jCenterPanel.add(jLabel1, new XYConstraints(15, 15, 100, 20));
    jCenterPanel.add(jTextField1, new XYConstraints(128, 15, 175, 20));

    jCenterPanel.add(jLabel2, new XYConstraints(15, 45, 65, 20));
    jCenterPanel.add(jTextField2, new XYConstraints(128, 45, 175, 20));

    jCenterPanel.add(jLabel4,  new XYConstraints(15, 75, 100, 20));
    jCenterPanel.add(jTextField3,    new XYConstraints(128, 75, 115, 20));
    jCenterPanel.add(jComboBox1,   new XYConstraints(250, 75, 65, 20));

    jCenterPanel.add(jCheckBox1,      new XYConstraints(15, 115, 150, 20));
    jCenterPanel.add(jPanel2,         new XYConstraints(15, 145, 300, 135));

    jPanel2.add(jComboBox2,  new XYConstraints(105, 5, 175, 20));
    jPanel2.add(jLabel6,   new XYConstraints(5, 5, 95, 20));
    jPanel2.add(jLabel7,   new XYConstraints(5, 35, 95, 20));
    jPanel2.add(jComboBox3,   new XYConstraints(105, 35, 175, 20));
    jPanel2.add(jLabel9,    new XYConstraints(5, 65, 95, 20));
    jPanel2.add(jLabel10,    new XYConstraints(5, 95, 95, 20));
    jPanel2.add(jComboBox5,    new XYConstraints(105, 65, 175, 20));
    jPanel2.add(jComboBox6,    new XYConstraints(105, 95, 175, 20));

    jComboBox2.setFocusable(false);
    jComboBox3.setFocusable(false);

    jComboBox2.addItemListener(this);
    jComboBox3.addItemListener(this);
    jCheckBox1.addItemListener(this);
    jComboBox5.addItemListener(this);
    jComboBox6.addItemListener(this);

    jButton2.addActionListener(this);

    jTextField1.getDocument().addDocumentListener(this);
    jTextField2.getDocument().addDocumentListener(this);

    this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1Pressed");

    this.getRootPane().getActionMap().
            put("F1Pressed", new AbstractAction("F1Pressed") {
        public void actionPerformed(ActionEvent actionEvent) {
            onPressedF1();
        }
      });
    jTextField3.getDocument().addDocumentListener(this);

  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
    Datamodel.setValue("fpmm_fixed", "false");
    Datamodel.setValue("fpmm_onset_type_dependent", "A");
    Datamodel.setValue("fpmm_onset_multi_dependent", "N");
    Datamodel.setValue("fpmm_loci", "3");
    Datamodel.setValue("fpmm_freq", "0.5");
  }

  public void save_init_state()
  {
    Datamodel.setValue("fpmm_onset_age_onset", jComboBox5.getSelectedItem().toString());
    Datamodel.setValue("fpmm_onset_age_exam", jComboBox6.getSelectedItem().toString());
    Datamodel.setValue("fpmm", "use");

    if(Datamodel.hasValue("fpmm_loci"))
    {
        if(Datamodel.getValue("fpmm_loci").toString().compareTo("0")==0)
        {
            if(Datamodel.hasValue("fpmm_freq"))
                Datamodel.removeValue("fpmm_freq");
        }
    }

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
      if (jTextField1.getText().trim().length() <= 0) {
        Datamodel.setValue("fpmm_loci", "3");
      }
      else {
        Datamodel.setValue("fpmm_loci", jTextField1.getText());

        if(jTextField1.getText().trim().compareTo("0")==0)
        {
            jLabel2.setEnabled(false);
            jLabel4.setEnabled(false);
            jTextField2.setEnabled(false);
            jTextField3.setEnabled(false);

            //Multi dependents
            jLabel7.setEnabled(false);
            jComboBox3.setEnabled(false);
            if(Datamodel.hasValue("fpmm_onset_multi_dependent"))
                Datamodel.removeValue("fpmm_onset_multi_dependent");

        }
        else
        {
            jLabel2.setEnabled(true);
            jLabel4.setEnabled(true);
            jTextField2.setEnabled(true);
            jTextField3.setEnabled(true);

            if (jCheckBox1.isSelected())
            {
                //Multi dependents
                jLabel7.setEnabled(true);
                jComboBox3.setEnabled(true);
                Datamodel.setValue("fpmm_onset_multi_dependent",
                         multi_real[jComboBox3.getSelectedIndex()]);
            }
        }
      }
    }
    if (document == jTextField2.getDocument()) {
      if (jTextField2.getText().length() <= 0) {
        Datamodel.setValue("fpmm_freq", "0.5");
      }
      else {
        Datamodel.setValue("fpmm_freq", jTextField2.getText());
      }
    }
    if (document == jTextField3.getDocument()) {
      if (jTextField3.getText().length() <= 0) {
        jComboBox1.setEnabled(false);
        Datamodel.removeValue("fpmm_var");
      }
      else {
        jComboBox1.setEnabled(true);
        Datamodel.setValue("fpmm_var", jTextField3.getText());
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
      if(jComboBox1.isSelected())
        Datamodel.setValue("fpmm_fixed", "true");
      else
        Datamodel.setValue("fpmm_fixed", "false");
    }
    if (ob == jComboBox2) {
      Datamodel.setValue("fpmm_onset_type_dependent",
                         type_real[jComboBox2.getSelectedIndex()]);
    }
    if (ob == jComboBox3) {
      Datamodel.setValue("fpmm_onset_multi_dependent",
                         multi_real[jComboBox3.getSelectedIndex()]);
    }

    if (ob == jCheckBox1) {
      if (jCheckBox1.isSelected()) {
        jComboBox2.setEnabled(true);
        jComboBox3.setEnabled(true);
        jComboBox5.setEnabled(true);
        jComboBox6.setEnabled(true);

        Datamodel.setValue("fpmm_onset", "use");

        if(jTextField1.getText().trim().compareTo("0")==0)
        {
            //Multi dependents
            jLabel7.setEnabled(false);
            jComboBox3.setEnabled(false);
            if(Datamodel.hasValue("fpmm_onset_multi_dependent"))
                Datamodel.removeValue("fpmm_onset_multi_dependent");

        }
        else
        {
                //Multi dependents
                jLabel7.setEnabled(true);
                jComboBox3.setEnabled(true);
                Datamodel.setValue("fpmm_onset_multi_dependent",
                         multi_real[jComboBox3.getSelectedIndex()]);
        }
      }
      else {
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
        jComboBox5.setEnabled(false);
        jComboBox6.setEnabled(false);
        Datamodel.removeValue("fpmm_onset");
        jButton1.setEnabled(false);
      }
    }

    if(jCheckBox1.isSelected())
    {
      if (jComboBox5.getSelectedIndex()>0 &&
          jComboBox6.getSelectedIndex()>0) {
        jButton1.setEnabled(true);
      }
      else
        jButton1.setEnabled(false);

    }
  }

  void onPressedF1()
  {
     Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("loci", false, 161);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("loci", false, 161);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("freq", false, 161);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 161);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fixed", false, 161);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("onset", false, 161);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("type_dependent", false, 163);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("multi_dependent", false, 163);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("age_onset", false, 163);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("age_exam", false, 163);
  }
}

class SEGREG_Dialog8_Fpmm_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jTextField1_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog8_Fpmm_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jTextField2_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SEGREG_Dialog8_Fpmm_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jCheckBox1_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog8_Fpmm_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jComboBox2_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SEGREG_Dialog8_Fpmm_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jComboBox3_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class SEGREG_Dialog8_Fpmm_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jComboBox5_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}

class SEGREG_Dialog8_Fpmm_jComboBox6_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jComboBox6_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class SEGREG_Dialog8_Fpmm_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jComboBox1_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog8_Fpmm_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog8_Fpmm adaptee;

  SEGREG_Dialog8_Fpmm_jTextField3_mouseAdapter(SEGREG_Dialog8_Fpmm adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}
