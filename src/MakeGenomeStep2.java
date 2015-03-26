package sage;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;

public class MakeGenomeStep2 extends WizardPanel2 implements ItemListener{
  JTextField pName2;
  Vector rowdata;
  Vector columnname;
  JLabel jLabel1 = new JLabel();
  JScrollPane jScrollPane1 = new JScrollPane();
  DefaultListModel listModel = new DefaultListModel();
  JList jList1 = new JList(listModel);
  JComboBox comboBox = new JComboBox();
  static DefaultTableModel dm;
  DataCollectionModel DataModel;
  MyTable jTable1= new MyTable();
  int MarkerColumn = 0;
  int PositionColumn = 0;

  GenomeDialog1 Gdialog;

  public MakeGenomeStep2()
  {
       super("Specification", "Set the marker and position columns.");

       Gdialog = new GenomeDialog1(Frame1.mainFrame1,"Specification",true);
       Gdialog.setLocationRelativeTo(this);
       dm = new DefaultTableModel();

       JPanel top = new JPanel();
       top.setPreferredSize(new Dimension(360, 15));

       jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

       JPanel middle = new JPanel();
       middle.setLayout(new BorderLayout());
       middle.add(jScrollPane1, BorderLayout.CENTER);

       middle.setPreferredSize(new Dimension(360,250));
       middle.setBorder(new EmptyBorder(5,10,8,10));

       JPanel bottom = new JPanel();
       bottom.setPreferredSize(new Dimension(360, 15));

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());
       panel.add("North", top);
       panel.add("Center", middle);
       panel.add("South", bottom);

       add("Center", panel);

       comboBox.addItem("Unspecified");
       comboBox.addItem("Marker");
       comboBox.addItem("Position");

       comboBox.addItemListener(this);
       canMoveForward = true;
  }

  public void itemStateChanged(ItemEvent e) {
    if(e.getStateChange() == e.SELECTED)
    {
      String petName = (String) comboBox.getSelectedItem();
       int sindex = jTable1.getSelectedColumn();
       String HeaderName = new String();
       if(sindex <0)
         return;
       else
         HeaderName = dm.getColumnName(sindex);

       if (petName.compareTo("Marker")==0) {
         getDataModel().setValue("MarkerColumn", HeaderName);
         MarkerColumn = sindex;
       }
       if (petName.compareTo("Position")==0) {
         for(int i=1;i<rowdata.size();i++)
         {
           try{
             String value = dm.getValueAt(i, sindex).toString();
             double distance1 = Double.parseDouble(value);
           }
           catch(Exception ex)
           {
             comboBox.setSelectedIndex(0);
             JOptionPane.showOptionDialog(this.getParent(),
                  "The position column may contain only numeric characters.",
                  "Error",
                  JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
              return;
           }
         }

         Gdialog.set_dataModel(DataModel);
         getDataModel().setValue("MarkerPosition", HeaderName);
         PositionColumn = sindex;
         Gdialog.setVisible(true);
       }
    }
  }

  void set_table_data(Vector data, Vector col)
  {
    this.rowdata = data;
    this.columnname = col;
    dm.setDataVector(this.rowdata, this.columnname);

    DataModel = (DataCollectionModel)getDataModel();

    jTable1.renderer = true;
    jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jTable1.setColumnSelectionAllowed(true);
    jTable1.setIntercellSpacing(new Dimension(1, 0));
    jTable1.setRowSelectionAllowed(true);
    jTable1.setModel(dm);
    jTable1.setRowHeight(20);

    EachRowEditor rowEditor = new EachRowEditor(jTable1, col.size());
    rowEditor.setEditorAt(0, new DefaultCellEditor(comboBox));

    for (int i = 0; i < this.columnname.size(); i++) {
      jTable1.getColumn(this.columnname.get(i)).setCellEditor(rowEditor);
      jTable1.getColumn(this.columnname.get(i)).setPreferredWidth(80);
    }

    jScrollPane1.getViewport().add(jTable1, null);
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
