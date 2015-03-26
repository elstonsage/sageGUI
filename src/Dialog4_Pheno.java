package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.Document;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Dialog4_Pheno extends JInternalFrame implements DocumentListener, ActionListener, ItemListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton OKButton = new JButton();
  JButton jButton2 = new JButton();
  JTextField MissingValue = new JTextField();
  JLabel jLabel3 = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  TitledBorder titledBorder3;
  JLabel jLabel2 = new JLabel();
  JTextField UnAffected = new JTextField();
  JLabel jLabel1 = new JLabel();
  JTextField Affected = new JTextField();
  JRadioButton jBinaryCheckBox = new JRadioButton();
  JRadioButton jContinueCheckBox = new JRadioButton();
  JLabel jLabel4 = new JLabel();
  String HeaderName;
  String SelectedType;

  DataCollectionModel DataModel;
  JLabel jLabel5 = new JLabel();
  JTextField Name = new JTextField();
  JCheckBox jCheckBox1 = new JCheckBox();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel6 = new JLabel();

  int cindex;

  //Robot rb=null;
  String[] missingoption = {"Dot (\".\")","Blank (\"\")","Space (\" \")","Tab (\"\\t\")","other"};
  JComboBox jComboBox1 = new JComboBox(missingoption);

  String DefaultMissingValue=".";
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout2 = new BorderLayout();

  public void showDialog() {
    DefaultMissingValue=".";
      //center
      int xlocation = (JWizardProject.wizardProject.getWidth() - this.getWidth())/2 + JWizardProject.locationx;
      int ylocation = (JWizardProject.wizardProject.getHeight() - this.getHeight())/2 + JWizardProject.locationy ;
      this.setLocation(xlocation, ylocation);

      jCheckBox1.setSelected(false);
      //jTextField1.setText("");

      //if(rb!=null)
      ///{
      //  rb.mousePress(InputEvent.BUTTON1_MASK);
      //  rb.mouseRelease(InputEvent.BUTTON1_MASK);
      //}

      Name.setRequestFocusEnabled(true);
      Name.requestFocus(true);
      setVisible(true);
  }

  public Dialog4_Pheno(String title) {
     //super(Frame1.mainFrame1, title, true);
      this.setTitle(title);
      try {
          jbInit();
          pack();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

  private void jbInit() throws Exception {
      titledBorder3 = new TitledBorder("Type");
    panel1.setLayout(borderLayout1);
     jLabel4.setBorder(new EmptyBorder(0,20,0,0)); //top, left, bottom, right
      this.setSize(new Dimension(340, 380));//width, height
      this.setResizable(false);
      ButtonGroup bg=new ButtonGroup();
    jBinaryCheckBox.addItemListener(this);
    jLabel4.setText("Fill in the fields below as they apply to the pedigree data.");
    jTopPanel.setLayout(borderLayout2);
    OKButton.addActionListener(this);
    jButton2.addActionListener(this);

    jLabel5.setToolTipText("");
    jLabel5.setText("Name");
    Name.setText("");
    jContinueCheckBox.setSelected(true);
    jContinueCheckBox.addItemListener(this);
    jCheckBox1.setText("Apply to next");
    jCheckBox1.addItemListener(this);
    jLabel6.setText("column(s)");
    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);

    Affected.setEditable(false);
    UnAffected.setEditable(false);
    MissingValue.setEnabled(false);
    MissingValue.setEditable(false);
    OKButton.setPreferredSize(new Dimension(60, 25));
    jButton2.setPreferredSize(new Dimension(60, 25));
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    bg.add(jBinaryCheckBox);
      bg.add(jContinueCheckBox);

      jTopPanel.setBackground(Color.white);
      jTopPanel.setPreferredSize(new Dimension(340, 40));
      jCenterPanel.setLayout(xYLayout1);
      jCenterPanel.setPreferredSize(new Dimension(340,300));//320,240
      jCenterPanel.setBorder(lineborder);
      jBottomPanel.setBorder(lineborder);
      jBottomPanel.setPreferredSize(new Dimension(340, 40));
      jBottomPanel.setLayout(flowLayout1);

      OKButton.setText("OK");
      jButton2.setText("Cancel");
      jButton2.setMargin(new Insets(2, 2, 2, 2));
    jLabel3.setText("Missing value");
    jPanel1.setLayout(xYLayout2);
    jPanel1.setBorder(titledBorder3);
    jLabel2.setText("Unaffected ");
    UnAffected.setEnabled(false);
    UnAffected.setText("0");
    jLabel1.setToolTipText("");
    jLabel1.setText("Affected ");
    Affected.setEnabled(false);

    Affected.setText("1");
    jBinaryCheckBox.setText("Binary");
    jContinueCheckBox.setToolTipText("");
    jContinueCheckBox.setText("Continuous");
    getContentPane().add(panel1);
      panel1.add(jTopPanel,  BorderLayout.NORTH);
    jTopPanel.add(jLabel4, null);
      panel1.add(jCenterPanel,  BorderLayout.CENTER);
        jCenterPanel.add(jComboBox1,    new XYConstraints(110, 45, 100, 20));
    jCenterPanel.add(MissingValue,        new XYConstraints(220, 45, 100, 20));
        jCenterPanel.add(Name,                           new XYConstraints(110, 15, 210, 20));
        jCenterPanel.add(jTextField1,          new XYConstraints(125, 265, 50, 20));
        panel1.add(jBottomPanel, BorderLayout.SOUTH);
      jBottomPanel.add(OKButton, null);
    jBottomPanel.add(jButton2, null);
        jCenterPanel.add(jLabel5, new XYConstraints(20, 15, 50, 20));
        jCenterPanel.add(jLabel3, new XYConstraints(20, 45, 74, 20));
        jPanel1.add(jContinueCheckBox, new XYConstraints(10, 35, 80, 20));
        jPanel1.add(jLabel2, new XYConstraints(130, 35, 80, 20));
        jPanel1.add(jBinaryCheckBox, new XYConstraints(10, 5, 80, 20));
        jPanel1.add(jLabel1, new XYConstraints(130, 5, 80, 20));
        jPanel1.add(UnAffected, new XYConstraints(200, 35, 80, 20));
        jPanel1.add(Affected, new XYConstraints(200, 5, 80, 20));
        jCenterPanel.add(jCheckBox1, new XYConstraints(20, 265, 104, 20));
        jCenterPanel.add(jLabel6, new XYConstraints(190, 265, 50, 20));
        jCenterPanel.add(jPanel1, new XYConstraints(15, 75, 310, 100));
        jComboBox1.addItemListener(this);
      MissingValue.getDocument().addDocumentListener(this);
      //rb = new Robot();
  }

  public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();
    if(o == OKButton)
    {
      if (MakeParameterStep2.phenotype_count == 1) {
        DataModel.setValue("phenotype.missing", DefaultMissingValue);
      }
      MakeParameterStep2.phenotype_count++;

      switch(jComboBox1.getSelectedIndex())
      {
      case 0:
        DefaultMissingValue = ".";
        break;
        case 1:
          DefaultMissingValue = "";
          break;
        case 2:
          DefaultMissingValue = " ";
          break;
        case 3:
          DefaultMissingValue = "\t";
          break;
        case 4:
          if(MissingValue.getText().length()>0)
            DefaultMissingValue = MissingValue.getText();
          break;
      }

      if(jCheckBox1.isSelected() && jTextField1.getText().length()>0)
      {
        int totalcolumnnumber = MakeParameterStep2.real_col_size;
        int restcolumnnumber = Math.abs(totalcolumnnumber - cindex -1);

        try {
          Integer.parseInt(jTextField1.getText());
        }
        catch (NumberFormatException en) {
          JOptionPane.showOptionDialog(this.getParent(),
          "You must enter a numerical value between 1 and "+ restcolumnnumber +
          ", inclusive, \nto specify the applicable columns.",
          "Error", // title
          JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
          jTextField1.setRequestFocusEnabled(true);
          jTextField1.setFocusable(true);
          this.updateUI();
          return;
        }

        int count = Integer.parseInt(jTextField1.getText());

        if(count > restcolumnnumber)
        {
          JOptionPane.showOptionDialog(this.getParent(),
          "You must enter a numerical value between 1 and "+ restcolumnnumber +
          ", inclusive, \nto specify the applicable columns.",
          "Error", // title
          JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
          jTextField1.setRequestFocusEnabled(true);
          jTextField1.setFocusable(true);
          this.updateUI();
          return;
        }

        for (int i = 0; i <= count; i++) {
          if(cindex+i < 500)
            MakeParameterStep2.dm.setValueAt(SelectedType, 0, cindex + i); //value, row, column
          String HeaderNames = MakeParameterStep2.columnNames_all[cindex + i];
//write
          DataModel.setValue("pedigree"+HeaderNames, SelectedType);
          DataModel.setValue("pedigree"+HeaderNames+".Hname", HeaderNames);
          DataModel.setValue("pedigree"+HeaderNames+".Aname", HeaderNames);

          if(jBinaryCheckBox.isSelected()) // binary
          {
            DataModel.setValue("pedigree"+HeaderNames+".type", "binary");
            if(Affected.getText().length()>0)
              DataModel.setValue("pedigree"+HeaderNames+".binary_affected", Affected.getText());
            if(UnAffected.getText().length()>0)
              DataModel.setValue("pedigree"+HeaderNames+".binary_unaffected", UnAffected.getText());
            DataModel.setValue("pedigree"+HeaderNames+".missing", DefaultMissingValue);
          }
          if(jContinueCheckBox.isSelected()) // continuous
          {
            DataModel.setValue("pedigree"+HeaderNames+".type", "continuous");
            DataModel.setValue("pedigree"+HeaderNames+".missing", DefaultMissingValue);
          }
        }
        setVisible(false);
        //Frame1.desktop.remove(this);

  //end write
      }
      else // only one trait
      {
          OKButton_actionPerformed(e);
      }

    }
    else if(o == jButton2)
    {
      jButton2_actionPerformed(e);
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if(ob == jComboBox1)
    {
      switch(jComboBox1.getSelectedIndex())
      {
      case 0:
        DefaultMissingValue = ".";
        MissingValue.setEnabled(false);
        MissingValue.setEditable(false);
        break;
        case 1:
          DefaultMissingValue = "";
          MissingValue.setEnabled(false);
          MissingValue.setEditable(false);
          break;
        case 2:
          DefaultMissingValue = " ";
          MissingValue.setEnabled(false);
          MissingValue.setEditable(false);
          break;
        case 3:
          DefaultMissingValue = "\t";
          MissingValue.setEnabled(false);
          MissingValue.setEditable(false);
          break;
        case 4:
          MissingValue.setEnabled(true);
          MissingValue.setEditable(true);
          MissingValue.requestFocus(true);
          break;
      }
      //this.repaint();
    }

    if (ob == jCheckBox1) {
      if (jCheckBox1.isSelected())
      {
        jTextField1.setEnabled(true);
        jTextField1.setEditable(true);
      }
      else{
        jTextField1.setEnabled(false);
        jTextField1.setEditable(false);
      }
    }
    else if (ob == jBinaryCheckBox) {
      if (jBinaryCheckBox.isSelected()) {
        UnAffected.setEnabled(true);
        UnAffected.setEditable(true);
        Affected.setEnabled(true);
        Affected.setEditable(true);
      }
      else {
        UnAffected.setEnabled(false);
        UnAffected.setEditable(false);
        Affected.setEditable(false);
        Affected.setEnabled(false);
      }
    }
  }

  public void SetDataModel(DataCollectionModel DM, String HN, String ST, int columnindex) {
      DataModel = DM;
      HeaderName = HN;
      Name.setText(HeaderName);
      SelectedType = ST;
      cindex = columnindex;
  }

  public void SetDefaultData(DataCollectionModel DM, String HN, String ST, int columnindex) {
      DataModel = DM;
      HeaderName = HN;
      Name.setText(HeaderName);
      SelectedType = ST;
      cindex = columnindex;

      String defaultmissingvalue="";

      if(DataModel.hasValue("phenotype.missing"))
        defaultmissingvalue = DataModel.getValue("phenotype.missing").toString();

      if (defaultmissingvalue.compareTo(".") == 0)
        jComboBox1.setSelectedIndex(0);
      else if (defaultmissingvalue.compareTo("") == 0)
        jComboBox1.setSelectedIndex(1);
      else if (defaultmissingvalue.compareTo(" ") == 0)
        jComboBox1.setSelectedIndex(2);
      else if (defaultmissingvalue.compareTo("\t") == 0)
        jComboBox1.setSelectedIndex(3);
      else {
        jComboBox1.setSelectedIndex(4);
        MissingValue.setText(defaultmissingvalue);
      }
  }

  void OKButton_actionPerformed(ActionEvent e) {
    DataModel.setValue("pedigree"+HeaderName, SelectedType);
    DataModel.setValue("pedigree"+HeaderName+".Hname", HeaderName);
    DataModel.setValue("pedigree"+HeaderName+".Aname", Name.getText());

    if(jBinaryCheckBox.isSelected()) // binary
    {
      DataModel.setValue("pedigree"+HeaderName+".type", "binary");
      if(Affected.getText().length()>0)
        DataModel.setValue("pedigree"+HeaderName+".binary_affected", Affected.getText());
      if(UnAffected.getText().length()>0)
        DataModel.setValue("pedigree"+HeaderName+".binary_unaffected", UnAffected.getText());
      DataModel.setValue("pedigree"+HeaderName+".missing", DefaultMissingValue);
    }
    if(jContinueCheckBox.isSelected()) // continuous
    {
      DataModel.setValue("pedigree"+HeaderName+".type", "continuous");
      DataModel.setValue("pedigree"+HeaderName+".missing", DefaultMissingValue);
    }
    setVisible(false);
    //Frame1.desktop.remove(this);
  }

  void jButton2_actionPerformed(ActionEvent e) {
    setVisible(false);
    //Frame1.desktop.remove(this);
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == MissingValue.getDocument()) {
      if(MissingValue.getText().length()>0)
        DefaultMissingValue = MissingValue.getText();
    }
    //this.repaint();
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

}
