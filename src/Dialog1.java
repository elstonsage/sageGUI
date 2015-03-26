package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class Dialog1 extends JDialog implements ActionListener, ItemListener{
    JPanel panel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jTopPanel = new JPanel();
    JPanel jCenterPanel = new JPanel();
    JPanel jBottomPanel = new JPanel();
    EdgeBorder lineborder = new EdgeBorder();
    JLabel jLabel1 = new JLabel();
    XYLayout xYLayout1 = new XYLayout();
    JTextField jTextField1 = new JTextField();
    JPanel jCenterSexPanel = new JPanel();
    XYLayout xYLayout2 = new XYLayout();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    JTextField jTextField4 = new JTextField();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    TitledBorder titledBorder1;
    TitledBorder titledBorder2;
    JButton OKButton = new JButton();
    JButton CancelButton = new JButton();
  JCheckBox jCheckBox1 = new JCheckBox();
  XYLayout xYLayout4 = new XYLayout();
  JLabel title = new JLabel();

  DataCollectionModel DataModel;
  JLabel jLabel5 = new JLabel();

  String[] missingoption = {"Dot (\".\")","Blank (\"\")","Space (\" \")","Tab (\"\\t\")","Other"};
  JComboBox jComboBox1 = new JComboBox(missingoption);
  FlowLayout flowLayout1 = new FlowLayout();
    JComboBox jComboBox2 = new JComboBox(missingoption);
    JCheckBox jCheckBox3 = new JCheckBox();
    JCheckBox jCheckBox2 = new JCheckBox();
    BorderLayout borderLayout2 = new BorderLayout();

    MakeParameterStep2 parent;

    public void showDialog() {
    this.setLocationRelativeTo(Frame1.mainFrame1);
     this.setVisible(true);
 }

  public Dialog1(String title, MakeParameterStep2 parent) {
      super(Frame1.mainFrame1, title, false);
      this.setTitle(title);
      this.parent = parent;
      try {
          jbInit();
          pack();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder("Sex Code");
        titledBorder2 = new TitledBorder("");
        panel1.setLayout(borderLayout1);
        title.setBorder(new EmptyBorder(0,20,0,0));
        this.setSize(new Dimension(365, 380));

        jCheckBox1.setFocusPainted(false);
        jCheckBox2.setFocusPainted(false);
        jCheckBox3.setFocusPainted(false);

        jTopPanel.setPreferredSize(new Dimension(365, 40));
        jTopPanel.setLayout(borderLayout2);
        jTopPanel.setBackground(Color.white);
        jCenterPanel.setLayout(xYLayout1);
        jCenterPanel.setPreferredSize(new Dimension(365,300));
        jCenterPanel.setBorder(lineborder);
        jBottomPanel.setBorder(lineborder);
        jBottomPanel.setPreferredSize(new Dimension(365, 40));
        jBottomPanel.setLayout(flowLayout1);

        jLabel1.setText("Individual missing value ");
        jTextField1.setEnabled(false);
        jTextField1.setText("");
        jTextField1.setFocusable(true);
        jCenterSexPanel.setLayout(xYLayout2);
        jTextField2.setText("M");

        jTextField3.setText("F");
        jLabel4.setText("Missing");
        jLabel2.setText("Male ");
        jLabel3.setText("Female ");
        jCenterSexPanel.setBorder(titledBorder1);
        OKButton.setPreferredSize(new Dimension(60, 25));
        OKButton.setText("OK");
        OKButton.addActionListener(this);
        CancelButton.setPreferredSize(new Dimension(60, 25));
        CancelButton.setMargin(new Insets(2, 2, 2, 2));
        CancelButton.setText("Cancel");
        CancelButton.setMargin(new Insets(2, 2, 2, 2));
        CancelButton.addActionListener(this);
        jCheckBox1.setText("Missing sex information for some individuals");

        title.setText("Fill in the fields below as they apply to the pedigree data.");
        jLabel5.setText("(applies to Pedigree ID, Individual ID, parent1 and parent2 fields)");
        jTextField4.setEnabled(false);
        jTextField4.setText("");
        jCheckBox3.setText(
                "Treat individuals who share the same pedigree ID as full siblings");
        jCheckBox2.setText(
                "The sex field is deliberately absent from the data file");
        getContentPane().add(panel1);
        jTopPanel.add(title,  BorderLayout.CENTER);
        jCenterPanel.add(jLabel1,          new XYConstraints(20, 15, -1, 20));
        jCenterPanel.add(jLabel5,      new XYConstraints(20, 40, -1, 20));
        panel1.add(jBottomPanel, BorderLayout.SOUTH);
        jBottomPanel.add(OKButton, null);
        jBottomPanel.add(CancelButton, null);
        panel1.add(jCenterPanel, java.awt.BorderLayout.CENTER);
        panel1.add(jTopPanel, java.awt.BorderLayout.NORTH);
        jCenterSexPanel.add(jLabel2, new XYConstraints(30, 3, 50, 20));
        jCenterSexPanel.add(jLabel3, new XYConstraints(30, 33, 50, 20));
        jCenterSexPanel.add(jLabel4, new XYConstraints(30, 63, 50, 20));
        jCenterSexPanel.add(jCheckBox2, new XYConstraints(15, 130, 280, 20));
        jCenterSexPanel.add(jCheckBox1, new XYConstraints(15, 100, 270, 20));
        jCenterPanel.add(jCheckBox3, new XYConstraints(15, 265, 350, 20));
        jCenterPanel.add(jComboBox1, new XYConstraints(145, 15, 100, 20));
        jCenterPanel.add(jTextField1, new XYConstraints(255, 15, 70, 20));
        jCenterSexPanel.add(jTextField2, new XYConstraints(120, 3, 180, 20));
        jCenterSexPanel.add(jTextField3, new XYConstraints(120, 33, 180, 20));
        jCenterPanel.add(jCenterSexPanel, new XYConstraints(20, 70, 325, 188));
        jCenterSexPanel.add(jComboBox2, new XYConstraints(120, 63, 100, 20));
        jCenterSexPanel.add(jTextField4, new XYConstraints(230, 63, 70, 20));
        jComboBox1.addItemListener(this);
        jComboBox2.addItemListener(this);
        jCheckBox1.addItemListener(this);
        jCheckBox2.addItemListener(this);
        jCheckBox3.addItemListener(this);
    }

    public void SetDataModel(DataCollectionModel DM) {
        DataModel = DM;
        DataModel.setValue("pedigree.individual_missing_value", ".");
        DataModel.setValue("pedigree.sex_code.male", jTextField2.getText());
        DataModel.setValue("pedigree.sex_code.female", jTextField3.getText());
        DataModel.setValue("pedigree.sex_code.unknown", ".");
    }

    public void actionPerformed(ActionEvent e) {
      Object o = e.getSource();

      if (o == OKButton) {
        switch(jComboBox1.getSelectedIndex())
        {
          case 0:
            DataModel.setValue("pedigree.individual_missing_value", ".");
            break;
          case 1:
            DataModel.setValue("pedigree.individual_missing_value", "");
            break;
          case 2:
            DataModel.setValue("pedigree.individual_missing_value", " ");
            break;
          case 3:
            DataModel.setValue("pedigree.individual_missing_value", "\t");
            break;
          case 4:
            DataModel.setValue("pedigree.individual_missing_value",jTextField1.getText());
            break;
        }

        switch(jComboBox2.getSelectedIndex())
        {
          case 0:
            DataModel.setValue("pedigree.sex_code.unknown", ".");
            break;
          case 1:
            DataModel.setValue("pedigree.sex_code.unknown", "");
            break;
          case 2:
            DataModel.setValue("pedigree.sex_code.unknown", " ");
            break;
          case 3:
            DataModel.setValue("pedigree.sex_code.unknown", "\t");
            break;
          case 4:
            DataModel.setValue("pedigree.sex_code.unknown",jTextField4.getText());
            break;
        }

        DataModel.setValue("pedigree.sex_code.male", jTextField2.getText());
        DataModel.setValue("pedigree.sex_code.female", jTextField3.getText());
        DataModel.setValue("pedigree.sex_code_trait", "deafult");

        setVisible(false);
        DataModel.setValue("detail","set");
      }

      else if (o == CancelButton) {
        setVisible(false);
        if(DataModel.hasValue("detail"))
            DataModel.removeValue("detail");
      }

      if (DataModel.hasValue("pedigree.individual_id") &&
          DataModel.hasValue("detail")) {
          parent.canMoveForward = true;
      } else {
          parent.canMoveForward = false;
      }

      ((PropertyDataModel)parent.getDataModel()).fireChangeEvent();
       JWizardProject.wizard.nav.validate();
    }

    public void itemStateChanged(ItemEvent e) {
      Object ob = e.getSource();
      if (ob == jCheckBox1)
      {
          if (jCheckBox1.isSelected())
              DataModel.setValue("pedigree.no_sex_ok", "Yes");
          else {
              if (DataModel.hasValue("pedigree.no_sex_ok"))
                  DataModel.removeValue("pedigree.no_sex_ok");
          }
      }
      if(ob == jCheckBox2)
      {
          if (jCheckBox2.isSelected())
          {
              DataModel.setValue("pedigree.no_sex_field", "true");

              jTextField2.setEnabled(false);
              jTextField3.setEnabled(false);
              jTextField4.setEnabled(false);
              jComboBox2.setEnabled(false);
              jCheckBox1.setEnabled(false);
          }
          else
          {
              if (DataModel.hasValue("pedigree.no_sex_field"))
                  DataModel.removeValue("pedigree.no_sex_field");

              jTextField2.setEnabled(true);
              jTextField3.setEnabled(true);
              if(jComboBox2.getSelectedIndex()==3)
                  jTextField4.setEnabled(true);
              jComboBox2.setEnabled(true);
              jCheckBox1.setEnabled(true);
          }
      }
      else if(ob == jCheckBox3)
      {
          if (jCheckBox3.isSelected())
              DataModel.setValue("pedigree.treat_as_sibs", "true");
          else
          {
              if (DataModel.hasValue("pedigree.treat_as_sibs"))
                  DataModel.removeValue("pedigree.treat_as_sibs");
          }
      }
      else if(ob == jComboBox1)
      {
        switch(jComboBox1.getSelectedIndex())
        {
        case 0:
          DataModel.setValue("pedigree.individual_missing_value", ".");
          jTextField1.setEnabled(false);
          break;
          case 1:
            DataModel.setValue("pedigree.individual_missing_value", "");
            jTextField1.setEnabled(false);
            break;
          case 2:
            DataModel.setValue("pedigree.individual_missing_value", " ");
            jTextField1.setEnabled(false);
            break;
          case 3:
            DataModel.setValue("pedigree.individual_missing_value", "\t");
            jTextField1.setEnabled(false);
            break;
          case 4:
            jTextField1.setEnabled(true);
            jTextField1.requestFocus(true);
            break;
        }
      }
      else if(ob == jComboBox2)
      {
        switch(jComboBox2.getSelectedIndex())
        {
        case 0:
          DataModel.setValue("pedigree.sex_code.unknown", ".");
          jTextField4.setEnabled(false);
          break;
          case 1:
            DataModel.setValue("pedigree.sex_code.unknown", "");
            jTextField4.setEnabled(false);
            break;
          case 2:
            DataModel.setValue("pedigree.sex_code.unknown", " ");
            jTextField4.setEnabled(false);
            break;
          case 3:
            DataModel.setValue("pedigree.sex_code.unknown", "\t");
            jTextField4.setEnabled(false);
            break;
          case 4:
            jTextField4.setEnabled(true);
            jTextField4.requestFocus(true);
            break;
        }
      }

      this.repaint();

    }
}
