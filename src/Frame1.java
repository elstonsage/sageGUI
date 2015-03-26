package sage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.AWTEvent;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.*;
import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.SAXBuilder;
import java.awt.Image;
import java.beans.*;

public class Frame1 extends JFrame implements ActionListener, ItemListener{
  String version = "S.A.G.E. 6.3";
  CompoundBorder TopBottomborder = new CompoundBorder(new EmptyBorder(0,0,0,0), new TopbottomBorder());
  CompoundBorder Myborder = new CompoundBorder(new MyEmptyBorder(), new MyBorder());
  CompoundBorder Bottomborder = new CompoundBorder(new EmptyBorder(0,0,1,0), new BottomBorder());
  CompoundBorder MyEmptyborder = new CompoundBorder(new MyEmptyBorder(), new MyEmptyBorder());

  CompoundBorder mouseborder = new CompoundBorder(new EmptyBorder(1,1,1,1), new MouseOverBorder());
  CompoundBorder initborder = new CompoundBorder(new EmptyBorder(1,1,1,1),new MyEmptyBorder());

  MyInternalFrame activeinframe;
  MyComponentsFrame comf;

  MyFileFrame file_frame;

  MyConsoleFrame consoleframe;
  MyJobFrame jobframe;
  MyPDFSearchFrame pdfframe;
  SetupDialog setupdialog;
  PrefDialog prefdialog;
  SnpDialog snpClipDialog;
  RDialog rdialog;

  static int FRAME_COUNTER = 0;
  static int INI_WIDTH = 780;
  static int INI_HEIGHT = 660;

  boolean isDataViewExist = false;
  boolean isJobViewExist = false;
  boolean isConsolViewExist = false;
  boolean isCompoViewExist = false;
  boolean isPDFViewExist = false;

  ProjectFileFilter ProjectFilter = new ProjectFileFilter("prj","Project File");
  ParaFileFilter ParaFilter = new ParaFileFilter("par", "Parameter File");
  ParaFileFilter FamilyFilter = new ParaFileFilter(new String[] {"dat", "ped"}, "Pedigree File");
  ParaFileFilter TraitLocusFilter = new ParaFileFilter("loc", "Trait File");
  ParaFileFilter MarkerLocusFilter = new ParaFileFilter("loc", "Locus File");
  ParaFileFilter GenomeFilter = new ParaFileFilter("gen", "Genome File");
  ParaFileFilter IBDFilter = new ParaFileFilter("ibd", "IBD File");
  ParaFileFilter TypeFilter = new ParaFileFilter("typ", "Type File");

  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenuItem jMenuFileOpen = new JMenuItem();
  JMenuItem jMenuFileSave = new JMenuItem();
  JMenuItem jMenuFileSaveAs = new JMenuItem();

  JMenu m_windowMenu = new JMenu();
  JMenuItem cascade = new JMenuItem();
  JMenuItem jMenuHelpPDF = new JMenuItem();

  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JLabel statusBar = new JLabel();

  BorderLayout borderLayout1 = new BorderLayout();
  static JDesktopPane desktop = new JDesktopPane();

  JMenu jMenuView = new JMenu();
  JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem2 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem3 = new JCheckBoxMenuItem();

  JMenu jMenuAnalysis = new JMenu();
  JMenu jMenuTools = new JMenu();
  JMenuItem jMenuFunction = new JMenuItem();
  JMenuItem jMenuGenome = new JMenuItem();
  JMenuItem jMenuSQL = new JMenuItem();
  JMenuItem jMenuGMDR  = new JMenuItem();
  JMenuItem jMenuPref = new JMenuItem();
  JMenu jMenuRun = new JMenu();
  JMenuItem jMenuRunSnpClip = new JMenuItem();

  JMenuItem jMenuNewProject = new JMenuItem();
  JMenu jMenu5 = new JMenu();
    JMenuItem jMenuPEDINFO = new JMenuItem();
    JMenu jMenu6 = new JMenu();
    JMenu jMenu7 = new JMenu();
    JMenuItem jMenMARKERINFO = new JMenuItem();
    JMenu jMenu8 = new JMenu();
    JMenuItem jMenuRELTEST = new JMenuItem();
    JMenu jMenu9 = new JMenu();
    JMenuItem jMenuFREQ = new JMenuItem();
    JMenu jMenu10 = new JMenu();
    JMenuItem jMenuDECIPHER = new JMenuItem();
    JMenu jMenu11 = new JMenu();
    JMenu jMenu12 = new JMenu();
    JMenuItem jMenuASSOC1 = new JMenuItem();
    JMenu jMenu13 = new JMenu();
    JMenuItem jMenuFCOR = new JMenuItem();
    JMenu jMenu14 = new JMenu();
    JMenuItem jMenuSEGREG1 = new JMenuItem();
    JMenu jMenu15 = new JMenu();
    JMenuItem jMenuSEGREG2 = new JMenuItem();
    JMenu jMenu16 = new JMenu();
    JMenuItem jMenuGENIBD = new JMenuItem();
    JMenu jMenu17 = new JMenu();
    JMenuItem jMenuAGEON = new JMenuItem();
    JMenu jMenu18 = new JMenu();
    JMenu jMenu19 = new JMenu();
    JMenu jMenu20 = new JMenu();
    JMenuItem jMenuMLOD = new JMenuItem();
    JMenu jMenu21 = new JMenu();
    JMenuItem jMenuLODLINK = new JMenuItem();
    JMenu jMenu22 = new JMenu();
    JMenu jMenu23 = new JMenu();
    JMenuItem jMenuSIBPAL = new JMenuItem();
    JMenu jMenu24 = new JMenu();
    JMenuItem jMenuLODPAL = new JMenuItem();
    JMenu jMenu31 = new JMenu();
    JMenuItem jMenuRELPAL = new JMenuItem();

    JMenu jMenu26 = new JMenu();
    JMenu jMenu27 = new JMenu();
    JMenuItem jMenuTDTEX = new JMenuItem();
    JMenu jMenu29 = new JMenu();
    JMenu jMenu30 = new JMenu();
    JMenuItem jMenuASSOC2 = new JMenuItem();

    FunctionDialog1 fdialog1;

    ButtonGroup m_windowButtonGroup = new ButtonGroup();

    static Frame1 mainFrame1;
    CloseDialog closedialog;

    OpenProgressDialog cd;

   String path_forFileChooser = new String();
   Image frameiconimage = new ImageIcon(Frame1.class.getResource("SAGE.PNG")).getImage();

   String pathEdigorProg = new String();
   String pathSnpClipProg = new String();
   String pathRProg = new String();

  public Frame1() {
     mainFrame1 = this;

  }

  public void initializeFrame()
 {
     try {
         enableEvents(AWTEvent.WINDOW_EVENT_MASK);

         pdfframe = new MyPDFSearchFrame(this, "SAGE_UserDoc.pdf");
         fdialog1 = new FunctionDialog1(Frame1.mainFrame1,"Specification",true);
         fdialog1.setLocationRelativeTo(this);
         prefdialog = new PrefDialog(this, "Preferences", true);
         prefdialog.setLocationRelativeTo(this);

         snpClipDialog = new SnpDialog(this, "Run", true);
         snpClipDialog.setLocationRelativeTo(this);

         rdialog = new RDialog(this, "Run", true);
         rdialog.setLocationRelativeTo(this);

          jbInit();
     } catch (Exception ex) {
         ex.printStackTrace();
     }
}

