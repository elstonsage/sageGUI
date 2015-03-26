package sage;

import java.awt.Insets;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.*;
import java.util.*;

public class LODLINK1
    extends SageFilePanel
    implements DocumentListener, ActionListener {
  IconNode analysis_node;
  IconNode errorF_node;
  IconNode errorpedigree_node;
  IconNode errorlocus_node;
  IconNode pedi_node;
  IconNode marker_node;
  IconNode trait_node;
  IconNode type_node;

  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JButton jButton3 = new JButton();
  JTextField jTextField4 = new JTextField();
  JButton jButton4 = new JButton();
  JLabel jLabel5 = new JLabel();
  JTextField jTextField5 = new JTextField();
  JButton jButton5 = new JButton();

  public LODLINK1(sage_analysis_info data, IconNode inputnode,
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
    Datamodel.setValue("output_name", jTextField4.getText());
  }

  void jbInit() throws Exception {
    NodeInfo outputfolder = new NodeInfo("Output", "OutputFolder", "LODLINK",
                                         Analysis_object, "");
    outputF_node = new IconNode(outputfolder, "OutputFolder");

    this.setLayout(xYLayout1);
    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jLabel3.setToolTipText(
        "<html>Lists the alleles, allele frequencies and phenotype to genotype mapping " +
        "<br>for each marker locus.");
    jLabel3.setText("Marker locus file");
    jTextFieldPara.addMouseListener(new LODLINK1_jTextField1_mouseAdapter(this));
    jTextFieldPed.addMouseListener(new LODLINK1_jTextField2_mouseAdapter(this));
    jTextField3.setText("");
    jTextField3.addMouseListener(new LODLINK1_jTextField3_mouseAdapter(this));
    jButton3.setText("...");

    jButton3.setToolTipText("Lists the alleles at each marker locus.");
    jButton3.setText("...");

    jTextField4.setToolTipText(
        "<html>Specifies the name of the output file generated by this analysis.");
    jTextField4.setText("");
    jTextField4.addMouseListener(new LODLINK1_jTextField4_mouseAdapter(this));
    jLabel4.setToolTipText(
        "<html>Lists the alleles, allele frequencies and phenotype to genotype mapping " +
        "<br>for each trait marker.");
    jLabel4.setText("Trait locus file");
    jButton4.setText("...");

    jLabel5.setToolTipText(
        "<html>Produced by SEGREG. Lists penetrance and posterior type probabilities " +
        "<br>for each individual.");
    jLabel5.setText("Type file");
    jTextField5.setToolTipText("");
    jTextField5.setText("");
    jTextField5.addMouseListener(new LODLINK1_jTextField5_mouseAdapter(this));

    jNextButton.addActionListener(this);

    OutputNameField.addMouseListener(new LODLINK1_OutputNameField_mouseAdapter(this));
    OutputNameField.setText("lodlink_analysis");
    jButton5.setMargin(new Insets(2, 2, 2, 2));
    jButton5.setText("...");
    this.add(jLabelPara, new XYConstraints(20, 20, 113, 20));
    this.add(jLabelPed, new XYConstraints(20, 50, 113, 20));
    this.add(jLabel3, new XYConstraints(20, 80, 113, 20));
    this.add(jLabel4, new XYConstraints(20, 110, 113, 20));
    this.add(jTextFieldPara,  new XYConstraints(155, 20, 280, 20));
    this.add(jTextFieldPed,    new XYConstraints(155, 50, 280, 20));
    this.add(jTextField3,    new XYConstraints(155, 80, 280, 20));
    this.add(jButtonPed,  new XYConstraints(445, 50, 30, 20));
    this.add(jButton3,  new XYConstraints(445, 80, 30, 20));
    this.add(jButtonPara,  new XYConstraints(445, 20, 30, 20));
    this.add(jTextField4,    new XYConstraints(155, 110, 280, 20));
    this.add(jButton4,  new XYConstraints(445, 110, 30, 20));
    this.add(jLabel5, new XYConstraints(20, 140, 113, 20));
    this.add(jTextField5,    new XYConstraints(155, 140, 280, 20));
    this.add(jNextButton, new XYConstraints(420, 520, 60, 25));
    this.add(jLabelOutputName, new XYConstraints(20, 180, 113, 20));
    this.add(OutputNameField, new XYConstraints(155, 180, 280, 20));
    this.add(jButton5,  new XYConstraints(445, 140, 30, 20));

    jButtonPara.addActionListener(this);
    jButtonPed.addActionListener(this);
    jButton3.addActionListener(this);
    jButton4.addActionListener(this);
    jButton5.addActionListener(this);
    jTextFieldPara.getDocument().addDocumentListener(this);
    jTextFieldPed.getDocument().addDocumentListener(this);
    jTextField3.getDocument().addDocumentListener(this);
    jTextField4.getDocument().addDocumentListener(this);
    jTextField5.getDocument().addDocumentListener(this);
    OutputNameField.getDocument().addDocumentListener(this);

    Datamodel.setValue("output_name", OutputNameField.getText());
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jButtonPara) {
      jButton1_actionPerformed();
    }
    if (ob == jButtonPed) {
      jButton2_actionPerformed();
    }
    if (ob == jButton3) {
      jButton3_actionPerformed();
    }
    if (ob == jButton4) {
      jButton4_actionPerformed();
    }
    if (ob == jButton5) {
      jButton5_actionPerformed();
    }
    if (ob == jNextButton) {
      jNextButton_actionPerformed();
    }
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
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "LODLINK",
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
  }

  void SetPanel2Info(NodeInfo source) {

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

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "LODLINK",
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

    setPIDInfo(FilePath);
  }

  void setPIDInfo(String source_file_path)
  {
    DataCollectionModel paranodemodel = ((NodeInfo)para_node.getUserObject()).infomodel;

    Vector pidlist = new Vector();
    if(paranodemodel.hasValue("pid_list"))
    {
      pidlist = (Vector)paranodemodel.getValue("pid_list");
    }
    else
    {
      if(paranodemodel.hasValue("pedigree_id_name")== false)
      {
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
        "You must specify the \"pedigree_id\" option in the parameter file pedigree block.",
        "Error",
        JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        return;
      }
      if(paranodemodel.hasValue("delimiters_name")== false)
      {
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
        "You must specify the \"delimiters\" option in the parameter file pedigree block.",
        "Error",
        JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        return;
      }

      try{
        pidlist = getPIDList(paranodemodel, source_file_path);
      }catch(Exception ec)
      {
          ec.printStackTrace();
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
       "I/O exception encountered while attempting to read file '"+source_file_path+
       "'.\nPlease check the file for correct formatting, file attributes, "+
       "\nuser access privileges, possible data corruption, etc. ",
       "Error",
       JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
       return;
      }
    }

    CheckableItem items1[] = new CheckableItem[pidlist.size()];

    for (int i=0;i<pidlist.size();i++) {
      String temp = pidlist.get(i).toString();
      VariableData temp2 = new VariableData(temp, "pid");
      items1[i] = new CheckableItem(temp2);
    }

    NodeInfo n = (NodeInfo) analysis_node.getUserObject();
    LODLINK2 f2 = (LODLINK2) n.component_vector.get(1);

    f2.dialog1.jComboBox3.setData(items1);
  }

  Vector getPIDList(DataCollectionModel paranodemodel, String source_file_path) throws Exception
  {
    String pedigreeid = paranodemodel.getValue("pedigree_id_name").toString();
    String delimiter = paranodemodel.getValue("delimiters_name").toString();
    String delimiter_mode = paranodemodel.getValue("delimiter_mode").toString();

    if(delimiter.compareTo("\\t")==0)
    {
        delimiter = "\t";
    }

    boolean isHeaderExist = false;
    if(!paranodemodel.hasValue("format_name"))
    {
      isHeaderExist = true;
    }

    int i = 0;
    Vector column = new Vector();

    FileReader fr = new FileReader(source_file_path);
    BufferedReader br = new BufferedReader(fr);
    String temp = new String();
    while ( (temp = br.readLine()) != null && temp.trim().length() > 0) {
        StringTokenizer st = new StringTokenizer(temp, delimiter);
        if (isHeaderExist) {
          if (i == 0) {
            while (st.hasMoreTokens()) {
              column.add(st.nextToken());
            }

            break;
          }
        }
    }

    if (!isHeaderExist) {
      String headerlist = paranodemodel.getValue("format_name").toString();
      if(delimiter_mode.compareTo("multiple")==0)
      {
        StringTokenizer st = new StringTokenizer(headerlist, delimiter);
        while (st.hasMoreTokens()) {
          column.add(st.nextToken());
        }
      }
      else
      {
        String line_array[] = headerlist.split(delimiter);
        for(int il=0;il<line_array.length;il++)
        {
          column.add(line_array[il]);
        }
      }
    }

    int pidindex = -1;
    for(i=0;i<column.size();i++)
    {
      String pidlist = column.get(i).toString().trim();
      if(pidlist.compareTo(pedigreeid)==0)
      {
        pidindex = i;
        break;
      }
    }

    if (pidindex < 0) {
      JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                   "The name specified for 'pedigree_id' in parameter file pedigree block"+
                                   "\nmust match the corresponding column name in the pedigree data file.",
                                   "Error",
                                   JOptionPane.CLOSED_OPTION,
                                   JOptionPane.WARNING_MESSAGE, null, null, null);
      throw new Exception();
    }

    Vector temp_pid_list = new Vector();

    int lineindex=0;
    while ( (temp = br.readLine()) != null && temp.trim().length() > 0) {
        StringTokenizer st = new StringTokenizer(temp, delimiter);

        int j=0;
        while (st.hasMoreTokens()) {
            String temp2 = st.nextToken();

            if (j == pidindex && temp2 != null)
            {
              if(isHeaderExist)
                if(lineindex > 0)
                  temp_pid_list.add(temp2);
              else
                temp_pid_list.add(temp2);
            }
          j++;
        }
        lineindex++;
    }

    Vector pid_list = new Vector();

    List sortedlist = temp_pid_list.subList(0, temp_pid_list.size());
    Collections.sort(sortedlist);

    String strFileLine = new String();
    String strFileNextLine = new String();

    for(i=0;i<sortedlist.size();i++)
    {
      strFileLine = sortedlist.get(i).toString();
      if (strFileLine.compareTo(strFileNextLine) != 0) {
        pid_list.add(strFileLine);
      }
      strFileNextLine = strFileLine;
    }
    return pid_list;
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
    jTextField3.setText(FilePath);
    jFileChooser1.setCurrentDirectory(importFile);
    Frame1.mainFrame1.path_forFileChooser = FilePath;

    Analysis_object.locus_file_path = FilePath;

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "LODLINK",
                                       Analysis_object, "");
      IconNode node = new IconNode(nodeinfo, "InputFolder");

      inputF_node = addObject(node, analysis_node, false);
      Analysis_object.create_input_folder = true;
    }
    if (Analysis_object.create_locus_file_node == false) {
      marker_node = new IconNode(source, "Marker Locus File");

      addObject(marker_node, inputF_node, false);
      Analysis_object.create_locus_file_node = true;

      errorlocus_node.removeFromParent();
      treeModel.nodeStructureChanged(errorF_node);

      if (errorF_node.getChildCount() < 1) {
          Analysis_object.create_error_folder= false;

        errorF_node.removeFromParent();
        treeModel.nodeStructureChanged(analysis_node);
        TreePath p = new TreePath(marker_node.getPath());
        Frame1.mainFrame1.activeinframe.MyTree1.scrollPathToVisible(p);
      }
    }
    else {
      marker_node.setUserObject(source);
      TreePath p = new TreePath(marker_node.getPath());
      Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    }

    TreePath p = new TreePath(analysis_node.getPath());
    Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
  }

  void jButton4_actionPerformed() {
    jFileChooser1.setFileFilter(TraitLocusFilter);
    jFileChooser1.setDialogTitle("Add Locus File");

    if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {
      String filepath = jFileChooser1.getSelectedFile().getPath();
      String filename = jFileChooser1.getSelectedFile().getName();
      NodeInfo filenode = new NodeInfo(filename, "Trait File", new File(filepath));
      inserttraitfile(filenode);
    }
  }

  public void inserttraitfile(NodeInfo source) {
    jButton5.setEnabled(false);
    jTextField5.setEditable(false);
    jTextField5.setEnabled(false);

    File importFile = (File) source.file;
    String FilePath = importFile.getPath();
    Datamodel.setValue("trait_path", FilePath);
    jTextField4.setText(FilePath);

    jFileChooser1.setCurrentDirectory(importFile);
    Frame1.mainFrame1.path_forFileChooser = FilePath;

    Analysis_object.trait_file_path = FilePath;

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "LODLINK",
                                       Analysis_object, "");
      IconNode node = new IconNode(nodeinfo, "InputFolder");

      inputF_node = addObject(node, analysis_node, false);
      Analysis_object.create_input_folder = true;
    }
    if (Analysis_object.create_trait_file_node == false) {
      trait_node = new IconNode(source, "Trait File");

      addObject(trait_node, inputF_node, false);
      Analysis_object.create_trait_file_node = true;
    }
    else {
      trait_node.setUserObject(source);
      TreePath p = new TreePath(trait_node.getPath());
      Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    }

    TreePath p = new TreePath(analysis_node.getPath());
    Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
  }

  void jButton5_actionPerformed() {
    jFileChooser1.setFileFilter(TypeFilter);
    jFileChooser1.setDialogTitle("Add Type File");

    if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {
      String filepath = jFileChooser1.getSelectedFile().getPath();
      String filename = jFileChooser1.getSelectedFile().getName();
      NodeInfo filenode = new NodeInfo(filename, "Type File", new File(filepath));
      inserttypefile(filenode);
    }
  }

  public void inserttypefile(NodeInfo source) {
    jButton4.setEnabled(false);
    jTextField4.setEditable(false);
    jTextField4.setEnabled(false);

    File importFile = (File) source.file;
    String FilePath = importFile.getPath();
    Datamodel.setValue("type_path", FilePath);
    jTextField5.setText(FilePath);

    jFileChooser1.setCurrentDirectory(importFile);
    Frame1.mainFrame1.path_forFileChooser = FilePath;

    Analysis_object.type_file_path = FilePath;

    if (Analysis_object.create_input_folder == false) {
      NodeInfo nodeinfo = new NodeInfo("Input", "InputFolder", "LODLINK",
                                       Analysis_object, "");
      IconNode node = new IconNode(nodeinfo, "InputFolder");

      inputF_node = addObject(node, analysis_node, false);
      Analysis_object.create_input_folder = true;
    }
    if (Analysis_object.create_type_file_node == false) {
      type_node = new IconNode(source, "Type File");

      addObject(type_node, inputF_node, false);
      Analysis_object.create_type_file_node = true;
    }
    else {
      type_node.setUserObject(source);
      TreePath p = new TreePath(type_node.getPath());
      Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    }

    TreePath p = new TreePath(analysis_node.getPath());
    Frame1.mainFrame1.activeinframe.MyTree1.setSelectionPath(p);
    Frame1.mainFrame1.activeinframe.MyTree1.Refresh();
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
    if (document == jTextField3.getDocument()) {
      if(jTextField3.getText().length()>0)
      {
          Datamodel.setValue("locus_path", jTextField3.getText());
      }
      else
      {
          if(Datamodel.hasValue("locus_path"))
              Datamodel.removeValue("locus_path");
      }

    }
    if (document == jTextField4.getDocument()) {
      Datamodel.setValue("trait_path", jTextField4.getText());
    }
    if (document == jTextField5.getDocument()) {
      Datamodel.setValue("type_path", jTextField5.getText());
    }

    if (document == OutputNameField.getDocument()) {
      Datamodel.setValue("output_name", OutputNameField.getText());
    }

    if (Datamodel.hasValue("para_path") && Datamodel.hasValue("pedi_path")
        && Datamodel.hasValue("locus_path") && Datamodel.hasValue("output_name")) {
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

  void deteleLocusNode()
  {
    if(errorlocus_node != null && errorF_node != null)
    {
        addObject(errorlocus_node, errorF_node, false);
        if(!Analysis_object.create_error_folder)
        {
            treeModel.insertNodeInto(errorF_node, analysis_node, 0);
            Analysis_object.create_error_folder = true;
        }

        Analysis_object.create_locus_file_node = false;
        TreePath p = new TreePath(errorlocus_node.getPath());
        Frame1.mainFrame1.activeinframe.MyTree1.scrollPathToVisible(p);
    }
  }

  void jTextField1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("lodlink parameter fil", false, 297);
   }

   void jTextField2_mouseClicked(MouseEvent e) {
       Frame1.mainFrame1.pdfframe.setTextonPage("pedigree data fil", false, 297);
   }

  void jTextField3_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("marker locus description fil", false, 297);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("trait locus description file", false, 297);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("trait genotype probability fil", false, 297);
  }

  void OutputNameField_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("out ", false, 298);
  }
}

class LODLINK1_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK1 adaptee;

  LODLINK1_jTextField1_mouseAdapter(LODLINK1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class LODLINK1_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK1 adaptee;

  LODLINK1_jTextField2_mouseAdapter(LODLINK1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class LODLINK1_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK1 adaptee;

  LODLINK1_jTextField3_mouseAdapter(LODLINK1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class LODLINK1_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK1 adaptee;

  LODLINK1_jTextField4_mouseAdapter(LODLINK1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class LODLINK1_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK1 adaptee;

  LODLINK1_jTextField5_mouseAdapter(LODLINK1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class LODLINK1_OutputNameField_mouseAdapter extends java.awt.event.MouseAdapter {
  LODLINK1 adaptee;

  LODLINK1_OutputNameField_mouseAdapter(LODLINK1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.OutputNameField_mouseClicked(e);
  }
}
