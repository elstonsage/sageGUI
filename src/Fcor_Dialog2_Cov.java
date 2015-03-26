package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;

public class Fcor_Dialog2_Cov
    extends SageDialog
    implements java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  XYLayout xYLayout2 = new XYLayout();
  int trait_count = 0;

  String[] main_type = {
      "self", "mother:father", "parent:offspring", "sibling",
      "half-sibling", "grandparental", "avuncular", "cousin",
      "male-self", "female-self",
      "mother:father",
      "father:son", "mother:son", "father:daughter", "mother:daughter",
      "brother:brother", "sister:brother", "sister:sister",
      "paternal-half-brother:half-brother", "paternal-half-sister:half-brother",
      "paternal-half-sister:half-sister", "maternal-half-brother:half-brother",
      "maternal-half-sister:half-brother", "maternal-half-sister:half-sister",
      "grandfather-through-father:grandson",
      "grandmother-through-father:grandson",
      "grandfather-through-mother:grandson",
      "grandmother-through-mother:grandson",
      "grandfather-through-father:granddaughter",
      "grandmother-through-father:granddaughter",
      "grandfather-through-mother:granddaughter",
      "grandmother-through-mother:granddaughter",
      "uncle-through-father:nephew", "aunt-through-father:nephew",
      "uncle-through-mother:nephew", "aunt-through-mother:nephew",
      "uncle-through-father:niece", "aunt-through-father:niece",
      "uncle-through-mother:niece", "aunt-through-mother:niece",
      "male-cousin-through-father:male-cousin-through-father",
      "male-cousin-through-mother:male-cousin-through-father",
      "male-cousin-through-mother:male-cousin-through-mother",
      "female-cousin-through-father:male-cousin-through-father",
      "female-cousin-through-father:male-cousin-through-mother",
      "female-cousin-through-mother:male-cousin-through-father",
      "female-cousin-through-mother:male-cousin-through-mother",
      "female-cousin-through-father:female-cousin-through-father",
      "female-cousin-through-mother:female-cousin-through-father",
      "female-cousin-through-mother:female-cousin-through-mother"};

  DataCollectionModel Datamodel;
  TitledBorder titledBorder1;
  TitledBorder titledBorder3;

  CheckListRenderer ch = new CheckListRenderer();
  EmptyBorder empbor = new EmptyBorder(0, 4, 0, 0);
  ListMouseListener ml = new ListMouseListener();

  String CovList = new String();
  JLabel jLabel4 = new JLabel();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel2 = new JLabel();
  JComboBox jComboBox2 = new JComboBox(main_type);
  JList jList1 = new JList();
  XYLayout xYLayout4 = new XYLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  JLabel jLabel6 = new JLabel();
  JRadioButton jRadioButton1 = new JRadioButton();
  JLabel jLabel1 = new JLabel();
  JComboBox jComboBox1 = new JComboBox(main_type);
  JLabel jLabel3 = new JLabel();
  JRadioButton jRadioButton2 = new JRadioButton();
  JButton jAddButton = new JButton();
  FCOR2 parent;

  public Fcor_Dialog2_Cov(FCOR2 parent, String title) {
    super(title);
    this.parent = parent;

    this.setTitle(title);

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
    titledBorder3 = new TitledBorder("");
    jRadioButton2.setText("Joint");
    jRadioButton2.addMouseListener(new Fcor_Dialog2_Cov_jRadioButton2_mouseAdapter(this));
    jRadioButton2.setToolTipText(
            "Prints a matrix for each pair of traits specified.");
    jLabel1.setText("Trait(s)");
    jLabel1.setForeground(Color.red);
    jLabel1.setRequestFocusEnabled(true);
    jLabel1.setToolTipText("<html>Names trait(s), covariate(s) for which variance-covariance " +
                           "<br>matrix is to be printed.");
    jRadioButton1.setText("Single");
    jRadioButton1.addMouseListener(new Fcor_Dialog2_Cov_jRadioButton1_mouseAdapter(this));
    jRadioButton1.setSelected(true);

    jRadioButton2.setFocusPainted(false);
    jRadioButton1.setFocusPainted(false);

    jRadioButton1.setToolTipText(
            "Prints a matrix for each trait specified.");
    jLabel6.setText("Type");
    jLabel6.setToolTipText(
        "Specifies the type of variance-covariance matrix to be printed.");

    jList1.setCellRenderer(ch);
    jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jList1.addMouseListener(new Fcor_Dialog2_Cov_jList1_mouseAdapter(this));
    jList1.setBorder(empbor);

    jList1.addMouseListener(ml);

    jLabel3.setText("Relative type");
    jLabel3.setToolTipText(
            "<html>Specifies relative type for the variance-covariance matrix of correlation estimates."+
            "<br>Up to 2 relative types may be specified.");

    jLabel2.setText("Relative type");
    jLabel2.setToolTipText(
            "<html>Specifies relative type for the variance-covariance matrix of correlation estimates."+
            "<br>Up to 2 relative types may be specified.");
    jPanel1.setLayout(xYLayout4);
    jPanel1.setBorder(titledBorder3);

    this.setSize(new Dimension(490, 400));
    jLabel4.setText("Specify the options to print out of variance-covariance.");
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout5);

    ButtonGroup bp = new ButtonGroup();
    okButton.setMargin(new Insets(2, 2, 2, 2));

    jTopPanel.setPreferredSize(new Dimension(490, 40));
    jCenterPanel.setPreferredSize(new Dimension(490, 320));
    jBottomPanel.setPreferredSize(new Dimension(490, 40));

    okButton.addActionListener(this);
    okButton.setText("OK");
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add correlation");
    jAddButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jComboBox1.addMouseListener(new Fcor_Dialog2_Cov_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new Fcor_Dialog2_Cov_jComboBox2_mouseAdapter(this));
    jTopPanel.add(jLabel4, new XYConstraints(15, 5, 400, 24));
    jCenterPanel.add(jPanel2, new XYConstraints(15, 10, 465, 293));
    jPanel1.add(jRadioButton1, new XYConstraints(5, 2, 70, 20));
    jPanel1.add(jRadioButton2, new XYConstraints(5, 25, 70, 20));

    jPanel2.add(jLabel6, new XYConstraints(10, 10, 77, 20));
    jPanel2.add(jPanel1,    new XYConstraints(95, 10, 100, 60));

    jPanel2.add(jLabel1, new XYConstraints(225, 10, 67, 20));
    jPanel2.add(jScrollPane2,     new XYConstraints(270, 10, 175, 60));

    jPanel2.add(jLabel2,  new XYConstraints(9, 80, 90, 20));
    jPanel2.add(jComboBox1,      new XYConstraints(95, 80, 350, 20));
    jPanel2.add(jLabel3,  new XYConstraints(9, 110, 90, 20));
    jPanel2.add(jComboBox2,  new XYConstraints(95, 110, 350, 20));

    jPanel2.add(jAddButton,             new XYConstraints(325, 140, 120, 30));
    jPanel2.add(jScrollPane1, new XYConstraints(8, 180, 435, 90));

    jScrollPane2.getViewport().add(jList1, null);
    bp.add(jRadioButton1);
    bp.add(jRadioButton2);
    jBottomPanel.add(cancelButton, new XYConstraints(405, 7, 65, 25));
    jBottomPanel.add(okButton, new XYConstraints(327, 7, 65, 25));

    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);
    jRadioButton1.addItemListener(this);
    jRadioButton2.addItemListener(this);
    jMenuItemDelete.addActionListener(this);
  }

  void set_dataModel(DataCollectionModel input)
  {
    this.Datamodel = input;
    Datamodel.setValue("var_cov_type", "single");
  }

  void set_ListModel(CheckableItem[] listmodel) {
    int n = listmodel.length;

    Vector dialog_list = new Vector();
    jList1.setListData(dialog_list);

    int trait_count=0;

    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) listmodel[i];
      if (item.isSelected()) {
        trait_count++;
        dialog_list.add(item);
      }
    }

    if(trait_count==0)
    {
      jLabel3.setEnabled(false);
      jLabel2.setEnabled(false);
      jComboBox1.setEnabled(false);
      jComboBox2.setEnabled(false);
      jAddButton.setEnabled(false);
    }
    else
    {
      jLabel3.setEnabled(true);
      jLabel2.setEnabled(true);
      jComboBox1.setEnabled(true);
      jComboBox2.setEnabled(true);
      jAddButton.setEnabled(true);

      CheckableItem[] items = new CheckableItem[dialog_list.size()];
      int i = 0;
      for (Enumeration e = dialog_list.elements(); e.hasMoreElements(); i++) {
        CheckableItem tempcheck = (CheckableItem) e.nextElement();
        String type = tempcheck.getVarType();
        VariableData temp2 = new VariableData(tempcheck.toString(), type);
        items[i] = new CheckableItem(temp2);
      }
      jList1.setListData(items);
    }
  }

  public void save_init_state()
  {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }
    Datamodel.setValue("var_cov", resultinfo.trim());
    Datamodel.setValue("cov", "use");
    parent.jTextField2.setText("Specified");
    parent.jLabel8.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if(ob == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }

    else if (ob == okButton) { //ok
      save_init_state();
      dispose();
    }

    else if (ob == cancelButton) {
      dispose();
      if(!Datamodel.hasValue("cov"))
        listModel.removeAllElements();
    }

    else if (ob == jAddButton) {
      String CovList = "var_cov, " + Datamodel.getValue("var_cov_type") + " {" +
          "\n";

      ListModel model = jList1.getModel();
      int n = model.getSize();
      for (int i = 0; i < n; i++) {
        CheckableItem item = (CheckableItem) model.getElementAt(i);
        if (item.isSelected()) {
          CovList = CovList + "trait = " +"\""+ item.toString() +"\""+ "\n";
          item.setSelected(false);
        }
      }
      this.repaint();
      CovList = CovList + "correlation = " +"\""+
          jComboBox1.getSelectedItem().toString() +"\""+ "\n";
      CovList = CovList + "correlation = " +"\""+
          jComboBox2.getSelectedItem().toString() +"\""+ "\n";
      CovList = CovList + "}";
      listModel.addElement(CovList);
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if (ob == jComboBox1) {
      jComboBox2.setSelectedIndex(jComboBox1.getSelectedIndex());
    }
    if (ob == jRadioButton1) {
      Datamodel.setValue("var_cov_type", "single");
    }
    if (ob == jRadioButton2) {
      Datamodel.setValue("var_cov_type", "joint");
    }

  }

  class ListMouseListener
      extends MouseAdapter
      implements java.io.Serializable {
    public void mouseClicked(MouseEvent e) {
      int index = jList1.locationToIndex(e.getPoint());
      Object sitem = jList1.getModel().getElementAt(index);

      if (sitem instanceof CheckableItem) {
        CheckableItem item = (CheckableItem) jList1.getModel().getElementAt(
            index);
        item.setSelected(!item.isSelected());
        Rectangle rect = jList1.getCellBounds(index, index);
        jList1.repaint(rect);
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("var_co", false, 109);
  }

  void jRadioButton1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("single ", false, 110);
  }

  void jRadioButton2_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("joint ", false, 110);
  }

  void jList1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 112);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("correlation", false, 112);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("correlation", false, 112);
  }
}

