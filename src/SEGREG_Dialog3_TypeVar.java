package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog3_TypeVar
    extends SageDialog
    implements DocumentListener, ItemListener, ActionListener{
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  JLabel jLabel6 = new JLabel();
  TitledBorder titledBorder1;

  DataCollectionModel Datamodel;

  String[] option = {"One","Two","Three","Two dominant","Two recessive","Three additive"};
  String[] option_real = {"one","two","three","two_dom","two_rec","three_add"};

  String[] mean={"","\u03C3\u2082AA","\u03C3\u2082AB","\u03C3\u2082BB","\u03C3\u2082AA=\u03C3\u2082AB",
      "\u03C3\u2082BB=\u03C3\u2082AB","\u03C3\u2082AA=\u03C3\u2082AB=\u03C3\u2082BB"};
  String[] realmean={"","AA","AB","BB","A*","B*","**"};

  JComboBox jComboBox1 = new JComboBox(option);
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JComboBox jComboBox2 = new JComboBox(mean);
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JCheckBox jComboBox3 = new JCheckBox("True");
  JButton jAddButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();

  JTextField resulttextfield;
  JLabel resultlabel;

  public SEGREG_Dialog3_TypeVar(String title) {
    super(title);
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
    jLabel3.setText("<html>Specifies the number of variances.");
    jLabel6.setToolTipText("Specifies type variance option.");
    jLabel6.setText("Option");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel1.setToolTipText("Specifies the effect of a type.");
    jLabel1.setText("Variance");
    jComboBox3.setEnabled(false);
    jComboBox3.setToolTipText("Specifies option to fix the given value.");
    jComboBox3.setText("Fixed");
    jComboBox3.addMouseListener(new SEGREG_Dialog3_TypeVar_jComboBox3_mouseAdapter(this));
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add variance");
    jLabel4.setToolTipText("Specifies value of the variance.");
    jLabel4.setText("Value");
    jTextField1.setEnabled(false);
    jTextField1.addMouseListener(new SEGREG_Dialog3_TypeVar_jTextField1_mouseAdapter(this));
    jComboBox1.addMouseListener(new SEGREG_Dialog3_TypeVar_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new SEGREG_Dialog3_TypeVar_jComboBox2_mouseAdapter(this));
    jTopPanel.add(jLabel3,     new XYConstraints(15, 3, 300, 30));
    jCenterPanel.add(jLabel6,     new XYConstraints(15, 15, 47, 20));
    jCenterPanel.add(jComboBox1,   new XYConstraints(70, 15, 240, 20));
    jCenterPanel.add(jPanel1,    new XYConstraints(15, 45, 300, 220));
    jPanel1.add(jLabel1,  new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jTextField1,  new XYConstraints(90, 35, 90, 20));
    jPanel1.add(jComboBox3,   new XYConstraints(200, 35, 90, 20));
    jPanel1.add(jComboBox2,     new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jAddButton,      new XYConstraints(185, 60, 95, 30));
    jPanel1.add(jLabel2,   new XYConstraints(10, 99, 156, 20));
    jPanel1.add(jScrollPane1,        new XYConstraints(8, 101, 272, 101));
    jPanel1.add(jLabel4,   new XYConstraints(30, 35, 50, 20));

    jComboBox1.setFocusable(false);
    jComboBox2.setFocusable(false);
    jComboBox3.setFocusable(false);


    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);
    jTextField1.getDocument().addDocumentListener(this);
    jAddButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jMenuItemDelete.addActionListener(this);
  }

  void set_dataModel(DataCollectionModel input)
  {
    this.Datamodel = input;
    Datamodel.setValue("type_var_option", "one");
  }

  public void save_init_state(){
    String resultinfo=new String();
    for(int i=0;i<listModel.size();i++)
    {
      resultinfo = resultinfo + listModel.get(i).toString()+"\n";
    }
    if(resultinfo.length()>0)
      Datamodel.setValue("type_var_info",resultinfo.trim());

    Datamodel.setValue("type_var", "use");
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

    else if(button == okButton)
    {
        save_init_state();
        dispose();
    }
    else if(button == cancelButton)
    {
       dispose();
       if (!Datamodel.hasValue("type_var"))
         listModel.removeAllElements();
       jComboBox1.setSelectedIndex(0);
       jComboBox2.setSelectedIndex(0);
    }
    else if(button == jAddButton)
    {
      String cov = "var = "+ "\""+realmean[jComboBox2.getSelectedIndex()]+ "\"";
      if(jTextField1.getText().length()>0)
      {
        cov = cov +", val = "+ "\""+ jTextField1.getText()+ "\"";
        if(jComboBox3.isSelected())
          cov = cov + ", fixed = " + "\"true\"";
      }
      listModel.addElement(cov);

      jTextField1.setText("");
      jComboBox2.setSelectedIndex(1);
      jComboBox3.setSelected(false);
    }

  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if(document == jTextField1.getDocument())
    {
      if(jTextField1.getText().length()<=0)
      {
        jComboBox3.setEnabled(false);
      }
      else
      {
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

    if(ob == jComboBox1)
    {
      Datamodel.setValue("type_var_option", option_real[jComboBox1.getSelectedIndex()]);
    }
    if(ob == jComboBox2)
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
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 151);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 151);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("var", false, 151);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 151);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fixed", false, 151);
  }
}

class SEGREG_Dialog3_TypeVar_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog3_TypeVar adaptee;

  SEGREG_Dialog3_TypeVar_jComboBox1_mouseAdapter(SEGREG_Dialog3_TypeVar adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog3_TypeVar_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog3_TypeVar adaptee;

  SEGREG_Dialog3_TypeVar_jComboBox2_mouseAdapter(SEGREG_Dialog3_TypeVar adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SEGREG_Dialog3_TypeVar_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog3_TypeVar adaptee;

  SEGREG_Dialog3_TypeVar_jTextField1_mouseAdapter(SEGREG_Dialog3_TypeVar adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog3_TypeVar_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog3_TypeVar adaptee;

  SEGREG_Dialog3_TypeVar_jComboBox3_mouseAdapter(SEGREG_Dialog3_TypeVar adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}


