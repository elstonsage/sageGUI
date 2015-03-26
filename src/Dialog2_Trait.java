package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.Document;

public class Dialog2_Trait extends JDialog implements DocumentListener, ActionListener, ItemListener{
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
  JLabel missingLabel = new JLabel();
  JPanel jPanel1 = new JPanel();
  XYLayout xYLayout2 = new XYLayout();
  TitledBorder titledBorder3;
  JLabel unaffLabel = new JLabel();
  JTextField UnAffected = new JTextField();
  JLabel affLabel = new JLabel();
  JTextField Affected = new JTextField();
  JRadioButton jBinaryCheckBox = new JRadioButton();
  JRadioButton jContinueCheckBox = new JRadioButton();
  JRadioButton cateCheckBox = new JRadioButton();
  JLabel catevalueLabel = new JLabel("Values");
  JTextField catevalueTextField = new JTextField();
  JLabel titleLabel = new JLabel();
  String HeaderName;
  String SelectedType;

  DataCollectionModel DataModel;
  JLabel nameLabel = new JLabel();
  JTextField Name = new JTextField();
  JCheckBox applynextCheckBox = new JCheckBox();
  JTextField applyTextField = new JTextField();
  JLabel applyLabel = new JLabel();

  int cindex;

  String[] missingoption = {"Dot (\".\")","Blank (\"\")","Space (\" \")","Tab (\"\\t\")","Other"};
  JComboBox missingComboBox = new JComboBox(missingoption);

  String DefaultMissingValue=".";
  FlowLayout flowLayout1 = new FlowLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  JCheckBox usetraitCheckBox = new JCheckBox();

  ProgressDialog wait = new ProgressDialog("Please wait..");

  public void showDialog()
  {
    DefaultMissingValue=".";
      applynextCheckBox.setSelected(false);
      usetraitCheckBox.setSelected(false);
     Name.setRequestFocusEnabled(true);
     Name.requestFocus();

     this.setLocationRelativeTo(Frame1.mainFrame1);

     setVisible(true);
  }

