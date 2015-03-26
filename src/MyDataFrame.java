package sage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.text.Position;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class MyDataFrame extends JFrame implements ActionListener {
  //JDesktopPane desktop;
  //Frame1 frame;
  JScrollPane jScrollPane1 = new JScrollPane();
  static JTextArea m_editor = new JTextArea();
  JPanel jPanel1 = new JPanel();
  JPopupMenu jFontMenu = new JPopupMenu();
  JCheckBoxMenuItem jWrap = new JCheckBoxMenuItem();
  JMenuItem jMenuType = new JMenuItem();
  JMenu jMenuSize = new JMenu();

  File m_currentFile;
  boolean m_textChanged=false;

  String fontname = "Courier New";
  String fontsize = "12";
  JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem2 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem3 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem4 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem5 = new JCheckBoxMenuItem();

  ButtonGroup bp = new ButtonGroup();
  ButtonGroup bp2 = new ButtonGroup();
  JMenuItem jMenuEdit = new JMenuItem();
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuSave = new JMenuItem();
  JToolBar jToolBar1 = new JToolBar();
  JButton jButton1 = new JButton();

  public MyDataFrame() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    setSize(new Dimension(600, 600));
    //setLocation(400, 5);
    m_editor.setFont(new java.awt.Font(fontname, 0, Integer.parseInt(fontsize)));
    m_editor.setToolTipText("Right click to option");
    m_editor.setEditable(false);//Monospaced
    m_editor.setLineWrap(false);
    MouseListener popupListener = new PopupListener();
    m_editor.addMouseListener(popupListener);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    jPanel1.setLayout(new BorderLayout());
    jWrap.setSelected(false);
    jWrap.setText("Wrap");
    jMenuType.setText("Font...");
    jMenuType.addActionListener(this);
    jMenuSize.setText("Size");

    jCheckBoxMenuItem1.setText("8");
    jCheckBoxMenuItem2.setText("10");
    jCheckBoxMenuItem3.setSelected(true);
    jCheckBoxMenuItem3.setText("12");
    jCheckBoxMenuItem4.setText("14");
    jCheckBoxMenuItem5.setText("16");
    jMenuEdit.setText("Edit...");
    jMenu1.setText("File");
    jMenuSave.setText("Save");
    jButton1.setToolTipText("Save");
    jButton1.setIcon(new ImageIcon(MyDataFrame.class.getResource("save.png")));
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("");
    jButton1.addActionListener(this);
    this.getContentPane().add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane1.getViewport().add(m_editor, null);
    jFontMenu.add(jMenuEdit);
    jFontMenu.addSeparator();
    jFontMenu.add(jMenuType);
    jFontMenu.add(jMenuSize);
    jFontMenu.addSeparator();
    jFontMenu.add(jWrap);
    jMenuSize.add(jCheckBoxMenuItem1);
    jMenuSize.add(jCheckBoxMenuItem2);
    jMenuSize.add(jCheckBoxMenuItem3);
    jMenuSize.add(jCheckBoxMenuItem4);
    jMenuSize.add(jCheckBoxMenuItem5);
    jWrap.addActionListener(this);

    jCheckBoxMenuItem1.addActionListener(this);
    jCheckBoxMenuItem2.addActionListener(this);
    jCheckBoxMenuItem3.addActionListener(this);
    jCheckBoxMenuItem4.addActionListener(this);
    jCheckBoxMenuItem5.addActionListener(this);
    jMenuEdit.addActionListener(this);
    jMenuSave.addActionListener(this);

    bp.add(jCheckBoxMenuItem1);
    bp.add(jCheckBoxMenuItem2);
    bp.add(jCheckBoxMenuItem3);
    bp.add(jCheckBoxMenuItem4);
    bp.add(jCheckBoxMenuItem5);
    jMenuBar1.add(jMenu1);
    jMenu1.add(jMenuSave);
    jToolBar1.add(jButton1, null);
    jPanel1.updateUI();
  }

  public void SetDisplayFile(File input)//String path)
  {
      m_editor.setText("");
      m_currentFile = input;
      this.setTitle(input.getName());

      Runnable doSetDisplay = new Runnable() {
        public void run() {
          try {
            FileReader in = new FileReader(m_currentFile);
            m_editor.read(in, null);
            in.close();
            setTitle(m_currentFile.getName());
          }
          catch (IOException e) {
          }
        }
      };
      SwingUtilities.invokeLater(doSetDisplay);

      m_textChanged=false;
      m_editor.getDocument().addDocumentListener(new UpDateListener());
  }

  public void actionPerformed(ActionEvent event)
  {
    Object source = event.getSource();

    if(source == jCheckBoxMenuItem1 || source == jCheckBoxMenuItem4
       || source ==jCheckBoxMenuItem3 || source ==jCheckBoxMenuItem2 || source ==jCheckBoxMenuItem5)
    {
      fontsize = event.getActionCommand();
      m_editor.setFont(new java.awt.Font(fontname, 0, Integer.parseInt(fontsize)));
    }

    else if(source == jWrap)
    {
      if(jWrap.isSelected())
      {
        m_editor.setLineWrap(true);
      }
      else
      {
        m_editor.setLineWrap(false);
      }
    }

    else if(source == jMenuType)// font type
    {
      //fontname= event.getActionCommand();
      //jEditorPane1.setFont(new java.awt.Font(fontname, 0, Integer.parseInt(fontsize)));
      FontDialog fd = new FontDialog();

      fd.jFontNameList.setSelectedValue(m_editor.getFont().getFontName(), true);
      fd.jFontStyleList.setSelectedIndex(m_editor.getFont().getStyle());
      fd.jFontSizeList.setSelectedValue(String.valueOf(m_editor.getFont().getSize()), true);

      fd.setLocationRelativeTo(this);
      fd.setVisible(true);

      if(fd.ok_clicked)
      {
        m_editor.setFont(new java.awt.Font(fd.jFontNameList.getSelectedValue().toString(), fd.jFontStyleList.getSelectedIndex(), Integer.parseInt(fd.jFontSizeList.getSelectedValue().toString())));
      }
    }

    else if(source == jMenuEdit)
    {
      m_editor.setEditable(true);
      jPanel1.add(jToolBar1, BorderLayout.NORTH);
      jPanel1.updateUI();
    }

    else if(source == jButton1)
    {
      if (promptToSave()) {
        saveFile();
      }
      m_editor.setEditable(false);
      m_textChanged=false;
      jPanel1.remove(jToolBar1);
      jPanel1.updateUI();
      SetDisplayFile(m_currentFile);
    }

    else if(source == jMenuSave)
    {
      if(promptToSave())
      {
        saveFile();
      }
      m_editor.setEditable(false);
      m_textChanged=false;
      jPanel1.remove(jToolBar1);
      jPanel1.updateUI();
      SetDisplayFile(m_currentFile);
    }
  }

  public boolean saveFile() {
    try {
      FileWriter out = new FileWriter(m_currentFile);
      m_editor.write(out);
      out.close();

    }
    catch (Exception ex) {
      ex.printStackTrace();
      return false;
    }

    m_textChanged=false;
    return true;
  }

  boolean promptToSave()
  {
    if(!m_textChanged)
      return true;
    int result = JOptionPane.showConfirmDialog(this, "Save changes to "+m_currentFile.getName()+"?",
                                               "Save file..", JOptionPane.YES_NO_CANCEL_OPTION,
                                               JOptionPane.INFORMATION_MESSAGE);
    switch(result)
    {
      case JOptionPane.YES_OPTION :
        if(!saveFile())
          return false;
        return true;
      case JOptionPane.NO_OPTION:
        return true;
      case JOptionPane.CANCEL_OPTION:
        return false;
    }
    return true;
  }

  class UpDateListener implements DocumentListener
  {
    public void changedUpdate(DocumentEvent event)
    {
      m_textChanged=true;
    }

    public void insertUpdate(DocumentEvent event)
    {
          changedUpdate(event);
    }

    public void removeUpdate(DocumentEvent event)
    {
          changedUpdate(event);
    }

  }

  class PopupListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseExited(MouseEvent e) {
    }

    private void maybeShowPopup(MouseEvent e) {
      if (e.isPopupTrigger())
        jFontMenu.show(e.getComponent(), e.getX(), e.getY());
    }
  }
}
