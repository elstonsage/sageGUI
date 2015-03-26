package sage;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;
import java.io.*;
import jxl.*;
import jxl.write.*;

public class WizardNavigator2
    extends JPanel
    implements ActionListener, ChangeListener
{
  JButton back, next, cancel;
  Icon backIcon, nextIcon;
  DeckPanel deck;
  boolean cancel_clicked = false;
  boolean first = true;
  int regionnumber;
  DefaultListModel listmodel;
  Vector column = new Vector();
  int column_size=0;
  Vector linelist = new Vector();

  public WizardNavigator2(DeckPanel deck) throws Exception{
    this.deck = deck;
    setOpaque(true);
    setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    setBorder(new EdgeBorder(EdgeBorder.NORTH));
    JPanel nav = new JPanel();
    nav.setLayout(new BorderLayout());
    backIcon = new ImageIcon(WizardNavigator2.class.getResource("WestArrow.gif"));
    nav.add("West", back = new JButton("Back", backIcon));
    back.setMargin(new Insets(1, 5, 1, 10));
    back.setPreferredSize(new Dimension(75, 22));
    back.addActionListener(this);
    nextIcon = new ImageIcon(WizardNavigator2.class.getResource("EastArrow.gif"));
    nav.add("East", next = new JButton("Next", nextIcon));
    next.setHorizontalTextPosition(JButton.LEFT);
    next.setMargin(new Insets(1, 1, 1, 1));
    next.setPreferredSize(new Dimension(75, 22));
    next.addActionListener(this);
    add(nav);
    add(cancel = new JButton("Cancel"));
    cancel.setPreferredSize(new Dimension(75, 22));
    cancel.addActionListener(this);
    regionnumber = 1;
  }

  public void actionPerformed(ActionEvent event) {
    Object source = event.getSource();
    if (source == back) {
      setPanel(deck.getPrevious());
    }
    if (source == next) {

      if (deck.getCurrent().compareTo("makegenome0") == 0) {
        MakeGenomeStep0 m = (MakeGenomeStep0) deck.getComponent("makegenome0");
        MakeGenomeStep1 m1 = (MakeGenomeStep1) deck.getComponent("makegenome1");

        String filepath = m.pPath.getText();
        m1.pName2.requestFocus(true);

        if (m.jRadioButton1.isSelected()) //text file
          Make_Table_Data();
        else if (m.jRadioButton2.isSelected()) { //excel file
          try {
            Workbook workbook = Workbook.getWorkbook(new File(filepath));
            File tempxls = File.createTempFile("marker", "xls");
            WritableWorkbook newworkbook = Workbook.createWorkbook(tempxls,workbook);
            Make_Table_Data_fromExcel(newworkbook);
            newworkbook.write();
            newworkbook.close();
            tempxls.delete();
          }
          catch (Exception excel) {
            JOptionPane.showOptionDialog(this.getParent(),
                  "I/O exception encountered while attempting to read file '"+filepath+
                  "'.\nPlease check the file for correct formatting, file attributes, "+
                  "\nuser access privileges, possible data corruption, etc. ",
                  "Error",
                  JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
             return;
          }
        }
      }


      if (deck.getCurrent().compareTo("makegenome1")==0) {
      }

      else if (deck.getCurrent().compareTo("makegenome2")==0) {
        MakeGenomeStep2 m2 = (MakeGenomeStep2) deck.getComponent("makegenome2");
        MakeGenomeStep3 m3 = (MakeGenomeStep3) deck.getComponent("makegenome3");
        linelist = m2.rowdata;

        if (!m2.getDataModel().hasValue("MarkerPosition")
            || !m2.getDataModel().hasValue("MarkerColumn")) {
          JOptionPane.showOptionDialog(this.getParent(),
                           "You must specify the marker and position columns.",
                           "Error",
                           JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
          return;
        }

        GenomeTableData m_datamodel = (GenomeTableData)m3.jTable1.getModel();

        int markercount = m2.MarkerColumn;
        int positioncount = m2.PositionColumn;

        m_datamodel.setDefaultData();

        Vector type = (Vector)linelist.get(0);
        for(int i=0;i<type.size();i++)
        {
          String columntype = type.get(i).toString();
          if(columntype.compareTo("Marker")==0)
          {
            markercount = i;
          }
          if(columntype.compareTo("Position")==0)
          {
            positioncount = i;
          }
        }

        for(int i=1;i<linelist.size();i++)
        {
          Vector temp = (Vector)linelist.get(i);
          String name = temp.get(markercount).toString();
          String distance = temp.get(positioncount).toString();
          String region = "Region1";

          GenomeData g = new GenomeData(name, distance, region);
          m_datamodel.addData(g);
        }
        m3.set_table_data();
      }

      else if (deck.getCurrent().compareTo("makegenome4")==0) {
        MakeGenomeStep4 m4 = (MakeGenomeStep4) deck.getComponent(deck.getCurrent());

        String filename = m4.FileName.getText();
        MyInternalFrame mf = Frame1.mainFrame1.activeinframe;
        String dirpath = mf.projectdatamodel.getValue("info.DirPath").toString();

       File pp = new File(dirpath);
       File[] files = pp.listFiles();
       for (int i = 0; i < files.length; i++) {
         if (files[i].isFile()) {
           if (files[i].getName().compareTo(filename) == 0) {
             int n = JOptionPane.showOptionDialog(this.getParent(),
                 "The genome file named '" + filename +"' already exists."+
                 "\nYou must rename the file or specify a different location.",
                 "File name Error",
                 JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
             return;
           }
         }
       }

        Make_Genome_File();

        if (m4.getDataModel().hasValue("HeaderExist"))
            m4.getDataModel().removeValue("HeaderExist");

        getTopLevelAncestor().setVisible(false);
     }

      setPanel(deck.getNext());
    }
    if (source == cancel) {
      cancel_clicked = true;
      getTopLevelAncestor().setVisible(false);
    }

    JWizardGenome.wizardGenome.repaint();
    this.repaint();
    this.updateUI();
  }

  public void Make_Table_Data() {
    MakeGenomeStep0 m = (MakeGenomeStep0) deck.getComponent("makegenome0");
    MakeGenomeStep2 m2 = (MakeGenomeStep2) deck.getComponent("makegenome2");
    m2.clear_table_data();

    String source_file_path = m.pPath.getText();
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
      m.getDataModel().setValue("HeaderExist","header");
      isHeaderExist = true;
    }

    if(m.getDataModel().hasValue("HeaderExist"))
    {
      isHeaderExist = true;
    }

      int i = 0;
      try {
        FileReader fr = new FileReader(source_file_path);
        BufferedReader br = new BufferedReader(fr);
        String temp = new String();
        while ( (temp = br.readLine()) != null) {
          StringTokenizer st = new StringTokenizer(temp, delimiter);
          if (isHeaderExist) {
            if (i == 0) {
              while (st.hasMoreTokens()) {
                column.add(st.nextToken());
              }
            }
            else if (i > 0) {
              Vector eachlinecontents = new Vector();
              while (st.hasMoreTokens()) {
                eachlinecontents.add(st.nextToken());
              }
              column_size = eachlinecontents.size();
              linelist.add(eachlinecontents);
            }
            i++;
          }
          else {
            Vector eachlinecontents = new Vector();
            while (st.hasMoreTokens()) {
              eachlinecontents.add(st.nextToken());
             }
            column_size = eachlinecontents.size();
            linelist.add(eachlinecontents);
          }
        }
      }
      catch (Exception ex) {
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                     "I/O exception encountered while attempting to read file '"+source_file_path+
                                     "'.\nPlease check the file for correct formatting, file attributes, "+
                                     "\nuser access privileges, possible data corruption, etc. ",
                                     "Error",
                                     JOptionPane.CLOSED_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null, null, null);
        return;
      }

    if (!isHeaderExist) {
      for (int c = 0; c < column_size; c++) {
        column.add("Column " + c);
      }
    }

    Vector firstrow = new Vector(column.size());
    for (int ii = 0; ii < column.size(); ii++) {
      firstrow.add("Unspecified");
    }

    linelist.add(0, firstrow);
    linelist.trimToSize();
    column.trimToSize();
    m2.set_table_data(linelist, column);
  }

  public void Make_Table_Data_fromExcel(WritableWorkbook workbook) {
    MakeGenomeStep0 m = (MakeGenomeStep0) deck.getComponent("makegenome0");
    MakeGenomeStep2 m2 = (MakeGenomeStep2) deck.getComponent("makegenome2");
    m2.clear_table_data();

    WritableSheet newsheet = workbook.getSheet(0);
    int column_count = newsheet.getColumns();
    int row_count = newsheet.getRows();

    boolean isHeaderExist = false;
    if (m.getDataModel().hasValue("pedigree.HeaderExist"))
        isHeaderExist = true;

    for (int i = 0; i < row_count; i++) {
      if (i == 0) {
        if (isHeaderExist) {
          for (int j = 0; j < column_count; j++) {
            Cell a1 = newsheet.getCell(j, 0);
            column.add(a1.getContents());
          }
        }
        else {
          for (int j = 0; j < column_count; j++) {
            column.add("Column " + j);
          }
        }
      }
      else if (i > 0) {
        Vector eachlinecontents = new Vector();
        for (int j = 0; j < column_count; j++) {
          Cell a1 = newsheet.getCell(j, i);
          String temp;
          if (a1.getType() == CellType.LABEL)
          {
            LabelCell lc = (LabelCell) a1;
            temp = lc.getString();
          }
          if (a1.getType() == CellType.NUMBER)
          {
            NumberCell nc = (NumberCell) a1;
            temp = nc.getContents();
            if(temp.endsWith(".0"))
              temp = Integer.toString((int)nc.getValue());
          }
          else
          {
            temp = a1.getContents();
          }
          eachlinecontents.add(temp);
        }
        linelist.add(eachlinecontents);
      }
    }

    Vector firstrow = new Vector(column.size());
    for (int ii = 0; ii < column.size(); ii++) {
      firstrow.add("Unspecified");
    }

    column_size = column.size();

    linelist.add(0, firstrow);
    linelist.trimToSize();
    column.trimToSize();

    m2.set_table_data(linelist, column);
  }

  public void Make_Genome_File()
  {
    MakeGenomeStep4 m4 = (MakeGenomeStep4) deck.getComponent(deck.getCurrent());
    MakeGenomeStep3 m3 = (MakeGenomeStep3) deck.getComponent("makegenome3");

    DataCollectionModel datamodel = ((JWizard2) getParent()).getModel();
    String filename = m4.FileName.getText();
    MyInternalFrame mf = Frame1.mainFrame1.activeinframe;
    String dirpath = mf.projectdatamodel.getValue("info.DirPath").toString();

    String filepath = dirpath + System.getProperty("file.separator") + filename;

    GenomeTableData m_datamodel = (GenomeTableData)m3.jTable1.getModel();
    int datasize = m_datamodel.getRowCount();
    m_datamodel.setSort(2,true);
    m_datamodel.sortData();

    File genome = new File(filepath);

    try {
      FileWriter fos = new FileWriter(genome);

      fos.write("Genome");
      if(datamodel.hasValue("genomename"))
      {
        fos.write(" = "+"\""+datamodel.getValue("genomename").toString()+"\"");
      }
      if(datamodel.hasValue("mapfunction"))
      {
        fos.write(", map = "+"\""+datamodel.getValue("mapfunction").toString()+"\"");
      }
      fos.write("\n");
      fos.write("{");
      fos.write("\n");

      String beforeregion = m_datamodel.getValueAt(0,2).toString();
      String currentregion = m_datamodel.getValueAt(0,2).toString();
      String markername;
      String distance;

      String informationtype = datamodel.getValue("position").toString();

      for(int i=0;i<datasize-1;)
      {
        fos.write("region = "+"\""+currentregion+"\"");
        if(datamodel.hasValue(currentregion))
        {
          fos.write(", x_linked");
        }
        fos.write("\n");
        fos.write("{");
        fos.write("\n");

        int regioncount=0;

        while(beforeregion.compareTo(currentregion)==0)
        {
          currentregion = m_datamodel.getValueAt(i+regioncount,2).toString();
          regioncount++;
        }

        --regioncount;

        if(informationtype.compareTo("genetic position")==0)
        {
          fos.write("marker = "+"\""+"p-ter"+"\""+"\n");
          fos.write("distance = "+ "\""+m_datamodel.getValueAt(i,1).toString()+ "\""+"\n");

          for(int j=0;j<regioncount-1;j++)
          {
            markername = m_datamodel.getValueAt(i,0).toString();
            double distance1 = Double.parseDouble(m_datamodel.getValueAt(i,1).toString());
            double distance2 = Double.parseDouble(m_datamodel.getValueAt(i+1,1).toString());
            double result = distance2 - distance1;

            if(result == 0.0)
              result = 0.0001;
            distance = Double.toString(result);

            fos.write("marker = "+"\""+markername+"\""+"\n");
            fos.write("distance = "+ "\""+distance+ "\""+"\n");
            i++;
          }
          markername = m_datamodel.getValueAt(i,0).toString();
          fos.write("marker = "+"\""+markername+"\""+"\n");

        }
        else if(informationtype.compareTo("physical position")==0)
        {
          fos.write("marker = "+"\""+"p-ter"+"\""+"\n");
          fos.write("distance = "+ "\""+m_datamodel.getValueAt(i,1).toString()+ "\""+"\n");

          for(int j=0;j<regioncount-1;j++)
          {
            markername = m_datamodel.getValueAt(i,0).toString();
            double distance1 = Double.parseDouble(m_datamodel.getValueAt(i,1).toString());
            double distance2 = Double.parseDouble(m_datamodel.getValueAt(i+1,1).toString());
            double result = distance2 - distance1;

            result = result/1000000;
            if(result == 0.0)
              result = 0.0001;
            distance = Double.toString(result);

            fos.write("marker = "+"\""+markername+"\""+"\n");
            fos.write("distance = "+ "\""+distance+ "\""+"\n");
            i++;
          }
          markername = m_datamodel.getValueAt(i,0).toString();
          fos.write("marker = "+"\""+markername+"\""+"\n");
        }
        else
        {
          for(int j=0;j<regioncount-1;j++)
          {
            markername = m_datamodel.getValueAt(i,0).toString();
            String distance1 = m_datamodel.getValueAt(i,1).toString();

            fos.write("marker = "+"\""+markername+"\""+"\n");
            fos.write(informationtype+" = "+ "\""+distance1+ "\""+"\n");
            i++;
          }
          markername = m_datamodel.getValueAt(i,0).toString();
          fos.write("marker = "+"\""+markername+"\""+"\n");
        }

        fos.write("}");
        fos.write("\n");

        i++;
        beforeregion = m_datamodel.getValueAt(i,2).toString();
      }

      fos.write("}");
      fos.write("\n");
      fos.close();
    }
    catch (Exception x) {
      x.printStackTrace();
    }

    NodeInfo filend = new NodeInfo(filename, "Genome File", genome);
    IconNode filenode = new IconNode(filend, "Genome File");
    mf.addObject(filenode, mf.internalnode, true);
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



}
