package sage;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.event.*;

public class MakeGenomeStep3 extends WizardPanel2 implements ActionListener
{
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable();
  JLabel regionname = new JLabel();
  JButton setregionButton = new JButton();
  ImageIcon genomefile;

  JEditorPane explain = new JEditorPane();
  GenomeTableData m_data = new GenomeTableData();

        public MakeGenomeStep3() throws Exception
        {
             super("Region Information", "Select markers and name your region.");

             genomefile = new ImageIcon(MakeGenomeStep3.class.getResource("GENOME.PNG"));

             JPanel top = new JPanel();
             top.setPreferredSize(new Dimension(360, 15));

             JPanel buttonpanel = new JPanel();
             buttonpanel.setLayout(new BorderLayout());
             buttonpanel.setPreferredSize(new Dimension(100,250));
             buttonpanel.setBorder(new EmptyBorder(20,5,185,5));
             buttonpanel.add(setregionButton, BorderLayout.NORTH);

             String message = "<html><font size=\"3\" face=\"Dialog\">Select markers by clicking the first marker and then clicking the last marker while holding down the shift key "
                              +"(alternatively, the marker range can be selected by dragging the cursor over the markers). "+
                              "Then click the <b>Set Region </b> button and type in the region name for the selected markers.</font></html>";

                 explain.setContentType("text/html");
                 explain.setText(message);
                 explain.setFont(new Font("Dialog", Font.PLAIN, 10));
                 explain.setBorder(new EmptyBorder(0, 5, 20, 70));

             setregionButton.setText("Set Region");
             setregionButton.addActionListener(this);
             setregionButton.setMargin(new Insets(2,2,2,2));
              setregionButton.setPreferredSize(new Dimension(80,30));

             JPanel middle = new JPanel();
             middle.setLayout(new BorderLayout());
             middle.add(explain, BorderLayout.NORTH);
             middle.add(jScrollPane1, BorderLayout.CENTER);
             middle.add(buttonpanel, BorderLayout.EAST);
             middle.setPreferredSize(new Dimension(310,250));
             middle.setBorder(new EmptyBorder(0,10,8,2));

             explain.setBackground(new Color(236,233,216));

             JPanel bottom = new JPanel();
             bottom.setPreferredSize(new Dimension(360, 15));

             JPanel panel = new JPanel();
             panel.setLayout(new BorderLayout());
             panel.add("North",top);
             panel.add("Center",middle);
             panel.add("South", bottom);
             add("Center", panel);

              jTable1.setAutoCreateColumnsFromModel(false);
             jTable1.setColumnSelectionAllowed(false);
             jTable1.setIntercellSpacing(new Dimension(2, 2));
             jTable1.setRowSelectionAllowed(true);
             jTable1.setRowHeight(20);
             jTable1.setModel(m_data);

             for (int k = 0; k < GenomeTableData.m_columns.length; k++) {
               DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
               renderer.setHorizontalAlignment(GenomeTableData.m_columns[k].m_alignment);
               TableColumn column = new TableColumn(k, GenomeTableData.m_columns[k].m_width, renderer, null);
               column.setHeaderRenderer(createDefaultRenderer());
               jTable1.addColumn(column);
            }

            JTableHeader header = jTable1.getTableHeader();
            header.setUpdateTableInRealTime(true);
            header.addMouseListener(new ColumnListener());
            header.setReorderingAllowed(true);

             canMoveForward = true;
        }

        void set_table_data()
        {
          jScrollPane1.getViewport().add(jTable1, null);
        }

        protected TableCellRenderer createDefaultRenderer() {
          DefaultTableCellRenderer label = new DefaultTableCellRenderer()
          {
            public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
              if (table != null) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                  setForeground(header.getForeground());
                  setBackground(header.getBackground());
                  setFont(header.getFont());
                }
              }

              setText( (value == null) ? "" : value.toString());
              setBorder(UIManager.getBorder("TableHeader.cellBorder"));
              return this;
            }
          };
          label.setHorizontalAlignment(JLabel.CENTER);
          return label;
        }

        class ColumnListener  extends MouseAdapter {

          public void mouseClicked(MouseEvent e)
          {
            TableColumnModel colModel = jTable1.getColumnModel();
            int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
            int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

            if (modelIndex < 0)
              return;
            if (m_data.m_sortCol == modelIndex)
              m_data.m_sortAsc = !m_data.m_sortAsc;
            else
              m_data.m_sortCol = modelIndex;

            for (int i = 0; i < m_data.getColumnCount(); i++) {
              TableColumn column = colModel.getColumn(i);
              int index = column.getModelIndex();
              JLabel renderer = (JLabel) column.getHeaderRenderer();
              renderer.setIcon(m_data.getColumnIcon(index));
            }
            jTable1.getTableHeader().repaint();

            m_data.sortData();
            jTable1.tableChanged(new TableModelEvent(m_data));
            jTable1.repaint();
          }
        }

        public void actionPerformed(ActionEvent e)
        {
          Object ob = e.getSource();
          if(ob == setregionButton)
          {
            int count = jTable1.getSelectedRowCount();
            int list[] = jTable1.getSelectedRows();

            JTextField region_name = new JTextField("Region1");

            Object[] message = new Object[3];
            message[0] = "You must specify the region name.";
            message[1] = region_name;

            JCheckBox cb = new JCheckBox("X-linked region");
            message[2] = cb;

            int input = JOptionPane.showOptionDialog(
                this,
                message,
                "Set Region",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                genomefile,
                null,
                null);

            if(input==0)
            {
              for(int i=0;i<count;i++)
              {
                m_data.setValueAt(region_name.getText().trim(), list[i], 2);
              }

              if(cb.isSelected())
                getDataModel().setValue(region_name.getText().trim() , "x_linked");
            }

            jTable1.repaint();
          }
        }
}
