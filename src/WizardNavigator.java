package sage;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.table.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.awt.Cursor;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WizardNavigator extends JPanel implements ActionListener, ChangeListener {
  JButton back, next, cancel;
  Icon backIcon, nextIcon;
  DeckPanel deck;
  boolean cancel_clicked = false;
  Vector column = new Vector();
  Vector linelist = new Vector();

  String[] columnNames_all;

  int row_limit = 200;
  int col_limit = 500;
  int column_size = 0;

  String header_format = new String();

  Workbook  newworkbook;
  FileOutputStream out;
  boolean close = false;

  String project_id;
  String project_name;
  String project_path;
  String makepath;

  boolean writePara = false;
  boolean writePedi = false;
  boolean step1 = false;
  boolean step2 = false;
  boolean step3 = false;
  boolean IsExcel = false;

  boolean multi_pedi_block = false;
  Vector multi_pedi_list = new Vector();

  int pedigreefile_count = 0;
  Vector internal_pedilist = new Vector();
  Vector external_pedilist = new Vector();
  int parameterfile_count = 0;
  int traitfile_count=0;
  Vector paralist = new Vector();
  Vector traitlist = new Vector();

  NodeInfo innodeinfo = new NodeInfo("Internal", "Internal", "Internal","Internal");
  IconNode internalnode = new IconNode(innodeinfo, "Internal");
  NodeInfo outnodeinfo = new NodeInfo("External", "External", "External","External");
  IconNode externalnode = new IconNode(outnodeinfo, "External");

  File temp_pedigree_file;
  File temp_parameter_file;
  File temp_trait_file;
  IconNode temp_internal_pedi;
  IconNode temp_external_pedi;
  IconNode temp_internal_trait;
  NodeInfo temp_trait_blocknode;

  String external_pedi_filepath;
  Vector pid_list = new Vector();
  Dialog1_AllIndep ai = new Dialog1_AllIndep(Frame1.mainFrame1, "Warning!", true);

  private String AAA =
          "Individuals are all assigned the same dummy pedigree id (and are therefore assumed"
          + " to belong to the same sibship). Please note that any analyses that depend"
          + " on sex-specific data may produce unpredictable results.";

  private String AAP =
          "Individuals are all assigned the same dummy pedigree id.";

  private String APA =
          "Individuals are all assigned the same dummy pedigree id. Since the presence"
          + " of parent id fields indicates that the pedigree file contains lineage information, the"
          + " subsequent absence of a sex field renders S.A.G.E. unable to infer the sex of a parent"
          + " in any parent-offspring relationship. For analyses that rely on sex-specific lineages,"
          + " this situation may produce unpredictable results. Consequently, the user must explicitly"
          + " acknowledge this situation by including the no_sex_field parameter in the pedigree block."
          + " If the parameter is not included, the pedigree will be considered invalid."
          + " If it is included, S.A.G.E. will produce a warning reminding the user of the"
          + " situation and suppress subsequent warnings about sex-indeterminate lineages.  ";

  private String APP =
            "Individuals are all assigned the same dummy pedigree id.";

  private String PAA =
          "If the user has included the optional treat_as_sibs parameter, individuals"
          + " sharing the same pedigree id are assigned the same dummy parent ids, thereby"
          + " making those individuals full sibs.";

  private String PAP =
          "If the user has included the optional treat_as_sibs parameter, individuals"
          + " sharing the same pedigree id are assigned the same dummy parent ids, thereby "
          + " making those individuals full sibs.";

  private String PPA =
          "Since the presence of parent id fields indicates that the pedigree contains lineage"
          + " information, the subsequent absence of a sex field renders S.A.G.E. unable to infer"
          + " the sex of a parent in any parent-offspring relationship. For analyses that rely on"
          + " sexspecific lineages, this situation may produce unpredictable results. Consequently,"
          + " the user must explicitly acknowledge this situation by including the no_sex_field"
          + " parameter in the pedigree block. If the parameter is not included, the pedigree"
          + " will be considered invalid. If it is included, S.A.G.E. will produce a warning"
          + " reminding the user of the situation and suppress subsequent warnings about"
          + " sex-indeterminate lineages.";


  public WizardNavigator(DeckPanel deck) throws Exception{
    this.deck = deck;
    setOpaque(true);
    setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    setBorder(new EdgeBorder(EdgeBorder.NORTH));
    JPanel nav = new JPanel();
    nav.setLayout(new BorderLayout());
    backIcon = new ImageIcon(WizardNavigator.class.getResource("WestArrow.gif"));
    nav.add("West", back = new JButton("Back", backIcon));
    back.setMargin(new Insets(1, 5, 1, 10));
    back.setPreferredSize(new Dimension(75, 22));
    back.addActionListener(this);
    nextIcon = new ImageIcon(WizardNavigator.class.getResource("EastArrow.gif"));
    nav.add("East", next = new JButton("Next", nextIcon));
    next.setHorizontalTextPosition(JButton.LEFT);
    next.setMargin(new Insets(1, 1, 1, 1));
    next.setPreferredSize(new Dimension(75, 22));
    next.addActionListener(this);
    add(nav);
    add(cancel = new JButton("Cancel"));
    cancel.setPreferredSize(new Dimension(75, 22));
    cancel.addActionListener(this);
  }

  public void actionPerformed(java.awt.event.ActionEvent event) {
    Object source = event.getSource();


    if (source == cancel) {
      int confirm = JOptionPane.showInternalOptionDialog(JWizardProject.wizardProject,
          "Warning!" +
          "\nClosing the window before completing the project wizard will leave some required options unspecified." +
          "\nDo you wish to close the window?",
          "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
          null, null, null);

      if (confirm == 0) {
        Frame1.desktop.remove(JWizardProject.wizardProject);
        Frame1.desktop.updateUI();
        JWizardProject.frame.jMenuFile.setEnabled(true);
        JWizardProject.frame.jMenuView.setEnabled(true);
        JWizardProject.frame.jMenuHelp.setEnabled(true);
        JWizardProject.frame.m_windowMenu.setEnabled(true);
      }
    }

    if (source == back) {

      if (deck.getPrevious().compareTo("makepedi2") == 0) {
        JWizardProject.wizardProject.setTitle("Set Familial Relationships");
        Frame1.mainFrame1.repaint();
      }
      else if (deck.getPrevious().compareTo("makepara2") == 0) {
        JWizardProject.wizardProject.setTitle("Set Pedigree Field Properties");
        Frame1.mainFrame1.repaint();
      }
      else {
        JWizardProject.wizardProject.setTitle("New Project");
      }

      if (deck.getPrevious().compareTo("makepedi1") == 0) {
        paralist.removeElementAt(parameterfile_count);
        external_pedilist.removeElementAt(pedigreefile_count);
        internal_pedilist.removeElementAt(pedigreefile_count);
        multi_pedi_list.removeElementAt(parameterfile_count);
        removeDataValue();
      }
      if (deck.getPrevious().compareTo("makepara1") == 0) {
        paralist.removeElementAt(parameterfile_count);
        external_pedilist.removeElementAt(pedigreefile_count);
        internal_pedilist.removeElementAt(pedigreefile_count);
        multi_pedi_list.removeElementAt(parameterfile_count);
        removeDataValue();
        MakeParameterStep2 m = (MakeParameterStep2) deck.getComponent("makepara2");
        m.canMoveForward = false;
        paintAll(getGraphics());
      }

      if (deck.getPrevious().compareTo("makepedi4")==0) {
        if(multi_pedi_block)
        {
          pedigreefile_count--;
          parameterfile_count--;
        }

         if (traitfile_count >= 1)
         {
           traitfile_count--;
           traitlist.remove(temp_trait_blocknode);
           traitlist.trimToSize();
           internalnode.remove(temp_internal_trait);
           temp_trait_file.delete();
         }

         if(temp_external_pedi!=null)
           externalnode.remove(temp_external_pedi);
         ((JWizard) getParent()).getModel().setValue("externalnode",externalnode);

         if(temp_internal_pedi!=null)
           internalnode.remove(temp_internal_pedi);
         ((JWizard) getParent()).getModel().setValue("internalnode",internalnode);

         temp_pedigree_file.delete();
         temp_parameter_file.delete();
      }

      setPanel(deck.getPrevious());
    }
    if (source == next) {

      if (next.getText().compareTo("Finish") == 0) {
        Finish_Wizard();
      }

      // add by me
      if (deck.getNext().compareTo("makepedi2") == 0) {
        JWizardProject.wizardProject.setTitle("Set Familial Relationships");
        Frame1.mainFrame1.repaint();
      }
      else if (deck.getNext().compareTo("makepara2") == 0) {
        JWizardProject.wizardProject.setTitle("Set Pedigree Field Properties");
        Frame1.mainFrame1.repaint();
        if(multi_pedi_block)
          removeDataValue();
      }
      else {
        JWizardProject.wizardProject.setTitle("New Project");
      }

      if (deck.getCurrent().compareTo("selection") == 0) {
        Select_Job();
      }

      if (deck.getCurrent().compareTo("favorites") == 0) {
        ResultPanel r = (ResultPanel) deck.getComponent("result");
        r.setInformation();
      }

      if (deck.getCurrent().compareTo("info")==0) {
        project_path = ( (JWizard) getParent()).getModel().getValue("info.DirPath").toString();
        project_name = ( (JWizard) getParent()).getModel().getValue("info.pName").toString();

        File pp = new File(project_path);
        File[] files = pp.listFiles();
        for (int i = 0; i < files.length; i++) {
          if (files[i].isDirectory()) {
            if (files[i].getName().compareTo(project_name) == 0) {
              int n = JOptionPane.showOptionDialog(this.getParent(),
                  "The project named '" + project_name + "' already exists."
                  +"\nYou must rename the project or specify a different location. ",
                  "Project name Error",
                  JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
              return;
            }
          }
        }

        makepath = project_path + project_name + System.getProperty("file.separator");
      }

      if (deck.getCurrent().compareTo("makepara1") == 0) {
        final MakeParameterStep1 m = (MakeParameterStep1) deck.getComponent("makepara1");
        final MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");

        final String filepath = m.pName.getText();
        m.getDataModel().setValue("external_pedigree_filepath2", filepath);

        JWizardProject.wizardProject.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        m2.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker worker = new SwingWorker() {
            public Object construct() {
                try {
                    if (m.jRadioButton1.isSelected())
                        Gen_Table_Data_fromText(filepath);
                    else if (m.jRadioButton3.isSelected())
                    {
                        Workbook wb=null;
                        if(filepath.endsWith(".xlsx"))
                        {
                            wb = new XSSFWorkbook(new FileInputStream(filepath));
                        }
                        else
                        {
                            wb = new HSSFWorkbook(new FileInputStream(filepath));
                        }

                        newworkbook = wb;
                        Gen_Table_Data_fromExcel(wb);
                    }
                } catch (Exception excep) {
                    excep.printStackTrace();
                    JOptionPane.showOptionDialog(JWizardProject.
                            wizardProject,
                            "I/O exception encountered while attempting to read file '" +
                            filepath +
                            "'.\nPlease check the file for correct formatting, file attributes, " +
                            "\nuser access privileges, possible data corruption, etc. ",
                            "Error",
                            JOptionPane.CLOSED_OPTION,
                            JOptionPane.WARNING_MESSAGE, null, null, null);
                }
                return "done";
            }
            public void finished() {
                JWizardProject.wizardProject.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                m2.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        };
        worker.start();

        paralist.add(parameterfile_count, init_wizard_vector());
        external_pedilist.add(pedigreefile_count, "");
        internal_pedilist.add(pedigreefile_count, "");
        multi_pedi_list.add(pedigreefile_count,new java.lang.Boolean(false));
      }

      if (deck.getCurrent().compareTo("makepedi1") == 0) {
        MakePedigreeStep1 mp = (MakePedigreeStep1) deck.getComponent("makepedi1");
        MakePedigreeStep2 mp2 = (MakePedigreeStep2) deck.getComponent("makepedi2");

        String message = "To use this option, the first column of your data file must contain"
                         +"\nthe pedigree identifier (PID) field, and the second column"
                         +"\nmust contain the individual identifier (ID) field.";

        JOptionPane.showMessageDialog(this.getParent(),
                                     message,
                                     "Warning",
                                     JOptionPane.WARNING_MESSAGE);

        final String filepath = mp.pName.getText();
        mp.getDataModel().setValue("external_pedigree_filepath1", filepath);

        boolean header = false;
        if (mp.jCheck1.isSelected())
          header = true;

        JWizardProject.wizardProject.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        mp2.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        if (mp.jRadioButton1.isSelected()) {//text file
          Runnable lookupData = new Runnable() {
            public void run() {
              try {
                Make_RelationshipTable_Data();
              }
              catch (Exception excel) {
                excel.printStackTrace();
                JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                "I/O exception encountered while attempting to read file '"+filepath+
                "'.\nPlease check the file for correct formatting, file attributes, "+
                "\nuser access privileges, possible data corruption, etc. ",
                      "Error",
                      JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                 return;
              }
            }
          };

          Thread lookupThread = new Thread(lookupData, "MyThread");
          lookupThread.start();
        }
        else if (mp.jRadioButton3.isSelected()) { //Excel file
          try{
            mp2.init_table_data_fromExcel(mp.pName.getText(), header);
          }catch(Exception ex)
          {
            JOptionPane.showOptionDialog(JWizardProject.wizardProject,
            "I/O exception encountered while attempting to read file '"+filepath+
            "'.\nPlease check the file for correct formatting, file attributes, "+
            "\nuser access privileges, possible data corruption, etc. ",
                  "Error",
                  JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
             return;
          }
        }
        paralist.add(parameterfile_count, init_wizard_vector());
        external_pedilist.add(pedigreefile_count, "");
        internal_pedilist.add(pedigreefile_count, "");
        multi_pedi_list.add(pedigreefile_count,new java.lang.Boolean(false));
      }

      if (deck.getCurrent().compareTo("makepara2") == 0) {

        MakeParameterStep1 m = (MakeParameterStep1) deck.getComponent("makepara1");
        MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");
        MakePedigreeStep4 m4 = (MakePedigreeStep4) deck.getComponent("makepedi4");
        DataCollectionModel dm = (DataCollectionModel) m2.getDataModel();

        boolean missing_required = false;

        MyTable table = m2.jTable1;

        int col_size = table.getColumnCount();
        EditableHeader header = (EditableHeader)  table.getTableHeader();
        String HeaderName="";
        String petName="";

        for(int i=0;i<col_size;i++)
        {
            HeaderName = header.getColumnModel().getColumn(i).getHeaderValue().toString();
            petName = table.getValueAt(0, i).toString();

            if(petName.equals("Unspecified"))
            {
                if(m2.getDataModel().hasValue("pedigree" + HeaderName))
                {
                    String val = m2.getDataModel().getValue("pedigree" + HeaderName).toString();
                    m2.getDataModel().removeValue(val);
                }
            }

            if (!petName.equals("Unspecified")) {
                if (petName.compareTo("Pedigree ID") == 0) {
                    m2.getDataModel().setValue("pedigree.pedigree_id", HeaderName);
                    m2.getDataModel().setValue("pedigree.pedigree_id_columncount", Integer.toString(i));
                    m2.getDataModel().setValue("pedigree" + HeaderName, "pedigree.pedigree_id");
                }
                else if (petName.compareTo("Individual ID") == 0) {
                    m2.getDataModel().setValue("pedigree.individual_id", HeaderName);
                    m2.getDataModel().setValue("pedigree_id_name", HeaderName);
                    m2.getDataModel().setValue("pedigree" + HeaderName, "pedigree.individual_id");
                }
                else if (petName.compareTo("Parent1") == 0) {
                    m2.getDataModel().setValue("pedigree.parent_id1", HeaderName);
                    m2.getDataModel().setValue("pedigree" + HeaderName, "pedigree.parent_id1");
                }
                else if (petName.compareTo("Parent2") == 0) {
                    m2.getDataModel().setValue("pedigree.parent_id2", HeaderName);
                    m2.getDataModel().setValue("pedigree" + HeaderName, "pedigree.parent_id2");
                }
                else if (petName.compareTo("SEX") == 0) {
                    m2.getDataModel().setValue("pedigree.sex_field", HeaderName);
                    m2.getDataModel().setValue("pedigree" + HeaderName, "pedigree.sex_field");
                }
                else if (petName.compareTo("TRAIT") == 0) {
                    m2.getDataModel().setValue("pedigree"+HeaderName, "TRAIT");
                }
                else if (petName.compareTo("COVARIATE") == 0) {
                    m2.getDataModel().setValue("pedigree"+HeaderName, "COVARIATE");
                }
                else if (petName.compareTo("ALLELE") == 0) {
                    m2.getDataModel().setValue("pedigree" + HeaderName, "ALLELE");
                }
                else if (petName.compareTo("MARKER") == 0) {
                    m2.getDataModel().setValue("pedigree" + HeaderName, "MARKER");
                }
                else if (petName.compareTo("TRAIT MARKER") == 0) {
                    m2.getDataModel().setValue("pedigree" + HeaderName,"TRAIT MARKER");
                }
                else if (petName.compareTo("TEXT") == 0) {
                    m2.getDataModel().setValue("pedigree" + HeaderName, petName);
                    m2.getDataModel().setValue("pedigree" + HeaderName + ".Hname",HeaderName);
                }
            }
        }

        if ((dm.hasValue("pedigree.pedigree_id") == true))
        {
            if (dm.hasValue("pedigree.parent_id1") == true
                && dm.hasValue("pedigree.parent_id2") == true) {
                if (dm.hasValue("pedigree.sex_field") == false) {
                    ai.jLabel_PID.setText("Present");
                    ai.jLabel_PaID.setText("Present");
                    ai.jLabel_Sex.setText("Absent");
                    ai.jTextPane1.setText(PPA);
                    missing_required = true;
                }
            }
            else {
                if (dm.hasValue("pedigree.sex_field") == false) {
                    ai.jLabel_PID.setText("Present");
                    ai.jLabel_PaID.setText("Absent");
                    ai.jLabel_Sex.setText("Absent");
                    ai.jTextPane1.setText(PAA);
                    missing_required = true;
                }
                if (dm.hasValue("pedigree.sex_field") == true) {

                    ai.jLabel_PID.setText("Present");
                    ai.jLabel_PaID.setText("Absent");
                    ai.jLabel_Sex.setText("Present");
                    ai.jTextPane1.setText(PAP);
                    missing_required = true;
                }
            }
        }
        else if ((dm.hasValue("pedigree.pedigree_id") == false))
        {
            if (dm.hasValue("pedigree.parent_id1") == true
                && dm.hasValue("pedigree.parent_id2") == true)
            {
                if (dm.hasValue("pedigree.sex_field") == false) {
                    ai.jLabel_PID.setText("Absent");
                    ai.jLabel_PaID.setText("Present");
                    ai.jLabel_Sex.setText("Absent");
                    ai.jTextPane1.setText(APA);
                    missing_required = true;
                }

                if (dm.hasValue("pedigree.sex_field") == true) {
                    ai.jLabel_PID.setText("Absent");
                    ai.jLabel_PaID.setText("Present");
                    ai.jLabel_Sex.setText("Present");
                    ai.jTextPane1.setText(APP);
                    missing_required = true;
                }
            }
            else
            {
                if (dm.hasValue("pedigree.sex_field") == false) {
                    ai.jLabel_PID.setText("Absent");
                    ai.jLabel_PaID.setText("Absent");
                    ai.jLabel_Sex.setText("Absent");
                    ai.jTextPane1.setText(AAA);
                    missing_required = true;
                }
                if (dm.hasValue("pedigree.sex_field") == true) {
                    ai.jLabel_PID.setText("Absent");
                    ai.jLabel_PaID.setText("Absent");
                    ai.jLabel_Sex.setText("Present");
                    ai.jTextPane1.setText(AAP);
                    missing_required = true;
                }
            }

        }

        if(missing_required)
        {
            ai.setLocationRelativeTo(JWizardProject.wizardProject);
            ai.setVisible(true);

            if (ai.option == 2)
                 return;
        }

        m4.pName.setText("pedigree" + pedigreefile_count+".dat");
        m4.jRadioButton4.setSelected(true);

        if (!m.jCheck1.isSelected()) {
          dm.setValue("pedigree.header", "add");

          header_format = "format=\"";

          for (int i = 0; i < table.getColumnCount(); i++) {
            header_format = header_format + table.getColumnName(i) + ",";
          }

          header_format = header_format.substring(0, header_format.length() - 1);
          header_format = header_format + "\"";
        }

        TableColumnModel tcm = header.getColumnModel();

        for (int i = 0; i < table.getColumnCount(); i++) {
          for (int j = i + 1; j < table.getColumnCount(); j++) {
            String cname1 = tcm.getColumn(i).getHeaderValue().toString();
            String cname2 = tcm.getColumn(j).getHeaderValue().toString();

            if (cname1.compareTo(cname2) == 0) {
              JOptionPane.showOptionDialog(this.getParent(),
                                           "Please use different name for column " +
                                           (i + 1) + " and " + (j + 1) + ".",
                                           "Error", // title
                                           JOptionPane.CLOSED_OPTION,
                                           JOptionPane.WARNING_MESSAGE, null, null, null);

              table.changeSelection(1, i + 3, true, true);
              return;
            }
          }
        }

        Vector temp_para=null;
        try {
          temp_para = Write_Parameter_Block();
        }
        catch (Exception exe) {
          exe.printStackTrace();
          JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                       "I/O exception encountered while attempting to write parameter file. "+
                                       "\nReported error number is '" + exe +"'",
                                       "Error",
                                       JOptionPane.CLOSED_OPTION,
                                       JOptionPane.WARNING_MESSAGE, null, null, null);
          return;
        }

        paralist.set(parameterfile_count, temp_para);
      }

      else if (deck.getCurrent().compareTo("makepedi2") == 0) {
        MakePedigreeStep2 m2 = (MakePedigreeStep2) deck.getComponent("makepedi2");
        MakePedigreeStep1 m = (MakePedigreeStep1) deck.getComponent("makepedi1");

        try {
            newworkbook = m2.resultworkbook;
            out = m2.out;

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        close = true;

        try {
            MakeParameterStep2 mp2 = (MakeParameterStep2) deck.getComponent("makepara2");

            mp2.clear_table_data();

            Sheet newsheet = newworkbook.getSheetAt(0);

            boolean isHeaderExist = false;
            if (m.jCheck1.isSelected())
            {
              m.getDataModel().setValue("pedigree.HeaderExist","header");
              isHeaderExist = true;
            }

            if(m.getDataModel().hasValue("pedigree.HeaderExist"))
            {
              isHeaderExist = true;
            }

            String delimiter = "\t";

            m2.getDataModel().setValue("pedigree.delimiters", delimiter);
            m2.getDataModel().setValue("pedigree.delimiter_mode", "multiple");

            Make_TableData_Excel(isHeaderExist, newsheet);
    }
        catch (Exception e) {
          JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                "I/O exception encountered while attempting to write pedigree data file"+
                "\nReported error number is '" + e+"'",
                "Error",
                JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
           return;
        }
      }

      else if (deck.getCurrent().compareTo("makepedi4") == 0) {
        MakePedigreeStep4 m4 = (MakePedigreeStep4) deck.getComponent("makepedi4");
        MakeParameterStep1 m1 = (MakeParameterStep1) deck.getComponent("makepara1");
        MakePedigreeStep1 mp = (MakePedigreeStep1) deck.getComponent("makepedi1");
        MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");

        if (step1)
          external_pedi_filepath = mp.getDataModel().getValue("external_pedigree_filepath1").toString();
        if (step2)
          external_pedi_filepath = mp.getDataModel().getValue("external_pedigree_filepath2").toString();

        if (m4.pName.getText().length() <= 0) {
          JOptionPane.showInternalOptionDialog(this.getParent(),
              "You must specify a name for the pedigree data file.",
              "Error",
              JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
          return;
        }

        if (step1) {
          if (m4.jRadioButton3.isSelected()) {
            m4.getSequenceManager().setNext("makepedi4", "makepedi1");
            m2.canMoveForward = false;
            m2.jTable1.changeSelection(1, 0, true, true);
            multi_pedi_block = true;
          }
          else {
            m4.getSequenceManager().setNext("makepedi4", "makepara3");
            m4.getSequenceManager().setNext("makepara3", "result");
            m4.getSequenceManager().setNext("result", "");
            multi_pedi_block = false;
          }
          mp.init_state();
        }

        if (step2) {
          if (m4.jRadioButton3.isSelected()) {
            m4.getSequenceManager().setNext("makepedi4", "makepara1");
            m2.canMoveForward = false;
            m2.jTable1.changeSelection(1, 0, true, true);
            multi_pedi_block = true;
          }
          else {
            m4.getSequenceManager().setNext("makepedi4", "makepara3");
            m4.getSequenceManager().setNext("makepara3", "result");
            m4.getSequenceManager().setNext("result", "");
            multi_pedi_block = false;
          }
          m1.init_state();
        }

        multi_pedi_list.add(pedigreefile_count, new Boolean(multi_pedi_block));

        Frame1.mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        JWizardProject.wizardProject.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        Runnable lookupData = new Runnable() {
          public void run() {
            try {
              ProgressDialog pd = new ProgressDialog("Warning");
              pd.showDialog();

              String temp_pedigree_filepath = Write_Pedigree_File(external_pedi_filepath, pd);

              multi_pedi_list.set(pedigreefile_count, new Boolean(multi_pedi_block));

              external_pedilist.setElementAt(external_pedi_filepath, pedigreefile_count);
              internal_pedilist.setElementAt(temp_pedigree_filepath, pedigreefile_count);

              if(multi_pedi_block)
                parameterfile_count++;

              if(multi_pedi_block)
                pedigreefile_count++;

              pd.jButton1.setEnabled(true);
              pd.jButton1_actionPerformed();

              Frame1.mainFrame1.setCursor(null);
              JWizardProject.wizardProject.setCursor(null);

            } catch (Exception exe) {
              exe.printStackTrace();
              JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                           "I/O exception encountered while attempting to write pedigree data file" +
                                           "\nReported error number is '" + exe +
                                           "'",
                                           "Error",
                                           JOptionPane.CLOSED_OPTION,
                                           JOptionPane.WARNING_MESSAGE, null, null, null);
              return;
            }
          }
        };

        Thread lookupThread = new Thread(lookupData, "MyThread");
        lookupThread.start();
      }

      else if (deck.getCurrent().compareTo("makepara3") == 0) {
        MakeParameterStep3 m3 = (MakeParameterStep3) deck.getComponent("makepara3");

        if (m3.pName.getText().length() <= 0) {
          JOptionPane.showInternalOptionDialog(this.getParent(),
              "You must specify a name for the parameter file.",
              "Error", // title
              JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
          return;
        }

        try {
          Write_Parameter_File();
        }
        catch (Exception e) {
            e.printStackTrace();
          JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                "I/O exception encountered while attempting to write parameter file. "+
                "\nReported error number is '" + e +"'",
                "Error",
                JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
           return;
       }

        ResultPanel r = (ResultPanel) deck.getComponent("result");
        r.setInformation();
      }

      setPanel(deck.getNext());
    }

    JWizardProject.wizard.updateUI();
    JWizardProject.wizard.repaint();

    this.repaint();
    this.updateUI();
  }

  public void stateChanged(ChangeEvent event) {
    checkValidation(deck.getCurrent());
  }

  public void checkValidation(String name) {
    if (deck.getComponent(name) instanceof WizardValidator) {
      WizardValidator validator = ( (WizardValidator) deck.getComponent(name));
      next.setEnabled(validator.canMoveForward());
      back.setEnabled(validator.canMoveBackward());
      paintAll(getGraphics());
    }
  }

  public void setPanel(String name) {
    deck.setPanel(name);
    checkValidation(name);
    back.setVisible(!deck.getFirst().equals(name));
    back.setEnabled(!deck.getFirst().equals(name));
    if (deck.isLast(name)) {
      next.setText("Finish");
      next.setIcon(null);
    }
    else {
      next.setText("Next");
      next.setIcon(nextIcon);
    }
    paintAll(getGraphics());
  }

  public void Finish_Wizard() {
    if (close) {
      try {
        out.close();
        MakePedigreeStep2 m2 = (MakePedigreeStep2) deck.getComponent("makepedi2");
        m2.tempfile.deleteOnExit();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    File project = new File(makepath);
    try {
      project.mkdir();
    }
    catch (SecurityException x) {
      JOptionPane.showOptionDialog(JWizardProject.wizardProject,
            "Security exception encountered while attempting to create project directory. "+
            "\nReported error number is '" + x +"'",
            "Error",
            JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
      return;
    }

    ((JWizard) getParent()).getModel().setValue("info.DirPath", makepath);

    for(int i=0; i< internalnode.getChildCount();i++)
    {
      IconNode child = (IconNode)internalnode.getChildAt(i);
      NodeInfo child_nodeinfo = (NodeInfo)child.getUserObject();
      File child_file = child_nodeinfo.file;
      String child_filepath = child_file.getPath();

      int nameindex = child_filepath.lastIndexOf(System.getProperty("file.separator"));
      String child_filename = child_filepath.substring(nameindex + 1,child_filepath.length());

      File newfile = new File(makepath+child_filename);
      MyInternalFrame.CopyFile(child_file, newfile);
      child_nodeinfo.file = newfile;
      child_file.delete();
    }

    temp_pedigree_file=null;
    temp_parameter_file=null;

    MakeParameterStep2.marker_count = 1;
    MakeParameterStep2.trait_count = 1;
    MakeParameterStep2.phenotype_count = 1;
    MakeParameterStep2.covariate_count = 1;
    MakeParameterStep2.allele_count = 1;
    MakeParameterStep2.trait_marker_count = 1;

    Frame1.desktop.remove(JWizardProject.wizardProject);
    Frame1.desktop.updateUI();
    DataCollectionModel dm = JWizardProject.wizard.getModel();
    JWizardProject.frame.Make_NewProjectFrame(dm, makepath);
  }

  public void Select_Job() {
    SelectJobPanel sj = (SelectJobPanel) deck.getComponent("selection");

    DataCollectionModel dm = (DataCollectionModel) sj.getDataModel();
    dm.setValue("internalnode", internalnode);

    if (sj.jRadioButton1.isSelected()) { // Create pedigree file from row data
      sj.getSequenceManager().setNext("selection", "makepedi1");
      sj.getSequenceManager().setNext("makepedi1", "makepedi2");
      sj.getSequenceManager().setNext("makepedi2", "makepara2");
      sj.getSequenceManager().setNext("makepara2", "makepedi4");
      step1 = true;
      step2 = false;
      step3 = false;
    }

    else if (sj.jRadioButton2.isSelected()) { // Create parameter file from pedigree file
      sj.getSequenceManager().setNext("selection", "makepara1");
      sj.getSequenceManager().setNext("makepara1", "makepara2");
      sj.getSequenceManager().setNext("makepara2", "makepedi4");
      step1 = false;
      step2 = true;
      step3 = false;
    }

    else if (sj.jRadioButton3.isSelected()) { // Create new analysis
      sj.getSequenceManager().setNext("selection", "favorites");
      sj.getSequenceManager().setNext("favorites", "result");
      sj.getSequenceManager().setNext("result", "");
      step1 = false;
      step2 = false;
      step3 = true;
    }
  }

  public Vector init_wizard_vector()
  {
    Vector list = new Vector();
    list.add(0, new Boolean(true));
    for(int i=1;i<10;i++)
    {
      list.add(i, "init");
    }
    return list;
  }

  public void Make_RelationshipTable_Data() throws Exception {
    MakePedigreeStep1 mp = (MakePedigreeStep1) deck.getComponent("makepedi1");
    MakePedigreeStep2 mp2 = (MakePedigreeStep2) deck.getComponent("makepedi2");

    boolean header = false;
    if (mp.jCheck1.isSelected())
      header = true;

    String source_file_path = mp.pName.getText();
    String delimiter = new String();

    if (mp.tab.isSelected())
      delimiter = "\t";
    else if (mp.comma.isSelected())
      delimiter = ",";
    else if (mp.space.isSelected())
      delimiter = " ";
    else if (mp.other.isSelected())
      delimiter = mp.delimiter.getText();

    mp.getDataModel().setValue("pedigree.delimiters", delimiter);
    if (mp.single.isSelected())
      mp.getDataModel().setValue("pedigree.delimiter_mode", "single");
    else
      mp.getDataModel().setValue("pedigree.delimiter_mode", "multiple");

    boolean isHeaderExist = false;
    if (mp.jCheck1.isSelected())
      isHeaderExist = true;

    File tempfile = File.createTempFile("temp", "xls");

    FileOutputStream out = new FileOutputStream(tempfile);
    Workbook wb = new HSSFWorkbook();
    Sheet s = wb.createSheet();
    Row r = null;
    Cell c = null;

    Vector temp_column = new Vector();
    Vector temp_linelist = new Vector();

    if (mp.single.isSelected()) {
      int i = 0;

      FileReader fr = new FileReader(source_file_path);
      BufferedReader br = new BufferedReader(fr);
      String temp = new String();
      while ( (temp = br.readLine()) != null) {
        String[] t = temp.split(delimiter);
        if (isHeaderExist) {
          if (i == 0) {
            for (int ti = 0; ti < t.length; ti++) {
              temp_column.add(t[ti]);
            }
          }
          else if (i > 0) {
            Vector eachlinecontents = new Vector();
            for (int ti = 0; ti < t.length; ti++) {
              eachlinecontents.add(t[ti]);
            }
            column_size = eachlinecontents.size();
            temp_linelist.add(eachlinecontents);
          }
          i++;
        }
        else {
          Vector eachlinecontents = new Vector();
          for (int ti = 0; ti < t.length; ti++) {
            eachlinecontents.add(t[ti]);
          }

          column_size = eachlinecontents.size();
          temp_linelist.add(eachlinecontents);
        }
      }
    }
    else if (mp.multiple.isSelected()) {
      int i = 0;

      FileReader fr = new FileReader(source_file_path);
      BufferedReader br = new BufferedReader(fr);
      String temp = new String();
      while ( (temp = br.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(temp, delimiter);
        if (isHeaderExist) {
          if (i == 0) {
            while (st.hasMoreTokens()) {
              temp_column.add(st.nextToken());
            }
          }
          else if (i > 0) {
            Vector eachlinecontents = new Vector();
            while (st.hasMoreTokens()) {
              eachlinecontents.add(st.nextToken());
            }
            column_size = eachlinecontents.size();
            temp_linelist.add(eachlinecontents);
          }
          i++;
        }
        else {
          Vector eachlinecontents = new Vector();
          while (st.hasMoreTokens()) {
            eachlinecontents.add(st.nextToken());
          }
          column_size = eachlinecontents.size();
          temp_linelist.add(eachlinecontents);
        }
      }
    }

    Collections.sort(temp_linelist, new PedigreeComparator(0, true));

    if (isHeaderExist) {
        for(int k=0;k<temp_column.size();k++) {
          c = r.createCell(k);
          c.setCellValue(temp_column.get(k).toString());
        }
    }

    for(int i=0;i<temp_linelist.size();i++) {
      Vector eachline = (Vector)temp_linelist.get(i);
      r = s.createRow(i);

      if (isHeaderExist) {
          for(int k=0;k<eachline.size();k++) {
            c = r.createCell(k);
            c.setCellValue(eachline.get(k).toString());
          }
      }
      else {
        for(int k=0;k<eachline.size();k++) {
            c = r.createCell(k);
            c.setCellValue(eachline.get(k).toString());
        }
      }
    }

    wb.write(out);
    out.close();


    mp2.init_table_data_fromExcel(tempfile.getPath(), header);
    tempfile.deleteOnExit();
  }

  class PedigreeComparator implements Comparator {

    protected int	m_sortCol;
    protected boolean m_sortAsc;

    public PedigreeComparator(int sortCol, boolean sortAsc) {
      m_sortCol = sortCol;
      m_sortAsc = sortAsc;
    }

    public int compare(Object o1, Object o2) {
      Vector s1 = (Vector)o1;
      Vector s2 = (Vector)o2;
      int result = 0;
      switch (m_sortCol) {
        case 0:
          result = s1.get(0).toString().compareTo(s2.get(0).toString());
          break;
      }
      if (!m_sortAsc)
        result = -result;
      return result;
    }

    public boolean equals(Object obj) {
      return true;
    }
  }

  void Gen_Table_Data_fromText(String filepath) throws Exception
  {
      MakeParameterStep1 m = (MakeParameterStep1) deck.getComponent("makepara1");
      String delimiter = new String();

      if (m.jRadioButton1.isSelected()) {
          if (m.tab.isSelected())
              delimiter = "\t";
          if (m.comma.isSelected())
              delimiter = ",";
          if (m.space.isSelected())
              delimiter = " ";
          if (m.other.isSelected())
              delimiter = m.delimiter.getText();
      }

      boolean isHeaderExist = false;
      if (m.jCheck1.isSelected())
      {
        m.getDataModel().setValue("pedigree.HeaderExist","header");
        isHeaderExist = true;
      }

      if(m.getDataModel().hasValue("pedigree.HeaderExist"))
      {
        isHeaderExist = true;
      }

      boolean moreThan500 = false;
      boolean single = m.single.isSelected();

      MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");
      m2.clear_table_data();

      m.getDataModel().setValue("pedigree.delimiters", delimiter);
      if (single)
        m.getDataModel().setValue("pedigree.delimiter_mode", "single");
      else
        m.getDataModel().setValue("pedigree.delimiter_mode", "multiple");

      if (single) {
          FileReader fr = new FileReader(filepath);
          BufferedReader br = new BufferedReader(fr);
          String temp = new String();
          while ((temp = br.readLine()) != null && temp.trim().length() > 0) {
              String[] t = temp.split(delimiter);

              if (t.length > 500)
                  moreThan500 = true;
               break;
          }
      }
      else {
          FileReader fr = new FileReader(filepath);
          BufferedReader br = new BufferedReader(fr);
          String temp = new String();
          while ((temp = br.readLine()) != null && temp.trim().length() > 0) {
              StringTokenizer st = new StringTokenizer(temp, delimiter);

              if (st.countTokens() > 500)
                  moreThan500 = true;
               break;
          }
      }

      if (moreThan500)
      {
          Make_TableData_MoreThan500(single, delimiter, isHeaderExist, filepath);
      }
      else
      {
          Make_TableData(single, delimiter, isHeaderExist, filepath);
      }

      column = null;
      linelist = null;

      column = new Vector();
      linelist = new Vector();
  }


  void Gen_Table_Data_fromExcel(Workbook newworkbook) throws Exception
    {
        MakeParameterStep1 m = (MakeParameterStep1) deck.getComponent("makepara1");
        MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");

        m2.clear_table_data();

        Sheet newsheet = newworkbook.getSheetAt(0);

        boolean isHeaderExist = false;
        if (m.jCheck1.isSelected())
        {
          m.getDataModel().setValue("pedigree.HeaderExist","header");
          isHeaderExist = true;
        }

        if(m.getDataModel().hasValue("pedigree.HeaderExist"))
        {
          isHeaderExist = true;
        }

        String delimiter = "\t";

        m2.getDataModel().setValue("pedigree.delimiters", delimiter);
        m2.getDataModel().setValue("pedigree.delimiter_mode", "multiple");

        Make_TableData_Excel(isHeaderExist, newsheet);
  }


  public void Make_TableData_MoreThan500(boolean filetype, String delimiter, boolean isHeaderExist, String source_file_path) throws Exception
  {
      MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");

      String[] t = null;

      String last_col="";

      int real_col_size = 0;
      int real_row_size = 0;

      Vector eachlinecontents = null;

      if (filetype) {
          int i = 0;

          FileReader fr = new FileReader(source_file_path);
          BufferedReader br = new BufferedReader(fr);
          String aLine = new String();

          while ((aLine = br.readLine()) != null) {

            if(i>=row_limit)
                break;

               if(aLine.trim().length() > 0)
               {
                   aLine = aLine.trim();
                   t = aLine.split(delimiter);
                   last_col = aLine.substring(aLine.lastIndexOf(delimiter)+1, aLine.length()).trim();

                   if(i==0)
                   {
                       real_col_size = t.length;
                       columnNames_all = new String[real_col_size];

                       if(isHeaderExist)
                       {
                           for (int ti = 0; ti < col_limit-2; ti++) {
                               column.add(t[ti]);
                               columnNames_all[ti] = t[ti];
                           }
                           column.add("...");
                           column.add(last_col);

                           for (int ti = col_limit-2; ti < real_col_size; ti++) {
                               columnNames_all[ti] = t[ti];
                           }
                       }
                       else
                       {
                           eachlinecontents = new Vector();

                           for (int ti = 0; ti < col_limit-2; ti++) {
                             column.add("column" + (ti+1));
                             columnNames_all[ti] = "column" + (ti+1);
                             eachlinecontents.add(t[ti]);
                           }

                           column.add("...");
                           column.add("column"+real_col_size);

                           eachlinecontents.add("...");
                           eachlinecontents.add(last_col);

                           for (int ti = col_limit-2; ti < real_col_size; ti++) {
                             columnNames_all[ti] = "column" + ti;
                           }

                           linelist.add(eachlinecontents);
                           eachlinecontents = null;
                       }
                   }
                   else if(i > 0)
                   {
                       eachlinecontents = new Vector();

                       int ti = 0;
                       for (ti = 0; ti < col_limit-2; ti++) {
                           if(i==row_limit-1)
                               eachlinecontents.add("...");
                           else
                             eachlinecontents.add(t[ti]);
                       }

                       eachlinecontents.add("...");

                       if (i == row_limit - 1)
                         eachlinecontents.add("...");
                       else
                         eachlinecontents.add(last_col);

                       linelist.add(eachlinecontents);
                   }
                   i++;
               }
          }
          real_row_size = i-1;
      }
      else {
          int i = 0;

          FileReader fr = new FileReader(source_file_path);
          BufferedReader br = new BufferedReader(fr);
          String temp = new String();
          String temp_h = new String();

          while ((temp = br.readLine().trim()) != null) {
              if(temp.trim().length() > 0)
              {
                if(i>=row_limit)
                  break;

                  StringTokenizer st = new StringTokenizer(temp, delimiter);
                  last_col = temp.substring(temp.lastIndexOf(delimiter), temp.length()).trim();

                  if(i==0)
                  {
                      real_col_size = st.countTokens();
                      columnNames_all = new String[real_col_size];

                      if(isHeaderExist)
                      {
                          for (int ti = 0; ti < col_limit-2; ti++) {
                            if(st.hasMoreTokens())
                            {
                              temp_h = st.nextToken();
                              column.add(temp_h);
                              columnNames_all[ti] = temp_h;
                            }
                          }
                          column.add("...");
                          column.add(last_col);

                          for (int ti = col_limit-2; ti < real_col_size; ti++) {
                            if(st.hasMoreTokens())
                              columnNames_all[ti] = st.nextToken();
                          }
                      }
                      else
                      {
                          eachlinecontents = new Vector();

                          for (int ti = 0; ti < col_limit-2; ti++) {
                            column.add("column" + ti);
                            columnNames_all[ti] = "column" + ti;

                            if(st.hasMoreTokens())
                              eachlinecontents.add(st.nextToken());
                          }
                          column.add("...");
                          column.add("column499");
                          eachlinecontents.add("...");
                          eachlinecontents.add(last_col);

                          for (int ti = col_limit-2; ti < real_col_size; ti++) {
                            columnNames_all[ti] = "column" + column;
                          }

                          linelist.add(eachlinecontents);
                          eachlinecontents = null;
                      }
                  }
                  else if(i > 0)
                  {
                      eachlinecontents = new Vector();

                      for (int ti = 0; ti < col_limit-2; ti++) {
                        temp_h = st.nextToken();
                          if(i==row_limit-1)
                              eachlinecontents.add("...");
                          else
                            eachlinecontents.add(temp_h);
                      }

                      eachlinecontents.add("...");

                      if (i == row_limit - 1)
                        eachlinecontents.add("...");
                      else
                        eachlinecontents.add(last_col);

                        linelist.add(eachlinecontents);
                        eachlinecontents = null;
                  }
                  i++;
              }
          }
          real_row_size = i-1;
      }

      Vector firstrow = new Vector(col_limit);
      for (int ii = 0; ii < col_limit; ii++) {
        firstrow.add("Unspecified");
      }
      firstrow.set(col_limit-2, "...");

      linelist.add(0, firstrow);
      linelist.trimToSize();

      m2.columnNames_all = columnNames_all;

      if(real_col_size > col_limit-1)
          m2.set_table_data(linelist, column, true, real_col_size, real_row_size, columnNames_all);
      else
          m2.set_table_data(linelist, column, false, real_col_size, real_row_size, columnNames_all);
  }

  public void Make_TableData(boolean filetype, String delimiter, boolean isHeaderExist, String source_file_path) throws Exception
  {
      MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");

      int real_col_size = 0;
      int real_row_size = 0;

      String[] t = null;
      Vector eachlinecontents = null;

      if (filetype) {
          int i = 0;

          FileReader fr = new FileReader(source_file_path);
          BufferedReader br = new BufferedReader(fr);
          String aLine = new String();

          while ((aLine = br.readLine()) != null && aLine.trim().length() > 0) {

            if(i>=row_limit)
                        break;

                t = aLine.split(delimiter, real_col_size);
                if(isHeaderExist)
                {
                  if(i==0)
                  {
                    real_col_size = t.length;
                    columnNames_all = new String[real_col_size];

                    for (int ti = 0; ti < real_col_size; ti++) {
                        column.add(t[ti]);
                        columnNames_all[ti] = t[ti];
                    }

                  }
                  else if(i>0)
                  {
                    eachlinecontents = new Vector();

                    for (int ti = 0; ti < real_col_size; ti++) {

                      if(i==row_limit-1)
                           eachlinecontents.add("...");
                      else
                        eachlinecontents.add(t[ti]);
                    }

                    linelist.add(eachlinecontents);
                    eachlinecontents = null;
                  }
                  i++;
                }
                else
                {
                  if(i==0)
                  {
                    real_col_size = t.length;
                    columnNames_all = new String[real_col_size];
                  }

                  eachlinecontents = new Vector();
                  for (int ti = 0; ti < real_col_size; ti++) {
                      if(i==row_limit - 1)
                        eachlinecontents.add("...");
                      else
                          eachlinecontents.add(t[ti]);
                  }
                  linelist.add(eachlinecontents);
                  eachlinecontents = null;
                  i++;
                }

          }

          real_row_size = i-1;
      }
      else {
          int i = 0;

          FileReader fr = new FileReader(source_file_path);
          BufferedReader br = new BufferedReader(fr);
          String temp = new String();
          String eachname = new String();

          while ((temp = br.readLine()) != null && temp.length()>0) {
                  StringTokenizer st = new StringTokenizer(temp, delimiter);
                  if(i>=row_limit)
                        break;
                  if(isHeaderExist)
                  {
                    if(i==0)
                    {
                      real_col_size = st.countTokens();
                      columnNames_all = new String[real_col_size];

                      int j=0;
                      while (st.hasMoreTokens()) {
                        eachname = st.nextToken();
                          column.add(eachname);
                          columnNames_all[j] = eachname;
                          j++;
                      }
                    }
                    else if(i>0)
                    {
                      eachlinecontents = new Vector();

                      int j=0;
                      while (st.hasMoreTokens()) {
                        eachname = st.nextToken();
                        if (i == row_limit - 1)
                          eachlinecontents.add("...");
                        else
                          eachlinecontents.add(eachname);
                        j++;
                      }

                      linelist.add(eachlinecontents);
                      eachlinecontents = null;
                    }
                    i++;
                  }
                  else
                  {
                    if(i==0)
                    {
                      real_col_size = st.countTokens();
                      columnNames_all = new String[real_col_size];
                    }

                    eachlinecontents = new Vector();

                    int j=0;
                    while (st.hasMoreTokens()) {
                      eachname = st.nextToken();
                      if(j==row_limit - 1)
                        eachlinecontents.add("...");
                      else
                        eachlinecontents.add(eachname);
                      j++;
                    }
                    linelist.add(eachlinecontents);
                    eachlinecontents = null;
                    i++;
                  }

          }
          real_row_size = i-1;
      }

      if (!isHeaderExist) {
        for (int c = 0; c < real_col_size; c++) {
          column.add("column" + c);
          columnNames_all[c] = "column" + (c);
        }
      }

      Vector firstrow = new Vector(column.size());
      for (int ii = 0; ii < column.size(); ii++) {
        firstrow.add("Unspecified");
      }

      linelist.add(0, firstrow);
      linelist.trimToSize();

      m2.set_table_data(linelist, column, false, real_col_size, real_row_size, columnNames_all);
  }

  public void  Make_TableData_Excel(boolean header, Sheet newsheet) {

        MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");

        int rows = newsheet.getPhysicalNumberOfRows();
        Row  row = newsheet.getRow(0);
        int cells = row.getPhysicalNumberOfCells();

        int column_count = cells;
        int row_count = rows;

        int c = 0;
        int real_col_size = column_count;
        int real_row_size = row_count;

        int limit = Math.min(real_row_size, row_limit);

        for (int i = 0; i < limit ; i++) {
          Vector eachlinecontents = new Vector();

          row = newsheet.getRow(i);

            if (i == 0) {
              columnNames_all = new String[real_col_size];
                if (header) {
                    for (c = 0; c < column_count; c++) {
                        Cell a1 = row.getCell(c);;
                        String temp= null;

                        switch (a1.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    if(Double.toString((double) a1.getNumericCellValue()).endsWith(".0"))
                                        temp = Integer.toString((int) a1.getNumericCellValue());
                                    else
                                        temp = Double.toString((double) a1.getNumericCellValue());
                                        break;
                                case Cell.CELL_TYPE_STRING:
                                        temp = a1.getStringCellValue();
                                        break;
                                default:
                                    temp = a1.getStringCellValue();
                                    break;
                      }

                        column.add(temp);
                        columnNames_all[c] = temp;
                    }
                }
                else
                {
                    for (c = 0; c < column_count; c++) {
                        Cell a1 = row.getCell(c);;
                        column.add("column" + c);
                        columnNames_all[c] = "column" + c;
                        eachlinecontents.add(a1.getStringCellValue());
                    }
                    linelist.add(eachlinecontents);
                }

            }
            else if (i > 0) {

                for (c = 0; c < column_count; c++) {
                    Cell a1 = row.getCell(c);;
                    String temp= null;

                    switch (a1.getCellType()) {
                            case Cell.CELL_TYPE_NUMERIC:

                                if(Double.toString((double) a1.getNumericCellValue()).endsWith(".0"))
                                    temp = Integer.toString((int) a1.getNumericCellValue());
                                else
                                    temp = Double.toString((double) a1.getNumericCellValue());
                                    break;
                            case Cell.CELL_TYPE_STRING:
                                    temp = a1.getStringCellValue();
                                    break;
                            default:
                                temp = a1.getStringCellValue();
                                break;
                      }
                    if(i==row_limit - 1)
                      eachlinecontents.add("...");
                    else
                      eachlinecontents.add(temp);
                }
                linelist.add(eachlinecontents);
            }
        }

        Vector firstrow = new Vector(column.size());
        for (int ii = 0; ii < column.size(); ii++) {
          firstrow.add("Unspecified");
        }

        linelist.add(0, firstrow);
        linelist.trimToSize();

        m2.set_table_data(linelist, column, false, real_col_size, real_row_size, columnNames_all);
      }

public String Write_Pedigree_File(String externalfilepath, ProgressDialog pd) throws Exception{

  MakePedigreeStep4 m4 = (MakePedigreeStep4) deck.getComponent("makepedi4");
  MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");
  MakeParameterStep1 m = (MakeParameterStep1) deck.getComponent("makepara1");

  DataCollectionModel dm = (DataCollectionModel) m2.getDataModel();

  int nameindex = externalfilepath.lastIndexOf(System.getProperty("file.separator"));
  String externalfilepath2 = externalfilepath.substring(nameindex + 1,externalfilepath.length());

  File originalpedfile = new File(externalfilepath);
  NodeInfo filend = new NodeInfo(externalfilepath2, "Pedigree File",originalpedfile);
  temp_external_pedi = new IconNode(filend, "Pedigree File");
  externalnode.add(temp_external_pedi);
  dm.setValue("externalnode", externalnode);

  String filename = m4.pName.getText();
  String filepath = (String) m4.getDataModel().getValue("info.DirPath") + filename;

  temp_pedigree_file = new File(filepath);
  NodeInfo internalfilend = new NodeInfo(temp_pedigree_file.getName(), "Pedigree File", temp_pedigree_file);
  temp_internal_pedi = new IconNode(internalfilend, "Pedigree File");
  internalnode.add(temp_internal_pedi);
  dm.setValue("internalnode", internalnode);

  boolean single = false;
  if(m.getDataModel().getValue("pedigree.delimiter_mode").toString().equals("single"))
    single = true;

  String original_delimiter =  m.getDataModel().getValue("pedigree.delimiters").toString();
  String tab_delimiter = "\t";
  m4.getDataModel().setValue("pedigree.delimiters", tab_delimiter);
  m4.getDataModel().setValue("pedigree.delimiter_mode", "single");
  m4.getDataModel().setValue("delimiters_name", tab_delimiter);
  m4.getDataModel().setValue("delimiter_mode", "single");

  boolean isHeaderExist = false;
  if (m.getDataModel().hasValue("pedigree.HeaderExist"))
      isHeaderExist = true;

  FileWriter fos = new FileWriter(temp_pedigree_file);

  boolean header[] = new boolean[columnNames_all.length];
  for (int i = 0; i < header.length; i++) {
    header[i] = false;
  }

    for (int i = 0; i < columnNames_all.length; i++) {
      if (m2.getDataModel().getValue("pedigree" + columnNames_all[i]) != null)
      {
        if(!isHeaderExist)
        {
          fos.write(columnNames_all[i]);
          if(i<columnNames_all.length -1)
              fos.write(tab_delimiter);
        }

        header[i] = true;
      }
    }
    if(!isHeaderExist)
     fos.write("\n");

   int progress = 10;
   pd.SetProgress(progress);

  int pid_column = -1000;
  if(m4.getDataModel().hasValue("pedigree.pedigree_id_columncount"))
  {
    pid_column = Integer.parseInt(m4.getDataModel().getValue("pedigree.pedigree_id_columncount").toString());
  }

  Vector temp_pid_list = new Vector();
  pid_list.removeAllElements();


 if(IsExcel)
  {
          Sheet newsheet = newworkbook.getSheetAt(0);

          int row_count = newsheet.getPhysicalNumberOfRows();
          Row row = newsheet.getRow(0);
          int column_count = row.getPhysicalNumberOfCells();

          int per = row_count / 10;

          if (per == 0)
              per = 1;

          for (int i = 0; i < row_count; i++) {

              row = newsheet.getRow(i);

              for (int c = 0; c < column_count; c++) {
                  if (i % per == 0) {
                      progress += 10;
                      pd.SetProgress(progress);
                  }

                  if (header[c]) {
                      Cell a1 = row.getCell(c); ;

                      String temp = null;

                      switch (a1.getCellType()) {
                      case Cell.CELL_TYPE_NUMERIC:
                          if(Double.toString((double) a1.getNumericCellValue()).endsWith(".0"))
                              temp = Integer.toString((int) a1.getNumericCellValue());
                          else
                              temp = Double.toString((double) a1.getNumericCellValue());
                          break;
                      case Cell.CELL_TYPE_STRING:
                          temp = a1.getStringCellValue();
                          break;
                      default:
                          temp = a1.getStringCellValue();
                          break;
                      }

                      fos.write(temp);
                      if (i < column_count - 1)
                          fos.write(tab_delimiter);

                      if (c == pid_column && temp != null) {
                          if (isHeaderExist)
                              if (i > 0)
                                  temp_pid_list.add(temp);
                              else
                                  temp_pid_list.add(temp);
                      }
                  }
              }
              fos.write("\n");
          }
  }
  else
  {
    long total_size = originalpedfile.length();

    if (single) {
        FileReader fr = new FileReader(originalpedfile);
        BufferedReader br = new BufferedReader(fr);
        String temp = new String();
        int lineindex=0;
        String[] t = null;

        long line_size = 0;
        long per_nor = 0;
        int per = 0;

        while ((temp = br.readLine()) != null && temp.trim().length() > 0) {

          if(lineindex==0)
          {
            line_size = temp.length()/4;
            per_nor = total_size/line_size;
            per = (int)per_nor/100;
            if(per == 0)
              per = 1;
          }

          if(lineindex % per ==0)
          {
            progress += 1;
            pd.SetProgress(progress);
          }

          t = temp.split(original_delimiter, columnNames_all.length);

            for (int ti = 0; ti < t.length; ti++) {
              if(header[ti])
              {
                if (t[ti] == null)
                {
                  fos.write(" ");
                }
                else
                {
                  fos.write(t[ti]);
                }

                if(ti<t.length -1)
                   fos.write(tab_delimiter);

                if (ti == pid_column && t[ti] != null)
                {
                  if(isHeaderExist)
                    if(lineindex > 0)
                      temp_pid_list.add(t[ti]);
                  else
                    temp_pid_list.add(t[ti]);
                }
              }
            }
            t=null;
            fos.write("\n");
            lineindex++;
        }
    }
    else {
        FileReader fr = new FileReader(originalpedfile);
        BufferedReader br = new BufferedReader(fr);
        String temp = new String();
        int lineindex=0;

        long line_size = 0;
        long per_nor = 0;
        int per = 0;

        while ((temp = br.readLine()) != null && temp.trim().length() > 0) {

          if(lineindex==0)
          {
            line_size = temp.length()/4;
            per_nor = total_size/line_size;
            per = (int)per_nor/100;
            if(per == 0)
              per = 1;
          }

          if(lineindex % per ==0)
          {
            progress += 1;
            pd.SetProgress(progress);
          }

            StringTokenizer st = new StringTokenizer(temp, original_delimiter);

            int ct = st.countTokens();
            for(int j=0;j<ct;j++)
            {
              String temp2 = st.nextToken();

              if(header[j])
              {
                if (temp2 == null)
                  fos.write(" ");
                else
                  fos.write(temp2);

              if(j<ct -1)
                fos.write(tab_delimiter);

                if (j == pid_column && temp2 != null)
                {
                  if(isHeaderExist)
                    if(lineindex > 0)
                      temp_pid_list.add(temp2);
                  else
                    temp_pid_list.add(temp2);
                }
              }
            }

            for(int j=ct; j<columnNames_all.length;j++)
            {
              if(header[j])
             {
               fos.write(" ");
               if(j<columnNames_all.length -1)
               fos.write(tab_delimiter);
             }
            }
            fos.write("\n");
            lineindex++;
        }
    }

  }
  fos.close();

  if(pid_column >= 0 && temp_pid_list.size()>0)
  {
    List sortedlist = temp_pid_list.subList(0, temp_pid_list.size());
    Collections.sort(sortedlist);

    String strFileLine = new String();
    String strFileNextLine = new String();

    for(int i=0;i<sortedlist.size();i++)
    {
      strFileLine = sortedlist.get(i).toString();
      if (strFileLine.compareTo(strFileNextLine) != 0) {
        pid_list.add(strFileLine);
      }
      strFileNextLine = strFileLine;
    }

    m4.getDataModel().setValue("pid_list", pid_list);
    progress = 100;
    pd.SetProgress(progress);
  }

  String filepath_result = m4.getDataModel().getValue("info.DirPath").toString()
     + m4.getDataModel().getValue("info.pName") + System.getProperty("file.separator") + filename;

  return filepath_result;
}

  public void removeDataValue()
  {
    MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");
    DataCollectionModel m = (DataCollectionModel) m2.getDataModel();

    m2.marker_count=1;
    m2.trait_count=1;
    m2.phenotype_count=1;
    m2.covariate_count=1;
    m2.allele_count=1;
    m2.trait_marker_count=1;

      m.removeValue("pedigree.delimiters");
      m.removeValue("pedigree.delimiter_mode");

      if (m.hasValue("pedigree.individual_missing_value")) {
        m.removeValue("pedigree.individual_missing_value");
      }
      if (m.hasValue("pedigree.sex_code.male")) {
        m.removeValue("pedigree.sex_code.male");
      }
      if (m.hasValue("pedigree.sex_code.female")) {
        m.removeValue("pedigree.sex_code.female");
      }
      if (m.hasValue("pedigree.sex_code.unknown")) {
        m.removeValue("pedigree.sex_code.unknown");
      }
      if (m.hasValue("pedigree.sex_code_trait")) {
        m.removeValue("pedigree.sex_code_trait");
      }

      if (m.hasValue("pedigree.pedigree_id")) {
        m.removeValue("pedigree.pedigree_id");
      }

      if (m.hasValue("pedigree.individual_id")) {
        m.removeValue("pedigree.individual_id");
      }
      if (m.hasValue("pedigree.parent_id1")) {
        m.removeValue("pedigree.parent_id1");
      }
      if (m.hasValue("pedigree.parent_id2")) {
        m.removeValue("pedigree.parent_id2");
      }
      if (m.hasValue("pedigree.sex_field")) {
        m.removeValue("pedigree.sex_field");
      }

      if (m.hasValue("pedigree.no_sex_field")) {
        m.removeValue("pedigree.no_sex_field");
      }
      if (m.hasValue("pedigree.treat_as_sibs")) {
        m.removeValue("pedigree.treat_as_sibs");
      }

      if(m.hasValue("pedigree.HeaderExist"))
        m.removeValue("pedigree.HeaderExist");

      if(m2.columnname != null)
      {
        for (int i = 0; i < m2.columnname.size(); i++) {
          if (m.getValue("pedigree" + m2.columnname.get(i)) != null) {
            String temp = "pedigree" + m2.columnname.get(i);
            String type = m.getValue(temp).toString();

            if (type.compareTo("COVARIATE")==0 ||
                type.compareTo("PHENOTYPE")==0 ||
                type.compareTo("TRAIT")==0) {
            }

            else if (type.compareTo("MARKER")==0 || type.compareTo("ALLELE")==0) {
              if (m.hasValue(temp + ".allele_delimiter"))
                m.removeValue(temp + ".allele_delimiter");

              if (m.hasValue(temp + ".allele_missing"))
                m.removeValue(temp + ".allele_missing");

              if (m.hasValue(temp + ".allele_frequency.minimum"))
                m.removeValue(temp + ".allele_frequency.minimum");

              if (m.hasValue(temp + ".allele_frequency.maximum"))
                m.removeValue(temp + ".allele_frequency.maximum");

              if (m.hasValue(temp + ".allele_frequency.complement"))
                m.removeValue(temp + ".allele_frequency.complement");

              if (m.hasValue(temp + ".allele_frequency.equal"))
                m.removeValue(temp + ".allele_frequency.equal");
          }

          else if(type.compareTo("TRAIT MARKER")==0)
          {
            if (m.hasValue(temp + ".affected"))
              m.removeValue(temp + ".affected");

            if (m.hasValue(temp + ".unaffected"))
              m.removeValue(temp + ".unaffected");

            if (m.hasValue(temp + ".disease"))
              m.removeValue(temp + ".disease");

            if (m.hasValue(temp + ".homozygote"))
              m.removeValue(temp + ".homozygote");

            if (m.hasValue(temp + ".heterozygote"))
              m.removeValue(temp + ".heterozygote");

          }
            m.removeValue(temp + ".Hname");
            m.removeValue(temp + ".Aname");
          }
          m.removeValue("pedigree" + m2.columnname.get(i));
        }


      }
      if (m.hasValue("marker.allele_delimiter"))
        m.removeValue("marker.allele_delimiter");

      if (m.hasValue("marker.allele_missing"))
        m.removeValue("marker.allele_missing");

      if (m.hasValue("marker.allele_frequency"))
        m.removeValue("marker.allele_frequency");

      if (m.hasValue("marker.allele_frequency.minimum"))
        m.removeValue("marker.allele_frequency.minimum");

      if (m.hasValue("marker.allele_frequency.maximum"))
        m.removeValue("marker.allele_frequency.maximum");

      if (m.hasValue("marker.allele_frequency.complement"))
        m.removeValue("marker.allele_frequency.complement");

      if (m.hasValue("marker.allele_frequency.equal"))
        m.removeValue("marker.allele_frequency.equal");
  }

  public Vector Write_Parameter_Block() throws Exception{
    Vector Covariate_array = new Vector();
    Vector Trait_array = new Vector();
    Vector Phenotype_array = new Vector();
    Vector Marker_array = new Vector();
    Vector Allele_array = new Vector();
    Vector TraitMarker_array = new Vector();
    Vector String_array = new Vector();

    boolean trait = false;
    boolean trait_fileinfo = false;

    MakeParameterStep3 m3 = (MakeParameterStep3) deck.getComponent("makepara3");
    DataCollectionModel m = (DataCollectionModel) m3.getDataModel();

    String filename = m3.pName.getText();
    String filepath = (String) m3.getDataModel().getValue("info.DirPath") + "traits"+traitfile_count+".loc";

    temp_trait_blocknode = null;

      temp_parameter_file = File.createTempFile(filename ,"");
      temp_trait_file = new File(filepath);

      temp_trait_blocknode = new NodeInfo(temp_trait_file.getName(), "Trait File", temp_trait_file);
      FileWriter fos_trait = new FileWriter(temp_trait_file);

      StringBuffer fos = new StringBuffer();

      fos.append("pedigree");
      fos.append("\n{");
      fos.append("\n");

      // delimiter
      fos.append("delimiters = " + "\"\\t\"");
      fos.append("\n");
      fos.append("delimiter_mode = " + "\"single\"");
      fos.append("\n\n");

      // field encoidng paramters
      if (m.hasValue("pedigree.individual_missing_value")) {
        fos.append("individual_missing_value = " + "\"" + m.getValue("pedigree.individual_missing_value") + "\"" + "\n");
      }

      if (m.hasValue("pedigree.no_sex_field"))
      {
          fos.append("no_sex_field" + "\n");
      }
      else
      {
          if (m.hasValue("pedigree.sex_code.male")) {
            fos.append("sex_code, male = " + "\"" + m.getValue("pedigree.sex_code.male") + "\"");
          }
          if (m.hasValue("pedigree.sex_code.female")) {
            fos.append(", female = " + "\"" + m.getValue("pedigree.sex_code.female") +"\"");
          }
          if (m.hasValue("pedigree.sex_code.unknown")) {
            fos.append(", missing = " + "\"" + m.getValue("pedigree.sex_code.unknown") +"\"");
          }
          if (m.hasValue("pedigree.sex_code_trait")) {
            fos.append(", trait");
            Trait_array.add("SEX_CODE");
          }
          if (m.hasValue("pedigree.no_sex_ok")) {
            fos.append("\nno_sex_ok = \"true\"");
          }
          fos.append("\n");
      }
      fos.append("\n");

      // Family Structure fields
      if (m.hasValue("pedigree.pedigree_id")) {
        fos.append("pedigree_id = " + "\"" + m.getValue("pedigree.pedigree_id") + "\"");
        fos.append("\n");
      }

      if (m.hasValue("pedigree.individual_id")) {
        fos.append("individual_id = " + "\"" + m.getValue("pedigree.individual_id") + "\"" + "\n");
      }
      if (m.hasValue("pedigree.parent_id1")) {
        fos.append("parent_id = " + "\"" + m.getValue("pedigree.parent_id1") + "\"" + "\n");
      }
      if (m.hasValue("pedigree.parent_id2")) {
        fos.append("parent_id = " + "\"" + m.getValue("pedigree.parent_id2") + "\"" + "\n");
      }
      if (m.hasValue("pedigree.sex_field")) {
        fos.append("sex_field = " + "\"" + m.getValue("pedigree.sex_field") + "\"" + "\n");
        Trait_array.add(m.getValue("pedigree.sex_field"));
      }

      if (m.hasValue("pedigree.treat_as_sibs")) {
        fos.append("treat_as_sibs"+"\n");
      }
      fos.append("\n");

      boolean add_marker = false;
      boolean add_covariate = false;
      int marker_list_index = 0;
      int covariate_list_index = 0;
      Vector MList_Start_array = (Vector)m.getValue("MList_Start_array");
      Vector MList_End_array = (Vector)m.getValue("MList_End_array");

      Vector CovList_Start_array = (Vector)m.getValue("CovList_Start_array");
      Vector CovList_End_array = (Vector)m.getValue("CovList_End_array");

      // Trait/Covariate/Phenotypes
      MakeParameterStep2 m2 = (MakeParameterStep2) deck.getComponent("makepara2");
      for (int i = 0; i < m2.columnNames_all.length; i++) {
          String Header_Name = m2.columnNames_all[i];

          if(MList_Start_array != null)
          {
            if(marker_list_index < MList_Start_array.size())
            {
              if(Header_Name.compareTo(MList_Start_array.get(marker_list_index).toString())==0)
              {
                  add_marker = true;
              }
            }
          }

          if(CovList_Start_array != null)
          {
            if(covariate_list_index < CovList_Start_array.size())
            {
              if(Header_Name.compareTo(CovList_Start_array.get(covariate_list_index).toString())==0)
              {
                  add_covariate = true;
              }
            }
          }
          if (m.getValue("pedigree" + Header_Name) != null) {
          String temp = "pedigree" + Header_Name;
          String type = m.getValue(temp).toString();

          if (type.compareTo("COVARIATE")==0 ||
              type.compareTo("PHENOTYPE")==0 ||
              type.compareTo("TRAIT")==0) {
            String HeaderName = m.getValue(temp + ".Hname").toString();
            String InputName = m.getValue(temp + ".Aname").toString();

            boolean trait_marker = false;

            if (type.compareTo("COVARIATE") == 0) {
                if (!add_covariate)
                    fos.append("covariate = " + "\"" + HeaderName + "\"");
                if (HeaderName.compareTo(InputName) != 0) {
                    if (!add_covariate)
                        fos.append(", name = " + "\"" + InputName + "\"");
                    if (!add_covariate)
                        Covariate_array.add(InputName);
                } else {
                    if (!add_covariate)
                        Covariate_array.add(HeaderName);
                }
            }
            else if (type.compareTo("PHENOTYPE") == 0) {
              fos.append("phenotype = " + "\""+ HeaderName+ "\"");
              if (HeaderName.compareTo(InputName) != 0) {
                fos.append(", name = " + "\""+ InputName+ "\"");
                Phenotype_array.add(InputName);
              }
              else {
                Phenotype_array.add(HeaderName);
              }
            }
            else if (type.compareTo("TRAIT") == 0) {
              fos.append("trait = " + "\""+ HeaderName+ "\"");
              if (HeaderName.compareTo(InputName) != 0) {
                fos.append(", name = " + "\""+ InputName+ "\"");
                Trait_array.add(InputName);
              }
              else {
                Trait_array.add(HeaderName);
              }

              if (m.hasValue("pedigree" + HeaderName + ".TR"))
              {
                  trait_marker = true;
              }
            }

            if(type.compareTo("COVARIATE") == 0)
            {
                if (!add_covariate)
                {
                    if (m.getValue(temp + ".type").toString().compareTo("binary") == 0) {
                      fos.append(", binary");
                      if(m.hasValue(temp + ".binary_affected"))
                        fos.append(", affected = " + "\"" + m.getValue(temp + ".binary_affected") + "\"");
                      if(m.hasValue(temp + ".binary_unaffected"))
                        fos.append(", unaffected = " + "\"" +
                                m.getValue(temp + ".binary_unaffected") + "\"");
                      if(m.hasValue(temp + ".missing"))
                        fos.append(", missing = " + "\"" + m.getValue(temp + ".missing") + "\"");
                    }
                    else if (m.getValue(temp + ".type").toString().compareTo("continuous") == 0) {
                      fos.append(", continuous");
                      if(m.hasValue(temp + ".missing"))
                        fos.append(", missing = " + "\"" + m.getValue(temp + ".missing") + "\"");
                    }
                    else if (m.getValue(temp + ".type").toString().compareTo("categorical") == 0) {
                      fos.append(", categorical");
                      if(m.hasValue(temp + ".missing"))
                        fos.append(", missing = " + "\"" + m.getValue(temp + ".missing") + "\"");
                    }

                    fos.append("\n");
                }
            }
            else if(type.compareTo("PHENOTYPE") == 0 || type.compareTo("TRAIT") == 0)
            {
                if (m.getValue(temp + ".type").toString().compareTo("binary") == 0) {
                  fos.append(", binary");
                  if(m.hasValue(temp + ".binary_affected"))
                    fos.append(", affected = " + "\"" + m.getValue(temp + ".binary_affected") + "\"");
                  if(m.hasValue(temp + ".binary_unaffected"))
                    fos.append(", unaffected = " + "\"" +
                            m.getValue(temp + ".binary_unaffected") + "\"");
                  if(m.hasValue(temp + ".missing"))
                    fos.append(", missing = " + "\"" + m.getValue(temp + ".missing") + "\"");
                }
                else if (m.getValue(temp + ".type").toString().compareTo("continuous") == 0) {
                  fos.append(", continuous");
                  if(m.hasValue(temp + ".missing"))
                    fos.append(", missing = " + "\"" + m.getValue(temp + ".missing") + "\"");
                }
                else if (m.getValue(temp + ".type").toString().compareTo("categorical") == 0) {
                  fos.append(", categorical");
                  if(m.hasValue(temp + ".missing"))
                    fos.append(", missing = " + "\"" + m.getValue(temp + ".missing") + "\"");
                }

                    fos.append("\n");
            }

            if (trait_marker)
            {
                fos.append("trait_marker = " + "\""+HeaderName+ "\"");

                String Headert = m.getValue("pedigree" + HeaderName + ".TR" + ".Hname").toString();
                String Inputt = m.getValue("pedigree" + HeaderName + ".TR" + ".Aname").toString();

                if (Headert.compareTo(Inputt) != 0)
                    fos.append(", name=" + "\"" + InputName + "\"");
                fos.append("\n");
                TraitMarker_array.add(HeaderName);
                trait_marker = false;
            }
          }

          else if (type.compareTo("MARKER")==0 || type.compareTo("ALLELE")==0) {
            String HeaderName = m.getValue(temp + ".Hname").toString();
            String InputName = m.getValue(temp + ".Aname").toString();

            if (type.compareTo("MARKER") == 0) {
              if(!add_marker)
                fos.append("marker = " + "\"" + HeaderName + "\"");
              if (HeaderName.compareTo(InputName) != 0) {
                if(!add_marker)
                  fos.append(", name = " + "\""+ InputName+ "\"");
                if(!add_marker)
                  Marker_array.add(InputName);
              }
              else {
                if(!add_marker)
                  Marker_array.add(HeaderName);
              }
            }
            else if (type.compareTo("ALLELE") == 0) {
              fos.append("allele = " + "\""+ HeaderName+ "\"");
              fos.append(", name = " + "\""+ InputName+ "\"");
              Allele_array.add(InputName);
            }

            if (type.compareTo("MARKER")==0) {
              if(!add_marker)
              {
                if (m.getValue(temp + ".allele_delimiter") != null)
                      fos.append(", allele_delimiter = " + "\"" + m.getValue(temp + ".allele_delimiter") + "\"");
              }
            }

            if (m.hasValue(temp + ".allele_missing"))
              if(!add_marker)
              {
                if (m.getValue(temp + ".allele_missing") != null)
                      fos.append(", allele_missing = " + "\"" + m.getValue(temp + ".allele_missing") + "\"");
              }

            if (m.hasValue(temp + ".allele_frequency.minimum")) {
              if (m.hasValue("marker.allele_frequency.minimum")) {
                if (m.getValue("marker.allele_frequency.minimum").toString().compareTo(m.getValue(temp + ".allele_frequency.minimum").toString()) != 0)
                  fos.append(", minimum = " + "\""+ m.getValue(temp + ".allele_frequency.minimum")+ "\"");
                if (m.getValue("marker.allele_frequency.maximum").toString().compareTo(m.getValue(temp + ".allele_frequency.maximum").toString()) != 0)
                  fos.append(", maximum = " + "\""+ m.getValue(temp + ".allele_frequency.maximum")+ "\"");
              }
            }
            else if (m.hasValue(temp + ".allele_frequency.complement")) {
              if (m.getValue("marker.allele_frequency.complement") != m.getValue(temp + ".allele_frequency.complement"))
                fos.append(", complement");
            }
            else if (m.hasValue(temp + ".allele_frequency.equal")) {
              if (m.getValue("marker.allele_frequency.equal") != m.getValue(temp + ".allele_frequency.equal"))
                fos.append(", equal");
            }

           // use as covariate options for each marker
            if (m.hasValue(temp + ".useascov"))
            {
                if (m.hasValue(temp + ".useascov.covmode")) {
                  if (m.hasValue("marker.useascov.covmode")) {
                    if (m.getValue("marker.useascov.covmode").toString().compareTo(m.getValue(temp + ".useascov.covmode").toString()) != 0)
                      fos.append(", covariate_function = " + "\""+ m.getValue(temp + ".useascov.covmode")+ "\"");
                  }
                  else
                      fos.append(", covariate_function = " + "\""+ m.getValue(temp + ".useascov.covmode")+ "\"");
                }

                if (m.hasValue(temp + ".useascov.covallele")) {
                  if (m.hasValue("marker.useascov.covallele")) {
                    if (m.getValue("marker.useascov.covallele").toString().compareTo(m.getValue(temp + ".useascov.covallele").toString()) != 0)
                      fos.append(", base_allele = " + "\""+ m.getValue(temp + ".useascov.covallele")+ "\"");
                  }
                  else
                      fos.append(", base_allele = " + "\""+ m.getValue(temp + ".useascov.covallele")+ "\"");
                }

                if (m.hasValue(temp + ".useascov.hemizygote")) {
                  if (m.hasValue("marker.useascov.hemizygote")) {
                    if (m.getValue("marker.useascov.hemizygote").toString().compareTo(m.getValue(temp + ".useascov.hemizygote").toString()) != 0)
                      fos.append(", allow_hemizygote = " + "\""+ m.getValue(temp + ".useascov.hemizygote")+ "\"");
                  }
                  else
                      fos.append(", allow_hemizygote = " + "\""+ m.getValue(temp + ".useascov.hemizygote")+ "\"");
                }
            }

            if (type.compareTo("MARKER") == 0)
                if (!add_marker)
                    fos.append("\n");
            else if (type.compareTo("ALLELE") == 0)
                fos.append("\n");
          }

          else if (type.compareTo("TEXT")==0) {
            String HeaderName = m.getValue(temp + ".Hname").toString();
            fos.append("string = " + "\""+ HeaderName+ "\"");
            String_array.add(HeaderName);
            fos.append("\n");
          }

          else if (type.compareTo("TRAIT MARKER") == 0) {

            trait = true;

            String HeaderName = m.getValue(temp + ".Hname").toString();
            String InputName = m.getValue(temp + ".Aname").toString();

            fos.append("trait_marker = " + HeaderName);

            if (HeaderName.compareTo(InputName) != 0) {
              fos.append(", name=" + "\""+ InputName+ "\"");
              fos_trait.write(InputName + "\n");
              TraitMarker_array.add(InputName);
            }
            else {
              fos_trait.write(HeaderName + "\n");
              TraitMarker_array.add(HeaderName);
            }

            if (m.hasValue(temp + ".affected") && m.hasValue(temp + ".unaffected")
                && m.hasValue(temp + ".daf"))
            {
              //dominant
              if (m.hasValue(temp + ".d1") && m.hasValue(temp + ".d2"))
              {
                trait_fileinfo = true;

                String tempaffected = m.getValue(temp + ".affected").toString();
                String tempunaffected = m.getValue(temp + ".unaffected").toString();

                double count = -1;
                double count1 = -1;
                double count2 = -1;
                try {
                  count = Double.parseDouble(m.getValue(temp + ".daf").toString());
                  count1 = Double.parseDouble(m.getValue(temp + ".d1").toString());
                  count2 = Double.parseDouble(m.getValue(temp + ".d2").toString());
                }
                catch (NumberFormatException en) {
                  throw en;
                }

                double value = 1 - count;
                double value1 = 1 - count1;
                double value2 = 1 - count2;

                fos_trait.write("1 = " + Double.toString(count) + "\n");
                fos_trait.write("2 = " + Double.toString(value) + "\n");

                fos_trait.write(";\n");

                fos_trait.write(tempaffected + " = < 1/1=" +
                                Double.toString(count1) +
                                ", 1/2=" + Double.toString(count1) + ", 2/2=" +
                                Double.toString(count2) + " >\n");
                fos_trait.write(tempunaffected + " = < 1/1=" +
                                Double.toString(value1) +
                                ", 1/2=" + Double.toString(value1) + ", 2/2=" +
                                Double.toString(value2) + " >\n");

                fos_trait.write(";\n");
              }

              //recessive
              if (m.hasValue(temp + ".r1") && m.hasValue(temp + ".r2") && m.hasValue(temp + ".r3"))
              {
                trait_fileinfo = true;

                String tempaffected = m.getValue(temp + ".affected").toString();
                String tempunaffected = m.getValue(temp + ".unaffected").toString();

                double count = -1;
                double count1 = -1;
                double count2 = -1;
                double count3 = -1;
                try {
                  count = Double.parseDouble(m.getValue(temp + ".daf").toString());
                  count1 = Double.parseDouble(m.getValue(temp + ".r1").toString());
                  count2 = Double.parseDouble(m.getValue(temp + ".r2").toString());
                  count3 = Double.parseDouble(m.getValue(temp + ".r3").toString());
                }
                catch (NumberFormatException en) {
                  throw en;
                }

                double value = 1 - count;
                double value1 = 1 - count1;
                double value2 = 1 - count2;
                double value3 = 1 - count3;

                fos_trait.write("1 = " + Double.toString(count) + "\n");
                fos_trait.write("2 = " + Double.toString(value) + "\n");

                fos_trait.write(";\n");

                fos_trait.write(tempaffected + " = < 1/1=" +
                                Double.toString(count1) +
                                ", 1/2=" + Double.toString(count2) + ", 2/2=" +
                                Double.toString(count3) + " >\n");
                fos_trait.write(tempunaffected + " = < 1/1=" +
                                Double.toString(value1) +
                                ", 1/2=" + Double.toString(value2) + ", 2/2=" +
                                Double.toString(value3) + " >\n");

                fos_trait.write(";\n");
              }//recessive
            }
            fos.append("\n");
          }

          if (MList_Start_array != null)
          {
            if (marker_list_index < MList_End_array.size())
            {
              if (Header_Name.compareTo(MList_End_array.get(marker_list_index).toString()) == 0) {
                add_marker = false;
                marker_list_index++;
              }
            }
          }

          if (CovList_Start_array != null)
          {
              if (covariate_list_index < CovList_End_array.size())
              {
                  if (Header_Name.compareTo(CovList_End_array.get(covariate_list_index).toString()) == 0) {
                      add_covariate = false;
                      covariate_list_index++;
                  }
              }
          }


        }
      }

      //Write marker list
      if (MList_Start_array != null)
      {
        for(int ml=0;ml<MList_Start_array.size();ml++)
        {
            fos.append("marker_list, start = ");
            fos.append("\""+MList_Start_array.get(ml)+"\"");
            fos.append(", end = ");
            fos.append("\""+MList_End_array.get(ml)+"\"");
            fos.append("\n");
        }
      }

      //Write covariate list
      if (CovList_Start_array != null)
      {
        for(int ml=0;ml<CovList_Start_array.size();ml++)
        {
            fos.append("covariate_list, start = ");
            fos.append("\""+CovList_Start_array.get(ml)+"\"");
            fos.append(", end = ");
            fos.append("\""+CovList_End_array.get(ml)+"\"");
            fos.append("\n");
        }
      }
      fos.append("\n");
      fos.append("}");
      fos.append("\n\n");

      if (m.hasValue("marker.allele_delimiter") &&  m.hasValue("marker.allele_missing")) {
        fos.append("marker" + "\n");
        fos.append("{");
        fos.append("\n");

        if (m.hasValue("marker.allele_delimiter"))
          fos.append("allele_delimiter=" + "\"" +  m.getValue("marker.allele_delimiter").toString() + "\"" + "\n");
        if (m.hasValue("marker.allele_missing"))
          fos.append("allele_missing=" + "\"" + m.getValue("marker.allele_missing").toString() + "\"" + "\n");

        if (m.getValue("marker.allele_frequency") != null) {
          fos.append("allele_frequency");
          if (m.getValue("marker.allele_frequency.minimum") != null) {
            fos.append(",minimum = " + "\""+ m.getValue("marker.allele_frequency.minimum").toString()+ "\"");
            fos.append(",minimum = " + "\""+ m.getValue("marker.allele_frequency.maximum").toString()+ "\"");
          }
          else if (m.getValue("marker.allele_frequency.complement") != null) {
            fos.append(",complement");
          }
          else if (m.getValue("marker.allele_frequency.equal") != null) {
            fos.append(",equal");
          }
        }
        fos.append("\n");

        if (m.hasValue("marker.useascov")) {
            if (m.hasValue("marker.useascov.covmode"))
                fos.append("covariate_function = " + "\"" +
                           m.getValue("marker.useascov.covmode") + "\"");

            if (m.hasValue("marker.useascov.covallele"))
                fos.append(", base_allele = " + "\"" +
                           m.getValue("marker.useascov.covallele")+ "\"");

            if (m.hasValue("marker.useascov.hemizygote")) {
                fos.append(", allow_hemizygote = " + "\"" +
                           m.getValue("marker.useascov.hemizygote") + "\"\n");
            }
        }

        fos.append("}");
      }

      fos_trait.close();

      if (trait&&trait_fileinfo)
      {
        temp_internal_trait = new IconNode(temp_trait_blocknode, "Trait File");
        internalnode.add(temp_internal_trait);
        m.setValue("internalnode", internalnode);

        traitlist.add(temp_trait_blocknode);
        traitfile_count++;
      }
      else
      {
         temp_trait_file.delete();
      }

    Vector temp_para_vector = new Vector();
    temp_para_vector.add(new Boolean(true));
    temp_para_vector.add(fos);
    temp_para_vector.add(Covariate_array);
    temp_para_vector.add(Trait_array);
    temp_para_vector.add(Phenotype_array);
    temp_para_vector.add(Marker_array);
    temp_para_vector.add(Allele_array);
    temp_para_vector.add(TraitMarker_array);
    temp_para_vector.add(String_array);

    temp_para_vector.add(MList_Start_array);
    temp_para_vector.add(MList_End_array);

    temp_para_vector.add(CovList_Start_array);
    temp_para_vector.add(CovList_End_array);

    return temp_para_vector;
  }

  public void Write_Parameter_File() throws Exception{
    Vector All_Covariate_array = new Vector();
    Vector All_Trait_array = new Vector();
    Vector All_Phenotype_array = new Vector();
    Vector All_Marker_array = new Vector();
    Vector All_Allele_array = new Vector();
    Vector All_TraitMarker_array = new Vector();
    Vector All_String_array = new Vector();
    Vector All_Mlist_Start_array = new Vector();
    Vector All_MList_End_array = new Vector();

    Vector All_Covlist_Start_array = new Vector();
    Vector All_CovList_End_array = new Vector();

    MakeParameterStep3 m3 = (MakeParameterStep3) deck.getComponent("makepara3");
    DataCollectionModel m = (DataCollectionModel) m3.getDataModel();
    String filename = m3.pName.getText();
    String filepath = (String) m3.getDataModel().getValue("info.DirPath") + filename;

    File parameterfile = new File(filepath);

    IconNode internalnode = (IconNode) m.getValue("internalnode");
    NodeInfo parafilenode = new NodeInfo(parameterfile.getName(), "Parameter File", parameterfile);
    IconNode filenode = new IconNode(parafilenode, "Parameter File");
    internalnode.add(filenode);
    m.setValue("internalnode", internalnode);
    parafilenode.setModel(new PropertyDataModel());
    m.setValue("ParameterFileNode", (IconNode)filenode);

      FileWriter fos = new FileWriter(parameterfile);

      for (int ii = 0; ii < paralist.size(); ii++) {
        Vector temp = (Vector) paralist.get(ii);

        if(((Boolean)temp.get(0)).booleanValue())
        {
          StringBuffer each_block = (StringBuffer)temp.get(1);

          boolean result_multi = false;
          int multi_pedi_size = multi_pedi_list.size();
          if(multi_pedi_size == 1)
          {
            boolean temp1 = ((Boolean)multi_pedi_list.get(0)).booleanValue();
            result_multi = temp1;
          }
          else
          {
            for(int i=0;i<multi_pedi_size-1;i++)
            {
              boolean temp1 = ((Boolean)multi_pedi_list.get(i)).booleanValue();
              boolean temp2 = ((Boolean)multi_pedi_list.get(i+1)).booleanValue();
              if(temp1 || temp2)
              {
                result_multi = true;
                break;
              }
            }
          }

          if(parameterfile_count!=0 && result_multi)
            each_block.insert(8, ", file = \""+internal_pedilist.get(ii).toString() + "\"");

          fos.write(each_block.toString());
          fos.write("\n");

          if (temp.get(2) != null) {
            Vector list = (Vector)temp.get(2);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_Covariate_array.add(tempcov);
            }
          }
          if (temp.get(3) != null) {
            Vector list = (Vector)temp.get(3);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_Trait_array.add(tempcov);
            }
          }
          if (temp.get(4) != null) {
            Vector list = (Vector)temp.get(4);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_Phenotype_array.add(tempcov);
            }
          }
          if (temp.get(5) != null) {
            Vector list = (Vector)temp.get(5);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_Marker_array.add(tempcov);
            }
          }
          if (temp.get(6) != null) {
            Vector list = (Vector)temp.get(6);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_Allele_array.add(tempcov);
            }
          }
          if (temp.get(7) != null) {
            Vector list = (Vector)temp.get(7);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_TraitMarker_array.add(tempcov);
            }
          }
          if (temp.get(8) != null) {
            Vector list = (Vector)temp.get(8);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_String_array.add(tempcov);
            }
          }
          if (temp.get(9) != null) {
            Vector list = (Vector)temp.get(9);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_Mlist_Start_array.add(tempcov);
            }
          }
          if (temp.get(10) != null) {
            Vector list = (Vector)temp.get(10);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_MList_End_array.add(tempcov);
            }
          }
          if (temp.get(11) != null) {
            Vector list = (Vector)temp.get(11);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_CovList_End_array.add(tempcov);
            }
          }
          if (temp.get(12) != null) {
            Vector list = (Vector)temp.get(12);
            for (int count = 0; count < list.size(); count++) {
              String tempcov = list.get(count).toString();
              All_CovList_End_array.add(tempcov);
            }
          }

        }
      }

      fos.close();

    parafilenode.infomodel.setValue("Covariate_array", All_Covariate_array);
    parafilenode.infomodel.setValue("Trait_array", All_Trait_array);
    parafilenode.infomodel.setValue("Phenotype_array", All_Phenotype_array);
    parafilenode.infomodel.setValue("Marker_array", All_Marker_array);
    parafilenode.infomodel.setValue("Allele_array", All_Allele_array);
    parafilenode.infomodel.setValue("TraitMarker_array", All_TraitMarker_array);
    parafilenode.infomodel.setValue("String_array", All_String_array);
    parafilenode.infomodel.setValue("MList_Start_array", All_Mlist_Start_array);
    parafilenode.infomodel.setValue("MList_End_array", All_MList_End_array);

    parafilenode.infomodel.setValue("CovList_Start_array", All_Covlist_Start_array);
    parafilenode.infomodel.setValue("CovList_End_array", All_CovList_End_array);

    parafilenode.infomodel.setValue("pid_list", pid_list);
    parafilenode.infomodel.setValue("pedigree_id_name", m.getValue("pedigree_id_name").toString());
    parafilenode.infomodel.setValue("delimiters_name", m.getValue("delimiters_name").toString());
    parafilenode.infomodel.setValue("delimiter_mode", m.getValue("delimiter_mode").toString());

    FavoritesPanel.Parse_Parameter_File2(parafilenode);
  }
}
