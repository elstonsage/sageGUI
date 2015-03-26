package sage;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import javax.swing.text.DefaultEditorKit;

public class MakeSQLStep2 extends WizardPanel3 implements ActionListener{
  JButton ExecuteButton;
  JButton ResetButton;
  JButton SaveButton = new JButton();
  JButton UndoButton;
  JTextArea pQuary;
  JLabel jLabel1 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  Vector rowdata;
  Vector columnname;
  JTable jTable1;

  DataCollectionModel DataModel;

  Vector stored_data_header = new Vector();
  Vector stored_data_row = new Vector();

  int selected_col = -1;
  int selected_row = -1;

  JProgressBar bar = new JProgressBar(0, 100);
  ResultSetTableModelFactory factory;
  boolean init_db = false;
  ResultSetTableModel current_rstm;

  boolean undo=false;
  Vector query_list = new Vector();

  JLabel record_count = new JLabel("");
  DecimalFormat df = new DecimalFormat("#,##0");

  JSplitPane jSplitPane1 = new JSplitPane();

  JPopupMenu jPopupMenu = new JPopupMenu();

  JMenuItem jCopyMenuItem;
  JMenuItem jCutMenuItem;
  JMenuItem jPasteMenuItem;

  public MakeSQLStep2() throws Exception
  {
      super("SQL Query",
            "Please enter the valid SQL query to modify your data file.");

       jTable1 = new JTable() {
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

       pQuary = new JTextArea("SELECT * FROM PEDIGREE;");
       pQuary.setFont(new java.awt.Font("Dialog", Font.PLAIN, 11));
       pQuary.setLineWrap(true);
       jScrollPane2.getViewport().add(pQuary, null);


       MouseListener popupListener = new PopupListener();
       pQuary.addMouseListener(popupListener);

       pQuary.addKeyListener(new KeyAdapter () {
           // This method is invoked when the user hits ENTER in the field
           public void keyTyped(KeyEvent e) {
               if(e.getID() == KeyEvent.KEY_TYPED)
               {
                  if(e.getKeyChar() == '\n')
                  {
                     final String sql = pQuary.getText().toUpperCase().trim();
                      Thread T = new Thread() {
                          public void run() {
                                  displayQueryResults(sql, true);
                                  if(!sql.startsWith("SELECT"))
                                  {
                                      displayQueryResults("SELECT * FROM PEDIGREE;", true);
                                  }
                          }
                      };
                      T.start();
                      T = null;


                  }
               }
           }
       });


       bar.setStringPainted(true);
       bar.setIndeterminate(true);

       jCopyMenuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
       jCutMenuItem = new JMenuItem(new DefaultEditorKit.CutAction());
       jPasteMenuItem = new JMenuItem(new DefaultEditorKit.PasteAction());

       jCopyMenuItem.setText("Copy");
       jCopyMenuItem.setRequestFocusEnabled(false);

       jCutMenuItem.setText("Cut");
       jCutMenuItem.setRequestFocusEnabled(false);

       jPasteMenuItem.setText("Paste");
       jPasteMenuItem.setRequestFocusEnabled(false);

       jPopupMenu.add(jCopyMenuItem);
       jPopupMenu.add(jCutMenuItem);
       jPopupMenu.add(jPasteMenuItem);

       jCopyMenuItem.addActionListener(this);
       jCutMenuItem.addActionListener(this);
       jPasteMenuItem.addActionListener(this);

       SaveButton.setPreferredSize(new Dimension(80, 20));
       SaveButton.setText("Apply");
       SaveButton.setToolTipText("Save the displayed query into the current project.");
       SaveButton.setMargin(new Insets(0,2,0,2));
       SaveButton.addActionListener(this);

       jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

       JPanel middle = new JPanel();
       middle.setLayout(new BorderLayout());
       middle.add(jScrollPane1, BorderLayout.CENTER);
       middle.setPreferredSize(new Dimension(580,150));
       middle.setBorder(new EmptyBorder(10,10,0,10));

       JPanel bottom = new JPanel();
       bottom.setPreferredSize(new Dimension(580, 155));
       bottom.setBorder(new EmptyBorder(5,10,10,10));
       bottom.setLayout(new BorderLayout(0,0));
       JLabel pLabel = new JLabel("SQL");
       pLabel.setVerticalAlignment(JLabel.TOP);
       pLabel.setPreferredSize(new Dimension(30,80));

       ExecuteButton = new JButton("Execute");
       ExecuteButton.setToolTipText("Execute the displayed query.");
       ExecuteButton.setPreferredSize(new Dimension(80,20));
       ExecuteButton.setMargin(new Insets(0,2,0,2));
       ExecuteButton.addActionListener(this);

       ResetButton = new JButton("Reset");
       ResetButton.setToolTipText("Restore the original query.");
       ResetButton.setPreferredSize(new Dimension(80, 20));
       ResetButton.setMargin(new Insets(0, 2, 0, 2));
       ResetButton.addActionListener(this);

       UndoButton = new JButton("Undo");
       UndoButton.setToolTipText("Restore the previous query.");
       UndoButton.setPreferredSize(new Dimension(80, 20));
       UndoButton.setMargin(new Insets(0, 2, 0, 2));
       UndoButton.addActionListener(this);

       JPanel bottom_right = new JPanel();
       bottom_right.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 3));
       bottom_right.setPreferredSize(new Dimension(85,105));
       bottom_right.setBorder(new EmptyBorder(-2,5,0,0));
       bottom_right.add(ExecuteButton);
       bottom_right.add(UndoButton);
       bottom_right.add(ResetButton);
       bottom_right.add(SaveButton);

