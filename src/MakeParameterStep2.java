package sage;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.text.JTextComponent;

public class MakeParameterStep2 extends WizardPanel implements ActionListener{
  JButton DetailButton;
  JLabel jLabel1 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  Vector rowdata;
  Vector columnname;
  static String[] columnNames_all;
  static int real_col_size;
  static int real_row_size;

  MyTable jTable1;
  Dialog1 dialog1;
  Dialog2_Trait dialog2_trait;
  Dialog3_Cov dialog3_cov;
  Dialog5_Marker dialog5_marker;
  Dialog6_Allele dialog6_allele;
  Dialog7_TMarker dialog7_tmarker;
  Dialog5_Marker_sub marker_sub;

  DataCollectionModel DataModel;
  static DefaultTableModel dm;

  static int marker_count=1;
  static int trait_count=1;
  static int phenotype_count=1;
  static int covariate_count=1;
  static int allele_count=1;
  static int trait_marker_count=1;
  String liststring[] = {"Unspecified", "Pedigree ID", "Individual ID",
                        "Parent1", "Parent2", "SEX", "TRAIT", "COVARIATE",
                        "MARKER", "ALLELE", "TRAIT MARKER", "TEXT"};

  JComboBox comboBox = new JComboBox(liststring);
  ComboBoxRenderer renderer = new ComboBoxRenderer();

  int tableindex=-1;

  public MakeParameterStep2()
  {
       super("Specification",
             "Set each column's name, type of variable and specific properties."
             +"\nThe individual identifier (ID) field is required for all records.");

       dm = new DefaultTableModel();

       jTable1= new MyTable(){
           public void changeSelection(int row, int column, boolean toggle, boolean extend) {
               super.changeSelection(row, column, toggle, extend);
               if (editCellAt(row, column))
                   getEditorComponent().requestFocusInWindow();
           }

           public boolean xxxeditCellAt(int row, int column, EventObject e) {
               boolean result = super.editCellAt(row, column, e);
               final Component editor = getEditorComponent();

               if (editor != null && editor instanceof JTextComponent) {
                   if (e == null) {
                       ((JTextComponent) editor).selectAll();
                   }
                   else {
                       SwingUtilities.invokeLater(new Runnable() {
                           public void run() {
                               ((JTextComponent) editor).selectAll();
                           }
                       });
                   }
               }
               return result;
           }

           public Component xxxprepareEditor(TableCellEditor editor, int row, int column) {
               Component comp = super.prepareEditor(editor, row, column);
                  if (comp instanceof JTextComponent) {
                   ((JTextField) comp).selectAll();
               }
               return comp;
           }
       };

       dialog1 = new Dialog1("Variable Characteristics", this);
       dialog2_trait = new Dialog2_Trait("Variable Characteristics");
       dialog3_cov = new Dialog3_Cov("Variable Characteristics");
       dialog5_marker = new Dialog5_Marker("Variable Characteristics");
       dialog6_allele = new Dialog6_Allele("Variable Characteristics");
       dialog7_tmarker = new Dialog7_TMarker("Variable Characteristics");
       marker_sub = new Dialog5_Marker_sub(Frame1.mainFrame1, "Marker Information", true);
       marker_sub.setLocationRelativeTo(JWizardProject.wizardProject);

       JPanel top = new JPanel();
       DetailButton = new JButton("General Specifications");
       DetailButton.setPreferredSize(new Dimension(150,25));

       DetailButton.setIcon(new ImageIcon(MakeParameterStep2.class.getResource("Missing.png")));
       DetailButton.setMargin(new Insets(2,2,2,2));
       DetailButton.addActionListener(this);

       JLabel line = new JLabel();
       line.setBorder(BorderFactory.createEtchedBorder());
       line.setText("");
       line.setPreferredSize(new Dimension(550,2));

       JLabel temp = new JLabel();
       temp.setText("");
       temp.setPreferredSize(new Dimension(250, 20));

       top.setLayout(new BorderLayout(5,10));
       top.add(DetailButton, BorderLayout.WEST);
       top.add(temp, BorderLayout.CENTER);
       top.add(line, BorderLayout.SOUTH);
       top.setBorder(new EmptyBorder(5,8,0,8));

       jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

       JPanel middle = new JPanel();
       middle.setLayout(new BorderLayout());
       middle.add(jScrollPane1, BorderLayout.CENTER);

       middle.setPreferredSize(new Dimension(550,230));
       middle.setBorder(new EmptyBorder(5,8,8,8));

       JPanel bottom = new JPanel();
       bottom.setPreferredSize(new Dimension(550, 5));

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());
       panel.add("North",top);
       panel.add("Center",middle);
       panel.add("South", bottom);

