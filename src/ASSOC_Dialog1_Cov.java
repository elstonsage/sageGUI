package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ASSOC_Dialog1_Cov
    extends SageDialog
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;

  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();

  ASSOC2 parent;

  JCheckBox jComboBox3 = new JCheckBox("True");
  JLabel jLabel6 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JButton jAddButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField1 = new JTextField();

  public ASSOC_Dialog1_Cov(ASSOC2 parent, String title) {
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

    this.setSize(new Dimension(330, 370));

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jCenterPanel.setPreferredSize(new Dimension(330, 290));
    jBottomPanel.setPreferredSize(new Dimension(330, 40));

    okButton.setText("OK");
    jComboBox3.setFocusPainted(false);

    jLabel3.setText("<html>Specifies a trait or covariates in the regression model.");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jComboBox3.setEnabled(false);
    jComboBox3.setToolTipText("<html>Specifies that the covariate coefficient will be fixed"+
                              "<br>at the given value, and will not be estimated.");
    jComboBox3.setMargin(new Insets(2, 0, 2, 2));
    jComboBox3.setText("Fixed");
    jComboBox3.addMouseListener(new ASSOC_Dialog1_Cov_jComboBox3_mouseAdapter(this));
    jLabel6.setForeground(Color.red);
    jLabel6.setToolTipText(
            "Specifies a covariate in the regression model.");
    jLabel6.setText("Covariate");
    jComboBox1.addItemListener(this);
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add covariate");

    jLabel2.setToolTipText(
            "<html>Input the model names in comma-separated format "+
            "<br>(eg. M1, M2, …) in which the covariate is to be "+
            "<br>included as a test covariate. [May be left blank]");
    jLabel2.setText("Models");
    jLabel4.setToolTipText(
        "Specifies the initial estimate for the covariate coefficient.");
    jLabel4.setText("Value");
    jTextField3.setEnabled(false);
    jTextField3.setText("");
    jTextField3.addMouseListener(new ASSOC_Dialog1_Cov_jTextField3_mouseAdapter(this));
    jComboBox1.addMouseListener(new ASSOC_Dialog1_Cov_jComboBox1_mouseAdapter(this));
    jTextField1.addMouseListener(new ASSOC_Dialog1_Cov_jTextField1_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 288, 30));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 10, 300, 264));
    jPanel1.add(jLabel6, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jComboBox1,    new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jLabel2,   new XYConstraints(30, 35, 50, 20));
    jPanel1.add(jTextField1,   new XYConstraints(90, 35, 190, 20));
    jPanel1.add(jLabel4,   new XYConstraints(30, 65, 50, 20));
    jPanel1.add(jTextField3,   new XYConstraints(90, 65, 90, 20));
    jPanel1.add(jComboBox3,   new XYConstraints(200, 65, 90, 20));
    jPanel1.add(jAddButton,      new XYConstraints(185, 90, 95, 30));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 131, 272, 113));

    jComboBox1.setFocusable(false);
    jComboBox3.setFocusable(false);
    jComboBox1.addItemListener(this);
    jTextField3.getDocument().addDocumentListener(this);
    jAddButton.addActionListener(this);
    okButton.addActionListener(this);
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
    Datamodel.setValue("assoc_cov", resultinfo.trim());
    parent.covTextField.setText("Specified");
    parent.covLabel.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {

    Object button = e.getSource();
   if(button == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }

    if (button == okButton) {
      save_init_state();
      dispose();

    }
    else if (button == cancelButton) {
      dispose();
      if(!Datamodel.hasValue("assoc_cov"))
        listModel.removeAllElements();
    }

    else if (button == jAddButton) { // Add Button
      String cov = "covariate = \"" + jComboBox1.getSelectedItem().toString()+"\"";

      if(jTextField1.getText().length() > 0)
      {
        cov = cov + ", models = "+"\""+ jTextField1.getText()+"\"";;
      }

      if (jTextField3.getText().length() > 0) {
        cov = cov + ", val = " +"\""+ jTextField3.getText()+"\"";
        if(jComboBox3.isSelected())
          cov = cov + ", fixed = " +"\"true\"";
      }
      listModel.addElement(cov);
      jTextField1.setText("");
      jTextField3.setText("");
      jComboBox3.setSelected(false);
      jComboBox1.setSelectedIndex(0);
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField3.getDocument()) {
      if (jTextField3.getText().length() <= 0) {
        jComboBox3.setEnabled(false);
      }
      else {
        jComboBox3.setEnabled(true);
      }
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
      if (jComboBox1.getSelectedIndex() == 0) {
        jAddButton.setEnabled(false);
        jTextField1.setEnabled(false);
        jTextField3.setEnabled(false);
      }
      else {
        jAddButton.setEnabled(true);
        jTextField1.setEnabled(true);
        jTextField3.setEnabled(true);
      }
    }

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 330);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 330);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", models", false, 331);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", val", false, 331);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", fixe", false, 331);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("no_transform", false, 331);
  }

}

class ASSOC_Dialog1_Cov_jComboBox1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC_Dialog1_Cov adaptee;

  ASSOC_Dialog1_Cov_jComboBox1_mouseAdapter(ASSOC_Dialog1_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class ASSOC_Dialog1_Cov_jTextField1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC_Dialog1_Cov adaptee;

 ASSOC_Dialog1_Cov_jTextField1_mouseAdapter(ASSOC_Dialog1_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}
class ASSOC_Dialog1_Cov_jTextField3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC_Dialog1_Cov adaptee;

 ASSOC_Dialog1_Cov_jTextField3_mouseAdapter(ASSOC_Dialog1_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class ASSOC_Dialog1_Cov_jComboBox3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  ASSOC_Dialog1_Cov adaptee;

  ASSOC_Dialog1_Cov_jComboBox3_mouseAdapter(ASSOC_Dialog1_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class ASSOC_Dialog1_Cov_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC_Dialog1_Cov adaptee;

  ASSOC_Dialog1_Cov_jComboBox2_mouseAdapter(ASSOC_Dialog1_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}
