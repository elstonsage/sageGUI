package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DECIPHER_Dialog1_Partition2
    extends SageDialog
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  JLabel jLabel6 = new JLabel();
  TitledBorder titledBorder1;

  DataCollectionModel Datamodel;

  JComboBox jComboBox1 = new JComboBox();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JButton jAddButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField1 = new JTextField();

  DECIPHER2 parent;

  public DECIPHER_Dialog1_Partition2(DECIPHER2 parent, String title) {
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
    jLabel3.setText("Specifies the second level partition variable.");
    jLabel6.setToolTipText(
            "Indicate the variable to specify the  second level partition.");
    jLabel6.setText("Partition");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel1.setToolTipText("Name of subpopulation.");
    jLabel1.setText("Subpopulation");
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add subpopulation");
    jLabel4.setToolTipText("<html>Specifies value of the partition variable common<br>to all individuals in this subpopulation.");
    jLabel4.setText("Partition Value");
    jTextField2.setEnabled(false);
    jTextField2.addMouseListener(new DECIPHER_Dialog1_Partition2_jTextField2_mouseAdapter(this));
    jTextField1.setEnabled(false);
    jTextField1.setText("");
    jTextField1.addMouseListener(new DECIPHER_Dialog1_Partition2_jTextField1_mouseAdapter(this));
    jComboBox1.addMouseListener(new DECIPHER_Dialog1_Partition2_jComboBox1_mouseAdapter(this));
     jTopPanel.add(jLabel3, new XYConstraints(15, 3, 300, 30));
    jCenterPanel.add(jLabel6, new XYConstraints(15, 15, 47, 20));
    jCenterPanel.add(jComboBox1, new XYConstraints(70, 15, 240, 20));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 45, 300, 220));

    jPanel1.add(jLabel1, new XYConstraints(10, 5, 90, 20));
    jPanel1.add(jTextField1,     new XYConstraints(95, 5, 80, 20));
    jPanel1.add(jLabel4,  new XYConstraints(10, 35, 90, 20));
    jPanel1.add(jTextField2,  new XYConstraints(95, 35, 80, 20));


    jPanel1.add(jAddButton,          new XYConstraints(183, 25, 100, 30));
    jPanel1.add(jLabel2,  new XYConstraints(10, 69, 156, 20));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 71, 272, 126));

    jComboBox1.setFocusable(false);

    jComboBox1.addItemListener(this);
    jTextField2.getDocument().addDocumentListener(this);
    jTextField1.getDocument().addDocumentListener(this);
    jAddButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jMenuItemDelete.addActionListener(this);
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
  }

  public void save_init_state()
  {
    String partition = "partition = \""+jComboBox1.getSelectedItem().toString()+"\"";
    if(listModel.size()>0)
    {
      String resultinfo = new String();
      for (int i = 0; i < listModel.size(); i++) {
        resultinfo = resultinfo + listModel.get(i).toString() + "\n";
      }
      partition = partition + "\n{\n" + resultinfo + "}\n";
    }
    else
    {
      partition = partition + "\n{\n}\n";
    }

    Datamodel.setValue("partition2_info", partition);
    Datamodel.setValue("partition2", "use");
    parent.jTextField4.setText("Specified");
    parent.jLabel12.setFont(new java.awt.Font("Dialog", 1, 11));
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
      String sub = "sub_pop = \"" + jTextField1.getText().trim()+"\"";
      sub = sub + ", sub_pop_value = "+"\""+ jTextField2.getText().trim()+"\"";

      listModel.addElement(sub);
      jTextField1.setText("");
      jTextField2.setText("");
      jTextField2.requestFocus(true);
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      if (jTextField1.getText().length() > 0) {
        Datamodel.setValue("sub_pop","true");
      }
      else {
        Datamodel.removeValue("sub_pop");
      }
    }
    if (document == jTextField2.getDocument()) {
      if (jTextField2.getText().length() > 0) {
        Datamodel.setValue("sub_pop_value","true");
      }
      else {
        Datamodel.removeValue("sub_pop_value");
      }
    }
    if(Datamodel.hasValue("sub_pop") && Datamodel.hasValue("sub_pop_value"))
    {
      jAddButton.setEnabled(true);
    }
    else
    {
      jAddButton.setEnabled(false);
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
    if(ob == jComboBox1)
    {
      if(jComboBox1.getSelectedIndex()==0)
      {
        jTextField2.setEnabled(false);
        jTextField1.setEnabled(false);
        okButton.setEnabled(false);
      }
      else
      {
          jTextField2.setEnabled(true);
          jTextField1.setEnabled(true);
          okButton.setEnabled(true);
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("partition", false, 409);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("partition", false, 409);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sub_pop ", false, 412);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", sub_pop_value", false, 412);
  }
}

class DECIPHER_Dialog1_Partition2_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog1_Partition2 adaptee;

  DECIPHER_Dialog1_Partition2_jComboBox1_mouseAdapter(DECIPHER_Dialog1_Partition2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class DECIPHER_Dialog1_Partition2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog1_Partition2 adaptee;

  DECIPHER_Dialog1_Partition2_jTextField1_mouseAdapter(DECIPHER_Dialog1_Partition2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class DECIPHER_Dialog1_Partition2_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog1_Partition2 adaptee;

  DECIPHER_Dialog1_Partition2_jTextField2_mouseAdapter(DECIPHER_Dialog1_Partition2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}




