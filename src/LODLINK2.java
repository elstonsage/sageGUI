package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LODLINK2
    extends SageAnalysisPanel
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  ButtonGroup bg = new ButtonGroup();

  String option[] = {"none", "standard", "specified"};

  JTextField title = new JTextField();

  TitledBorder titledBorder3;
  DataCollectionModel Datamodel;

  LODLINK1 lodlink1;
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JLabel homo1Label = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  JButton jButton1 = new JButton();
  JComboBox lodoptionComboBox = new JComboBox(option);
  JLabel jLabel13 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JButton jButton2 = new JButton();
  JLabel jLabel16 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JButton jButton3 = new JButton();
  JTextField jTextField5 = new JTextField();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout3 = new XYLayout();
  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();

  Lodlink_Dialog1 dialog1 = new Lodlink_Dialog1(this, "Specification");
  Lodlink_Dialog2 dialog2 = new Lodlink_Dialog2(this, "Specification");
  Lodlink_Dialog3 dialog3 = new Lodlink_Dialog3(this, "Specification");

  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JPanel jPanel4 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JPanel jPanel5 = new JPanel();
  XYLayout xYLayout6 = new XYLayout();
  JCheckBox genotypesexCheckBox = new JCheckBox();
  JCheckBox genotypeCheckBox = new JCheckBox();
  JCheckBox linkagesexCheckBox = new JCheckBox();
  JCheckBox linkagehomoCheckBox = new JCheckBox();
  JCheckBox linkagetestCheckBox = new JCheckBox();
  JCheckBox lodsexCheckBox = new JCheckBox();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  JCheckBox smithtestCheckBox = new JCheckBox();
  JCheckBox smithsexCheckBox = new JCheckBox();

  public LODLINK2(LODLINK1 input) {
    this.Datamodel = input.Datamodel;
    lodlink1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    titledBorder3 = new TitledBorder("");

    genotypesexCheckBox.setFocusPainted(false);
    genotypeCheckBox.setFocusPainted(false);
    linkagesexCheckBox.setFocusPainted(false);
    linkagehomoCheckBox.setFocusPainted(false);
    linkagetestCheckBox.setFocusPainted(false);
    lodsexCheckBox.setFocusPainted(false);
    jRadioButton1.setFocusPainted(false);
    jRadioButton2.setFocusPainted(false);
    smithtestCheckBox.setFocusPainted(false);
    smithsexCheckBox.setFocusPainted(false);


    jLabel1.setToolTipText(
        "<html>Specifies a title for the analysis.");
    jLabel1.setText("Title");
    this.setLayout(xYLayout1);
    title.setToolTipText("");
    title.setMargin(new Insets(1, 5, 2, 4));
    title.setText("LODLINK Analysis");
    title.addMouseListener(new LODLINK2_title_mouseAdapter(this));

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    lodoptionComboBox.setSelectedIndex(1);
    lodoptionComboBox.addMouseListener(new LODLINK2_jComboBox6_mouseAdapter(this));

    jRunButton.addActionListener(this);
    jTextField2.setEnabled(false);
    jTextField2.addMouseListener(new LODLINK2_jTextField2_mouseAdapter(this));
    homo1Label.setToolTipText("<html>Specifies tests for linkage homogeneity.");
    homo1Label.setText("Homogeneity");
    jLabel8.setToolTipText("<html>Lods are calculated at these recombination fractions.");
    jLabel8.setText("Recombination");
    jLabel9.setText("fractions");

    jLabel10.setToolTipText(
        "Specifies pedigree groups for Morton\'s test of linkage homogeneity.");
    jLabel10.setText("Morton\'s test");
    jPanel1.setBorder(titledBorder3);
    jPanel1.setLayout(xYLayout2);
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("Define");
    jLabel13.setToolTipText("<html>Specifies calculation option.");
    jLabel13.setText("Option");
    jLabel15.setToolTipText(
        "<html>Specifies sex-specific recombination fractions.");
    jLabel15.setText("Male/Female");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Define");
    jLabel16.setToolTipText(
        "<html>Specifies sex-averaged recombination fractions.");
    jLabel16.setText("Average");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Define");
    jTextField5.setEnabled(false);
    jTextField5.setEditable(false);

    jTextField5.setText("");
    jTextField5.addMouseListener(new LODLINK2_jTextField5_mouseAdapter(this));
    jTextField4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setText("");
    jTextField4.addMouseListener(new LODLINK2_jTextField4_mouseAdapter(this));
    jTextField3.setEnabled(false);
    jTextField3.setEditable(false);
    jTextField3.setText("");
    jTextField3.addMouseListener(new LODLINK2_jTextField3_mouseAdapter(this));
    jPanel2.setBorder(titledBorder3);
    jPanel2.setLayout(xYLayout3);
    jPanel3.setBorder(titledBorder3);
    jPanel3.setLayout(xYLayout4);
    jLabel3.setForeground(Color.red);
    jLabel3.setToolTipText("<html>Specifies model for linkage calculations.");
    jRadioButton1.setToolTipText(
            "<html>Title of model in the <b>*.typ</b> file from <b>SEGREG</b>.");
    jRadioButton1.setSelected(false);
    jRadioButton1.addMouseListener(new LODLINK2_jRadioButton1_mouseAdapter(this));
    jRadioButton2.setToolTipText(
            "<html>Marker against which all others are tested for linkage. <br>Must be specified as such in the parameter file.");
    jRadioButton2.addMouseListener(new LODLINK2_jRadioButton2_mouseAdapter(this));
    jTextField1.setEnabled(false);
    jTextField1.addMouseListener(new LODLINK2_jTextField1_mouseAdapter(this));
    smithtestCheckBox.addMouseListener(new LODLINK2_jComboBox4_mouseAdapter(this));
    smithsexCheckBox.addMouseListener(new LODLINK2_jComboBox5_mouseAdapter(this));
    lodsexCheckBox.addMouseListener(new LODLINK2_jComboBox7_mouseAdapter(this));
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton4.setText("Reset");
    jButton5.setMargin(new Insets(2, 2, 2, 2));
    jButton5.setText("Reset");
    jButton6.setMargin(new Insets(2, 2, 2, 2));
    jButton6.setText("Reset");
    smithtestCheckBox.setToolTipText("<html>Specifies option to perform Smith's test for linkage homogeneity.");
    smithtestCheckBox.setMargin(new Insets(2, 0, 2, 2));
    smithtestCheckBox.setText("Perform Smith\'s test");
    smithsexCheckBox.setToolTipText("<html>Use sex-specific recombination fractions.");
    smithsexCheckBox.setMargin(new Insets(2, 0, 2, 2));
    smithsexCheckBox.setText("Use sex-specific recombination fractions");//\u0275");
    lodsexCheckBox.setToolTipText("<html>Specifies option to use sex-specific recombination fractions.");
    lodsexCheckBox.setMargin(new Insets(2, 0, 2, 2));
    lodsexCheckBox.setText("Use sex-specific recombination fractions");
    jPanel4.setBorder(titledBorder3);
    jPanel4.setLayout(xYLayout5);
    linkagesexCheckBox.addMouseListener(new LODLINK2_jComboBox2_mouseAdapter(this));
    linkagesexCheckBox.setToolTipText("<html>Specifies option to use sex-specific recombination fractions.");
    linkagesexCheckBox.setMargin(new Insets(2, 0, 2, 2));
    linkagesexCheckBox.setText("Use sex-specific recombination fractions");
    linkagesexCheckBox.addItemListener(this);
    linkagehomoCheckBox.addMouseListener(new LODLINK2_jComboBox3_mouseAdapter(this));
    linkagehomoCheckBox.setToolTipText("<html>Specifies option to assume linkage homogeneity.");
    linkagehomoCheckBox.setMargin(new Insets(2, 0, 2, 2));
    linkagehomoCheckBox.setSelected(true);
    linkagehomoCheckBox.setText("Assume linkage homogeneity");
    linkagehomoCheckBox.addItemListener(this);
    jLabel2.setToolTipText("<html>Specifies option to perform linkage tests.");
    jLabel2.setText("Linkage tests");
    linkagetestCheckBox.setToolTipText("<html>Specifies option to perform linkage tests.");
    linkagetestCheckBox.setMargin(new Insets(2, 0, 2, 2));
    linkagetestCheckBox.setSelected(true);
    linkagetestCheckBox.setText("Perform linkage tests");
    linkagetestCheckBox.addMouseListener(new LODLINK2_jComboBox1_mouseAdapter(this));
    linkagetestCheckBox.addItemListener(this);
    jLabel4.setText("Genotype");
    JLabel jLabelg = new JLabel("probabilities");
    jPanel5.setBorder(titledBorder3);
    jPanel5.setLayout(xYLayout6);
    genotypesexCheckBox.setToolTipText("<html>Specifies option to use sex-specific recombination fractions.");
    genotypesexCheckBox.setVerifyInputWhenFocusTarget(true);
    genotypesexCheckBox.setMargin(new Insets(2, 0, 2, 2));
    genotypesexCheckBox.setText("Use sex-specific recombination fractions");
    genotypesexCheckBox.addMouseListener(new LODLINK2_jComboBox9_mouseAdapter(this));
    genotypesexCheckBox.addItemListener(this);
    genotypeCheckBox.setToolTipText("Specifies option to calculate posterior genotype probabilities.");
    genotypeCheckBox.setMargin(new Insets(2, 0, 2, 2));
    genotypeCheckBox.setText("Calculate genotype probabilities");
    genotypeCheckBox.addMouseListener(new LODLINK2_jComboBox8_mouseAdapter(this));
    genotypeCheckBox.addItemListener(this);
    bg.add(jRadioButton1);
    bg.add(jRadioButton2);

    jLabel3.setText("Model");
    jRadioButton1.setText("Trait marker model name");
    jRadioButton2.setText("Trait marker name");
    jRadioButton1.addItemListener(this);
    jRadioButton2.addItemListener(this);

    dialog1.set_dataModel(Datamodel);
    dialog2.set_dataModel(Datamodel);
    dialog3.set_dataModel(Datamodel);

    jTextField1.setText("");
    jTextField2.setText("");

    this.add(jLabel1, new XYConstraints(20, 20, 75, 20));
    this.add(title,       new XYConstraints(115, 20, 370, 20));
    this.add(jLabel3,  new XYConstraints(20, 48, 80, 20));
    this.add(jPanel1,             new XYConstraints(115, 48, 370, 66));

    this.add(jLabel2,              new XYConstraints(20, 122, 100, 20));
    this.add(jPanel4,         new XYConstraints(115, 122, 370, 95));

    this.add(homo1Label,     new XYConstraints(20, 225, 95, 20));
    this.add(new JLabel("tests"),     new XYConstraints(20, 239, 95, 20));
    this.add(jPanel2,          new XYConstraints(115, 225, 370, 66));

    this.add(jLabel10,     new XYConstraints(20, 299, 75, 20));
    this.add(jTextField5,              new XYConstraints(115, 299, 261, 20));
    this.add(jButton1,             new XYConstraints(382, 299, 45, 20));
    this.add(jButton4,             new XYConstraints(432, 299, 45, 20));

    this.add(jLabel8,      new XYConstraints(20, 327, 130, 20));
    this.add(jLabel9,      new XYConstraints(20, 341, 130, 20));
    this.add(jPanel3,             new XYConstraints(115, 327, 370, 98));

    this.add(jLabel4,    new XYConstraints(20, 436, 110, 20));
    this.add(jLabelg,    new XYConstraints(20, 450, 110, 20));
    this.add(jPanel5,      new XYConstraints(115, 436, 370, 66));
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));

    //model
    jPanel1.add(jRadioButton1, new XYConstraints(2, 2, 150, 20));
    jPanel1.add(jRadioButton2,  new XYConstraints(2, 31, 150, 20));
    jPanel1.add(jTextField1,     new XYConstraints(155, 2, 200, 20));
    jPanel1.add(jTextField2,     new XYConstraints(155, 31, 200, 20));
    //linkage test
    jPanel4.add(linkagetestCheckBox,   new XYConstraints(2, 2, 150, 20));
    jPanel4.add(linkagehomoCheckBox,       new XYConstraints(20, 31, 250, 20));
    jPanel4.add(linkagesexCheckBox,       new XYConstraints(20, 60, 260, 20));

    //homogeneity test
    jPanel2.add(smithtestCheckBox,       new XYConstraints(2, 2, 130, 20));
    jPanel2.add(smithsexCheckBox,       new XYConstraints(20, 31, 260, 20));
    //lods
    jPanel3.add(jLabel13, new XYConstraints(2, 2, 70, 20));
    jPanel3.add(lodoptionComboBox,       new XYConstraints(70, 2, 68, 20));
    jPanel3.add(lodsexCheckBox,          new XYConstraints(143, 2, -1, 20));
    jPanel3.add(jLabel15,   new XYConstraints(2, 31, 70, 20));
    jPanel3.add(jTextField3,           new XYConstraints(70, 31, 183, 20));
    jPanel3.add(jButton2,        new XYConstraints(259, 31, 45, 20));
    jPanel3.add(jButton5,     new XYConstraints(309, 31, 45, 20));
    jPanel3.add(jLabel16,   new XYConstraints(2, 60, 70, 20));
    jPanel3.add(jTextField4,         new XYConstraints(70, 60, 183, 20));
    jPanel3.add(jButton3,        new XYConstraints(259, 60, 45, 20));
    jPanel3.add(jButton6,     new XYConstraints(309, 60, 45, 20));
    //genotype
    jPanel5.add(genotypesexCheckBox,   new XYConstraints(20, 31, 228, 20));
    jPanel5.add(genotypeCheckBox,   new XYConstraints(2, 2, 179, 20));

    title.getDocument().addDocumentListener(this);
    jTextField1.getDocument().addDocumentListener(this);
    jTextField2.getDocument().addDocumentListener(this);

    smithtestCheckBox.addItemListener(this);
    smithsexCheckBox.addItemListener(this);
    lodoptionComboBox.addItemListener(this);
    lodsexCheckBox.addItemListener(this);

    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jButton3.addActionListener(this);
    jButton4.addActionListener(this);
    jButton5.addActionListener(this);
    jButton6.addActionListener(this);

    Datamodel.setValue("Title", title.getText());
    Datamodel.setValue("Model", "trait");
    Datamodel.setValue("linkage_tests", "true");
    Datamodel.setValue("linkage_sex_specific", "false");
    Datamodel.setValue("linkage_homog", "true");
    Datamodel.setValue("smiths_test", "false");
    Datamodel.setValue("smiths_sex_specific", "false");
    Datamodel.setValue("mortons_test", "false");
    Datamodel.setValue("mortons_sex_specific", "false");
    Datamodel.setValue("option", "standard");
    Datamodel.setValue("option_sex_specific", "false");
    Datamodel.setValue("genotypes", "false");
    Datamodel.setValue("genotypes_sex_specific", "false");
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton)
      jRunButton_actionPerformed(e);
    else if (ob == jButton1) {
        dialog1.showDialog();
    }
    else if (ob == jButton2) {
        dialog3.showDialog();
    }
    else if (ob == jButton3) {
        dialog2.showDialog();
    }
    else if (ob == jButton4) {
      if(Datamodel.hasValue("use_mortons_test"))
      {
        jTextField5.setText("");
        jLabel10.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("use_mortons_test");
        dialog1.listModel.removeAllElements();
      }
    }
    else if (ob == jButton5) {
      if(Datamodel.hasValue("use_male_female"))
      {
        jTextField3.setText("");
        jLabel15.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("use_male_female");
        dialog3.listModel.removeAllElements();
      }
    }
    else if (ob == jButton6) {
      if(Datamodel.hasValue("use_average"))
      {
        jTextField4.setText("");
        jLabel16.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("use_average");
        dialog2.listModel.removeAllElements();
      }
    }
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) lodlink1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunLODLINK(treenode, currentnode, e.getActionCommand());
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == title.getDocument()) {
      Datamodel.setValue("Title", title.getText());
      if (title.getText().length() <= 0)
        Datamodel.removeValue("Title");
    }
    if (document == jTextField1.getDocument()) {
      Datamodel.setValue("Model_trait", jTextField1.getText());

      if (jTextField1.getText().length() <= 0)
        Datamodel.removeValue("Model_trait");
    }
    if (document == jTextField2.getDocument()) {
      Datamodel.setValue("Model_marker", jTextField2.getText());
      if (jTextField2.getText().length() <= 0)
        Datamodel.removeValue("Model_marker");
    }

    if (Datamodel.hasValue("Model") && (
        (Datamodel.hasValue("Model_trait") || Datamodel.hasValue("Model_marker")))) {
      jRunButton.setIcon(next_image);
    }
    else
    {
        jRunButton.setIcon(error_image);
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
    if (ob == jRadioButton1) {
      if (jRadioButton1.isSelected()) {
        jTextField1.setEnabled(true);
        Datamodel.setValue("Model", "trait");
      }
      else
        jTextField1.setEnabled(false);
    }
    if (ob == jRadioButton2) {
      if (jRadioButton2.isSelected()) {
        jTextField2.setEnabled(true);
        Datamodel.setValue("Model", "marker");
      }
      else
        jTextField2.setEnabled(false);
    }

    if (ob == linkagetestCheckBox) {
      if(linkagetestCheckBox.isSelected())
        Datamodel.setValue("linkage_tests", "true");
      else
        Datamodel.setValue("linkage_tests", "false");
    }
    if (ob == linkagesexCheckBox) {
      if(linkagesexCheckBox.isSelected())
        Datamodel.setValue("linkage_sex_specific","true");
      else
        Datamodel.setValue("linkage_sex_specific","false");

    }
    if (ob == linkagehomoCheckBox) {
      if(linkagehomoCheckBox.isSelected())
        Datamodel.setValue("linkage_homog", "true");
      else
        Datamodel.setValue("linkage_homog", "false");
    }
    if (ob == smithtestCheckBox) {
      if(smithtestCheckBox.isSelected())
         Datamodel.setValue("smiths_test", "true");
      else
         Datamodel.setValue("smiths_test", "false");
    }
    if (ob == smithsexCheckBox) {
      if(smithsexCheckBox.isSelected())
        Datamodel.setValue("smiths_sex_specific","true");
      else
        Datamodel.setValue("smiths_sex_specific","false");
    }
    if (ob == lodoptionComboBox) {
      Datamodel.setValue("option", lodoptionComboBox.getSelectedItem().toString());
    }
    if (ob == lodsexCheckBox) {
      if(lodsexCheckBox.isSelected())
        Datamodel.setValue("option_sex_specific","true");
      else
        Datamodel.setValue("option_sex_specific","false");
    }
    if (ob == genotypeCheckBox) {
      if(genotypeCheckBox.isSelected())
        Datamodel.setValue("genotypes", "true");
      else
        Datamodel.setValue("genotypes", "false");
    }
    if (ob == genotypesexCheckBox) {
      if(genotypesexCheckBox.isSelected())
        Datamodel.setValue("genotypes_sex_specific","true");
      else
        Datamodel.setValue("genotypes_sex_specific","false");
    }
  }

  void title_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("title", false, 299);
  }

  void jRadioButton1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trai", false, 299);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trai", false, 299);
  }

  void jRadioButton2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marker", false, 299);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("marker", false, 299);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("linkage_test", false, 299);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sex_specific", false, 299);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", homo", false, 300);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("smiths_test", false, 301);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sex_specifi", false, 301);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("mortons_test", false, 301);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("option ", false, 304);
  }

  void jComboBox7_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sex_specific", false, 304);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("male_female", false, 304);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("average", false, 304);
  }

  void jComboBox8_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("genotype", false, 300);
  }

  void jComboBox9_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sex_specific", false, 300);
  }
}

class LODLINK2_title_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_title_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.title_mouseClicked(e);
  }
}

class LODLINK2_jRadioButton1_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jRadioButton1_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton1_mouseClicked(e);
  }
}

class LODLINK2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jTextField1_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class LODLINK2_jRadioButton2_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jRadioButton2_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton2_mouseClicked(e);
  }
}

class LODLINK2_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jTextField2_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class LODLINK2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox4_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class LODLINK2_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox5_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}

class LODLINK2_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jTextField5_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class LODLINK2_jComboBox6_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox6_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class LODLINK2_jComboBox7_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox7_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox7_mouseClicked(e);
  }
}

class LODLINK2_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jTextField3_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class LODLINK2_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jTextField4_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class LODLINK2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox2_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class LODLINK2_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox3_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class LODLINK2_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox1_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class LODLINK2_jComboBox8_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox8_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox8_mouseClicked(e);
  }
}

class LODLINK2_jComboBox9_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK2 adaptee;

  LODLINK2_jComboBox9_mouseAdapter(LODLINK2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox9_mouseClicked(e);
  }
}
