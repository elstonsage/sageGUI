package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GENIBD2
    extends SageAnalysisPanel
    implements DocumentListener, java.awt.event.ItemListener, ActionListener {
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel2 = new JLabel();
  ButtonGroup bg = new ButtonGroup();
  String[] output_pair_types = {
      "Full sibs", "All sibs", "All relatives"};
  String[] realoutput_pair_types = {
      "siblings", "all_sibs", "relatives"};
  String[] scan_type = {
      "Markers", "Interval(s)"};
  String[] realscan_type = {
      "markers", "intervals"};

  String[] ibd_mode = {
      "Multipoint", "Singlepoint"};
 String[] realibd_mode = {
        "multipoint", "singlepoint"};

  String[] split_pedigrees = {
      "If necessary", "No", "Always"};
  String[] use_simulation = {
      "True", "False", "Always"};
  String[] realuse_simulation = {
      "yes", "no", "always"};

  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel13 = new JLabel();

  JTextField title = new JTextField();
  JTextField max_ped = new JTextField();
  JComboBox outputpairtype = new JComboBox(output_pair_types);
  JCheckBox allowloops = new JCheckBox();
  JComboBox ibdmode = new JComboBox(ibd_mode);
  JComboBox splitped = new JComboBox(split_pedigrees);
  JComboBox usesimulation = new JComboBox(use_simulation);
  JTextField factor11 = new JTextField();
  JTextField factor12 = new JTextField();
  JTextField factor13 = new JTextField();

  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder3;
  XYLayout xYLayout2 = new XYLayout();
  DataCollectionModel Datamodel;

  GENIBD1 genibd1;
  MyComboBox jRegionComboBox = new MyComboBox();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  XYLayout xYLayout3 = new XYLayout();
  JTextField factor14 = new JTextField();
  JLabel jLabel17 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JTextField factor21 = new JTextField();
  JLabel jLabel19 = new JLabel();
  JTextField factor22 = new JTextField();
  JLabel jLabel20 = new JLabel();
  JTextField factor23 = new JTextField();
  JPanel jPanel4 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  XYLayout xYLayout4 = new XYLayout();
  XYLayout xYLayout5 = new XYLayout();
  JLabel jLabel110 = new JLabel();
  JCheckBox factoring = new JCheckBox();
  JTextField jTextField2 = new JTextField();
  JLabel jLabel11 = new JLabel();
  JPanel jPanel7 = new JPanel();
  XYLayout xYLayout6 = new XYLayout();
  JLabel jLabel16 = new JLabel();
  JComboBox scantype = new JComboBox(scan_type);
  JTextField jTextField1 = new JTextField();
  JLabel jLabel7 = new JLabel();

  public GENIBD2(GENIBD1 input) {
    this.Datamodel = input.Datamodel;
    genibd1 = input;
    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    titledBorder3 = new TitledBorder("");

    jPanel4.setLayout(new CardLayout());


    allowloops.setFocusPainted(false);
    factoring.setFocusPainted(false);

    allowloops.addMouseListener(new GENIBD2_allowloops_mouseAdapter(this));
    splitped.setSelectedIndex(1);
    splitped.addMouseListener(new GENIBD2_splitped_mouseAdapter(this));
    outputpairtype.setSelectedIndex(2);
    outputpairtype.addMouseListener(new GENIBD2_outputpairtype_mouseAdapter(this));

    jLabel1.setToolTipText("<html>The analysis title.");
    jLabel1.setText("Title");
    this.setLayout(xYLayout1);
    jLabel3.setToolTipText(
        "<html>The largest 2n \u2212 f value to be processed for a pedigree while" +
        "<br>performing exact multi-point or single-point analysis.");
    jLabel3.setText("Maximum bit #");
    jLabel2.setToolTipText(
        "<html>Selects either single- or multi-point IBD generation.");
    jLabel2.setText("IBD mode");
    title.setToolTipText("");
    title.setText("analysis1");
    title.addMouseListener(new GENIBD2_title_mouseAdapter(this));

    max_ped.setText("18");
    max_ped.addMouseListener(new GENIBD2_max_ped_mouseAdapter(this));
    jLabel4.setToolTipText(
            "<html>Specifies the types of relative pairs to use for the analysis. <b>All relative pairs</b>"+
            "<br>signifies full-sibs, half-sibs, grandparents, avuncular and cousins .");
    jLabel4.setText("Output pair types");
    jLabel5.setToolTipText("Specifies a region to be analyzed.");
    jLabel5.setText("Region");
    jLabel6.setToolTipText(
        "<html>Indicates whether to compute IBD sharing at the observed markers " +
        "<br>or at the markers and intervals between them.");
    jLabel6.setText("Scan type");
    jLabel8.setToolTipText(
        "<html>Option to allow pedigrees that are too large for the exact analysis" +
        "<br>to be split into nuclear families before processing. Setting the value" +
        "<br>to \"<b>always</b>\" means that all pedigrees will be split in this fashion.");
    jLabel8.setText("Split pedigrees");
    jLabel9.setToolTipText(
        "<html>Option to allow MCMC sampling on pedigrees that are too large for the exact methods." +
        "<br>Setting the value to <b>always</b> means that all pedigrees will use sampling.");
    jLabel9.setText("Use simulation");
    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);
    jPanel1.setLayout(xYLayout2);


    jLabel12.setToolTipText(
        "<html>The base scaling factor provides a method of adjusting all three " +
        "<br>scaling factors together.");
    jLabel12.setText("Base factor");

    jLabel13.setText("MCMC steps scaling factor");
    jLabel13.setToolTipText(
            "This controls the number of MCMC steps during each batch.");

    jLabel14.setToolTipText(
            "This controls the number of batches of MCMC to perform.");
    jLabel14.setText("MCMC batch count scaling factor");

    jLabel17.setText("Dememorization scaling factor");
    jLabel17.setToolTipText(
            "This controls the number of dememorization steps done during each batch.");

    jLabel18.setToolTipText("The number of MCMC steps in each batch.");
    jLabel18.setText("Number of MCMC steps");

    jLabel19.setToolTipText(
            "The number of dememorization steps for each batch.");
    jLabel19.setText("Dememorization steps number");

    jLabel20.setToolTipText("The number of batches of MCMC to perform.");
    jLabel20.setText("MCMC batch count number");


    factor11.setText("");
    factor11.addMouseListener(new GENIBD2_factor11_mouseAdapter(this));
    factor12.setText("15");
    factor12.addMouseListener(new GENIBD2_factor12_mouseAdapter(this));
    factor13.setText("150");
    factor13.addMouseListener(new GENIBD2_factor13_mouseAdapter(this));

    jRunButton.addActionListener(this);
    jPanel3.setBorder(titledBorder3);
    jPanel3.setLayout(borderLayout1);
    jPanel2.setLayout(xYLayout3);
    factor21.addMouseListener(new GENIBD2_factor21_mouseAdapter(this));
    factor22.addMouseListener(new GENIBD2_factor22_mouseAdapter(this));
    factor23.addMouseListener(new GENIBD2_factor23_mouseAdapter(this));
    factor14.setText("30");
    factor14.addMouseListener(new GENIBD2_factor14_mouseAdapter(this));
    jRegionComboBox.addMouseListener(new GENIBD2_jRegionComboBox_mouseAdapter(this));
    ibdmode.addMouseListener(new GENIBD2_ibdmode_mouseAdapter(this));
    usesimulation.addMouseListener(new GENIBD2_usesimulation_mouseAdapter(this));
    jPanel5.setLayout(xYLayout4);
    jPanel6.setLayout(xYLayout5);

    factoring.addItemListener(this);
    factoring.addMouseListener(new GENIBD2_factoring_mouseAdapter(this));
    jPanel3.setPreferredSize(new Dimension(290, 150));
    jPanel6.setPreferredSize(new Dimension(290, 25));
    jPanel4.setPreferredSize(new Dimension(290, 110));
    jPanel5.setPreferredSize(new Dimension(290, 25));


    jPanel1.setPreferredSize(new Dimension(275, 90));
    borderLayout1.setVgap(3);
    jTextField2.setText("");
    jTextField2.addMouseListener(new GENIBD2_jTextField2_mouseAdapter(this));
    jLabel11.setToolTipText("Specifies the seed value for the random simulation.");
    jLabel11.setText("Random seed");
    allowloops.setToolTipText("<html>Allows pedigrees with loops to be processed while performing<br>single-point analysis.");
    allowloops.setMargin(new Insets(2, 0, 2, 2));
    allowloops.setText("Allow pedigrees with loops");

    factoring.setToolTipText(
            "<html>Controls whether the MCMC scaling factors are used. If they are not used,"+
            "<br>MCMC sampling uses the same number of steps regardless of pedigree size.");


    factoring.setMargin(new Insets(2, 0, 2, 2));
    factoring.setSelected(true);
    factoring.setText("Use factoring");
    jPanel7.setBorder(titledBorder3);
    jPanel7.setLayout(xYLayout6);
    jLabel16.setEnabled(false);
    jLabel16.setToolTipText("<html>Sets the interval to be used to compute IBD sharing between" +
        "<br>observed markers in centimorgans.");
    jLabel16.setText("Interval size");
    scantype.addItemListener(this);
    jTextField1.setEnabled(false);
    jTextField1.setText("2.0");
    jTextField1.addMouseListener(new GENIBD2_jTextField1_mouseAdapter(this));
    jLabel7.setText("Allow loop");
    scantype.addMouseListener(new GENIBD2_scantype_mouseAdapter(this));
    this.add(jLabel1, new XYConstraints(20, 20, 75, 20));
    this.add(title,    new XYConstraints(155, 20, 320, 20));
    this.add(jLabel6, new XYConstraints(20, 110, 75, 20));
    this.add(jLabel2, new XYConstraints(20, 80, 75, 20));
    this.add(ibdmode,     new XYConstraints(155, 80, 320, 20));
    jPanel1.add(factor12,            new XYConstraints(201, 31, 100, 20));
    jPanel1.add(jLabel12,   new XYConstraints(5, 2, 131, 20));
    jPanel1.add(jLabel13,    new XYConstraints(5, 60, 150, 20));
    jPanel1.add(jLabel14,   new XYConstraints(5, 89, 186, 20));
    jPanel1.add(factor11,                new XYConstraints(201, 2, 100, 20));
    jPanel1.add(factor13,            new XYConstraints(201, 60, 100, 20));
    jPanel1.add(factor14,           new XYConstraints(201, 89, 100, 20));
    jPanel1.add(jLabel17,    new XYConstraints(5, 31, 162, 20));
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(jLabel5, new XYConstraints(20, 50, 75, 20));
    this.add(jRegionComboBox,     new XYConstraints(155, 50, 320, 20));

    jPanel4.add(jPanel1, "true");
    jPanel4.add(jPanel2, "false");

    jPanel3.add(jPanel6, BorderLayout.NORTH);
    jPanel3.add(jPanel4, BorderLayout.CENTER);
    jPanel3.add(jPanel5, BorderLayout.SOUTH);

    jPanel2.add(jLabel18,   new XYConstraints(5, 2, 131, 20));
    jPanel2.add(factor21,           new XYConstraints(201, 2, 100, 20));
    jPanel2.add(jLabel19,    new XYConstraints(5, 31, 161, 20));
    jPanel2.add(factor22,          new XYConstraints(201, 31, 100, 20));
    jPanel2.add(jLabel20,   new XYConstraints(5, 60, 165, 20));
    jPanel2.add(factor23,          new XYConstraints(201, 60, 100, 20));

    jPanel5.add(jTextField2,      new XYConstraints(201, 2, 100, 20));
    jPanel5.add(jLabel11,    new XYConstraints(5, 2, 70, 20));

    jPanel6.add(factoring,                        new XYConstraints(2, 2, 160, 20));

    jPanel7.add(jLabel16,   new XYConstraints(20, 31, 85, 20));
    jPanel7.add(jTextField1,    new XYConstraints(120, 31, 186, 20));
    jPanel7.add(scantype,      new XYConstraints(2, 2, 304, 20));



    this.add(jLabel3,  new XYConstraints(20, 441, 95, 20));//471
    this.add(max_ped,  new XYConstraints(155, 441, 95, 20));
    this.add(jLabel8,  new XYConstraints(260, 441, 75, 20));
    this.add(splitped,  new XYConstraints(380, 441, 95, 20));

    this.add(jLabel7,  new XYConstraints(20, 471, 75, 20));
    this.add(allowloops,  new XYConstraints(155, 471, 285, 20));//501

    this.add(jLabel9,     new XYConstraints(20, 216, 90, 20));
    this.add(usesimulation,     new XYConstraints(155, 216, 320, 20));
    this.add(jPanel7,     new XYConstraints(155, 110, 320, 66));
    this.add(outputpairtype,     new XYConstraints(155, 186, 320, 20));
    this.add(jLabel4,   new XYConstraints(20, 186, 90, 20));
    this.add(jPanel3,      new XYConstraints(155, 246, 320, 185));

    jRegionComboBox.setWidth(320);

    title.getDocument().addDocumentListener(this);
    max_ped.getDocument().addDocumentListener(this);
    outputpairtype.addItemListener(this);
    allowloops.addItemListener(this);
    ibdmode.addItemListener(this);
    splitped.addItemListener(this);
    usesimulation.addItemListener(this);
    factor11.getDocument().addDocumentListener(this);
    factor12.getDocument().addDocumentListener(this);
    factor13.getDocument().addDocumentListener(this);
    factor14.getDocument().addDocumentListener(this);
    factor21.getDocument().addDocumentListener(this);
    factor22.getDocument().addDocumentListener(this);
    factor23.getDocument().addDocumentListener(this);

    Datamodel.setValue("Title", title.getText());
    Datamodel.setValue("Max_ped", max_ped.getText());

    Datamodel.setValue("Output_pair_type",
                       realoutput_pair_types[outputpairtype.getSelectedIndex()]);
    Datamodel.setValue("Scan_type", "markers");
    Datamodel.setValue("Scan_type_value", jTextField1.getText());
    Datamodel.setValue("Allow_loops", "off");
    Datamodel.setValue("IBD_mode", ibdmode.getSelectedItem().toString());
    Datamodel.setValue("Split_ped", "yes");
    Datamodel.setValue("Use_sim", realuse_simulation[usesimulation.getSelectedIndex()]);
    Datamodel.setValue("Use_factoring", "true");
    jTextField2.getDocument().addDocumentListener(this);
    jTextField1.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if (ob == jRunButton)
      jRunButton_actionPerformed(e);
  }

  public void jRunButton_actionPerformed(ActionEvent e) {
    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) genibd1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunGENIBD(treenode, currentnode, e.getActionCommand());
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == title.getDocument()) {
      Datamodel.setValue("Title", title.getText());
      if (title.getText().length() <= 0)
      {
        if (Datamodel.hasValue("Title"))
          Datamodel.removeValue("Title");
      }
    }
    if (document == max_ped.getDocument()) {
      Datamodel.setValue("Max_ped", max_ped.getText());
      if (max_ped.getText().length() <= 0)
      {
        if (Datamodel.hasValue("Max_ped"))
          Datamodel.removeValue("Max_ped");
      }
    }
    if (document == factor11.getDocument()) {
      if (factor11.getText().length() > 0) {
        Datamodel.setValue("factor11", factor11.getText());

        try {
          Integer.parseInt(factor11.getText());
        }
        catch (NumberFormatException en) {
          JOptionPane.showOptionDialog(this.getParent(),
                                       "The base factor field may contain only numeric characters.",
                                       "Error", // title
                                       JOptionPane.CLOSED_OPTION,
                                       JOptionPane.WARNING_MESSAGE, null, null, null);
          jTextField1.requestFocus();
          return;
        }
        int basefactor = Integer.parseInt(factor11.getText());
        int demem_factor = basefactor * 5;
        int sim_factor = basefactor * 10;

        factor12.setText(Integer.toString(demem_factor));
        factor12.setEnabled(false);
        factor13.setText(Integer.toString(sim_factor));
        factor13.setEnabled(false);
        factor14.setText(Integer.toString(basefactor));
        factor14.setEnabled(false);
      }
      else {
        if (Datamodel.hasValue("factor11"))
          Datamodel.removeValue("factor11");
        factor12.setEnabled(true);
        factor13.setEnabled(true);
        factor14.setEnabled(true);
        factor12.setText("15");
        factor13.setText("150");
        factor14.setText("30");
      }
    }
    if (document == factor12.getDocument()) {
      if (factor12.getText().length() > 0)
        Datamodel.setValue("factor12", factor12.getText());
      else
      {
        if (Datamodel.hasValue("factor12"))
          Datamodel.removeValue("factor12");
      }
    }
    if (document == factor13.getDocument()) {
      if (factor13.getText().length() > 0)
        Datamodel.setValue("factor13", factor13.getText());
      else
      {
        if (Datamodel.hasValue("factor13"))
          Datamodel.removeValue("factor13");
      }
    }
    if (document == factor14.getDocument()) {
      if (factor14.getText().length() > 0)
        Datamodel.setValue("factor14", factor14.getText());
      else
      {
        if (Datamodel.hasValue("factor14"))
          Datamodel.removeValue("factor14");
      }
    }

    if (document == factor21.getDocument()) {
      if (factor21.getText().length() > 0)
        Datamodel.setValue("factor21", factor21.getText());
      else
      {
        if (Datamodel.hasValue("factor21"))
          Datamodel.removeValue("factor21");
      }
    }
    if (document == factor22.getDocument()) {
      if (factor22.getText().length() > 0)
        Datamodel.setValue("factor22", factor22.getText());
      else
      {
        if (Datamodel.hasValue("factor22"))
          Datamodel.removeValue("factor22");
      }
    }
    if (document == factor23.getDocument()) {
      if (factor23.getText().length() > 0)
        Datamodel.setValue("factor23", factor23.getText());
      else
      {
        if (Datamodel.hasValue("factor23"))
          Datamodel.removeValue("factor23");
      }
    }

    if (document == jTextField1.getDocument()) {
      if (jTextField1.getText().length() <= 0)
        Datamodel.setValue("Scan_type_value", "2.0");
      else
        Datamodel.setValue("Scan_type_value", jTextField1.getText());
    }

    if (document == jTextField2.getDocument()) {
      if (jTextField2.getText().length() > 0)
      {
        Datamodel.setValue("random_seed", jTextField2.getText());
      }
      else
      {
        if(Datamodel.hasValue("random_seed"))
         Datamodel.removeValue("random_seed");
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
    if (ob == outputpairtype) {
      int index = outputpairtype.getSelectedIndex();
      Datamodel.setValue("Output_pair_type", realoutput_pair_types[index]);
    }
    if (ob == scantype) {
      int index = scantype.getSelectedIndex();
      Datamodel.setValue("Scan_type", realscan_type[index]);
      if (index == 0) {
        jLabel16.setEnabled(false);
        jTextField1.setEnabled(false);
      }
      if (index == 1) {
        jLabel16.setEnabled(true);
        jTextField1.setEnabled(true);
      }
    }
    if (ob == allowloops) {
      if(allowloops.isSelected())
        Datamodel.setValue("Allow_loops","on");
      else
        Datamodel.setValue("Allow_loops","off");
    }
    if (ob == ibdmode) {
        int index = ibdmode.getSelectedIndex();
      Datamodel.setValue("IBD_mode", realibd_mode[index]);

      if(!genibd1.Analysis_object.create_genome_file_node)//exist genome error node
      {
        if (ibdmode.getSelectedIndex() == 0) { //multipoint
          genibd1.errorF_node.add(genibd1.errorgenome_node);
          genibd1.treeModel.nodeStructureChanged(genibd1.errorF_node);
        }
        else if (ibdmode.getSelectedIndex() == 1) { //singlepoint
          genibd1.removeErrorGenomenode();
        }
      }
    }
    if (ob == splitped) {
      int index = splitped.getSelectedIndex();
      if (index == 0)
        Datamodel.setValue("Split_ped", "yes");
      else
        Datamodel.setValue("Split_ped", splitped.getSelectedItem().toString());
    }
    if (ob == usesimulation) {
      Datamodel.setValue("Use_sim",realuse_simulation[usesimulation.getSelectedIndex()]);
      int option = usesimulation.getSelectedIndex();
      switch(option)
      {
        case 0: //true
        case 2: //always
          Set_Use_Simulation_Block(true);
          break;
        case 1: //false
          Set_Use_Simulation_Block(false);
          break;
      }
    }
    if (ob == factoring) {
      CardLayout cl = (CardLayout) (jPanel4.getLayout());
      if(factoring.isSelected())
      {
        cl.show(jPanel4, "true");
        Datamodel.setValue("Use_factoring", "true");
      }
      else
      {
        cl.show(jPanel4, "false");
        Datamodel.setValue("Use_factoring", "false");
      }
    }

  }

  void Set_Use_Simulation_Block(boolean value)
  {
    jLabel12.setEnabled(value);
    jLabel13.setEnabled(value);
    jLabel14.setEnabled(value);
    jLabel17.setEnabled(value);
    jLabel18.setEnabled(value);
    jLabel19.setEnabled(value);
    jLabel20.setEnabled(value);

    jLabel11.setEnabled(value);
    jTextField2.setEnabled(value);

    factor11.setEnabled(value);
    factor12.setEnabled(value);
    factor13.setEnabled(value);
    factor14.setEnabled(value);
    factor21.setEnabled(value);
    factor22.setEnabled(value);
    factor23.setEnabled(value);
    factoring.setEnabled(value);
  }

  void title_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("titl", false, 208);
  }

  void jRegionComboBox_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("region", false, 209);
  }

  void outputpairtype_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("output_pair_type", false, 209);
  }

  void max_ped_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("max_pedigre", false, 209);
  }

  void scantype_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("scan_typ", false, 209);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("distance", false, 209);
  }

  void allowloops_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("allow_loops", false, 209);
  }

  void ibdmode_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("ibd_mod", false, 210);
  }

  void splitped_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("split_pedigrees", false, 210);
  }

  void usesimulation_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("use_simulatio", false, 210);
 }

  void factoring_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("use_factoring ", false, 211);
  }

  void factor11_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("base_factor  ", false, 211);
  }

  void factor12_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("demem_facto", false, 211);
  }

  void factor13_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sim_factor", false, 211);
  }

  void factor14_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sim_batch_factor", false, 212);
  }

  void factor21_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("sim_steps", false, 212);
  }

  void factor22_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("demem_steps", false, 212);
  }

  void factor23_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("batch_coun", false, 212);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("random_seed", false, 211);
  }
}