  public Dialog2_Trait(String title) {
     super(Frame1.mainFrame1, title, false);


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
     titleLabel.setBorder(new EmptyBorder(0,20,0,0));
      this.setSize(new Dimension(340, 430));
      this.setResizable(false);
      ButtonGroup bg=new ButtonGroup();
    jBinaryCheckBox.addItemListener(this);
    titleLabel.setText("Fill in the fields below as they apply to the pedigree data.");
    jTopPanel.setLayout(borderLayout2);
    OKButton.addActionListener(this);
    jButton2.addActionListener(this);

    nameLabel.setToolTipText("");
    nameLabel.setText("Name");
    Name.setText("");
    jContinueCheckBox.setSelected(true);
    jContinueCheckBox.addItemListener(this);
    applynextCheckBox.setText("Apply to next");
    applynextCheckBox.addItemListener(this);
    applyLabel.setText("column(s)");
    applyTextField.setEnabled(false);
    applyTextField.setEditable(false);

    unaffLabel.setEnabled(false);
    affLabel.setEnabled(false);

    Affected.setEditable(false);
    UnAffected.setEditable(false);
    MissingValue.setEnabled(false);
    catevalueTextField.setEditable(false);
    catevalueLabel.setEnabled(false);
    catevalueTextField.setEnabled(false);
    cateCheckBox.setText("Categorical");
    cateCheckBox.addItemListener(this);

    MissingValue.setEditable(false);
    OKButton.setPreferredSize(new Dimension(60, 25));
    jButton2.setPreferredSize(new Dimension(60, 25));
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jTopPanel.setBackground(Color.white);
    usetraitCheckBox.setToolTipText("");
    usetraitCheckBox.setText("Use as trait marker");
    bg.add(jBinaryCheckBox);
      bg.add(jContinueCheckBox);
      bg.add(cateCheckBox);

      jBinaryCheckBox.setFocusPainted(false);
      jContinueCheckBox.setFocusPainted(false);
      cateCheckBox.setFocusPainted(false);
      usetraitCheckBox.setFocusPainted(false);
      applynextCheckBox.setFocusPainted(false);

      jTopPanel.setPreferredSize(new Dimension(340, 40));
      jCenterPanel.setLayout(xYLayout1);
      jCenterPanel.setPreferredSize(new Dimension(340,350));
      jCenterPanel.setBorder(lineborder);
      jBottomPanel.setBorder(lineborder);
      jBottomPanel.setPreferredSize(new Dimension(340, 40));
      jBottomPanel.setLayout(flowLayout1);

      OKButton.setText("OK");
      jButton2.setText("Cancel");
      jButton2.setMargin(new Insets(2, 2, 2, 2));
    missingLabel.setText("Missing value");
    jPanel1.setLayout(xYLayout2);
    jPanel1.setBorder(titledBorder3);
    unaffLabel.setText("Unaffected ");
    unaffLabel.setEnabled(false);
    UnAffected.setEnabled(false);
    UnAffected.setText("0");
    affLabel.setToolTipText("");
    affLabel.setText("Affected ");
    Affected.setEnabled(false);
     affLabel.setEnabled(false);

    Affected.setText("1");
    jBinaryCheckBox.setText("Binary");
    jContinueCheckBox.setToolTipText("");
    jContinueCheckBox.setText("Quantitative");
    getContentPane().add(panel1);
      panel1.add(jTopPanel,  BorderLayout.NORTH);
    jTopPanel.add(titleLabel, BorderLayout.CENTER);
      panel1.add(jCenterPanel,  BorderLayout.CENTER);

        panel1.add(jBottomPanel, BorderLayout.SOUTH);
      jBottomPanel.add(OKButton, null);
    jBottomPanel.add(jButton2, null);

    jCenterPanel.add(nameLabel, new XYConstraints(20, 15, 50, 20));
    jCenterPanel.add(Name,                            new XYConstraints(110, 15, 210, 20));

        jCenterPanel.add(missingLabel, new XYConstraints(20, 45, 74, 20));
        jCenterPanel.add(missingComboBox, new XYConstraints(110, 45, 100, 20));
        jCenterPanel.add(MissingValue,          new XYConstraints(220, 45, 100, 20));

        jPanel1.add(jBinaryCheckBox, new XYConstraints(10, 5, 80, 20));
        jPanel1.add(affLabel, new XYConstraints(130, 5, 80, 20));
        jPanel1.add(Affected, new XYConstraints(200, 5, 80, 20));
        jPanel1.add(jContinueCheckBox, new XYConstraints(10, 35, 110, 20));
        jPanel1.add(unaffLabel, new XYConstraints(130, 35, 80, 20));
        jPanel1.add(UnAffected, new XYConstraints(200, 35, 80, 20));

        jPanel1.add(cateCheckBox, new XYConstraints(10, 65, 110, 20));
        jPanel1.add(catevalueLabel, new XYConstraints(130, 65, 80, 20));
        jPanel1.add(catevalueTextField, new XYConstraints(200, 65, 80, 20));

        jCenterPanel.add(jPanel1, new XYConstraints(15, 75, 310, 130));

        jCenterPanel.add(applynextCheckBox, new XYConstraints(20, 295, -1, 20));
        jCenterPanel.add(applyTextField, new XYConstraints(125, 295, 50, 20));
        jCenterPanel.add(applyLabel, new XYConstraints(190, 295, 50, 20));

    jCenterPanel.add(usetraitCheckBox,       new XYConstraints(20, 215, 150, 20));
        missingComboBox.addItemListener(this);
      MissingValue.getDocument().addDocumentListener(this);
  }

