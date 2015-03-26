package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TDTEX2
    extends SageAnalysisPanel
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {

  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  ButtonGroup bg1 = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  String[] type = {
      "Alleles", "Genotypes"};
  String[] max = {
      "None", "Unlimited", "Number"};

  DataCollectionModel Datamodel;
  TDTEX1 tdtex1;
  JLabel jLabel9 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel7 = new JLabel();
  MyComboBox jComboBox3 = new MyComboBox();
  JComboBox jComboBox7 = new JComboBox(max);
  JComboBox jComboBox8 = new JComboBox(max);
  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JComboBox jComboBox9 = new JComboBox();
  JComboBox jComboBox10 = new JComboBox();

  MyComboBox jMarkerComboBox = new MyComboBox();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  XYLayout xYLayout2 = new XYLayout();
  JCheckBox jComboBox2 = new JCheckBox();
  JComboBox jComboBox1 = new JComboBox(type);

  JCheckBox test1CheckBox = new JCheckBox();
  JCheckBox test2CheckBox = new JCheckBox();
  JCheckBox test3CheckBox = new JCheckBox();

  XYLayout xYLayout3 = new XYLayout();
  JPanel jPanel2 = new JPanel();
  public TDTEX2(TDTEX1 input) {
    this.Datamodel = input.Datamodel;
    tdtex1 = input;
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
    jLabel1.setForeground(Color.red);
    jLabel1.setToolTipText("<html>Specifies a trait denoting affection status.");
    jLabel1.setText("Trait");
    this.setLayout(xYLayout1);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    jComboBox2.setFocusPainted(false);
    jRunButton.addActionListener(this);
    jLabel9.setForeground(Color.red);

    jLabel9.setToolTipText(
        "<html>Specifies a marker for which transmissions are scored.");
    jLabel9.setText("Marker");
    jLabel2.setToolTipText(
        "<html>Specifies a trait used as an indicator variable to select subsets of pairs to analyze.");
    jLabel2.setText("Subset");
    jLabel3.setToolTipText(
        "<html>Specifies which type of transmission is to be scored.");
    jLabel3.setText("Sample");
    jLabel4.setToolTipText("<html>Specifies the maximum number of informative affected offspring transmissions"+
                           "<br>per nuclear family that the sampler may use.");
    jLabel4.setText("Maximum children");
    jLabel5.setToolTipText(
        "<html>Specifies the maximum number of informative affected sibling pair transmissions" +
        "<br>per nuclear family that the sampler may use.");
    jLabel5.setText("Maximum sibling pairs");
    jLabel7.setToolTipText(
            "Specifies the exact tests to be performed.");
    jLabel7.setText("Exact tests");
    jTextField1.setEnabled(false);
    jTextField1.setText("");
    jTextField2.setEnabled(false);
    jTextField2.setText("");

    jMarkerComboBox.addMouseListener(new TDTEX2_jMarkerComboBox_mouseAdapter(this));
    jComboBox9.addMouseListener(new TDTEX2_jComboBox9_mouseAdapter(this));
    jComboBox10.addMouseListener(new TDTEX2_jComboBox10_mouseAdapter(this));
    jComboBox7.addMouseListener(new TDTEX2_jComboBox7_mouseAdapter(this));
    jComboBox8.addMouseListener(new TDTEX2_jComboBox8_mouseAdapter(this));
    jComboBox3.addMouseListener(new TDTEX2_jComboBox3_mouseAdapter(this));
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(xYLayout2);
    jComboBox2.setToolTipText("<html>Causes three tests to be performed: <br>1. one scoring transmissions " +
    "from all parents, <br>2. one that scores only paternal transmissions, " +
    "and <br>3. one that scores only maternal transmissions.");
    jComboBox2.setMargin(new Insets(2, 0, 2, 2));
    jComboBox2.setText("Perform sex-differential tests");
    jComboBox2.addMouseListener(new TDTEX2_jComboBox2_mouseAdapter(this));
    jComboBox2.addItemListener(this);
    jComboBox1.addMouseListener(new TDTEX2_jComboBox1_mouseAdapter(this));
    jComboBox1.addItemListener(this);

    test1CheckBox.setText("Complete Permutation test");
    test1CheckBox.setToolTipText("<html>Specifies that the exact permutation McNemar test should be performed.<br>USE CAUTIOUSLY.");
    test2CheckBox.setText("Sampling the permutation distribution for McNemar test");
    test2CheckBox.setToolTipText("Specifies that a Monte Carlo McNemar test should be performed.");

    test3CheckBox.setText("Sampling the permutation distribution for marginal");
    test3CheckBox.setToolTipText("Specifies that a Monte Carlo marginal homogeneity test should be performed.");

    JLabel temp = new JLabel("homogeneity test");

    test1CheckBox.setFocusPainted(false);
    test2CheckBox.setFocusPainted(false);
    test3CheckBox.setFocusPainted(false);

    test2CheckBox.setSelected(true);
    test3CheckBox.setSelected(true);

    jPanel2.setLayout(xYLayout3);
    jPanel2.setBorder(titledBorder1);
    jPanel2.add(test1CheckBox,    new XYConstraints(0, 2, 300, 20));
    jPanel2.add(test2CheckBox,                new XYConstraints(0, 31, 300, 20));
    jPanel2.add(test3CheckBox,         new XYConstraints(0, 60, 300, 20));
    jPanel2.add(temp,         new XYConstraints(23, 75, 300, 20));

    jPanel1.add(jComboBox2,   new XYConstraints(20, 31, 200, 20));
    jPanel1.add(jComboBox1,  new XYConstraints(2, 2, 304, 20));

    this.add(jLabel9, new XYConstraints(20, 20, 75, 20));
    this.add(jMarkerComboBox,    new XYConstraints(155, 20, 320, 20));

    this.add(jLabel1, new XYConstraints(20, 50, 81, 20));
    this.add(jComboBox9,     new XYConstraints(155, 50, 320, 20));

    this.add(jLabel2, new XYConstraints(20, 80, 89, 20));
    this.add(jComboBox10,     new XYConstraints(155, 80, 320, 20));

    this.add(jLabel4, new XYConstraints(20, 110, 89, 20));
    this.add(jComboBox7,      new XYConstraints(155, 110, 150, 20));
    this.add(jTextField1,       new XYConstraints(315, 110, 160, 20));

    this.add(jLabel5,   new XYConstraints(20, 140, 120, 20));
    this.add(jComboBox8,       new XYConstraints(155, 140, 150, 20));
    this.add(jTextField2,           new XYConstraints(315, 140, 160, 20));

    this.add(jLabel3,   new XYConstraints(20, 170, 89, 20));
    this.add(jPanel1,     new XYConstraints(155, 170, 320, 66));

    this.add(jLabel7,  new XYConstraints(20, 246, 89, 20));
    this.add(jPanel2,  new XYConstraints(155, 246, 320, 115));

    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));

    jMarkerComboBox.setWidth(320);
    jComboBox3.setWidth(320);

    jComboBox7.addItemListener(this);
    jComboBox8.addItemListener(this);
    jComboBox9.addItemListener(this);
    jComboBox10.addItemListener(this);

    jTextField1.getDocument().addDocumentListener(this);
    jTextField2.getDocument().addDocumentListener(this);

    Datamodel.setValue("sample", "alleles");
    Datamodel.setValue("sex_differential", "false");

    Runnable setinfo = new Runnable()
    {
      public void run()
      {
        Setoption(true);
      }
    };
    SwingUtilities.invokeLater(setinfo);
  }

  void Setoption(boolean input)
  {
    CheckableItem[] items = new CheckableItem[3];
    VariableData temp1 = new VariableData("Complete Permutation test", "option");
    VariableData temp2 = new VariableData("Sampling the permutation distribution for McNemar test", "option");
    VariableData temp3 = new VariableData("Monte Carlo marginal homogeneity test", "option");
    items[0] = new CheckableItem(temp1);
    items[0].setSelected(input);
    items[1] = new CheckableItem(temp2);
    items[1].setSelected(input);
    items[2] = new CheckableItem(temp3);
    items[2].setSelected(input);

    jComboBox3.setData(items);
    if(input)
    {
      String all = "Permutation test, Monte Carlo McNemar test, Monte Carlo marginal homogeneity test";
      jComboBox3.setSelectedItem(all);
    }
  }


  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton) {
      MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
      IconNode treenode = (IconNode) tdtex1.analysis_node;
      NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
      mf.RunTDTEX(treenode, currentnode, e.getActionCommand());
    }

  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      Datamodel.setValue("max_children_value", jTextField1.getText());
      if (Datamodel.getValue("max_children").toString().compareTo("number") == 0) {
        if (jTextField1.getText().length() <= 0)
          jRunButton.setIcon(error_image);
        else
          jRunButton.setIcon(next_image);
      }
    }
    if (document == jTextField2.getDocument()) {
      Datamodel.setValue("max_sib_pairs_value", jTextField2.getText());
      if (Datamodel.getValue("max_sib_pairs").toString().compareTo("number") ==
          0) {
        if (jTextField2.getText().length() <= 0)
          jRunButton.setIcon(error_image);
        else
          jRunButton.setIcon(next_image);
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
      Datamodel.setValue("sample", jComboBox1.getSelectedItem().toString().toLowerCase());
    }
    if (ob == jComboBox2) {
      if(jComboBox2.isSelected())
        Datamodel.setValue("sex_differential","true");
      else
        Datamodel.setValue("sex_differential","false");
    }
    if (ob == jComboBox7) {
      String item = jComboBox7.getSelectedItem().toString().toLowerCase();
      Datamodel.setValue("max_children", item);
      if (jComboBox7.getSelectedIndex() == 2) {
        jTextField1.setEnabled(true);
        jTextField1.requestFocus();
        if (jTextField1.getText().length() <= 0)
          jRunButton.setIcon(error_image);
      }
      else {
        jTextField1.setEnabled(false);
        jRunButton.setIcon(next_image);
      }
    }
    if (ob == jComboBox8) {
      String item = jComboBox8.getSelectedItem().toString().toLowerCase();
      Datamodel.setValue("max_sib_pairs", item);

      if (jComboBox8.getSelectedIndex() == 2) {
        jTextField2.setEnabled(true);
        jTextField2.setFocusable(true);
        if (jTextField2.getText().length() <= 0)
          jRunButton.setIcon(error_image);
      }
      else {
        jTextField2.setEnabled(false);
        jRunButton.setIcon(next_image);
      }
    }
    if (ob == jComboBox9) {
       if (jComboBox9.getSelectedIndex() > 0) {
         Datamodel.setValue("trait", jComboBox9.getSelectedItem());
       }
       else
       {
         Datamodel.removeValue("trait");
       }
     }
     if (ob == jComboBox10) {
       if (jComboBox10.getSelectedIndex() > 0) {
         Datamodel.setValue("parental_trait", jComboBox10.getSelectedItem());
       }
       else
       {
           Datamodel.removeValue("parental_trait");
       }
     }

  }

  void jMarkerComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marke", false, 367);
  }

  void jComboBox9_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 367);
  }

  void jComboBox10_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("parental_trai", false, 367);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sample ", false, 367);
  }

  void jComboBox7_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("max_childre", false, 367);
  }

  void jComboBox8_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("max_sib_pair", false, 368);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sex_differentia", false, 368);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("skip_exact_tests", false, 368);
  }
}

