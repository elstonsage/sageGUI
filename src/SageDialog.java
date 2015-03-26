package sage;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import com.borland.jbcl.layout.*;

public abstract class SageDialog extends JDialog{
  JList infoList = new JList();
  DefaultListModel listModel= new DefaultListModel();
  JScrollPane jScrollPane1 = new JScrollPane();

  JPopupMenu jPopupMenuList = new JPopupMenu();
  JMenuItem jMenuItemDelete = new JMenuItem("Delete");

  JButton okButton = new JButton();
  JButton cancelButton = new JButton();

  MyListDataListener listlinstener = new MyListDataListener();
  ListPopupListener listpopup = new ListPopupListener();
  KeyListener keylistener = new DeleteKeyListener();

  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();

  JLabel titleLabel = new JLabel();

  EdgeBorder lineborder = new EdgeBorder();
  BorderLayout borderLayout1 = new BorderLayout();

  public void showDialog() {
  setLocationRelativeTo(Frame1.mainFrame1.activeinframe);
  this.setVisible(true);
  }


  public SageDialog(String title) {
    super(Frame1.mainFrame1, title, true);

    jbInit();


    okButton.setText("OK");
    okButton.setEnabled(false);
    cancelButton.setText("Cancel");
    cancelButton.setMargin(new Insets(2, 2, 2, 2));

    jScrollPane1.getViewport().add(infoList, null);
    infoList.setToolTipText("Select and right-click to delete");
    jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());

    infoList.setModel(listModel);
    infoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listModel.addListDataListener(listlinstener);
    infoList.addMouseListener(listpopup);
    infoList.addKeyListener(keylistener);

    jPopupMenuList.add(jMenuItemDelete);
  }


  private void jbInit(){
    this.setSize(new Dimension(330, 360));
    this.setResizable(false);

    jTopPanel.setPreferredSize(new Dimension(330, 40));
    jTopPanel.setLayout(new XYLayout());
    jTopPanel.setBackground(Color.white);
    jCenterPanel.setLayout(new XYLayout());
    jCenterPanel.setPreferredSize(new Dimension(330, 280));
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setPreferredSize(new Dimension(330, 40));
    jBottomPanel.setBorder(lineborder);
    jBottomPanel.setLayout(new XYLayout());

    jTopPanel.add(titleLabel, new XYConstraints(15, 3, 288, 30));
    jBottomPanel.add(okButton, new XYConstraints(165, 7,  65, 25));
    jBottomPanel.add(cancelButton, new XYConstraints(243, 7,  65, 25));

      this.getContentPane().add(jTopPanel, BorderLayout.NORTH);
       this.getContentPane().add(jCenterPanel, BorderLayout.CENTER);
       this.getContentPane().add(jBottomPanel, BorderLayout.SOUTH);


    this.getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "F1Pressed");

    this.getRootPane().getActionMap().
            put("F1Pressed", new AbstractAction("F1Pressed") {
        public void actionPerformed(ActionEvent actionEvent) {
            onPressedF1();
        }
      });

  }

  abstract void onPressedF1();

  class MyListDataListener implements ListDataListener, Serializable {
      public void contentsChanged(ListDataEvent e) {
        if (listModel.size() > 0)
        {
          okButton.setEnabled(true);
        }
        else {
          okButton.setEnabled(false);
        }
      }
      public void intervalAdded(ListDataEvent e) {
        contentsChanged(e);

      }
      public void intervalRemoved(ListDataEvent e) {
        contentsChanged(e);
      }
  }

  class ListPopupListener  extends MouseAdapter implements Serializable{

    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
      if (infoList.getSelectedValue()!=null && (e.isPopupTrigger()))
      {
        jPopupMenuList.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }

  class DeleteKeyListener extends KeyAdapter
  {
    public void keyTyped(KeyEvent e)
    {
      if(e.getKeyChar()==KeyEvent.VK_DELETE)
      {
        int index = infoList.getSelectedIndex();
        if(index >= 0)
          listModel.remove(index);
      }
      if(e.getKeyChar()==KeyEvent.VK_F1)
      {
          Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
      }
    }
  }
}
