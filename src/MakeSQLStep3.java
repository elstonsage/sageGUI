package sage;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class MakeSQLStep3 extends WizardPanel3 implements ActionListener, ItemListener{
  JButton ExecuteButton;
  JTextField pQuary;
  JLabel jLabel1 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  Vector rowdata;
  Vector columnname;
  JTable jTable1= new JTable();

  DataCollectionModel DataModel;
  static DefaultTableModel dm;

  Connection connection = null;
  Statement stmt = null;
  JPopupMenu jPopupMenu1 = new JPopupMenu();
  JPopupMenu jPopupMenu2 = new JPopupMenu();
  JMenuItem jMenuItemH_INSERT = new JMenuItem("Insert");
  JMenuItem jMenuItemH_DELETE = new JMenuItem("Delete");
  JMenuItem jMenuItemH_CUT = new JMenuItem("Cut");
  JMenuItem jMenuItemH_COPY = new JMenuItem("Copy");
  JMenuItem jMenuItemH_PASTE = new JMenuItem("Paste");

  JMenuItem jMenuItemR_INSERT = new JMenuItem("Insert");
  JMenuItem jMenuItemR_DELETE = new JMenuItem("Delete");
  JMenuItem jMenuItemR_CUT = new JMenuItem("Cut");
  JMenuItem jMenuItemR_COPY = new JMenuItem("Copy");
  JMenuItem jMenuItemR_PASTE = new JMenuItem("Paste");

  Vector stored_data_header = new Vector();
  Vector stored_data_row = new Vector();

  int selected_col = -1;
  int selected_row = -1;

    public MakeSQLStep3() throws Exception
  {
       super("Specification",
             "Set each column's name, type of variable and specific characteristics. Red writing implies necessary information.");

       dm = new DefaultTableModel();

       jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

       JPanel middle = new JPanel();
       middle.setLayout(new BorderLayout());
       middle.add(jScrollPane1, BorderLayout.CENTER);

       middle.setPreferredSize(new Dimension(550,230));
       middle.setBorder(new EmptyBorder(5,8,8,8));

       JPanel bottom = new JPanel();
       bottom.setPreferredSize(new Dimension(550, 25));
       bottom.setLayout(new BorderLayout(5,10));
       JLabel pLabel = new JLabel("SQL");
       pQuary = new JTextField();
       pQuary.setPreferredSize(new Dimension(120,20));
       ExecuteButton = new JButton("Execute");
       ExecuteButton.setPreferredSize(new Dimension(60,20));
       ExecuteButton.setMargin(new Insets(2,2,2,2));
       ExecuteButton.addActionListener(this);

       bottom.add(pLabel, BorderLayout.WEST);
       bottom.add(pQuary, BorderLayout.CENTER);
       bottom.add(ExecuteButton, BorderLayout.EAST);

       jPopupMenu1.add(jMenuItemH_CUT);
       jPopupMenu1.add(jMenuItemH_COPY);
       jPopupMenu1.add(jMenuItemH_PASTE);
       jPopupMenu1.addSeparator();
       jPopupMenu1.add(jMenuItemH_INSERT);
       jPopupMenu1.add(jMenuItemH_DELETE);

       jPopupMenu2.add(jMenuItemR_CUT);
       jPopupMenu2.add(jMenuItemR_COPY);
       jPopupMenu2.add(jMenuItemR_PASTE);
       jPopupMenu2.addSeparator();
       jPopupMenu2.add(jMenuItemR_INSERT);
       jPopupMenu2.add(jMenuItemR_DELETE);

       jMenuItemH_CUT.addActionListener(this);
       jMenuItemH_COPY.addActionListener(this);
       jMenuItemH_PASTE.addActionListener(this);
       jMenuItemH_INSERT.addActionListener(this);
       jMenuItemH_DELETE.addActionListener(this);

       jMenuItemR_CUT.addActionListener(this);
       jMenuItemR_COPY.addActionListener(this);
       jMenuItemR_PASTE.addActionListener(this);
       jMenuItemR_INSERT.addActionListener(this);
       jMenuItemR_DELETE.addActionListener(this);

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());
       panel.add("Center",middle);
       panel.add("South", bottom);

       JPanel center = new JPanel();
       center.setLayout(new UnitLayout());
       center.add(panel);
       add("Center", center);
    }

  public void itemStateChanged(ItemEvent e)
  {

  }

  public void actionPerformed(ActionEvent event)
  {
      Object ob = event.getSource();

      if(ob == jMenuItemH_INSERT)
      {
          EditableHeader header = (EditableHeader) jTable1.getTableHeader();
          TableColumnModel tcm = header.getColumnModel();

          Vector newcolumn=new Vector();
          for (int i = 0; i < jTable1.getColumnCount(); i++) {
              String cname1 = tcm.getColumn(i).getHeaderValue().toString();
              newcolumn.add(cname1);
          }

          for(int i=0;i<newcolumn.size();i++)
          {
              if(i==selected_col)
              {
                  newcolumn.insertElementAt("Column", selected_col);
              }
          }

          for(int i=0;i<rowdata.size();i++)
          {
              Vector eachline = (Vector)rowdata.get(i);

              for(int j=0;j<eachline.size();j++)
              {
                  if(j==selected_col)
                  {
                      eachline.insertElementAt(" ", selected_col);
                  }
              }

              rowdata.setElementAt(eachline, i);
          }

          columnname = newcolumn;

          dm.setDataVector(rowdata, columnname);

          TableColumnModel columnModel = jTable1.getColumnModel();
          EditableHeader h = new EditableHeader(columnModel);
          jTable1.setTableHeader(h);

          jTable1.validate();
      }

      if(ob == jMenuItemH_DELETE)
      {
          EditableHeader header = (EditableHeader) jTable1.getTableHeader();
          TableColumnModel tcm = header.getColumnModel();

          Vector newcolumn=new Vector();
          for (int i = 0; i < jTable1.getColumnCount(); i++) {
              String cname1 = tcm.getColumn(i).getHeaderValue().toString();
              newcolumn.add(cname1);
          }

          for(int i=0;i<newcolumn.size();i++)
          {
              if(i==selected_col)
              {
                  newcolumn.removeElementAt(selected_col);
              }
          }

          for(int i=0;i<rowdata.size();i++)
          {
              Vector eachline = (Vector)rowdata.get(i);

              for(int j=0;j<eachline.size();j++)
              {
                  if(j==selected_col)
                  {
                      eachline.removeElementAt(selected_col);
                  }
              }

              rowdata.setElementAt(eachline, i);
          }

          columnname = newcolumn;
          dm.setDataVector(rowdata, columnname);

          TableColumnModel columnModel = jTable1.getColumnModel();
          EditableHeader h = new EditableHeader(columnModel);
          jTable1.setTableHeader(h);

          jTable1.validate();
      }

      if(ob == jMenuItemR_INSERT)
      {
          Vector new_row = new Vector();

          Vector eachline = (Vector) rowdata.get(0);

          for (int j = 0; j < eachline.size(); j++) {
              new_row.add(" ");
          }

          rowdata.insertElementAt(new_row, selected_row);
          dm.setDataVector(rowdata, columnname);

          jTable1.validate();
      }

      if(ob == jMenuItemR_DELETE)
      {
          rowdata.removeElementAt(selected_row);
          dm.setDataVector(rowdata, columnname);

          jTable1.validate();
      }

      if(ob == jMenuItemH_CUT)
      {
          stored_data_header.removeAllElements();

          EditableHeader header = (EditableHeader) jTable1.getTableHeader();
          TableColumnModel tcm = header.getColumnModel();

          Vector newcolumn=new Vector();
          for (int i = 0; i < jTable1.getColumnCount(); i++) {
              String cname1 = tcm.getColumn(i).getHeaderValue().toString();
              newcolumn.add(cname1);
          }

          for(int i=0;i<newcolumn.size();i++)
          {
              if(i==selected_col)
              {
                  stored_data_header.add(newcolumn.get(i));
                  newcolumn.removeElementAt(selected_col);
              }
          }

          for(int i=0;i<rowdata.size();i++)
          {
              Vector eachline = (Vector)rowdata.get(i);

              for(int j=0;j<eachline.size();j++)
              {
                  if(j==selected_col)
                  {
                      stored_data_header.add(eachline.get(j));
                      eachline.removeElementAt(selected_col);
                  }
              }

              rowdata.setElementAt(eachline, i);
          }

          columnname = newcolumn;
          dm.setDataVector(rowdata, columnname);

          TableColumnModel columnModel = jTable1.getColumnModel();
          EditableHeader h = new EditableHeader(columnModel);
          jTable1.setTableHeader(h);

          jTable1.validate();
      }

      if(ob == jMenuItemH_COPY)
      {
          stored_data_header.removeAllElements();

          EditableHeader header = (EditableHeader) jTable1.getTableHeader();
          TableColumnModel tcm = header.getColumnModel();

          Vector newcolumn=new Vector();
          for (int i = 0; i < jTable1.getColumnCount(); i++) {
              String cname1 = tcm.getColumn(i).getHeaderValue().toString();
              newcolumn.add(cname1);
          }

          for(int i=0;i<newcolumn.size();i++)
          {
              if(i==selected_col)
              {
                  stored_data_header.add(newcolumn.get(i));
              }
          }

          for(int i=0;i<rowdata.size();i++)
          {
              Vector eachline = (Vector)rowdata.get(i);

              for(int j=0;j<eachline.size();j++)
              {
                  if(j==selected_col)
                  {
                      stored_data_header.add(eachline.get(j));
                  }
              }
          }
      }

      if(ob == jMenuItemH_PASTE)
      {
          if(stored_data_header.size()>0)
          {
              int new_data_index = 0;

              EditableHeader header = (EditableHeader) jTable1.getTableHeader();
              TableColumnModel tcm = header.getColumnModel();

              Vector newcolumn=new Vector();
              for (int i = 0; i < jTable1.getColumnCount(); i++) {
                  String cname1 = tcm.getColumn(i).getHeaderValue().toString();
                  newcolumn.add(cname1);
              }

              for(int i=0;i<newcolumn.size();i++)
              {
                  if(i==selected_col)
                  {
                      String new_data = stored_data_header.get(new_data_index).toString();
                      newcolumn.insertElementAt(new_data, selected_col);
                      new_data_index++;
                  }
              }

              for(int i=0;i<rowdata.size();i++)
              {
                  Vector eachline = (Vector)rowdata.get(i);

                  for(int j=0;j<eachline.size();j++)
                  {
                      if(j==selected_col)
                      {
                          String new_data = stored_data_header.get(new_data_index).toString();
                          eachline.insertElementAt(new_data, selected_col);
                          new_data_index++;
                      }
                  }
                  rowdata.setElementAt(eachline, i);
              }

              columnname = newcolumn;
              dm.setDataVector(rowdata, columnname);

              TableColumnModel columnModel = jTable1.getColumnModel();
              EditableHeader h = new EditableHeader(columnModel);
              jTable1.setTableHeader(h);

              jTable1.validate();
          }
      }

      if(ob == jMenuItemR_CUT)
      {
          stored_data_row.removeAllElements();

          Vector eachline = (Vector) rowdata.get(selected_row);
          for (int j = 0; j < eachline.size(); j++) {
              stored_data_row.add(eachline.get(j));
          }
          stored_data_row = (Vector)rowdata.get(selected_row);

          dm.removeRow(selected_row);
          jTable1.validate();
      }

      if(ob == jMenuItemR_COPY)
      {
          stored_data_row.removeAllElements();

          Vector eachline = (Vector) rowdata.get(selected_row);
          for (int j = 0; j < eachline.size(); j++) {
              stored_data_row.add(eachline.get(j));
          }
      }

      if(ob == jMenuItemR_PASTE)
      {
          if(stored_data_row.size()>0)
          {
              dm.insertRow(selected_row, stored_data_row);
              jTable1.validate();
          }
      }

      if(ob == ExecuteButton)
      {
          String sql = pQuary.getText().trim();
          if(sql.length()>0)
          {
              if(sql.indexOf("INSERT")>=0)
              {
                 int s_index = sql.indexOf("(");
                 int e_index = sql.indexOf(")");

                 String sub_value = sql.substring(s_index+1, e_index-1);

                 Vector linevalue = new Vector();
                 StringTokenizer st = new StringTokenizer(sub_value, ",");
                 while (st.hasMoreTokens()) {
                     String each_value = st.nextToken().replaceAll("'","").trim();
                     linevalue.add(each_value);
                 }
                 try{
                     stmt.executeUpdate(sql);
                     rowdata.addElement(linevalue);
                     jTable1.updateUI();
                 }
                 catch (SQLException e) {
                     e.printStackTrace();
                 }
                 catch (Exception e) {
                     e.printStackTrace();
                 }
              }
          }
      }
  }

  void set_table_data(Vector data, Vector col)
  {
    this.rowdata = data;
    this.columnname = col;
    dm.setDataVector(this.rowdata, this.columnname);

    DataModel = (DataCollectionModel)getDataModel();

    UIManager.put("Table.focusCellHighlightBorder", new EmptyBorder(1, 1, 1, 1));

    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable1.setIntercellSpacing(new Dimension(2, 0));
    jTable1.setShowHorizontalLines(true);
    jTable1.setShowVerticalLines(true);
    jTable1.setModel(dm);

    EachRowEditor rowEditor = new EachRowEditor(jTable1, columnname.size());

    for (int i = 0; i < this.columnname.size(); i++) {
      jTable1.getColumn(this.columnname.get(i)).setCellEditor(rowEditor);
      jTable1.getColumn(this.columnname.get(i)).setPreferredWidth(80);
    }

    TableColumnModel columnModel = jTable1.getColumnModel();
    EditableHeader h = new EditableHeader(columnModel);
    jTable1.setTableHeader(h);
    JTableHeader header = jTable1.getTableHeader();
    header.addMouseListener(new TableHeaderMouseAdapter());

    jTable1.setColumnSelectionAllowed(true);
    jScrollPane1.getViewport().add(jTable1, null);

    JViewport jvp = new JViewport();
    RowNumberHeader rh = new RowNumberHeader(jTable1);
    rh.addMouseListener(new TableRowMouseAdapter());
    jvp.setView(rh);
    jScrollPane1.setRowHeader(jvp);
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

  void init_Database()
  {
      String path = Frame1.mainFrame1.activeinframe.projectPath;
      try {
        Class.forName("org.hsqldb.jdbcDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:"
                                                 + path,
                                                 "sa",
                                                 "");
        stmt = connection.createStatement();

        String Field = new String();
        for(int  i=0;i<columnname.size();i++)
        {
            String temp = columnname.get(i).toString()+" VARCHAR(50)";
            Field = Field + ", "+temp;
        }
        Field = Field.replaceFirst(",","").trim();

        String sql_PEDIGREE = "CREATE TABLE PEDIGREE("+Field+")";
        stmt.executeUpdate(sql_PEDIGREE);

        for(int  i=0;i<rowdata.size();i++)
        {
            Vector eachlinedata = (Vector)rowdata.get(i);
            String Value = new String();
            for(int j=0;j<eachlinedata.size();j++)
            {
                String temp = "'"+eachlinedata.get(j).toString()+"'";
                Value = Value + ", "+temp;
            }
            Value = Value.replaceFirst(",","").trim();

            String sql_PEDIGREE_VALUE = "INSERT INTO PEDIGREE VALUES ("+Value+")";
            stmt.executeUpdate(sql_PEDIGREE_VALUE);
        }
      }
      catch (ClassNotFoundException e) {
          e.printStackTrace();        // Could not find the database driver
      }
      catch (SQLException e) {
          e.printStackTrace();        // Could not connect to the database
    }
  }

  void close_Database()
  {
      String SQL_SHUTDOWN = "SHUTDOWN";
      try {
          stmt.executeUpdate(SQL_SHUTDOWN);
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

  private class TableHeaderMouseAdapter extends MouseAdapter {

      public TableHeaderMouseAdapter() {
      }

      public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
      }

      public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }
      public void maybeShowPopup(MouseEvent e) {
          selected_col = jTable1.columnAtPoint(e.getPoint());
          selected_row = jTable1.rowAtPoint(e.getPoint());

          if (selected_row == 0 && selected_row != -1) {
              jTable1.clearSelection();

              jTable1.setRowSelectionInterval(0, jTable1.getRowCount()-1);
              jTable1.setColumnSelectionInterval(selected_col,selected_col);

              if (e.isPopupTrigger())
                  jPopupMenu1.show(e.getComponent(), e.getX(), e.getY());
          }
      }
  }

  private class TableRowMouseAdapter extends MouseAdapter {

    public TableRowMouseAdapter() {
    }

    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
  }
    public void maybeShowPopup(MouseEvent e) {
        selected_col = jTable1.columnAtPoint(e.getPoint());
        selected_row = jTable1.rowAtPoint(e.getPoint());

        if (selected_col == 0 && selected_col != -1) {
            jTable1.clearSelection();

            jTable1.setRowSelectionInterval(selected_row,selected_row);
            jTable1.setColumnSelectionInterval(0,jTable1.getColumnCount()-1);

            if (e.isPopupTrigger())
                jPopupMenu2.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}


}
