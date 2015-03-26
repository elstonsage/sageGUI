package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class DECIPHER_Dialog4_Region
    extends SageDialog
    implements ItemListener, ActionListener {

  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;

  DataCollectionModel Datamodel;

  JComboBox jComboBox1 = new JComboBox();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JButton jAddButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();

  DECIPHER2 parent;
  JComboBox jComboBox2 = new JComboBox();
  JComboBox jComboBox3 = new JComboBox();

  public DECIPHER_Dialog4_Region(DECIPHER2 parent, String title) {
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
    titledBorder1 = new TitledBorder("");

    okButton.setText("OK");
    okButton.setEnabled(false);
    okButton.addActionListener(this);
    jLabel3.setText("Specifies a region for haplotype determination.");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel1.setForeground(Color.red);
    jLabel1.setToolTipText("");
    jLabel1.setText("Region");
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add region");

    jLabel4.setToolTipText("Names the last marker in the region.");
    jLabel4.setText("Last marker");

    jLabel2.setToolTipText("Names the first marker in the region.");
    jLabel2.setText("First marker");
    jComboBox1.addMouseListener(new DECIPHER_Dialog4_Region_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new DECIPHER_Dialog4_Region_jComboBox2_mouseAdapter(this));
    jComboBox3.addMouseListener(new DECIPHER_Dialog4_Region_jComboBox3_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 300, 30));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 10, 300, 254));
    jPanel1.add(jLabel1,   new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jAddButton,                 new XYConstraints(190, 55, 90, 30));
    jPanel1.add(jLabel2,        new XYConstraints(20, 35, 80, 20));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 101, 272, 133));
    jPanel1.add(jLabel4,     new XYConstraints(20, 65, 75, 20));
    jPanel1.add(jComboBox1,      new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jComboBox2,     new XYConstraints(90, 35, 90, 20));
    jPanel1.add(jComboBox3,     new XYConstraints(90, 65, 90, 20));

    jComboBox1.setFocusable(false);
    jComboBox1.addItemListener(this);

    jComboBox2.setEnabled(false);
    jComboBox3.setEnabled(false);

    jAddButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jMenuItemDelete.addActionListener(this);
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
  }

  public void save_init_state()
  {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }

    Datamodel.setValue("region_info", resultinfo);
    Datamodel.setValue("region", "use");
    parent.jTextField7.setText("Specified");
    parent.jLabel5.setFont(new java.awt.Font("Dialog", 1, 11));
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
      String sub = "region = \"" + jComboBox1.getSelectedItem()+"\"";
      sub = sub + ", first = "+"\""+ jComboBox2.getSelectedItem()+"\"";
      sub = sub + ", last = "+"\""+ jComboBox3.getSelectedItem()+"\"";

      listModel.addElement(sub);
      jComboBox2.setSelectedIndex(0);
      jComboBox3.setSelectedIndex(0);
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if(ob == jComboBox1)
    {
      if(jComboBox1.getSelectedItem().toString().length() <= 0)
      {
        jAddButton.setEnabled(false);
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
      }
      else
      {
        jAddButton.setEnabled(true);
        jComboBox2.setEnabled(true);
        jComboBox3.setEnabled(true);
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("region", false, 403);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("region", false, 403);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("first", false, 403);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("last ", false, 403);
  }

}

class DECIPHER_Dialog4_Region_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog4_Region adaptee;

  DECIPHER_Dialog4_Region_jComboBox1_mouseAdapter(DECIPHER_Dialog4_Region adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class DECIPHER_Dialog4_Region_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog4_Region adaptee;

  DECIPHER_Dialog4_Region_jComboBox2_mouseAdapter(DECIPHER_Dialog4_Region adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class DECIPHER_Dialog4_Region_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog4_Region adaptee;

  DECIPHER_Dialog4_Region_jComboBox3_mouseAdapter(DECIPHER_Dialog4_Region adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}




