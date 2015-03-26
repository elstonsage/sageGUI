package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog4_TypeSuscept
    extends SageDialog
    implements DocumentListener, java.awt.event.ItemListener, ActionListener{
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  JLabel jLabel6 = new JLabel();
  TitledBorder titledBorder1;

  DataCollectionModel Datamodel;

  String[] option = {"One","Two","Three",
      "Two dominant","Two recessive","Three additive","Three decreasing","Three increasing"};
  String[] option_real = {"one","two","three",
      "two_dom","two_rec","three_add","three_dec","three_inc"};

  String[] mean={"","\u03B2AA","\u03B2AB","\u03B2BB","\u03B2AA=\u03B2AB","\u03B2BB=\u03B2AB","\u03B2AA=\u03B2AB=\u03B2BB"};
  String[] realmean={"","AA","AB","BB","A*","B*","**"};

  String message = "If any mean or logit susceptibility is fixed, initial values must be entered for all.";

  JComboBox jComboBox1 = new JComboBox(option);
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JComboBox jComboBox2 = new JComboBox(mean);
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JCheckBox fixedCheckBox = new JCheckBox("True");
  JButton jAddButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();

  SEGREG4 segreg4=null;
  SEGREG5 segreg5=null;

  public SEGREG_Dialog4_TypeSuscept(JPanel parent, String title) {
    super(title);
    if(parent instanceof SEGREG4)
      this.segreg4 = (SEGREG4)parent;
    if(parent instanceof SEGREG5)
      this.segreg5 = (SEGREG5)parent;


      try {
          jbInit();
          pack();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");

    okButton.setEnabled(true);
    okButton.addActionListener(this);
    jLabel3.setText("<html>Specifies the number of type susceptibilities.");
    jLabel6.setRequestFocusEnabled(true);
    jLabel6.setToolTipText("Specifies type susceptibility option.");
    jLabel6.setText("Option");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel1.setToolTipText("Specifies the effect of a type on the logit scale.");
    jLabel1.setText("Logit Susceptibility");
    fixedCheckBox.setEnabled(false);
    fixedCheckBox.setToolTipText("Specifies option to fix the given value.");
    fixedCheckBox.setText("Fixed");
    fixedCheckBox.addMouseListener(new SEGREG_Dialog4_TypeSuscept_jComboBox3_mouseAdapter(this));
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add susceptibility");
    jLabel4.setToolTipText("Specifies value of a given mean.");
    jLabel4.setText("Value");
    jTextField1.setEnabled(false);
    jTextField1.addMouseListener(new SEGREG_Dialog4_TypeSuscept_jTextField1_mouseAdapter(this));
    jComboBox1.addMouseListener(new SEGREG_Dialog4_TypeSuscept_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new SEGREG_Dialog4_TypeSuscept_jComboBox2_mouseAdapter(this));
    jTopPanel.add(jLabel3,     new XYConstraints(15, 3, 300, 30));
    jCenterPanel.add(jLabel6,     new XYConstraints(15, 15, 47, 20));
    jCenterPanel.add(jComboBox1,   new XYConstraints(70, 15, 240, 20));
    jCenterPanel.add(jPanel1,    new XYConstraints(15, 45, 300, 220));
    jPanel1.add(jLabel1,  new XYConstraints(10, 5, 100, 20));
    jPanel1.add(jTextField1,   new XYConstraints(110, 35, 85, 20));
    jPanel1.add(fixedCheckBox,    new XYConstraints(210, 35, 70, 20));
    jPanel1.add(jComboBox2,     new XYConstraints(110, 5, 170, 20));
    jPanel1.add(jAddButton,            new XYConstraints(185, 60, 95, 30));
    jPanel1.add(jLabel2,   new XYConstraints(10, 99, 156, 20));
    jPanel1.add(jScrollPane1,        new XYConstraints(8, 101, 272, 101));
    jPanel1.add(jLabel4,   new XYConstraints(30, 35, 50, 20));

    jComboBox1.setFocusable(false);
    jComboBox2.setFocusable(false);
    fixedCheckBox.setFocusable(false);

    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);
    fixedCheckBox.addItemListener(this);
    jTextField1.getDocument().addDocumentListener(this);
    jAddButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jMenuItemDelete.addActionListener(this);
  }

  void set_dataModel(DataCollectionModel input)
  {
    this.Datamodel = input;
    Datamodel.setValue("type_suscept_option", "one");
  }

  public void save_init_state(){
    String resultinfo=new String();
    for(int i=0;i<listModel.size();i++)
    {
      resultinfo = resultinfo + listModel.get(i).toString()+"\n";
    }
    if(resultinfo.length()>0)
      Datamodel.setValue("type_suscept_info",resultinfo.trim());
    Datamodel.setValue("type_suscept", "use");

    if (segreg4 != null)
    {
      segreg4.jTextField1.setText("Specified");
      segreg4.jLabel2.setFont(new java.awt.Font("Dialog", 1, 11));

      if(jComboBox1.getSelectedIndex()==0)//one
      {
        segreg4.jTextField6.setText("Not applicable");
        segreg4.jButton6.setEnabled(false);
      }
      else
      {
        segreg4.jTextField6.setText("");
        segreg4.jButton6.setEnabled(true);
      }
    }
    else if(segreg5!=null)
    {
      segreg5.jTextField2.setText("Specified");
      segreg5.jLabel2.setFont(new java.awt.Font("Dialog", 1, 11));
    }
  }

  private boolean isvalid()
  {
      int option = jComboBox1.getSelectedIndex();
      int required_size = 1;
      switch (option)
      {
      case 1:
      case 3:
      case 4: //two
          required_size = 2;
          break;
      case 2:
      case 5:
      case 6:
      case 7: //three
          required_size = 3;
          break;
      }

      int size = listModel.getSize();
      boolean[] booleanvalues = new boolean[size];

      boolean existval = false;
      boolean fixed = false;

      for (int i = 0; i < size; i++) {
          String item = listModel.getElementAt(i).toString().trim();
          String lists[] = item.split(",");

          for (int k = 0; k < lists.length; k++) {
              if (lists[k].indexOf("val") >= 0) {//val
                  booleanvalues[i] = true;
              }
              if (lists[k].indexOf("fixed") >= 0) {//val
                  fixed = true;
              }
          }
      }

      boolean andvalue1 = true;

      if(existval)
          andvalue1 = true;

      for (int i = 0; i < size; i++) {
          andvalue1 = booleanvalues[i] && andvalue1;
      }

      if(fixed)
      {
          if (!(required_size == size))
              return false;
          if (andvalue1)
              return true;
          else
              return false;
      }
      else
             return true;
  }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();
    if(button == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }
    else if(button == okButton)
    {
        if(isvalid())
        {
            save_init_state();
            dispose();
        }
        else
        {
            JOptionPane.showConfirmDialog(this,
                                          message, "Warning",
                                          JOptionPane.CLOSED_OPTION,
                                          JOptionPane.
                                          INFORMATION_MESSAGE);
        }
    }
    else if(button == cancelButton)
    {
       dispose();
       if (!Datamodel.hasValue("type_suscept"))
         listModel.removeAllElements();
       jComboBox1.setSelectedIndex(0);
       jComboBox2.setSelectedIndex(0);
    }
    else if(button == jAddButton)
    {
      String cov = "suscept = "+ "\""+realmean[jComboBox2.getSelectedIndex()]+ "\"";
      if(jTextField1.getText().length()>0)
      {
        cov = cov +", val = "+ "\""+ jTextField1.getText()+ "\"";
        if(fixedCheckBox.isSelected())
          cov = cov + ", fixed = " + "\"true\"";
      }
      listModel.addElement(cov);

      jTextField1.setText("");
      jComboBox2.setSelectedIndex(1);
      fixedCheckBox.setSelected(false);
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if(document == jTextField1.getDocument())
    {
      if(jTextField1.getText().length()<=0)
      {
        fixedCheckBox.setEnabled(false);
      }
      else
      {
        fixedCheckBox.setEnabled(true);
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

    if(ob == jComboBox1)
    {
      Datamodel.setValue("type_suscept_option", option_real[jComboBox1.getSelectedIndex()]);
    }
    else if(ob == jComboBox2)
    {
      if(jComboBox2.getSelectedIndex()==0)
      {
        jAddButton.setEnabled(false);
        jTextField1.setEnabled(false);
      }
      else
      {
        jAddButton.setEnabled(true);
        jTextField1.setEnabled(true);
      }
    }
    else if(ob == fixedCheckBox)
    {
        if(fixedCheckBox.isSelected())
        {
            if(jComboBox1.getSelectedIndex()>0)
                JOptionPane.showConfirmDialog(this,
                                          message, "Warning",
                                          JOptionPane.CLOSED_OPTION,
                                          JOptionPane.
                                          INFORMATION_MESSAGE);
        }
    }

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 153);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 153);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("suscept", false, 153);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 153);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fixed", false, 153);
  }

}

class SEGREG_Dialog4_TypeSuscept_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog4_TypeSuscept adaptee;

  SEGREG_Dialog4_TypeSuscept_jComboBox1_mouseAdapter(SEGREG_Dialog4_TypeSuscept adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog4_TypeSuscept_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog4_TypeSuscept adaptee;

  SEGREG_Dialog4_TypeSuscept_jComboBox2_mouseAdapter(SEGREG_Dialog4_TypeSuscept adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SEGREG_Dialog4_TypeSuscept_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog4_TypeSuscept adaptee;

  SEGREG_Dialog4_TypeSuscept_jTextField1_mouseAdapter(SEGREG_Dialog4_TypeSuscept adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog4_TypeSuscept_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog4_TypeSuscept adaptee;

  SEGREG_Dialog4_TypeSuscept_jComboBox3_mouseAdapter(SEGREG_Dialog4_TypeSuscept adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}