  private void jbInit() throws Exception  {
    ToolTipManager tp = ToolTipManager.sharedInstance();
    tp.setDismissDelay(100000);
    tp.setInitialDelay(500);

    path_forFileChooser = System.getProperty("user.dir");

    cd = new OpenProgressDialog("Open");

    closedialog= new CloseDialog(Frame1.mainFrame1,"Save Modified",true);
    closedialog.setLocationRelativeTo(this);

    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    jMenuFile.setEnabled(false);
    jMenuView.setEnabled(false);
    jMenuAnalysis.setEnabled(false);
    m_windowMenu.setEnabled(false);
    jMenuTools.setEnabled(false);
    jMenuHelp.setEnabled(false);

    jMenuNewProject.setText("New Project");
    jMenuNewProject.addActionListener(this);

    comf = new MyComponentsFrame("",this);

    jCheckBoxMenuItem1.addItemListener(this);
    jCheckBoxMenuItem2.addItemListener(this);
    jCheckBoxMenuItem3.addItemListener(this);

    desktop.add(comf);

    file_frame = new MyFileFrame(this);
    consoleframe = new MyConsoleFrame(this);
    jobframe = new MyJobFrame(this);

    Toolkit t = Toolkit.getDefaultToolkit();
    Dimension wd = t.getScreenSize();
    int new_height = wd.height-30;
    wd.setSize(wd.width, new_height);
    this.setSize(wd);
    desktop.setSize(wd);
    this.setIconImage(frameiconimage);
    this.setTitle(version);
    statusBar.setText("");
    m_windowMenu.setText("Window");
    cascade.setText("Cascade");
    cascade.addActionListener(this);
    jMenuFile.setText("File");
    jMenuFileExit.setText("Exit");
    jMenuFileExit.addActionListener(this);
    jMenuFileOpen.setText("Open Project");
    jMenuFileOpen.addActionListener(this);
    jMenuFileSave.setText("Save Project");
    jMenuFileSave.setEnabled(false);
    jMenuFileSave.addActionListener(this);

    jMenuFileSaveAs.setText("Rename Project");
    jMenuFileSaveAs.setEnabled(false);
    jMenuFileSaveAs.addActionListener(this);

    jMenuHelp.setText("Help");
    jMenuHelpAbout.setText("About");
    jMenuHelpAbout.addActionListener(this);

    jMenuHelpPDF.setText("Help");
    jMenuHelpPDF.addActionListener(this);
    jMenuHelpPDF.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0));

    jMenuView.setText("View");
    jCheckBoxMenuItem1.setText("Palette");
    jCheckBoxMenuItem2.setText("Tasks");
    jCheckBoxMenuItem3.setText("Console");
    jMenuAnalysis.setText("Analysis");
    jMenuTools.setText("Tools");
    jMenuFunction.setText("Create New Variable");
    jMenuGenome.setText("Create a Genome Description File");
    jMenuSQL.setText("SQL Wizard");
    jMenuGMDR.setText("GMDR Utility");

    jMenuPref.setText("Preferences");
    jMenuRun.setText("Run");
    jMenuRunSnpClip.setText("SnpClip");

    jMenuGenome.setEnabled(false);
    jMenuSQL.setEnabled(false);
    jMenuFunction.setEnabled(false);
    jMenuFunction.addActionListener(this);
    jMenuGenome.addActionListener(this);
    jMenuSQL.addActionListener(this);
    jMenuGMDR.addActionListener(this);
    jMenuPref.addActionListener(this);
    jMenuRunSnpClip.addActionListener(this);

    jMenu5.setText("Summary Statistics");
    jMenuPEDINFO.setText("PEDINFO");
    jMenuPEDINFO.addActionListener(this);
    jMenu6.setText("Data Quality");
    jMenu7.setText("Mendelian Inconsistencies");
    jMenMARKERINFO.setText("MARKERINFO");
    jMenMARKERINFO.addActionListener(this);
    jMenu8.setText("Relationship Testing");
    jMenuRELTEST.setText("RELTEST");
    jMenuRELTEST.addActionListener(this);
    jMenu9.setText("Allele Frequency Estimation");
    jMenuFREQ.setText("FREQ");
    jMenuFREQ.addActionListener(this);
    jMenu10.setText("Haplotype  Frequency Estimation");
    jMenuDECIPHER.setText("DECIPHER");
    jMenuDECIPHER.addActionListener(this);
    jMenu11.setText("Familial Aggregation");
    jMenu12.setText("Heritability Estimation");
    jMenuASSOC1.setText("ASSOC");
    jMenuASSOC1.addActionListener(this);
    jMenu13.setText("Correlations");
    jMenuFCOR.setText("FCOR");
    jMenuFCOR.addActionListener(this);
    jMenu14.setText("Commingling Analysis");
    jMenuSEGREG1.setText("SEGREG");
    jMenuSEGREG1.addActionListener(this);
    jMenu15.setText("Segregation Analysis");
    jMenuSEGREG2.setText("SEGREG");
    jMenuSEGREG2.addActionListener(this);
    jMenu16.setText("IBD Allele Sharing Estimation");
    jMenuGENIBD.setText("GENIBD");
    jMenuGENIBD.addActionListener(this);

    jMenu17.setText("Age-of-Onset Analysis");
    jMenuAGEON.setText("AGEON");
    jMenuAGEON.addActionListener(this);
    jMenu18.setText("Linkage Analysis");
    jMenu19.setText("Model-Based");
    jMenu20.setText("Multipoint");
    jMenuMLOD.setText("MLOD");
    jMenuMLOD.addActionListener(this);
    jMenu21.setText("Single Point/2-Point");
    jMenuLODLINK.setText("LODLINK");
    jMenuLODLINK.addActionListener(this);
    jMenu22.setText("Model-Free");
    jMenu23.setText("Sibling Pairs");
    jMenuSIBPAL.setText("SIBPAL");
    jMenuSIBPAL.addActionListener(this);
    jMenu24.setText("Affected Relative Pairs");
    jMenuLODPAL.setText("LODPAL");
    jMenuLODPAL.addActionListener(this);

    jMenu31.setText("Arbitrary Relative Pairs");
    jMenuRELPAL.setText("RELPAL");
    jMenuRELPAL.addActionListener(this);

    jMenu26.setText("Transmission Disequilibrium (TDT)");
    jMenu27.setText("Dichotomous (Binary) Traits");
    jMenuTDTEX.setText("TDTEX");
    jMenuTDTEX.addActionListener(this);
    jMenu29.setText("Allelic Association");
    jMenu30.setText("Family-Based");
    jMenuASSOC2.setText("ASSOC");
    jMenuASSOC2.addActionListener(this);

    jMenuAnalysis.add(jMenu5);
    jMenuAnalysis.add(jMenu6);
    jMenuAnalysis.add(jMenu9);
    jMenuAnalysis.add(jMenu10);
    jMenuAnalysis.add(jMenu11);
    jMenuAnalysis.add(jMenu14);
    jMenuAnalysis.add(jMenu15);
    jMenuAnalysis.add(jMenu16);
    jMenuAnalysis.add(jMenu17);
    jMenuAnalysis.add(jMenu18);
    jMenuAnalysis.add(jMenu26);
    jMenuAnalysis.add(jMenu29);
    jMenu5.add(jMenuPEDINFO);
    jMenu6.add(jMenu7);
    jMenu6.add(jMenu8);
    jMenu7.add(jMenMARKERINFO);
    jMenu8.add(jMenuRELTEST);
    jMenu9.add(jMenuFREQ);
    jMenu10.add(jMenuDECIPHER);
    jMenu11.add(jMenu12);
    jMenu11.add(jMenu13);
    jMenu12.add(jMenuASSOC1);
    jMenu13.add(jMenuFCOR);
    jMenu14.add(jMenuSEGREG1);
    jMenu15.add(jMenuSEGREG2);
    jMenu16.add(jMenuGENIBD);
    jMenu17.add(jMenuAGEON);
    jMenu18.add(jMenu19);
    jMenu18.add(jMenu22);
    jMenu19.add(jMenu20);
    jMenu19.add(jMenu21);
    jMenu20.add(jMenuMLOD);
    jMenu21.add(jMenuLODLINK);
    jMenu22.add(jMenu23);
    jMenu22.add(jMenu24);
    jMenu22.add(jMenu31);
    jMenu23.add(jMenuSIBPAL);
    jMenu24.add(jMenuLODPAL);
    jMenu31.add(jMenuRELPAL);
    jMenu26.add(jMenu27);
    jMenu27.add(jMenuTDTEX);
    jMenu29.add(jMenu30);
    jMenu30.add(jMenuASSOC2);

    jMenuFile.add(jMenuNewProject);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileOpen);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileSave);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileSaveAs);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuHelp.add(jMenuHelpPDF);

    m_windowMenu.add(cascade);
    m_windowMenu.addSeparator();

    jMenuView.add(jCheckBoxMenuItem1);
    jMenuView.add(jCheckBoxMenuItem2);
    jMenuView.add(jCheckBoxMenuItem3);
    jMenuTools.add(jMenuGenome);
    jMenuTools.add(jMenuFunction);
    jMenuTools.add(jMenuSQL);
    jMenuTools.addSeparator();
    jMenuTools.add(jMenuGMDR);
    jMenuTools.add(jMenuRun);
    jMenuRun.add(jMenuRunSnpClip);
    jMenuTools.addSeparator();
    jMenuTools.add(jMenuPref);

    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuView);
    jMenuBar1.add(jMenuAnalysis);
    jMenuBar1.add(m_windowMenu);
    jMenuBar1.add(jMenuTools);
    jMenuBar1.add(jMenuHelp);

    this.setJMenuBar(jMenuBar1);
    this.getContentPane().add(desktop, BorderLayout.CENTER);

    setContentPane(desktop);
  }

  void ShowSetupDialog()
  {
    setupdialog = new SetupDialog(this,"Lunch Dialog",true);
    setupdialog.listModel.removeAllElements();
    setupdialog.setLocationRelativeTo(this);

    String init_dir = new String();
    String os_type = System.getProperty("os.name");
    if (os_type.indexOf("Windows") >= 0) {
      init_dir = System.getProperty("user.home")+System.getProperty("file.separator");
    }
    else {
      init_dir = System.getProperty("user.home")+System.getProperty("file.separator");
    }

    File inffile=null;

    boolean fileexist = false;
    File pp = new File(init_dir);
    File[] files = pp.listFiles();
    if(files !=null)
    {
      for (int i = 0; i < files.length; i++) {
        if (files[i].isFile()) {
          if (files[i].getName().compareTo("sage.ini") == 0) {
            fileexist = true;
            inffile = files[i];
          }
        }
      }
    }

    if(!fileexist)
    {
      inffile = new File(init_dir, "sage.ini");
      try{
        inffile.createNewFile();
      }catch(Exception exp)
      {
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                 "I/O exception encountered while attempting to write file \"sage.ini\"." +
                 "\nReported error number is "+exp+
                 "\n\"sage.ini\" file path : "+init_dir,
                 "Error",
                 JOptionPane.CLOSED_OPTION,
                JOptionPane.WARNING_MESSAGE, null, null, null);
         return;
      }
    }

    if(inffile!=null)
    {

      SAXBuilder builder = new SAXBuilder();
      try{

      FileInputStream fis = new FileInputStream(inffile);
      InputStreamReader isr = new InputStreamReader(fis);
      BufferedReader in = new BufferedReader(isr);
      String strFileLine = new String();
      boolean newformat = false;

      if ((strFileLine = in.readLine()) != null) {
        strFileLine = strFileLine.trim();
        if (strFileLine.indexOf("xml version") >= 0) {
          newformat = true;
        }
      }
      if (newformat) {
        Document doc = builder.build(inffile);
        Element root = doc.getRootElement();

        List children = root.getContent();
        Iterator iterator = children.iterator();
        while(iterator.hasNext())
        {
          Object child = iterator.next();
          if (child instanceof Element) {
            Element element = (Element) child;
            String element_name = element.getName();

            if(element_name.compareTo("Editor")==0)
            {
              String propath = element.getTextTrim();
              this.pathEdigorProg = propath;
              prefdialog.jTextField1.setText(propath);
            }
            else if(element_name.compareTo("SnpClip")==0)
            {
                String snpclippath = element.getTextTrim();
                this.pathSnpClipProg = snpclippath;
                snpClipDialog.jTextField1.setText(snpclippath);
            }

            else if(element_name.compareTo("List")==0)
            {
              int i=0;
              List plist = element.getContent();
              Iterator piterator = plist.iterator();

              while(piterator.hasNext())
              {
                  i++;
                  Object pobj = piterator.next();
                  Element ppath = (Element) pobj;

                  String filepath = ppath.getTextTrim();
                  setupdialog.listModel.addElement(filepath);

                  if(i>5)
                    break;
              }
            }
          }
        }

      } else {

        String strFileNextLine = new String();
        setupdialog.listModel.addElement(strFileLine);

        int i=0;
        while ((strFileLine = in.readLine()) != null) {
          if(i>4)
            break;

          if (strFileLine.compareTo(strFileNextLine) == 0)
            continue;
          else
            setupdialog.listModel.addElement(strFileLine);

          strFileNextLine = strFileLine;
          i++;
        }
      }

      }
      catch(JDOMException e)
      {
        JOptionPane.showOptionDialog(this.getParent(),
                                     "Unable to open project file "+inffile.getName()
                                     +"\nReported error number is "+e,
                                     "Error",
                                     JOptionPane.CLOSED_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null, null, null);
        e.printStackTrace();
        System.exit(0);
        return;
      }
      catch(Exception e)
      {
        JOptionPane.showOptionDialog(this.getParent(),
                                     "Unable to open project file "+inffile.getName()
                                     +"\nReported error number is "+e,
                                     "Error", // title
                                     JOptionPane.CLOSED_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null, null, null);
        e.printStackTrace();
        System.exit(0);
        return;
      }

    }
    setupdialog.setVisible(true);

  }

  public void itemStateChanged(ItemEvent e)
  {
    Object ob = e.getSource();
    if (ob == jCheckBoxMenuItem1) {
      jCheckBoxMenuItem1_itemStateChanged(e);
    }
    if (ob == jCheckBoxMenuItem2) {
      jCheckBoxMenuItem2_itemStateChanged(e);
    }
    if (ob == jCheckBoxMenuItem3) {
      jCheckBoxMenuItem3_itemStateChanged(e);
    }
  }


  public void actionPerformed(ActionEvent e)
  {
    Object ob = e.getSource();

    if(ob ==jMenuNewProject)
    {
        SwingWorker worker = new SwingWorker() {
          public Object construct() {
            jMenuNewProject_actionPerformed();
            return "";
          }
          public void finished() {
          }
        };
        worker.start();
    }
    else if(ob == jMenuFileSave)
    {
      jMenuFileSave_actionPerformed();
    }
    else if(ob == jMenuFileSaveAs)
    {
      jMenuFileSaveAs_actionPerformed();
    }
    else if(ob == jMenuFileOpen)
    {
       jMenuFileOpen_actionPerformed(e);
    }
    else if(ob == jMenuFileExit)
    {
      jMenuFileExit_actionPerformed(null);
    }
    else if(ob == jMenuHelpAbout)
    {
      jMenuHelpAbout_actionPerformed(e);
    }
    else if(ob == jMenuHelpPDF)
    {
      jMenuHelpPDF_actionPerformed();
    }
    else if(ob == jMenuFREQ)
    {
      jMenuFREQ_actionPerformed();
    }
    else if(ob == jMenuGENIBD)
    {
      jMenuGENIBD_actionPerformed();
    }
    else if(ob == jMenuLODPAL)
    {
      jMenuLODPAL_actionPerformed();
    }
    else if(ob == jMenuRELPAL)
    {
      jMenuRELPAL_actionPerformed();
    }
    else if(ob == jMenuPEDINFO)
    {
      jMenuPEDINFO_actionPerformed();
    }
    else if(ob== jMenuASSOC2 || ob==jMenuASSOC1)
    {
      jMenuASSOC_actionPerformed();
    }
    else if (ob == jMenuAGEON)
    {
      jMenuAGEON_actionPerformed();
    }
    else if(ob == jMenMARKERINFO)
    {
      jMenuMARKERINFO_actionPerformed();
    }
    else if(ob == jMenuRELTEST)
    {
      jMenuRELTEST_actionPerformed();
    }
    else if(ob == jMenuFCOR)
    {
      jMenuFCOR_actionPerformed();
    }
    else if(ob== jMenuSEGREG1 || ob==jMenuSEGREG2)
    {
      jMenuSEGREG_actionPerformed();
    }
    else if(ob == jMenuMLOD)
   {
     jMenuMLOD_actionPerformed();
   }
   else if(ob == jMenuLODLINK)
   {
     jMenuLODLINK_actionPerformed();
   }
   else if(ob == jMenuSIBPAL)
   {
     jMenuSIBPAL_actionPerformed();
   }
   else if(ob== jMenuTDTEX)
   {
     jMenuTDTEX_actionPerformed();
   }
   else if(ob == jMenuDECIPHER)
   {
     jMenuDECIPHER_actionPerformed();
   }
   else if(ob == jMenuFunction)
   {
     jMenuFunctionBlock_actionPerformed();
   }
   else if(ob == jMenuGenome)
   {
     GenomeWizard();
   }
   else if(ob == jMenuSQL)
   {
     activeinframe.jModifyFileMenuItem2_actionPerformed();
   }
   else if(ob == jMenuPref)
   {
     ShowPreferencesDialog();
   }
   else if(ob == jMenuRunSnpClip)
   {
       RunSnpclip();
   }
   else if(ob == cascade)
   {
     cascadeFrames();
   }
   else if(ob == jMenuGMDR)
   {
     ShowGMDRDialog();
   }
  }

  void RunSnpclip()
  {
      if (pathSnpClipProg == null || pathSnpClipProg.length() <= 0) {
          snpClipDialog.setVisible(true);
      }
      else {
          try {
              Runtime.getRuntime().exec(pathSnpClipProg);
          } catch (Exception ex) {
              JOptionPane.showOptionDialog(this.getParent(),
                      "Please check the path for SnpClip.",
                      "Error", // title
                      JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                      snpClipDialog.jTextField1.requestFocus();
                      snpClipDialog.setVisible(true);
          }
      }
  }

  void ShowPreferencesDialog()
  {
    prefdialog.setVisible(true);
  }


  void ShowGMDRDialog()
  {
      GMDRDialog gmdr = new GMDRDialog(this, "GMDR Utility", true);
      gmdr.setLocationRelativeTo(this);
      gmdr.setVisible(true);
  }

  public void jMenuHelpPDF_actionPerformed() {
    if(!isPDFViewExist)
    {
      pdfframe.setVisible(true);

      pdfframe.scalingBox.setSelectedIndex(5);
      isPDFViewExist = true;
    }
  }

  public void GenomeWizard()
  {
      try{
          JWizardGenome.initialize(this, "Genome map file wizard");
      }catch(Exception ex)
      {
          ex.printStackTrace();
      }

    JWizardGenome.showDialog(this);
  }

  public void cascadeFrames()
  {
    try{
      JInternalFrame[] frames = desktop.getAllFrames();
      JInternalFrame selectedFrame = desktop.getSelectedFrame();

      int x=100;
      int y=0;

      for(int k=frames.length-1;k>=0;k--)
      {
        if(!(frames[k] instanceof MyInternalFrame))
          continue;
        frames[k].setMaximum(false);
        frames[k].setIcon(false);
        frames[k].setBounds(x, y, INI_WIDTH, INI_HEIGHT);

        x+=20;
        y+=20;
      }
      if(selectedFrame!=null)
        desktop.setSelectedFrame(selectedFrame);

    }catch(Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void jMenuFileExit_actionPerformed(WindowEvent e) {
    JInternalFrame[] inter = desktop.getAllFrames();
    final Vector listvector = new Vector();
    Vector listnamevector = new Vector();

    for(int i=0;i<inter.length;i++)
    {
      if(inter[i] instanceof MyInternalFrame)
      {
        MyInternalFrame temp = (MyInternalFrame)inter[i];
        VariableData temp2 = new VariableData(temp.getTitle(),"myinframe");
        CheckableItem item = new CheckableItem(temp2);
        item.setSelected(true);
        listvector.add(temp);
        listnamevector.add(item);
      }
    }

    if(listvector.size()>0)
    {
      closedialog.jList1.setListData(listnamevector);
      closedialog.setVisible(true);

      if (closedialog.ok) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SwingWorker worker = new SwingWorker() {

          public Object construct() {
            int listsize = closedialog.jList1.getModel().getSize();
            for (int i = 0; i < listsize; i++) {
              CheckableItem item = (CheckableItem) closedialog.jList1.getModel().getElementAt(i);
              if (item.isSelected()) {
                MyInternalFrame temp = (MyInternalFrame) listvector.get(i);
                temp.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                activateInternalFrame(temp);
                jMenuFileSave_actionPerformed();
              }
            }
            return "";
          }
          public void finished() {
            mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            System.exit(0);
          }
        };
        worker.start();

     }
      else
        return;
    }
    else
      System.exit(0);
  }

  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    Frame1_AboutBox dlg = new Frame1_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.setVisible(true);

  }

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(e);
    }
  }

  void Make_NewProjectFrame(DataCollectionModel datamodel, String porjectpath)
  {
    String title = datamodel.getValue("info.pName").toString();
    String path = datamodel.getValue("info.DirPath").toString();
    IconNode internal;
    IconNode external;

    File projectfile = new File(porjectpath+ System.getProperty("file.separator")+title + ".prj");

    if(!projectfile.exists())
    {
      try{
      projectfile.createNewFile();
      }catch(Exception ex)
      {
        ex.printStackTrace();

      }
    }

    final MyInternalFrame inframe = new MyInternalFrame(title, desktop, datamodel, this);
    inframe.projectPath = porjectpath;
    inframe.projectFile = projectfile;

    inframe.jTabbedPane1.removeAll();

    if(datamodel.hasValue("internalnode"))
    {
       internal =(IconNode)datamodel.getValue("internalnode");
    }
    else
    {
      NodeInfo temp = new NodeInfo("Internal", "Internal", "Internal", "Internal");
      internal = new IconNode(temp, "Internal");
    }
    inframe.internalnode = internal;

    if(datamodel.hasValue("externalnode"))
   {
      external =(IconNode)datamodel.getValue("externalnode");
   }
   else
   {
     NodeInfo temp = new NodeInfo("External", "External", "External", "External");
     external = new IconNode(temp, "External");
   }
   inframe.externalnode = external;

    IconNode parent = inframe.rootnode;
    NodeInfo nodeinfo = new NodeInfo(title, "Project", "Project", path);
    IconNode projectnode = new IconNode(nodeinfo, "Project");
    inframe.treeModel.insertNodeInto(projectnode, parent, parent.getChildCount());
    inframe.projectnode = projectnode;

    nodeinfo = new NodeInfo("Data", "Data", "Data", "Data");
    IconNode datanode = new IconNode(nodeinfo, "Data");
    inframe.treeModel.insertNodeInto(datanode, projectnode,projectnode.getChildCount());

    inframe.treeModel.insertNodeInto(external, datanode, datanode.getChildCount());
    inframe.treeModel.insertNodeInto(internal, datanode, datanode.getChildCount());

    nodeinfo = new NodeInfo("Jobs", "Jobs" , "Jobs", "Jobs");
    IconNode jobnode = new IconNode(nodeinfo, "Jobs");
    inframe.jobnode = jobnode;
    inframe.treeModel.insertNodeInto(jobnode, projectnode, projectnode.getChildCount());

    activateInternalFrame(inframe);

    final DataCollectionModel MyinframeDatamodel = datamodel;

    if(datamodel.hasValue("ParameterFileNode"))
    {
      mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

      SwingWorker parsingworker = new SwingWorker() {
        public Object construct() {
          ParsingParameterFile(MyinframeDatamodel, inframe);
          return "";
        }
        public void finished() {
          mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
          jMenuFileSave_actionPerformed();
        }
      };
      parsingworker.start();
    }

    if(external!=null)
    {
      TreePath p = new TreePath(external.getFirstLeaf().getPath());
      p = new TreePath(internal.getFirstLeaf().getPath());
      inframe.MyTree1.scrollPathToVisible(p);
    }
    else
    {
      TreePath p = new TreePath(jobnode.getPath());
      inframe.MyTree1.scrollPathToVisible(p);
    }

    inframe.setVisible(true);
    desktop.add(inframe);
    try {
      inframe.setSelected(true);
    }
    catch (java.beans.PropertyVetoException ex) {
      ex.printStackTrace();
    }

    inframe.setBounds(108+(FRAME_COUNTER*30), FRAME_COUNTER*20, INI_WIDTH, INI_HEIGHT);
    FRAME_COUNTER = (FRAME_COUNTER+1)%10;

    JRadioButtonMenuItem item = inframe.m_frameMenuItem;
    m_windowMenu.add(item);
    m_windowButtonGroup.add(item);
    item.setSelected(true);

    inframe.addInternalFrameListener(inframe.new FrameListener());

    init_menu();

    inframe.CreateMonitorThread();

    path_forFileChooser = System.getProperty("user.dir");
  }

  public void ParsingParameterFile(DataCollectionModel datamodel, MyInternalFrame inframe) {

    IconNode jobnode = inframe.jobnode;
    String path = datamodel.getValue("info.DirPath").toString();

    IconNode Parameter_node =(IconNode)datamodel.getValue("ParameterFileNode");
    inframe.parameterfilenode = Parameter_node;

    String master_information=new String();
    NodeInfo paranode = (NodeInfo)Parameter_node.getUserObject();

    inframe.init_lastModified = paranode.file.lastModified();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;
    if (dm.hasValue("Master_Information")) {
      master_information = dm.getValue("Master_Information").toString();
    }

    if (dm.hasValue("Assoc_array")) {
      Vector Assoc_array = (Vector) dm.getValue("Assoc_array");
      for (int i=0;i<Assoc_array.size();i++)
      {
        final String block = (String)Assoc_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "assoc analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("ASSOC");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","ASSOC" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "ASSOC", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final ASSOC1 assoc1 = new ASSOC1(ai, assoc, errorfolder);
        final ASSOC2 assoc2 = new ASSOC2(assoc1);
        assoc1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(assoc1);
        assocnode.component_vector.add(assoc2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"ASSOC", ai, block);
        IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        assoc1.errorpedigree_node = error1;

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            assoc1.insertparafile(parafilenode);
            setASSOCblock(block, assoc2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.assoc_count++;
      }
    }

    if (dm.hasValue("Ageon_array")) {
      Vector Ageon_array = (Vector) dm.getValue("Ageon_array");
      for (int i=0;i<Ageon_array.size();i++)
      {
        final String block = (String)Ageon_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "ageon analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("AGEON");
        NodeInfo ageonnode = new NodeInfo(blockname,"Analysis","AGEON" ,ai, block);
        IconNode ageon = new IconNode(ageonnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "AGEON", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, ageon, ageon.getChildCount());

        final AGEON1 ageon1 = new AGEON1(ai, ageon, errorfolder);
        final AGEON2 ageon2 = new AGEON2(ageon1);
        ageon1.OutputNameField.setText(blockname);

        ageonnode.component_vector.add(ageon1);
        ageonnode.component_vector.add(ageon2);
        ageonnode.component_name_vector.add("Files");
        ageonnode.component_name_vector.add("Analysis Definition");

        errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"AGEON", ai, block);
        IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        ageon1.errorpedigree_node = error1;

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            ageon1.insertparafile(parafilenode);
            setAGEONblock(block, ageon2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(ageon, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(ageon.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.ageon_count++;
      }
    }

    if (dm.hasValue("Freq_array")) {
      Vector Freq_array = (Vector) dm.getValue("Freq_array");
      for (int i=0;i<Freq_array.size();i++)
      {
        final String block = (String)Freq_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "freq analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("FREQ");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","FREQ" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "FREQ", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final FREQ1 freq1 = new FREQ1(ai, assoc, errorfolder);
        final FREQ2 freq2 = new FREQ2(freq1);
        freq1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(freq1);
        assocnode.component_vector.add(freq2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Required Pedigree file", "ErrorPedigreeNode" ,"FREQ", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorpedigree_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            freq1.insertparafile(parafilenode);
            setFREQblock(block, freq2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.freq_count++;
      }
    }
    if (dm.hasValue("Fcor_array")) {
      Vector Fcor_array = (Vector) dm.getValue("Fcor_array");
      for (int i=0;i<Fcor_array.size();i++)
      {
        final String block = (String)Fcor_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "fcor analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("FCOR");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","FCOR" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "FCOR", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final FCOR1 fcor1 = new FCOR1(ai, assoc, errorfolder);
        final FCOR2 fcor2 = new FCOR2(fcor1);
        fcor1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(fcor1);
        assocnode.component_vector.add(fcor2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","FCOR", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        fcor1.errorpedigree_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run() {
            fcor1.insertparafile(parafilenode);
            setFCORblock(block, fcor2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.fcor_count++;
     }
    }
    if (dm.hasValue("Genibd_array")) {
      Vector Genibd_array = (Vector) dm.getValue("Genibd_array");
      for (int i=0;i<Genibd_array.size();i++)
      {
        final String block = (String)Genibd_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "genibd analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("GENIBD");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","GENIBD" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "GENIBD", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final GENIBD1 freq1 = new GENIBD1(ai, assoc, errorfolder);
        final GENIBD2 freq2 = new GENIBD2(freq1);
        freq1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(freq1);
        assocnode.component_vector.add(freq2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"GENIBD", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorpedigree_node = error1;

        nodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode" ,"GENIBD", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorLocusNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorlocus_node = error1;

        nodeinfo = new NodeInfo("Genome File to the multipoint Missing", "ErrorGenomeNode" ,"GENIBD", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorGenomeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorgenome_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;
        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            freq1.insertparafile(parafilenode);
            setGENIBDblock(block, freq2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        //inframe.MyTree1.collapsePath(p);
        inframe.genibd_count++;
      }
    }
    if (dm.hasValue("Lodlink_array")) {
      Vector Lodlink_array = (Vector) dm.getValue("Lodlink_array");
      for (int i=0;i<Lodlink_array.size();i++)
      {
        final String block = (String)Lodlink_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "lodlink analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("LODLINK");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","LODLINK" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "LODLINK", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final LODLINK1 lodlink1 = new LODLINK1(ai, assoc, errorfolder);
        final LODLINK2 lodlink2 = new LODLINK2(lodlink1);
        lodlink1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(lodlink1);
        assocnode.component_vector.add(lodlink2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"LODLINK", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        lodlink1.errorpedigree_node = error1;

        nodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","LODLINK", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorLocusNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        lodlink1.errorlocus_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;
        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            lodlink1.insertparafile(parafilenode);
            setLODLINKblock(block, lodlink2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.lodlink_count++;
      }

    }
    if (dm.hasValue("Lodpal_array")) {
      Vector Lodpal_array = (Vector) dm.getValue("Lodpal_array");
      for (int i=0;i<Lodpal_array.size();i++)
      {
        final String block = (String)Lodpal_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "lodpal analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("LODPAL");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","LODPAL" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "LODPAL", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final LODPAL1 freq1 = new LODPAL1(ai, assoc, errorfolder);
        final LODPAL2 freq2 = new LODPAL2(freq1);
        freq1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(freq1);
        assocnode.component_vector.add(freq2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"LODPAL", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorpedigree_node = error1;

        nodeinfo = new NodeInfo("IBD File Missing", "ErrorIBDNode","LODPAL", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorIBDNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorlocus_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;
        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            freq1.insertparafile(parafilenode);
            setLODPALblock(block, freq2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.lodpal_count++;
      }
    }
    if (dm.hasValue("Relpal_array")) {


        Vector Relpal_array = (Vector) dm.getValue("Relpal_array");
        for (int i = 0; i < Relpal_array.size(); i++) {

            final String analysis_block = (String) Relpal_array.get(i);
            String analysis_name = getAnalysisName(analysis_block);
            if (analysis_name.length() == 0) {
                analysis_name = "relpal analysis" + i;
            }

            IconNode relpal = makeRELPALNode(analysis_name, analysis_block.toString());

            NodeInfo relpalnode = (NodeInfo)relpal.getUserObject();
            final RELPAL1 relpal1 = (RELPAL1)relpalnode.component_vector.get(0);
            final RELPAL2 relpal2 = (RELPAL2)relpalnode.component_vector.get(1);

            File analysis_block_file = new File(path+System.getProperty("file.separator")+analysis_name+".par");
            try{
              FileWriter fos = new FileWriter(analysis_block_file);
              fos.write(master_information.toString());
              fos.write("\n");
              fos.write(analysis_block.toString());
              fos.close();
            }catch(Exception ex)
            {
              ex.printStackTrace();
            }

            final NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
            parafilenode.infomodel = dm;
            Runnable setinfo = new Runnable()
            {
              public void run()
              {
                relpal1.insertparafile(parafilenode);
                //setRELPALblock(analysis_block, relpal2);
              }
            };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(relpal, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(relpal.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.relpal_count++;
        }
    }


    if (dm.hasValue("Markerinfo_array")) {
      Vector Markerinfo_array = (Vector) dm.getValue("Markerinfo_array");
      for (int i=0;i<Markerinfo_array.size();i++)
      {
        final String block = (String)Markerinfo_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "markerinfo analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("MARKERINFO");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","MARKERINFO" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "MARKERINFO", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final MARKERINFO1 freq1 = new MARKERINFO1(ai, assoc, errorfolder);
        final MARKERINFO2 freq2 = new MARKERINFO2(freq1);
        freq1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(freq1);
        assocnode.component_vector.add(freq2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","MARKERINFO", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorpedigree_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            freq1.insertparafile(parafilenode);
            setMARKERINFOblock(block, freq2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.markerinfo_count++;
      }

    }
    if (dm.hasValue("Sibpal_array")) {
      Vector Sibpal_array = (Vector) dm.getValue("Sibpal_array");
      for (int i=0;i<Sibpal_array.size();i++)
      {
        final String block = (String)Sibpal_array.get(i);
        String blockname = getAnalysisName(block);
        if (blockname.length() == 0)
        {
          blockname = "sibpal analysis" + i;
        }

        sage_analysis_info ai = new sage_analysis_info("SIBPAL");
        NodeInfo sibpalnode = new NodeInfo(blockname,"Analysis","SIBPAL" ,ai, block);
        IconNode sibpal = new IconNode(sibpalnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "SIBPAL", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, sibpal, sibpal.getChildCount());

        final SIBPAL1 sibpal1 = new SIBPAL1(ai, sibpal, errorfolder);
        final SIBPAL2 sibpal2 = new SIBPAL2(sibpal1);
        final SIBPAL3 sibpal3 = new SIBPAL3(sibpal1);
        final SIBPAL4 sibpal4 = new SIBPAL4(sibpal1);
        sibpal1.OutputNameField.setText(blockname);

        sibpalnode.component_vector.add(sibpal1);
        sibpalnode.component_vector.add(sibpal2);
        sibpalnode.component_vector.add(sibpal3);
        sibpalnode.component_vector.add(sibpal4);

        sibpalnode.component_name_vector.add("Files");
        sibpalnode.component_name_vector.add("Analysis Definition");
        sibpalnode.component_name_vector.add("Mean Tests");
        sibpalnode.component_name_vector.add("Trait Regression");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","SIBPAL", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        sibpal1.errorpedigree_node = error1;

        nodeinfo = new NodeInfo("IBD File Missing", "ErrorIBDNode", "SIBPAL", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorIBDNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        sibpal1.errorlocus_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;
        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            sibpal1.insertparafile(parafilenode);
            setSIBPALblock(block, sibpal2, sibpal3, sibpal4);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(sibpal, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(sibpal.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.sibpal_count++;
      }
    }

    if (dm.hasValue("Sibpal_Trait_array")) {
      Vector Sibpal_array = (Vector) dm.getValue("Sibpal_Trait_array");
      for (int i=0;i<Sibpal_array.size();i++)
      {
        final String block = (String)Sibpal_array.get(i);
        String blockname = "trait regression"+i;

        sage_analysis_info ai = new sage_analysis_info("SIBPAL");
        NodeInfo sibpalnode = new NodeInfo(blockname,"Analysis","SIBPAL" ,ai, block);
        IconNode sibpal = new IconNode(sibpalnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "SIBPAL", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, sibpal, sibpal.getChildCount());

        final SIBPAL1 sibpal1 = new SIBPAL1(ai, sibpal, errorfolder);
        SIBPAL2 sibpal2 = new SIBPAL2(sibpal1);
        SIBPAL3 sibpal3 = new SIBPAL3(sibpal1);
        final SIBPAL4 sibpal4 = new SIBPAL4(sibpal1);
        sibpal1.OutputNameField.setText(blockname);

        sibpalnode.component_vector.add(sibpal1);
        sibpalnode.component_vector.add(sibpal2);
        sibpalnode.component_vector.add(sibpal3);
        sibpalnode.component_vector.add(sibpal4);

        sibpalnode.component_name_vector.add("Files");
        sibpalnode.component_name_vector.add("Analysis Definition");
        sibpalnode.component_name_vector.add("Mean Test");
        sibpalnode.component_name_vector.add("Trait Regression");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","SIBPAL", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        sibpal1.errorpedigree_node = error1;

        nodeinfo = new NodeInfo("IBD File Missing", "ErrorIBDNode", "SIBPAL", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorIBDNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        sibpal1.errorlocus_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;
        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            sibpal1.insertparafile(parafilenode);
            setSIBPAL_TraitRegression(block, sibpal4);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(sibpal, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(sibpal.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.sibpal_count++;
      }
    }

    if (dm.hasValue("Sibpal_Mean_array")) {
      Vector Sibpal_array = (Vector) dm.getValue("Sibpal_Mean_array");
      for (int i=0;i<Sibpal_array.size();i++)
      {
        final String block = (String)Sibpal_array.get(i);
        String blockname = "mean test"+i;

        sage_analysis_info ai = new sage_analysis_info("SIBPAL");
        NodeInfo sibpalnode = new NodeInfo(blockname,"Analysis","SIBPAL" ,ai, block);
        IconNode sibpal = new IconNode(sibpalnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "SIBPAL", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, sibpal, sibpal.getChildCount());

        final SIBPAL1 sibpal1 = new SIBPAL1(ai, sibpal, errorfolder);
        SIBPAL2 sibpal2 = new SIBPAL2(sibpal1);
        final SIBPAL3 sibpal3 = new SIBPAL3(sibpal1);
        SIBPAL4 sibpal4 = new SIBPAL4(sibpal1);
        sibpal1.OutputNameField.setText(blockname);

        sibpalnode.component_vector.add(sibpal1);
        sibpalnode.component_vector.add(sibpal2);
        sibpalnode.component_vector.add(sibpal3);
        sibpalnode.component_vector.add(sibpal4);

        sibpalnode.component_name_vector.add("Files");
        sibpalnode.component_name_vector.add("Analysis Definition");
        sibpalnode.component_name_vector.add("Mean Test");
        sibpalnode.component_name_vector.add("Trait Regression");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","SIBPAL", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        sibpal1.errorpedigree_node = error1;

        nodeinfo = new NodeInfo("IBD File Missing", "ErrorIBDNode", "SIBPAL", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorIBDNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        sibpal1.errorlocus_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;
        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            sibpal1.insertparafile(parafilenode);
            setSIBPAL_MeanTest(block, sibpal3);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(sibpal, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(sibpal.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.sibpal_count++;
      }
    }

    if (dm.hasValue("Segreg_array")) {
      Vector Segreg_array = (Vector) dm.getValue("Segreg_array");
      for (int i=0;i<Segreg_array.size();i++)
      {
        final String block = (String)Segreg_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "segreg analysis"+i;
        }

        int segreg_type = getSegregType(block, master_information);

        if(segreg_type != -1)
        {
          sage_analysis_info ai = new sage_analysis_info("SEGREG");
          NodeInfo segregnode = new NodeInfo(blockname,"Analysis","SEGREG" ,ai, block);
          IconNode segreg = new IconNode(segregnode, "Analysis");

          NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "SEGREG", ai, block);
          IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
          inframe.treeModel.insertNodeInto(errorfolder, segreg, segreg.getChildCount());

          final SEGREG1 segreg1 = new SEGREG1(ai, segreg, errorfolder);
          final SEGREG2 segreg2 = new SEGREG2(segreg1);
          final SEGREG3 segreg3 = new SEGREG3(segreg2);
          final SEGREG4 segreg4 = new SEGREG4(segreg2);
          final SEGREG5 segreg5 = new SEGREG5();
          segreg1.OutputNameField.setText(blockname);
          final int segreg_type_info = segreg_type;

          segregnode.component_vector.add(segreg1);
          segregnode.component_vector.add(segreg2);
          segregnode.component_vector.add(segreg3);
          segregnode.component_vector.add(segreg4);
          segregnode.component_vector.add(segreg5);

          segregnode.component_name_vector.add("Files");
          segregnode.component_name_vector.add("Analysis Definition");
          segregnode.component_name_vector.add("Quantitative");
          segregnode.component_name_vector.add("Binary");
          segregnode.component_name_vector.add("Binary with variable age of onset");

          segreg2.setSEGREGpanel(segreg2.Default);

          File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
          try{
            FileWriter fos = new FileWriter(analysis_block_file);
            fos.write(master_information);
            fos.write("\n");
            fos.write(block);
            fos.close();
          }catch(Exception ex)
          {
            ex.printStackTrace();
          }

          NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","SEGREG", ai, block);
          IconNode error = new IconNode(nodeinfo, "ErrorPedigreeNode");
          inframe.treeModel.insertNodeInto(error, errorfolder, errorfolder.getChildCount());
          segreg1.errorpedigree_node = error;

          inframe.treeModel.insertNodeInto(segreg, jobnode, jobnode.getChildCount());
          TreePath p = new TreePath(segreg.getPath());
          inframe.MyTree1.scrollPathToVisible(p);
          inframe.MyTree1.setSelectionPath(p);

          final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
          parafilenode.infomodel = dm;
          Runnable setinfo = new Runnable()
          {
            public void run()
            {
              segreg1.insertparafile(parafilenode);
              switch(segreg_type_info)
              {
                case 1:
                  segreg2.QRadioButton.setSelected(true);
                  setSEGREGblock_C(block, segreg2, segreg3, false);
                  break;
                case 2:
                  segreg2.BRadioButton.setSelected(true);
                  setSEGREGblock_B(block, segreg2, segreg4, false);
                  break;
                case 3:
                  segreg2.BARadioButton.setSelected(true);
                  setSEGREGblock_BA(block, segreg2, segreg5, false);
                  break;
              }
            }
          };
          SwingUtilities.invokeLater(setinfo);
          inframe.segreg_count++;
        }
        else
        {
            return;
        }
      }
    }
    if (dm.hasValue("Reltest_array")) {
      Vector Reltest_array = (Vector) dm.getValue("Reltest_array");
      for (int i=0;i<Reltest_array.size();i++)
      {
        final String block = (String)Reltest_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "reltest analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("RELTEST");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","RELTEST" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "RELTEST", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final RELTEST1 freq1 = new RELTEST1(ai, assoc, errorfolder);
        final RELTEST2 freq2 = new RELTEST2(freq1);
        freq1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(freq1);
        assocnode.component_vector.add(freq2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"RELTEST",  ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorpedigree_node = error1;

        nodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","RELTEST",  ai, block);
        error1 = new IconNode(nodeinfo, "ErrorLocusNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorlocus_node = error1;

        nodeinfo = new NodeInfo("Genome File Missing", "ErrorGenomeNode","RELTEST",  ai, block);
        error1 = new IconNode(nodeinfo, "ErrorGenomeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorgenome_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run()
          {
             freq1.insertparafile(parafilenode);
            setRELTESTblock(block, freq2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.reltest_count++;
      }

    }
    if (dm.hasValue("Tdtex_array")) {
      Vector Tdtex_array = (Vector) dm.getValue("Tdtex_array");
      for (int i=0;i<Tdtex_array.size();i++)
      {
        final String block = (String)Tdtex_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "tdtex analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("TDTEX");
        final NodeInfo assocnode = new NodeInfo(blockname,"Analysis","TDTEX" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "TDTEX", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final TDTEX1 freq1 = new TDTEX1(ai, assoc, errorfolder);
        final TDTEX2 freq2 = new TDTEX2(freq1);
        freq1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(freq1);
        assocnode.component_vector.add(freq2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","TDTEX", ai, block);
        IconNode error1 = new IconNode(nodeinfo,"ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorpedigree_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run()
          {
             freq1.insertparafile(parafilenode);
            setTDTEXblock(block, freq2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.tdtex_count++;
      }
    }
    if (dm.hasValue("Pedinfo_array")) {
      Vector Pedinfo_array = (Vector) dm.getValue("Pedinfo_array");
      for (int i=0;i<Pedinfo_array.size();i++)
      {
        final String block = (String)Pedinfo_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "pedinfo analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("PEDINFO");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","PEDINFO" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "PEDINFO", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final PEDINFO1 pedinfo1 = new PEDINFO1(ai, assoc, errorfolder);
        final PEDINFO2 pedinfo2 = new PEDINFO2(pedinfo1);
        pedinfo1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(pedinfo1);
        assocnode.component_vector.add(pedinfo2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","PEDINFO",ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        pedinfo1.errorpedigree_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            pedinfo1.insertparafile(parafilenode);
            setPEDINFOblock(block, pedinfo2);
          }
        };

        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.pedinfo_count++;
      }
    }
    if (dm.hasValue("Mlod_array")) {
      Vector Mlod_array = (Vector) dm.getValue("Mlod_array");
      for (int i=0;i<Mlod_array.size();i++)
      {
        final String block = (String)Mlod_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "mlod analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("MLOD");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","MLOD" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "MLOD", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final MLOD1 freq1 = new MLOD1(ai, assoc, errorfolder);
        final MLOD2 freq2 = new MLOD2(freq1);
        freq1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(freq1);
        assocnode.component_vector.add(freq2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"MLOD",ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorpedigree_node = error1;

        nodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","MLOD", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorLocusNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorlocus_node = error1;

        nodeinfo = new NodeInfo("Trait Locus File Missing", "ErrorTraitNode","MLOD", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorTraitNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errortrait_node = error1;

        nodeinfo = new NodeInfo("Genome File Missing", "ErrorGenomeNode","MLOD", ai, block);
        error1 = new IconNode(nodeinfo, "ErrorGenomeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        freq1.errorgenome_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

        Runnable setinfo = new Runnable()
        {
          public void run()
          {
            freq1.insertparafile(parafilenode);
            setMLODblock(block, freq2);
          }
        };
        SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);
        inframe.mlod_count++;
      }
    }
    if (dm.hasValue("Decipher_array")) {
      Vector Decipher_array = (Vector) dm.getValue("Decipher_array");
      for (int i=0;i<Decipher_array.size();i++)
      {
        final String block = (String)Decipher_array.get(i);
        String blockname = getAnalysisName(block);
         if(blockname.length()==0)
        {
          blockname = "decipher analysis"+i;
        }

        sage_analysis_info ai = new sage_analysis_info("DECIPHER");
        NodeInfo assocnode = new NodeInfo(blockname,"Analysis","DECIPHER" ,ai, block);
        IconNode assoc = new IconNode(assocnode, "Analysis");

        NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "DECIPHER", ai, block);
        IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
        inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

        final DECIPHER1 decipher1 = new DECIPHER1(ai, assoc, errorfolder);
        final DECIPHER2 decipher2 = new DECIPHER2(decipher1);
        decipher1.OutputNameField.setText(blockname);

        assocnode.component_vector.add(decipher1);
        assocnode.component_vector.add(decipher2);
        assocnode.component_name_vector.add("Files");
        assocnode.component_name_vector.add("Analysis Definition");

        File analysis_block_file = new File(path+ System.getProperty("file.separator")+blockname+".par");
        try{
          FileWriter fos = new FileWriter(analysis_block_file);
          fos.write(master_information);
          fos.write("\n");
          fos.write(block);
          fos.close();
        }catch(Exception ex)
        {
          ex.printStackTrace();
        }

        NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"DECIPHER", ai, block);
        IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
        inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
        decipher1.errorpedigree_node = error1;

        final NodeInfo parafilenode = new NodeInfo(blockname,"Parameter File", analysis_block_file);
        parafilenode.infomodel = dm;

         Runnable setinfo = new Runnable()
         {
           public void run()
           {
             decipher1.insertparafile(parafilenode);
             setDECIPHERblock(block, decipher2);
           }
         };
         SwingUtilities.invokeLater(setinfo);

        inframe.treeModel.insertNodeInto(assoc, jobnode, jobnode.getChildCount());
        TreePath p = new TreePath(assoc.getPath());
        inframe.MyTree1.scrollPathToVisible(p);
        inframe.MyTree1.setSelectionPath(p);

        inframe.decipher_count++;
      }
    }
  }

  public void setSEGREG_PrevE(String subblock, SEGREG_Dialog15_PrevE dialog15, boolean IsShow)
  {
    dialog15.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result="";

   while (st_sub.hasMoreTokens()) {
     String temp = st_sub.nextToken().trim();

     if (temp.indexOf("#") >= 0) {
       if (temp.indexOf("#") == 0)
         temp = st_sub.nextToken().trim();
       else
         temp = temp.substring(0, temp.indexOf("#") - 1).trim();
     }

     if (temp.startsWith("covariate")) {
       dialog15.listModel.addElement(temp);
     }

     if (temp.startsWith("age")) {
       String trait[] = temp.split("=");
       if (trait.length > 1) {
         if (trait[1].trim().length() >= 0) {
           result = trait[1].replaceAll("\"", "").trim();
           dialog15.jTextField3.setText(result);
         }
       }
     }
   }
   dialog15.save_init_state(IsShow);
  }

  public void setSEGREG_PrevC(String subblock, SEGREG_Dialog14_PrevC dialog14, boolean IsShow)
  {
    dialog14.listModel.removeAllElements();

    subblock = subblock.substring(15,subblock.length()-1);

    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result="";

    boolean cons = false;
    while (st_sub.hasMoreTokens()) {
        String temp = st_sub.nextToken().trim();

        if (temp.startsWith("constraint")) {
            cons = true;
        } else if (temp.indexOf("}") >= 0) {
            if (cons) {
                cons = false;

                result = "constraint\n{\n" + result + "}";
                dialog14.listModel.addElement(result);
                dialog14.save_init_state(IsShow);
                result = "";
            }
        }

        if (cons) {
            if (temp.startsWith("covariate")
                || temp.startsWith("R")
                || temp.startsWith("N")
                || temp.startsWith("age")) {
                result = result + temp + "\n";

            }
        }

    }
  }

  public void setSEGREG_SusCov(String subblock, SEGREG_Dialog7_SusCov dialog7)
  {
    dialog7.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("covariate") >= 0) {
        dialog7.listModel.addElement(temp);
      }
    }
    dialog7.save_init_state();
  }

  public void setSEGREG_TypeSuscept(String subblock, SEGREG_Dialog4_TypeSuscept dialog4)
  {
    dialog4.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result="";

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("option") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("one") == 0)
              dialog4.jComboBox1.setSelectedIndex(0);
            if (result.compareTo("two") == 0)
              dialog4.jComboBox1.setSelectedIndex(1);
            if (result.compareTo("three") == 0)
              dialog4.jComboBox1.setSelectedIndex(2);
            if (result.compareTo("two_dom") == 0)
              dialog4.jComboBox1.setSelectedIndex(3);
            if (result.compareTo("three_add") == 0)
              dialog4.jComboBox1.setSelectedIndex(4);
            if (result.compareTo("three_dec") == 0)
              dialog4.jComboBox1.setSelectedIndex(5);
            if (result.compareTo("three_inc") == 0)
              dialog4.jComboBox1.setSelectedIndex(6);
          }
        }
      }

      if (temp.indexOf("suscept") >= 0 && temp.indexOf("type_suscept") < 0) {
        dialog4.listModel.addElement(temp);
      }
    }
    dialog4.save_init_state();
  }

  public void setSEGREG_Ascer(String subblock, SEGREG_Dialog13_Ascer dialog13)
  {
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result="";

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.startsWith("cond_set")) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("none") == 0)
              dialog13.jComboBox1.setSelectedIndex(0);
            if (result.compareTo("founders") == 0)
              dialog13.jComboBox1.setSelectedIndex(1);
            if (result.compareTo("psf") == 0)
              dialog13.jComboBox1.setSelectedIndex(2);
            if (result.compareTo("founders_plus_psf") == 0)
              dialog13.jComboBox1.setSelectedIndex(3);
          }
        }
      }

      if (temp.indexOf("psf_indic") >= 0 && temp.indexOf("psf_indec_include") < 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().length() >= 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            dialog13.jComboBox4.setSelectedItem(result);
          }
        }
      }

      if (temp.startsWith("psf_indec_include")) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            dialog13.jTextField1.setText(result);
          }
        }
      }


      if (temp.startsWith("cond_val"))
      {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.length()>0 && lambdavalue.length()>0)
            {
              if(lambdapara.compareTo("cond_val")==0)
              {
                if(lambdavalue.compareTo("actual")==0)
                  dialog13.jComboBox2.setSelectedIndex(0);
                if(lambdavalue.compareTo("gte_thresh")==0)
                  dialog13.jComboBox2.setSelectedIndex(1);
                if(lambdavalue.compareTo("lte_thresh")==0)
                  dialog13.jComboBox2.setSelectedIndex(2);
                if(lambdavalue.compareTo("thresh_indic")==0)
                  dialog13.jComboBox2.setSelectedIndex(3);
              }
              else if(lambdapara.compareTo("thresh_indic_high")==0)
                dialog13.jTextField4.setText(lambdavalue);
              else if(lambdapara.compareTo("thresh_indic_low")==0)
                dialog13.jTextField5.setText(lambdavalue);
              else if(lambdapara.compareTo("thresh")==0)
                dialog13.jTextField3.setText(lambdavalue);
            }
          }
        }
      }

      if (temp.startsWith("thresh_indic"))
      {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.length()>0 && lambdavalue.length()>0)
            {
              if(lambdapara.compareTo("thresh_indic")==0)
              {
                  dialog13.jComboBox5.setSelectedItem(lambdavalue);
              }
              else if(lambdapara.compareTo("thresh")==0)
                dialog13.jTextField2.setText(lambdavalue);
            }
          }
        }
      }

      if (temp.startsWith("onset_option")) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("actual") == 0)
              dialog13.jComboBox3.setSelectedIndex(0);
            if (result.compareTo("by_onset") == 0)
              dialog13.jComboBox3.setSelectedIndex(1);
          }
        }
      }
    }
    dialog13.save_init_state();
  }

  public void setSEGREG_Com(String subblock, SEGREG_Dialog1_Com dialog1)
  {
    dialog1.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("covariate") >= 0) {
        dialog1.listModel.addElement(temp);
      }
    }
    dialog1.save_init_state();
  }

  public void setSEGREG_TransM(String subblock, SEGREG_Dialog12_TransM dialog12, boolean IsShow)
  {
    dialog12.IsPopup = IsShow;
    dialog12.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result = "";
    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("option") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("homog_no_trans") == 0)
              dialog12.optionComboBox.setSelectedIndex(0);
            if (result.compareTo("homog_mendelian") == 0)
              dialog12.optionComboBox.setSelectedIndex(1);
            if (result.compareTo("homog_general") == 0)
              dialog12.optionComboBox.setSelectedIndex(2);
            if (result.compareTo("tau_ab_free") == 0)
              dialog12.optionComboBox.setSelectedIndex(3);
            if (result.compareTo("general") == 0)
              dialog12.optionComboBox.setSelectedIndex(4);
            if (result.compareTo("no_trans") == 0)
              dialog12.optionComboBox.setSelectedIndex(5);
          }
        }
      }

      if (temp.indexOf("no_bounds") >= 0) {
        dialog12.noboundsCheckBox.setSelected(true);
      }

      if (temp.indexOf("tau") >= 0 && temp.indexOf("tau_ab_free") < 0) {
        dialog12.listModel.addElement(temp);
      }
    }
    dialog12.save_init_state();
  }

  public void setSEGREG_Geno(String subblock, SEGREG_Dialog11_Geno dialog11)
  {
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");

    String result = "";
    int prob_count = 0;

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("option") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if(result.compareTo("hwe")==0)
              dialog11.optionComboBox.setSelectedIndex(0);
            if (result.compareTo("nhwe") == 0)
              dialog11.optionComboBox.setSelectedIndex(1);
          }
        }
      }

      if (temp.indexOf("prob") >= 0 && temp.indexOf("probs_fixed") < 0) {
        prob_count++;
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("prob") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (prob_count == 1) {
                  if (result.compareTo("AA") == 0)
                    dialog11.prob1ComboBox.setSelectedIndex(1);
                  if (result.compareTo("AB") == 0)
                    dialog11.prob1ComboBox.setSelectedIndex(2);
                  if (result.compareTo("BB") == 0)
                    dialog11.prob1ComboBox.setSelectedIndex(3);
                }
                if (prob_count == 2) {
                  if (result.compareTo("AA") == 0)
                    dialog11.prob2ComboBox.setSelectedIndex(1);
                  if (result.compareTo("AB") == 0)
                    dialog11.prob2ComboBox.setSelectedIndex(2);
                  if (result.compareTo("BB") == 0)
                    dialog11.prob2ComboBox.setSelectedIndex(3);
                }
              }
            }
          }

          if (trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (prob_count == 1)
                  dialog11.prob1valTextField.setText(result);
                if (prob_count == 2)
                  dialog11.prob2valTextField.setText(result);
              }
            }
          }
        }
      }

      if (temp.indexOf("probs_fixed") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("true") == 0)
              dialog11.probsfixedCheckBox.setSelected(true);
          else if (result.compareTo("false") == 0)
            dialog11.probsfixedCheckBox.setSelected(false);

          }
        }
      }

      if (temp.indexOf("freq_A") >= 0) {
        dialog11.freqACheckBox.setSelected(true);
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog11.freqAvalTextField.setText(result);
              }
            }
          }
        }
      }
    }
    dialog11.save_init_state();
  }

  public void setSEGREG_TransF(String subblock, SEGREG_Dialog10_TransF dialog10)
  {
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result="";

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("option") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("none") == 0)
              dialog10.optionComboBox.setSelectedIndex(0);
            if (result.compareTo("george_elston") == 0)
              dialog10.optionComboBox.setSelectedIndex(2);
          }
        }
      }

      if (temp.indexOf("lambda1") >= 0) {
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog10.lambda1ValueTextField.setText(result);
              }
            }
          }
          if (trait[i].indexOf("fixed") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (result.compareTo("true") == 0)
                  dialog10.lambda1FixedCheckBox.setSelected(true);
              }
            }
          }
          if (trait[i].indexOf("lower_bound") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog10.lambda1LBTextField.setText(result);
              }
            }
          }
          if (trait[i].indexOf("upper_bound") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog10.lambda1UBTextField.setText(result);
              }
            }
          }
        }
      }

      if (temp.indexOf("lambda2") >= 0) {
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog10.lambda2ValueTextField.setText(result);
              }
            }
          }
          if (trait[i].indexOf("fixed") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (result.compareTo("false") == 0)
                  dialog10.lambda2FixedCheckBox.setSelected(false);
              }
            }
          }
        }
      }
    }
    dialog10.save_init_state();
  }

  public void setSEGREG_Resid(String subblock, SEGREG_Dialog9_Resid dialog9) {

    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result="";

    dialog9.jCheckBox1.setSelected(false);
    dialog9.jCheckBox2.setSelected(false);
    dialog9.jCheckBox3.setSelected(false);
    dialog9.jCheckBox4.setSelected(false);

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("option") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("equal_po") == 0)
              dialog9.jComboBox1.setSelectedIndex(1);
            if (result.compareTo("arb") == 0)
              dialog9.jComboBox1.setSelectedIndex(2);
          }
        }
      }

      if (temp.indexOf("fm") >= 0) {
        dialog9.jCheckBox1.setSelected(true);
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog9.jTextField1.setText(result);
              }
            }
          }
          if (trait[i].indexOf("fixed") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (result.compareTo("false") == 0)
                  dialog9.jComboBox2.setSelected(false);
              }
            }
          }
        }
      }

      if (temp.indexOf("mo") >= 0) {
        dialog9.jCheckBox2.setSelected(true);
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog9.jTextField2.setText(result);
              }
            }
          }
          if (trait[i].indexOf("fixed") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (result.compareTo("true") == 0)
                  dialog9.jComboBox3.setSelected(true);
              }
            }
          }
        }
      }

      if (temp.indexOf("fo") >= 0) {
        dialog9.jCheckBox3.setSelected(true);
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog9.jTextField3.setText(result);
              }
            }
          }
          if (trait[i].indexOf("fixed") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (result.compareTo("true") == 0)
                  dialog9.jComboBox4.setSelected(true);
              }
            }
          }
        }
      }

      if (temp.indexOf("ss") >= 0) {
        dialog9.jCheckBox4.setSelected(true);
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog9.jTextField4.setText(result);
              }
            }
          }
          if (trait[i].indexOf("fixed") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (result.compareTo("true") == 0)
                  dialog9.jComboBox5.setSelected(true);
              }
            }
          }
        }
      }
    }
    dialog9.save_init_state();
  }

  public void setSEGREG_FPMM(String subblock, SEGREG_Dialog8_Fpmm dialog8)
  {
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result="";

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("loci") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            dialog8.jTextField1.setText(result);
          }
        }
      }

      if (temp.indexOf("freq") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            dialog8.jTextField2.setText(result);
          }
        }
      }

      if (temp.indexOf("var") >= 0) {
        String trait[] = temp.split(",");
        for (int i = 0; i < trait.length; i++) {
          if (trait[i].indexOf("var") >= 0 || trait[i].indexOf("val") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                dialog8.jTextField3.setText(result);
              }
            }
          }
          if (trait[i].indexOf("fixed") >= 0) {
            String traitname[] = trait[i].split("=");
            if (traitname.length > 1) {
              if (traitname[1].trim().length() >= 0) {
                result = traitname[1].replaceAll("\"", "").trim();
                if (result.compareTo("true") == 0)
                  dialog8.jComboBox1.setSelected(true);
              }
            }
          }
        }
      }

      if (temp.indexOf("onset") >= 0) {
        dialog8.jCheckBox1.setSelected(true);
      }

      if (temp.indexOf("type_dependent") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("S") == 0)
              dialog8.jComboBox2.setSelectedIndex(1);
          }
        }
      }

      if (temp.indexOf("multi_dependent") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("A") == 0)
              dialog8.jComboBox3.setSelectedIndex(1);
            if (result.compareTo("S") == 0)
              dialog8.jComboBox3.setSelectedIndex(2);
          }
        }
      }

      if (temp.indexOf("age_onset") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            dialog8.jComboBox5.setSelectedItem(result);
          }
        }
      }

      if (temp.indexOf("age_exam") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            dialog8.jComboBox6.setSelectedItem(result);
          }
        }
      }
    }
    dialog8.save_init_state();
  }

  public void setSEGREG_VarCov(String subblock, SEGREG_Dialog6_VarCov dialog6)
  {
    dialog6.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("covariate") >= 0) {
        dialog6.listModel.addElement(temp);
      }
    }
    dialog6.save_init_state();
  }

  public void setSEGREG_TypeVar(String subblock, SEGREG_Dialog3_TypeVar dialog3)
  {
    dialog3.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");
    String result="";

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("option") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("one") == 0)
              dialog3.jComboBox1.setSelectedIndex(0);
            if (result.compareTo("two") == 0)
              dialog3.jComboBox1.setSelectedIndex(1);
            if (result.compareTo("three") == 0)
              dialog3.jComboBox1.setSelectedIndex(2);
            if (result.compareTo("two_dom") == 0)
              dialog3.jComboBox1.setSelectedIndex(3);
            if (result.compareTo("three_add") == 0)
              dialog3.jComboBox1.setSelectedIndex(4);
          }
        }
      }

      if (temp.indexOf("var") >= 0 && temp.indexOf("type_var") < 0) {
        dialog3.listModel.addElement(temp);
      }
    }
    dialog3.save_init_state();
  }

  public void setSEGREG_MeanCov(String subblock, SEGREG_Dialog5_MeanCov dialog5) {
    dialog5.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(subblock, "\n");

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("covariate") >= 0) {
        dialog5.listModel.addElement(temp);
      }
    }
    dialog5.save_init_state();
  }

  public void setSEGREG_TypeMean(String typemeanblock,
                                 SEGREG_Dialog2_TypeMean dialog2) {
    dialog2.listModel.removeAllElements();
    StringTokenizer st_sub = new StringTokenizer(typemeanblock, "\n");
    String result = "";

    while (st_sub.hasMoreTokens()) {
      String temp = st_sub.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st_sub.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("option") >= 0) {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          if (trait[1].trim().length() >= 0) {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("one") == 0)
              dialog2.jComboBox1.setSelectedIndex(0);
            if (result.compareTo("two") == 0)
              dialog2.jComboBox1.setSelectedIndex(1);
            if (result.compareTo("three") == 0)
              dialog2.jComboBox1.setSelectedIndex(2);
            if (result.compareTo("two_dom") == 0)
              dialog2.jComboBox1.setSelectedIndex(3);
            if (result.compareTo("three_add") == 0)
              dialog2.jComboBox1.setSelectedIndex(4);
            if (result.compareTo("three_dec") == 0)
              dialog2.jComboBox1.setSelectedIndex(5);
            if (result.compareTo("three_inc") == 0)
              dialog2.jComboBox1.setSelectedIndex(6);
          }
        }
      }

      if (temp.indexOf("mean") >= 0 && temp.indexOf("type_mean") < 0) {
        dialog2.listModel.addElement(temp);
      }
    }
    dialog2.save_init_state();
  }

  public String deleteAllSubBlock(String block)
  {
    if (block.indexOf("type_mean") >= 0)
    {
      int here = block.indexOf("type_mean");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "type_mean");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("mean_cov") >= 0)
    {
      int here = block.indexOf("mean_cov");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "mean_cov");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("type_var") >= 0)
    {
      int here = block.indexOf("type_var");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "type_var");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("var_cov") >= 0)
    {
      int here = block.indexOf("var_cov");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "var_cov");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("type_suscept") >= 0)
    {
      int here = block.indexOf("type_suscept");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "type_suscept");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend + 1);
        block = s.toString();
      }
    }

    if (block.indexOf("suscept_cov") >= 0)
    {
      int here = block.indexOf("suscept_cov");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "suscept_cov");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("fpmm") >= 0)
    {
      int here = block.indexOf("fpmm");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "fpmm");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("resid") >= 0)
    {
      int here = block.indexOf("resid");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "resid");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("transformation") >= 0)
    {
      int here = block.indexOf("transformation");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "transformation");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("geno_freq") >= 0)
    {
      int here = block.indexOf("geno_freq");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "geno_freq");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("transmission") >= 0)
    {
      int here = block.indexOf("transmission");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "transmission");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("composite_trait") >= 0)
    {
      int here = block.indexOf("composite_trait");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "composite_trait");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("ascertainment") >= 0)
    {
      int here = block.indexOf("ascertainment");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "ascertainment");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("prev_constraints") >= 0)
    {
      int here = block.indexOf("prev_constraints");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "prev_constraints");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("prev_estimate") >= 0)
    {
      int here = block.indexOf("prev_estimate");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "prev_estimate");
      if (here >= 0 && substart >= 0 && subend >= 0) {
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    return block;
  }

  public int getSegregType(String segreg_block, String masterinformation) {
    String block = segreg_block;
    block = deleteAllSubBlock(block);

    StringTokenizer st = new StringTokenizer(block, "\n");
    String segregtraitname = "";

    while (st.hasMoreTokens()) {
      String temp = st.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf("trait") >= 0)
      {
        String trait[] = temp.split(",");
        for(int i=0;i<trait.length;i++)
        {
          trait[i] = trait[i].trim();
          if(trait[i].startsWith("trait"))
          {
            String traitname[] = trait[i].split("=");
            if(traitname.length > 1)
            {
              if (traitname[1].trim().length() >= 0)
              {
                String result = traitname[1].replaceAll("\"", "").trim();
                segregtraitname = result;
              }
            }
          }
          else if(trait[i].startsWith("type"))
          {
            String traitname[] = trait[i].split("=");
            if(traitname.length > 1)
            {
              if (traitname[1].trim().length() >= 0)
              {
                String result = traitname[1].replaceAll("\"", "").trim();
                if(result.compareTo("continuous")==0)
                  return 1;
                if(result.compareTo("binary")==0)
                  return 2;
                if(result.compareTo("age_onset")==0)
                  return 3;
              }
            }
          }
        }
      }
    }

    StringTokenizer st2 = new StringTokenizer(masterinformation, "\n");

    while (st2.hasMoreTokens()) {
      String temp = st2.nextToken().trim();

      if (temp.indexOf("#") >= 0) {
        if (temp.indexOf("#") == 0)
          temp = st2.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if (temp.indexOf(segregtraitname) >= 0)
      {
        String trait[] = temp.split(",");
        for(int i=0;i<trait.length;i++)
        {
          trait[i] = trait[i].trim();
          if(trait[i].startsWith("binary"))
          {
            return 2;
          }
          if(trait[i].startsWith("continuous"))
          {
            return 1;
          }
        }
      }
    }
      return -1;
  }

  public void setSEGREGblock_C(String original_block, SEGREG2 segreg2, SEGREG3 segreg3, boolean IsShow)
  {
    String block = original_block;
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("title")>=0)
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"", "").trim();
          if (title[1].trim().length() >= 0)
            segreg2.jTextField1.setText(result);
        }
      }

      if (temp.indexOf("trait") >= 0)
      {
        String trait[] = temp.split(",");
        for(int i=0;i<trait.length;i++)
        {
          if(trait[i].indexOf("trait")>=0)
          {
            String traitname[] = trait[i].split("=");
            if(traitname.length > 1)
            {
              if (traitname[1].trim().length() >= 0)
              {
                result = traitname[1].replaceAll("\"", "").trim();
                segreg2.jComboBox4.setSelectedItem(result);
              }
            }
          }
        }
      }

      if (temp.indexOf("type_prob") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          if (trait[1].trim().length() >= 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("true") == 0)
              segreg2.tprobCheckBox.setSelected(true);
          }
        }
      }

      if (temp.indexOf("class") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().length() >= 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            if(result.compareTo("A")==0)
              segreg3.jComboBox1.setSelectedIndex(1);
            if(result.compareTo("D")==0)
              segreg3.jComboBox1.setSelectedIndex(2);
            if(result.compareTo("FPMM")==0)
              segreg3.jComboBox1.setSelectedIndex(3);
          }
        }

        int here = block.indexOf("class");
        int subend = temp.length()+here;

        if (here >= 0 && subend >= 0)
        {
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }
    }

    if (block.indexOf("type_mean") >= 0)
    {
      int here = block.indexOf("type_mean");
      int substart = block.indexOf("{",here);
      int subend = Find_EndBlock_Index(block, "type_mean");

      if(here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);
        setSEGREG_TypeMean(subblock, segreg3.dialog2);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

      if (block.indexOf("mean_cov") >= 0)
      {
        int here = block.indexOf("mean_cov");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "mean_cov");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog5.resulttextfield = segreg3.jTextField2;
          segreg3.dialog5.resultlabel = segreg3.meanCovLabel;
          setSEGREG_MeanCov(subblock, segreg3.dialog5);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("type_var") >= 0)
      {
        int here = block.indexOf("type_var");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "type_var");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog3.resulttextfield = segreg3.jTextField3;
          segreg3.dialog3.resultlabel = segreg3.typeVarLabel;
          setSEGREG_TypeVar(subblock, segreg3.dialog3);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("var_cov") >= 0)
      {
        int here = block.indexOf("var_cov");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "var_cov");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog6.resulttextfield = segreg3.jTextField4;
          segreg3.dialog6.resultlabel = segreg3.varCovLabel;
          setSEGREG_VarCov(subblock, segreg3.dialog6);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("fpmm") >= 0)
      {
        int here = block.indexOf("fpmm");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "fpmm");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog8.resulttextfield = segreg3.jTextField5;
          segreg3.dialog8.resultlabel = segreg3.fpmmLabel;
          setSEGREG_FPMM(subblock, segreg3.dialog8);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("resid") >= 0)
      {
        int here = block.indexOf("resid");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "resid");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog9.resulttextfield = segreg3.jTextField6;
          segreg3.dialog9.resultlabel = segreg3.resCorLabel;
          setSEGREG_Resid(subblock, segreg3.dialog9);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("transformation") >= 0)
      {
        int here = block.indexOf("transformation");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "transformation");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog10.resulttextfield = segreg3.jTextField7;
          segreg3.dialog10.resultlabel = segreg3.transformLabel;
          setSEGREG_TransF(subblock, segreg3.dialog10);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("geno_freq") >= 0)
      {
        int here = block.indexOf("geno_freq");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "geno_freq");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog11.resulttextfield = segreg3.jTextField8;
          segreg3.dialog11.resultlabel1 = segreg3.genoFreq1Label;
          segreg3.dialog11.resultlabel2 = segreg3.genoFreq2Label;
          setSEGREG_Geno(subblock, segreg3.dialog11);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("transmission") >= 0)
      {
        int here = block.indexOf("transmission");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "transmission");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog12.resulttextfield = segreg3.jTextField9;
          segreg3.dialog12.resultlabel = segreg3.transmissionLabel;
          setSEGREG_TransM(subblock, segreg3.dialog12, IsShow);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("composite_trait") >= 0)
      {
        int here = block.indexOf("composite_trait");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "composite_trait");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog1.resulttextfield = segreg3.jTextField10;
          segreg3.dialog1.resultlabel = segreg3.comTraitLabel;
          setSEGREG_Com(subblock, segreg3.dialog1);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

      if (block.indexOf("ascertainment") >= 0)
      {
        int here = block.indexOf("ascertainment");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "ascertainment");

        if (here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          segreg3.dialog13.resulttextfield = segreg3.jTextField11;
          segreg3.dialog13.resultlabel = segreg3.ascertLabel;
          setSEGREG_Ascer(subblock, segreg3.dialog13);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend);
          block = s.toString();
        }
      }

  }

  public void setSEGREGblock_B(String original_block, SEGREG2 segreg2, SEGREG4 segreg4, boolean IsShow)
  {
    String block = original_block;
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("title")>=0)
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"", "").trim();
          if (title[1].trim().length() >= 0)
            segreg2.jTextField1.setText(result);
        }
      }

      if (temp.indexOf("trait") >= 0)
      {
        String trait[] = temp.split(",");
        for(int i=0;i<trait.length;i++)
        {
          if(trait[i].indexOf("trait")>=0)
          {
            String traitname[] = trait[i].split("=");
            if(traitname.length > 1)
            {
              if (traitname[1].trim().length() >= 0)
              {
                result = traitname[1].replaceAll("\"", "").trim();
                segreg2.jComboBox4.setSelectedItem(result);
              }
            }
          }
        }
      }

      if (temp.indexOf("type_prob") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          if (trait[1].trim().length() >= 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("true") == 0)
              segreg2.tprobCheckBox.setSelected(true);
          }
        }
      }

      if (temp.indexOf("class") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().length() >= 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            if(result.compareTo("MLM")==0)
              segreg4.jComboBox1.setSelectedIndex(1);
            if(result.compareTo("FPMM")==0)
              segreg4.jComboBox1.setSelectedIndex(2);
          }
        }
        int here = block.indexOf("class");
        int subend = temp.length()+here;

        if (here >= 0 && subend > 0)
        {
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend+1);
          block = s.toString();
        }
      }
    }

    if (block.indexOf("type_suscept") >= 0)
    {
      int here = block.indexOf("type_suscept");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "type_suscept");

      if (here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);
        setSEGREG_TypeSuscept(subblock, segreg4.dialog4);

        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("suscept_cov") >= 0)
    {
      int here = block.indexOf("suscept_cov");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "suscept_cov");

      if (here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);

        segreg4.dialog7.resulttextfield = segreg4.jTextField2;
        segreg4.dialog7.resultlabel = segreg4.jLabel3;

        setSEGREG_SusCov(subblock, segreg4.dialog7);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    //resid
    if (block.indexOf("resid") >= 0)
    {
      int here = block.indexOf("resid");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "resid");

      if (here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);

        segreg4.dialog9.resulttextfield = segreg4.jTextField4;
        segreg4.dialog9.resultlabel = segreg4.jLabel5;

        setSEGREG_Resid(subblock, segreg4.dialog9);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("geno_freq") >= 0)
    {
      int here = block.indexOf("geno_freq");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "geno_freq");

      if (here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);
        segreg4.dialog11.resulttextfield = segreg4.jTextField5;
        segreg4.dialog11.resultlabel1 = segreg4.jLabel6;
        segreg4.dialog11.resultlabel2 = segreg4.jLabel9;

        setSEGREG_Geno(subblock, segreg4.dialog11);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("transmission") >= 0)
    {
      int here = block.indexOf("transmission");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "transmission");

      if (here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);
        segreg4.dialog12.resulttextfield = segreg4.jTextField6;
        segreg4.dialog12.resultlabel = segreg4.jLabel8;

        setSEGREG_TransM(subblock, segreg4.dialog12, IsShow);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("ascertainment") >= 0)
    {
      int here = block.indexOf("ascertainment");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "ascertainment");

      if (here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);

        segreg4.dialog13.resulttextfield = segreg4.jTextField7;
        segreg4.dialog13.resultlabel = segreg4.jLabel12;

        setSEGREG_Ascer(subblock, segreg4.dialog13);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("prev_constraints") >= 0)
    {
      int here = block.indexOf("prev_constraints");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "prev_constraints");

      if (here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);
        segreg4.dialog14.resulttextfield = segreg4.jTextField8;
        segreg4.dialog14.resultlabel = segreg4.jLabel10;

        setSEGREG_PrevC(subblock, segreg4.dialog14, IsShow);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("prev_estimate") >= 0)
    {
      int here = block.indexOf("prev_estimate");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "prev_estimate");

      if (here >= 0 && substart >= 0 && subend >= 0)
      {
        String subblock = block.substring(here, subend);

        segreg4.dialog15.resulttextfield = segreg4.jTextField9;
        segreg4.dialog15.resultlabel = segreg4.jLabel7;

        setSEGREG_PrevE(subblock, segreg4.dialog15, IsShow);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }
  }

  public void setSEGREGblock_BA(String original_block, SEGREG2 segreg2, SEGREG5 segreg5, boolean IsShow)
  {
    String block = original_block;
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("title")>=0)
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"", "").trim();
          if (title[1].trim().length() >= 0)
            segreg2.jTextField1.setText(result);
        }
      }

      if (temp.indexOf("trait") >= 0)
      {
        String trait[] = temp.split(",");
        for(int i=0;i<trait.length;i++)
        {
          if(trait[i].indexOf("trait")>=0)
          {
            String traitname[] = trait[i].split("=");
            if(traitname.length > 1)
            {
              if (traitname[1].trim().length() >= 0)
              {
                result = traitname[1].replaceAll("\"", "").trim();
                segreg2.jComboBox4.setSelectedItem(result);
              }
            }
          }
        }
      }

      if (temp.indexOf("type_prob") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          if (trait[1].trim().length() >= 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("true") == 0)
              segreg2.tprobCheckBox.setSelected(true);
          }
        }
      }

      if(temp.indexOf("class")>=0)
      {
        int here = block.indexOf("class");
        int subend = temp.length()+here;

        if (here >= 0 && subend >= 0) {
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend+1);
          block = s.toString();
        }
      }
    }

    if (block.indexOf("type_mean") >= 0)
    {
      int here = block.indexOf("type_mean");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "type_mean");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);
        setSEGREG_TypeMean(subblock, segreg5.dialog2);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("type_suscept") >= 0)
    {
      int here = block.indexOf("type_suscept");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "type_suscept");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);
        setSEGREG_TypeSuscept(subblock, segreg5.dialog4);

        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("mean_cov") >= 0)
    {
      int here = block.indexOf("mean_cov");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "mean_cov");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog5.resulttextfield = segreg5.jTextField3;
        segreg5.dialog5.resultlabel = segreg5.jLabel3;

        setSEGREG_MeanCov(subblock, segreg5.dialog5);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("suscept_cov") >= 0)
    {
      int here = block.indexOf("suscept_cov");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "suscept_cov");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog7.resulttextfield = segreg5.jTextField4;
        segreg5.dialog7.resultlabel = segreg5.jLabel4;

        setSEGREG_SusCov(subblock, segreg5.dialog7);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("type_var") >= 0)
    {
      int here = block.indexOf("type_var");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "type_var");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog3.resulttextfield = segreg5.jTextField5;
        segreg5.dialog3.resultlabel = segreg5.jLabel5;

        setSEGREG_TypeVar(subblock, segreg5.dialog3);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("var_cov") >= 0)
    {
      int here = block.indexOf("var_cov");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "var_cov");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog6.resulttextfield = segreg5.jTextField6;
        segreg5.dialog6.resultlabel = segreg5.jLabel6;

        setSEGREG_VarCov(subblock, segreg5.dialog6);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("fpmm") >= 0)
    {
      int here = block.indexOf("fpmm");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "fpmm");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog8.resulttextfield = segreg5.jTextField7;
        segreg5.dialog8.resultlabel = segreg5.jLabel7;

        setSEGREG_FPMM(subblock, segreg5.dialog8);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("transformation") >= 0)
    {
      int here = block.indexOf("transformation");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "transformation");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog10.resulttextfield = segreg5.jTextField8;
        segreg5.dialog10.resultlabel = segreg5.jLabel8;

        setSEGREG_TransF(subblock, segreg5.dialog10);
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("geno_freq") >= 0)
    {
      int here = block.indexOf("geno_freq");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "geno_freq");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog11.resulttextfield = segreg5.jTextField9;
        segreg5.dialog11.resultlabel1 = segreg5.jLabel9;
        segreg5.dialog11.resultlabel2 = segreg5.jLabel9_2;

        setSEGREG_Geno(subblock, segreg5.dialog11);

        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("transmission") >= 0)
    {
      int here = block.indexOf("transmission");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "transmission");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog12.resulttextfield = segreg5.jTextField10;
        segreg5.dialog12.resultlabel = segreg5.jLabel10;

        setSEGREG_TransM(subblock, segreg5.dialog12, IsShow);
        segreg5.jTextField10.setText("Specified");

        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("ascertainment") >= 0)
    {
      int here = block.indexOf("ascertainment");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "ascertainment");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog13.resulttextfield = segreg5.jTextField12;
        segreg5.dialog13.resultlabel = segreg5.jLabel12;

        setSEGREG_Ascer(subblock, segreg5.dialog13);

        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("prev_constraint") >= 0)
    {
      int here = block.indexOf("prev_constraint");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "prev_constraint");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog14.resulttextfield = segreg5.jTextField13;
        segreg5.dialog14.resultlabel = segreg5.jLabel13;

        setSEGREG_PrevC(subblock, segreg5.dialog14, IsShow);
        segreg5.jTextField13.setText("Specified");
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }

    if (block.indexOf("prev_estimate") >= 0)
    {
      int here = block.indexOf("prev_estimate");
      int substart = block.indexOf("{", here);
      int subend = Find_EndBlock_Index(block, "prev_estimate");

      if (here >= 0 && substart >= 0 && subend >= 0) {
        String subblock = block.substring(here, subend);

        segreg5.dialog15.resulttextfield = segreg5.jTextField14;
        segreg5.dialog15.resultlabel = segreg5.jLabel14;

        setSEGREG_PrevE(subblock, segreg5.dialog15, IsShow);
        segreg5.jTextField14.setText("Specified");
        StringBuffer s = new StringBuffer(block);
        s.delete(here, subend);
        block = s.toString();
      }
    }
  }

  public void setSIBPALblock(String original_block, SIBPAL2 sibpal2, SIBPAL3 sibpal3, SIBPAL4 sibpal4)
  {
    sibpal4.dialog3_inter.listModel.removeAllElements();

    String block = original_block;
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("mean_test")>=0)
      {
        int here = block.indexOf("mean_test");
        int substart = block.indexOf("{",here);
        int subend = block.indexOf("}",here);

        if(here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend+1);
          setSIBPAL_MeanTest(subblock, sibpal3);
          sibpal2.jRadioButton3.setSelected(true);
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend+1);
          block = s.toString();
        }
      }

      if (temp.indexOf("interaction") >= 0)
      {
        int here = block.indexOf("interaction");
        int substart = block.indexOf("{",here);
        int subend = block.indexOf("}",here);

        if(here >= 0 && substart >= 0 && subend >= 0)
        {
          String tempsubblock = block.substring(here, subend+1);
          sibpal4.dialog3_inter.listModel.addElement(tempsubblock);
          sibpal4.dialog3_inter.save_init_state();
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend+1);
          block = s.toString();
        }
      }

      if(block.indexOf("interaction")<0)
      {
        if(temp.indexOf("trait_regression")>=0 && temp.indexOf("trait_regression_default")<0)
        {
          int here = block.indexOf("trait_regression");
          int substart = block.indexOf("{",here);
          int subend = block.indexOf("}",here);

          if(here >= 0 && substart >= 0 && subend >= 0)
          {
            String subblock = block.substring(here, subend+1);
            setSIBPAL_TraitRegression(subblock, sibpal4);
            sibpal2.jRadioButton4.setSelected(true);
            StringBuffer s = new StringBuffer(block);
            s.delete(here, subend+1);
            block = s.toString();
          }
        }
      }
      else
        continue;


      if(temp.indexOf("trait_regression_default")>=0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1) {
          result = trait[1].replaceAll("\"", "").trim();
          sibpal4.jComboBox11.setSelectedItem(result);
        }
      }

    }
  }

  public void setSIBPAL_TraitRegression(String subblock, SIBPAL4 panel)
  {
    panel.dialog1_trait.listModel.removeAllElements();
    panel.dialog2_cov.listModel.removeAllElements();

    String block = subblock;
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    CheckableItem[] subset_lm = panel.jSubsetComboBox.ListData;
    CheckableItem[] marker_lm = panel.jMarkerComboBox.ListData;

    String subset_list=new String();
    String marker_list=new String();

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if (temp.indexOf("trait") >= 0 && temp.indexOf("trait_regression") < 0)
      {
        panel.dialog1_trait.listModel.addElement(temp);
        panel.dialog1_trait.save_init_state();
      }

      if (temp.indexOf("covariate") >= 0 && temp.indexOf("covariate_out") < 0 && temp.indexOf("add_sum_covariate") < 0)
      {
        panel.dialog2_cov.listModel.addElement(temp);
        panel.dialog2_cov.save_init_state();
      }

      if(temp.indexOf("subset")>=0)
      {
        String traitname[] = temp.split("=");

        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();

          for (int listindex = 0; listindex < subset_lm.length; listindex++) {
            CheckableItem item = (CheckableItem) subset_lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              subset_list = subset_list + ", " + item;
            }
          }
        }
      }

      if(temp.indexOf("marker")>=0)
      {
        boolean dom = false;
        String traitname[] = temp.split("=");
        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();
          String markername[] = result.split(",");

          if(markername.length>1 && result.indexOf("dom")>=0)
          {
            result = markername[0].replaceAll("\"", "").trim();
            dom = true;
          }

          for (int listindex = 0; listindex < marker_lm.length; listindex++) {
            CheckableItem item = (CheckableItem) marker_lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              if(dom)
                item.setSelected2(true);

              marker_list = marker_list + ", " + item;
            }
          }
        }
      }

      if (temp.indexOf("compute_empirical_pvalues") >= 0)
      {
        if(temp.indexOf("true") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("threshold")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog4_comp.jTextField1.setText(lambdavalue);
                }
              }
              else if(lambdapara.compareTo("replicates")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog4_comp.jTextField2.setText(lambdavalue);
                }
              }
              else if(lambdapara.compareTo("max_replicates")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog4_comp.jTextField3.setText(lambdavalue);
                }
              }
              else if(lambdapara.compareTo("width")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog4_comp.jTextField4.setText(lambdavalue);
                }
              }
              else if(lambdapara.compareTo("confidence")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog4_comp.jTextField5.setText(lambdavalue);
                }
              }
            }
          }
        }

        panel.dialog4_comp.save_init_state();
      }

      if (temp.indexOf("robust_variance") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if (result.indexOf("true") >= 0)
            panel.jComboBox3.setSelected(true);
        }
      }

      if (temp.indexOf("wide_out") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if (result.indexOf("true") >= 0)
            panel.jComboBox6.setSelected(true);
        }
      }

      if (temp.indexOf("regression_method") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if (result.indexOf("diff") >= 0)
            panel.jComboBox1.setSelectedIndex(0);
          else if (result.indexOf("sum") >= 0)
            panel.jComboBox1.setSelectedIndex(1);
          else if (result.indexOf("prod") >= 0)
            panel.jComboBox1.setSelectedIndex(2);
          else if (result.indexOf("w2") >= 0)
            panel.jComboBox1.setSelectedIndex(3);
          else if (result.indexOf("w3") >= 0)
            panel.jComboBox1.setSelectedIndex(4);
          else if (result.indexOf("w4") >= 0)
            panel.jComboBox1.setSelectedIndex(5);
        }
      }

      if (temp.indexOf("skip_uninformative_pairs") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if (result.indexOf("true") >= 0)
            panel.jComboBox7.setSelected(true);
        }
      }

      if (temp.indexOf("identity_weights") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if (result.indexOf("true") >= 0)
            panel.jComboBox2.setSelected(true);
        }
      }

      if (temp.indexOf("export_output") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          if (result.compareTo("true") == 0)
            panel.jComboBox4.setSelected(true);
        }
      }

      if (temp.indexOf("pval_scientific_notation") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          if (result.compareTo("true") == 0)
            panel.jComboBox9.setSelected(true);
        }
      }

      if (temp.indexOf("print_design_matrix") >= 0)
      {
        if(temp.indexOf("rows") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("print_design_matrix")==0)
              {
                if(lambdavalue.length()>0)
                  panel.jComboBox8.setSelectedItem(lambdavalue);
              }
              if(lambdapara.compareTo("rows")==0)
              {
                if(lambdavalue.length()>0)
                  panel.rowTextField.setText(lambdavalue);
              }
            }
          }
        }
        else
        {
            String wide_out[] = temp.split("=");
            if (wide_out.length > 1) {
              result = wide_out[1].replaceAll("\"", "").trim();
              panel.jComboBox8.setSelectedItem(result);
            }

        }
      }

      if (temp.indexOf("print_correlation_matrix") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          panel.printCMComboBox.setSelectedItem(result);
        }
      }

      if (temp.indexOf("print_QLS") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          panel.printQLSComboBox.setSelectedItem(result);
        }
      }

      if (temp.indexOf("w1") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          if (result.length() > 0)
            panel.w1TextField.setText(result);
        }
      }

      if (temp.indexOf("use_pairs") >= 0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if (result.indexOf("full") >= 0)
          {
              panel.usePairComboBox.setSelectedIndex(0);
              panel.w1Label.setEnabled(true);
              panel.w1TextField.setEnabled(true);
          }
          if (result.indexOf("half") >= 0)
          {
              panel.usePairComboBox.setSelectedIndex(1);
              panel.w1Label.setEnabled(false);
              panel.w1TextField.setEnabled(false);
          }
          if (result.indexOf("both") >= 0)
          {
              panel.usePairComboBox.setSelectedIndex(2);
              panel.w1Label.setEnabled(false);
              panel.w1TextField.setEnabled(false);
          }
        }
      }
    }

    subset_list = subset_list.replaceFirst(",", "");
    subset_list = subset_list.trim();
    panel.jSubsetComboBox.setSelectedItem(subset_list);

    marker_list = marker_list.replaceFirst(",","");
    marker_list = marker_list.trim();
    panel.jMarkerComboBox.setSelectedItem(marker_list);
  }

  public void setSIBPAL_MeanTest(String subblock, SIBPAL3 panel)
  {
    String block = subblock;
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    CheckableItem[] trait_lm = panel.jTraitComboBox.ListData;
    CheckableItem[] subset_lm = panel.jSubsetComboBox.ListData;
    CheckableItem[] marker_lm = panel.jMarkerComboBox.ListData;

    String trait_list=new String();
    String subset_list=new String();
    String marker_list=new String();

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("trait")>=0)
      {
        String traitname[] = temp.split("=");

        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();

          for (int listindex = 0; listindex < trait_lm.length; listindex++) {
            CheckableItem item = (CheckableItem) trait_lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              trait_list = trait_list + ", " + item;
            }
          }
        }
      }

      if(temp.indexOf("subset")>=0)
      {
        String traitname[] = temp.split("=");

        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();

          for (int listindex = 0; listindex < subset_lm.length; listindex++) {
            CheckableItem item = (CheckableItem) subset_lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              subset_list = subset_list + ", " + item;
            }
          }
        }
      }

      if(temp.indexOf("marker")>=0)
      {
        String traitname[] = temp.split("=");
        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();

          for (int listindex = 0; listindex < marker_lm.length; listindex++) {
            CheckableItem item = (CheckableItem) marker_lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              marker_list = marker_list + ", " + item;
            }
          }
        }
      }

      if (temp.indexOf("w1") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          if (result.length() > 0)
            panel.jTextField1.setText(result);
        }
      }

      if (temp.indexOf("wide_out") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          if (result.compareTo("true") == 0)
            panel.jCheckBox1.setSelected(true);
        }
      }

      if (temp.indexOf("export_output") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          if (result.compareTo("true") == 0)
            panel.jCheckBox2.setSelected(true);
        }
      }

      if (temp.indexOf("pval_scientific_notation") >= 0)
      {
        String wide_out[] = temp.split("=");
        if (wide_out.length > 1) {
          result = wide_out[1].replaceAll("\"", "").trim();
          if (result.compareTo("true") == 0)
            panel.jCheckBox3.setSelected(true);
        }
      }
    }

    trait_list = trait_list.replaceFirst(",", "");
    trait_list = trait_list.trim();
    panel.jTraitComboBox.setSelectedItem(trait_list);

    subset_list = subset_list.replaceFirst(",", "");
    subset_list = subset_list.trim();
    panel.jSubsetComboBox.setSelectedItem(subset_list);

    marker_list = marker_list.replaceFirst(",","");
    marker_list = marker_list.trim();
    panel.jMarkerComboBox.setSelectedItem(marker_list);
  }

  public void setLODPALblock(String original_block, LODPAL2 panel)
  {
     panel.cov_dialog.listModel.removeAllElements();
     panel.pair_dialog.listModel.removeAllElements();

    String block = original_block;
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    CheckableItem[] lm = panel.jSubsetComboBox.ListData;
    Vector checkitem = new Vector();
    String subset_list=new String();
    String marker_list=new String();

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if (temp.indexOf("trait") >= 0) {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.compareTo("trait")==0)
            {
              panel.trait_dialog.jComboBox1.setSelectedItem(lambdavalue);
            }
            else if(lambdapara.compareTo("cutpoint")==0)
            {
              if (lambdavalue.length()>0)
                panel.trait_dialog.Cutpoint.setText(lambdavalue);
            }
          }
          else
          {
              if(lambdalist[i].indexOf("condisc")>=0)
                panel.trait_dialog.jRadioButton2.setSelected(true);
              if(lambdalist[i].indexOf("noconunaff")>=0)
                panel.trait_dialog.jRadioButton3.setSelected(true);
              if(lambdalist[i].indexOf("contrast")>=0)
                panel.trait_dialog.jRadioButton4.setSelected(true);
          }
        }

        panel.trait_dialog.save_init_state();
      }

      if (temp.indexOf("covariate") >= 0 && temp.indexOf("pair_covariate") < 0 )
      {
        panel.cov_dialog.listModel.addElement(temp);
        panel.cov_dialog.save_init_state();
      }

      if(temp.indexOf("subset")>=0)
      {
        String traitname[] = temp.split("=");

        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();

          for (int listindex = 0; listindex < lm.length; listindex++) {
            CheckableItem item = (CheckableItem) lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              subset_list = subset_list + ", " + item;
            }
          }
        }
      }

      if(temp.indexOf("marker")>=0)
      {
        String traitname[] = temp.split("=");
        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"","").trim();

          VariableData tempdata = new VariableData(result, "marker");
          CheckableItem items = new CheckableItem(tempdata);
          items.setSelected(true);
          checkitem.add(items);
          marker_list = marker_list+", "+items;
        }
      }

      if (temp.indexOf("diagnostic") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().length() >= 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            panel.jComboBox6.addItem(result);
            panel.jComboBox6.setSelectedItem(result);
          }
        }
      }

      if (temp.indexOf("pval_scientific_notation") >= 0)
      {
        panel.jComboBox2.setSelected(true);
      }

      if (temp.indexOf("wide_out") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.indexOf("true") >= 0)
            panel.jComboBox3.setSelected(true);
        }
      }

      if (temp.indexOf("sib_pairs_only") >= 0 || temp.indexOf("sib") >= 0)
      {
        panel.jComboBox4.setSelected(true);
      }

      if (temp.indexOf("turn_off_default") >= 0)
      {
        panel.jComboBox5.setSelected(true);
      }

      if (temp.indexOf("pair_info_file") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();

          int existtemp=-1;
          if((existtemp = result.indexOf("{"))>=0)
          {
            if(existtemp < result.length())
              result = result.substring(0, existtemp-1).trim();
          }

          if (result.length() > 0)
            panel.pair_dialog.Name.setText(result);
        }
      }

      if (temp.indexOf("pair_covariate") >= 0)
      {
        panel.pair_dialog.listModel.addElement(temp);
        panel.pair_dialog.save_init_state();
      }

      if (temp.indexOf("model") >= 0) {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.compareTo("model")==0)
            {
              if(lambdavalue.compareTo("one_parameter")==0)
                panel.auto_dialog.jComboBox1.setSelectedIndex(0);
              if(lambdavalue.compareTo("two_parameter")==0)
                panel.auto_dialog.jComboBox1.setSelectedIndex(1);
            }
            else if(lambdapara.compareTo("alpha")==0)
            {
              if (lambdavalue.length()>0)
                panel.auto_dialog.Alpha.setText(lambdavalue);
            }
          }
          else
          {
              if(lambdalist[i].indexOf("uncon")>=0)
                panel.auto_dialog.jComboBox4.setSelected(true);
          }
        }
        panel.auto_dialog.save_init_state();
      }

      if (temp.indexOf("parent_of_origin") >= 0) {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.compareTo("parent_of_origin")==0)
            {
              if(lambdavalue.compareTo("true")==0)
                panel.auto_dialog.jCheckBox1.setSelected(true);
              if(lambdavalue.compareTo("false")==0)
                panel.auto_dialog.jCheckBox1.setSelected(false);
            }
            else if(lambdapara.compareTo("fixed")==0)
            {
              if (lambdavalue.compareTo("maternal")==0)
                panel.auto_dialog.jComboBox3.setSelectedIndex(1);
              if (lambdavalue.compareTo("paternal")==0)
                panel.auto_dialog.jComboBox3.setSelectedIndex(2);
            }
          }
          else
          {
              if(lambdalist[i].indexOf("all_pairs")>=0)
                panel.auto_dialog.jComboBox5.setSelected(true);
          }
        }
        panel.auto_dialog.save_init_state();
      }

      if (temp.indexOf("pair_type") >= 0) {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();

          if (result.compareTo("M-M") == 0)
            panel.xlin_dialog.jComboBox1.setSelectedIndex(1);
          if (result.compareTo("M-F") == 0)
            panel.xlin_dialog.jComboBox1.setSelectedIndex(2);
          if (result.compareTo("F-F") == 0)
            panel.xlin_dialog.jComboBox1.setSelectedIndex(3);
        }
        panel.xlin_dialog.save_init_state();
      }

      if (temp.indexOf("lambda1_equal") >= 0) {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();

          if (result.compareTo("false") == 0)
            panel.xlin_dialog.jComboBox2.setSelected(false);
        }
        panel.xlin_dialog.save_init_state();
      }

      if (temp.indexOf("lambda2_fixed") >= 0) {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.compareTo("lambda2_fixed")==0)
            {
              if (lambdavalue.compareTo("false") == 0)
                panel.xlin_dialog.jComboBox3.setSelected(false);
            }

            if(lambdapara.compareTo("alpha")==0)
            {
              if (lambdavalue.length() > 0)
                panel.xlin_dialog.Alpha.setText(lambdavalue);
            }
          }
        }
        panel.xlin_dialog.save_init_state();
      }
    }

    subset_list = subset_list.replaceFirst(",", "");
    subset_list = subset_list.trim();
    panel.jSubsetComboBox.setSelectedItem(subset_list);

    marker_list = marker_list.replaceFirst(",","");
    marker_list = marker_list.trim();

    int length = checkitem.size();
    CheckableItem[] itemlist = new CheckableItem[length];
    for (int i = 0; i < length; i++) {
      itemlist[i] = (CheckableItem) checkitem.get(i);
    }
    panel.jMarkerComboBox.setData(itemlist);

    panel.jMarkerComboBox.setSelectedItem(marker_list);

  }

  public Vector Find_Match_Block(String subblocks, String matchstring) {

    boolean block = false;

    String result_block = new String();
    Vector result_vector = new Vector();

    String strFileLine_original = new String();
    String strFileLine_temp = new String();

    int block_count = 0;
    boolean block_count_start = false;

    int start_index = -1;
    int block_length=-1;

    String para_array[] = subblocks.split("\n");

    for(int in=0;in<para_array.length;in++)
    {
      strFileLine_original = para_array[in];
      strFileLine_temp = strFileLine_original.toLowerCase().trim();


      if (strFileLine_temp.startsWith("#")) {
        if(!block)
        {
          if(in+1 < para_array.length)
          {
            strFileLine_original = para_array[++in];
            strFileLine_temp = strFileLine_original.toLowerCase().trim();
          }
        }
      }

      if (strFileLine_temp.startsWith(matchstring)) {
          block = true;
          start_index = subblocks.indexOf(matchstring);
      }

      if (block){
             result_block = result_block + strFileLine_original + "\n";
             if (strFileLine_temp.indexOf("{") >= 0) {
               block_count_start = true;
               block_count++;
             }

             if (strFileLine_temp.indexOf("}") >= 0) {
               block_count--;
             }

             if (block_count_start && block_count == 0) {
               block = false;
               block_count_start = false;
               block_length=result_block.length();

               int end_index = start_index + block_length;
               if(start_index >= 0 && end_index > start_index)
               {
                 StringBuffer s = new StringBuffer(subblocks);
                 s.delete(start_index, end_index);
                 subblocks = s.toString();
               }
             }
      }
    }

    result_vector.add(result_block.trim());
    result_vector.add(subblocks.trim());
    return result_vector;
  }

  public void setRELPALblock(String original_block, RELPAL2 panel)
  {
   panel.firstDialog.listModel.removeAllElements();
   panel.secondDialog.listModel.removeAllElements();

   String block = original_block;

   CheckableItem[] traitlm = panel.traitComboBox.ListData;
   CheckableItem[] subsetlm = panel.subsetComboBox.ListData;
   String subset_list=new String();
   String trait_list=new String();

   boolean p_value = false;

   Vector flb = Find_Match_Block(block, "first_level");
   String first_block_temp = flb.get(0).toString();
   if (first_block_temp.length() > 0) {
       int first_brackets = first_block_temp.indexOf("{");
       int last_brackets = first_block_temp.lastIndexOf("}");
       first_block_temp = first_block_temp.substring(first_brackets + 1, last_brackets);

       String block_array[] = first_block_temp.split("\n");
       String first_block = "";

       for (int in = 0; in < block_array.length; in++) {
           String temp = block_array[in].trim();

           if (temp.compareTo("batch") == 0) {
               panel.firstDialog.batchCheckBox.setSelected(true);
           }
           else if(temp.startsWith("normalize_residual"))
           {
               String nor_array[] = temp.split("=");

               if(nor_array.length==2)
               {
                   if (nor_array[1].replaceAll("\"","").trim().compareTo("true")==0)
                   panel.firstDialog.normalizeCheckBox.setSelected(true);
               }
           }
           else {
               first_block = first_block + temp + "\n"; ;
           }
       }
       panel.firstDialog.listModel.addElement(first_block);
       panel.firstDialog.save_init_state();
   }

   Vector slb = Find_Match_Block(flb.get(1).toString(), "second_level");
   String second_block = slb.get(0).toString();
   if (second_block.length() > 0) {

       int first_brackets = second_block.indexOf("{");
       int last_brackets = second_block.lastIndexOf("}");
       second_block = second_block.substring(first_brackets + 1, last_brackets);

       int startIndex = second_block.indexOf("naive_variance");
       if(startIndex>=0)
       {
           int endIndex = second_block.indexOf("\n", startIndex);
           String temp = second_block.substring(startIndex, endIndex);

           if(temp.indexOf("false")>=0)
               panel.secondDialog.naiveCheckBox.setSelected(false);

           StringBuffer stemp = new StringBuffer(second_block);
           second_block = stemp.delete(startIndex, endIndex).toString();
       }
       startIndex = second_block.indexOf("sandwich_variance");
       if(startIndex>=0)
       {
           int endIndex = second_block.indexOf("\n", startIndex);
           String temp = second_block.substring(startIndex, endIndex);

           if(temp.indexOf("false")>=0)
               panel.secondDialog.sandCheckBox.setSelected(false);

           StringBuffer stemp = new StringBuffer(second_block);
           second_block = stemp.delete(startIndex, endIndex).toString();
       }
       startIndex = second_block.indexOf("alternative_variance");
       if(startIndex>=0)
       {
           int endIndex = second_block.indexOf("\n", startIndex);
           String temp = second_block.substring(startIndex, endIndex);

           if(temp.indexOf("false")>=0)
               panel.secondDialog.alterCheckBox.setSelected(false);

           StringBuffer stemp = new StringBuffer(second_block);
           second_block = stemp.delete(startIndex, endIndex).toString();
        }
       startIndex = second_block.indexOf("ibd_variance");
       if(startIndex>=0)
       {
           int endIndex = second_block.indexOf("\n", startIndex);
           String temp = second_block.substring(startIndex, endIndex);

           if(temp.indexOf("false")>=0)
               panel.secondDialog.ibdCheckBox.setSelected(false);

           StringBuffer stemp = new StringBuffer(second_block);
           second_block = stemp.delete(startIndex, endIndex).toString();
       }

       panel.secondDialog.listModel.addElement(second_block);
       panel.secondDialog.save_init_state();
   }

   block = slb.get(1).toString();
   StringTokenizer st = new StringTokenizer(block, "\n");
   String result="";

   while (st.hasMoreTokens())
   {
     String temp = st.nextToken().trim();

     if(temp.indexOf("#")>=0)
     {
       if(temp.indexOf("#")==0)
         temp = st.nextToken().trim();
       else
         temp = temp.substring(0, temp.indexOf("#")-1).trim();
     }

     if (temp.indexOf("trait") >= 0) {
         String traitname[] = temp.split("=");

         if(traitname.length > 1)
         {
           result = traitname[1].replaceAll("\"", "").trim();

           for (int listindex = 0; listindex < traitlm.length; listindex++) {
             CheckableItem item = (CheckableItem) traitlm[listindex];
             if (item.toString().compareTo(result) == 0)
             {
               item.setSelected(true);
               trait_list = trait_list + ", " + item;
             }
           }
       }
     }

     if (temp.indexOf("model") >= 0) {
       String trait[] = temp.split("=");
       if(trait.length > 1)
       {
         result = trait[1].replaceAll("\"","").trim();

         if (result.compareTo("single_marker") == 0)
           panel.modelComboBox.setSelectedIndex(0);
         if (result.compareTo("multiple_marker") == 0)
           panel.modelComboBox.setSelectedIndex(1);
         if (result.compareTo("zero_marker") == 0)
           panel.modelComboBox.setSelectedIndex(2);
       }
     }

     if (temp.indexOf("pvalue_options") >= 0) {
         p_value = true;
     }

     if(p_value)
     {
         if (temp.indexOf("seed") >= 0)
         {
             String seed[] = temp.split("=");
             if(seed.length>1)
                 panel.compDialog.seedTextField.setText(seed[1].trim());
             panel.compDialog.save_init_state();
         }
         else if (temp.indexOf("replicates") >= 0)
         {
             String seed[] = temp.split("=");
             if(seed.length>1)
                 panel.compDialog.repTextField.setText(seed[1].trim());
             panel.compDialog.save_init_state();
         }
         else if (temp.indexOf("min_replicates") >= 0)
         {
             String seed[] = temp.split("=");
             if(seed.length>1)
                 panel.compDialog.minTextField.setText(seed[1].trim());
             panel.compDialog.save_init_state();
         }
         else if (temp.indexOf("max_replicates") >= 0)
         {
             String seed[] = temp.split("=");
             if(seed.length>1)
                 panel.compDialog.maxTextField.setText(seed[1].trim());
             panel.compDialog.save_init_state();
         }
         else if (temp.indexOf("width") >= 0)
         {
             String seed[] = temp.split("=");
             if(seed.length>1)
                 panel.compDialog.widthTextField.setText(seed[1].trim());
             panel.compDialog.save_init_state();
         }
         else if (temp.indexOf("confidence") >= 0)
         {
             String seed[] = temp.split("=");
             if(seed.length>1)
                 panel.compDialog.conTextField.setText(seed[1].trim());
             panel.compDialog.save_init_state();
         }

         if (temp.indexOf("}") >= 0)
             p_value = false;
     }

     if(temp.indexOf("subset")>=0)
     {
       String traitname[] = temp.split("=");

       if(traitname.length > 1)
       {
         result = traitname[1].replaceAll("\"", "").trim();

         for (int listindex = 0; listindex < subsetlm.length; listindex++) {
           CheckableItem item = (CheckableItem) subsetlm[listindex];
           if (item.toString().compareTo(result) == 0)
           {
             item.setSelected(true);
             subset_list = subset_list + ", " + item;
           }
         }
       }
     }

     if (temp.indexOf("use_pair") >= 0)
     {
       String trait[] = temp.split("=");
       if(trait.length > 1)
       {
         result = trait[1].replaceAll("\"","").trim();
         if (result.compareTo("all") == 0)
           panel.pairtypeComboBox.setSelectedIndex(0);
         else if (result.compareTo("sib") == 0)
           panel.pairtypeComboBox.setSelectedIndex(1);
         else if (result.compareTo("fsib") == 0)
           panel.pairtypeComboBox.setSelectedIndex(2);
       }
      }

     if (temp.indexOf("detailed_out") >= 0)
     {
       String wide_out[] = temp.split("=");
       if (wide_out.length > 1) {
         result = wide_out[1].replaceAll("\"", "").trim();
         if (result.compareTo("true") == 0)
           panel.detailedCheckBox.setSelected(true);
       }
     }

     if (temp.indexOf("export_output") >= 0)
     {
       String wide_out[] = temp.split("=");
       if (wide_out.length > 1) {
         result = wide_out[1].replaceAll("\"", "").trim();
         if (result.compareTo("true") == 0)
           panel.tabularCheckBox.setSelected(true);
       }
      }
   }

   subset_list = subset_list.replaceFirst(",", "");
   subset_list = subset_list.trim();
   panel.subsetComboBox.setSelectedItem(subset_list);

   trait_list = trait_list.replaceFirst(",", "");
   trait_list = trait_list.trim();
   panel.traitComboBox.setSelectedItem(trait_list);
  }

  public void setLODLINKblock(String original_block, LODLINK2 panel)
  {
    panel.dialog1.listModel.removeAllElements();
    panel.dialog3.listModel.removeAllElements();
    panel.dialog2.listModel.removeAllElements();

    String block = original_block;

    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";
    boolean lods = false;


    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("title")>=0)
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"", "").trim();
          if (title[1].trim().length() >= 0)
            panel.title.setText(result);
        }
      }

      if(temp.indexOf("model")>=0)
      {
        String title[] = temp.split(",");
        if(title.length > 1)
        {
          String model[] = title[1].split("=");
         if(model.length>1)
         {
           String modelpara = model[0].replaceAll("\"", "").trim();
           String modelvalue = model[1].replaceAll("\"", "").trim();

           if(modelpara.length()>0 && modelvalue.length()>0)
           {
             if(modelpara.compareTo("trait")==0)
             {
               panel.jRadioButton1.setSelected(true);
               panel.jTextField1.setText(modelvalue);
             }
             if(modelpara.compareTo("marker")==0)
             {
               panel.jRadioButton2.setSelected(true);
               panel.jTextField2.setText(modelvalue);
             }
           }
         }
        }
      }

      if(temp.indexOf("linkage_tests")>=0)
      {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if (lambdapara.length() > 0 && lambdavalue.length() > 0)
            {
              if(lambdavalue.compareTo("true")==0)
              {
                if(lambdapara.compareTo("linkage_tests")==0)
                 {
                    panel.linkagetestCheckBox.setSelected(true);
                 }
                 else if(lambdapara.compareTo("sex_specific")==0)
                 {
                   panel.linkagesexCheckBox.setSelected(true);
                 }
                 else if(lambdapara.compareTo("homog")==0)
                 {
                   panel.linkagehomoCheckBox.setSelected(true);
                 }
              }
            }
          }
        }
      }

      if(temp.indexOf("genotypes")>=0)
      {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if (lambdapara.length() > 0 && lambdavalue.length() > 0)
            {
              if(lambdavalue.compareTo("true")==0)
              {
                if(lambdapara.compareTo("genotypes")==0)
                {
                  panel.genotypeCheckBox.setSelected(true);
                }
                else if(lambdapara.compareTo("sex_specific")==0)
                {
                  panel.genotypesexCheckBox.setSelected(true);
                }
              }
            }
          }
        }
      }

      if(temp.indexOf("smiths_test")>=0)
      {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if (lambdapara.length() > 0 && lambdavalue.length() > 0)
            {
              if(lambdavalue.compareTo("true")==0)
              {
                if(lambdapara.compareTo("smiths_test")==0)
                {
                  panel.smithtestCheckBox.setSelected(true);
                }
                else if(lambdapara.compareTo("sex_specific")==0)
                {
                  panel.smithsexCheckBox.setSelected(true);
                }
              }
            }
          }
        }
      }

      if(temp.indexOf("mortons_test")>=0)
      {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if (lambdapara.length() > 0 && lambdavalue.length() > 0)
            {
              if(lambdavalue.compareTo("true")==0)
              {
                if(lambdapara.compareTo("mortons_test")==0)
                {
                  panel.dialog1.jComboBox1.setSelected(true);
                }
                else if(lambdapara.compareTo("sex_specific")==0)
                {
                  panel.dialog1.jComboBox2.setSelected(true);
                }
              }
            }
          }
        }
      }

      if (temp.indexOf("group") >= 0)
      {
        int here = block.indexOf("group");
        int substart = block.indexOf("{",here);
        int subend = block.indexOf("}",here+1);

        if(here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend+1);
          panel.dialog1.listModel.addElement(subblock);
          panel.dialog1.save_init_state();
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend+1);
          block = s.toString();
        }
      }

      if (temp.indexOf("lods") >= 0)
      {
        lods = true;
      }

      if (lods && temp.indexOf("option") >= 0)
      {
        String title[] = temp.split("=");
        if (title.length > 1)
        {
          result = title[1].replaceAll("\"", "").trim();
          if (result.compareTo("none") == 0)
            panel.lodoptionComboBox.setSelectedIndex(0);
          else if (result.compareTo("specified") == 0)
            panel.lodoptionComboBox.setSelectedIndex(2);
          else
            panel.lodoptionComboBox.setSelectedIndex(1);
        }
      }

      if (lods && temp.startsWith("sex_specific"))
      {
        String title[] = temp.split("=");
        if (title.length > 1)
        {
          result = title[1].replaceAll("\"", "").trim();
          if (result.compareTo("true") == 0)
            panel.lodsexCheckBox.setSelected(true);
        }
      }

      if (temp.indexOf("male_female") >= 0)
      {
        int here = block.indexOf("male_female");
        int substart = block.indexOf("{",here);
        int subend = block.indexOf("}",here);

        if(here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);
          StringTokenizer male_female = new StringTokenizer(subblock, "\n");
          while (male_female.hasMoreTokens()) {
            String male_female_temp = male_female.nextToken().trim();

            if(male_female_temp.indexOf("theta")>=0)
            {
              panel.dialog3.listModel.addElement(male_female_temp.trim());
              panel.dialog3.save_init_state();
            }
          }
        }
      }

      if (temp.indexOf("average") >= 0)
      {
        int here = block.indexOf("average");
        int substart = block.indexOf("{",here);
        int subend = block.indexOf("}",here);

        if(here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend);

          StringTokenizer male_female = new StringTokenizer(subblock, "\n");
          while (male_female.hasMoreTokens()) {
            String male_female_temp = male_female.nextToken().trim();
            if(male_female_temp.indexOf("theta")>=0)
            {
              panel.dialog2.listModel.addElement(male_female_temp.trim());
              panel.dialog2.save_init_state();
            }
          }
        }
      }
    }


  }

  public void setAGEONblock(String block, AGEON2 panel)
  {
    panel.dialog1.listModel.removeAllElements();
    panel.dialog2.listModel.removeAllElements();
    panel.dialog3.listModel.removeAllElements();

    StringTokenizer st = new StringTokenizer(block, "\n");

    String result="";

    boolean mean_cov = false;
    boolean in_mean_cov = false;
    boolean var_cov = false;
    boolean in_var_cov = false;
    boolean sus_cov = false;
    boolean in_sus_cov = false;

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("title")>=0)
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"", "").trim();
          if (title[1].trim().length() > 0)
            panel.title.setText(result);
        }
      }

      if(temp.indexOf("affectedness")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().length() > 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            panel.jComboBox1.setSelectedItem(result);
          }
        }
      }

      if(temp.indexOf("age_of_onset")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().length() > 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            panel.jComboBox2.setSelectedItem(result);
          }
        }
      }

      if(temp.indexOf("age_of_exam")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().length() > 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            panel.jComboBox3.setSelectedItem(result);
          }
        }
      }

      if (temp.indexOf("mean_cov") >= 0)
      {
        mean_cov = true;
      }

      if(mean_cov)
      {
        if(temp.indexOf("{") >= 0)
        {
          in_mean_cov = true;
        }
      }

      if(mean_cov)
      {
        if(temp.indexOf("}") >= 0)
        {
          in_mean_cov = false;
          mean_cov = false;
        }
      }

      if (temp.indexOf("var_cov") >= 0)
      {
        var_cov = true;
      }

      if(var_cov)
      {
        if(temp.indexOf("{") >= 0)
        {
          in_var_cov = true;
        }
      }

      if(var_cov)
      {
        if(temp.indexOf("}") >= 0)
        {
          in_var_cov = false;
          var_cov = false;
        }
      }

      if (temp.indexOf("suscept_cov") >= 0)
      {
        sus_cov = true;
      }

      if(sus_cov)
      {
        if(temp.indexOf("{") >= 0)
        {
          in_sus_cov = true;
        }
      }

      if(sus_cov)
      {
        if(temp.indexOf("}") >= 0)
        {
          if(in_sus_cov)
          {
            in_sus_cov = false;
          }
          else
          {
            sus_cov = false;
          }
        }
      }

      if(temp.indexOf("covariate") >= 0)
      {
        if(mean_cov && in_mean_cov)
        {
          panel.dialog1.listModel.addElement(temp);
          panel.dialog1.save_init_state();
        }
        if(var_cov && in_var_cov)
        {
          panel.dialog2.listModel.addElement(temp);
          panel.dialog2.save_init_state();
        }
        if(sus_cov && in_sus_cov)
        {
          panel.dialog3.listModel.addElement(temp);
          panel.dialog3.save_init_state();
        }
      }

      if(temp.startsWith("pool"))
      {
        if(temp.indexOf("#")>0)
        {
          temp = temp.substring(0, temp.indexOf("#")).trim();
        }

        int firstq = temp.indexOf("=");

        if (firstq > 4 && firstq < temp.length())
        {
            String poolb = temp.substring(firstq+1, temp.length()).trim();

            if (poolb.length() >= 0)
            {
                result = poolb.replaceAll("\"", "").trim();

                StringTokenizer pool = new StringTokenizer(result, ",");
                while (pool.hasMoreTokens())
                {
                    String pool_temp = pool.nextToken().trim();

                    if (pool_temp.startsWith("??"))
                    {
                        String each_pool[] = pool_temp.split("=");
                        if (each_pool.length > 1) {
                            if (each_pool[1].trim().length() > 0)
                            {
                                String value = each_pool[1].replaceAll("\"", "").trim();
                                if(value.compareTo("?A")==0 || value.compareTo("A?")==0)
                                {
                                    panel.dialog5.jRadioButton2.setSelected(true);
                                }
                                else if(value.compareTo("?U")==0 || value.compareTo("U?")==0)
                                {
                                    panel.dialog5.jRadioButton3.setSelected(true);
                                }
                                else if(value.compareTo("AA")==0)
                                {
                                    panel.dialog5.jRadioButton4.setSelected(true);
                                }
                                else if(value.compareTo("AU")==0 || value.compareTo("UA")==0)
                                {
                                    panel.dialog5.jRadioButton5.setSelected(true);
                                }
                                else if(value.compareTo("UU")==0)
                                {
                                    panel.dialog5.jRadioButton6.setSelected(true);
                                }
                            }
                        }
                    }
                    else if (pool_temp.startsWith("?A") || pool_temp.startsWith("A?"))
                    {
                        String each_pool[] = pool_temp.split("=");
                        if (each_pool.length > 1) {
                            if (each_pool[1].trim().length() > 0)
                            {
                                String value = each_pool[1].replaceAll("\"", "").trim();
                                if(value.compareTo("AU")==0 || value.compareTo("UA")==0)
                                {
                                    panel.dialog5.jRadioButton8.setSelected(true);
                                }
                                else if(value.compareTo("AA")==0)
                                {
                                    panel.dialog5.jRadioButton9.setSelected(true);
                                }
                            }
                        }
                    }
                    else if (pool_temp.startsWith("?U") || pool_temp.startsWith("U?"))
                    {
                        String each_pool[] = pool_temp.split("=");
                        if (each_pool.length > 1) {
                            if (each_pool[1].trim().length() > 0)
                            {
                                String value = each_pool[1].replaceAll("\"", "").trim();
                                if(value.compareTo("AU")==0  || value.compareTo("UA")==0)
                                {
                                    panel.dialog5.jRadioButton11.setSelected(true);
                                }
                                else if(value.compareTo("UU")==0)
                                {
                                    panel.dialog5.jRadioButton12.setSelected(true);
                                }
                            }
                        }
                    }
                }

                 panel.dialog5.pool = result;
                 panel.dialog5.save_init_state();
            }
        }
      }

      if(temp.indexOf("trait") >= 0)
      {
        if(sus_cov && in_sus_cov)
        {
          String trait[] = temp.split("=");
          if(trait.length > 1)
          {
            if (trait[1].trim().length() >= 0)
            {
              result = trait[1].replaceAll("\"", "").trim();
              panel.jComboBox4.setSelectedItem(result);
            }
          }
        }
      }

      if(temp.indexOf("num_of_classes") >= 0)
      {
        if(sus_cov && in_sus_cov)
        {
          String trait[] = temp.split("=");
          if(trait.length > 1)
          {
            result = trait[1].replaceAll("\"","").trim();
            if (result.length()>=0)
              panel.jTextField5.setText(result);
          }
        }
      }

      if(temp.indexOf("transformation") >= 0)
      {
        panel.dialog4.save_init_state();
      }

      if (temp.indexOf("lambda1") >= 0)
      {
        temp = temp.replaceAll("}", "").trim();

        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.length()>0 && lambdavalue.length()>0)
            {
              if(lambdapara.compareTo("val")==0)
                  panel.dialog4.jTextField1.setText(lambdavalue);

              else if(lambdapara.compareTo("fixed")==0)
              {
                if(lambdavalue.compareTo("true")==0)
                  panel.dialog4.jComboBox1.setSelected(true);
              }
            }
          }
        }
      }

      if (temp.indexOf("lambda2") >= 0) {

        temp = temp.replaceAll("}", "").trim();
        String lambdalist[] = temp.split(",");

        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.length()>0 && lambdavalue.length()>0)
            {
              if(lambdapara.compareTo("val")==0)
                  panel.dialog4.jTextField2.setText(lambdavalue);

              else if(lambdapara.compareTo("fixed")==0)
              {
                if(lambdavalue.compareTo("true")==0)
                  panel.dialog4.jComboBox2.setSelected(true);
              }
            }
          }
        }
      }

      if (temp.indexOf("allow_averaging") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("mean") == 0)
            panel.jComboBoxAA.setSelectedIndex(1);
        }
      }
    }
  }

  public void setFCORblock(String original_block, FCOR2 panel)
  {
    panel.cdialog.listModel.removeAllElements();
    String block = original_block;

    StringTokenizer st = new StringTokenizer(block, "\n");

    String result="";
    String trait_list="";

    boolean var_cov = false;
    boolean in_var_cov = false;

    CheckableItem[] lm = panel.jTraitComboBox.ListData;

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(in_var_cov == false && temp.indexOf("trait")>=0)
      {
        String traitname[] = temp.split("=");

        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();

          for (int listindex = 0; listindex < lm.length; listindex++) {
            CheckableItem item = (CheckableItem) lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              trait_list = trait_list + ", " + item;
            }
          }
        }
      }

      if (temp.indexOf("interclass_weight") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("pair_wise") == 0)
            panel.jComboBox1.setSelectedIndex(0);
          if (result.compareTo("uniform") == 0)
            panel.jComboBox1.setSelectedIndex(1);
        }
      }

      if (temp.indexOf("intraclass_weight") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("pair_wise") == 0)
            panel.jComboBox2.setSelectedIndex(0);
          if (result.compareTo("uniform") == 0)
            panel.jComboBox2.setSelectedIndex(1);
        }
      }

      if (temp.indexOf("var_cov") >= 0)
      {
        var_cov = true;

        int here = block.indexOf("var_cov");
        int substart = block.indexOf("{",here);
        int subend = block.indexOf("}",here);

        if(here >= 0 && substart >= 0 && subend >= 0)
        {
          String subblock = block.substring(here, subend+1);
          panel.cdialog.listModel.addElement(subblock);
          panel.cdialog.save_init_state();
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend+1);
          block = s.toString();
        }
      }

      if(var_cov)
      {
        if(temp.indexOf("{") >= 0)
        {
          in_var_cov = true;
        }
      }

      if(var_cov)
      {
        if(temp.indexOf("}") >= 0)
        {
          in_var_cov = false;
          var_cov = false;
        }
      }

      if (temp.indexOf("standard_error") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.indexOf("true") >= 0)
            panel.jComboBox3.setSelected(true);
          else
            panel.jComboBox3.setSelected(false);
        }
      }

      if (temp.indexOf("conservative") >= 0)
      {
        panel.jComboBox7.setSelected(true);
      }

      if (temp.indexOf("pairs") >= 0)
      {
        panel.jComboBox8.setSelected(true);
      }

      if (temp.indexOf("type") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.indexOf("subtypes") >= 0)
            panel.jComboBox4.setSelectedIndex(0);
          else if (result.indexOf("maintypes") >= 0)
            panel.jComboBox4.setSelectedIndex(1);
          else if (result.indexOf("both") >= 0)
            panel.jComboBox4.setSelectedIndex(2);
        }
      }

      if (temp.indexOf("tabular") >= 0)
      {
        panel.jComboBox9.setSelected(true);
      }

      if (temp.indexOf("sex_name") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.indexOf("true") >= 0)
            panel.jComboBox5.setSelected(true);
          else
            panel.jComboBox5.setSelected(false);
        }
      }

      if (temp.indexOf("homogeneity_test") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.indexOf("true") >= 0)
          {
            panel.jComboBox6.setSelectedIndex(1);
            if (result.indexOf("ind") >= 0)
            panel.jComboBox6.setSelectedIndex(2);
          }
          else
            panel.jComboBox6.setSelectedIndex(0);
        }
      }

      if (temp.indexOf("generation_limit") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.jTextField1.setText(result);
        }
      }
    }

    trait_list = trait_list.replaceFirst(",", "");
    trait_list = trait_list.trim();

    panel.jTraitComboBox.setSelectedItem(trait_list);
  }

  public void setASSOCblock(String block, ASSOC2 panel)
  {
    panel.covDialog.listModel.removeAllElements();
    panel.CEDialog.listModel.removeAllElements();
    panel.residualsDialog.listModel.removeAllElements();

    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("title")>=0)
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"", "").trim();
          if (title[1].trim().length() >= 0)
            panel.titleTextField.setText(result);
        }
      }

      if(temp.indexOf("primary_trait")>=0 || temp.indexOf("trait")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().length() >= 0)
          {
            result = trait[1].replaceAll("\"", "").trim();
            panel.traitComboBox.setSelectedItem(result);
          }
        }
      }

      if (temp.indexOf("covariate") >= 0 || temp.startsWith("cov"))
      {
        panel.covDialog.listModel.addElement(temp);
        panel.covDialog.save_init_state();
      }

      if (temp.indexOf("class_eff") >= 0)
      {
          panel.CEDialog.listModel.addElement(temp);
          panel.CEDialog.save_init_state();
      }

      if (temp.indexOf("polygenic_effect") >= 0 || temp.indexOf("pe") >= 0)
      {
        String trait[] = temp.split(",");

        String temp_result[] = trait[0].split("=");
        if(temp_result.length > 1)
        {
          result = temp_result[1].replaceAll("\"","").trim();
          if(result.compareTo("true")==0)
            panel.PECheckBox.setSelected(true);
          else
            panel.PECheckBox.setSelected(false);
        }

        if(trait.length == 2 || trait.length == 3)
        {
          for(int optionindex=1;optionindex<trait.length;optionindex++)
          {
              String temp_option[] = trait[optionindex].split("=");
              if(temp_option.length > 1)
              {
                String optionresult = temp_option[1].replaceAll("\"","").trim();

                if(temp_option[0].indexOf("val")>=0 && optionresult.length()>=0)
                {
                  panel.PEVal.setEnabled(true);
                  panel.PEVal.setText(result);
                }
                else if(temp_option[0].indexOf("fixed")>=0)
                  panel.PEfixedCheckBox.setSelected(true);
              }
          }
        }
      }

      if (temp.indexOf("family_effect") >= 0 || temp.startsWith("fe"))
      {
        String trait[] = temp.split(",");

        String temp_result[] = trait[0].split("=");
        if (temp_result.length > 1)
        {
          result = temp_result[1].replaceAll("\"", "").trim();
          if(result.compareTo("true")==0)
            panel.FECheckBox.setSelected(true);
          else
            panel.FECheckBox.setSelected(false);
        }

        if(trait.length == 2 || trait.length == 3)
        {
          for(int optionindex=1;optionindex<trait.length;optionindex++)
          {
              String temp_option[] = trait[optionindex].split("=");
              if(temp_option.length > 1)
              {
                String optionresult = temp_option[1].replaceAll("\"", "").trim();

                if (temp_option[0].indexOf("val") >= 0 && optionresult.length() >= 0) {
                  panel.FEVal.setEnabled(true);
                  panel.FEVal.setText(optionresult);
                }
                else if (temp_option[0].indexOf("fixed") >= 0)
                  panel.FEfixedCheckBox.setSelected(true);
              }
            }
        }
      }

      if (temp.indexOf("marital_effect") >= 0 || temp.indexOf("me") >= 0)
      {
        String trait[] = temp.split(",");

        String temp_result[] = trait[0].split("=");
        if (temp_result.length > 1)
        {
          result = temp_result[1].replaceAll("\"","").trim();
          if(result.compareTo("true")==0)
            panel.MECheckBox.setSelected(true);
          else
            panel.MECheckBox.setSelected(false);
        }

        if(trait.length == 2 || trait.length == 3)
        {
          for(int optionindex=1;optionindex<trait.length;optionindex++)
          {
              String temp_option[] = trait[optionindex].split("=");
              if(temp_option.length > 1)
              {
                String optionresult = temp_option[1].replaceAll("\"","").trim();

                if(temp_option[0].indexOf("val")>=0 && optionresult.length()>=0)
                {
                  panel.MEVal.setEnabled(true);
                  panel.MEVal.setText(optionresult);
                }
                else if(temp_option[0].indexOf("fixed")>=0)
                  panel.MEfixedCheckBox.setSelected(true);
              }
          }
        }
      }

      if (temp.indexOf("sibship_effect") >= 0 || temp.indexOf("se") >= 0)
      {
        String trait[] = temp.split(",");

        String temp_result[] = trait[0].split("=");
        if (temp_result.length > 1)
        {
          result = temp_result[1].replaceAll("\"","").trim();
          if(result.compareTo("true")==0)
            panel.SECheckBox.setSelected(true);
          else
            panel.SECheckBox.setSelected(false);
        }

        if(trait.length == 2 || trait.length == 3)
        {
          for(int optionindex=1;optionindex<trait.length;optionindex++)
          {
              String temp_option[] = trait[optionindex].split("=");
              if(temp_option.length > 1)
              {
                String optionresult = temp_option[1].replaceAll("\"","").trim();

                if(temp_option[0].indexOf("val")>=0 && optionresult.length()>=0)
                {
                  panel.SEVal.setEnabled(true);
                  panel.SEVal.setText(optionresult);
                }
                else if(temp_option[0].indexOf("fixed")>=0)
                  panel.SEfixedCheckBox.setSelected(true);
              }
          }
        }
      }

      if (temp.indexOf("option") >= 0)
      {
        String trait[] = temp.split(",");

        String temp_result[] = trait[0].split("=");
        if(temp_result.length > 1)
          result = temp_result[1].replaceAll("\"","").trim();

        if(trait.length == 1)
        {
          if (result.compareTo("george_elston") == 0)
            panel.transDialog.jComboBoxTrans.setSelectedIndex(0);
          else
            panel.transDialog.jComboBoxTrans.setSelectedIndex(1);

          panel.transDialog.save_init_state();
        }
      }

      if (temp.indexOf("both_sides") >= 0) {
          panel.transDialog.jCheckboxBothSides.setSelected(true);
          panel.transDialog.save_init_state();
      }

      if (temp.indexOf("lambda1") >= 0)
      {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.compareTo("val")==0)
            {
              if(lambdavalue.length()>0)
              {
                  panel.transDialog.lambda1.setText(lambdavalue);
                  panel.transDialog.save_init_state();
              }
            }
            else if(lambdapara.compareTo("fixed")==0)
            {
              if(lambdavalue.compareTo("true")==0)
                panel.transDialog.jComboBoxLambda1Fixed.setSelected(true);
              else
                panel.transDialog.jComboBoxLambda1Fixed.setSelected(false);

              panel.transDialog.save_init_state();
            }
            else if(lambdapara.compareTo("lower_bound")==0)
            {
              if(lambdavalue.length()>0)
              {
                  panel.transDialog.lowerbound.setText(lambdavalue);
                  panel.transDialog.save_init_state();
              }
            }
            else if(lambdapara.compareTo("upper_bound")==0)
            {
              if(lambdavalue.length()>0)
              {
                  panel.transDialog.upperbound.setText(lambdavalue);
                  panel.transDialog.save_init_state();
              }
            }
          }
        }
      }

      if (temp.indexOf("lambda2") >= 0) {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.compareTo("val")==0)
            {
              if(lambdavalue.length()>0)
              {
                  panel.transDialog.lambda2.setText(lambdavalue);
                  panel.transDialog.save_init_state();
              }
            }
            else if(lambdapara.compareTo("fixed")==0)
            {
              if(lambdavalue.compareTo("true")==0)
                panel.transDialog.jComboBoxLambda2Fixed.setSelected(true);
              else
                panel.transDialog.jComboBoxLambda2Fixed.setSelected(false);

              panel.transDialog.save_init_state();
            }
          }
        }
      }

      if (temp.indexOf("order") >= 0)
      {
          String trait[] = temp.split("=");
          if (trait.length > 1) {
              result = trait[1].replaceAll("\"", "").trim();
              if (result.compareTo("as_input") == 0)
                  panel.summaryDialog.orderComboBox.setSelectedIndex(0);
              if (result.compareTo("lrt") == 0)
                  panel.summaryDialog.orderComboBox.setSelectedIndex(1);
              if (result.compareTo("wald") == 0)
                  panel.summaryDialog.orderComboBox.setSelectedIndex(2);
              if (result.compareTo("larger_pvalue") == 0)
                  panel.summaryDialog.orderComboBox.setSelectedIndex(3);
              if (result.compareTo("pvalue_ratio") == 0)
                  panel.summaryDialog.orderComboBox.setSelectedIndex(4);
              panel.summaryDialog.save_init_state();
          }
      }
      if (temp.startsWith("all") && temp.indexOf("allow_averaging") < 0) {
          String trait[] = temp.split("=");
          if(trait.length > 1)
          {
            result = trait[1].replaceAll("\"", "").trim();
            if (result.compareTo("true") == 0)
              panel.summaryDialog.allCheckBox.setSelected(true);

          panel.summaryDialog.save_init_state();
        }
      }
      if (temp.indexOf("lrt") >= 0) {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.compareTo("max")==0)
            {
              if(lambdavalue.length()>0)
              {
                  panel.summaryDialog.lrtTextField.setText(lambdavalue);
                  panel.summaryDialog.save_init_state();
              }
            }
            else if(lambdapara.compareTo("lrt")==0)
            {
              if(lambdavalue.compareTo("true")==0)
                panel.summaryDialog.lrtCheckBox.setSelected(true);
              else
                panel.summaryDialog.lrtCheckBox.setSelected(false);

              panel.summaryDialog.save_init_state();
            }
          }
        }
      }
      if (temp.indexOf("wald") >= 0) {
        String lambdalist[] = temp.split(",");
        for(int i=0;i<lambdalist.length;i++)
        {
          String lambdatemp[] = lambdalist[i].split("=");

          if(lambdatemp.length>1)
          {
            String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
            String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

            if(lambdapara.compareTo("max")==0)
            {
              if(lambdavalue.length()>0)
              {
                  panel.summaryDialog.waldTextField.setText(lambdavalue);
                  panel.summaryDialog.save_init_state();
              }
            }
            else if(lambdapara.compareTo("wald")==0)
            {
              if(lambdavalue.compareTo("true")==0)
                panel.summaryDialog.waldCheckBox.setSelected(true);
              else
                panel.summaryDialog.waldCheckBox.setSelected(false);

              panel.summaryDialog.save_init_state();
            }
          }
        }
      }
      if (temp.indexOf("limit_number") >= 0) {
       String lambdalist[] = temp.split(",");
       for(int i=0;i<lambdalist.length;i++)
       {
         String lambdatemp[] = lambdalist[i].split("=");

         if(lambdatemp.length>1)
         {
           String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
           String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

           if(lambdapara.compareTo("number")==0)
           {
             if(lambdavalue.length()>0)
             {
                 panel.summaryDialog.limitTextField.setText(lambdavalue);
                 panel.summaryDialog.save_init_state();
             }
           }
           else if(lambdapara.compareTo("limit_number")==0)
           {
             if(lambdavalue.compareTo("true")==0)
               panel.summaryDialog.limitCheckBox.setSelected(true);
             else
               panel.summaryDialog.limitCheckBox.setSelected(false);

             panel.summaryDialog.save_init_state();
           }
         }
       }
     }

      if (temp.indexOf("allow_averaging") >= 0 || temp.indexOf("aa") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if (result.compareTo("mean") == 0)
            panel.allowaveComboBox.setSelectedIndex(1);
        }
      }

      if (temp.compareTo("batch") == 0) {
        panel.batchCheckBox.setSelected(true);
      }

     if (temp.startsWith("model") && temp.compareTo("models")!=0)
     {
         panel.residualsDialog.listModel.addElement(temp);
         panel.residualsDialog.save_init_state();
      }
    }

  }

  public void setGENIBDblock(String block, GENIBD2 panel)
  {
    StringTokenizer st = new StringTokenizer(block, "\n");

    String result="";

    Vector checkitem = new Vector();
    String list=new String();
    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("title")>=0)
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"","").trim();
          if (title[1].trim().length()>=0)
            panel.title.setText(result);
        }
      }

      if(temp.indexOf("region")>=0)
      {
        String traitname[] = temp.split("=");

        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"","").trim();

          VariableData tempdata = new VariableData(result, "region");
          CheckableItem items = new CheckableItem(tempdata);
          items.setSelected(true);
          checkitem.add(items);
          list = list+", "+items;
        }
      }

      if (temp.indexOf("ibd_mode") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("singlepoint") == 0)
            panel.ibdmode.setSelectedItem(result);
        }
      }

      if (temp.indexOf("scan_type") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.indexOf("intervals") >= 0)
          {
            panel.scantype.setSelectedIndex(1);

            if(trait.length >= 3)
            {
              String distance = trait[2].replaceAll("\"","").trim();
              if(distance.length()>=0)
                panel.jTextField1.setText(distance);
            }
          }
        }
      }

      if (temp.indexOf("max_pedigree") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.max_ped.setText(result);
        }
      }

      if (temp.indexOf("output_pair_type") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("siblings") == 0)
            panel.outputpairtype.setSelectedIndex(0);
          else if (result.compareTo("all_sibs") == 0)
            panel.outputpairtype.setSelectedIndex(1);
          else
            panel.outputpairtype.setSelectedIndex(2);
        }
      }

      if (temp.indexOf("split_pedigrees") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("yes") == 0)
            panel.splitped.setSelectedIndex(0);
          else if (result.compareTo("no") == 0)
            panel.splitped.setSelectedIndex(1);
          else
            panel.splitped.setSelectedIndex(2);
        }
      }

      if (temp.indexOf("allow_loops") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("true") == 0)
            panel.allowloops.setSelected(true);
          else
            panel.allowloops.setSelected(false);
        }
      }

      if (temp.indexOf("random_seed") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.jTextField2.setText(result);
        }
      }

      if (temp.indexOf("use_simulation") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("yes") == 0)
            panel.usesimulation.setSelectedIndex(0);
          else if (result.compareTo("no") == 0)
            panel.usesimulation.setSelectedIndex(1);
          else
            panel.usesimulation.setSelectedIndex(2);
        }
      }

      if (temp.indexOf("use_factoring") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.compareTo("true") == 0)
            panel.factoring.setSelected(true);
          else if (result.compareTo("false") == 0)
            panel.factoring.setSelected(false);
        }
      }

      if (temp.indexOf("base_factor") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.factor11.setText(result);
        }
      }

      if (temp.indexOf("demem_factor") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.factor12.setText(result);
        }
      }
      if (temp.indexOf("sim_factor") >= 0 && temp.indexOf("sim_batch_factor") < 0 )
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.factor13.setText(result);
        }
      }
      if (temp.indexOf("sim_batch_factor") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.factor14.setText(result);
        }
      }

      if (temp.indexOf("sim_steps") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.factor21.setText(result);
        }
      }
      if (temp.indexOf("demem_steps") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.factor22.setText(result);
        }
      }
      if (temp.indexOf("sim_batch_count") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"","").trim();
          if (result.length()>=0)
            panel.factor23.setText(result);
        }
      }
    }

    list = list.replaceFirst(",","");
    list = list.trim();

    int length = checkitem.size();
    CheckableItem[] itemlist = new CheckableItem[length];
    for (int i = 0; i < length; i++) {
      itemlist[i] = (CheckableItem) checkitem.get(i);
    }
    panel.jRegionComboBox.setData(itemlist);

    panel.jRegionComboBox.setSelectedItem(list);
  }

  public void setRELTESTblock(String block, RELTEST2 panel)
  {
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    Vector checkitem = new Vector();
    String list=new String();
    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("pair_type")>=0)
      {
        String each_pedigree[] = temp.split("=");
        if(each_pedigree.length > 1)
        {
          if(each_pedigree[1].trim().compareTo("sib")==0)
            panel.jComboBox3.setSelected(true);
          else if(each_pedigree[1].trim().compareTo("hsib")==0)
            panel.jComboBox4.setSelected(true);
          else if(each_pedigree[1].trim().compareTo("parent_offspring")==0)
            panel.jComboBox5.setSelected(true);
          else if(each_pedigree[1].trim().compareTo("marital")==0)
            panel.jComboBox6.setSelected(true);
        }
      }

      if(temp.indexOf("region")>=0)
      {
        String traitname[] = temp.split("=");
        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"","").trim();

          VariableData tempdata = new VariableData(result, "region");
          CheckableItem items = new CheckableItem(tempdata);
          items.setSelected(true);
          checkitem.add(items);
          list = list+", "+items;
        }
      }

      if(temp.indexOf("cut_points")>=0)
      {
        StringTokenizer st_cut = new StringTokenizer(temp, ",");
        while (st_cut.hasMoreTokens()) {
          String temp_cut = st_cut.nextToken().trim();
          if(temp_cut.indexOf("sib")>=0 && temp_cut.indexOf("hsib")<0)
          {
            String sib[] = temp_cut.split("=");
            if (sib.length > 1 && sib[1].trim().length()>=0)
            {
              result = sib[1].replaceAll("\"","").trim();
              panel.jTextField1.setText(result);
            }
          }
          if(temp_cut.indexOf("hsib")>=0)
          {
            String sib[] = temp_cut.split("=");
            if (sib.length > 1 && sib[1].trim().length()>=0)
            {
              result = sib[1].replaceAll("\"","").trim();
              panel.jTextField2.setText(result);
            }
          }
          if(temp_cut.indexOf("MZtwin")>=0)
          {
            String sib[] = temp_cut.split("=");
            if (sib.length > 1 && sib[1].trim().length()>=0)
            {
              result = sib[1].replaceAll("\"","").trim();
              panel.jTextField3.setText(result);
            }
          }
          if(temp_cut.indexOf("parent_offspring")>=0)
          {
            String sib[] = temp_cut.split("=");
            if (sib.length > 1 && sib[1].trim().length()>=0)
            {
              result = sib[1].replaceAll("\"","").trim();
              panel.jTextField4.setText(result);
            }
          }
        }
      }

      if (temp.indexOf("nucfam_file") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().compareTo("true") == 0)
            panel.jComboBox1.setSelected(true);
        }
      }

      if (temp.indexOf("detailed_file") >= 0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          if (trait[1].trim().compareTo("true") == 0)
            panel.jComboBox2.setSelected(true);
        }
      }
    }

    list = list.replaceFirst(",","");
    list = list.trim();

    int length = checkitem.size();
    CheckableItem[] itemlist = new CheckableItem[length];
    for (int i = 0; i < length; i++) {
      itemlist[i] = (CheckableItem) checkitem.get(i);
    }
    panel.jRegionComboBox.setData(itemlist);

    panel.jRegionComboBox.setSelectedItem(list);
  }

  public void setTDTEXblock(String block, TDTEX2 panel)
  {
    StringTokenizer st = new StringTokenizer(block, "\n");

    CheckableItem[] lm = panel.jMarkerComboBox.ListData;
    String result="";
    String marker_list="";
    String option_list="";
    panel.Setoption(false);

    boolean all_test = false;

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();
      if(temp.indexOf("marker")>=0)
      {
        String traitname[] = temp.split("=");
        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"","").trim();

          for (int listindex = 0; listindex < lm.length; listindex++) {
            CheckableItem item = (CheckableItem) lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              marker_list = marker_list + ", " + item;
            }
          }
        }
      }
      if(temp.indexOf("trait")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1 && trait[1].trim().length()>=0)
        {
          result = trait[1].replaceAll("\"","").trim();
          panel.jComboBox9.setSelectedItem(result);
        }
      }
      if(temp.indexOf("parental_trait")>=0)
      {
        String trait[] = temp.split("=");
        if (trait.length > 1 && trait[1].trim().length() >= 0)
        {
          result = trait[1].replaceAll("\"", "").trim();
          panel.jComboBox10.setSelectedItem(result);
        }
      }
      if(temp.indexOf("max_children")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();

          if(result.compareTo("none")==0)
          {
          }
          else if(result.compareTo("unlimited")==0)
          {
            panel.jComboBox7.setSelectedItem(trait[1].trim());
          }
          else
          {
            panel.jComboBox7.setSelectedItem("number");
            if(result.length()>=0)
              panel.jTextField1.setText(result);
          }
        }
      }
      if(temp.indexOf("max_sib_pairs")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if(result.compareTo("none")==0)
          {
          }
          else if(result.compareTo("unlimited")==0)
          {
            panel.jComboBox8.setSelectedItem(result);
          }
          else
          {
            panel.jComboBox8.setSelectedItem("number");
            if(result.length()>=0)
              panel.jTextField2.setText(result);
          }
        }
      }
      if(temp.indexOf("sample")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if(result.compareTo("genotypes")==0)
          {
            panel.jComboBox1.setSelectedItem("genotypes");
          }
        }
      }
      if(temp.indexOf("sex_differential")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          if(result.compareTo("true")==0)
          {
            panel.jComboBox2.setSelected(true);
          }
        }
      }
      if(temp.indexOf("skip_exact_tests")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          lm = panel.jComboBox3.ListData;
            for (int listindex = 0; listindex < lm.length; listindex++)
            {
              CheckableItem item = (CheckableItem) lm[listindex];
              if(result.compareTo("true")==0)
              {
                item.setSelected(true);
                option_list = option_list + ", " + item;
                all_test = true;
              }
              else if(result.compareTo("false")==0)
              {
                item.setSelected(false);
              }
            }
        }
      }
      if(temp.indexOf("skip_permutation_test")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          lm = panel.jComboBox3.ListData;
          CheckableItem item = (CheckableItem) lm[0];

          if(result.compareTo("true")==0)
          {
            item.setSelected(true);
            if(!all_test)
              option_list = option_list + ", " + item;
          }
          else if(result.compareTo("false")==0)
          {
            item.setSelected(false);
          }
        }
      }
      if(temp.indexOf("skip_mc_test")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          lm = panel.jComboBox3.ListData;
          CheckableItem item = (CheckableItem) lm[1];
          if(result.compareTo("true")==0)
          {
            item.setSelected(true);
            if(!all_test)
              option_list = option_list + ", " + item;

          }
          else if(result.compareTo("false")==0)
          {
            item.setSelected(false);
          }
        }
      }
      if(temp.indexOf("skip_mcmh_test")>=0)
      {
        String trait[] = temp.split("=");
        if(trait.length > 1)
        {
          result = trait[1].replaceAll("\"", "").trim();
          lm = panel.jComboBox3.ListData;
          CheckableItem item = (CheckableItem) lm[2];

          if(result.compareTo("true")==0)
          {
            item.setSelected(true);
            if(!all_test)
              option_list = option_list + ", " + item;

          }
          else if(result.compareTo("false")==0)
          {
            item.setSelected(false);
          }
        }
      }
    }

    marker_list = marker_list.replaceFirst(",", "");
    marker_list = marker_list.trim();
    panel.jMarkerComboBox.setSelectedItem(marker_list);

    option_list = option_list.replaceFirst(",", "");
    option_list = option_list.trim();
    panel.jComboBox3.setSelectedItem(option_list);
  }

  public void setMLODblock(String block, MLOD2 panel)
  {
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    Vector checkitem = new Vector();
    String list = new String();

    Vector checkitem2 = new Vector();
    String list2 = new String();

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("title")>=0)
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"","").trim();
          if (title[1].trim().length()>=0)
            panel.jTextField1.setText(result);
        }
      }

      if(temp.indexOf("region")>=0)
      {
        String traitname[] = temp.split("=");

        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"","").trim();

          VariableData tempdata = new VariableData(result, "region");
          CheckableItem items = new CheckableItem(tempdata);
          items.setSelected(true);
          checkitem.add(items);
          list = list+", "+items;
        }
      }

      if(temp.indexOf("trait_marker")>=0)
      {
        String traitname[] = temp.split("=");

        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"","").trim();

          VariableData tempdata = new VariableData(result, "trait_marker");
          CheckableItem items = new CheckableItem(tempdata);
          items.setSelected(true);
          checkitem2.add(items);
          list2 = list2+", "+items;
        }
      }

      if(temp.indexOf("scan_type")>=0)
      {
        String scan_type[] = temp.split("=");
        if(scan_type.length > 1)
        {
          result = scan_type[1].replaceAll("\"", "").trim();

          if(result.compareTo("markers")==0)
              panel.jComboBox4.setSelectedItem(0);
          else if(result.compareTo("interval")==0)
              panel.jComboBox4.setSelectedItem(1);
          else if(result.compareTo("both")==0)
              panel.jComboBox4.setSelectedItem(2);
        }
      }
      if(temp.indexOf("distance")>=0)
      {
        String distance[] = temp.split("=");
        if(distance.length > 1)
        {
          result = distance[1].replaceAll("\"", "").trim();
          if(result.compareTo("2.0")!=0)
            panel.jTextField2.setText(result);
        }
      }
      if(temp.indexOf("max_size")>=0)
      {
        String distance[] = temp.split("=");
        if(distance.length > 1)
        {
          result = distance[1].replaceAll("\"", "").trim();
          if(result.compareTo("18")!=0)
            panel.jTextField3.setText(result);
        }
      }

      if(temp.indexOf("output_pedigree")>=0)
      {
        String pedigree_lod_out[] = temp.split("=");
        if(pedigree_lod_out.length > 1)
        {
          result = pedigree_lod_out[1].replaceAll("\"", "").trim();
          if(result.compareTo("none")==0)
              panel.jComboBox5.setSelectedItem(0);
          else if(result.compareTo("marker")==0)
              panel.jComboBox5.setSelectedItem(1);
          else if(result.compareTo("interval")==0)
              panel.jComboBox5.setSelectedItem(2);
          else if(result.compareTo("both")==0)
              panel.jComboBox5.setSelectedItem(3);

        }
      }

      if(temp.indexOf("sample_detail")>=0)
      {
        String pedigree_lod_out[] = temp.split("=");
        if(pedigree_lod_out.length > 1)
        {
          result = pedigree_lod_out[1].replaceAll("\"", "").trim();
          panel.jComboBox6.setSelectedItem(result);
        }
      }
    }

    list = list.replaceFirst(",","");
    list = list.trim();
    int length = checkitem.size();
    CheckableItem[] itemlist = new CheckableItem[length];
    for (int i = 0; i < length; i++) {
      itemlist[i] = (CheckableItem) checkitem.get(i);
    }
    panel.jRegionComboBox.setData(itemlist);
    panel.jRegionComboBox.setSelectedItem(list);

    list2 = list2.replaceFirst(",","");
    list2 = list2.trim();
    int length2 = checkitem2.size();
    CheckableItem[] itemlist2 = new CheckableItem[length2];
    for (int i = 0; i < length2; i++) {
      itemlist2[i] = (CheckableItem) checkitem2.get(i);
    }
    panel.jTraitMarkerComboBox.setData(itemlist2);
    panel.jTraitMarkerComboBox.setSelectedItem(list2);
  }


  public void setMARKERINFOblock(String block, MARKERINFO2 panel)
  {
    StringTokenizer st = new StringTokenizer(block, "\n");
    String result="";

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("sample_id")>=0)
      {
        String sample_id[] = temp.split("=");
        if(sample_id.length > 1)
        {
          result = sample_id[1].replaceAll("\"", "").trim();
          if(result.length()>=0)
            panel.jComboBox1.setSelectedItem(result);
        }
      }
      if(temp.indexOf("consistent_out")>=0)
      {
        String consistent_out[] = temp.split("=");
        if(consistent_out.length > 1)
        {
          result = consistent_out[1].replaceAll("\"", "").trim();
          if(result.compareTo("true")==0)
            panel.jCheckBox1.setSelected(true);
        }
      }
      if(temp.indexOf("pedigree_out")>=0)
      {
        String consistent_out[] = temp.split("=");
        if(consistent_out.length > 1)
        {
          result = consistent_out[1].replaceAll("\"", "").trim();
          if(result.compareTo("true")==0)
            panel.genPedCheckBox.setSelected(true);
        }
      }
    }
  }

  public void setFREQblock(String block, FREQ2 panel)
  {
    StringTokenizer st = new StringTokenizer(block, "\n");

    CheckableItem[] lm = panel.jMarkerComboBox.ListData;
    String result="";
    String marker_list = new String();

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.indexOf("founder_weight")>=0)
      {
        String each_pedigree[] = temp.split("=");
        if(each_pedigree.length > 1)
        {
          result = each_pedigree[1].replaceAll("\"", "").trim();
          if(result.length()>=0)
            panel.jTextField1.setText(result);
        }
      }
      if(temp.indexOf("skip_mle")>=0)
      {
        String suppress_general[] = temp.split("=");
        if(suppress_general.length > 1)
        {
          result = suppress_general[1].replaceAll("\"", "").trim();
          if(result.compareTo("true")==0)
            panel.jCheckBox1.setSelected(true);
        }
      }

      if(temp.indexOf("inbreeding")>=0)
      {
        String inbreeding[] = temp.split("=");
        if(inbreeding.length > 1)
        {
          result = inbreeding[1].replaceAll("\"", "").trim();
          if(result.compareTo("true")==0)
            panel.jCheckBox2.setSelected(true);
        }
      }

      if(temp.indexOf("marker")>=0)
      {
        String traitname[] = temp.split("=");
        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();
          for (int listindex = 0; listindex < lm.length; listindex++) {
            CheckableItem item = (CheckableItem) lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              marker_list = marker_list + ", " + item;
            }
          }
        }
      }
    }

    marker_list = marker_list.replaceFirst(",", "");
    marker_list = marker_list.trim();
    panel.jMarkerComboBox.setSelectedItem(marker_list);
  }

  public void setPEDINFOblock(String block, PEDINFO2 panel)
  {
    StringTokenizer st = new StringTokenizer(block, "\n");

    CheckableItem[] lm = panel.jTraitComboBox.ListData;

    String result="";
    String list = new String();

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if (temp.indexOf("#") >= 0)
      {
        if (temp.indexOf("#") == 0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#") - 1).trim();
      }

      if(temp.indexOf("trait")>=0 || temp.indexOf("covariate")>=0 || temp.indexOf("phenotype")>=0)
      {
        String traitname[] = temp.split("=");
        if(traitname.length > 1)
        {
          result = traitname[1].replaceAll("\"", "").trim();

          for (int listindex = 0; listindex < lm.length; listindex++) {
            CheckableItem item = (CheckableItem) lm[listindex];
            if (item.toString().compareTo(result) == 0)
            {
              item.setSelected(true);
              list = list + ", " + item;
            }
          }
        }
      }
      if(temp.indexOf("each_pedigree")>=0)
      {
        String each_pedigree[] = temp.split("=");
        if(each_pedigree.length > 1)
        {
          result = each_pedigree[1].replaceAll("\"", "").trim();

          if(result.compareTo("true")==0)
            panel.jCheckBox1.setSelected(true);
        }
      }
      if(temp.indexOf("suppress_general")>=0)
      {
        String suppress_general[] = temp.split("=");
        if(suppress_general.length > 1)
        {
          result = suppress_general[1].replaceAll("\"", "").trim();

          if(result.compareTo("true")==0)
            panel.jCheckBox2.setSelected(true);
        }
      }
    }

    list = list.replaceFirst(",", "");
    list = list.trim();
    panel.jTraitComboBox.setSelectedItem(list);
  }

  public void setDECIPHERblock(String decipher_block, DECIPHER2 panel)
  {
    String block = decipher_block;
    panel.jComboBox2.setSelectedIndex(0);

    panel.dialog1.listModel.removeAllElements();
    panel.dialog2.listModel.removeAllElements();
    panel.dialog5.listModel.removeAllElements();

    StringTokenizer st = new StringTokenizer(block, "\n");

    String result="";
    int partition_count=0;

    while (st.hasMoreTokens())
    {
      String temp = st.nextToken().trim();

      if(temp.indexOf("#")>=0)
      {
        if(temp.indexOf("#")==0)
          temp = st.nextToken().trim();
        else
          temp = temp.substring(0, temp.indexOf("#")-1).trim();
      }

      if(temp.startsWith("title"))
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"","").trim();
          if (title[1].trim().length()>=0)
            panel.title.setText(result);
        }
      }

      if (temp.startsWith("region"))
      {
        panel.dialog5.listModel.addElement(temp);
        panel.dialog5.save_init_state();
      }

      if(temp.startsWith("epsilon"))
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"","").trim();
          if (title[1].trim().length()>=0)
            panel.epsilon.setText(result);
        }
      }

      if(temp.startsWith("starting_points"))
      {
        String title[] = temp.split("=");
        if(title.length > 1)
        {
          result = title[1].replaceAll("\"","").trim();
          if (title[1].trim().length()>=0)
            panel.starting_points.setText(result);
        }
      }

      if (temp.startsWith("dump"))
      {
        if(temp.indexOf("cutoff") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("dump")==0)
              {
                if(lambdavalue.length()>0 && lambdavalue.compareTo("true")==0)
                {
                  panel.jComboBox3.setSelected(true);
                }
              }
              else if(lambdapara.compareTo("cutoff")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.jTextField5.setText(lambdavalue);
                }
              }
            }
          }
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.length()>0 && result.compareTo("true")==0)
            {
              panel.jComboBox3.setSelected(true);
            }
          }
        }
      }

      if (temp.startsWith("maf_filter"))
      {
        if(temp.indexOf("threshold") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("maf_filter")==0)
              {
                if(lambdavalue.length()>0 && lambdavalue.compareTo("true")==0)
                {
                  panel.jCheckBox1.setSelected(true);
                  panel.jTextField9.setEnabled(true);
                  panel.jLabel17.setEnabled(true);
                }
              }
              else if(lambdapara.compareTo("threshold")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.jTextField9.setText(lambdavalue);
                }
              }
            }
          }
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.length()>0 && result.compareTo("true")==0)
            {
              panel.jCheckBox1.setSelected(true);
              panel.jTextField9.setEnabled(true);
              panel.jLabel17.setEnabled(true);
            }
          }
        }
      }

      if (temp.startsWith("sliding_window"))
      {
        if(temp.indexOf("width") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("sliding_window")==0)
              {
                if(lambdavalue.compareTo("true")==0)
                {
                  panel.dialog6.jCheckBox1.setSelected(true);
                  panel.dialog6.jTextField1.setEnabled(true);
                  panel.dialog6.jLabel1.setEnabled(true);
                }
                panel.dialog6.save_init_state();
              }
              else if(lambdapara.compareTo("width")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog6.jTextField1.setText(lambdavalue);
                  panel.dialog6.save_init_state();
                }
              }
            }
          }
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.compareTo("true")==0)
            {
              panel.dialog6.jCheckBox1.setSelected(true);
              panel.dialog6.jTextField1.setEnabled(true);
              panel.dialog6.jLabel1.setEnabled(true);
            }
            panel.dialog6.save_init_state();
          }
        }
      }

      if (temp.startsWith("four_gamete_rule"))
      {
        if(temp.indexOf("threshold") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("four_gamete_rule")==0)
              {
                if(lambdavalue.compareTo("true")==0)
                {
                  panel.dialog6.jCheckBox2.setSelected(true);
                  panel.dialog6.jTextField2.setEnabled(true);
                  panel.dialog6.jLabel2.setEnabled(true);
                }
                panel.dialog6.save_init_state();
              }
              else if(lambdapara.compareTo("threshold")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog6.jTextField2.setText(lambdavalue);
                  panel.dialog6.save_init_state();
                }
              }
            }
          }
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.compareTo("true")==0)
            {
              panel.dialog6.jCheckBox2.setSelected(true);
              panel.dialog6.jTextField2.setEnabled(true);
              panel.dialog6.jLabel2.setEnabled(true);
            }
            panel.dialog6.save_init_state();
          }
        }
      }

      if (temp.startsWith("ld"))
      {
        if(temp.indexOf("threshold") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("ld")==0)
              {
                if(lambdavalue.compareTo("true")==0)
                {
                  panel.dialog6.jCheckBox3.setSelected(true);
                  panel.dialog6.jTextField3.setEnabled(true);
                  panel.dialog6.jLabel4.setEnabled(true);
                }
                panel.dialog6.save_init_state();
              }
              else if(lambdapara.compareTo("threshold")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog6.jTextField3.setText(lambdavalue);
                  panel.dialog6.save_init_state();
                }
              }
            }
          }
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.compareTo("true")==0)
            {
              panel.dialog6.jCheckBox3.setSelected(true);
              panel.dialog6.jTextField3.setEnabled(true);
              panel.dialog6.jLabel4.setEnabled(true);
            }
            panel.dialog6.save_init_state();
          }
        }
      }

      if(temp.startsWith("analysis_unit"))
      {
        String related[] = temp.split("=");
        if(related.length > 1)
        {
          result = related[1].replaceAll("\"", "").trim();
          if(result.length()>=0)
          {
              if (result.compareTo("each_individual") == 0)
                panel.jComboBox1.setSelectedIndex(0);
              if (result.compareTo("family_rep") == 0)
                panel.jComboBox1.setSelectedIndex(1);
              if (result.compareTo("family_founders") == 0)
                panel.jComboBox1.setSelectedIndex(2);
              if (result.compareTo("pool") == 0)
                panel.jComboBox1.setSelectedIndex(3);
          }
        }
      }

      if (temp.startsWith("family_rep"))
      {
        if(temp.indexOf("family_rep_value") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("family_rep")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.jComboBox2.setSelectedItem(lambdavalue);
                }
              }
              else if(lambdapara.compareTo("family_rep_value")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.jComboBox4.setSelectedItem(lambdavalue);
                }
              }
            }
          }
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.length()>=0)
              panel.jComboBox2.setSelectedItem(result);
          }
        }
      }

      if (temp.startsWith("partition"))
      {
        int here = block.indexOf("partition");
        int substart = block.indexOf("{",here);
       int subend = block.indexOf("}",here);

        if(here >= 0 && substart >= 0 && subend >= 0)
        {
          String partition_sub_block = block.substring(here, subend+1);
          StringTokenizer st_sub1 = new StringTokenizer(partition_sub_block, "\n");
          while (st_sub1.hasMoreTokens())
          {
            String sub = st_sub1.nextToken().trim();
            if(sub.startsWith("partition"))
            {
              String name[] = sub.split("=");

              if (name.length > 1) {
                result = name[1].replaceAll("\"", "").trim();
                if(partition_count==0)
                {
                  panel.dialog1.jComboBox1.setSelectedItem(result);
                  panel.dialog1.save_init_state();
                }
                if(partition_count==1)
                {
                  panel.dialog2.jComboBox1.setSelectedItem(result);
                  panel.dialog2.save_init_state();
                }
              }
            }
            else if(sub.startsWith("sub_pop"))
            {
              if(partition_count==0)
              {
                panel.dialog1.listModel.addElement(sub);
                panel.dialog1.save_init_state();
              }
              if(partition_count==1)
              {
                panel.dialog2.listModel.addElement(sub);
                panel.dialog2.save_init_state();
              }
            }
          }
          StringBuffer s = new StringBuffer(block);
          s.delete(here, subend+1);
          block = s.toString();
          partition_count++;
        }
      }

      if (temp.startsWith("pop_freq"))
      {
        if(temp.indexOf("cutoff") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("pop_freq")==0)
              {
                if(lambdavalue.length()>0 && lambdavalue.compareTo("false")==0)
                {
                  panel.dialog3.jComboBox1.setSelected(false);
                }
              }
              else if(lambdapara.compareTo("cutoff")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog3.jTextField1.setText(lambdavalue);
                }
              }
            }
          }
          panel.dialog3.save_init_state();
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.length()>0 && result.compareTo("false")==0)
            {
              panel.dialog3.jComboBox1.setSelected(false);
              panel.dialog3.save_init_state();
            }
          }
        }
      }

      if(temp.startsWith("all_possible_diplotypes_table") || temp.startsWith("all_possible_combinations_table"))
      {
        String related[] = temp.split("=");
        if(related.length > 1)
        {
          result = related[1].replaceAll("\"", "").trim();
          if(result.length()>0 && result.compareTo("true")==0)
          {
            panel.dialog3.jComboBox3.setSelected(true);
            panel.dialog3.save_init_state();
          }
        }
      }

      if (temp.startsWith("most_likely_diplotypes") || temp.startsWith("most_likely_combinations"))
      {
        if(temp.indexOf("cutoff") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("most_likely_diplotypes")==0)
              {
                if(lambdavalue.length()>0 && lambdavalue.compareTo("true")==0)
                {
                  panel.dialog3.jComboBox4.setSelected(true);
                }
              }
              else if(lambdapara.compareTo("cutoff")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog3.jTextField6.setText(lambdavalue);
                }
              }
            }
          }
          panel.dialog3.save_init_state();
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.length()>0 && result.compareTo("true")==0)
            {
              panel.dialog3.jComboBox4.setSelected(true);
              panel.dialog3.save_init_state();
            }
          }
        }
      }

      if(temp.startsWith("likelihood_ratio_test"))
      {
        String related[] = temp.split("=");
        if(related.length > 1)
        {
          result = related[1].replaceAll("\"", "").trim();
          if(result.length()>0 && result.compareTo("true")==0)
          {
            panel.dialog3.jComboBox6.setSelected(true);
            panel.dialog3.save_init_state();
          }
        }
      }

      if (temp.startsWith("compute_empirical_pvalue"))
      {
        if(temp.indexOf("true") >= 0)
        {
          String lambdalist[] = temp.split(",");
          for(int i=0;i<lambdalist.length;i++)
          {
            String lambdatemp[] = lambdalist[i].split("=");

            if(lambdatemp.length>1)
            {
              String lambdapara = lambdatemp[0].replaceAll("\"", "").trim();
              String lambdavalue = lambdatemp[1].replaceAll("\"", "").trim();

              if(lambdapara.compareTo("compute_empirical_pvalue")==0)
              {
                if(lambdavalue.length()>0 && lambdavalue.compareTo("true")==0)
                {
                  panel.dialog3.jComboBox5.setSelected(true);
                }
              }
              else if(lambdapara.compareTo("permutations")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog3.jTextField2.setText(lambdavalue);
                }
              }
              else if(lambdapara.compareTo("max_permutations")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog3.jTextField3.setText(lambdavalue);
                }
              }
              else if(lambdapara.compareTo("width")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog3.jTextField4.setText(lambdavalue);
                }
              }
              else if(lambdapara.compareTo("confidence")==0)
              {
                if(lambdavalue.length()>0)
                {
                  panel.dialog3.jTextField5.setText(lambdavalue);
                }
              }
            }
          }
          panel.dialog3.save_init_state();
        }
        else
        {
          String related[] = temp.split("=");
          if(related.length > 1)
          {
            result = related[1].replaceAll("\"", "").trim();
            if(result.length()>0 && result.compareTo("true")==0)
            {
              panel.dialog3.jComboBox5.setSelected(true);
              panel.dialog3.save_init_state();
            }
          }
        }
      }
    }

    if (block.indexOf("pools")>=0)
    {
        int here = block.indexOf("pools");
        int substart = block.indexOf("{", here);
        int subend = Find_EndBlock_Index(block, "pools");

        if (here >= 0 && substart >= 0 && subend >= 0) {
            String subblock = block.substring(here, subend);

            setDECIPHER_Pools(subblock, panel.dialog4);
        }
    }
  }


  public void setDECIPHER_Pools(String poolblock, DECIPHER_Dialog3_Pools dialog)
  {
      dialog.listModel.removeAllElements();
      StringTokenizer st = new StringTokenizer(poolblock, "\n");
      String block = poolblock;

      String result = "";
      while (st.hasMoreTokens()) {
          String temp = st.nextToken().trim();

          if (temp.indexOf("#") >= 0) {
              if (temp.indexOf("#") == 0)
                  temp = st.nextToken().trim();
              else
                  temp = temp.substring(0, temp.indexOf("#") - 1).trim();
          }

          if (temp.startsWith("pool_size") && !temp.startsWith("pool_size_trait")) {
              String pool_size[] = temp.split("=");
              if (pool_size.length > 1)
              {
                  result = pool_size[1].replaceAll("\"", "").trim();
                  if (pool_size[1].trim().length() >= 0)
                      dialog.jTextField1.setText(result);
              }
          }

          if (temp.startsWith("pool_size_trait")) {
              String pool_size[] = temp.split("=");
              if (pool_size.length > 1)
              {
                  result = pool_size[1].replaceAll("\"", "").trim();
                  if (pool_size[1].trim().length() >= 0)
                      dialog.jComboBox1.setSelectedItem(result);
              }
          }

          if (temp.startsWith("locus")) {
              int here = block.indexOf("locus");
              int substart = block.indexOf("{", here);
              int subend = block.indexOf("}", substart);

              if (here >= 0 && substart >= 0 && subend >= 0) {
                  String subblock = block.substring(here, subend + 1);
                  dialog.listModel.addElement(subblock);
                  dialog.save_init_state();
                  StringBuffer s = new StringBuffer(block);
                  s.delete(here, subend + 1);
                  block = s.toString();
              }
          }
      }
  }

  public void setDisableAllComponents(JPanel inputpanel)
  {
    for(int i=0;i<inputpanel.getComponentCount();i++)
    {
      inputpanel.getComponent(i).setEnabled(false);

      if(inputpanel.getComponent(i) instanceof JPanel)
      {
        JPanel temppanel = (JPanel)inputpanel.getComponent(i);
        for(int j=0;j<temppanel.getComponentCount();j++)
        {
          temppanel.getComponent(j).setEnabled(false);

          if(temppanel.getComponent(j) instanceof JPanel)
          {
            JPanel temppane2 = (JPanel)temppanel.getComponent(j);
            for(int k=0;k<temppane2.getComponentCount();k++)
            {
              temppane2.getComponent(k).setEnabled(false);
            }
          }
        }
      }

      if(inputpanel.getComponent(i) instanceof JButton)
      {
        JButton tempbutton = (JButton)inputpanel.getComponent(i);

        if(tempbutton.getText().compareTo("Run")==0)
          tempbutton.setEnabled(true);
      }
    }
  }

  public String getAnalysisBlock(String filepath, String matchstring_input)
  {
   String parameter_file_info="";
   try {
     FileInputStream fin = new FileInputStream(filepath);
     InputStreamReader isr = new InputStreamReader(fin);
     BufferedReader in = new BufferedReader(isr);

     String strFileLine = new String();
     while ( (strFileLine = in.readLine()) != null) {
       strFileLine = strFileLine;
       parameter_file_info = parameter_file_info + strFileLine + "\n";
     }
     fin.close();
     isr.close();
     in.close();
   }
   catch (Exception ex) {
     ex.printStackTrace();
   }

   String matchstring = matchstring_input.toLowerCase();

   boolean block = false;

   String result_block = new String();

   String strFileLine_original = new String();
   String strFileLine_temp = new String();

   int block_count = 0;
   boolean block_count_start = false;

   int start_index = -1;
   int block_length=-1;

   String para_array[] = parameter_file_info.split("\n");

   for(int in=0;in<para_array.length;in++)
   {
     strFileLine_original = para_array[in];
     strFileLine_temp = strFileLine_original.toLowerCase().trim();

     if (strFileLine_temp.startsWith("#")) {
       if(!block)
       {
         if(in+1 < para_array.length)
         {
           strFileLine_original = para_array[++in];
           strFileLine_temp = strFileLine_original.toLowerCase().trim();
         }
       }
     }

     if (strFileLine_temp.startsWith(matchstring)) {
       if (matchstring.compareTo("pedigree") == 0) {
         if (strFileLine_temp.startsWith("pedigree_id") == false) {
           block = true;
           start_index = parameter_file_info.indexOf(matchstring);
         }
       }
       else
       {
         block = true;
         start_index = parameter_file_info.indexOf(matchstring);
       }
     }

     if (block){
       result_block = result_block + strFileLine_original + "\n";
       if (strFileLine_temp.indexOf("{") >= 0) {
         block_count_start = true;
         block_count++;
       }

       if (strFileLine_temp.indexOf("}") >= 0) {
         block_count--;
       }

       if (block_count_start && block_count == 0) {
         block = false;
         block_count_start = false;
         block_length=result_block.length();
         int end_index = start_index + block_length;
         return result_block;
        }
     }
   }
   return result_block;
  }

  public String getAnalysisName(String analysisblock)
  {
    int first_comma_index = analysisblock.indexOf(",");
    int first_brace_index = analysisblock.indexOf("{");

    if(first_comma_index>0 && first_brace_index >0)
    {
      if(first_comma_index > first_brace_index)
      {
        return "";
      }
      else
      {
          String temp = analysisblock.substring(first_comma_index+1, first_brace_index-1);

          String attribute[] = temp.split(",");
          for(int atti=0;atti<attribute.length;atti++)
          {
            if(attribute[atti].indexOf("out")>=0)
            {
              String nameblock[] = temp.split("=");
              if(nameblock.length>1)
              {
                nameblock[1] = nameblock[1].replaceAll("\"","").trim();
                return nameblock[1];
              }
            }
          }
      }
    }
    return "";
  }

  public void activateInternalFrame(MyInternalFrame frame)
  {
    activeinframe = frame;
    JRadioButtonMenuItem item = frame.m_frameMenuItem;
    item.setSelected(true);
  }

  void init_menu()
  {
    jMenuFile.setEnabled(true);
    jMenuFileSave.setEnabled(true);
    jMenuFileSaveAs.setEnabled(true);
    jMenuView.setEnabled(true);
    jMenuAnalysis.setEnabled(true);
    jMenuTools.setEnabled(true);
    jMenuHelp.setEnabled(true);
    jMenuGenome.setEnabled(true);
    jMenuFunction.setEnabled(true);
    m_windowMenu.setEnabled(true);

    jCheckBoxMenuItem1.setSelected(true);
    jCheckBoxMenuItem2.setSelected(false);
    jCheckBoxMenuItem3.setSelected(false);
  }

  void jMenuNewProject_actionPerformed() {
      try {
          int new_width = 600;
          int new_height = 420;

          JWizardProject.initialize(this, "New Project", new_width, new_height);
          desktop.add(JWizardProject.wizardProject);
          JWizardProject.showDialog();
          desktop.setSelectedFrame(JWizardProject.wizardProject);
      } catch (Exception ex) {
          ex.printStackTrace();
      }
  }

  void jCheckBoxMenuItem1_itemStateChanged(ItemEvent e) {
    if(jCheckBoxMenuItem1.isSelected())
        {
          comf.setVisible(true);
          isCompoViewExist = true;
        }
        else
        {
          isCompoViewExist = false;
          comf.setVisible(false);
        }
  }

  void jCheckBoxMenuItem2_itemStateChanged(ItemEvent e) {
    if(jCheckBoxMenuItem2.isSelected())
        {
          jobframe.setVisible(true);
          isJobViewExist = true;
        }
        else
        {
            jobframe.setVisible(false);
            isJobViewExist = false;
        }
  }

  void jCheckBoxMenuItem3_itemStateChanged(ItemEvent e) {
    if(jCheckBoxMenuItem3.isSelected())
        {
          consoleframe.setVisible(true);
          isConsolViewExist = true;
        }
        else {
          isConsolViewExist = false;
          consoleframe.setVisible(false);
        }
  }

