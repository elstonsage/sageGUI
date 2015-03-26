package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog14_PrevC
    extends SageDialog
    implements DocumentListener, ActionListener {

  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;

  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel6 = new JLabel();
  JButton jAddButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JTextField jTextField5 = new JTextField();

  JTextField covTextField = new JTextField();
  JButton covDefineButton = new JButton();
  JButton covResetButton = new JButton();

  JTextField resulttextfield;
  JLabel resultlabel;

  String message = "Any covariate in this sub-block must also\n"+
      "appear in the susceptibility covariates sub-block.";

  boolean IsPopup = true;

  SEGREG_Dialog14_PrevC_cov covDialog = new SEGREG_Dialog14_PrevC_cov(this, "Specification");

  public SEGREG_Dialog14_PrevC(String title) {
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

    covDefineButton.setMargin(new Insets(2, 2, 2, 2));
        covDefineButton.setText("Define");
        covResetButton.setMargin(new Insets(2, 2, 2, 2));
        covResetButton.setText("Reset");
    covTextField.setText("");

    this.setSize(new Dimension(370, 370));
    this.setResizable(false);

    jTopPanel.setPreferredSize(new Dimension(370, 40));
    jCenterPanel.setPreferredSize(new Dimension(370, 290));
    jBottomPanel.setPreferredSize(new Dimension(370, 40));

    jLabel3.setText(
        "Specifies constraints on the population prevalence of a binary trait.");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);

    jLabel6.setToolTipText(
        "<html>Specifies a covariate on which prevalence" +
        "<br>(cumulative incidence since birth) depends.");
    jLabel6.setText("Covariate");
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add constraint");
    jLabel2.setForeground(Color.red);
    jLabel2.setToolTipText("Specifies the number of people in this sample.");
    jLabel2.setText("Number of affecteds");
    jLabel4.setForeground(Color.red);
    jLabel4.setToolTipText(
        "Specifies the size of an independent random sample.");
    jLabel4.setText("Sample size");
    jTextField3.setText("");
    jTextField3.addMouseListener(new SEGREG_Dialog14_PrevC_jTextField3_mouseAdapter(this));
    jLabel5.setToolTipText(
        "Specifies a value for the covariate at which the prevalence holds.");
    jLabel5.setText("Value");
    jLabel7.setToolTipText(
        "<html>Specifies the age at which prevalence (cumulative since birth) should " +
        "<br>be computed. Required for age of onset traits, and disallowed otherwise.");
    jLabel7.setText("Age");
    jTextField5.setText("");
    jTextField5.addMouseListener(new SEGREG_Dialog14_PrevC_jTextField5_mouseAdapter(this));
    jTextField4.addMouseListener(new SEGREG_Dialog14_PrevC_jTextField4_mouseAdapter(this));

    covTextField.setEditable(false);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 330, 30));

   jCenterPanel.add(jPanel1, new XYConstraints(15, 15, 340, 260));

   jPanel1.add(jLabel6, new XYConstraints(10, 5, 100, 20));
   jPanel1.add(covTextField, new XYConstraints(120, 5, 100, 20));
   jPanel1.add(covDefineButton, new XYConstraints(226, 5, 45, 20));
   jPanel1.add(covResetButton, new XYConstraints(275, 5, 45, 20));
   jPanel1.add(jLabel2, new XYConstraints(10, 35, 102, 20));
   jPanel1.add(jTextField3, new XYConstraints(120, 35, 200, 20));
   jPanel1.add(jLabel4, new XYConstraints(10, 65, 65, 20));
   jPanel1.add(jTextField4, new XYConstraints(120, 65, 200, 20));
   jPanel1.add(jLabel7, new XYConstraints(10, 95, 50, 18));
   jPanel1.add(jTextField5, new XYConstraints(120, 95, 100, 20));

    jPanel1.add(jAddButton,   new XYConstraints(225, 90, 95, 30));

    jPanel1.add(jScrollPane1, new XYConstraints(8, 130, 308, 107));

    jTextField3.getDocument().addDocumentListener(this);
    jTextField4.getDocument().addDocumentListener(this);

    jAddButton.addActionListener(this);
    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jMenuItemDelete.addActionListener(this);
    covDefineButton.addActionListener(this);
    covResetButton.addActionListener(this);

  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;

  }

  public void save_init_state(boolean isshow)
  {
    IsPopup = isshow;

    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {

      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }

    Datamodel.setValue("prev_constraint", resultinfo.trim());

    resulttextfield.setText("Specified");
    resultlabel.setFont(new java.awt.Font("Dialog", 1, 11));

  }


  void covResetButton_actionPerformed()
  {
      if(Datamodel.hasValue("prev_cov_info"))
      {
        covTextField.setText("");
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("prev_cov_info");
        covDialog.listModel.removeAllElements();
      }
  }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();
    if(button == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }

    else if(button == covDefineButton) // define cov
    {
      Point point = this.getLocation();
      Dimension d = this.getPreferredSize();

      int x_location = point.x + d.width;
      int y_location = point.y + 40;

      covDialog.set_dataModel(Datamodel);
      covDialog.showDialog(x_location, y_location);

    }

    else if(button == covResetButton) // reset cov
    {
        covResetButton_actionPerformed();
    }
    else if (button == okButton) {
      save_init_state(true);

      if (Datamodel.hasValue("prev_cov_info")) {
          if (IsPopup)
              JOptionPane.showConfirmDialog(this,
                                            message, "Warning",
                                            JOptionPane.CLOSED_OPTION,
                                            JOptionPane.
                                            INFORMATION_MESSAGE);
          IsPopup = true;
      }

     dispose();
    }
    else if (button == jAddButton) {

      StringBuffer cons = new StringBuffer();

      cons.append("constraint\n{\n");

      if(Datamodel.hasValue("prev_cov_info"))
      {
          cons.append(Datamodel.getValue("prev_cov_info")+"\n");
          covResetButton_actionPerformed();
      }

      if (jTextField3.getText().length() > 0) {
        cons.append("R = " + "\""+ jTextField3.getText()+ "\"\n");
      }

      if (jTextField4.getText().length() > 0) {
        cons.append("N = " + "\""+ jTextField4.getText()+ "\"\n");
      }

      if (jTextField5.getText().length() > 0) {
        cons.append("age = " + "\""+ jTextField5.getText()+ "\"\n");
      }

      jTextField3.setText("");
      jTextField4.setText("");
      jTextField5.setText("");

      cons.append("}");

      listModel.addElement(cons.toString());
    }

    else if (button == cancelButton) {
       dispose();
    }

  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();

    if(jTextField3.getText().length() > 0 && jTextField4.getText().length() > 0)
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

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("N", false, 178);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("N", false, 178);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("R", false, 178);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("age", false, 178);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 178);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 178);
  }
}

class SEGREG_Dialog14_PrevC_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog14_PrevC adaptee;

  SEGREG_Dialog14_PrevC_jTextField4_mouseAdapter(SEGREG_Dialog14_PrevC adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class SEGREG_Dialog14_PrevC_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog14_PrevC adaptee;

  SEGREG_Dialog14_PrevC_jTextField3_mouseAdapter(SEGREG_Dialog14_PrevC adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG_Dialog14_PrevC_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog14_PrevC adaptee;

  SEGREG_Dialog14_PrevC_jTextField5_mouseAdapter(SEGREG_Dialog14_PrevC adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class SEGREG_Dialog14_PrevC_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog14_PrevC adaptee;

  SEGREG_Dialog14_PrevC_jComboBox1_mouseAdapter(SEGREG_Dialog14_PrevC adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog14_PrevC_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog14_PrevC adaptee;

  SEGREG_Dialog14_PrevC_jTextField1_mouseAdapter(SEGREG_Dialog14_PrevC adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}
