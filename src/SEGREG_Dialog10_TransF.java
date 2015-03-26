package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog10_TransF
    extends JDialog
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;

  DataCollectionModel Datamodel;

  String[] option = {
      "None", "Box and Cox", "George and Elston"};
  String[] option_real = {
      "none", "box_cox", "george_elston"};

  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  JLabel jLabel1 = new JLabel();
  JComboBox optionComboBox = new JComboBox(option);
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JCheckBox lambda1FixedCheckBox = new JCheckBox("True");
  JLabel jLabel6 = new JLabel();
  JTextField lambda1ValueTextField = new JTextField();
  JTextField lambda1LBTextField = new JTextField();
  JLabel jLabel7 = new JLabel();
  JTextField lambda1UBTextField = new JTextField();
  JLabel jLabel5 = new JLabel();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JLabel jLabel8 = new JLabel();
  JTextField lambda2ValueTextField = new JTextField();
  JCheckBox lambda2FixedCheckBox = new JCheckBox("True");
  JButton jButton2 = new JButton();

  JTextField resulttextfield;
  JLabel resultlabel;
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();

  public SEGREG_Dialog10_TransF(String title) {
    super(Frame1.mainFrame1, title, true);

    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void showDialog() {
  setLocationRelativeTo(Frame1.mainFrame1.activeinframe);
      this.setVisible(true);
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");

    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 360));
    this.setResizable(false);

    jButton1.setEnabled(true);
    optionComboBox.setSelectedIndex(1);
    optionComboBox.addMouseListener(new SEGREG_Dialog10_TransF_jComboBox1_mouseAdapter(this));
    optionComboBox.addItemListener(this);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(330, 280));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jBottomPanel.setLayout(xYLayout3);

    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jLabel3.setText("Specifies transformation.");
    jLabel1.setToolTipText("Specifies transformation type.");
    jLabel1.setText("Option");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    lambda1FixedCheckBox.setToolTipText("Specifies option to fix lambda1 at given value.");
    lambda1FixedCheckBox.setMargin(new Insets(2, 0, 2, 2));
    lambda1FixedCheckBox.setText("Fixed");
    lambda1FixedCheckBox.addMouseListener(new SEGREG_Dialog10_TransF_jComboBox2_mouseAdapter(this));
    jLabel6.setToolTipText("Specifies upper bound for lambda1.");
    jLabel6.setText("Upper bound");
    lambda1ValueTextField.setText("1");
    lambda1ValueTextField.addMouseListener(new SEGREG_Dialog10_TransF_jTextField1_mouseAdapter(this));
    lambda1LBTextField.setText("-1");
    lambda1LBTextField.addMouseListener(new SEGREG_Dialog10_TransF_jTextField3_mouseAdapter(this));
    jLabel7.setToolTipText("Specifies the value for lambda1.");
    jLabel7.setText("Value");
    lambda1UBTextField.setText("\u221E");
    lambda1UBTextField.addMouseListener(new SEGREG_Dialog10_TransF_jTextField4_mouseAdapter(this));
    jLabel5.setToolTipText("Specifies lower bound for lambda1.");
    jLabel5.setText("Lower bound");
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout5);
    jLabel8.setToolTipText("Specifies the value for lambda2.");
    jLabel8.setText("Value");
    lambda2ValueTextField.setText("1");
    lambda2ValueTextField.addMouseListener(new SEGREG_Dialog10_TransF_jTextField2_mouseAdapter(this));
    lambda2FixedCheckBox.setToolTipText("Specifies option to fix lambda2 at given value.");
    lambda2FixedCheckBox.setMargin(new Insets(2, 0, 2, 2));
    lambda2FixedCheckBox.setSelected(true);
    lambda2FixedCheckBox.setText("Fixed");
    lambda2FixedCheckBox.addMouseListener(new SEGREG_Dialog10_TransF_jComboBox3_mouseAdapter(this));
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jLabel9.setToolTipText("Specifies of the power parameter, lambda1.");
    jLabel9.setText("Lambda1");
    jLabel10.setToolTipText("Specifies the shift parameter, lambda2.");
    jLabel10.setText("Lambda2");
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 266, 30));
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jCenterPanel.add(jLabel1, new XYConstraints(15, 15, 52, 20));
    jCenterPanel.add(optionComboBox,   new XYConstraints(85, 15, 230, 20));
    jCenterPanel.add(jPanel1,      new XYConstraints(85, 45, 230, 130));
    jPanel1.add(jLabel7,  new XYConstraints(5, 5, 50, 20));
    jPanel1.add(lambda1ValueTextField,   new XYConstraints(80, 5, 130, 20));
    jPanel1.add(lambda1FixedCheckBox,     new XYConstraints(80, 31, 130, 20));
    jPanel1.add(jLabel5,   new XYConstraints(5, 63, 73, 20));
    jPanel1.add(lambda1LBTextField,     new XYConstraints(80, 63, 130, 20));
    jPanel1.add(jLabel6,   new XYConstraints(5, 92, 65, 20));
    jPanel1.add(lambda1UBTextField,      new XYConstraints(80, 92, 130, 20));
    jPanel2.add(jLabel8,  new XYConstraints(5, 5, 50, 20));
    jPanel2.add(lambda2ValueTextField,  new XYConstraints(80, 5, 130, 20));
    jPanel2.add(lambda2FixedCheckBox,  new XYConstraints(80, 31, 130, 20));
    jCenterPanel.add(jLabel10,  new XYConstraints(15, 185, 52, 20));
    jCenterPanel.add(jLabel9,       new XYConstraints(15, 45, 52, 20));
    jCenterPanel.add(jPanel2,    new XYConstraints(85, 185, 230, 68));

    optionComboBox.setFocusable(false);
    lambda1FixedCheckBox.setFocusable(false);
    lambda2FixedCheckBox.setFocusable(false);

    lambda1ValueTextField.getDocument().addDocumentListener(this);
    lambda2ValueTextField.getDocument().addDocumentListener(this);
    lambda1LBTextField.getDocument().addDocumentListener(this);
    lambda1UBTextField.getDocument().addDocumentListener(this);

    this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1Pressed");

    this.getRootPane().getActionMap().
            put("F1Pressed", new AbstractAction("F1Pressed") {
        public void actionPerformed(ActionEvent actionEvent) {
            onPressedF1();
        }
      });
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
    Datamodel.setValue("transformation_lambda1_val",lambda1ValueTextField.getText());
    Datamodel.setValue("transformation_lambda1_lb", lambda1LBTextField.getText());
    Datamodel.setValue("transformation_lambda2_val", lambda2ValueTextField.getText());
    Datamodel.setValue("transformation_option", "box_cox");
  }

  public void save_init_state()
  {
    Datamodel.setValue("transformation_option",
                       option_real[optionComboBox.getSelectedIndex()]);

      Datamodel.setValue("transformation_lambda1", "use");
      if(lambda1FixedCheckBox.isSelected())
        Datamodel.setValue("transformation_lambda1_fixed","true");
      else
        Datamodel.setValue("transformation_lambda1_fixed","false");

      Datamodel.setValue("transformation_lambda2", "use");
      if(lambda2FixedCheckBox.isSelected())
        Datamodel.setValue("transformation_lambda2_fixed","true");
      else
        Datamodel.setValue("transformation_lambda2_fixed","false");

    Datamodel.setValue("transformation", "use");

    resulttextfield.setText("Specified");
    resultlabel.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jButton1) {
      save_init_state();
      dispose();
    }
    else if (e.getSource() == jButton2) {
      dispose();
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == lambda1ValueTextField.getDocument()) {
      if (lambda1ValueTextField.getText().length() <= 0) {
        lambda1FixedCheckBox.setEnabled(false);
        Datamodel.removeValue("transformation_lambda1_val");

      }
      else {
        lambda1FixedCheckBox.setEnabled(true);
        Datamodel.setValue("transformation_lambda1_val", lambda1ValueTextField.getText());
      }
    }
    if (document == lambda2ValueTextField.getDocument()) {
      if (lambda2ValueTextField.getText().length() <= 0) {
        lambda2FixedCheckBox.setEnabled(false);
        Datamodel.removeValue("transformation_lambda2_val");
      }
      else {
        lambda2FixedCheckBox.setEnabled(true);
        Datamodel.setValue("transformation_lambda2_val", lambda2ValueTextField.getText());
      }
    }
    if (document == lambda1LBTextField.getDocument()) {
      if (lambda1LBTextField.getText().length() <= 0) {
        Datamodel.removeValue("transformation_lambda1_lb");
      }
      else {
        Datamodel.setValue("transformation_lambda1_lb", lambda1LBTextField.getText());
      }
    }
    if (document == lambda1UBTextField.getDocument()) {
      if (lambda1UBTextField.getText().length() <= 0 || lambda1UBTextField.getText().compareTo("\u221E")==0) {
        Datamodel.removeValue("transformation_lambda1_ub");
      }
      else {
        Datamodel.setValue("transformation_lambda1_ub", lambda1UBTextField.getText());
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

    if (ob == optionComboBox) {

        int option = optionComboBox.getSelectedIndex();

        switch(option)
        {
        case 0://none
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
            jLabel7.setEnabled(false);
            jLabel8.setEnabled(false);
            jLabel9.setEnabled(false);
            jLabel10.setEnabled(false);

            lambda1ValueTextField.setEnabled(false);
            lambda2ValueTextField.setEnabled(false);
            lambda1FixedCheckBox.setEnabled(false);
            lambda2FixedCheckBox.setEnabled(false);
            lambda1LBTextField.setEnabled(false);
            lambda1UBTextField.setEnabled(false);
            break;

        case 1: //"Box and Cox"
            jLabel5.setEnabled(true);
            jLabel6.setEnabled(true);
            jLabel7.setEnabled(true);
            jLabel8.setEnabled(true);
            jLabel9.setEnabled(true);
            jLabel10.setEnabled(true);

            lambda1ValueTextField.setEnabled(true);
            lambda2ValueTextField.setEnabled(true);
            lambda1FixedCheckBox.setEnabled(true);
            lambda2FixedCheckBox.setEnabled(true);
            lambda1LBTextField.setEnabled(true);
            lambda1UBTextField.setEnabled(true);

            lambda1ValueTextField.setText("1");
            lambda2ValueTextField.setText("1");
            lambda1FixedCheckBox.setSelected(false);
            lambda2FixedCheckBox.setSelected(true);
            break;
        case 2://"George and Elston"
            jLabel5.setEnabled(true);
            jLabel6.setEnabled(true);
            jLabel7.setEnabled(true);
            jLabel8.setEnabled(true);
            jLabel9.setEnabled(true);
            jLabel10.setEnabled(true);

            lambda1ValueTextField.setEnabled(true);
            lambda2ValueTextField.setEnabled(true);
            lambda1FixedCheckBox.setEnabled(true);
            lambda2FixedCheckBox.setEnabled(true);
            lambda1LBTextField.setEnabled(true);
            lambda1UBTextField.setEnabled(true);

            lambda1ValueTextField.setText("1");
            lambda2ValueTextField.setText("0");
            lambda1FixedCheckBox.setSelected(true);
            lambda2FixedCheckBox.setSelected(true);
            break;
        }

    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
     Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 168);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 168);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda1", false, 168);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 168);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fixed", false, 168);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lower_bound", false, 168);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("upper_bound", false, 168);
  }

  void jCheckBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda2", false, 168);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 169);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fixed", false, 169);
  }
}

class SEGREG_Dialog10_TransF_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog10_TransF adaptee;

  SEGREG_Dialog10_TransF_jComboBox1_mouseAdapter(SEGREG_Dialog10_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog10_TransF_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog10_TransF adaptee;

  SEGREG_Dialog10_TransF_jTextField1_mouseAdapter(SEGREG_Dialog10_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog10_TransF_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog10_TransF adaptee;

  SEGREG_Dialog10_TransF_jComboBox2_mouseAdapter(SEGREG_Dialog10_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SEGREG_Dialog10_TransF_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog10_TransF adaptee;

  SEGREG_Dialog10_TransF_jTextField3_mouseAdapter(SEGREG_Dialog10_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class SEGREG_Dialog10_TransF_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog10_TransF adaptee;

  SEGREG_Dialog10_TransF_jTextField4_mouseAdapter(SEGREG_Dialog10_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class SEGREG_Dialog10_TransF_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog10_TransF adaptee;

  SEGREG_Dialog10_TransF_jTextField2_mouseAdapter(SEGREG_Dialog10_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class SEGREG_Dialog10_TransF_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog10_TransF adaptee;

  SEGREG_Dialog10_TransF_jComboBox3_mouseAdapter(SEGREG_Dialog10_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}
