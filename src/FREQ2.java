package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.borland.jbcl.layout.*;
import java.io.Serializable;

public class FREQ2
    extends SageAnalysisPanel
    implements DocumentListener, ActionListener, ItemListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  TitledBorder titledBorder2;
  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();

  DataCollectionModel Datamodel;
  FREQ1 freq1;
  MyComboBox jMarkerComboBox = new MyComboBox();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  JCheckBox jCheckBox2 = new JCheckBox();
  JCheckBox jCheckBox1 = new JCheckBox();

  public FREQ2(FREQ1 input) {
    this.Datamodel = input.Datamodel;
    freq1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    titledBorder2 = new TitledBorder("");
    jLabel1.setToolTipText(
        "<html>The weight used for founders when combining with non-founder estimates." +
        "<br>May be set to a value between 0 and 1.");
    jLabel1.setText("Founder weight");
    this.setLayout(xYLayout1);
    jLabel3.setForeground(Color.red);
    jLabel3.setToolTipText(
        "<html>Specifies markers to be included in the current test.");
    jLabel3.setText("Marker");
    jTextField1.setToolTipText(
        "<html> The weight used to combine founder-only and approximate non-frequency " +
        "estimates.<br>May be set to values between 0 and 1.");
    jTextField1.setText("");
    jTextField1.addMouseListener(new FREQ2_jTextField1_mouseAdapter(this));
    Datamodel.setValue("skip_mle", "false");
    Datamodel.setValue("inbreeding", "false");
    jRunButton.addActionListener(this);

    jMarkerComboBox.addMouseListener(new FREQ2_jMarkerComboBox_mouseAdapter(this));
    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jLabel2.setText("Other options");
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(xYLayout2);
    jCheckBox2.setToolTipText("Enables estimation of inbreeding coefficient.");
    jCheckBox2.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox2.setText("Estimate Inbreeding coefficient");
    jCheckBox2.addMouseListener(new FREQ2_jCheckBox2_mouseAdapter(this));
    jCheckBox2.addItemListener(this);
    jCheckBox1.setToolTipText("<html>When false, maximum likehood estimates of allele frequencies " +
    "are computed.<br>When true, the calculation is skipped.");
    jCheckBox1.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox1.setText("Skip maximum likelihood estimate");
    jCheckBox1.addMouseListener(new FREQ2_jCheckBox1_mouseAdapter(this));
    jCheckBox1.addItemListener(this);
    this.add(jLabel1, new XYConstraints(20, 20, 85, 20));
    this.add(jLabel3,      new XYConstraints(20, 50, 85, 20));
    this.add(jTextField1,    new XYConstraints(155, 20, 320, 20));
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(jMarkerComboBox,      new XYConstraints(155, 50, 320, 20));
    this.add(jLabel2,       new XYConstraints(20, 80, 120, 20));
    this.add(jPanel1,     new XYConstraints(155, 80, 320, 66));
    jPanel1.add(jCheckBox2,      new XYConstraints(2, 32, 200, 20));
    jPanel1.add(jCheckBox1,   new XYConstraints(2, 2, 200, 20));
    jMarkerComboBox.setWidth(320);

    jTextField1.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    Object o = e.getSource();
    if (o == jRunButton)
      jRunButton_actionPerformed(e);

  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if(ob == jCheckBox1)
    {
      if (jCheckBox1.isSelected())
        Datamodel.setValue("skip_mle", "true");
      else
        Datamodel.setValue("skip_mle", "false");
    }
    if(ob == jCheckBox2)
    {
      if (jCheckBox2.isSelected())
        Datamodel.setValue("inbreeding", "true");
      else
        Datamodel.setValue("inbreeding", "false");
    }
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) freq1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunFREQ(treenode, currentnode, e.getActionCommand());
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      Datamodel.setValue("founder_weight", jTextField1.getText());
      if (jTextField1.getText().length() <= 0)
        Datamodel.removeValue("founder_weight");
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("founder_weigh", false, 198);
  }

  void jMarkerComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marker", false, 198);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("skip_mle", false, 198);
  }

  void jCheckBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("inbreedin", false, 198);
  }


}

class FREQ2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  FREQ2 adaptee;

  FREQ2_jTextField1_mouseAdapter(FREQ2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class FREQ2_jMarkerComboBox_mouseAdapter extends java.awt.event.MouseAdapter
implements Serializable{
  FREQ2 adaptee;

  FREQ2_jMarkerComboBox_mouseAdapter(FREQ2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jMarkerComboBox_mouseClicked(e);
  }
}

class FREQ2_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  FREQ2 adaptee;

  FREQ2_jCheckBox1_mouseAdapter(FREQ2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class FREQ2_jCheckBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  FREQ2 adaptee;

  FREQ2_jCheckBox2_mouseAdapter(FREQ2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox2_mouseClicked(e);
  }
}
