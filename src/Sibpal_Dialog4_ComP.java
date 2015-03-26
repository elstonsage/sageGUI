package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class Sibpal_Dialog4_ComP
    extends JDialog
    implements ActionListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;
  JTextField jTextField4 = new JTextField();
  JTextField jTextField5 = new JTextField();

  JLabel jCheckBox1 = new JLabel();
  JLabel jCheckBox2 = new JLabel();
  JLabel jCheckBox3 = new JLabel();
  JLabel jCheckBox4 = new JLabel();
  JLabel jCheckBox5 = new JLabel();

  JPanel jPanel1 = new JPanel();
  JTextField jTextField3 = new JTextField();
  XYLayout xYLayout4 = new XYLayout();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField1 = new JTextField();

  SIBPAL4 parent;

  public Sibpal_Dialog4_ComP(SIBPAL4 parent, String title) {
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
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 360));
    this.setResizable(false);

    jTopPanel.setPreferredSize(new Dimension(40, 40));
    jTopPanel.setLayout(xYLayout2);
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(xYLayout1);
    jCenterPanel.setPreferredSize(new Dimension(327, 280));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(40, 40));
    jBottomPanel.setLayout(xYLayout3);

    jButton1.setEnabled(true);
    jButton1.setText("OK");
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jLabel3.setText("Specifies options for computing empirical P-values.");
    jTextField4.setText("0.2");
    jTextField4.addMouseListener(new Sibpal_Dialog4_ComP_jTextField4_mouseAdapter(this));
    jTextField4.setEditable(true);
    jCheckBox2.setText("Permutations");
    jCheckBox2.addMouseListener(new Sibpal_Dialog4_ComP_jCheckBox2_mouseAdapter(this));
    jCheckBox2.setToolTipText(
            "<html>Specifies an exact number of permutations that should always be"+
            "<br>performed if the asymptotic P-value is less than the threshold."+
            "<br>Use of this option effectively overrides all of the following options.");
    jTextField5.setText("0.95");
    jTextField5.addMouseListener(new
                                 Sibpal_Dialog4_ComP_jTextField5_mouseAdapter(this));
    jTextField5.setEditable(true);
    jCheckBox1.setText("Threshold P-value");
    jCheckBox1.addMouseListener(new Sibpal_Dialog4_ComP_jCheckBox1_mouseAdapter(this));
    jCheckBox1.setToolTipText(
            "<html>Only compute empirical P-values for asymptotic pvalues" +
            "<br>less than this value.");
    jPanel1.setLayout(xYLayout4);
    jPanel1.setBorder(titledBorder1);
    jTextField3.setEditable(true);
    jTextField3.setText("10000");
    jTextField3.addMouseListener(new
                                 Sibpal_Dialog4_ComP_jTextField3_mouseAdapter(this));
    jCheckBox5.setToolTipText(
            "<html>Specifies the probability that an empirical P-value will be within" +
            "<br>a relative interval (i.e., the width) of its true value.");
    jCheckBox5.setText("Confidence");
    jCheckBox5.addMouseListener(new Sibpal_Dialog4_ComP_jCheckBox5_mouseAdapter(this));
    jTextField2.setEditable(true);
    jTextField2.setText("");
    jTextField2.addMouseListener(new Sibpal_Dialog4_ComP_jTextField2_mouseAdapter(this));
    jCheckBox3.setToolTipText(
        "<html>Specifies the maximum number of permutations that<br>should be performed.");
    jCheckBox3.setText("Max permutations");
    jCheckBox3.addMouseListener(new Sibpal_Dialog4_ComP_jCheckBox3_mouseAdapter(this));
    jCheckBox4.setToolTipText(
            "<html>Specifies the relative precision of the empirical P-value.");
    jCheckBox4.setText("Width of P-value");
    jCheckBox4.addMouseListener(new Sibpal_Dialog4_ComP_jCheckBox4_mouseAdapter(this));
    jTextField1.setEditable(true);
    jTextField1.setText("0.05");
    jTextField1.addMouseListener(new Sibpal_Dialog4_ComP_jTextField1_mouseAdapter(this));

    getContentPane().add(panel1);
    jPanel1.add(jCheckBox1,  new XYConstraints(5, 5, 120, 20));
    jPanel1.add(jTextField1,      new XYConstraints(130, 5, 150, 20));
    jPanel1.add(jTextField5,    new XYConstraints(130, 125, 150, -1));
    jPanel1.add(jCheckBox5,   new XYConstraints(5, 125, 101, 20));
    jPanel1.add(jTextField2,     new XYConstraints(130, 35, 150, 20));
    jPanel1.add(jCheckBox2,   new XYConstraints(5, 35, 101, 20));
    jPanel1.add(jTextField3,    new XYConstraints(130, 65, 150, -1));
    jPanel1.add(jCheckBox3,   new XYConstraints(5, 65, 101, 20));
    jPanel1.add(jCheckBox4,   new XYConstraints(5, 95, 101, 20));
    jPanel1.add(jTextField4,    new XYConstraints(130, 95, 150, -1));

    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 300, 30));
    jCenterPanel.add(jPanel1,     new XYConstraints(15, 15, 300, 165));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));
    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));


    panel1.add(jTopPanel, BorderLayout.NORTH);
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);


    this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1Pressed");

    this.getRootPane().getActionMap().
            put("F1Pressed", new AbstractAction("F1Pressed") {
        public void actionPerformed(ActionEvent actionEvent) {
            onPressedF1();
        }
      });
  }

  public void save_init_state()
  {
    if (jTextField1.getText().length() > 0)
      Datamodel.setValue("cep_threshold", jTextField1.getText());

    if (jTextField2.getText().length() > 0)
      Datamodel.setValue("cep_replicates", jTextField2.getText());

    if (jTextField3.getText().length() > 0)
      Datamodel.setValue("cep_max_r", jTextField3.getText());

    if (jTextField4.getText().length() > 0)
      Datamodel.setValue("cep_width", jTextField4.getText());

    if (jTextField5.getText().length() > 0)
      Datamodel.setValue("cep_confidence", jTextField5.getText());

    Datamodel.setValue("compute_empirical_pvalues","true");

    parent.jTextField4.setText("Specified");
    parent.jLabel17.setFont(new java.awt.Font("Dialog", 1, 11));
    parent.jLabel18.setFont(new java.awt.Font("Dialog", 1, 11));
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

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("compute_empirical_pvalue", false, 257);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("compute_empirical_pvalue", false, 257);
  }

  void jCheckBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", threshold", false, 257);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", threshold", false, 257);
  }

  void jCheckBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", permutations", false, 257);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", permutations", false, 257);
  }

  void jCheckBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("max_permutations", false, 257);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("max_permutations", false, 257);
  }

  void jCheckBox4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("width", false, 257);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("width", false, 257);
  }

  void jCheckBox5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("confidenc", false, 258);
  }

  void jTextField5_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("confidenc", false, 258);
  }









}

class Sibpal_Dialog4_ComP_jCheckBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jCheckBox1_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox1_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jTextField1_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jCheckBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jCheckBox2_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox2_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jTextField2_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jCheckBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jCheckBox3_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox3_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jTextField3_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jCheckBox4_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jCheckBox4_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox4_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jTextField4_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jCheckBox5_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jCheckBox5_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jCheckBox5_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jTextField5_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jTextField5_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField5_mouseClicked(e);
  }
}

class Sibpal_Dialog4_ComP_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog4_ComP adaptee;

  Sibpal_Dialog4_ComP_jComboBox1_mouseAdapter(Sibpal_Dialog4_ComP adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}
