package sage;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.event.*;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MakePedigreeStep2 extends WizardPanel implements ActionListener{

  int srow_table1 = -1;
  int srow_table3 = -1;
  int scol_table3 = -1;

  DataCollectionModel DataModel;

  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  JScrollPane jScrollPane3 = new JScrollPane();
  JButton FOButton = new JButton();
  JButton MOButton = new JButton();
  JButton FBButton = new JButton();
  JButton MHSButton = new JButton();
  JButton PHSButton = new JButton();
  JButton DPButton = new JButton();
  JButton RButton = new JButton();

  JTable jTable1 = new JTable();
  JTable jTable2 = new JTable();
  JTable jTable3 = new JTable();
  MyTableModel dm1 = new MyTableModel();
  MyTableModel dm2 = new MyTableModel();
  MyTableModel dm3 = new MyTableModel();

  Workbook  resultworkbook;
  Sheet resultsheet;
  Workbook workbook;
  MakePedigreeStep2 parent;

  File tempfile;

  Dialog2_Relationship relationshipDialog;
  FileOutputStream out;
  public MakePedigreeStep2() throws Exception
  {
       super("Specify parent-offspring and sibling pairs",
             "Highlight a pedigree identifier(PID) and individual identifier(ID) pair from each column on the left and click on the button "+
             "that correctly describes their relationship.");

       parent=this;

       relationshipDialog = new Dialog2_Relationship(this, "Relationship");

       FOButton.setIcon(new ImageIcon(MakePedigreeStep2.class.getResource("button1.png")));
       FOButton.setPreferredSize(new Dimension(100,25));
       FOButton.setText("Father-Offspring");
       FOButton.setMargin(new Insets(2, 5, 2, 2));
       FOButton.addActionListener(this);
       FOButton.setHorizontalAlignment(SwingConstants.LEFT);

       MOButton.setIcon(new ImageIcon(MakePedigreeStep2.class.getResource("button2.png")));
       MOButton.setPreferredSize(new Dimension(100,25));
       MOButton.setText("Mother-Offspring");
       MOButton.setMargin(new Insets(2, 5, 2, 2));
       MOButton.addActionListener(this);
       MOButton.setHorizontalAlignment(SwingConstants.LEFT);

       FBButton.setIcon(new ImageIcon(MakePedigreeStep2.class.getResource("button3.png")));
       FBButton.setPreferredSize(new Dimension(100,25));
       FBButton.setText("Full-Sibling");
       FBButton.setMargin(new Insets(2, 5, 2, 2));
       FBButton.addActionListener(this);
       FBButton.setHorizontalAlignment(SwingConstants.LEFT);

       MHSButton.setIcon(new ImageIcon(MakePedigreeStep2.class.getResource("button4.png")));
       MHSButton.setPreferredSize(new Dimension(100,25));
       MHSButton.setText("Maternal-Half-Sib");
       MHSButton.setMargin(new Insets(2, 5, 2, 2));
       MHSButton.addActionListener(this);
       MHSButton.setHorizontalAlignment(SwingConstants.LEFT);

       PHSButton.setIcon(new ImageIcon(MakePedigreeStep2.class.getResource("button5.png")));
       PHSButton.setPreferredSize(new Dimension(100,25));
       PHSButton.setText("Paternal-Half-Sib");
       PHSButton.setMargin(new Insets(2, 5, 2, 2));
       PHSButton.addActionListener(this);
       PHSButton.setHorizontalAlignment(SwingConstants.LEFT);

       RButton.setIcon(new ImageIcon(MakePedigreeStep2.class.getResource("Relationship.png")));
       RButton.setPreferredSize(new Dimension(100,25));
       RButton.setText("Set Relationship");
       RButton.setToolTipText("Set relationship.");

       RButton.setMargin(new Insets(2, 5, 2, 2));
       RButton.addActionListener(this);
       RButton.setHorizontalAlignment(SwingConstants.LEFT);

       DPButton.setIcon(new ImageIcon(MakePedigreeStep2.class.getResource("button6.png")));
       DPButton.setPreferredSize(new Dimension(100,25));
       DPButton.setText("Indicate Founders");
       DPButton.setToolTipText("Set remainders to founders.");
       DPButton.setMargin(new Insets(2, 5, 2, 2));
       DPButton.addActionListener(this);
       DPButton.setHorizontalAlignment(SwingConstants.LEFT);

       JPanel left = new JPanel();
       left.setLayout(new GridLayout(1, 2, 5, 5));
       left.setPreferredSize(new Dimension(230,250));
       left.setBorder(new EmptyBorder(5,8,5,5));
       left.setBorder(new TitledBorder("Input"));

       left.add(jScrollPane1,  BorderLayout.WEST);
       left.add(jScrollPane2,  BorderLayout.CENTER);
       jScrollPane1.getViewport().add(jTable1, null);
       jScrollPane2.getViewport().add(jTable2, null);

       JPanel middle = new JPanel();
       middle.setLayout(new BorderLayout());
       middle.setPreferredSize(new Dimension(100,240));
       middle.setBorder(new EmptyBorder(5,2,0,2));
       middle.setMaximumSize(new Dimension(100,240));

       JPanel mid_top = new JPanel();
       mid_top.setBorder(new EmptyBorder(10,0,0,0));
       mid_top.setLayout(new GridLayout(8, 1, 5, 4));
       mid_top.setPreferredSize(new Dimension(100, 235));
       mid_top.add(FOButton);
       mid_top.add(MOButton);
       mid_top.add(FBButton);
       mid_top.add(MHSButton);
       mid_top.add(PHSButton);
       mid_top.add( new JLabel(""));
       mid_top.add(RButton);
       mid_top.add(DPButton);

       JPanel mid_mid = new JPanel();
       mid_mid.setPreferredSize(new Dimension(100,5));

       middle.add(mid_top, BorderLayout.NORTH);
       middle.add(mid_mid, BorderLayout.CENTER);

       JPanel right = new JPanel();
       right.setLayout(new BorderLayout());
       right.setBorder(new EmptyBorder(5,5,5,8));
       right.setPreferredSize(new Dimension(230, 240));
       right.setBorder(new TitledBorder("Result"));
       right.add(jScrollPane3,  BorderLayout.CENTER);
       jScrollPane3.getViewport().add(jTable3, null);

       JPanel bottom = new JPanel();
       bottom.setPreferredSize(new Dimension(550, 5));

       JPanel top = new JPanel();
       top.setLayout(new GridBagLayout());

      top.add(left, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
               , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
               new Insets(10, 10, 10, 5), 210, 240));
       top.add(middle, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
               , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
               new Insets(10, 0, 10, 0), 50,240));
       top.add(right, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0
               , GridBagConstraints.CENTER, GridBagConstraints.BOTH,
               new Insets(10, 5, 10, 10), 230, 240));

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());
       panel.add("Center",top);
       panel.add("South", bottom);

       add("Center", panel);

       jTable1.setModel(dm1);
       jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       jTable1.setRowSelectionAllowed(true);

       jTable2.setModel(dm2);
       jTable2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
       jTable2.setRowSelectionAllowed(true);

       jTable3.setModel(dm3);
       jTable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       jTable3.setRowSelectionAllowed(true);

       canMoveForward = true;
       Frame1.desktop.add(relationshipDialog);
  }

  void init_table_data_fromExcel(String source_file_name, boolean header) throws Exception
  {
    init_data(source_file_name, header);
  }

  void init_data(String filename, boolean header) throws Exception
  {
      final String filepath = filename;
      final boolean exheader = header;
      set_data(filepath, exheader);
  }

  void set_data(String source_file_name, boolean header) throws Exception
  {
    try {
        Workbook workbook = null;

        if(source_file_name.endsWith(".xlsx"))
        {
            workbook = new XSSFWorkbook(new FileInputStream(source_file_name));
        }
        else
        {
            workbook = new HSSFWorkbook(new FileInputStream(source_file_name));
        }

        Sheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        Row  row = sheet.getRow(0);
        int cells = row.getPhysicalNumberOfCells();

        tempfile = File.createTempFile("pedigree","xls");
        out = new FileOutputStream(tempfile);

        resultworkbook = new HSSFWorkbook();
        resultsheet = resultworkbook.createSheet();
        Row r = null;
        Cell cell = null;

        Object input_data[][];
        Object output_data[][];

        int oj=0;

        if (header) { // exist header
            input_data = new Object[rows - 1][2];
            output_data = new Object[rows - 1][4];

        } else { // no header
            input_data = new Object[rows][2];
            output_data = new Object[rows][4];
        }

        for (int i = 0; i < rows ; i++) {
            r = resultsheet.createRow(i);
            row = sheet.getRow(i);

            for (int j = 0; j < cells+2; j++) {
                oj = j;
                if(j>3)
                    oj=j-2;
                cell = row.getCell(oj);

                String temp = null;
                switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    if(Double.toString((double) cell.getNumericCellValue()).endsWith(".0"))
                        temp = Integer.toString((int) cell.getNumericCellValue());
                    else
                        temp = Double.toString((double) cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    temp = cell.getStringCellValue();
                    break;
                default:
                    temp = cell.getStringCellValue();
                    break;
                }

                if(j==2 || j==3)
                    temp = ".";

                if (header)
                {
                    if(i==0)
                    {
                        if(j==2)
                            temp = "DAD";
                        else if(j==3)
                            temp = "MOM";
                    }
                    else
                    {
                        if(j==0)//PID
                        {
                            input_data[i-1][0] = temp;
                            output_data[i-1][0] = temp;
                        }
                        else if(j==1)//ID
                        {
                            input_data[i-1][1] = temp;
                            output_data[i-1][1] = temp;
                        }
                    }
                }
                else//no header
                {
                    if (j == 0) { //PID
                        input_data[i][0] = temp;
                        output_data[i][0] = temp;
                    } else if (j == 1) { //ID
                        input_data[i][1] = temp;
                        output_data[i][1] = temp;
                    }
                }

                r.createCell(j).setCellValue(temp);
            }
        }

    Object input_col[] = {"PID","ID"};
    Object output_col[] = {"PID","ID","Parent 1","Parent 2"};

    dm1.setDataVector(input_data, input_col);
    dm2.setDataVector(input_data, input_col);
    dm3.setDataVector(output_data, output_col);

    JWizardProject.wizardProject.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
} catch (Exception ex) {
    ex.printStackTrace();
}
   }

  public void actionPerformed(ActionEvent event)
  {
    Object source = event.getSource();

    // dummy parents
    if (source == DPButton)
    {
        Object options[] = new String[] {"Yes","No"};

        int n = JOptionPane.showOptionDialog(this,
                                             "Warning! All remaining individuals for whom no parental"
                                             +"\ninformation is specified will be classified as founders.\n"
                                             +"Do you wish to continue?",
                                             "Warning",
                                             JOptionPane.YES_NO_OPTION,
                                             JOptionPane.INFORMATION_MESSAGE,
                                             null,
                                             options,
                                             options[1]);

        if (n==0) {
            for (int i = 0; i < dm3.getRowCount(); i++) {
                Row row = resultsheet.getRow(i);
                // 2: dad, 3: mom
                if (dm3.getValueAt(i, 2) == null || dm3.getValueAt(i, 3) == null) {
                    dm3.setValueAt("[missing]", i, 2);
                    dm3.setValueAt("[missing]", i, 3);
                    try {
                        if(i>0)
                        {
                            row.getCell(2).setCellValue("."); //dad
                            row.getCell(3).setCellValue("."); //mom
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    if( source == RButton)
    {
      relationshipDialog.initData();
      relationshipDialog.showDialog();
      try {
        relationshipDialog.setSelected(true);
      }
      catch (java.beans.PropertyVetoException e) {}
    }
    else
    {
      int srow_table1 = jTable1.getSelectedRow();
      int srow_table2 = jTable2.getSelectedRow();
      int result_row = srow_table1;

      if(srow_table1 < 0 || srow_table2 < 0)
        return;

      String temp = new String();
      String peid = new String();
      String info = new String();

      temp = dm2.getValueAt(srow_table2, 1).toString();
      peid = dm2.getValueAt(srow_table2, 0).toString();
      info = info + "Pedigree" + peid + ":ID" + temp+"";

      if (source == FOButton) { // Father_offspring

      if(dm3.getValueAt(srow_table2, 3)!=null)//if mother is not null
        {
          String mom = dm3.getValueAt(srow_table2,3).toString();
          String dad = dm1.getValueAt(srow_table1,1).toString();

          if(mom.compareTo(dad)==0)
          {
            String message = "You may not specify the same person \nas both mother and father to an individual.";
            JOptionPane.showConfirmDialog(this, message,"Warning", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
            return;
          }
        }

        String message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":ID"+dm1.getValueAt(srow_table1,1) +
            " is the father of "+info +".\n"
            +"Is this correct?";

       int result = JOptionPane.showConfirmDialog(this, message,
                                                   "Set relationship", JOptionPane.YES_NO_OPTION,
                                                   JOptionPane.INFORMATION_MESSAGE);
        switch(result)
        {
          case JOptionPane.YES_OPTION :
              dm3.setValueAt(dm1.getValueAt(srow_table1,1), srow_table2, 2);

              try {
                resultsheet.getRow(srow_table2).getCell(2).setCellValue(Integer.parseInt(dm1.getValueAt(srow_table1,1).toString()));
              }
              catch (Exception e) {
                e.printStackTrace();
              }
            //}
            break;
          case JOptionPane.NO_OPTION:
        }
      }
      if (source == MOButton) { // Mother_offspring
        if(dm3.getValueAt(srow_table2, 2)!=null)//if father is not null
        {
          String dad = dm3.getValueAt(srow_table2,2).toString();
          String mom = dm1.getValueAt(srow_table1,1).toString();

          if(mom.compareTo(dad)==0)
          {
            String message = "You may not specify the same person \nas both mother and father to an individual. ";
            JOptionPane.showConfirmDialog(this, message,"Warning", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
            return;
          }
        }

        String message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":ID"+dm1.getValueAt(srow_table1,1) +
            " is the mother of "+info +".\n"
            +"Is this correct?";

        int result = JOptionPane.showConfirmDialog(this, message,
                                                   "Set relationship", JOptionPane.YES_NO_OPTION,
                                                   JOptionPane.INFORMATION_MESSAGE);
        switch(result)
        {
          case JOptionPane.YES_OPTION :
              dm3.setValueAt(dm1.getValueAt(srow_table1,1), srow_table2, 3);
              try {
                resultsheet.getRow(srow_table2).getCell(3).setCellValue(Integer.parseInt(dm1.getValueAt(srow_table1,1).toString()));
              }
              catch (Exception e) {
                e.printStackTrace();
            }
            break;
          case JOptionPane.NO_OPTION:
        }
      }

      if (source == FBButton) { // Full_sib
        String message = new String();
        String dad = new String();
        String mom = new String();

        if(dm3.getValueAt(srow_table1, 2)==null || dm3.getValueAt(srow_table1, 3)==null)
        {
          // no parent information both of them
          if(dm3.getValueAt(srow_table2, 2)==null || dm3.getValueAt(srow_table2, 3)==null)
          {
            message = "You must specify the individual's parents first.";
            JOptionPane.showConfirmDialog(this, message,"Warning", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
            return;
          }
          // table1 : null, table2 : not null
          if(dm3.getValueAt(srow_table2, 2)!=null && dm3.getValueAt(srow_table2, 3)!=null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)
                +" and "+info+" have same parents.\n"+"Is this correct?";

            mom = dm3.getValueAt(srow_table2,3).toString();
            dad = dm3.getValueAt(srow_table2,2).toString();
            result_row = srow_table1;
          }
        }
        else
        {
          // table1 : not null, table2 : null
          if(dm3.getValueAt(srow_table2, 2)==null || dm3.getValueAt(srow_table2, 3)==null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)
                +" and "+info+" have same parents.\n"+"Is this correct?";
            mom = dm3.getValueAt(srow_table1,3).toString();
            dad = dm3.getValueAt(srow_table1,2).toString();
            result_row = srow_table2;
          }
          // table1 : not null, table2 : not null
          if(dm3.getValueAt(srow_table2, 2)!=null && dm3.getValueAt(srow_table2, 3)!=null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)
                +" and "+info+" have same parents.\n"+"Is this correct?";
            mom = dm3.getValueAt(srow_table1,3).toString();
            dad = dm3.getValueAt(srow_table1,2).toString();
            result_row = srow_table2;

            if(mom.equals("[missing]") | dad.equals("[missing]"))
            {
              mom = dm3.getValueAt(srow_table2,3).toString();
              dad = dm3.getValueAt(srow_table2,2).toString();
              result_row = srow_table1;
            }

            if(mom.equals("[missing]") | dad.equals("[missing]"))
            {
              message = "You must specify the individual's parents first.";
                          JOptionPane.showConfirmDialog(this, message,"Warning", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
                          return;
            }
          }
        }

        int result = JOptionPane.showConfirmDialog(this, message,"Set relationship", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        switch(result)
        {
          case JOptionPane.YES_OPTION :
            dm3.setValueAt(dad, result_row, 2);
            dm3.setValueAt(mom, result_row, 3);

              try {
                resultsheet.getRow(result_row).getCell(2).setCellValue(Integer.parseInt(dad));
                resultsheet.getRow(result_row).getCell(3).setCellValue(Integer.parseInt(mom));
              }
              catch (Exception e) {
                e.printStackTrace();
              }
            break;
          case JOptionPane.NO_OPTION:
        }
      }

      if (source == MHSButton) { // Maternal_Half_Sib
        String message = new String();
        String mom = new String();

        if(dm3.getValueAt(srow_table1, 3)==null)
        {
          // table1 : null, table2 : null
          if(dm3.getValueAt(srow_table2, 3)==null)
          {
            message = "You must specify the individual's mother first.";
            JOptionPane.showConfirmDialog(this, message,"Warning", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
            return;
          }
          // table1 : null, table2 : not null
          if(dm3.getValueAt(srow_table2, 3)!=null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)+" and "+info+ " have same mother.\n"+"Is this correct?";
            mom = dm3.getValueAt(srow_table2,3).toString();
            result_row = srow_table1;
          }
        }
        else
        {
          // table1 : not null, table2 : null
          if(dm3.getValueAt(srow_table2, 3)==null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)+" and "+info+ " have same mother.\n"+"Is this correct?";
            mom = dm3.getValueAt(srow_table1,3).toString();
            result_row = srow_table2;
          }
          // table1 : not null, table2 : not null
          if(dm3.getValueAt(srow_table2, 3)!=null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)+" and "+info+ " have same mother.\n"+"Is this correct?";
            mom = dm3.getValueAt(srow_table1,3).toString();
            result_row = srow_table2;
          }
        }

        int result = JOptionPane.showConfirmDialog(this, message,"Set relationship", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
        switch(result)
        {
          case JOptionPane.YES_OPTION :
              dm3.setValueAt(mom, result_row, 3);
              try {
                resultsheet.getRow(result_row).getCell(3).setCellValue(Integer.parseInt(mom));
              }
              catch (Exception e) {
                e.printStackTrace();
              }
            break;
          case JOptionPane.NO_OPTION:
        }
      }

      if (source == PHSButton) { // Parternal_Half_Sib
        String message = new String();
        String dad = new String();

        if(dm3.getValueAt(srow_table1, 2)==null)
        {
          // table1 : null, table2 : null
          if(dm3.getValueAt(srow_table2, 2)==null)
          {
            message = "You must specify the individual's father first.";
            JOptionPane.showConfirmDialog(this, message,"Warning", JOptionPane.CLOSED_OPTION,JOptionPane.WARNING_MESSAGE);
            return;
          }
          // table1 : null, table2 : not null
          if(dm3.getValueAt(srow_table2, 2)!=null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)+" and "+info+ " have same father.\n"+"Is this correct?";
            dad = dm3.getValueAt(srow_table2,2).toString();
            result_row = srow_table1;
          }
        }
        else
        {
          // table1 : not null, table2 : null
          if(dm3.getValueAt(srow_table2, 2)==null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)+" and "+info+ " have same father.\n"+"Is this correct?";
            dad = dm3.getValueAt(srow_table1,2).toString();
            result_row = srow_table2;
          }
          // table1 : not null, table2 : not null
          if(dm3.getValueAt(srow_table2, 2)!=null)
          {
            message = "Pedigree"+dm1.getValueAt(srow_table1,0)+":Id"+dm1.getValueAt(srow_table1,1)+" and "+info+ " have same father.\n"+"Is this correct?";
            dad = dm3.getValueAt(srow_table1,2).toString();
            result_row = srow_table2;
          }
        }

        int result = JOptionPane.showConfirmDialog(this, message,
                                                   "Set relationship", JOptionPane.YES_NO_OPTION,
                                                   JOptionPane.INFORMATION_MESSAGE);
        switch(result)
        {
          case JOptionPane.YES_OPTION :
              dm3.setValueAt(dad, result_row, 2);
              try {
                resultsheet.getRow(result_row).getCell(2).setCellValue(Integer.parseInt(dad));
              }
              catch (Exception e) {
                e.printStackTrace();
              }
            break;
          case JOptionPane.NO_OPTION:
        }
      }
    }
  }
}

