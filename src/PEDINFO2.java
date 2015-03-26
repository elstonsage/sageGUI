package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.*;

public class PEDINFO2
    extends SageAnalysisPanel
    implements ActionListener, ItemListener {

  XYLayout xYLayout1 = new XYLayout();
  TitledBorder titledBorder1;
  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();

  DataCollectionModel Datamodel;
  String skipmale;
  PEDINFO1 pedinfo1;
  Vector Covariate_array;
  Vector Trait_array;
  Vector Phenotype_array;

  JLabel jLabel1 = new JLabel();
  MyComboBox jTraitComboBox = new MyComboBox();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder2;
  XYLayout xYLayout2 = new XYLayout();
  JCheckBox jCheckBox2 = new JCheckBox();
  JCheckBox jCheckBox1 = new JCheckBox();
  public PEDINFO2(PEDINFO1 input) {
    this.Datamodel = input.Datamodel;
    pedinfo1 = input;
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
    this.setLayout(xYLayout1);
    Datamodel.setValue("skip_mle", "false");

    jRunButton.addActionListener(this);

    jLabel1.setToolTipText(
        "<html>Character string representing the name of a trait listed in the pedigree " +
        "<br>data file.");
    jLabel1.setText("Trait, Covariate");
    jTraitComboBox.addMouseListener(new PEDINFO2_jTraitComboBox_mouseAdapter(this));
    jTraitComboBox.setEditable(false);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jLabel2.setText("Other options");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout2);
    jCheckBox2.setToolTipText("Outputs trait tables only.");
    jCheckBox2.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox2.setText("Output only trait-relevant statistics");
    jCheckBox2.addMouseListener(new PEDINFO2_jCheckBox2_mouseAdapter(this));
    jCheckBox2.addItemListener(this);
    jCheckBox1.setFocusPainted(false);
    jCheckBox2.setFocusPainted(false);
    jCheckBox1.setToolTipText("<html>Specifies option to calculate statistics on a pedigree-by-pedigree " +
    "<br>basis.");
    jCheckBox1.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox1.setText("Perform calculation on a per-pedigree basis");
    jCheckBox1.addMouseListener(new PEDINFO2_jCheckBox1_mouseAdapter(this));
    jCheckBox1.addItemListener(this);
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(jLabel1, new XYConstraints(20, 20, 138, 20));
    this.add(jTraitComboBox,   new XYConstraints(155, 20, 320, 20));
    this.add(jLabel2,     new XYConstraints(20, 50, 138, 20));
    this.add(jPanel1,           new XYConstraints(155, 50, 320, 67));
    jPanel1.add(jCheckBox2,     new XYConstraints(2, 31, 200, 20));
    jPanel1.add(jCheckBox1,   new XYConstraints(2, 2, 250, 20));

    jTraitComboBox.setWidth(320);

    Datamodel.setValue("each_pedigree", "false");
    Datamodel.setValue("suppress_general", "false");
  }

  public void itemStateChanged(ItemEvent e)
  {
    Object ob = e.getSource();
    if (ob == jCheckBox1)
    {
      if (jCheckBox1.isSelected())
        Datamodel.setValue("each_pedigree", "true");
      else
        Datamodel.setValue("each_pedigree", "false");
    }
    if (ob == jCheckBox2)
    {
      if (jCheckBox2.isSelected())
        Datamodel.setValue("suppress_general", "true");
      else
        Datamodel.setValue("suppress_general", "false");
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton)
      jRunButton_actionPerformed(e);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) pedinfo1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();

    mf.RunPEDINFO(treenode, currentnode, e.getActionCommand());
  }

  void jTraitComboBox_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 93);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("each_pedigree", false, 93);
  }

  void jCheckBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("suppress_genera", false, 93);
  }

}

class PEDINFO2_jTraitComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  PEDINFO2 adaptee;

  PEDINFO2_jTraitComboBox_mouseAdapter(PEDINFO2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTraitComboBox_mouseClicked(e);
  }
}

class PEDINFO2_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  PEDINFO2 adaptee;

  PEDINFO2_jCheckBox1_mouseAdapter(PEDINFO2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class PEDINFO2_jCheckBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  PEDINFO2 adaptee;

  PEDINFO2_jCheckBox2_mouseAdapter(PEDINFO2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox2_mouseClicked(e);
  }
}
