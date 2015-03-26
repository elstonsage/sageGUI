package sage;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.print.*;
import java.util.*;

public class MyFileFrame extends JFrame implements ActionListener{
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea m_editor;
  JPanel jPanel1 = new JPanel();
  JPopupMenu jFontMenu = new JPopupMenu();
  JCheckBoxMenuItem jWrap = new JCheckBoxMenuItem();
  JMenuItem jMenuType = new JMenuItem();
  JMenu jMenuSize = new JMenu();
  JMenuItem jMenuEdit = new JMenuItem();

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

  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenu1 = new JMenu();
  JMenu jMenu2 = new JMenu();
  JMenu jMenu3 = new JMenu();
  JToolBar jToolBar1 = new JToolBar();

  JMenuItem jMenuSave = new JMenuItem();
  JMenuItem jMenuExit = new JMenuItem();
  JMenuItem jMenuPrint = new JMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuWrap = new JCheckBoxMenuItem();
  JMenuItem jMenuFont = new JMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuToolbar = new JCheckBoxMenuItem();

  JButton jSaveButton = new JButton();
  JButton jPrintButton = new JButton();
  JButton jEditButton = new JButton();

  static Parser myParser;
  JMenuItem jMenuPreview = new JMenuItem();

  Frame1 frame1;
  MyInternalFrame inframe;
  boolean editable=false;

  boolean master_para_file = false;
  boolean note_file = false;

