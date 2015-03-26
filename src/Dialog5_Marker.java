package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.Document;
import java.util.Vector;
import java.awt.event.*;

public class Dialog5_Marker extends JDialog implements DocumentListener,
        ActionListener, ItemListener {
    JPanel panel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jTopPanel = new JPanel();
    JPanel jCenterPanel = new JPanel();
    JPanel jBottomPanel = new JPanel();
    EdgeBorder lineborder = new EdgeBorder();
    XYLayout xYLayout1 = new XYLayout();
    JButton jButton1 = new JButton();
    JButton jButton2 = new JButton();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField ADelimiter = new JTextField();
    JTextField AMissing = new JTextField();
    JLabel jLabel3 = new JLabel();

    DataCollectionModel DataModel;
    String HeaderName;
    JLabel jLabel6 = new JLabel();
    JTextField Name = new JTextField();
    XYLayout xYLayout4 = new XYLayout();
    TitledBorder titledBorder1;

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
    String[] missingoption = {"Dot (\".\")", "Blank (\"\")", "Space (\" \")",
                             "Tab (\"\\t\")", "Other"};
    String[] covmodeoption = {"", "Additive", "Dominant", "Recessive"};
    JComboBox jComboBox1 = new JComboBox(missingoption);

    String DefaultAMissingValue = ".";
    FlowLayout flowLayout1 = new FlowLayout();
    BorderLayout borderLayout2 = new BorderLayout();

    JLabel covmodeLabel = new JLabel();
    JLabel covalleleLabel = new JLabel();

    JComboBox covmodeComboBox = new JComboBox(covmodeoption);
    JTextField covalleleComboBox = new JTextField();
    JCheckBox hemizygoteCheckBox = new JCheckBox();
    JCheckBox useascovCheckBox = new JCheckBox("Use this marker as covariate");

    ComponentTitledPanel allelePanel = new ComponentTitledPanel(
            usealleleCheckBox);
    ComponentTitledPanel covPanel = new ComponentTitledPanel(useascovCheckBox);

    public void showDialog() {
        DefaultAMissingValue = ".";
        this.setLocationRelativeTo(Frame1.mainFrame1);
        Name.setRequestFocusEnabled(true);
        Name.requestFocus();
        setVisible(true);
    }

    public Dialog5_Marker(String title) {
        super(Frame1.mainFrame1, title, false);
        try {
            jbInit();

            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder("Options");
        panel1.setLayout(borderLayout1);
        jLabel3.setBorder(new EmptyBorder(0, 20, 0, 0));
        this.setSize(new Dimension(340, 500));
        this.setResizable(false);
        ButtonGroup bg = new ButtonGroup();
        minCheckBox.addItemListener(this);

        jLabel1.setToolTipText(
                "Character used to delimit alleles of codominant markers in a pedigree " +
                "data file.");
        jLabel2.setToolTipText("");
        jCheckBox1.setText("Apply to next ");
        jCheckBox1.addItemListener(this);
        jLabel4.setText("column(s)");
        jTextField1.setEnabled(false);
        jTextField1.setEditable(false);
        jTextField1.setText("");
        jLabel5.setEnabled(false);
        jLabel5.setToolTipText(
                "Specifies maximum allele frequency for the marker.");
        minCheckBox.setEnabled(false);
        minCheckBox.setToolTipText(
                "Specifies minimum allele frequency for the marker.");
        minCheckBox.setSelected(true);
        jCheckComplement.setEnabled(false);
        jCheckComplement.setToolTipText(
                "Sets allele frequencies to proportional complementary values.");
        jCheckEqual.setEnabled(false);
        jCheckEqual.setToolTipText("Sets all allele frequencies to be equal.");
        usealleleCheckBox.addItemListener(this);

        hemizygoteCheckBox.setFocusPainted(false);
        jCheckEqual.setFocusPainted(false);
        jCheckComplement.setFocusPainted(false);
        jCheckBox1.setFocusPainted(false);
        minCheckBox.setFocusPainted(false);
        usealleleCheckBox.setFocusPainted(false);
        useascovCheckBox.setFocusPainted(false);

        Minimum.setEditable(false);
        Maximum.setEditable(false);
        AMissing.setEnabled(false);
        AMissing.setEditable(false);
        AMissing.setText("");
        jButton1.setPreferredSize(new Dimension(60, 25));
        jButton2.setPreferredSize(new Dimension(60, 25));
        jButton2.setMargin(new Insets(2, 2, 2, 2));
        covmodeLabel.setText("Covariate mode of inheritance");
        covalleleLabel.setText("Covariate allele");
        hemizygoteCheckBox.setText("Allow hemizygote data from autosomal loci");
        covmodeLabel.setEnabled(false);
        covmodeComboBox.setEnabled(false);
        covalleleLabel.setEnabled(false);
        covalleleComboBox.setEnabled(false);
        hemizygoteCheckBox.setEnabled(false);
        useascovCheckBox.addItemListener(this);

        bg.add(minCheckBox);
        bg.add(jCheckComplement);
        bg.add(jCheckEqual);

        Minimum.setEnabled(false);
        Maximum.setEnabled(false);

        jTopPanel.setBackground(Color.white);
        jTopPanel.setPreferredSize(new Dimension(340, 40));
        jTopPanel.setLayout(borderLayout2);
        jCenterPanel.setLayout(xYLayout1);
        jCenterPanel.setPreferredSize(new Dimension(340, 420));
        jCenterPanel.setBorder(lineborder);
        jBottomPanel.setBorder(lineborder);
        jBottomPanel.setPreferredSize(new Dimension(340, 40));
        jBottomPanel.setLayout(flowLayout1);

        jButton1.setText("OK");
        jButton1.addActionListener(this);
        jButton2.setText("Cancel");
        jButton2.setMargin(new Insets(2, 2, 2, 2));
        jButton2.addActionListener(this);
        jLabel1.setText("Allele delimiter ");
        jLabel2.setText("Missing value");
        ADelimiter.setText("/");
        jLabel3.setText(
                "Fill in the fields below as they apply to the pedigree data.");
        jLabel6.setText("Name");
        Name.setText("");
        Minimum.setText("");
        jCheckComplement.setText("Complement");
        jCheckComplement.addItemListener(this);
        Maximum.setText("");
        minCheckBox.setText("Minimum");
        minCheckBox.addItemListener(this);
        jCheckEqual.setText("Equal");
        jCheckEqual.addItemListener(this);
        jLabel5.setText("Maximum");
        getContentPane().add(panel1);
        panel1.add(jTopPanel, BorderLayout.NORTH);
        jTopPanel.add(jLabel3, BorderLayout.CENTER);
        panel1.add(jCenterPanel, BorderLayout.CENTER);
        panel1.add(jBottomPanel, BorderLayout.SOUTH);
        jBottomPanel.add(jButton1, null);
        jBottomPanel.add(jButton2, null);

        jCenterPanel.add(Name, new XYConstraints(110, 15, 210, 20));
        jCenterPanel.add(ADelimiter, new XYConstraints(110, 45, 210, 20));

        allelePanel.getContentPane().setLayout(new XYLayout());
        allelePanel.getContentPane().add(minCheckBox,
                                         new XYConstraints(5, 5, 70, 20));
        allelePanel.getContentPane().add(Minimum,
                                         new XYConstraints(85, 5, 60, 20));
        allelePanel.getContentPane().add(jLabel5,
                                         new XYConstraints(165, 5, 70, 20));
        allelePanel.getContentPane().add(Maximum,
                                         new XYConstraints(220, 5, 60, 20));

        allelePanel.getContentPane().add(jCheckComplement,
                                         new XYConstraints(5, 34, 90, 20));
        allelePanel.getContentPane().add(jCheckEqual,
                                         new XYConstraints(5, 63, 80, 20));

        jCenterPanel.add(jComboBox1, new XYConstraints(110, 75, 100, 20));
        jCenterPanel.add(AMissing, new XYConstraints(220, 75, 100, 20));
        jCenterPanel.add(jLabel6, new XYConstraints(20, 15, 50, 20));
        jCenterPanel.add(jLabel1, new XYConstraints(20, 45, 76, 20));
        jCenterPanel.add(jLabel2, new XYConstraints(20, 75, 75, 20));
        covPanel.getContentPane().setLayout(new XYLayout());

        covPanel.getContentPane().add(covmodeLabel,
                                      new XYConstraints(10, 5, 200, 20));
        covPanel.getContentPane().add(covmodeComboBox,
                                      new XYConstraints(180, 5, 100, 20)); //170

        covPanel.getContentPane().add(covalleleLabel,
                                      new XYConstraints(10, 34, 200, 20));
        covPanel.getContentPane().add(covalleleComboBox,
                                      new XYConstraints(180, 34, 100, 20));
        covPanel.getContentPane().add(hemizygoteCheckBox,
                                      new XYConstraints(5, 63, 300, 20));

        jCenterPanel.add(allelePanel, new XYConstraints(15, 105, 305, 123));
        jCenterPanel.add(covPanel, new XYConstraints(15, 235, 305, 123));

        jCenterPanel.add(jCheckBox1, new XYConstraints(20, 385, 100, 20));
        jCenterPanel.add(jTextField1, new XYConstraints(125, 385, 50, 20));
        jCenterPanel.add(jLabel4, new XYConstraints(190, 385, 385, 20));

        jComboBox1.addItemListener(this);
        AMissing.getDocument().addDocumentListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o == jButton1) {
            if (ADelimiter.getText().compareTo(DefaultAMissingValue) == 0) {
                JOptionPane.showInternalOptionDialog(Frame1.mainFrame1,
                        "You may not specify the same character \nfor the allele delimiter and allele missing options.",
                        "Error",
                        JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                return;
            }

            if (usealleleCheckBox.isSelected()) {
                if (minCheckBox.isSelected()) {
                    if (Minimum.getText().compareTo("") == 0 ||
                        Maximum.getText().compareTo("") == 0) {
                        JOptionPane.showInternalOptionDialog(Frame1.mainFrame1,
                                "You must specify a minimum and maximum value \nfor the allele frequency option.",
                                "Error",
                                JOptionPane.CLOSED_OPTION,
                                JOptionPane.WARNING_MESSAGE, null, null, null);
                        return;
                    }
                }
            }
            //end error check

            switch (jComboBox1.getSelectedIndex()) {
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
                if (AMissing.getText().length() > 0)
                    DefaultAMissingValue = AMissing.getText();
                break;
            }

            if (MakeParameterStep2.marker_count == 1) {
                // set default marker block
                DataModel.setValue("marker.allele_delimiter",
                                   ADelimiter.getText());
                DataModel.setValue("marker.allele_missing",
                                   DefaultAMissingValue);

                // allele frequency
                if (usealleleCheckBox.isSelected()) {
                    DataModel.setValue("marker.allele_frequency", "use");
                    if (minCheckBox.isSelected()) {
                        DataModel.setValue("marker.allele_frequency.minimum",
                                           Minimum.getText());
                        DataModel.setValue("marker.allele_frequency.maximum",
                                           Maximum.getText());
                    } else if (jCheckComplement.isSelected()) {
                        DataModel.setValue("marker.allele_frequency.complement",
                                           "use");
                    } else if (jCheckEqual.isSelected()) {
                        DataModel.setValue("marker.allele_frequency.equal",
                                           "use");
                    }
                }

                // use as covariate
                if (useascovCheckBox.isSelected()) {
                    DataModel.setValue("marker.useascov", "use");
                    if (covmodeComboBox.getSelectedIndex() > 0) {
                        DataModel.setValue("marker.useascov.covmode",
                                           covmodeComboBox.getSelectedItem().
                                           toString());
                    }
                    if (covalleleComboBox.getText().trim().length() > 0) {
                        DataModel.setValue("marker.useascov.covallele",
                                           covalleleComboBox.getText().trim());
                    }
                    if (hemizygoteCheckBox.isSelected())
                        DataModel.setValue("marker.useascov.hemizygote", "true");
                    else
                        DataModel.setValue("marker.useascov.hemizygote",
                                           "false");
                }
            }
            MakeParameterStep2.marker_count++;

            if (jCheckBox1.isSelected() && jTextField1.getText().length() > 0) {
                int totalcolumnnumber = MakeParameterStep2.real_col_size;
                int restcolumnnumber = Math.abs(totalcolumnnumber - cindex - 1);

                try {
                    Integer.parseInt(jTextField1.getText());
                } catch (NumberFormatException en) {
                    JOptionPane.showOptionDialog(this.getParent(),
                                                 "You must enter a numerical value between 1 and " +
                                                 restcolumnnumber +
                                                 ", inclusive, \nto specify the applicable columns.",
                                                 "Error",
                                                 JOptionPane.CLOSED_OPTION,
                                                 JOptionPane.WARNING_MESSAGE, null, null, null);
                    jTextField1.requestFocus();
                    return;
                }

                final int count = Integer.parseInt(jTextField1.getText());

                if (count > restcolumnnumber) {
                    JOptionPane.showOptionDialog(this.getParent(),
                                                 "You must enter a numerical value between 1 and " +
                                                 restcolumnnumber +
                                                 ", inclusive, \nto specify the applicable columns.",
                                                 "Error",
                                                 JOptionPane.CLOSED_OPTION,
                                                 JOptionPane.WARNING_MESSAGE, null, null, null);
                    jTextField1.requestFocus();
                    return;
                }

                setVisible(false);

                final Vector MList_Start_array = new Vector();
                final Vector MList_End_array = new Vector();
                final Vector MList_Start_Index_array = new Vector();
                final Vector MList_End_Index_array = new Vector();

                Frame1.mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.
                        WAIT_CURSOR));
                JWizardProject.wizardProject.setCursor(Cursor.
                        getPredefinedCursor(Cursor.WAIT_CURSOR));

                Runnable lookupData = new Runnable() {
                    public void run() {
                        try {

                            ProgressDialog pd = new ProgressDialog("Warning");
                            pd.showDialog();

                            int progress = 0;

                            pd.SetProgress(progress);

                            int per = count / 100;

                            if (per == 0)
                                per = 1;

                            for (int i = 0; i <= count; i++) {
                                if (i % per == 0) {
                                    progress += 1;
                                    pd.SetProgress(progress);
                                }

                                if (cindex + i < 500)
                                    MakeParameterStep2.dm.setValueAt(
                                            SelectedType, 0, cindex + i);

                                String HeaderNames = MakeParameterStep2.
                                        columnNames_all[cindex + i];

                                DataModel.setValue("pedigree" + HeaderNames,
                                        SelectedType);
                                DataModel.setValue("pedigree" + HeaderNames +
                                        ".Hname", HeaderNames);
                                DataModel.setValue("pedigree" + HeaderNames +
                                        ".Aname", HeaderNames);

                                if (count >= 5) {
                                    if (i == 0) {
                                        MList_Start_array.add(HeaderNames);
                                        MList_Start_Index_array.add(Integer.
                                                toString(cindex + i));
                                    }
                                    if (i == count) {
                                        MList_End_array.add(HeaderNames);
                                        MList_End_Index_array.add(Integer.
                                                toString(cindex + i));
                                    }
                                }
                            }

                            DataModel.setValue("MList_Start_array",
                                               MList_Start_array);
                            DataModel.setValue("MList_End_array",
                                               MList_End_array);
                            DataModel.setValue("MList_Start_Index_array",
                                               MList_Start_Index_array);
                            DataModel.setValue("MList_End_Index_array",
                                               MList_End_Index_array);

                            pd.jButton1.setEnabled(true);
                            pd.jButton1_actionPerformed();

                            Frame1.mainFrame1.setCursor(null);
                            JWizardProject.wizardProject.setCursor(null);

                            ((PropertyDataModel) DataModel).fireChangeEvent();
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
            } else {
                jButton1_actionPerformed(e);
            }
        }
        if (o == jButton2)
            jButton2_actionPerformed(e);
    }

    public void itemStateChanged(ItemEvent e) {
        Object ob = e.getSource();

        if (ob == jComboBox1) {
            switch (jComboBox1.getSelectedIndex()) {
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

        else if (ob == jCheckBox1) {
            if (jCheckBox1.isSelected()) {
                jTextField1.setEnabled(true);
                jTextField1.setEditable(true);
                jTextField1.requestFocus();
            } else {
                jTextField1.setEnabled(false);
                jTextField1.setEditable(false);
            }

        }

        else if (ob == usealleleCheckBox) {
            if (usealleleCheckBox.isSelected()) {
                minCheckBox.setEnabled(true);
                jCheckComplement.setEnabled(true);
                jCheckEqual.setEnabled(true);
                Minimum.setEditable(true);
                Minimum.setEnabled(true);
                Maximum.setEditable(true);
                Maximum.setEnabled(true);
                jLabel5.setEnabled(true);
                Minimum.setText(".05");
                Maximum.setText(".95");
            } else {
                minCheckBox.setEnabled(false);
                jCheckComplement.setEnabled(false);
                jCheckEqual.setEnabled(false);
                Minimum.setEditable(false);
                Minimum.setEnabled(false);
                Maximum.setEditable(false);
                Maximum.setEnabled(false);
                jLabel5.setEnabled(false);
                Minimum.setText("");
                Maximum.setText("");
            }
        } else if (ob == useascovCheckBox) {
            if (useascovCheckBox.isSelected()) {
                covmodeLabel.setEnabled(true);
                covmodeComboBox.setEnabled(true);
                covalleleLabel.setEnabled(true);
                covalleleComboBox.setEnabled(true);
                hemizygoteCheckBox.setEnabled(true);
            } else {
                covmodeLabel.setEnabled(false);
                covmodeComboBox.setEnabled(false);
                covalleleLabel.setEnabled(false);
                covalleleComboBox.setEnabled(false);
                hemizygoteCheckBox.setEnabled(false);
            }
        } else if (ob == minCheckBox) {
            if (minCheckBox.isSelected()) {
                Minimum.setEnabled(true);
                Maximum.setEnabled(true);
            } else {
                Minimum.setEnabled(false);
                Maximum.setEnabled(false);
            }
        }
    }

    public void SetDataModel(DataCollectionModel DM, String HN, String ST,
                             int columnindex) {
        DataModel = DM;
        HeaderName = HN;
        Name.setText(HeaderName);
        SelectedType = ST;
        cindex = columnindex;
    }

    public void SetDefaultData(DataCollectionModel DM, String HN, String ST,
                               int columnindex) {
        DataModel = DM;
        HeaderName = HN;
        Name.setText(HeaderName);
        SelectedType = ST;
        cindex = columnindex;

        ADelimiter.setText(DataModel.getValue("marker.allele_delimiter").
                           toString());

        String defaultmissingvalue = DataModel.getValue("marker.allele_missing").
                                     toString();
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
            AMissing.setText(defaultmissingvalue);
        }

        if (DataModel.hasValue("marker.allele_frequency")) {
            usealleleCheckBox.setSelected(true);
            if (DataModel.hasValue("marker.allele_frequency.minimum")) {
                minCheckBox.setSelected(true);
                Minimum.setText(DataModel.getValue(
                        "marker.allele_frequency.minimum").toString());
                Maximum.setText(DataModel.getValue(
                        "marker.allele_frequency.maximum").toString());
            }
            if (DataModel.hasValue("marker.allele_frequency.complement")) {
                jCheckComplement.setSelected(true);
            }
            if (DataModel.hasValue("marker.allele_frequency.equal")) {
                jCheckEqual.setSelected(true);
            }
        }

        if (DataModel.hasValue("marker.useascov")) {

            useascovCheckBox.setSelected(true);

            if (DataModel.hasValue("marker.useascov.covmode")) {
                covmodeComboBox.setSelectedItem(DataModel.getValue(
                        "marker.useascov.covmode").toString());
            }
            if (DataModel.hasValue("marker.useascov.covallele")) {
                covalleleComboBox.setText(DataModel.getValue(
                        "marker.useascov.covallele").toString());
            }
            if (DataModel.hasValue("marker.useascov.hemizygote")) {
                if (DataModel.getValue("marker.useascov.hemizygote").toString().
                    compareTo("true") == 0) {
                    hemizygoteCheckBox.setSelected(true);
                }
            }
        }

    }

    void jButton1_actionPerformed(ActionEvent e) {
        switch (jComboBox1.getSelectedIndex()) {
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
            if (AMissing.getText().length() > 0)
                DefaultAMissingValue = AMissing.getText();
            break;
        }

        DataModel.setValue("pedigree" + HeaderName, SelectedType);
        DataModel.setValue("pedigree" + HeaderName + ".Hname", HeaderName);
        DataModel.setValue("pedigree" + HeaderName + ".Aname", Name.getText());

        DataModel.setValue("pedigree" + HeaderName + ".allele_delimiter",
                           ADelimiter.getText());
        DataModel.setValue("pedigree" + HeaderName + ".allele_missing",
                           DefaultAMissingValue);

        if (usealleleCheckBox.isSelected()) {
            if (minCheckBox.isSelected()) {
                DataModel.setValue("pedigree" + HeaderName +
                                   ".allele_frequency.minimum", Minimum.getText());
                DataModel.setValue("pedigree" + HeaderName +
                                   ".allele_frequency.maximum", Maximum.getText());
            } else if (jCheckComplement.isSelected()) {
                DataModel.setValue("pedigree" + HeaderName +
                                   ".allele_frequency.complement", "use");
            } else if (jCheckEqual.isSelected()) {
                DataModel.setValue("pedigree" + HeaderName +
                                   ".allele_frequency.equal", "use");
            }
        }

        if (useascovCheckBox.isSelected()) {
            DataModel.setValue("pedigree" + HeaderName + ".useascov", "true");
            if (covmodeComboBox.getSelectedIndex() > 0) {
                DataModel.setValue("pedigree" + HeaderName +
                                   ".useascov.covmode",
                                   covmodeComboBox.getSelectedItem().toString());
            }

            if (covalleleComboBox.getText().trim().length() > 0) {
                DataModel.setValue("pedigree" + HeaderName +
                                   ".useascov.covallele",
                                   covalleleComboBox.getText().trim());
            }
            if (hemizygoteCheckBox.isSelected()) {
                DataModel.setValue("pedigree" + HeaderName +
                                   ".useascov.hemizygote", "true");
            } else
                DataModel.setValue("pedigree" + HeaderName +
                                   ".useascov.hemizygote", "false");

        }

        setVisible(false);
    }

    void jButton2_actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public void changedUpdate(DocumentEvent event) {
        Document document = event.getDocument();
        if (document == AMissing.getDocument()) {
            if (AMissing.getText().length() > 0)
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