class GENIBD2_title_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_title_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.title_mouseClicked(e);
  }
}

class GENIBD2_jRegionComboBox_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_jRegionComboBox_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRegionComboBox_mouseClicked(e);
  }
}

class GENIBD2_ibdmode_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_ibdmode_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.ibdmode_mouseClicked(e);
  }
}

class GENIBD2_max_ped_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_max_ped_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.max_ped_mouseClicked(e);
  }
}

class GENIBD2_outputpairtype_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_outputpairtype_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.outputpairtype_mouseClicked(e);
  }
}

class GENIBD2_splitped_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_splitped_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.splitped_mouseClicked(e);
  }
}

class GENIBD2_allowloops_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_allowloops_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.allowloops_mouseClicked(e);
  }
}

class GENIBD2_usesimulation_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_usesimulation_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.usesimulation_mouseClicked(e);
  }
}
class GENIBD2_factoring_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_factoring_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.factoring_mouseClicked(e);
  }
}

class GENIBD2_factor11_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_factor11_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.factor11_mouseClicked(e);
  }
}

class GENIBD2_factor12_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_factor12_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.factor12_mouseClicked(e);
  }
}

class GENIBD2_factor13_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_factor13_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.factor13_mouseClicked(e);
  }
}

class GENIBD2_factor14_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_factor14_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.factor14_mouseClicked(e);
  }
}

class GENIBD2_factor21_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_factor21_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.factor21_mouseClicked(e);
  }
}

class GENIBD2_factor22_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_factor22_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.factor22_mouseClicked(e);
  }
}

class GENIBD2_factor23_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_factor23_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.factor23_mouseClicked(e);
  }
}

class GENIBD2_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_jTextField2_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class GENIBD2_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_jTextField1_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class GENIBD2_scantype_mouseAdapter extends java.awt.event.MouseAdapter {
  GENIBD2 adaptee;

  GENIBD2_scantype_mouseAdapter(GENIBD2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.scantype_mouseClicked(e);
  }
}
