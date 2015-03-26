package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class Sibpal_Dialog1_Trait
    extends SageDialog
    implements ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  String option[] = {
      "sample mean", "sibship mean","BLUP mean", "specified mean"};

  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JLabel jLabel1 = new JLabel();
  JTextField MeanValue = new JTextField();

  DataCollectionModel Datamodel;
  JComboBox jComboBox1 = new JComboBox();
  ButtonGroup bg = new ButtonGroup();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JComboBox jComboBox2 = new JComboBox(option);
  JButton jAddButton = new JButton();
  JLabel jLabel5 = new JLabel();

  SIBPAL4 parent;

  public Sibpal_Dialog1_Trait(SIBPAL4 parent, String title) {
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
    titledBorder1 = new TitledBorder("");

    jLabel1.setEnabled(false);

    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jLabel3.setText("Specifies trait to be used as the dependent variable.");
    jPanel1.setLayout(xYLayout4);
    jPanel1.setBorder(titledBorder1);
    jLabel1.setText("Value");
    MeanValue.setEnabled(false);
    MeanValue.addMouseListener(new Sibpal_Dialog1_Trait_MeanValue_mouseAdapter(this));
    jLabel2.setToolTipText("Specifies a trait.");
    jLabel2.setText("Trait");
    jLabel4.setToolTipText(
            "<html>Specifies how to calculate the mean of the trait."+
            "<br><b>BLUP</b>(best linear unbiased predictor) is the best,"+
            "<br>but makes the program run more slowly.");
    jLabel4.setText("Mean");
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add trait");
    jAddButton.addActionListener(this);
    jComboBox2.setEnabled(false);
    jComboBox2.setSelectedIndex(2);
    jComboBox2.addMouseListener(new Sibpal_Dialog1_Trait_jComboBox2_mouseAdapter(this));
    jComboBox1.addMouseListener(new Sibpal_Dialog1_Trait_jComboBox1_mouseAdapter(this));
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 289, 30));
    jCenterPanel.add(jPanel1, new XYConstraints(15, 10, 300, 254));
    jPanel1.add(MeanValue,  new XYConstraints(90, 65, 105, 20));
    jPanel1.add(jComboBox1,  new XYConstraints(90, 5, 190, 20));
    jPanel1.add(jLabel2, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jLabel4, new XYConstraints(30, 35, 50, 20));
    jPanel1.add(jComboBox2,  new XYConstraints(90, 35, 105, 20));
    jPanel1.add(jAddButton,     new XYConstraints(205, 55, 75, 30));
    jPanel1.add(jLabel5, new XYConstraints(10, 129, 156, 20));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 101, 272, 133));
    jPanel1.add(jLabel1, new XYConstraints(30, 65, 50, 20));

    jComboBox1.addItemListener(this);
    jComboBox2.addItemListener(this);
    jMenuItemDelete.addActionListener(this);

  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
  }

  public void save_init_state()
  {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }
    Datamodel.setValue("Trait_info", resultinfo.trim());
    Datamodel.setValue("trait", "use");
    parent.jTextField1.setText("Specified");
    parent.jLabel3.setFont(new java.awt.Font("Dialog", 1, 11));
  }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();
    if(button == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }

    if (button == okButton) {
      save_init_state();
      dispose();
    }

    else if (button == jAddButton) {
      if (jComboBox1.getSelectedItem() != null) {
        int index = jComboBox2.getSelectedIndex();

        String covlist = new String();
        switch (index) {
        case 0: //sample mean
          covlist = "trait = " + "\"" + jComboBox1.getSelectedItem().toString() + "\"";
          break;
        case 1: //sibship mean
          covlist = "trait = " + "\"" + jComboBox1.getSelectedItem().toString() + "\"";
          covlist = covlist + ", mean = \"sibship\"";
          break;
        case 2: //BLUP mean
          covlist = "trait = " + "\"" + jComboBox1.getSelectedItem().toString() + "\"";
          covlist = covlist + ", mean = \"blup\"";
          break;
        case 3: //specified mean
          covlist = "trait = " + "\"" + jComboBox1.getSelectedItem().toString() + "\"";
          if (MeanValue.getText().length() > 0)
            covlist = covlist + ", mean = " + "\"" + MeanValue.getText() + "\"";
          break;
        }

        listModel.addElement(covlist);
        jComboBox1.setSelectedIndex(0);
        MeanValue.setText("");
        jComboBox2.setSelectedIndex(0);
      }
    }
    else  if (button == cancelButton){
      if (!Datamodel.hasValue("trait"))
        listModel.removeAllElements();
      jComboBox1.setSelectedIndex(0);
      dispose();
   }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if (ob == jComboBox1) {
      if (jComboBox1.getSelectedIndex() == 0) {
        jAddButton.setEnabled(false);
        jComboBox2.setEnabled(false);
        MeanValue.setEnabled(false);
        jLabel1.setEnabled(false);
      }
      else {
        jAddButton.setEnabled(true);
        jComboBox2.setEnabled(true);
      }
    }
    if (ob == jComboBox2) {
      int index = jComboBox2.getSelectedIndex();
      switch (index) {
        case 0://sample mean
        case 1://sibship mean
        case 2://BLUP mean
          MeanValue.setEnabled(false);
          jLabel1.setEnabled(false);
          MeanValue.setText("");
          break;
        case 3://specified mean
          MeanValue.setEnabled(true);
          jLabel1.setEnabled(true);
          break;
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 254);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 254);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 254);
  }

  void MeanValue_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 254);
  }

  void Threshold_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("trait", false, 254);
  }
}

class Sibpal_Dialog1_Trait_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog1_Trait adaptee;

  Sibpal_Dialog1_Trait_jComboBox1_mouseAdapter(Sibpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class Sibpal_Dialog1_Trait_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog1_Trait adaptee;

  Sibpal_Dialog1_Trait_jComboBox2_mouseAdapter(Sibpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class Sibpal_Dialog1_Trait_MeanValue_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog1_Trait adaptee;

  Sibpal_Dialog1_Trait_MeanValue_mouseAdapter(Sibpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.MeanValue_mouseClicked(e);
  }
}

class Sibpal_Dialog1_Trait_Threshold_mouseAdapter extends java.awt.event.MouseAdapter {
  Sibpal_Dialog1_Trait adaptee;

  Sibpal_Dialog1_Trait_Threshold_mouseAdapter(Sibpal_Dialog1_Trait adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.Threshold_mouseClicked(e);
  }
}
