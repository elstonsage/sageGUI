package sage;

import java.awt.*;
import javax.swing.tree.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Lodpal_Dialog3_Pair
    extends SageDialog
    implements DocumentListener, ActionListener, java.awt.event.ItemListener {

  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  JLabel jLabel6 = new JLabel();
  JTextField Name = new JTextField();
  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();

  String option[] = {
      "mean centering", "minimum offset"};

  JComboBox jComboBox1 = new JComboBox(option);
  JTextField jTextField1 = new JTextField();
  JButton jAddButton = new JButton();
  JLabel jLabel5 = new JLabel();
  JButton jButton3 = new JButton();
  JComboBox jComboBox3 = new JComboBox();

  DefaultMutableTreeNode dir_rootnode;
  LODPAL2 parent;
  public Lodpal_Dialog3_Pair(LODPAL2 parent, String title) {
    super(title);

    this.setTitle(title);
    this.parent = parent;

    dir_rootnode = makeTree();
    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    jLabel2.setText("Pair covariate");

    jLabel2.setToolTipText(
        "<html>Specifies a variable name to be used as covariate in the current " +
        "test.");

      this.setSize(new Dimension(330, 380));
      jCenterPanel.setPreferredSize(new Dimension(330, 300));

    jLabel6.setToolTipText("Character string representing a valid file name.");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);


    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jButton3.addActionListener(this);

    jLabel3.setText("Specifies pair info file name.");
    jLabel6.setText("File");
    Name.setBackground(Color.white);
    Name.setEditable(false);
    Name.setText("");
    Name.addMouseListener(new Lodpal_Dialog3_Pair_Name_mouseAdapter(this));
    jLabel4.setToolTipText("Specifies option to include pair specific covariate value.");
    jLabel4.setText("Option");
    jTextField1.setEnabled(false);
    jTextField1.setText("observed mean");
    jTextField1.addMouseListener(new
                                 Lodpal_Dialog3_Pair_jTextField1_mouseAdapter(this));
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add covariate ");
    jAddButton.addActionListener(this);
    jComboBox1.setEnabled(false);
    jComboBox1.setToolTipText("");
    jComboBox1.addMouseListener(new Lodpal_Dialog3_Pair_jComboBox1_mouseAdapter(this));
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("...");
    jComboBox3.addMouseListener(new Lodpal_Dialog3_Pair_jComboBox3_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 300, 30));
    jCenterPanel.add(jLabel6, new XYConstraints(15, 15, 76, 20));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 45, 300, 240));
    jPanel1.add(jLabel2, new XYConstraints(10, 5, 83, 20));
    jPanel1.add(jLabel4, new XYConstraints(30, 35, 50, 20));
    jPanel1.add(jComboBox1,   new XYConstraints(90, 35, 100, 20));
    jPanel1.add(jTextField1,   new XYConstraints(90, 65, 100, 20));
    jPanel1.add(jAddButton,  new XYConstraints(200, 55, 85, 30));
    jPanel1.add(jLabel5, new XYConstraints(10, 99, 156, 20));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 101, 272, 120));
    jPanel1.add(jComboBox3,  new XYConstraints(90, 5, 190, 20));
    jCenterPanel.add(Name, new XYConstraints(80, 15, 195, 20));
    jCenterPanel.add(jButton3, new XYConstraints(280, 15, 30, 20));

    Name.getDocument().addDocumentListener(this);

    jComboBox1.addItemListener(this);
    jComboBox3.addItemListener(this);
    jMenuItemDelete.addActionListener(this);

  }

  DefaultMutableTreeNode makeTree() {
    FileSystemView fileSystem = FileSystemView.getFileSystemView();

    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Project");
    DefaultMutableTreeNode fnode;
    File file_roots[] = fileSystem.getRoots();
    for (int i = 0; i < file_roots.length; i++) {
      fnode = new DefaultMutableTreeNode(file_roots[i]);
      root.add(fnode);
    }
    return root;
  }

  public void save_init_state()
  {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }
    Datamodel.setValue("Pair_info", resultinfo.trim());

    Datamodel.setValue("Pair", "use");
    parent.jTextField3.setText("Specified");
    parent.jLabel4.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();
    if(button == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }

    else if (button == okButton) {
      save_init_state();
      dispose();
    }

    else if (button == cancelButton) {
      dispose();
    }

    else if (button == jAddButton) {
      String covlist = "pair_covariate = " +"\""+
          jComboBox3.getSelectedItem().toString()+"\"";
      if (jComboBox1.getSelectedIndex() == 0) {
        covlist = covlist + ", mean";
        if (jTextField1.getText().indexOf("mean") < 0 && jTextField1.getText().length()>0)
          covlist = covlist + "=" +"\""+ jTextField1.getText()+"\"";
      }
      else {
        covlist = covlist + ", minimum";
      }
      listModel.addElement(covlist);
      jComboBox1.setSelectedIndex(0);
      jAddButton.setEnabled(false);
    }
    else if (button == jButton3) {
      MyFileChooser.initialize(this, dir_rootnode, "Browse for File",
                               "Select pair info file and delimiter");
      String filepath = MyFileChooser.showDialog(Frame1.mainFrame1);

      if (!MyFileChooser.ok)
        return;

      String delimiter = new String();

      if (MyFileChooser.tab.isSelected())
        delimiter = "\t";
      if (MyFileChooser.comma.isSelected())
        delimiter = ",";
      if (MyFileChooser.other.isSelected())
        delimiter = MyFileChooser.delimiter.getText();

      String strFileLine = new String();
      Name.setText(filepath);

      try {
        FileInputStream fin = new FileInputStream(filepath);
        InputStreamReader isr = new InputStreamReader(fin); // Unicode, utf-8 ...
        BufferedReader in = new BufferedReader(isr);

        strFileLine = in.readLine();

        String HeaderList[] = strFileLine.split(delimiter);

        jComboBox3.removeAllItems();
        jComboBox3.addItem("");

        for (int i = 3; i < HeaderList.length; i++) {
          jComboBox3.addItem(HeaderList[i].trim());
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    else {
      dispose();
      if (!Datamodel.hasValue("Pair"))
        listModel.removeAllElements();
    }
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == Name.getDocument()) {
      Datamodel.setValue("Pair_name", Name.getText());
    }
    if (Datamodel.hasValue("Pair_weight") && Datamodel.hasValue("Pair_name")) {
      okButton.setEnabled(true);
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
      int i = jComboBox1.getSelectedIndex();
      switch (i) {
        case 0: // mean
          jTextField1.setText("observed mean");
          jTextField1.setEnabled(true);
          jTextField1.setEditable(true);
          break;

        case 1: //minimum
          jTextField1.setText("observed minimum");
          jTextField1.setEnabled(false);
          jTextField1.setEditable(false);
          break;
      }
    }
    if (ob == jComboBox3) {
      if (jComboBox3.getSelectedIndex() == 0) {
        jComboBox1.setEnabled(false);
        jAddButton.setEnabled(false);
      }
      else {
        jComboBox1.setEnabled(true);
        jAddButton.setEnabled(true);
      }
    }

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
     Frame1.mainFrame1.pdfframe.setTextonPage("pair_info_fil", false, 281);
  }

  void Name_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pair_info_fil", false, 281);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pair_weight", false, 281);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
   Frame1.mainFrame1.pdfframe.setTextonPage("pair_covariate", false, 281);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("mean", false, 281);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("minimum", false, 281);
  }
}

class Lodpal_Dialog3_Pair_Name_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog3_Pair adaptee;

  Lodpal_Dialog3_Pair_Name_mouseAdapter(Lodpal_Dialog3_Pair adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.Name_mouseClicked(e);
  }
}

class Lodpal_Dialog3_Pair_jComboBox2_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog3_Pair adaptee;

  Lodpal_Dialog3_Pair_jComboBox2_mouseAdapter(Lodpal_Dialog3_Pair adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class Lodpal_Dialog3_Pair_jComboBox3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog3_Pair adaptee;

  Lodpal_Dialog3_Pair_jComboBox3_mouseAdapter(Lodpal_Dialog3_Pair adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class Lodpal_Dialog3_Pair_jComboBox1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog3_Pair adaptee;

  Lodpal_Dialog3_Pair_jComboBox1_mouseAdapter(Lodpal_Dialog3_Pair adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Lodpal_Dialog3_Pair_jTextField1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog3_Pair adaptee;

  Lodpal_Dialog3_Pair_jTextField1_mouseAdapter(Lodpal_Dialog3_Pair adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}
