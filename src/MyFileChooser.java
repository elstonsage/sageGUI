package sage;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javax.swing.filechooser.*;

public class MyFileChooser extends JDialog implements ItemListener, ActionListener{
  private static MyFileChooser dialog;
  private static String value = "";
  private JTree tree;
  static JLabel select_path;
  String filename;
  static boolean ok = false;

  static JCheckBox tab = new JCheckBox("tab");
  static JCheckBox comma = new JCheckBox("comma");
  static JCheckBox other = new JCheckBox("other:");
  static JTextField delimiter = new JTextField("");

  JButton cancelButton = new JButton("Cancel");
  final JButton OKButton = new JButton("OK");

  public static void initialize(Component comp,
                                DefaultMutableTreeNode tree_dir_node,
                                String title,
                                String labelText) {
      Frame frame = JOptionPane.getFrameForComponent(comp);
      select_path = new JLabel(tree_dir_node.getLastChild().toString());
      ok = false;

      dialog = new MyFileChooser(frame, tree_dir_node,title, labelText);
  }

  public static String showDialog(Component comp) {
      if (dialog != null) {
          dialog.setLocationRelativeTo(comp);
          dialog.setVisible(true);
      } else {
          System.err.println("DirDialog requires you to call initialize "
                             + "before calling showDialog.");
      }
      return value;
  }

  public MyFileChooser(Frame frame, DefaultMutableTreeNode tree_dir_node, String title,
                     String labelText) {
      super(frame, title, true);
      try {
          jbInit(tree_dir_node, labelText);
          pack();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

  private void jbInit(DefaultMutableTreeNode tree_dir_node, String labelText) throws Exception {

    cancelButton.addActionListener(this);
    OKButton.addActionListener(this);

      getRootPane().setDefaultButton(OKButton);

      tree = new JTree(tree_dir_node, true);
      tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
      tree.setRootVisible(false);
      tree.setCellRenderer(new MyDirRenderer());
      tree.addTreeWillExpandListener(new DirExpandListener());
      tree.addTreeSelectionListener(new SelectListener());

      JScrollPane listScroller = new JScrollPane(tree);
      listScroller.setPreferredSize(new Dimension(300, 200));
      listScroller.setMinimumSize(new Dimension(300, 200));
      listScroller.setAlignmentX(LEFT_ALIGNMENT);

      JPanel listPane = new JPanel();
      listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
      JLabel label = new JLabel(labelText);

      label.setLabelFor(tree);
      listPane.add(label);
      listPane.add(Box.createRigidArea(new Dimension(0, 10)));
      listPane.add(select_path);
      listPane.add(Box.createRigidArea(new Dimension(0, 5)));
      listPane.add(listScroller);
      listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
      buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
      buttonPane.add(Box.createHorizontalGlue());
      buttonPane.add(OKButton);
      buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
      buttonPane.add(cancelButton);

      ButtonGroup bp = new ButtonGroup();
      bp.add(tab);
      tab.setSelected(true);
      bp.add(comma);
      bp.add(other);
      delimiter.setEnabled(false);
      delimiter.setEditable(false);
      other.addItemListener(this);
      tab.addItemListener(this);
      comma.addItemListener(this);

      JPanel delimiters = new JPanel();
      delimiters.setBorder(new EmptyBorder(0,10,0,0));
      delimiters.setLayout(new GridLayout(1,4,5,5));
      delimiters.add(tab);
      delimiters.add(comma);
      delimiters.add(other);
      delimiters.add(delimiter);

      JPanel p = new JPanel(new BorderLayout());
      p.setBorder(new EmptyBorder(0,10,10,10));
      p.add(new JLabel("Delimiter"), BorderLayout.NORTH);
      p.add(delimiters, BorderLayout.CENTER);

      Container contentPane = getContentPane();
      contentPane.add(listPane, BorderLayout.NORTH);
      contentPane.add(p, BorderLayout.CENTER);
      contentPane.add(buttonPane, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e)
  {
    Object ob = e.getSource();
    if (ob == cancelButton) {
      dispose();
    }
    if (ob == OKButton) {
      TreePath currentSelection = tree.getSelectionPath();
      if (currentSelection != null) {
        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.
                                     getLastPathComponent());
        Object node = currentNode.getUserObject();
        MyFileChooser.value = node.toString();
        ok = true;
        dispose();
      }
    }
  }

  class MyDirRenderer extends DefaultTreeCellRenderer {
      FileSystemView fileSystem = null;

      public MyDirRenderer() {
          fileSystem = FileSystemView.getFileSystemView();
      }

      public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                    boolean sel, boolean expanded,
                                                    boolean leaf, int row,
                                                    boolean hasFocus) {
          super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                                             row, hasFocus);
          DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
          Object nodeInfo = node.getUserObject();
          File file = (File) nodeInfo;

          setIcon(fileSystem.getSystemIcon(file));
          setText(fileSystem.getSystemDisplayName(file));

          return this;
          }
  }


  class DirExpandListener implements TreeWillExpandListener {
      FileSystemView fileSystem = FileSystemView.getFileSystemView();

      public void treeWillExpand(TreeExpansionEvent ev) {
          final DefaultMutableTreeNode node = (DefaultMutableTreeNode) (ev.
                  getPath().
                  getLastPathComponent());
          final Object obj = node.getUserObject();

          Thread runner = new Thread() {
              public void run() {
                  if (obj instanceof File) {
                      File file = (File) obj;
                      if(file.isDirectory())
                      {
                        File lists[] = fileSystem.getFiles(file, false);
                        if (node.getChildCount() == lists.length)
                        {
                          return;
                        }
                        node.removeAllChildren();
                        for (int i = 0; i < lists.length; i++) {
                            DefaultMutableTreeNode fnode = new DefaultMutableTreeNode(lists[i]);
                            node.add(fnode);
                        }
                      }
                          Runnable runnable = new Runnable() {
                              public void run() {
                                  DefaultTreeModel tm = (DefaultTreeModel)tree.getModel();
                                  tm.reload(node);
                              }
                          };
                          SwingUtilities.invokeLater(runnable);
                  }
              }
          };
          runner.start();
      }

      public void treeWillCollapse(TreeExpansionEvent event) {
      }
  }

  class SelectListener implements TreeSelectionListener {
    public void valueChanged(TreeSelectionEvent e) {
      TreePath currentSelection = tree.getSelectionPath();
      if (currentSelection != null) {
        DefaultMutableTreeNode currentNode = (
            DefaultMutableTreeNode) (currentSelection.
                                     getLastPathComponent());
        Object node = currentNode.getUserObject();
        File file = (File) node;
        String temp = file.toString();

        int index = temp.lastIndexOf(System.getProperty("file.separator"));
        temp = temp.substring(index+1, temp.length());

        filename = temp;
        select_path.setText(temp);
        select_path.setVisible(true);
      }
    }
  }

  public void itemStateChanged(ItemEvent event)
  {
    Object source = event.getSource();

    if(source == other)
    {
      if(other.isSelected())
      {
        delimiter.setEditable(true);
        delimiter.setEnabled(true);
      }
      else
      {
        delimiter.setEditable(false);
        delimiter.setEnabled(false);
      }
    }

    if(source == tab || source == comma)
    {
      if(tab.isSelected())
      {
        delimiter.setEditable(false);
        delimiter.setEnabled(false);
      }
      if(comma.isSelected())
      {
        delimiter.setEditable(false);
        delimiter.setEnabled(false);
      }
    }
  }
}