      JPanel bottom_left_top = new JPanel();
       bottom_left_top.setLayout(new BorderLayout());
       bottom_left_top.setPreferredSize(new Dimension(30,23));
       bottom_left_top.setBorder(new EmptyBorder(5,0,0,0));

       JLabel rLabel = new JLabel("");
       rLabel.setPreferredSize(new Dimension(30,20));
       record_count.setHorizontalAlignment(JLabel.LEADING);

       record_count.setPreferredSize(new Dimension(150,20));

       JPanel task = new JPanel(new BorderLayout());
       task.setPreferredSize(new Dimension(465,23));
       task.add(record_count, BorderLayout.WEST);
       task.add(bar, BorderLayout.CENTER);

       bottom_left_top.add(rLabel, BorderLayout.WEST);
       bottom_left_top.add(task, BorderLayout.CENTER);

       JPanel bottom_left_center = new JPanel();
       bottom_left_center.setPreferredSize(new Dimension(495,80));
       bottom_left_center.setBorder(new EmptyBorder(2,0,0,0)); //top, left, bottom, right
       bottom_left_center.setLayout(new BorderLayout());
       bottom_left_center.add(pLabel, BorderLayout.WEST);
       bottom_left_center.add(jScrollPane2, BorderLayout.CENTER);

       JPanel bottom_left = new JPanel();
       bottom_left.setLayout(new BorderLayout());
       bottom_left.setPreferredSize(new Dimension(495,105));
       bottom_left.add(bottom_left_center, BorderLayout.CENTER);
       bottom_left.add(bottom_left_top, BorderLayout.SOUTH);

       bottom.add(bottom_left, BorderLayout.CENTER);
       bottom.add(bottom_right, BorderLayout.EAST);

       jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
       jSplitPane1.setTopComponent(middle);
       jSplitPane1.setBottomComponent(bottom);
       jSplitPane1.setDividerLocation(180);
       jSplitPane1.setDividerSize(2);

       bottom.setMinimumSize(new Dimension(580, 105));

       jSplitPane1.setContinuousLayout(true);