Vector getAnalysisnodelist(IconNode jobnode)
{
  Vector list=new Vector();
  Enumeration joblist = jobnode.depthFirstEnumeration();

  while (joblist.hasMoreElements()) {
    IconNode temp = (IconNode) joblist.nextElement();

    if (temp.iconName.indexOf("Analysis") >= 0)
    {
      list.add(temp);
    }
  }
  return list;
}

void jMenuFileSaveAs_actionPerformed()
{
  File save = activeinframe.projectFile;
  String old_prg_path = activeinframe.projectPath;

  Rename_Dialog renamedialog = new Rename_Dialog(this, "Rename project", true);
  renamedialog.pName.setText(activeinframe.getTitle());

  renamedialog.pName.requestFocus();
  renamedialog.DirPath.setText(save.getParentFile().getParent());

  renamedialog.setLocationRelativeTo(activeinframe);

  renamedialog.setVisible(true);

  if(renamedialog.rename)
  {
    String filepath = renamedialog.DirPath.getText();
    String filename = renamedialog.pName.getText();

    if(filename.indexOf(".prj")>=0)
    {
      int startindex = filename.indexOf(".prj");
      filename = filename.substring(0, startindex);
    }

    File pp = new File(filepath);
    File[] files = pp.listFiles();
    for (int i = 0; i < files.length; i++) {
      if (files[i].isDirectory()) {
        if (files[i].getName().compareTo(filename) == 0) {
          int n = JOptionPane.showOptionDialog(this.getParent(),
              "The project named '" + filename + "' already exists."
              +"\nYou must rename the project or specify a different location. ",
              "Project name Error",
              JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
          return;
        }
      }
    }

    String newprojectpath = filepath + System.getProperty("file.separator") + filename;
    File newprojectdir = new File(newprojectpath);
    try {
      newprojectdir.mkdir();
    }
    catch (SecurityException x) {
      JOptionPane.showOptionDialog(JWizardProject.wizardProject,
            "Security exception encountered while attempting to create project directory. "+
            "\nReported error number is '" + x +"'",
            "Error", // title
            JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
      return;
    }

    File newprojectfile = new File(newprojectpath+ System.getProperty("file.separator") + filename+".prj");

    MyInternalFrame.CopyFile(save, newprojectfile);
    activeinframe.projectFile = newprojectfile;
    activeinframe.setTitle(filename);
    activeinframe.projectPath = newprojectpath+ System.getProperty("file.separator");

    save.delete();

    NodeInfo nodeinfo = new NodeInfo(filename, "Project", "Project", newprojectpath+ System.getProperty("file.separator"));
    activeinframe.projectnode.setUserObject(nodeinfo);
    activeinframe.MyTree1.updateUI();

    File oldparents = save.getParentFile();

    File original_list[] = oldparents.listFiles();
    for(int i=0;i<original_list.length;i++)
    {
      File old_file = original_list[i];
      String new_name = old_file.getName();
      File new_file = new File(newprojectpath, new_name);
      old_file.renameTo(new_file);
    }

    int ex_child_count = activeinframe.externalnode.getChildCount();
    for (int i = 0; i < ex_child_count; i++) {
      IconNode temp_child = (IconNode) activeinframe.externalnode.getChildAt(i);
      NodeInfo temp_node = (NodeInfo) temp_child.getUserObject();
      String filepath1 = temp_node.file.getAbsolutePath();
      if(filepath1.startsWith(old_prg_path))
      {
        String new_filepath = activeinframe.projectPath + filepath1.substring(old_prg_path.length(), filepath1.length());
        temp_node.file = new File(new_filepath);
        temp_child.setUserObject(temp_node);
      }
    }

    int in_child_count = activeinframe.internalnode.getChildCount();
    for (int i = 0; i < in_child_count; i++) {
      IconNode temp_child = (IconNode) activeinframe.internalnode.getChildAt(i);
      NodeInfo temp_node = (NodeInfo) temp_child.getUserObject();
      String filepath1 = temp_node.file.getAbsolutePath();
      if(filepath1.startsWith(old_prg_path))
      {
        String new_filepath = activeinframe.projectPath + filepath1.substring(old_prg_path.length(), filepath1.length());
        temp_node.file = new File(new_filepath);
        temp_child.setUserObject(temp_node);
      }
    }

    Vector analysis_list = getAnalysisnodelist(activeinframe.jobnode);
    int analysis_count = analysis_list.size();

    for (int i = 0; i < analysis_count; i++) {
      IconNode temp_iconnode = (IconNode) analysis_list.get(i);
      NodeInfo temp_node = (NodeInfo) temp_iconnode.getUserObject();
      SageFilePanel temppanel1 = (SageFilePanel) temp_node.component_vector.get(0);

      IconNode inputfolder = temppanel1.inputF_node;
      int in_file_count = inputfolder.getChildCount();
      for (int j = 0; j < in_file_count; j++) {
        IconNode temp_child = (IconNode) inputfolder.getChildAt(j);
        NodeInfo inputfilenode = (NodeInfo) temp_child.getUserObject();
        String filepath1 = inputfilenode.file.getAbsolutePath();
        if(filepath1.startsWith(old_prg_path))
        {
          String new_filepath = activeinframe.projectPath + filepath1.substring(old_prg_path.length(), filepath1.length());
          inputfilenode.file = new File(new_filepath);
          temp_child.setUserObject(inputfilenode);
        }
      }

      IconNode outputfolder = temppanel1.outputF_node;
      int out_file_count = outputfolder.getChildCount();
      for (int j = 0; j < out_file_count; j++) {
        IconNode temp_child = (IconNode) outputfolder.getChildAt(j);
        NodeInfo outputfilenode = (NodeInfo) temp_child.getUserObject();
        String filepath1 = outputfilenode.file.getAbsolutePath();
        if(filepath1.startsWith(old_prg_path))
        {
          String new_filepath = activeinframe.projectPath + filepath1.substring(old_prg_path.length(), filepath1.length());
          outputfilenode.file = new File(new_filepath);
          temp_child.setUserObject(outputfilenode);
        }
      }
    }

    activeinframe.MyTree1.Refresh();
    save.getParentFile().delete();
  }
}

void jMenuFileSave_actionPerformed()
{
  String init_dir = new String();
  String os_type = System.getProperty("os.name");
  if (os_type.indexOf("Windows") >= 0) {
    init_dir = System.getProperty("user.home") + System.getProperty("file.separator");
  }
  else {
    init_dir = System.getProperty("user.home") + System.getProperty("file.separator");
  }

  try {
    final File save = activeinframe.projectFile;
    OutputStream fos = new FileOutputStream(save);

    File inffile = new File(init_dir, "sage.ini");
    OutputStream fwriter = new FileOutputStream(inffile);

    Element info = new Element("Info");
    Element editor = new Element("Editor");
    editor.setText(pathEdigorProg);
    Element snpclip = new Element("SnpClip");
    snpclip.setText(pathSnpClipProg);

    Element list = new Element("List");
    Element path = new Element("Path");
    path.setText(save.getPath());
    list.addContent(path);

    setupdialog.listModel.trimToSize();
    int pro_count = setupdialog.listModel.getSize();
    String propath = new String();
    for (int i = 0; i < pro_count; i++) {
      if(setupdialog.listModel.getElementAt(i) != null)
        propath = setupdialog.listModel.getElementAt(i).toString();

      if(propath.compareTo(save.getPath())!=0)
      {
        Element temp_path = new Element("Path");
        temp_path.setText(propath);
        list.addContent(temp_path);
      }

      if(i>5)
        break;
    }

    info.addContent(editor);
    info.addContent(snpclip);
    info.addContent(list);

    Document ini = new Document(info);
    XMLOutputter serializer1 = new XMLOutputter();
    serializer1.output(ini, fwriter);
    fwriter.flush();
    fwriter.close();

    IconNode rootnode = activeinframe.projectnode;
    IconNode internalnode = activeinframe.internalnode;
    IconNode externalnode = activeinframe.externalnode;
    IconNode jobnode = activeinframe.jobnode;
    String projectpath = activeinframe.projectPath;

    IconNode Parameter_node = activeinframe.parameterfilenode;
    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();
    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    Element project = new Element("Project");
    project.setAttribute("path", projectpath);
    project.setText(rootnode.toString());

    Element external = new Element("External");
    int ex_child_count = externalnode.getChildCount();
    for (int i = 0; i < ex_child_count; i++) {
      IconNode temp_child = (IconNode) externalnode.getChildAt(i);
      NodeInfo temp_node = (NodeInfo) temp_child.getUserObject();
      String filename = temp_node.nodename;
      String filetype = temp_node.nodetype;
      String filepath = temp_node.file.getAbsolutePath();

      Element externalfile = new Element("ExternalFile");
      externalfile.setText(filename);
      externalfile.setAttribute("type", filetype);
      externalfile.setAttribute("path", filepath);
      external.addContent(externalfile);
    }
    project.addContent(external);

    Element internal = new Element("Internal");
    int in_child_count = internalnode.getChildCount();
    for (int i = 0; i < in_child_count; i++) {
      IconNode temp_child = (IconNode) internalnode.getChildAt(i);
      NodeInfo temp_node = (NodeInfo) temp_child.getUserObject();
      String filename = temp_node.nodename;
      String filetype = temp_node.nodetype;
      String filepath = temp_node.file.getAbsolutePath();

      Element internalfile = new Element("InternalFile");
      internalfile.setText(filename);
      internalfile.setAttribute("type", filetype);
      internalfile.setAttribute("path", filepath);

      internal.addContent(internalfile);
    }
    project.addContent(internal);

    Element jobs = new Element("Jobs");
    Vector analysis_list = getAnalysisnodelist(jobnode);
    int analysis_count = analysis_list.size();

    for (int i = 0; i < analysis_count; i++) {
      IconNode temp_iconnode = (IconNode) analysis_list.get(i);
      NodeInfo temp_node = (NodeInfo) temp_iconnode.getUserObject();
      SageFilePanel temppanel1 = (SageFilePanel) temp_node.component_vector.get(0);

      String nodename = temp_node.nodename;
      String nodetype = temp_node.nodetype;
      String analysistype = temp_node.analysis_type;

    if(nodetype.compareTo("Analysis")==0)
      saveParfiles(temp_node, analysistype);

      Element analysis = new Element("Analysis");
      analysis.setText(nodename);
      analysis.setAttribute("locked", nodetype);
      analysis.setAttribute("type", analysistype);
      jobs.addContent(analysis);

      Element note = new Element("Note");
      int total_children_count = temp_iconnode.getChildCount();
      for (int j = 0; j < total_children_count; j++) {
        IconNode temp_child = (IconNode) temp_iconnode.getChildAt(j);
        if (temp_child.iconName.compareTo("Note File") == 0) {
          NodeInfo inputfilenode = (NodeInfo) temp_child.getUserObject();
          String filename = inputfilenode.nodename;
          String filetype = inputfilenode.nodetype;
          String filepath = inputfilenode.file.getAbsolutePath();

          Element notefile = new Element("NoteFile");
          notefile.setText(filename);
          notefile.setAttribute("type", filetype);
          notefile.setAttribute("path", filepath);

          note.addContent(notefile);
        }
      }
      analysis.addContent(note);

      Element infolder = new Element("Input");
      IconNode inputfolder = temppanel1.inputF_node;
      int in_file_count = inputfolder.getChildCount();
      for (int j = 0; j < in_file_count; j++) {
        IconNode temp_child = (IconNode) inputfolder.getChildAt(j);
        NodeInfo inputfilenode = (NodeInfo) temp_child.getUserObject();
        String filename = inputfilenode.nodename;
        String filetype = inputfilenode.nodetype;
        String filepath = inputfilenode.file.getAbsolutePath();

        Element inputfile = new Element("InputFile");
        inputfile.setText(filename);
        inputfile.setAttribute("type", filetype);
        inputfile.setAttribute("path", filepath);

        infolder.addContent(inputfile);
      }
      analysis.addContent(infolder);

      Element outfolder = new Element("Output");
      IconNode outputfolder = temppanel1.outputF_node;
      int out_file_count = outputfolder.getChildCount();
      for (int j = 0; j < out_file_count; j++) {
        IconNode temp_child = (IconNode) outputfolder.getChildAt(j);
        NodeInfo outputfilenode = (NodeInfo) temp_child.getUserObject();
        String filename = outputfilenode.nodename;
        String filetype = outputfilenode.nodetype;
        String filepath = outputfilenode.file.getAbsolutePath();

        Element outputfile = new Element("OutputFile");
        outputfile.setText(filename);
        outputfile.setAttribute("type", filetype);
        outputfile.setAttribute("path", filepath);

        outfolder.addContent(outputfile);
      }
      analysis.addContent(outfolder);
    }
    project.addContent(jobs);

    Document doc = new Document(project);
    XMLOutputter serializer = new XMLOutputter();
    serializer.output(doc, fos);
    fos.flush();
    fos.close();
  }
  catch (Exception ex) {
    ex.printStackTrace();
  }
}

public void saveParfiles(NodeInfo currentnode, String analysis_type)
{
  SageFilePanel temppanel1 = (SageFilePanel) currentnode.component_vector.get(0);

  if (analysis_type.compareTo("AGEON") == 0) {
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "ageon";

    currentnode.analysis_block = activeinframe.GetAGEONblock(ageonData, Outputfilefilter);
  }

  else if (analysis_type.compareTo("ASSOC") == 0) {
    ASSOC2 temppanel2 = (ASSOC2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "assoc";

    currentnode.analysis_block = activeinframe.GetASSOCblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("FCOR") == 0) {
    FCOR2 temppanel2 = (FCOR2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "fcor";

    currentnode.analysis_block = activeinframe.GetFCORblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("FREQ") == 0) {
    FREQ2 temppanel2 = (FREQ2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "freq";

    currentnode.analysis_block = activeinframe.GetFREQblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("GENIBD") == 0) {
    GENIBD2 temppanel2 = (GENIBD2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "genibd";

    currentnode.analysis_block = activeinframe.GetGENIBDblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("LODLINK") == 0) {
    LODLINK2 temppanel2 = (LODLINK2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "lodlink";

    currentnode.analysis_block = activeinframe.GetLODLINKblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("LODPAL") == 0) {
    LODPAL2 temppanel2 = (LODPAL2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "lodpal";

    currentnode.analysis_block = activeinframe.GetLODPALblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("MARKERINFO") == 0) {
    MARKERINFO2 temppanel2 = (MARKERINFO2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "markerinfo";

    currentnode.analysis_block = activeinframe.GetMARKERINFOblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("MLOD") == 0) {
    MLOD2 temppanel2 = (MLOD2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "mlod";

    currentnode.analysis_block = activeinframe.GetMLODblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("PEDINFO") == 0) {
    PEDINFO2 temppanel2 = (PEDINFO2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "pedinfo";

    currentnode.analysis_block = activeinframe.GetPEDINFOblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("RELTEST") == 0) {
    RELTEST2 temppanel2 = (RELTEST2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "reltest";

    currentnode.analysis_block = activeinframe.GetRELTESTblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("SEGREG") == 0) {
    SEGREG3 temppanel3 = (SEGREG3) currentnode.component_vector.get(2);
    SEGREG4 temppanel4 = (SEGREG4) currentnode.component_vector.get(3);
    SEGREG5 temppanel5 = (SEGREG5) currentnode.component_vector.get(4);
    DataCollectionModel segreg3 = temppanel3.Datamodel;
    DataCollectionModel segreg4 = temppanel4.Datamodel;
    DataCollectionModel segreg5 = temppanel5.Datamodel;

    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "segreg";

    currentnode.analysis_block = activeinframe.GetSEGREGblock(ageonData, Outputfilefilter,
        segreg3, segreg4, segreg5);
  }

  else if (analysis_type.compareTo("SIBPAL") == 0) {
    SIBPAL2 temppanel2 = (SIBPAL2) currentnode.component_vector.get(1);
    SIBPAL3 temppanel3 = (SIBPAL3) currentnode.component_vector.get(2);
    SIBPAL4 temppanel4 = (SIBPAL4) currentnode.component_vector.get(3);

    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "sibpal";

    currentnode.analysis_block = activeinframe.GetSIBPALblock(ageonData, Outputfilefilter,
        temppanel3, temppanel4);
  }

  else if (analysis_type.compareTo("TDTEX") == 0) {
    TDTEX2 temppanel2 = (TDTEX2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "tdtex";

    currentnode.analysis_block = activeinframe.GetTDTEXblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("DECIPHER") == 0) {
    DECIPHER2 temppanel2 = (DECIPHER2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "decipher";

    currentnode.analysis_block = activeinframe.GetDECIPHERblock(ageonData, Outputfilefilter, temppanel2);
  }

  else if (analysis_type.compareTo("RELPAL") == 0) {
    RELPAL2 temppanel2 = (RELPAL2) currentnode.component_vector.get(1);
    DataCollectionModel ageonData = temppanel1.Datamodel;
    String Outputfilefilter;
    if (ageonData.hasValue("output_name"))
      Outputfilefilter = ageonData.getValue("output_name").toString();
    else
      Outputfilefilter = "relpal";

    currentnode.analysis_block = activeinframe.GetRELPALblock(ageonData, Outputfilefilter, temppanel2);
  }

  try {

    NodeInfo paranodeinfo = (NodeInfo) activeinframe.parameterfilenode.getUserObject();

    if (paranodeinfo.infomodel.hasValue("Master_Information")) {
      String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

      File parameterfileforrun = File.createTempFile("TempFile", "tmp");
      FileWriter fos = new FileWriter(parameterfileforrun);
      fos.write(master_info);
      fos.write("\n");
      fos.write(currentnode.analysis_block);
      fos.write("\n");
      fos.close();

      NodeInfo paranode = (NodeInfo)temppanel1.para_node.getUserObject();
      File parameterfile = paranode.file;
      MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
      parameterfileforrun.delete();
    }
  }
  catch (Exception e) {
    e.printStackTrace();
    return;
  }
}

  void jMenuFileOpen_actionPerformed(ActionEvent e) {
    JFileChooser jFileChooser1 = new JFileChooser(".");
    jFileChooser1.addChoosableFileFilter(ProjectFilter);
    jFileChooser1.setFileFilter(ProjectFilter);
    jFileChooser1.setDialogTitle("Open Project");

    if (JFileChooser.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {

      mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      final File selectedfile = jFileChooser1.getSelectedFile();

     SwingWorker worker = new SwingWorker() {
       public Object construct() {
          FileOpen(selectedfile);
          return "";
        }

        public void finished() {
          mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
          cd.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
      };
      worker.start();
    }
    else
    {
        jMenuFile.setEnabled(true);
        jMenuView.setEnabled(true);
        jMenuHelp.setEnabled(true);
        m_windowMenu.setEnabled(true);
    }
  }

  void FileOpen(File openproject) {
    final File selectedfile = openproject;

    SwingWorker worker = new SwingWorker() {
      public Object construct() {
        try {
          Frame1.desktop.add(cd);
          cd.showDialog();
          cd.setSelected(true);
          int progress = 0;
          SetProgress(progress, true);

          FileInputStream fis = new FileInputStream(selectedfile);
          InputStreamReader isr = new InputStreamReader(fis);
          BufferedReader in = new BufferedReader(isr);
          String strFileLine = new String();
          boolean newformat = false;
          while ( (strFileLine = in.readLine()) != null) {
            strFileLine = strFileLine.trim();
            if (strFileLine.indexOf("xml version") >= 0) {
              newformat = true;
              break;
            }
          }
          if (newformat) {
            FileOpen_version2(selectedfile);
          }
          else {
            FileOpen_version1(selectedfile);
          }

          path_forFileChooser = selectedfile.getPath();

          fis.close();
          isr.close();
          in.close();
          return "";
        }
        catch (Exception cex) {
          JOptionPane.showOptionDialog(Frame1.this,
                                       "Unable to open project file "+selectedfile.getName()
                                       +"\nReported error number is "+cex,
                                       "Error", // title
                                       JOptionPane.CLOSED_OPTION,
                                       JOptionPane.WARNING_MESSAGE, null, null, null);
          cex.printStackTrace();
          return cex;
        }
      }

      public void finished() {
        mainFrame1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        cd.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        init_menu();
      }
    };
    worker.start();
  }

  void FileOpen_version1(File openproject)
  {
    try {
      int progress = 20;
      SetProgress(progress, true);
      FileInputStream fis = new FileInputStream(openproject);
      ObjectInputStream ois = new ObjectInputStream(fis);

      IconNode project_object = (IconNode) ois.readObject();
      progress = 30;
      SetProgress(progress, true);
      IconNode internal_object = (IconNode) ois.readObject();
      progress = 40;
      SetProgress(progress, true);
      IconNode external_object = (IconNode) ois.readObject();
      progress = 50;
      SetProgress(progress, true);
      IconNode job_object = (IconNode) ois.readObject();
      progress = 60;
      SetProgress(progress, true);
      IconNode para_object = (IconNode) ois.readObject();

      DataCollectionModel dm = (DataCollectionModel) ois.readObject();

      String projectpath = ois.readObject().toString();

      NodeInfo nodeinfo = (NodeInfo) project_object.getUserObject();

      DataCollectionModel datamodel = new PropertyDataModel();

      MyInternalFrame frame = new MyInternalFrame(nodeinfo.nodename, desktop,
                                                  datamodel, this);
      cd.inframe = frame;
      progress = 70;
      SetProgress(progress, true);

      frame.projectnode = project_object;
      frame.internalnode = internal_object;
      frame.externalnode = external_object;
      frame.jobnode = job_object;
      frame.projectFile = openproject;
      frame.projectPath = projectpath;

      frame.parameterfilenode = para_object;
      NodeInfo paranodeinfo = (NodeInfo) para_object.getUserObject();
      paranodeinfo.infomodel = dm;



      datamodel.setValue("info.DirPath", nodeinfo.temp);

      frame.treeModel.insertNodeInto(project_object, frame.rootnode,
                                     frame.rootnode.getChildCount());

      TreePath p = new TreePath(project_object.getLastLeaf().getPath());
      frame.MyTree1.scrollPathToVisible(p);

      frame.setBounds(108 + (FRAME_COUNTER * 30), FRAME_COUNTER * 20, INI_WIDTH,
                      INI_HEIGHT);
      FRAME_COUNTER = (FRAME_COUNTER + 1) % 10;

      int children_count = frame.jobnode.getChildCount();
      Enumeration children_list = frame.jobnode.children();

      progress = 80;
      SetProgress(progress, true);

      for (int i = 0; i < children_count; i++) {
        IconNode analnode = (IconNode) children_list.nextElement();
        NodeInfo analnodeinfo = (NodeInfo) analnode.getUserObject();

        if (analnodeinfo.analysis_type.compareTo("FREQ") == 0) {
          frame.freq_count++;

          FREQ1 test1 = (FREQ1) analnodeinfo.component_vector.get(0);
          FREQ2 test2 = (FREQ2) analnodeinfo.component_vector.get(1);

          test1.SetPanel2Info( (NodeInfo) test1.para_node.getUserObject());

          if (test2.jMarkerComboBox.getSelectedItem() != null) {
            String list = test2.jMarkerComboBox.getSelectedItem().toString();

            CheckableItem[] lm = test2.jMarkerComboBox.ListData;

            StringTokenizer st = new StringTokenizer(list, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }
          }
        }
        else if (analnodeinfo.analysis_type.compareTo("FCOR") == 0) {
          frame.fcor_count++;

          FCOR1 test1 = (FCOR1) analnodeinfo.component_vector.get(0);
          FCOR2 test2 = (FCOR2) analnodeinfo.component_vector.get(1);

          test1.SetPanel2Info( (NodeInfo) test1.para_node.getUserObject());

          if (test2.jTraitComboBox.getSelectedItem() != null) {
            String list = test2.jTraitComboBox.getSelectedItem().toString();

            CheckableItem[] lm = test2.jTraitComboBox.ListData;

            StringTokenizer st = new StringTokenizer(list, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }
          }
        }

        else if (analnodeinfo.analysis_type.compareTo("SIBPAL") == 0) {
          frame.sibpal_count++;

          SIBPAL1 test1 = (SIBPAL1) analnodeinfo.component_vector.get(0);
          SIBPAL2 test2 = (SIBPAL2) analnodeinfo.component_vector.get(1);
          SIBPAL3 test3 = (SIBPAL3) analnodeinfo.component_vector.get(2);
          SIBPAL4 test4 = (SIBPAL4) analnodeinfo.component_vector.get(3);

          test1.SetPanel2Info( (NodeInfo) test1.para_node.getUserObject());

          if (test3.jTraitComboBox.getSelectedItem() != null) {
            String list1 = test3.jTraitComboBox.getSelectedItem().toString();

            CheckableItem[] lm = test3.jTraitComboBox.ListData;
            StringTokenizer st = new StringTokenizer(list1, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }
          }

          if (test3.jSubsetComboBox.getSelectedItem() != null) {
            String list2 = test3.jSubsetComboBox.getSelectedItem().toString();

            CheckableItem[] lm = test3.jSubsetComboBox.ListData;
            StringTokenizer st = new StringTokenizer(list2, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }

          }

          if (test4.jSubsetComboBox.getSelectedItem() != null) {
            String list3 = test4.jSubsetComboBox.getSelectedItem().toString();
            CheckableItem[] lm = test4.jSubsetComboBox.ListData;

            StringTokenizer st = new StringTokenizer(list3, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }
          }

          if (test1.ibd_node != null) {

            test1.SetSIBPAL2MarkerInfo( (NodeInfo) test1.ibd_node.getUserObject());

            if (test3.jMarkerComboBox.getSelectedItem() != null) {
              String markerlist1 = test3.jMarkerComboBox.getSelectedItem().toString();
              CheckableItem[] lm = test3.jMarkerComboBox.ListData;
              StringTokenizer st = new StringTokenizer(markerlist1, ",");
              while (st.hasMoreTokens()) {
                String checkitem = st.nextElement().toString().trim();
                for (int listindex = 0; listindex < lm.length; listindex++) {
                  CheckableItem item = (CheckableItem) lm[listindex];
                  if (item.toString().compareTo(checkitem) == 0)
                    item.setSelected(true);
                }
              }
            }

            if (test4.jMarkerComboBox.getSelectedItem() != null) {
              String markerlist2 = test4.jMarkerComboBox.getSelectedItem().
                  toString();

              CheckableItem[] lm = test4.jMarkerComboBox.ListData;
              StringTokenizer st = new StringTokenizer(markerlist2, ",");
              while (st.hasMoreTokens()) {
                String checkitem = st.nextElement().toString().trim();
                for (int listindex = 0; listindex < lm.length; listindex++) {
                  CheckableItem item = (CheckableItem) lm[listindex];
                  if (item.toString().compareTo(checkitem) == 0)
                    item.setSelected(true);
                }
              }
            }
          }
        }
        else if (analnodeinfo.analysis_type.compareTo("TDTEX") == 0) {
          frame.tdtex_count++;

          TDTEX1 test1 = (TDTEX1) analnodeinfo.component_vector.get(0);
          TDTEX2 test2 = (TDTEX2) analnodeinfo.component_vector.get(1);

          test1.SetPanel2Info( (NodeInfo) test1.para_node.getUserObject());
          if (test2.jMarkerComboBox.getSelectedItem() != null) {
            String list = test2.jMarkerComboBox.getSelectedItem().toString();

            CheckableItem[] lm = test2.jMarkerComboBox.ListData;

            StringTokenizer st = new StringTokenizer(list, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }
          }

          if (test2.jComboBox3.getSelectedItem() != null) {
            String list = test2.jComboBox3.getSelectedItem().toString();

            test2.Setoption(false);
            CheckableItem[] lm = test2.jComboBox3.ListData;

            StringTokenizer st = new StringTokenizer(list, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }
          }
        }

        else if (analnodeinfo.analysis_type.compareTo("PEDINFO") == 0) {
          frame.pedinfo_count++;

          PEDINFO1 test1 = (PEDINFO1) analnodeinfo.component_vector.get(0);
          PEDINFO2 test2 = (PEDINFO2) analnodeinfo.component_vector.get(1);

          test1.SetPanel2Info( (NodeInfo) test1.para_node.getUserObject());

          if (test2.jTraitComboBox.getSelectedItem() != null) {
            String list = test2.jTraitComboBox.getSelectedItem().toString();

            CheckableItem[] lm = test2.jTraitComboBox.ListData;

            StringTokenizer st = new StringTokenizer(list, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }
          }
        }
        else if (analnodeinfo.analysis_type.compareTo("LODPAL") == 0) {
          frame.lodpal_count++;
          LODPAL1 test1 = (LODPAL1) analnodeinfo.component_vector.get(0);
          LODPAL2 test2 = (LODPAL2) analnodeinfo.component_vector.get(1);

          test1.SetPanel2Info( (NodeInfo) test1.para_node.getUserObject());

          if (test2.jSubsetComboBox.getSelectedItem() != null) {
            String list = test2.jSubsetComboBox.getSelectedItem().toString();

            CheckableItem[] lm = test2.jSubsetComboBox.ListData;

            StringTokenizer st = new StringTokenizer(list, ",");
            while (st.hasMoreTokens()) {
              String checkitem = st.nextElement().toString().trim();
              for (int listindex = 0; listindex < lm.length; listindex++) {
                CheckableItem item = (CheckableItem) lm[listindex];
                if (item.toString().compareTo(checkitem) == 0)
                  item.setSelected(true);
              }
            }
          }

          if (test1.ibd_node != null) {
            test1.SetLODPAL2MarkerInfo( (NodeInfo) test1.ibd_node.getUserObject());

            if (test2.jMarkerComboBox.getSelectedItem() != null) {
              String list = test2.jMarkerComboBox.getSelectedItem().toString();

              CheckableItem[] lm = test2.jMarkerComboBox.ListData;

              StringTokenizer st = new StringTokenizer(list, ",");
              while (st.hasMoreTokens()) {
                String checkitem = st.nextElement().toString().trim();
                for (int listindex = 0; listindex < lm.length; listindex++) {
                  CheckableItem item = (CheckableItem) lm[listindex];
                  if (item.toString().compareTo(checkitem) == 0)
                    item.setSelected(true);
                }
              }
            }
          }
        }

        else if (analnodeinfo.analysis_type.compareTo("GENIBD") == 0) {
          frame.genibd_count++;
          GENIBD1 test1 = (GENIBD1) analnodeinfo.component_vector.get(0);
          GENIBD2 test2 = (GENIBD2) analnodeinfo.component_vector.get(1);

          if (test1.genome_node != null) {
            test1.SetGENIBD2RegionInfo( (NodeInfo) test1.genome_node.
                                       getUserObject());

            if (test2.jRegionComboBox.getSelectedItem() != null) {
              String list = test2.jRegionComboBox.getSelectedItem().toString();

              CheckableItem[] lm = test2.jRegionComboBox.ListData;

              StringTokenizer st = new StringTokenizer(list, ",");
              while (st.hasMoreTokens()) {
                String checkitem = st.nextElement().toString().trim();
                for (int listindex = 0; listindex < lm.length; listindex++) {
                  CheckableItem item = (CheckableItem) lm[listindex];
                  if (item.toString().compareTo(checkitem) == 0)
                    item.setSelected(true);
                }
              }
            }
          }
        }
        else if (analnodeinfo.analysis_type.compareTo("RELTEST") == 0) {
          frame.reltest_count++;
          RELTEST1 test1 = (RELTEST1) analnodeinfo.component_vector.get(0);
          RELTEST2 test2 = (RELTEST2) analnodeinfo.component_vector.get(1);

          if (test1.genome_node != null) {
            test1.SetRELTEST2RegionInfo( (NodeInfo) test1.genome_node.
                                        getUserObject());

            if (test2.jRegionComboBox.getSelectedItem() != null) {
              String list = test2.jRegionComboBox.getSelectedItem().toString();

              CheckableItem[] lm = test2.jRegionComboBox.ListData;

              StringTokenizer st = new StringTokenizer(list, ",");
              while (st.hasMoreTokens()) {
                String checkitem = st.nextElement().toString().trim();
                for (int listindex = 0; listindex < lm.length; listindex++) {
                  CheckableItem item = (CheckableItem) lm[listindex];
                  if (item.toString().compareTo(checkitem) == 0)
                    item.setSelected(true);
                }
              }
            }
          }
        }
        else if (analnodeinfo.analysis_type.compareTo("SEGREG") == 0) {
          frame.segreg_count++;
        }
        else if (analnodeinfo.analysis_type.compareTo("MLOD") == 0) {
          frame.mlod_count++;
        }
        else if (analnodeinfo.analysis_type.compareTo("LODLINK") == 0) {
          frame.lodlink_count++;
        }
        else if (analnodeinfo.analysis_type.compareTo("MARKERINFO") == 0) {
          frame.markerinfo_count++;
        }
        else if (analnodeinfo.analysis_type.compareTo("ASSOC") == 0) {
          frame.assoc_count++;
        }
        else if (analnodeinfo.analysis_type.compareTo("AGEON") == 0) {
          frame.ageon_count++;
        }
      }

      progress = 120;
      SetProgress(progress, true);

      frame.setVisible(true);
      desktop.add(frame);
      desktop.updateUI();
      init_menu();

      frame.CreateMonitorThread();

      cd.setSelected(true);
      cd.jButton1.setEnabled(true);
      cd.jButton1_actionPerformed();
    }
    catch (Exception ex) {
      desktop.remove(cd);
      desktop.updateUI();

      JOptionPane.showOptionDialog(this.getParent(),
                                   "Unable to open project file "+openproject.getName()
                                   +"\nReported error number is "+ex,
                                   "Error", // title
                                   JOptionPane.CLOSED_OPTION,
                                   JOptionPane.WARNING_MESSAGE, null, null, null);
      return;
    }
  }

  void FileOpen_version2(File openproject)
  {
    SAXBuilder builder = new SAXBuilder();
    try{
      Document doc = builder.build(openproject);
      Element root = doc.getRootElement();

      MyInternalFrame frame = getRootNode(doc);
      frame.projectFile = openproject;

      activateInternalFrame(frame);
      cd.inframe = frame;

      int progress = 10;
      SetProgress(progress, true);
      List children = root.getContent();
      Iterator iterator = children.iterator();
      while(iterator.hasNext())
      {
        Object child = iterator.next();
        if (child instanceof Element) {
          Element element = (Element) child;
          String element_name = element.getName();

          if(element_name.compareTo("External")==0)
          {
            setExternalNode(element, frame);
            progress = 20;
            SetProgress(progress, true);
          }
          else if(element_name.compareTo("Internal")==0)
          {
            setInternalNode(element, frame);
            progress = 30;
            SetProgress(progress, true);
          }
          else if(element_name.compareTo("Jobs")==0)
          {
            NodeInfo paranode = (NodeInfo)frame.parameterfilenode.getUserObject();
            DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;
            setJobsNode(element, frame, dm);

            progress = 90;
            SetProgress(progress, true);
          }
        }
      }
      frame.setBounds(108 + (FRAME_COUNTER * 30), FRAME_COUNTER * 20, INI_WIDTH,
                      INI_HEIGHT);
      FRAME_COUNTER = (FRAME_COUNTER + 1) % 10;

      progress = 100;
      SetProgress(progress, true);

      frame.setVisible(true);
      desktop.add(frame);
      desktop.updateUI();
      init_menu();

      frame.CreateMonitorThread();

      cd.jButton1.setEnabled(true);
      cd.jButton1_actionPerformed();
    }
    catch(JDOMException e)
    {
      JOptionPane.showOptionDialog(this.getParent(),
                                   "Unable to open project file "+openproject.getName()
                                   +"\nReported error number is "+e,
                                   "Error",
                                   JOptionPane.CLOSED_OPTION,
                                   JOptionPane.WARNING_MESSAGE, null, null, null);
      e.printStackTrace();
      System.exit(0);
      return;
    }
    catch(Exception e)
    {
      JOptionPane.showOptionDialog(this.getParent(),
                                   "Unable to open project file "+openproject.getName()
                                   +"\nReported error number is "+e,
                                   "Error",
                                   JOptionPane.CLOSED_OPTION,
                                   JOptionPane.WARNING_MESSAGE, null, null, null);
      e.printStackTrace();

      System.exit(0);
      return;
    }
  }

  public IconNode CreateAnaysisNode(Element element, IconNode job, final MyInternalFrame frame, DataCollectionModel dm)
  {
    Element analysis_element = element;

    String analysis_type = analysis_element.getAttributeValue("type");
    String analysis_name = analysis_element.getTextTrim();
    String analysis_block = new String();

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         if(filetype.compareTo("Parameter File")==0)
         {
           analysis_block = getAnalysisBlock(filepath, analysis_type);
         }
     }

    IconNode analysis=null;

    if(analysis_type.compareTo("AGEON")==0)
    {
      analysis = CreateAGEON(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("ASSOC")==0)
    {
      analysis = CreateASSOC(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if (analysis_type.compareTo("FCOR") == 0)
    {
      analysis = CreateFCOR(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if (analysis_type.compareTo("FREQ") == 0)
    {
      analysis = CreateFREQ(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if (analysis_type.compareTo("GENIBD") == 0)
    {
      analysis = CreateGENIBD(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if (analysis_type.compareTo("LODLINK") == 0)
    {
      analysis = CreateLODLINK(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("LODPAL")==0)
    {
      analysis = CreateLODPAL(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("MARKERINFO")==0)
    {
      analysis = CreateMARKERINFO(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("MLOD")==0)
    {
      analysis = CreateMLOD(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if (analysis_type.compareTo("PEDINFO")== 0)
    {
      analysis = CreatePEDINFO(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("RELTEST")==0)
    {
      analysis = CreateRELTEST(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if (analysis_type.compareTo("SEGREG")== 0)
    {
      analysis = CreateSEGREG(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("SIBPAL")==0)
    {
      analysis = CreateSIBPAL(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("TDTEX")==0)
    {
      analysis = CreateTDTEX(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("DECIPHER")==0)
    {
      analysis = CreateDECIPHER(analysis_name, analysis_block, analysis_element, frame, dm);
    }
    else if(analysis_type.compareTo("RELPAL")==0)
    {
        analysis = CreateRELPAL(analysis_name, analysis_block, analysis_element, frame, dm);
    }

    if(analysis != null)
    {
      final TreePath p = new TreePath(analysis.getPath());

      NodeInfo currentnode = (NodeInfo)analysis.getUserObject();
      SageFilePanel test1 = (SageFilePanel) currentnode.component_vector.get(0);
      final IconNode outputf = test1.outputF_node;

      Element outputfolder = analysis_element.getChild("Output");
      List outputfile = outputfolder.getChildren();
      final Iterator iterator2 = outputfile.iterator();

      final IconNode analysis_p = analysis;

      List notefile = null;
      Iterator iterator_note_temp = null;
      Element notefolder = analysis_element.getChild("Note");
      if(notefolder!=null)
      {
        notefile = notefolder.getChildren();
        iterator_note_temp = notefile.iterator();
      }

      final List notefilelist = notefile;
      final Iterator iterator_note = iterator_note_temp;

      Runnable setinfo = new Runnable()
      {
        public void run()
        {
          if (notefilelist != null)
            while (iterator_note.hasNext()) {
              Object child = iterator_note.next();
              Element outfile_element = (Element) child;

              String filename = outfile_element.getTextTrim();
              String filetype = outfile_element.getAttributeValue("type");
              String filepath = outfile_element.getAttributeValue("path");

              NodeInfo filenode = new NodeInfo(filename, filetype,
                                               new File(filepath));
              IconNode notefilenode = new IconNode(filenode, filetype);
              frame.treeModel.insertNodeInto(notefilenode, analysis_p, 0);
            }

          boolean output = false;

          while(iterator2.hasNext())
          {
              Object child = iterator2.next();
              Element outfile_element = (Element) child;

              String filename = outfile_element.getTextTrim();
              String filetype = outfile_element.getAttributeValue("type");
              String filepath = outfile_element.getAttributeValue("path");

              NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));
              IconNode outputfilenode = new IconNode(filenode, filetype);
              frame.addObject(outputfilenode, outputf, false);
              output = true;
          }
          if(output)
            frame.addObject(outputf, analysis_p, false);

        }
      };
      SwingUtilities.invokeLater(setinfo);
    }

   return analysis;
  }

  public IconNode CreateAGEON(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("AGEON");
    NodeInfo ageonnode = new NodeInfo(blockname,locked_type,"AGEON" ,ai, block);
    IconNode ageon = new IconNode(ageonnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "AGEON", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, ageon, ageon.getChildCount());

    final AGEON1 ageon1 = new AGEON1(ai, ageon, errorfolder);
    final AGEON2 ageon2 = new AGEON2(ageon1);
    ageon1.OutputNameField.setText(blockname);

    ageonnode.component_vector.add(ageon1);
    ageonnode.component_vector.add(ageon2);
    ageonnode.component_name_vector.add("Files");
    ageonnode.component_name_vector.add("Analysis Definition");

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(ageon2);

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"AGEON", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    ageon1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable()
         {
           public void run()
           {
             if (filetype.compareTo("Parameter File") == 0)
             {
               filenode.infomodel = dm;
               ageon1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setAGEONblock(block, ageon2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               ageon1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setAGEONblock(block, ageon2);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.ageon_count++;
     return ageon;
  }

  public IconNode CreateASSOC(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("ASSOC");
    NodeInfo assocnode = new NodeInfo(blockname,locked_type,"ASSOC" ,ai, block);
    IconNode assoc = new IconNode(assocnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "ASSOC", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

    final ASSOC1 assoc1 = new ASSOC1(ai, assoc, errorfolder);
    final ASSOC2 assoc2 = new ASSOC2(assoc1);
    assoc1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(assoc2);

    assocnode.component_vector.add(assoc1);
    assocnode.component_vector.add(assoc2);
    assocnode.component_name_vector.add("Files");
    assocnode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"ASSOC", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    assoc1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               assoc1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setASSOCblock(block, assoc2);

             }
             if (filetype.compareTo("Pedigree File") == 0) {
               assoc1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setASSOCblock(block, assoc2);
             }

           }
         };
         SwingUtilities.invokeLater(setinfo);
     }

     inframe.assoc_count++;
     return assoc;
  }

  public IconNode CreateFCOR(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");
    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("FCOR");
    NodeInfo fcornode = new NodeInfo(blockname,locked_type,"FCOR" ,ai, block);
    IconNode fcor = new IconNode(fcornode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "FCOR", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, fcor, fcor.getChildCount());

    final FCOR1 fcor1 = new FCOR1(ai, fcor, errorfolder);
    final FCOR2 fcor2 = new FCOR2(fcor1);
    fcor1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(fcor2);

    fcornode.component_vector.add(fcor1);
    fcornode.component_vector.add(fcor2);
    fcornode.component_name_vector.add("Files");
    fcornode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"FCOR", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    fcor1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               fcor1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setFCORblock(block, fcor2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               fcor1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setFCORblock(block, fcor2);
             }
             if (filetype.compareTo("Marker Locus File") == 0) {
               fcor1.insertlocusfile(filenode);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.fcor_count++;
     return fcor;
  }

  public IconNode CreateFREQ(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");
    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("FREQ");
    NodeInfo freqnode = new NodeInfo(blockname,locked_type,"FREQ" ,ai, block);
    IconNode freq = new IconNode(freqnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "FREQ", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, freq, freq.getChildCount());

    final FREQ1 freq1 = new FREQ1(ai, freq, errorfolder);
    final FREQ2 freq2 = new FREQ2(freq1);
    freq1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(freq2);

    freqnode.component_vector.add(freq1);
    freqnode.component_vector.add(freq2);
    freqnode.component_name_vector.add("Files");
    freqnode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"FREQ", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    freq1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               freq1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setFREQblock(block, freq2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               freq1.insertpedigreefile(filenode);
               setFREQblock(block, freq2);
             }
             if (filetype.compareTo("Marker Locus File") == 0) {
               freq1.insertlocusfile(filenode);
             }
           }
          };
         SwingUtilities.invokeLater(setinfo);
     }
     inframe.freq_count++;
     return freq;
  }

  public IconNode CreateGENIBD(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("GENIBD");
    NodeInfo genibdnode = new NodeInfo(blockname,locked_type,"GENIBD" ,ai, block);
    IconNode genibd = new IconNode(genibdnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "GENIBD", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, genibd, genibd.getChildCount());

    final GENIBD1 genibd1 = new GENIBD1(ai, genibd, errorfolder);
    final GENIBD2 genibd2 = new GENIBD2(genibd1);
    genibd1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(genibd2);

    genibdnode.component_vector.add(genibd1);
    genibdnode.component_vector.add(genibd2);
    genibdnode.component_name_vector.add("Files");
    genibdnode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"GENIBD", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    genibd1.errorpedigree_node = error1;

    errornodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode" ,"GENIBD", ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorLocusNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    genibd1.errorlocus_node = error1;

    errornodeinfo = new NodeInfo("Genome File to the multipoint Missing", "ErrorGenomeNode" ,"GENIBD", ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorGenomeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    genibd1.errorgenome_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               genibd1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setGENIBDblock(block, genibd2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               genibd1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setGENIBDblock(block, genibd2);
             }
             if (filetype.compareTo("Marker Locus File") == 0) {
               genibd1.insertlocusfile(filenode);
             }
             if (filetype.compareTo("Genome File") == 0) {
               genibd1.insertgenomefile(filenode);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.genibd_count++;
     return genibd;
  }

  public IconNode CreateLODLINK(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("LODLINK");
    NodeInfo lodlinknode = new NodeInfo(blockname,locked_type,"LODLINK" ,ai, block);
    IconNode lodlink = new IconNode(lodlinknode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "LODLINK", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, lodlink, lodlink.getChildCount());

    final LODLINK1 lodlink1 = new LODLINK1(ai, lodlink, errorfolder);
    final LODLINK2 lodlink2 = new LODLINK2(lodlink1);
    lodlink1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(lodlink2);

    lodlinknode.component_vector.add(lodlink1);
    lodlinknode.component_vector.add(lodlink2);
    lodlinknode.component_name_vector.add("Files");
    lodlinknode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"LODLINK", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    lodlink1.errorpedigree_node = error1;

    errornodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","LODLINK", ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorLocusNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    lodlink1.errorlocus_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable()
         {
           public void run()
           {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               lodlink1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setLODLINKblock(block, lodlink2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               lodlink1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setLODLINKblock(block, lodlink2);
             }
             if (filetype.compareTo("Marker Locus File") == 0) {
               lodlink1.insertlocusfile(filenode);
             }
             if (filetype.compareTo("Trait File") == 0) {
               lodlink1.inserttraitfile(filenode);
             }
             if (filetype.compareTo("Type File") == 0) {
               lodlink1.inserttypefile(filenode);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }

     inframe.lodlink_count++;
     return lodlink;
  }

  public IconNode CreateLODPAL(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("LODPAL");
    NodeInfo lodpalnode = new NodeInfo(blockname,locked_type,"LODPAL" ,ai, block);
    IconNode lodpal = new IconNode(lodpalnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "LODPAL", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, lodpal, lodpal.getChildCount());

    final LODPAL1 lodpal1 = new LODPAL1(ai, lodpal, errorfolder);
    final LODPAL2 lodpal2 = new LODPAL2(lodpal1);
    lodpal1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(lodpal2);

    lodpalnode.component_vector.add(lodpal1);
    lodpalnode.component_vector.add(lodpal2);
    lodpalnode.component_name_vector.add("Files");
    lodpalnode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"LODPAL", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    lodpal1.errorpedigree_node = error1;

    errornodeinfo = new NodeInfo("IBD File Missing", "ErrorIBDNode","LODPAL", ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorIBDNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    lodpal1.errorlocus_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               lodpal1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setLODPALblock(block, lodpal2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               lodpal1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setLODPALblock(block, lodpal2);
             }
             if (filetype.compareTo("IBD Sharing File") == 0) {
               lodpal1.insertibdfile(filenode);
               setLODPALblock(block, lodpal2);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.lodpal_count++;
     return lodpal;
  }


  public IconNode CreateRELPAL(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
 {
     final DataCollectionModel dm = dm_input;
     final String locked_type = analysis_element.getAttributeValue("locked");
     IconNode relpal = makeRELPALNode(analysis_name, analysis_block.toString());

     final String block = analysis_block;
     NodeInfo relpalnode = (NodeInfo)relpal.getUserObject();
     final RELPAL1 relpal1 = (RELPAL1)relpalnode.component_vector.get(0);
     final RELPAL2 relpal2 = (RELPAL2)relpalnode.component_vector.get(1);

     Element inputfolder = analysis_element.getChild("Input");
     List inputfile = inputfolder.getChildren();
     Iterator iterator = inputfile.iterator();
     while (iterator.hasNext()) {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
             public void run() {
                 if (filetype.compareTo("Parameter File") == 0) {
                     filenode.infomodel = dm;
                     relpal1.insertparafile(filenode);

                     if (locked_type.compareTo("Analysis") == 0)
                         setRELPALblock(block, relpal2);
                 }
                 if (filetype.compareTo("Pedigree File") == 0) {
                     relpal1.insertpedigreefile(filenode);
                     if (locked_type.compareTo("Analysis") == 0)
                         setRELPALblock(block, relpal2);

                 }
                 if (filetype.compareTo("IBD Sharing File") == 0) {
                     relpal1.insertibdfile(filenode);
                     setRELPALblock(block, relpal2);
                 }
             }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.relpal_count++;
     return relpal;
 }


  public IconNode CreateMARKERINFO(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("MARKERINFO");
    NodeInfo markerinfonode = new NodeInfo(blockname,locked_type,"MARKERINFO" ,ai, block);
    IconNode markerinfo = new IconNode(markerinfonode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "MARKERINFO", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, markerinfo, markerinfo.getChildCount());

    final MARKERINFO1 markerinfo1 = new MARKERINFO1(ai, markerinfo, errorfolder);
    final MARKERINFO2 markerinfo2 = new MARKERINFO2(markerinfo1);
    markerinfo1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(markerinfo2);

    markerinfonode.component_vector.add(markerinfo1);
    markerinfonode.component_vector.add(markerinfo2);
    markerinfonode.component_name_vector.add("Files");
    markerinfonode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"MARKERINFO", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    markerinfo1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               markerinfo1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setMARKERINFOblock(block, markerinfo2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               markerinfo1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setMARKERINFOblock(block, markerinfo2);
             }
             if (filetype.compareTo("Marker Locus File") == 0) {
               markerinfo1.insertlocusfile(filenode);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.markerinfo_count++;
     return markerinfo;
  }

  public IconNode CreateMLOD(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("MLOD");
    NodeInfo mlodnode = new NodeInfo(blockname,locked_type,"MLOD" ,ai, block);
    IconNode mlod = new IconNode(mlodnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "MLOD", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, mlod, mlod.getChildCount());

    final MLOD1 mlod1 = new MLOD1(ai, mlod, errorfolder);
    final MLOD2 mlod2 = new MLOD2(mlod1);
    mlod1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(mlod2);

    mlodnode.component_vector.add(mlod1);
    mlodnode.component_vector.add(mlod2);
    mlodnode.component_name_vector.add("Files");
    mlodnode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"MLOD", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    mlod1.errorpedigree_node = error1;

    errornodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","MLOD", ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorLocusNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    mlod1.errorlocus_node = error1;

    errornodeinfo = new NodeInfo("Genome File Missing", "ErrorGenomeNode","MLOD", ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorGenomeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    mlod1.errorgenome_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               mlod1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setMLODblock(block, mlod2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               mlod1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setMLODblock(block, mlod2);
             }
             if (filetype.compareTo("Marker Locus File") == 0) {
               mlod1.insertlocusfile(filenode);
             }
             if (filetype.compareTo("Trait File") == 0) {
               mlod1.inserttraitfile(filenode);
             }
             if (filetype.compareTo("Type File") == 0) {
               mlod1.inserttypefile(filenode);
             }
             if (filetype.compareTo("Genome File") == 0) {
               mlod1.insertgenomefile(filenode);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.mlod_count++;
     return mlod;
  }

  public IconNode CreatePEDINFO(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("PEDINFO");
    final NodeInfo pedinfonode = new NodeInfo(blockname,locked_type,"PEDINFO" ,ai, block);
    IconNode pedinfo = new IconNode(pedinfonode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "PEDINFO", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, pedinfo, pedinfo.getChildCount());

    final PEDINFO1 pedinfo1 = new PEDINFO1(ai, pedinfo, errorfolder);
    final PEDINFO2 pedinfo2 = new PEDINFO2(pedinfo1);
    pedinfo1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(pedinfo2);

    pedinfonode.component_vector.add(pedinfo1);
    pedinfonode.component_vector.add(pedinfo2);
    pedinfonode.component_name_vector.add("Files");
    pedinfonode.component_name_vector.add("Analysis Definition");

    NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","PEDINFO", ai, block);
    IconNode error1 = new IconNode(nodeinfo,"ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    pedinfo1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               pedinfo1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                   setPEDINFOblock(block, pedinfo2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               pedinfo1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                   setPEDINFOblock(block, pedinfo2);
             }
             if(locked_type.compareTo("Analysis")==0)
                 setPEDINFOblock(block, pedinfo2);

           }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.pedinfo_count++;
     return pedinfo;
  }

  public IconNode CreateRELTEST(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");
    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("RELTEST");
    final NodeInfo reltestnode = new NodeInfo(blockname,locked_type,"RELTEST" ,ai, block);
    IconNode reltest = new IconNode(reltestnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "RELTEST", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, reltest, reltest.getChildCount());

    final RELTEST1 reltest1 = new RELTEST1(ai, reltest, errorfolder);
    final RELTEST2 reltest2 = new RELTEST2(reltest1);
    reltest1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(reltest2);

    reltestnode.component_vector.add(reltest1);
    reltestnode.component_vector.add(reltest2);
    reltestnode.component_name_vector.add("Files");
    reltestnode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","RELTEST", ai, block);
    IconNode error1 = new IconNode(errornodeinfo,"ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    reltest1.errorpedigree_node = error1;

    errornodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","RELTEST",  ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorLocusNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    reltest1.errorlocus_node = error1;

    errornodeinfo = new NodeInfo("Genome File Missing", "ErrorGenomeNode","RELTEST",  ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorGenomeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    reltest1.errorgenome_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               reltest1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setRELTESTblock(block, reltest2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               reltest1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setRELTESTblock(block, reltest2);

             }
             if (filetype.compareTo("Marker Locus File") == 0) {
               reltest1.insertlocusfile(filenode);
             }
             if (filetype.compareTo("Genome File") == 0) {
               reltest1.insertgenomefile(filenode);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);


     }
     inframe.reltest_count++;
     return reltest;
  }

  public IconNode CreateSIBPAL(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("SIBPAL");
    NodeInfo sibpalnode = new NodeInfo(blockname,locked_type,"SIBPAL" ,ai, block);
    IconNode sibpal = new IconNode(sibpalnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "SIBPAL", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, sibpal, sibpal.getChildCount());

    final SIBPAL1 sibpal1 = new SIBPAL1(ai, sibpal, errorfolder);
    final SIBPAL2 sibpal2 = new SIBPAL2(sibpal1);
    final SIBPAL3 sibpal3 = new SIBPAL3(sibpal1);
    final SIBPAL4 sibpal4 = new SIBPAL4(sibpal1);
    sibpal1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
    {
      setDisableAllComponents(sibpal2);
      setDisableAllComponents(sibpal3);
      setDisableAllComponents(sibpal4);
    }

    sibpalnode.component_vector.add(sibpal1);
    sibpalnode.component_vector.add(sibpal2);
    sibpalnode.component_vector.add(sibpal3);
    sibpalnode.component_vector.add(sibpal4);

    sibpalnode.component_name_vector.add("Files");
    sibpalnode.component_name_vector.add("Analysis Definition");
    sibpalnode.component_name_vector.add("Mean Test");
    sibpalnode.component_name_vector.add("Trait Regression");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"SIBPAL", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    sibpal1.errorpedigree_node = error1;

    errornodeinfo = new NodeInfo("IBD File Missing", "ErrorIBDNode","SIBPAL", ai, block);
    error1 = new IconNode(errornodeinfo, "ErrorIBDNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    sibpal1.errorlocus_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               sibpal1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setSIBPALblock(block, sibpal2, sibpal3, sibpal4);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               sibpal1.insertpedigreefile(filenode);

               if(locked_type.compareTo("Analysis")==0)
                 setSIBPALblock(block, sibpal2, sibpal3, sibpal4);

             }
             if (filetype.compareTo("IBD Sharing File") == 0) {
               sibpal1.insertibdfile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setSIBPALblock(block, sibpal2, sibpal3, sibpal4);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }
     inframe.sibpal_count++;
     return sibpal;
  }

  public IconNode CreateSEGREG(String analysis_name, String analysis_block,
                               Element analysis_element,
                               MyInternalFrame inframe,
                               DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    String master_information = new String();
    if (dm.hasValue("Master_Information")) {
      master_information = dm.getValue("Master_Information").toString();
    }

    int segreg_type = getSegregType(block, master_information);

    sage_analysis_info ai = new sage_analysis_info("SEGREG");
    NodeInfo segregnode = new NodeInfo(blockname, locked_type, "SEGREG", ai, block);
    IconNode segreg = new IconNode(segregnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "SEGREG", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, segreg, segreg.getChildCount());

    final SEGREG1 segreg1 = new SEGREG1(ai, segreg, errorfolder);
    final SEGREG2 segreg2 = new SEGREG2(segreg1);
    final SEGREG3 segreg3 = new SEGREG3(segreg2);
    final SEGREG4 segreg4 = new SEGREG4(segreg2);
    final SEGREG5 segreg5 = new SEGREG5();
    segreg1.OutputNameField.setText(blockname);

    final int segreg_type_info = segreg_type;

    if(locked_type.compareTo("Locked Analysis")==0)
    {
      setDisableAllComponents(segreg2);
      setDisableAllComponents(segreg3);
      setDisableAllComponents(segreg4);
      setDisableAllComponents(segreg5);
    }

    segregnode.component_vector.add(segreg1);
    segregnode.component_vector.add(segreg2);
    segregnode.component_vector.add(segreg3);
    segregnode.component_vector.add(segreg4);
    segregnode.component_vector.add(segreg5);

    segregnode.component_name_vector.add("Files");
    segregnode.component_name_vector.add("Analysis Definition");
    segregnode.component_name_vector.add("Quantitative");
    segregnode.component_name_vector.add("Binary");
    segregnode.component_name_vector.add("Binary with variable age of onset");

    segreg2.setSEGREGpanel(segreg2.Default);

    NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode", "SEGREG", ai, block);
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    segreg1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
    while (iterator.hasNext()) {
      Object child = iterator.next();
      Element infile_element = (Element) child;

      String filename = infile_element.getTextTrim();
      final String filetype = infile_element.getAttributeValue("type");
      String filepath = infile_element.getAttributeValue("path");

      final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

      Runnable setinfo = new Runnable() {
        public void run() {
          if (filetype.compareTo("Parameter File") == 0) {
            filenode.infomodel = dm;
            segreg1.insertparafile(filenode);
            switch (segreg_type_info) {
              case 1:
                segreg2.QRadioButton.setSelected(true);
                if(locked_type.compareTo("Analysis")==0)
                  setSEGREGblock_C(block, segreg2, segreg3, false);
                break;
              case 2:
                segreg2.BRadioButton.setSelected(true);
                if(locked_type.compareTo("Analysis")==0)
                  setSEGREGblock_B(block, segreg2, segreg4, false);
                break;
              case 3:
                segreg2.BARadioButton.setSelected(true);
                if(locked_type.compareTo("Analysis")==0)
                  setSEGREGblock_BA(block, segreg2, segreg5, false);
                break;
            }
          }
          if (filetype.compareTo("Pedigree File") == 0) {
            segreg1.insertpedigreefile(filenode);
            switch (segreg_type_info) {
              case 1:
                segreg2.QRadioButton.setSelected(true);
                if(locked_type.compareTo("Analysis")==0)
                  setSEGREGblock_C(block, segreg2, segreg3, false);
                break;
              case 2:
                segreg2.BRadioButton.setSelected(true);
                if(locked_type.compareTo("Analysis")==0)
                  setSEGREGblock_B(block, segreg2, segreg4, false);
                break;
              case 3:
                segreg2.BARadioButton.setSelected(true);
                if(locked_type.compareTo("Analysis")==0)
                  setSEGREGblock_BA(block, segreg2, segreg5, false);
                break;
            }
          }
        }
      };
      SwingUtilities.invokeLater(setinfo);

    }
    inframe.segreg_count++;
    return segreg;
  }

  public IconNode CreateTDTEX(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("TDTEX");
    final NodeInfo tdtexnode = new NodeInfo(blockname,locked_type,"TDTEX" ,ai, block);
    IconNode tdtex = new IconNode(tdtexnode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "TDTEX", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, tdtex, tdtex.getChildCount());

    final TDTEX1 tdtex1 = new TDTEX1(ai, tdtex, errorfolder);
    final TDTEX2 tdtex2 = new TDTEX2(tdtex1);
    tdtex1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(tdtex2);

    tdtexnode.component_vector.add(tdtex1);
    tdtexnode.component_vector.add(tdtex2);
    tdtexnode.component_name_vector.add("Files");
    tdtexnode.component_name_vector.add("Analysis Definition");

    NodeInfo nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","TDTEX", ai, block);
    IconNode error1 = new IconNode(nodeinfo,"ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    tdtex1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               tdtex1.insertparafile(filenode);

               if(locked_type.compareTo("Analysis")==0)
                 setTDTEXblock(block, tdtex2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               tdtex1.insertpedigreefile(filenode);
               setTDTEXblock(block, tdtex2);
               if(locked_type.compareTo("Analysis")==0)
                 setTDTEXblock(block, tdtex2);

             }
           }
         };
         SwingUtilities.invokeLater(setinfo);

     }

     inframe.tdtex_count++;
     return tdtex;
  }

  public IconNode CreateDECIPHER(String analysis_name, String analysis_block,
                              Element analysis_element, MyInternalFrame inframe,
                              DataCollectionModel dm_input)
  {
    final String locked_type = analysis_element.getAttributeValue("locked");

    final String block = analysis_block;
    String blockname = analysis_name;
    final DataCollectionModel dm = dm_input;

    sage_analysis_info ai = new sage_analysis_info("DECIPHER");
    NodeInfo deciphernode = new NodeInfo(blockname,locked_type,"DECIPHER" ,ai, block);
    IconNode decipher = new IconNode(deciphernode, locked_type);

    NodeInfo errornodeinfo = new NodeInfo("Errors", "ErrorFolder", "DECIPHER", ai, block);
    IconNode errorfolder = new IconNode(errornodeinfo, "ErrorFolder");
    inframe.treeModel.insertNodeInto(errorfolder, decipher, decipher.getChildCount());

    final DECIPHER1 decipher1 = new DECIPHER1(ai, decipher, errorfolder);
    final DECIPHER2 decipher2 = new DECIPHER2(decipher1);
    decipher1.OutputNameField.setText(blockname);

    if(locked_type.compareTo("Locked Analysis")==0)
      setDisableAllComponents(decipher2);

    deciphernode.component_vector.add(decipher1);
    deciphernode.component_vector.add(decipher2);
    deciphernode.component_name_vector.add("Files");
    deciphernode.component_name_vector.add("Analysis Definition");

    errornodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"DECIPHER", ai, block);
    IconNode error1 = new IconNode(errornodeinfo, "ErrorPedigreeNode");
    inframe.treeModel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    decipher1.errorpedigree_node = error1;

    Element inputfolder = analysis_element.getChild("Input");
    List inputfile = inputfolder.getChildren();
    Iterator iterator = inputfile.iterator();
     while(iterator.hasNext())
     {
         Object child = iterator.next();
         Element infile_element = (Element) child;

         String filename = infile_element.getTextTrim();
         final String filetype = infile_element.getAttributeValue("type");
         String filepath = infile_element.getAttributeValue("path");

         final NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));

         Runnable setinfo = new Runnable() {
           public void run() {
             if (filetype.compareTo("Parameter File") == 0) {
               filenode.infomodel = dm;
               decipher1.insertparafile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setDECIPHERblock(block, decipher2);
             }
             if (filetype.compareTo("Pedigree File") == 0) {
               decipher1.insertpedigreefile(filenode);
               if(locked_type.compareTo("Analysis")==0)
                 setDECIPHERblock(block, decipher2);
             }
             if (filetype.compareTo("Marker Locus File") == 0) {
               decipher1.insertlocusfile(filenode);
             }
             if (filetype.compareTo("Genome File") == 0) {
               decipher1.insertgenomefile(filenode);
             }
           }
         };
         SwingUtilities.invokeLater(setinfo);
     }
     inframe.decipher_count++;
     return decipher;
  }

  public void setJobsNode(Element e, final MyInternalFrame frame, DataCollectionModel dm)
  {
    IconNode job = frame.jobnode;
    Element element = e;
    List children = element.getContent();
    Iterator iterator = children.iterator();

    int total = children.size();
    if(total==0)
      total=1;
    int each_step = (int)(60/total);
    int progess = 40;
    while(iterator.hasNext())
    {
      Object child = iterator.next();
      Element job_element = (Element) child;

      final IconNode analysisnode = CreateAnaysisNode(job_element, job, frame, dm);
      if (analysisnode != null)
      {
          frame.treeModel.insertNodeInto(analysisnode, job, job.getChildCount());

        TreePath p = new TreePath(analysisnode.getPath());
        frame.MyTree1.collapsePath(p);

      }
      progess = progess + each_step;
      SetProgress(progess, true);
    }
  }

  public void setInternalNode(Element e,MyInternalFrame frame) throws Exception
  {
    final IconNode internal = frame.internalnode;
    Element element = e;
    List children = element.getContent();
    Iterator iterator = children.iterator();
    while(iterator.hasNext())
    {
        Object child = iterator.next();
        Element internal_element = (Element) child;

        String filename = internal_element.getTextTrim();
        String filetype = internal_element.getAttributeValue("type");
        String filepath = internal_element.getAttributeValue("path");
        File temp = new File(filepath);

        NodeInfo filenode = new NodeInfo(filename, filetype, temp);
        IconNode file = new IconNode(filenode, filetype);
        if(filetype.compareTo("Parameter File")==0)
        {
            try{
                FavoritesPanel.Parse_Parameter_File(filenode);
                FavoritesPanel.Parse_Parameter_File2(filenode);
                FavoritesPanel.Parse_Parameter_File3(filenode);
                frame.parameterfilenode = file;

                frame.init_lastModified = temp.lastModified();

            }catch(FileNotFoundException nf)
            {
                throw nf;
            }
            catch(Exception ex)
            {
                throw ex;
            }
            finally
            {
            }
        }
        frame.treeModel.insertNodeInto(file, internal, internal.getChildCount());
    }
  }

  public void setExternalNode(Element e, MyInternalFrame frame)
  {
    final IconNode external = frame.externalnode;
    Element element = e;
    List children = element.getContent();
    Iterator iterator = children.iterator();
    while(iterator.hasNext())
    {
        Object child = iterator.next();
        Element external_element = (Element) child;

        String filename = external_element.getTextTrim();
        String filetype = external_element.getAttributeValue("type");
        String filepath = external_element.getAttributeValue("path");

        NodeInfo filenode = new NodeInfo(filename, filetype, new File(filepath));
        IconNode file = new IconNode(filenode, filetype);
        frame.treeModel.insertNodeInto(file, external, external.getChildCount());
    }
  }

  public MyInternalFrame getRootNode(Document o)
  {
    MyInternalFrame frame=null;
    String projectname = new String();
    String projectpath = new String();

    Document doc = (Document) o;
    List children = doc.getContent();
    Iterator iterator = children.iterator();
    while (iterator.hasNext()) {
      Object child = iterator.next();
      Element project = (Element) child;

      projectname = project.getTextTrim();
      projectpath = project.getAttributeValue("path");

      DataCollectionModel datamodel = new PropertyDataModel();
      datamodel.setValue("info.DirPath", projectpath);

      frame = new MyInternalFrame(projectname, desktop, datamodel, this);
      frame.projectPath = projectpath;
    }

    IconNode parent = frame.rootnode;

    NodeInfo projectnode = new NodeInfo(projectname, "Project", "Project", projectpath);
    IconNode project = new IconNode(projectnode, "Project");
    frame.treeModel.insertNodeInto(project, parent, parent.getChildCount());

    NodeInfo datanode = new NodeInfo("Data", "Data", "Data", "Data");
    IconNode data = new IconNode(datanode, "Data");
    NodeInfo internalnode = new NodeInfo("Internal", "Internal", "Internal", "Internal");
    IconNode internal = new IconNode(internalnode, "Internal");
    NodeInfo externalnode = new NodeInfo("External", "External", "External", "External");
    IconNode external = new IconNode(externalnode, "External");
    frame.treeModel.insertNodeInto(data, project,project.getChildCount());
    frame.treeModel.insertNodeInto(external, data, data.getChildCount());
    frame.treeModel.insertNodeInto(internal, data, data.getChildCount());

    NodeInfo jobnode = new NodeInfo("Jobs", "Jobs" , "Jobs", "Jobs");
    IconNode job = new IconNode(jobnode, "Jobs");
    frame.treeModel.insertNodeInto(job, project, project.getChildCount());

    frame.projectnode = project;
    frame.internalnode = internal;
    frame.externalnode = external;
    frame.jobnode = job;

    return frame;
  }

  void SetProgress(int progress, boolean dialog)
  {
    final int value = progress;
    Runnable r = new Runnable()
    {
      public void run()
      {
        setProgressValue(value);
      }
    };
    SwingUtilities.invokeLater(r);
  }

  void setProgressValue(int val)
  {
      cd.jProgressBar1.setValue(val);
  }



  void jMenuFREQ_actionPerformed() {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="FREQ"+activeinframe.freq_count;
    sage_analysis_info fi = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("freq, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo freqnode = new NodeInfo(analysisname,"Analysis","FREQ" ,fi, empty_block.toString());
    IconNode freq = new IconNode(freqnode, "Analysis");
    treemodel.insertNodeInto(freq, parent, 0);

    TreePath p = new TreePath(freq.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","FREQ", fi, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, freq, freq.getChildCount());

    fi.count_info_of_all = activeinframe.freq_count;
    activeinframe.freq_count++;

    FREQ1 freq1 = new FREQ1(fi, freq, errorfolder);
    FREQ2 freq2 = new FREQ2(freq1);
    freq1.OutputNameField.setText(analysisname);

    freqnode.component_vector.add(freq1);
    freqnode.component_vector.add(freq2);

    freqnode.component_name_vector.add("Files");
    freqnode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"FREQ", fi, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    freq1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    freq1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(freq.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }


  void jMenuGENIBD_actionPerformed() {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="GENIBD"+activeinframe.genibd_count;
    sage_analysis_info gi = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("genibd, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo genibdnode = new NodeInfo(analysisname,"Analysis","GENIBD" ,gi, empty_block.toString());
    IconNode genibd = new IconNode(genibdnode, "Analysis");
    treemodel.insertNodeInto(genibd, parent, 0);

    TreePath p = new TreePath(genibd.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","GENIBD", gi, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, genibd, genibd.getChildCount());

    gi.count_info_of_all = activeinframe.genibd_count;
    activeinframe.genibd_count++;

    GENIBD1 genibd1 = new GENIBD1(gi, genibd, errorfolder);
    GENIBD2 genibd2 = new GENIBD2(genibd1);
    genibd1.OutputNameField.setText(analysisname);

    genibdnode.component_vector.add(genibd1);
    genibdnode.component_vector.add(genibd2);

    genibdnode.component_name_vector.add("Files");
    genibdnode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"GENIBD", gi, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    genibd1.errorpedigree_node = error1;

    nodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode" ,"GENIBD", gi, "");
    error1 = new IconNode(nodeinfo, "ErrorLocusNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    genibd1.errorlocus_node = error1;

    nodeinfo = new NodeInfo("Genome File to the multipoint Missing", "ErrorGenomeNode" ,"GENIBD", gi, "");
    error1 = new IconNode(nodeinfo, "ErrorGenomeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    genibd1.errorgenome_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    genibd1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(genibd.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuLODPAL_actionPerformed(){
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="LODPAL"+activeinframe.lodpal_count;
    sage_analysis_info li = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("lodpal, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo lodpalnode = new NodeInfo(analysisname,"Analysis","LODPAL" ,li, empty_block.toString());
    IconNode lodpal = new IconNode(lodpalnode, "Analysis");
    treemodel.insertNodeInto(lodpal, parent, 0);

    TreePath p = new TreePath(lodpal.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","LODPAL", li, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, lodpal, lodpal.getChildCount());

    li.count_info_of_all = activeinframe.lodpal_count;
    activeinframe.lodpal_count++;

    LODPAL1 lodpal1 = new LODPAL1(li, lodpal, errorfolder);
    LODPAL2 lodpal2 = new LODPAL2(lodpal1);
    lodpal1.OutputNameField.setText(analysisname);

    lodpalnode.component_vector.add(lodpal1);
    lodpalnode.component_vector.add(lodpal2);

    lodpalnode.component_name_vector.add("Files");
    lodpalnode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"LODPAL", li, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    lodpal1.errorpedigree_node = error1;

    nodeinfo = new NodeInfo("IBD File Missing", "ErrorIBDNode","LODPAL", li, "");
    error1 = new IconNode(nodeinfo, "ErrorIBDNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    lodpal1.errorlocus_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    lodpal1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(lodpal.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }


  IconNode makeRELPALNode(String analysisname, String analysis_block)
  {

      sage_analysis_info ri = new sage_analysis_info(analysisname);

      DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();

      NodeInfo relpalnode = new NodeInfo(analysisname,"Analysis","RELPAL" ,ri, analysis_block);
      IconNode relpal = new IconNode(relpalnode, "Analysis");

      NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","RELPAL", ri, "");
      IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
      treemodel.insertNodeInto(errorfolder, relpal, relpal.getChildCount());
      ri.count_info_of_all = activeinframe.relpal_count;
      activeinframe.relpal_count++;

      RELPAL1 relpal1 = new RELPAL1(ri, relpal, errorfolder);
      RELPAL2 relpal2 = new RELPAL2(relpal1);
      relpal1.OutputNameField.setText(analysisname);

      relpalnode.component_vector.add(relpal1);
      relpalnode.component_vector.add(relpal2);

      relpalnode.component_name_vector.add("Files");
      relpalnode.component_name_vector.add("Analysis Definition");


      nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"LODPAL", ri, "");
      IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
      treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
      relpal1.errorpedigree_node = error1;


      return relpal;
  }

  void jMenuRELPAL_actionPerformed()
  {
      String master_information = new String();
      IconNode Parameter_node = activeinframe.parameterfilenode;
      NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();
      DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

      if (dm.hasValue("Master_Information"))
          master_information = dm.getValue("Master_Information").toString();

      String analysisname = "RELPAL" + activeinframe.relpal_count;
      StringBuffer empty_block = new StringBuffer();
      empty_block.append("relpal, out = \"" + analysisname + "\"" + "\n");
      empty_block.append("{" + "\n");
      empty_block.append("}" + "\n");

      IconNode relpal = makeRELPALNode(analysisname, empty_block.toString());
      NodeInfo relpalnode = (NodeInfo)relpal.getUserObject();
      RELPAL1 relpal1 = (RELPAL1)relpalnode.component_vector.get(0);

      String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();
      File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
      try{
        FileWriter fos = new FileWriter(analysis_block_file);
        fos.write(master_information.toString());
        fos.write("\n");
        fos.write(empty_block.toString());
        fos.close();
      }catch(Exception ex)
      {
        ex.printStackTrace();
      }
      NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
      parafilenode.infomodel = dm;
      relpal1.insertparafile(parafilenode);

      DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
      IconNode parent = activeinframe.jobnode;
      treemodel.insertNodeInto(relpal, parent, 0);


      TreePath p = new TreePath(relpal.getPath());
      activeinframe.MyTree1.setSelectionPath(p);
      p = new TreePath(relpal.getPath());
      activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuAGEON_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="AGEON"+activeinframe.ageon_count;

    sage_analysis_info ai = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("ageon, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo ageonnode = new NodeInfo(analysisname,"Analysis","AGEON" ,ai, empty_block.toString());
    IconNode ageon = new IconNode(ageonnode, "Analysis");
    treemodel.insertNodeInto(ageon, parent, 0);

    TreePath p = new TreePath(ageon.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","AGEON", ai, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, ageon, ageon.getChildCount());

    ai.count_info_of_all = activeinframe.ageon_count;
    activeinframe.ageon_count++;

    AGEON1 ageon1 = new AGEON1(ai, ageon, errorfolder);
    AGEON2 ageon2 = new AGEON2(ageon1);
    ageon1.OutputNameField.setText(analysisname);

    ageonnode.component_vector.add(ageon1);
    ageonnode.component_vector.add(ageon2);

    ageonnode.component_name_vector.add("Files");
    ageonnode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"AGEON", ai, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    ageon1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    ageon1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(ageon.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuASSOC_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="ASSOC"+activeinframe.assoc_count;
    sage_analysis_info ai = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("assoc, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo assocnode = new NodeInfo(analysisname,"Analysis","ASSOC" ,ai, empty_block.toString());
    IconNode assoc = new IconNode(assocnode, "Analysis");
    treemodel.insertNodeInto(assoc, parent, 0);

    TreePath p = new TreePath(assoc.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","ASSOC", ai, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, assoc, assoc.getChildCount());

    ai.count_info_of_all = activeinframe.assoc_count;
    activeinframe.assoc_count++;

    ASSOC1 assoc1 = new ASSOC1(ai, assoc, errorfolder);
    ASSOC2 assoc2 = new ASSOC2(assoc1);
    assoc1.OutputNameField.setText(analysisname);

    assocnode.component_vector.add(assoc1);
    assocnode.component_vector.add(assoc2);

    assocnode.component_name_vector.add("Files");
    assocnode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"ASSOC", ai, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    assoc1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    assoc1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(assoc.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuPEDINFO_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    sage_analysis_info pi = new sage_analysis_info("PEDINFO"+activeinframe.pedinfo_count);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("pedinfo, out = \""+pi.analysis_name+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo pedinfonode = new NodeInfo("PEDINFO" + activeinframe.pedinfo_count, "Analysis","PEDINFO", pi, empty_block.toString());
    IconNode pedinfo = new IconNode(pedinfonode, "Analysis");
    treemodel.insertNodeInto(pedinfo, parent, 0);

    TreePath p = new TreePath(pedinfo.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder", "PEDINFO", pi,"");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, pedinfo,pedinfo.getChildCount());

    pi.count_info_of_all = activeinframe.pedinfo_count;
    activeinframe.pedinfo_count++;

    String analysisname=pedinfonode.nodename;

    PEDINFO1 pedinfo1 = new PEDINFO1(pi, pedinfo, errorfolder);
    PEDINFO2 pedinfo2 = new PEDINFO2(pedinfo1);
    pedinfo1.OutputNameField.setText(analysisname);

    pedinfonode.component_vector.add(pedinfo1);
    pedinfonode.component_vector.add(pedinfo2);

    pedinfonode.component_name_vector.add("Files");
    pedinfonode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","PEDINFO",pi, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    pedinfo1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    pedinfo1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(pedinfo.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuMARKERINFO_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="MARKERINFO"+activeinframe.markerinfo_count;

    sage_analysis_info mi = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("markerinfo, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo markerinfonode = new NodeInfo(analysisname, "Analysis","MARKERINFO", mi, empty_block.toString());
    IconNode markerinfo = new IconNode(markerinfonode, "Analysis");
    treemodel.insertNodeInto(markerinfo, parent, 0);

    TreePath p = new TreePath(markerinfo.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder", "MARKERINFO", mi, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, markerinfo,markerinfo.getChildCount());

    mi.count_info_of_all = activeinframe.markerinfo_count;
    activeinframe.markerinfo_count++;

    MARKERINFO1 markerinfo1 = new MARKERINFO1(mi, markerinfo, errorfolder);
    MARKERINFO2 markerinfo2 = new MARKERINFO2(markerinfo1);
    markerinfo1.OutputNameField.setText(analysisname);

    markerinfonode.component_vector.add(markerinfo1);
    markerinfonode.component_vector.add(markerinfo2);

    markerinfonode.component_name_vector.add("Files");
    markerinfonode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","MARKERINFO", mi, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    markerinfo1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    markerinfo1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(markerinfo.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

  }

  void jMenuRELTEST_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="RELTEST"+activeinframe.reltest_count;
    sage_analysis_info ri = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("reltest, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo reltestnode = new NodeInfo(analysisname,"Analysis","RELTEST" ,ri, empty_block.toString());
    IconNode reltest = new IconNode(reltestnode, "Analysis");
    treemodel.insertNodeInto(reltest, parent, 0);

    TreePath p = new TreePath(reltest.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","RELTEST", ri, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, reltest, reltest.getChildCount());

    ri.count_info_of_all = activeinframe.reltest_count;
    activeinframe.reltest_count++;

    RELTEST1 reltest1 = new RELTEST1(ri, reltest, errorfolder);
    RELTEST2 reltest2 = new RELTEST2(reltest1);
    reltest1.OutputNameField.setText(analysisname);

    reltestnode.component_vector.add(reltest1);
    reltestnode.component_vector.add(reltest2);

    reltestnode.component_name_vector.add("Files");
    reltestnode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"RELTEST", ri, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    reltest1.errorpedigree_node = error1;

    nodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","RELTEST", ri, "");
    error1 = new IconNode(nodeinfo, "ErrorLocusNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    reltest1.errorlocus_node = error1;

    nodeinfo = new NodeInfo("Genome File Missing", "ErrorGenomeNode","RELTEST", ri, "");
    error1 = new IconNode(nodeinfo, "ErrorGenomeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    reltest1.errorgenome_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    reltest1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(reltest.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuFCOR_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="FCOR"+activeinframe.fcor_count;

    sage_analysis_info fi = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("fcor, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo fcornode = new NodeInfo(analysisname, "Analysis","FCOR", fi, empty_block.toString());
    IconNode fcor = new IconNode(fcornode, "Analysis");
    treemodel.insertNodeInto(fcor, parent, 0);

    TreePath p = new TreePath(fcor.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder", "FCOR", fi, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, fcor,fcor.getChildCount());

    fi.count_info_of_all = activeinframe.fcor_count;
    activeinframe.fcor_count++;

    FCOR1 fcor1 = new FCOR1(fi, fcor, errorfolder);
    FCOR2 fcor2 = new FCOR2(fcor1);
    fcor1.OutputNameField.setText(analysisname);

    fcornode.component_vector.add(fcor1);
    fcornode.component_vector.add(fcor2);

    fcornode.component_name_vector.add("Files");
    fcornode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","FCOR", fi, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    fcor1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    fcor1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(fcor.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuSEGREG_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel) activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="SEGREG" + activeinframe.segreg_count;
    sage_analysis_info si = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("segreg, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo segregnode = new NodeInfo(analysisname, "Analysis","SEGREG", si, empty_block.toString());
    IconNode segreg = new IconNode(segregnode, "Analysis");
    treemodel.insertNodeInto(segreg, parent, 0);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder", "SEGREG", si, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, segreg, segreg.getChildCount());

    si.count_info_of_all = activeinframe.segreg_count;
    activeinframe.segreg_count++;

    SEGREG1 segreg1 = new SEGREG1(si, segreg, errorfolder);
    SEGREG2 segreg2 = new SEGREG2(segreg1);
    SEGREG3 segreg3 = new SEGREG3(segreg2);
    SEGREG4 segreg4 = new SEGREG4(segreg2);
    SEGREG5 segreg5 = new SEGREG5();
    segreg1.OutputNameField.setText(analysisname);

    segregnode.component_vector.add(segreg1);
    segregnode.component_vector.add(segreg2);
    segregnode.component_vector.add(segreg3);
    segregnode.component_vector.add(segreg4);
    segregnode.component_vector.add(segreg5);

    segregnode.component_name_vector.add("Files");
    segregnode.component_name_vector.add("Analysis Definition");
    segregnode.component_name_vector.add("Quantitative");
    segregnode.component_name_vector.add("Binary");
    segregnode.component_name_vector.add("Binary with variable age of onset");

    segreg2.setSEGREGpanel(segreg2.Default);

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    NodeInfo nodeinfo2 = new NodeInfo("Data File Missing", "ErrorPedigreeNode","SEGREG", si, "");
    IconNode error1 = new IconNode(nodeinfo2, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    segreg1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    segreg1.insertparafile(parafilenode);

    TreePath p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(segreg.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuMLOD_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();
    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="MLOD"+activeinframe.mlod_count;
    sage_analysis_info ri = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("mlod, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo mlodnode = new NodeInfo(analysisname,"Analysis","MLOD" ,ri, empty_block.toString());
    IconNode mlod = new IconNode(mlodnode, "Analysis");
    treemodel.insertNodeInto(mlod, parent, 0);

    TreePath p = new TreePath(mlod.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","MLOD", ri, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, mlod, mlod.getChildCount());

    ri.count_info_of_all = activeinframe.mlod_count;
    activeinframe.mlod_count++;

    MLOD1 mlod1 = new MLOD1(ri, mlod, errorfolder);
    MLOD2 mlod2 = new MLOD2(mlod1);
    mlod1.OutputNameField.setText(analysisname);

    mlodnode.component_vector.add(mlod1);
    mlodnode.component_vector.add(mlod2);

    mlodnode.component_name_vector.add("Files");
    mlodnode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"MLOD", ri, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    mlod1.errorpedigree_node = error1;

    nodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","MLOD", ri, "");
    error1 = new IconNode(nodeinfo, "ErrorLocusNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    mlod1.errorlocus_node = error1;

    nodeinfo = new NodeInfo("Genome File Missing", "ErrorGenomeNode","MLOD", ri, "");
    error1 = new IconNode(nodeinfo, "ErrorGenomeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    mlod1.errorgenome_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    mlod1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(mlod.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuLODLINK_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="LODLINK"+activeinframe.lodlink_count;
    sage_analysis_info ai = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("lodlink, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo lodlinknode = new NodeInfo(analysisname,"Analysis","LODLINK" ,ai, empty_block.toString());
    IconNode lodlink = new IconNode(lodlinknode, "Analysis");
    treemodel.insertNodeInto(lodlink, parent, 0);

    TreePath p = new TreePath(lodlink.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","LODLINK", ai, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, lodlink, lodlink.getChildCount());

    ai.count_info_of_all = activeinframe.lodlink_count;
    activeinframe.lodlink_count++;

    LODLINK1 lodlink1 = new LODLINK1(ai, lodlink, errorfolder);
    LODLINK2 lodlink2 = new LODLINK2(lodlink1);
    lodlink1.OutputNameField.setText(analysisname);

    lodlinknode.component_vector.add(lodlink1);
    lodlinknode.component_vector.add(lodlink2);

    lodlinknode.component_name_vector.add("Files");
    lodlinknode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"LODLINK", ai, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    lodlink1.errorpedigree_node = error1;

    nodeinfo = new NodeInfo("Marker Locus File Missing", "ErrorLocusNode","LODLINK", ai, "");
    error1 = new IconNode(nodeinfo, "ErrorLocusNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    lodlink1.errorlocus_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    lodlink1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(lodlink.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuSIBPAL_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="SIBPAL"+activeinframe.sibpal_count;
    sage_analysis_info si = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("sibpal, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo sibpalnode = new NodeInfo(analysisname,"Analysis","SIBPAL" ,si, empty_block.toString());
    IconNode sibpal = new IconNode(sibpalnode,"Analysis");
    treemodel.insertNodeInto(sibpal, parent, 0);

    TreePath p = new TreePath(sibpal.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","SIBPAL", si, "");
    IconNode errorfolder = new IconNode(nodeinfo,"ErrorFolder");
    treemodel.insertNodeInto(errorfolder, sibpal, sibpal.getChildCount());

    si.count_info_of_all = activeinframe.sibpal_count;
    activeinframe.sibpal_count++;

    SIBPAL1 sibpal1 = new SIBPAL1(si, sibpal, errorfolder);
    SIBPAL2 sibpal2 = new SIBPAL2(sibpal1);
    SIBPAL3 sibpal3 = new SIBPAL3(sibpal1);
    SIBPAL4 sibpal4 = new SIBPAL4(sibpal1);
    sibpal1.OutputNameField.setText(analysisname);

    sibpalnode.component_vector.add(sibpal1);
    sibpalnode.component_vector.add(sibpal2);
    sibpalnode.component_vector.add(sibpal3);
    sibpalnode.component_vector.add(sibpal4);

    sibpalnode.component_name_vector.add("Files");
    sibpalnode.component_name_vector.add("Analysis Definition");
    sibpalnode.component_name_vector.add("Mean Tests");
    sibpalnode.component_name_vector.add("Trait Regression");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","SIBPAL", si, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    sibpal1.errorpedigree_node = error1;

    nodeinfo = new NodeInfo("IBD File Missing", "ErrorIBDNode", "SIBPAL", si, "");
    error1 = new IconNode(nodeinfo, "ErrorIBDNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    sibpal1.errorlocus_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    sibpal1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(sibpal.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuTDTEX_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="TDTEX"+activeinframe.tdtex_count;
    sage_analysis_info pi = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("tdtex, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo tdtexnode = new NodeInfo(analysisname, "Analysis","TDTEX", pi, empty_block.toString());
    IconNode tdtex = new IconNode(tdtexnode,"Analysis");
    treemodel.insertNodeInto(tdtex, parent, 0);

    TreePath p = new TreePath(tdtex.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder", "TDTEX", pi, "");
    IconNode errorfolder = new IconNode(nodeinfo,"ErrorFolder");
    treemodel.insertNodeInto(errorfolder, tdtex,tdtex.getChildCount());

    pi.count_info_of_all = activeinframe.tdtex_count;
    activeinframe.tdtex_count++;

    TDTEX1 tdtex1 = new TDTEX1(pi, tdtex, errorfolder);
    TDTEX2 tdtex2 = new TDTEX2(tdtex1);
    tdtex1.OutputNameField.setText(analysisname);

    tdtexnode.component_vector.add(tdtex1);
    tdtexnode.component_vector.add(tdtex2);

    tdtexnode.component_name_vector.add("Files");
    tdtexnode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }
    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode","TDTEX", pi, "");
    IconNode error1 = new IconNode(nodeinfo,"ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    tdtex1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    tdtex1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(tdtex.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  void jMenuDECIPHER_actionPerformed()
  {
    String master_information = new String();
    IconNode Parameter_node = activeinframe.parameterfilenode;

    NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();

    DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

    if (dm.hasValue("Master_Information"))
      master_information = dm.getValue("Master_Information").toString();

    DefaultTreeModel treemodel = (DefaultTreeModel)activeinframe.MyTree1.getModel();
    IconNode parent = activeinframe.jobnode;

    String analysisname="DECIPHER"+activeinframe.decipher_count;
    sage_analysis_info gi = new sage_analysis_info(analysisname);

    StringBuffer empty_block=new StringBuffer();
    empty_block.append("decipher, out = \""+analysisname+"\""+"\n");
    empty_block.append("{"+"\n");
    empty_block.append("}"+"\n");

    NodeInfo deciphernode = new NodeInfo(analysisname,"Analysis","DECIPHER" ,gi, empty_block.toString());
    IconNode decipher = new IconNode(deciphernode, "Analysis");
    treemodel.insertNodeInto(decipher, parent, 0);

    TreePath p = new TreePath(decipher.getPath());
    activeinframe.MyTree1.setSelectionPath(p);

    NodeInfo nodeinfo = new NodeInfo("Errors", "ErrorFolder","DECIPHER", gi, "");
    IconNode errorfolder = new IconNode(nodeinfo, "ErrorFolder");
    treemodel.insertNodeInto(errorfolder, decipher, decipher.getChildCount());

    gi.count_info_of_all = activeinframe.decipher_count;
    activeinframe.decipher_count++;

    DECIPHER1 decipher1 = new DECIPHER1(gi, decipher, errorfolder);
    DECIPHER2 decipher2 = new DECIPHER2(decipher1);
    decipher1.OutputNameField.setText(analysisname);

    deciphernode.component_vector.add(decipher1);
    deciphernode.component_vector.add(decipher2);

    deciphernode.component_name_vector.add("Files");
    deciphernode.component_name_vector.add("Analysis Definition");

    String path = activeinframe.projectdatamodel.getValue("info.DirPath").toString();

    File analysis_block_file = new File(path+System.getProperty("file.separator")+analysisname+".par");
    try{
      FileWriter fos = new FileWriter(analysis_block_file);
      fos.write(master_information.toString());
      fos.write("\n");
      fos.write(empty_block.toString());
      fos.close();
    }catch(Exception ex)
    {
      ex.printStackTrace();
    }

    nodeinfo = new NodeInfo("Data File Missing", "ErrorPedigreeNode" ,"DECIPHER", gi, "");
    IconNode error1 = new IconNode(nodeinfo, "ErrorPedigreeNode");
    treemodel.insertNodeInto(error1, errorfolder, errorfolder.getChildCount());
    decipher1.errorpedigree_node = error1;

    NodeInfo parafilenode = new NodeInfo(analysis_block_file.getName(),"Parameter File", analysis_block_file);
    parafilenode.infomodel = dm;
    decipher1.insertparafile(parafilenode);

    p = new TreePath(error1.getPath());
    activeinframe.MyTree1.scrollPathToVisible(p);
    p = new TreePath(decipher.getPath());
    activeinframe.MyTree1.setSelectionPath(p);
  }

  public void jMenuFunctionBlock_actionPerformed()
  {
    IconNode parafilenode = activeinframe.parameterfilenode;

    if(parafilenode == null)
    {
      JOptionPane.showOptionDialog(this,
                                           "You need a parameter file to define the function block.",
                                          "Warning Message", // title
                                          JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
      return;
    }
    else
    {
      Vector List_array = new Vector();
      NodeInfo nodeinfo = (NodeInfo) parafilenode.getUserObject();
      DataCollectionModel fileinfo = nodeinfo.infomodel;
      if (fileinfo.hasValue("Trait_array")) {
        Vector temp2 = (Vector) fileinfo.getValue("Trait_array");
        FunctionDialog3.traitList = temp2;
        for (Enumeration es = temp2.elements(); es.hasMoreElements(); ) {
          List_array.add(es.nextElement().toString());
        }
      }
      if (fileinfo.hasValue("Covariate_array")) {
        Vector temp2 = (Vector) fileinfo.getValue("Covariate_array");
        FunctionDialog3.covList = temp2;
        for (Enumeration es = temp2.elements(); es.hasMoreElements(); ) {
          List_array.add(es.nextElement().toString());
        }
      }
      if (fileinfo.hasValue("Phenotype_array")) {
        Vector temp2 = (Vector) fileinfo.getValue("Phenotype_array");
        FunctionDialog3.phenoList = temp2;
        for (Enumeration es = temp2.elements(); es.hasMoreElements(); ) {
          List_array.add(es.nextElement().toString());
        }
      }
      if (fileinfo.hasValue("Marker_array")) {
        Vector temp2 = (Vector) fileinfo.getValue("Marker_array");
        FunctionDialog3.markerList = temp2;
        for (Enumeration es = temp2.elements(); es.hasMoreElements(); ) {
          List_array.add(es.nextElement().toString());
        }
      }
      if (fileinfo.hasValue("Allele_array")) {
        Vector temp2 = (Vector) fileinfo.getValue("Allele_array");
        FunctionDialog3.alleleList = temp2;
        for (Enumeration es = temp2.elements(); es.hasMoreElements(); ) {
          List_array.add(es.nextElement().toString());
        }
      }

      String filepath = nodeinfo.file.getPath();

      Vector list = new Vector();
      list = FunctionDialog1.getFunctionBlockList(filepath);
      FunctionDialog3.AllList = List_array;

      fdialog1.v_data.removeAllElements();

      for (int i = 0; i < list.size(); i++) {
        fdialog1.v_data.addElement(list.get(i));
      }

      fdialog1.setVisible(true);
    }
  }

  void UpdateParaFilesInfo()
  {
    NodeInfo parafilenode = (NodeInfo)activeinframe.parameterfilenode.getUserObject();
    DataCollectionModel fileinfomodel = parafilenode.infomodel;

    StringBuffer master_information=new StringBuffer();

    if (fileinfomodel.hasValue("Pedigree_array")) {
      Vector pedigree = (Vector) fileinfomodel.getValue("Pedigree_array");
      for (int i = 0; i < pedigree.size(); i++) {
        String block = (String) pedigree.get(i);
        master_information.append(block);
      }
    }

    if (fileinfomodel.hasValue("MarkerBlock_array")) {
      Vector marker = (Vector) fileinfomodel.getValue("MarkerBlock_array");
      for (int i = 0; i < marker.size(); i++) {
        String block = (String) marker.get(i);
        master_information.append(block);
      }
    }

    if (fileinfomodel.hasValue("Function_array")) {
      Vector function = (Vector) fileinfomodel.getValue("Function_array");
      for (int i = 0; i < function.size(); i++) {
        String block = (String) function.get(i);
        master_information.append(block);
      }
    }
    fileinfomodel.setValue("Master_Information", master_information.toString());

    for (Enumeration em = activeinframe.jobnode.children(); em.hasMoreElements(); ) {
      IconNode temp = (IconNode) em.nextElement();
      NodeInfo nodeinfo = (NodeInfo) temp.getUserObject();
      nodeinfo.infomodel = fileinfomodel;

      if (nodeinfo.nodetype.compareTo("Analysis") == 0) {

        SageFilePanel test1 = (SageFilePanel) nodeinfo.component_vector.get(0);
        test1.SetPanel2Info(nodeinfo);

        NodeInfo originalnode = (NodeInfo)test1.para_node.getUserObject();
        File beforefile = originalnode.file;

        File newfile = new File(beforefile.getPath());
        try {
          FileWriter fos = new FileWriter(newfile);
          fos.write(master_information.toString());
          fos.write("\n");
          fos.write(nodeinfo.analysis_block);
          fos.write("\n");
          fos.close();
        }
        catch (Exception exc) {
          exc.printStackTrace();
        }
      }
    }
  }

  public int Find_EndBlock_Index(String startblock, String matchstring) {

    boolean block = false;

    String temp_block = new String();

    String strFileLine_original = new String();
    String strFileLine_temp = new String();

    int block_count = 0;
    boolean block_count_start = false;

    int start_index = -1;
    int block_length=-1;
    int end_index=-1;

    String para_array[] = startblock.split("\n");

    for(int in=0;in<para_array.length;in++)
    {
      strFileLine_original = para_array[in];
      strFileLine_temp = strFileLine_original.toLowerCase().trim();

      if (strFileLine_temp.startsWith("#")) {
        if(!block)
        {
          if(in+1 < para_array.length)
          {
            strFileLine_original = para_array[++in];
            strFileLine_temp = strFileLine_original.toLowerCase().trim();
          }
        }
      }

      if (strFileLine_temp.startsWith(matchstring)) {
        if (matchstring.compareTo("pedigree") == 0) {
          if (strFileLine_temp.startsWith("pedigree_id") == false) {
            block = true;
            start_index = startblock.indexOf(matchstring);
          }
        }
        else
        {
          block = true;
          start_index = startblock.indexOf(matchstring);
        }
      }

      if (block ){
        temp_block = temp_block + strFileLine_original + "\n";
        if (strFileLine_temp.indexOf("{") >= 0) {
          block_count_start = true;
          block_count++;
        }

        if (strFileLine_temp.indexOf("}") >= 0) {
          block_count--;
        }

        if (block_count_start && block_count == 0) {
          block = false;
          block_count_start = false;
          block_length=temp_block.length();
          temp_block = "";

          end_index = start_index + block_length;
          if(start_index >= 0 && end_index > start_index)
          {
            StringBuffer s = new StringBuffer(startblock);
            s.delete(start_index, end_index);
            startblock = s.toString();
          }
        }
      }
    }

    return end_index;
  }

  void updateAnalysisPanel(String filepath)
  {
    TreePath s_path = activeinframe.MyTree1.getSelectionPath();
    IconNode select_node = (IconNode) s_path.getLastPathComponent();

    IconNode analysisnode = (IconNode)select_node.getParent().getParent();

    final NodeInfo cn = (NodeInfo) analysisnode.getUserObject();
    final String analysis_type = cn.analysis_type;
    final String analysis_block = getAnalysisBlock(filepath, analysis_type);
    final String blockname = getAnalysisName(analysis_block);
    Runnable setinfo = new Runnable() {
      public void run() {
        SageFilePanel filepanel = (SageFilePanel)(cn.component_vector.get(0));
        filepanel.OutputNameField.setText(blockname);

        if(analysis_type.compareTo("AGEON")==0)
        {
          AGEON2 ageon2 = (AGEON2)cn.component_vector.get(1);
          setAGEONblock(analysis_block, ageon2);
        }
        else if(analysis_type.compareTo("ASSOC")==0)
        {
          ASSOC2 assoc2 = (ASSOC2)cn.component_vector.get(1);
          setASSOCblock(analysis_block, assoc2);
        }
        else if (analysis_type.compareTo("FCOR") == 0)
        {
          FCOR2 fcor2 = (FCOR2)cn.component_vector.get(1);
          setFCORblock(analysis_block, fcor2);
        }
        else if (analysis_type.compareTo("FREQ") == 0)
        {
          FREQ2 freq2 = (FREQ2)cn.component_vector.get(1);
          setFREQblock(analysis_block, freq2);
        }
        else if (analysis_type.compareTo("GENIBD") == 0)
        {
          GENIBD2 genibd2 = (GENIBD2)cn.component_vector.get(1);
          setGENIBDblock(analysis_block, genibd2);
        }
        else if (analysis_type.compareTo("LODLINK") == 0)
        {
          LODLINK2 lodlink2 = (LODLINK2)cn.component_vector.get(1);
          setLODLINKblock(analysis_block, lodlink2);
        }
        else if(analysis_type.compareTo("LODPAL")==0)
        {
          LODPAL2 lodpal2 = (LODPAL2)cn.component_vector.get(1);
          setLODPALblock(analysis_block, lodpal2);
        }
        else if(analysis_type.compareTo("MARKERINFO")==0)
        {
          MARKERINFO2 markerinfo2 = (MARKERINFO2)cn.component_vector.get(1);
          setMARKERINFOblock(analysis_block, markerinfo2);
        }
        else if(analysis_type.compareTo("MLOD")==0)
        {
          MLOD2 mlod2 = (MLOD2)cn.component_vector.get(1);
          setMLODblock(analysis_block, mlod2);
        }
        else if (analysis_type.compareTo("PEDINFO")== 0)
        {
          PEDINFO2 pedinfo2 = (PEDINFO2)cn.component_vector.get(1);
          setPEDINFOblock(analysis_block, pedinfo2);
        }
        else if(analysis_type.compareTo("RELTEST")==0)
        {
          RELTEST2 reltest2 = (RELTEST2)cn.component_vector.get(1);
          setRELTESTblock(analysis_block, reltest2);
        }
        else if (analysis_type.compareTo("SEGREG")== 0)
        {
          SEGREG2 segreg2 = (SEGREG2)cn.component_vector.get(1);
          SEGREG3 segreg3 = (SEGREG3)cn.component_vector.get(2);
          SEGREG4 segreg4 = (SEGREG4)cn.component_vector.get(3);
          SEGREG5 segreg5 = (SEGREG5)cn.component_vector.get(4);

          NodeInfo paranode = (NodeInfo)activeinframe.parameterfilenode.getUserObject();
          DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;
          String master_information = new String();
          if (dm.hasValue("Master_Information")) {
            master_information = dm.getValue("Master_Information").toString();
          }

          int segreg_type = getSegregType(analysis_block, master_information);

          switch (segreg_type)
          {
            case 1:
              segreg2.QRadioButton.setSelected(true);
              setSEGREGblock_C(analysis_block, segreg2, segreg3, false);
              break;
            case 2:
              segreg2.BRadioButton.setSelected(true);
              setSEGREGblock_B(analysis_block, segreg2, segreg4, false);
              break;
            case 3:
              segreg2.BARadioButton.setSelected(true);
              setSEGREGblock_BA(analysis_block, segreg2, segreg5, false);
              break;
          }
        }
        else if(analysis_type.compareTo("SIBPAL")==0)
        {
          SIBPAL2 sibpal2 = (SIBPAL2)cn.component_vector.get(1);
          SIBPAL3 sibpal3 = (SIBPAL3)cn.component_vector.get(2);
          SIBPAL4 sibpal4 = (SIBPAL4)cn.component_vector.get(3);
          setSIBPALblock(analysis_block, sibpal2, sibpal3, sibpal4);
        }
        else if(analysis_type.compareTo("TDTEX")==0)
        {
          TDTEX2 tdtex2 = (TDTEX2)cn.component_vector.get(1);
          setTDTEXblock(analysis_block, tdtex2);
        }
        else if(analysis_type.compareTo("DECIPHER")==0)
        {
          DECIPHER2 decihper2 = (DECIPHER2)cn.component_vector.get(1);
          setDECIPHERblock(analysis_block, decihper2);
        }
      }
    };
    SwingUtilities.invokeLater(setinfo);
  }
}
