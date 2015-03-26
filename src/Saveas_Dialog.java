package sage;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.File;

public class Saveas_Dialog extends JDialog implements DocumentListener, ActionListener{
  JPanel panel1 = new JPanel();
  TitledBorder titledBorder1;
  JPanel jTopPanel = new JPanel();
  JPanel jMiddlePanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel1 = new JLabel();
  JPanel jPanel1 = new JPanel();
  JTextField DirPath = new JTextField();
  JLabel jLabel3 = new JLabel();
  JButton DirButton = new JButton();
  JTextField pName = new JTextField();
  JLabel jLabel2 = new JLabel();
  XYLayout xYLayout3 = new XYLayout();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout(5,0);
  Border border1;
  EdgeBorder lineborder = new EdgeBorder();
  Border border2;
  JPanel jPanel2 = new JPanel();
  JButton jButton2 = new JButton();
  JButton jButton1 = new JButton();
  BorderLayout borderLayout4 = new BorderLayout();
  JLabel jLabel7 = new JLabel();
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel8 = new JLabel();
  Border border3;
  boolean rename = false;
    JCheckBox jCheckBox1 = new JCheckBox();

    public Saveas_Dialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public Saveas_Dialog() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Project location");
    border1 = BorderFactory.createCompoundBorder(new TitledBorder(BorderFactory.createEtchedBorder(Color.white,new Color(165, 163, 151)),"Project location"),BorderFactory.createEmptyBorder(2,5,5,5));
    border2 = BorderFactory.createEmptyBorder(0,10,0,0);
    border3 = BorderFactory.createEmptyBorder(0,0,0,10);
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(400,220));
    jLabel1.setBorder(border2);
    jLabel8.setBorder(border3);
    jLabel8.setIcon(new ImageIcon(Saveas_Dialog.class.getResource("PEDIGREE.PNG")));
    jLabel1.setText("Name your data file.");
    jPanel1.setBorder(border1);
    jPanel1.setLayout(borderLayout3);
    DirPath.setBackground(Color.white);
    DirPath.setBorder(BorderFactory.createLoweredBevelBorder());
    DirPath.setEditable(false);
    DirPath.setText("");
    jLabel3.setText("Directory");
    DirButton.setMargin(new Insets(2, 2, 2, 2));
    DirButton.setText("Browse...");
    pName.setText("");
    jLabel2.setText("Name");
    jMiddlePanel.setLayout(xYLayout3);
    jTopPanel.setLayout(borderLayout2);
    jTopPanel.setBackground(Color.white);
    jTopPanel.setPreferredSize(new Dimension(400,35));
    jMiddlePanel.setBorder(lineborder);
    jMiddlePanel.setPreferredSize(new Dimension(400,140));
    jBottomPanel.setPreferredSize(new Dimension(400,45));
    jBottomPanel.setLayout(borderLayout4);
    borderLayout3.setVgap(5);
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Cancel");
    jButton1.setEnabled(false);
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("OK");
    jPanel2.setLayout(xYLayout1);
    jLabel7.setBorder(null);
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Import this file into project");
        getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel1, BorderLayout.CENTER);
    jTopPanel.add(jLabel8,  BorderLayout.EAST);
    jMiddlePanel.add(pName,            new XYConstraints(95, 15, 280, 20));
    jMiddlePanel.add(jPanel1,               new XYConstraints(20, 45, 360, 53));
    jPanel1.add(DirPath, BorderLayout.CENTER);
    jPanel1.add(DirButton, BorderLayout.EAST);
    jPanel1.add(jLabel3, BorderLayout.WEST);
    jMiddlePanel.add(jLabel2,       new XYConstraints(30, 15, 60, 20));
        panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jButton1,      new XYConstraints(145, 10, 55, 25));
    jPanel2.add(jButton2,          new XYConstraints(206, 10, 55, 25));
    jBottomPanel.add(jLabel7, BorderLayout.NORTH);
    panel1.add(jMiddlePanel, BorderLayout.CENTER);
        jMiddlePanel.add(jCheckBox1, new XYConstraints(20, 110, 200, 20));
        DirButton.addActionListener(this);
    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    pName.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();
    if (button == DirButton)
      DirButton_actionPerformed(e);
    else if(button == jButton1)
    {
      rename = true;

      String filepath = DirPath.getText();
      String filename = pName.getText();

      File pp = new File(filepath);
      File[] files = pp.listFiles();
      for (int i = 0; i < files.length; i++) {
          if (files[i].isFile()) {
              if (files[i].getName().compareTo(filename) == 0) {
                  int n = JOptionPane.showOptionDialog(this.getParent(),
                          "The data file named '" + filename +
                          "' already exists." +
                          "\nYou must rename the data file or specify a different location. ",
                          "File name Error",
                          JOptionPane.CLOSED_OPTION,
                          JOptionPane.WARNING_MESSAGE, null, null, null);
                  return;
              }
          }
        }

      dispose();
    }
    else if(button == jButton2)
    {
      rename = false;
      dispose();
    }
  }

  public void DirButton_actionPerformed(ActionEvent e) {
    JFileChooser chooser = new JFileChooser(DirPath.getText());
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      DirPath.setText(chooser.getSelectedFile().toString() +
                      System.getProperty("file.separator"));
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == pName.getDocument()) {
      if(pName.getText().length()>0)
        jButton1.setEnabled(true);
      else
        jButton1.setEnabled(false);
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

}