  public void click_ok_button()
  {
    if (MakeParameterStep2.trait_count == 1) {
      DataModel.setValue("trait.missing", DefaultMissingValue);
    }
    MakeParameterStep2.trait_count++;

    switch(missingComboBox.getSelectedIndex())
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

    if(applynextCheckBox.isSelected() && applyTextField.getText().length()>0)
    {
      int totalcolumnnumber = MakeParameterStep2.real_col_size;
      int restcolumnnumber = Math.abs(totalcolumnnumber - cindex -1);

      try {
        Integer.parseInt(applyTextField.getText());
      }
      catch (NumberFormatException en) {
        JOptionPane.showOptionDialog(this.getParent(),
        "You must enter a numerical value between 1 and "+ restcolumnnumber +
        ", inclusive, \nto specify the applicable columns.",
        "Error",
        JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

        applyTextField.requestFocus();
        return;
      }

      final int count = Integer.parseInt(applyTextField.getText());

      if(count > restcolumnnumber)
      {
        JOptionPane.showOptionDialog(this.getParent(),
        "You must enter a numerical value between 1 and "+ restcolumnnumber +
        ",\n inclusive, \nto specify the applicable columns.",
        "Error",
        JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        applyTextField.requestFocus();

        return;
      }
      setVisible(false);

     SwingWorker worker = new SwingWorker() {

          public Object construct() {
            wait.jLabel4.setText("Please wait while your data is being processed.");
            wait.showDialog();

            double value = (double)100/(double)count;
            int old_progress = 0;

            for (int i = 0; i <= count; i++) {
              int progress = (int)(i * value);
              if(progress != old_progress)
                wait.setProgressValue(progress);

              if(cindex+i < 500)
                MakeParameterStep2.dm.setValueAt(SelectedType, 0, cindex + i);
              String HeaderNames = MakeParameterStep2.columnNames_all[cindex + i];
              DataModel.setValue("pedigree"+HeaderNames, SelectedType);
              DataModel.setValue("pedigree"+HeaderNames+".Hname", HeaderNames);
              DataModel.setValue("pedigree"+HeaderNames+".Aname", HeaderNames);

              if(usetraitCheckBox.isSelected())
              {
                  DataModel.setValue("pedigree" + HeaderNames + ".TR", "TRAIT_MARKER");
                  DataModel.setValue("pedigree"+HeaderNames+ ".TR"+".Hname", HeaderNames);
                  DataModel.setValue("pedigree"+HeaderNames+ ".TR"+".Aname", HeaderNames);
              }

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

              if(cateCheckBox.isSelected()) // categorical
              {
                DataModel.setValue("pedigree"+HeaderNames+".type", "categorical");
                DataModel.setValue("pedigree"+HeaderNames+".missing", DefaultMissingValue);
              }

              old_progress = progress;
            }

              return "done";
          }
          public void finished() {

            wait.jButton1.setEnabled(true);
            wait.jButton1_actionPerformed();
          }
      };
      worker.start();
    }
    else
    {
        OKButton_actionPerformed();
    }


  }
  public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();
    if(o == OKButton)
    {
      click_ok_button();
    }
    else if(o == jButton2)
    {
      jButton2_actionPerformed(e);
    }
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
    if(ob == missingComboBox)
    {
      switch(missingComboBox.getSelectedIndex())
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
    }

    if (ob == applynextCheckBox) {
      if (applynextCheckBox.isSelected())
      {
        applyTextField.setEnabled(true);
        applyTextField.setEditable(true);
        applyTextField.requestFocus();
      }
      else{
        applyTextField.setEnabled(false);
        applyTextField.setEditable(false);
      }
    }
    else if (ob == jBinaryCheckBox) {
      if (jBinaryCheckBox.isSelected()) {
        UnAffected.setEnabled(true);
        UnAffected.setEditable(true);
        Affected.setEnabled(true);
        Affected.setEditable(true);
        unaffLabel.setEnabled(true);
        affLabel.setEnabled(true);
      }
      else {
        UnAffected.setEnabled(false);
        UnAffected.setEditable(false);
        Affected.setEditable(false);
        Affected.setEnabled(false);
        unaffLabel.setEnabled(false);
        affLabel.setEnabled(false);
      }
    }
    else if(ob == cateCheckBox)
    {
        if(cateCheckBox.isSelected())
        {
            catevalueLabel.setEnabled(true);
            catevalueTextField.setEditable(true);
            catevalueTextField.setEnabled(true);
        }
        else
        {
            catevalueLabel.setEnabled(false);
            catevalueTextField.setEditable(false);
            catevalueTextField.setEnabled(false);
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

      if(DataModel.hasValue("trait.missing"))
        defaultmissingvalue = DataModel.getValue("trait.missing").toString();
      if (defaultmissingvalue.compareTo(".") == 0)
        missingComboBox.setSelectedIndex(0);
      else if (defaultmissingvalue.compareTo("") == 0)
        missingComboBox.setSelectedIndex(1);
      else if (defaultmissingvalue.compareTo(" ") == 0)
        missingComboBox.setSelectedIndex(2);
      else if (defaultmissingvalue.compareTo("\t") == 0)
        missingComboBox.setSelectedIndex(3);
      else {
        missingComboBox.setSelectedIndex(4);
        MissingValue.setText(defaultmissingvalue);
      }
  }

  void OKButton_actionPerformed() {
    DataModel.setValue("pedigree"+HeaderName, SelectedType);
    DataModel.setValue("pedigree"+HeaderName+".Hname", HeaderName);
    DataModel.setValue("pedigree"+HeaderName+".Aname", Name.getText());

    if(usetraitCheckBox.isSelected())
    {
        DataModel.setValue("pedigree" + HeaderName + ".TR", "TRAIT_MARKER");
        DataModel.setValue("pedigree"+HeaderName+ ".TR"+".Hname", HeaderName);
        DataModel.setValue("pedigree"+HeaderName+ ".TR"+".Aname", Name.getText());
    }

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
    if(cateCheckBox.isSelected()) // categorical
    {
      DataModel.setValue("pedigree"+HeaderName+".type", "categorical");
      DataModel.setValue("pedigree"+HeaderName+".missing", DefaultMissingValue);
    }

    setVisible(false);
  }

  void jButton2_actionPerformed(ActionEvent e) {
    setVisible(false);
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == MissingValue.getDocument()) {
      if(MissingValue.getText().length()>0)
        DefaultMissingValue = MissingValue.getText();
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

}
