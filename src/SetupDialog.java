package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.awt.event.*;

public class SetupDialog extends JDialog implements ActionListener{
  Frame1 parent;
  JPanel panel1 = new JPanel();
  JPanel jLeftPanel = new JPanel();
  JPanel jRightPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JRadioButton RecentlyPButton = new JRadioButton();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  JRadioButton CreatePButton = new JRadioButton();
  JRadioButton OpenPButton = new JRadioButton();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JButton OKButton = new JButton();
  JButton CancelButton = new JButton();
  BorderLayout borderLayout6 = new BorderLayout();
  JList jList1;
  DefaultListModel listModel = new DefaultListModel();
  JLabel jLabel1 = new JLabel();
  ButtonGroup bp = new ButtonGroup();
  FlowLayout flowLayout1 = new FlowLayout();


  JPopupMenu jPopupMenuList = new JPopupMenu();
  JMenuItem jMenuItemDelete = new JMenuItem("Delete");

  public SetupDialog(Frame1 frame, String title, boolean modal) {
    super(frame, title, modal);
    parent=frame;
    try{
        jbInit();
        pack();

    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public SetupDialog() {
    this(null, "", false);
  }
  private void jbInit() throws Exception{
    this.setSize(new Dimension(450, 350));
    this.setTitle("Setup Dialog");
    //this.setResizable(false);

    RecentlyPButton.setFocusPainted(false);
    CreatePButton.setFocusPainted(false);
    OpenPButton.setFocusPainted(false);
    jList1 = new JList(listModel);

    jList1.setEnabled(false);
    jList1.setCellRenderer(new MyListTooltipRenderer());
    MouseListener popupListener = new PopupListener();
    jList1.addMouseListener(popupListener);

    CreatePButton.setSelected(true);
    jLabel1.setForeground(SystemColor.desktop);
    jLabel1.setIcon(new ImageIcon(SetupDialog.class.getResource("startsage.png")));
    OKButton.setPreferredSize(new Dimension(60, 25));
    CancelButton.setPreferredSize(new Dimension(60, 25));
    bp.add(CreatePButton);
    bp.add(OpenPButton);
    bp.add(RecentlyPButton);

    jPopupMenuList.add(jMenuItemDelete);

    RecentlyPButton.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        if(RecentlyPButton.isSelected())
        {
          jList1.setEnabled(true);
          jList1.setSelectedIndex(0);
        }
        else
        {
          jList1.setEnabled(false);
          jList1.clearSelection();
        }
      }
    });

    jMenuItemDelete.addActionListener(this);

    jLeftPanel.setPreferredSize(new Dimension(100,200));
    jLeftPanel.setLayout(borderLayout6);
    jRightPanel.setPreferredSize(new Dimension(300,220));
    jBottomPanel.setPreferredSize(new Dimension(300,40));
    jBottomPanel.setLayout(flowLayout1);

    panel1.setLayout(borderLayout1);
    panel1.setBorder(new EmptyBorder(10,10,0,10));
    jRightPanel.setLayout(borderLayout2);
    RecentlyPButton.setText("Recently accessed projects");
    jPanel3.setLayout(borderLayout3);
    jPanel3.setBorder(new EmptyBorder(10,10,0,10));
    jPanel2.setLayout(borderLayout5);
    jPanel1.setPreferredSize(new Dimension(250, 50));
    jPanel1.setLayout(borderLayout4);
    CreatePButton.setText("Create new project");
    OpenPButton.setText("Open existing project");
    OKButton.setText("OK");
    OKButton.addActionListener(this);
    CancelButton.setText("Cancel");
    CancelButton.setMargin(new Insets(2, 2, 2, 2));
    CancelButton.addActionListener(this);
    jList1.setBorder(BorderFactory.createLoweredBevelBorder());
    jRightPanel.setBorder(new EmptyBorder(8,10,0,5));
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
    jLabel1.setText("S.A.G.E.");
    jLabel1.setVerticalTextPosition(SwingConstants.BOTTOM);
    getContentPane().add(panel1);
    panel1.add(jLeftPanel,  BorderLayout.WEST);
    jLeftPanel.add(jLabel1, BorderLayout.CENTER);
    panel1.add(jRightPanel,  BorderLayout.CENTER);
    jRightPanel.add(jPanel1, BorderLayout.NORTH);
    jPanel1.add(CreatePButton,  BorderLayout.NORTH);
    jPanel1.add(OpenPButton,  BorderLayout.CENTER);
    jRightPanel.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(RecentlyPButton, BorderLayout.NORTH);
    jPanel2.add(jList1, BorderLayout.CENTER);
    jRightPanel.add(jPanel3, BorderLayout.SOUTH);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(OKButton, null);
    jBottomPanel.add(CancelButton, null);
  }

  public void actionPerformed(ActionEvent event) {
    final ActionEvent e = event;
    Object source = event.getSource();
    if(source == OKButton)
    {
      if(CreatePButton.isSelected())
      {
        SwingWorker worker = new SwingWorker() {
          public Object construct() {
            parent.jMenuNewProject_actionPerformed();
            return "";
          }
          public void finished() {
          }
        };
        worker.start();
      }
      else if(OpenPButton.isSelected())
      {
        SwingWorker worker = new SwingWorker() {
          public Object construct() {
            parent.jMenuFileOpen_actionPerformed(e);
            return "";
          }
          public void finished() {
          }
        };
        worker.start();
      }
      else if(RecentlyPButton.isSelected())
      {
        jList1.setEnabled(true);

        if(listModel.size()>0 && jList1.getSelectedIndex()>=0)
        {
          String openpath = listModel.get(jList1.getSelectedIndex()).toString();
          final File openfile = new File(openpath);
          String projectname = openfile.getName();
          String projectdir = openfile.getParent();

          try{

            FileInputStream fis = new FileInputStream(openfile);

          }catch(Exception exp)
          {
            int n = JOptionPane.showOptionDialog(this.getParent(),
                              "Unable to locate project '" + projectname +
                              "'\nin directory "+projectdir+".",
                              "Project path Error", // title
                              JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

            return;
          }
          Frame1.mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              parent.FileOpen(openfile);
              return "";
            }
            public void finished() {
              Frame1.mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
          };
          worker.start();
        }
        else
          return;
      }
      dispose();
    }
    if(source == CancelButton)
    {
      parent.jMenuFile.setEnabled(true);
      parent.jMenuView.setEnabled(true);
      parent.jMenuHelp.setEnabled(true);

      parent.jCheckBoxMenuItem1.setSelected(false);
      dispose();
    }
    if(source == jMenuItemDelete)
    {
      int index = jList1.getSelectedIndex();
      if(index >= 0)
        listModel.remove(index);
    }
  }

  class MyListTooltipRenderer extends JLabel implements ListCellRenderer  {
    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
      if (isSelected) {
        setBackground(list.getSelectionBackground());
        setForeground(list.getSelectionForeground());
      }
      else {
        setBackground(list.getBackground());
        setForeground(list.getForeground());
      }

      if(value != null)
        list.setToolTipText(value.toString());

      setFont(list.getFont());
      setText( (value == null) ? "" : value.toString());
      setOpaque(true);

      return this;
    }
  }

  class PopupListener extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
      if (jList1.getSelectedValue()!=null && (e.isPopupTrigger()))
      {
        jPopupMenuList.show(e.getComponent(), e.getX(), e.getY());
      }
    }

}
}
