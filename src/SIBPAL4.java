package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class SIBPAL4
    extends SageAnalysisPanel
    implements ActionListener, ItemListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  String[] r_method = {"Difference", "Sum", "Product", "W2", "W3", "W4"};
  String[] r_method_real = {"diff", "sum", "prod", "w2", "w3", "w4"};
  String[] pairtype = {"full", "half", "both"};

  String[] traitregression = {"Single marker", "Multiple marker","Zero marker"};
  String[] traitregression_real = {"single", "multiple","zero"};

  DataCollectionModel Datamodel;
  TitledBorder titledBorder1;

  SIBPAL1 sibpal1;
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();

  ButtonGroup bp = new ButtonGroup();
  ButtonGroup bp2 = new ButtonGroup();

  Sibpal_Dialog1_Trait dialog1_trait = new Sibpal_Dialog1_Trait(this, "Specification");
  Sibpal_Dialog2_Cov dialog2_cov = new Sibpal_Dialog2_Cov(this, "Covariate Specification");
  Sibpal_Dialog3_Interaction dialog3_inter = new Sibpal_Dialog3_Interaction(this, "Interaction Specification");
  Sibpal_Dialog4_ComP dialog4_comp = new Sibpal_Dialog4_ComP(this, "Specification");

  JLabel jLabel5 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JButton jButton1 = new JButton();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JButton jButton2 = new JButton();
  JLabel jLabel7 = new JLabel();
  JComboBox jComboBox1 = new JComboBox(r_method);
  JLabel jLabel12 = new JLabel();
  JComboBox usePairComboBox = new JComboBox(pairtype);
  JLabel jLabel17 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JButton jButton3 = new JButton();
  MyComboBox jSubsetComboBox = new MyComboBox();
  MyComboBox2 jMarkerComboBox = new MyComboBox2();
  JLabel jLabel20 = new JLabel();
  JComboBox jComboBox11 = new JComboBox(traitregression);
  JTextField jTextField1 = new JTextField();
  JButton jButton4 = new JButton();
  JLabel jLabel19 = new JLabel();
  JComboBox jComboBox8 = new JComboBox();
  JComboBox printCMComboBox = new JComboBox();
  JLabel jLabel23 = new JLabel();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JButton jButton7 = new JButton();
  JButton jButton8 = new JButton();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout3 = new XYLayout();
  JCheckBox jComboBox9 = new JCheckBox();
  JCheckBox jComboBox6 = new JCheckBox();
  JCheckBox jComboBox4 = new JCheckBox();
  JCheckBox jComboBox2 = new JCheckBox();
  JCheckBox jComboBox3 = new JCheckBox();
  JCheckBox jComboBox7 = new JCheckBox();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();

  JLabel rowLabel = new JLabel();
  JTextField rowTextField = new JTextField();

  JLabel w1Label = new JLabel();
  JTextField w1TextField = new JTextField();

  JLabel printQLSLabel = new JLabel();
  JComboBox printQLSComboBox = new JComboBox();

  public SIBPAL4(SIBPAL1 input) {
    this.Datamodel = input.Datamodel;
    sibpal1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {

      jComboBox9.setFocusPainted(false);
      jComboBox6.setFocusPainted(false);
      jComboBox4.setFocusPainted(false);
      jComboBox2.setFocusPainted(false);
      jComboBox3.setFocusPainted(false);
      jComboBox7.setFocusPainted(false);
      w1Label.setText("W1");
      w1TextField.setText("0.5");
      w1Label.setToolTipText("<html>Specifies a value for <b>w<sub>1</sub></b>. See manual.");
      printQLSLabel.setText("Print QLS");
      printQLSLabel.setToolTipText("<html>Specifies option to print the linkage score in Wang and Elston(2006).");
          rowLabel.setText("Number of Rows");
          rowLabel.setToolTipText("Specifies the number of rows to print.");
          rowTextField.setText("10");
          rowTextField.setEnabled(false);
          rowLabel.setEnabled(false);


    titledBorder1 = new TitledBorder("");
    jLabel1.setToolTipText(
        "Specifies a trait used as an indicator variable to select subsets of pairs to analyze.");
    jLabel1.setText("Subset");
    this.setLayout(xYLayout1);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    jComboBox1.addMouseListener(new SIBPAL4_jComboBox1_mouseAdapter(this));

    jRunButton.setIcon(back_image);
    jRunButton.setText("Next");

    jLabel2.setToolTipText(
            "Specifies the name of marker(s) or location(s) for which to test mean IBD sharing.");
    jLabel2.setText("Marker");
    jLabel3.setToolTipText(
            "Specifies a trait to be used as the dependent variable in the current test.");
    jLabel3.setText("Trait");
    jLabel5.setToolTipText("<html>Names a covariate.");
    jLabel5.setText("Covariate");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("Define");
    jTextField2.setEnabled(false);
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.addMouseListener(new SIBPAL4_jTextField2_mouseAdapter(this));
    jLabel6.setToolTipText(
            "Includes multiplicative interaction(s) in the design matrix.");
    jLabel6.setText("Interaction(s)");
    jButton2.setToolTipText("");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Define");
    jTextField3.setEnabled(false);
    jTextField3.setEditable(false);
    jTextField3.setText("");
    jTextField3.addMouseListener(new SIBPAL4_jTextField3_mouseAdapter(this));
    jLabel7.setToolTipText(
            "Specifies the dependent variable.");
    jLabel7.setText("Dependent variable");
    jLabel17.setToolTipText("Compute empirical P-values by permutation.");
    jLabel17.setText("Compute");
    jLabel18.setToolTipText("Compute empirical P-values by permutation.");
    jLabel18.setText("empirical P-values");
    jTextField4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setText("");
    jTextField4.addMouseListener(new SIBPAL4_jTextField4_mouseAdapter(this));
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Define");
    jLabel12.setToolTipText(
            "Specifies the type of sibling pairs to be included in the analysis.");
    jLabel12.setText("Pair type");
    jLabel20.setToolTipText(
            "Specifies the number of markers in the regression.");
    jLabel20.setText("Type of regression");
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton4.setText("Define");
    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);
    jTextField1.setText("");
    jTextField1.addMouseListener(new SIBPAL4_jTextField1_mouseAdapter(this));
    jSubsetComboBox.addMouseListener(new SIBPAL4_jSubsetComboBox_mouseAdapter(this));
    jMarkerComboBox.addMouseListener(new SIBPAL4_jMarkerComboBox_mouseAdapter(this));
    jComboBox11.addMouseListener(new SIBPAL4_jComboBox11_mouseAdapter(this));
    usePairComboBox.addMouseListener(new SIBPAL4_jComboBox5_mouseAdapter(this));
    jLabel19.setToolTipText("Specifies option to print the design matrix A.");
    jLabel19.setText("Print design matrix");
    jLabel23.setToolTipText(
            "<html>Specifies option to choose a marker(or location) for which"+
            "<br>to print the sibship-size specific correlation matrices.");
    jLabel23.setText("Print correlation matrices");

    printCMComboBox.addMouseListener(new SIBPAL4_jComboBox10_mouseAdapter(this));
    jButton5.setMargin(new Insets(2, 2, 2, 2));
    jButton5.setText("Reset");
    jButton6.setMargin(new Insets(2, 2, 2, 2));
    jButton6.setText("Reset");
    jButton7.setMargin(new Insets(2, 2, 2, 2));
    jButton7.setText("Reset");
    jButton8.setMargin(new Insets(2, 2, 2, 2));
    jButton8.setText("Reset");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout2);
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout3);
    jComboBox9.addMouseListener(new SIBPAL4_jComboBox9_mouseAdapter(this));
    jComboBox9.setToolTipText(
            "<html>Specifies option to print p-values using scientific notation" +
            "<br>as opposed to the default of fixed decimal notation.");
    jComboBox9.setMargin(new Insets(2, 0, 2, 2));
    jComboBox9.setText("Output p-value in scientific notation");
    jComboBox9.addItemListener(this);
    jComboBox6.addMouseListener(new SIBPAL4_jComboBox6_mouseAdapter(this));
    jComboBox6.setToolTipText(
            "<html>Prints more verbose output information. This causes some" +
            "<br>output tables to be >80 columns wide.");
    jComboBox6.setMargin(new Insets(2, 0, 2, 2));
    jComboBox6.setText("Produce more detailed output");
    jComboBox6.addItemListener(this);
    jComboBox4.addMouseListener(new SIBPAL4_jComboBox4_mouseAdapter(this));
    jComboBox4.setToolTipText(
            "<html>Specifies option to produce tab-delimited output that can easily" +
            "<br>be imported to other programs such as Excel, SAS and SPlus.");
    jComboBox4.setMargin(new Insets(2, 0, 2, 2));
    jComboBox4.setText("Produce tab delimited output");
    jComboBox4.addItemListener(this);
    jComboBox2.setToolTipText(
            "<html>Specifies option to assume that all sib pairs are independent by using the"+
            "<br>identity matrix for correlations between pairs of the dependent variable.");
    jComboBox2.setMargin(new Insets(2, 0, 2, 2));
    jComboBox2.setText("Assume sib pairs are independent");
    jComboBox2.addMouseListener(new SIBPAL4_jComboBox2_mouseAdapter(this));
    jComboBox2.addItemListener(this);
    jComboBox3.setToolTipText(
            "<html>Computes the variance of parameter estimates using the robust, " +
            "or <br>sandwich, variance estimator. This can lead to very conservative " +
            "<br>tests for larger samples containing non-independent sibling pairs.");
    jComboBox3.setMargin(new Insets(2, 0, 2, 2));
    jComboBox3.setText("Use robust variance estimator");
    jComboBox3.addMouseListener(new SIBPAL4_jComboBox3_mouseAdapter(this));
    jComboBox3.addItemListener(this);
    jComboBox7.setToolTipText(
            "<html>Option to skip pairs of individuals whose prior and observed IBD sharing" +
            "<br>probabilities are numerically identical given the machine precision.");
    jComboBox7.setMargin(new Insets(2, 0, 2, 2));
    jComboBox7.setText("Skip uninformative pairs");
    jComboBox7.addMouseListener(new SIBPAL4_jComboBox7_mouseAdapter(this));
    jComboBox7.addItemListener(this);
    jLabel8.setText("Output options");
    jLabel9.setText("Other options");
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));

    //trait
    this.add(jLabel3,   new XYConstraints(20, 20, 50, 20));
    this.add(jTextField1,       new XYConstraints(135, 20, 239, 20));
    this.add(jButton1,      new XYConstraints(380, 20, 45, 20));
    this.add(jButton5,     new XYConstraints(430, 20, 45, 20));

    //covariate
    this.add(jLabel5,   new XYConstraints(20, 50, 88, 20));
    this.add(jTextField2,        new XYConstraints(135, 50, 239, 20));
    this.add(jButton2,       new XYConstraints(380, 50, 45, 20));
    this.add(jButton6,       new XYConstraints(430, 50, 45, 20));

    //marker
    this.add(jLabel2,   new XYConstraints(20, 80, 44, 20));
    this.add(jMarkerComboBox,       new XYConstraints(135, 80, 340, 20));

    //subset
    this.add(jLabel1,   new XYConstraints(20, 110, 60, 20));
    this.add(jSubsetComboBox,      new XYConstraints(135, 110, 340, 20));


    //interaction
    this.add(jLabel6,   new XYConstraints(20, 140, 88, 20));
    this.add(jTextField3,        new XYConstraints(135, 140, 229, 20));
    this.add(jButton3,      new XYConstraints(380, 140, 45, 20));
    this.add(jButton7,       new XYConstraints(430, 140, 45, 20));

    //compute p-values
    this.add(jLabel17,         new XYConstraints(20, 163, 100, 20));
    this.add(jLabel18,      new XYConstraints(20, 177, 110, -1));
    this.add(jTextField4,          new XYConstraints(135, 170, 239, 20));
    this.add(jButton4,   new XYConstraints(380, 170, 45, 20));
    this.add(jButton8,       new XYConstraints(430, 170, 45, 20));

    //tarit regression & dependent variable
    this.add(jLabel20,   new XYConstraints(20, 200, 130, 17));
    this.add(jComboBox11,         new XYConstraints(135, 200, 95, 20));
    this.add(jLabel7,         new XYConstraints(250, 200, 120, 20));
    this.add(jComboBox1,           new XYConstraints(380, 200, 95, 20));

    //print design matrix
    this.add(jLabel19,        new XYConstraints(20, 230, 91, 20));
    this.add(jComboBox8,           new XYConstraints(135, 230, 95, 20));
    this.add(rowLabel,         new XYConstraints(250, 230, 120, 20));
    this.add(rowTextField,           new XYConstraints(380, 230, 95, 20));

    //print QTL & print correlation matrix
    this.add(printQLSLabel,     new XYConstraints(20, 260, 95, 20));
    this.add(printQLSComboBox,        new XYConstraints(135, 260, 95, 20));
    this.add(jLabel23,     new XYConstraints(250, 260, 120, 20));
    this.add(printCMComboBox,     new XYConstraints(380, 260, 95, 20));

    //pair type & W1
    this.add(jLabel12,     new XYConstraints(20, 290, 110, 20));
    this.add(usePairComboBox,     new XYConstraints(135, 290, 95, 20));
    this.add(w1Label,     new XYConstraints(250, 290, 95, 20));
    this.add(w1TextField,        new XYConstraints(380, 290, 95, 20));

    // output options
    this.add(jLabel8,      new XYConstraints(20, 320, 110, 20));
    this.add(jPanel1,   new XYConstraints(135, 320, 340, 95));
    jPanel1.add(jComboBox9,   new XYConstraints(2, 60, 300, 20));
    jPanel1.add(jComboBox6,   new XYConstraints(2, 2, 300, 20));
    jPanel1.add(jComboBox4,   new XYConstraints(2, 31, 300, 20));

    // other options
    this.add(jLabel9,     new XYConstraints(20, 425, 110, 20));
    this.add(jPanel2,    new XYConstraints(135, 425, 340, 95));
    jPanel2.add(jComboBox2,   new XYConstraints(2, 31, 300, 20));
    jPanel2.add(jComboBox7,   new XYConstraints(2, 2, 300, 20));
    jPanel2.add(jComboBox3,   new XYConstraints(2, 60, 300, 20));

    jMarkerComboBox.setWidth(320);
    jSubsetComboBox.setWidth(320);

    jRunButton.addActionListener(this);
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jButton3.addActionListener(this);
    jButton4.addActionListener(this);

    jButton5.addActionListener(this);
    jButton6.addActionListener(this);
    jButton7.addActionListener(this);
    jButton8.addActionListener(this);

    jComboBox1.addItemListener(this);
    usePairComboBox.addItemListener(this);
    jComboBox11.addItemListener(this);
    jComboBox8.addItemListener(this);
    printCMComboBox.addItemListener(this);
    printQLSComboBox.addItemListener(this);

    Datamodel.setValue("regression_method", "prod");
    Datamodel.setValue("identity_weights", "false");
    Datamodel.setValue("robust_variance", "false");
    Datamodel.setValue("sibship_mean", "false");
    Datamodel.setValue("wide_out", "false");
    Datamodel.setValue("skip_uninformative_pairs", "false");
    Datamodel.setValue("cep_threshold", "0.05");
    Datamodel.setValue("cep_width", "0.2");
    Datamodel.setValue("cep_confidence", "0.95");
    Datamodel.setValue("cep_max_r", "10000");
    Datamodel.setValue("use_pair", "full");
    Datamodel.setValue("trait_regression_default", "single");
    Datamodel.setValue("export_output_tr", "false");
    Datamodel.setValue("pval_scientific_notation_tr", "false");
    Datamodel.setValue("w1_trait_reg", "0.5");

    dialog1_trait.set_dataModel(Datamodel);
    dialog2_cov.set_dataModel(Datamodel);
    dialog3_inter.set_dataModel(Datamodel);
    dialog4_comp.set_dataModel(Datamodel);
  }

  public void jRunButton_actionPerformed() {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    mf.jTabbedPane1.setSelectedIndex(1);
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if (ob == jComboBox1) {
      Datamodel.setValue("regression_method", r_method_real[jComboBox1.getSelectedIndex()]);
    }
    if (ob == jComboBox2) {
      if(jComboBox2.isSelected())
        Datamodel.setValue("identity_weights","true");
      else
        Datamodel.setValue("identity_weights","false");
    }
    if (ob == jComboBox3) {
      if(jComboBox3.isSelected())
        Datamodel.setValue("robust_variance","true");
      else
        Datamodel.setValue("robust_variance","false");
    }

    if (ob == usePairComboBox) {
      Datamodel.setValue("use_pair", usePairComboBox.getSelectedItem().toString());

      if(usePairComboBox.getSelectedIndex()==0)//full
      {
          w1Label.setEnabled(true);
          w1TextField.setEnabled(true);
      }
      else //half, both
      {
          w1Label.setEnabled(false);
          w1TextField.setEnabled(false);
      }
    }

    if (ob == jComboBox6) {
      if(jComboBox6.isSelected())
        Datamodel.setValue("wide_out", "true");
      else
        Datamodel.setValue("wide_out", "false");
    }

    if (ob == jComboBox7) {
      if(jComboBox7.isSelected())
        Datamodel.setValue("skip_uninformative_pairs","true");
      else
        Datamodel.setValue("skip_uninformative_pairs","false");
    }
    if (ob == jComboBox11) {
      Datamodel.setValue("trait_regression_default",
                         traitregression_real[jComboBox11.getSelectedIndex()]);
    }
    if (ob == jComboBox4) {
      if(jComboBox4.isSelected())
        Datamodel.setValue("export_output_tr", "true");
      else
        Datamodel.setValue("export_output_tr", "false");
    }
    if (ob == jComboBox9) {
      if(jComboBox9.isSelected())
        Datamodel.setValue("pval_scientific_notation_tr", "true");
      else
        Datamodel.setValue("pval_scientific_notation_tr", "false");
    }

    if (ob == jComboBox8)
    {
        if (jComboBox8.getSelectedIndex() == 0) {
            if (Datamodel.hasValue("print_design_matrix"))
                Datamodel.removeValue("print_design_matrix");

            rowTextField.setEnabled(false);
            rowLabel.setEnabled(false);

        } else {
            if (jComboBox8.getSelectedItem() != null)
                Datamodel.setValue("print_design_matrix",
                                   jComboBox8.getSelectedItem().toString());

            rowTextField.setEnabled(true);
            rowLabel.setEnabled(true);
        }
    }

    if (ob == printCMComboBox) {
      if(printCMComboBox.getSelectedIndex()==0)
      {
        if(Datamodel.hasValue("print_correlation_matrix"))
          Datamodel.removeValue("print_correlation_matrix");
      }
      else
      {
          if(printCMComboBox.getSelectedItem()!=null)
              Datamodel.setValue("print_correlation_matrix", printCMComboBox.getSelectedItem().toString());
      }
    }

    if (ob == printQLSComboBox) {
      if(printQLSComboBox.getSelectedIndex()==0)
      {
        if(Datamodel.hasValue("print_QLS"))
          Datamodel.removeValue("print_QLS");
      }
      else
      {
          if(printQLSComboBox.getSelectedItem()!=null)
              Datamodel.setValue("print_QLS", printQLSComboBox.getSelectedItem().toString());
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if (ob == jButton1) {
        dialog1_trait.showDialog();
    }

    if (ob == jButton5) {
      if(Datamodel.hasValue("trait"))
      {
        jTextField1.setText("");
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("trait");
        dialog1_trait.listModel.removeAllElements();
      }
    }

    if (ob == jButton2) {
        dialog2_cov.showDialog();
    }

    if (ob == jButton6) {
      if(Datamodel.hasValue("cov"))
      {
        jTextField2.setText("");
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("cov");
        dialog2_cov.listModel.removeAllElements();
      }
    }

    if (ob == jButton3) {
        dialog3_inter.showDialog();
    }

    if (ob == jButton7) {
      if(Datamodel.hasValue("interaction"))
      {
        jTextField3.setText("");
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("interaction");
        dialog3_inter.listModel.removeAllElements();
      }
    }

    if (ob == jButton4) {
        dialog4_comp.showDialog();
    }

    if (ob == jButton8) {
      if(Datamodel.hasValue("compute_empirical_pvalues"))
      {
        jTextField4.setText("");
        jLabel17.setFont(new java.awt.Font("Dialog", 0, 11));
        jLabel18.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("compute_empirical_pvalues");
      }
    }

    if (ob == jRunButton)
      jRunButton_actionPerformed();
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 254);
  }

  void jMarkerComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marke", false, 254);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 254);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("interaction", false, 255);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("regression_metho", false, 256);
  }

  void jSubsetComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("subset", false, 256);
  }

  void jWeightComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("weight", false, 256);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
     Frame1.mainFrame1.pdfframe.setTextonPage("identity_weights", false, 256);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("robust_variance", false, 256);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("wide_out", false, 257);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("compute_empirical_pvalue", false, 257);
  }

  void jComboBox11_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait_regression", false, 250);
  }

  void jComboBox7_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("skip_uninformative_pair", false, 258);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("export_output", false, 258);
  }

 void jComboBox9_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pval_scientific_notatio", false, 258);
  }

  void jComboBox8_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("print_design_matrix", false, 258);
  }

  void jComboBox10_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("print_correlation_matrix", false, 259);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("use_pairs", false, 259);
  }
}

class SIBPAL4_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jTextField1_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SIBPAL4_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jTextField2_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SIBPAL4_jSubsetComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jSubsetComboBox_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jSubsetComboBox_mouseClicked(e);
  }
}

class SIBPAL4_jMarkerComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jMarkerComboBox_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jMarkerComboBox_mouseClicked(e);
  }
}

class SIBPAL4_jWeightComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jWeightComboBox_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jWeightComboBox_mouseClicked(e);
  }
}

class SIBPAL4_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jTextField3_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SIBPAL4_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jTextField4_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox11_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox11_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox11_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox5_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox1_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox8_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox8_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox8_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox10_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox10_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox10_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox6_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox6_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox4_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox9_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox9_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox9_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox7_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox7_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox7_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox2_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SIBPAL4_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL4 adaptee;

  SIBPAL4_jComboBox3_mouseAdapter(SIBPAL4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}
