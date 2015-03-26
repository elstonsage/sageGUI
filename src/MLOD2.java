package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;

public class MLOD2
    extends SageAnalysisPanel
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();

  DataCollectionModel Datamodel;
  MLOD1 mlod1;

  String[] scan_type = {"Markers", "Interval(s)","Markers and Interval(s)"};
  String[] real_scan_type = {"markers", "interval", "both"};
  String[] output_pedigree = {"None","Marker","Interval","Marker and Interval"};
  String[] real_output_pedigree = {"none","marker","interval","both"};

  String[] sample_detail = {"None","Removed","All"};
  String[] real_sample_detail = {"none","removed","all"};

  JLabel jLabel4 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel5 = new JLabel();
  MyComboBox jRegionComboBox = new MyComboBox();
  JLabel jLabel6 = new JLabel();
  MyComboBox jTraitMarkerComboBox = new MyComboBox();
  JLabel jLabel7 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel8 = new JLabel();
  JComboBox jComboBox5 = new JComboBox(output_pedigree);
  JLabel jLabel9 = new JLabel();
  JComboBox jComboBox6 = new JComboBox(sample_detail);
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  XYLayout xYLayout2 = new XYLayout();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JComboBox jComboBox4 = new JComboBox(scan_type);

  public MLOD2(MLOD1 input) {
    this.Datamodel = input.Datamodel;
    mlod1 = input;
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
    jRegionComboBox.setWidth(320);
    jRegionComboBox.addMouseListener(new MLOD2_jRegionComboBox_mouseAdapter(this));
    jTraitMarkerComboBox.setWidth(320);
    jTraitMarkerComboBox.addMouseListener(new MLOD2_jTraitMarkerComboBox_mouseAdapter(this));
    jLabel1.setToolTipText(
            "<html>Specifies computing LOD scores at the observed markers"+
            "<br>or at the markers and intervals between them.");
    jLabel1.setText("Scan type");
    this.setLayout(xYLayout1);
    jRunButton.addActionListener(this);

    jLabel4.setToolTipText("Specifies a title for the analysis.");
    jLabel4.setText("Title");
    jTextField1.setText("");
    jTextField1.addMouseListener(new MLOD2_jTextField1_mouseAdapter(this));
    jLabel5.setToolTipText("Specifies the name of the region to be analyzed.");
    jLabel5.setText("Region");
    jLabel6.setForeground(Color.red);
    jLabel6.setToolTipText(
        "Character string representing the name of a trait marker to be analyzed.");
    jLabel6.setText("Trait_marker");
    jLabel7.setToolTipText("Pedigrees for which (2n-f) is larger than this are not analyzed.");
    jLabel7.setText("Max size");
    jTextField3.setText("18");
    jTextField3.addMouseListener(new MLOD2_jTextField3_mouseAdapter(this));
    jLabel8.setToolTipText(
            "<html>Controls the amount of output generated on"+
            "<bt>a per pedigree basis in the detailed output file.");
    jLabel8.setText("Output pedigree");
    jLabel9.setToolTipText(
        "<html>Controls the amount of detail provided about" +
        "<br>the useable pedigree data sample.");
    jLabel9.setText("Sample detail");
    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jComboBox6.setSelectedIndex(1);
    jComboBox6.addMouseListener(new MLOD2_jComboBox6_mouseAdapter(this));
    jComboBox5.addMouseListener(new MLOD2_jComboBox5_mouseAdapter(this));
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(xYLayout2);
    jLabel2.setEnabled(false);
    jLabel2.setToolTipText("<html>Sets the interval size in centimorgans for computing LOD scores" +
        "<br>between observed markers.");
    jLabel2.setText("Interval size");
    jTextField2.setEnabled(false);
    jTextField2.setToolTipText("");
    jTextField2.setText("2.0");
    jTextField2.addMouseListener(new MLOD2_jTextField2_mouseAdapter(this));
    jComboBox4.addItemListener(this);
    jComboBox4.addMouseListener(new MLOD2_jComboBox4_mouseAdapter(this));

    jComboBox5.addItemListener(this);
    jComboBox6.addItemListener(this);
    this.add(jLabel4,  new XYConstraints(20, 20, 75, 20));
    this.add(jTextField1,        new XYConstraints(155, 20, 320, 20));

    this.add(jLabel6,   new XYConstraints(20, 50, 75, 20));
    this.add(jTraitMarkerComboBox,          new XYConstraints(155, 50, 320, 20));

    this.add(jLabel5,     new XYConstraints(20, 80, 75, 20));
    this.add(jRegionComboBox,            new XYConstraints(155, 80, 320, 20));

    this.add(jLabel7,          new XYConstraints(20, 110, 80, 20));
    this.add(jTextField3,                new XYConstraints(155, 110, 100, 20));

    this.add(jLabel9,            new XYConstraints(285, 110, 80, 20));
    this.add(jComboBox6,        new XYConstraints(375, 110, 100, 20));

    this.add(jLabel8,  new XYConstraints(20, 140, 80, 20));
    this.add(jComboBox5,   new XYConstraints(155, 140, 320, 20));

    this.add(jLabel1,  new XYConstraints(20, 170, 80, 20));
    this.add(jPanel1,   new XYConstraints(155, 170, 320, 66));

    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    jPanel1.add(jLabel2,  new XYConstraints(2, 31, 85, 20));
    jPanel1.add(jTextField2,   new XYConstraints(120, 31, 186, 20));
    jPanel1.add(jComboBox4,   new XYConstraints(2, 2, 304, 20));

    jTextField1.getDocument().addDocumentListener(this);
    jTextField3.getDocument().addDocumentListener(this);

    Datamodel.setValue("distance", "2.0");
    Datamodel.setValue("scan_type", "marker");
    Datamodel.setValue("max_size", "18");
    Datamodel.setValue("output_pedigree", "none");
    Datamodel.setValue("sample_detail", "removed");
    jTextField2.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton)
      jRunButton_actionPerformed(e);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) mlod1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunMLOD(treenode, currentnode, e.getActionCommand());
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      Datamodel.setValue("Title", jTextField1.getText());
      if (jTextField1.getText().length() <= 0)
        Datamodel.removeValue("Title");
    }

    if (document == jTextField2.getDocument()) {
      if (jTextField2.getText().length() <= 0)
        Datamodel.setValue("distance", "2.0");
      else
        Datamodel.setValue("distance", jTextField2.getText());
    }
    if (document == jTextField3.getDocument()) {
      if (jTextField3.getText().length() <= 0)
        Datamodel.removeValue("max_size");
      else
        Datamodel.setValue("max_size", jTextField3.getText());
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
    if (ob == jComboBox4) {
      int index = jComboBox4.getSelectedIndex();
      if(index==0)
      {
        jLabel2.setEnabled(false);
        jTextField2.setEnabled(false);
      }
      else
      {
        jLabel2.setEnabled(true);
        jTextField2.setEnabled(true);
      }
      Datamodel.setValue("scan_type", real_scan_type[index]);
    }

    else if (ob == jComboBox5) {
      int index = jComboBox5.getSelectedIndex();
      Datamodel.setValue("output_pedigree", real_output_pedigree[index]);
    }

    else if (ob == jComboBox6) {
      int index = jComboBox6.getSelectedIndex();
      Datamodel.setValue("sample_detail", real_sample_detail[index]);
    }
  }

  void jComboBox4_mouseClicked(MouseEvent e) {//scan type
    Frame1.mainFrame1.pdfframe.setTextonPage("scan_typ", false, 319);
  }

  void jTextField1_mouseClicked(MouseEvent e) {//title
    Frame1.mainFrame1.pdfframe.setTextonPage("titl", false, 319);
  }

  void jRegionComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("region", false, 319);
  }

  void jTraitMarkerComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait_marker", false, 319);
  }

  void jTextField3_mouseClicked(MouseEvent e) {//max size
    Frame1.mainFrame1.pdfframe.setTextonPage("max_siz", false, 319);
  }

  void jTextField2_mouseClicked(MouseEvent e) {//interval size
    Frame1.mainFrame1.pdfframe.setTextonPage("distance", false, 319);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("output_pedigree", false, 320);
  }

  void jComboBox6_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sample_detail", false, 320);
  }
}

class MLOD2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  MLOD2 adaptee;

  MLOD2_jTextField1_mouseAdapter(MLOD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class MLOD2_jTraitMarkerComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  MLOD2 adaptee;

  MLOD2_jTraitMarkerComboBox_mouseAdapter(MLOD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTraitMarkerComboBox_mouseClicked(e);
  }
}

class MLOD2_jRegionComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  MLOD2 adaptee;

  MLOD2_jRegionComboBox_mouseAdapter(MLOD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRegionComboBox_mouseClicked(e);
  }
}

class MLOD2_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  MLOD2 adaptee;

  MLOD2_jTextField3_mouseAdapter(MLOD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class MLOD2_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  MLOD2 adaptee;

  MLOD2_jComboBox5_mouseAdapter(MLOD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}

class MLOD2_jComboBox6_mouseAdapter extends java.awt.event.MouseAdapter {
  MLOD2 adaptee;

  MLOD2_jComboBox6_mouseAdapter(MLOD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox6_mouseClicked(e);
  }
}

class MLOD2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  MLOD2 adaptee;

  MLOD2_jComboBox4_mouseAdapter(MLOD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class MLOD2_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  MLOD2 adaptee;

  MLOD2_jTextField2_mouseAdapter(MLOD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}
