package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ASSOC2
    extends SageAnalysisPanel
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {

  XYLayout xYLayout1 = new XYLayout();
  ButtonGroup bg = new ButtonGroup();
  String[] aa = {"No", "Yes"};
  String[] realaa = {"none", "mean"};

  ASSOC_Dialog1_Cov covDialog = new ASSOC_Dialog1_Cov(this, "Specification");
  ASSOC_Dialog2_TransF transDialog = new ASSOC_Dialog2_TransF(this, "Specification");
  ASSOC_Dialog3_CE CEDialog= new ASSOC_Dialog3_CE(this, "Specification");
  ASSOC_Dialog4_Summary summaryDialog= new ASSOC_Dialog4_Summary(this, "Specification");
  ASSOC_Dialog5_Residuals residualsDialog= new ASSOC_Dialog5_Residuals(this, "Specification");

  TitledBorder titledBorder3;
  DataCollectionModel Datamodel;
  ASSOC1 assoc1;

  //Title
  JTextField titleTextField = new JTextField();
  JLabel titleLabel = new JLabel();
  //Trait
  JLabel traitLabel = new JLabel();
  JComboBox traitComboBox = new JComboBox();
  //Covariate
  JLabel covLabel = new JLabel();
  JTextField covTextField = new JTextField();
  JButton covDefineButton = new JButton();
  JButton covResetButton = new JButton();
  //Transformation
  JLabel transLabel = new JLabel();
  JTextField transTextField = new JTextField();
  JButton transDefineButton = new JButton();
  JButton transResetButton = new JButton();
  //Summary display
  JLabel summaryLabel = new JLabel();
  JTextField summaryTextField = new JTextField();
  JButton summaryDefineButton = new JButton();
  JButton summaryResetButton = new JButton();
  //Allow averaging
  JLabel allowaveLabel = new JLabel();
  JComboBox allowaveComboBox = new JComboBox(aa);

  //Variance Component
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  JLabel varcomLabel = new JLabel();

  JCheckBox PECheckBox = new JCheckBox();
  JLabel PEvalLabel = new JLabel();
  JTextField PEVal = new JTextField();
  JCheckBox PEfixedCheckBox = new JCheckBox();
  JCheckBox FECheckBox = new JCheckBox();
  JLabel FEvalLabel = new JLabel();
  JTextField FEVal = new JTextField();
  JCheckBox FEfixedCheckBox = new JCheckBox();
  JCheckBox MECheckBox = new JCheckBox();
  JTextField MEVal = new JTextField();
  JCheckBox MEfixedCheckBox = new JCheckBox();
  JLabel MEvalLabel = new JLabel();
  JCheckBox SECheckBox = new JCheckBox();
  JTextField SEVal = new JTextField();
  JCheckBox SEfixedCheckBox = new JCheckBox();
  JLabel SEvalLabel = new JLabel();

  JLabel CELabel = new javax.swing.JLabel();
  JTextField CETextField = new javax.swing.JTextField();
  JButton CEDefineButton = new javax.swing.JButton();
  JButton CEResetButton = new javax.swing.JButton();

  //other options
  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel optionsLabel = new JLabel();
  JCheckBox batchCheckBox = new JCheckBox();
   //residuals
 JLabel residualLabel = new JLabel();
 JTextField resTextField = new JTextField();
 JButton resDefineButton = new javax.swing.JButton();
 JButton resResetButton = new javax.swing.JButton();

  public ASSOC2(ASSOC1 input) {
    this.Datamodel = input.Datamodel;
    assoc1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    titledBorder3 = new TitledBorder("");
    this.setLayout(xYLayout1);
    covDialog.set_dataModel(Datamodel);
    transDialog.set_dataModel(Datamodel);
    CEDialog.set_dataModel(Datamodel);
    summaryDialog.set_dataModel(Datamodel);
    residualsDialog.set_dataModel(Datamodel);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    allowaveComboBox.setSelectedIndex(1);

    PECheckBox.setFocusPainted(false);
    PEfixedCheckBox.setFocusPainted(false);
    FECheckBox.setFocusPainted(false);
    FEfixedCheckBox.setFocusPainted(false);
    MECheckBox.setFocusPainted(false);
    MEfixedCheckBox.setFocusPainted(false);
    SECheckBox.setFocusPainted(false);
    SEfixedCheckBox.setFocusPainted(false);
    batchCheckBox.setFocusPainted(false);
    jRunButton.addActionListener(this);

    titleLabel.setToolTipText("Specifies the title of the run.");
    titleLabel.setText("Title");
    titleTextField.setToolTipText("");
    titleTextField.setText("analysis1");
    titleTextField.addMouseListener(new ASSOC2_title_mouseAdapter(this));

    traitLabel.setForeground(Color.red);
    traitLabel.setToolTipText(
        "Specifies a dependent variable as the trait in the regression model.");
    traitLabel.setText("Trait");
    traitComboBox.addMouseListener(new ASSOC2_jComboBoxTrait_mouseAdapter(this));

    covLabel.setToolTipText(
            "<html>Covariates defined here but not specified as test covariates in a regression"+
            "<br>model are automatically included in each regression model.");
    covLabel.setText("Covariate");
    covTextField.setEnabled(false);
    covTextField.setEditable(false);
    covTextField.setText("");
    covTextField.addMouseListener(new ASSOC2_jTextFieldCov_mouseAdapter(this));
    covDefineButton.setMargin(new Insets(2, 2, 2, 2));
    covDefineButton.setText("Define");
    covDefineButton.addActionListener(this);
    covResetButton.addActionListener(this);
    covResetButton.setMargin(new Insets(1, 1, 1, 1));
    covResetButton.setText("Reset");

    transLabel.setToolTipText("Specifies parameters for transformation.");
    transLabel.setText("Transformation");
    transTextField.setEnabled(false);
    transTextField.setEditable(false);
    transTextField.setText("");
    transTextField.addMouseListener(new ASSOC2_jTextField1_mouseAdapter(this));
    transDefineButton.setMargin(new Insets(2, 2, 2, 2));
    transDefineButton.setText("Define");
    transDefineButton.addActionListener(this);
    transResetButton.addActionListener(this);
    transResetButton.setMargin(new Insets(2, 2, 2, 2));
    transResetButton.setText("Reset");

    summaryLabel.setToolTipText("Specifies options for the summary output file.");
    summaryLabel.setText("Summary display");
    summaryTextField.setEnabled(false);
    summaryTextField.setEditable(false);
    summaryTextField.setText("");
    summaryTextField.addMouseListener(new ASSOC2_jTextField1_mouseAdapter(this));
    summaryDefineButton.setMargin(new Insets(2, 2, 2, 2));
    summaryDefineButton.setText("Define");
    summaryDefineButton.addActionListener(this);
    summaryResetButton.addActionListener(this);
    summaryResetButton.setMargin(new Insets(2, 2, 2, 2));
    summaryResetButton.setText("Reset");

    allowaveLabel.setToolTipText(
        "<html>This option allows the user to substitute covariates' respective"+
        "<br>means for missing covariate data.");
    allowaveLabel.setText("Allow averaging");
    allowaveComboBox.addMouseListener(new ASSOC2_jComboBoxAA_mouseAdapter(this));

    varcomLabel.setText("Variance components");
    varcomLabel.setToolTipText("Selection of the variance components.");
    jPanel1.setBorder(titledBorder3);
    jPanel1.setLayout(xYLayout2);

    PECheckBox.setToolTipText("Specifies the inclusion of a polygenic effect in the model.");
    PECheckBox.setMargin(new Insets(2, 0, 2, 2));
    PECheckBox.setSelected(true);
    PECheckBox.setText("Estimate polygenic");
    PECheckBox.addItemListener(this);
    PECheckBox.addMouseListener(new ASSOC2_jComboBoxPE_mouseAdapter(this));
    PEvalLabel.setToolTipText("Specifies the initial estimate for the variance of this effect.");
    PEvalLabel.setText("Value");
    PEvalLabel.addMouseListener(new ASSOC2_jCheckBox3_mouseAdapter(this));
    PEVal.setText("");
    PEVal.addMouseListener(new ASSOC2_PEVal_mouseAdapter(this));
    PEfixedCheckBox.setEnabled(false);
    PEfixedCheckBox.setToolTipText("Specifies that the variance of this effect is fixed.");
    PEfixedCheckBox.setText("Fixed");
    PEfixedCheckBox.addMouseListener(new ASSOC2_jComboBox6_mouseAdapter(this));

    FECheckBox.addMouseListener(new ASSOC2_jComboBoxFE_mouseAdapter(this));
    FECheckBox.setToolTipText("Specifies the inclusion of a nuclear family effect in the model.");
    FECheckBox.setMargin(new Insets(2, 0, 2, 2));
    FECheckBox.setText("Estimate Family");
    FECheckBox.addItemListener(this);
    FEvalLabel.setEnabled(false);
    FEvalLabel.setToolTipText("Specifies the initial estimate for the variance of this effect.");
    FEvalLabel.setText("Value");
    FEvalLabel.addMouseListener(new ASSOC2_jCheckBox4_mouseAdapter(this));
    FEVal.setEnabled(false);
    FEVal.setText("");
    FEVal.addMouseListener(new ASSOC2_FEVal_mouseAdapter(this));
    FEfixedCheckBox.setEnabled(false);
    FEfixedCheckBox.setToolTipText("Specifies that the variance of this effect is fixed.");
    FEfixedCheckBox.setText("Fixed");
    FEfixedCheckBox.addMouseListener(new ASSOC2_jComboBox7_mouseAdapter(this));

    SEVal.setText("");
    SEVal.addMouseListener(new ASSOC2_SEVal_mouseAdapter(this));
    SECheckBox.addMouseListener(new ASSOC2_jComboBoxSE_mouseAdapter(this));
    SECheckBox.setToolTipText("Specifies the inclusion of a sibling effect in the model.");
    SECheckBox.setVerifyInputWhenFocusTarget(true);
    SECheckBox.setMargin(new Insets(2, 0, 2, 2));
    SECheckBox.setSelected(true);
    SECheckBox.setText("Estimate Sibling");
    SECheckBox.addItemListener(this);
    SEfixedCheckBox.setEnabled(false);
    SEfixedCheckBox.setToolTipText("Specifies that the variance of this effect is fixed.");
    SEfixedCheckBox.setText("Fixed");
    SEfixedCheckBox.addMouseListener(new ASSOC2_jComboBox9_mouseAdapter(this));
    SEvalLabel.setToolTipText("Specifies the initial estimate for the variance of this effect.");
    SEvalLabel.setText("Value");
    SEvalLabel.addMouseListener(new ASSOC2_jCheckBox6_mouseAdapter(this));

    MECheckBox.addMouseListener(new ASSOC2_jComboBoxME_mouseAdapter(this));
    MECheckBox.setToolTipText("Specifies the inclusion of a marital effect in the model.");
    MECheckBox.setMargin(new Insets(2, 0, 2, 2));
    MECheckBox.setSelected(true);
    MECheckBox.setText("Estimate Marital");
    MECheckBox.addItemListener(this);
    MEVal.setText("");
    MEVal.addMouseListener(new ASSOC2_MEVal_mouseAdapter(this));
    MEfixedCheckBox.setEnabled(false);
    MEfixedCheckBox.setToolTipText("Specifies that the variance of this effect is fixed.");
    MEfixedCheckBox.setText("Fixed");
    MEfixedCheckBox.addMouseListener(new ASSOC2_jComboBox8_mouseAdapter(this));
    MEvalLabel.setToolTipText("Specifies the initial estimate for the variance of this effect.");
    MEvalLabel.setText("Value");
    MEvalLabel.addMouseListener(new ASSOC2_jCheckBox5_mouseAdapter(this));

    CELabel.setText("Class effect");
    CELabel.setToolTipText("Specifies the inclusion of a class effect in the model.");
    CETextField.setEditable(false);
    CEDefineButton.setText("Define");
    CEDefineButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
    CEDefineButton.setPreferredSize(new java.awt.Dimension(45, 20));
    CEResetButton.setText("Reset");
    CEResetButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
    CEResetButton.setPreferredSize(new java.awt.Dimension(45, 20));
    CEDefineButton.addActionListener(this);
    CEResetButton.addActionListener(this);

    jPanel3.setBorder(titledBorder3);
    jPanel3.setLayout(xYLayout4);
    optionsLabel.setText("Other options");
    batchCheckBox.addMouseListener(new ASSOC2_jComboBox1_mouseAdapter(this));
    batchCheckBox.setToolTipText(
            "<html>Instructs ASSOC to individually test each variable specified"+
            "<br>as a covariate when importing the data file, or indicated as"+
            "<br>being a test covariate in a regression model.");
    batchCheckBox.setMargin(new Insets(2, 0, 2, 2));
    batchCheckBox.setText("Batch mode enabled");
    batchCheckBox.addItemListener(this);

    residualLabel.setText("Residuals");
    residualLabel.setToolTipText("Specifies which residual output file(s) to produce.");
    resTextField.setEnabled(false);
    resTextField.setEditable(false);
    resTextField.setText("");
    resDefineButton.setMargin(new Insets(2, 2, 2, 2));
    resDefineButton.setText("Define");
    resDefineButton.addActionListener(this);
    resResetButton.addActionListener(this);
    resResetButton.setMargin(new Insets(1, 1, 1, 1));
    resResetButton.setText("Reset");

    jPanel1.add(PECheckBox,       new XYConstraints(2, 2, 140, 20));
    jPanel1.add(PEvalLabel,       new XYConstraints(150, 2, 30, 20));
    jPanel1.add(PEVal,            new XYConstraints(190, 2, 50, 20));
    jPanel1.add(PEfixedCheckBox,  new XYConstraints(250, 2, 55, 20));

    jPanel1.add(FECheckBox,       new XYConstraints(2, 32, 140, 20));
    jPanel1.add(FEvalLabel,       new XYConstraints(150, 32, 30, 20));
    jPanel1.add(FEVal,            new XYConstraints(190, 32, 50, 20));
    jPanel1.add(FEfixedCheckBox,  new XYConstraints(250, 32, 55, 20));

    jPanel1.add(MECheckBox,       new XYConstraints(2, 62, 140, 20));
    jPanel1.add(MEvalLabel,       new XYConstraints(150, 62, 30, 20));
    jPanel1.add(MEVal,            new XYConstraints(190, 62, 50, 20));
    jPanel1.add(MEfixedCheckBox,  new XYConstraints(250, 62, 55, 20));

    jPanel1.add(SECheckBox,       new XYConstraints(2, 92, 140, 20));
    jPanel1.add(SEvalLabel,       new XYConstraints(150, 92, 30, 20));
    jPanel1.add(SEVal,            new XYConstraints(190, 92, 50, 20));
    jPanel1.add(SEfixedCheckBox,  new XYConstraints(250, 92, 55, 20));

    jPanel1.add(new javax.swing.JSeparator(), new XYConstraints(2, 121, 300, 5));

    jPanel1.add(CELabel,          new XYConstraints(2, 130, 130, 20));
    jPanel1.add(CETextField,      new XYConstraints(75, 130, 130, 20));
    jPanel1.add(CEDefineButton,   new XYConstraints(210, 130, 45, 20));
    jPanel1.add(CEResetButton,    new XYConstraints(260, 130, 45, 20));

    jPanel3.add(batchCheckBox,    new XYConstraints(2, 2, 160, 20));

    this.add(titleLabel,          new XYConstraints(20, 20, 75, 20));
    this.add(titleTextField,      new XYConstraints(155, 20, 320, 20));
    this.add(jRunButton,          new XYConstraints(420, 520, 60, 25));
    this.add(covLabel,            new XYConstraints(20, 80, 80, 20));
    this.add(covTextField,        new XYConstraints(155, 80, 219, 20));
    this.add(covDefineButton,     new XYConstraints(380, 80, 45, 20));
    this.add(covResetButton,      new XYConstraints(430, 80, 45, 20));
    this.add(traitLabel,          new XYConstraints(20, 50, 80, 20));
    this.add(traitComboBox,       new XYConstraints(155, 50, 320, 20));
    this.add(transLabel,          new XYConstraints(20, 110, 120, 20));
    this.add(transTextField,      new XYConstraints(155, 110, 219, 20));
    this.add(transDefineButton,   new XYConstraints(380, 110, 45, 20));
    this.add(transResetButton,    new XYConstraints(430, 110, 45, 20));

    this.add(summaryLabel,          new XYConstraints(20, 140, 120, 20));
    this.add(summaryTextField,      new XYConstraints(155, 140, 219, 20));
    this.add(summaryDefineButton,   new XYConstraints(380, 140, 45, 20));
    this.add(summaryResetButton,    new XYConstraints(430, 140, 45, 20));

    this.add(residualLabel, new XYConstraints(20, 170, 120, 20));
    this.add(resTextField,      new XYConstraints(155, 170, 219, 20));
    this.add(resDefineButton,   new XYConstraints(380, 170, 45, 20));
    this.add(resResetButton,    new XYConstraints(430, 170, 45, 20));

    this.add(allowaveLabel,       new XYConstraints(20, 200, 85, 20));
    this.add(allowaveComboBox,    new XYConstraints(155, 200, 320, 20));

    this.add(varcomLabel,         new XYConstraints(20, 230, 120, 20));
    this.add(jPanel1,             new XYConstraints(155, 230, 320, 166));//128

    this.add(optionsLabel,        new XYConstraints(20, 406, 120, 20));
    this.add(jPanel3,             new XYConstraints(155, 406, 320, 40));

    titleTextField.getDocument().addDocumentListener(this);
    covTextField.getDocument().addDocumentListener(this);

    allowaveComboBox.addItemListener(this);
    traitComboBox.addItemListener(this);

    Datamodel.setValue("Title", titleTextField.getText());
    Datamodel.setValue("PE", "true");
    Datamodel.setValue("FE", "false");
    Datamodel.setValue("ME", "true");
    Datamodel.setValue("SE", "true");
    Datamodel.setValue("AA", "none");

    SEVal.getDocument().addDocumentListener(this);
    FEVal.getDocument().addDocumentListener(this);
    PEVal.getDocument().addDocumentListener(this);
    MEVal.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == covDefineButton)
      covDefineButton_actionPerformed();
    else if (ob == transDefineButton)
      transDefineButton_actionPerformed();
    else if (ob == covResetButton)
      covResetButton_actionPerformed();
    else if (ob == transResetButton)
      transResetButton_actionPerformed();

  else if (ob == resDefineButton)
      resDefineButton_actionPerformed();
  else if (ob == resResetButton)
      resResetButton_actionPerformed();

  else if (ob == summaryDefineButton)
      summaryDefineButton_actionPerformed();
  else if (ob == summaryResetButton)
      summaryResetButton_actionPerformed();

    else if (ob == CEDefineButton)
      CEDefineButton_actionPerformed();
    else if (ob == CEResetButton)
      CEResetButton_actionPerformed();
    else if (ob == jRunButton)
      jRunButton_actionPerformed(e);
  }

  public void covDefineButton_actionPerformed() {
     covDialog.showDialog();
  }

  public void resDefineButton_actionPerformed() {
     residualsDialog.showDialog();
  }

  public void resResetButton_actionPerformed()
  {
      if(Datamodel.hasValue("assoc_residuals"))
      {
        resTextField.setText("");
        residualLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("assoc_residuals");
        residualsDialog.listModel.removeAllElements();
    }
  }

  public void summaryDefineButton_actionPerformed() {
     summaryDialog.showDialog();
  }

  public void transDefineButton_actionPerformed() {
   transDialog.showDialog();
}


  public void CEDefineButton_actionPerformed() {
     CEDialog.showDialog();
  }

  public void covResetButton_actionPerformed() {
    if(Datamodel.hasValue("assoc_cov"))
    {
      covTextField.setText("");
      covLabel.setFont(new java.awt.Font("Dialog", 0, 11));
      Datamodel.removeValue("assoc_cov");
      covDialog.listModel.removeAllElements();
    }
  }

  public void transResetButton_actionPerformed() {
    if(Datamodel.hasValue("transformation_info"))
    {
      transTextField.setText("");
      transLabel.setFont(new java.awt.Font("Dialog", 0, 11));
      Datamodel.removeValue("transformation_info");
    }
  }

  public void summaryResetButton_actionPerformed() {
    if(Datamodel.hasValue("summary_display"))
    {
      summaryTextField.setText("");
      summaryLabel.setFont(new java.awt.Font("Dialog", 0, 11));
      Datamodel.removeValue("summary_display");
    }
  }
  public void CEResetButton_actionPerformed() {
    if(Datamodel.hasValue("class_effect"))
    {
      CETextField.setText("");
      CELabel.setFont(new java.awt.Font("Dialog", 0, 11));
      Datamodel.removeValue("class_effect");
      CEDialog.listModel.removeAllElements();
    }
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) assoc1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();

    mf.RunASSOC(treenode, currentnode, e.getActionCommand());
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == titleTextField.getDocument()) {
      if (titleTextField.getText().length() > 0)
        Datamodel.setValue("Title", titleTextField.getText());
      else
        Datamodel.removeValue("Title");
    }

    if (document == PEVal.getDocument()) {
      if(PEVal.getText().length()>0)
      {
        Datamodel.setValue("PEVal", PEVal.getText());
        PEfixedCheckBox.setEnabled(true);
      }
      else
      {
        Datamodel.removeValue("PEVal");
        PEfixedCheckBox.setEnabled(false);
      }
    }
    if (document == FEVal.getDocument()) {
      if (FEVal.getText().length() > 0)
      {
        Datamodel.setValue("FEVal", FEVal.getText());
        FEfixedCheckBox.setEnabled(true);
      }
      else
      {
        Datamodel.removeValue("FEVal");
        FEfixedCheckBox.setEnabled(false);
      }
    }
    if (document == MEVal.getDocument()) {
      if (MEVal.getText().length() > 0)
      {
        Datamodel.setValue("MEVal", MEVal.getText());
        MEfixedCheckBox.setEnabled(true);
      }
      else
      {
        Datamodel.removeValue("MEVal");
        MEfixedCheckBox.setEnabled(false);

      }
    }
    if (document == SEVal.getDocument()) {
      if (SEVal.getText().length() > 0)
      {
        Datamodel.setValue("SEVal", SEVal.getText());
        SEfixedCheckBox.setEnabled(true);
      }
      else
      {
        Datamodel.removeValue("SEVal");
        SEfixedCheckBox.setEnabled(false);

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

    if (ob == traitComboBox) {

      String value = traitComboBox.getSelectedItem().toString();

      if (traitComboBox.getSelectedIndex() <= 0)
      {
          if (Datamodel.hasValue("Trait"))
              Datamodel.removeValue("Trait");
      }
      else
      {
          Datamodel.setValue("Trait", value);
      }

      //not allow "transformation" for binary : as of Feb. 25, 2009
      //updated. not allow "both-sides of transformation" for binary : as of Oct. 25, 2011
      int type = Frame1.mainFrame1.activeinframe.getTraitType(value);

      switch (type)
      {
          case 1: //Binary

              transDialog.jCheckboxBothSides.setEnabled(false);

              break;
          case 2: //Quantitative
              transDialog.jCheckboxBothSides.setEnabled(true);
              break;
      }

      if (Datamodel.hasValue("Trait")) {
        jRunButton.setIcon(next_image);
      }
      else
          jRunButton.setIcon(error_image);
    }

    if (ob == PECheckBox) {
       if(PECheckBox.isSelected())//true
       {
         Datamodel.setValue("PE", "true");
         PEvalLabel.setEnabled(true);
         PEVal.setEnabled(true);
         PEVal.requestFocus(true);
       }
       else //false
       {
         Datamodel.setValue("PE", "false");
         PEvalLabel.setEnabled(false);
         PEVal.setEnabled(false);
         PEfixedCheckBox.setEnabled(false);
       }
     }

     if (ob == FECheckBox) {
       if(FECheckBox.isSelected())//true
       {
         Datamodel.setValue("FE", "true");
         FEvalLabel.setEnabled(true);
         FEVal.setEnabled(true);
         FEVal.requestFocus(true);

       }
       else//false
       {
         Datamodel.setValue("FE", "false");
         FEvalLabel.setEnabled(false);
         FEVal.setEnabled(false);
         FEfixedCheckBox.setEnabled(false);
       }
     }

     if (ob == MECheckBox) {
       if(MECheckBox.isSelected())//true
       {
         Datamodel.setValue("ME", "true");
         MEvalLabel.setEnabled(true);
         MEVal.setEnabled(true);
         MEVal.requestFocus(true);
       }
       else//false
       {
         Datamodel.setValue("ME", "false");
         MEVal.setEnabled(false);
         MEfixedCheckBox.setEnabled(false);
         MEvalLabel.setEnabled(false);
       }
     }
     if (ob == SECheckBox) {
       if(SECheckBox.isSelected())//true
       {
         Datamodel.setValue("SE", "true");
         SEvalLabel.setEnabled(true);
         SEVal.setEnabled(true);
         SEVal.requestFocus(true);
       }
       else//false
       {
         Datamodel.setValue("SE", "false");
         SEvalLabel.setEnabled(false);
         SEVal.setEnabled(false);
         SEfixedCheckBox.setEnabled(false);
       }
     }
     if (ob == allowaveComboBox) {
       Datamodel.setValue("AA", realaa[allowaveComboBox.getSelectedIndex()]);
     }

   if (ob == batchCheckBox) {
      if (batchCheckBox.isSelected())
        Datamodel.setValue("batch", "use");
      else
      {
        if (Datamodel.hasValue("batch"))
          Datamodel.removeValue("batch");
      }
    }
  }

  void title_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("titl", false, 330);
  }

  void jComboBoxTrait_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("primary_trai", false, 330);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("intercept ", false, 330);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("no_transform", false, 330);
  }

  void jTextFieldCov_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 330);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("batch ", false, 331);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("limit_output ", false, 331);
  }

  void jComboBoxPE_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("polygenic_effect", false, 332);
  }

  void jCheckBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("polygenic_effect", false, 332);
  }

  void PEVal_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("polygenic_effect", false, 332);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("polygenic_effect", false, 332);
  }

  void jComboBoxFE_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("family_effect", false, 332);
  }

  void jCheckBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("family_effect", false, 332);
  }

  void FEVal_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("family_effect", false, 332);
  }

  void jComboBox7_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("family_effect", false, 332);
  }

  void jComboBoxME_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marital_effect", false, 332);
  }

  void jCheckBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marital_effect", false, 332);
  }

  void MEVal_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marital_effect", false, 332);
  }

  void jComboBox8_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marital_effect", false, 332);
  }

  void jComboBoxSE_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sibship_effect", false, 333);
  }

  void jCheckBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sibship_effect", false, 333);
  }

  void SEVal_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sibship_effect", false, 333);
  }

  void jComboBox9_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sibship_effect", false, 333);
  }

  void jComboBoxAA_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("allow_averaging", false, 333);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("transformation", false, 333);
  }
}

