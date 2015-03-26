package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class SEGREG3
    extends SageAnalysisPanel
    implements ActionListener, ItemListener {
  XYLayout xYLayout1 = new XYLayout();
  String[] classoption = {
      "", "Bonney's Class A Model", "Bonney's class D Model",
      "Finite Polygenic Mixed Model(FPMM)"};
  String[] realclassoption = {
      "", "A", "D", "FPMM"};

  DataCollectionModel Datamodel;
  TitledBorder titledBorder1;

  JButton jButton10 = new JButton();
  JButton jButton1 = new JButton();
  JButton jButton3 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JButton jButton7 = new JButton();
  JButton jButton8 = new JButton();
  JButton jButton9 = new JButton();

  JLabel classLabel = new JLabel();
  JLabel typeMeanLabel = new JLabel();
  JLabel meanCovLabel = new JLabel();
  JLabel typeVarLabel = new JLabel();
  JLabel varCovLabel = new JLabel();
  JLabel fpmmLabel = new JLabel();
  JLabel resCorLabel = new JLabel();
  JLabel transformLabel = new JLabel();
  JLabel genoFreq1Label = new JLabel();
  JLabel genoFreq2Label = new JLabel();

  JLabel transmissionLabel = new JLabel();
  JLabel comTraitLabel = new JLabel();

  JLabel ascertLabel = new JLabel();

  JComboBox jComboBox1 = new JComboBox(classoption);
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JTextField jTextField9 = new JTextField();
  JTextField jTextField10 = new JTextField();
  JTextField jTextField11 = new JTextField();
  JButton jButton11 = new JButton();

  SEGREG_Dialog1_Com dialog1 = new SEGREG_Dialog1_Com("Specification");
  SEGREG_Dialog2_TypeMean dialog2 = new SEGREG_Dialog2_TypeMean(this, "Specification");
  SEGREG_Dialog3_TypeVar dialog3 = new SEGREG_Dialog3_TypeVar("Specification");
  SEGREG_Dialog5_MeanCov dialog5 = new SEGREG_Dialog5_MeanCov("Specification");
  SEGREG_Dialog6_VarCov dialog6 = new SEGREG_Dialog6_VarCov("Specification");
  SEGREG_Dialog8_Fpmm dialog8 = new SEGREG_Dialog8_Fpmm("Specification");
  SEGREG_Dialog9_Resid dialog9 = new SEGREG_Dialog9_Resid("Specification");
  SEGREG_Dialog10_TransF dialog10 = new SEGREG_Dialog10_TransF("Specification");
  SEGREG_Dialog11_Geno dialog11 = new SEGREG_Dialog11_Geno("Specification");
  SEGREG_Dialog12_TransM dialog12 = new SEGREG_Dialog12_TransM("Specification");
  SEGREG_Dialog13_Ascer dialog13 = new SEGREG_Dialog13_Ascer("Specification");

  SEGREG2 segreg2;
  JButton jButton12 = new JButton();
  JButton jButton13 = new JButton();
  JButton jButton14 = new JButton();
  JButton jButton15 = new JButton();
  JButton jButton16 = new JButton();
  JButton jButton17 = new JButton();
  JButton jButton18 = new JButton();
  JButton jButton19 = new JButton();
  JButton jButton20 = new JButton();
  JButton jButton21 = new JButton();
  JButton jButton22 = new JButton();

  public SEGREG3(SEGREG2 input) {
    setModel(new PropertyDataModel());
    segreg2 = input;

    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void setModel(DataCollectionModel model) {
  this.Datamodel = model;
 }


  void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    this.setLayout(xYLayout1);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    jRunButton.setIcon(back_image);
    jRunButton.setText("Next");

    jButton10.setText("Define");
    jButton10.setEnabled(false);
    jButton10.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("Define");
    jButton1.setEnabled(false);
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Define");
    jButton3.setEnabled(false);
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Define");
    jButton2.setEnabled(false);
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton4.setText("Define");
    jButton4.setEnabled(false);
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton5.setText("Define");
    jButton5.setEnabled(false);
    jButton5.setMargin(new Insets(2, 2, 2, 2));
    jButton6.setText("Define");
    jButton6.setEnabled(false);
    jButton6.setMargin(new Insets(2, 2, 2, 2));
    jButton7.setText("Define");
    jButton7.setEnabled(false);
    jButton7.setMargin(new Insets(2, 2, 2, 2));
    jButton8.setText("Define");
    jButton8.setEnabled(false);
    jButton8.setMargin(new Insets(2, 2, 2, 2));
    jButton9.setText("Define");
    jButton9.setEnabled(false);
    jButton9.setMargin(new Insets(2, 2, 2, 2));

    classLabel.setForeground(Color.red);
    classLabel.setToolTipText("Specifies the model class.");
    classLabel.setText("Model class");
    typeMeanLabel.setToolTipText("Specifies the number of component distributions : 1, 2 or 3.");
    typeMeanLabel.setText("Type mean");
    meanCovLabel.setToolTipText("Specifies covariates for the mean.");
    meanCovLabel.setText("Mean covariates");
    typeVarLabel.setToolTipText("Specifies the number of variances.");
    typeVarLabel.setText("Type variance");
    varCovLabel.setToolTipText("Specifies covariates for the variance.");
    varCovLabel.setText("Variance covariates");
    fpmmLabel.setToolTipText("Details for the FPMM model.");
    fpmmLabel.setText("FPMM");
    resCorLabel.setToolTipText("Specifies residual correlations.");
    resCorLabel.setText("Residual correlations");
    transformLabel.setToolTipText("Specifies transformation options.");
    transformLabel.setText("Transformation");
    genoFreq1Label.setToolTipText("Specifies the founder genotype frequency model.");
    genoFreq2Label.setToolTipText("Specifies the founder genotype frequency model.");
    genoFreq1Label.setText("Genotype frequency");
    genoFreq2Label.setText("model");
    transmissionLabel.setToolTipText("Specifies the transmission model.");
    transmissionLabel.setText("Transmission");
    comTraitLabel.setToolTipText("Specifies composite trait covariates.");
    comTraitLabel.setText("Composite trait");
    ascertLabel.setToolTipText("Specifies the pedigree ascertainment options.");
    ascertLabel.setText("Ascertainment");
    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);
    jTextField1.setText("");
    jTextField1.addMouseListener(new SEGREG3_jTextField1_mouseAdapter(this));
    jTextField3.setEnabled(false);
    jTextField3.setEditable(false);
    jTextField3.setText("");
    jTextField3.addMouseListener(new SEGREG3_jTextField3_mouseAdapter(this));
    jTextField4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setText("");
    jTextField4.addMouseListener(new SEGREG3_jTextField4_mouseAdapter(this));
    jTextField5.setEnabled(false);
    jTextField5.setEditable(false);
    jTextField5.setText("");
    jTextField5.addMouseListener(new SEGREG3_jTextField5_mouseAdapter(this));
    jTextField6.setEnabled(false);
    jTextField6.setEditable(false);
    jTextField6.setText("");
    jTextField6.addMouseListener(new SEGREG3_jTextField6_mouseAdapter(this));
    jTextField7.setEnabled(false);
    jTextField7.setEditable(false);
    jTextField7.setText("");
    jTextField7.addMouseListener(new SEGREG3_jTextField7_mouseAdapter(this));
    jTextField8.setEnabled(false);
    jTextField8.setEditable(false);
    jTextField8.setText("");
    jTextField8.addMouseListener(new SEGREG3_jTextField8_mouseAdapter(this));
    jTextField9.setEnabled(false);
    jTextField9.setEditable(false);
    jTextField9.setText("");
    jTextField9.addMouseListener(new SEGREG3_jTextField9_mouseAdapter(this));
    jTextField10.setEnabled(false);
    jTextField10.setEditable(false);
    jTextField10.setText("");
    jTextField10.addMouseListener(new SEGREG3_jTextField10_mouseAdapter(this));
    jTextField2.setEnabled(false);
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.addMouseListener(new SEGREG3_jTextField2_mouseAdapter(this));

    jTextField11.setEnabled(false);
    jTextField11.setEditable(false);
    jTextField11.setText("");
    jTextField11.addMouseListener(new SEGREG3_jTextField11_mouseAdapter(this));
    jButton11.setEnabled(false);
    jButton11.setMargin(new Insets(2, 2, 2, 2));
    jButton11.setText("Define");
    jComboBox1.addMouseListener(new SEGREG3_jComboBox1_mouseAdapter(this));
    jButton12.setEnabled(false);
    jButton12.setHorizontalTextPosition(SwingConstants.CENTER);
    jButton12.setMargin(new Insets(2, 2, 2, 2));
    jButton12.setText("Reset");
    jButton13.setEnabled(false);
    jButton13.setMargin(new Insets(2, 2, 2, 2));
    jButton13.setText("Reset");
    jButton14.setEnabled(false);
    jButton14.setMargin(new Insets(2, 2, 2, 2));
    jButton14.setText("Reset");
    jButton15.setEnabled(false);
    jButton15.setMargin(new Insets(2, 2, 2, 2));
    jButton15.setText("Reset");
    jButton16.setEnabled(false);
    jButton16.setMargin(new Insets(2, 2, 2, 2));
    jButton16.setText("Reset");
    jButton17.setEnabled(false);
    jButton17.setMargin(new Insets(2, 2, 2, 2));
    jButton17.setText("Reset");
    jButton18.setEnabled(false);
    jButton18.setMargin(new Insets(2, 2, 2, 2));
    jButton18.setText("Reset");
    jButton19.setEnabled(false);
    jButton19.setMargin(new Insets(2, 2, 2, 2));
    jButton19.setText("Reset");
    jButton20.setEnabled(false);
    jButton20.setMargin(new Insets(2, 2, 2, 2));
    jButton20.setText("Reset");
    jButton21.setEnabled(false);
    jButton21.setMargin(new Insets(2, 2, 2, 2));
    jButton21.setText("Reset");
    jButton22.setEnabled(false);
    jButton22.setMargin(new Insets(2, 2, 2, 2));
    jButton22.setText("Reset");
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(classLabel, new XYConstraints(20, 20, 120, 20));
    this.add(typeMeanLabel, new XYConstraints(20, 50, 120, 20));
    this.add(meanCovLabel, new XYConstraints(20, 80, 120, 20));
    this.add(typeVarLabel, new XYConstraints(20, 110, 120, 20));
    this.add(varCovLabel, new XYConstraints(20, 140, 120, 20));
    this.add(fpmmLabel, new XYConstraints(20, 170, 120, 20));
    this.add(resCorLabel, new XYConstraints(20, 200, 120, 20));
    this.add(transformLabel, new XYConstraints(20, 230, 120, 20));
    this.add(genoFreq1Label, new XYConstraints(20, 253, 120, 20));
    this.add(genoFreq2Label, new XYConstraints(20, 267, 73, 20));

    this.add(transmissionLabel, new XYConstraints(20, 290, 120, 20));
    this.add(comTraitLabel, new XYConstraints(20, 320, 120, 20));
    this.add(jComboBox1,    new XYConstraints(155, 20, 320, 20));
    this.add(jButton1,   new XYConstraints(380, 50, 45, 20));
    this.add(jButton3,    new XYConstraints(380, 110, 45, 20));
    this.add(jButton2,    new XYConstraints(380, 80, 45, 20));
    this.add(jButton4,    new XYConstraints(380, 140, 45, 20));
    this.add(jButton5,    new XYConstraints(380, 170, 45, 20));
    this.add(jButton6,    new XYConstraints(380, 200, 45, 20));
    this.add(jButton7,    new XYConstraints(380, 230, 45, 20));
    this.add(jButton8,    new XYConstraints(380, 260, 45, 20));
    this.add(jButton10,    new XYConstraints(380, 320, 45, 20));
    this.add(jButton9,    new XYConstraints(380, 290, 45, 20));
    this.add(jTextField1,    new XYConstraints(155, 50, 219, 20));
    this.add(jTextField2,     new XYConstraints(155, 80, 219, 20));
    this.add(jTextField3,     new XYConstraints(155, 110, 219, 20));
    this.add(jTextField4,     new XYConstraints(155, 140, 219, 20));
    this.add(jTextField5,     new XYConstraints(155, 170, 219, 20));
    this.add(jTextField6,     new XYConstraints(155, 200, 219, 20));
    this.add(jTextField7,     new XYConstraints(155, 230, 219, 20));
    this.add(jTextField8,     new XYConstraints(155, 260, 219, 20));
    this.add(jTextField9,     new XYConstraints(155, 290, 219, 20));
    this.add(jTextField10,     new XYConstraints(155, 320, 219, 20));
    this.add(ascertLabel, new XYConstraints(20, 350, 100, 20));
    this.add(jTextField11,     new XYConstraints(155, 350, 219, 20));
    this.add(jButton11,    new XYConstraints(380, 350, 45, 20));
    this.add(jButton12,    new XYConstraints(430, 50, 45, 20));
    this.add(jButton13,       new XYConstraints(430, 80, 45, 20));
    this.add(jButton14,       new XYConstraints(430, 110, 45, 20));
    this.add(jButton15,       new XYConstraints(430, 140, 45, 20));
    this.add(jButton16,       new XYConstraints(430, 170, 45, 20));
    this.add(jButton17,       new XYConstraints(430, 200, 45, 20));
    this.add(jButton18,       new XYConstraints(430, 230, 45, 20));
    this.add(jButton19,       new XYConstraints(430, 260, 45, 20));
    this.add(jButton20,       new XYConstraints(430, 290, 45, 20));
    this.add(jButton21,       new XYConstraints(430, 320, 45, 20));
    this.add(jButton22,       new XYConstraints(430, 350, 45, 20));

    jRunButton.addActionListener(this);

    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jButton3.addActionListener(this);
    jButton4.addActionListener(this);
    jButton5.addActionListener(this);
    jButton6.addActionListener(this);
    jButton7.addActionListener(this);
    jButton8.addActionListener(this);
    jButton9.addActionListener(this);
    jButton10.addActionListener(this);
    jButton11.addActionListener(this);

    jButton12.addActionListener(this);
    jButton13.addActionListener(this);
    jButton14.addActionListener(this);
    jButton15.addActionListener(this);
    jButton16.addActionListener(this);
    jButton17.addActionListener(this);
    jButton18.addActionListener(this);
    jButton19.addActionListener(this);
    jButton20.addActionListener(this);
    jButton21.addActionListener(this);
    jButton22.addActionListener(this);

    jComboBox1.addItemListener(this);

    dialog2.set_dataModel(Datamodel);
    dialog5.set_dataModel(Datamodel);
    dialog3.set_dataModel(Datamodel);
    dialog6.set_dataModel(Datamodel);
    dialog8.set_dataModel(Datamodel);
    dialog9.set_dataModel(Datamodel);
    dialog10.set_dataModel(Datamodel);
    dialog11.set_dataModel(Datamodel);
    dialog12.set_dataModel(Datamodel);
    dialog1.set_dataModel(Datamodel);
    dialog13.set_dataModel(Datamodel);

    jRunButton.setIcon(error_image);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    mf.jTabbedPane1.setSelectedIndex(1);
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if (ob == jComboBox1) {
      if(jComboBox1.getSelectedIndex()==0)
      {
        Datamodel.removeValue("class");
        jTextField5.setText("");
        jTextField6.setText("");
        jRunButton.setIcon(error_image);
      }

      if(segreg2.QRadioButton.isSelected())
      {
        int index = jComboBox1.getSelectedIndex(); //"","A", "D","FPMM"
       switch (index) {
         case 1:
           Datamodel.setValue("class", realclassoption[index]);
           jButton5.setEnabled(false);
           jTextField5.setText("Not applicable");
           jButton6.setEnabled(true);
           jTextField6.setText("");
           jRunButton.setIcon(next_image);

           if(Datamodel.hasValue("fpmm"))
             Datamodel.removeValue("fpmm");
           break;
         case 2:
           Datamodel.setValue("class", realclassoption[index]);
           jButton5.setEnabled(false);
           jTextField5.setText("Not applicable");
           jButton6.setEnabled(true);
           jTextField6.setText("");
           jRunButton.setIcon(next_image);

           if(Datamodel.hasValue("fpmm"))
             Datamodel.removeValue("fpmm");
           break;
         case 3:
           Datamodel.setValue("class", realclassoption[index]);
           jButton6.setEnabled(false);
           jTextField6.setText("Not applicable");
           jButton5.setEnabled(true);
           jTextField5.setText("");
           jRunButton.setIcon(next_image);
           if(Datamodel.hasValue("resid"))
             Datamodel.removeValue("resid");
           break;
       }
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if (ob == jRunButton)
      jRunButton_actionPerformed(e);

    if (ob == jButton1) {
        dialog2.showDialog();
    }

    if (ob == jButton12) { // Reset Type mean
      if(Datamodel.hasValue("type_mean"))
      {
        jTextField1.setText("");
        typeMeanLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("type_mean");
        dialog2.listModel.removeAllElements();

        if(Datamodel.hasValue("type_mean_option"))
        {
            if(Datamodel.getValue("type_mean_option").toString().compareTo("one")==0)
            {
                jTextField9.setText("");
                jButton9.setEnabled(true);
            }
        }
      }
    }

    if (ob == jButton2) {
      dialog5.resulttextfield = jTextField2;
      dialog5.resultlabel = meanCovLabel;
      dialog5.showDialog();
    }

    if (ob == jButton13) { // Reset Mean cov
      if(Datamodel.hasValue("mean_cov"))
      {
        jTextField2.setText("");
        meanCovLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("mean_cov");
        dialog5.listModel.removeAllElements();
      }
    }

    if (ob == jButton3) {
      dialog3.resulttextfield = jTextField3;
      dialog3.resultlabel = typeVarLabel;
      dialog3.showDialog();
    }

    if (ob == jButton14) { // Reset Type var
      if(Datamodel.hasValue("type_var"))
      {
        jTextField3.setText("");
        typeVarLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("type_var");
        dialog3.listModel.removeAllElements();
      }
    }

    if (ob == jButton4) {
      dialog6.resulttextfield = jTextField4;
      dialog6.resultlabel = varCovLabel;
      dialog6.showDialog();
    }

    if (ob == jButton15) { // Reset Var cov
      if(Datamodel.hasValue("var_cov"))
      {
        jTextField4.setText("");
        varCovLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("var_cov");
        dialog6.listModel.removeAllElements();
      }
    }

    if (ob == jButton5) {
      dialog8.resulttextfield = jTextField5;
      dialog8.resultlabel = fpmmLabel;
      dialog8.showDialog();
    }

    if (ob == jButton16) { // Reset FPMM
      if(Datamodel.hasValue("fpmm"))
      {
        jTextField5.setText("");
        fpmmLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("fpmm");
      }
    }

    if (ob == jButton6) {
        dialog9.jLabel3.setText("Specifies the residual correlations.");

      dialog9.resulttextfield = jTextField6;
      dialog9.resultlabel = resCorLabel;
      dialog9.showDialog();
    }

    if (ob == jButton17) { // Reset Residual correlation
      if(Datamodel.hasValue("resid"))
      {
        jTextField6.setText("");
        resCorLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("resid");
      }
    }

    if (ob == jButton7) {
      dialog10.resulttextfield = jTextField7;
      dialog10.resultlabel = transformLabel;
      dialog10.showDialog();
    }

    if (ob == jButton18) { // Reset Transformation
      if(Datamodel.hasValue("transformation"))
      {
        jTextField7.setText("");
        transformLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("transformation");
      }
    }

    if (ob == jButton8) {
      dialog11.resulttextfield = jTextField8;
      dialog11.resultlabel1 = genoFreq1Label;
      dialog11.resultlabel2 = genoFreq2Label;
      dialog11.showDialog();
    }

    if (ob == jButton19) { // Reset Genotype frequency model
      if(Datamodel.hasValue("geno_freq"))
      {
        jTextField8.setText("");
        genoFreq1Label.setFont(new java.awt.Font("Dialog", 0, 11));
        genoFreq2Label.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("geno_freq");
      }
    }

    if (ob == jButton9) {
      dialog12.resulttextfield = jTextField9;
      dialog12.resultlabel = transmissionLabel;
      dialog12.showDialog();
    }

    if (ob == jButton20) { // Reset Transmission
      if(Datamodel.hasValue("transmission"))
      {
        jTextField9.setText("");
        transmissionLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("transmission");
      }
    }

    if (ob == jButton10) {
      dialog1.resulttextfield = jTextField10;
      dialog1.resultlabel = comTraitLabel;
      dialog1.showDialog();
    }

    if (ob == jButton21) { // Reset Composite trait
      if(Datamodel.hasValue("composite_trait"))
      {
        jTextField10.setText("");
        comTraitLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("composite_trait");
      }
    }

    if (ob == jButton11) {
      dialog13.resulttextfield = jTextField11;
      dialog13.resultlabel = ascertLabel;
      dialog13.showDialog();
    }

    if (ob == jButton22) { // Reset Ascertainment
      if(Datamodel.hasValue("ascertainment"))
      {
        jTextField11.setText("");
        ascertLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("ascertainment");
      }
    }

  }

  void jComboBox1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("class", false, 144);
  }

  void jTextField10_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("composite_trait", false, 143);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("type_mea", false, 143);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("type_va", false, 143);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("mean_cov", false, 144);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("var_cov", false, 144);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("fpmm", false, 144);
  }

  void jTextField6_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("resid", false, 144);
  }

  void jTextField7_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("transformation", false, 144);
  }

  void jTextField8_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("geno_freq", false, 145);
  }

  void jTextField9_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("transmission", false, 145);
  }

  void jTextField11_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("ascertainment", false, 145);
  }
}

class SEGREG3_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jComboBox1_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG3_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField1_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG3_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField2_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SEGREG3_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField3_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG3_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField4_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class SEGREG3_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField5_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class SEGREG3_jTextField6_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField6_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField6_mouseClicked(e);
  }
}

class SEGREG3_jTextField7_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField7_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField7_mouseClicked(e);
  }
}

class SEGREG3_jTextField8_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField8_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField8_mouseClicked(e);
  }
}

class SEGREG3_jTextField9_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField9_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField9_mouseClicked(e);
  }
}

class SEGREG3_jTextField10_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField10_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField10_mouseClicked(e);
  }
}

class SEGREG3_jTextField11_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG3 adaptee;

  SEGREG3_jTextField11_mouseAdapter(SEGREG3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField11_mouseClicked(e);
  }
}
