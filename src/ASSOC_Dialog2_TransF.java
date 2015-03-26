package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ASSOC_Dialog2_TransF
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

  String[] trans = {"George and Elston", "None"};
  String[] realtrans={"george_elston", "none"};

  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  JLabel jLabel1 = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JCheckBox jComboBoxLambda1Fixed = new JCheckBox("True");
  JTextField lambda1 = new JTextField();
  JLabel jLabel7 = new JLabel();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JLabel jLabel8 = new JLabel();
  JTextField lambda2 = new JTextField();
  JCheckBox jComboBoxLambda2Fixed = new JCheckBox("True");
  JButton jButton2 = new JButton();
  JLabel jLabel9 = new JLabel();
  ASSOC2 parent;
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JTextField lowerbound = new JTextField();
  JTextField upperbound = new JTextField();
  JLabel jLabel10 = new JLabel();
  JComboBox jComboBoxTrans = new JComboBox(trans);
  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout6 = new XYLayout();

  JCheckBox jCheckboxBothSides = new JCheckBox("Both Sides");

  public ASSOC_Dialog2_TransF(ASSOC2 parent, String title) {
    super(Frame1.mainFrame1, title, true);
    this.parent = parent;
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

    jButton1.setEnabled(true);

    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 405));
    this.setResizable(false);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(330, 325));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jBottomPanel.setLayout(xYLayout3);

    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jLabel3.setText("<html>Specifies the transformation.");
    jLabel1.setToolTipText("Specifies of the power parameter, lambda1.");
    jLabel1.setText("Lambda1");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jPanel3.setLayout(xYLayout6);
    jPanel3.setBorder(titledBorder1);

    jComboBoxLambda1Fixed.addMouseListener(new ASSOC_Dialog2_TransF_jComboBoxLambda1Fixed_mouseAdapter(this));
    jComboBoxLambda2Fixed.addMouseListener(new ASSOC_Dialog2_TransF_jComboBoxLambda2Fixed_mouseAdapter(this));
    lambda1.setText("1");
    lambda1.addMouseListener(new ASSOC_Dialog2_TransF_lambda1_mouseAdapter(this));
    jLabel7.setToolTipText("Specifies the value for lambda1.");
    jLabel7.setText("Value");
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout5);
    jLabel8.setToolTipText("Specifies the value for lambda2.");
    jLabel8.setText("Value");
    lambda2.setText("1");
    lambda2.addMouseListener(new ASSOC_Dialog2_TransF_lambda2_mouseAdapter(this));
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jLabel9.setToolTipText("Specifies the shift parameter, lambda2.");
    jLabel9.setText("Lambda2");
    jLabel5.setToolTipText("Specifies inclusive lower bound for power parameter.");
    jLabel5.setText("Lower bound");
    jLabel6.setToolTipText("Specifies inclusive upper bound for power parameter.");
    jLabel6.setText("Upper bound");
    lowerbound.setText("-\u221E");
    lowerbound.addMouseListener(new ASSOC_Dialog2_TransF_lowerbound_mouseAdapter(this));
    upperbound.setText("\u221E");
    upperbound.addMouseListener(new ASSOC_Dialog2_TransF_upperbound_mouseAdapter(this));
    jLabel10.setText("Option");
     jLabel10.setToolTipText("Specifies a particular transformation option.");
    jComboBoxTrans.addMouseListener(new ASSOC_Dialog2_TransF_jComboBoxTrans_mouseAdapter(this));
    jComboBoxLambda1Fixed.setToolTipText("Specifies option to fix lambda1 at given value.");
    jComboBoxLambda1Fixed.setMargin(new Insets(2, 0, 2, 2));
    jComboBoxLambda1Fixed.setText("Fixed");
    jComboBoxLambda2Fixed.setToolTipText("Specifies option to fix lambda2 at given value.");
    jComboBoxLambda2Fixed.setMargin(new Insets(2, 0, 2, 2));
    jComboBoxLambda2Fixed.setSelected(true);
    jComboBoxLambda2Fixed.setText("Fixed");

    jCheckboxBothSides.setToolTipText("<html>The transformation is applied separately to the trait and its expected value."
                                      +"<br>The default is to transform the difference between the trait and its expected value.");
    jCheckboxBothSides.setFocusPainted(false);
    jCheckboxBothSides.addItemListener(this);

    getContentPane().add(panel1);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 266, 30));

    jPanel1.add(jLabel7,   new XYConstraints(5, 5, 80, 20));
    jPanel1.add(lambda1,      new XYConstraints(103, 5, 110, 20));
    jPanel1.add(jComboBoxLambda1Fixed,    new XYConstraints(103, 35, 110, 20));
    jPanel1.add(jLabel5,     new XYConstraints(5, 65, 80, 20));
    jPanel1.add(jLabel6,    new XYConstraints(5, 95, 80, 20));
    jPanel1.add(lowerbound,     new XYConstraints(103, 65, 110, 20));
    jPanel1.add(upperbound,     new XYConstraints(103, 95, 110, 20));
    jPanel2.add(jLabel8, new XYConstraints(5, 5, 50, 20));
    jPanel2.add(lambda2,    new XYConstraints(103, 2, 110, 20));
    jPanel2.add(jComboBoxLambda2Fixed,   new XYConstraints(103, 35, 110, 20));
    jPanel3.add(jCheckboxBothSides,  new XYConstraints(2, 2, 200, 20));

    jCenterPanel.add(jLabel10,  new XYConstraints(15, 15, 55, 20));
    jCenterPanel.add(jComboBoxTrans,  new XYConstraints(85, 15, 230, 20));
    jCenterPanel.add(jLabel1,  new XYConstraints(15, 45, 52, 20));
    jCenterPanel.add(jPanel1,    new XYConstraints(85, 45, 230, 135));
    jCenterPanel.add(jLabel9,   new XYConstraints(15, 190, 62, 20));
    jCenterPanel.add(jPanel2,    new XYConstraints(85, 190, 230, 68));
    jCenterPanel.add(jPanel3,    new XYConstraints(85, 268, 230, 40));

    jBottomPanel.add(jButton1,  new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2,  new XYConstraints(243, 7, 65, 25));

    panel1.add(jTopPanel, BorderLayout.NORTH);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    panel1.add(jCenterPanel, BorderLayout.CENTER);

    jComboBoxLambda1Fixed.setFocusable(false);
    jComboBoxLambda2Fixed.setFocusable(false);
    jComboBoxLambda1Fixed.addItemListener(this);
    jComboBoxLambda2Fixed.addItemListener(this);

    lambda1.getDocument().addDocumentListener(this);
    lambda2.getDocument().addDocumentListener(this);
    jComboBoxTrans.setSelectedIndex(0);
    jComboBoxTrans.addItemListener(this);

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
    Datamodel.setValue("Trans", "george_elston");
    Datamodel.setValue("lambda1", "1");
    Datamodel.setValue("lambda2", "0");
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

  public void save_init_state()
  {
    Datamodel.setValue("transformation_info", "use");
    parent.transTextField.setText("Specified");
    parent.transLabel.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == lambda1.getDocument()) {
      if (lambda1.getText().length() <= 0) {
        jComboBoxLambda1Fixed.setEnabled(false);
        Datamodel.removeValue("lambda1");
      }
      else {
        jComboBoxLambda1Fixed.setEnabled(true);
        Datamodel.setValue("lambda1", lambda1.getText());
      }
    }
    if (document == lambda2.getDocument()) {
      if (lambda2.getText().length() <= 0) {
        jComboBoxLambda2Fixed.setEnabled(false);
        Datamodel.removeValue("lambda2");
      }
      else {
        jComboBoxLambda2Fixed.setEnabled(true);
        Datamodel.setValue("lambda2", lambda2.getText());
      }
    }

    if (document == lowerbound.getDocument()) {
      if (lowerbound.getText().length() > 0)
        Datamodel.setValue("lower_bound", lowerbound.getText());
      else
        Datamodel.removeValue("lower_bound");
    }
    if (document == upperbound.getDocument()) {
      if (upperbound.getText().length() <= 0 || upperbound.getText().compareTo("\u221E")==0)
        Datamodel.removeValue("upper_bound");
      else
        Datamodel.setValue("upper_bound", upperbound.getText());
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
    if (ob == jComboBoxLambda1Fixed) {
      if(jComboBoxLambda1Fixed.isSelected())
      {
        lowerbound.setForeground(Color.red);
        upperbound.setForeground(Color.red);
        Datamodel.setValue("lambda1_fixed", "true");
      }
      else
      {
        lowerbound.setForeground(Color.black);
        upperbound.setForeground(Color.black);
        Datamodel.setValue("lambda1_fixed", "false");
      }
    }
    if (ob == jComboBoxLambda2Fixed) {

      if(jComboBoxLambda2Fixed.isSelected())
        Datamodel.setValue("lambda2_fixed", "true");
      else
        Datamodel.setValue("lambda2_fixed", "false");
    }
    if (ob == jComboBoxTrans) {
      int index = jComboBoxTrans.getSelectedIndex();
      Datamodel.setValue("Trans", realtrans[index]);

      if (index == 0)
      {
        jLabel1.setEnabled(true);

        jLabel7.setEnabled(true);
        jLabel5.setEnabled(true);
        jLabel6.setEnabled(true);
        lambda1.setEnabled(true);
        lambda1.setText("1");
        jComboBoxLambda1Fixed.setEnabled(true);
        jComboBoxLambda1Fixed.setSelected(true);
        lowerbound.setEnabled(true);
        upperbound.setEnabled(true);

        jLabel9.setEnabled(true);
        jLabel8.setEnabled(true);
        jComboBoxLambda2Fixed.setEnabled(true);
        jComboBoxLambda2Fixed.setSelected(true);
        lambda2.setEnabled(true);

        lambda2.setText("0");
      }
      else if (index == 1)
      {
        jLabel1.setEnabled(false);

        jLabel7.setEnabled(false);
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        lambda1.setEnabled(false);
        jComboBoxLambda1Fixed.setEnabled(false);
        lowerbound.setEnabled(false);
        upperbound.setEnabled(false);

        jLabel9.setEnabled(false);
        jLabel8.setEnabled(false);
        jComboBoxLambda2Fixed.setEnabled(false);
        lambda2.setEnabled(false);
      }
    }

    if(ob == jCheckboxBothSides)
    {
        if(jCheckboxBothSides.isSelected())
        {
          Datamodel.setValue("both_sides", "true");
        }
        else
        {
            if(Datamodel.hasValue("both_sides"))
                Datamodel.removeValue("both_sides");
      }
    }

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("option ", false, 336);
  }

  void lambda1_mouseClicked(MouseEvent e) {
   Frame1.mainFrame1.pdfframe.setTextonPage("lambda1 ", false, 336);
  }

  void jComboBoxLambda1Fixed_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda1 ", false, 336);
  }

  void lambda2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda2 ", false, 336);
  }

  void jComboBoxLambda2Fixed_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda2 ", false, 336);
  }

  void lowerbound_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lower_boun ", false, 336);
  }

  void upperbound_mouseClicked(MouseEvent e) {
Frame1.mainFrame1.pdfframe.setTextonPage("upper_boun ", false, 336);
  }

  void jComboBoxTrans_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("option ", false, 336);
  }
}

