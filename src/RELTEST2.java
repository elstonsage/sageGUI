package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RELTEST2
    extends SageAnalysisPanel
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  ButtonGroup bg = new ButtonGroup();

  DataCollectionModel Datamodel;
  RELTEST1 reltest1;
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  XYLayout xYLayout2 = new XYLayout();
  JCheckBox jCheckBox5 = new JCheckBox();
  JCheckBox jCheckBox6 = new JCheckBox();
  JCheckBox jCheckBox7 = new JCheckBox();
  JCheckBox jCheckBox8 = new JCheckBox();
  JPanel jPanel2 = new JPanel();
  TitledBorder titledBorder3;
  TitledBorder titledBorder4;
  XYLayout xYLayout3 = new XYLayout();
  MyComboBox jRegionComboBox = new MyComboBox();
  JCheckBox jComboBox3 = new JCheckBox();
  JCheckBox jComboBox4 = new JCheckBox();
  JCheckBox jComboBox5 = new JCheckBox();
  JCheckBox jComboBox6 = new JCheckBox();
  JLabel jLabel8 = new JLabel();
  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JCheckBox jComboBox2 = new JCheckBox();
  JCheckBox jComboBox1 = new JCheckBox();

  public RELTEST2(RELTEST1 input) {
    this.Datamodel = input.Datamodel;
    reltest1 = input;
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
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    jLabel1.setForeground(Color.red);
    jLabel1.setToolTipText("<html>Specifies the putative pair to be analyzed.");
    jLabel1.setText("Pair types");
    this.setLayout(xYLayout1);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jRunButton.addActionListener(this);

    jLabel2.setToolTipText(
        "<html>Specifies the genomic regions that will be used in the analysis.");
    jLabel2.setText("Region");
    jLabel3.setToolTipText(
        "<html>Specifies pre-calculated cut points to be used to classify pairs.");
    jLabel3.setText("Cut points");
    jLabel4.setToolTipText("Cut point for the sib pair statistic between full sib pairs and half sib pairs.");
    jLabel4.setText("Sibling pair");
    jLabel5.setToolTipText("Cut point for the sib pair statistic between half sib pairs and unrelated pairs.");
    jLabel5.setText("Half sib pair");
    jLabel6.setToolTipText("Cut point for the sib pair statistic between MZ twin pairs and  full sib pairs.");
    jLabel6.setText("MZ twin");
    jLabel7.setToolTipText("<html>Cut point for the parent offspring pair statistic between full sib pairs<br>and parent offspring pairs.");
    jLabel7.setText("Parent offspring pair");
    jTextField2.setMargin(new Insets(1, 2, 2, 4));
    jTextField2.setSelectionStart(11);
    jTextField2.setText("[calculated]");
    jTextField2.addMouseListener(new RELTEST2_jTextField2_mouseAdapter(this));
    jTextField1.setMargin(new Insets(1, 2, 1, 1));
    jTextField1.setText("[calculated]");
    jTextField1.addMouseListener(new RELTEST2_jTextField1_mouseAdapter(this));
    jTextField3.setMargin(new Insets(1, 2, 2, 4));
    jTextField3.setText("[calculated]");
    jTextField3.addMouseListener(new RELTEST2_jTextField3_mouseAdapter(this));
    jTextField4.setMargin(new Insets(1, 2, 2, 4));
    jTextField4.setText("[calculated]");
    jTextField4.addMouseListener(new RELTEST2_jTextField4_mouseAdapter(this));
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(xYLayout2);

    jComboBox1.setFocusPainted(false);
    jComboBox2.setFocusPainted(false);
    jComboBox3.setFocusPainted(false);
    jComboBox4.setFocusPainted(false);
    jComboBox5.setFocusPainted(false);
    jComboBox6.setFocusPainted(false);
    jCheckBox5.setFocusPainted(false);
    jCheckBox6.setFocusPainted(false);
    jCheckBox7.setFocusPainted(false);
    jCheckBox8.setFocusPainted(false);

    jCheckBox5.setText("half_sib");
    jCheckBox5.setSelected(true);
    jCheckBox6.setText("marital");
    jCheckBox6.setSelected(true);
    jCheckBox7.setText("sib");
    jCheckBox7.setSelected(true);
    jCheckBox8.setText("parent_offspring");
    jCheckBox8.setSelected(true);
    jPanel2.setBorder(titledBorder4);
    jPanel2.setLayout(xYLayout3);
    jRegionComboBox.addMouseListener(new RELTEST2_jRegionComboBox_mouseAdapter(this));
    jComboBox3.setMargin(new Insets(2, 0, 2, 2));
    jComboBox3.setSelected(true);
    jComboBox3.setText("To analyze sibling");
    jComboBox3.addMouseListener(new RELTEST2_jComboBox3_mouseAdapter(this));
    jComboBox4.setMargin(new Insets(2, 0, 2, 2));
    jComboBox4.setSelected(true);
    jComboBox4.setText("To analyze half sibling");
    jComboBox4.addMouseListener(new RELTEST2_jComboBox4_mouseAdapter(this));
    jComboBox5.setMargin(new Insets(2, 0, 2, 2));
    jComboBox5.setSelected(true);
    jComboBox5.setText("To analyze parent offspring");
    jComboBox5.addMouseListener(new RELTEST2_jComboBox5_mouseAdapter(this));
    jComboBox6.setMargin(new Insets(2, 0, 2, 2));
    jComboBox6.setSelected(true);
    jComboBox6.setText("To analyze marital");
    jComboBox6.addMouseListener(new RELTEST2_jComboBox6_mouseAdapter(this));
    jLabel8.setText("Output options");
    jPanel3.setBorder(titledBorder1);
    jPanel3.setDebugGraphicsOptions(0);
    jPanel3.setLayout(xYLayout4);
    jComboBox2.setToolTipText("Specifies option to print file containing both statistics for all pairs used in the analysis.");
    jComboBox2.setMargin(new Insets(2, 0, 2, 2));
    jComboBox2.setText("Produce detailed file");
    jComboBox2.addMouseListener(new RELTEST2_jComboBox2_mouseAdapter(this));
    jComboBox2.addItemListener(this);
    jComboBox1.setToolTipText("Specifies option to print a file that contain all siblings in the nuclear family file.");
    jComboBox1.setMargin(new Insets(2, 0, 2, 2));
    jComboBox1.setText("Produce nuclear family file");
    jComboBox1.addMouseListener(new RELTEST2_jComboBox1_mouseAdapter(this));
    jComboBox1.addItemListener(this);
    this.add(jLabel1, new XYConstraints(20, 20, 75, 20));

    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(jLabel2,  new XYConstraints(20, 155, 75, 20));
    this.add(jLabel3,  new XYConstraints(20, 185, 75, 20));
    this.add(jPanel1,     new XYConstraints(155, 20, 320, 125));
    jPanel1.add(jComboBox3,         new XYConstraints(2, 2, 200, 20));
    jPanel1.add(jComboBox4,            new XYConstraints(2, 31, 200, 20));
    jPanel1.add(jComboBox5,            new XYConstraints(2, 60, 200, 20));
    jPanel1.add(jComboBox6,          new XYConstraints(2, 89, 200, 20));
    this.add(jPanel2,        new XYConstraints(155, 185, 320, 125));

    jPanel2.add(jLabel5,    new XYConstraints(2, 2, 83, 20));
    jPanel2.add(jTextField2,               new XYConstraints(175, 2, 130, 20));

    jPanel2.add(jLabel4,   new XYConstraints(2, 31, 83, 20));
    jPanel2.add(jTextField1,               new XYConstraints(175, 31, 130, 20));

    jPanel2.add(jLabel6,    new XYConstraints(2, 60, 83, 20));
    jPanel2.add(jTextField3,              new XYConstraints(175, 60, 130, 20));

    jPanel2.add(jLabel7,   new XYConstraints(2, 89, 100, 20));
    jPanel2.add(jTextField4,             new XYConstraints(175, 89, 130, 20));


    this.add(jRegionComboBox,      new XYConstraints(155, 155, 320, 20));
    this.add(jLabel8,       new XYConstraints(20, 320, 100, 20));
    this.add(jPanel3,      new XYConstraints(155, 320, 320, 66));
    jPanel3.add(jComboBox2,   new XYConstraints(2, 31, 178, 20));
    jPanel3.add(jComboBox1,  new XYConstraints(2, 2, 190, 20));
    jRegionComboBox.setWidth(320);

    jTextField1.getDocument().addDocumentListener(this);
    jTextField2.getDocument().addDocumentListener(this);
    jTextField3.getDocument().addDocumentListener(this);
    jTextField4.getDocument().addDocumentListener(this);

    jComboBox3.addItemListener(this);
    jComboBox4.addItemListener(this);
    jComboBox5.addItemListener(this);
    jComboBox6.addItemListener(this);

    Datamodel.setValue("pair_type_sib", "use");
    Datamodel.setValue("pair_type_hsib", "use");
    Datamodel.setValue("pair_type_parent_offspring", "use");
    Datamodel.setValue("pair_type_marital", "use");

    Datamodel.setValue("nuclear_family_file", "false");
    Datamodel.setValue("detailed_file", "false");
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton)
      jRunButton_actionPerformed(e);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) reltest1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunRELTEST(treenode, currentnode, e.getActionCommand());
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      Datamodel.setValue("cut_point_sib", jTextField1.getText());
      if (jTextField1.getText().length() <= 0) {
        Datamodel.removeValue("cut_point_sib");
      }
    }
    if (document == jTextField2.getDocument()) {
      Datamodel.setValue("cut_point_hsib", jTextField2.getText());
      if (jTextField2.getText().length() <= 0) {
        Datamodel.removeValue("cut_point_hsib");
      }
    }
    if (document == jTextField3.getDocument()) {
      Datamodel.setValue("cut_point_MZtwin", jTextField3.getText());
      if (jTextField3.getText().length() <= 0) {
        Datamodel.removeValue("cut_point_MZtwin");
      }
    }
    if (document == jTextField4.getDocument()) {
      Datamodel.setValue("cut_point_parent_offspring", jTextField4.getText());
      if (jTextField4.getText().length() <= 0) {
        Datamodel.removeValue("cut_point_parent_offspring");
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
        Datamodel.setValue("nuclear_family_file","true");
      else
        Datamodel.setValue("nuclear_family_file","false");
    }
    else if (ob == jComboBox2) {
      if(jComboBox2.isSelected())
        Datamodel.setValue("detailed_file", "true");
      else
        Datamodel.setValue("detailed_file", "false");
    }
    else if (ob == jComboBox3) {
      if (jComboBox3.isSelected())
        Datamodel.setValue("pair_type_sib", "use");
      else
        Datamodel.removeValue("pair_type_sib");
    }
    else if (ob == jComboBox4) {
      if (jComboBox4.isSelected())
        Datamodel.setValue("pair_type_hsib", "use");
      else
        Datamodel.removeValue("pair_type_hsib");
    }
    else if (ob == jComboBox5) {
      if (jComboBox5.isSelected())
        Datamodel.setValue("pair_type_parent_offspring", "use");
      else
        Datamodel.removeValue("pair_type_parent_offspring");
    }
    else if (ob == jComboBox6) {
      if (jComboBox6.isSelected())
        Datamodel.setValue("pair_type_marital", "use");
      else
        Datamodel.removeValue("pair_type_marital");
    }

  }

  void jRegionComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("region", false, 226);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sib", false, 226);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("hsi", false, 226);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("MZtwi", false, 226);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("parent_offsprin", false, 227);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("nucfam_file", false, 227);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("detailed_fil", false, 227);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pair_type", false, 226);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pair_type", false, 226);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pair_type", false, 226);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pair_type", false, 226);
  }



}

class RELTEST2_jRegionComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jRegionComboBox_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRegionComboBox_mouseClicked(e);
  }
}

class RELTEST2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jTextField1_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class RELTEST2_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jTextField2_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class RELTEST2_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jTextField3_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class RELTEST2_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jTextField4_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class RELTEST2_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jComboBox3_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class RELTEST2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jComboBox4_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class RELTEST2_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jComboBox5_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}

class RELTEST2_jComboBox6_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jComboBox6_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class RELTEST2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jComboBox2_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class RELTEST2_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  RELTEST2 adaptee;

  RELTEST2_jComboBox1_mouseAdapter(RELTEST2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}
