package sage;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.*;
import java.util.*;
import com.borland.jbcl.layout.*;
/**
 * <p>Title: NEW GUI for SAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author suna
 * @version 1.0
 */

public class ASSOC1
    extends SageFilePanel
    implements DocumentListener, Serializable, ActionListener {
  IconNode analysis_node;
  IconNode errorF_node;
  IconNode errorpedigree_node;
  IconNode pedi_node;
  IconNode marker_node;

  BorderLayout borderLayout2 = new BorderLayout();
  XYLayout xYLayout1 = new XYLayout();

  JLabel jLabelMarker = new JLabel();
  JTextField jTextFieldMarker = new JTextField();
  JButton jButtonMarker = new JButton();
  JLabel jLabelMarker2 = new JLabel();

  public ASSOC1(sage_analysis_info data, IconNode inputnode, IconNode errornode) {
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

    this.setLayout(xYLayout1);

    Datamodel.setValue("output_name", "assoc");
    NodeInfo outputfolder = new NodeInfo("Output", "OutputFolder", "ASSOC", Analysis_object, "");
    outputF_node = new IconNode(outputfolder, "OutputFolder");

    jTextFieldPara.addMouseListener(new ASSOC1_jTextField1_mouseAdapter(this));
    jTextFieldPed.addMouseListener(new ASSOC1_jTextField2_mouseAdapter(this));

    jButtonPed.addActionListener(this);
    OutputNameField.addMouseListener(new ASSOC1_OutputNameField_mouseAdapter(this));

    OutputNameField.setText("assoc");

    jNextButton.addActionListener(this);
    jLabelMarker.setText("Marker locus file");
    jLabelMarker.setToolTipText(
        "<html>Lists the alleles at each marker locus. This file will be used by " +
        "<br>ASSOC only if a marker, transformed to be quantitative, is used as " +
        "<br>a covariate.");

    jButtonMarker.setToolTipText("Lists the alleles at each marker locus.");
    jButtonMarker.setText("...");
    jButtonMarker.addActionListener(this);
    jTextFieldMarker.setText("");
    jTextFieldMarker.addMouseListener(new ASSOC1_jTextField3_mouseAdapter(this));
    jLabelMarker2.setToolTipText(
        "<html>If you do not have codominant markers, or have genotypes coded as " +
        "<br>phenotypes than you must provide a locus description file.");
    jLabelMarker2.setText("(optional)");

    this.add(jLabelOutputName,  new XYConstraints(20, 120, 99, 20));
    this.add(jButtonPed,   new XYConstraints(445, 50, 30, 20));
    this.add(jLabelPed, new XYConstraints(20, 50, 99, 20));
    this.add(jTextFieldPed,    new XYConstraints(155, 50, 280, 20));
    this.add(jButtonPara,    new XYConstraints(445, 20, 30, 20));
    this.add(jLabelPara, new XYConstraints(20, 20, 99, 20));
    this.add(jTextFieldPara,    new XYConstraints(155, 20, 280, 20));
    this.add(OutputNameField, new XYConstraints(155, 120, 280, 20));
    this.add(jNextButton, new XYConstraints(420, 520, 60, 25));
    this.add(jLabelMarker,        new XYConstraints(20, 80, 82, 20));
    this.add(jTextFieldMarker,         new XYConstraints(155, 80, 280, 20));
    this.add(jButtonMarker,        new XYConstraints(445, 80, 30, 20));
    this.add(jLabelMarker2,        new XYConstraints(102, 80, 55, 20));

    jTextFieldPara.getDocument().addDocumentListener(this);
    jTextFieldPed.getDocument().addDocumentListener(this);
    jTextFieldMarker.getDocument().addDocumentListener(this);
    OutputNameField.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jButtonPed)
      jButton2_actionPerformed();
    else if (ob == jButtonMarker)
      jButton3_actionPerformed();
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

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "ASSOC",
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
        ASSOC2 f2 = (ASSOC2) n.component_vector.get(1);
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

  void SetPanel2Info(NodeInfo source) {
    NodeInfo n = (NodeInfo) analysis_node.getUserObject();
    ASSOC2 f2 = (ASSOC2) n.component_vector.get(1);

    DataCollectionModel dm = (DataCollectionModel) source.infomodel;

    f2.traitComboBox.removeAllItems();
    f2.covDialog.jComboBox1.removeAllItems();
    f2.CEDialog.jComboBox1.removeAllItems();

    f2.traitComboBox.addItem("");
    f2.covDialog.jComboBox1.addItem("");
    f2.CEDialog.jComboBox1.addItem("");

    if (dm.hasValue("Trait_array")) {
      Vector Trait_array = (Vector) dm.getValue("Trait_array");
      int i = 0;
      for (Enumeration e = Trait_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.traitComboBox.addItem(temp);
        f2.covDialog.jComboBox1.addItem(temp);
        f2.CEDialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Covariate_array")) {
      Vector Covariate_array = (Vector) dm.getValue("Covariate_array");
      int i = 0;
      for (Enumeration e = Covariate_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.traitComboBox.addItem(temp);
        f2.covDialog.jComboBox1.addItem(temp);
        f2.CEDialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Phenotype_array")) {
      Vector Phenotype_array = (Vector) dm.getValue("Phenotype_array");
      int i = 0;
      for (Enumeration e = Phenotype_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.traitComboBox.addItem(temp);
        f2.covDialog.jComboBox1.addItem(temp);
        f2.CEDialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Trait_Farray")) {
      Vector Trait_array = (Vector) dm.getValue("Trait_Farray");
      int i = 0;
      for (Enumeration e = Trait_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.traitComboBox.addItem(temp);
        f2.covDialog.jComboBox1.addItem(temp);
        f2.CEDialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Covariate_Farray")) {
      Vector Covariate_array = (Vector) dm.getValue("Covariate_Farray");
      int i = 0;
      for (Enumeration e = Covariate_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.traitComboBox.addItem(temp);
        f2.covDialog.jComboBox1.addItem(temp);
        f2.CEDialog.jComboBox1.addItem(temp);
      }
    }

    if (dm.hasValue("Phenotype_Farray")) {
      Vector Phenotype_array = (Vector) dm.getValue("Phenotype_Farray");
      int i = 0;
      for (Enumeration e = Phenotype_array.elements(); e.hasMoreElements(); i++) {
        String temp = e.nextElement().toString();
        f2.traitComboBox.addItem(temp);
        f2.covDialog.jComboBox1.addItem(temp);
        f2.CEDialog.jComboBox1.addItem(temp);
      }
    }
  }

  void jButton2_actionPerformed() {
    jFileChooser1.setDialogTitle("Add Data File");
    jFileChooser1.setFileFilter(FamilyFilter);

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

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "ASSOC",
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

    SetCovariateList(source);
  }

  void SetCovariateList(NodeInfo source)
  {
      NodeInfo n = (NodeInfo) analysis_node.getUserObject();
      ASSOC2 f2 = (ASSOC2) n.component_vector.get(1);

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
                  f2.traitComboBox.addItem(temp);
                  f2.covDialog.jComboBox1.addItem(temp);
                  f2.CEDialog.jComboBox1.addItem(temp);
              }
          }

      } catch (Exception exe) {
          exe.printStackTrace();
      }
  }

  void jButton3_actionPerformed() {
    jFileChooser1.setDialogTitle("Add Locus File");
    jFileChooser1.setFileFilter(MarkerLocusFilter);

    if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {
      String filepath = jFileChooser1.getSelectedFile().getPath();
      String filename = jFileChooser1.getSelectedFile().getName();
      NodeInfo filenode = new NodeInfo(filename, "Marker Locus File", new File(filepath));
      insertlocusfile(filenode);
    }
  }

  public void insertlocusfile(NodeInfo source)
  {
   File importFile = (File)source.file;
    String FilePath = importFile.getPath();
    Datamodel.setValue("locus_path",FilePath);
    jTextFieldMarker.setText(FilePath);

    jFileChooser1.setCurrentDirectory(importFile);
    Frame1.mainFrame1.path_forFileChooser = FilePath;

    Analysis_object.locus_file_path = FilePath;

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "ASSOC",Analysis_object,"");
      IconNode node = new IconNode(nodeinfo, "InputFolder");

      inputF_node = addObject(node, analysis_node, false);
      Analysis_object.create_input_folder = true;
    }
    if (Analysis_object.create_locus_file_node == false) {
      marker_node = new IconNode(source, "Marker Locus File");

      addObject(marker_node, inputF_node, false);
      Analysis_object.create_locus_file_node = true;
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
    if (document == jTextFieldMarker.getDocument()) {
      Datamodel.setValue("locus_path", jTextFieldMarker.getText());
    }
    if (document == OutputNameField.getDocument()) {
      Datamodel.setValue("output_name", OutputNameField.getText());
    }

    if (Datamodel.hasValue("para_path") && Datamodel.hasValue("pedi_path")
        && Datamodel.hasValue("output_name")) {
      jNextButton.setIcon(next_image);
      this.updateUI();
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
      Frame1.mainFrame1.pdfframe.setTextonPage("parameter fil", false, 328);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("pedigree data fil", false, 328);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("marker locus description fil", false, 328);
  }

  void OutputNameField_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("out", false, 329);
  }

}

class ASSOC1_jTextField1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC1 adaptee;

  ASSOC1_jTextField1_mouseAdapter(ASSOC1 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class ASSOC1_jTextField2_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC1 adaptee;

  ASSOC1_jTextField2_mouseAdapter(ASSOC1 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class ASSOC1_jTextField3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC1 adaptee;

  ASSOC1_jTextField3_mouseAdapter(ASSOC1 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class ASSOC1_OutputNameField_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC1 adaptee;

  ASSOC1_OutputNameField_mouseAdapter(ASSOC1 adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.OutputNameField_mouseClicked(e);
  }
}

