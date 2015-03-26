package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Lodpal_Dialog4_Auto
    extends JDialog
    implements DocumentListener, ActionListener, java.awt.event.ItemListener {
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

  String[] model = {
      "One_parameter", "Two_parameter"};
  String[] fixed = {
      "None fixed", "Set maternal equal to 1", "Set paternal equal to 1"};

  JLabel jLabel6 = new JLabel();

  DataCollectionModel Datamodel;
  JButton jButton2 = new JButton();

  LODPAL2 parent;
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;

  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel4 = new JLabel();
  JTextField Alpha = new JTextField();
  JComboBox jComboBox1 = new JComboBox(model);
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JLabel jLabel2 = new JLabel();
  JComboBox jComboBox3 = new JComboBox(fixed);
  JCheckBox jCheckBox1 = new JCheckBox();
  JCheckBox jComboBox5 = new JCheckBox("True");
  JCheckBox jComboBox4 = new JCheckBox("True");

  JLabel jLabel1 = new JLabel();


  public Lodpal_Dialog4_Auto(LODPAL2 parent, String title) {
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
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 360));
    this.setResizable(false);

    jCheckBox1.setFocusPainted(false);
    jComboBox5.setFocusPainted(false);
    jComboBox4.setFocusPainted(false);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(330, 280));
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setLayout(xYLayout3);

    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jLabel3.setText("Specifies an autosomal model.");
    jLabel6.setToolTipText(
        "<html>Specifies the model type to be used in the analysis.");
    jLabel6.setText("Model");
    jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);

    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel4.setToolTipText(
            "<html>Specifies the Whittemore and Tu one-parameter model\'s alpha value." +
            "<br>An <b>alpha</b> value of 1 specifies a model with no dominant genetic variance.");
    jLabel4.setText("Alpha");
    Alpha.setText("2.634");
    Alpha.addMouseListener(new Lodpal_Dialog4_Auto_Alpha_mouseAdapter(this));
    jComboBox4.addItemListener(this);
    jComboBox4.setToolTipText("No genetic constraints on the parameters to be estimated.");
    jComboBox4.setMargin(new Insets(2, 0, 2, 2));
    jComboBox4.setText("Estimate without genetic constraints");
    jComboBox4.addMouseListener(new Lodpal_Dialog4_Auto_jComboBox4_mouseAdapter(this));
    jComboBox1.addItemListener(this);
    jComboBox1.addMouseListener(new Lodpal_Dialog4_Auto_jComboBox1_mouseAdapter(this));
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout5);
    jLabel2.setToolTipText("Specifies the option to fix parental relative recurrence risk.");
    jLabel2.setText("Fixed");
    jComboBox3.addItemListener(this);
    jComboBox3.addMouseListener(new Lodpal_Dialog4_Auto_jComboBox3_mouseAdapter(this));
    jCheckBox1.addActionListener(this);
    jCheckBox1.setToolTipText("<html>Specifies the option to test for the parent-of-origin effect. " +
    "<br>By default, only sib-pairs are used in the analysis.");
    jCheckBox1.setMargin(new Insets(2, 0, 2, 2));
    jCheckBox1.setText("Test for parent-of-origin effect");
    jCheckBox1.addMouseListener(new Lodpal_Dialog4_Auto_jCheckBox1_mouseAdapter(this));
    jComboBox5.addItemListener(this);
    jComboBox5.setToolTipText("<html>Specifies the option to include non-sibs in the analysis.");
    jComboBox5.setMargin(new Insets(2, 0, 2, 2));
    jComboBox5.setText("Include non-sibs in the analysis");
    jComboBox5.addMouseListener(new Lodpal_Dialog4_Auto_jComboBox5_mouseAdapter(this));
    jLabel1.setText("Parent of origin");
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3,  new XYConstraints(15, 3, 300, 30));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));
    jCenterPanel.add(jLabel6,   new XYConstraints(15, 15, 76, 20));
    jCenterPanel.add(jPanel1,      new XYConstraints(100, 15, 215, 105));
    jCenterPanel.add(jLabel1,     new XYConstraints(12, 125, 90, 20));

    jCenterPanel.add(jPanel2,     new XYConstraints(100, 130, 215, 105));

    jPanel1.add(jComboBox1,     new XYConstraints(5, 5, 190, 20));
    jPanel1.add(jLabel4,     new XYConstraints(5, 35, 40, 20));
    jPanel1.add(Alpha,      new XYConstraints(65, 35, 130, 20));
    jPanel1.add(jComboBox4,      new XYConstraints(2, 65, 200, 20));

    jPanel2.add(jCheckBox1,  new XYConstraints(2, 5, 190, 20));
    jPanel2.add(jLabel2,    new XYConstraints(5, 35, 40, 20));
    jPanel2.add(jComboBox3,   new XYConstraints(65, 35, 130, 20));
    jPanel2.add(jComboBox5,     new XYConstraints(2, 65, 180, 20));

    this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1Pressed");

    this.getRootPane().getActionMap().
            put("F1Pressed", new AbstractAction("F1Pressed") {
        public void actionPerformed(ActionEvent actionEvent) {
            onPressedF1();
        }
      });
    Alpha.getDocument().addDocumentListener(this);
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
    Datamodel.setValue("Auto_model", "one_parameter");
    Datamodel.setValue("Auto_alpha", "2.634");
    Datamodel.setValue("Auto_parent", "false");
    Datamodel.setValue("Auto_fixed", "none");

    if (Datamodel.hasValue("Auto_alpha")) {
      jButton1.setEnabled(true);
    }

  }

  public void save_init_state()
  {
    Datamodel.setValue("Auto", "use");
    parent.jTextField4.setText("Specified");
    parent.jLabel5.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jButton1) {
      save_init_state();
      dispose();
    }
    else if(ob == jButton2)
    {
        dispose();
    }

    else if(ob == jCheckBox1)
    {
      if(jCheckBox1.isSelected())
        Datamodel.setValue("Auto_parent", "true");
      else
        Datamodel.setValue("Auto_parent", "false");
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if (ob == jComboBox1) {
      int index = jComboBox1.getSelectedIndex();
      if (index == 0)
        Datamodel.setValue("Auto_model", "one_parameter");
      if (index == 1)
        Datamodel.setValue("Auto_model", "two_parameter");
    }
    if (ob == jComboBox3) {
      int index = jComboBox3.getSelectedIndex();
      if (index == 0)
        Datamodel.removeValue("Auto_fixed");
      if (index == 1)
        Datamodel.setValue("Auto_fixed", "maternal");
      if (index == 2)
        Datamodel.setValue("Auto_fixed", "paternal");
    }
    if (ob == jComboBox4) {
      if (jComboBox4.isSelected())
        Datamodel.setValue("Auto_uncon", "use");
      else
      {
        if (Datamodel.hasValue("Auto_uncon"))
          Datamodel.removeValue("Auto_uncon");
      }
    }
    if (ob == jComboBox5) {
      if (jComboBox5.isSelected())
        Datamodel.setValue("Auto_all_pairs", "use");

      else
      {
          if (Datamodel.hasValue("Auto_all_pairs"))
            Datamodel.removeValue("Auto_all_pairs");
      }
    }
    if (Datamodel.hasValue("Auto_alpha")) {
      jButton1.setEnabled(true);
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == Alpha.getDocument()) {
      Datamodel.setValue("Auto_alpha", Alpha.getText());
    }
    if (Datamodel.hasValue("Auto_alpha")) {
      jButton1.setEnabled(true);
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
     Frame1.mainFrame1.pdfframe.setTextonPage("model", false, 283);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("model", false, 283);
  }

  void Alpha_mouseClicked(MouseEvent e) {
   Frame1.mainFrame1.pdfframe.setTextonPage("alph", false, 283);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("parent_of_origi", false, 283);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fixe", false, 283);
  }

  void jComboBox4_mouseClicked(MouseEvent e) {
Frame1.mainFrame1.pdfframe.setTextonPage("unconstrained", false, 283);
  }

  void jComboBox5_mouseClicked(MouseEvent e) {
Frame1.mainFrame1.pdfframe.setTextonPage("all_pair", false, 283);
  }


}