       add("Center", jSplitPane1);
       canMoveForward = true;
    }

  public void actionPerformed(ActionEvent event)
  {
      Object ob = event.getSource();

      if(ob == ResetButton)
      {
          pQuary.setText("SELECT * FROM PEDIGREE;");
      }

      else if(ob == SaveButton)
      {
          try {
            SaveTableAsPedigreeFile();
          }
          catch (Exception exe) {
            exe.printStackTrace();
            JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                         "I/O exception encountered while attempting to write data file"+
                                         "\nReported error number is '" + exe +"'",
                                         "Error",
                                         JOptionPane.CLOSED_OPTION,
                                         JOptionPane.WARNING_MESSAGE, null, null, null);
            return;
        }

      }
      else if (ob == UndoButton)
      {
        Thread T = new Thread() {
          public void run() {

            close_Database();
            init_Database();

            try {
              if (query_list.size() > 0) {

                query_list.remove(query_list.size() - 1);
                query_list.trimToSize();

                boolean need_refresh = false;
                for (int i = 0; i < query_list.size(); i++) {
                  undo = true;
                  String each_query = query_list.get(i).toString();

                  displayQueryResults(each_query, false);
                  Thread.sleep(500);

                  if (i == query_list.size() - 1) {
                    pQuary.setText(each_query);

                    if (!each_query.startsWith("SELECT"))
                      need_refresh = true;
                  }
                }
                if (need_refresh) {
                  displayQueryResults("SELECT * FROM PEDIGREE;", true);
                  Thread.sleep(500);
                }

                if (query_list.size() == 0) {
                  pQuary.setText("SELECT * FROM PEDIGREE;");
                }

                undo = false;
              }
            } catch (InterruptedException e) {
            }

          }
        };

        T.start();
        T = null;

      }

      else if (ob == ExecuteButton)
      {
          final String sql = pQuary.getText().toUpperCase().trim();

          if (sql.length() > 0) {
              Thread T = new Thread() {
                  public void run() {
                      try {
                          displayQueryResults(sql, true);
                          Thread.sleep(500);
                          if(!sql.startsWith("SELECT"))
                          {
                              displayQueryResults("SELECT * FROM PEDIGREE;", true);
                              Thread.sleep(500);
                          }
                      } catch (InterruptedException e) {
                      }
                  }
              };
              T.start();
              T = null;
          }
      }
  }


  /**
   * This method uses the supplied SQL query string, and the
   * ResultSetTableModelFactory object to create a TableModel that holds
   * the results of the database query.  It passes that TableModel to the
   * JTable component for display.
   **/
  public synchronized void displayQueryResults(final String q, final boolean update) {
      final Updater updater = new Updater(bar);
      factory.updater = updater;

      if(update)
            updater.update(10);

      try {
          current_rstm = factory.getResultSetTableModel(q, update);
      } catch (SQLException ex) {
          ex.printStackTrace();
          JOptionPane.showMessageDialog(MakeSQLStep2.this.getParent(),
                                        ex.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
          return;
      }


      record_count.setText("Record count : "+df.format(current_rstm.getRowCount()));

      if(undo == false && init_db == false)
      {
          if(q.startsWith("SELECT"))
          {
              if(q.indexOf("WHERE")>0 || q.indexOf("ORDER")>0)
              {
                  query_list.add(q);
              }
          }
          else
          {
              query_list.add(q);
          }
      }

      for (int i = 0; i < query_list.size(); i++) {
          String each_query = query_list.get(i).toString();
      }

      if (update)
          updater.update(80);

      jTable1.setModel(current_rstm);

      if (update)
          updater.update(90);

          JViewport jvp = new JViewport();
          RowNumberHeader rh = new RowNumberHeader(jTable1);
          jvp.setView(rh);
          jScrollPane1.setRowHeader(jvp);

          jTable1.validate();

          if (update)
              updater.update(100);
  }

  void SaveTableAsPedigreeFile() throws Exception
  {
      File save = Frame1.mainFrame1.activeinframe.projectFile;

      Saveas_Dialog savedialog = new Saveas_Dialog(JWizardSQL.wizardSQL, "Name data file", true);
      savedialog.pName.setText("pedigree.dat");
      savedialog.pName.requestFocus();
      savedialog.DirPath.setText(save.getParentFile().getPath());

      savedialog.setLocationRelativeTo(Frame1.mainFrame1.activeinframe);
      savedialog.setVisible(true);

      if (savedialog.rename) {
          String filepath = savedialog.DirPath.getText();
          String filename = savedialog.pName.getText();

          String newpedigreepath = filepath + System.getProperty("file.separator") + filename;
          File newpedigreefile = new File(newpedigreepath);

          JTable table = jTable1;
          JTableHeader header = (JTableHeader) table.getTableHeader();

          ResultSetTableModel rm = (ResultSetTableModel)jTable1.getModel();
          ResultSet rs = rm.results;
          int colNo = rm.getColumnCount();
          int rowNo = rm.getRowCount();

          String delimiter = "\t";
          FileWriter fos = new FileWriter(newpedigreefile);

          String tempheader;
          for (int i = 0; i < table.getColumnCount(); i++) {
              tempheader = header.getColumnModel().getColumn(i).getHeaderValue().toString();
              fos.write(tempheader);
              fos.write(delimiter);
          }
          fos.write("\n");

          for (int i = 0; i < rowNo; i++) {
              for (int j = 0; j < colNo; j++) {
                  fos.write(String.valueOf(jTable1.getValueAt(i,j)));
                  fos.write(delimiter);
              }
              fos.write("\n");
          }
          fos.close();

          if(savedialog.jCheckBox1.isSelected())
          {
              NodeInfo filend = new NodeInfo(filename, "Pedigree File", new File(newpedigreepath));
              IconNode fileico = new IconNode(filend, "Pedigree File");

              Frame1.mainFrame1.activeinframe.treeModel.insertNodeInto(fileico,
                      Frame1.mainFrame1.activeinframe.internalnode,
                      Frame1.mainFrame1.activeinframe.internalnode.getChildCount());
          }
      }
  }

  void set_table_data(Vector data, Vector col)
  {
    rowdata = data;
    columnname = col;

    DataModel = (DataCollectionModel)getDataModel();

    UIManager.put("Table.focusCellHighlightBorder", new EmptyBorder(1, 1, 1, 1));

    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable1.setColumnSelectionAllowed(true);
    jTable1.setIntercellSpacing(new Dimension(2, 0));
    jTable1.setRowSelectionAllowed(true);
    jTable1.setShowHorizontalLines(true);
    jTable1.setShowVerticalLines(true);
    jScrollPane1.getViewport().add(jTable1, null);

    JViewport jvp = new JViewport();
    RowNumberHeader rh = new RowNumberHeader(jTable1);
    jvp.setView(rh);
    jScrollPane1.setRowHeader(jvp);

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
      try {
        String url = "jdbc:hsqldb:"+ path+name;
        factory = new ResultSetTableModelFactory("org.hsqldb.jdbcDriver", url, "sa", "");

        String Field = new String();
        for(int  i=0;i<columnname.size();i++)
        {
            String temp = columnname.get(i).toString()+" VARCHAR(50)";
            Field = Field + ", "+temp;
        }
        Field = Field.replaceFirst(",","").trim();

        init_db = true;

        final String sql_PEDIGREE = "CREATE TABLE PEDIGREE("+Field+")";
                displayQueryResults(sql_PEDIGREE, false);

        for(int  i=0;i<rowdata.size();i++)
        {
            Vector eachlinedata = (Vector)rowdata.get(i);
            String Value = new String();
            for(int j=0;j<eachlinedata.size();j++)
            {
              String each_temp = eachlinedata.get(j).toString();
              if(each_temp.length()<0)
                each_temp = " ";
                String temp = "'"+eachlinedata.get(j).toString()+"'";
                Value = Value + ", "+temp;
            }
            Value = Value.replaceFirst(",","").trim();
            final String sql_PEDIGREE_VALUE = "INSERT INTO PEDIGREE VALUES ("+Value+")";

            displayQueryResults(sql_PEDIGREE_VALUE, false);
        }

        bar.setIndeterminate(false);
        bar.setValue(0);

        displayQueryResults("SELECT * FROM PEDIGREE;", true);
        init_db = false;
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
      try {
          bar.setIndeterminate(true);
          bar.setValue(0);

          if(factory!=null)
              factory.close();

          String path = Frame1.mainFrame1.activeinframe.projectPath;
          String name = Frame1.mainFrame1.activeinframe.projectFile.getName();
          name = name.substring(0, name.indexOf("."));

          File pp = new File(path);
          File[] files = pp.listFiles();
          for (int i = 0; i < files.length; i++) {
              if (files[i].isFile()) {
                  if (files[i].getName().startsWith(name))
                  {
                      if(files[i].getName().endsWith("properties")
                         || files[i].getName().endsWith("data")
                         || files[i].getName().endsWith("script")) {
                       files[i].delete();
                   }
                  }
              }
          }


      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  class PopupListener
      extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
      if(e.isPopupTrigger())
          jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
  }


}
