package sage;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import com.borland.jbcl.layout.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
/*public class SQLDialog1 extends JDialog {
    JPanel panel1 = new JPanel();
    BorderLayout borderLayout1 = new BorderLayout();

    public SQLDialog1(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        try {
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jbInit();
            pack();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public SQLDialog1() {
        this(new Frame(), "SQLDialog1", false);
    }

    private void jbInit() throws Exception {
        panel1.setLayout(borderLayout1);
        getContentPane().add(panel1);
    }
}*/
public class SQLDialog1 extends JDialog implements ActionListener{
  JButton ExecuteButton;
  JTextField pQuary = new JTextField();

  JScrollPane jScrollPane1 = new JScrollPane();
  Vector rowdata;
  Vector columnname;
  JTable jTable1;

  //DataCollectionModel DataModel;
  DefaultTableModel dm;
  //MyDynamicTableModel dm;

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
  JPanel east = new JPanel();
  JPanel panel = new JPanel();
  JPanel top = new JPanel();
  JPanel middle = new JPanel();
  JPanel bottom = new JPanel();
  JLabel pLabel = new JLabel("SQL");
  EdgeBorder eb= new EdgeBorder(EdgeBorder.SOUTH);
  JLabel jLabel1 = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JButton AddButton = new JButton();
  JButton jButton2 = new JButton();

  JPopupMenu jAddPopupMenu = new JPopupMenu();
  JMenuItem jAddEmptyCol = new JMenuItem("Empty col");
  JMenuItem jAddDataCol = new JMenuItem("Data col");

  ImageIcon arrow_null;
  ImageIcon arrow;

  public SQLDialog1(Frame owner, String title, boolean modal) {
      super(owner, title, modal);
      try {
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          jbInit();
          pack();
      } catch (Exception exception) {
          exception.printStackTrace();
      }
  }

  public SQLDialog1() {
      this(new Frame(), "SQLDialog1", false);
  }

  private void jbInit() throws Exception {
    this.setResizable(true);
    jAddPopupMenu.add(jAddEmptyCol);
    jAddPopupMenu.add(jAddEmptyCol);
    jAddEmptyCol.addActionListener(this);
    jAddEmptyCol.addActionListener(this);
    MouseListener popupListener = new PopupListener();
    AddButton.addMouseListener(popupListener);

    arrow_null = new ImageIcon(SQLDialog1.class.getResource("arrow_null.png"));
    arrow = new ImageIcon(SQLDialog1.class.getResource("arrow.png"));


      jTable1 = new JTable() {
          //  Place cell in edit mode when it 'gains focus'
          public void changeSelection(int row, int column, boolean toggle, boolean extend) {
              super.changeSelection(row, column, toggle, extend);
              if (editCellAt(row, column))
                  getEditorComponent().requestFocusInWindow();
          }

          //  Select the text when the cell starts editing
          //  a) text will be replaced when you start typing in a cell
          //  b) text will be selected when you use F2 to start editing
          //  c) text will be selected when double clicking to start editing
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

          //  Select the text when the cell starts editing
          //  a) text will be replaced when you start typing in a cell
          //  b) text will be selected when you use F2 to start editing
          //  c) caret is placed at end of text when double clicking to start editing
          public Component xxxprepareEditor(TableCellEditor editor, int row, int column) {
              Component comp = super.prepareEditor(editor, row, column);
                 if (comp instanceof JTextComponent) {
                  ((JTextField) comp).selectAll();
              }
              return comp;
          }
      };
      dm = new DefaultTableModel();

      jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
      jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

      panel.setLayout(new BorderLayout());
      jLabel1.setBorder(eb);

      this.setSize(new Dimension(600,500));
      top.setBackground(Color.white);
      top.setPreferredSize(new Dimension(600,40));
      top.setLayout(borderLayout1);

      middle.setLayout(new BorderLayout());
    AddButton.setPreferredSize(new Dimension(85, 25));
    AddButton.setText("Insert col");
    AddButton.setHorizontalTextPosition(SwingConstants.LEFT);
    AddButton.setIcon(arrow_null);
    AddButton.setIconTextGap(2);

    jButton2.setMaximumSize(new Dimension(83, 25));
    jButton2.setPreferredSize(new Dimension(85, 25));
    jButton2.setText("Insert row");
    middle.add(jScrollPane1, BorderLayout.CENTER);
      middle.setPreferredSize(new Dimension(600,230));
      middle.setBorder(new EmptyBorder(10,10,10,5)); //top, left, bottom, right

      east.setPreferredSize(new Dimension(100,500));
      east.setBorder(new EmptyBorder(10,5,10,10)); //top, left, bottom, right

      bottom.setPreferredSize(new Dimension(600,50));
      bottom.setLayout(new BorderLayout(5,10));
      pQuary.setPreferredSize(new Dimension(100,20));
      ExecuteButton = new JButton("Execute");
      ExecuteButton.setPreferredSize(new Dimension(100,20));
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

      panel.add("Center",middle);
      panel.add("South", bottom);
      panel.add("East", east);
    east.add(AddButton, null);
    east.add(jButton2, null);
      panel.add("North", top);
      top.add(jLabel1,  BorderLayout.SOUTH);

      getContentPane().add(panel);
    }

