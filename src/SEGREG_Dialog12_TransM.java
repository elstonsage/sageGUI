package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SEGREG_Dialog12_TransM
    extends SageDialog
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {

  XYLayout xYLayout1 = new XYLayout();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

  JLabel jLabel6 = new JLabel();
  TitledBorder titledBorder1;

  DataCollectionModel Datamodel;

  String[] option = {
      "Homogeneous no transmission",
      "Homogeneous mendelian transmission",
      "Heterozygote transmission lies between those for homozygotes",
      "Heterozygote transmission estimated, the others fixed at 1 and 0",
      "All transmissions estimated",
      "No transmission"};
  String[] option_real = {
      "homog_no_trans",
      "homog_mendelian",
      "homog_general",
      "tau_ab_free",
      "general",
      "no_trans"};


  String[] mean = {
      "", "\u03C4AA", "\u03C4AB", "\u03C4BB", "\u03C4AA=\u03C4AB",
      "\u03C4BB=\u03C4AB", "\u03C4AA=\u03C4AB=\u03C4BB"};

  String[] realmean = {
      "", "AA", "AB", "BB", "A*", "B*", "**"};


  JComboBox optionComboBox = new JComboBox(option);
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  JComboBox tauComboBox = new JComboBox(mean);
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JCheckBox fixedCheckBox = new JCheckBox();
  JButton jAddButton = new JButton();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel4 = new JLabel();

  JTextField resulttextfield;
  JLabel resultlabel;

  String message = "You must either specify Hardy Weinberg equilibrium\n"+
      "for the genotype frequency or\n"+
      "leave the genotype frequency undefined.";
  JCheckBox noboundsCheckBox = new JCheckBox();

boolean IsPopup = true;


  public SEGREG_Dialog12_TransM(String title) {
    super(title);

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

    this.setSize(new Dimension(400, 360));
    jTopPanel.setPreferredSize(new Dimension(400, 40));
    jCenterPanel.setPreferredSize(new Dimension(400, 280));
    jBottomPanel.setPreferredSize(new Dimension(400, 40));

    noboundsCheckBox.setEnabled(false);

    okButton.addActionListener(this);
    cancelButton.addActionListener(this);
    jLabel3.setText("Specifies the transmission model.");
    jLabel6.setToolTipText("Specifies transmission type.");
    jLabel6.setText("Option");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout4);
    jLabel1.setToolTipText("Specifies transmission probability type.");
    jLabel1.setText("Tau");
    fixedCheckBox.setEnabled(false);
    fixedCheckBox.setToolTipText("Option to fix the given value.");
    fixedCheckBox.setText("Fixed");
    fixedCheckBox.addMouseListener(new SEGREG_Dialog12_TransM_jComboBox3_mouseAdapter(this));
    jAddButton.setEnabled(false);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add tau");
    jLabel4.setToolTipText("Specifies a value for the parameter.");
    jLabel4.setText("Value");
    jTextField1.setEnabled(false);
    jTextField1.addMouseListener(new SEGREG_Dialog12_TransM_jTextField1_mouseAdapter(this));

    tauComboBox.addMouseListener(new SEGREG_Dialog12_TransM_jComboBox2_mouseAdapter(this));
    noboundsCheckBox.setToolTipText(
        "<html>Option to remove the range restriction on the transmission" +
        "<br>probabilities when the option value is set to either"+
        "<br><b>heterozygote transmission lies between those for"+
        "<br>homozygotes</b> or <b>all transmissions estimated</b>.");
    noboundsCheckBox.setMargin(new Insets(2, 0, 2, 2));
    noboundsCheckBox.setText("No bounds ");
    jTopPanel.add(jLabel3, new XYConstraints(15, 3, 330, 30));
    jCenterPanel.add(jLabel6, new XYConstraints(15, 15, 47, 20));
    jCenterPanel.add(optionComboBox,   new XYConstraints(55, 15, 330, 20));
    jCenterPanel.add(jPanel1,  new XYConstraints(15, 75, 370, 190));
    jPanel1.add(jLabel1, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jTextField1, new XYConstraints(90, 35, 160, 20));
    jPanel1.add(fixedCheckBox,   new XYConstraints(270, 35, 70, 20));
    jPanel1.add(tauComboBox,  new XYConstraints(90, 5, 255, 20));
    jPanel1.add(jAddButton,  new XYConstraints(260, 55, 85, 30));
    jPanel1.add(jLabel2, new XYConstraints(10, 99, 156, 20));
    jPanel1.add(jScrollPane1,      new XYConstraints(8, 95, 338, 73));
    jPanel1.add(jLabel4, new XYConstraints(30, 35, 50, 20));
    jCenterPanel.add(noboundsCheckBox,            new XYConstraints(55, 45, 274, 20));

    optionComboBox.setFocusable(false);
    tauComboBox.setFocusable(false);
    fixedCheckBox.setFocusable(false);

    optionComboBox.addActionListener(this);
    tauComboBox.addItemListener(this);
    jTextField1.getDocument().addDocumentListener(this);
    jAddButton.addActionListener(this);
    jMenuItemDelete.addActionListener(this);

    okButton.setEnabled(true);
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
    Datamodel.setValue("transmission_option", "homog_no_trans");
  }

  public void save_init_state()
  {
    String resultinfo = new String();
    for (int i = 0; i < listModel.size(); i++) {
      resultinfo = resultinfo + listModel.get(i).toString() + "\n";
    }
    if(resultinfo.length()>0)
      Datamodel.setValue("transmission_info", resultinfo.trim());
    Datamodel.setValue("transmission", "use");

    if (noboundsCheckBox.isEnabled())
    {
      if (noboundsCheckBox.isSelected()) { //use
        Datamodel.setValue("transmission_no_bounds", "use");
      }
      else {
        if (Datamodel.hasValue("transmission_no_bounds"))
          Datamodel.removeValue("transmission_no_bounds");
      }
    }
    else //disable
    {
      if (Datamodel.hasValue("transmission_no_bounds"))
        Datamodel.removeValue("transmission_no_bounds");
    }

    if(optionComboBox.getSelectedIndex() == 1)//Homogeneous mendelian transmission
    {
        if (Datamodel.hasValue("transmission_no_bounds"))
            Datamodel.removeValue("transmission_no_bounds");
        if (Datamodel.hasValue("transmission_info"))
            Datamodel.removeValue("transmission_info");
    }

    resulttextfield.setText("Specified");
    resultlabel.setFont(new java.awt.Font("Dialog", 1, 11));
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

      if(Datamodel.hasValue("geno_freq"))
      if (Datamodel.hasValue("geno_option")) {
          if (Datamodel.getValue("geno_option").toString().compareTo("nhwe") == 0) {
              if (optionComboBox.getSelectedIndex() < 3) { // 0,1,2
                  if (IsPopup)
                      JOptionPane.showConfirmDialog(this,
                              message, "Warning",
                              JOptionPane.CLOSED_OPTION,
                              JOptionPane.
                              INFORMATION_MESSAGE);
                  IsPopup = true;
              }
          }
      }

      dispose();
    }
    else if (button == jAddButton) {
      String cov = "tau = " + "\""+ realmean[tauComboBox.getSelectedIndex()]+ "\"";
      if (jTextField1.getText().length() > 0) {
        cov = cov + ", val = " + "\""+ jTextField1.getText()+ "\"";
        if(fixedCheckBox.isSelected())
          cov = cov + ", fixed = " + "\"true\"";
      }
      listModel.addElement(cov);

      fixedCheckBox.setSelected(false);
      tauComboBox.setSelectedIndex(0);
      jTextField1.setText("");
    }
    else if (button == cancelButton) {
       dispose();
       if (!Datamodel.hasValue("transmission"))
         listModel.removeAllElements();
       tauComboBox.setSelectedIndex(0);
    }

    else if (button == optionComboBox)
    {
          Datamodel.setValue("transmission_option",
                             option_real[optionComboBox.getSelectedIndex()]);

          int index = optionComboBox.getSelectedIndex();
          switch(index)
          {
            case 0:
                setTauPanel(true);
                noboundsCheckBox.setEnabled(false);
                break;
            case 1:
              setTauPanel(false);
              noboundsCheckBox.setEnabled(false);
              break;
            case 2:
              setTauPanel(true);
              if(IsPopup)
              noboundsCheckBox.setEnabled(true);
              break;
            case 3:
            case 5:
            setTauPanel(true);
              noboundsCheckBox.setEnabled(false);
              break;
            case 4:
                setTauPanel(true);
                noboundsCheckBox.setEnabled(true);
              break;
          }
    }
  }

  void setTauPanel(boolean in)
  {
      tauComboBox.setEnabled(in);
      jLabel1.setEnabled(in);
      jLabel4.setEnabled(in);
      jScrollPane1.setEnabled(in);
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      if (jTextField1.getText().length() <= 0) {
        fixedCheckBox.setEnabled(false);
      }
      else {
        fixedCheckBox.setEnabled(true);
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
    if (ob == tauComboBox) {
      if (tauComboBox.getSelectedIndex() == 0) {
        jAddButton.setEnabled(false);
        jTextField1.setEnabled(false);
      }
      else {
        jAddButton.setEnabled(true);
        jTextField1.setEnabled(true);
      }
    }
  }

  void onPressedF1()
  {
      Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
       Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 172);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("option", false, 172);
  }

  void jComboBox2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("tau", false, 172);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("val", false, 172);
  }

  void jComboBox3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("fixed", false, 172);
  }
}

class SEGREG_Dialog12_TransM_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog12_TransM adaptee;

  SEGREG_Dialog12_TransM_jComboBox1_mouseAdapter(SEGREG_Dialog12_TransM adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class SEGREG_Dialog12_TransM_jComboBox2_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog12_TransM adaptee;

  SEGREG_Dialog12_TransM_jComboBox2_mouseAdapter(SEGREG_Dialog12_TransM adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox2_mouseClicked(e);
  }
}

class SEGREG_Dialog12_TransM_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog12_TransM adaptee;

  SEGREG_Dialog12_TransM_jTextField1_mouseAdapter(SEGREG_Dialog12_TransM adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class SEGREG_Dialog12_TransM_jComboBox3_mouseAdapter extends java.awt.event.MouseAdapter {
  SEGREG_Dialog12_TransM adaptee;

  SEGREG_Dialog12_TransM_jComboBox3_mouseAdapter(SEGREG_Dialog12_TransM adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox3_mouseClicked(e);
  }
}
