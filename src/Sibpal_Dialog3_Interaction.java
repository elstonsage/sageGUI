package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Sibpal_Dialog3_Interaction
    extends SageDialog
    implements DocumentListener, ActionListener, ItemListener {
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  ButtonGroup bp = new ButtonGroup();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;

  ButtonGroup bp1 = new ButtonGroup();

  TitledBorder titledBorder1cc;
  ButtonGroup bpcc = new ButtonGroup();
  ButtonGroup bp2cc = new ButtonGroup();

  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout1mc = new XYLayout();

  JLabel jLabel1mc = new JLabel();
  XYLayout xYLayout2cc = new XYLayout();
  JLabel jLabel1 = new JLabel();

  XYLayout xYLayout3cc = new XYLayout();
  JPanel jPanel2cc = new JPanel();
  JLabel jLabel2 = new JLabel();
  JRadioButton jRadioButton4cc = new JRadioButton();
  JRadioButton jRadioButton1mc = new JRadioButton();
  JRadioButton jRadioButton4mc = new JRadioButton();
  JRadioButton jRadioButton3cc = new JRadioButton();
  JRadioButton jRadioButton5cc = new JRadioButton();
  JRadioButton jRadioButton2mc = new JRadioButton();
  JRadioButton jRadioButton8cc = new JRadioButton();
  JRadioButton jRadioButton3mc = new JRadioButton();
  JRadioButton jRadioButton7cc = new JRadioButton();
  JRadioButton jRadioButton2cc = new JRadioButton();
  JRadioButton jRadioButton1cc = new JRadioButton();
  JRadioButton jRadioButton6cc = new JRadioButton();

  JTextField jTextField1cc = new JTextField();

  JPanel jPanel1cc = new JPanel();
  JPanel jPanel1mc = new JPanel();
  JLabel jLabel2cc = new JLabel();
  XYLayout xYLayout1cc = new XYLayout();
  JComboBox jComboBox2mc = new JComboBox();
  XYLayout xYLayoutm = new XYLayout();
  JComboBox jComboBox1mc = new JComboBox();
  JComboBox jComboBox1cc = new JComboBox();
  JTextField jTextField2cc = new JTextField();
  JLabel jLabel1cc = new JLabel();
  JTextField jTextField1mc = new JTextField();
  JComboBox jComboBox2 = new JComboBox();
  JPanel jMarkerPanel = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel3mc = new JLabel();
  JLabel jLabel4cc = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JLabel jLabel3cc = new JLabel();
  JPanel jCovPanel = new JPanel();
  JLabel jLabel2mc = new JLabel();
  JPanel jMarkerCovPanel = new JPanel();
  JComboBox jComboBox2cc = new JComboBox();
  JPanel jTabbedPane1 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();

  String selection[] = {
      "Marker by Marker", "Marker by Covariate", "Covariate by Covariate"};
  JComboBox jComboBox3 = new JComboBox(selection);
  JButton jAddButton = new JButton();

  SIBPAL4 parent;

  public Sibpal_Dialog3_Interaction(SIBPAL4 parent, String title) {
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
    jRadioButton4cc.setFocusPainted(false);
     jRadioButton1mc.setFocusPainted(false);
     jRadioButton4mc.setFocusPainted(false);
     jRadioButton3cc.setFocusPainted(false);
     jRadioButton5cc.setFocusPainted(false);
     jRadioButton2mc.setFocusPainted(false);
     jRadioButton8cc.setFocusPainted(false);
     jRadioButton3mc.setFocusPainted(false);
     jRadioButton7cc.setFocusPainted(false);
     jRadioButton2cc.setFocusPainted(false);
     jRadioButton1cc.setFocusPainted(false);
     jRadioButton6cc.setFocusPainted(false);

    this.setSize(new Dimension(410, 450));
    jCenterPanel.setPreferredSize(new Dimension(410, 370));

    jTabbedPane1.setLayout(new CardLayout());

    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jLabel3.setText("Specifies an interaction.");

    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout1);
    jRadioButton4mc.addItemListener(this);
    jRadioButton4mc.setToolTipText("");
    jRadioButton4mc.setText("All");
    jLabel1mc.setText("Marker");
    jLabel1.setText("Marker");
    jRadioButton3cc.addItemListener(this);
    jRadioButton3cc.setText("Difference");
    jPanel2cc.setLayout(xYLayout3cc);
    jPanel2cc.setBorder(titledBorder1);
    jLabel2.setText("Marker");
    jRadioButton4cc.setText("All");
    jRadioButton4cc.addItemListener(this);
    jRadioButton1mc.setSelected(true);
    jRadioButton1mc.setText("Product");
    jRadioButton1mc.addItemListener(this);
    jTextField1cc.setText("1.0");
    jRadioButton5cc.setSelected(true);
    jRadioButton5cc.setText("Product");
    jRadioButton5cc.addItemListener(this);
    jPanel1cc.setBorder(titledBorder1);
    jPanel1cc.setLayout(xYLayout2cc);
    jPanel1mc.setBorder(titledBorder1);
    jPanel1mc.setLayout(xYLayout1mc);
    jLabel2cc.setText("Covariate");
    jRadioButton2mc.setText("Sum");
    jRadioButton2mc.addItemListener(this);
    jRadioButton8cc.setText("All");
    jRadioButton8cc.addItemListener(this);
    jRadioButton3mc.setText("Difference");
    jRadioButton3mc.addItemListener(this);
    jTextField2cc.setText("1.0");
    jLabel1cc.setText("Covariate");
    jTextField1mc.setText("1.0");
    jRadioButton7cc.setText("Difference");
    jRadioButton7cc.addItemListener(this);
    jMarkerPanel.setLayout(xYLayoutm);
    jMarkerPanel.setToolTipText("Interaction between markers");
    jMarkerPanel.setSize(new java.awt.Dimension(360, 160));
    jRadioButton2cc.setText("Sum");
    jRadioButton2cc.addItemListener(this);
    jLabel3mc.setText("Power");
    jLabel4cc.setText("Power");
    jRadioButton1cc.setSelected(true);
    jRadioButton1cc.setText("Product");
    jRadioButton1cc.addItemListener(this);
    jLabel3cc.setText("Power");
    jCovPanel.setLayout(xYLayout1cc);
    jCovPanel.setToolTipText("Interaction between covariates");
    jCovPanel.setSize(new java.awt.Dimension(360, 160));
    jLabel2mc.setText("Covariate");
    jMarkerCovPanel.setLayout(xYLayout4);
    jMarkerCovPanel.setToolTipText("Interaction between marker and covariate");
    jMarkerCovPanel.setSize(new java.awt.Dimension(360, 160));
    jRadioButton6cc.setText("Sum");
    jRadioButton6cc.addItemListener(this);
    jTabbedPane1.setBorder(titledBorder1);
    jLabel5.setToolTipText("Specifies the type of interaction term to be included in the trait regression.");
    jLabel5.setText("Interaction between");
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add interaction");
    jAddButton.addActionListener(this);
    jComboBox3.addMouseListener(new Sibpal_Dialog3_Interaction_jComboBox3_mouseAdapter(this));
    jComboBox1.addMouseListener(new Sibpal_Dialog3_Interaction_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new Sibpal_Dialog3_Interaction_jComboBox2_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 300, 30));
    jBottomPanel.add(cancelButton, new XYConstraints(243, 7, 65, 25));
    jBottomPanel.add(okButton, new XYConstraints(165, 7, 65, 25));

    titledBorder1 = new TitledBorder("");
    titledBorder1cc = new TitledBorder("");

    jCenterPanel.add(jPanel1, new XYConstraints(15, 10, 377, 341));
    jMarkerPanel.add(jLabel1, new XYConstraints(15, 10, 60, 20));
    jMarkerPanel.add(jComboBox1, new XYConstraints(75, 10, 80, 20));
    jMarkerPanel.add(jLabel2, new XYConstraints(180, 10, 60, 20));
    jMarkerPanel.add(jComboBox2, new XYConstraints(240, 10, 80, 20));
    jMarkerCovPanel.add(jLabel1mc, new XYConstraints(15, 10, 60, 20));
    jMarkerCovPanel.add(jComboBox1mc, new XYConstraints(75, 10, 80, 20));
    jMarkerCovPanel.add(jLabel2mc, new XYConstraints(180, 10, 60, 20));
    jMarkerCovPanel.add(jComboBox2mc, new XYConstraints(240, 10, 80, 20));
    jMarkerCovPanel.add(jPanel1mc, new XYConstraints(180, 70, 141, 60));
    jPanel1mc.add(jRadioButton1mc, new XYConstraints(5, 0, -1, -1));
    jPanel1mc.add(jRadioButton2mc, new XYConstraints(80, 0, -1, -1));
    jPanel1mc.add(jRadioButton3mc, new XYConstraints(5, 25, -1, -1));
    jPanel1mc.add(jRadioButton4mc, new XYConstraints(80, 25, -1, -1));
    jMarkerCovPanel.add(jLabel3mc, new XYConstraints(180, 40, 60, 20));
    jMarkerCovPanel.add(jTextField1mc, new XYConstraints(240, 40, 80, 20));

    jCovPanel.add(jLabel1cc, new XYConstraints(15, 10, 60, 20));
    jCovPanel.add(jComboBox1cc, new XYConstraints(75, 10, 80, 20));
    jCovPanel.add(jLabel2cc, new XYConstraints(180, 10, 60, 20));
    jCovPanel.add(jComboBox2cc, new XYConstraints(240, 10, 80, 20));
    jCovPanel.add(jPanel1cc, new XYConstraints(15, 70, 141, 60));
    jPanel1cc.add(jRadioButton1cc, new XYConstraints(5, 0, -1, -1));
    jPanel1cc.add(jRadioButton2cc, new XYConstraints(80, 0, -1, -1));
    jPanel1cc.add(jRadioButton3cc, new XYConstraints(5, 25, -1, -1));
    jPanel1cc.add(jRadioButton4cc, new XYConstraints(80, 25, -1, -1));
    jCovPanel.add(jLabel3cc, new XYConstraints(180, 40, 60, 20));
    jCovPanel.add(jTextField1cc, new XYConstraints(75, 40, 80, 20));
    jCovPanel.add(jLabel4cc, new XYConstraints(15, 40, 48, 20));
    jCovPanel.add(jTextField2cc, new XYConstraints(240, 40, 80, 20));
    jCovPanel.add(jPanel2cc, new XYConstraints(180, 70, 141, 60));
    jPanel2cc.add(jRadioButton5cc, new XYConstraints(5, 0, -1, -1));
    jPanel2cc.add(jRadioButton6cc, new XYConstraints(80, 0, -1, -1));
    jPanel2cc.add(jRadioButton7cc, new XYConstraints(5, 25, -1, -1));
    jPanel2cc.add(jRadioButton8cc, new XYConstraints(80, 25, -1, -1));
    jPanel1.add(jLabel4, new XYConstraints(10, 211, 90, 20));
    jPanel1.add(jAddButton, new XYConstraints(270, 196, 85, 30));
    jPanel1.add(jScrollPane1, new XYConstraints(10, 233, 345, 85));
    jPanel1.add(jLabel5, new XYConstraints(10, 5, 127, 20));
    jPanel1.add(jComboBox3, new XYConstraints(148, 5, 205, 20));
    jPanel1.add(jTabbedPane1, new XYConstraints(10, 35, 345, 155));

    jTabbedPane1.add(jMarkerPanel, "Marker by Marker");
    jTabbedPane1.add(jMarkerCovPanel, "Marker by Covariate");
    jTabbedPane1.add(jCovPanel, "Covariate by Covariate");

    jTextField1cc.getDocument().addDocumentListener(this);
    jTextField2cc.getDocument().addDocumentListener(this);
    jTextField1mc.getDocument().addDocumentListener(this);
    jComboBox3.addItemListener(this);
    jMenuItemDelete.addActionListener(this);
  }

  public void save_init_state()
  {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }
    Datamodel.setValue("interaction_info", resultinfo.trim());
    Datamodel.setValue("interaction", "use");

    parent.jTextField3.setText("Specified");
    parent.jLabel6.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if(ob == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }

    else if (ob == okButton) { //ok
      save_init_state();
      dispose();
    }

    else if (ob == jAddButton) {
      savecontent();
      init_state();
    }
    else if (ob == cancelButton) { //cancel
      dispose();
      if (!Datamodel.hasValue("interaction"))
        listModel.removeAllElements();
    }
  }

  void init_state() {
    jTextField1mc.setText("1.0");
    jRadioButton1mc.setSelected(true);
    jTextField1cc.setText("1.0");
    jRadioButton1cc.setSelected(true);
    jTextField2cc.setText("1.0");
    jRadioButton5cc.setSelected(true);
    jComboBox3.setSelectedIndex(0);
  }

  void savecontent() {
    int index = jComboBox3.getSelectedIndex();
    String Interaction = "interaction {" + "\n";
    switch (index) {
      case 0: //marker to marker
        Interaction = Interaction + "marker = " + "\""+
            jComboBox1.getSelectedItem().toString()+ "\"" + "\n";
        Interaction = Interaction + "marker = " + "\""+
            jComboBox2.getSelectedItem().toString()+ "\"" + "\n";
        break;
      case 1: //marker to cov
        Interaction = Interaction + "marker = " + "\""+
            jComboBox1mc.getSelectedItem().toString()+ "\"" + "\n";
        Interaction = Interaction + "covariate = " + "\""+
            jComboBox2mc.getSelectedItem().toString()+ "\"";
        Interaction = Interaction + " ," +
            Datamodel.getValue("mtocov_option").toString();
        if (Datamodel.hasValue("mtocov_power"))
          Interaction = Interaction + " , power = " + "\""+
              Datamodel.getValue("mtocov_power").toString()+ "\"";
        Interaction = Interaction + "\n";
        break;
      case 2: //cov to cov
        Interaction = Interaction + "covariate = " + "\""+
            jComboBox1cc.getSelectedItem().toString()+ "\"";
        Interaction = Interaction + " ," +
            Datamodel.getValue("covtocov1_option").toString();
        if (Datamodel.hasValue("covtocov1_power"))
          Interaction = Interaction + " , power = " + "\""+
              Datamodel.getValue("covtocov1_power").toString()+ "\"";
        Interaction = Interaction + "\n";

        Interaction = Interaction + "covariate = " + "\""+
            jComboBox2cc.getSelectedItem().toString()+ "\"";
        Interaction = Interaction + " ," +
            Datamodel.getValue("covtocov2_option").toString();
        if (Datamodel.hasValue("covtocov2_power"))
          Interaction = Interaction + " , power = " + "\""+
              Datamodel.getValue("covtocov2_power").toString()+ "\"";
        Interaction = Interaction + "\n";
        break;
    }
    Interaction = Interaction + "}";
    listModel.addElement(Interaction);
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
    Datamodel.setValue("mtocov_option", "prod");
    Datamodel.setValue("covtocov2_option", "prod");
    Datamodel.setValue("covtocov1_option", "prod");
    Datamodel.setValue("mtocov_power", "1.0");
    Datamodel.setValue("covtocov1_power", "1.0");
    Datamodel.setValue("covtocov2_power", "1.0");

    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);

    jComboBox1mc.addItemListener(this);
    jComboBox2mc.addItemListener(this);
    jComboBox1cc.addItemListener(this);
    jComboBox2cc.addItemListener(this);
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();

    if (document == jTextField1mc.getDocument()) {
      Datamodel.setValue("mtocov_power", jTextField1mc.getText());
      if (jTextField1mc.getText().length() <= 0)
        Datamodel.removeValue("mtocov_power");
    }

    if (document == jTextField1cc.getDocument()) {
      Datamodel.setValue("covtocov1_power", jTextField1cc.getText());
      if (jTextField1cc.getText().length() <= 0)
        Datamodel.removeValue("covtocov1_power");
    }

    if (document == jTextField2cc.getDocument()) {
      Datamodel.setValue("covtocov2_power", jTextField2cc.getText());
      if (jTextField2cc.getText().length() <= 0)
        Datamodel.removeValue("covtocov2_power");
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
    if (ob == jComboBox3) {
      CardLayout cl = (CardLayout) (jTabbedPane1.getLayout());
      cl.show(jTabbedPane1, jComboBox3.getSelectedItem().toString());
    }
    else if (ob == jRadioButton1mc) {
      Datamodel.setValue("mtocov_option", "prod");
    }
    else if (ob == jRadioButton2mc) {
      Datamodel.setValue("mtocov_option", "sum");
    }
    else if (ob == jRadioButton3mc) {
      Datamodel.setValue("mtocov_option", "diff");
    }
    else if (ob == jRadioButton4mc) {
      Datamodel.setValue("mtocov_option", "all");
    }

    else if (ob == jRadioButton1cc) {
      Datamodel.setValue("covtocov1_option", "prod");
    }
    else if (ob == jRadioButton2cc) {
      Datamodel.setValue("covtocov1_option", "sum");
    }
    else if (ob == jRadioButton3cc) {
      Datamodel.setValue("covtocov1_option", "diff");
    }
    else if (ob == jRadioButton4cc) {
      Datamodel.setValue("covtocov1_option", "all");
    }

    else if (ob == jRadioButton5cc) {
      Datamodel.setValue("covtocov2_option", "prod");
    }
    else if (ob == jRadioButton6cc) {
      Datamodel.setValue("covtocov2_option", "sum");
    }
    else if (ob == jRadioButton7cc) {
      Datamodel.setValue("covtocov2_option", "diff");
    }
    else if (ob == jRadioButton8cc) {
      Datamodel.setValue("covtocov2_option", "all");
    }

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("interaction", false, 255);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("interaction", false, 255);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("interaction", false, 255);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("interaction", false, 255);
  }



}

class Sibpal_Dialog3_Interaction_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog3_Interaction adaptee;

  Sibpal_Dialog3_Interaction_jComboBox3_mouseAdapter(Sibpal_Dialog3_Interaction adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class Sibpal_Dialog3_Interaction_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog3_Interaction adaptee;

  Sibpal_Dialog3_Interaction_jComboBox1_mouseAdapter(Sibpal_Dialog3_Interaction adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Sibpal_Dialog3_Interaction_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog3_Interaction adaptee;

  Sibpal_Dialog3_Interaction_jComboBox2_mouseAdapter(Sibpal_Dialog3_Interaction adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}
