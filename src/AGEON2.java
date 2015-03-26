package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
/**
 * <p>Title: NEW GUI for SAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author suna
 * @version 1.0
 */

public class AGEON2
    extends SageAnalysisPanel
    implements DocumentListener, ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  String[] aa = {"True", "False"};
  String[] aa_real = {"true", "false"};

  JTextField title = new JTextField();

  DataCollectionModel Datamodel;
  AGEON1 ageon1;

  JLabel jLabel2 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JComboBox jComboBoxAA = new JComboBox(aa);
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JButton jButton2 = new JButton();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JButton jButton4 = new JButton();
  JComboBox jComboBox1 = new JComboBox();
  JComboBox jComboBox2 = new JComboBox();
  JComboBox jComboBox3 = new JComboBox();

  AGEON_Dialog1_MeanCov dialog1 = new AGEON_Dialog1_MeanCov(this, "Specification");
  AGEON_Dialog2_VarCov dialog2 = new AGEON_Dialog2_VarCov(this, "Specification");
  AGEON_Dialog3_SusCov dialog3 = new AGEON_Dialog3_SusCov(this, "Specification");
  AGEON_Dialog4_TransF dialog4 = new AGEON_Dialog4_TransF(this, "Specification");
  AGEON_Dialog5_Pool dialog5 = new AGEON_Dialog5_Pool(this, "Specification");

  JLabel jLabel9 = new JLabel();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  XYLayout xYLayout2 = new XYLayout();
  JLabel jLabel10 = new JLabel();
  JComboBox jComboBox4 = new JComboBox();
  JButton jButton3 = new JButton();
  JTextField jTextField3 = new JTextField();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JButton jButton7 = new JButton();
  JButton jButton8 = new JButton();
  JLabel jLabel11 = new JLabel();
  JTextField jTextField5 = new JTextField();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JButton jButton9 = new JButton();
  JButton jButton10 = new JButton();
  public AGEON2(AGEON1 input) {
    this.Datamodel = input.Datamodel;
    ageon1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    jLabel1.setToolTipText("Specifies the title of the run.");
    jLabel1.setText("Title");
    this.setLayout(xYLayout1);
    title.setToolTipText("");
    title.setText("AGEON Analysis");
    title.addMouseListener(new AGEON2_title_mouseAdapter(this));

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jRunButton.addActionListener(this);

    jLabel2.setToolTipText("<html>Starts a sub-block for specifying covariates for the mean age " +
    "<br>of onset.");
    jLabel2.setText("Mean covariates");
    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);
    jTextField1.setText("");
    jTextField1.addMouseListener(new AGEON2_jTextField1_mouseAdapter(this));
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("Define");
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jButton4.addActionListener(this);

    jButton5.addActionListener(this);
    jButton6.addActionListener(this);
    jButton7.addActionListener(this);
    jButton8.addActionListener(this);

    jButton9.addActionListener(this);
    jButton10.addActionListener(this);

    jLabel3.setForeground(Color.red);
    jLabel3.setToolTipText(
        "Specifies name of a variable containing affection status.");
    jLabel3.setText("Trait");
    jLabel13.setToolTipText(
        "<html>Specifies option to substitute covariates\' mean values for missing " +
        "<br>covariate data.");
    jLabel13.setText("Allow averaging");
    jLabel4.setForeground(Color.red);
    jLabel4.setToolTipText(
        "Specifies name of a trait containing age of onset information.");
    jLabel4.setText("Age of onset");
    jLabel5.setForeground(Color.red);
    jLabel5.setToolTipText(
        "Specifies name of a trait containing age of examination information.");
    jLabel5.setText("Age of examination");
    jLabel6.setToolTipText(
        "<html>Starts a sub-block for specifying covariates for the variance of " +
        "<br>age of onset.");
    jLabel6.setText("Variance covariates");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Define");
    jLabel7.setToolTipText(
        "<html>Starts a sub-block for specifying covariates for the susceptibility " +
        "<br>on the logit scale.");
    jLabel7.setText("Susceptibility");
    jLabel8.setToolTipText(
        "Starts a sub-block for specifying transformation options.");
    jLabel8.setText("Transformation");
    jTextField2.setEnabled(false);
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.addMouseListener(new AGEON2_jTextField2_mouseAdapter(this));
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton4.setText("Define");
    jTextField4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setText("");
    jTextField4.addMouseListener(new AGEON2_jTextField4_mouseAdapter(this));
    jLabel9.setToolTipText(
        "Covariate to modify the mean of a quantitative trait.");
    jLabel9.setText("Covariate");
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(xYLayout2);
    jLabel10.setToolTipText(
        "<html>Specifies the name of an alternate trait on which to base the individual\'s " +
        "<br>classification code.");
    jLabel10.setText("Trait");
    jComboBox1.addMouseListener(new AGEON2_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new AGEON2_jComboBox2_mouseAdapter(this));
    jComboBox3.addMouseListener(new AGEON2_jComboBox3_mouseAdapter(this));
    jComboBox4.addMouseListener(new AGEON2_jComboBox4_mouseAdapter(this));
    jComboBoxAA.addMouseListener(new AGEON2_jComboBoxAA_mouseAdapter(this));
    jButton3.addActionListener(this);
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Define");
    jTextField3.setEnabled(false);
    jTextField3.setEditable(false);
    jTextField3.setText("");
    jTextField3.addMouseListener(new AGEON2_jTextField3_mouseAdapter(this));
    jButton5.setMargin(new Insets(2, 2, 2, 2));
    jButton5.setText("Reset");
    jButton6.setMargin(new Insets(2, 2, 2, 2));
    jButton6.setText("Reset");
    jButton7.setMargin(new Insets(2, 2, 2, 2));
    jButton7.setText("Reset");
    jButton8.setMargin(new Insets(2, 2, 2, 2));
    jButton8.setText("Reset");
    jLabel11.setText("Number of ");
    jLabel11.setToolTipText(
        "<html>Specifies the number of classes to be considered during the analysis.");
    jLabel12.setToolTipText(
      "<html>Specifies the number of classes to be considered during the analysis.");
    jLabel12.setText("classes");
    jTextField5.setText("6");
    jTextField5.addMouseListener(new AGEON2_jTextField5_mouseAdapter(this));


    jLabel14.setToolTipText("Instructs AGEON how to pool the sample.");
    jLabel14.setText("Pool");
    jButton9.setMargin(new Insets(2, 2, 2, 2));
    jButton9.setText("Define");
    jButton10.setMargin(new Insets(2, 2, 2, 2));
    jButton10.setText("Reset");
    jTextField6.setEnabled(false);
    jTextField6.setEditable(false);
    jTextField6.setText("");
    jTextField6.addMouseListener(new AGEON2_jTextField6_mouseAdapter(this));
    this.add(jLabel1, new XYConstraints(20, 20, 118, 20));
    this.add(title,           new XYConstraints(155, 20, 320, 20));
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(jLabel3, new XYConstraints(20, 50, 118, 20));
    this.add(jLabel13,    new XYConstraints(20, 365, 118, 20));
    this.add(jLabel4, new XYConstraints(20, 80, 118, 20));
    this.add(jLabel5, new XYConstraints(20, 110, 118, 20));
    this.add(jButton1,         new XYConstraints(380, 140, 45, 20));
    this.add(jLabel6, new XYConstraints(20, 170, 118, 20));
    this.add(jLabel7,   new XYConstraints(20, 260, 89, 20));
    this.add(jTextField2,                 new XYConstraints(155, 170, 219, 20));
    this.add(jTextField1,                     new XYConstraints(155, 140, 219, 20));
    this.add(jButton2,        new XYConstraints(380, 170, 45, 20));
    this.add(jLabel8, new XYConstraints(20, 200, 118, 20));
    this.add(jTextField4,             new XYConstraints(155, 200, 219, 20));
    this.add(jButton4,      new XYConstraints(380, 200, 45, 20));
    this.add(jLabel2, new XYConstraints(19, 140, 118, 20));
    this.add(jComboBoxAA,                new XYConstraints(155, 365, 320, 20));
    this.add(jComboBox1,           new XYConstraints(155, 50, 320, 20));
    this.add(jComboBox2,           new XYConstraints(155, 80, 320, 20));
    this.add(jComboBox3,           new XYConstraints(155, 110, 320, 20));
    this.add(jPanel1,                         new XYConstraints(155, 258, 328, 98));
    jPanel1.add(jLabel10,   new XYConstraints(2, 32, 54, 20));
    jPanel1.add(jComboBox4,             new XYConstraints(70, 32, 244, 20));
    jPanel1.add(jButton3,               new XYConstraints(219, 2, 45, 20));
    jPanel1.add(jTextField3,                       new XYConstraints(70, 2, 143, 20));
    jPanel1.add(jLabel9,    new XYConstraints(2, 2, 55, 20));
    jPanel1.add(jButton8,                 new XYConstraints(269, 2, 45, 20));
    jPanel1.add(jLabel11,    new XYConstraints(2, 55, 93, 20));
    jPanel1.add(jTextField5,          new XYConstraints(70, 62, 244, 20));
    jPanel1.add(jLabel12,  new XYConstraints(2, 69, 51, -1));
    this.add(jButton5,               new XYConstraints(430, 140, 45, 20));
    this.add(jButton6,        new XYConstraints(430, 170, 45, 20));
    this.add(jButton7,      new XYConstraints(430, 200, 45, 20));
    this.add(jLabel14,      new XYConstraints(20, 230, 80, 20));
    this.add(jTextField6,           new XYConstraints(155, 230, 219, 20));
    this.add(jButton9,        new XYConstraints(380, 229, 45, 20));
    this.add(jButton10,        new XYConstraints(430, 229, 45, 20));

    title.getDocument().addDocumentListener(this);
    jTextField1.getDocument().addDocumentListener(this);
    jTextField5.getDocument().addDocumentListener(this);

    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);
    jComboBox3.addItemListener(this);
    jComboBox4.addItemListener(this);
    jComboBoxAA.addItemListener(this);

    Datamodel.setValue("Title", title.getText());
    Datamodel.setValue("sus_cov_class_number", "6");
    Datamodel.setValue("AA", "false");

    dialog1.set_dataModel(Datamodel);
    dialog2.set_dataModel(Datamodel);
    dialog3.set_dataModel(Datamodel);
    dialog4.set_dataModel(Datamodel);
    dialog5.set_dataModel(Datamodel);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jButton1) {
        dialog1.showDialog();
    }

    if (ob == jButton2) {
        dialog2.showDialog();
    }

    if (ob == jButton3) {
        dialog3.showDialog();
    }

    if (ob == jButton4) {
        dialog4.showDialog();
    }

    if (ob == jButton5) {
      if(Datamodel.hasValue("mean_cov_info"))
      {
        jTextField1.setText("");
        jLabel2.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("mean_cov_info");
        dialog1.listModel.removeAllElements();
      }
    }

    if (ob == jButton6) {
      if(Datamodel.hasValue("var_cov_info"))
      {
        jTextField2.setText("");
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("var_cov_info");
        dialog2.listModel.removeAllElements();
      }

    }
    if (ob == jButton7) {
      if(Datamodel.hasValue("transformation_info"))
      {
        jTextField4.setText("");
        jLabel8.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("transformation_info");
      }

    }
    if (ob == jButton8) {
      if(Datamodel.hasValue("suscept_cov_info"))
      {
        jTextField3.setText("");
        jLabel9.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("suscept_cov_info");
        dialog3.listModel.removeAllElements();
      }
    }

    if (ob == jButton9) {
        dialog5.showDialog();
    }

    if (ob == jButton10) {
      if(Datamodel.hasValue("pool_info"))
      {
        Datamodel.removeValue("pool_info");
        dialog5.clear_init_state();
      }
      jTextField6.setText("");
      jLabel14.setFont(new java.awt.Font("Dialog", 0, 11));
    }

    else if (ob == jRunButton)
      jRunButton_actionPerformed(e);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) ageon1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();

    mf.RunAGEON(treenode, currentnode, e.getActionCommand());
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == title.getDocument()) {
      Datamodel.setValue("Title", title.getText());
    }

    if (document == jTextField5.getDocument()) {
      if (jTextField5.getText().length() <= 0) {
        Datamodel.setValue("sus_cov_class_number", "6");
      }
      else {
        Datamodel.setValue("sus_cov_class_number", jTextField5.getText());
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
      if (jComboBox1.getSelectedIndex() <= 0)
        Datamodel.removeValue("affectedness");
      else
        Datamodel.setValue("affectedness",
                           jComboBox1.getSelectedItem().toString());

    }
    if (ob == jComboBox2) {
      if (jComboBox2.getSelectedIndex() <= 0)
        Datamodel.removeValue("age_of_onset");
      else
        Datamodel.setValue("age_of_onset",
                           jComboBox2.getSelectedItem().toString());

    }
    if (ob == jComboBox3) {
      if (jComboBox3.getSelectedIndex() <= 0)
        Datamodel.removeValue("age_of_exam");
      else
        Datamodel.setValue("age_of_exam", jComboBox3.getSelectedItem().toString());
    }

    if (ob == jComboBox4) {
      if (jComboBox4.getSelectedIndex() <= 0)
        Datamodel.removeValue("sus_cov_class_trait");
      else
        Datamodel.setValue("sus_cov_class_trait",
                           jComboBox4.getSelectedItem().toString());
    }

    if (ob == jComboBoxAA) {
      Datamodel.setValue("AA", aa_real[jComboBoxAA.getSelectedIndex()]);
    }

    if (Datamodel.hasValue("affectedness") &&
        Datamodel.hasValue("age_of_onset") && Datamodel.hasValue("age_of_exam")) {
        jRunButton.setIcon(next_image);
    }
  }

  void title_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("title", false, 377);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("affectednes", false, 377);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("age_of_onset", false, 377);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("age_of_exam", false, 377);
  }

  void jComboBoxAA_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("allow_averaging", false, 377);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("mean_cov", false, 378);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("var_cov", false, 378);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("suscept_cov", false, 378);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("transformation", false, 378);
  }

  void jTextField6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pool ", false, 378);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 384);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("num_of_classes", false, 384);
  }
}

class AGEON2_title_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_title_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.title_mouseClicked(e);
  }
}

class AGEON2_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jComboBox1_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class AGEON2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jComboBox2_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class AGEON2_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jComboBox3_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class AGEON2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jTextField1_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class AGEON2_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jTextField2_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class AGEON2_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jTextField4_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class AGEON2_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jTextField3_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class AGEON2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jComboBox4_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class AGEON2_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jTextField5_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class AGEON2_jComboBoxAA_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jComboBoxAA_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxAA_mouseClicked(e);
  }
}

class AGEON2_jTextField6_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON2 adaptee;

  AGEON2_jTextField6_mouseAdapter(AGEON2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField6_mouseClicked(e);
  }
}




