package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Lodlink_Dialog3
    extends SageDialog
    implements DocumentListener, ActionListener, java.awt.event.ItemListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;
  JLabel jLabel1 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton3 = new JButton();
  //JScrollPane jScrollPane1 = new JScrollPane();
  JLabel jLabel5 = new JLabel();
  JTextField jTextField2 = new JTextField();
  LODLINK2 parent;

  public Lodlink_Dialog3(LODLINK2 parent, String title) {
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
    titledBorder1 = new TitledBorder("Recombination fraction");
    jButton3.setEnabled(false);
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Add");
    jTextField1.setText("");
    jTextField1.addMouseListener(new Lodlink_Dialog3_jTextField1_mouseAdapter(this));
    jLabel4.setForeground(Color.red);
    jLabel4.setRequestFocusEnabled(true);
    jLabel4.setToolTipText("<html>Specifies the female recombination fraction.");
    jLabel4.setText("Female");
    jLabel1.setForeground(Color.red);
    jLabel1.setToolTipText("<html>Specifies the male recombination fraction.");
    jLabel1.setText("Male");
    jPanel2.setLayout(xYLayout5);

    jPanel2.setBorder(titledBorder1);
    jPanel2.setToolTipText("<html>Specifies sex-specific recombination fractions<br>for which a lod score is to be calculated.");

    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jButton3.addActionListener(this);
    jLabel3.setText("Specifies sex-specific recombination fractions.");
    jTextField2.setText("");
    jTextField2.addMouseListener(new Lodlink_Dialog3_jTextField2_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 300, 30));
    jCenterPanel.add(jPanel2, new XYConstraints(15, 10, 300, 254));
    jPanel2.add(jLabel1, new XYConstraints(10, 5, 75, 20));
    jPanel2.add(jTextField1,  new XYConstraints(90, 5, 100, 20));
    jPanel2.add(jLabel4, new XYConstraints(10, 35, 75, 20));
    jPanel2.add(jButton3,  new XYConstraints(200, 25, 80, 30));
    jPanel2.add(jScrollPane1, new XYConstraints(8, 71, 272, 140));
    jPanel2.add(jLabel5, new XYConstraints(10, 94, 145, 20));
    jPanel2.add(jTextField2,  new XYConstraints(90, 35, 100, 20));

    jTextField1.getDocument().addDocumentListener(this);
    jTextField2.getDocument().addDocumentListener(this);
    jMenuItemDelete.addActionListener(this);
  }

  public void save_init_state()
  {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }
    Datamodel.setValue("male_female_theta", resultinfo.trim());

    parent.jTextField3.setText("Specified");
    parent.jLabel15.setFont(new java.awt.Font("Dialog", 1, 11));
    Datamodel.setValue("use_male_female", "use");
  }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();

    if(button == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }

    else if (button == okButton) {
      save_init_state();
      dispose();
    }
    else if (button == cancelButton) {
      dispose();
      if (!Datamodel.hasValue("use_male_female"))
      {
        jTextField1.setText("");
        jTextField2.setText("");
        listModel.removeAllElements();
      }
    }
    else {
      String male = jTextField1.getText();
      String female = jTextField2.getText();

      String data = "theta, " + "male = " +"\""+ male +"\""+ ", female = " +"\""+ female+"\"";

      listModel.addElement(data);
      jTextField1.setText("");
      jTextField2.setText("");
      jButton3.setEnabled(false);

    }
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (jTextField1.getText().length() > 0 &&
        jTextField2.getText().length() > 0) {
      jButton3.setEnabled(true);
    }

  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void itemStateChanged(ItemEvent e) {

  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
     Frame1.mainFrame1.pdfframe.setTextonPage(", mal", false, 305);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", mal", false, 305);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage(", fem", false, 305);
  }
}

class Lodlink_Dialog3_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodlink_Dialog3 adaptee;

  Lodlink_Dialog3_jTextField1_mouseAdapter(Lodlink_Dialog3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class Lodlink_Dialog3_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  Lodlink_Dialog3 adaptee;

  Lodlink_Dialog3_jTextField2_mouseAdapter(Lodlink_Dialog3 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}