       add("Center", panel);

       comboBox.addActionListener(this);
       comboBox.setRenderer(renderer);
   }

   public void setDataModelValue() {

       String petName = (String) comboBox.getSelectedItem();

       int sindex = jTable1.getSelectedColumn();

       String HeaderName = new String();
       String restcols = new String();

       EditableHeader header = (EditableHeader) jTable1.getTableHeader();

       if (sindex < 0) {
           return;
       } else {
           HeaderName = header.getColumnModel().getColumn(sindex).getHeaderValue().toString();
           restcols = String.valueOf(Math.abs(real_col_size - sindex - 1));
           if (sindex == 499)
               restcols = String.valueOf(0);
       }

       if (!petName.equals("Unspecified")) {
           if (petName.compareTo("Individual ID") == 0) {
               if (getDataModel().hasValue("pedigree" + HeaderName)) {
                   String val = getDataModel().getValue("pedigree" + HeaderName).toString();
                   getDataModel().removeValue(val);
               }
               getDataModel().setValue("pedigree.individual_id", HeaderName);
               getDataModel().setValue("pedigree_id_name", HeaderName);
               getDataModel().setValue("pedigree" + HeaderName, "pedigree.individual_id");
           }

           if (petName.compareTo("TRAIT") == 0) {
               if (getDataModel().hasValue("pedigree" + HeaderName))
                   getDataModel().removeValue("pedigree" + HeaderName);

               make_trait_default(HeaderName, petName, sindex, restcols);
           }

           else if (petName.compareTo("COVARIATE") == 0) {
               if (getDataModel().hasValue("pedigree" + HeaderName))
                   getDataModel().removeValue("pedigree" + HeaderName);

               make_covariate_default(HeaderName, petName, sindex, restcols);
           }
           else if (petName.compareTo("ALLELE") == 0) {
               if (getDataModel().hasValue("pedigree" + HeaderName))
                   getDataModel().removeValue("pedigree" + HeaderName);

               make_allele_default(HeaderName, petName, sindex, restcols);
           }
           else if (petName.compareTo("MARKER") == 0) {
               if (getDataModel().hasValue("pedigree" + HeaderName))
                   getDataModel().removeValue("pedigree" + HeaderName);

               marker_sub.setVisible(true);
               if (marker_sub.ok_clicked) {
                   if (marker_sub.jRadioButton2.isSelected()) //noncodominant marker
                       dialog5_marker.ADelimiter.setEnabled(false);
                   else //codominant marker
                       dialog5_marker.ADelimiter.setEnabled(true);
               }
               make_marker_block(HeaderName, petName, sindex, restcols);
           }
           else if (petName.compareTo("TRAIT MARKER") == 0) {
               if (getDataModel().hasValue("pedigree" + HeaderName))
                   getDataModel().removeValue("pedigree" + HeaderName);

               make_traitmarker_default(HeaderName, petName, sindex, restcols);
           }

           else if (petName.compareTo("TEXT") == 0) {
               if (getDataModel().hasValue("pedigree" + HeaderName))
                   getDataModel().removeValue("pedigree" + HeaderName);

               DataModel.setValue("pedigree" + HeaderName, petName);
               DataModel.setValue("pedigree" + HeaderName + ".Hname",
                                  HeaderName);
           }
       }

       if (getDataModel().hasValue("pedigree.individual_id")
               && getDataModel().hasValue("detail")) {
           canMoveForward = true;
       }
       else {
           canMoveForward = false;
       }

       ((PropertyDataModel) getDataModel()).fireChangeEvent();
       JWizardProject.wizard.nav.validate();

   }

  public void actionPerformed(ActionEvent event)
   {
       Object ob = event.getSource();
       if (ob == DetailButton) {
           dialog1.showDialog();
       }
       if (ob == comboBox) {
           setDataModelValue();
       }


       if (getDataModel().hasValue("pedigree.individual_id")
           && getDataModel().hasValue("detail")) {
           canMoveForward = true;
       } else {
           canMoveForward = false;
       }

       ((PropertyDataModel) getDataModel()).fireChangeEvent();
       JWizardProject.wizard.nav.validate();

   }

   void make_traitmarker_default(String HeaderName, String petName, int columnindex, String defaultcols)
   {
     if (trait_count == 1) {
       dialog7_tmarker.SetDataModel(DataModel, HeaderName, petName, columnindex);
    }
     else {
       dialog7_tmarker.SetDefaultData(DataModel, HeaderName, petName, columnindex);
     }

     dialog7_tmarker.jTextField1.setText(defaultcols);

     SwingUtilities.invokeLater(new Runnable()
     {
       public void run()
       {
           dialog7_tmarker.showDialog();
       }
     });

   }

   void make_trait_default(String HeaderName, String petName, int columnindex, String defaultcols)
   {
     if (trait_count == 1) {
       dialog2_trait.SetDataModel(DataModel, HeaderName, petName, columnindex);
    }
     else {
       dialog2_trait.SetDefaultData(DataModel, HeaderName, petName, columnindex);
     }

      dialog2_trait.applyTextField.setText(defaultcols);

    if(DataModel.hasValue("pedigree"+HeaderName))
    {
      String name = "";
      if(DataModel.hasValue("pedigree"+HeaderName+".Aname"))
        name = DataModel.getValue("pedigree"+HeaderName+".Aname").toString();
      dialog2_trait.Name.setText(name);

      String type = "";
      if(DataModel.hasValue("pedigree"+HeaderName+".type"))
        type = DataModel.getValue("pedigree"+HeaderName+".type").toString();
      if(type.compareTo("binary")==0)
      {
        dialog2_trait.jBinaryCheckBox.setSelected(true);

        if(DataModel.hasValue("pedigree"+HeaderName+".binary_affected"))
          dialog2_trait.Affected.setText(DataModel.getValue("pedigree"+HeaderName+".binary_affected").toString());

        if(DataModel.hasValue("pedigree"+HeaderName+".binary_unaffected"))
          dialog2_trait.UnAffected.setText(DataModel.getValue("pedigree"+HeaderName+".binary_unaffected").toString());
      }
      else if(type.compareTo("continuous")==0)
      {
        dialog2_trait.jContinueCheckBox.setSelected(true);
      }
      else if (type.compareTo("categorical") == 0) {
        dialog2_trait.cateCheckBox.setSelected(true);
      }

      if(DataModel.hasValue("pedigree"+HeaderName+".missing"))
      {
        String tempmissingvalue = DataModel.getValue("pedigree"+HeaderName+".missing").toString();

        if(tempmissingvalue.compareTo(".")==0)
        {
          dialog2_trait.missingComboBox.setSelectedIndex(0);
          dialog2_trait.MissingValue.setText("");
        }
        if(tempmissingvalue.compareTo("")==0)
        {
          dialog2_trait.missingComboBox.setSelectedIndex(1);
          dialog2_trait.MissingValue.setText("");
        }
        if(tempmissingvalue.compareTo(" ")==0)
        {
          dialog2_trait.missingComboBox.setSelectedIndex(2);
          dialog2_trait.MissingValue.setText(" ");
        }
        if(tempmissingvalue.compareTo("\t")==0)
        {
          dialog2_trait.missingComboBox.setSelectedIndex(3);
          dialog2_trait.MissingValue.setText("");
        }
        else
        {
            dialog2_trait.missingComboBox.setSelectedIndex(4);
            dialog2_trait.MissingValue.setText(DataModel.getValue("pedigree"+HeaderName+".missing").toString());
        }
      }
    }

      SwingUtilities.invokeLater(new Runnable()
      {
        public void run()
        {
            dialog2_trait.showDialog();
        }
      });
   }

   void make_covariate_default(String HeaderName, String petName, int columnindex, String defaultcols)
   {
     if (covariate_count == 1) {
       dialog3_cov.SetDataModel(DataModel, HeaderName, petName, columnindex);
     }
     else {
       dialog3_cov.SetDefaultData(DataModel, HeaderName, petName, columnindex);
     }

     dialog3_cov.applyTextField.setText(defaultcols);

     if (DataModel.hasValue("pedigree" + HeaderName)) {
       String name = "";
       if(DataModel.hasValue("pedigree"+HeaderName+".Aname"))
         name = DataModel.getValue("pedigree"+HeaderName+".Aname").toString();
       dialog3_cov.Name.setText(name);

       String type = "";
       if(DataModel.hasValue("pedigree"+HeaderName+".type"))
         type = DataModel.getValue("pedigree"+HeaderName+".type").toString();
       if (type.compareTo("binary") == 0) {
         dialog3_cov.jBinaryCheckBox.setSelected(true);

         if (DataModel.hasValue("pedigree" + HeaderName + ".binary_affected"))
           dialog3_cov.Affected.setText(DataModel.getValue("pedigree" +
               HeaderName + ".binary_affected").toString());

         if (DataModel.hasValue("pedigree" + HeaderName + ".binary_unaffected"))
           dialog3_cov.UnAffected.setText(DataModel.getValue("pedigree" +
               HeaderName + ".binary_unaffected").toString());
       }
       else if (type.compareTo("continuous") == 0) {
         dialog3_cov.jContinueCheckBox.setSelected(true);
       }
       else if (type.compareTo("categorical") == 0) {
         dialog3_cov.cateCheckBox.setSelected(true);
       }
     }

     if(DataModel.hasValue("pedigree"+HeaderName+".missing"))
     {
       String tempmissingvalue = DataModel.getValue("pedigree"+HeaderName+".missing").toString();

       if(tempmissingvalue.compareTo(".")==0)
         dialog3_cov.missingComboBox.setSelectedIndex(0);
       if(tempmissingvalue.compareTo("")==0)
         dialog3_cov.missingComboBox.setSelectedIndex(1);
       else if(tempmissingvalue.compareTo(" ")==0)
         dialog3_cov.missingComboBox.setSelectedIndex(2);
       else if(tempmissingvalue.compareTo("\t")==0)
         dialog3_cov.missingComboBox.setSelectedIndex(3);
       else
       {
           dialog3_cov.missingComboBox.setSelectedIndex(4);
           dialog3_cov.MissingValue.setText(DataModel.getValue("pedigree"+HeaderName+".missing").toString());
       }
     }

     SwingUtilities.invokeLater(new Runnable()
     {
       public void run()
       {
             dialog3_cov.showDialog();
       }
     });
   }

  void make_allele_default(String HeaderName, String petName, int columnindex, String defaultcols)
  {
    if(allele_count==1)
    {
     dialog6_allele.SetDataModel(DataModel, HeaderName, petName, columnindex);
    }
    else
    {
      dialog6_allele.SetDefaultData(DataModel, HeaderName, petName, columnindex);
    }
    dialog6_allele.jTextField1.setText(defaultcols);

    if (DataModel.hasValue("pedigree" + HeaderName))
    {
      String name = "";
      if(DataModel.hasValue("pedigree"+HeaderName+".Aname"))
        name = DataModel.getValue("pedigree"+HeaderName+".Aname").toString();
      dialog6_allele.Name.setText(name);

      if (DataModel.hasValue("pedigree" + HeaderName +".allele_missing"))
      {
        String missingvalue = DataModel.getValue("pedigree" + HeaderName +
                                                 ".allele_missing").toString();
        if(missingvalue.compareTo(".")==0)
          dialog6_allele.jComboBox1.setSelectedIndex(0);
        else if(missingvalue.compareTo("")==0)
          dialog6_allele.jComboBox1.setSelectedIndex(1);
        else if(missingvalue.compareTo(" ")==0)
          dialog6_allele.jComboBox1.setSelectedIndex(2);
        else if(missingvalue.compareTo("\t")==0)
          dialog6_allele.jComboBox1.setSelectedIndex(3);
        else
        {
          dialog6_allele.jComboBox1.setSelectedIndex(4);
          dialog6_allele.AMissing.setText(missingvalue);
        }
      }
      if (DataModel.hasValue("pedigree" + HeaderName +".allele_frequency.minimum"))
      {
        dialog6_allele.minCheckBox.setSelected(true);
        dialog6_allele.Minimum.setText(DataModel.getValue("pedigree" +
            HeaderName + ".allele_frequency.minimum").toString());
        dialog6_allele.Maximum.setText(DataModel.getValue("pedigree" +
            HeaderName + ".allele_frequency.maximum").toString());
      }
      if (DataModel.hasValue("pedigree" + HeaderName +".allele_frequency.complement"))
      {
        dialog6_allele.jCheckComplement.setSelected(true);
      }
      if (DataModel.hasValue("pedigree" + HeaderName +".allele_frequency.equal"))
      {
        dialog6_allele.jCheckEqual.setSelected(true);
      }

      if (DataModel.hasValue("pedigree" + HeaderName +".useascov")) {

        if (DataModel.hasValue("pedigree" + HeaderName +".useascov.covmode"))
        {
          dialog5_marker.covmodeComboBox.setSelectedItem(DataModel.getValue("pedigree" + HeaderName +".useascov.covmode").toString());
        }
        if (DataModel.hasValue("pedigree" + HeaderName +".useascov.covallele"))
        {
            dialog5_marker.covalleleComboBox.setText(DataModel.getValue("pedigree" + HeaderName +".useascov.covallele").toString());
        }

        if (DataModel.hasValue("pedigree" + HeaderName +".useascov.hemizygote")) {
            if (DataModel.getValue("pedigree" + HeaderName +".useascov.hemizygote").toString().compareTo("true") == 0) {
                dialog5_marker.hemizygoteCheckBox.setSelected(true);
            }
          }
      }
    }

    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
          dialog6_allele.showDialog();
      }
    });
  }

  void make_marker_block(String HeaderName, String petName, int columnindex, String defaultcols)
  {
    if(marker_count==1)
    {
      dialog5_marker.SetDataModel(DataModel, HeaderName, petName, columnindex);
    }
    else
    {
      dialog5_marker.SetDefaultData(DataModel, HeaderName, petName, columnindex);
    }

    dialog5_marker.jTextField1.setText(defaultcols);

    if (DataModel.hasValue("pedigree" + HeaderName))
    {
      String name = "";
      if(DataModel.hasValue("pedigree"+HeaderName+".Aname"))
        name = DataModel.getValue("pedigree"+HeaderName+".Aname").toString();
      dialog5_marker.Name.setText(name);

      if (DataModel.hasValue("pedigree" + HeaderName + ".allele_delimiter"))
        dialog5_marker.ADelimiter.setText(DataModel.getValue("pedigree" +
            HeaderName + ".allele_delimiter").toString());

      if (DataModel.hasValue("pedigree" + HeaderName +".allele_missing"))
      {
        String missingvalue = DataModel.getValue("pedigree" + HeaderName +
                                                 ".allele_missing").toString();

        if(missingvalue.compareTo(".")==0)
          dialog5_marker.jComboBox1.setSelectedIndex(0);
        else if(missingvalue.compareTo("")==0)
          dialog5_marker.jComboBox1.setSelectedIndex(1);
        else if(missingvalue.compareTo(" ")==0)
          dialog5_marker.jComboBox1.setSelectedIndex(2);
        else if(missingvalue.compareTo("\t")==0)
          dialog5_marker.jComboBox1.setSelectedIndex(3);
        else
        {
          dialog5_marker.jComboBox1.setSelectedIndex(4);
          dialog5_marker.AMissing.setText(missingvalue);
        }
      }
      if (DataModel.hasValue("pedigree" + HeaderName +".allele_frequency.minimum"))
      {
        dialog5_marker.minCheckBox.setSelected(true);
        dialog5_marker.Minimum.setText(DataModel.getValue("pedigree" +
            HeaderName + ".allele_frequency.minimum").toString());
        dialog5_marker.Maximum.setText(DataModel.getValue("pedigree" +
            HeaderName + ".allele_frequency.maximum").toString());
      }
      if (DataModel.hasValue("pedigree" + HeaderName +".allele_frequency.complement"))
      {
        dialog5_marker.jCheckComplement.setSelected(true);
      }
      if (DataModel.hasValue("pedigree" + HeaderName +".allele_frequency.equal"))
      {
        dialog5_marker.jCheckEqual.setSelected(true);
      }

      if (DataModel.hasValue("pedigree" + HeaderName +".useascov")) {

        if (DataModel.hasValue("pedigree" + HeaderName +".useascov.covmode"))
        {
          dialog5_marker.covmodeComboBox.setSelectedItem(DataModel.getValue("pedigree" + HeaderName +".useascov.covmode").toString());
        }
        if (DataModel.hasValue("pedigree" + HeaderName +".useascov.covallele"))
        {
            dialog5_marker.covalleleComboBox.setText(DataModel.getValue("pedigree" + HeaderName +".useascov.covallele").toString());
        }

        if (DataModel.hasValue("pedigree" + HeaderName +".useascov.hemizygote")) {
            if (DataModel.getValue("pedigree" + HeaderName +".useascov.hemizygote").toString().compareTo("true") == 0) {
                dialog5_marker.hemizygoteCheckBox.setSelected(true);
            }
          }
      }
    }

    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
          dialog5_marker.showDialog();
      }
    });
  }

  void set_table_data(Vector data, Vector col, boolean col_limit, int real_col_size, int real_row_size, String[] columnNames_all)
  {
    this.real_col_size = real_col_size;
    this.real_row_size = real_row_size;
    this.rowdata = data;
    this.columnname = col;
    this.columnNames_all = columnNames_all;
    dm.setDataVector(this.rowdata, this.columnname);

    DataModel = (DataCollectionModel)getDataModel();

    dialog1.SetDataModel(DataModel);

    jTable1.renderer = true;
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jTable1.setColumnSelectionAllowed(true);
    jTable1.setIntercellSpacing(new Dimension(2, 0));
    jTable1.setRowSelectionAllowed(true);
    jTable1.setModel(dm);

    EachRowEditor rowEditor = new EachRowEditor(jTable1, real_col_size);
    rowEditor.setEditorAt(0, new DefaultCellEditor(comboBox));

    for (int i = 0; i < this.columnname.size(); i++) {
      if(real_col_size <= 500)
      {
        jTable1.getColumn(this.columnname.get(i)).setCellEditor(rowEditor);
      }
      else
      {
          if(i<498 || i == 499)
            jTable1.getColumn(this.columnname.get(i)).setCellEditor(rowEditor);
      }
      jTable1.getColumn(this.columnname.get(i)).setPreferredWidth(80);
    }

    TableColumnModel columnModel = jTable1.getColumnModel();
    EditableHeader h = new EditableHeader(columnModel);
    jTable1.setTableHeader(h);

    jScrollPane1.getViewport().add(jTable1, null);

    JWizardProject.wizardProject.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    if (col_limit) {
        String message = new String();

        message = "Your data file contains " + real_col_size + " columns.\n"
                      + "Only 500 columns are displayed.";

        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                     message,
                                     "Warning",
                                     JOptionPane.CLOSED_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null, null, null);
        return;
    }
  }
  void clear_table_data()
  {
    if(this.rowdata != null)
    {
      this.rowdata.removeAllElements();
      this.rowdata.trimToSize();
    }
    if(this.columnname != null)
    {
      this.columnname.removeAllElements();
      this.columnname.trimToSize();
    }
  }
}