class Lodpal_Dialog4_Auto_jComboBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog4_Auto adaptee;

  Lodpal_Dialog4_Auto_jComboBox4_mouseAdapter(Lodpal_Dialog4_Auto adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox4_mouseClicked(e);
  }
}

class Lodpal_Dialog4_Auto_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog4_Auto adaptee;

  Lodpal_Dialog4_Auto_jComboBox1_mouseAdapter(Lodpal_Dialog4_Auto adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Lodpal_Dialog4_Auto_jComboBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog4_Auto adaptee;

  Lodpal_Dialog4_Auto_jComboBox5_mouseAdapter(Lodpal_Dialog4_Auto adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox5_mouseClicked(e);
  }
}

class Lodpal_Dialog4_Auto_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog4_Auto adaptee;

  Lodpal_Dialog4_Auto_jCheckBox1_mouseAdapter(Lodpal_Dialog4_Auto adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class Lodpal_Dialog4_Auto_Alpha_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog4_Auto adaptee;

  Lodpal_Dialog4_Auto_Alpha_mouseAdapter(Lodpal_Dialog4_Auto adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.Alpha_mouseClicked(e);
  }
}

class Lodpal_Dialog4_Auto_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog4_Auto adaptee;

  Lodpal_Dialog4_Auto_jComboBox3_mouseAdapter(Lodpal_Dialog4_Auto adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}