class TDTEX2_jMarkerComboBox_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TDTEX2 adaptee;

  TDTEX2_jMarkerComboBox_mouseAdapter(TDTEX2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jMarkerComboBox_mouseClicked(e);
  }
}

class TDTEX2_jComboBox9_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TDTEX2 adaptee;

  TDTEX2_jComboBox9_mouseAdapter(TDTEX2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox9_mouseClicked(e);
  }
}

class TDTEX2_jComboBox10_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TDTEX2 adaptee;

  TDTEX2_jComboBox10_mouseAdapter(TDTEX2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox10_mouseClicked(e);
  }
}

class TDTEX2_jComboBox7_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TDTEX2 adaptee;

  TDTEX2_jComboBox7_mouseAdapter(TDTEX2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox7_mouseClicked(e);
  }
}

class TDTEX2_jComboBox8_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TDTEX2 adaptee;

  TDTEX2_jComboBox8_mouseAdapter(TDTEX2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox8_mouseClicked(e);
  }
}

class TDTEX2_jComboBox3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  TDTEX2 adaptee;

  TDTEX2_jComboBox3_mouseAdapter(TDTEX2 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class TDTEX2_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  TDTEX2 adaptee;

  TDTEX2_jComboBox1_mouseAdapter(TDTEX2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class TDTEX2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  TDTEX2 adaptee;

  TDTEX2_jComboBox2_mouseAdapter(TDTEX2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}
