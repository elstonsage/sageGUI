package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class DECIPHER_Dialog3_Pools_sub
    extends SageDialog implements DocumentListener, ItemListener, ActionListener {
  JPanel jPanel2 = new JPanel();
  TitledBorder titledBorder1;

  JButton jAddButton = new JButton();
  JLabel jLabel4 = new JLabel();
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JComboBox jComboBox1 = new JComboBox();
  DataCollectionModel Datamodel;
  DECIPHER_Dialog3_Pools parent;
  JLabel jLabeltitle = new JLabel();
  public void showDialog(int x, int y) {
    this.setLocation(x, y);
    this.setVisible(true);
  }

  public DECIPHER_Dialog3_Pools_sub(DECIPHER_Dialog3_Pools parent, String title) {
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

  private void jbInit() {
    titledBorder1 = new TitledBorder("");
    this.setSize(new Dimension(320, 280));

    jLabeltitle.setText("Specifies the locus.");

    jTopPanel.setPreferredSize(new Dimension(320, 40));
    jCenterPanel.setPreferredSize(new Dimension(320, 200));
    jBottomPanel.setPreferredSize(new Dimension(320, 40));
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add allele");
    jAddButton.addActionListener(this);
    jLabel4.setText("Frequencies");
    jLabel4.setToolTipText("Name of a trait used to specify allele probabilities.");

    cancelButton.addActionListener(this);

    okButton.addActionListener(this);

    jMenuItemDelete.addActionListener(this);

    jComboBox1.addMouseListener(new DECIPHER_Dialog3_Pools_sub_jComboBox1_mouseAdapter(this));
    jTextField1.addMouseListener(new DECIPHER_Dialog3_Pools_sub_jTextField1_mouseAdapter(this));

    jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout1);
    jLabel3.setText("Allele");
    jLabel3.setToolTipText("Specifies an allele name.");
    jTextField1.setText("2");

    jTopPanel.add(jLabeltitle,  new XYConstraints(15, 3, 292, 30));

    jCenterPanel.add(jPanel2,       new XYConstraints(15, 15, 290, 170));
    jPanel2.add(jScrollPane1,        new XYConstraints(8, 70, 262, 81));
    jPanel2.add(jLabel4, new XYConstraints(30, 35, 90, 20));
    jPanel2.add(jLabel3, new XYConstraints(10, 5, 75, 20));
    jPanel2.add(jTextField1,    new XYConstraints(100, 5, 170, 20));
    jPanel2.add(jAddButton,    new XYConstraints(206, 34, 65, 25));
    jPanel2.add(jComboBox1,   new XYConstraints(100, 35, 100, 20));

    this.getRootPane().getInputMap(JComponent.
                                   WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
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
  }

  public void save_init_state() {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }

    resultinfo = resultinfo.substring(0, resultinfo.length()-1);

    if (resultinfo.length() > 0)
      Datamodel.setValue("allele_trait_info", resultinfo.trim());

    if (Datamodel.hasValue("allele_trait_info")) {
      parent.jTextField3.setText("Specified");
      parent.jLabel4.setFont(new java.awt.Font("Dialog", 1, 11));
    }
  }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();
    if (button == jMenuItemDelete) {
      int index = infoList.getSelectedIndex();
      listModel.remove(index);
    }

    else if (button == okButton) {
      save_init_state();
      dispose();
    }
    else if (button == cancelButton) {
      if (!Datamodel.hasValue("allele_trait_info"))
      {
        listModel.removeAllElements();
        Datamodel.removeValue("allele_trait_info");
      }
      dispose();
    }
    else if (button == jAddButton) {
      if (jTextField1.getText().length() > 0) {
        String allele = "allele = " + "\"" + jTextField1.getText().trim()+ "\"";
        if(jComboBox1.getSelectedItem().toString().length()>0)
            allele = allele + ", trait = " + "\"" + jComboBox1.getSelectedItem().toString() + "\"";
        listModel.addElement(allele);
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
      }
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
  }

  void onPressedF1() {
    Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
    Frame1.mainFrame1.pdfframe.setTextonPage("allele", false, 414);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 414);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("allele", false, 414);
  }
}

class DECIPHER_Dialog3_Pools_sub_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog3_Pools_sub adaptee;

  DECIPHER_Dialog3_Pools_sub_jComboBox1_mouseAdapter(DECIPHER_Dialog3_Pools_sub adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class DECIPHER_Dialog3_Pools_sub_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog3_Pools_sub adaptee;

  DECIPHER_Dialog3_Pools_sub_jTextField1_mouseAdapter(DECIPHER_Dialog3_Pools_sub adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}
