package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class Lodlink_Dialog1
    extends SageDialog
    implements ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();
  XYLayout xYLayout6 = new XYLayout();
  static int group_count = 0;

  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;
  JCheckBox jComboBox1 = new JCheckBox("True");
  JCheckBox jComboBox2 = new JCheckBox("True");
  JLabel jLabel1 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton3 = new JButton();
  JLabel jLabel5 = new JLabel();
  MyComboBox jComboBox3 = new MyComboBox();
  LODLINK2 parent;
  TitledBorder titledBorder2;
  JPanel jPanel3 = new JPanel();

  public Lodlink_Dialog1(LODLINK2 parent, String title) {
    super(title);

    this.parent = parent;

    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("Groups");
    titledBorder2 = new TitledBorder("");

    jComboBox1.setFocusPainted(false);
    jComboBox2.setFocusPainted(false);


    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Add group");
    jTextField1.setText("");
    jTextField1.addMouseListener(new Lodlink_Dialog1_jTextField1_mouseAdapter(this));
    jLabel4.setForeground(Color.red);
    jLabel4.setRequestFocusEnabled(true);
    jLabel4.setToolTipText("<html>Specifies IDs of pedigrees in this group;<br>pedigrees must " +
    "be listed in one and only one group.");
    jLabel4.setText("Pedigree ID(s)");
    jLabel1.setToolTipText(
        "Character string that uniquely names a pedigree group.");
    jLabel1.setText("Group name");

    jPanel2.setLayout(xYLayout5);
    jPanel3.setLayout(xYLayout6);
    jPanel3.setBorder(titledBorder2);

    jComboBox1.addMouseListener(new Lodlink_Dialog1_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new Lodlink_Dialog1_jComboBox2_mouseAdapter(this));
    jComboBox3.setWidth(90);
    jComboBox3.addMouseListener(new Lodlink_Dialog1_jComboBox3_mouseAdapter(this));

    jPanel2.setBorder(titledBorder1);
    jPanel2.setToolTipText(
        "Specifies groups of pedigree IDs to be used for Morton\'s test.");

    jCenterPanel.setPreferredSize(new Dimension(330, 285));

    okButton.setText("OK");
    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jButton3.addActionListener(this);
    jLabel3.setText("<html>Specifies the Morton\'s test.");
    jComboBox1.setText("Perform Morton\'s test");
    jComboBox2.setToolTipText("Testing homogeneity of sex-specific recombination fractions jointly.");
    jComboBox2.setText("Use sex-specific recombination fractions");
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 300, 30));
    jCenterPanel.add(jPanel3, new XYConstraints(16, 15, 298, 70));
    jPanel3.add(jComboBox1, new XYConstraints(5, 5, 250, 20));
    jPanel3.add(jComboBox2, new XYConstraints(25, 32, 250, 20));

    jCenterPanel.add(jPanel2, new XYConstraints(15, 90, 300, 180));
    jPanel2.add(jLabel1, new XYConstraints(10, 5, 75, 20));
    jPanel2.add(jTextField1,  new XYConstraints(90, 5, 190, 20));
    jPanel2.add(jLabel4, new XYConstraints(10, 35, 75, 20));
    jPanel2.add(jButton3,       new XYConstraints(200, 30, 80, 30));
    jPanel2.add(jScrollPane1, new XYConstraints(8, 71, 272, 70));
    jPanel2.add(jLabel5,   new XYConstraints(10, 69, 145, 20));
    jPanel2.add(jComboBox3,    new XYConstraints(90, 35, 100, 20));
    jMenuItemDelete.addActionListener(this);
  }

  public void save_init_state()
  {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }
    Datamodel.setValue("mortons_test_groups", resultinfo);

    if(jComboBox1.isSelected())
      Datamodel.setValue("mortons_test", "true");
    if(jComboBox2.isSelected())
      Datamodel.setValue("mortons_sex_specific","true");

    if (Datamodel.hasValue("mortons_test_groups")) {
      parent.jTextField5.setText("Specified");
      parent.jLabel10.setFont(new java.awt.Font("Dialog", 1, 11));
      Datamodel.setValue("use_mortons_test", "use");
    }
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
      if (!Datamodel.hasValue("use_mortons_test"))
      {
        listModel.removeAllElements();
        jTextField1.setText("");
      }
    }
    else if (button == jButton3)
    {
      if (jTextField1.getText().length() > 0) {
        CheckableItem[] model = jComboBox3.ListData;
        int n = model.length;
        int checkitem = -1;
        for (int i = 0; i < n; i++) {
          CheckableItem item = (CheckableItem) model[i];
          if (item.isSelected()) {
            checkitem++;
          }
        }
        if(checkitem>=0)
          savecontents();

      }
    }
  }

  void savecontents() {
    group_count++;

    String group_name = jTextField1.getText();

    StringBuffer data = new StringBuffer();
    data.append("group = " + group_name + "{" + "\n");

    CheckableItem[] model = jComboBox3.ListData;
    int n = model.length;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      if (item.isSelected()) {
        data.append("pedigree_id = " +"\""+ item.toString() +"\""+ "\n");
      }
    }
    data.append("}");
    listModel.addElement(data);

    jTextField1.setText("");
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      item.setSelected(false);
    }
    jComboBox3.setSelectedItem("");
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
    Datamodel.setValue("mortons_test", "false");
    Datamodel.setValue("mortons_sex_specific", "false");
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("mortons_test", false, 301);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("mortons_test", false, 301);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sex_specifi", false, 301);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("group", false, 302);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pedigree_id", false, 303);
  }
}

class Lodlink_Dialog1_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodlink_Dialog1 adaptee;

  Lodlink_Dialog1_jComboBox1_mouseAdapter(Lodlink_Dialog1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Lodlink_Dialog1_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodlink_Dialog1 adaptee;

  Lodlink_Dialog1_jComboBox2_mouseAdapter(Lodlink_Dialog1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class Lodlink_Dialog1_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodlink_Dialog1 adaptee;

  Lodlink_Dialog1_jTextField1_mouseAdapter(Lodlink_Dialog1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class Lodlink_Dialog1_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodlink_Dialog1 adaptee;

  Lodlink_Dialog1_jComboBox3_mouseAdapter(Lodlink_Dialog1 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}