class ASSOC2_title_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_title_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.title_mouseClicked(e);
  }
}

class ASSOC2_jComboBoxTrait_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBoxTrait_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxTrait_mouseClicked(e);
  }
}

class ASSOC2_jTextFieldCov_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jTextFieldCov_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextFieldCov_mouseClicked(e);
  }
}

class ASSOC2_jComboBoxPE_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBoxPE_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxPE_mouseClicked(e);
  }
}

class ASSOC2_PEVal_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_PEVal_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.PEVal_mouseClicked(e);
  }
}

class ASSOC2_jComboBox6_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBox6_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class ASSOC2_jComboBoxFE_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBoxFE_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxFE_mouseClicked(e);
  }
}

class ASSOC2_jCheckBox3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jCheckBox3_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox3_mouseClicked(e);
  }
}

class ASSOC2_jCheckBox4_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jCheckBox4_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox4_mouseClicked(e);
  }
}

class ASSOC2_jComboBox7_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBox7_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox7_mouseClicked(e);
  }
}

class ASSOC2_jComboBoxME_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBoxME_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxME_mouseClicked(e);
  }
}

class ASSOC2_jCheckBox5_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jCheckBox5_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox5_mouseClicked(e);
  }
}

class ASSOC2_MEVal_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_MEVal_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.MEVal_mouseClicked(e);
  }
}

class ASSOC2_jComboBox8_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBox8_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox8_mouseClicked(e);
  }
}

class ASSOC2_jComboBoxSE_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBoxSE_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxSE_mouseClicked(e);
  }
}

class ASSOC2_jCheckBox6_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jCheckBox6_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox6_mouseClicked(e);
  }
}

class ASSOC2_SEVal_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_SEVal_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.SEVal_mouseClicked(e);
  }
}

class ASSOC2_jComboBox9_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBox9_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox9_mouseClicked(e);
  }
}

class ASSOC2_jComboBoxAA_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBoxAA_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxAA_mouseClicked(e);
  }
}

class ASSOC2_FEVal_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_FEVal_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.FEVal_mouseClicked(e);
  }
}

class ASSOC2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jTextField1_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class ASSOC2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBox4_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class ASSOC2_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBox3_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class ASSOC2_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBox1_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class ASSOC2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC2 adaptee;

  ASSOC2_jComboBox2_mouseAdapter(ASSOC2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}


