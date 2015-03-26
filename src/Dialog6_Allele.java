package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.Document;

public class Dialog6_Allele extends JDialog implements  DocumentListener, ActionListener, ItemListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JLabel jLabel2 = new JLabel();
  JTextField AMissing = new JTextField();
  JLabel jLabel3 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();

DataCollectionModel DataModel;
    String HeaderName;
  JLabel jLabel6 = new JLabel();
  JTextField Name = new JTextField();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  JTextField Minimum = new JTextField();
  JRadioButton jCheckComplement = new JRadioButton();
  JTextField Maximum = new JTextField();
  JRadioButton minCheckBox = new JRadioButton();
  JRadioButton jCheckEqual = new JRadioButton();
  JLabel jLabel5 = new JLabel();
String SelectedType;
  JCheckBox jCheckBox1 = new JCheckBox();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel4 = new JLabel();
  JCheckBox usealleleCheckBox = new JCheckBox("Allele frequency options");
int cindex;
  String[] missingoption = {"Dot (\".\")","Blank (\"\")","Space (\" \")","Tab (\"\\t\")","Other"};
  JComboBox jComboBox1 = new JComboBox(missingoption);

  String DefaultAMissingValue=".";
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout2 = new BorderLayout();

  JLabel covmodeLabel = new JLabel();
  JLabel covalleleLabel = new JLabel();

  String[] covmodeoption = {"","Additive","Dominant","Recessive"};
  JComboBox covmodeComboBox = new JComboBox(covmodeoption);
  JTextField covalleleComboBox = new JTextField();
  JCheckBox hemizygoteCheckBox = new JCheckBox();
  JCheckBox useascovCheckBox = new JCheckBox("Use this marker as covariate");

  ComponentTitledPanel allelePanel = new ComponentTitledPanel(usealleleCheckBox);
  ComponentTitledPanel covPanel = new ComponentTitledPanel(useascovCheckBox);


  public void showDialog() {
    DefaultAMissingValue=".";

    this.setLocationRelativeTo(Frame1.mainFrame1);
    Name.requestFocus();
    Name.requestFocus(true);
    setVisible(true);
  }

  public Dialog6_Allele(String title) {
      super(Frame1.mainFrame1, title, false);
      try {
          jbInit();
          pack();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
     jLabel3.setBorder(new EmptyBorder(0,20,0,0));
      this.setSize(340, 500);
      this.setResizable(false);
      ButtonGroup bg=new ButtonGroup();
      minCheckBox.addItemListener(this);

    jLabel2.setToolTipText("");
    jCheckBox1.setText("Apply to next ");
    jCheckBox1.addItemListener(this);
    jLabel4.setText("column(s)");
    jTextField1.setEnabled(false);
    jTextField1.setEditable(false);
    jTextField1.setText("");
    jLabel5.setEnabled(false);
    jLabel5.setToolTipText("Specifies maximum allele frequency for the marker.");
    minCheckBox.setEnabled(false);
    minCheckBox.setToolTipText("Specifies minimum allele frequency for the marker.");
    minCheckBox.setSelected(true);
    jCheckComplement.setEnabled(false);
    jCheckComplement.setToolTipText("Sets allele frequencies to proportional complementary values.");
    jCheckEqual.setEnabled(false);
    jCheckEqual.setToolTipText("Sets all allele frequencies to be equal.");
    Minimum.setEditable(false);
    Maximum.setEditable(false);
    AMissing.setEnabled(false);
    AMissing.setEditable(false);
    AMissing.setText("");
    jButton1.setPreferredSize(new Dimension(60, 25));
    jButton2.setPreferredSize(new Dimension(60, 25));
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jTopPanel.setLayout(borderLayout2);
    jLabel6.setToolTipText(
        "<html>This name must be the <b>same for 2 alleles</b> of the same marker and " +
        "<br>match the name in the locus description file, if one is provided.");

    bg.add(minCheckBox);
      bg.add(jCheckComplement);
      bg.add(jCheckEqual);

      Minimum.setEnabled(false);
      Maximum.setEnabled(false);

      jTopPanel.setBackground(Color.white);
      jTopPanel.setPreferredSize(new Dimension(340, 40));
      jCenterPanel.setLayout(xYLayout1);
      jCenterPanel.setPreferredSize(new Dimension(340,420));
      jCenterPanel.setBorder(lineborder);
      jBottomPanel.setBorder(lineborder);
      jBottomPanel.setPreferredSize(new Dimension(340, 40));
      jBottomPanel.setLayout(flowLayout1);

      jButton1.setText("OK");
    jButton1.addActionListener(this);
      jButton2.setText("Cancel");
      jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(this);
    jLabel2.setText("Missing value");
    jLabel3.setText("Fill in the fields below as they apply to the pedigree data.");
    jLabel6.setText("Name");
    Name.setText("");
    jPanel1.setLayout(xYLayout4);
    jPanel1.setBorder(titledBorder1);
    Minimum.setText("");
    jCheckComplement.setText("Complement");
    jCheckComplement.addItemListener(this);
    Maximum.setText("");
    minCheckBox.setText("Minimum");
    minCheckBox.addItemListener(this);
    jCheckEqual.setText("Equal");
    jCheckEqual.addItemListener(this);
    jLabel5.setText("Maximum");

     usealleleCheckBox.addItemListener(this);

    hemizygoteCheckBox.setFocusPainted(false);
    jCheckEqual.setFocusPainted(false);
    jCheckComplement.setFocusPainted(false);
    jCheckBox1.setFocusPainted(false);
    minCheckBox.setFocusPainted(false);
    usealleleCheckBox.setFocusPainted(false);
    useascovCheckBox.setFocusPainted(false);
    covmodeLabel.setText("Covariate mode of inheritance");
    covalleleLabel.setText("Covariate allele");
    hemizygoteCheckBox.setText("Allow hemizygote data from autosomal loci");
    covmodeLabel.setEnabled(false);
    covmodeComboBox.setEnabled(false);
    covalleleLabel.setEnabled(false);
    covalleleComboBox.setEnabled(false);
    hemizygoteCheckBox.setEnabled(false);
    useascovCheckBox.addItemListener(this);

    getContentPane().add(panel1);
    panel1.add(jTopPanel,  BorderLayout.NORTH);
    jTopPanel.add(jLabel3,   BorderLayout.CENTER);
    panel1.add(jCenterPanel,  BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1, null);
    jBottomPanel.add(jButton2, null);

    allelePanel.getContentPane().setLayout(new XYLayout());
    allelePanel.getContentPane().add(minCheckBox,           new XYConstraints(5, 5, 70, 20));
    allelePanel.getContentPane().add(Minimum,                          new XYConstraints(85, 5, 60, 20));
    allelePanel.getContentPane().add(jLabel5,             new XYConstraints(165, 5, 70, 20));
    allelePanel.getContentPane().add(Maximum,                         new XYConstraints(220, 5, 60, 20));
    allelePanel.getContentPane().add(jCheckComplement,                new XYConstraints(5, 34, 90, 20));
    allelePanel.getContentPane().add(jCheckEqual,               new XYConstraints(5, 63, 80, 20));


    covPanel.getContentPane().setLayout(new XYLayout());
    covPanel.getContentPane().add(covmodeLabel, new XYConstraints(10, 5, 200, 20));
    covPanel.getContentPane().add(covmodeComboBox, new XYConstraints(180, 5, 100, 20));//170
    covPanel.getContentPane().add(covalleleLabel, new XYConstraints(10, 34, 200, 20));
    covPanel.getContentPane().add(covalleleComboBox, new XYConstraints(180, 34, 100, 20));
    covPanel.getContentPane().add(hemizygoteCheckBox, new XYConstraints(5, 63, 300, 20));



    jCenterPanel.add(jLabel6,     new XYConstraints(20, 15, 50, 20));
    jCenterPanel.add(Name,                 new XYConstraints(110, 15, 210, 20));
    jCenterPanel.add(AMissing,                      new XYConstraints(220, 45, 100, 20));
        jCenterPanel.add(jComboBox1,     new XYConstraints(110, 45, 100, 20));
        jCenterPanel.add(jLabel2, new XYConstraints(20, 45, -1, 20));
        jCenterPanel.add(allelePanel, new XYConstraints(15, 75, 305, 123));
        jCenterPanel.add(covPanel, new XYConstraints(15, 205, 305, 123));

        jCenterPanel.add(jCheckBox1, new XYConstraints(20, 385, 105, 20));
        jCenterPanel.add(jTextField1,        new XYConstraints(125, 385, 50, 20));
        jCenterPanel.add(jLabel4, new XYConstraints(190, 385, 50, 20));

        jComboBox1.addItemListener(this);
    AMissing.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();
      if(o==jButton1)
      {
        if (Name.getText().length()<=0) {
          JOptionPane.showOptionDialog(Frame1.mainFrame1,
              "You must specify the name that will correspond to locus description file.",
              "Error",
              JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
          return;
        }

        if (usealleleCheckBox.isSelected()) {
          if (minCheckBox.isSelected()) {
            if (Minimum.getText().compareTo("") == 0 || Maximum.getText().compareTo("") == 0) {
              JOptionPane.showOptionDialog(this,
                  "You must specify a minimum and maximum value \nfor the allele frequency option.",
                  "Error",
                  JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
              return;
            }
          }
        }

        switch(jComboBox1.getSelectedIndex())
        {
        case 0:
          DefaultAMissingValue = ".";
          break;

          case 1:
            DefaultAMissingValue = "";
            break;
          case 2:
            DefaultAMissingValue = " ";
            break;
          case 3:
            DefaultAMissingValue = "\t";
            break;
          case 4:
            if(AMissing.getText().length()>0)
              DefaultAMissingValue = AMissing.getText();
            break;
        }

          if (MakeParameterStep2.allele_count == 1)
          {
            DataModel.setValue("marker.allele_missing",DefaultAMissingValue);

            if(usealleleCheckBox.isSelected())
            {
              DataModel.setValue("marker.allele_frequency","use");
              if(minCheckBox.isSelected())
              {
                DataModel.setValue("marker.allele_frequency.minimum",Minimum.getText());
                DataModel.setValue("marker.allele_frequency.maximum",Maximum.getText());
              }
              else if(jCheckComplement.isSelected())
              {
                DataModel.setValue("marker.allele_frequency.complement","use");
              }
              else if(jCheckEqual.isSelected())
              {
                DataModel.setValue("marker.allele_frequency.equal","use");
              }
            }

             if (useascovCheckBox.isSelected()) {
               DataModel.setValue("marker.useascov", "use");
               if (covmodeComboBox.getSelectedIndex()>0)
               {
                 DataModel.setValue("marker.useascov.covmode", covmodeComboBox.getSelectedItem().toString());
               }
               if (covalleleComboBox.getText().trim().length()>0) {
                 DataModel.setValue("marker.useascov.covallele", covalleleComboBox.getText().trim());
               }
               if (hemizygoteCheckBox.isSelected())
                 DataModel.setValue("marker.useascov.hemizygote", "true");
               else
                 DataModel.setValue("marker.useascov.hemizygote", "false");
          }

          }
          MakeParameterStep2.allele_count++;

        if(jCheckBox1.isSelected() && jTextField1.getText().length()>0)
        {
          int totalcolumnnumber = MakeParameterStep2.real_col_size;
          final int restcolumnnumber = Math.abs(totalcolumnnumber - cindex -1);

          try {
            Integer.parseInt(jTextField1.getText());
          }
          catch (NumberFormatException en) {
            JOptionPane.showOptionDialog(this.getParent(),
            "You must enter a numerical value between 1 and "+ restcolumnnumber +
            ", inclusive, \nto specify the applicable columns.",
            "Error",
            JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            jTextField1.requestFocus();
            return;
          }

          final int count = Integer.parseInt(jTextField1.getText());

          if(count > restcolumnnumber)
          {
            JOptionPane.showOptionDialog(this.getParent(),
            "You must enter a numerical value between 1 and "+ restcolumnnumber +
            ", inclusive, \nto specify the applicable columns.",
            "Error",
            JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            jTextField1.requestFocus();
            return;
          }

          setVisible(false);

          Frame1.mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          JWizardProject.wizardProject.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

          Runnable lookupData = new Runnable() {
              public void run() {
                  try {

                    ProgressDialog pd = new ProgressDialog("Warning");
                    pd.showDialog();

                    int progress = 0;

                    pd.SetProgress(progress);

                    int per = count/100;

                    if(per == 0)
                      per = 1;

                    String HeaderNames = new String();

                    if(count > 1)
                    {
                      for (int i = 0; i <= count; i++) {
                        if(i%per ==0)
                        {
                          progress += 1;
                          pd.SetProgress(progress);
                        }

                        if(cindex+i < 500)
                          MakeParameterStep2.dm.setValueAt(SelectedType, 0, cindex + i); //value, row, column

                        HeaderNames = MakeParameterStep2.columnNames_all[cindex + i];

                        DataModel.setValue("pedigree"+HeaderNames, SelectedType);
                        DataModel.setValue("pedigree"+HeaderNames+".Hname", HeaderNames);
                        DataModel.setValue("pedigree"+HeaderNames+".Aname", HeaderNames);
                        DataModel.setValue("pedigree"+HeaderNames+".allele_missing", DefaultAMissingValue);

                        if (usealleleCheckBox.isSelected()) {
                          if (minCheckBox.isSelected()) {
                            DataModel.setValue("pedigree" + HeaderNames +".allele_frequency.minimum", Minimum.getText());
                            DataModel.setValue("pedigree" + HeaderNames +".allele_frequency.maximum", Maximum.getText());
                          }
                          else if (jCheckComplement.isSelected()) {
                            DataModel.setValue("pedigree" + HeaderNames +".allele_frequency.complement", "use");
                          }
                          else if (jCheckEqual.isSelected()) {
                            DataModel.setValue("pedigree" + HeaderNames +".allele_frequency.equal", "use");
                          }
                        }

                        if (useascovCheckBox.isSelected()) {
                            DataModel.setValue("pedigree" + HeaderNames + ".useascov", "true");
                            if (covmodeComboBox.getSelectedIndex() > 0) {
                                DataModel.setValue("pedigree" + HeaderNames + ".useascov.covmode", covmodeComboBox.getSelectedItem().toString());
                            }

                            if (covalleleComboBox.getText().trim().length() > 0) {
                                DataModel.setValue("pedigree" + HeaderNames + ".useascov.covallele", covalleleComboBox.getText().trim());
                            }
                            if (hemizygoteCheckBox.isSelected()) {
                                DataModel.setValue("pedigree" + HeaderNames + ".useascov.hemizygote", "true");
                            } else
                                DataModel.setValue("pedigree" + HeaderNames + ".useascov.hemizygote", "false");
                        }
                      }
                    }
                    else
                    {
                      for (int i = 0; i <= count; i++) {
                        if(i%per ==0)
                        {
                          progress += 1;
                          pd.SetProgress(progress);
                        }

                        if(cindex+i < 500)
                          MakeParameterStep2.dm.setValueAt(SelectedType, 0, cindex + i);

                        HeaderNames = MakeParameterStep2.columnNames_all[cindex + i];

                        DataModel.setValue("pedigree"+HeaderNames, SelectedType);
                        DataModel.setValue("pedigree"+HeaderNames+".Hname", HeaderNames);
                        DataModel.setValue("pedigree"+HeaderNames+".Aname", Name.getText());
                        DataModel.setValue("pedigree"+HeaderNames+".allele_missing", DefaultAMissingValue);

                        if (usealleleCheckBox.isSelected()) {
                          if (minCheckBox.isSelected()) {
                            DataModel.setValue("pedigree" + HeaderNames +".allele_frequency.minimum", Minimum.getText());
                            DataModel.setValue("pedigree" + HeaderNames +".allele_frequency.maximum", Maximum.getText());
                          }
                          else if (jCheckComplement.isSelected()) {
                            DataModel.setValue("pedigree" + HeaderNames +".allele_frequency.complement", "use");
                          }
                          else if (jCheckEqual.isSelected()) {
                            DataModel.setValue("pedigree" + HeaderNames +".allele_frequency.equal", "use");
                          }
                        }

                        if (useascovCheckBox.isSelected()) {
                            DataModel.setValue("pedigree" + HeaderNames + ".useascov", "true");
                            if (covmodeComboBox.getSelectedIndex() > 0) {
                                DataModel.setValue("pedigree" + HeaderNames + ".useascov.covmode", covmodeComboBox.getSelectedItem().toString());
                            }

                            if (covalleleComboBox.getText().trim().length() > 0) {
                                DataModel.setValue("pedigree" + HeaderNames + ".useascov.covallele", covalleleComboBox.getText().trim());
                            }
                            if (hemizygoteCheckBox.isSelected()) {
                                DataModel.setValue("pedigree" + HeaderNames + ".useascov.hemizygote", "true");
                            } else
                                DataModel.setValue("pedigree" + HeaderNames + ".useascov.hemizygote", "false");
                        }

                      }
                    }

                      pd.jButton1.setEnabled(true);
                      pd.jButton1_actionPerformed();

                      Frame1.mainFrame1.setCursor(null);
                      JWizardProject.wizardProject.setCursor(null);

                      ((PropertyDataModel)DataModel).fireChangeEvent();
                      JWizardProject.wizard.nav.validate();
                      JWizardProject.wizard.updateUI();
                      JWizardProject.wizard.repaint();

                  } catch (Exception excel) {
                      excel.printStackTrace();
                  }
              }
          };

          Thread lookupThread = new Thread(lookupData, "MyThread");
          lookupThread.start();

        }
        else
        {
            jButton1_actionPerformed(e);
        }

      }
      if(o==jButton2)
        jButton2_actionPerformed(e);
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if(ob == jComboBox1)
    {
      switch(jComboBox1.getSelectedIndex())
      {
      case 0:
        DefaultAMissingValue = ".";
        AMissing.setEnabled(false);
        AMissing.setEditable(false);
        break;
        case 1:
          DefaultAMissingValue = "";
          AMissing.setEnabled(false);
          AMissing.setEditable(false);
          break;
        case 2:
          DefaultAMissingValue = " ";
          AMissing.setEnabled(false);
          AMissing.setEditable(false);
          break;
        case 3:
          DefaultAMissingValue = "\t";
          AMissing.setEnabled(false);
          AMissing.setEditable(false);
          break;
        case 4:
          AMissing.setEnabled(true);
          AMissing.setEditable(true);
          AMissing.requestFocus(true);
          break;

      }
    }

    if(ob == jCheckBox1)
    {
      if (jCheckBox1.isSelected())
      {
        jTextField1.setEnabled(true);
        jTextField1.setEditable(true);
        jTextField1.requestFocus();
      }
      else{
        jTextField1.setEnabled(false);
        jTextField1.setEditable(false);
      }

    }

    if (ob == usealleleCheckBox) {
      if (usealleleCheckBox.isSelected()) {
        minCheckBox.setEnabled(true);
        jCheckComplement.setEnabled(true);
        jCheckEqual.setEnabled(true);
        Minimum.setEditable(true);
        Minimum.setEnabled(true);
        Maximum.setEditable(true);
        Maximum.setEnabled(true);
        jLabel5.setEnabled(true);
      }
      else {
        minCheckBox.setEnabled(false);
        jCheckComplement.setEnabled(false);
        jCheckEqual.setEnabled(false);
        Minimum.setEditable(false);
        Minimum.setEnabled(false);
        Maximum.setEditable(false);
        Maximum.setEnabled(false);
        jLabel5.setEnabled(false);
      }
    }
    else if(ob == useascovCheckBox)
    {
        if (useascovCheckBox.isSelected()) {
          covmodeLabel.setEnabled(true);
          covmodeComboBox.setEnabled(true);
          covalleleLabel.setEnabled(true);
          covalleleComboBox.setEnabled(true);
          hemizygoteCheckBox.setEnabled(true);
        }
        else {
            covmodeLabel.setEnabled(false);
            covmodeComboBox.setEnabled(false);
            covalleleLabel.setEnabled(false);
            covalleleComboBox.setEnabled(false);
            hemizygoteCheckBox.setEnabled(false);
      }
    }
    else if (ob == minCheckBox) {
      if (minCheckBox.isSelected()) {
        Minimum.setEnabled(true);
        Maximum.setEnabled(true);
      }
      else {
        Minimum.setEnabled(false);
        Maximum.setEnabled(false);
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

      String defaultmissingvalue = DataModel.getValue("marker.allele_missing").toString();
      if(defaultmissingvalue.compareTo(".")==0)
        jComboBox1.setSelectedIndex(0);
      else if(defaultmissingvalue.compareTo("")==0)
        jComboBox1.setSelectedIndex(1);
      else if(defaultmissingvalue.compareTo(" ")==0)
        jComboBox1.setSelectedIndex(2);
      else if(defaultmissingvalue.compareTo("\t")==0)
        jComboBox1.setSelectedIndex(3);
      else
      {
        jComboBox1.setSelectedIndex(4);
        AMissing.setText(defaultmissingvalue);
      }

      if(DataModel.hasValue("marker.allele_frequency")) // use allele_frequency
      {
        usealleleCheckBox.setSelected(true);
        if(DataModel.hasValue("marker.allele_frequency.minimum"))
        {
          minCheckBox.setSelected(true);
          Minimum.setText(DataModel.getValue("marker.allele_frequency.minimum").toString());
          Maximum.setText(DataModel.getValue("marker.allele_frequency.maximum").toString());
        }
        if(DataModel.hasValue("marker.allele_frequency.complement"))
        {
          jCheckComplement.setSelected(true);
        }
        if(DataModel.hasValue("marker.allele_frequency.equal"))
        {
          jCheckEqual.setSelected(true);
        }
      }

      if (DataModel.hasValue("marker.useascov")) {

          useascovCheckBox.setSelected(true);

          if (DataModel.hasValue("marker.useascov.covmode")) {
              covmodeComboBox.setSelectedItem(DataModel.getValue("marker.useascov.covmode").toString());
          }
          if (DataModel.hasValue("marker.useascov.covallele")) {
              covalleleComboBox.setText(DataModel.getValue("marker.useascov.covallele").toString());
          }
          if (DataModel.hasValue("marker.useascov.hemizygote")) {
              if (DataModel.getValue("marker.useascov.hemizygote").toString().compareTo("true") == 0) {
                  hemizygoteCheckBox.setSelected(true);
              }
          }
      }
  }


  void jButton1_actionPerformed(ActionEvent e) {
    switch(jComboBox1.getSelectedIndex())
    {
        case 0:
      DefaultAMissingValue = ".";
      break;
      case 1:
        DefaultAMissingValue = "";
        break;
      case 2:
        DefaultAMissingValue = " ";
        break;
      case 3:
        DefaultAMissingValue = "\t";
        break;
      case 4:
        if(AMissing.getText().length()>0)
          DefaultAMissingValue = AMissing.getText();
        break;
    }

      DataModel.setValue("pedigree"+HeaderName, SelectedType);
      DataModel.setValue("pedigree"+HeaderName+".Hname", HeaderName);
      DataModel.setValue("pedigree"+HeaderName+".Aname", Name.getText());

      DataModel.setValue("pedigree"+HeaderName+".allele_missing", DefaultAMissingValue);

      if (usealleleCheckBox.isSelected()) {
        if (minCheckBox.isSelected()) {
          DataModel.setValue("pedigree" + HeaderName +".allele_frequency.minimum", Minimum.getText());
          DataModel.setValue("pedigree" + HeaderName +".allele_frequency.maximum", Maximum.getText());
        }
        else if (jCheckComplement.isSelected()) {
          DataModel.setValue("pedigree" + HeaderName +".allele_frequency.complement", "use");
        }
        else if (jCheckEqual.isSelected()) {
          DataModel.setValue("pedigree" + HeaderName +".allele_frequency.equal", "use");
        }
      }

      if (useascovCheckBox.isSelected()) {
          DataModel.setValue("pedigree" + HeaderName + ".useascov", "true");
          if (covmodeComboBox.getSelectedIndex() > 0) {
              DataModel.setValue("pedigree" + HeaderName + ".useascov.covmode", covmodeComboBox.getSelectedItem().toString());
          }

          if (covalleleComboBox.getText().trim().length() > 0) {
              DataModel.setValue("pedigree" + HeaderName + ".useascov.covallele", covalleleComboBox.getText().trim());
          }
          if (hemizygoteCheckBox.isSelected()) {
              DataModel.setValue("pedigree" + HeaderName + ".useascov.hemizygote", "true");
          } else
              DataModel.setValue("pedigree" + HeaderName + ".useascov.hemizygote", "false");

      }

      setVisible(false);
  }

  void jButton2_actionPerformed(ActionEvent e) {
    setVisible(false);
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == AMissing.getDocument()) {
      if(AMissing.getText().length()>0)
        DefaultAMissingValue = AMissing.getText();
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

}

