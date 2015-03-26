package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Lodpal_Dialog1_Trait
    extends JDialog
    implements ItemListener, DocumentListener, ActionListener {

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

  JLabel jLabel6 = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  JRadioButton jRadioButton3 = new JRadioButton();
  JLabel jLabel1 = new JLabel();
  JTextField Cutpoint = new JTextField();
  DataCollectionModel Datamodel;
  JComboBox jComboBox1 = new JComboBox();
  JButton jButton2 = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();

  LODPAL2 parent;
  JRadioButton jRadioButton4 = new JRadioButton();

  public Lodpal_Dialog1_Trait(LODPAL2 parent, String title) {
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
    titledBorder1 = new TitledBorder("Type");
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(330, 360));
    this.setResizable(false);

    jRadioButton1.setFocusPainted(false);
    jRadioButton2.setFocusPainted(false);
    jRadioButton3.setFocusPainted(false);

    ButtonGroup bg = new ButtonGroup();
    jLabel6.setToolTipText(
        "<html>Specifies a trait to be used in the current analysis.");
    jLabel1.setToolTipText(
        "<html>Traits that are not binary are dichotomized at this value."+
        "<br>(trait values &lt;= <b><i>cutpoint</i></b> will be treated as unaffected"+
        "<br>and values &gt; <b><i>cutpoint</i></b> will be treated as affected.)");
    jRadioButton1.setToolTipText(
        "<html>Specifies option to analyze affected relative pairs only.");
    jRadioButton1.addMouseListener(new
        Lodpal_Dialog1_Trait_jRadioButton1_mouseAdapter(this));
    jRadioButton2.setToolTipText(
        "<html>Specifies option to pool concordantly affected relative pairs with " +
        "<br>concordantly unaffected sib pairs, and include discordant sib pairs " +
        "<br>in the analysis.");
    jRadioButton2.setText("Affected relative pairs, discordant sibling pairs ");
    jRadioButton2.addMouseListener(new
        Lodpal_Dialog1_Trait_jRadioButton2_mouseAdapter(this));
    jRadioButton3.setToolTipText(
        "<html>Specifies option to analyze concordantly affected relative pairs " +
        "<br>and discordant sib pairs.");
    jRadioButton3.addMouseListener(new
        Lodpal_Dialog1_Trait_jRadioButton3_mouseAdapter(this));

    jRadioButton4.setToolTipText(
        "<html>Specifies option to analyze concordantly affected relative pairs " +
        "<br>and discordant relative pairs.");

    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Cancel");
    jLabel2.setText("& concordantly unaffected sibling pairs");
    jButton1.setEnabled(false);
    jComboBox1.addMouseListener(new
                                Lodpal_Dialog1_Trait_jComboBox1_mouseAdapter(this));
    Cutpoint.addMouseListener(new Lodpal_Dialog1_Trait_Cutpoint_mouseAdapter(this));
    jRadioButton4.setText("Affected relative pairs contrasted to");
    jLabel4.setText("discordant relative pairs");
    jLabel4.setToolTipText(
        "<html>Specifies option to analyze concordantly affected relative pairs " +
        "<br>and discordant relative pairs.");
    bg.add(jRadioButton2);
    bg.add(jRadioButton3);
    bg.add(jRadioButton1);
    bg.add(jRadioButton4);

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
    jLabel3.setText(
        "<html>Specifies a trait to be used in the current " +
        "analysis.");
    jLabel6.setText("Name");
    jPanel1.setLayout(xYLayout4);
    jPanel1.setBorder(titledBorder1);
    jRadioButton1.setSelected(true);

    jRadioButton1.setText("Affected relative pairs");
    jRadioButton3.setText("Affected relative pairs & discordant sibling pairs");
    jLabel1.setText("Cutpoint");
    Cutpoint.setText("0");
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel3,    new XYConstraints(15, 3, 300, 30));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1, new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton2, new XYConstraints(243, 7, 65, 25));
    jCenterPanel.add(jLabel6, new XYConstraints(15, 15, 40, 20));
    jCenterPanel.add(jPanel1,  new XYConstraints(13, 80, 300, 185));

    jPanel1.add(jRadioButton1, new XYConstraints(15, 7, 270, 20));
    jPanel1.add(jRadioButton3, new XYConstraints(15, 37, 270, 20));
    jPanel1.add(jRadioButton2, new XYConstraints(15, 67, 270, 20));
    jPanel1.add(jLabel2,  new XYConstraints(35, 82, 250, 20));
    jPanel1.add(jRadioButton4,     new XYConstraints(15, 112, 274, 20));
    jPanel1.add(jLabel4,     new XYConstraints(35, 127, 274, 20));

    jCenterPanel.add(jLabel1, new XYConstraints(15, 45, 50, 20));
    jCenterPanel.add(Cutpoint, new XYConstraints(70, 45, 240, 20));
    jCenterPanel.add(jComboBox1, new XYConstraints(70, 15, 240, 20));

    Cutpoint.getDocument().addDocumentListener(this);
    jComboBox1.addItemListener(this);

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
    Datamodel.setValue("Trait_type", "conaff");
    Datamodel.setValue("Trait_cutpoint", "0");
  }

  public void save_init_state()
  {
    if (jRadioButton1.isSelected())
      Datamodel.setValue("Trait_type", "conaff");
    else if (jRadioButton2.isSelected())
      Datamodel.setValue("Trait_type", "condisc");
    else if (jRadioButton3.isSelected())
      Datamodel.setValue("Trait_type", "noconunaff");
    else if (jRadioButton4.isSelected())
      Datamodel.setValue("Trait_type", "contrast");


    Datamodel.setValue("Trait_name", jComboBox1.getSelectedItem().toString());
    Datamodel.setValue("Trait", "use");

    parent.jTextField1.setText("Specified");
    parent.jLabel1.setFont(new java.awt.Font("Dialog", 1, 11));
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

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if (ob == jComboBox1) {
      if (jComboBox1.getSelectedIndex()>0)
        jButton1.setEnabled(true);
      else
        jButton1.setEnabled(false);
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == Cutpoint.getDocument()) {
      Datamodel.setValue("Trait_cutpoint", Cutpoint.getText());
    }
    if (Datamodel.hasValue("Trait_cutpoint") && Datamodel.hasValue("Trait_name")) {
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
     Frame1.mainFrame1.pdfframe.setTextonPage("trait ", false, 274);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("trait ", false, 274);
  }

  void Cutpoint_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("cutpoint", false, 274);
  }

  void jRadioButton1_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("conaf", false, 274);
  }

  void jRadioButton3_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("noconunaff", false, 274);
  }

  void jRadioButton2_mouseClicked(MouseEvent e) {
      Frame1.mainFrame1.pdfframe.setTextonPage("condis", false, 274);
  }

}

class Lodpal_Dialog1_Trait_jComboBox1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog1_Trait adaptee;

  Lodpal_Dialog1_Trait_jComboBox1_mouseAdapter(Lodpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Lodpal_Dialog1_Trait_Cutpoint_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog1_Trait adaptee;

  Lodpal_Dialog1_Trait_Cutpoint_mouseAdapter(Lodpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.Cutpoint_mouseClicked(e);
  }
}

class Lodpal_Dialog1_Trait_jRadioButton1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog1_Trait adaptee;

  Lodpal_Dialog1_Trait_jRadioButton1_mouseAdapter(Lodpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton1_mouseClicked(e);
  }
}

class Lodpal_Dialog1_Trait_jRadioButton3_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog1_Trait adaptee;

  Lodpal_Dialog1_Trait_jRadioButton3_mouseAdapter(Lodpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton3_mouseClicked(e);
  }
}

class Lodpal_Dialog1_Trait_jRadioButton2_mouseAdapter
    extends java.awt.event.MouseAdapter {
  Lodpal_Dialog1_Trait adaptee;

  Lodpal_Dialog1_Trait_jRadioButton2_mouseAdapter(Lodpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }

  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton2_mouseClicked(e);
  }
}
