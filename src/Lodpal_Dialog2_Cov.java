package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class Lodpal_Dialog2_Cov
    extends SageDialog
    implements ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  String option[] = {
      "sum", "difference", "sum & difference", "product", "average",
      "first member value"};
  String option_real[] = {
      "sum", "diff", "both", "prod", "avg", "single"};
  String option2[] = {
      "mean center", "minimum offset"};

  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  TitledBorder titledBorder1;

  JLabel jLabel1 = new JLabel();
  JTextField Power = new JTextField();
  DataCollectionModel Datamodel;
  JComboBox jComboBox1 = new JComboBox();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JComboBox jComboBox2 = new JComboBox(option);
  JComboBox jComboBox3 = new JComboBox(option2);
  JTextField jTextField1 = new JTextField();
  JButton jAddButton = new JButton();
  JLabel jLabel5 = new JLabel();

  LODPAL2 parent;

  public Lodpal_Dialog2_Cov(LODPAL2 parent, String title) {
    super(title);
    this.setTitle(title);
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
    this.setSize(new Dimension(330, 380));
    jCenterPanel.setPreferredSize(new Dimension(330, 300));

  okButton.setEnabled(false);
  jLabel1.setToolTipText(
   "<html>Covariate terms are taken to the power specified.");

    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jLabel3.setText("<html>Specifies a covariate.");
    jPanel1.setLayout(xYLayout4);
    jPanel1.setBorder(titledBorder1);
    jLabel1.setText("Power");
    Power.setEnabled(false);
    Power.setText("1.0");
    Power.addMouseListener(new Lodpal_Dialog2_Cov_Power_mouseAdapter(this));

    jLabel2.setToolTipText(
        "Specifies a covariate for the one-parameter model only.");
    jLabel2.setText("Covariate");
    jLabel4.setToolTipText("Specifies option to include terms.");
    jLabel4.setText("Option");
    jTextField1.setEnabled(false);
    jTextField1.setText("observed mean");
    jTextField1.addMouseListener(new
                                 Lodpal_Dialog2_Cov_jTextField1_mouseAdapter(this));
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add covariate");
    jAddButton.addActionListener(this);
    jComboBox2.setEnabled(false);
    jComboBox2.addMouseListener(new Lodpal_Dialog2_Cov_jComboBox2_mouseAdapter(this));
    jComboBox3.setEnabled(false);
    jComboBox3.addMouseListener(new Lodpal_Dialog2_Cov_jComboBox3_mouseAdapter(this));
    jComboBox1.addMouseListener(new Lodpal_Dialog2_Cov_jComboBox1_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 289, 30));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 10, 300, 275));
    jPanel1.add(jLabel1, new XYConstraints(30, 35, 50, 20));
    jPanel1.add(Power,  new XYConstraints(90, 35, 100, 20));
    jPanel1.add(jComboBox1,   new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jLabel2, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jLabel4, new XYConstraints(30, 65, 50, 20));
    jPanel1.add(jComboBox2,  new XYConstraints(90, 65, 100, 20));
    jPanel1.add(jComboBox3,  new XYConstraints(90, 95, 100, 20));
    jPanel1.add(jTextField1,  new XYConstraints(90, 125, 100, 20));
    jPanel1.add(jAddButton,   new XYConstraints(200, 115, 80, 30));
    jPanel1.add(jLabel5, new XYConstraints(10, 159, 156, 20));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 161, 272, 93));

    jComboBox3.addItemListener(this);
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
    Datamodel.setValue("Cov_info", resultinfo.trim());

    Datamodel.setValue("Cov", "use");
    parent.jTextField2.setText("Specified");
    parent.jLabel8.setFont(new java.awt.Font("Dialog", 1, 11));
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
      if (jComboBox1.getSelectedItem() != null) {
        String covlist = "covariate = " +"\""+ jComboBox1.getSelectedItem().toString()+"\"";
        if (Power.getText().length() > 0)
          covlist = covlist + ", power = " +"\""+ Power.getText()+"\"";
        covlist = covlist + ", " + option_real[jComboBox2.getSelectedIndex()];
        if (jComboBox3.getSelectedIndex() == 0) {
          covlist = covlist + ", mean";
          if (jTextField1.getText().indexOf("mean") < 0)
            covlist = covlist + "=" +"\""+ jTextField1.getText()+"\"";
        }
        else {
          covlist = covlist + ", minimum";
        }
        listModel.addElement(covlist);
        jComboBox1.setSelectedIndex(0);
        Power.setText("1.0");
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
      }
    }
    else {
      dispose();
      if (!Datamodel.hasValue("Cov"))
        listModel.removeAllElements();
      jComboBox1.setSelectedIndex(0);
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if (ob == jComboBox1) {
      if (jComboBox1.getSelectedIndex()>0){
        Power.setEnabled(true);
        jComboBox2.setEnabled(true);
        jComboBox3.setEnabled(true);
        jTextField1.setEnabled(true);
        jAddButton.setEnabled(true);
      }
      else {
        Power.setEnabled(false);
        jComboBox2.setEnabled(false);
        jComboBox3.setEnabled(false);
        jTextField1.setEnabled(false);
        jAddButton.setEnabled(false);
      }
    }

    if (ob == jComboBox3) {
      int i = jComboBox3.getSelectedIndex();
      switch (i) {
        case 0: // mean
          jTextField1.setText("observed mean");
          jTextField1.setEditable(true);
          break;

        case 1: //minimum
          jTextField1.setText("observed minimum");
          jTextField1.setEditable(false);
          break;
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
     Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 275);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 275);
  }

  void Power_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("powe", false, 275);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sum", false, 275);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("mean", false, 276);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("minimum", false, 276);

  }
}

class Lodpal_Dialog2_Cov_jComboBox1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog2_Cov adaptee;

  Lodpal_Dialog2_Cov_jComboBox1_mouseAdapter(Lodpal_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Lodpal_Dialog2_Cov_Power_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog2_Cov adaptee;

  Lodpal_Dialog2_Cov_Power_mouseAdapter(Lodpal_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.Power_mouseClicked(e);
  }
}

class Lodpal_Dialog2_Cov_jComboBox2_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog2_Cov adaptee;

  Lodpal_Dialog2_Cov_jComboBox2_mouseAdapter(Lodpal_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class Lodpal_Dialog2_Cov_jComboBox3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog2_Cov adaptee;

  Lodpal_Dialog2_Cov_jComboBox3_mouseAdapter(Lodpal_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class Lodpal_Dialog2_Cov_jTextField1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog2_Cov adaptee;

  Lodpal_Dialog2_Cov_jTextField1_mouseAdapter(Lodpal_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}
