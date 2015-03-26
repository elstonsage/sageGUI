package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.*;
//import javax.help.CSH;
/**
 * <p>Title: NEW GUI for SAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author suna
 * @version 1.0
 */

public class SIBPAL3
    extends SageAnalysisPanel
    implements ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();

  DataCollectionModel Datamodel;
  TitledBorder titledBorder1;

  SIBPAL1 sibpal1;
  JLabel jLabel3 = new JLabel();

  ButtonGroup bp = new ButtonGroup();
  ButtonGroup bp2 = new ButtonGroup();
  ButtonGroup bp3 = new ButtonGroup();

  JLabel jLabel4 = new JLabel();
  MyComboBox jTraitComboBox = new MyComboBox();
  MyComboBox jSubsetComboBox = new MyComboBox();
  MyComboBox jMarkerComboBox = new MyComboBox();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel9 = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  JCheckBox jCheckBox2 = new JCheckBox();
  JCheckBox jCheckBox1 = new JCheckBox();
  JCheckBox jCheckBox3 = new JCheckBox();
  JLabel jLabel2 = new JLabel();

  public SIBPAL3(SIBPAL1 input) {
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
    titledBorder1 = new TitledBorder("");
    this.setLayout(xYLayout1);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    jCheckBox2.setFocusPainted(false);
    jCheckBox1.setFocusPainted(false);
    jCheckBox3.setFocusPainted(false);



    jRunButton.setIcon(back_image);
    jRunButton.setText("Next");

    jLabel1.setToolTipText(
            "Specifies the name of marker(s) or location(s) for which to test mean IBD sharing.");
    jLabel1.setText("Marker");

    jLabel3.setToolTipText(
            "<html>Names a binary trait denoting affection status. Analysis is performed" +
            "<br>separately on concordantly affected, unaffected and discordant pairs.");
    jLabel3.setText("Binary trait");
    jLabel4.setToolTipText(
        "Specifies a trait used as an indicator variable to select subsets of pairs to analyze.");
    jLabel4.setText("Subset");

    jTextField1.addMouseListener(new SIBPAL3_jTextField1_mouseAdapter(this));

    jTextField1.setText("0.5");
    jLabel9.setToolTipText("<html>Specifies a value for <b>w<sub>1</sub></b>. See manual.");
    jLabel9.setText("W1");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout2);
    jCheckBox2.setToolTipText(
            "<html>Specifies option to produce tab-delimited output which can easily" +
            "<br>be imported to other programs such as Excel, SAS and Splus.");
    jCheckBox2.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox2.setText("Produce tab delimited output");
    jCheckBox2.addMouseListener(new SIBPAL3_jCheckBox2_mouseAdapter(this));
    jCheckBox2.addActionListener(this);
    jCheckBox1.setToolTipText(
            "<html>Prints more verbose output information. This causes some" +
            "<br>output tables to be more than 80 columns wide.");
    jCheckBox1.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox1.setText("Produce more detailed output");
    jCheckBox1.addMouseListener(new SIBPAL3_jCheckBox1_mouseAdapter(this));
    jCheckBox1.addActionListener(this);
    jCheckBox3.setToolTipText(
            "<html>Specifies option to print P-values using scientific notation" +
            "<br>as opposed to the default of fixed decimal notation.");
    jCheckBox3.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox3.setText("Output P-value in scientific notation");
    jCheckBox3.addMouseListener(new SIBPAL3_jCheckBox3_mouseAdapter(this));
    jCheckBox3.addActionListener(this);
    jLabel2.setText("Output options");
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(jTextField1,         new XYConstraints(155, 110, 320, 20));
    this.add(jLabel9,  new XYConstraints(21, 110, 80, 20));
    this.add(jTraitComboBox,     new XYConstraints(155, 20, 320, 20));
    this.add(jSubsetComboBox,       new XYConstraints(155, 50, 320, 20));
    this.add(jMarkerComboBox,       new XYConstraints(155, 80, 320, 20));
    this.add(jLabel3, new XYConstraints(20, 20, 58, 20));
    this.add(jLabel1, new XYConstraints(20, 80, 59, 20));
    this.add(jLabel4, new XYConstraints(20, 50, 71, 20));
    this.add(jPanel1,       new XYConstraints(155, 140, 320, 98));
    jPanel1.add(jCheckBox2,  new XYConstraints(2, 31, 170, 20));
    jPanel1.add(jCheckBox1,  new XYConstraints(2, 2, 233, 20));
    jPanel1.add(jCheckBox3,  new XYConstraints(2, 60, 200, 20));
    this.add(jLabel2,   new XYConstraints(20, 140, 115, 20));
    jTraitComboBox.setWidth(320);
    jSubsetComboBox.setWidth(320);
    jMarkerComboBox.setWidth(320);

    jRunButton.addActionListener(this);



    Datamodel.setValue("wide_out", "false");
    Datamodel.setValue("export_output", "false");
    Datamodel.setValue("pval_scientific_notation", "false");
    Datamodel.setValue("w1", "0.5");
  }

  public void jRunButton_actionPerformed() {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    mf.jTabbedPane1.setSelectedIndex(1);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if (ob == jCheckBox1)
    {
      if(jCheckBox1.isSelected())
        Datamodel.setValue("wide_out", "true");
      else
        Datamodel.setValue("wide_out", "false");
    }
    if (ob == jCheckBox2)
    {
      if(jCheckBox2.isSelected())
        Datamodel.setValue("export_output", "true");
      else
        Datamodel.setValue("export_output", "false");
    }
    if (ob == jCheckBox3)
    {
      if(jCheckBox3.isSelected())
        Datamodel.setValue("pval_scientific_notation", "true");
      else
        Datamodel.setValue("pval_scientific_notation", "false");
    }

    if (ob == jRunButton)
      jRunButton_actionPerformed();

  }

  void jMarkerComboBox_mouseClicked(MouseEvent e) {
// Frame1.viewer.setCurrentID("MT_MARKER");
    Frame1.mainFrame1.pdfframe.setTextonPage("marker", false, 252);
  }

  void jTraitComboBox_mouseClicked(MouseEvent e) {
// Frame1.viewer.setCurrentID("MT_TRAIT_10");
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 252);
  }

  void jSubsetComboBox_mouseClicked(MouseEvent e) {
// Frame1.viewer.setCurrentID("MT_SUBSET_10");
    Frame1.mainFrame1.pdfframe.setTextonPage("subset", false, 252);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
// Frame1.viewer.setCurrentID("s1");
    Frame1.mainFrame1.pdfframe.setTextonPage("w1", false, 253);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("wide_out", false, 252);
  }

  void jCheckBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("export_outpu", false, 252);
  }

  void jCheckBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pval_scientific_notatio", false, 253);
  }

}

class SIBPAL3_jTraitComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL3 adaptee;

  SIBPAL3_jTraitComboBox_mouseAdapter(SIBPAL3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTraitComboBox_mouseClicked(e);
  }
}

class SIBPAL3_jSubsetComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL3 adaptee;

  SIBPAL3_jSubsetComboBox_mouseAdapter(SIBPAL3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jSubsetComboBox_mouseClicked(e);
  }
}

class SIBPAL3_jMarkerComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL3 adaptee;

  SIBPAL3_jMarkerComboBox_mouseAdapter(SIBPAL3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jMarkerComboBox_mouseClicked(e);
  }
}

class SIBPAL3_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL3 adaptee;

  SIBPAL3_jTextField1_mouseAdapter(SIBPAL3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SIBPAL3_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL3 adaptee;

  SIBPAL3_jCheckBox1_mouseAdapter(SIBPAL3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class SIBPAL3_jCheckBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL3 adaptee;

  SIBPAL3_jCheckBox2_mouseAdapter(SIBPAL3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox2_mouseClicked(e);
  }
}

class SIBPAL3_jCheckBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL3 adaptee;

  SIBPAL3_jCheckBox3_mouseAdapter(SIBPAL3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox3_mouseClicked(e);
  }
}
