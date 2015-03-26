package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.plaf.basic.*;

public class Sibpal_Dialog2_Cov
    extends SageDialog
    implements ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  String option[] = {
      "product", "sum", "difference", "all"};
  String option_real[] = {
      "prod", "sum", "diff", "all"};
  String optiontooltip[] = {"mean-corrected cross-product",null,null,null};

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
  JButton jAddButton = new JButton();
  JLabel jLabel5 = new JLabel();

  SIBPAL4 parent;

  public Sibpal_Dialog2_Cov(SIBPAL4 parent, String title) {
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
    Power.addMouseListener(new Sibpal_Dialog2_Cov_Power_mouseAdapter(this));
    jLabel2.setToolTipText("Specifies a covariate.");
    jLabel2.setText("Covariate");
    jLabel4.setToolTipText("Specifies pair-specific terms to include.");
    jLabel4.setText("Option");
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add covariate");
    jAddButton.addActionListener(this);
    jComboBox2.setEnabled(false);
    jComboBox2.setSelectedIndex(1);
    jComboBox2.addMouseListener(new Sibpal_Dialog2_Cov_jComboBox2_mouseAdapter(this));
    jComboBox1.addMouseListener(new Sibpal_Dialog2_Cov_jComboBox1_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 289, 30));
    jBottomPanel.add(okButton, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(cancelButton, new XYConstraints(243, 7, 65, 25));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 10, 300, 254));
    jPanel1.add(Power,  new XYConstraints(90, 65, 95, 20));
    jPanel1.add(jComboBox1,  new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jLabel2, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jLabel4, new XYConstraints(30, 35, 50, 20));
    jPanel1.add(jComboBox2,  new XYConstraints(90, 35, 95, 20));
    jPanel1.add(jAddButton,  new XYConstraints(195, 55, 85, 30));
    jPanel1.add(jLabel5, new XYConstraints(10, 99, 156, 20));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 101, 272, 133));
    jPanel1.add(jLabel1, new XYConstraints(30, 65, 50, 20));

    jComboBox1.addItemListener(this);
    jComboBox2.setRenderer(new MyComboBoxRenderer());

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
    Datamodel.setValue("cov", "use");

    parent.jTextField2.setText("Specified");
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
    else if (button == jAddButton) {
      if (jComboBox1.getSelectedItem() != null) {
        String covlist = "covariate = " + "\""+ jComboBox1.getSelectedItem().toString()+ "\"";
        if (Power.getText().length() > 0)
          covlist = covlist + ", power = " + "\""+ Power.getText()+ "\"";
        covlist = covlist + ", " + option_real[jComboBox2.getSelectedIndex()];

        listModel.addElement(covlist);
        jComboBox1.setSelectedIndex(0);
        Power.setText("1.0");
        jComboBox2.setSelectedIndex(0);
      }
    }
    else if (button == cancelButton){
      dispose();
      if (!Datamodel.hasValue("cov"))
        listModel.removeAllElements();
      jComboBox1.setSelectedIndex(0);
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if (ob == jComboBox1) {
      if (jComboBox1.getSelectedIndex() == 0) {
        jAddButton.setEnabled(false);
        Power.setEnabled(false);
        jComboBox2.setEnabled(false);
      }
      else {
        jAddButton.setEnabled(true);
        Power.setEnabled(true);
        jComboBox2.setEnabled(true);
      }
    }
  }

  class MyComboBoxRenderer extends BasicComboBoxRenderer {
    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
      if (isSelected) {
        setBackground(list.getSelectionBackground());
        setForeground(list.getSelectionForeground());
        if ( -1 < index) {
          list.setToolTipText(optiontooltip[index]);
        }
      }
      else {
        setBackground(list.getBackground());
        setForeground(list.getForeground());
      }
      setFont(list.getFont());
      setText( (value == null) ? "" : value.toString());
      return this;
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 254);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 254);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 254);
  }

  void Power_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("covariate", false, 254);
  }
}




class Sibpal_Dialog2_Cov_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog2_Cov adaptee;

  Sibpal_Dialog2_Cov_jComboBox1_mouseAdapter(Sibpal_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Sibpal_Dialog2_Cov_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog2_Cov adaptee;

  Sibpal_Dialog2_Cov_jComboBox2_mouseAdapter(Sibpal_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class Sibpal_Dialog2_Cov_Power_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog2_Cov adaptee;

  Sibpal_Dialog2_Cov_Power_mouseAdapter(Sibpal_Dialog2_Cov adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.Power_mouseClicked(e);
  }
}
