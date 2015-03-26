package sage;

import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class MARKERINFO2
    extends SageAnalysisPanel
    implements java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  ButtonGroup bg = new ButtonGroup();

  DataCollectionModel Datamodel;
  String skipmale;
  MARKERINFO1 markerinfo1;
  JComboBox jComboBox1 = new JComboBox();
  JCheckBox jCheckBox1 = new JCheckBox();
  JLabel jLabel2 = new JLabel();

  JCheckBox genPedCheckBox = new JCheckBox();

  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();

  public MARKERINFO2(MARKERINFO1 input) {
    this.Datamodel = input.Datamodel;
    markerinfo1 = input;
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
    jCheckBox1.setToolTipText("<html>Specifies that output should include nuclear family members" +
    "<br>who are Mendelian consistent.");
    jCheckBox1.setText("Include consistent family members in output");
    jCheckBox1.addMouseListener(new MARKERINFO2_jCheckBox1_mouseAdapter(this));
    jLabel1.setToolTipText(
        "<html>Specifies an extra ID field to be printed in the analysis output file.");
    jLabel1.setText("Sample ID");
    this.setLayout(xYLayout1);
    Datamodel.setValue("skip_mle", "false");
    genPedCheckBox.setText("Produce new data file");
    genPedCheckBox.setToolTipText("<html>Specifies option to generate a new pedigree file along with"+
    "<br>a corresponding new parameter file.");

    jRunButton.addActionListener(this);

    jComboBox1.addMouseListener(new MARKERINFO2_jComboBox1_mouseAdapter(this));
    jComboBox1.setEnabled(false);
    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    jPanel3.setBorder(new TitledBorder(""));
    jPanel3.setLayout(xYLayout4);

    jLabel2.setText("Other option");

    genPedCheckBox.setFocusPainted(false);
    jCheckBox1.setFocusPainted(false);

    jPanel3.add(jCheckBox1, new XYConstraints(2, 2, 300, 20));
    jPanel3.add(genPedCheckBox,    new XYConstraints(2, 32, 300, 20));

    this.add(jLabel1, new XYConstraints(20, 20, 85, 20));
    this.add(jComboBox1,  new XYConstraints(155, 20, 320, 20));
    this.add(jLabel2,     new XYConstraints(20, 50, 107, 20));
    this.add(jPanel3,             new XYConstraints(155, 50, 320, 69));
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));

    jComboBox1.addItemListener(this);
    genPedCheckBox.addItemListener(this);

    jCheckBox1.addItemListener(this);

    Datamodel.setValue("consistent_out", "false");
    Datamodel.setValue("sample_id", "not use");
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton)
      jRunButton_actionPerformed(e);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) markerinfo1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunMARKERINFO(treenode, currentnode, e.getActionCommand());
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if (ob == jComboBox1) {
      Datamodel.setValue("sample_id", jComboBox1.getSelectedItem().toString());
    }
    else if (ob == jCheckBox1) {
      if (jCheckBox1.isSelected())
        Datamodel.setValue("consistent_out", "true");
      else
        Datamodel.setValue("consistent_out", "false");
    }
    else if (ob == genPedCheckBox) {
      if (genPedCheckBox.isSelected())
        Datamodel.setValue("pedigree_out", "true");
      else
      {
          if(Datamodel.hasValue("pedigree_out"))
              Datamodel.removeValue("pedigree_out");
      }
    }
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("samle_id", false, 191);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("consistent_ou", false, 191);
  }
}

class MARKERINFO2_jComboBox1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  MARKERINFO2 adaptee;

  MARKERINFO2_jComboBox1_mouseAdapter(MARKERINFO2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class MARKERINFO2_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  MARKERINFO2 adaptee;

  MARKERINFO2_jCheckBox1_mouseAdapter(MARKERINFO2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

