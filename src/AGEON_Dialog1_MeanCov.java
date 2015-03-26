package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.*;

public class AGEON_Dialog1_MeanCov
    extends SageDialog
    implements DocumentListener, ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;

  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JTextField jTextField1 = new JTextField();
  JCheckBox jComboBox2 = new JCheckBox();
  JLabel jLabel6 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JButton jAddButton = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();

  AGEON2 parent;

  public AGEON_Dialog1_MeanCov(AGEON2 parent, String title) {
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

    jComboBox2.setFocusPainted(false);
    jLabel3.setText("<html>Specifies the <i>mean covariates</i> sub-block.");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jComboBox2.setEnabled(false);
    jComboBox2.setToolTipText("<html>Specifies that the covariate coefficient will be fixed<br>at " +
    "the given value, and will not be estimated.");
    jComboBox2.setMargin(new Insets(2, 0, 2, 2));
    jComboBox2.setText("Fixed");
    jComboBox2.addMouseListener(new AGEON_Dialog1_MeanCov_jComboBox2_mouseAdapter(this));
    jLabel6.setForeground(Color.red);
    jLabel6.setToolTipText(
        "Covariate to modify the mean of a quantitative trait.");
    jLabel6.setText("Covariate");
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add covariate");

    jLabel2.setToolTipText("Specifies the value of the covariate coefficient.");
    jLabel2.setText("Value");
    jTextField1.setEnabled(false);
    jTextField1.addMouseListener(new AGEON_Dialog1_MeanCov_jTextField1_mouseAdapter(this));
    jComboBox1.addMouseListener(new AGEON_Dialog1_MeanCov_jComboBox1_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 288, 30));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 10, 300, 254));
    jPanel1.add(jLabel6, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jComboBox1,   new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jComboBox2,     new XYConstraints(200, 35, 90, 20));
    jPanel1.add(jTextField1, new XYConstraints(90, 35, 90, 20));
    jPanel1.add(jAddButton,   new XYConstraints(185, 60, 95, 30));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 101, 272, 133));
    jPanel1.add(jLabel1, new XYConstraints(10, 99, 156, 20));
    jPanel1.add(jLabel2, new XYConstraints(30, 35, 55, 20));

    jComboBox1.setFocusable(false);
    jComboBox2.setFocusable(false);

    jTextField1.getDocument().addDocumentListener(this);
    jAddButton.addActionListener(this);
    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jComboBox1.addItemListener(this);

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
    Datamodel.setValue("mean_cov_info", resultinfo.trim());

    if (Datamodel.hasValue("mean_cov_info")) {
      parent.jTextField1.setText("Specified");
      parent.jLabel2.setFont(new java.awt.Font("Dialog", 1, 11));
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
      if(!Datamodel.hasValue("mean_cov_info"))
        listModel.removeAllElements();
      jComboBox1.setSelectedIndex(0);
    }
    else if (button == jAddButton) {
      String cov = "covariate = " + "\""+ jComboBox1.getSelectedItem().toString()+ "\"";
      if (jTextField1.getText().length() > 0) {
        cov = cov + ", val = " + "\""+ jTextField1.getText()+ "\"";
        if(jComboBox2.isSelected())
          cov = cov + ", fixed = " + "\"true\"";
      }
      listModel.addElement(cov);

      jTextField1.setText("");
      jComboBox1.setSelectedIndex(0);
      jComboBox2.setSelected(false);
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      if (jTextField1.getText().length() <= 0) {
        jComboBox2.setEnabled(false);
      }
      else {
        jComboBox2.setEnabled(true);
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
      }
      else {
        jAddButton.setEnabled(true);
        jTextField1.setEnabled(true);
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("covariate ", false, 380);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate ", false, 380);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", val ", false, 380);

  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", fixe", false, 380);
  }


}

class AGEON_Dialog1_MeanCov_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog1_MeanCov adaptee;

  AGEON_Dialog1_MeanCov_jComboBox1_mouseAdapter(AGEON_Dialog1_MeanCov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class AGEON_Dialog1_MeanCov_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog1_MeanCov adaptee;

  AGEON_Dialog1_MeanCov_jTextField1_mouseAdapter(AGEON_Dialog1_MeanCov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class AGEON_Dialog1_MeanCov_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog1_MeanCov adaptee;

  AGEON_Dialog1_MeanCov_jComboBox2_mouseAdapter(AGEON_Dialog1_MeanCov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}
