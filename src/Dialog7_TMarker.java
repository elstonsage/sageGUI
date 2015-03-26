package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class Dialog7_TMarker extends JDialog implements ActionListener,
        ItemListener {
    JPanel panel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jTopPanel = new JPanel();
    JPanel jCenterPanel = new JPanel();
    JPanel jBottomPanel = new JPanel();
    EdgeBorder lineborder = new EdgeBorder();
    XYLayout xYLayout1 = new XYLayout();
    JButton OKButton = new JButton();
    JButton jButton2 = new JButton();
    JPanel jPanel1 = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JLabel jLabel2 = new JLabel();
    JTextField UnAffected = new JTextField();
    JLabel jLabel1 = new JLabel();
    JTextField Affected = new JTextField();
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

    JPanel jPanel2 = new JPanel();
    XYLayout xYLayout5 = new XYLayout();
    JLabel jLabel8 = new JLabel();
    JTextField jTextField_Daf = new JTextField();
    JLabel jLabel3 = new JLabel();
    JTextField jTextField_D1 = new JTextField();
    JLabel jLabel7 = new JLabel();
    JTextField jTextField_D2 = new JTextField();
    TitledBorder titledBorder1;
    FlowLayout flowLayout1 = new FlowLayout();
    JRadioButton jRadioButton1 = new JRadioButton();
    JRadioButton jRadioButton2 = new JRadioButton();
    JPanel jPanel3 = new JPanel();
    JPanel jPanel4 = new JPanel();
    XYLayout xYLayout3 = new XYLayout();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JLabel jLabel11 = new JLabel();
    JTextField jTextField_R1 = new JTextField();
    JTextField jTextField_R2 = new JTextField();
    JTextField jTextField_R3 = new JTextField();

    ButtonGroup bp = new ButtonGroup();
    BorderLayout borderLayout2 = new BorderLayout();

    ProgressDialog wait = new ProgressDialog("Please wait..");
    public void showDialog() {
        jCheckBox1.setSelected(false);
        jTextField1.setText("");
        Name.requestFocus();

        this.setLocationRelativeTo(Frame1.mainFrame1);
        setVisible(true);
    }

    public Dialog7_TMarker(String title) {
        super(Frame1.mainFrame1, title, false);
        try {
            jbInit();
            pack();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {

        titledBorder1 = new TitledBorder("");
        panel1.setLayout(borderLayout1);
        jLabel4.setBorder(new EmptyBorder(0, 20, 0, 0));
        this.setSize(new Dimension(340, 380));
        this.setResizable(false);
        jLabel4.setText(
                "Fill in the fields below as they apply to the pedigree data.");
        jTopPanel.setLayout(borderLayout2);
        OKButton.addActionListener(this);
        jButton2.addActionListener(this);

        jRadioButton1.addActionListener(this);
        jRadioButton2.addActionListener(this);

        jLabel5.setToolTipText("");
        jLabel5.setText("Name");
        Name.setText("");
        jCheckBox1.setText("Apply to next");
        jCheckBox1.addItemListener(this);
        jLabel6.setText("column(s)");
        jTextField1.setEnabled(false);
        jTextField1.setEditable(false);

        jTopPanel.setBackground(Color.white);
        jTopPanel.setPreferredSize(new Dimension(340, 40));
        jCenterPanel.setLayout(xYLayout1);
        jCenterPanel.setPreferredSize(new Dimension(340, 300));
        jCenterPanel.setBorder(lineborder);
        jBottomPanel.setBorder(lineborder);
        jBottomPanel.setPreferredSize(new Dimension(340, 40));
        jBottomPanel.setLayout(flowLayout1);

        OKButton.setPreferredSize(new Dimension(60, 25));
        OKButton.setText("OK");
        jButton2.setPreferredSize(new Dimension(60, 25));
        jButton2.setMargin(new Insets(2, 2, 2, 2));
        jButton2.setText("Cancel");
        jPanel1.setLayout(xYLayout2);
        jPanel1.setBorder(titledBorder1);
        jLabel2.setText("Unaffected ");
        jLabel1.setToolTipText("");
        jLabel1.setText("Affected ");

        jPanel4.setLayout(new CardLayout());
        jPanel3.setLayout(xYLayout3);
        jLabel9.setText("Penetrance");
        jLabel10.setText("Heterozygote sporadic rate");
        jLabel11.setText("Homozygote sporadic rate");
        jTextField_R1.setText("");
        jTextField_R2.setText("");
        jTextField_R3.setText("");

        jTextField_Daf.setText("");
        Affected.setText("");
        UnAffected.setText("");
        jTextField_D1.setText("");
        jRadioButton1.setSelected(true);
        jPanel3.add(jLabel9, new XYConstraints(10, 5, 130, 20));
        jPanel3.add(jLabel10, new XYConstraints(10, 35, 153, 20));
        jPanel3.add(jLabel11, new XYConstraints(10, 65, 130, 20));
        jPanel2.setLayout(xYLayout5);
        jLabel8.setText("Sporadic rate");
        jLabel3.setText("Disease allele frequency");
        jLabel7.setText("Penetrance");
        jRadioButton1.setText("Dominant");
        jRadioButton2.setText("Recessive");
        jPanel4.setBorder(titledBorder1);
        getContentPane().add(panel1);
        panel1.add(jTopPanel, BorderLayout.NORTH);
        jTopPanel.add(jLabel4, BorderLayout.CENTER);
        panel1.add(jCenterPanel, BorderLayout.WEST);
        jPanel1.add(jLabel2, new XYConstraints(110, 65, 80, 20));
        jPanel1.add(jLabel1, new XYConstraints(110, 35, 80, 20));
        jPanel1.add(jRadioButton1, new XYConstraints(10, 35, 80, 20));
        jPanel1.add(jRadioButton2, new XYConstraints(10, 65, 80, 20));
        jPanel1.add(jLabel3, new XYConstraints(10, 5, 130, 20));
        jCenterPanel.add(Name, new XYConstraints(110, 15, 210, 20));
        jCenterPanel.add(jTextField1, new XYConstraints(125, 265, 50, 20));
        jCenterPanel.add(jPanel2, new XYConstraints(30, 147, 295, 105));
        jPanel2.add(jLabel8, new XYConstraints(10, 35, 130, 20));
        jPanel2.add(jLabel7, new XYConstraints(10, 5, 130, 20));
        jCenterPanel.add(jPanel3, new XYConstraints(30, 157, 294, 108));
        jPanel4.add(jPanel2, "Dominant");
        jPanel4.add(jPanel3, "Recessive");

        panel1.add(jBottomPanel, BorderLayout.SOUTH);
        jBottomPanel.add(OKButton, null);
        jBottomPanel.add(jButton2, null);
        bp.add(jRadioButton1);
        bp.add(jRadioButton2);
        jCenterPanel.add(jLabel5, new XYConstraints(20, 15, 50, 20));
        jCenterPanel.add(jPanel1, new XYConstraints(20, 45, 301, 103));
        jCenterPanel.add(jPanel4, new XYConstraints(20, 152, 301, 105));
        jCenterPanel.add(jLabel6, new XYConstraints(190, 265, 50, 20));
        jCenterPanel.add(jCheckBox1, new XYConstraints(20, 265, 105, 20));
        jPanel1.add(jTextField_Daf, new XYConstraints(180, 5, 100, 20));
        jPanel1.add(Affected, new XYConstraints(180, 35, 100, 20));
        jPanel1.add(UnAffected, new XYConstraints(180, 65, 100, 20));
        jPanel2.add(jTextField_D1, new XYConstraints(180, 5, 100, 20));
        jPanel2.add(jTextField_D2, new XYConstraints(180, 35, 100, 20));
        jPanel3.add(jTextField_R1, new XYConstraints(180, 5, 100, 20));
        jPanel3.add(jTextField_R2, new XYConstraints(180, 35, 100, 20));
        jPanel3.add(jTextField_R3, new XYConstraints(180, 65, 100, 20));
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == OKButton) {
            if (MakeParameterStep2.trait_marker_count == 1) {
//        DataModel.setValue("trait_marker.missing", DefaultMissingValue);
            }
            MakeParameterStep2.trait_marker_count++;

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

                SwingWorker worker = new SwingWorker() {

                    public Object construct() {
                        wait.jLabel4.setText(
                                "Please wait while your data is being processed.");
                        wait.showDialog();

                        double value = (double) 100 / (double) count;
                        int old_progress = 0;

                        for (int i = 0; i <= count; i++) {

                            int progress = (int) (i * value);
                            if (progress != old_progress)
                                wait.setProgressValue(progress);

                            if (cindex + i < 500)
                                MakeParameterStep2.dm.setValueAt(SelectedType,
                                        0, cindex + i);
                            String HeaderNames = MakeParameterStep2.
                                                 columnNames_all[cindex + i];

                            DataModel.setValue("pedigree" + HeaderNames,
                                               SelectedType);
                            DataModel.setValue("pedigree" + HeaderNames +
                                               ".Hname", HeaderNames);
                            DataModel.setValue("pedigree" + HeaderNames +
                                               ".Aname", HeaderNames);

                            if (Affected.getText().length() > 0)
                                DataModel.setValue("pedigree" + HeaderNames +
                                        ".affected", Affected.getText());

                            if (UnAffected.getText().length() > 0)
                                DataModel.setValue("pedigree" + HeaderNames +
                                        ".unaffected", UnAffected.getText());

                            if (jTextField_Daf.getText().length() > 0)
                                DataModel.setValue("pedigree" + HeaderNames +
                                        ".daf", jTextField_Daf.getText());

                            if (jRadioButton1.isSelected()) {
                                if (jTextField_D1.getText().length() > 0)
                                    DataModel.setValue("pedigree" + HeaderNames +
                                            ".d1", jTextField_D1.getText());

                                if (jTextField_D2.getText().length() > 0)
                                    DataModel.setValue("pedigree" + HeaderNames +
                                            ".d2", jTextField_D2.getText());
                            }
                            if (jRadioButton2.isSelected()) {
                                if (jTextField_R1.getText().length() > 0)
                                    DataModel.setValue("pedigree" + HeaderNames +
                                            ".r1", jTextField_R1.getText());

                                if (jTextField_R2.getText().length() > 0)
                                    DataModel.setValue("pedigree" + HeaderNames +
                                            ".r2", jTextField_R2.getText());

                                if (jTextField_R3.getText().length() > 0)
                                    DataModel.setValue("pedigree" + HeaderNames +
                                            ".r3", jTextField_R3.getText());
                            }
                        }

                        return "done";
                    }

                    public void finished() {
                        wait.jButton1.setEnabled(true);
                        wait.jButton1_actionPerformed();
                    }
                };
                worker.start();

            } else {
                OKButton_actionPerformed(e);
            }

        } else if (o == jButton2) {
            jButton2_actionPerformed(e);
        }

        else if (o == jRadioButton1) {
            CardLayout cl = (CardLayout) (jPanel4.getLayout());
            cl.show(jPanel4, jRadioButton1.getText());
        }

        else if (o == jRadioButton2) {
            CardLayout cl = (CardLayout) (jPanel4.getLayout());
            cl.show(jPanel4, jRadioButton2.getText());
        }

    }

    public void itemStateChanged(ItemEvent e) {
        Object ob = e.getSource();
        if (ob == jCheckBox1) {
            if (jCheckBox1.isSelected()) {
                jTextField1.setEnabled(true);
                jTextField1.setEditable(true);
                jTextField1.requestFocus();
            } else {
                jTextField1.setEnabled(false);
                jTextField1.setEditable(false);
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
    }

    void OKButton_actionPerformed(ActionEvent e) {
        DataModel.setValue("pedigree" + HeaderName, SelectedType);
        DataModel.setValue("pedigree" + HeaderName + ".Hname", HeaderName);
        DataModel.setValue("pedigree" + HeaderName + ".Aname", Name.getText());

        if (Affected.getText().length() > 0)
            DataModel.setValue("pedigree" + HeaderName + ".affected",
                               Affected.getText());

        if (UnAffected.getText().length() > 0)
            DataModel.setValue("pedigree" + HeaderName + ".unaffected",
                               UnAffected.getText());

        if (jTextField_Daf.getText().length() > 0)
            DataModel.setValue("pedigree" + HeaderName + ".daf",
                               jTextField_Daf.getText());

        if (jRadioButton1.isSelected()) { //dominant
            if (jTextField_D1.getText().length() > 0)
                DataModel.setValue("pedigree" + HeaderName + ".d1",
                                   jTextField_D1.getText());

            if (jTextField_D2.getText().length() > 0)
                DataModel.setValue("pedigree" + HeaderName + ".d2",
                                   jTextField_D2.getText());
        }

        if (jRadioButton2.isSelected()) { //recessive
            if (jTextField_R1.getText().length() > 0)
                DataModel.setValue("pedigree" + HeaderName + ".r1",
                                   jTextField_R1.getText());

            if (jTextField_R2.getText().length() > 0)
                DataModel.setValue("pedigree" + HeaderName + ".r2",
                                   jTextField_R2.getText());

            if (jTextField_R3.getText().length() > 0)
                DataModel.setValue("pedigree" + HeaderName + ".r3",
                                   jTextField_R3.getText());
        }
        setVisible(false);
    }

    void jButton2_actionPerformed(ActionEvent e) {
        setVisible(false);
    }

}