class ASSOC_Dialog2_TransF_lambda1_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC_Dialog2_TransF adaptee;

  ASSOC_Dialog2_TransF_lambda1_mouseAdapter(ASSOC_Dialog2_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.lambda1_mouseClicked(e);
  }
}

class ASSOC_Dialog2_TransF_jComboBoxLambda1Fixed_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC_Dialog2_TransF adaptee;

  ASSOC_Dialog2_TransF_jComboBoxLambda1Fixed_mouseAdapter(ASSOC_Dialog2_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxLambda1Fixed_mouseClicked(e);
  }
}

class ASSOC_Dialog2_TransF_lambda2_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC_Dialog2_TransF adaptee;

  ASSOC_Dialog2_TransF_lambda2_mouseAdapter(ASSOC_Dialog2_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.lambda2_mouseClicked(e);
  }
}

class ASSOC_Dialog2_TransF_jComboBoxLambda2Fixed_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC_Dialog2_TransF adaptee;

  ASSOC_Dialog2_TransF_jComboBoxLambda2Fixed_mouseAdapter(ASSOC_Dialog2_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxLambda2Fixed_mouseClicked(e);
  }
}

class ASSOC_Dialog2_TransF_lowerbound_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC_Dialog2_TransF adaptee;

  ASSOC_Dialog2_TransF_lowerbound_mouseAdapter(ASSOC_Dialog2_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.lowerbound_mouseClicked(e);
  }
}

class ASSOC_Dialog2_TransF_upperbound_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC_Dialog2_TransF adaptee;

  ASSOC_Dialog2_TransF_upperbound_mouseAdapter(ASSOC_Dialog2_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.upperbound_mouseClicked(e);
  }
}

class ASSOC_Dialog2_TransF_jComboBoxTrans_mouseAdapter extends java.awt.event.MouseAdapter {
  ASSOC_Dialog2_TransF adaptee;

  ASSOC_Dialog2_TransF_jComboBoxTrans_mouseAdapter(ASSOC_Dialog2_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBoxTrans_mouseClicked(e);
  }
}