  public MyFileFrame(Frame1 parent) {
    try {
      frame1 = parent;
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    ImageIcon saveimage = new ImageIcon(MyFileFrame.class.getResource("save.png"));
    ImageIcon printimage = new ImageIcon(MyFileFrame.class.getResource("print.png"));
    ImageIcon editimage = new ImageIcon(MyFileFrame.class.getResource("edit.png"));

    setSize(new Dimension(600, 600));

    m_editor = new JTextArea();
    m_editor.getDocument().addDocumentListener(new MyDocumentListener());
    m_editor.getDocument().putProperty("name", "Text Area");
    m_editor.setFont(new java.awt.Font(fontname, 0, Integer.parseInt(fontsize)));
    m_editor.setEditable(false);
    m_editor.setLineWrap(false);
    MouseListener popupListener = new PopupListener();
    m_editor.addMouseListener(popupListener);

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setJMenuBar(jMenuBar1);

    jPanel1.setLayout(new BorderLayout());

    jMenu1.setText("File");
    jMenuSave.setText("Save");
    jSaveButton.setToolTipText("Save");
    jSaveButton.setIcon(saveimage);
    jSaveButton.setText("");
    jSaveButton.addActionListener(this);
    jMenuExit.setText("Exit");
    jMenuPrint.setText("Print...");
    jMenu2.setText("Format");
    jMenuFont.setText("Font...");
    jMenu3.setText("View");

    jMenuSave.addActionListener(this);
    jMenuPrint.addActionListener(this);
    jMenuExit.addActionListener(this);
    jMenuFont.addActionListener(this);

    jCheckBoxMenuToolbar.addActionListener(this);

    jCheckBoxMenuWrap.setSelected(true);
    jCheckBoxMenuWrap.setText("Word wrap");
    jCheckBoxMenuToolbar.setSelected(true);
    jCheckBoxMenuToolbar.setText("Tool bar");
    jPrintButton.setToolTipText("Print");
    jPrintButton.setIcon(printimage);
    jPrintButton.setText("");
    jPrintButton.addActionListener(this);
    jToolBar1.setBorder(null);
    jEditButton.setToolTipText("Edit");
    jEditButton.setIcon(editimage);
    jEditButton.setText("");
    jEditButton.addActionListener(this);

    jMenuPreview.setText("Print Preview");
    jMenuPreview.addActionListener(this);

    this.getContentPane().add(jPanel1,  BorderLayout.CENTER);
    jPanel1.add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    jScrollPane1.getViewport().add(m_editor, null);
    jPanel1.add(jToolBar1, BorderLayout.NORTH);

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

    bp.add(jCheckBoxMenuItem1);
    bp.add(jCheckBoxMenuItem2);
    bp.add(jCheckBoxMenuItem3);
    bp.add(jCheckBoxMenuItem4);
    bp.add(jCheckBoxMenuItem5);

    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenu2);
    jMenuBar1.add(jMenu3);
    jMenu1.add(jMenuSave);
    jMenu1.addSeparator();
    jMenu1.add(jMenuPreview);
    jMenu1.add(jMenuPrint);
    jMenu1.addSeparator();
    jMenu1.add(jMenuExit);
    jToolBar1.add(jPrintButton, null);
    jToolBar1.add(jSaveButton, null);
    jToolBar1.add(jEditButton, null);
    jMenu2.add(jCheckBoxMenuWrap);
    jMenu2.add(jMenuFont);
    jMenu3.add(jCheckBoxMenuToolbar);
    jPanel1.updateUI();
  }

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {

        if (promptToSave())
                saveFile();

      m_editor.setEditable(false);
      m_textChanged=false;
      master_para_file = false;
      note_file = false;

      this.dispose();
    }
  }

  public void SetDisplayFile(File input, final MyInternalFrame inframe)
  {
    note_file = false;
    this.inframe = inframe;

    m_editor.setText("");
    m_currentFile = input;
    this.setTitle("Reading.. please wait..");
    m_editor.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

    final String filepath = input.getPath();

    Runnable doSetDisplay = new Runnable() {
      public void run() {
        try {
          FileReader in = new FileReader(m_currentFile);
          m_editor.read(in, null);
          in.close();
          setTitleSafely();
          m_editor.getDocument().addDocumentListener(new MyDocumentListener());
        }
        catch (IOException e)
        {
          JOptionPane.showOptionDialog(MyFileFrame.this,
                "I/O exception encountered while attempting to read file '"+filepath+
                "'.\nPlease check the file for correct formatting, file attributes, "+
                "\nuser access privileges, possible data corruption, etc. ",
                "Error",
                JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
           dispose();
           return;
        }
      }
    };
    Thread readthread = new Thread(doSetDisplay, "readthread");
    readthread.start();

    m_textChanged=false;
  }

  void setTitleSafely()
  {
    Runnable doSetDisplay = new Runnable() {
      public void run() {
        setTitle(m_currentFile.getName());
        m_editor.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }
    };
    SwingUtilities.invokeLater(doSetDisplay);
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

    else if(source == jCheckBoxMenuWrap || source == jWrap)
    {
      if(jCheckBoxMenuWrap.isSelected() || jWrap.isSelected())
      {
        m_editor.setLineWrap(true);
      }
      else
      {
        m_editor.setLineWrap(false);
      }
    }

    else if(source == jMenuFont || source == jMenuType)
    {
      FontDialog fd = new FontDialog();

      fd.jFontNameList.setSelectedValue(m_editor.getFont().getFontName(), true);
      fd.jFontStyleList.setSelectedIndex(m_editor.getFont().getStyle());
      fd.jFontSizeList.setSelectedValue(String.valueOf(m_editor.getFont().getSize()), true);

      fd.setLocationRelativeTo(this);
      fd.setVisible(true);

      if(fd.ok_clicked)
      {
        int font_size = Integer.parseInt(fd.jFontSizeList.getSelectedValue().toString());
        m_editor.setFont(new java.awt.Font(fd.jFontNameList.getSelectedValue().toString(), fd.jFontStyleList.getSelectedIndex(), font_size));
        switch(font_size)
        {
          case 8:
            jCheckBoxMenuItem1.setSelected(true);
            break;
          case 10:
            jCheckBoxMenuItem2.setSelected(true);
            break;
          case 12:
            jCheckBoxMenuItem3.setSelected(true);
            break;
          case 14:
            jCheckBoxMenuItem4.setSelected(true);
            break;
          case 16:
            jCheckBoxMenuItem5.setSelected(true);
            break;
        }
      }
    }

    else if(source == jEditButton || source == jMenuEdit)
    {
        setEditable();
    }

    else if(source == jPrintButton || source == jMenuPrint)
    {
      Thread runner = new Thread()
      {
        public void run()
        {
          if(m_currentFile != null)
            printData();
        }
      };
      runner.start();
    }

    else if(source == jSaveButton || source == jMenuSave)
    {
      if (promptToSave()) {
        saveFile();

        if(!note_file)
         if(editable)
        {
          if(master_para_file)
          {
            try{
              inframe.updateMasterParaFile(m_currentFile);
            }catch(Exception ex)
            {
              ex.printStackTrace();
            }
          }
          else
          {
            frame1.updateAnalysisPanel(m_currentFile.getPath());
          }
        }
      }

      m_editor.setEditable(false);
      m_textChanged=false;
      SetDisplayFile(m_currentFile, inframe);
    }

    else if(source == jCheckBoxMenuToolbar)
    {
      if(jCheckBoxMenuToolbar.isSelected())
      {
        jPanel1.add(jToolBar1, BorderLayout.NORTH);
        jPanel1.updateUI();
      }
      else
      {
        jPanel1.remove(jToolBar1);
        jPanel1.updateUI();
      }
    }

    else if(source == jMenuPreview)
    {
      Thread runner = new Thread()
      {
        public void run()
        {
          setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
          myParser = new Parser(m_editor.getText());
          PrintPreview preview = new PrintPreview(myParser.pages, m_currentFile.getName());
          preview.setVisible(true);
          setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
      };
      runner.start();

    }
    else if(source == jMenuExit)
    {
        if (promptToSave())
                saveFile();

      m_editor.setEditable(false);
      m_textChanged=false;
      master_para_file = false;
      note_file = false;
      this.dispose();
    }
  }


  public void setEditable()
  {
      m_editor.setEditable(true);
      m_editor.requestFocus(true);
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

    return true;
  }

  boolean promptToSave()
  {
    if(!m_textChanged)
      return true;
    int result = JOptionPane.showConfirmDialog(this, "Do you want to save changes to "+m_currentFile.getName()+"?",
                                               "Save file..", JOptionPane.YES_NO_OPTION,
                                               JOptionPane.INFORMATION_MESSAGE);
    switch(result)
    {
      case JOptionPane.YES_OPTION :
        if(!saveFile())
          return false;
        return true;
      case JOptionPane.NO_OPTION:
        return false;
    }
    return true;
  }

  public void printData()
  {
    myParser = new Parser(m_editor.getText());
    myParser.print();
  }

  public void Set_Disable_Option()
  {
    jSaveButton.setEnabled(false);
    jEditButton.setEnabled(false);
    jMenuEdit.setEnabled(false);
    jMenuSave.setEnabled(false);
    editable = false;
  }

  public void Set_Enable_Option()
  {
    jSaveButton.setEnabled(true);
    jEditButton.setEnabled(true);
    jMenuEdit.setEnabled(true);
    jMenuSave.setEnabled(true);
    editable = true;
  }

  class MyDocumentListener implements DocumentListener {
      String newline = "\n";

      public void insertUpdate(DocumentEvent e) {
          updateLog();
      }
      public void removeUpdate(DocumentEvent e) {
          updateLog();
      }
      public void changedUpdate(DocumentEvent e) {
      }

      public void updateLog() {
          m_textChanged=true;
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

  class Parser
  {
    String rawData;
    Vector data;
    int pageCount=0;
    int currentPage=0;
    Book pages = new Book();
    PrinterJob job;
    int linesPerPage=0;

    public Parser(String data)
    {
      rawData = data;
      createPages();
    }

    public void createPages()
    {
      int x=0;
      PrinterJob job = PrinterJob.getPrinterJob();
      this.job = job;
      PageFormat format = job.defaultPage();
      Font myFont = m_editor.getFont();
      Label fontlabel = new Label();
      FontMetrics fm = fontlabel.getFontMetrics(myFont);
      int wPage = (int) format.getImageableWidth();
      int hPage = (int) format.getImageableHeight();

      int hLine = fm.getHeight();

      data = getLines(fm, wPage);

      linesPerPage = Math.max(hPage / hLine, 1);

      pageCount = data.size() / linesPerPage;

      if((data.size() % linesPerPage) != 0)
        pageCount++;

      try
      {
        int currentLine=0;
        for(x = 0;x<pageCount;x++)
        {
          Vector subVector;
          MyPage newPage = new MyPage(x);
          if(linesPerPage < data.size()-currentLine)
          {
            subVector = subList(data, currentLine, currentLine + linesPerPage - 1);
            currentLine += linesPerPage;
          }
          else
          {
              subVector = subList(data, currentLine, (int)data.size()-1);
              currentLine += data.size()-1;
          }
          newPage.setData(subVector);
          pages.append(newPage, format);
        }
      }
      catch(PrinterException e)
      {
      }
    }

    public static final int TAB_SIZE = 4;

    protected Vector getLines(FontMetrics fm, int wPage) {
      Vector v = new Vector();

      String text = m_editor.getText();
      String prevToken = "";
      StringTokenizer st = new StringTokenizer(text, "\n\r", true);
      while (st.hasMoreTokens()) {
        String line = st.nextToken();

        if (line.equals("\r"))
          continue;

        if (line.equals("\n") && prevToken.equals("\n"))
          v.add("");
        prevToken = line;
        if (line.equals("\n"))
          continue;

        StringTokenizer st2 = new StringTokenizer(line, " \t", true);
        String line2 = "";
        while (st2.hasMoreTokens()) {
          String token = st2.nextToken();

          if (token.equals("\t")) {
            int numSpaces = TAB_SIZE - line2.length() % TAB_SIZE;
            token = "";
            for (int k = 0; k < numSpaces; k++)
              token += " ";
          }

          int lineLength = fm.stringWidth(line2 + token);
          if (lineLength > wPage && line2.length() > 0) {
            v.add(line2);
            line2 = token.trim();
            continue;
          }
          line2 += token;
        }
        v.add(line2);
      }

      return v;
    }


    public int getPageCount() {
      return pageCount;
    }

    public void setCurrentPage(int page) {
      currentPage = page;
    }

    public Vector subList(Vector data, int start, int end) {
      Vector subData = new Vector();

      for (int x = start; x <= end; x++) {
        subData.add(data.get(x));
      }

      return subData;
    }

    public void print() {
      job.setPageable(pages);
      if (job.printDialog()) {
        try {
          job.print();
        }
        catch (PrinterException ex) {
          ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(MyFileFrame.this, "Printing completed successfully",
                                    m_currentFile.getName(), JOptionPane.INFORMATION_MESSAGE);
      }
    }

  }

 class MyPage implements Printable {
    protected PrinterJob job;
    protected Printable printable;
    protected int page;
    Vector data = new Vector(0);
    Point start;
    Point end;

    public MyPage(int page) throws PrinterException {
      this.page = page;
    }

    public boolean setData(Vector data) {
      this.data = (Vector) data;
      return true;
    }

    public int print(Graphics g, PageFormat format, int pageNumber) {
      int currentY;
      myParser.setCurrentPage(pageNumber);
      int x = (int) format.getImageableX();
      int y = (int) format.getImageableY();
      int w = (int) format.getImageableWidth();
      int h = (int) format.getImageableHeight();

      Font myFont = m_editor.getFont();
      FontMetrics fm;

      g.setColor(Color.white);
      g.fillRect(x, y, w, h);

      g.setColor(Color.black);
      g.setFont(myFont);
      fm = g.getFontMetrics();
      currentY = y + fm.getHeight();

      for (int r = 0; r < data.size(); r++) {
        g.drawString( (String) data.get(r), x, currentY);
        currentY += fm.getHeight();
      }

      if (pageNumber < myParser.getPageCount()) {
        return PAGE_EXISTS;
      }
      else {
        return NO_SUCH_PAGE;
      }
    }
  }
}