  public void actionPerformed(ActionEvent event)
  {
      Object ob = event.getSource();

      if(ob == jMenuItemH_INSERT)//header insert
      {
          insertCol();
      }

      if(ob == jMenuItemH_DELETE)//header delete
      {
          delCol();
      }

      if(ob == jMenuItemR_INSERT)//row insert
      {
          insertRow();
      }

      if(ob == jMenuItemR_DELETE)//row delete
      {
          if(selected_row >= 0)
              delRow();
      }

      if(ob == jMenuItemH_CUT)
      {
          cutCol();
      }

      if(ob == jMenuItemH_COPY)
      {
          copyCol();
      }

      if(ob == jMenuItemH_PASTE)
      {
          pasteCol();
      }

      if(ob == jMenuItemR_CUT)
      {
          delRow();
      }

      if(ob == jMenuItemR_COPY)
      {
          copyRow();
      }

      if(ob == jMenuItemR_PASTE)
      {
          if (stored_data_row.size() > 0)
              pasteRow();
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

                 String sub_value = sql.substring(s_index+1, e_index);

                 Vector linevalue = new Vector();
                 StringTokenizer st = new StringTokenizer(sub_value, ","); // each line tokenizer by tab delimit
                 while (st.hasMoreTokens()) {
                     String each_value = st.nextToken().replaceAll("'","").trim();
                     linevalue.add(each_value);
                 }

                 System.out.println(linevalue);
                 try{
                     stmt.executeUpdate(sql);
                     rowdata.addElement(linevalue);
                     jTable1.updateUI();
                 }
                 catch (SQLException e) {
                     System.out.println("Vendor's error code : "+e.getErrorCode());
                     System.out.println("SQLState value : "+e.getSQLState());
                     e.printStackTrace();
                 }
                 //catch (Exception e) {
                 //    e.printStackTrace();
              //}
              }
          }
      }
  }

  void insertCol()
  {
      EditableHeader header = (EditableHeader) jTable1.getTableHeader();
      TableColumnModel tcm = header.getColumnModel();

      Vector newcolumn = new Vector();
      for (int i = 0; i < jTable1.getColumnCount(); i++) {
          String cname1 = tcm.getColumn(i).getHeaderValue().toString();
          newcolumn.add(cname1);
      }

      for (int i = 0; i < newcolumn.size(); i++) {
          if (i == selected_col) {
              newcolumn.insertElementAt("Column", selected_col);
          }
      }

      for (int i = 0; i < rowdata.size(); i++) {
          Vector eachline = (Vector) rowdata.get(i);

          for (int j = 0; j < eachline.size(); j++) {
              if (j == selected_col) {
                  eachline.insertElementAt(" ", selected_col);
              }
          }
          rowdata.setElementAt(eachline, i);
      }

      columnname = newcolumn;

      dm.setDataVector(rowdata, columnname);

      TableColumnModel columnModel = jTable1.getColumnModel();
      EditableHeader h = new EditableHeader(columnModel); //tcm);
      jTable1.setTableHeader(h);

      jTable1.validate();
  }

  void delCol()
  {
      EditableHeader header = (EditableHeader) jTable1.getTableHeader();
      TableColumnModel tcm = header.getColumnModel();

      Vector newcolumn = new Vector();
      for (int i = 0; i < jTable1.getColumnCount(); i++) {
          String cname1 = tcm.getColumn(i).getHeaderValue().toString();
          newcolumn.add(cname1);
      }

      for (int i = 0; i < newcolumn.size(); i++) {
          if (i == selected_col) {
              newcolumn.removeElementAt(selected_col);
          }
      }

      for (int i = 0; i < rowdata.size(); i++) {
          Vector eachline = (Vector) rowdata.get(i);

          for (int j = 0; j < eachline.size(); j++) {
              if (j == selected_col) {
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

  void insertRow()
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

  void cutCol()
  {
      stored_data_header.removeAllElements();

      EditableHeader header = (EditableHeader) jTable1.getTableHeader();
      TableColumnModel tcm = header.getColumnModel();

      Vector newcolumn = new Vector();
      for (int i = 0; i < jTable1.getColumnCount(); i++) {
          String cname1 = tcm.getColumn(i).getHeaderValue().toString();
          newcolumn.add(cname1);
      }

      for (int i = 0; i < newcolumn.size(); i++) {
          if (i == selected_col) {
              stored_data_header.add(newcolumn.get(i)); // store data before delete
              newcolumn.removeElementAt(selected_col);
          }
      }

      for (int i = 0; i < rowdata.size(); i++) {
          Vector eachline = (Vector) rowdata.get(i);

          for (int j = 0; j < eachline.size(); j++) {
              if (j == selected_col) {
                  stored_data_header.add(eachline.get(j)); // store data before delete
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

  void copyCol()
  {
      stored_data_header.removeAllElements();

      EditableHeader header = (EditableHeader) jTable1.getTableHeader();
      TableColumnModel tcm = header.getColumnModel();

      Vector newcolumn = new Vector();
      for (int i = 0; i < jTable1.getColumnCount(); i++) {
          String cname1 = tcm.getColumn(i).getHeaderValue().toString();
          newcolumn.add(cname1);
      }

      for (int i = 0; i < newcolumn.size(); i++) {
          if (i == selected_col) {
              stored_data_header.add(newcolumn.get(i)); // store data before delete
          }
      }

      for (int i = 0; i < rowdata.size(); i++) {
          Vector eachline = (Vector) rowdata.get(i);

          for (int j = 0; j < eachline.size(); j++) {
              if (j == selected_col) {
                  stored_data_header.add(eachline.get(j)); // store data before delete
              }
          }
      }
  }

  void pasteCol()
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
          EditableHeader h = new EditableHeader(columnModel);//tcm);
          jTable1.setTableHeader(h);

          jTable1.validate();
      }
  }

  void copyRow()
  {
      stored_data_row.removeAllElements();

      Vector eachline = (Vector) rowdata.get(selected_row);
      for (int j = 0; j < eachline.size(); j++) {
          stored_data_row.add(eachline.get(j));
      }
  }

  synchronized void delRow()
  {
      Thread T = new Thread()
      {
          public void run()
          {
              stored_data_row.removeAllElements();

              Vector eachline = (Vector) rowdata.get(selected_row);
              for (int j = 0; j < eachline.size(); j++) {
                  stored_data_row.add(eachline.get(j));
              }
              stored_data_row = (Vector) rowdata.get(selected_row);

              //System.out.println("before cut : "+dm.getRowCount());
              //System.out.println("before cut : "+jTable1.getRowCount());
              dm.removeRow(selected_row);
              //System.out.println("after cut : "+dm.getRowCount());
              //System.out.println("after cut : "+jTable1.getRowCount());

              jTable1.validate();
          }
      };

      T.start();
      T = null;
  }

  synchronized void pasteRow()
  {
      Thread T = new Thread()
      {
          public void run()
          {
              //System.out.println("before paste : " + dm.getRowCount());
              //System.out.println("before paste : " + jTable1.getRowCount());

              //SwingUtilities.invokeLater(new Runnable() {
              //    public void run() {
              dm.insertRow(selected_row, stored_data_row);
              //rowdata.insertElementAt(stored_data_row, selected_row);
              //    }
              // });

              //System.out.println("after paste: " + dm.getRowCount());
              //System.out.println("after paste : " + jTable1.getRowCount());

              jTable1.validate();
          }
      };

      T.start();
      T = null;
  }

  void set_table_data(Vector data, Vector col)
  {
    this.rowdata = data;
    this.columnname = col;
    dm.setDataVector(this.rowdata, this.columnname);

    UIManager.put("Table.focusCellHighlightBorder", new EmptyBorder(1, 1, 1, 1));

    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    //jTable1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    jTable1.setColumnSelectionAllowed(true);
    jTable1.setIntercellSpacing(new Dimension(2, 0));
    jTable1.setRowSelectionAllowed(true);
    //jTable1.setCellSelectionEnabled(false);
    jTable1.setShowHorizontalLines(true);
    jTable1.setShowVerticalLines(true);
    jTable1.setModel(dm);


    /*EachRowEditor rowEditor = new EachRowEditor(jTable1);

    for (int i = 0; i < this.columnname.size(); i++) {
      jTable1.getColumn(this.columnname.get(i)).setCellEditor(rowEditor);
      jTable1.getColumn(this.columnname.get(i)).setPreferredWidth(80);
    }*/

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
    jvp.setView(rh);//new RowNumberHeader(jTable1));
    jScrollPane1.setRowHeader(jvp);

   /* InputMap im = jTable1.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0);
    KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
    im.put(enter, im.get(tab));

    final Action oldTabAction = jTable1.getActionMap().get(im.get(tab));
    Action tabAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            oldTabAction.actionPerformed(e);
            JTable table = (JTable) e.getSource();
            int rowCount = table.getRowCount();
            int columnCount = table.getColumnCount();
            int row = table.getSelectedRow();
            int column = table.getSelectedColumn();

            while (!table.isCellEditable(row, column)) {
                column += 1;

                if (column == columnCount) {
                    column = 0;
                    row += 1;
                }

                if (row == rowCount) {
                    row = 0;
                }

                //  Back to where we started, get out.

                if (row == table.getSelectedRow()
                    && column == table.getSelectedColumn()) {
                    break;
                }
            }

            table.changeSelection(row, column, false, false);
        }
    };
    jTable1.getActionMap().put(im.get(tab), tabAction);*/


    init_Database();
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
      String name = Frame1.mainFrame1.activeinframe.projectFile.getName();
      name = name.substring(0, name.indexOf("."));
      //System.out.println(path);
      try {
        Class.forName("org.hsqldb.jdbcDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:"
                                                 + path+name, // filenames
                                                 "sa", // username
                                                 ""); // password
        stmt = connection.createStatement();

        String Field = new String();
        for(int  i=0;i<columnname.size();i++)
        {
            String temp = columnname.get(i).toString()+" VARCHAR(50)";
            Field = Field + ", "+temp;
        }
        Field = Field.replaceFirst(",","").trim();

        String sql_PEDIGREE = "CREATE TABLE PEDIGREE("+Field+")";
        //System.out.println("crate : "+sql_PEDIGREE);
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
            //System.out.println("insert : "+sql_PEDIGREE_VALUE);
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

  class PopupListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
      //AddButton.setBorder(BorderFactory.createLoweredBevelBorder());
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      //AddButton.setBorder(BorderFactory.createLoweredBevelBorder());
      maybeShowPopup(e);
    }

    public void mouseExited(MouseEvent e) {
      AddButton.setIcon(arrow_null);
    }

    private void maybeShowPopup(MouseEvent e) {
      AddButton.setIcon(arrow);
      jAddPopupMenu.show(e.getComponent(), 80, 0);

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

          if (selected_row == 0 && selected_row != -1) {//(e.getClickCount() == 1) &&
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

        System.out.println(selected_row);

        if (selected_col == 0 && selected_col != -1) {//(e.getClickCount() == 1) &&
            jTable1.clearSelection();

            jTable1.setRowSelectionInterval(selected_row,selected_row);
            jTable1.setColumnSelectionInterval(0,jTable1.getColumnCount()-1);

            if (e.isPopupTrigger())
                jPopupMenu2.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}
}