class Fcor_Dialog2_Cov_jRadioButton1_mouseAdapter extends java.awt.event.MouseAdapter {
  Fcor_Dialog2_Cov adaptee;

  Fcor_Dialog2_Cov_jRadioButton1_mouseAdapter(Fcor_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton1_mouseClicked(e);
  }
}

class Fcor_Dialog2_Cov_jRadioButton2_mouseAdapter extends java.awt.event.MouseAdapter {
  Fcor_Dialog2_Cov adaptee;

  Fcor_Dialog2_Cov_jRadioButton2_mouseAdapter(Fcor_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton2_mouseClicked(e);
  }
}

class Fcor_Dialog2_Cov_jList1_mouseAdapter extends java.awt.event.MouseAdapter {
  Fcor_Dialog2_Cov adaptee;

  Fcor_Dialog2_Cov_jList1_mouseAdapter(Fcor_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jList1_mouseClicked(e);
  }
}

class Fcor_Dialog2_Cov_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Fcor_Dialog2_Cov adaptee;

  Fcor_Dialog2_Cov_jComboBox1_mouseAdapter(Fcor_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Fcor_Dialog2_Cov_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  Fcor_Dialog2_Cov adaptee;

  Fcor_Dialog2_Cov_jComboBox2_mouseAdapter(Fcor_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}
