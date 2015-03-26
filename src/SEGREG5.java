package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class SEGREG5
    extends SageAnalysisPanel
    implements ActionListener{
  XYLayout xYLayout1 = new XYLayout();
  String[] classoption = {"Finite Polygenic Mixed Model(FPMM)"};

  DataCollectionModel Datamodel;
  TitledBorder titledBorder1;

  JButton jButton10 = new JButton();
  JButton jButton1 = new JButton();
  JButton jButton3 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JButton jButton7 = new JButton();
  JButton jButton8 = new JButton();
  JButton jButton9 = new JButton();

  JLabel jLabel1class = new JLabel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JComboBox jComboBox1 = new JComboBox(classoption);
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JTextField jTextField9 = new JTextField();
  JTextField jTextField10 = new JTextField();
  JLabel jLabel9_2 = new JLabel();
  JLabel jLabel12 = new JLabel();

  SEGREG_Dialog2_TypeMean dialog2 = new SEGREG_Dialog2_TypeMean(this, "Specification");
  SEGREG_Dialog3_TypeVar dialog3 = new SEGREG_Dialog3_TypeVar("Specification");
  SEGREG_Dialog4_TypeSuscept dialog4 = new SEGREG_Dialog4_TypeSuscept(this, "Specification");
  SEGREG_Dialog5_MeanCov dialog5 = new SEGREG_Dialog5_MeanCov("Specification");
  SEGREG_Dialog6_VarCov dialog6 = new SEGREG_Dialog6_VarCov("Specification");
  SEGREG_Dialog7_SusCov dialog7 = new SEGREG_Dialog7_SusCov("Specification");
  SEGREG_Dialog8_Fpmm dialog8 = new SEGREG_Dialog8_Fpmm("Specification");
  SEGREG_Dialog10_TransF dialog10 = new SEGREG_Dialog10_TransF("Specification");
  SEGREG_Dialog11_Geno dialog11 = new SEGREG_Dialog11_Geno("Specification");
  SEGREG_Dialog12_TransM dialog12 = new SEGREG_Dialog12_TransM("Specification");
  SEGREG_Dialog13_Ascer dialog13 = new SEGREG_Dialog13_Ascer("Specification");
  SEGREG_Dialog14_PrevC dialog14 = new SEGREG_Dialog14_PrevC("Specification");
  SEGREG_Dialog15_PrevE dialog15 = new SEGREG_Dialog15_PrevE("Specification");

  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField12 = new JTextField();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JTextField jTextField13 = new JTextField();
  JTextField jTextField14 = new JTextField();
  JButton jButton6 = new JButton();
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
  JButton jButton23 = new JButton();
  JButton jButton24 = new JButton();
  JButton jButton26 = new JButton();
  JButton jButton27 = new JButton();
  JButton jButton28 = new JButton();

  public SEGREG5() {
    setModel(new PropertyDataModel());
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
    jButton7.setText("Define");
    jButton7.setEnabled(false);
    jButton7.setMargin(new Insets(2, 2, 2, 2));
    jButton8.setText("Define");
    jButton8.setEnabled(false);
    jButton8.setMargin(new Insets(2, 2, 2, 2));
    jButton9.setText("Define");
    jButton9.setEnabled(false);
    jButton9.setMargin(new Insets(2, 2, 2, 2));
    jLabel1class.setForeground(Color.red);
    jLabel1class.setToolTipText("<html>Specifies the model class. <br>If you don't want any polygenic loci, define the FPMM to have zero loci.");
    jLabel1class.setText("Model class");
    jLabel1.setToolTipText("Specifies the number of component distributions : 1, 2 or 3.");
    jLabel1.setText("Age of onset means");//Type mean
    jLabel3.setToolTipText(
            "Specifies covariates for the mean.");
    jLabel3.setText("Age of onset mean covariates");
    jLabel5.setToolTipText("Specifies the number of variances.");

    jLabel2.setToolTipText("Specifies the number of type susceptibilities, or penetrances : 1, 2 or 3.");
    jLabel2.setText("Type susceptibilities");
    jLabel4.setToolTipText("Specifies covariates for the trait susceptibility, or penetrance.");
    jLabel4.setText("Susceptibility covariates");

    jLabel5.setText("Age of onset variances");//Type variance
    jLabel6.setToolTipText(
            "Specifies covariates for the variance.");
    jLabel6.setText("Variance covariates");
    jLabel7.setToolTipText("Details for the FPMM model.");
    jLabel7.setText("FPMM");
    jLabel7.setForeground(Color.red);

    jLabel8.setToolTipText(
            "Specifies transformation options.");
    jLabel8.setText("Transformation");
    jLabel9.setToolTipText(
            "Specifies the founder genotype frequency model.");
    jLabel9.setText("Genotype frequency model");

    jLabel10.setToolTipText(
            "Specifies the transmission model.");
    jLabel10.setText("Transmission");
    jLabel12.setToolTipText(
            "Specifies the pedigree ascertainment options.");
    jLabel12.setText("Ascertainment");

    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);
    jTextField1.setText("");
    jTextField1.addMouseListener(new SEGREG5_jTextField1_mouseAdapter(this));
    jTextField3.setEnabled(false);
    jTextField3.setEditable(false);
    jTextField3.setText("");
    jTextField3.addMouseListener(new SEGREG5_jTextField3_mouseAdapter(this));
    jTextField4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setText("");
    jTextField4.addMouseListener(new SEGREG5_jTextField4_mouseAdapter(this));
    jTextField5.setEnabled(false);
    jTextField5.setEditable(false);
    jTextField5.setText("");
    jTextField5.addMouseListener(new SEGREG5_jTextField5_mouseAdapter(this));
    jTextField6.addMouseListener(new SEGREG5_jTextField6_mouseAdapter(this));
    jTextField7.setEnabled(false);
    jTextField7.setEditable(false);
    jTextField7.setText("");
    jTextField7.addMouseListener(new SEGREG5_jTextField7_mouseAdapter(this));
    jTextField8.setEnabled(false);
    jTextField8.setEditable(false);
    jTextField8.setText("");
    jTextField8.addMouseListener(new SEGREG5_jTextField8_mouseAdapter(this));
    jTextField9.setEnabled(false);
    jTextField9.setEditable(false);
    jTextField9.setText("");
    jTextField9.addMouseListener(new SEGREG5_jTextField9_mouseAdapter(this));
    jTextField10.setEnabled(false);
    jTextField10.setEditable(false);
    jTextField10.setText("");
    jTextField10.addMouseListener(new SEGREG5_jTextField10_mouseAdapter(this));
    jTextField2.setEnabled(false);
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.addMouseListener(new SEGREG5_jTextField2_mouseAdapter(this));
    jComboBox1.addMouseListener(new SEGREG5_jComboBox1_mouseAdapter(this));
    jTextField6.setEnabled(false);
    jTextField6.setEditable(false);
    jTextField6.setText("");
    jTextField12.setEnabled(false);
    jTextField12.setEditable(false);
    jTextField12.setText("");
    jTextField12.addMouseListener(new SEGREG5_jTextField12_mouseAdapter(this));
    jLabel13.setToolTipText("Specifies constraints on the population prevalence of a binary trait.");
    jLabel13.setText("Prevalence constraint");
    jLabel14.setToolTipText("Specifies the covariate values at which prevalence is to be estimated.");
    jLabel14.setText("Prevalence estimate");
    jButton6.setEnabled(false);
    jButton6.setMargin(new Insets(2, 2, 2, 2));
    jButton6.setText("Define");
    jButton12.setEnabled(false);
    jButton12.setMargin(new Insets(2, 2, 2, 2));
    jButton12.setText("Define");
    jButton13.setEnabled(false);
    jButton13.setContentAreaFilled(true);
    jButton13.setMargin(new Insets(2, 2, 2, 2));
    jButton13.setText("Define");
    jButton14.setEnabled(false);
    jButton14.setMargin(new Insets(2, 2, 2, 2));
    jButton14.setText("Define");
    jTextField13.setEnabled(false);
    jTextField13.setEditable(false);
    jTextField13.setText("");
    jTextField13.addMouseListener(new SEGREG5_jTextField13_mouseAdapter(this));
    jTextField14.setEnabled(false);
    jTextField14.setEditable(false);
    jTextField14.setText("");
    jTextField14.addMouseListener(new SEGREG5_jTextField14_mouseAdapter(this));
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
    jButton23.setEnabled(false);
    jButton23.setMargin(new Insets(2, 2, 2, 2));
    jButton23.setText("Reset");
    jButton24.setEnabled(false);
    jButton24.setMargin(new Insets(2, 2, 2, 2));
    jButton24.setText("Reset");
    jButton26.setEnabled(false);
    jButton26.setMargin(new Insets(2, 2, 2, 2));
    jButton26.setText("Reset");
    jButton27.setEnabled(false);
    jButton27.setMargin(new Insets(2, 2, 2, 2));
    jButton27.setText("Reset");
    jButton28.setEnabled(false);
    jButton28.setMargin(new Insets(2, 2, 2, 2));
    jButton28.setText("Reset");

    String message = "<html><font size=\"3\" face=\"Dialog\">For a binary trait with variable age of onset, <b>SEGREG</b> will assume a polygenic background by default."
                     +"This requires you to specify the Finite Polygenic Mixed Model within the <font color=\"red\">FPMM</font> option dialog."+
                     "If you DO NOT wish to model a polygenic component, then simply specify the number of loci to be <i>zero(0)</i> in the <font color=\"red\">FPMM</font> option."+
                     "<br>You must still specify the age-of-onset type dependencies as usual.</font></html>";

    JEditorPane me = new JEditorPane();
    me.setEditable(false);
    me.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
    me.setContentType("text/html");
    me.setText(message);
    me.setFont(new java.awt.Font("Dialog", 0, 5));
    me.setMargin(new Insets(0, 0, 0, 0));


    this.add(me, new XYConstraints(20, 10, 460, 80));

    // model class
    this.add(jLabel1class, new XYConstraints(20, 100, 100, 20));
    this.add(jComboBox1,   new XYConstraints(175, 100, 300, 20));

    // type mean
    this.add(jLabel1, new XYConstraints(20, 130, 140, 20));
    this.add(jTextField1,     new XYConstraints(175, 130, 199, 20));
    this.add(jButton1,   new XYConstraints(380, 130, 45, 20));
    this.add(jButton15,    new XYConstraints(430, 130, 45, 20));

    // type susceptibility
    this.add(jLabel2,  new XYConstraints(20, 160, 140, 20));
    this.add(jButton2,      new XYConstraints(380, 160, 45, 20));
    this.add(jTextField2,          new XYConstraints(175, 160, 199, 20));
    this.add(jButton16,          new XYConstraints(430, 160, 45, 20));

    // age on onset covariates

    this.add(jLabel3,   new XYConstraints(20, 190, 160, 20));
    this.add(jTextField3,          new XYConstraints(175, 190, 199, 20));
    this.add(jButton3,      new XYConstraints(380, 190, 45, 20));
    this.add(jButton17,         new XYConstraints(430, 190, 45, 20));

    // susceptibility covariates
    this.add(jLabel4,       new XYConstraints(20, 220, 140, 20));
    this.add(jTextField4,             new XYConstraints(175, 220, 199, 20));
    this.add(jButton18,          new XYConstraints(430, 220, 45, 20));
    this.add(jButton4,      new XYConstraints(380, 220, 45, 20));

    // type variance
    this.add(jLabel5,  new XYConstraints(20, 250, 140, 20));
    this.add(jTextField5,          new XYConstraints(175, 250, 199, 20));
    this.add(jButton5,      new XYConstraints(380, 250, 45, 20));
    this.add(jButton19,          new XYConstraints(430, 250, 45, 20));

    // variance covariates
    this.add(jLabel6,  new XYConstraints(20, 280, 140, 20));
    this.add(jButton6,      new XYConstraints(380, 280, 45, 20));
    this.add(jTextField6,          new XYConstraints(175, 280, 199, 20));
    this.add(jButton20,       new XYConstraints(430, 280, 45, 20));

    //fpmm
    this.add(jLabel7,  new XYConstraints(20, 310, 140, 20));
    this.add(jButton7,      new XYConstraints(380, 310, 45, 20));
    this.add(jTextField7,          new XYConstraints(175, 310, 199, 20));
    this.add(jButton21,       new XYConstraints(430, 310, 45, 20));

    // transformation
    this.add(jLabel8, new XYConstraints(20, 340, 140, 20));
    this.add(jButton8,      new XYConstraints(380, 340, 45, 20));
    this.add(jTextField8,          new XYConstraints(175, 340, 199, 20));
    this.add(jButton22,       new XYConstraints(430, 340, 45, 20));

    // genotype frequency model
    this.add(jLabel9, new XYConstraints(20, 370, 140, 20));
    this.add(jTextField9,          new XYConstraints(175, 370, 199, 20));
    this.add(jButton9,      new XYConstraints(380, 370, 45, 20));
    this.add(jButton23,       new XYConstraints(430, 370, 45, 20));

    // transmission
    this.add(jLabel10, new XYConstraints(20, 400, 140, 20));
    this.add(jTextField10,          new XYConstraints(175, 400, 199, 20));
    this.add(jButton10,      new XYConstraints(380, 400, 45, 20));
    this.add(jButton24,       new XYConstraints(430, 400, 45, 20));

    this.add(jLabel12, new XYConstraints(20, 430, 140, 20));
    this.add(jTextField12,          new XYConstraints(175, 430, 199, 20));
    this.add(jButton12,      new XYConstraints(380, 430, 45, 20));
    this.add(jButton26,       new XYConstraints(430, 430, 45, 20));

    this.add(jLabel13,       new XYConstraints(20, 460, 120, 20));
    this.add(jTextField13,           new XYConstraints(175, 460, 199, 20));
    this.add(jButton13,          new XYConstraints(380, 460, 45, 20));
    this.add(jButton27,       new XYConstraints(430, 460, 45, 20));

    this.add(jLabel14,      new XYConstraints(20, 490, 120, 20));
    this.add(jTextField14,           new XYConstraints(175, 490, 199, 20));
    this.add(jButton14,          new XYConstraints(380, 490, 45, 20));
    this.add(jButton28,        new XYConstraints(430, 490, 45, 20));

    this.add(jRunButton,    new XYConstraints(420, 520, 60, 25));

    jComboBox1.addActionListener(this);

    jRunButton.addActionListener(this);
    jButton1.addActionListener(this);
    jButton3.addActionListener(this);
    jButton2.addActionListener(this);
    jButton4.addActionListener(this);
    jButton5.addActionListener(this);
    jButton6.addActionListener(this);
    jButton7.addActionListener(this);
    jButton8.addActionListener(this);
    jButton9.addActionListener(this);
    jButton10.addActionListener(this);
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
    jButton23.addActionListener(this);
    jButton24.addActionListener(this);
    jButton26.addActionListener(this);
    jButton27.addActionListener(this);
    jButton28.addActionListener(this);

    Datamodel.setValue("class", "FPMM");

    dialog2.set_dataModel(Datamodel);
    dialog4.set_dataModel(Datamodel);
    dialog5.set_dataModel(Datamodel);
    dialog7.set_dataModel(Datamodel);
    dialog3.set_dataModel(Datamodel);
    dialog6.set_dataModel(Datamodel);
    dialog8.set_dataModel(Datamodel);
    dialog10.set_dataModel(Datamodel);
    dialog11.set_dataModel(Datamodel);
    dialog12.set_dataModel(Datamodel);
    dialog13.set_dataModel(Datamodel);
    dialog14.set_dataModel(Datamodel);
    dialog15.set_dataModel(Datamodel);

    jRunButton.setIcon(error_image);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    mf.jTabbedPane1.setSelectedIndex(1);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if(ob == jComboBox1)
    {
        String message = "Even if you don't have polygenic component, you must go through FPMM and set the loci to 0.";
    }

    if (ob == jRunButton)
    {
      jRunButton_actionPerformed(e);
    }

    if (ob == jButton1) {
        dialog2.showDialog();
    }
    if (ob == jButton15) { // Reset Type mean
      if(Datamodel.hasValue("type_mean"))
      {
        jTextField1.setText("");
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("type_mean");
        dialog2.listModel.removeAllElements();
      }
    }

    if (ob == jButton2) {
        dialog4.showDialog();
    }

    if (ob == jButton16) { // Reset Type susceptibility
      if(Datamodel.hasValue("type_suscept"))
      {
        jTextField2.setText("");
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("type_suscept");
        dialog4.listModel.removeAllElements();
      }
    }

    if (ob == jButton3) {
      dialog5.resulttextfield = jTextField3;
      dialog5.resultlabel = jLabel3;
      dialog5.showDialog();
    }

    if (ob == jButton17) { // Reset Mean cov
      if(Datamodel.hasValue("mean_cov"))
      {
        jTextField3.setText("");
        jLabel3.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("mean_cov");
        dialog5.listModel.removeAllElements();
      }
    }

    if (ob == jButton4) {
      dialog7.resulttextfield = jTextField4;
      dialog7.resultlabel = jLabel4;
      dialog7.showDialog();
    }

    if (ob == jButton18) { // Reset Susceptibility cov
      if(Datamodel.hasValue("suscept_cov"))
      {
        jTextField4.setText("");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("suscept_cov");
        dialog4.listModel.removeAllElements();
      }
    }

    if (ob == jButton5) {//Type var
      dialog3.resulttextfield = jTextField5;
      dialog3.resultlabel = jLabel5;
      dialog3.showDialog();
    }

    if (ob == jButton19) { // Reset Type var
      if(Datamodel.hasValue("type_var"))
      {
        jTextField5.setText("");
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("type_var");
        dialog3.listModel.removeAllElements();
      }
    }

    if (ob == jButton6) {
      dialog6.resulttextfield = jTextField6;
      dialog6.resultlabel = jLabel6;
        dialog6.showDialog();
    }

    if (ob == jButton20) { // Reset Var cov
      if(Datamodel.hasValue("var_cov"))
      {
        jTextField6.setText("");
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("var_cov");
        dialog6.listModel.removeAllElements();
      }
    }

    if (ob == jButton7) {
      dialog8.resulttextfield = jTextField7;
      dialog8.resultlabel = jLabel7;
      dialog8.jCheckBox1.setSelected(true);
      dialog8.jCheckBox1.setForeground(Color.red);
      dialog8.showDialog();
      jRunButton.setIcon(next_image);
    }

    if (ob == jButton21) { // Reset FPMM
      if(Datamodel.hasValue("fpmm"))
      {
        jTextField7.setText("");
        dialog8.jCheckBox1.setSelected(false);
        dialog8.jCheckBox1.setForeground(Color.black);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("fpmm");
        jRunButton.setIcon(error_image);
      }
    }

    if (ob == jButton8) {
      dialog10.resulttextfield = jTextField8;
      dialog10.resultlabel = jLabel8;
      dialog10.showDialog();
    }

    if (ob == jButton22) { // Reset Transformation
      if(Datamodel.hasValue("transformation"))
      {
        jTextField8.setText("");
        jLabel8.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("transformation");
      }
    }

    if (ob == jButton9) {
      dialog11.resulttextfield = jTextField9;
      dialog11.resultlabel1 = jLabel9;
      dialog11.resultlabel2 = jLabel9_2;
      dialog11.showDialog();
    }

    if (ob == jButton23) { // Reset Genotype frequency model
      if(Datamodel.hasValue("geno_freq"))
      {
        jTextField9.setText("");
        jLabel9.setFont(new java.awt.Font("Dialog", 0, 11));
        jLabel9_2.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("geno_freq");
      }
    }

    if (ob == jButton10) {
      dialog12.resulttextfield = jTextField10;
      dialog12.resultlabel = jLabel10;
        dialog12.showDialog();
    }

    if (ob == jButton24) { // Reset Transmission
      if(Datamodel.hasValue("transmission"))
      {
        jTextField10.setText("");
        jLabel10.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("transmission");
      }
    }

    if (ob == jButton12) {
      dialog13.resulttextfield = jTextField12;
      dialog13.resultlabel = jLabel12;
        dialog13.showDialog();
    }

    if (ob == jButton26) { // Reset Ascertainment
      if(Datamodel.hasValue("ascertainment"))
      {
        jTextField12.setText("");
        jLabel12.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("ascertainment");
      }
    }


    if (ob == jButton13) {
      dialog14.resulttextfield = jTextField13;
      dialog14.resultlabel = jLabel13;
        dialog14.showDialog();
    }

    if (ob == jButton27) { // Reset Prevalence constraint
      if(Datamodel.hasValue("prev_constraint"))
      {
        jTextField13.setText("");
        jLabel13.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("prev_constraint");
        dialog14.listModel.removeAllElements();
      }
    }

    if (ob == jButton14) {
      dialog15.resulttextfield = jTextField14;
      dialog15.resultlabel = jLabel14;
        dialog15.showDialog();
    }

    if (ob == jButton28) { // Reset Prevalence estimate
      if(Datamodel.hasValue("prev_estimate"))
      {
        jTextField14.setText("");
        jLabel14.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("prev_estimate");
        dialog15.listModel.removeAllElements();
      }
    }
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("class", false, 144);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("type_mea", false, 143);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
     Frame1.mainFrame1.pdfframe.setTextonPage("type_suscept", false, 143);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("mean_cov", false, 144);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("suscept_cov", false, 144);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("type_va", false, 143);
  }

  void jTextField6_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("var_cov", false, 144);
  }

  void jTextField7_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("fpmm", false, 144);
  }

  void jTextField8_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("transformation", false, 144);
  }

  void jTextField9_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("geno_freq", false, 145);
  }

  void jTextField10_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("transmission", false, 145);
  }

  void jTextField11_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("composite_trait", false, 143);
  }

  void jTextField12_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("ascertainment", false, 145);
  }

  void jTextField13_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("prev_constraints", false, 145);
  }

  void jTextField14_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("prev_estimate", false, 145);
  }
}

class SEGREG5_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jComboBox1_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG5_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField1_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG5_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField2_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SEGREG5_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField3_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG5_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField4_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class SEGREG5_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField5_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class SEGREG5_jTextField6_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField6_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField6_mouseClicked(e);
  }
}

class SEGREG5_jTextField7_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField7_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField7_mouseClicked(e);
  }
}

class SEGREG5_jTextField8_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField8_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField8_mouseClicked(e);
  }
}

class SEGREG5_jTextField9_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField9_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField9_mouseClicked(e);
  }
}

class SEGREG5_jTextField10_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField10_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField10_mouseClicked(e);
  }
}

class SEGREG5_jTextField11_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField11_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField11_mouseClicked(e);
  }
}

class SEGREG5_jTextField12_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField12_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField12_mouseClicked(e);
  }
}

class SEGREG5_jTextField13_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField13_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField13_mouseClicked(e);
  }
}

class SEGREG5_jTextField14_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG5 adaptee;

  SEGREG5_jTextField14_mouseAdapter(SEGREG5 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField14_mouseClicked(e);
  }
}


