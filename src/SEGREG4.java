package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.awt.event.*;

public class SEGREG4
    extends SageAnalysisPanel
    implements ActionListener, ItemListener {
  XYLayout xYLayout1 = new XYLayout();
  String[] classoption = {
      "", "Multivariate Logistic Model(MLM)", "Finite Polygenic Mixed Model(FPMM)"};
  String[] realclassoption = {
      "", "MLM", "FPMM"};

  DataCollectionModel Datamodel;

  JButton jButton1 = new JButton();
  JButton fpmmDefineButton = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JButton jButton7 = new JButton();
  JButton jButton8 = new JButton();
  JButton jButton9 = new JButton();

  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel fpmmLabel = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JComboBox jComboBox1 = new JComboBox(classoption);
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField fpmmTextField = new JTextField();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JTextField jTextField9 = new JTextField();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel7 = new JLabel();

  SEGREG_Dialog4_TypeSuscept dialog4 = new SEGREG_Dialog4_TypeSuscept(this, "Specification");
  SEGREG_Dialog7_SusCov dialog7 = new SEGREG_Dialog7_SusCov("Specification");
  SEGREG_Dialog8_Fpmm dialog8 = new SEGREG_Dialog8_Fpmm("Specification");
  SEGREG_Dialog9_Resid dialog9 = new SEGREG_Dialog9_Resid("Specification");
  SEGREG_Dialog11_Geno dialog11 = new SEGREG_Dialog11_Geno("Specification");
  SEGREG_Dialog12_TransM dialog12 = new SEGREG_Dialog12_TransM("Specification");
  SEGREG_Dialog13_Ascer dialog13 = new SEGREG_Dialog13_Ascer("Specification");
  SEGREG_Dialog14_PrevC dialog14 = new SEGREG_Dialog14_PrevC("Specification");
  SEGREG_Dialog15_PrevE dialog15 = new SEGREG_Dialog15_PrevE("Specification");

  SEGREG2 segreg2;
  JButton jButton10 = new JButton();
  JButton jButton11 = new JButton();
  JButton fpmmResetButton = new JButton();
  JButton jButton13 = new JButton();
  JButton jButton14 = new JButton();
  JButton jButton15 = new JButton();
  JButton jButton16 = new JButton();
  JButton jButton17 = new JButton();
  JButton jButton18 = new JButton();

  public SEGREG4(SEGREG2 input) {
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

    this.setLayout(xYLayout1);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    dialog13.jLabel5.setEnabled(false);
    dialog13.jComboBox2.setEnabled(false);

    dialog8.jCheckBox1.setEnabled(false);
    dialog8.jLabel6.setEnabled(false);
    dialog8.jLabel7.setEnabled(false);
    dialog8.jLabel9.setEnabled(false);
    dialog8.jLabel10.setEnabled(false);

    jRunButton.setIcon(back_image);
    jRunButton.setText("Next");

    jButton1.setText("Define");
    jButton1.setEnabled(false);
    jButton1.setMargin(new Insets(2, 2, 2, 2));
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
    jButton6.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
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
    jLabel1.setForeground(Color.red);

    jLabel1.setToolTipText("Specifies the model class.");
    jLabel1.setText("Model class");
    jLabel2.setToolTipText(
            "Specifies the number of type susceptibilities, or penetrances : 1, 2 or 3.");
    jLabel2.setText("Type susceptibility");
    jLabel3.setToolTipText(
            "Specifies covariates for the trait susceptibility, or penetrance.");
    jLabel3.setText("Susceptibility covariates");
    jLabel5.setToolTipText("Specifies residual associations.");
    jLabel5.setText("Residual associations");
    jLabel6.setToolTipText("Specifies the founder genotype frequency model.");
    jLabel6.setText("Genotype frequency");
    jLabel9.setToolTipText("Specifies the founder genotype frequency model.");
    jLabel9.setText("model");

    jLabel8.setToolTipText("Specifies transformation options.");
    jLabel8.setText("Transmission");

    jLabel10.setToolTipText(
            "Specifies constraints on the population prevalence of a binary trait.");
    jLabel10.setText("Prevalence constraint");
    jLabel12.setToolTipText(
            "Specifies the pedigree ascertainment options.");
    jLabel12.setText("Ascertainment");
    jLabel7.setToolTipText(
            "Specifies the covariate values at which prevalence is to be estimated.");
    jLabel7.setText("Prevalence estimate");

    fpmmLabel.setToolTipText("Details for the FPMM model.");
    fpmmLabel.setText("FPMM");

    fpmmDefineButton.setText("Define");
    fpmmDefineButton.setEnabled(false);
    fpmmDefineButton.setMargin(new Insets(2, 2, 2, 2));

    fpmmTextField.setEnabled(false);
    fpmmTextField.setEditable(false);
    fpmmTextField.setText("");
    fpmmTextField.addMouseListener(new SEGREG4_jTextField3_mouseAdapter(this));

    fpmmResetButton.setEnabled(false);
    fpmmResetButton.setMargin(new Insets(2, 2, 2, 2));
    fpmmResetButton.setText("Reset");

    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);
    jTextField1.setText("");
    jTextField1.addMouseListener(new SEGREG4_jTextField1_mouseAdapter(this));
    jTextField4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setText("");
    jTextField4.addMouseListener(new SEGREG4_jTextField4_mouseAdapter(this));
    jTextField5.setEnabled(false);
    jTextField5.setEditable(false);
    jTextField5.setText("");
    jTextField5.addMouseListener(new SEGREG4_jTextField5_mouseAdapter(this));
    jTextField6.setEnabled(false);
    jTextField6.setEditable(false);
    jTextField6.setText("");
    jTextField6.addMouseListener(new SEGREG4_jTextField6_mouseAdapter(this));
    jTextField7.setEnabled(false);
    jTextField7.setEditable(false);
    jTextField7.setText("");
    jTextField7.addMouseListener(new SEGREG4_jTextField7_mouseAdapter(this));
    jTextField8.setEnabled(false);
    jTextField8.setEditable(false);
    jTextField8.setText("");
    jTextField8.addMouseListener(new SEGREG4_jTextField8_mouseAdapter(this));
    jTextField9.setEnabled(false);
    jTextField9.setEditable(false);
    jTextField9.setText("");
    jTextField9.addMouseListener(new SEGREG4_jTextField9_mouseAdapter(this));
    jTextField2.setEnabled(false);
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.addMouseListener(new SEGREG4_jTextField2_mouseAdapter(this));
    jComboBox1.addMouseListener(new SEGREG4_jComboBox1_mouseAdapter(this));
    jButton10.setEnabled(false);
    jButton10.setActionCommand("jButton10");
    jButton10.setMargin(new Insets(2, 2, 2, 2));
    jButton10.setText("Reset");
    jButton11.setEnabled(false);
    jButton11.setMargin(new Insets(2, 2, 2, 2));
    jButton11.setText("Reset");
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

    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));

    this.add(jLabel1, new XYConstraints(20, 20, 120, 20));
    this.add(jComboBox1,   new XYConstraints(155, 20, 320, 20));

    this.add(jLabel2, new XYConstraints(20, 50, 120, 20));
    this.add(jTextField1,    new XYConstraints(155, 50, 219, 20));
    this.add(jButton1,    new XYConstraints(380, 50, 45, 20));
    this.add(jButton10,    new XYConstraints(430, 50, 45, 20));

    this.add(jLabel3, new XYConstraints(20, 80, 140, 20));
    this.add(jButton2,    new XYConstraints(380, 80, 45, 20));
    this.add(jTextField2,     new XYConstraints(155, 80, 219, 20));
    this.add(jButton11,       new XYConstraints(430, 80, 45, 20));

    this.add(fpmmLabel, new XYConstraints(20, 110, 100, 20));
    this.add(fpmmTextField,     new XYConstraints(155, 110, 219, 20));
    this.add(fpmmDefineButton,    new XYConstraints(380, 110, 45, 20));
    this.add(fpmmResetButton,       new XYConstraints(430, 110, 45, 20));

    this.add(jLabel5, new XYConstraints(20, 140, 120, 20));
    this.add(jTextField4,     new XYConstraints(155, 140, 219, 20));
    this.add(jButton4,    new XYConstraints(380, 140, 45, 20));
    this.add(jButton13,       new XYConstraints(430, 140, 45, 20));

    this.add(jLabel6, new XYConstraints(20, 163, 120, 20));
    this.add(jLabel9, new XYConstraints(20, 177, 120, -1));
    this.add(jTextField5,     new XYConstraints(155, 170, 219, 20));
    this.add(jButton5,    new XYConstraints(380, 170, 45, 20));
    this.add(jButton14,       new XYConstraints(430, 170, 45, 20));

    this.add(jLabel8, new XYConstraints(20, 200, 120, 20));
    this.add(jTextField6,     new XYConstraints(155, 200, 219, 20));
    this.add(jButton6,    new XYConstraints(380, 200, 45, 20));
    this.add(jButton15,       new XYConstraints(430, 200, 45, 20));

    this.add(jLabel12, new XYConstraints(20, 230, 120, 20));
    this.add(jTextField7,     new XYConstraints(155, 230, 219, 20));
    this.add(jButton7,    new XYConstraints(380, 230, 45, 20));
    this.add(jButton16,       new XYConstraints(430, 230, 45, 20));

    this.add(jLabel10, new XYConstraints(20, 260, 120, 20));
    this.add(jTextField8,     new XYConstraints(155, 260, 219, 20));
    this.add(jButton8,    new XYConstraints(380, 260, 45, 20));
    this.add(jButton17,       new XYConstraints(430, 260, 45, 20));

    this.add(jLabel7, new XYConstraints(20, 290, 120, 20));
    this.add(jTextField9,     new XYConstraints(155, 290, 219, 20));
    this.add(jButton9,    new XYConstraints(380, 290, 45, 20));
    this.add(jButton18,       new XYConstraints(430, 290, 45, 20));

    jRunButton.addActionListener(this);
    jButton1.addActionListener(this);
    fpmmDefineButton.addActionListener(this);
    jButton2.addActionListener(this);
    jButton4.addActionListener(this);
    jButton5.addActionListener(this);
    jButton6.addActionListener(this);
    jButton7.addActionListener(this);
    jButton8.addActionListener(this);
    jButton9.addActionListener(this);

    jButton10.addActionListener(this);
    jButton11.addActionListener(this);
    jButton13.addActionListener(this);
    fpmmResetButton.addActionListener(this);
    jButton14.addActionListener(this);
    jButton15.addActionListener(this);
    jButton16.addActionListener(this);
    jButton17.addActionListener(this);
    jButton18.addActionListener(this);

    jComboBox1.addItemListener(this);

    dialog4.set_dataModel(Datamodel);
    dialog7.set_dataModel(Datamodel);
    dialog8.set_dataModel(Datamodel);
    dialog9.set_dataModel(Datamodel);
    dialog11.set_dataModel(Datamodel);
    dialog12.set_dataModel(Datamodel);
    dialog13.set_dataModel(Datamodel);
    dialog14.set_dataModel(Datamodel);
    dialog15.set_dataModel(Datamodel);

    jRunButton.setIcon(error_image);
  }

  public void jRunButton_actionPerformed() {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    mf.jTabbedPane1.setSelectedIndex(1);
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if (ob == jComboBox1) {
      if(jComboBox1.getSelectedIndex()==0)
      {
        Datamodel.removeValue("class");
        jTextField4.setText("");
        fpmmTextField.setText("");
        jRunButton.setIcon(error_image);
      }

      if(segreg2.BRadioButton.isSelected())
      {
        int index = jComboBox1.getSelectedIndex(); //"","MLM","FPMM"

        switch (index) {
          case 1:
            fpmmDefineButton.setEnabled(false);
            fpmmTextField.setText("not applicable");
            jButton4.setEnabled(true);
            jTextField4.setText("");
            Datamodel.setValue("class", realclassoption[index]);
            jRunButton.setIcon(next_image);
            if(Datamodel.hasValue("fpmm"))
                Datamodel.removeValue("fpmm");
            break;
          case 2:
            jButton4.setEnabled(false);
            jTextField4.setText("Not applicable");
            fpmmDefineButton.setEnabled(true);
            fpmmTextField.setText("");
            Datamodel.setValue("class", realclassoption[index]);

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
      jRunButton_actionPerformed();

    if (ob == jButton1) { //type susceptibility
        dialog4.showDialog();
    }

    if (ob == jButton10) { // Reset Type susceptibility
      if(Datamodel.hasValue("type_suscept"))
      {
        jTextField1.setText("");
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("type_suscept");
        dialog4.listModel.removeAllElements();

        if(Datamodel.hasValue("type_suscept_option"))
        {
            if(Datamodel.getValue("type_suscept_option").toString().compareTo("one")==0)
            {
                jTextField6.setText("");
                jButton6.setEnabled(true);
            }
        }
      }
    }

    if (ob == jButton2) { //susceptibility covariates
      dialog7.resulttextfield = jTextField2;
      dialog7.resultlabel = jLabel3;
        dialog7.showDialog();
    }

    if (ob == jButton11) { // Reset Susceptibility covariates
      if(Datamodel.hasValue("suscept_cov"))
      {
        jTextField2.setText("");
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("suscept_cov");
        dialog7.listModel.removeAllElements();
      }
    }

    if (ob == fpmmDefineButton) {
      dialog8.resulttextfield = fpmmTextField;
      dialog8.resultlabel = fpmmLabel;
      dialog8.showDialog();
    }

    if (ob == fpmmResetButton) { // Reset FPMM
      if(Datamodel.hasValue("fpmm"))
      {
        fpmmTextField.setText("");
        fpmmLabel.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("fpmm");
      }
    }

    if (ob == jButton4) {
        dialog9.jLabel3.setText("Specifies the residual associations.");
      dialog9.resulttextfield = jTextField4;
      dialog9.resultlabel = jLabel5;
      dialog9.showDialog();
    }

    if (ob == jButton13) { // Reset Residual correlation
      if(Datamodel.hasValue("resid"))
      {
        jTextField4.setText("");
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("resid");
      }
    }

    if (ob == jButton5) {
      dialog11.resulttextfield = jTextField5;
      dialog11.resultlabel1 = jLabel6;
      dialog11.resultlabel2 = jLabel9;
      dialog11.showDialog();
    }

    if (ob == jButton14) { // Reset Genotype frequency model
      if(Datamodel.hasValue("geno_freq"))
      {
        jTextField5.setText("");
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 11));
        jLabel9.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("geno_freq");
      }
    }

    if (ob == jButton6) {
      dialog12.resulttextfield = jTextField6;
      dialog12.resultlabel = jLabel8;
      dialog12.showDialog();
    }

    if (ob == jButton15) { // Reset Transmission
      if(Datamodel.hasValue("transmission"))
      {
        jTextField6.setText("");
        jLabel8.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("transmission");
      }
    }

    if (ob == jButton7) {
      dialog13.resulttextfield = jTextField7;
      dialog13.resultlabel = jLabel12;
        dialog13.showDialog();
    }

    if (ob == jButton16) { // Reset Ascertainment
      if(Datamodel.hasValue("ascertainment"))
      {
        jTextField7.setText("");
        jLabel12.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("ascertainment");
      }
    }

    if (ob == jButton8) {
      dialog14.resulttextfield = jTextField8;
      dialog14.resultlabel = jLabel10;
      dialog14.showDialog();
    }

    if (ob == jButton17) { // Reset Prevalence constraint
      if(Datamodel.hasValue("prev_constraint"))
      {
        jTextField8.setText("");
        jLabel10.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("prev_constraint");
        dialog14.listModel.removeAllElements();
      }
    }

    if (ob == jButton9) {
      dialog15.resulttextfield = jTextField9;
      dialog15.resultlabel = jLabel7;
      dialog15.showDialog();
    }

    if (ob == jButton18) { // Reset Prevalence estimate
      if(Datamodel.hasValue("prev_estimate"))
      {
        jTextField9.setText("");
        jLabel7.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("prev_estimate");
        dialog15.listModel.removeAllElements();
      }
    }

  }

  void jComboBox1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("class", false, 144);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("type_suscept", false, 143);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
          Frame1.mainFrame1.pdfframe.setTextonPage("suscept_cov", false, 144);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("fpmm", false, 144);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("resid", false, 144);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("geno_freq", false, 145);
  }

  void jTextField6_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("transmission", false, 145);
  }

  void jTextField7_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("ascertainment", false, 145);
  }

  void jTextField8_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("prev_constraints", false, 145);
  }

  void jTextField9_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("prev_estimate", false, 145);
  }
}

class SEGREG4_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jComboBox1_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG4_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField1_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG4_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField2_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SEGREG4_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField3_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG4_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField4_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class SEGREG4_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField5_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class SEGREG4_jTextField6_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField6_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField6_mouseClicked(e);
  }
}

class SEGREG4_jTextField7_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField7_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField7_mouseClicked(e);
  }
}

class SEGREG4_jTextField8_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField8_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField8_mouseClicked(e);
  }
}

class SEGREG4_jTextField9_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG4 adaptee;

  SEGREG4_jTextField9_mouseAdapter(SEGREG4 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField9_mouseClicked(e);
  }
}
