package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class FCOR2
    extends SageAnalysisPanel
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  ButtonGroup bg = new ButtonGroup();
  String[] interclass = {
      "Pairwise", "Uniform"};
  String[] realinterclass = {
      "pair_wise", "uniform"};
  String[] type = {
      "Subtypes", "Main types", "Both"};
  String[] realtype = {
      "subtypes", "maintypes", "both"};

  String[] homotest = {
        "none","jointly for all traits","each trait separately"};

  Fcor_Dialog2_Cov  cdialog = new Fcor_Dialog2_Cov(this,"Variance-Covariance Specification");

  DataCollectionModel Datamodel;
  FCOR1 fcor1;
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  TitledBorder titledBorder1;

  JComboBox jComboBox1 = new JComboBox(interclass);
  JComboBox jComboBox2 = new JComboBox(interclass);
  JLabel jLabel5 = new JLabel();
  JCheckBox jComboBox5 = new JCheckBox();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JComboBox jComboBox6 = new JComboBox(homotest);
  JLabel jLabel9 = new JLabel();
  JButton jButton1 = new JButton();

  static boolean setInfo = false;
  JTextField jTextField2 = new JTextField();
  MyComboBox jTraitComboBox = new MyComboBox();
  JButton jButton2 = new JButton();
  JLabel jLabel4 = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  JCheckBox jComboBox8 = new JCheckBox();
  JCheckBox jComboBox7 = new JCheckBox();
  JCheckBox jComboBox3 = new JCheckBox();
  JLabel jLabel7 = new JLabel();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout3 = new XYLayout();
  JCheckBox jComboBox9 = new JCheckBox();
  JComboBox jComboBox4 = new JComboBox(type);

  public FCOR2(FCOR1 input) {
    this.Datamodel = input.Datamodel;
    fcor1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {


      jComboBox5.setFocusPainted(false);
      jComboBox8.setFocusPainted(false);
      jComboBox7.setFocusPainted(false);
      jComboBox3.setFocusPainted(false);
      jComboBox9.setFocusPainted(false);

    titledBorder1 = new TitledBorder("");
    jLabel1.setToolTipText(
        "Specifies the weight to be used for interclass correlations.");
    jLabel1.setText("Interclass weight");
    this.setLayout(xYLayout1);

    jComboBox6.addMouseListener(new FCOR2_jComboBox6_mouseAdapter(this));

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    jRunButton.addActionListener(this);

    jLabel2.setToolTipText(
        "<html>Specifies the weight to be used for intraclass correlations.");
    jLabel2.setText("Intraclass weight");
    jLabel3.setRequestFocusEnabled(true);
    jLabel3.setToolTipText(
        "<html>Specifies calculation of correlations for subtypes only, main " +
        "<br>relative types only, or for both main relative types and subtypes.");
    jLabel3.setText("Type");
    jLabel5.setToolTipText(
        "<html>Specifies the calculation of chi-square statistics and associated P-values" +
        "<br>for homogeneity among subtypes..");
    jLabel5.setText("Homogeneity test");
    jLabel8.setToolTipText(
        "Specifies the type of variance-covariance matrix to be printed.");
    jLabel8.setText("Variance-covariance");
    jTextField1.setText("2");
    jTextField1.addMouseListener(new FCOR2_jTextField1_mouseAdapter(this));
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("Define");
    jButton1.addActionListener(this);
    jLabel9.setToolTipText(
        "<html>Specifies trait(s) to be used in the analysis.");
    jLabel9.setText("Trait");
    jLabel6.setToolTipText(
        "<html>For any correlation, the largest number of steps between the pair of individuals" +
        "<br>and their closest common ancestor.");
    jLabel6.setText("Generation limit");
    jTextField2.setEnabled(false);
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.addMouseListener(new FCOR2_jTextField2_mouseAdapter(this));
    jTraitComboBox.addMouseListener(new FCOR2_jTraitComboBox_mouseAdapter(this));
    jComboBox1.addMouseListener(new FCOR2_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new FCOR2_jComboBox2_mouseAdapter(this));
    jComboBox5.addMouseListener(new FCOR2_jComboBox5_mouseAdapter(this));
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Reset");
    jButton2.addActionListener(this);
    jComboBox5.setToolTipText("Option to print out sex-specific names.");
    jComboBox5.setMargin(new Insets(2, 0, 2, 2));
    jComboBox5.setSelected(true);
    jComboBox5.setText("Output sex-dependent relationship names");
    jLabel4.setToolTipText("<html>Option to calculate asymptotic standard errors.");
    jLabel4.setText("Standard error");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout2);
    jComboBox8.setToolTipText(
            "<html>Specifies option to produce additional output file indicating,"+
            "<br>for each standard error, the smallest number of pairs used"+
            "<br>to calculate any of the required constituent correlations.");
    jComboBox8.setMargin(new Insets(2, 0, 2, 2));
    jComboBox8.setText("Output smallest number of pairs used");
    jComboBox8.addMouseListener(new FCOR2_jComboBox8_mouseAdapter(this));
    jComboBox8.addItemListener(this);
    jComboBox7.setToolTipText("Excludes non-estimable standard errors.");
    jComboBox7.setMargin(new Insets(2, 0, 2, 2));
    jComboBox7.setText("Produce conservative standard error estimates");
    jComboBox7.addMouseListener(new FCOR2_jComboBox7_mouseAdapter(this));
    jComboBox7.addItemListener(this);
    jComboBox3.addMouseListener(new FCOR2_jComboBox3_mouseAdapter(this));
    jComboBox3.setToolTipText("<html>Option to calculate asymptotic standard errors.");
    jComboBox3.setMargin(new Insets(2, 0, 2, 2));
    jComboBox3.setSelected(true);
    jComboBox3.setText("Estimate standard errors");
    jComboBox3.addItemListener(this);
    jLabel7.setText("Sex name");
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout3);
    jComboBox9.setToolTipText("Specifies option to produce an additional output file in comma delimited format.");
    jComboBox9.setMargin(new Insets(2, 0, 2, 2));
    jComboBox9.setText("Produce comma delimited output");
    jComboBox9.addMouseListener(new FCOR2_jComboBox9_mouseAdapter(this));
    jComboBox9.addItemListener(this);
    jComboBox4.setActionCommand("comboBoxChanged");
    jComboBox4.addMouseListener(new FCOR2_jComboBox4_mouseAdapter(this));
    jComboBox4.addItemListener(this);
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(jComboBox1,     new XYConstraints(155, 50, 320, 20));
    this.add(jLabel9,   new XYConstraints(20, 20, 75, 20));
    this.add(jComboBox2,     new XYConstraints(155, 80, 320, 20));
    this.add(jLabel8,        new XYConstraints(20, 140, 120, 20));
    this.add(jTextField2,              new XYConstraints(155, 140, 219, 20));
    this.add(jButton1,      new XYConstraints(380, 140, 45, 20));
    this.add(jLabel1,   new XYConstraints(20, 50, 95, 20));
    this.add(jLabel2,   new XYConstraints(20, 80, 95, 20));
    this.add(jLabel5,         new XYConstraints(20, 110, 95, 20));
    this.add(jComboBox6,               new XYConstraints(155, 110, 320, 20));
    this.add(jTraitComboBox,    new XYConstraints(155, 20, 320, 20));
    this.add(jButton2,      new XYConstraints(430, 140, 45, 20));
    this.add(jLabel4,           new XYConstraints(20, 170, 120, 20));
    this.add(jLabel3,    new XYConstraints(20, 275, 95, 20));
    this.add(jPanel1,            new XYConstraints(155, 170, 320, 95));
    jPanel1.add(jComboBox8,         new XYConstraints(20, 60, 250, 20));
    jPanel1.add(jComboBox7,                new XYConstraints(20, 31, 252, 20));
    jPanel1.add(jComboBox3,    new XYConstraints(2, 2, 160, 20));
    this.add(jComboBox5,    new XYConstraints(155, 381, 250, 20));
    this.add(jLabel6,   new XYConstraints(20, 351, 95, 20));
    this.add(jTextField1,       new XYConstraints(155, 351, 320, 20));
    this.add(jLabel7,       new XYConstraints(20, 381, 95, 20));
    this.add(jPanel2,     new XYConstraints(155, 275, 320, 66));
    jPanel2.add(jComboBox9,   new XYConstraints(20, 31, 200, 20));
    jPanel2.add(jComboBox4,        new XYConstraints(2, 2, 304, 20));

    jTraitComboBox.setWidth(320);

    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);
    jComboBox5.addItemListener(this);
    jComboBox6.addItemListener(this);

    jTextField1.getDocument().addDocumentListener(this);
    Datamodel.setValue("generation_limit", jTextField1.getText());
    Datamodel.setValue("interclass_weight", "pair_wise");
    Datamodel.setValue("intraclass_weight", "pair_wise");
    Datamodel.setValue("standard_error", "true");
    Datamodel.setValue("type", "subtypes");
    Datamodel.setValue("sex_name", "true");
    Datamodel.setValue("homogeneity_test", Integer.toString(jComboBox6.getSelectedIndex()));

    cdialog.set_dataModel(Datamodel);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton) {
      MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
      IconNode treenode = (IconNode) fcor1.analysis_node;
      NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
      mf.RunFCOR(treenode, currentnode, e.getActionCommand());
    }
    if (ob == jButton1) {
      CheckableItem[] model = jTraitComboBox.ListData;
      cdialog.set_ListModel(model);
      cdialog.showDialog();
    }
    if(ob == jButton2)
    {
      if (Datamodel.hasValue("cov"))
        Datamodel.removeValue("cov");
      jTextField2.setText("");
      jLabel8.setFont(new java.awt.Font("Dialog", 0, 11));
      cdialog.listModel.removeAllElements();
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      Datamodel.setValue("generation_limit", jTextField1.getText());
      if (jTextField1.getText().length() <= 0)
        Datamodel.removeValue("generation_limit");
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
      int index = jComboBox1.getSelectedIndex();
      Datamodel.setValue("interclass_weight",
                         realinterclass[index]);
    }
    if (ob == jComboBox2) {
      int index = jComboBox2.getSelectedIndex();
      Datamodel.setValue("intraclass_weight",
                         realinterclass[index]);
    }
    if (ob == jComboBox3) {
      if(jComboBox3.isSelected())
      {
        Datamodel.setValue("standard_error","true");
        jComboBox7.setEnabled(true);
        jComboBox8.setEnabled(true);
      }
      else
      {
        Datamodel.setValue("standard_error","false");
        jComboBox7.setEnabled(false);
        jComboBox8.setEnabled(false);
      }
    }
    if (ob == jComboBox4) {
      Datamodel.setValue("type", realtype[jComboBox4.getSelectedIndex()]);
    }
    if (ob == jComboBox5) {
      if(jComboBox5.isSelected())
        Datamodel.setValue("sex_name", "true");
      else
        Datamodel.setValue("sex_name", "false");
    }
    if (ob == jComboBox6) {
      Datamodel.setValue("homogeneity_test", Integer.toString(jComboBox6.getSelectedIndex()));
    }

    if (ob == jComboBox7) {
      if (jComboBox7.isSelected()) {
        Datamodel.setValue("conservative", "use");
      }
      else {
        Datamodel.removeValue("conservative");
      }
    }

    if (ob == jComboBox8) {
      if (jComboBox8.isSelected()) {
        Datamodel.setValue("pairs", "use");
      }
      else {
        Datamodel.removeValue("pairs");
      }
    }
    if (ob == jComboBox9) {
      if (jComboBox9.isSelected()) {
        Datamodel.setValue("tabular", "use");
      }
      else {
        Datamodel.removeValue("tabular");
      }
    }

  }

  void jTraitComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trai", false, 108);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("interclass_weigh", false, 108);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("intraclass_weigh", false, 108);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("type", false, 108);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("standard_erro", false, 108);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sex_nam", false, 109);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("generation_limit", false, 109);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("homogeneity_test", false, 109);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("var_co", false, 109);
  }

  void jComboBox7_mouseClicked(MouseEvent e) {
Frame1.mainFrame1.pdfframe.setTextonPage("conservative", false, 109);
  }

  void jComboBox8_mouseClicked(MouseEvent e) {
Frame1.mainFrame1.pdfframe.setTextonPage("pair", false, 109);
  }

  void jComboBox9_mouseClicked(MouseEvent e) {
Frame1.mainFrame1.pdfframe.setTextonPage("tabular", false, 108);
  }


}
class FCOR2_jTraitComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jTraitComboBox_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTraitComboBox_mouseClicked(e);
  }
}

class FCOR2_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox1_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class FCOR2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox2_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class FCOR2_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jTextField2_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class FCOR2_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox5_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}

class FCOR2_jComboBox6_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox6_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class FCOR2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jTextField1_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class FCOR2_jComboBox7_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox7_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox7_mouseClicked(e);
  }
}

class FCOR2_jComboBox8_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox8_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox8_mouseClicked(e);
  }
}

class FCOR2_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox3_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class FCOR2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox4_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class FCOR2_jComboBox9_mouseAdapter extends java.awt.event.MouseAdapter {
  FCOR2 adaptee;

  FCOR2_jComboBox9_mouseAdapter(FCOR2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox9_mouseClicked(e);
  }
}
