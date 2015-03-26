package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG2
    extends SageAnalysisPanel
    implements DocumentListener, ActionListener, ItemListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  XYLayout xYLayout2 = new XYLayout();

  SEGREG1 segreg1;
  NodeInfo segregnode;
  DataCollectionModel Datamodel;
  TitledBorder titledBorder1;

  ButtonGroup bp = new ButtonGroup();

  JComboBox jComboBox4 = new JComboBox();

  JRadioButton QRadioButton = new JRadioButton();
  JRadioButton BRadioButton = new JRadioButton();
  JRadioButton BARadioButton = new JRadioButton();

  JPanel jPanel2 = new JPanel();
  JLabel titleLabel = new JLabel();
  JLabel traitLabel = new JLabel();
  JLabel typeLabel = new JLabel();
  JLabel optionsLabel = new JLabel();

  JTextField jTextField1 = new JTextField();
  JPanel jPanel1 = new JPanel();
  JCheckBox tprobCheckBox = new JCheckBox();

  boolean IsPopup = true;

  final int QuantitativeType = 1;
  final int BinaryType = 2;
  final int BinaryAgeType = 3;
  final int Default = 0;

  public SEGREG2(SEGREG1 input) {
    this.Datamodel = input.Datamodel;
    segreg1 = input;
    segregnode = (NodeInfo) input.analysis_node.getUserObject();

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

    QRadioButton.setFocusPainted(false);
    BRadioButton.setFocusPainted(false);
    BARadioButton.setFocusPainted(false);
    tprobCheckBox.setFocusPainted(false);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    if (segreg1.errorF_node != null) {
      jRunButton.setIcon(error_image);
    }
    else {
      jRunButton.setIcon(next_image);
    }

    traitLabel.setForeground(Color.red);
    traitLabel.setToolTipText("Specifies the name of a primary trait.");
    traitLabel.setText("Dependent variate");

    BRadioButton.setText("Binary trait");
    BRadioButton.addMouseListener(new SEGREG2_jRadioButton2_mouseAdapter(this));

    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout3);

    QRadioButton.setText("Quantitative");
    QRadioButton.addMouseListener(new SEGREG2_jRadioButton1_mouseAdapter(this));

    titleLabel.setToolTipText("Specifies a title for the analysis.");
    titleLabel.setText("Title");
    typeLabel.setText("Trait type");
    typeLabel.setToolTipText("Primary trait type.");

    jTextField1.setText("");
    jTextField1.addMouseListener(new SEGREG2_jTextField1_mouseAdapter(this));

    optionsLabel.setText("Output options");

    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout2);

    tprobCheckBox.addMouseListener(new SEGREG2_jComboBox3_mouseAdapter(this));
    tprobCheckBox.addItemListener(this);
    jComboBox4.addMouseListener(new SEGREG2_jComboBox4_mouseAdapter(this));
    BARadioButton.setText("Binary with variable age of onset");
    QRadioButton.addMouseListener(new SEGREG2_QRadioButton_mouseAdapter(this));
    tprobCheckBox.setToolTipText("Specifies option to produce output file of type probabilities and penetrance functions.");
    tprobCheckBox.setMargin(new Insets(2, 0, 2, 2));
    tprobCheckBox.setText("Output file of type probabilities and penetrance functions");
    jPanel2.add(QRadioButton,   new XYConstraints(2, 2, 150, 20));
    jPanel2.add(BRadioButton,      new XYConstraints(2, 31, 100, 20));
    jPanel2.add(BARadioButton,      new XYConstraints(2, 60, 200, 20));
    this.add(titleLabel, new XYConstraints(20, 20, 75, 20));
    this.add(jTextField1,  new XYConstraints(155, 20, 320, 20));
    this.add(traitLabel, new XYConstraints(20, 50, 140, 20));
    this.add(jComboBox4,   new XYConstraints(155, 50, 320, 20));
    this.add(typeLabel, new XYConstraints(20, 80, 80, 20));
    this.add(jPanel2,    new XYConstraints(155, 80, 320, 98));
    this.add(optionsLabel,   new XYConstraints(20, 188, 80, 20));
    this.add(jPanel1,         new XYConstraints(155, 188, 320, 38));
    jPanel1.add(tprobCheckBox,     new XYConstraints(2, 2, 300, 20));
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));

    jRunButton.addActionListener(this);
    bp.add(QRadioButton);
    bp.add(BRadioButton);
    bp.add(BARadioButton);

    QRadioButton.addActionListener(this);
    BRadioButton.addActionListener(this);
    BARadioButton.addActionListener(this);

    jComboBox4.addItemListener(this);

    jTextField1.getDocument().addDocumentListener(this);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) segreg1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunSEGREG(treenode, currentnode, e.getActionCommand());
  }

  void setSEGREGpanel(int type)
  {
      SEGREG3 s3 = (SEGREG3) segregnode.component_vector.get(2);
      SEGREG4 s4 = (SEGREG4) segregnode.component_vector.get(3);
      SEGREG5 s5 = (SEGREG5) segregnode.component_vector.get(4);
      Component com3[];
      Component com4[];
      Component com5[];

      MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;

      switch(type)
      {
      case QuantitativeType:

          mf.jTabbedPane1.setSelectedIndex(2);

            Datamodel.setValue("continuous", "use");
            Datamodel.setValue("trait_type", "continuous");

            com3 = s3.getComponents();
            for (int i = 0; i < com3.length; i++) {
              if (com3[i] instanceof JButton)
                com3[i].setEnabled(true);
            }
            s3.jComboBox1.setSelectedIndex(0);

            Datamodel.removeValue("binary_trait");
            com4 = s4.getComponents();
            for (int i = 0; i < com4.length; i++) {
              if (com4[i] instanceof JButton)
                com4[i].setEnabled(false);
            }
            s4.jComboBox1.setSelectedIndex(0);

            Datamodel.removeValue("binary_trait_age");
            com5 = s5.getComponents();
            for (int i = 0; i < com5.length; i++) {
              if (com5[i] instanceof JButton)
                com5[i].setEnabled(false);
      }
          break;
      case BinaryType:
          mf.jTabbedPane1.setSelectedIndex(3);

          Datamodel.setValue("binary_trait", "use");
          Datamodel.setValue("trait_type", "binary");

          com4 = s4.getComponents();
          for (int i = 0; i < com4.length; i++) {
            if (com4[i] instanceof JButton)
              com4[i].setEnabled(true);
          }
          s4.jComboBox1.setSelectedIndex(0);

          //other tabs
          Datamodel.removeValue("continuous");
          com3 = s3.getComponents();
          for (int i = 0; i < com3.length; i++) {
            if (com3[i] instanceof JButton)
              com3[i].setEnabled(false);
          }
          s3.jComboBox1.setSelectedIndex(0);

          Datamodel.removeValue("binary_trait_age");
          com5 = s5.getComponents();
          for (int i = 0; i < com5.length; i++) {
            if (com5[i] instanceof JButton)
              com5[i].setEnabled(false);
      }
      break;
      case BinaryAgeType:
          mf.jTabbedPane1.setSelectedIndex(4);

          Datamodel.setValue("binary_trait_age", "use");
          Datamodel.setValue("trait_type", "age_onset");

          com5 = s5.getComponents();
          for (int i = 0; i < com5.length; i++) {
            if (com5[i] instanceof JButton)
              com5[i].setEnabled(true);
          }

          //other tabs
          Datamodel.removeValue("continuous");
          com3 = s3.getComponents();
          for (int i = 0; i < com3.length; i++) {
            if (com3[i] instanceof JButton)
              com3[i].setEnabled(false);
          }
          s3.jComboBox1.setSelectedIndex(0);

          Datamodel.removeValue("binary_trait");
          com4 = s4.getComponents();
          for (int i = 0; i < com4.length; i++) {
            if (com4[i] instanceof JButton)
              com4[i].setEnabled(false);
          }
          s4.jComboBox1.setSelectedIndex(0);

      break;

      case Default :
          if(Datamodel.hasValue("binary_trait"))
              Datamodel.removeValue("binary_trait");
          if(Datamodel.hasValue("continuous"))
              Datamodel.removeValue("continuous");
          if(Datamodel.hasValue("binary_trait_age"))
              Datamodel.removeValue("binary_trait_age");
          if(Datamodel.hasValue("trait_type"))
              Datamodel.removeValue("trait_type");

          com3 = s3.getComponents();
          for (int i = 0; i < com3.length; i++) {
            if (com3[i] instanceof JButton)
              com3[i].setEnabled(false);
          }
          s3.jComboBox1.setSelectedIndex(0);

          com4 = s4.getComponents();
          for (int i = 0; i < com4.length; i++) {
            if (com4[i] instanceof JButton)
              com4[i].setEnabled(false);
          }
          s4.jComboBox1.setSelectedIndex(0);

          com5 = s5.getComponents();
          for (int i = 0; i < com5.length; i++) {
            if (com5[i] instanceof JButton)
              com5[i].setEnabled(false);
          }

          break;
      }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

   if (ob == tprobCheckBox) {
      if(tprobCheckBox.isSelected())
        Datamodel.setValue("type_prob", "true");
      else
      {
          if (Datamodel.hasValue("type_prob"))
              Datamodel.removeValue("type_prob");
      }
    }
    else if (ob == jComboBox4) {
      if (jComboBox4.getSelectedIndex() <= 0)
        Datamodel.removeValue("trait");
      else
        Datamodel.setValue("trait", jComboBox4.getSelectedItem().toString());

      if (Datamodel.hasValue("trait")) {
        jRunButton.setIcon(next_image);
      }
      else
      {
          jRunButton.setIcon(error_image);
      }
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      Datamodel.setValue("title", jTextField1.getText());
      if (jTextField1.getText().length() <= 0)
        Datamodel.removeValue("title");
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if (ob == jRunButton)
      jRunButton_actionPerformed(e);

  else if (ob == QRadioButton) { //continuous
      setSEGREGpanel(QuantitativeType);
  }

  else if (ob == BRadioButton) { //binary
      setSEGREGpanel(BinaryType);
  }
  else if (ob == BARadioButton) { //binary with variable age of onset
      setSEGREGpanel(BinaryAgeType);
    }
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("titl", false, 143);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 143);
  }

  void jRadioButton1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("type", false, 143);
  }

  void jRadioButton2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("type", false, 143);
  }

  void jRadioButton3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("type", false, 143);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pen_func_out", false, 181);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("type_pro", false, 181);
  }

}

class SEGREG2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG2 adaptee;

  SEGREG2_jTextField1_mouseAdapter(SEGREG2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG2 adaptee;

  SEGREG2_jComboBox4_mouseAdapter(SEGREG2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class SEGREG2_jRadioButton1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG2 adaptee;

  SEGREG2_jRadioButton1_mouseAdapter(SEGREG2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton1_mouseClicked(e);
  }
}

class SEGREG2_jRadioButton2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG2 adaptee;

  SEGREG2_jRadioButton2_mouseAdapter(SEGREG2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton2_mouseClicked(e);
  }
}

class SEGREG2_QRadioButton_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG2 adaptee;

  SEGREG2_QRadioButton_mouseAdapter(SEGREG2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton3_mouseClicked(e);
  }
}
class SEGREG2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG2 adaptee;

  SEGREG2_jComboBox2_mouseAdapter(SEGREG2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SEGREG2_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG2 adaptee;

  SEGREG2_jComboBox3_mouseAdapter(SEGREG2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}
