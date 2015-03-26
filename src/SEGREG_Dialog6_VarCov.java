package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog6_VarCov
    extends SageDialog
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;

  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JTextField jTextField1 = new JTextField();
  JCheckBox jComboBox3 = new JCheckBox("True");
  JLabel jLabel6 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JButton jAddButton = new JButton();
  JLabel jLabel1 = new JLabel();
  JCheckBox jComboBox2 = new JCheckBox("True");
  JLabel jLabel2 = new JLabel();

  JTextField resulttextfield;
  JLabel resultlabel;

  public SEGREG_Dialog6_VarCov(String title) {
    super(title);

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

    okButton.setEnabled(false);

    okButton.addActionListener(this);
    jLabel3.setText("<html>Specifies covariates for the variance.");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jComboBox3.setEnabled(false);
    jComboBox3.setToolTipText("Specifies whether interaction effects are to be estimated.");
    jComboBox3.setMargin(new Insets(2, 0, 2, 2));
    jComboBox3.setText("Estimate Interaction");
    jComboBox3.addMouseListener(new SEGREG_Dialog6_VarCov_jComboBox3_mouseAdapter(this));
    jComboBox2.setEnabled(false);
    jComboBox2.setToolTipText("<html>Specifies that the covariate coefficient will be fixed<br>at " +
    "the given value, and will not be estimated.");
    jComboBox2.setText("Fixed");
    jComboBox2.addMouseListener(new SEGREG_Dialog6_VarCov_jComboBox2_mouseAdapter(this));
    jLabel6.setForeground(Color.red);
    jLabel6.setToolTipText(
        "<html>Covariate to modify the variance (conditional on type)" +
        "<br>variance of a quantitative trait.");
    jLabel6.setText("Covariate");
    jComboBox1.addItemListener(this);
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add covariate");
    jLabel2.setToolTipText("Specifies value of the covariate coefficient.");
    jLabel2.setText("Value");
    jTextField1.setEnabled(false);
    jTextField1.addMouseListener(new SEGREG_Dialog6_VarCov_jTextField1_mouseAdapter(this));
    jComboBox1.addMouseListener(new SEGREG_Dialog6_VarCov_jComboBox1_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 288, 30));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 10, 300, 254));
    jPanel1.add(jLabel6, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jComboBox1,  new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jComboBox3,    new XYConstraints(30, 65, 150, 20));
    jPanel1.add(jTextField1, new XYConstraints(90, 35, 90, 20));
    jPanel1.add(jAddButton,   new XYConstraints(185, 60, 95, 30));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 101, 272, 133));
    jPanel1.add(jLabel1, new XYConstraints(10, 129, 156, 20));
    jPanel1.add(jComboBox2,  new XYConstraints(200, 35, 90, 20));
    jPanel1.add(jLabel2, new XYConstraints(30, 35, 50, 20));

    jComboBox1.setFocusable(false);
    jComboBox2.setFocusable(false);
    jComboBox3.setFocusable(false);

    jComboBox1.addItemListener(this);
    jTextField1.getDocument().addDocumentListener(this);
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
    Datamodel.setValue("var_cov_info", resultinfo.trim());
    Datamodel.setValue("var_cov", "use");

    resulttextfield.setText("Specified");
    resultlabel.setFont(new java.awt.Font("Dialog", 1, 11));
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
       if (!Datamodel.hasValue("var_cov"))
         listModel.removeAllElements();
       jComboBox1.setSelectedIndex(0);
    }

    else if (button == jAddButton) {
      int removei = jComboBox1.getSelectedIndex();

      String cov = "covariate = " + "\""+ jComboBox1.getSelectedItem().toString()+ "\"";
      if (jTextField1.getText().length() > 0) {
        cov = cov + ", val = " + "\""+ jTextField1.getText()+ "\"";
        if(jComboBox2.isSelected())
            cov = cov + ", fixed = " + "\"true\"";
      }
      if(jComboBox3.isSelected())
          cov = cov + ", interaction = " + "\"true\"";

      listModel.addElement(cov);

      jTextField1.setText("");
      jComboBox2.setSelected(false);
      jComboBox3.setSelected(false);
      jComboBox1.updateUI();
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
        jComboBox3.setEnabled(false);
      }
      else {
        jAddButton.setEnabled(true);
        jTextField1.setEnabled(true);
        jComboBox3.setEnabled(true);
      }
    }

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 157);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 157);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 157);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fixed", false, 157);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("interaction", false, 157);
  }
}

class SEGREG_Dialog6_VarCov_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog6_VarCov adaptee;

  SEGREG_Dialog6_VarCov_jComboBox1_mouseAdapter(SEGREG_Dialog6_VarCov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog6_VarCov_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog6_VarCov adaptee;

  SEGREG_Dialog6_VarCov_jTextField1_mouseAdapter(SEGREG_Dialog6_VarCov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog6_VarCov_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog6_VarCov adaptee;

  SEGREG_Dialog6_VarCov_jComboBox2_mouseAdapter(SEGREG_Dialog6_VarCov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SEGREG_Dialog6_VarCov_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog6_VarCov adaptee;

  SEGREG_Dialog6_VarCov_jComboBox3_mouseAdapter(SEGREG_Dialog6_VarCov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}
