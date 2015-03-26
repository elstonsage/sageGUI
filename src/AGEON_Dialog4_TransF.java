package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class AGEON_Dialog4_TransF
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

  ButtonGroup bg = new ButtonGroup();
  ButtonGroup bg2 = new ButtonGroup();
  JLabel jLabel1 = new JLabel();
  JPanel jincenterPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JCheckBox jComboBox1 = new JCheckBox("True");
  JTextField jTextField1 = new JTextField();
  JLabel jLabel7 = new JLabel();
  JPanel jincenterPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JCheckBox jComboBox2 = new JCheckBox("True");
  JButton jButton2 = new JButton();
  JLabel jLabel9 = new JLabel();

  AGEON2 parent;

  public AGEON_Dialog4_TransF(AGEON2 parent, String title) {
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
    jComboBox1.setFocusPainted(false);
    jComboBox2.setFocusPainted(false);
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 360));
    this.setResizable(true);

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
    jLabel3.setText("<html>Specifies the <i>transformation</i> sub-block.");
    jLabel1.setToolTipText("Specifies of the power parameter, lambda1.");
    jLabel1.setText("Lambda1");
    jincenterPanel1.setBorder(titledBorder1);
    jincenterPanel1.setLayout(xYLayout4);
    jComboBox1.addMouseListener(new AGEON_Dialog4_TransF_jComboBox1_mouseAdapter(this));
    jComboBox2.addMouseListener(new AGEON_Dialog4_TransF_jComboBox2_mouseAdapter(this));
    jTextField1.setText("1.0");
    jTextField1.addMouseListener(new AGEON_Dialog4_TransF_jTextField1_mouseAdapter(this));
    jLabel7.setToolTipText("Specifies the value for lambda1.");
    jLabel7.setText("Value");
    jincenterPanel2.setBorder(titledBorder1);
    jincenterPanel2.setLayout(xYLayout5);
    jLabel8.setToolTipText("Specifies the value for lambda2.");
    jLabel8.setText("Value");
    jTextField2.setText("0.5");
    jTextField2.addMouseListener(new AGEON_Dialog4_TransF_jTextField2_mouseAdapter(this));
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jLabel9.setToolTipText("Specifies the shift parameter, lambda2.");
    jLabel9.setText("Lambda2");
    jComboBox1.setToolTipText("Specifies option to fix lambda1 at given value.");
    jComboBox1.setMargin(new Insets(2, 0, 2, 2));
    jComboBox1.setText("Fixed");
    jComboBox2.setToolTipText("Specifies option to fix lambda2 at given value, which must not be 0.");
    jComboBox2.setMargin(new Insets(2, 0, 2, 2));
    jComboBox2.setText("Fixed");
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 266, 30));
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1,  new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2,  new XYConstraints(243, 7, 65, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jincenterPanel1.add(jLabel7, new XYConstraints(5, 5, 50, 20));
    jincenterPanel1.add(jTextField1, new XYConstraints(75, 5, 135, 20));
    jincenterPanel1.add(jComboBox1, new XYConstraints(75, 35, 135, 20));
    jCenterPanel.add(jincenterPanel2, new XYConstraints(85, 100, 230, 75));
    jCenterPanel.add(jLabel1, new XYConstraints(15, 15, 52, 20));
    jincenterPanel2.add(jLabel8, new XYConstraints(5, 5, 50, 20));
    jincenterPanel2.add(jTextField2, new XYConstraints(75, 5, 135, 20));
    jincenterPanel2.add(jComboBox2, new XYConstraints(75, 35, 135, 20));
    jCenterPanel.add(jLabel9, new XYConstraints(15, 100, 62, 21));
    jCenterPanel.add(jincenterPanel1, new XYConstraints(85, 15, 230, 75));

    jComboBox1.setFocusable(false);
    jComboBox2.setFocusable(false);
    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);

    jTextField1.getDocument().addDocumentListener(this);
    jTextField2.getDocument().addDocumentListener(this);

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
    Datamodel.setValue("lambda1_val", jTextField1.getText());
    Datamodel.setValue("lambda2_val", jTextField2.getText());
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
    parent.jTextField4.setText("Specified");
    parent.jLabel8.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      if (jTextField1.getText().length() <= 0) {
        jComboBox1.setEnabled(false);
        Datamodel.removeValue("lambda1_val");
      }
      else {
        jComboBox1.setEnabled(true);
        Datamodel.setValue("lambda1_val", jTextField1.getText());
      }
    }
    if (document == jTextField2.getDocument()) {
      if (jTextField2.getText().length() <= 0) {
        jComboBox2.setEnabled(false);
        Datamodel.removeValue("lambda2_val");
      }
      else {
        jComboBox2.setEnabled(true);
        Datamodel.setValue("lambda2_val", jTextField2.getText());
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

    if (ob == jComboBox1) {
      if(jComboBox1.isSelected())
        Datamodel.setValue("lambda1_fixed", "true");
      else
      {
          if (Datamodel.hasValue("lambda1_fixed"))
              Datamodel.removeValue("lambda1_fixed");
      }
    }
    if (ob == jComboBox2) {
      if(jComboBox2.isSelected())
        Datamodel.setValue("lambda2_fixed", "true");
      else
      {
          if (Datamodel.hasValue("lambda2_fixed"))
              Datamodel.removeValue("lambda2_fixed");
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
     Frame1.mainFrame1.pdfframe.setTextonPage("lambda1 ", false, 383);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda1 ", false, 383);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda1 ", false, 383);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda2 ", false, 383);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("lambda2 ", false, 383);
  }

}

class AGEON_Dialog4_TransF_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog4_TransF adaptee;

  AGEON_Dialog4_TransF_jTextField1_mouseAdapter(AGEON_Dialog4_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class AGEON_Dialog4_TransF_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog4_TransF adaptee;

  AGEON_Dialog4_TransF_jComboBox1_mouseAdapter(AGEON_Dialog4_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class AGEON_Dialog4_TransF_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog4_TransF adaptee;

  AGEON_Dialog4_TransF_jTextField2_mouseAdapter(AGEON_Dialog4_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class AGEON_Dialog4_TransF_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  AGEON_Dialog4_TransF adaptee;

  AGEON_Dialog4_TransF_jComboBox2_mouseAdapter(AGEON_Dialog4_TransF adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

