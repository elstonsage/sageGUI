package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class LODPAL2
    extends SageAnalysisPanel
    implements ActionListener, java.awt.event.ItemListener {

  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();

  JTextField jTextField1 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField2 = new JTextField();

  DataCollectionModel Datamodel;
  JButton jTraitButton = new JButton();
  JButton jCovButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JButton jPairButton = new JButton();
  JButton jAutoButton = new JButton();
  JButton jXlinButton = new JButton();
  TitledBorder titledBorder4;
  TitledBorder titledBorder1;

  Lodpal_Dialog1_Trait trait_dialog = new Lodpal_Dialog1_Trait(this, "Trait Specification");
  Lodpal_Dialog2_Cov cov_dialog = new Lodpal_Dialog2_Cov(this, "Covariate Specification");
  Lodpal_Dialog3_Pair pair_dialog = new Lodpal_Dialog3_Pair(this,"Pair Information File Specification");
  Lodpal_Dialog4_Auto auto_dialog = new Lodpal_Dialog4_Auto(this,  "Autosomal Model Specification");
  Lodpal_Dialog5_XLin xlin_dialog = new Lodpal_Dialog5_XLin(this, "X-Linkage Model Specification");

  LODPAL1 lodpal1;
  JLabel jLabel10 = new JLabel();

  JComboBox jComboBox6 = new JComboBox();
  MyComboBox jSubsetComboBox = new MyComboBox();
  MyComboBox jMarkerComboBox = new MyComboBox();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  JCheckBox jComboBox2 = new JCheckBox();
  JCheckBox jComboBox4 = new JCheckBox();
  JCheckBox jComboBox3 = new JCheckBox();
  JCheckBox jComboBox5 = new JCheckBox();
  JLabel jLabel11 = new JLabel();

  public LODPAL2(LODPAL1 input) {
    this.Datamodel = input.Datamodel;
    lodpal1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    titledBorder4 = new TitledBorder("");
    titledBorder1 = new TitledBorder("");
    jLabel1.setRequestFocusEnabled(true);
    jLabel1.setToolTipText(
        "Specifies a binary trait to be used in the current analysis.");
    jLabel1.setText("Trait");
    this.setLayout(xYLayout1);

    jComboBox2.setFocusPainted(false);
    jComboBox4.setFocusPainted(false);
    jComboBox3.setFocusPainted(false);
    jComboBox5.setFocusPainted(false);

    jComboBox6.addItemListener(this);

    trait_dialog.set_dataModel(Datamodel);
    cov_dialog.set_dataModel(Datamodel);
    pair_dialog.set_dataModel(Datamodel);
    auto_dialog.set_dataModel(Datamodel);
    xlin_dialog.set_dataModel(Datamodel);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);
    jTextField1.setText("");
    jTextField1.addMouseListener(new LODPAL2_jTextField1_mouseAdapter(this));
    jLabel6.setToolTipText(
            "Specifies a trait used as an indicator variable to select subsets  of pairs to analyze.");
    jLabel6.setText("Subset");
    jLabel7.setToolTipText(
        "Specifies the name of marker(s) or location(s) for which to test mean IBD sharing.");
    jLabel7.setText("Marker");
    jLabel8.setToolTipText(
        "Specifies a covariate for the one-parameter model only.");
    jLabel8.setText("Covariate");
    jTextField2.setEnabled(false);
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.addMouseListener(new LODPAL2_jTextField2_mouseAdapter(this));
    jTraitButton.setFont(new java.awt.Font("MS Sans Serif", 0, 11));
    jTraitButton.setMargin(new Insets(2, 2, 2, 2));
    jTraitButton.setText("Define");
    jCovButton.setMargin(new Insets(2, 2, 2, 2));
    jCovButton.setText("Define");
    jLabel2.setToolTipText(
        "<html>Specifies option to print diagnostic information to a separate file.");
    jLabel2.setText("Marker/Location for");

    jTraitButton.addActionListener(this);
    jCovButton.addActionListener(this);
    jPairButton.addActionListener(this);
    jAutoButton.addActionListener(this);
    jXlinButton.addActionListener(this);
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jButton3.addActionListener(this);
    jButton4.addActionListener(this);
    jButton5.addActionListener(this);

    jLabel4.setToolTipText(
        "<html>Specifies the file name that contains the pair-specific covariate(s) " +
        "<br>and/or weight values to be used in the current analysis.");
    jLabel4.setText("Pair info file");
    jLabel5.setToolTipText(
            "Specifies an autosomal model.");
    jLabel5.setText("Autosomal model");
    jLabel9.setToolTipText(
            "Specifies options for an X-linked model.");
    jLabel9.setText("X-linkage model");
    jTextField3.setEnabled(false);
    jTextField3.setEditable(false);
    jTextField3.setText("");
    jTextField3.addMouseListener(new LODPAL2_jTextField3_mouseAdapter(this));
    jTextField4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setText("");
    jTextField4.addMouseListener(new LODPAL2_jTextField4_mouseAdapter(this));
    jTextField5.setEnabled(false);
    jTextField5.setEditable(false);
    jTextField5.setText("");
    jTextField5.addMouseListener(new LODPAL2_jTextField5_mouseAdapter(this));
    jPairButton.setMargin(new Insets(2, 2, 2, 2));
    jPairButton.setText("Define");
    jAutoButton.setMargin(new Insets(2, 2, 2, 2));
    jAutoButton.setText("Define");
    jXlinButton.setMargin(new Insets(2, 2, 2, 2));
    jXlinButton.setText("Define");

    if (lodpal1.errorF_node != null) {
      jRunButton.setIcon(error_image);
    }
    else {
      jRunButton.setIcon(next_image);
    }

    jRunButton.addActionListener(this);

    jLabel10.setToolTipText(
        "<html>Specifies option to print diagnostic information to a separate file.");
    jLabel10.setText("diagnostic output");

    jSubsetComboBox.addMouseListener(new LODPAL2_jSubsetComboBox_mouseAdapter(this));
    jMarkerComboBox.addMouseListener(new LODPAL2_jMarkerComboBox_mouseAdapter(this));
    jComboBox6.addMouseListener(new LODPAL2_jComboBox6_mouseAdapter(this));
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("Reset");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Reset");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Reset");
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton4.setText("Reset");
    jButton5.setMargin(new Insets(2, 2, 2, 2));
    jButton5.setText("Reset");

    jPanel1.setBorder(titledBorder4);
    jPanel1.setLayout(xYLayout2);
    jComboBox2.setToolTipText("<html>Specifies option to print P-values using scientific notation " +
    "as opposed<br>to the default of fixed decimal notation.");
    jComboBox2.setMargin(new Insets(2, 0, 2, 2));
    jComboBox2.setText("Output P-value in scientific notation");
    jComboBox2.addMouseListener(new LODPAL2_jComboBox2_mouseAdapter(this));
    jComboBox4.addItemListener(this);
    jComboBox4.setToolTipText("<html>Specifiies option to use only full sib pair in analysis.");
    jComboBox4.setMargin(new Insets(2, 0, 2, 2));
    jComboBox4.setText("Analyze only full sibs");
    jComboBox4.addMouseListener(new LODPAL2_jComboBox4_mouseAdapter(this));
    jComboBox3.addItemListener(this);
    jComboBox3.setToolTipText("<html>Specifies option to print more verbose output information. " +
    "This causes <br>some output tables to be more than 80 columns wide.");
    jComboBox3.setMargin(new Insets(2, 0, 2, 2));
    jComboBox3.setText("Produce tabular output");
    jComboBox3.addMouseListener(new LODPAL2_jComboBox3_mouseAdapter(this));
    jComboBox5.addItemListener(this);
    jComboBox5.setToolTipText("<html>Specifies option to disable the default maximization process.");
    jComboBox5.setMargin(new Insets(2, 0, 2, 2));
    jComboBox5.setText("Report possible non-maxima");
    jComboBox5.addMouseListener(new LODPAL2_jComboBox5_mouseAdapter(this));
    jLabel11.setText("Other options");
    this.add(jLabel1, new XYConstraints(20, 20, 80, 20));
    this.add(jTextField1,        new XYConstraints(155, 20, 219, 20));
    this.add(jTraitButton,   new XYConstraints(380, 20, 45, 20));
    this.add(jButton1,    new XYConstraints(430, 20, 45, 20));

    this.add(jLabel8, new XYConstraints(20, 50, 80, 20));
    this.add(jTextField2,        new XYConstraints(155, 50, 219, 20));
    this.add(jCovButton,   new XYConstraints(380, 50, 45, 20));
    this.add(jButton2,       new XYConstraints(430, 50, 45, 20));

    this.add(jLabel6, new XYConstraints(20, 80, 80, 20));
    this.add(jSubsetComboBox,       new XYConstraints(155, 80, 320, 20));

    this.add(jLabel7, new XYConstraints(20, 110, 57, 20));
    this.add(jMarkerComboBox,       new XYConstraints(155, 110, 320, 20));

    this.add(jLabel2,  new XYConstraints(20, 133, 150, 20));
    this.add(jLabel10,  new XYConstraints(20, 147, 150, 20));
    this.add(jComboBox6,    new XYConstraints(155, 140, 320, 20));

    this.add(jLabel4,    new XYConstraints(20, 170, 80, 20));
    this.add(jTextField3,          new XYConstraints(155, 170, 219, 20));
    this.add(jPairButton,      new XYConstraints(380, 170, 45, 20));
    this.add(jButton3,        new XYConstraints(430, 170, 45, 20));

    this.add(jLabel5,    new XYConstraints(20, 200, 100, 20));
    this.add(jTextField4,          new XYConstraints(155, 200, 219, 20));
    this.add(jAutoButton,     new XYConstraints(380, 200, 45, 20));
    this.add(jButton4,        new XYConstraints(430, 200, 45, 20));

    this.add(jLabel9,    new XYConstraints(20, 230, 100, 20));
    this.add(jTextField5,          new XYConstraints(155, 230, 219, 20));
    this.add(jXlinButton,     new XYConstraints(380, 230, 45, 20));
    this.add(jButton5,        new XYConstraints(430, 230, 45, 20));

    this.add(jLabel11,      new XYConstraints(20, 260, 100, 20));
    this.add(jPanel1,    new XYConstraints(155, 260, 320, 130));
    jPanel1.add(jComboBox2,   new XYConstraints(2, 62, 250, 20));
    jPanel1.add(jComboBox4,   new XYConstraints(2, 2, 250, 20));
    jPanel1.add(jComboBox3,    new XYConstraints(2, 32, 250, 20));
    jPanel1.add(jComboBox5,   new XYConstraints(2, 92, 250, 20));

    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));

    jMarkerComboBox.setWidth(320);
    jSubsetComboBox.setWidth(320);

    Datamodel.setValue("pval_scientific_notation", "false");
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) lodpal1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunLODPAL(treenode, currentnode, e.getActionCommand());
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if (ob == jComboBox2) {
      if(jComboBox2.isSelected())
        Datamodel.setValue("pval_scientific_notation", "true");
      else
        Datamodel.setValue("pval_scientific_notation", "false");
    }
    if (ob == jComboBox4) {
      if (jComboBox4.isSelected()) {
        Datamodel.setValue("sib_pairs_only", "use");
      }
      else {
        Datamodel.removeValue("sib_pairs_only");
      }
    }
    if (ob == jComboBox3) {
      if (jComboBox3.isSelected()) {
        Datamodel.setValue("wide_out", "use");
      }
      else {
        Datamodel.removeValue("wide_out");
      }
    }
    if (ob == jComboBox5) {
      if (jComboBox5.isSelected()) {
        Datamodel.setValue("turn_off_default", "use");
      }
      else {
        Datamodel.removeValue("turn_off_default");
      }
    }
    if (ob == jComboBox6) {
      if (jComboBox6.getSelectedIndex() > 0) {
        Datamodel.setValue("diagnostic", jComboBox6.getSelectedItem().toString());
      }
      else {
        Datamodel.removeValue("diagnostic");
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if (ob == jTraitButton) {
        trait_dialog.showDialog();
    }
    else if (ob == jCovButton) {
        cov_dialog.showDialog();
    }
    else if (ob == jPairButton) {
        pair_dialog.showDialog();
    }
    else if (ob == jAutoButton) {
        auto_dialog.showDialog();
    }
    else if (ob == jXlinButton) {
        xlin_dialog.showDialog();
    }
    else if (ob == jButton1) {
      if(Datamodel.hasValue("Trait"))
      {
        jTextField1.setText("");
        jLabel1.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("Trait");
      }
    }
    else if (ob == jButton2) {
      if(Datamodel.hasValue("Cov"))
      {
        jTextField2.setText("");
        jLabel8.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("Cov");
        cov_dialog.listModel.removeAllElements();
      }
    }
    else if (ob == jButton3) {
      if(Datamodel.hasValue("Pair"))
      {
        jTextField3.setText("");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("Pair");
        pair_dialog.listModel.removeAllElements();
      }
    }
    else if (ob == jButton4) {
      if(Datamodel.hasValue("Auto"))
      {
        jTextField4.setText("");
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("Auto");
      }
    }
    else if (ob == jButton5) {
      if(Datamodel.hasValue("XLin"))
      {
        jTextField5.setText("");
        jLabel9.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("XLin");
      }
    }
    else if (ob == jRunButton) {
      jRunButton_actionPerformed(e);
    }
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait ", false, 274);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 275);
  }

  void jSubsetComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("subse", false, 275);
  }

  void jMarkerComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marker", false, 275);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("diagnostic", false, 276);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("turn_off_default", false, 276);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sib_pairs_only", false, 277);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("wide_ou", false, 277);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("weight", false, 277);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pval_scientific_notatio", false, 277);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pair_info_fil", false, 277);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("autosomal_model", false, 277);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("x_linkage_model", false, 278);
  }

}

class LODPAL2_jTextField1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jTextField1_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class LODPAL2_jTextField2_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jTextField2_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class LODPAL2_jSubsetComboBox_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jSubsetComboBox_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jSubsetComboBox_mouseClicked(e);
  }
}

class LODPAL2_jMarkerComboBox_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jMarkerComboBox_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jMarkerComboBox_mouseClicked(e);
  }
}

class LODPAL2_jComboBox1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jComboBox1_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class LODPAL2_jComboBox6_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jComboBox6_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class LODPAL2_jTextField3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jTextField3_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class LODPAL2_jTextField4_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jTextField4_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class LODPAL2_jTextField5_mouseAdapter
    extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jTextField5_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class LODPAL2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jComboBox4_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class LODPAL2_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jComboBox3_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class LODPAL2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jComboBox2_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class LODPAL2_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  LODPAL2 adaptee;

  LODPAL2_jComboBox5_mouseAdapter(LODPAL2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}
