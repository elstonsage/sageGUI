package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.*;
import java.util.*;
/**
 * <p>Title: NEW GUI for SAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author suna
 * @version 1.0
 */

public class DECIPHER2
    extends SageAnalysisPanel
    implements DocumentListener, ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel2 = new JLabel();
  ButtonGroup bg = new ButtonGroup();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel10 = new JLabel();

  JTextField title = new JTextField();
  JTextField starting_points = new JTextField();
  JTextField epsilon = new JTextField();

  DataCollectionModel Datamodel;

  DECIPHER1 decipher1;
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  XYLayout xYLayout2 = new XYLayout();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton3 = new JButton();
  JLabel jLabel7 = new JLabel();
  JComboBox jComboBox2 = new JComboBox();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JButton jButton1 = new JButton();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JButton jButton2 = new JButton();

  DECIPHER_Dialog1_Partition1 dialog1 = new DECIPHER_Dialog1_Partition1(this, "Specification");
  DECIPHER_Dialog1_Partition2 dialog2 = new DECIPHER_Dialog1_Partition2(this, "Specification");
  DECIPHER_Dialog2_Tasks dialog3 = new DECIPHER_Dialog2_Tasks(this, "Specification");
  DECIPHER_Dialog3_Pools dialog4 = new DECIPHER_Dialog3_Pools(this, "Specification");
  DECIPHER_Dialog4_Region dialog5 = new DECIPHER_Dialog4_Region(this, "Specification");
  DECIPHER_Dialog5_Blocks dialog6 = new DECIPHER_Dialog5_Blocks(this, "Specification");

  JButton jButton4 = new JButton();
  JButton jButton5 = new JButton();
  JButton jButton6 = new JButton();
  JComboBox jComboBox4 = new JComboBox();
  JLabel jLabel6 = new JLabel();

  String[] analysis_unit = {"Each individual", "Family representative","Family founders","Pool"};
  String[] analysis_unit_real = {"each_individual", "family_rep","family_founders","pool"};

  JComboBox jComboBox1 = new JComboBox(analysis_unit);
  JLabel jLabel15 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JButton jButton7 = new JButton();
  JButton jButton8 = new JButton();
  JTextField jTextField7 = new JTextField();
  JButton jButton9 = new JButton();
  JButton jButton10 = new JButton();
  JLabel jLabel18 = new JLabel();
  JTextField jTextField8 = new JTextField();
  JButton jButton11 = new JButton();
  JButton jButton12 = new JButton();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel14 = new JLabel();
  JTextField jTextField5 = new JTextField();
  JCheckBox jComboBox3 = new JCheckBox();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel16 = new JLabel();
  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JCheckBox jCheckBox1 = new JCheckBox();
  JLabel jLabel17 = new JLabel();
  JTextField jTextField9 = new JTextField();
  public DECIPHER2(DECIPHER1 input) {
    this.Datamodel = input.Datamodel;
    decipher1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
      jComboBox3.setFocusPainted(false);
      jCheckBox1.setFocusPainted(false);
    dialog1.set_dataModel(Datamodel);
    dialog2.set_dataModel(Datamodel);
    dialog3.set_dataModel(Datamodel);
    dialog4.set_dataModel(Datamodel);
    dialog5.set_dataModel(Datamodel);
    dialog6.set_dataModel(Datamodel);

    jComboBox4.addMouseListener(new DECIPHER2_jComboBox4_mouseAdapter(this));

    jComboBox1.setSelectedIndex(2);
    jComboBox1.addItemListener(this);
    jComboBox1.addMouseListener(new DECIPHER2_jComboBox1_mouseAdapter(this));
    titledBorder1 = new TitledBorder("");
    jLabel1.setToolTipText("The analysis title.");
    jLabel1.setText("Title");
    this.setLayout(xYLayout1);
    jLabel3.setToolTipText(
            "<html>Number of sets of randomly chosen starting values"+
            "<br>for which the EM algorithm is to be run.");
    jLabel3.setText("Starting points");
    jLabel2.setToolTipText(
            "<html>Specifies how to treat relatedness of individuals, individuals" +
            "<br>to represent of families and subpopulations.");
    jLabel2.setText("Data");
    title.setToolTipText("");
    title.setText("analysis1");
    title.addMouseListener(new DECIPHER2_title_mouseAdapter(this));

    starting_points.setText("10");
    starting_points.addMouseListener(new DECIPHER2_starting_points_mouseAdapter(this));
    jLabel5.setForeground(Color.red);
    jLabel5.setToolTipText("Specifies a region to be analyzed.");
    jLabel5.setText("Region");
    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jLabel10.setToolTipText(
            "<html>A convergence criterion for the EM algorithm that specifies the maximum"+
            "<br>difference in haplotype frequencies between successive iterations.");
    jLabel10.setText("Epsilon");
    epsilon.setText("0.00001");
    epsilon.addMouseListener(new DECIPHER2_epsilon_mouseAdapter(this));

    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jButton3.addActionListener(this);

    jButton4.addActionListener(this);
    jButton5.addActionListener(this);
    jButton6.addActionListener(this);
    jButton7.addActionListener(this);
    jButton8.addActionListener(this);
    jButton9.addActionListener(this);
    jButton10.addActionListener(this);
    jButton11.addActionListener(this);
    jButton12.addActionListener(this);

    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout2);
    jLabel4.setToolTipText(
            "Specifies the tasks to be performed.");
    jLabel4.setText("Analysis tasks");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Define");
    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);
    jTextField1.setText("");
    jTextField1.addMouseListener(new DECIPHER2_jTextField1_mouseAdapter(this));
    jLabel7.setText("Family representative");
    jLabel7.setToolTipText(
            "Variable used to specify one genotyped individual per constituent pedigree.");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("Define");
    jLabel9.setText("Family representative");
    jLabel11.setText("value");
    jLabel9.setToolTipText(
            "<html>Value of family representative variable which designates <br>a genotyped individual for haplotype reporting.");
    jLabel11.setToolTipText(
            "<html>Value of family representative variable which designates <br>a genotyped individual for haplotype reporting.");

    jTextField2.setEnabled(false);
    jTextField2.setEditable(false);
    jTextField2.setMargin(new Insets(1, 2, 2, 2));
    jTextField2.setText("");
    jTextField2.addMouseListener(new DECIPHER2_jTextField2_mouseAdapter(this));

    jLabel8.setText("Primary partition");
    jLabel8.setToolTipText(
            "Specifies  first level partitions of the data.");

    jLabel12.setText("Secondary partition");
    jLabel12.setToolTipText(
            "Specifies second level partitions nested within each first level partition.");

    jTextField4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setMargin(new Insets(1, 2, 2, 2));
    jTextField4.setText("");
    jTextField4.addMouseListener(new DECIPHER2_jTextField4_mouseAdapter(this));
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Define");
    jComboBox2.addMouseListener(new DECIPHER2_jComboBox2_mouseAdapter(this));
    jButton4.setHorizontalAlignment(SwingConstants.CENTER);
    jButton4.setHorizontalTextPosition(SwingConstants.CENTER);
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton4.setText("Reset");
    jButton5.setHorizontalTextPosition(SwingConstants.CENTER);
    jButton5.setMargin(new Insets(2, 2, 2, 2));
    jButton5.setText("Reset");
    jButton6.setHorizontalTextPosition(SwingConstants.CENTER);
    jButton6.setMargin(new Insets(2, 2, 2, 2));
    jButton6.setText("Reset");
    jLabel6.setText("Analysis unit");
    jLabel6.setToolTipText("A set of individuals for whom joint haplotype frequencies are to be determined.");

    jLabel15.setText("Pools");
    jLabel15.setToolTipText("Allow to specify traits that contains the estimated allele frequencies from pools.");

    jTextField3.setEnabled(false);
    jTextField3.setEditable(false);
    jTextField3.addMouseListener(new DECIPHER2_jTextField3_mouseAdapter(this));
    jButton7.setMargin(new Insets(2, 2, 2, 2));
    jButton7.setText("Define");
    jButton8.setMargin(new Insets(2, 2, 2, 2));
    jButton8.setText("Reset");
    jTextField7.setEnabled(false);
    jTextField7.setEditable(false);
    jTextField7.setText("");
    jTextField7.addMouseListener(new DECIPHER2_jTextField7_mouseAdapter(this));
    jButton9.setMargin(new Insets(2, 2, 2, 2));
    jButton9.setText("Define");
    jButton10.setMargin(new Insets(2, 2, 2, 2));
    jButton10.setText("Reset");
    jLabel18.setText("Blocks");
    jLabel18.setToolTipText("Specifies how to determine haplotype blocks.");

    jTextField8.setEnabled(false);
    jTextField8.setEditable(false);
    jTextField8.setText("");
    jTextField8.addMouseListener(new DECIPHER2_jTextField8_mouseAdapter(this));
    jButton11.setMargin(new Insets(2, 2, 2, 2));
    jButton11.setText("Define");
    jButton12.setMargin(new Insets(2, 2, 2, 2));
    jButton12.setText("Reset");
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout3);
    jLabel14.setEnabled(false);
    jLabel14.setToolTipText(
        "<html>Haplotype frequency estimates greater than or equal to" +
        "<br>this value are displayed.");
    jLabel14.setText("Cutoff");
    jTextField5.setEnabled(false);
    jTextField5.setText("0.001");
    jTextField5.addMouseListener(new DECIPHER2_jTextField5_mouseAdapter(this));
    jComboBox3.addMouseListener(new DECIPHER2_jComboBox3_mouseAdapter(this));
    jComboBox3.setToolTipText(
        "<html>Write haplotype frequency estimates and ln likelihood for each set" +
        "<br>of starting points for which the EM algorithm is run to a dump file.");
    jComboBox3.setMargin(new Insets(0, 0, 0, 0));
    jComboBox3.setText("Produce EM detail file");
    jComboBox3.addItemListener(this);
    jLabel13.setToolTipText(
            "<html>Write to a dump file haplotype frequency estimates and the ln likelihood"+
            "<br>for each set of starting points for which the EM algorithm is run.");
    jLabel13.setText("Dump");
    jLabel16.setToolTipText("Specifies  marker filtering options.");
    jLabel16.setText("Filters");
    jPanel3.setBorder(titledBorder1);
    jPanel3.setLayout(xYLayout4);
    jCheckBox1.setToolTipText(
        "Specifies removal of markers based on their minor allele frequency.");
    jCheckBox1.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox1.setText("Filter by minor allele frequency");
    jCheckBox1.addMouseListener(new DECIPHER2_jCheckBox1_mouseAdapter(this));
    jLabel17.setEnabled(false);
    jLabel17.setToolTipText(
            "<html>Specifies the minor allele frequency below which diallelic markers"+
            "<br>are to be removed from the analysis.");
    jLabel17.setText("Threshold");
    jTextField9.setEnabled(false);
    jTextField9.setText(".1");
    jTextField9.addMouseListener(new DECIPHER2_jTextField9_mouseAdapter(this));

    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));

    this.add(jLabel1, new XYConstraints(20, 20, 75, 20));
    this.add(title,         new XYConstraints(155, 20, 320, 20));

    this.add(jLabel5,  new XYConstraints(20, 50, 75, 20));
    this.add(jTextField7, new XYConstraints(155, 50, 219, 20));
    this.add(jButton9, new XYConstraints(380, 50, 45, 20));
    this.add(jButton10, new XYConstraints(430, 50, 45, 20));

    this.add(jLabel4,            new XYConstraints(20, 80, 75, 20));
    this.add(jTextField1, new XYConstraints(155, 80, 219, 20));
    this.add(jButton3, new XYConstraints(380, 80, 45, 20));
    this.add(jButton6, new XYConstraints(430, 80, 45, 20));

    this.add(jLabel18, new XYConstraints(20, 110, 75, 20));
    this.add(jTextField8, new XYConstraints(155, 110, 219, 20));
    this.add(jButton11, new XYConstraints(380, 110, 45, 20));
    this.add(jButton12, new XYConstraints(430, 110, 45, 20));

    jPanel1.add(jLabel6, new XYConstraints(2, 2, 116, 20));
    jPanel1.add(jComboBox1, new XYConstraints(120, 2, 194, 20));
    jPanel1.add(jLabel15, new XYConstraints(2, 31, 116, 20));
    jPanel1.add(jTextField3, new XYConstraints(120, 31, 93, 20));
    jPanel1.add(jButton7, new XYConstraints(219, 31, 45, 20));
    jPanel1.add(jButton8, new XYConstraints(269, 31, 45, 20));

    jPanel1.add(jLabel7,  new XYConstraints(2, 60, 116, 17));
    jPanel1.add(jComboBox2, new XYConstraints(120, 60, 194, 20));

    jPanel1.add(jLabel9, new XYConstraints(2, 82, 116, 20));
    jPanel1.add(jLabel11, new XYConstraints(2, 96, 116, 20));
    jPanel1.add(jComboBox4, new XYConstraints(120, 89, 194, 20));

    jPanel1.add(jLabel8, new XYConstraints(2, 118, 116, 20));
    jPanel1.add(jTextField2, new XYConstraints(120, 118, 93, 20));
    jPanel1.add(jButton1, new XYConstraints(219, 118, 45, 20));
    jPanel1.add(jButton4, new XYConstraints(269, 118, 45, 20));

    jPanel1.add(jLabel12, new XYConstraints(2, 147, 116, 20));
    jPanel1.add(jTextField4, new XYConstraints(120, 147, 93, 20));
    jPanel1.add(jButton2, new XYConstraints(219, 147, 45, 20));
    jPanel1.add(jButton5, new XYConstraints(269, 147, 45, 20));

    this.add(jLabel16,   new XYConstraints(20, 335, 75, 20));
    this.add(jPanel3,   new XYConstraints(154, 335, 328, 66));
    jPanel3.add(jCheckBox1,   new XYConstraints(2, 2, 200, 20));
    jPanel3.add(jLabel17,  new XYConstraints(20, 31, 85, 20));
    jPanel3.add(jTextField9,  new XYConstraints(120, 31, 194, 20));

    this.add(starting_points,       new XYConstraints(380, 487, 95, 20));
    this.add(jLabel10,                 new XYConstraints(20, 487, 75, 20));
    this.add(epsilon,                      new XYConstraints(155, 487, 95, 20));
    this.add(jLabel3,                    new XYConstraints(260, 487, 85, 20));
    this.add(jPanel2,         new XYConstraints(155, 411, 328, 66));
    jPanel2.add(jLabel14,   new XYConstraints(20, 31, 85, 20));
    jPanel2.add(jTextField5,    new XYConstraints(120, 31, 194, 20));
    jPanel2.add(jComboBox3,  new XYConstraints(2, 2, 160, 20));
    this.add(jLabel13,            new XYConstraints(20, 411, 75, 20));
    this.add(jLabel2,    new XYConstraints(20, 140, 75, 20));
    this.add(jPanel1,      new XYConstraints(155, 140, 328, 185));

    jComboBox2.addItemListener(this);

    title.getDocument().addDocumentListener(this);
    starting_points.getDocument().addDocumentListener(this);
    epsilon.getDocument().addDocumentListener(this);

    Datamodel.setValue("Title", title.getText());
    Datamodel.setValue("starting_points", starting_points.getText());
    Datamodel.setValue("epsilon", epsilon.getText());
    Datamodel.setValue("dump_cutoff", jTextField5.getText());
    Datamodel.setValue("maf_threshold", jTextField9.getText());
    Datamodel.setValue("analysis_unit", analysis_unit_real[jComboBox1.getSelectedIndex()]);
    jTextField5.getDocument().addDocumentListener(this);
    jTextField9.getDocument().addDocumentListener(this);

    jCheckBox1.addItemListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton)
      jRunButton_actionPerformed(e);
    else if (ob == jButton1) {
      dialog1.showDialog();
    }
    else if (ob == jButton2) {
      dialog2.showDialog();
    }
    else if (ob == jButton3) {
      dialog3.showDialog();
    }
    else if (ob == jButton4) {
      if(Datamodel.hasValue("partition1"))
      {
        jTextField2.setText("");
        jLabel8.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("partition1");
        dialog1.listModel.removeAllElements();
      }
    }
    else if (ob == jButton5) {
      if(Datamodel.hasValue("partition2"))
      {
        jTextField4.setText("");
        jLabel12.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("partition2");
        dialog2.listModel.removeAllElements();
      }
    }
    else if (ob == jButton6) {
      if(Datamodel.hasValue("tasks"))
      {
        jTextField1.setText("");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("tasks");
      }
    }
    else if (ob == jButton7) {//pools define
      dialog4.showDialog();
    }
    else if (ob == jButton8) {//pools reset
      if(Datamodel.hasValue("pools_locus"))
      {
        jTextField3.setText("");
        jLabel15.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("pools_locus");
      }
    }
    else if (ob == jButton9) {//region define
      dialog5.showDialog();
    }
    else if (ob == jButton10) {//region reset
      if(Datamodel.hasValue("region"))
      {
        jTextField7.setText("");
        jLabel5.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("region");
      }
    }
    else if (ob == jButton11) {//blocks define
      dialog6.showDialog();
    }
    else if (ob == jButton12) {//blocks reset
      if(Datamodel.hasValue("blocks_info"))
      {
        jTextField8.setText("");
        jLabel18.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("blocks_info");
      }
    }
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) decipher1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunDECIPHER(treenode, currentnode, e.getActionCommand());
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == title.getDocument()) {
      Datamodel.setValue("Title", title.getText());
      if (title.getText().length() <= 0)
        if(Datamodel.hasValue("Title"))
          Datamodel.removeValue("Title");
    }
    if (document == starting_points.getDocument()) {
      Datamodel.setValue("starting_points", starting_points.getText());
      if (starting_points.getText().length() <= 0)
        if(Datamodel.hasValue("starting_points"))
          Datamodel.removeValue("starting_points");
    }
    if (document == epsilon.getDocument()) {
      Datamodel.setValue("epsilon", epsilon.getText());
      if (epsilon.getText().length() <= 0)
        if(Datamodel.hasValue("epsilon"))
          Datamodel.removeValue("epsilon");
    }
    if (document == jTextField5.getDocument()) {
      Datamodel.setValue("dump_cutoff", jTextField5.getText());
      if (jTextField5.getText().length() <= 0)
        if(Datamodel.hasValue("dump_cutoff"))
          Datamodel.removeValue("dump_cutoff");
    }
    if (document == jTextField9.getDocument()) {
      Datamodel.setValue("maf_threshold", jTextField9.getText());
      if (jTextField9.getText().length() <= 0)
        Datamodel.removeValue("maf_threshold");
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

    if(ob == jCheckBox1)
    {
      if(jCheckBox1.isSelected())//need locus file
      {
        if(decipher1.locus_node==null)
        {
          String block = ((NodeInfo)decipher1.analysis_node.getUserObject()).analysis_block;
          NodeInfo errornodeinfo = new NodeInfo("Marker Locus File Missing ", "ErrorLocusNode" ,"DECIPHER", decipher1.Analysis_object, block);
          IconNode error1 = new IconNode(errornodeinfo, "ErrorLocusNode");
          decipher1.treeModel.insertNodeInto(error1, decipher1.errorF_node, decipher1.errorF_node.getChildCount());
          decipher1.errorlocus_node = error1;
          Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
        }
        Datamodel.setValue("maf_filter", "true");
        jLabel17.setEnabled(true);
        jTextField9.setEnabled(true);
      }
      else
      {
        if(decipher1.errorlocus_node!=null)
        {
          decipher1.errorlocus_node.removeFromParent();
          decipher1.treeModel.nodeStructureChanged(decipher1.errorF_node);
          Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
          decipher1.errorlocus_node = null;
        }
        Datamodel.setValue("maf_filter", "false");
        jLabel17.setEnabled(false);
        jTextField9.setEnabled(false);
      }
    }

    else if(ob == jComboBox1)
    {
      if(jComboBox1.getSelectedIndex()==3)//pool
      {
        jLabel15.setForeground(Color.red);

        //dump
        jComboBox3.setEnabled(false);
        jLabel14.setEnabled(false);
        jTextField5.setEnabled(false);

        //blocks
        dialog6.jCheckBox1.setEnabled(false);
        dialog6.jLabel1.setEnabled(false);
        dialog6.jTextField1.setEnabled(false);

        if(Datamodel.hasValue("sliding_window"))
          Datamodel.removeValue("sliding_window");

        //fileters
        jCheckBox1.setEnabled(false);
        jLabel16.setEnabled(false);
        jLabel17.setEnabled(false);
        jTextField9.setEnabled(false);

        //genome file
        if(decipher1.genome_node != null)
        {
          decipher1.jTextFieldGenome.setText("");
          decipher1.genome_node.removeFromParent();
          decipher1.treeModel.nodeStructureChanged(decipher1.inputF_node);
          Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
          decipher1.genome_node = null;
          decipher1.Analysis_object.create_genome_file_node = false;
        }

        //genome file
        if(decipher1.locus_node != null)
        {
          decipher1.jTextFieldMarker.setText("");
          decipher1.locus_node.removeFromParent();
          decipher1.treeModel.nodeStructureChanged(decipher1.inputF_node);
          Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
          decipher1.locus_node = null;
          decipher1.Analysis_object.create_locus_file_node = false;
        }
        if(decipher1.errorlocus_node!=null)
        {
          decipher1.errorlocus_node.removeFromParent();
          decipher1.treeModel.nodeStructureChanged(decipher1.errorF_node);
          Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
          decipher1.errorlocus_node = null;
        }

      }
      else
      {
        jLabel15.setForeground(Color.black);

        //dump
        jComboBox3.setEnabled(true);
        if(jComboBox3.isSelected())
        {
          jLabel14.setEnabled(true);
          jTextField5.setEnabled(true);
        }

        //blocks
        dialog6.jCheckBox1.setEnabled(true);
        if(dialog6.jCheckBox1.isSelected())
        {
          dialog6.jLabel1.setEnabled(true);
          dialog6.jTextField1.setEnabled(true);
        }

        //fileters
        jCheckBox1.setEnabled(true);
        jLabel16.setEnabled(true);
        jTextField9.setEnabled(true);
        if(jCheckBox1.isSelected())
          jLabel17.setEnabled(true);

      }
      Datamodel.setValue("analysis_unit", analysis_unit_real[jComboBox1.getSelectedIndex()]);
    }
    else if(ob == jComboBox2)
    {
      jComboBox4.removeAllItems();
      if(jComboBox2.getSelectedIndex()==0)
      {
        jComboBox4.setEnabled(false);
      }
      else
      {
         jComboBox4.setEnabled(true);
         if(jComboBox2.getSelectedItem() != null)
         {
             String selected_fm = jComboBox2.getSelectedItem().toString().trim();
             if(Datamodel.hasValue("pedi_path"))
             {
               setFamilyRepValue(selected_fm);
             }
         }
      }
    }
    else if(ob == jComboBox3)
    {
      if(jComboBox3.isSelected())//true
      {
        jLabel14.setEnabled(true);
        jTextField5.setEnabled(true);
      }
      else//false
      {
        jLabel14.setEnabled(false);
        jTextField5.setEnabled(false);
      }
    }

  }

  void setFamilyRepValue(String value)
  {
    DataCollectionModel paranodemodel = ((NodeInfo) decipher1.para_node.
                                         getUserObject()).infomodel;

    if (paranodemodel.hasValue("delimiters_name") == false) {
      JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                   "You must specify the \"delimiters\" option in the parameter file pedigree block.",
                                   "Error",
                                   JOptionPane.CLOSED_OPTION,
                                   JOptionPane.WARNING_MESSAGE, null, null, null);
      return;
    }

    if (paranodemodel.hasValue("delimiter_mode") == false) {
      JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                   "You must specify the \"delimiter_mode\" option in the parameter file pedigree block.",
                                   "Error",
                                   JOptionPane.CLOSED_OPTION,
                                   JOptionPane.WARNING_MESSAGE, null, null, null);
      return;
    }

    String delimiter = paranodemodel.getValue("delimiters_name").toString();
    String delimiter_mode = paranodemodel.getValue("delimiter_mode").toString();

    String pedipath = Datamodel.getValue("pedi_path").toString();

    boolean isHeaderExist = true;

    Vector column = new Vector();
    int column_size = 0;
    Vector linelist = new Vector();

    if (delimiter_mode.compareTo("single")==0) {
      int i = 0;
      try {
        FileReader fr = new FileReader(pedipath);
        BufferedReader br = new BufferedReader(fr);
        String temp = new String();
        while ((temp = br.readLine()) != null && temp.length() > 0) {
          String[] t = temp.split("\t");
          if (isHeaderExist) {
            if (i == 0) {
              for (int ti = 0; ti < t.length; ti++) {
                column.add(t[ti]);
              }
            } else if (i > 0) {
              Vector eachlinecontents = new Vector();
              for (int ti = 0; ti < t.length; ti++) {
                eachlinecontents.add(t[ti]);
              }
              column_size = eachlinecontents.size();
              linelist.add(eachlinecontents);
            }
            i++;
          } else {
            Vector eachlinecontents = new Vector();
            for (int ti = 0; ti < t.length; ti++) {
              eachlinecontents.add(t[ti]);
            }
            column_size = eachlinecontents.size();
            linelist.add(eachlinecontents);
          }
        }
      } catch (Exception ex) {
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                     "Exception occurred while a file is being read." +
                                     "\nPlease check your file.",
                                     "Error",
                                     JOptionPane.CLOSED_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null, null, null);
        return;
      }
    }

    // multiple - use "StringTokenizer st = new StringTokenizer(temp, delimiter);"
    else if (delimiter_mode.compareTo("multiple")==0) {
      int i = 0;

      try {
        FileReader fr = new FileReader(pedipath);
        BufferedReader br = new BufferedReader(fr);
        String temp = new String();
        while ((temp = br.readLine()) != null && temp.length() > 0) {
          StringTokenizer st = new StringTokenizer(temp, delimiter);
          if (isHeaderExist) {
            if (i == 0) {
              while (st.hasMoreTokens()) {
                column.add(st.nextToken());
              }
            } else if (i > 0) {
              Vector eachlinecontents = new Vector();
              while (st.hasMoreTokens()) {
                eachlinecontents.add(st.nextToken());
              }
              column_size = eachlinecontents.size();
              linelist.add(eachlinecontents);
            }
            i++;
          } else {
            Vector eachlinecontents = new Vector();
            while (st.hasMoreTokens()) {
              eachlinecontents.add(st.nextToken());
            }
            column_size = eachlinecontents.size();
            linelist.add(eachlinecontents);
          }
        }
      } catch (Exception ex) {
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                     "Exception occurred while a file is being read." +
                                     "\nPlease check your file.",
                                     "Error", // title
                                     JOptionPane.CLOSED_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null, null, null);
        return;
      }
    }

    int colindex = -1;
    for(int i=0;i<column.size();i++)
    {
      String pidlist = column.get(i).toString().trim();
      if(pidlist.compareTo(value)==0)
      {
        colindex = i;
        break;
      }
    }

    if(colindex < 0)
    {
      String pedigree_block = new String();
      if(paranodemodel.hasValue("Master_Information"))
      {
        pedigree_block = paranodemodel.getValue("Master_Information").toString();

        StringTokenizer st = new StringTokenizer(pedigree_block, "\n");
        while (st.hasMoreTokens())
        {
          String eachline = st.nextToken();
          if(eachline.indexOf(value)>0 && eachline.indexOf("name")>0)
          {
            int fistequal = eachline.indexOf("=");
            int fistcomma = eachline.indexOf(",");

            String temp_value = eachline.substring(fistequal+1, fistcomma).trim();

            value = temp_value;
            break;
          }
        }
      }

      for(int i=0;i<column.size();i++)
      {
        String pidlist = column.get(i).toString().trim();
        if(pidlist.compareTo(value)==0)
        {
          colindex = i;
          break;
        }
      }
    }

    if (colindex < 0)
    {
      JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                   "The name specified for 'family representative' in parameter file pedigree block"+
                                   "\nmust match the corresponding column name in the data file.",
                                   "Error", // title
                                   JOptionPane.CLOSED_OPTION,
                                   JOptionPane.WARNING_MESSAGE, null, null, null);
      return;
    }

    Vector temp_data_list = new Vector();

    for(int i=0;i<linelist.size();i++)
    {
      Vector templist =(Vector)linelist.get(i);
      String pidlist = templist.get(colindex).toString().trim();
      temp_data_list.add(pidlist);
    }

    Collections.sort(temp_data_list);

    Vector data_list = new Vector();
    String strFileLine = new String();
    String strFileNextLine = new String();

    for(int i=0;i<temp_data_list.size();i++)
    {
      strFileLine = temp_data_list.get(i).toString();
      if (strFileLine.compareTo(strFileNextLine) != 0) {
        data_list.add(strFileLine);
      }
      strFileNextLine = strFileLine;
    }

    jComboBox4.addItem("");
    for(int i=0;i<data_list.size();i++)
    {
      strFileLine = data_list.get(i).toString();
      jComboBox4.addItem(strFileLine);
    }
  }

  void title_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("titl", false, 403);
  }

  void jTextField7_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("region", false, 403);
  }

  void epsilon_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("epsilo", false, 403);
  }

  void starting_points_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("starting_points", false, 403);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("dum", false, 404);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("cutof", false, 404);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("maf_filter", false, 405);
  }

  void jTextField9_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("threshold", false, 405);
  }

  void jTextField8_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("blocks", false, 405);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("tasks", false, 404);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("analysis_uni", false, 409);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("family_rep ", false, 409);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("family_rep_valu", false, 409);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("partition", false, 409);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("partition", false, 409);
 }

 void jTextField3_mouseClicked(MouseEvent e) {
   Frame1.mainFrame1.pdfframe.setTextonPage("pool", false, 410);
  }
}

class DECIPHER2_title_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_title_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.title_mouseClicked(e);
  }
}

class DECIPHER2_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jComboBox1_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class DECIPHER2_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jComboBox2_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class DECIPHER2_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jTextField2_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class DECIPHER2_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jTextField4_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class DECIPHER2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jTextField1_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class DECIPHER2_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jTextField3_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class DECIPHER2_epsilon_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_epsilon_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.epsilon_mouseClicked(e);
  }
}

class DECIPHER2_starting_points_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_starting_points_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.starting_points_mouseClicked(e);
  }
}

class DECIPHER2_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jComboBox4_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class DECIPHER2_jTextField7_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jTextField7_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField7_mouseClicked(e);
  }
}

class DECIPHER2_jTextField8_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jTextField8_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField8_mouseClicked(e);
  }
}

class DECIPHER2_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jComboBox3_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class DECIPHER2_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jTextField5_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class DECIPHER2_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jCheckBox1_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class DECIPHER2_jTextField9_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER2 adaptee;

  DECIPHER2_jTextField9_mouseAdapter(DECIPHER2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField9_mouseClicked(e);
  }
}






