package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Lodpal_Dialog5_XLin
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

  String[] type = {
      "All", "Male-Male", "Male-Female", "Female-Female"};
    String[] realtype = {
        "all", "M-M", "M-F", "F-F"};

  JLabel jLabel6 = new JLabel();
  JLabel jLabel1 = new JLabel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JLabel jLabel4 = new JLabel();
  JComboBox jComboBox1 = new JComboBox(type);
  DataCollectionModel Datamodel;
  JButton jButton2 = new JButton();

  LODPAL2 parent;
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JLabel jLabel2 = new JLabel();
  JTextField Alpha = new JTextField();
  JCheckBox jComboBox3 = new JCheckBox("True");
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JCheckBox jComboBox2 = new JCheckBox("True");

  public Lodpal_Dialog5_XLin(LODPAL2 parent, String title) {
  super(Frame1.mainFrame1, title, true);
  //super(title);
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

    jComboBox3.setFocusPainted(false);
    jComboBox2.setFocusPainted(false);

    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 360));
    this.setResizable(false);

    jLabel1.setToolTipText("Relative recurrence risk when sharing 2 alleles IBD.");
    jLabel1.setText("Lambda2 ");

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
    jComboBox1.addItemListener(this);

    jLabel3.setText("Specifies options for an X-linked model.");

    jLabel6.setToolTipText(
      "Specifies the sex specific pair types to be included in the analysis.");
    jLabel6.setText("Pair type");

    jLabel4.setToolTipText("Relative recurrence risk when sharing 1 allele IBD.");
    jLabel4.setText("Lambda1 ");
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jComboBox1.addMouseListener(new Lodpal_Dialog5_XLin_jComboBox1_mouseAdapter(this));
    jComboBox1.setRequestFocusEnabled(false);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel2.setToolTipText(
            "<html>Specifies the Whittemore and Tu one-parameter model\'s alpha value." +
            "<br>An <b>alpha</b> value of 1 specifies a model with no dominant genetic variance.");
    jLabel2.setText("Alpha");
    Alpha.setText("2.634");
    Alpha.addMouseListener(new Lodpal_Dialog5_XLin_Alpha_mouseAdapter(this));
    Alpha.addMouseListener(new Lodpal_Dialog5_XLin_Alpha_mouseAdapter(this));
    jComboBox3.setToolTipText("<html>Specifies lambda_2ff to be dependent on lambda_1ff.");
    jComboBox3.setMargin(new Insets(2, 0, 2, 2));
    jComboBox3.setSelected(true);
    jComboBox3.setText("Fixed");
    jComboBox3.addItemListener(this);
    jComboBox3.addMouseListener(new Lodpal_Dialog5_XLin_jComboBox3_mouseAdapter(this));
    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout5);
    jComboBox2.setToolTipText("Equal  for all  ASPs.");
    jComboBox2.setMargin(new Insets(2, 0, 2, 2));
    jComboBox2.setSelected(true);
    jComboBox2.setText("Equal");
    jComboBox2.addItemListener(this);
    jComboBox2.addMouseListener(new Lodpal_Dialog5_XLin_jComboBox2_mouseAdapter(this));
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 300, 30));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));
    jCenterPanel.add(jLabel6, new XYConstraints(15, 15, 76, 20));
    jCenterPanel.add(jLabel1,   new XYConstraints(15, 93, 76, 20));
    jCenterPanel.add(jLabel4, new XYConstraints(15, 45, 76, 20));
    jCenterPanel.add(jComboBox1,     new XYConstraints(100, 15, 215, 20));
    jCenterPanel.add(jPanel1,              new XYConstraints(100, 93, 215, 75));
    jPanel1.add(jComboBox3,   new XYConstraints(2, 2, 195, 20));
    jPanel1.add(jLabel2,    new XYConstraints(5, 32, 76, 20));
    jPanel1.add(Alpha,     new XYConstraints(65, 32, 130, 20));
    jCenterPanel.add(jPanel2,    new XYConstraints(100, 45, 215, 38));
    jPanel2.add(jComboBox2,     new XYConstraints(2, 2, 195, 20));


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
    Datamodel.setValue("XLin_pair_type", "all");
    Datamodel.setValue("XLin_lambda1", "true");
    Datamodel.setValue("XLin_lambda2", "true");
    Datamodel.setValue("XLin_lambda2_alpha", "2.634");
    if (Datamodel.hasValue("XLin_lambda2_alpha")) {
      jButton1.setEnabled(true);
    }
  }

  public void save_init_state()
  {
    Datamodel.setValue("XLin", "use");
    parent.jTextField5.setText("Specified");
    parent.jLabel9.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == jButton1) {
      save_init_state();
      dispose();
    }
    else
    {
        dispose();
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == Alpha.getDocument()) {
      Datamodel.setValue("XLin_lambda2_alpha", Alpha.getText());
    }
    if (Datamodel.hasValue("XLin_lambda2_alpha")) {
      jButton1.setEnabled(true);
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
    if (ob == jComboBox1) {
      Datamodel.setValue("XLin_pair_type",
                         realtype[jComboBox1.getSelectedIndex()]);
    }
    if (ob == jComboBox2) {

      if(jComboBox2.isSelected())
        Datamodel.setValue("XLin_lambda1", "true");
      else
        Datamodel.setValue("XLin_lambda1", "false");
    }
    if (ob == jComboBox3) {
      if(jComboBox3.isSelected())
      {
        Datamodel.setValue("XLin_lambda2", "true");
        jLabel2.setEnabled(true);
        Alpha.setEnabled(true);
      }
      else
      {
        Datamodel.setValue("XLin_lambda2", "false");
        jLabel2.setEnabled(false);
        Alpha.setEnabled(false);
      }
    }

    if (Datamodel.hasValue("XLin_lambda2_alpha")) {
      jButton1.setEnabled(true);
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
     Frame1.mainFrame1.pdfframe.setTextonPage("pair_typ", false, 285);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pair_typ", false, 285);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda1_equa", false, 285);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda2_fixed", false, 285);
  }

  void Alpha_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("alph", false, 285);
  }

}

class Lodpal_Dialog5_XLin_jComboBox1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog5_XLin adaptee;

  Lodpal_Dialog5_XLin_jComboBox1_mouseAdapter(Lodpal_Dialog5_XLin adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Lodpal_Dialog5_XLin_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog5_XLin adaptee;

  Lodpal_Dialog5_XLin_jComboBox2_mouseAdapter(Lodpal_Dialog5_XLin adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class Lodpal_Dialog5_XLin_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog5_XLin adaptee;

  Lodpal_Dialog5_XLin_jComboBox3_mouseAdapter(Lodpal_Dialog5_XLin adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}

class Lodpal_Dialog5_XLin_Alpha_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodpal_Dialog5_XLin adaptee;

  Lodpal_Dialog5_XLin_Alpha_mouseAdapter(Lodpal_Dialog5_XLin adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.Alpha_mouseClicked(e);
  }
}
