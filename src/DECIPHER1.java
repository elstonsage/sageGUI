package sage;

import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.tree.*;
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

public class DECIPHER1
    extends SageFilePanel
    implements DocumentListener, ActionListener {
  IconNode analysis_node;
  IconNode errorF_node;
  IconNode errorpedigree_node;
  IconNode errorlocus_node;
  IconNode pedi_node;
  IconNode locus_node;
  IconNode genome_node;

  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabelGenome = new JLabel();
  JTextField jTextFieldGenome = new JTextField();
  JButton jButtonGenome = new JButton();

  JLabel jLabelMarker = new JLabel();
  JTextField jTextFieldMarker = new JTextField();
  JButton jButtonMarker = new JButton();

  public DECIPHER1(sage_analysis_info data, IconNode inputnode,
                 IconNode errornode) {
    Analysis_object = data;
    analysis_node = inputnode;
    errorF_node = errornode;
    setModel(new PropertyDataModel());
    Analysis_object.create_error_folder= true;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void setModel(DataCollectionModel model) {
    this.Datamodel = model;
    Datamodel.setValue("output_name", OutputNameField.getText());
  }

  void jbInit() throws Exception {
    NodeInfo outputfolder = new NodeInfo("Output", "OutputFolder", "DECIPHER",
                                         Analysis_object, "");
    outputF_node = new IconNode(outputfolder, "OutputFolder");


    this.setLayout(xYLayout1);
    jTextFieldPara.addMouseListener(new DECIPHER1_jTextField1_mouseAdapter(this));
    jTextFieldPed.addMouseListener(new DECIPHER1_jTextField2_mouseAdapter(this));
    jButtonPara.addActionListener(this);
    jButtonPed.addActionListener(this);

    jLabelMarker.setToolTipText(
        "<html>Lists the alleles, allele freqeuncies and phenotype to genotype " +
        "<br>mapping for each marker locus. This file is irrelevant and need " +
        "<br>not be given if the parameter <i>analysis_unit</i> in the <i>data</i> sub-block"+
        "<br>of the parameter file equals <b>pool</b>.");
    jLabelMarker.setText("Marker locus file (optional)");
    jTextFieldMarker.setText("");
    jTextFieldMarker.addMouseListener(new DECIPHER1_jTextField3_mouseAdapter(this));
    jButtonMarker.setText("...");
    jButtonMarker.addActionListener(this);

    jLabelGenome.setText("Genome file (optional)");
    jLabelGenome.setToolTipText(
        "<html>Contains a description of the linked marker regions, including " +
        "<br>distances between markers. This file is irrelevant and need " +
        "<br>not be given if the parameter <i>analysis_unit</i> in the <i>data</i> sub-block"+
        "<br>of the parameter file equals <b>pool</b>.");
    jTextFieldGenome.setText("");
    jTextFieldGenome.addMouseListener(new DECIPHER1_jTextField4_mouseAdapter(this));
    jButtonGenome.setText("...");
    jButtonGenome.addActionListener(this);

    OutputNameField.addMouseListener(new DECIPHER1_OutputNameField_mouseAdapter(this));
    OutputNameField.setText("decipher");

    this.add(jLabelPara, new XYConstraints(20, 20, 142, 20));
    this.add(jLabelPed, new XYConstraints(20, 50, 142, 20));
    this.add(jLabelGenome,      new XYConstraints(20, 110, 142, 20));
    this.add(jTextFieldPara,  new XYConstraints(155, 20, 280, 20));
    this.add(jTextFieldPed,  new XYConstraints(155, 50, 280, 20));
    this.add(jButtonPed,  new XYConstraints(445, 50, 30, 20));
    this.add(jButtonPara,  new XYConstraints(445, 20, 30, 20));
    this.add(jTextFieldGenome,    new XYConstraints(155, 110, 280, 20));
    this.add(jButtonGenome,    new XYConstraints(445, 110, 30, 20));
    this.add(jLabelOutputName,   new XYConstraints(20, 150, 142, 20));
    this.add(OutputNameField, new XYConstraints(155, 150, 280, 20));
    this.add(jNextButton, new XYConstraints(420, 520, 60, 25));
    this.add(jLabelMarker,      new XYConstraints(20, 80, 142, 20));
    this.add(jTextFieldMarker,      new XYConstraints(155, 80, 280, 20));
    this.add(jButtonMarker,       new XYConstraints(445, 80, 30, 20));

    jTextFieldPara.getDocument().addDocumentListener(this);
    jTextFieldPed.getDocument().addDocumentListener(this);
    jTextFieldGenome.getDocument().addDocumentListener(this);
    OutputNameField.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jButtonPed)
      jButton2_actionPerformed();
    else if (ob == jButtonMarker)
      jButton3_actionPerformed();
    else if (ob == jButtonGenome)
      jButton4_actionPerformed();
    else if (ob == jNextButton)
      jNextButton_actionPerformed();
  }

  void jNextButton_actionPerformed() {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    mf.jTabbedPane1.setSelectedIndex(1);
  }

  void jButton1_actionPerformed() {
    jFileChooser1.setCurrentDirectory(new File(Frame1.mainFrame1.path_forFileChooser));
    jFileChooser1.setFileFilter(ParaFilter);
    jFileChooser1.setDialogTitle("Add Parameter File");

    if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {
      String filepath = jFileChooser1.getSelectedFile().getPath();
      String filename = jFileChooser1.getSelectedFile().getName();
      NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
      insertparafile(filenode);
    }
  }

  public void insertparafile(NodeInfo source) {
    File importFile = (File) source.file;
    String FilePath = importFile.getPath();
    Datamodel.setValue("para_path", FilePath);
    jTextFieldPara.setText(FilePath);

    jFileChooser1.setCurrentDirectory(importFile);
    Frame1.mainFrame1.path_forFileChooser = FilePath;

    Analysis_object.para_file_path = FilePath;
    Analysis_object.input_file.add(FilePath);

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "DECIPHER",
                                       Analysis_object, "");
      IconNode node = new IconNode(nodeinfo, "InputFolder");

      inputF_node = addObject(node, analysis_node, false);
      Analysis_object.create_input_folder = true;
    }
    if (Analysis_object.create_para_file_node == false) {
      para_node = new IconNode(source, "Parameter File");

      addObject(para_node, inputF_node, false);
      Analysis_object.create_para_file_node = true;

      treeModel.nodeStructureChanged(errorF_node);

      if (errorF_node.getChildCount() < 1) {
          Analysis_object.create_error_folder= false;

        errorF_node.removeFromParent();
        treeModel.nodeStructureChanged(analysis_node);
        TreePath p = new TreePath(para_node.getPath());
        Frame1.mainFrame1.activeinframe.MyTree1.scrollPathToVisible(p);

        NodeInfo n = (NodeInfo) analysis_node.getUserObject();
        DECIPHER2 f2 = (DECIPHER2) n.component_vector.get(1);
        f2.jRunButton.setIcon(next_image);
      }
    }
    else {
      para_node.setUserObject(source);
      TreePath p = new TreePath(para_node.getPath());
      Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    }
    TreePath p = new TreePath(analysis_node.getPath());
    Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);

    p = new TreePath(para_node.getPath());
    Frame1.mainFrame1.activeinframe.MyTree1.scrollPathToVisible(p);
    Frame1.mainFrame1.activeinframe.MyTree1.Refresh();

    SetPanel2Info(source);
  }

  void jButton2_actionPerformed() {
    jFileChooser1.setFileFilter(FamilyFilter);
    jFileChooser1.setDialogTitle("Add Data File");

    if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {
      String filepath = jFileChooser1.getSelectedFile().getPath();
      String filename = jFileChooser1.getSelectedFile().getName();
      NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
      insertpedigreefile(filenode);
    }
  }

  public void insertpedigreefile(NodeInfo source) {
    File importFile = (File) source.file;
    String FilePath = importFile.getPath();
    Datamodel.setValue("pedi_path", FilePath);
    jTextFieldPed.setText(FilePath);

    jFileChooser1.setCurrentDirectory(importFile);
    Frame1.mainFrame1.path_forFileChooser = FilePath;

    Analysis_object.family_file_path = FilePath;
    Analysis_object.input_file.add(FilePath);

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "DECIPHER",
                                       Analysis_object, "");
      IconNode node = new IconNode(nodeinfo, "InputFolder");

      inputF_node = addObject(node, analysis_node, false);
      Analysis_object.create_input_folder = true;
    }
    if (Analysis_object.create_family_file_node == false) {
      pedi_node = new IconNode(source, "Pedigree File");

      addObject(pedi_node, inputF_node, false);
      Analysis_object.create_family_file_node = true;

      errorpedigree_node.removeFromParent();
      treeModel.nodeStructureChanged(errorF_node);

      if (errorF_node.getChildCount() < 1) {
          Analysis_object.create_error_folder= false;

        errorF_node.removeFromParent();
        treeModel.nodeStructureChanged(analysis_node);
        TreePath p = new TreePath(pedi_node.getPath());
        Frame1.mainFrame1.activeinframe.MyTree1.scrollPathToVisible(p);

        NodeInfo n = (NodeInfo) analysis_node.getUserObject();
        DECIPHER2 f2 = (DECIPHER2) n.component_vector.get(1);
        f2.jRunButton.setIcon(next_image);
      }
    }
    else {
      pedi_node.setUserObject(source);
      TreePath p = new TreePath(pedi_node.getPath());
      Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    }

    TreePath p = new TreePath(analysis_node.getPath());
    Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    Frame1.mainFrame1.activeinframe.MyTree1.Refresh();

    SetMarkerList(source);
    SetCovariateList(source);
  }

  void SetCovariateList(NodeInfo source)
{
    NodeInfo n = (NodeInfo) analysis_node.getUserObject();
    DECIPHER2 f2 = (DECIPHER2) n.component_vector.get(1);

    NodeInfo para = (NodeInfo)para_node.getUserObject();
    DataCollectionModel dm = (DataCollectionModel) para.infomodel;

    try {
        ArrayList list = GetCovariateList(dm, (File) source.file, true);
        int list_size = list.size();

        if (list.size()>0)
        {
            for (int i = 0; i<list_size; i++)
            {
                String temp = list.get(i).toString();

                f2.jComboBox2.addItem(temp);
                f2.dialog1.jComboBox1.addItem(temp);
                f2.dialog2.jComboBox1.addItem(temp);
                f2.dialog4.jComboBox1.addItem(temp);
                f2.dialog4.dialog.jComboBox1.addItem(temp);
            }
        }

    } catch (Exception exe) {
        exe.printStackTrace();
    }
}


  void SetMarkerList(NodeInfo source)
  {
      NodeInfo n = (NodeInfo) analysis_node.getUserObject();
      DECIPHER2 f2 = (DECIPHER2) n.component_vector.get(1);

      NodeInfo para = (NodeInfo)para_node.getUserObject();
      DataCollectionModel dm = (DataCollectionModel) para.infomodel;

      try {
          ArrayList list = GetMarkerList(dm, (File) source.file, true);

          if (list.size()>0)
          {
              for (int i = 0; i<list.size(); i++)
              {
                  String temp = list.get(i).toString();
                  f2.dialog5.jComboBox2.addItem(temp);
                  f2.dialog5.jComboBox3.addItem(temp);
              }
          }
      } catch (Exception exe) {
          exe.printStackTrace();
      }
  }

  void jButton3_actionPerformed() {
    jFileChooser1.setFileFilter(MarkerLocusFilter);
    jFileChooser1.setDialogTitle("Add Locus File");

    if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {
      String filepath = jFileChooser1.getSelectedFile().getPath();
      String filename = jFileChooser1.getSelectedFile().getName();
      NodeInfo filenode = new NodeInfo(filename, "Marker Locus File", new File(filepath));
      insertlocusfile(filenode);
    }
  }

  public void insertlocusfile(NodeInfo source) {
    File importFile = (File) source.file;
    String FilePath = importFile.getPath();
    Datamodel.setValue("locus_path", FilePath);
    jTextFieldMarker.setText(FilePath);

    jFileChooser1.setCurrentDirectory(importFile);
    Frame1.mainFrame1.path_forFileChooser = FilePath;

    Analysis_object.locus_file_path = FilePath;
    Analysis_object.input_file.add(FilePath);

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "DECIPHER",
                                       Analysis_object, "");
      IconNode node = new IconNode(nodeinfo, "InputFolder");

      inputF_node = addObject(node, analysis_node, false);
      Analysis_object.create_input_folder = true;
    }
    if (Analysis_object.create_locus_file_node == false) {
      locus_node = new IconNode(source, "Marker Locus File");

      addObject(locus_node, inputF_node, false);
      Analysis_object.create_locus_file_node = true;

      if(errorlocus_node!=null)
      {
        errorlocus_node.removeFromParent();
        treeModel.nodeStructureChanged(errorF_node);
      }

      if (errorF_node.getChildCount() < 1) {
        errorF_node.removeFromParent();
        treeModel.nodeStructureChanged(analysis_node);
        TreePath p = new TreePath(locus_node.getPath());
        Frame1.mainFrame1.activeinframe.MyTree1.scrollPathToVisible(p);

        NodeInfo n = (NodeInfo) analysis_node.getUserObject();
        DECIPHER2 f2 = (DECIPHER2) n.component_vector.get(1);
        f2.jRunButton.setIcon(new ImageIcon(DECIPHER1.class.getResource(
            "next.png")));
      }
    }
    else {
      locus_node.setUserObject(source);
      TreePath p = new TreePath(locus_node.getPath());
      Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    }

    TreePath p = new TreePath(analysis_node.getPath());
    Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
  }

  void jButton4_actionPerformed() {
    jFileChooser1.setFileFilter(GenomeFilter);
    jFileChooser1.setDialogTitle("Add Genome File");

    if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {
      String filepath = jFileChooser1.getSelectedFile().getPath();
      String filename = jFileChooser1.getSelectedFile().getName();
      NodeInfo filenode = new NodeInfo(filename, "Genome File", new File(filepath));
      insertgenomefile(filenode);
    }
  }

  public void insertgenomefile(NodeInfo source) {
    File importFile = (File) source.file;
    String FilePath = importFile.getPath();
    Datamodel.setValue("genome_path", FilePath);
    jTextFieldGenome.setText(FilePath);

    jFileChooser1.setCurrentDirectory(importFile);
    Frame1.mainFrame1.path_forFileChooser = FilePath;

    Analysis_object.genome_file_path = FilePath;
    Analysis_object.input_file.add(FilePath);

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "DECIPHER",
                                       Analysis_object, "");
      IconNode node = new IconNode(nodeinfo, "InputFolder");

      inputF_node = addObject(node, analysis_node, false);
      Analysis_object.create_input_folder = true;
    }
    if (Analysis_object.create_genome_file_node == false) {
      genome_node = new IconNode(source, "Genome File");

      addObject(genome_node, inputF_node, false);
      Analysis_object.create_genome_file_node = true;

      if (errorF_node.getChildCount() < 1) {
        Analysis_object.create_error_folder= false;

        errorF_node.removeFromParent();
        treeModel.nodeStructureChanged(analysis_node);
        TreePath p = new TreePath(genome_node.getPath());
        Frame1.mainFrame1.activeinframe.MyTree1.scrollPathToVisible(p);

        NodeInfo n = (NodeInfo) analysis_node.getUserObject();
        DECIPHER2 f2 = (DECIPHER2) n.component_vector.get(1);
        f2.jRunButton.setIcon(next_image);
      }
    }
    else {
      genome_node.setUserObject(source);
      TreePath p = new TreePath(genome_node.getPath());
      Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    }
    TreePath p = new TreePath(analysis_node.getPath());
    Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
    SetDECIPHER2RegionInfo(source);
  }

  void SetPanel2Info(NodeInfo source) {
    NodeInfo n = (NodeInfo) analysis_node.getUserObject();
    DECIPHER2 f2 = (DECIPHER2) n.component_vector.get(1);

    DataCollectionModel dm = (DataCollectionModel) source.infomodel;

    f2.jComboBox2.removeAllItems();
    f2.dialog1.jComboBox1.removeAllItems();
    f2.dialog2.jComboBox1.removeAllItems();
    f2.dialog4.jComboBox1.removeAllItems();
    f2.dialog4.dialog.jComboBox1.removeAllItems();
    f2.dialog5.jComboBox2.removeAllItems();
    f2.dialog5.jComboBox3.removeAllItems();

    f2.jComboBox2.addItem("");
    f2.dialog1.jComboBox1.addItem("");
    f2.dialog2.jComboBox1.addItem("");
    f2.dialog4.jComboBox1.addItem("");
    f2.dialog4.dialog.jComboBox1.addItem("");

    f2.dialog5.jComboBox2.addItem("");
    f2.dialog5.jComboBox3.addItem("");

    if (dm.hasValue("Trait_array")) {
      Vector Trait_array = (Vector) dm.getValue("Trait_array");
      int i = 0;
      for (Enumeration e = Trait_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.jComboBox2.addItem(temp);
        f2.dialog1.jComboBox1.addItem(temp);
        f2.dialog2.jComboBox1.addItem(temp);
        f2.dialog4.jComboBox1.addItem(temp);
        f2.dialog4.dialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Covariate_array")) {
      Vector Covariate_array = (Vector) dm.getValue("Covariate_array");
      int i = 0;
      for (Enumeration e = Covariate_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.jComboBox2.addItem(temp);
        f2.dialog1.jComboBox1.addItem(temp);
        f2.dialog2.jComboBox1.addItem(temp);
        f2.dialog4.jComboBox1.addItem(temp);
        f2.dialog4.dialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Phenotype_array")) {
      Vector Phenotype_array = (Vector) dm.getValue("Phenotype_array");
      int i = 0;
      for (Enumeration e = Phenotype_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.jComboBox2.addItem(temp);
        f2.dialog1.jComboBox1.addItem(temp);
        f2.dialog2.jComboBox1.addItem(temp);
        f2.dialog4.jComboBox1.addItem(temp);
        f2.dialog4.dialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Trait_Farray")) {
      Vector Trait_array = (Vector) dm.getValue("Trait_Farray");
      int i = 0;
      for (Enumeration e = Trait_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.jComboBox2.addItem(temp);
        f2.dialog1.jComboBox1.addItem(temp);
        f2.dialog2.jComboBox1.addItem(temp);
        f2.dialog4.jComboBox1.addItem(temp);
        f2.dialog4.dialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Covariate_Farray")) {
      Vector Covariate_array = (Vector) dm.getValue("Covariate_Farray");
      int i = 0;
      for (Enumeration e = Covariate_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.jComboBox2.addItem(temp);
        f2.dialog1.jComboBox1.addItem(temp);
        f2.dialog2.jComboBox1.addItem(temp);
        f2.dialog4.jComboBox1.addItem(temp);
        f2.dialog4.dialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Phenotype_Farray")) {
      Vector Phenotype_array = (Vector) dm.getValue("Phenotype_Farray");
      int i = 0;
      for (Enumeration e = Phenotype_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.jComboBox2.addItem(temp);
        f2.dialog1.jComboBox1.addItem(temp);
        f2.dialog2.jComboBox1.addItem(temp);
        f2.dialog4.jComboBox1.addItem(temp);
        f2.dialog4.dialog.jComboBox1.addItem(temp);
      }
    }
    if (dm.hasValue("String_array")) {
      Vector String_array = (Vector) dm.getValue("String_array");
      int i = 0;
      for (Enumeration e = String_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.jComboBox2.addItem(temp);
        f2.dialog1.jComboBox1.addItem(temp);
        f2.dialog2.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Marker_array") || dm.hasValue("Allele_array"))
    {
      if (dm.hasValue("Marker_array")) {
        Vector Marker_array = (Vector) dm.getValue("Marker_array");
        for (Enumeration e = Marker_array.elements(); e.hasMoreElements();) {
          String temp = e.nextElement().toString();
          f2.dialog5.jComboBox2.addItem(temp);
          f2.dialog5.jComboBox3.addItem(temp);
        }
      }

      if(dm.hasValue("Allele_array"))
      {
        Vector Allele_array = (Vector) dm.getValue("Allele_array");
        for (Enumeration e = Allele_array.elements(); e.hasMoreElements(); ) {
          String temp = e.nextElement().toString();
          f2.dialog5.jComboBox2.addItem(temp);
          f2.dialog5.jComboBox3.addItem(temp);
        }
      }
    }
  }

  void SetDECIPHER2RegionInfo(NodeInfo source) {
    NodeInfo n = (NodeInfo) analysis_node.getUserObject();
    DECIPHER2 f2 = (DECIPHER2) n.component_vector.get(1);

    if (source.infomodel == null) {
      FavoritesPanel.Parse_Genome_File(source);
    }

    DataCollectionModel dm = (DataCollectionModel) source.infomodel;

    if (dm.hasValue("Region_List")) {
      Vector v = (Vector) dm.getValue("Region_List");
      for (Enumeration e = v.elements(); e.hasMoreElements();) {
        String temp = e.nextElement().toString().trim();
        f2.dialog5.jComboBox1.addItem(temp);
      }
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextFieldPara.getDocument()) {
      Datamodel.setValue("para_path", jTextFieldPara.getText());
    }
    if (document == jTextFieldPed.getDocument()) {
        if(jTextFieldPed.getText().length()>0)
        {
            Datamodel.setValue("pedi_path", jTextFieldPed.getText());
        }
        else
        {
            if(Datamodel.hasValue("pedi_path"))
                Datamodel.removeValue("pedi_path");
      }
    }
    if (document == jTextFieldGenome.getDocument()) {
      if(jTextFieldGenome.getText().length()>0)
      {
          Datamodel.setValue("genome_path", jTextFieldGenome.getText());
      }
      else
      {
          if(Datamodel.hasValue("genome_path"))
              Datamodel.removeValue("genome_path");
      }

    }
    if (document == OutputNameField.getDocument()) {
      Datamodel.setValue("output_name", OutputNameField.getText());
    }

    if (Datamodel.hasValue("para_path") && Datamodel.hasValue("pedi_path")
        && Datamodel.hasValue("genome_path") && Datamodel.hasValue("output_name")) {
      jNextButton.setIcon(next_image);
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  void detelePedNode()
  {
    if(errorpedigree_node != null && errorF_node != null)
    {
        addObject(errorpedigree_node, errorF_node, false);
        if(!Analysis_object.create_error_folder)
        {
            treeModel.insertNodeInto(errorF_node, analysis_node, 0);
            Analysis_object.create_error_folder = true;
        }
        Analysis_object.create_family_file_node = false;
        TreePath p = new TreePath(errorpedigree_node.getPath());
        Frame1.mainFrame1.activeinframe.MyTree1.scrollPathToVisible(p);
    }
  }

  void jTextField1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("parameter fil", false, 401);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("pedigree data fil", false, 401);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("Genome description fil", false, 401);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("marker locus description fil", false, 401);
  }

  void OutputNameField_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("out ", false, 402);
  }


}

class DECIPHER1_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER1 adaptee;

  DECIPHER1_jTextField1_mouseAdapter(DECIPHER1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class DECIPHER1_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER1 adaptee;

  DECIPHER1_jTextField2_mouseAdapter(DECIPHER1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class DECIPHER1_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER1 adaptee;

  DECIPHER1_jTextField4_mouseAdapter(DECIPHER1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class DECIPHER1_OutputNameField_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER1 adaptee;

  DECIPHER1_OutputNameField_mouseAdapter(DECIPHER1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.OutputNameField_mouseClicked(e);
  }
}

class DECIPHER1_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER1 adaptee;

  DECIPHER1_jTextField3_mouseAdapter(DECIPHER1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}


