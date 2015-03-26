package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class SEGREG_Dialog15_PrevE
    extends SageDialog
    implements ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;

  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JButton jAddButton = new JButton();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField3 = new JTextField();

  JTextField resulttextfield;
  JLabel resultlabel;

  String message = "Any covariate in this sub-block must also\n"+
      "appear in the susceptibility covariates sub-block.";
  boolean IsPopup = true;

  public SEGREG_Dialog15_PrevE(String title) {
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

    jLabel3.setText("Specifies the prevalence estimate.");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel6.setForeground(Color.red);
    jLabel6.setToolTipText(
        "<html>Specifies a covariate on which prevalence" +
        "<br>(cumulative incidence since birth) depends.");
    jLabel6.setText("Covariate");
    jComboBox1.addItemListener(this);
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add covariate");
    jLabel5.setToolTipText(
        "<html>Specifies a value for the covariate at which<br>prevalence is calculated.");
    jLabel5.setText("Value");
    jTextField1.setEnabled(false);
    jTextField1.addMouseListener(new SEGREG_Dialog15_PrevE_jTextField1_mouseAdapter(this));
    jLabel2.setToolTipText(
        "<html>Specifies the age at which prevalence should be calculated.");
    jLabel2.setText("Age");
    jTextField3.addMouseListener(new SEGREG_Dialog15_PrevE_jTextField3_mouseAdapter(this));
    jComboBox1.addMouseListener(new SEGREG_Dialog15_PrevE_jComboBox1_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 330, 30));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 45, 300, 220));
    jPanel1.add(jLabel6, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jComboBox1,  new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jTextField1, new XYConstraints(90, 35, 90, 20));
    jPanel1.add(jAddButton,  new XYConstraints(185, 30, 95, 30));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 71, 272, 130));
    jPanel1.add(jLabel1, new XYConstraints(10, 69, 156, 20));
    jPanel1.add(jLabel5, new XYConstraints(30, 35, 55, 20));
    jCenterPanel.add(jLabel2, new XYConstraints(20, 15, 46, 21));
    jCenterPanel.add(jTextField3, new XYConstraints(70, 15, 240, 20));

    jComboBox1.setFocusable(false);

    jAddButton.addActionListener(this);
    okButton.addActionListener(this);
    jComboBox1.addItemListener(this);
    cancelButton.addActionListener(this);
    jMenuItemDelete.addActionListener(this);
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
  }

  public void save_init_state(boolean isshow)
  {
    IsPopup = isshow;
    if (jTextField3.getText().length() > 0) {
      Datamodel.setValue("prev_estimate_age", jTextField3.getText());
    }

    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }
    Datamodel.setValue("prev_estimate_info", resultinfo.trim());
    Datamodel.setValue("prev_estimate", "use");

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
      save_init_state(true);
      if(IsPopup)
        JOptionPane.showConfirmDialog(this,
                              message, "Warning",
                              JOptionPane.CLOSED_OPTION,
                              JOptionPane.INFORMATION_MESSAGE);
      IsPopup = true;
      dispose();
    }
    else if (button == jAddButton) {
      String cov = "covariate = " + "\""+ jComboBox1.getSelectedItem().toString()+ "\"";
      if (jTextField1.getText().length() > 0) {
        cov = cov + ", val = " + "\""+ jTextField1.getText()+ "\"";
      }
      listModel.addElement(cov);

      jTextField1.setText("");
      jComboBox1.updateUI();
    }
    else if (button == cancelButton) {
       dispose();
       if (!Datamodel.hasValue("prev_estimate"))
         listModel.removeAllElements();
       jComboBox1.setSelectedIndex(0);
    }
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
      Frame1.mainFrame1.pdfframe.setTextonPage("age", false, 180);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("age", false, 180);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 180);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 180);
  }
}

class SEGREG_Dialog15_PrevE_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog15_PrevE adaptee;

  SEGREG_Dialog15_PrevE_jTextField3_mouseAdapter(SEGREG_Dialog15_PrevE adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG_Dialog15_PrevE_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog15_PrevE adaptee;

  SEGREG_Dialog15_PrevE_jComboBox1_mouseAdapter(SEGREG_Dialog15_PrevE adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog15_PrevE_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog15_PrevE adaptee;

  SEGREG_Dialog15_PrevE_jTextField1_mouseAdapter(SEGREG_Dialog15_PrevE adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}
