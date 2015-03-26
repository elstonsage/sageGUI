package sage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.util.*;
import javax.swing.Timer;
import java.text.*;
import javax.swing.text.*;

public class MyInternalFrame
    extends JInternalFrame
    implements ActionListener, TreeSelectionListener {
  static int freq_count = 1;
  static int genibd_count = 1;
  static int lodpal_count = 1;
  static int pedinfo_count = 1;
  static int markerinfo_count = 1;
  static int assoc_count = 1;
  static int reltest_count = 1;
  static int fcor_count = 1;
  static int lodlink_count = 1;
  static int tdtex_count = 1;
  static int mlod_count = 1;
  static int sibpal_count = 1;
  static int segreg_count = 1;
  static int ageon_count = 1;
  static int decipher_count = 1;
  static int relpal_count = 1;

  static int BinaryType = 1;
  static int QuantitativeType = 2;

  OutputStreamGobbler outputGobbler;
  ErrorStreamGobbler errorGobbler;

  public final static int ONE_SECOND = 1000;

  JDesktopPane desktop;
  Frame1 frame1;
  JList list;
  DataCollectionModel projectdatamodel;

  MyJobFrame jobframe;
  MyConsoleFrame consoleframe;

  JLabel TreeSelectionPath = new JLabel();

  DefaultListModel listModel = new DefaultListModel();

  IconNode externalnode;
  IconNode internalnode;
  IconNode jobnode;
  IconNode projectnode;
  IconNode parameterfilenode;

  NodeInfo nodeinfo;
  IconNode rootnode;
  MyTreeModel treeModel;
  MyTree MyTree1;

  DecimalFormat d = new DecimalFormat("00");

  JPopupMenu jPopupMenuPara = new JPopupMenu();
  JPopupMenu jPopupMenuPedigree = new JPopupMenu();
  JPopupMenu jPopupMenuLocus = new JPopupMenu();
  JPopupMenu jPopupMenuIBD = new JPopupMenu();
  JPopupMenu jPopupMenuGenome = new JPopupMenu();

  JPopupMenu jPopupMenuFile1 = new JPopupMenu();
  JPopupMenu jPopupMenuFile2 = new JPopupMenu();
  JPopupMenu jPopupMenuFile3 = new JPopupMenu();
  JPopupMenu jPopupMenuFile4 = new JPopupMenu();
  JPopupMenu jPopupMenuFile5 = new JPopupMenu();

  // for parameter file
  JMenuItem jOpenFileMenuItem1 = new JMenuItem("Open");
  JMenuItem jRenameFileMenuItem1 = new JMenuItem("Rename");
  JMenuItem jModifyFileMenuItem1 = new JMenuItem("Modify");

  // for pedigree file in Internal node
  JMenuItem jOpenFileMenuItem2 = new JMenuItem("Open");
  JMenuItem jRenameFileMenuItem2 = new JMenuItem("Rename");
  JMenuItem jDeleteFileMenuItem2 = new JMenuItem("Delete");
  JMenuItem jModifyFileMenuItem2 = new JMenuItem("SQL Wizard");

  // for other file in Internal node
  JMenuItem jOpenFileMenuItem3 = new JMenuItem("Open");
  JMenuItem jRenameFileMenuItem3 = new JMenuItem("Rename");
  JMenuItem jDeleteFileMenuItem3 = new JMenuItem("Delete");

  // for files in Analysis node
  JMenuItem jOpenFileMenuItem4 = new JMenuItem("Open");
  JMenuItem jRenameFileMenuItem4 = new JMenuItem("Rename");
  JMenuItem jDeleteFileMenuItem4 = new JMenuItem("Delete");

  // for output file
  JMenuItem jOpenFileMenuItem5 = new JMenuItem("Open");
  JMenuItem jRenameFileMenuItem5 = new JMenuItem("Rename");

  JMenuItem jImportParaMenuItem1 = new JMenuItem("Import new parameter file");
  JMenuItem jImportParaMenuItem2 = new JMenuItem("Use a default parameter file");
  JMenuItem jImportPedigreeMenuItem1 = new JMenuItem("Import new pedigree file");
  JMenuItem jImportPedigreeMenuItem2 = new JMenuItem("Use a default pedigree file");
  JMenuItem jImportLocusMenuItem1 = new JMenuItem("Import new locus file..");
  JMenuItem jImportLocusMenuItem2 = new JMenuItem("Use a default locus file");
  JMenuItem jImportIbdMenuItem1 = new JMenuItem("Import new ibd file");
  JMenuItem jImportIbdMenuItem2 = new JMenuItem("Use a default ibd file");
  JMenuItem jImportGenomeMenuItem1 = new JMenuItem("Import new genome file");
  JMenuItem jImportGenomeMenuItem2 = new JMenuItem("Use a default genome file");

  //project popup
  JPopupMenu jPopupMenuProject = new JPopupMenu();
  JMenuItem jRenameMenuItem = new JMenuItem();
  JPopupMenu jPopupMenuJobs = new JPopupMenu();
  JMenuItem jRunAllMenuItem = new JMenuItem();
  JMenu jMenuNewAnalysis = new JMenu();
  JMenu jMenu1 = new JMenu();
  JMenuItem jPEDINFO = new JMenuItem();
  JMenu jMenu2 = new JMenu();
  JMenu jMenu3 = new JMenu();
  JMenuItem jFREQ = new JMenuItem();
  JMenu jMenu5 = new JMenu();
  JMenu jMenu6 = new JMenu();
  JMenuItem jMARKERINFO = new JMenuItem();
  JMenu jMenu7 = new JMenu();
  JMenuItem jRELTEST = new JMenuItem();
  JMenu jMenu8 = new JMenu();
  JMenuItem jASSOC1 = new JMenuItem();
  JMenu jMenu9 = new JMenu();
  JMenuItem jFCOR = new JMenuItem();
  JMenu jMenu10 = new JMenu();
  JMenuItem jSEGREG1 = new JMenuItem();
  JMenu jMenu11 = new JMenu();
  JMenuItem jSEGREG2 = new JMenuItem();
  JMenu jMenu12 = new JMenu();
  JMenuItem jGENIBD = new JMenuItem();
  JMenu jMenu13 = new JMenu();
  JMenuItem jAGEON = new JMenuItem();
  JMenu jMenu14 = new JMenu();
  JMenu jMenu15 = new JMenu();
  JMenu jMenu16 = new JMenu();
  JMenu jMenu17 = new JMenu();
  JMenu jMenu18 = new JMenu();
  JMenuItem jTDTEX = new JMenuItem();
  JMenu jMenu19 = new JMenu();
  JMenuItem jMLOD = new JMenuItem();
  JMenu jMenu20 = new JMenu();
  JMenuItem jLODLINK = new JMenuItem();
  JMenu jMenu21 = new JMenu();
  JMenuItem jSIBPAL = new JMenuItem();
  JMenu jMenu22 = new JMenu();
  JMenuItem jLODPAL = new JMenuItem();
  JMenu jMenu25 = new JMenu();
  JMenu jMenu26 = new JMenu();
  JMenuItem jASSOC2 = new JMenuItem();

  // analysis popup
  JPopupMenu jPopupMenuAnalysis = new JPopupMenu();
  JMenuItem jMenuItemDelete = new JMenuItem();
  JMenuItem jRunMenuItem = new JMenuItem();

  JPanel jPanel1 = new JPanel();
  JSplitPane jSplitPane1 = new JSplitPane();
  BorderLayout borderLayout1 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();

  String projectPath;
  File projectFile;

  protected JRadioButtonMenuItem m_frameMenuItem;

  DataCollectionModel worker_vector_model;

  static SimpleAttributeSet RED = new SimpleAttributeSet();
  static SimpleAttributeSet BLACK = new SimpleAttributeSet();
  static SimpleAttributeSet BLUE = new SimpleAttributeSet();
  JMenu jMenu4 = new JMenu();
  JMenuItem jDECIPHER  = new JMenuItem();

  ImageIcon next;
  ImageIcon error;
  ImageIcon stop;
  ImageIcon check;
  JScrollPane jScrollPane2 = new JScrollPane();
  JTabbedPane jTabbedPane1 = new JTabbedPane();

  long init_lastModified = 0;

  static {
    StyleConstants.setForeground(RED, Color.red);
    StyleConstants.setFontFamily(RED, "Monospaced");
    StyleConstants.setFontSize(RED, 12);
    StyleConstants.setForeground(BLACK, Color.black);
    StyleConstants.setFontFamily(BLACK, "Monospaced");
    StyleConstants.setFontSize(BLACK, 12);
    StyleConstants.setForeground(BLUE, Color.blue);
    StyleConstants.setFontFamily(BLUE, "Monospaced");
    StyleConstants.setFontSize(BLUE, 12);
  }

  public MyInternalFrame(String title, JDesktopPane desktop_temp,
                         DataCollectionModel datamodel, Frame1 parent) {
    super(title,
          true, //resizable
          true, //closable
          true, //maximizable
          true); //iconifiable
    try {
      this.frame1 = parent;
      this.desktop = desktop_temp;
      this.projectdatamodel = datamodel;
      this.jobframe = parent.jobframe;
      this.consoleframe = parent.consoleframe;
      setModel(new PropertyDataModel());
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void CreateMonitorThread()
  {
    final File para = ((NodeInfo)this.parameterfilenode.getUserObject()).file;

    Timer monitor = new Timer(ONE_SECOND, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (IsMasterModified()) {
          try {
            updateMasterParaFile(para);
          } catch (Exception ex) {
          }
        }
      }
    });
    monitor.setInitialDelay(0);
    monitor.start();
  }

  boolean IsMasterModified()
  {
      IconNode Parameter_node = parameterfilenode;
      NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();
      File masterparafile = paranode.file;

      long lastModified = masterparafile.lastModified();

      if(lastModified > init_lastModified)
      {
        init_lastModified = lastModified;
        return true;
      }

      return false;
  }

  public void setModel(DataCollectionModel model)
  {
    this.worker_vector_model = model;
  }

  private void jbInit() throws Exception {

      check = new ImageIcon(MyInternalFrame.class.getResource("check.png"));
      next = new ImageIcon(MyInternalFrame.class.getResource("next.png"));
      error = new ImageIcon(MyInternalFrame.class.getResource("error_marker.png"));
      stop = new ImageIcon(MyInternalFrame.class.getResource("stop.PNG"));

    this.setFrameIcon(new ImageIcon(MyInternalFrame.class.getResource("project.png")));

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    init_tree();

    jPanel1.setLayout(borderLayout1);

    TreeSelectionPath.setPreferredSize(new Dimension(700, 20));
    TreeSelectionPath.setBorder(BorderFactory.createLoweredBevelBorder());

    jRunAllMenuItem.setText("Run All");
    jRenameMenuItem.setText("Rename project");
    jMenuNewAnalysis.setText("New analysis");
    jMenu1.setText("Summary Statistics");
    jPEDINFO.setText("PEDINFO");
    jMenu2.setText("Data Quality");
    jMenu3.setText("Allele Frequency Estimation");
    jFREQ.setText("FREQ");
    jMenu5.setText("Familial Aggregation");
    jMenu6.setText("Mendelian Inconsistencies");
    jMARKERINFO.setText("MARKERINFO");
    jMenu7.setText("Relationship Testing");
    jRELTEST.setText("RELTEST");
    jMenu8.setText("Heritability Estimation");
    jASSOC1.setText("ASSOC");
    jMenu9.setText("Correlations");
    jFCOR.setText("FCOR");
    jMenu10.setText("Commingling Analysis");
    jSEGREG1.setText("SEGREG");
    jMenu11.setText("Segregation Analysis");
    jSEGREG2.setText("SEGREG");
    jMenu12.setText("IBD Allele Sharing Estimation");
    jGENIBD.setText("GENIBD");
    jMenu13.setText("Age-of-Onset Analysis");
    jAGEON.setText("AGEON");
    jMenu14.setText("Linkage Analysis");
    jMenu15.setText("Model-Based");
    jMenu16.setText("Model-Free");
    jMenu17.setText("Transmission Disequilibrium(TDT)");
    jMenu18.setText("Dichotomous (Binary) Traits");
    jTDTEX.setText("TDTEX");
    jMenu19.setText("Multipoint");
    jMLOD.setText("MLOD");
    jMenu20.setText("Single Point/2-Point");
    jLODLINK.setText("LODLINK");
    jMenu21.setText("Sibling Pairs");
    jSIBPAL.setText("SIBPAL");
    jMenu22.setText("Affected Relative Pairs");
    jLODPAL.setText("LODPAL");
    jMenu25.setText("Allelic Association");
    jMenu26.setText("Family-Based");
    jASSOC2.setText("ASSOC");
    jMenu4.setText("Haplotype Frequency Estimation");
    jDECIPHER.setText("DECIPHER");
    jPopupMenuJobs.add(jRunAllMenuItem);
    jPopupMenuJobs.addSeparator();
    jPopupMenuJobs.add(jMenuNewAnalysis);
    jPopupMenuProject.add(jRenameMenuItem);
    jMenuNewAnalysis.add(jMenu1);
    jMenuNewAnalysis.add(jMenu2);
    jMenuNewAnalysis.add(jMenu3);
    jMenuNewAnalysis.add(jMenu4);
    jMenuNewAnalysis.add(jMenu5);
    jMenuNewAnalysis.add(jMenu10);
    jMenuNewAnalysis.add(jMenu11);
    jMenuNewAnalysis.add(jMenu12);
    jMenuNewAnalysis.add(jMenu13);
    jMenuNewAnalysis.add(jMenu14);
    jMenuNewAnalysis.add(jMenu17);
    jMenuNewAnalysis.add(jMenu25);
    jMenu1.add(jPEDINFO);
    jMenu2.add(jMenu6);
    jMenu2.add(jMenu7);
    jMenu3.add(jFREQ);
    jMenu5.add(jMenu8);
    jMenu5.add(jMenu9);
    jMenu6.add(jMARKERINFO);
    jMenu7.add(jRELTEST);
    jMenu8.add(jASSOC1);
    jMenu9.add(jFCOR);
    jMenu10.add(jSEGREG1);
    jMenu11.add(jSEGREG2);
    jMenu12.add(jGENIBD);
    jMenu13.add(jAGEON);
    jMenu14.add(jMenu15);
    jMenu14.add(jMenu16);
    jMenu15.add(jMenu19);
    jMenu15.add(jMenu20);
    jMenu16.add(jMenu21);
    jMenu16.add(jMenu22);
    jMenu17.add(jMenu18);
    jMenu18.add(jTDTEX);
    jMenu19.add(jMLOD);
    jMenu20.add(jLODLINK);
    jMenu21.add(jSIBPAL);
    jMenu22.add(jLODPAL);
    jMenu25.add(jMenu26);
    jMenu26.add(jASSOC2);

    jRunAllMenuItem.addActionListener(this);
    jRenameMenuItem.addActionListener(this);
    jPEDINFO.addActionListener(this);
    jFREQ.addActionListener(this);
    jMARKERINFO.addActionListener(this);
    jRELTEST.addActionListener(this);
    jASSOC1.addActionListener(this);
    jFCOR.addActionListener(this);
    jSEGREG1.addActionListener(this);
    jSEGREG2.addActionListener(this);
    jGENIBD.addActionListener(this);
    jAGEON.addActionListener(this);
    jTDTEX.addActionListener(this);
    jMLOD.addActionListener(this);
    jLODLINK.addActionListener(this);
    jSIBPAL.addActionListener(this);
    jLODPAL.addActionListener(this);
    jASSOC2.addActionListener(this);
    jDECIPHER.addActionListener(this);

    jMenuItemDelete.setText("Delete");
    jRunMenuItem.setText("Run");
    jPopupMenuAnalysis.add(jRunMenuItem);
    jPopupMenuAnalysis.addSeparator();
    jPopupMenuAnalysis.add(jMenuItemDelete);
    jRunMenuItem.addActionListener(this);
    jMenuItemDelete.addActionListener(this);

    jPopupMenuPara.add(jImportParaMenuItem1);
    jPopupMenuPara.addSeparator();
    jPopupMenuPara.add(jImportParaMenuItem2);
    jImportParaMenuItem1.addActionListener(this);
    jImportParaMenuItem2.addActionListener(this);

    jPopupMenuPedigree.add(jImportPedigreeMenuItem1);
    jPopupMenuPedigree.addSeparator();
    jPopupMenuPedigree.add(jImportPedigreeMenuItem2);
    jImportPedigreeMenuItem1.addActionListener(this);
    jImportPedigreeMenuItem2.addActionListener(this);

    jPopupMenuLocus.add(jImportLocusMenuItem1);
    jPopupMenuLocus.addSeparator();
    jPopupMenuLocus.add(jImportLocusMenuItem2);
    jImportLocusMenuItem1.addActionListener(this);
    jImportLocusMenuItem2.addActionListener(this);

    jPopupMenuIBD.add(jImportIbdMenuItem1);
    jPopupMenuIBD.addSeparator();
    jPopupMenuIBD.add(jImportIbdMenuItem2);
    jImportIbdMenuItem1.addActionListener(this);
    jImportIbdMenuItem2.addActionListener(this);

    jPopupMenuGenome.add(jImportGenomeMenuItem1);
    jPopupMenuGenome.addSeparator();
    jPopupMenuGenome.add(jImportGenomeMenuItem2);
    jImportGenomeMenuItem1.addActionListener(this);
    jImportGenomeMenuItem2.addActionListener(this);

    jPopupMenuFile1.add(jOpenFileMenuItem1);
    jPopupMenuFile1.addSeparator();
    jPopupMenuFile1.add(jRenameFileMenuItem1);
    jPopupMenuFile1.addSeparator();
    jPopupMenuFile1.add(jModifyFileMenuItem1);
    jOpenFileMenuItem1.addActionListener(this);
    jRenameFileMenuItem1.addActionListener(this);
    jModifyFileMenuItem1.addActionListener(this);

    jPopupMenuFile2.add(jOpenFileMenuItem2);
    jPopupMenuFile2.addSeparator();
    jPopupMenuFile2.add(jRenameFileMenuItem2);
    jPopupMenuFile2.addSeparator();
    jPopupMenuFile2.add(jDeleteFileMenuItem2 );
    jPopupMenuFile2.addSeparator();
    jPopupMenuFile2.add(jModifyFileMenuItem2);
    jOpenFileMenuItem2.addActionListener(this);
    jRenameFileMenuItem2.addActionListener(this);
    jDeleteFileMenuItem2.addActionListener(this);
    jModifyFileMenuItem2.addActionListener(this);

    jPopupMenuFile3.add(jOpenFileMenuItem3);
    jPopupMenuFile3.addSeparator();
    jPopupMenuFile3.add(jRenameFileMenuItem3);
    jPopupMenuFile3.addSeparator();
    jPopupMenuFile3.add(jDeleteFileMenuItem3);
    jOpenFileMenuItem3.addActionListener(this);
    jRenameFileMenuItem3.addActionListener(this);
    jDeleteFileMenuItem3.addActionListener(this);

    jPopupMenuFile4.add(jOpenFileMenuItem4);
    jPopupMenuFile4.addSeparator();
    jPopupMenuFile4.add(jRenameFileMenuItem4);
    jPopupMenuFile4.addSeparator();
    jPopupMenuFile4.add(jDeleteFileMenuItem4);
    jOpenFileMenuItem4.addActionListener(this);
    jRenameFileMenuItem4.addActionListener(this);
    jDeleteFileMenuItem4.addActionListener(this);

    jPopupMenuFile5.add(jOpenFileMenuItem5);
    jPopupMenuFile5.addSeparator();
    jPopupMenuFile5.add(jRenameFileMenuItem5);
    jOpenFileMenuItem5.addActionListener(this);
    jRenameFileMenuItem5.addActionListener(this);

    JPanel tabpanel = new JPanel(new BorderLayout());
    tabpanel.add(jScrollPane2,  BorderLayout.CENTER);
    jScrollPane2.getViewport().add(jTabbedPane1, null);

    jSplitPane1.setContinuousLayout(true);

    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jSplitPane1, BorderLayout.CENTER);
    jPanel1.add(TreeSelectionPath, BorderLayout.SOUTH);
    jSplitPane1.add(jScrollPane1, JSplitPane.LEFT);
    jScrollPane1.getViewport().add(MyTree1, null);
    jSplitPane1.add(tabpanel, JSplitPane.RIGHT);
    jMenu4.add(jDECIPHER);
    jSplitPane1.setDividerLocation(250);
    jSplitPane1.setLastDividerLocation(300);
    Dimension minimumSize = new Dimension(200, 200);
    jScrollPane1.setMinimumSize(minimumSize);
    tabpanel.setMinimumSize(new Dimension(420, 420));

    m_frameMenuItem = new JRadioButtonMenuItem(getTitle());
    m_frameMenuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (isSelected())
          return;
        try {
          if (isIcon())
            setIcon(false);
          setSelected(true);
        }
        catch (java.beans.PropertyVetoException e) {

        }
      }
    });

  }

  public IconNode addObject(IconNode childNode,  IconNode parentNode,  boolean visible) {
    parentNode.add(childNode);
    treeModel.nodeStructureChanged(parentNode);

    if (visible) {
      TreePath p = new TreePath(childNode.getPath());
      MyTree1.scrollPathToVisible(p);
      MyTree1.setSelectionPath(p);
    }

    TreePath p = new TreePath(parentNode.getPath());
    MyTree1.scrollPathToVisible(p);

    return childNode;
  }

  public void init_tree() throws Exception {
    nodeinfo = new NodeInfo("S.A.G.E", "Root", "Root", "Root");
    rootnode = new IconNode(nodeinfo, "Root");
    treeModel = new MyTreeModel(rootnode);
    MyTree1 = new MyTree(treeModel);

    MyTree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    MyTree1.setDragEnabled(true);
    MyTree1.setTransferHandler(new TreeTransferHandler(frame1, this));
    MouseListener popupListener = new PopupListener();
    MyTree1.addMouseListener(popupListener);
    MyTree1.addTreeSelectionListener(this);
    MyTree1.putClientProperty("JTree.icons", makeIcons());
    MyTree1.setCellRenderer(new MyIconNodeRenderer());
    MyTree1.setToolTipText("");

    MyTree1.setEditable(true);

    KeyListener keylistener = new DeleteKeyListener();
    MyTree1.addKeyListener(keylistener);
  }

  Hashtable makeIcons() throws Exception{
    ImageIcon root = new ImageIcon(MyInternalFrame.class.getResource("SAGE.PNG"));
    ImageIcon project = new ImageIcon(MyInternalFrame.class.getResource("project.png"));
    ImageIcon analysis = new ImageIcon(MyInternalFrame.class.getResource("analysis_icon.PNG"));
    ImageIcon lockanalysis = new ImageIcon(MyInternalFrame.class.getResource("lock_analysis_icon.png"));
    ImageIcon folder = new ImageIcon(MyInternalFrame.class.getResource("Folder_big.png"));
    ImageIcon folder_open = new ImageIcon(MyInternalFrame.class.getResource("Folder_open.png"));
    ImageIcon file = new ImageIcon(MyInternalFrame.class.getResource("output.png"));
    ImageIcon data = new ImageIcon(MyInternalFrame.class.getResource("data_big.png"));
    ImageIcon task = new ImageIcon(MyInternalFrame.class.getResource("jobs.png"));
    ImageIcon parafile = new ImageIcon(MyInternalFrame.class.getResource("parameter.png"));
    ImageIcon pedigreefile = new ImageIcon(MyInternalFrame.class.getResource("PEDIGREE.PNG"));
    ImageIcon locusfile = new ImageIcon(MyInternalFrame.class.getResource("LOCUS.PNG"));
    ImageIcon markerfile = new ImageIcon(MyInternalFrame.class.getResource("MARKER.PNG"));
    ImageIcon genomefile = new ImageIcon(MyInternalFrame.class.getResource("GENOME.PNG"));
    ImageIcon ibdfile = new ImageIcon(MyInternalFrame.class.getResource("ibd.png"));
    ImageIcon typefile = new ImageIcon(MyInternalFrame.class.getResource("type.png"));
    ImageIcon error_folder = new ImageIcon(MyInternalFrame.class.getResource("Error_Folder.png"));
    ImageIcon error_folder_open = new ImageIcon(MyInternalFrame.class.getResource("Error_Folder_open.png"));
    ImageIcon error_mark = new ImageIcon(MyInternalFrame.class.getResource("error_marker.png"));
    ImageIcon notefile = new ImageIcon(MyInternalFrame.class.getResource("File.PNG"));
    ImageIcon pdffile = new ImageIcon(MyInternalFrame.class.getResource("pdf.png"));
    ImageIcon rfile = new ImageIcon(MyInternalFrame.class.getResource("r.png"));

    Hashtable icons = new Hashtable();
    icons.put("Root", root);
    icons.put("Project", project);
    icons.put("Analysis", analysis);
    icons.put("Locked Analysis", lockanalysis);
    icons.put("InputFolder", folder);
    icons.put("OutputFolder", folder);
    icons.put("OpenFolder", folder_open);
    icons.put("Internal", folder);
    icons.put("External", folder);
    icons.put("Result File", file);
    icons.put("Data", data);
    icons.put("Jobs", task);
    icons.put("Parameter File", parafile);
    icons.put("Pedigree File", pedigreefile);
    icons.put("Marker Locus File", markerfile);
    icons.put("Trait File", locusfile);
    icons.put("Genome File", genomefile);
    icons.put("IBD Sharing File", ibdfile);
    icons.put("Type File", typefile);
    icons.put("ErrorFolder", error_folder);
    icons.put("ErrorOpenFolder", error_folder_open);
    icons.put("ErrorParameterNode", error_mark);
    icons.put("ErrorPedigreeNode", error_mark);
    icons.put("ErrorLocusNode", error_mark);
    icons.put("ErrorIBDNode", error_mark);
    icons.put("ErrorGenomeNode", error_mark);
    icons.put("ErrorTraitNode", error_mark);
    icons.put("Note File", notefile);
    icons.put("R File", rfile);
    icons.put("PDF File", pdffile);

    return icons;
  }

  public void valueChanged(TreeSelectionEvent e) {

    TreePath selectpath = e.getPath();

    final IconNode node = (IconNode) selectpath.getLastPathComponent();

    final NodeInfo nodeInfo = (NodeInfo) node.getUserObject();
    String leafname = nodeInfo.nodetype;

    if (leafname.indexOf("Analysis") >= 0) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          edit_analysis_pane(nodeInfo);
        }
      });
    }

    String path = new String();
    TreeNode[] nodelist = node.getPath();

    for (int i = 0; i < nodelist.length; i++) {
      path = path + System.getProperty("file.separator") + nodelist[i];
    }
    path = path.substring(1);

    TreeSelectionPath.setText(path);

    if (nodeInfo.file != null) {
        if (nodeInfo.nodetype.compareTo("Pedigree File") == 0)
        {
            IconNode parent = (IconNode)node.getParent();
            if(parent != null)
            {
                if(parent.iconName.compareTo("Internal")==0)
                    frame1.jMenuSQL.setEnabled(true);
                else
                  frame1.jMenuSQL.setEnabled(false);
            }
        }
        else
            frame1.jMenuSQL.setEnabled(false);
    }
  }

  public void edit_analysis_pane(NodeInfo node) {
    jTabbedPane1.removeAll();

    for (int i = 0; i < node.component_vector.size(); i++) {
      jTabbedPane1.add( (JPanel) node.component_vector.elementAt(i),
                       node.component_name_vector.elementAt(i));
    }
  }

  public int GetAnalysisCounts(IconNode project, String nodetype) {
    IconNode jobs;
    if (nodetype.compareTo("Project") == 0)
      jobs = (IconNode) project.getChildAt(1);
    else
      jobs = project;

    return jobs.getChildCount();
  }

  public void actionPerformed(ActionEvent e) {
    Object source = e.getSource();

    listModel.removeAllElements();
    int childcount = internalnode.getChildCount();

    for (int i = 0; i < childcount; i++) {
      IconNode child = (IconNode) internalnode.getChildAt(i);
      NodeInfo childnode = (NodeInfo) child.getUserObject();

      File childfile = (File) childnode.file;

      String filepath = childfile.getAbsolutePath();
      String filename = childfile.getName();

      ToolTipItem items = new ToolTipItem(filename, filepath);
      listModel.addElement(items);

      list = new JList(listModel) {
        public String getToolTipText(MouseEvent e) {
          int index = locationToIndex(e.getPoint());
          if ( -1 < index) {
            ToolTipItem item = (ToolTipItem) getModel().getElementAt(index);
            return item.getToolTipText();
          }
          else {
            return null;
          }
        }
      };
      list.setToolTipText("");
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      list.setBorder(BorderFactory.createLoweredBevelBorder());
    }

    TreePath currentpath = MyTree1.getSelectionPath();
    IconNode treenode = (IconNode) currentpath.getLastPathComponent();

    TreePath analysispath = currentpath.getParentPath().getParentPath();

    if (source == jImportParaMenuItem1) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseNewParaFile(info);
      MyTree1.setSelectionPath(analysispath);
    }
    else if (source == jImportParaMenuItem2) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseDefaultParaFile(info);
      MyTree1.setSelectionPath(analysispath);
    }
    else if (source == jImportPedigreeMenuItem1) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseNewPediFile(info);
      MyTree1.setSelectionPath(analysispath);
    }
    else if (source == jImportPedigreeMenuItem2) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseDefaultPediFile(info);
    }
    else if (source == jImportLocusMenuItem1) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseNewLocusFile(info);
      MyTree1.setSelectionPath(analysispath);
    }
    else if (source == jImportLocusMenuItem2) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseDefaultLocusFile(info);
    }

    else if (source == jImportIbdMenuItem1) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseNewIbdFile(info);
      MyTree1.setSelectionPath(analysispath);
    }

    else if (source == jImportIbdMenuItem2) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseDefaultIbdFile(info);
    }

    else if (source == jImportGenomeMenuItem1) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseNewGenomeFile(info);
      MyTree1.setSelectionPath(analysispath);
    }

    else if (source == jImportGenomeMenuItem2) {
      IconNode analysis = (IconNode) analysispath.getLastPathComponent();
      NodeInfo info = (NodeInfo) analysis.getUserObject();
      UseDefaultGenomeFile(info);
    }

    else if (source == jRunMenuItem) {
      NodeInfo currentnode = (NodeInfo) treenode.getUserObject();

      if (currentnode.analysis_type.compareTo("FREQ") == 0) {
        RunFREQ(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("GENIBD") == 0) {
        RunGENIBD(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("LODPAL") == 0) {
        RunLODPAL(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("PEDINFO") == 0) {
        RunPEDINFO(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("MARKERINFO") == 0) {
        RunMARKERINFO(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("ASSOC") == 0) {
        RunASSOC(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("RELTEST") == 0) {
        RunRELTEST(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("FCOR") == 0) {
        RunFCOR(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("LODLINK") == 0) {
        RunLODLINK(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("TDTEX") == 0) {
        RunTDTEX(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("MLOD") == 0) {
        RunMLOD(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("SIBPAL") == 0) {
        RunSIBPAL(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("SEGREG") == 0) {
        RunSEGREG(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("AGEON") == 0) {
        RunAGEON(treenode, currentnode, "Run");
      }
      else if (currentnode.analysis_type.compareTo("DECIPHER") == 0) {
        RunDECIPHER(treenode, currentnode, "Run");
      }

    }
    else if (source == jRunAllMenuItem) {
      ShowRunInfoAll d1 = new ShowRunInfoAll(frame1, this, "Project Information", true);
      d1.jobnode = jobnode;

      for (Enumeration es = jobnode.children(); es.hasMoreElements(); ) {
        IconNode temp = (IconNode) es.nextElement();
        NodeInfo tempnode = (NodeInfo) temp.getUserObject();
        IconNode firstchild = (IconNode) temp.getChildAt(0);
        NodeInfo child = (NodeInfo) firstchild.getUserObject();

        MyCheckBox jc = new MyCheckBox(temp, tempnode);
        jc.setText(temp.toString());

        if (child.nodetype.indexOf("Error") >= 0) {
          jc.setIcon(error);
        }
        d1.jResultPanel.add(jc);
      }

      d1.setLocationRelativeTo(this);
      d1.setVisible(true);
    }
    else if (source == jRenameMenuItem) {
      frame1.jMenuFileSaveAs_actionPerformed();
    }
    else if (source == jMenuItemDelete) {
      IconNode sib = (IconNode) treenode.getPreviousSibling();

      TreeNode parent = treenode.getParent();
      TreePath ppath = currentpath.getParentPath();
      MyTree1.setSelectionPath(ppath);

      treenode.removeFromParent();
      treeModel.nodeStructureChanged(parent);

      if (sib != null) {
        TreePath showpath = new TreePath(sib.getLastLeaf().getPath());
        MyTree1.scrollPathToVisible(showpath);
      }

      jTabbedPane1.removeAll();

      if(treenode.getIconName().compareTo("Analysis")==0)
      {
        NodeInfo currentnode = (NodeInfo) treenode.getUserObject();

        SageFilePanel temppanel = (SageFilePanel)currentnode.component_vector.get(0);
        IconNode tempiconnode = temppanel.para_node;
        NodeInfo tempnodeinfo = (NodeInfo) tempiconnode.getUserObject();
        if(tempnodeinfo.file!=null)
          tempnodeinfo.file.delete();

        if (currentnode.analysis_type.compareTo("FREQ") == 0) {
          freq_count--;
        }
        if (currentnode.analysis_type.compareTo("GENIBD") == 0) {
          genibd_count--;
        }
        if (currentnode.analysis_type.compareTo("LODPAL") == 0) {
          lodpal_count--;
        }
        if (currentnode.analysis_type.compareTo("PEDINFO") == 0) {
          pedinfo_count--;
        }
        if (currentnode.analysis_type.compareTo("MARKERINFO") == 0) {
          markerinfo_count--;
        }
        if (currentnode.analysis_type.compareTo("ASSOC") == 0) {
          assoc_count--;
        }
        if (currentnode.analysis_type.compareTo("AGEON") == 0) {
          ageon_count--;
        }
        if (currentnode.analysis_type.compareTo("RELTEST") == 0) {
          reltest_count--;
        }
        if (currentnode.analysis_type.compareTo("FCOR") == 0) {
          fcor_count--;
        }
        if (currentnode.analysis_type.compareTo("LODLINK") == 0) {
          lodlink_count--;
        }
        if (currentnode.analysis_type.compareTo("TDTEX") == 0) {
          tdtex_count--;
        }
        if (currentnode.analysis_type.compareTo("MLOD") == 0) {
          mlod_count--;
        }
        if (currentnode.analysis_type.compareTo("SIBPAL") == 0) {
          sibpal_count--;
        }
        if (currentnode.analysis_type.compareTo("SEGREG") == 0) {
          segreg_count--;
        }
        if (currentnode.analysis_type.compareTo("DECIPHER") == 0) {
          decipher_count--;
        }
        if (currentnode.analysis_type.compareTo("RELPAL") == 0) {
          relpal_count--;
        }
      }
    }

    else if (source == jPEDINFO) {
      frame1.jMenuPEDINFO_actionPerformed();
    }

    else if (source == jFREQ) {
      frame1.jMenuFREQ_actionPerformed();
    }

    else if (source == jMARKERINFO) {
      frame1.jMenuMARKERINFO_actionPerformed();
    }

    else if (source == jRELTEST) {
      frame1.jMenuRELTEST_actionPerformed();
    }

    else if (source == jASSOC1 || source == jASSOC2) {
      frame1.jMenuASSOC_actionPerformed();
    }

    else if (source == jAGEON) {
      frame1.jMenuAGEON_actionPerformed();
    }

    else if (source == jFCOR) {
      frame1.jMenuFCOR_actionPerformed();
    }

    else if (source == jSEGREG1 || source == jSEGREG2) {
      frame1.jMenuSEGREG_actionPerformed();
    }

    else if (source == jGENIBD) {
      frame1.jMenuGENIBD_actionPerformed();
    }

    else if (source == jTDTEX) {
      frame1.jMenuTDTEX_actionPerformed();
    }

    else if (source == jMLOD) {
      frame1.jMenuMLOD_actionPerformed();
    }

    else if (source == jLODLINK) {
      frame1.jMenuLODLINK_actionPerformed();
    }

    else if (source == jSIBPAL) {
      frame1.jMenuSIBPAL_actionPerformed();
    }

    else if (source == jLODPAL) {
      frame1.jMenuLODPAL_actionPerformed();
    }

    else if (source == jDECIPHER) {
      frame1.jMenuDECIPHER_actionPerformed();
    }

    else if (source == jOpenFileMenuItem1 || source == jOpenFileMenuItem2 ||
             source == jOpenFileMenuItem3 || source == jOpenFileMenuItem4 ||
             source == jOpenFileMenuItem5) {
        jOpenFileMenuItem1_actionPerformed();
    }

    else if (source == jRenameFileMenuItem1 || source == jRenameFileMenuItem2 ||
             source == jRenameFileMenuItem3 || source == jRenameFileMenuItem4 ||
             source == jRenameFileMenuItem5) {
        jRenameFileMenuItem1_actionPerformed();
    }
    else if(source == jModifyFileMenuItem1) //Modify parameter file
    {
        jModifyFileMenuItem1_actionPerformed();
    }

    else if(source == jModifyFileMenuItem2) //SQL Wizard
    {
      jModifyFileMenuItem2_actionPerformed();
    }

    else if(source == jDeleteFileMenuItem2 || source == jDeleteFileMenuItem3)
    {
        jDeleteFileMenuItem_actionPerformed();
    }
    else if(source == jDeleteFileMenuItem4)
    {
        jDeleteFileFromAnalysis_actionPerformed();
    }

  }

  void jRenameFileMenuItem1_actionPerformed()
  {
    TreePath selectpath = MyTree1.getSelectionPath();
    MyTree1.startEditingAtPath(selectpath);
  }

  void jDeleteFileMenuItem_actionPerformed()
  {
      TreePath currentpath = MyTree1.getSelectionPath();
      IconNode treenode = (IconNode) currentpath.getLastPathComponent();
      NodeInfo nodeInfo = (NodeInfo) treenode.getUserObject();
      File file = (File) nodeInfo.file;
      String title = file.getName();

      String message = "Do you want to delete the selected file from the project?";
      int result = JOptionPane.showConfirmDialog(this, message,
                                                 title, JOptionPane.YES_NO_OPTION,
                                                 JOptionPane.INFORMATION_MESSAGE);
      if(result == JOptionPane.YES_OPTION)
      {
          IconNode sib = (IconNode) treenode.getPreviousSibling();
          TreeNode parent = treenode.getParent();
          treenode.removeFromParent();
          treeModel.nodeStructureChanged(parent);

          if (sib != null) {
            TreePath showpath = new TreePath(sib.getLastLeaf().getPath());
            MyTree1.setSelectionPath(showpath);
          }
      }
      else
          return;
  }

  void jDeleteFileFromAnalysis_actionPerformed()
  {
      TreePath selectpath = MyTree1.getSelectionPath();
      IconNode select_node = (IconNode) selectpath.getLastPathComponent();
      NodeInfo nodeInfo = (NodeInfo) select_node.getUserObject();

      File file = (File) nodeInfo.file;
      String title = file.getName();

      String message = "Do you want to delete the selected file from the project?";

      int result = JOptionPane.showConfirmDialog(this, message,
              title, JOptionPane.YES_NO_OPTION,
              JOptionPane.INFORMATION_MESSAGE);

      if (result == JOptionPane.YES_OPTION)
      {
          IconNode sib = (IconNode) select_node.getPreviousSibling();
          TreeNode parent = select_node.getParent();
          IconNode Inputfolder = (IconNode) select_node.getParent();
          IconNode Analysis = (IconNode) Inputfolder.getParent();
          NodeInfo Anodeinfo = (NodeInfo) Analysis.getUserObject();

          if (nodeInfo.file != null)
          {
              if (select_node.getIconName().compareTo("Pedigree File") == 0)
              {
                  if (Anodeinfo.analysis_type.compareTo("ASSOC") == 0) {
                      ASSOC1 test1 = (ASSOC1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("AGEON") == 0) {
                      AGEON1 test1 = (AGEON1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("DECIPHER") == 0) {
                      DECIPHER1 test1 = (DECIPHER1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("FCOR") == 0) {
                      FCOR1 test1 = (FCOR1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("FREQ") == 0) {
                      FREQ1 test1 = (FREQ1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("GENIBD") == 0) {
                      GENIBD1 test1 = (GENIBD1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("LODLINK") == 0) {
                      LODLINK1 test1 = (LODLINK1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("LODPAL") == 0) {
                      LODPAL1 test1 = (LODPAL1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("MARKERINFO") == 0) {
                      MARKERINFO1 test1 = (MARKERINFO1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("MLOD") == 0) {
                      MLOD1 test1 = (MLOD1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("PEDINFO") == 0) {
                      PEDINFO1 test1 = (PEDINFO1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("RELTEST") == 0) {
                      RELTEST1 test1 = (RELTEST1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("SEGREG") == 0) {
                      SEGREG1 test1 = (SEGREG1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("SIBPAL") == 0) {
                      SIBPAL1 test1 = (SIBPAL1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("TDTEX") == 0) {
                      TDTEX1 test1 = (TDTEX1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("RELPAL") == 0) {
                      RELPAL1 test1 = (RELPAL1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldPed.setText("");
                      test1.detelePedNode();
                  }

              }
              else if (select_node.getIconName().compareTo("Marker Locus File") == 0)
              {
                  if (Anodeinfo.analysis_type.compareTo("GENIBD") == 0) {
                      GENIBD1 test1 = (GENIBD1) Anodeinfo.component_vector.get(0);
                      test1.jTextField3.setText("");
                      test1.deteleLocusNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("LODLINK") == 0) {
                      LODLINK1 test1 = (LODLINK1) Anodeinfo.component_vector.get(0);
                      test1.jTextField3.setText("");
                      test1.deteleLocusNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("MLOD") == 0) {
                      MLOD1 test1 = (MLOD1) Anodeinfo.component_vector.get(0);
                      test1.jTextField3.setText("");
                      test1.deteleLocusNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("RELTEST") == 0) {
                      RELTEST1 test1 = (RELTEST1) Anodeinfo.component_vector.get(0);
                      test1.jTextField3.setText("");
                      test1.deteleLocusNode();
                  }
              }
              else if (select_node.getIconName().compareTo("Genome File") == 0)
              {
                  if (Anodeinfo.analysis_type.compareTo("DECIPHER") == 0) {
                      DECIPHER1 test1 = (DECIPHER1) Anodeinfo.component_vector.get(0);
                      test1.jTextFieldGenome.setText("");
                  }
                  else if (Anodeinfo.analysis_type.compareTo("GENIBD") == 0) {
                      GENIBD1 test1 = (GENIBD1) Anodeinfo.component_vector.get(0);
                      test1.jTextField4.setText("");
                      test1.deteleGenomeNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("MLOD") == 0) {
                      MLOD1 test1 = (MLOD1) Anodeinfo.component_vector.get(0);
                      test1.jTextField5.setText("");
                      test1.deteleGenomeNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("RELTEST") == 0) {
                      RELTEST1 test1 = (RELTEST1) Anodeinfo.component_vector.get(0);
                      test1.jTextField4.setText("");
                      test1.deteleGenomeNode();
                  }
              }
              else if (select_node.getIconName().compareTo("IBD Sharing File") == 0)
              {
                  if (Anodeinfo.analysis_type.compareTo("LODPAL") == 0) {
                      LODPAL1 test1 = (LODPAL1) Anodeinfo.component_vector.get(0);
                      test1.jTextField3.setText("");
                      test1.deteleIbdNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("SIBPAL") == 0) {
                      SIBPAL1 test1 = (SIBPAL1) Anodeinfo.component_vector.get(0);
                      test1.jTextField3.setText("");
                      test1.deteleIbdNode();
                  }
                  else if (Anodeinfo.analysis_type.compareTo("RELPAL") == 0) {
                      RELPAL1 test1 = (RELPAL1) Anodeinfo.component_vector.get(0);
                      test1.jTextField3.setText("");
                      test1.deteleIbdNode();
                  }
              }

              select_node.removeFromParent();
              treeModel.nodeStructureChanged(parent);
              MyTree1.Refresh();

              if (sib != null) {
                  TreePath showpath = new TreePath(sib.getLastLeaf().getPath());
                  MyTree1.setSelectionPath(showpath);
              }
          }

      } else
          return;

  }

  void jModifyFileMenuItem2_actionPerformed()
  {
      try {
          JWizardSQL.initialize(frame1, "SQL wizard");
      } catch (Exception ex) {
          ex.printStackTrace();
      }

      TreePath selectpath = MyTree1.getSelectionPath();
      IconNode node = (IconNode) selectpath.getLastPathComponent();

      NodeInfo nodeInfo = (NodeInfo) node.getUserObject();

      File file = (File) nodeInfo.file;
      String filepath = file.getPath();
      MakeSQLStep1 m = (MakeSQLStep1) JWizardSQL.wizard3.deck.getComponent("makesql1");
      m.pName.setText(filepath);

      JWizardSQL.showDialog();
  }

  void jModifyFileMenuItem1_actionPerformed()
  {
      TreePath selectpath = MyTree1.getSelectionPath();
      IconNode node = (IconNode) selectpath.getLastPathComponent();

      NodeInfo nodeInfo = (NodeInfo) node.getUserObject();

      File file = (File) nodeInfo.file;

      if(nodeInfo.nodetype.compareTo("Parameter File")==0)
      {
        if(node == parameterfilenode)
        {
            frame1.file_frame.master_para_file = true;
            frame1.file_frame.SetDisplayFile(file, this);
            frame1.file_frame.Set_Enable_Option();
            frame1.file_frame.setVisible(true);
            frame1.file_frame.setEditable();
            frame1.file_frame.validate();
        }
        else
        {
            frame1.file_frame.master_para_file = false;
            frame1.file_frame.SetDisplayFile(file, this);
            frame1.file_frame.Set_Enable_Option();
            frame1.file_frame.setVisible(true);
            frame1.file_frame.setEditable();
            frame1.file_frame.validate();
        }
      }
  }

  void jOpenFileMenuItem1_actionPerformed()
  {
    TreePath selectpath = MyTree1.getSelectionPath();
    IconNode node = (IconNode) selectpath.getLastPathComponent();

    NodeInfo nodeInfo = (NodeInfo) node.getUserObject();

    File file = (File) nodeInfo.file;

      if(nodeInfo.nodetype.compareTo("Parameter File")==0)
      {
        if(node == parameterfilenode)
        {
          frame1.file_frame.master_para_file = true;
          frame1.file_frame.SetDisplayFile(file, this);
          frame1.file_frame.Set_Enable_Option();
          frame1.file_frame.validate();
          frame1.file_frame.setVisible(true);
        }
        else
        {
          frame1.file_frame.master_para_file = false;
          frame1.file_frame.SetDisplayFile(file, this);
          frame1.file_frame.Set_Enable_Option();
          frame1.file_frame.validate();
          frame1.file_frame.setVisible(true);
        }
      }
      else if(nodeInfo.nodetype.compareTo("Note File")==0)
      {
        frame1.file_frame.master_para_file = false;
        frame1.file_frame.SetDisplayFile(file, this);
        frame1.file_frame.note_file = true;
        frame1.file_frame.Set_Enable_Option();
        frame1.file_frame.setVisible(true);
        frame1.file_frame.setEditable();
        frame1.file_frame.validate();
      }
      else if(nodeInfo.nodetype.compareTo("Genome File")==0)
      {
        frame1.file_frame.master_para_file = false;
        frame1.file_frame.SetDisplayFile(file, this);
        frame1.file_frame.note_file = true;
        frame1.file_frame.Set_Enable_Option();
        frame1.file_frame.validate();
        frame1.file_frame.setVisible(true);
      }
      else
      {
        frame1.file_frame.master_para_file = false;
        frame1.file_frame.SetDisplayFile(file, this);
        frame1.file_frame.Set_Disable_Option();
        frame1.file_frame.validate();
        frame1.file_frame.setVisible(true);
      }
  }

  public void updateMasterParaFile(File master_para_file) throws Exception{
    NodeInfo masterparanode = (NodeInfo)parameterfilenode.getUserObject();
    masterparanode.file = master_para_file;

    FavoritesPanel.Parse_Parameter_File(masterparanode);
    FavoritesPanel.Parse_Parameter_File2(masterparanode);
    FavoritesPanel.Parse_Parameter_File3(masterparanode);

    for (Enumeration es = jobnode.children(); es.hasMoreElements(); ) {
      IconNode analysis = (IconNode) es.nextElement();
      NodeInfo analysisnode = (NodeInfo) analysis.getUserObject();

      SageFilePanel filepanel = (SageFilePanel)analysisnode.component_vector.get(0);
      IconNode para = filepanel.para_node;
      NodeInfo paranode = (NodeInfo)para.getUserObject();
      File parafile = paranode.file;
      String analysisblock = new String();
      DataCollectionModel datamodel = filepanel.Datamodel;
      String Outputfilefilter;

      if (analysisnode.analysis_type.compareTo("FREQ") == 0) {
        final FREQ2 test2 = (FREQ2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "freq";
        analysisblock = GetFREQblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setFREQblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("GENIBD") == 0) {
        final GENIBD2 test2 = (GENIBD2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "genibd";
        analysisblock = GetGENIBDblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setGENIBDblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("LODPAL") == 0) {
        final LODPAL2 test2 = (LODPAL2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "lodpal";
        analysisblock = GetLODPALblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setLODPALblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("PEDINFO") == 0) {
        final PEDINFO2 test2 = (PEDINFO2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "pedinfo";
        analysisblock = GetPEDINFOblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setPEDINFOblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("MARKERINFO") == 0) {
        final MARKERINFO2 test2 = (MARKERINFO2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "markerinfo";
        analysisblock = GetMARKERINFOblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setMARKERINFOblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("ASSOC") == 0) {
        final ASSOC2 test2 = (ASSOC2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "assoc";
        analysisblock = GetASSOCblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setASSOCblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("AGEON") == 0) {
        final AGEON2 test2 = (AGEON2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "ageon";
        analysisblock = GetAGEONblock(datamodel, Outputfilefilter);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setAGEONblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("RELTEST") == 0) {
        final RELTEST2 test2 = (RELTEST2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "reltest";
        analysisblock = GetRELTESTblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setRELTESTblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("FCOR") == 0) {
        final FCOR2 test2 = (FCOR2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "fcor";
        analysisblock = GetFCORblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setFCORblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("LODLINK") == 0) {
        final LODLINK2 test2 = (LODLINK2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "lodlink";
        analysisblock = GetLODLINKblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setLODLINKblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("TDTEX") == 0) {
        final TDTEX2 test2 = (TDTEX2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "tdtex";
        analysisblock = GetTDTEXblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setTDTEXblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("MLOD") == 0) {
        final MLOD2 test2 = (MLOD2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "mlod";
        analysisblock = GetMLODblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setMLODblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("SIBPAL") == 0) {
        final SIBPAL2 test2 = (SIBPAL2)analysisnode.component_vector.get(1);
        final SIBPAL3 test3 = (SIBPAL3)analysisnode.component_vector.get(2);
        final SIBPAL4 test4 = (SIBPAL4)analysisnode.component_vector.get(3);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "sibpal";
        analysisblock = GetSIBPALblock(datamodel, Outputfilefilter, test3, test4);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setSIBPALblock(block, test2, test3, test4);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }
      else if (analysisnode.analysis_type.compareTo("SEGREG") == 0) {
        final SEGREG2 test2 = (SEGREG2) analysisnode.component_vector.get(1);
        final SEGREG3 test3 = (SEGREG3) analysisnode.component_vector.get(2);
        final SEGREG4 test4 = (SEGREG4) analysisnode.component_vector.get(3);
        final SEGREG5 test5 = (SEGREG5) analysisnode.component_vector.get(4);

        DataCollectionModel segreg3 = test3.Datamodel;
        DataCollectionModel segreg4 = test4.Datamodel;
        DataCollectionModel segreg5 = test5.Datamodel;

        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "segreg";
        analysisblock = GetSEGREGblock(datamodel, Outputfilefilter, segreg3, segreg4, segreg5);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        if (masterparanode.infomodel.hasValue("Master_Information")) {
          String master_info = masterparanode.infomodel.getValue("Master_Information").toString();
          final int segreg_type_info = frame1.getSegregType(analysisblock, master_info);
          final String block = analysisblock;
          Runnable setinfo = new Runnable() {
            public void run() {
              switch (segreg_type_info) {
                case 1:
                  test2.QRadioButton.setSelected(true);
                  frame1.setSEGREGblock_C(block, test2, test3, false);
                  break;
                case 2:
                  test2.BRadioButton.setSelected(true);
                  frame1.setSEGREGblock_B(block, test2, test4, false);
                  break;
                case 3:
                  test2.BARadioButton.setSelected(true);
                  frame1.setSEGREGblock_BA(block, test2, test5, false);
                  break;
              }
            }
          };
          SwingUtilities.invokeLater(setinfo);
        }
      }
      else if (analysisnode.analysis_type.compareTo("DECIPHER") == 0) {
        final DECIPHER2 test2 = (DECIPHER2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "decipher";
        analysisblock = GetDECIPHERblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setDECIPHERblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }


      else if (analysisnode.analysis_type.compareTo("RELPAL") == 0) {
        final RELPAL2 test2 = (RELPAL2)analysisnode.component_vector.get(1);
        if (datamodel.hasValue("output_name"))
          Outputfilefilter = datamodel.getValue("output_name").toString();
        else
          Outputfilefilter = "relpal";
        analysisblock = GetRELPALblock(datamodel, Outputfilefilter, test2);

        analysisnode.analysis_block = analysisblock;
        paranode.infomodel = masterparanode.infomodel;
        paranode.file = updateEachParameterFile(paranode, analysisblock,parafile);
        filepanel.insertparafile(paranode);

        final String block = analysisblock;
        Runnable setinfo = new Runnable() {
          public void run() {
            frame1.setRELPALblock(block, test2);
          }
        };
        SwingUtilities.invokeLater(setinfo);
      }

      TreePath p = new TreePath(analysis.getPath());
      MyTree1.collapsePath(p);
    }
  }

  public File updateEachParameterFile(NodeInfo masterparanode, String analysisblock,
                                      File parafile) throws Exception {
    if (masterparanode.infomodel.hasValue("Master_Information")) {
      String master_info = masterparanode.infomodel.getValue("Master_Information").toString();

      File updatedparameterfile = File.createTempFile("TempFile", "tmp");
      FileWriter fos = new FileWriter(updatedparameterfile);
      fos.write(master_info);
      fos.write("\n");
      fos.write(analysisblock);
      fos.write("\n");
      fos.close();
      MyInternalFrame.CopyFile(updatedparameterfile, parafile);
      updatedparameterfile.delete();
    }
    return parafile;
  }

  public void UseNewParaFile(NodeInfo info) {
    if (info.analysis_type.compareTo("FREQ") == 0) {
      FREQ1 test = (FREQ1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("GENIBD") == 0) {
      GENIBD1 test = (GENIBD1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("LODPAL") == 0) {
      LODPAL1 test = (LODPAL1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("PEDINFO") == 0) {
      PEDINFO1 test = (PEDINFO1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("MARKERINFO") == 0) {
      MARKERINFO1 test = (MARKERINFO1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }

    else if (info.analysis_type.compareTo("ASSOC") == 0) {
      ASSOC1 test = (ASSOC1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("RELTEST") == 0) {
      RELTEST1 test = (RELTEST1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("FCOR") == 0) {
      FCOR1 test = (FCOR1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("LODLINK") == 0) {
      LODLINK1 test = (LODLINK1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("TDTEX") == 0) {
      TDTEX1 test = (TDTEX1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("MLOD") == 0) {
      MLOD1 test = (MLOD1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("SIBPAL") == 0) {
      SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("SEGREG") == 0) {
      SEGREG1 test = (SEGREG1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }
    else if (info.analysis_type.compareTo("DECIPHER") == 0) {
      DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
      test.jButton1_actionPerformed();
    }

  }

  public void UseDefaultParaFile(NodeInfo info) {
    if (listModel.getSize() != 0) {
      FileSelectionDialog fs = new FileSelectionDialog(frame1, "File Selection", true);
      fs.jPanel1.add(list, BorderLayout.CENTER);
      fs.setLocationRelativeTo(this);
      fs.setVisible(true);

      if (fs.ok_clicked) {
        ToolTipItem item = (ToolTipItem) list.getSelectedValue();
        String filepath = item.getToolTipText();
        String filename = item.toString();

        if (info.analysis_type.compareTo("FREQ") == 0) {
          FREQ1 test = (FREQ1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("GENIBD") == 0) {
          GENIBD1 test = (GENIBD1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("LODPAL") == 0) {
          LODPAL1 test = (LODPAL1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("PEDINFO") == 0) {
          PEDINFO1 test = (PEDINFO1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("MARKERINFO") == 0) {
          MARKERINFO1 test = (MARKERINFO1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }

        else if (info.analysis_type.compareTo("ASSOC") == 0) {
          ASSOC1 test = (ASSOC1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("RELTEST") == 0) {
          RELTEST1 test = (RELTEST1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("FCOR") == 0) {
          FCOR1 test = (FCOR1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("LODLINK") == 0) {
          LODLINK1 test = (LODLINK1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("TDTEX") == 0) {
          TDTEX1 test = (TDTEX1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("MLOD") == 0) {
          MLOD1 test = (MLOD1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("SIBPAL") == 0) {
          SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("SEGREG") == 0) {
          SEGREG1 test = (SEGREG1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }
        else if (info.analysis_type.compareTo("DECIPHER") == 0) {
          DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Parameter File", new File(filepath));
          test.insertparafile(filenode);
        }

      }
    }
  }

  public void UseNewPediFile(NodeInfo info) {
    if (info.analysis_type.compareTo("FREQ") == 0) {
      FREQ1 test = (FREQ1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("GENIBD") == 0) {
      GENIBD1 test = (GENIBD1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("LODPAL") == 0) {
      LODPAL1 test = (LODPAL1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("PEDINFO") == 0) {
      PEDINFO1 test = (PEDINFO1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("MARKERINFO") == 0) {
      MARKERINFO1 test = (MARKERINFO1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("ASSOC") == 0) {
      ASSOC1 test = (ASSOC1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("RELTEST") == 0) {
      RELTEST1 test = (RELTEST1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("FCOR") == 0) {
      FCOR1 test = (FCOR1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("LODLINK") == 0) {
      LODLINK1 test = (LODLINK1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("TDTEX") == 0) {
      TDTEX1 test = (TDTEX1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("MLOD") == 0) {
      MLOD1 test = (MLOD1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("SIBPAL") == 0) {
      SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("SEGREG") == 0) {
      SEGREG1 test = (SEGREG1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
    else if (info.analysis_type.compareTo("DECIPHER") == 0) {
      DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
      test.jButton2_actionPerformed();
    }
  }

  public void UseDefaultPediFile(NodeInfo info) {
    if (listModel.getSize() != 0) {
      FileSelectionDialog fs = new FileSelectionDialog(frame1, "File Selection", true);
      fs.jPanel1.add(list, BorderLayout.CENTER);
      fs.setLocationRelativeTo(this);
      fs.setVisible(true);

      if (fs.ok_clicked) {
        ToolTipItem item = (ToolTipItem) list.getSelectedValue();
        String filepath = item.getToolTipText();
        String filename = item.toString();

        if (info.analysis_type.compareTo("FREQ") == 0) {
          FREQ1 test = (FREQ1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("GENIBD") == 0) {
          GENIBD1 test = (GENIBD1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("LODPAL") == 0) {
          LODPAL1 test = (LODPAL1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("PEDINFO") == 0) {
          PEDINFO1 test = (PEDINFO1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("MARKERINFO") == 0) {
          MARKERINFO1 test = (MARKERINFO1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("ASSOC") == 0) {
          ASSOC1 test = (ASSOC1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("AGEON") == 0) {
          AGEON1 test = (AGEON1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("RELTEST") == 0) {
          RELTEST1 test = (RELTEST1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("FCOR") == 0) {
          FCOR1 test = (FCOR1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("LODLINK") == 0) {
          LODLINK1 test = (LODLINK1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("TDTEX") == 0) {
          TDTEX1 test = (TDTEX1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("MLOD") == 0) {
          MLOD1 test = (MLOD1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("SIBPAL") == 0) {
          SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("SEGREG") == 0) {
          SEGREG1 test = (SEGREG1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
        else if (info.analysis_type.compareTo("DECIPHER") == 0) {
          DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Pedigree File", new File(filepath));
          test.insertpedigreefile(filenode);
        }
      }
    }
  }

  public void UseNewLocusFile(NodeInfo info) {
    if (info.analysis_type.compareTo("GENIBD") == 0) {
      GENIBD1 test = (GENIBD1) info.component_vector.get(0);
      test.jButton3_actionPerformed();
    }
    else if (info.analysis_type.compareTo("RELTEST") == 0) {
      RELTEST1 test = (RELTEST1) info.component_vector.get(0);
      test.jButton3_actionPerformed();
    }
    else if (info.analysis_type.compareTo("LODLINK") == 0) {
      LODLINK1 test = (LODLINK1) info.component_vector.get(0);
      test.jButton3_actionPerformed();
    }
    else if (info.analysis_type.compareTo("MLOD") == 0) {
      MLOD1 test = (MLOD1) info.component_vector.get(0);
      test.jButton3_actionPerformed();
    }
  }

  public void UseDefaultLocusFile(NodeInfo info) {
    if (listModel.getSize() != 0) {
      FileSelectionDialog fs = new FileSelectionDialog(frame1, "File Selection", true);
      fs.jPanel1.add(list, BorderLayout.CENTER);
      fs.setLocationRelativeTo(this);
      fs.setVisible(true);

      if (fs.ok_clicked) {

        ToolTipItem item = (ToolTipItem) list.getSelectedValue();
        String filepath = item.getToolTipText();
        String filename = item.toString();

        if (info.analysis_type.compareTo("GENIBD") == 0) {
          GENIBD1 test = (GENIBD1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Marker Locus File", new File(filepath));
          test.insertlocusfile(filenode);
        }
        else if (info.analysis_type.compareTo("RELTEST") == 0) {
          RELTEST1 test = (RELTEST1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Marker Locus File", new File(filepath));
          test.insertlocusfile(filenode);
        }
        else if (info.analysis_type.compareTo("LODLINK") == 0) {
          LODLINK1 test = (LODLINK1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Marker Locus File", new File(filepath));
          test.insertlocusfile(filenode);
        }
        else if (info.analysis_type.compareTo("MLOD") == 0) {
          MLOD1 test = (MLOD1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Marker Locus File", new File(filepath));
          test.insertlocusfile(filenode);
        }
      }
    }
  }

  public void UseNewIbdFile(NodeInfo info) {
    if (info.analysis_type.compareTo("LODPAL") == 0) {
      LODPAL1 test = (LODPAL1) info.component_vector.get(0);
      test.jButton3_actionPerformed();
    }
    else if (info.analysis_type.compareTo("SIBPAL") == 0) {
      SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
      test.jButton3_actionPerformed();
    }
  }

  public void UseDefaultIbdFile(NodeInfo info) {
    if (listModel.getSize() != 0) {
      FileSelectionDialog fs = new FileSelectionDialog(frame1, "File Selection", true);
      fs.jPanel1.add(list, BorderLayout.CENTER);
      fs.setLocationRelativeTo(this);
      fs.setVisible(true);

      if (fs.ok_clicked) {
        ToolTipItem item = (ToolTipItem) list.getSelectedValue();
        String filepath = item.getToolTipText();
        String filename = item.toString();

        if (info.analysis_type.compareTo("LODPAL") == 0) {
          LODPAL1 test = (LODPAL1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "IBD Sharing File", new File(filepath));
          test.insertibdfile(filenode);
        }
        else if (info.analysis_type.compareTo("SIBPAL") == 0) {
          SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "IBD Sharing File", new File(filepath));
          test.insertibdfile(filenode);
        }
      }
    }
  }

  public void UseNewGenomeFile(NodeInfo info) {
    if (info.analysis_type.compareTo("DECIPHER") == 0) {
      DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
      test.jButton4_actionPerformed();
    }
    else if (info.analysis_type.compareTo("GENIBD") == 0) {
      GENIBD1 test = (GENIBD1) info.component_vector.get(0);
      test.jButton4_actionPerformed();
    }
    else if (info.analysis_type.compareTo("MLOD") == 0) {
      MLOD1 test = (MLOD1) info.component_vector.get(0);
      test.jButton5_actionPerformed();
    }
    else if (info.analysis_type.compareTo("RELTEST") == 0) {
      RELTEST1 test = (RELTEST1) info.component_vector.get(0);
      test.jButton4_actionPerformed();
    }
  }

  public void UseDefaultGenomeFile(NodeInfo info) {
    if (listModel.getSize() != 0) {
      FileSelectionDialog fs = new FileSelectionDialog(frame1, "File Selection", true);
      fs.jPanel1.add(list, BorderLayout.CENTER);
      fs.setLocationRelativeTo(this);
      fs.setVisible(true);

      if (fs.ok_clicked) {
        ToolTipItem item = (ToolTipItem) list.getSelectedValue();
        String filepath = item.getToolTipText();
        String filename = item.toString();

        if (info.analysis_type.compareTo("DECIPHER") == 0) {
          DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Genome File", new File(filepath));
          test.insertgenomefile(filenode);
        }
        else if (info.analysis_type.compareTo("GENIBD") == 0) {
          GENIBD1 test = (GENIBD1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Genome File", new File(filepath));
          test.insertgenomefile(filenode);
        }
        else if (info.analysis_type.compareTo("MLOD") == 0) {
          MLOD1 test = (MLOD1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Genome File", new File(filepath));
          test.insertgenomefile(filenode);
        }
        else if (info.analysis_type.compareTo("RELTEST") == 0) {
          RELTEST1 test = (RELTEST1) info.component_vector.get(0);
          NodeInfo filenode = new NodeInfo(filename, "Genome File", new File(filepath));
          test.insertgenomefile(filenode);
        }
      }
    }
  }

  public void RunFREQ(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final FREQ1 test1 = (FREQ1) currentnode.component_vector.get(0);
    final FREQ2 test2 = (FREQ2) currentnode.component_vector.get(1);
    final sage_analysis_info fanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel freqData = test1.Datamodel;

      final String Outputfilefilter;
      if (freqData.hasValue("output_name"))
        Outputfilefilter = freqData.getValue("output_name").toString();
      else
        Outputfilefilter = "freq";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("FREQ Analysis");

      if (!freqData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(fanalysis.para_file_path);
      }

      if (!freqData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(fanalysis.family_file_path);
      }

      if (freqData.hasValue("locus_path")) {
        d1.jLocusLabel.setText(fanalysis.locus_file_path);
      }

      d1.setFileList(3);

      try {
        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetFREQblock(freqData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();
          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.
                                          get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setFREQblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);
          }

          final Vector v = new Vector();
          final RunJobTask freq_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          fanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(freq_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "freq.exe";
            if (fanalysis.get_para_path() != null) {
              str = str + " " + "-p " + "\"" + generate_run_filepath(fanalysis.get_para_path()) + "\"";
            }
            if (fanalysis.get_family_path() != null) {
              str = str + " " + "-d " + "\"" + generate_run_filepath(fanalysis.get_family_path()) + "\"";
            }
            if (fanalysis.create_locus_file_node) {
              str = str + " " + "-l " + "\"" + generate_run_filepath(fanalysis.locus_file_path) + "\"";
            }
          }
          else {
            str = "freq";
            if (fanalysis.get_para_path() != null) {
              str = str + " " + "-p " + fanalysis.get_para_path();
            }
            if (fanalysis.get_family_path() != null) {
              str = str + " " + "-d " +fanalysis.get_family_path();
            }
            if (fanalysis.create_locus_file_node) {
              str = str + " " + "-l " + fanalysis.locus_file_path;
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          freq_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }
              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);

          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
    }
  }

  public String GetFREQblock(DataCollectionModel freqData,
                             String Outputfilefilter, FREQ2 test2)
  {
    StringBuffer analysis_block = new StringBuffer();
    analysis_block.append("freq, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (freqData.hasValue("founder_weight")) {
      analysis_block.append("founder_weight = " + "\"" +
                            freqData.getValue("founder_weight").toString() +
                            "\"" + "\n");
    }

    CheckableItem[] model = test2.jMarkerComboBox.ListData;
    int n = model.length;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      String type = item.getVarType();
      if (item.isSelected()) {
        analysis_block.append(type + " = " + "\""+ item.toString() + "\""+ "\n");
      }
    }

    analysis_block.append("skip_mle = " + "\"" +
                          freqData.getValue("skip_mle").toString() +
                          "\"" + "\n");

    analysis_block.append("inbreeding = " + "\"" +
                          freqData.getValue("inbreeding").toString() +
                          "\"" + "\n");

    analysis_block.append("}" + "\n");

    return analysis_block.toString();
  }

  public void RunGENIBD(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final GENIBD1 test1 = (GENIBD1) currentnode.component_vector.get(0);
    final GENIBD2 test2 = (GENIBD2) currentnode.component_vector.get(1);
    final sage_analysis_info ganalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel genibdData = test1.Datamodel;

      final String Outputfilefilter;
      if (genibdData.hasValue("output_name"))
        Outputfilefilter = genibdData.getValue("output_name").toString();
      else
        Outputfilefilter = "genibd";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("GENIBD Analysis");

      if (!genibdData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(ganalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!genibdData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(ganalysis.family_file_path);
        d1.pedLabel.setIcon(check);
      }

      if (!genibdData.hasValue("locus_path")) {
        d1.mlocusLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jLocusLabel.setText(ganalysis.locus_file_path);
        d1.mlocusLabel.setIcon(check);
      }

      if (ganalysis.create_genome_file_node) {
        d1.jGenomeLabel.setText(ganalysis.genome_file_path);
        d1.genomeLabel.setIcon(check);
      }

      d1.setFileList(4);
      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetGENIBDblock(genibdData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();
          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setGENIBDblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);

          }

          //check "p-ter" in genome file and modify locus file for run
          if (ganalysis.create_genome_file_node && ganalysis.create_locus_file_node)
          {
            if(CheckGenomeFile(ganalysis.genome_file_path))
            {
              if(!CheckLocusFile(ganalysis.locus_file_path))
                ganalysis.locus_file_path = ModifyLocusFile(ganalysis.locus_file_path);
            }
          }

          final Vector v = new Vector();
          final RunJobTask genibd_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          ganalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(genibd_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "genibd.exe";

            if (ganalysis.get_para_path() != null) {
              str = str + " " + "-p " + "\"" + generate_run_filepath(ganalysis.get_para_path()) + "\"";
            }
            if (ganalysis.get_family_path() != null) {
              str = str + " " + "-d " + "\"" + generate_run_filepath(ganalysis.get_family_path()) + "\"";
            }
            if (ganalysis.create_locus_file_node) {
              str = str + " " + "-l " + "\"" + generate_run_filepath(ganalysis.locus_file_path) + "\"";
            }
            if (ganalysis.create_genome_file_node) {
              str = str + " " + "-g " + "\"" + generate_run_filepath(ganalysis.genome_file_path) + "\"";
            }
          }
          else {
            str = "genibd";

            if (ganalysis.get_para_path() != null) {
              str = str + " " + "-p " +ganalysis.get_para_path();
            }
            if (ganalysis.get_family_path() != null) {
              str = str + " " + "-d " +ganalysis.get_family_path();
            }
            if (ganalysis.create_locus_file_node) {
              str = str + " " + "-l " +ganalysis.locus_file_path;
            }
            if (ganalysis.create_genome_file_node) {
              str = str + " " + "-g " +ganalysis.genome_file_path;
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          genibd_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };

          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetGENIBDblock(DataCollectionModel genibdData,
                               String Outputfilefilter, GENIBD2 test2)
  {
    StringBuffer analysis_block = new StringBuffer();
    analysis_block.append("genibd, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (genibdData.hasValue("Title"))
      analysis_block.append("title = " + "\"" +
                            genibdData.getValue("Title").toString() + "\"" +
                            "\n");

    CheckableItem[] model = test2.jRegionComboBox.ListData;
    int n = model.length;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      if (item.isSelected()) {
        analysis_block.append("region = " + "\""+ item.toString() + "\""+ "\n");
      }
    }

    analysis_block.append("output_pair_type = " + "\"" +
                          genibdData.getValue("Output_pair_type").toString() +
                          "\"" + "\n");
    if (genibdData.hasValue("Max_ped"))
      analysis_block.append("max_pedigree = " + "\"" +
                            genibdData.getValue("Max_ped").toString() +
                            "\"" + "\n");

    if (genibdData.getValue("Scan_type").toString().compareTo("markers") == 0) {
      analysis_block.append("scan_type = \"markers\"" + "\n");
    }
    else if (genibdData.getValue("Scan_type").toString().compareTo("intervals") == 0) {
      analysis_block.append("scan_type = \"intervals\", distance = ");
      analysis_block.append("\""+genibdData.getValue("Scan_type_value").toString() +"\""+
                            "\n");
    }

    analysis_block.append("allow_loops = " + "\"" +
                          genibdData.getValue("Allow_loops").toString() +
                          "\"" + "\n");
    analysis_block.append("ibd_mode = " + "\"" +
                          genibdData.getValue("IBD_mode").toString() + "\"" +
                          "\n");
    analysis_block.append("split_pedigrees = " + "\"" +
                          genibdData.getValue("Split_ped").toString() +
                          "\"" + "\n");

    analysis_block.append("use_simulation = " + "\"" +
                          genibdData.getValue("Use_sim").toString() + "\"" +
                          "\n");

    analysis_block.append("{" + "\n");

    if (genibdData.hasValue("random_seed"))
      analysis_block.append("random_seed = " + "\"" +
                            genibdData.getValue("random_seed").toString() +
                            "\"" + "\n");

    if (genibdData.getValue("Use_factoring").toString().compareTo("true") == 0) {
      if (genibdData.hasValue("factor11")) {
        analysis_block.append("use_factoring = \"true\"" + "\n");
        analysis_block.append("base_factor = " + "\"" +
                              genibdData.getValue("factor11").toString() +
                              "\"" + "\n");
        analysis_block.append("demem_factor = " + "\"" +
                              genibdData.getValue("factor12").toString() +
                              "\"" + "\n");
        analysis_block.append("sim_factor = " + "\"" +
                              genibdData.getValue("factor13").toString() +
                              "\"" + "\n");
        analysis_block.append("sim_batch_factor = " + "\"" +
                              genibdData.getValue("factor14").toString() +
                              "\"" + "\n");
      }
      else {
        analysis_block.append("use_factoring = \"true\"" + "\n");
        if (genibdData.hasValue("factor12")) {
          analysis_block.append("demem_factor = " + "\"" +
                                genibdData.getValue("factor12").toString() +
                                "\"" + "\n");
        }

        if (genibdData.hasValue("factor13")) {
          analysis_block.append("sim_factor = " + "\"" +
                                genibdData.getValue("factor13").toString() +
                                "\"" + "\n");
        }

        if (genibdData.hasValue("factor14")) {
          analysis_block.append("sim_batch_factor = " + "\"" +
                                genibdData.getValue("factor14").toString() +
                                "\"" + "\n");
        }
      }
    }
    else {
      analysis_block.append("use_factoring = \"false\"" + "\n");
      if (genibdData.hasValue("factor21")) {
        analysis_block.append("sim_steps = " + "\"" +
                              genibdData.getValue("factor21").toString() +
                              "\"" + "\n");
      }

      if (genibdData.hasValue("factor22")) {
        analysis_block.append("demem_steps = " + "\"" +
                              genibdData.getValue("factor22").toString() +
                              "\"" + "\n");
      }

      if (genibdData.hasValue("factor23")) {
        analysis_block.append("sim_batch_count = " + "\"" +
                              genibdData.getValue("factor23").toString() +
                              "\"" + "\n");
      }
    }
    analysis_block.append("}" + "\n");

    analysis_block.append("}" + "\n");
    return analysis_block.toString();
  }

  public void RunLODPAL(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final LODPAL1 test1 = (LODPAL1) currentnode.component_vector.get(0);
    final LODPAL2 test2 = (LODPAL2) currentnode.component_vector.get(1);
    final sage_analysis_info lanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      final DataCollectionModel lodpalData = test1.Datamodel;
      final String Outputfilefilter;
      if (lodpalData.hasValue("output_name"))
        Outputfilefilter = lodpalData.getValue("output_name").toString();
      else
        Outputfilefilter = "lodpal";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("LODPAL Analysis");

      if (!lodpalData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(lanalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!lodpalData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(lanalysis.family_file_path);
        d1.pedLabel.setIcon(check);
      }

      d1.mlocusLabel.setText("IBD sharing file..");
      if (!lodpalData.hasValue("ibd_path")) {
        d1.mlocusLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jLocusLabel.setText(lanalysis.ibd_file_path);
        d1.mlocusLabel.setIcon(check);
      }

      d1.setFileList(3);

      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetLODPALblock(lodpalData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();
          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setLODPALblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);

          }

          final Vector v = new Vector();
          final RunJobTask lodpal_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          lanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(lodpal_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "lodpal.exe";

            if (lanalysis.get_para_path() != null) {
              str = str + " " + "-p " + "\"" + generate_run_filepath(lanalysis.get_para_path()) + "\"";
            }
            if (lanalysis.get_family_path() != null) {
              str = str + " " + "-d " + "\"" + generate_run_filepath(lanalysis.get_family_path()) + "\"";
            }
            if (lanalysis.create_locus_file_node) {
              str = str + " " + "-i " + "\"" + generate_run_filepath(lanalysis.ibd_file_path) + "\"";
            }

          }
          else {
            str = "lodpal";

            if (lanalysis.get_para_path() != null) {
              str = str + " " + "-p " +lanalysis.get_para_path();
            }
            if (lanalysis.get_family_path() != null) {
              str = str + " " + "-d " +lanalysis.get_family_path();
            }
            if (lanalysis.create_locus_file_node) {
              str = str + " " + "-i " +lanalysis.ibd_file_path;
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          lodpal_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);

              WriteLODPALScript(outputf, lanalysis.get_ibd_path(), lodpalData, block);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetLODPALblock(DataCollectionModel lodpalData,
                               String Outputfilefilter, LODPAL2 test2) {
    StringBuffer analysis_block = new StringBuffer();
    analysis_block.append("lodpal, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (lodpalData.hasValue("Trait")) {
      analysis_block.append("trait = " + "\"" +
                            lodpalData.getValue("Trait_name").toString() +
                            "\"");
      analysis_block.append(" , cutpoint = " + "\"" +
                            lodpalData.getValue("Trait_cutpoint").toString() +
                            "\"");
      analysis_block.append(" , " +
                            lodpalData.getValue("Trait_type").toString() +
                            "\n");
    }
    if (lodpalData.hasValue("Cov")) {
      if (lodpalData.hasValue("Cov_info"))
        analysis_block.append(lodpalData.getValue("Cov_info").toString());
      analysis_block.append("\n");
    }

//subset
    CheckableItem[] model = test2.jSubsetComboBox.ListData;
    int n = model.length;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      if (item.isSelected()) {
        analysis_block.append("subset = " + "\""+ item.toString() + "\""+ "\n");
      }
    }

//marker
    model = test2.jMarkerComboBox.ListData;
    n = model.length;
    int marker_count=0;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      if (item.isSelected()) {
          if(marker_count==0)
          {
              lodpalData.setValue("lodpal_first_marker_name", item.toString());
          }
          marker_count++;
        analysis_block.append("marker = " + "\""+ item.toString() + "\""+ "\n");
      }
    }
    lodpalData.setValue("lodpal_marker_count", marker_count);

    if (lodpalData.hasValue("diagnostic")) {
      analysis_block.append("diagnostic = " + "\"" +
                            lodpalData.getValue("diagnostic").toString() +
                            "\"" + "\n");
    }

    if (lodpalData.hasValue("sib_pairs_only")) {
      analysis_block.append("sib_pairs_only" + "\n");
    }

    if (lodpalData.hasValue("wide_out")) {
      analysis_block.append("wide_out = \"true\"" + "\n");
    }
    else {
      analysis_block.append("wide_out = \"false\"" + "\n");
    }

    //pval_scientific_notation
    analysis_block.append("pval_scientific_notation = " + "\""+
                          lodpalData.getValue("pval_scientific_notation").toString() + "\""+
                          "\n");

    if (lodpalData.hasValue("turn_off_default")) {
      analysis_block.append("turn_off_default" + "\n");
    }

    if (lodpalData.hasValue("Pair")) {
      if (lodpalData.hasValue("Pair_name"))
      analysis_block.append("pair_info_file = " + "\"" +
                            generate_run_filepath(lodpalData.getValue("Pair_name").toString()) +
                            "\"" + "\n");
      analysis_block.append("{" + "\n");


      if (lodpalData.hasValue("Pair_weight"))
      analysis_block.append("pair_weight = " + "\"" +
                            lodpalData.getValue("Pair_weight").toString() +
                            "\"" + "\n");

      if (lodpalData.hasValue("Pair_info")) {
        analysis_block.append(lodpalData.getValue("Pair_info").toString());
      }

      analysis_block.append("\n");
      analysis_block.append("}" + "\n");
    }

    if (lodpalData.hasValue("Auto")) {
      analysis_block.append("autosomal" + "\n");
      analysis_block.append("{" + "\n");
      analysis_block.append("model = " + "\"" +
                            lodpalData.getValue("Auto_model").toString() +
                            "\"");
      analysis_block.append(" , alpha = " + "\"" +
                            lodpalData.getValue("Auto_alpha").toString() +
                            "\"");

      if (lodpalData.hasValue("Auto_uncon")) {
        analysis_block.append(" , uncon");
      }
      analysis_block.append("\n");
      analysis_block.append("parent_of_origin = " + "\"" +
                            lodpalData.getValue("Auto_parent").toString() +
                            "\"");
      if (lodpalData.hasValue("Auto_fixed"))
        analysis_block.append(" , fixed = " + "\"" +
                              lodpalData.getValue("Auto_fixed").toString() +
                              "\"");
      if (lodpalData.hasValue("Auto_all_pairs")) {
        analysis_block.append(" , all_pairs");
      }

      analysis_block.append("\n");
      analysis_block.append("}" + "\n");
    }

    if (lodpalData.hasValue("XLin")) {
      analysis_block.append("x_linkage" + "\n");
      analysis_block.append("{" + "\n");
      analysis_block.append("pair_type = " + "\"" +
                            lodpalData.getValue("XLin_pair_type").toString() +
                            "\"" + "\n");
      analysis_block.append("lambda1_equal = " + "\"" +
                            lodpalData.getValue("XLin_lambda1").toString() +
                            "\"" + "\n");
      analysis_block.append("lambda2_fixed = " + "\"" +
                            lodpalData.getValue("XLin_lambda2").toString() +
                            "\"");
      analysis_block.append(" , alpha = " + "\"" +
                            lodpalData.getValue("XLin_lambda2_alpha").
                            toString() + "\"" + "\n");
      analysis_block.append("}" + "\n");
    }

    analysis_block.append("}" + "\n");
    return analysis_block.toString();

  }


  public void RunPEDINFO(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final PEDINFO1 test1 = (PEDINFO1) currentnode.component_vector.get(0);
    final PEDINFO2 test2 = (PEDINFO2) currentnode.component_vector.get(1);
    final sage_analysis_info fanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {

      DataCollectionModel pedinfoData = test1.Datamodel;
      final String Outputfilefilter;
      if (test1.OutputNameField.getText().length() > 0)
        Outputfilefilter = test1.OutputNameField.getText();
      else
        Outputfilefilter = "pedinfo";

      pedinfoData.setValue("output_name", Outputfilefilter);

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("PEDINFO Analysis");

      if (!pedinfoData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jParaLabel.setText(fanalysis.para_file_path);
          d1.parLabel.setIcon(check);
      }

      if (!pedinfoData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jPediLabel.setText(fanalysis.family_file_path);
          d1.pedLabel.setIcon(check);
      }

      d1.setFileList(2);

      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetPEDINFOblock(pedinfoData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();

          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setPEDINFOblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);

          }

          final Vector v = new Vector();
          final RunJobTask assoc_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          fanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(assoc_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {

            str = "pedinfo.exe";
            str = str + " " + "-p " + "\"" + generate_run_filepath(fanalysis.para_file_path) + "\"" + "";
            str = str + " " + "-d " + "\"" + generate_run_filepath(fanalysis.family_file_path) + "\"" + "";
          }
          else {
            str = "pedinfo";
            str = str + " " + "-p " +fanalysis.para_file_path + "";
            str = str + " " + "-d " +fanalysis.family_file_path + "";
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          assoc_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);
          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetPEDINFOblock(DataCollectionModel pedinfoData,
                              String Outputfilefilter, PEDINFO2 test2)
{
  StringBuffer analysis_block = new StringBuffer();

  analysis_block.append("pedinfo, out = ");
  analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
  analysis_block.append("{" + "\n");

  CheckableItem[] model = test2.jTraitComboBox.ListData;
  int n = model.length;
  for (int i = 0; i < n; i++) {
    CheckableItem item = (CheckableItem) model[i];
    String type = item.getVarType();
    if (item.isSelected()) {
      analysis_block.append(type + " = " + "\""+ item.toString() + "\""+ "\n");
    }
  }

  analysis_block.append("each_pedigree = " + "\"" +
                        pedinfoData.getValue("each_pedigree").toString() +
                        "\"" + "\n");
  analysis_block.append("suppress_general = " + "\"" +
                        pedinfoData.getValue("suppress_general").toString() +
                        "\"" + "\n");
  analysis_block.append("}" + "\n");
  return analysis_block.toString();
}


   public void RunAGEON(IconNode dnode, NodeInfo currentnode, String action) {
     final IconNode node = dnode;
     final AGEON1 test1 = (AGEON1) currentnode.component_vector.get(0);
     final AGEON2 test2 = (AGEON2) currentnode.component_vector.get(1);
     final sage_analysis_info aanalysis = currentnode.analysis_info;
     final IconNode outputf = test1.outputF_node;

     if (action.compareTo("Run") == 0) {
       DataCollectionModel ageonData = test1.Datamodel;
       final String Outputfilefilter;
       if (ageonData.hasValue("output_name"))
         Outputfilefilter = ageonData.getValue("output_name").toString();
       else
         Outputfilefilter = "ageon";

       ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
       d1.analysisLabel.setText("AGEON Analysis");

       if (!ageonData.hasValue("para_path")) {
         d1.parLabel.setIcon(error);
         d1.jButton1.setEnabled(false);
       }
       else {
         d1.jParaLabel.setText(ageonData.getValue("para_path").toString());

         d1.parLabel.setIcon(check);
       }

       if (!ageonData.hasValue("pedi_path")) {
         d1.pedLabel.setIcon(error);
         d1.jButton1.setEnabled(false);
       }
       else {
         d1.jPediLabel.setText(aanalysis.family_file_path);
         d1.pedLabel.setIcon(check);
       }

       d1.setFileList(2);

       try {
         if (currentnode.nodetype.compareTo("Analysis") == 0) {
           String analysis_block = GetAGEONblock(ageonData, Outputfilefilter);
           currentnode.analysis_block = analysis_block.toString();
           d1.anaDefTextArea.setText(currentnode.analysis_block);
         }
         else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
           d1.anaDefTextArea.setText(currentnode.analysis_block);

         d1.setLocationRelativeTo(this);
         d1.setVisible(true);

         if (d1.Run) {
           if (!frame1.isConsolViewExist) {
             frame1.jCheckBoxMenuItem3.setSelected(true);
           }

           if (!frame1.isJobViewExist) {
             frame1.jCheckBoxMenuItem2.setSelected(true);
           }

           final String block = d1.anaDefTextArea.getText();

           NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
           if (paranodeinfo.infomodel.hasValue("Master_Information")) {
             String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

             File parameterfileforrun = File.createTempFile("TempFile", "tmp");
             FileWriter fos = new FileWriter(parameterfileforrun);
             fos.write(master_info);
             fos.write("\n");
             fos.write(block);
             fos.write("\n");
             fos.close();

             File parameterfile = new File(test1.Analysis_object.get_para_path());
             MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
             parameterfileforrun.delete();

             Runnable setinfo = new Runnable() {
                 public void run() {
                     frame1.setAGEONblock(block, test2);
                 }
             };
             SwingUtilities.invokeLater(setinfo);
           }
           final Vector v = new Vector();
           final RunJobTask assoc_task = new RunJobTask(node.toString());

           Calendar start = Calendar.getInstance();
           aanalysis.start_time = start;

           String ampm = null;
           if (start.get(Calendar.AM_PM) == 0) {
             ampm = "AM";
           }
           else
             ampm = "PM";
           String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
               d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

           final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
             public void actionPerformed(ActionEvent evt) {
               v.setElementAt(assoc_task.getMessage(), 3);
               jobframe.jTable1.updateUI();
             }
           });

           String str = new String();
           String os_type = System.getProperty("os.name");

           if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
             str = "ageon.exe";
             if (aanalysis.get_para_path() != null) {
               str = str + " " + "-p " + "\"" + generate_run_filepath(aanalysis.get_para_path()) + "\"";
             }
             if (aanalysis.get_family_path() != null) {
               str = str + " " + "-d " + "\"" + generate_run_filepath(aanalysis.get_family_path()) + "\"";
             }
             if (aanalysis.create_locus_file_node) {
               str = str + " " + "-l " + "\"" + generate_run_filepath(aanalysis.locus_file_path) + "\"";
             }

           }
           else {
             str = "ageon";
             if (aanalysis.get_para_path() != null) {
               str = str + " " + "-p " + aanalysis.get_para_path();
             }
             if (aanalysis.get_family_path() != null) {
               str = str + " " + "-d " + aanalysis.get_family_path();
             }
             if (aanalysis.create_locus_file_node) {
               str = str + " " + "-l " + aanalysis.locus_file_path;
             }
           }


           str = str.trim();
           final String run_command = str;

           v.addElement(" " + node.toString());
           v.addElement(currentnode.analysis_type);
           v.addElement(start_time);
           v.addElement("0");
           v.addElement("Running");
           jobframe.v_data.addElement(v);

           assoc_task.go(start);
           assoc_timer.setInitialDelay(0);
           assoc_timer.start();

           JScrollPane FileView = new JScrollPane();
           final JTextPane console_area = new JTextPane();
           console_area.setEditable(false);
           consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
           FileView.getViewport().add(console_area, null);
           consoleframe.setFocusable(true);

           SwingWorker worker = new SwingWorker() {
             public Object construct() {
               try {
                 Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
               }
               catch (Exception excep) {
                 excep.printStackTrace();
               }
               return "sss";
             }

             public void finished() {
               test1.stop_proecss = false;
               assoc_timer.stop();

               int v_index = jobframe.v_data.indexOf(v);
               Vector result_v = (Vector)jobframe.v_data.get(v_index);
               result_v.set(4, "Finished");
               jobframe.v_data.setElementAt(result_v, v_index);

               jobframe.jTable1.updateUI();
               worker_vector_model.removeValue(node.toString());
               test2.jRunButton.setText("Run");
               test2.jRunButton.setIcon(next);
             }
           };
           worker_vector_model.setValue(node.toString(),worker);
           test2.jRunButton.setText("Stop");
           test2.jRunButton.setIcon(stop);
           worker.start();

         }
       }
       catch (Exception exception) {
         exception.printStackTrace();
       }

     }
     else if (action.compareTo("Stop") == 0) {
       test1.stop_proecss = true;
       test2.jRunButton.setText("Run");
       test2.jRunButton.setIcon(next);
       SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
       worker_t.interrupt();
     }
   }

   public String GetAGEONblock(DataCollectionModel ageonData, String Outputfilefilter)
   {
     StringBuffer analysis_block = new StringBuffer();

     analysis_block.append("ageon, out = ");
     analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
     analysis_block.append("{" + "\n");

     if (ageonData.hasValue("Title")) {
       analysis_block.append("title = " + "\"" +
                             ageonData.getValue("Title").toString() + "\"" + "\n");
     }

     if (ageonData.hasValue("affectedness")) {
       analysis_block.append("affectedness = " + "\"" +
                             ageonData.getValue("affectedness").toString() + "\"" + "\n");
     }

     if (ageonData.hasValue("age_of_onset")) {
       analysis_block.append("age_of_onset = " + "\"" +
                             ageonData.getValue("age_of_onset").toString() + "\"" + "\n");
     }

     if (ageonData.hasValue("age_of_exam")) {
       analysis_block.append("age_of_exam = " + "\"" +
                             ageonData.getValue("age_of_exam").toString() + "\"" + "\n");
     }

     analysis_block.append("allow_averaging = " + "\"" + ageonData.getValue("AA").toString() + "\"" + "\n");

     if (ageonData.hasValue("mean_cov_info")) {
       analysis_block.append("mean_cov {" + "\n");
       analysis_block.append(ageonData.getValue("mean_cov_info").toString() +  "\n");
       analysis_block.append("}" + "\n");
     }

     if (ageonData.hasValue("var_cov_info")) {
       analysis_block.append("var_cov {" + "\n");
       analysis_block.append(ageonData.getValue("var_cov_info").toString() + "\n");
       analysis_block.append("}" + "\n");
     }

     if (ageonData.hasValue("pool_info"))
     {
       String pool_stat = ageonData.getValue("pool_info").toString();
       if(pool_stat.length()>0)
         analysis_block.append("pool = \""+ageonData.getValue("pool_info").toString()+"\"" + "\n");
     }

     if (ageonData.hasValue("suscept_cov_info")) {
       analysis_block.append("suscept_cov {" + "\n");
       analysis_block.append(ageonData.getValue("suscept_cov_info").toString() + "\n");
       if (ageonData.hasValue("sus_cov_class_trait")) {
         analysis_block.append("class {" + "\n");
         analysis_block.append("trait = " + "\""+
                               ageonData.getValue("sus_cov_class_trait").
                               toString() + "\"" + "\n");
         if (ageonData.hasValue("sus_cov_class_number"))
           analysis_block.append("num_of_classes = " +
                                 ageonData.getValue("sus_cov_class_number").
                                 toString() + "\n");
         analysis_block.append("}" + "\n");
       }
       analysis_block.append("}" + "\n");
     }

     if (ageonData.hasValue("transformation_info")) {
       analysis_block.append("transformation {" + "\n");

       if (ageonData.hasValue("lambda1_val")) {
         analysis_block.append("lambda1, val = " + "\""+
                               ageonData.getValue("lambda1_val").toString()+ "\"");
         if (ageonData.hasValue("lambda1_fixed"))
             analysis_block.append(", fixed  = " + "\""+
                               ageonData.getValue("lambda1_fixed").toString() + "\"");
         analysis_block.append("\n");
       }
       if (ageonData.hasValue("lambda2_val")) {
         analysis_block.append("lambda2, val = " + "\""+
                               ageonData.getValue("lambda2_val").toString()+ "\"");
         if (ageonData.hasValue("lambda2_fixed"))
             analysis_block.append(", fixed  = " + "\""+
                               ageonData.getValue("lambda2_fixed").toString() + "\"");
         analysis_block.append("\n");
       }
       analysis_block.append("}" + "\n");
     }

     analysis_block.append("}" + "\n");

     return analysis_block.toString();
   }

  public void RunASSOC(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final ASSOC1 test1 = (ASSOC1) currentnode.component_vector.get(0);
    final ASSOC2 test2 = (ASSOC2) currentnode.component_vector.get(1);
    final sage_analysis_info aanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel assocData = test1.Datamodel;
      final String Outputfilefilter;
      if (assocData.hasValue("output_name"))
        Outputfilefilter = assocData.getValue("output_name").toString();
      else
        Outputfilefilter = "assoc";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("ASSOC Analysis");

      if (!assocData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(aanalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!assocData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(aanalysis.family_file_path);
        d1.pedLabel.setIcon(check);
      }

      if (assocData.hasValue("locus_path")) {
        d1.jLocusLabel.setText(aanalysis.locus_file_path);
        d1.mlocusLabel.setIcon(check);
      }

      d1.setFileList(3);

      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetASSOCblock(assocData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();

          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();

          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setASSOCblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);
          }

          final Vector v = new Vector();
          final RunJobTask assoc_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          aanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(assoc_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "assoc.exe";
            if (aanalysis.get_para_path() != null) {
              str = str + " " + "-p " + "\"" + generate_run_filepath(aanalysis.get_para_path()) + "\"";
            }
            if (aanalysis.get_family_path() != null) {
              str = str + " " + "-d " + "\"" + generate_run_filepath(aanalysis.get_family_path()) + "\"";
            }
            if (aanalysis.create_locus_file_node) {
              str = str + " " + "-l " + "\"" + generate_run_filepath(aanalysis.locus_file_path) + "\"";
            }
          }
          else {
            str = "assoc";
            if (aanalysis.get_para_path() != null) {
              str = str + " " + "-p " +aanalysis.get_para_path();
            }
            if (aanalysis.get_family_path() != null) {
              str = str + " " + "-d " +aanalysis.get_family_path();
            }
            if (aanalysis.create_locus_file_node) {
              str = str + " " + "-l " +aanalysis.locus_file_path;
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          assoc_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }
              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);

              WriteASSOCRScript(outputf);


            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);
          worker.start();
        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }

    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetASSOCblock(DataCollectionModel assocData,
                              String Outputfilefilter, ASSOC2 test2) {
    StringBuffer analysis_block = new StringBuffer();
    analysis_block.append("assoc, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (assocData.hasValue("Title")) {
      analysis_block.append("title = " + "\"" +
                            assocData.getValue("Title").toString() + "\"" + "\n");
    }

    if (assocData.hasValue("Trait")) {
      analysis_block.append("primary_trait = " + "\"" +
                            assocData.getValue("Trait").toString() + "\"" + "\n");
    }

    if (assocData.hasValue("assoc_cov")) {
      analysis_block.append(assocData.getValue("assoc_cov").toString() + "\n");
    }

    analysis_block.append("polygenic_effect = " + "\""
                              + assocData.getValue("PE").toString() + "\"");
        if (test2.PEVal.isEnabled() && assocData.hasValue("PEVal")) {
          analysis_block.append(", " + "val = " + "\"" +
                                assocData.getValue("PEVal").toString() + "\"");

          if(test2.PEfixedCheckBox.isSelected())
            analysis_block.append(", " + "fixed = " + "\"true\"");
        }
        analysis_block.append("\n");

        analysis_block.append("family_effect = " + "\"" +
                              assocData.getValue("FE").toString() + "\"");
        if (test2.PEvalLabel.isEnabled() && assocData.hasValue("FEVal")) {
          analysis_block.append(", " + "val = " + "\"" +
                                assocData.getValue("FEVal").toString() + "\"");
          if(test2.FEfixedCheckBox.isSelected())
            analysis_block.append(", " + "fixed = " + "\"true\"");
        }
        analysis_block.append("\n");

        analysis_block.append("marital_effect = " + "\"" +
                              assocData.getValue("ME").toString() + "\"");
        if (test2.MEvalLabel.isEnabled() && assocData.hasValue("MEVal")) {
          analysis_block.append(", " + "val = " + "\"" +
                                assocData.getValue("MEVal").toString() + "\"");
          if(test2.MEfixedCheckBox.isSelected())
            analysis_block.append(", " + "fixed = " + "\"true\"");
        }
        analysis_block.append("\n");

        analysis_block.append("sibship_effect = " + "\"" +
                              assocData.getValue("SE").toString() + "\"");
        if (test2.SEvalLabel.isEnabled() && assocData.hasValue("SEVal")) {
          analysis_block.append(", " + "val = " + "\"" +
                                assocData.getValue("SEVal").toString() + "\"");
          if(test2.SEfixedCheckBox.isSelected())
            analysis_block.append(", " + "fixed = " + "\"true\"");
        }
        analysis_block.append("\n");

        if (assocData.hasValue("class_effect")) {
          analysis_block.append(assocData.getValue("class_effect").toString() + "\n");
        }

    // Transformation block
    if(assocData.hasValue("transformation_info"))
    {
      analysis_block.append("transformation {" + "\n");
      analysis_block.append("option = " + "\"" +
                            assocData.getValue("Trans").toString() + "\"" + "\n");

      if (assocData.hasValue("both_sides"))
      {
          analysis_block.append("both_sides"+"\n");
      }

      if(assocData.getValue("Trans").toString().compareTo("none")!=0)
      {
        if (assocData.hasValue("lambda1")) {
          analysis_block.append("lambda1, val = " + "\"" +
                                assocData.getValue("lambda1").toString() + "\"");
          if(test2.transDialog.jComboBoxLambda1Fixed.isSelected())
            analysis_block.append(", fixed = " + "\"true\"");
          if (assocData.hasValue("lower_bound"))
            analysis_block.append(", lower_bound = " + "\"" +
                                  assocData.getValue("lower_bound").toString() + "\"");
          if (assocData.hasValue("upper_bound"))
            analysis_block.append(", upper_bound = " + "\"" +
                                  assocData.getValue("upper_bound").toString() + "\"");
          analysis_block.append("\n");
        }

        if (assocData.hasValue("lambda2")) {
          analysis_block.append("lambda2, val = " + "\"" +
                                assocData.getValue("lambda2").toString() + "\"");
          if(test2.transDialog.jComboBoxLambda2Fixed.isSelected())
            analysis_block.append(", fixed = " + "\"true\"");
          analysis_block.append("\n");
        }
      }


      analysis_block.append("}" + "\n");
    }

    if (assocData.hasValue("summary_display")) {
      analysis_block.append(assocData.getValue("summary_display").toString() + "\n");
    }

    analysis_block.append("allow_averaging = " + "\"" +
                          assocData.getValue("AA").toString() + "\"" + "\n");

    if (assocData.hasValue("batch")) {
      analysis_block.append("batch" + "\n");
    }

    if (assocData.hasValue("assoc_residuals"))
    {
        analysis_block.append(assocData.getValue("assoc_residuals").toString()+ "\n");
    }

    analysis_block.append("}" + "\n");

    return analysis_block.toString();

  }

  public void WriteASSOCRScript(IconNode output_f) {
      String rfilepath = null;
      String name = "";
      String resultfile = "";
      for (int i = 0; i < output_f.getChildCount(); i++) {
          NodeInfo io = (NodeInfo)((IconNode) output_f.getChildAt(i)).getUserObject();
          if (io.file != null && io.nodetype.compareTo("Result File") == 0)
          {
              if(io.file.getName().endsWith(".csv"))
              {
                  name = io.file.getName().substring(0, io.file.getName().indexOf("."));
                  rfilepath = io.file.getParentFile()+ System.getProperty("file.separator")+name+".r";
                  resultfile = io.file.getPath();
              }
          }
      }

      if (rfilepath != null) {
          try {

              FileWriter fos = new FileWriter(rfilepath);
              fos.write("filepath = " + " \"" + generate_run_filepath(resultfile) + "\""+"\n");
              fos.write("\n");
              fos.write("# ---------------------------------------------"+"\n");
              fos.write("# 1. Read data"+"\n");
              fos.write("# ---------------------------------------------"+"\n");

              fos.write(
                      "data <- read.table(filepath, header=TRUE, sep=\",\", stringsAsFactors = FALSE)"+"\n");
              fos.write("\n");
              fos.write("wald_p = -log10(data$P.value..Wald.)"+"\n");
              fos.write("lrt_p = -log10(data$P.value..LRT.)"+"\n");
              fos.write("\n");
              fos.write("diff = c()"+"\n");
              fos.write("for(i in 1:length(lrt_p))"+"\n");
              fos.write("{"+"\n");
              fos.write("diff[i] = abs(lrt_p[i] - wald_p[i])"+"\n");
              fos.write("}"+"\n");
              fos.write("\n");
              fos.write("range = c(1:length(data$Model))"+"\n");
              fos.write("\n");
              fos.write("# ---------------------------------------------"+"\n");
              fos.write("# 2. Plot & Save"+"\n");
              fos.write("# ---------------------------------------------"+"\n");

              fos.write("windows(record=TRUE, width=2000, height=800)"+"\n");
              fos.write("par(mfrow=c(2,1))"+"\n");
              fos.write("\n");
              fos.write("plot(range, lrt_p,   type=\"l\", xlab=\"model\", ylab=\"-log10P\",axes=T, ann=T, col=\"steelblue\")"+"\n");
              fos.write("title(\"LRT\")"+"\n");
              fos.write("abline(h=-log10(0.05), lty=3)"+"\n");
              fos.write("\n");
              fos.write("plot(range, diff,   type=\"l\", xlab=\"model\", ylab=\"abs(LRT-Wald)\", col=\"mediumvioletred\", axes=T, ann=T)"+"\n");
              fos.write("title(\"Absolute Difference |LRT-Wald|\")"+"\n");
              fos.write("\n");
              fos.write("savepath = paste(filepath, \"_plot.pdf\",sep=\"\");"+"\n");
              fos.write("savePlot(savepath ,type=\"pdf\")"+"\n");

              fos.close();

              NodeInfo filenode1 = new NodeInfo(name + ".r", "R File", new File(rfilepath));
              IconNode rfilenode1 = new IconNode(filenode1, "R File");
              addObject(rfilenode1, output_f, true);

              Runtime.getRuntime().exec("Rscript" + " \"" + rfilepath + "\"");

              NodeInfo filenode2 = new NodeInfo(name + ".csv_plot.pdf", "PDF File", new File(resultfile + "_plot.pdf"));
              IconNode rfilenode2 = new IconNode(filenode2, "PDF File");
              addObject(rfilenode2, output_f, true);
          } catch (Exception excep) {
              excep.printStackTrace();
          }
      }

  }

  public void RunRELTEST(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final RELTEST1 test1 = (RELTEST1) currentnode.component_vector.get(0);
    final RELTEST2 test2 = (RELTEST2) currentnode.component_vector.get(1);
    final sage_analysis_info ranalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel reltestData = test1.Datamodel;
      final String Outputfilefilter;
      if (reltestData.hasValue("output_name"))
        Outputfilefilter = reltestData.getValue("output_name").toString();
      else
        Outputfilefilter = "reltest";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("RELTEST Analysis");

      if (!reltestData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jParaLabel.setText(ranalysis.para_file_path);
          d1.parLabel.setIcon(check);
      }

      if (!reltestData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jPediLabel.setText(ranalysis.family_file_path);
          d1.pedLabel.setIcon(check);
      }

      if (!reltestData.hasValue("locus_path")) {
        d1.mlocusLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jLocusLabel.setText(ranalysis.locus_file_path);
          d1.mlocusLabel.setIcon(check);
      }

      if (!reltestData.hasValue("genome_path")) {
        d1.genomeLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jGenomeLabel.setText(ranalysis.genome_file_path);
          d1.genomeLabel.setIcon(check);
      }

      d1.setFileList(4);
      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetRELTESTblock(reltestData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();
          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setRELTESTblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);
          }

          //check "p-ter" in genome file and modify locus file for run
          if (ranalysis.create_genome_file_node && ranalysis.create_locus_file_node)
          {
            if(CheckGenomeFile(ranalysis.genome_file_path))
            {
              if(!CheckLocusFile(ranalysis.locus_file_path))
                ranalysis.locus_file_path = ModifyLocusFile(ranalysis.locus_file_path);
            }
          }

          final Vector v = new Vector();
          final RunJobTask reltest_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          ranalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(reltest_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "reltest.exe";

            if (ranalysis.get_para_path() != null) {
              str = str + " " + "-p " + "\"" + generate_run_filepath(ranalysis.get_para_path()) + "\"";
            }
            if (ranalysis.get_family_path() != null) {
              str = str + " " + "-d " + "\"" + generate_run_filepath(ranalysis.get_family_path()) + "\"";
            }
            if (ranalysis.create_locus_file_node) {
              str = str + " " + "-l " + "\"" + generate_run_filepath(ranalysis.locus_file_path) + "\"";
            }
            if (ranalysis.create_genome_file_node) {
              str = str + " " + "-g " + "\"" + generate_run_filepath(ranalysis.genome_file_path) + "\"";
            }

          }
          else {
            str = "reltest";

            if (ranalysis.get_para_path() != null) {
              str = str + " " + "-p " + ranalysis.get_para_path();
            }
            if (ranalysis.get_family_path() != null) {
              str = str + " " + "-d " + ranalysis.get_family_path();
            }
            if (ranalysis.create_locus_file_node) {
              str = str + " " + "-l " + ranalysis.locus_file_path;
            }
            if (ranalysis.create_genome_file_node) {
              str = str + " " + "-g " + ranalysis.genome_file_path;
            }
          }


          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          reltest_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }

  }

  public String GetRELTESTblock(DataCollectionModel reltestData,
                                String Outputfilefilter, RELTEST2 test2)
  {
    StringBuffer analysis_block = new StringBuffer();
    analysis_block.append("reltest, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (reltestData.hasValue("pair_type_sib")) {
      analysis_block.append("pair_type = \"sib\"" + "\n");
    }
    if (reltestData.hasValue("pair_type_hsib")) {
      analysis_block.append("pair_type = \"hsib\"" + "\n");
    }
    if (reltestData.hasValue("pair_type_parent_offspring")) {
      analysis_block.append("pair_type = \"parent_offspring\"" + "\n");
    }
    if (reltestData.hasValue("pair_type_marital")) {
      analysis_block.append("pair_type = \"marital\"" + "\n");
    }

    // region
    CheckableItem[] model = test2.jRegionComboBox.ListData;
    int n = model.length;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      if (item.isSelected()) {
        analysis_block.append("region = " + "\""+ item.toString() + "\""+ "\n");
      }
    }

    if (reltestData.hasValue("cut_point_sib") ||
        reltestData.hasValue("cut_point_hsib") ||
        reltestData.hasValue("cut_point_MZtwin") ||
        reltestData.hasValue("cut_point_parent_offspring")) {

      analysis_block.append("cut_points");

      if (reltestData.hasValue("cut_point_sib")) {
        analysis_block.append(" , sib = " + "\"" +
                              reltestData.getValue("cut_point_sib").toString() +
                              "\"");
      }

      if (reltestData.hasValue("cut_point_hsib")) {
        analysis_block.append(", hsib = " + "\"" +
                              reltestData.getValue("cut_point_hsib").toString() +
                              "\"");
      }
      if (reltestData.hasValue("cut_point_MZtwin")) {
        analysis_block.append(", MZtwin = " + "\"" +
                              reltestData.getValue("cut_point_MZtwin").
                              toString() + "\"");
      }

      if (reltestData.hasValue("cut_point_parent_offspring")) {
        analysis_block.append(", parent_offspring = " + "\"" +
                              reltestData.
                              getValue("cut_point_parent_offspring").toString() +
                              "\"");
      }
      analysis_block.append("\n");
    }


    analysis_block.append("nucfam_file = " + "\"" +
                          reltestData.getValue("nuclear_family_file").
                          toString() + "\"" + "\n");
    analysis_block.append("detailed_file = " + "\"" +
                          reltestData.getValue("detailed_file").toString() +
                          "\"" + "\n");
    analysis_block.append("}" + "\n");
    return analysis_block.toString();
  }

  public void RunMARKERINFO(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final MARKERINFO1 test1 = (MARKERINFO1) currentnode.component_vector.get(0);
    final MARKERINFO2 test2 = (MARKERINFO2) currentnode.component_vector.get(1);
    final sage_analysis_info manalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel markerinfoData = test1.Datamodel;
      final String Outputfilefilter;
      if (markerinfoData.hasValue("output_name"))
        Outputfilefilter = markerinfoData.getValue("output_name").toString();
      else
        Outputfilefilter = "markerinfo";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("MARKERINFO Analysis");

      if (!markerinfoData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(manalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!markerinfoData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(manalysis.family_file_path);
        d1.pedLabel.setIcon(check);
      }

      if (markerinfoData.hasValue("locus_path")) {
        d1.jLocusLabel.setText(manalysis.locus_file_path);
        d1.mlocusLabel.setIcon(check);
      }

      d1.setFileList(3);

      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetMARKERINFOblock(markerinfoData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();

          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setMARKERINFOblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);
          }

          final Vector v = new Vector();
          final RunJobTask markerinfo_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          manalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(markerinfo_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "markerinfo.exe";
            if (manalysis.get_para_path() != null) {
              str = str + " " + "-p " + "\"" + generate_run_filepath(manalysis.get_para_path()) + "\"";
            }
            if (manalysis.get_family_path() != null) {
              str = str + " " + "-d " + "\"" + generate_run_filepath(manalysis.get_family_path()) + "\"";
            }
            if (manalysis.create_locus_file_node) {
              str = str + " " + "-l " + "\"" + generate_run_filepath(manalysis.get_locus_path()) + "\"";
            }

          }
          else {
            str = "markerinfo";
            if (manalysis.get_para_path() != null) {
              str = str + " " + "-p " + manalysis.get_para_path();
            }
            if (manalysis.get_family_path() != null) {
              str = str + " " + "-d " + manalysis.get_family_path();
            }
            if (manalysis.create_locus_file_node) {
               str = str + " " + "-l " + manalysis.get_locus_path();
             }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          markerinfo_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();
        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetMARKERINFOblock(DataCollectionModel markerinfoData,
                                   String Outputfilefilter, MARKERINFO2 test2)
  {
    StringBuffer analysis_block = new StringBuffer();

    analysis_block.append("markerinfo, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    analysis_block.append("consistent_out = " + "\"" +
                          markerinfoData.getValue("consistent_out").
                          toString() + "\"" + "\n");

    if (markerinfoData.hasValue("pedigree_out")) {

        analysis_block.append("pedigree_out = " + "\"" +
                            markerinfoData.getValue("pedigree_out").toString() + "\"" + "\n");
    }


    if (markerinfoData.getValue("sample_id").toString().compareTo("not use") ==
        0) {
    }
    else {
      analysis_block.append("sample_id = " + "\"" +
                            markerinfoData.getValue("sample_id").toString() +
                            "\"" + "\n");
    }

    analysis_block.append("}" + "\n");
    return analysis_block.toString();
  }



  public void RunMLOD(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final MLOD1 test1 = (MLOD1) currentnode.component_vector.get(0);
    final MLOD2 test2 = (MLOD2) currentnode.component_vector.get(1);
    final sage_analysis_info manalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel mlodData = test1.Datamodel;

      final String Outputfilefilter;
      if (mlodData.hasValue("output_name"))
        Outputfilefilter = mlodData.getValue("output_name").toString();
      else
        Outputfilefilter = "mlod";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("MLOD Analysis");

      d1.genomeLabel.setText("Trait locus file..");
      d1.typeLabel.setText("Genome description file..");

      if (!mlodData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
        d1.jParaLabel.setText(manalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!mlodData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jPediLabel.setText(manalysis.family_file_path);
          d1.pedLabel.setIcon(check);
      }

      if (!mlodData.hasValue("locus_path")) {
        d1.mlocusLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jLocusLabel.setText(manalysis.locus_file_path);
          d1.mlocusLabel.setIcon(check);
      }

      if (!mlodData.hasValue("genome_path")) {
        d1.jTypeLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jTypeLabel.setText(manalysis.genome_file_path);
          d1.typeLabel.setIcon(check);
      }

      if (mlodData.hasValue("trait_path")) {
        d1.jGenomeLabel.setText(manalysis.trait_file_path);
        d1.genomeLabel.setIcon(check);
      }

      d1.setFileList(5);
      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetMLODblock(mlodData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();

          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.
                                          get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setMLODblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);
          }

          //check "p-ter" in genome file and modify locus file for run
          if (manalysis.create_genome_file_node && manalysis.create_locus_file_node)
          {
            if(CheckGenomeFile(manalysis.genome_file_path))
            {
              if(!CheckLocusFile(manalysis.locus_file_path))
                manalysis.locus_file_path = ModifyLocusFile(manalysis.locus_file_path);
            }
          }

          final Vector v = new Vector();
          final RunJobTask mlod_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          manalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(mlod_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "mlod.exe";

            if (manalysis.get_para_path() != null) {
              str = str + " " + "-p " + "\""+ generate_run_filepath(manalysis.get_para_path()) + "\"";
            }
            if (manalysis.get_family_path() != null) {
              str = str + " " + "-d " + "\"" + generate_run_filepath(manalysis.get_family_path()) + "\"";
            }
            if (manalysis.create_locus_file_node) {
              str = str + " " + "-l " + "\"" + generate_run_filepath(manalysis.locus_file_path) + "\"";
            }
            if (manalysis.create_trait_file_node)
            {  str = str + " " + "-m " + "\"" + generate_run_filepath(manalysis.trait_file_path) + "\"";
            }
            if (manalysis.create_type_file_node) {
              str = str + " " + "-m " + "\"" + generate_run_filepath(manalysis.type_file_path) + "\"";
            }
            if (manalysis.create_genome_file_node) {
              str = str + " " + "-g " + "\"" + generate_run_filepath(manalysis.genome_file_path) + "\"";
            }
          }
          else {
            str = "mlod";

            if (manalysis.get_para_path() != null) {
              str = str + " " + "-p " + manalysis.get_para_path();
            }
            if (manalysis.get_family_path() != null) {
              str = str + " " + "-d " + manalysis.get_family_path();
            }
            if (manalysis.create_locus_file_node) {
              str = str + " " + "-l " + manalysis.locus_file_path;
            }
            if (manalysis.create_trait_file_node) {
              str = str + " " + "-m " + manalysis.trait_file_path;
            }
            if (manalysis.create_type_file_node) {
              str = str + " " + "-m " + manalysis.type_file_path;
            }
            if (manalysis.create_genome_file_node) {
              str = str + " " + "-g " + manalysis.genome_file_path;
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          mlod_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();
        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetMLODblock(DataCollectionModel mlodData,
                             String Outputfilefilter, MLOD2 test2)
  {
    StringBuffer analysis_block = new StringBuffer();

    analysis_block.append("mlod");
    analysis_block.append(", out = \"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (mlodData.hasValue("Title"))
      analysis_block.append("title = " + "\""+ mlodData.getValue("Title").toString()+ "\""+ "\n");

    // region
    CheckableItem[] model = test2.jRegionComboBox.ListData;
    int n = model.length;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      if (item.isSelected()) {
        analysis_block.append("region = " + "\""+ item.toString() + "\""+ "\n");
      }
    }

    // trait marker
    model = test2.jTraitMarkerComboBox.ListData;
    n = model.length;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      if (item.isSelected()) {
        analysis_block.append("trait_marker = " + "\""+item.toString() + "\""+  "\n");
      }
    }

    if (mlodData.hasValue("max_size"))
      analysis_block.append("max_size = " + "\"" +
                            mlodData.getValue("max_size").toString() + "\"" +
                            "\n");

    analysis_block.append("scan_type = " + "\"" +
                          mlodData.getValue("scan_type").toString() + "\", ");
    analysis_block.append("distance = " + "\"" +
                          mlodData.getValue("distance").toString() + "\"" +
                          "\n");
    analysis_block.append("output_pedigrees = " +"\""+
                          mlodData.getValue("output_pedigree").toString()+"\""
                          + "\n");

    analysis_block.append("sample_detail = " +"\""+
                          mlodData.getValue("sample_detail").toString()+"\""
                          + "\n");

    analysis_block.append("}" + "\n");

    return analysis_block.toString();
  }

  public void RunLODLINK(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final LODLINK1 test1 = (LODLINK1) currentnode.component_vector.get(0);
    final LODLINK2 test2 = (LODLINK2) currentnode.component_vector.get(1);
    final sage_analysis_info lanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel lodlinkData = test1.Datamodel;
      final String Outputfilefilter;
      if (lodlinkData.hasValue("output_name"))
        Outputfilefilter = lodlinkData.getValue("output_name").toString();
      else
        Outputfilefilter = "lodlink";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("LODLINK Analysis");

      if (!lodlinkData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(lanalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!lodlinkData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(lanalysis.family_file_path);
        d1.pedLabel.setIcon(check);
      }

      if (!lodlinkData.hasValue("locus_path")) {
        d1.mlocusLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jLocusLabel.setText(lanalysis.locus_file_path);
        d1.mlocusLabel.setIcon(check);
      }

      d1.genomeLabel.setText("Trait locus file..");
      if (lanalysis.create_trait_file_node) {
        d1.jGenomeLabel.setText(lanalysis.trait_file_path);
        d1.genomeLabel.setIcon(check);
      }

      if (lanalysis.create_type_file_node) {
        d1.jTypeLabel.setText(lanalysis.type_file_path);
        d1.typeLabel.setIcon(check);
      }

      d1.setFileList(5);

      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetLODLINKblock(lodlinkData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();
          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.
                                          get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setLODLINKblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);
          }

          final Vector v = new Vector();
          final RunJobTask lodlink_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          lanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(lodlink_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "lodlink.exe";

            if (lanalysis.get_para_path() != null) {
              str = str + " " + "-p " +"\"" + generate_run_filepath(lanalysis.get_para_path()) + "\"";
            }
            if (lanalysis.get_family_path() != null) {
              str = str + " " + "-d " +"\"" +
                  generate_run_filepath(lanalysis.get_family_path()) + "\"";
            }
            if (lanalysis.create_locus_file_node) {
              str = str + " " + "-l " +"\"" +
                  generate_run_filepath(lanalysis.locus_file_path) + "\"";
            }
            if (lanalysis.create_trait_file_node) {
              str = str + " " + "-m " +"\"" +
                  generate_run_filepath(lanalysis.trait_file_path) + "\"";
            }
            if (lanalysis.create_type_file_node) {
              str = str + " " + "-m " +"\"" +
                  generate_run_filepath(lanalysis.type_file_path) + "\"";
            }

          }
          else {
            str = "lodlink";

            if (lanalysis.get_para_path() != null) {
              str = str + " " + "-p " + lanalysis.get_para_path();
            }
            if (lanalysis.get_family_path() != null) {
              str = str + " " + "-d " + lanalysis.get_family_path();
            }
            if (lanalysis.create_locus_file_node) {
              str = str + " " + "-l " + lanalysis.locus_file_path;
            }
            if (lanalysis.create_trait_file_node) {
              str = str + " " + "-m " + lanalysis.trait_file_path;
            }
            if (lanalysis.create_type_file_node) {
              str = str + " " + "-m " + lanalysis.type_file_path;
            }

          }


          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          lodlink_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();
        }

      }
      catch (Exception exception) {
        exception.printStackTrace();
      }

    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetLODLINKblock(DataCollectionModel lodlinkData,
                                String Outputfilefilter, LODLINK2 test2) {
    StringBuffer analysis_block = new StringBuffer();
    analysis_block.append("lodlink, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (lodlinkData.hasValue("Title")) {
      analysis_block.append("title = " + "\"" +
                            lodlinkData.getValue("Title").toString() + "\"" +
                            "\n");
    }

    if (lodlinkData.hasValue("Model")) {
      String type = lodlinkData.getValue("Model").toString();

      if (lodlinkData.hasValue("Model_" + type)) {
        analysis_block.append("model, " + type + " = " +"\""+
                              lodlinkData.getValue("Model_" + type).
                              toString() +"\""+ "\n");
      }
    }

    // linkage_tests
    analysis_block.append("linkage_tests = " + "\""+
                          lodlinkData.getValue("linkage_tests").toString()+"\"");
    analysis_block.append(" , sex_specific = " +"\""+
                          lodlinkData.getValue("linkage_sex_specific").toString()+"\"");
    analysis_block.append(" , homog = " +"\""+
                          lodlinkData.getValue("linkage_homog").toString() +"\""+"\n");

    //homog_tests
    analysis_block.append("homog_tests { " + "\n");
    analysis_block.append("smiths_test = " +"\""+
                          lodlinkData.getValue("smiths_test").toString()+"\"");
    analysis_block.append(", sex_specific = " +"\""+
                          lodlinkData.getValue("smiths_sex_specific").
                          toString() +"\""+ "\n");

    if (lodlinkData.hasValue("use_mortons_test")) {
      analysis_block.append("mortons_test = " +"\""+
                            lodlinkData.getValue("mortons_test").toString()+"\"");
      analysis_block.append(", sex_specific = " +"\""+
                            lodlinkData.getValue("mortons_sex_specific").
                            toString()+"\"");
      analysis_block.append("\n{" + "\n");
      analysis_block.append(lodlinkData.getValue("mortons_test_groups").
                            toString());
      analysis_block.append("}\n");
    }
    analysis_block.append("}\n");

    //lods
    analysis_block.append("lods { " + "\n");
    analysis_block.append("option = " +"\""+
                          lodlinkData.getValue("option").toString()+"\"" + "\n");
    analysis_block.append("sex_specific = " +"\""+
                          lodlinkData.getValue("option_sex_specific").
                          toString() +"\""+ "\n");
    if (lodlinkData.hasValue("use_male_female")) {
      analysis_block.append("male_female {" + "\n");
      analysis_block.append(lodlinkData.getValue("male_female_theta").toString() +
                            "\n");
      analysis_block.append("}\n");
    }

    if (lodlinkData.hasValue("use_average")) {
      analysis_block.append("average {" + "\n");
      analysis_block.append(lodlinkData.getValue("average_theta").toString() +
                            "\n");
      analysis_block.append("}\n");
    }
    analysis_block.append("}\n");

    analysis_block.append("genotypes = " +"\""+
                          lodlinkData.getValue("genotypes").toString()+"\"");
    analysis_block.append(", sex_specific = " +"\""+
                          lodlinkData.getValue("genotypes_sex_specific").
                          toString()+"\"" + "\n");
    analysis_block.append("}" + "\n");
    return analysis_block.toString();
  }



  public void RunFCOR(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final FCOR1 test1 = (FCOR1) currentnode.component_vector.get(0);
    final FCOR2 test2 = (FCOR2) currentnode.component_vector.get(1);
    final sage_analysis_info fanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {

      DataCollectionModel fcorData = test1.Datamodel;
      final String Outputfilefilter;
      if (fcorData.hasValue("output_name"))
        Outputfilefilter = fcorData.getValue("output_name").toString();
      else
        Outputfilefilter = "fcor";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("FCOR Analysis");

      if (!fcorData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(fanalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!fcorData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(fanalysis.family_file_path);
        d1.pedLabel.setIcon(check);
      }

      if (fcorData.hasValue("locus_path")) {
        d1.jLocusLabel.setText(fanalysis.locus_file_path);
        d1.mlocusLabel.setIcon(check);
      }

      d1.setFileList(3);

      try {
        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetFCORblock(fcorData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();

          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.
                                          get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setFCORblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);

          }

          final Vector v = new Vector();
          final RunJobTask fcor_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          fanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(fcor_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "fcor.exe";
            if (fanalysis.get_para_path() != null) {
              str = str + " " + "-p "+ "\"" +  generate_run_filepath(fanalysis.get_para_path()) + "\"";
            }
            if (fanalysis.get_family_path() != null) {
              str = str + " " + "-d "+ "\"" +
                  generate_run_filepath(fanalysis.get_family_path()) + "\"";
            }
            if (fanalysis.create_locus_file_node) {
              str = str + " " + "-l "+ "\"" + generate_run_filepath(fanalysis.locus_file_path) + "\"";
            }
          }
          else {
            str = "fcor";
            if (fanalysis.get_para_path() != null) {
              str = str + " " + "-p "+fanalysis.get_para_path();
            }
            if (fanalysis.get_family_path() != null) {
              str = str + " " + "-d "+fanalysis.get_family_path();
            }
            if (fanalysis.create_locus_file_node) {
              str = str + " " + "-l "+fanalysis.locus_file_path;
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          fcor_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }
              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetFCORblock(DataCollectionModel fcorData,
                             String Outputfilefilter, FCOR2 test2) {
    StringBuffer analysis_block = new StringBuffer();
    analysis_block.append("fcor, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    // trait
    String fcor_trait = new String();
    CheckableItem[] model = test2.jTraitComboBox.ListData;
    int n = model.length;
    int countsTrait = 0;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) model[i];
      if (item.isSelected()) {
        analysis_block.append("trait = " + "\""+ item.toString() + "\""+ "\n");
        fcor_trait = fcor_trait + item.toString() + ", ";
        countsTrait++;
      }
    }

    analysis_block.append("interclass_weight = " + "\"" +
                          fcorData.getValue("interclass_weight").toString() +
                          "\"" + "\n");
    analysis_block.append("intraclass_weight = " + "\"" +
                          fcorData.getValue("intraclass_weight").toString() +
                          "\"" + "\n");

    if (fcorData.hasValue("cov")) {
      analysis_block.append(fcorData.getValue("var_cov").toString());
      analysis_block.append("\n");
    }

    analysis_block.append("standard_error = " +"\""+
                          fcorData.getValue("standard_error").toString()+"\"");

    if (fcorData.getValue("standard_error").toString().compareTo("true") == 0) {
      if (fcorData.hasValue("conservative")) {
        analysis_block.append(", conservative");
      }
      if (fcorData.hasValue("pairs")) {
        analysis_block.append(", pairs");
      }
    }

    analysis_block.append("\n");

    analysis_block.append("type = " + "\"" +
                          fcorData.getValue("type").toString() + "\"");
    if (fcorData.hasValue("tabular")) {
      analysis_block.append(", tabular");
    }
    analysis_block.append("\n");

    analysis_block.append("sex_name = " + "\"" +
                          fcorData.getValue("sex_name").toString() + "\"" +
                          "\n");

    // homogeneity test
    String option = fcorData.getValue("homogeneity_test").toString();
    if(option.compareTo("1")==0)
    {
      analysis_block.append("homogeneity_test = \"true\", all" +"\n");
    }
    else if(option.compareTo("2")==0)
    {
      analysis_block.append("homogeneity_test = \"true\", each" +"\n");
    }

    if (fcorData.hasValue("generation_limit")) {
      analysis_block.append("generation_limit = " + "\"" +
                            fcorData.getValue("generation_limit").toString() +
                            "\"" + "\n");
    }
    analysis_block.append("}" + "\n");

    return analysis_block.toString();
  }



  public void RunTDTEX(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final TDTEX1 test1 = (TDTEX1) currentnode.component_vector.get(0);
    final TDTEX2 test2 = (TDTEX2) currentnode.component_vector.get(1);
    final sage_analysis_info tanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel tdtexData = test1.Datamodel;
      final String Outputfilefilter;
      if (tdtexData.hasValue("output_name"))
        Outputfilefilter = tdtexData.getValue("output_name").toString();
      else
        Outputfilefilter = "tdtex";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("TDTEX Analysis");

      if (!tdtexData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jParaLabel.setText(tanalysis.para_file_path);
          d1.parLabel.setIcon(check);//
      }

      if (!tdtexData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jPediLabel.setText(tanalysis.family_file_path);
          d1.pedLabel.setIcon(check);
      }

      d1.setFileList(2);

      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetTDTEXblock(tdtexData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();
          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setTDTEXblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);
          }

          final Vector v = new Vector();
          final RunJobTask tdtex_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          tanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(tdtex_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "tdtex.exe";

            if (tanalysis.get_para_path() != null) {
              str = str + " " + "-p " +"\"" + generate_run_filepath(tanalysis.get_para_path()) + "\"";
            }
            if (tanalysis.get_family_path() != null) {
              str = str + " " + "-d " +"\"" +
                  generate_run_filepath(tanalysis.get_family_path()) + "\"";
            }
          }
          else {
            str = "tdtex";

            if (tanalysis.get_para_path() != null) {
              str = str + " " + "-p "+tanalysis.get_para_path();
            }
            if (tanalysis.get_family_path() != null) {
              str = str + " " + "-d "+tanalysis.get_family_path();
            }
          }


          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          tdtex_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();
        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetTDTEXblock(DataCollectionModel tdtexData,
                              String Outputfilefilter, TDTEX2 test2) {
    StringBuffer analysis_block = new StringBuffer();

    analysis_block.append("tdtex, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    CheckableItem[] markermodel = test2.jMarkerComboBox.ListData;
    int n = markermodel.length;
    for (int i = 0; i < n; i++) {
      CheckableItem item = (CheckableItem) markermodel[i];
      if (item.isSelected()) {
        analysis_block.append("marker = " + "\"" + item.toString() + "\"" +
                              "\n");
      }
    }

    if (tdtexData.hasValue("trait")) {
      analysis_block.append("trait = " + "\""+ tdtexData.getValue("trait").toString() + "\""+
                            "\n");
    }

    if (tdtexData.hasValue("parental_trait")) {
      analysis_block.append("parental_trait = " + "\""+
                            tdtexData.getValue("parental_trait").toString() + "\""+
                            "\n");
    }

    if (tdtexData.hasValue("max_children")) {
      if (tdtexData.getValue("max_children").toString().compareTo(
          "unlimited") == 0) {
        analysis_block.append("max_children = " + "\""+
                              tdtexData.getValue("max_children").toString() + "\""+
                              "\n");
      }
      if (tdtexData.getValue("max_children").toString().compareTo("none") ==
          0) {
        analysis_block.append("max_children = " + "\""+
                              tdtexData.getValue("max_children").toString() + "\""+
                              "\n");
      }
      else if (tdtexData.getValue("max_children").toString().compareTo(
          "number") == 0) {
        analysis_block.append("max_children = " + "\""+
                              tdtexData.getValue("max_children_value").
                              toString() + "\""+ "\n");
      }
    }

    if (tdtexData.hasValue("max_sib_pairs")) {
      if (tdtexData.getValue("max_sib_pairs").toString().compareTo(
          "unlimited") == 0) {
        analysis_block.append("max_sib_pairs = " + "\""+
                              tdtexData.getValue("max_sib_pairs").toString()+ "\"" +
                              "\n");
      }
      if (tdtexData.getValue("max_sib_pairs").toString().compareTo("none") ==
          0) {
        analysis_block.append("max_sib_pairs = " + "\""+
                              tdtexData.getValue("max_sib_pairs").toString() + "\""+
                              "\n");
      }
      else if (tdtexData.getValue("max_sib_pairs").toString().compareTo(
          "number") == 0) {
        analysis_block.append("max_sib_pairs = " + "\""+
                              tdtexData.getValue("max_sib_pairs_value").
                              toString() + "\""+ "\n");
      }
    }

    analysis_block.append("sample = " + "\""+tdtexData.getValue("sample").toString() + "\""+ "\n");
    analysis_block.append("sex_differential = " + "\""+
                          tdtexData.getValue("sex_differential").toString() + "\""+
                          "\n");

    CheckableItem[] model = test2.jComboBox3.ListData;
    CheckableItem item = (CheckableItem) model[0];
    if (item.isSelected()) {
      analysis_block.append("skip_permutation_test = \"true\"" + "\n");
    }

    item = (CheckableItem) model[1];
    if (item.isSelected()) {
      analysis_block.append("skip_mc_test = \"true\"" + "\n");
    }

    item = (CheckableItem) model[2];
    if (item.isSelected()) {
      analysis_block.append("skip_mcmh_test = \"true\"" + "\n");
    }

    analysis_block.append("}" + "\n");
    return analysis_block.toString();
  }



  public void RunSIBPAL(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final SIBPAL1 test1 = (SIBPAL1) currentnode.component_vector.get(0);
    final SIBPAL2 test2 = (SIBPAL2) currentnode.component_vector.get(1);
    final SIBPAL3 test3 = (SIBPAL3) currentnode.component_vector.get(2);
    final SIBPAL4 test4 = (SIBPAL4) currentnode.component_vector.get(3);
    final sage_analysis_info sanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    final DataCollectionModel sibpalData = test1.Datamodel;
    if (action.compareTo("Run") == 0) {
      final String Outputfilefilter;
      if (sibpalData.hasValue("output_name"))
        Outputfilefilter = sibpalData.getValue("output_name").toString();
      else
        Outputfilefilter = "sibpal";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("SIBPAL Analysis");

      if (!sibpalData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jParaLabel.setText(sanalysis.para_file_path);
          d1.parLabel.setIcon(check);
      }

      if (!sibpalData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jPediLabel.setText(sanalysis.family_file_path);
          d1.pedLabel.setIcon(check);
      }

      if (!sibpalData.hasValue("ibd_path")) {
        d1.mlocusLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jLocusLabel.setText(sanalysis.ibd_file_path);
          d1.mlocusLabel.setIcon(check);
      }

      d1.mlocusLabel.setText("IBD sharing File..");

      d1.setFileList(3);

      try {
        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetSIBPALblock(sibpalData, Outputfilefilter, test3, test4);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();
          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.
                                          get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setSIBPALblock(block, test2, test3, test4);
                }
            };
             SwingUtilities.invokeLater(setinfo);
          }

          final Vector v = new Vector();
          final RunJobTask fcor_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          sanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(fcor_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "sibpal.exe";

            if (sanalysis.get_para_path() != null) {
              str = str + " " + "-p " +"\"" +
                  generate_run_filepath(sanalysis.get_para_path()) + "\"";
            }
            if (sanalysis.get_family_path() != null) {
              str = str + " " + "-d " +"\"" +
                  generate_run_filepath(sanalysis.get_family_path()) + "\"";
            }

            if (sanalysis.create_locus_file_node) {
              str = str + " " + "-i " +"\"" +
                  generate_run_filepath(sanalysis.get_ibd_path()) + "\"";
            }
          }
          else {
            str = "sibpal";

            if (sanalysis.get_para_path() != null) {
              str = str + " " + "-p " +sanalysis.get_para_path();
            }
            if (sanalysis.get_family_path() != null) {
              str = str + " " + "-d " +sanalysis.get_family_path();
            }

            if (sanalysis.create_locus_file_node) {
              str = str + " " + "-i " +sanalysis.get_ibd_path();
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          fcor_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);

              if(test2.jRadioButton3.isSelected())
                  WriteMeanTestScript(outputf, sanalysis.get_ibd_path(), sibpalData);
              if(test2.jRadioButton4.isSelected())
                  WriteTraitRegressionScript(outputf, sanalysis.get_ibd_path(), sibpalData);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetSIBPALblock(DataCollectionModel sibpalData,
                               String Outputfilefilter,
                               SIBPAL3 test3, SIBPAL4 test4) {
    StringBuffer analysis_block = new StringBuffer();

    analysis_block.append("sibpal, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

//mean_tests
    if (sibpalData.hasValue("mean_tests")) {
      analysis_block.append("mean_test" + "\n");
      analysis_block.append("{" + "\n");

      CheckableItem[] model = test3.jTraitComboBox.ListData;
      int n = model.length;
      for (int i = 0; i < n; i++) {
        CheckableItem item = (CheckableItem) model[i];
        if (item.isSelected()) {
          analysis_block.append("trait = " + "\""+ item.toString() + "\""+ "\n");
        }
      }

      // subset
      model = test3.jSubsetComboBox.ListData;
      n = model.length;
      for (int i = 0; i < n; i++) {
        CheckableItem item = (CheckableItem) model[i];
        if (item.isSelected()) {
          analysis_block.append("subset = " + "\""+ item.toString() + "\""+ "\n");
        }
      }
      // marker
      model = test3.jMarkerComboBox.ListData;
      n = model.length;
      int marker_count=0;
      for (int i = 0; i < n; i++) {
        CheckableItem item = (CheckableItem) model[i];
        if (item.isSelected()) {
          analysis_block.append("marker = " + "\""+ item.toString() + "\""+ "\n");
          marker_count++;
        }
      }
      sibpalData.setValue("mean_test_marker_count", marker_count);

      //w1
      if(test3.jTextField1.getText().length()>0)
        analysis_block.append("w1 = " + "\""+
                            test3.jTextField1.getText().trim() + "\""+ "\n");

      //wide_out
      analysis_block.append("wide_out = " + "\""+ test3.jCheckBox1.isSelected() + "\""+
                            "\n");
      //export_output
      analysis_block.append("export_output = " + "\""+
                            "true" + "\""+//test3.jCheckBox2.isSelected()
                            "\n");

      //pval_scientific_notation
      analysis_block.append("pval_scientific_notation = " + "\""+
                            test3.jCheckBox3.isSelected() + "\""+
                            "\n");

      analysis_block.append("}" + "\n");
    }

// trait regression
    if (sibpalData.hasValue("trait_regression")) {

      analysis_block.append("trait_regression, ");
      analysis_block.append(sibpalData.getValue("trait_regression_default").
                            toString() + "\n");
      analysis_block.append("{" + "\n");

      // trait
      if (sibpalData.hasValue("trait")) {
        analysis_block.append(sibpalData.getValue("Trait_info").toString());
        analysis_block.append("\n");
      }

      // marker
      CheckableItem[] model = test4.jMarkerComboBox.ListData;
      int n = model.length;
      int marker_count = 0;

      for (int i = 0; i < n; i++) {
        CheckableItem item = (CheckableItem) model[i];
        if (item.isSelected()) {
          analysis_block.append("marker = " + "\""+ item.toString()+ "\"");
          marker_count++;

          if (item.isSelected2()) {
            analysis_block.append(", dominance");
          }
          analysis_block.append("\n");
        }
      }
      sibpalData.setValue("trait_reg_marker_count", marker_count);

      //covariate
      if (sibpalData.hasValue("cov")) {
        analysis_block.append(sibpalData.getValue("Cov_info").toString());
        analysis_block.append("\n");
      }

      //interaction
      if (sibpalData.hasValue("interaction")) {
        analysis_block.append(sibpalData.getValue("interaction_info").toString());
        analysis_block.append("\n");
      }

      // others
      analysis_block.append("identity_weights = " + "\""+
                            sibpalData.getValue("identity_weights").
                            toString() + "\""+
                            "\n");
      analysis_block.append("robust_variance = " + "\""+
                            sibpalData.getValue("robust_variance").toString() + "\""+
                            "\n");

      analysis_block.append("wide_out = " + "\""+
                            sibpalData.getValue("wide_out").toString() + "\""+
                            "\n");
      analysis_block.append("regression_method = " + "\""+
                            sibpalData.getValue("regression_method").
                            toString() + "\""+ "\n");

      //use_pair
      analysis_block.append("use_pairs = " + "\"" +
                            sibpalData.getValue("use_pair").toString() + "\"" + "\n");

      if(sibpalData.getValue("use_pair").toString().compareTo("full")==0)
      {
          //w1
          if (test4.w1TextField.getText().length() > 0)
              analysis_block.append("w1 = " + "\"" +
                                    test4.w1TextField.getText().trim() + "\"" + "\n");
      }

      analysis_block.append("skip_uninformative_pairs = " + "\""+
                            sibpalData.getValue("skip_uninformative_pairs").
                            toString() + "\""+ "\n");

      analysis_block.append("export_output = " + "\""+
                            "true" + "\""+//sibpalData.getValue("export_output_tr").toString()
                            "\n");

      //pval_scientific_notation
      analysis_block.append("pval_scientific_notation = " + "\""+
                            sibpalData.getValue("pval_scientific_notation_tr").toString() + "\""+
                            "\n");

      //print_design_matrix
      if (sibpalData.hasValue("print_design_matrix"))
      {
          analysis_block.append("print_design_matrix = " + "\"" +
                                sibpalData.getValue("print_design_matrix").toString() +
                                "\"");
          if(test4.rowTextField.getText().trim().length()>0)
              analysis_block.append(", rows = " + "\"" +
                                    test4.rowTextField.getText().trim() +
                                    "\"");
          analysis_block.append("\n");
      }
        //print_correlation_matrix
      if (sibpalData.hasValue("print_correlation_matrix"))
        analysis_block.append("print_correlation_matrix = " + "\""+
                              sibpalData.getValue("print_correlation_matrix").toString() + "\""+ "\n");

    //print_correlation_matrix
    if (sibpalData.hasValue("print_QLS"))
        analysis_block.append("print_QLS = " + "\""+
                          sibpalData.getValue("print_QLS").toString() + "\""+ "\n");


      //subset
      model = test4.jSubsetComboBox.ListData;
      n = model.length;
      for (int i = 0; i < n; i++) {
        CheckableItem item = (CheckableItem) model[i];
        if (item.isSelected()) {
          analysis_block.append("subset = " + "\""+ item.toString() + "\""+ "\n");
        }
      }

      if (sibpalData.hasValue("compute_empirical_pvalues")) {
        analysis_block.append("compute_empirical_pvalues = " + "\""+
                              sibpalData.getValue("compute_empirical_pvalues").toString()+ "\"");

        if (sibpalData.getValue("compute_empirical_pvalues").toString().compareTo("true") == 0) {
          if (sibpalData.hasValue("cep_threshold")) {
            analysis_block.append(", threshold = " + "\""+
                                  sibpalData.getValue("cep_threshold").
                                  toString()+ "\"");
          }

          if (sibpalData.hasValue("cep_replicates")) {
            analysis_block.append(", replicates = " + "\""+
                                  sibpalData.getValue("cep_replicates").
                                  toString()+ "\"");
          }

          if (sibpalData.hasValue("cep_max_r")) {
            analysis_block.append(", max_replicates = " + "\""+
                                  sibpalData.getValue("cep_max_r").toString()+ "\"");
          }

          if (sibpalData.hasValue("cep_width")) {
            analysis_block.append(", width = " + "\""+
                                  sibpalData.getValue("cep_width").toString()+ "\"");
          }

          if (sibpalData.hasValue("cep_confidence")) {
            analysis_block.append(", confidence = " + "\""+
                                  sibpalData.getValue("cep_confidence").
                                  toString()+ "\"");
          }
        }
        analysis_block.append("\n");
      }
      analysis_block.append("}" + "\n");
    }
    analysis_block.append("}" + "\n");

    return analysis_block.toString();
  }


  public void WriteLODPALScript(IconNode output_f, String ibdpath, DataCollectionModel lodpalData, String block){
      String rfilepath = null;
      String name = "";
      String resultfile = "";
      String parentpath = "";
      for (int i = 0; i < output_f.getChildCount(); i++) {
          NodeInfo io = (NodeInfo)((IconNode) output_f.getChildAt(i)).getUserObject();
          if (io.file != null && io.nodetype.compareTo("Result File") == 0)
          {
              if(io.file.getName().endsWith(".out"))
              {
                  name = io.file.getName().substring(0, io.file.getName().indexOf("."));
                  parentpath = io.file.getParentFile().toString();
                  rfilepath = parentpath + System.getProperty("file.separator")+name+".r";
                  resultfile = io.file.getPath();
              }
          }
      }

      if (rfilepath != null) {
          try {
              int i=0;
              int j=0;
              Vector traitNames = new Vector();

              String[] traitlists = block.split("\n");
              for(i=0;i<traitlists.length;i++)
              {
                  String each[] = traitlists[i].split(",");
                  if(each.length>1)
                  {
                      for(j=0;j<each.length;j++)
                      {
                          if(each[j].startsWith("trait"))
                          {
                              String temp[] = each[j].split("=");
                              if(temp.length>1)
                              {
                                  traitNames.add(temp[1].replaceAll("\"","").trim());
                              }
                          }
                      }
                  }
                  else
                  {
                      if(each[0].startsWith("trait"))
                          {
                              String temp[] = each[0].split("=");
                              if(temp.length>1)
                              {
                                  traitNames.add(temp[1].replaceAll("\"","").trim());
                              }
                          }
                  }
                }

              int trait_counts = traitNames.size();

              String line="";
              int markernum = 0;

              String first_marker_name = "";
              if (lodpalData.hasValue("lodpal_first_marker_name"))
                  first_marker_name = lodpalData.getValue("lodpal_first_marker_name").toString();
              else
              {
                  FileReader fribd = new FileReader(ibdpath);
                  BufferedReader bribd = new BufferedReader(fribd);
                  boolean count = false;
                  while ((line = bribd.readLine()) != null) {
                      if(line.trim().compareTo("MARKERS")==0)
                      {
                          count = true;
                      }
                      if(count)
                          markernum++;

                      if(markernum==3)
                      {
                          first_marker_name = line.substring(0, line.indexOf(" "));
                      }

                      if(line.trim().startsWith("#      Pedigree       Ind 1"))
                          break;
                  }
              }

              markernum = markernum-5;

              if (lodpalData.hasValue("lodpal_marker_count"))
              {
                 if(lodpalData.getValue("lodpal_marker_count").toString().compareTo("0")!=0)
                 {
                     markernum= Integer.parseInt(lodpalData.getValue("lodpal_marker_count").toString());
                 }
             }

              FileReader fr = new FileReader(resultfile);
              BufferedReader br = new BufferedReader(fr);

              int traitindex = 0;

              Vector foslist = new Vector();
              for(i=0;i<trait_counts;i++)
              {
                  foslist.add(new FileWriter(parentpath + System.getProperty("file.separator") + traitNames.get(i).toString()+".txt"));
              }

              FileWriter fos1 = null;

              int markernumindex=0;

              boolean write = false;
              while ( (line = br.readLine()) != null) {
                  if(line.trim().startsWith(first_marker_name))
                  {
                      write = true;
                  }

                  if(write)
                  {
                      fos1 = (FileWriter)foslist.get(traitindex);
                      fos1.write(line+"\n");
                      markernumindex++;
                  }

                  if(markernumindex == markernum)
                  {
                      write = false;
                      markernumindex = 0;
                      traitindex++;

                      fos1.close();
                      fos1 = null;

                      if(traitindex < trait_counts)
                      fos1 = (FileWriter)foslist.get(traitindex);
                  }
              }

              FileWriter fos = new FileWriter(rfilepath);
              fos.write("# ---------------------------------------------"+"\n");
              fos.write("# Read data"+"\n");
              fos.write("# ---------------------------------------------"+"\n");

              fos.write("traitlist = c(");
              for(i=0;i<trait_counts-1;i++)
              {
                  fos.write("'"+traitNames.get(i).toString().trim()+"',");
              }
              fos.write("'"+traitNames.get(trait_counts-1).toString().trim()+"')");
              fos.write("\n");

              for(i=0;i<trait_counts;i++)
              {
                  fos.write("filepath"+(i+1)+" = " + " \"" + generate_run_filepath(parentpath + System.getProperty("file.separator") + traitNames.get(i).toString()+".txt") + "\""+"\n");
                  fos.write("data"+(i+1)+" <- read.table("+"filepath"+(i+1)+", header=FALSE, fill = TRUE, stringsAsFactors=FALSE)"+"\n" );
                  fos.write("\n");
              }

              fos.write("# ---------------------------------------------"+"\n");
              fos.write("# Plot & Save"+"\n");
              fos.write("# ---------------------------------------------"+"\n");
              fos.write("windows(record=TRUE, width=2000, height=1600)"+"\n");
              fos.write("par(mfrow=c("+trait_counts+",1))"+"\n");
              fos.write("\n");

              for (i = 0; i < trait_counts; i++)
              {
                  fos.write("plot(data"+(i + 1) +"$V2, type=\"l\", axes=T, ann=T, xlab=\"Marker\", ylab=\"LOD Score\", col=\"red\")"+"\n");
                  fos.write("title(\"trait="+traitNames.get(i)+"\")"+"\n");
                }

              fos.write("savepath = paste(\""+generate_run_filepath(resultfile)+"\", \"_plot.pdf\",sep=\"\");"+"\n");
              fos.write("savePlot(savepath ,type=\"pdf\")"+"\n");

              fos.close();

              NodeInfo filenode1 = new NodeInfo(name + ".r", "R File", new File(rfilepath));
              IconNode rfilenode1 = new IconNode(filenode1, "R File");
              addObject(rfilenode1, output_f, true);

              Runtime.getRuntime().exec("Rscript" + " \"" + rfilepath + "\"");

              NodeInfo filenode2 = new NodeInfo(name + ".out_plot.pdf", "PDF File", new File(resultfile + "_plot.pdf"));
              IconNode rfilenode2 = new IconNode(filenode2, "PDF File");
              addObject(rfilenode2, output_f, true);

          } catch (Exception excep) {
              excep.printStackTrace();
          }
      }
  }

  public void WriteTraitRegressionScript(IconNode output_f, String ibdpath, DataCollectionModel sibpalData){
      String rfilepath = null;
      String name = "";
      String resultfile = "";
      for (int i = 0; i < output_f.getChildCount(); i++) {
          NodeInfo io = (NodeInfo)((IconNode) output_f.getChildAt(i)).getUserObject();
          if (io.file != null && io.nodetype.compareTo("Result File") == 0)
          {
              if(io.file.getName().endsWith(".treg_export"))
              {
                  name = io.file.getName().substring(0, io.file.getName().indexOf("."));
                  rfilepath = io.file.getParentFile()+ System.getProperty("file.separator")+name+".r";
                  resultfile = io.file.getPath();
              }
          }
      }

      if (rfilepath != null) {
          try {
              int i=0;
              int j=0;
              Vector traitNames = new Vector();
              Vector covNames = new Vector();

              // trait
              if (sibpalData.hasValue("trait")) {
                  String traitInfo = sibpalData.getValue("Trait_info").toString();

                  String[] traitlists = traitInfo.split("\n");
                  for(i=0;i<traitlists.length;i++)
                  {
                      String each[] = traitlists[i].split(",");
                      if(each.length>1)
                      {
                          for(j=0;j<each.length;j++)
                          {
                              if(each[j].startsWith("trait"))
                              {
                                  String temp[] = each[j].split("=");
                                  if(temp.length>1)
                                  {
                                      traitNames.add(temp[1].replaceAll("\"","").trim());
                                  }
                              }
                          }
                      }
                      else
                      {
                          if(each[0].startsWith("trait"))
                              {
                                  String temp[] = each[0].split("=");
                                  if(temp.length>1)
                                  {
                                      traitNames.add(temp[1].replaceAll("\"","").trim());
                                  }
                              }
                      }
                  }
              }
              //covariate
              if (sibpalData.hasValue("cov")) {
                  String covInfo = sibpalData.getValue("Cov_info").toString();
                  String[] covlists = covInfo.split("\n");
                  for(i=0;i<covlists.length;i++)
                  {
                      String each[] = covlists[i].split(",");
                      if(each.length>1)
                      {
                          for(j=0;j<each.length;j++)
                          {
                              if(each[j].startsWith("covariate"))
                              {
                                  String temp[] = each[j].split("=");
                                  if(temp.length>1)
                                  {
                                      covNames.add(temp[1].replaceAll("\"","").trim());
                                  }
                              }
                          }
                      }
                  }
              }

              int trait_counts = traitNames.size();
              int covariate_counts = covNames.size();

              FileWriter fos = new FileWriter(rfilepath);
              fos.write("# ---------------------------------------------"+"\n");
              fos.write("# Read data"+"\n");
              fos.write("# ---------------------------------------------"+"\n");
              fos.write("filepath = " + " \"" + generate_run_filepath(resultfile) + "\""+"\n");
              fos.write("data <- read.table(filepath, header=TRUE, fill = TRUE, stringsAsFactors=FALSE)"+"\n");
              fos.write("\n");

              fos.write("traitlist = c(");
              for(i=0;i<trait_counts-1;i++)
              {
                  fos.write("'"+traitNames.get(i).toString().trim()+"',");
              }
              fos.write("'"+traitNames.get(trait_counts-1).toString().trim()+"')");
              fos.write("\n");

              if(covariate_counts >0)
              {
                  fos.write("covlist = c(");
                  for(i=0;i<covariate_counts-1;i++)
                  {
                      fos.write("'"+covNames.get(i).toString().trim()+"',");
                  }
                  fos.write("'"+covNames.get(covariate_counts-1).toString().trim()+"')");
                  fos.write("\n");
              }

              if(sibpalData.getValue("trait_reg_marker_count").toString().compareTo("0")==0)
              {
                  fos.write("ibdfile = \""+generate_run_filepath(ibdpath) + "\""+"\n");
                  fos.write("ibdList <- list()"+"\n");
                  fos.write("con  <- file(ibdfile , open = \"r\")"+"\n");
                  fos.write("while (length(oneLine <- readLines(con)) > 0) {"+"\n");
                  fos.write("ibdList <- c(ibdList ,oneLine)"+"\n");
                  fos.write("} "+"\n");
                  fos.write("close(con)"+"\n");

                  fos.write("startindex = charmatch(\"MARKERS\",ibdList)+2"+"\n");
                  fos.write("endindex = grep(\"f1m-f1p\",ibdList)-3"+"\n");
                  fos.write("t = ibdList[startindex:endindex]"+"\n");
                  fos.write("numofmarker = length(t)"+"\n");
              }
              else
              {
                  fos.write("numofmarker  = "+sibpalData.getValue("trait_reg_marker_count").toString()+"\n");
              }
              fos.write("\n");

              if (covariate_counts > 0) {
                  for (i = 0; i < trait_counts; i++) {
                      String min_max = "";
                      fos.write("trait" + (i + 1) +  " = subset(data, Trait==traitlist[" + (i + 1) + "])"+"\n");

                      String cov_term = "";
                      for (j = 0; j < covariate_counts; j++) {
                          cov_term = cov_term + "IndependentVar==covlist[" + (j + 1) + "]"+"|";
                          fos.write("trait" + (i + 1) + "cov" + (j + 1) + " = subset(trait" + (i + 1) + ", IndependentVar==covlist[" + (j + 1) + "])"+"\n");
                          fos.write("trait" + (i + 1) +  "cov" + (j + 1) + "p = -log10(as.numeric(trait" + (i + 1) + "cov" + (j + 1)+"$Nom.P.value))"+"\n");
                          min_max = min_max + "trait" + (i + 1) +  "cov" + (j + 1) + "p,";
                      }
                      cov_term = cov_term.substring(0, cov_term.length()-1);

                      fos.write("trait" + (i + 1) + "markeronly = subset(trait" + (i + 1) + ", !("+cov_term+"))"+"\n");

                      fos.write("trait" + (i + 1) +  "markerp = -log10(as.numeric(trait" + (i + 1) + "markeronly$Nom.P.value))"+"\n");
                      min_max = min_max + "trait" + (i + 1) +  "markerp";

                      fos.write("trait" + (i + 1)+"_max = c()"+"\n");
                      fos.write("trait" + (i + 1)+"_max[1:numofmarker] = max("+min_max+")"+"\n");
                      fos.write("trait" + (i + 1)+"_max[1] = min("+min_max+")"+"\n");

                      fos.write("\n");
                  }


              } else { //no covariate
                  for (i = 0; i < trait_counts; i++) {
                      fos.write("trait" + (i + 1) +  " = subset(data, Trait==traitlist[" + (i + 1) + "])"+"\n");
                      fos.write("trait" + (i + 1) +  "markerp = -log10(as.numeric(trait" + (i + 1) + "$Nom.P.value))"+"\n");
                  }
              }


              fos.write("# ---------------------------------------------"+"\n");
              fos.write("# Plot & Save"+"\n");
              fos.write("# ---------------------------------------------"+"\n");
              fos.write("windows(record=TRUE, width=2000, height=1600)"+"\n");
              fos.write("par(mfrow=c("+trait_counts+","+(covariate_counts+1)+"))"+"\n");
              fos.write("\n");

              if (covariate_counts > 0) {


                  for (i = 0; i < trait_counts; i++)
                  {
                      fos.write("plot(trait"+(i + 1) +"_max, type=\"n\", axes=T, ann=T, xlab=\"TestNo\",sub=\"marker\", ylab=\"-log10P\")"+"\n");

                      fos.write("lines(trait"+(i + 1) +"markerp, col=\"red\")"+"\n");
                      fos.write("title(\"trait="+traitNames.get(i)+"\")"+"\n");

                      for (j = 0; j < covariate_counts; j++) {
                          fos.write("plot(trait"+(i + 1) +"_max, type=\"n\", axes=T, ann=T, xlab=\"TestNo\",sub=covlist["+(j+1)+"], ylab=\"-log10P\")"+"\n");

                          fos.write("lines(trait"+(i + 1) +"cov"+ (j + 1) + "p, col=\"red\")"+"\n");
                          fos.write("\n");
                      }
                  }
              }
              else
              {
                  for (i = 0; i < trait_counts; i++)
                  {
                      fos.write("plot(trait"+(i + 1) +"markerp, type=\"l\", axes=T, ann=T, xlab=\"TestNo : Marker\", ylab=\"-log10P\", col=\"red\")"+"\n");
                      fos.write("title(\"trait="+traitNames.get(i)+"\")"+"\n");
                  }
              }

              fos.write("savepath = paste(filepath, \"_plot.pdf\",sep=\"\");"+"\n");
              fos.write("savePlot(savepath ,type=\"pdf\")"+"\n");

              fos.close();

              NodeInfo filenode1 = new NodeInfo(name + ".r", "R File", new File(rfilepath));
              IconNode rfilenode1 = new IconNode(filenode1, "R File");
              addObject(rfilenode1, output_f, true);

              Runtime.getRuntime().exec("Rscript" + " \"" + rfilepath + "\"");

              NodeInfo filenode2 = new NodeInfo(name + ".treg_export_plot.pdf", "PDF File", new File(resultfile + "_plot.pdf"));
              IconNode rfilenode2 = new IconNode(filenode2, "PDF File");
              addObject(rfilenode2, output_f, true);

          } catch (Exception excep) {
              excep.printStackTrace();
          }
      }

  }


  public void WriteMeanTestScript(IconNode output_f, String ibdpath, DataCollectionModel sibpalData){
          String rfilepath = null;
          String name = "";
          String resultfile = "";
          for (int i = 0; i < output_f.getChildCount(); i++) {
              NodeInfo io = (NodeInfo)((IconNode) output_f.getChildAt(i)).getUserObject();
              if (io.file != null && io.nodetype.compareTo("Result File") == 0)
              {
                  if(io.file.getName().endsWith(".mean_export"))
                  {
                      name = io.file.getName().substring(0, io.file.getName().indexOf("."));
                      rfilepath = io.file.getParentFile()+ System.getProperty("file.separator")+name+".r";
                      resultfile = io.file.getPath();
                  }
              }
          }

          if (rfilepath != null) {
              try {

                  FileWriter fos = new FileWriter(rfilepath);
                  fos.write("# ---------------------------------------------"+"\n");
                  fos.write("# Read data"+"\n");
                  fos.write("# ---------------------------------------------"+"\n");
                  fos.write("filepath = " + " \"" + generate_run_filepath(resultfile) + "\""+"\n");
                  fos.write("data <- read.table(filepath, header=TRUE, fill = TRUE, stringsAsFactors=FALSE)"+"\n");
                  fos.write("\n");

                  if(sibpalData.getValue("mean_test_marker_count").toString().compareTo("0")==0)
                  {
                      fos.write("ibdfile = \""+generate_run_filepath(ibdpath) + "\""+"\n");
                      fos.write("ibdList <- list()"+"\n");
                      fos.write("con  <- file(ibdfile , open = \"r\")"+"\n");
                      fos.write("while (length(oneLine <- readLines(con)) > 0) {"+"\n");
                      fos.write("ibdList <- c(ibdList ,oneLine)"+"\n");
                      fos.write("} "+"\n");
                      fos.write("close(con)"+"\n");

                      fos.write("startindex = charmatch(\"MARKERS\",ibdList)+2"+"\n");
                      fos.write("endindex = grep(\"f1m-f1p\",ibdList)-3"+"\n");
                      fos.write("t = ibdList[startindex:endindex]"+"\n");
                      fos.write("numofmarker = length(t)"+"\n");
                  }
                  else
                  {
                      fos.write("numofmarker  = "+sibpalData.getValue("mean_test_marker_count").toString()+"\n");
                  }
                  fos.write("\n");

                  fos.write("pi0_fullsib = data$pi_0_Estimate[1:numofmarker]"+"\n");
                  fos.write("pi1_fullsib = data$pi_1_Estimate[1:numofmarker]"+"\n");
                  fos.write("pi2_fullsib = data$pi_2_Estimate[1:numofmarker]"+"\n");
                  fos.write("\n");

                  fos.write("fullsib_max = max(pi0_fullsib, pi1_fullsib, pi2_fullsib)"+"\n");
                  fos.write("fullsib_min = min(pi0_fullsib, pi1_fullsib, pi2_fullsib)"+"\n");
                  fos.write("\n");
                  fos.write("init_fullsib = c(1:numofmarker)"+"\n");
                  fos.write("init_fullsib [1:numofmarker] = fullsib_min"+"\n");
                  fos.write("init_fullsib [numofmarker] = fullsib_max"+"\n");
                  fos.write("\n");

                  fos.write("# ---------------------------------------------"+"\n");
                  fos.write("# Plot & Save"+"\n");
                  fos.write("# ---------------------------------------------"+"\n");
                  fos.write("windows(record=TRUE, width=2000, height=1600)"+"\n");
                  fos.write("if (numofmarker < length(data$pi_0_Estimate))"+"{\n");
                  fos.write("par(mfrow=c(2,1))"+"\n");
                  fos.write("}\n");
                  fos.write("\n");

                  fos.write("plot(init_fullsib, type=\"n\", axes=T, ann=T, xlab=\"location\", ylab=\"pi-hat\")"+"\n");
                  fos.write("title(\"Test of Mean Allele Sharing IBD for Full Sib Pairs : pi-hat plot\")"+"\n");
                  fos.write("abline(h=0.5, lty=3)"+"\n");
                  fos.write("\n");
                  fos.write("lines(pi0_fullsib , lwd=1.5, col=\"skyblue\", lty=1)"+"\n");
                  fos.write("lines(pi1_fullsib , lwd=1.5, col=\"mediumvioletred\", lty=3)"+"\n");
                  fos.write("lines(pi2_fullsib , lwd=1.5, col=\"steelblue\", lty=5)"+"\n");
                  fos.write("plot_colors <- c(\"skyblue\", \"mediumvioletred\",\"steelblue\")"+"\n");
                  fos.write("plot_names <- c(\"Concordantly unaffected\",\"Discordant\",\"Concordantly affected\")"+"\n");
                  fos.write("legend(1, fullsib_max, plot_names, cex=0.8, col=plot_colors, pch=21:23, lty=c(1,3,5));"+"\n");
                  fos.write("\n");

                  fos.write("if (numofmarker < length(data$pi_0_Estimate))"+"\n");
                  fos.write("{\n");
                  fos.write("half_start_index = numofmarker+2;"+"\n");
                  fos.write("half_end_index = numofmarker*2+1;"+"\n");
                  fos.write("\n");
                  fos.write("pi0_halfsib = data$pi_0_Estimate[half_start_index:half_end_index]"+"\n");
                  fos.write("pi1_halfsib = data$pi_1_Estimate[half_start_index:half_end_index]"+"\n");
                  fos.write("pi2_halfsib = data$pi_2_Estimate[half_start_index:half_end_index]"+"\n");
                  fos.write("\n");

                  fos.write("halfsib_max = max(pi0_halfsib, pi1_halfsib, pi2_halfsib)"+"\n");
                  fos.write("halfsib_min = min(pi0_halfsib, pi1_halfsib, pi2_halfsib)"+"\n");
                  fos.write("\n");
                  fos.write("init_halfsib = c(1:numofmarker)"+"\n");
                  fos.write("init_halfsib[1:numofmarker] = halfsib_min"+"\n");
                  fos.write("init_halfsib[numofmarker] = halfsib_max"+"\n");

                  fos.write("plot(init_halfsib, type=\"n\", axes=T, ann=T, xlab=\"location\", ylab=\"pi-hat\")"+"\n");
                  fos.write("title(\"Test of Mean Allele Sharing IBD for Half Sib Pairs : pi-hat plot\")"+"\n");
                  fos.write("abline(h=0.5, lty=3)"+"\n");
                  fos.write("\n");
                  fos.write("lines(pi0_halfsib , lwd=1.5, col=\"skyblue\", lty=1)"+"\n");
                  fos.write("lines(pi1_halfsib , lwd=1.5, col=\"mediumvioletred\", lty=3)"+"\n");
                  fos.write("lines(pi2_halfsib , lwd=1.5, col=\"steelblue\", lty=5)"+"\n");
                  fos.write("plot_colors <- c(\"skyblue\", \"mediumvioletred\",\"steelblue\")"+"\n");
                  fos.write("plot_names <- c(\"Concordantly unaffected\",\"Discordant\",\"Concordantly affected\")"+"\n");
                  fos.write("legend(1, halfsib_max, plot_names, cex=0.8, col=plot_colors, pch=21:23, lty=c(1,3,5));"+"\n");
                  fos.write("}\n");
                  fos.write("\n");

                  fos.write("savepath = paste(filepath, \"_plot.pdf\",sep=\"\");"+"\n");
                  fos.write("savePlot(savepath ,type=\"pdf\")"+"\n");

                  fos.close();

                  NodeInfo filenode1 = new NodeInfo(name + ".r", "R File", new File(rfilepath));
                  IconNode rfilenode1 = new IconNode(filenode1, "R File");
                  addObject(rfilenode1, output_f, true);

                  Runtime.getRuntime().exec("Rscript" + " \"" + rfilepath + "\"");

                  NodeInfo filenode2 = new NodeInfo(name + ".mean_export_plot.pdf", "PDF File", new File(resultfile + "_plot.pdf"));
                  IconNode rfilenode2 = new IconNode(filenode2, "PDF File");
                  addObject(rfilenode2, output_f, true);

              } catch (Exception excep) {
                  excep.printStackTrace();
              }
          }

  }

  public void RunSEGREG(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final SEGREG1 test1 = (SEGREG1) currentnode.component_vector.get(0);
    final SEGREG2 test2 = (SEGREG2) currentnode.component_vector.get(1);
    final SEGREG3 test3 = (SEGREG3) currentnode.component_vector.get(2);
    final SEGREG4 test4 = (SEGREG4) currentnode.component_vector.get(3);
    final SEGREG5 test5 = (SEGREG5) currentnode.component_vector.get(4);

    DataCollectionModel segreg3 = test3.Datamodel;
    DataCollectionModel segreg4 = test4.Datamodel;
    DataCollectionModel segreg5 = test5.Datamodel;

    final sage_analysis_info fanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel segregData = test1.Datamodel;
      final String Outputfilefilter;
      if (segregData.hasValue("output_name"))
        Outputfilefilter = segregData.getValue("output_name").toString();
      else
        Outputfilefilter = "segreg";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("SEGREG Analysis");

      if (!segregData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jParaLabel.setText(fanalysis.para_file_path);
          d1.parLabel.setIcon(check);
      }

      if (!segregData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else
      {
          d1.jPediLabel.setText(fanalysis.family_file_path);
          d1.pedLabel.setIcon(check);
      }

      d1.setFileList(2);

      try {
        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetSEGREGblock(segregData, Outputfilefilter,segreg3,segreg4,segreg5);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();
          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.
                                          get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    if (test2.BRadioButton.isSelected()) { //binary
                        frame1.setSEGREGblock_B(block, test2, test4, false);
                    }
                    else if (test2.QRadioButton.isSelected()) { //quantitative
                        frame1.setSEGREGblock_C(block, test2, test3, false);
                    }
                    else if (test2.BARadioButton.isSelected()) { //binary with age of onset
                        frame1.setSEGREGblock_BA(block, test2, test5, false);
                    }
                }
            };
             SwingUtilities.invokeLater(setinfo);

          }

          final Vector v = new Vector();
          final RunJobTask assoc_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          fanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(assoc_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "segreg.exe";

            if (fanalysis.get_para_path() != null) {
              str = str + " " + "-p " +"\"" +
                  generate_run_filepath(fanalysis.get_para_path()) + "\"";
            }
            if (fanalysis.get_family_path() != null) {
              str = str + " " + "-d " +"\"" +
                  generate_run_filepath(fanalysis.get_family_path()) + "\"";
            }

          }
          else {
            str = "segreg";

            if (fanalysis.get_para_path() != null) {
              str = str + " " + "-p " +fanalysis.get_para_path();
            }
            if (fanalysis.get_family_path() != null) {
              str = str + " " + "-d " +fanalysis.get_family_path();
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          assoc_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }
              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
             assoc_timer.stop();

             int v_index = jobframe.v_data.indexOf(v);
             Vector result_v = (Vector)jobframe.v_data.get(v_index);
             result_v.set(4, "Finished");
             jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);
          worker.start();
        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetSEGREGblock(DataCollectionModel segregData,
                               String Outputfilefilter,
                               DataCollectionModel segreg3,
                               DataCollectionModel segreg4,
                               DataCollectionModel segreg5)
  {
    StringBuffer analysis_block = new StringBuffer();

    analysis_block.append("segreg, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (segregData.hasValue("title")) {
      analysis_block.append("title = " + "\""+
                            segregData.getValue("title").toString() + "\"" +
                            "\n");
    }

    if (segregData.hasValue("trait")) {
      analysis_block.append("trait = " + "\""+
                            segregData.getValue("trait").toString()+ "\"");
      if (segregData.hasValue("trait_type")) {
          if(segregData.getValue("trait_type").toString().compareTo("binary")!=0)
              analysis_block.append(", type = " +
                              segregData.getValue("trait_type").toString());
      }

      analysis_block.append("\n");
    }

// continuous
    if (segregData.hasValue("continuous")) {
      if (segreg3.hasValue("class")) {
        analysis_block.append("class = " + "\""+
                              segreg3.getValue("class").toString() + "\"" +
                              "\n");
      }
      if (segreg3.hasValue("type_mean")) {
        analysis_block.append("type_mean" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+segreg3.getValue("type_mean_option").toString() + "\"" + "\n");
        if (segreg3.hasValue("type_mean_info"))
          analysis_block.append(segreg3.getValue("type_mean_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg3.hasValue("mean_cov")) {
        analysis_block.append("mean_cov" + "\n");
        analysis_block.append("{" + "\n");
        if (segreg3.hasValue("mean_cov_info"))
          analysis_block.append(segreg3.getValue("mean_cov_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }
      if (segreg3.hasValue("type_var")) {
        analysis_block.append("type_var" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg3.getValue("type_var_option").toString() + "\"" + "\n");
        if (segreg3.hasValue("type_var_info"))
          analysis_block.append(segreg3.getValue("type_var_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg3.hasValue("var_cov")) {
        analysis_block.append("var_cov" + "\n");
        analysis_block.append("{" + "\n");
        if (segreg3.hasValue("var_cov_info"))
          analysis_block.append(segreg3.getValue("var_cov_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }
      if (segreg3.hasValue("fpmm")) {
        analysis_block.append("fpmm" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("loci = " + "\""+ segreg3.getValue("fpmm_loci").toString() + "\""+ "\n");

        if(segreg3.hasValue("fpmm_freq"))
            analysis_block.append("freq = " + "\""+ segreg3.getValue("fpmm_freq").toString() + "\""+ "\n");

        if (segreg3.hasValue("fpmm_var")) {
          analysis_block.append("var = " + "\""+ segreg3.getValue("fpmm_var").toString()+ "\"");
          analysis_block.append(", fixed =  " + "\""+ segreg3.getValue("fpmm_fixed").toString()+ "\""+ "\n");
        }

        if (segreg3.hasValue("fpmm_onset")) {
          analysis_block.append("onset" + "\n");
          analysis_block.append("{" + "\n");
          analysis_block.append("type_dependent = " + "\""+ segreg3.getValue(
              "fpmm_onset_type_dependent").toString()+ "\"" + "\n");
          if(segreg3.hasValue("fpmm_onset_multi_dependent"))
              analysis_block.append("multi_dependent = " + "\"" + segreg3.getValue(
                      "fpmm_onset_multi_dependent").toString() + "\"" + "\n");
          analysis_block.append("age_onset = " + "\""+
                                segreg3.getValue("fpmm_onset_age_onset").
                                toString()+ "\"" + "\n");
          analysis_block.append("age_exam = " + "\""+
                                segreg3.getValue("fpmm_onset_age_exam").
                                toString()+ "\"" + "\n");
          analysis_block.append("}" + "\n");
        }
        analysis_block.append("}" + "\n");
      }

      if (segreg3.hasValue("resid")) {
        analysis_block.append("resid" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg3.getValue("resid_option").toString()+ "\"" + "\n");
        if (segreg3.hasValue("resid_fm")) {
          analysis_block.append("fm, val = " + "\""+ segreg3.getValue("resid_fm_val").toString()+ "\"");
          analysis_block.append(", fixed = " + "\""+ segreg3.getValue("resid_fm_fixed").toString() + "\""+ "\n");
        }

        if (segreg3.hasValue("resid_mo")) {
          analysis_block.append("mo, val = " + "\""+ segreg3.getValue("resid_mo_val").toString()+ "\"");
          analysis_block.append(", fixed = " + "\""+ segreg3.getValue("resid_mo_fixed").toString() + "\""+ "\n");
        }

        if (segreg3.hasValue("resid_fo")) {
          analysis_block.append("fo, val = " + "\""+ segreg3.getValue("resid_fo_val").toString()+ "\"");
          analysis_block.append(", fixed = " + "\""+ segreg3.getValue("resid_fo_fixed").toString() + "\""+ "\n");
        }

        if (segreg3.hasValue("resid_ss")) {
          analysis_block.append("ss, val = " + "\""+ segreg3.getValue("resid_ss_val").toString()+ "\"");
          analysis_block.append(", fixed = " + "\""+ segreg3.getValue("resid_ss_fixed").toString() + "\""+ "\n");
        }
        analysis_block.append("}" + "\n");
      }

      if (segreg3.hasValue("transformation")) {
        analysis_block.append("transformation" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg3.getValue("transformation_option").toString() + "\""+ "\n");

        if(segreg3.getValue("transformation_option").toString().compareTo("none")!=0)
        {
            if (segreg3.hasValue("transformation_lambda1")) {
              analysis_block.append("lambda1");
              if (segreg3.hasValue("transformation_lambda1_val")) {
                analysis_block.append(", val = " + "\""+ segreg3.getValue("transformation_lambda1_val").toString()+ "\"");
                analysis_block.append(", fixed = " + "\""+ segreg3.getValue("transformation_lambda1_fixed").toString()+ "\"");
              }
              if (segreg3.hasValue("transformation_lambda1_lb")) {
                analysis_block.append(", lower_bound = " + "\""+ segreg3.getValue("transformation_lambda1_lb").toString()+ "\"");
              }
              if (segreg3.hasValue("transformation_lambda1_ub")) {
                analysis_block.append(", upper_bound = " + "\""+ segreg3.getValue("transformation_lambda1_ub").toString()+ "\"");
              }
              analysis_block.append("\n");
            }

            if (segreg3.hasValue("transformation_lambda2")) {
              analysis_block.append("lambda2");
              if (segreg3.hasValue("transformation_lambda1_val")) {
                analysis_block.append(", val = " + "\""+ segreg3.getValue("transformation_lambda2_val").toString()+ "\"");
                analysis_block.append(", fixed = " + "\""+ segreg3.getValue("transformation_lambda2_fixed").toString()+ "\"");
              }
              analysis_block.append("\n");
          }
        }

        analysis_block.append("}" + "\n");
      }

      if (segreg3.hasValue("geno_freq")) {
        analysis_block.append("geno_freq" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg3.getValue("geno_option").toString() + "\""+"\n");
        if (segreg3.hasValue("geno_prob1")) {
          analysis_block.append("prob = " + "\""+ segreg3.getValue("geno_prob1").toString()+ "\"");
          if (segreg3.hasValue("geno_prob1_val")) {
            analysis_block.append(", val = " + "\""+ segreg3.getValue("geno_prob1_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }
        if (segreg3.hasValue("geno_prob2")) {
          analysis_block.append("prob = " + "\""+ segreg3.getValue("geno_prob2").toString()+ "\"");

          if (segreg3.hasValue("geno_prob2_val")) {
            analysis_block.append(", val = " + "\""+ segreg3.getValue("geno_prob2_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }

        analysis_block.append("probs_fixed = " + "\""+ segreg3.getValue("geno_prob_fixed").toString() + "\""+ "\n");

        if (segreg3.hasValue("geno_allele_freq_A")) {
          analysis_block.append("freq_A");
          if (segregData.hasValue("geno_allele_freq_A_val")) {
            analysis_block.append(", val = " + "\""+ segreg3.getValue("geno_allele_freq_A_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }
        analysis_block.append("}" + "\n");
      }

      if (segreg3.hasValue("transmission")) {
        analysis_block.append("transmission" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg3.getValue("transmission_option").toString() + "\"" + "\n");
        if (segreg3.hasValue("transmission_no_bounds"))
          analysis_block.append("no_bounds" + "\n");
        if (segreg3.hasValue("transmission_info"))
          analysis_block.append(segreg3.getValue("transmission_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg3.hasValue("composite_trait")) {
        analysis_block.append("composite_trait" + "\n");
        analysis_block.append("{" + "\n");
        if (segreg3.hasValue("composite_trait_info"))
          analysis_block.append(segreg3.getValue("composite_trait_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg3.hasValue("ascertainment")) {
        analysis_block.append("ascertainment" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("cond_set = " + "\""+ segreg3.getValue("ascer_cond_set").toString() + "\""+ "\n");
        if (segreg3.hasValue("ascer_psf_indic")) {
          analysis_block.append("psf_indic = " + "\""+ segreg3.getValue("ascer_psf_indic").toString() + "\""+ "\n");
          if (segreg3.hasValue("ascer_psf_indic_include")) {
            StringTokenizer st = new StringTokenizer(segreg3.getValue("ascer_psf_indic_include").toString(), ",");
            while (st.hasMoreTokens()) {
              analysis_block.append("psf_indic_include = " + "\""+ st.nextToken()+ "\"" + "\n");
            }
          }
        }

        analysis_block.append("onset_option = " + "\""+ segreg3.getValue("ascer_onset_option").toString() + "\""+ "\n");
        if (segreg3.hasValue("ascer_thresh_indic")) {
          analysis_block.append("thresh_indic = " + "\""+ segreg3.getValue("ascer_thresh_indic").toString()+ "\"");
          if (segreg3.hasValue("ascer_thresh_indic_thresh")) {
            analysis_block.append(", thresh = " + "\""+ segreg3.getValue("ascer_thresh_indic_thresh").toString()+ "\"");
          }
          analysis_block.append("\n");
        }

        analysis_block.append("cond_val = " + "\""+ segreg3.getValue("ascer_cond_val").toString()+ "\"");
        if (segreg3.hasValue("ascer_cond_val_thresh")) {
          analysis_block.append(", thresh = " + "\""+ segreg3.getValue("ascer_cond_val_thresh").toString()+ "\"");
        }

        if (segreg3.hasValue("ascer_cond_val_thresh_high")) {
          analysis_block.append(", thresh_indic_high = " + "\""+
                                segreg3.getValue("ascer_cond_val_thresh_high").toString()+ "\"");
        }

        if (segreg3.hasValue("ascer_cond_val_thresh_low")) {
          analysis_block.append(", thresh_indic_low = " + "\""+ segreg3.getValue("ascer_cond_val_thresh_low").toString()+ "\"");
        }
        analysis_block.append("\n}" + "\n");
      }
    }
//binary trait
    else if (segregData.hasValue("binary_trait")) {
      if (segreg4.hasValue("class")) {
        analysis_block.append("class = " + "\""+ segreg4.getValue("class").toString() + "\"" +"\n");
      }

      if (segreg4.hasValue("type_suscept")) {
        analysis_block.append("type_suscept" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg4.getValue("type_suscept_option").toString() + "\""+ "\n");
        if (segreg4.hasValue("type_suscept_info"))
          analysis_block.append(segreg4.getValue("type_suscept_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg4.hasValue("suscept_cov")) {
        analysis_block.append("suscept_cov" + "\n");
        analysis_block.append("{" + "\n");
        if (segreg4.hasValue("suscept_cov_info"))
          analysis_block.append(segreg4.getValue("suscept_cov_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg4.hasValue("resid")) {
        analysis_block.append("resid" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+
                              segreg4.getValue("resid_option").toString() + "\""+
                              "\n");
        if (segreg4.hasValue("resid_fm")) {
          analysis_block.append("fm, val = " + "\""+
                                segreg4.getValue("resid_fm_val").
                                toString()+ "\"");
          analysis_block.append(", fixed = " + "\""+
                                segreg4.getValue("resid_fm_fixed").
                                toString() + "\""+ "\n");
        }

        if (segreg4.hasValue("resid_mo")) {
          analysis_block.append("mo, val = " + "\""+
                                segreg4.getValue("resid_mo_val").
                                toString()+ "\"");
          analysis_block.append(", fixed = " + "\""+
                                segreg4.getValue("resid_mo_fixed").
                                toString() + "\""+ "\n");
        }

        if (segreg4.hasValue("resid_fo")) {
          analysis_block.append("fo, val = " + "\""+
                                segreg4.getValue("resid_fo_val").
                                toString()+ "\"");
          analysis_block.append(", fixed = " + "\""+
                                segreg4.getValue("resid_fo_fixed").
                                toString() + "\""+ "\n");
        }

        if (segreg4.hasValue("resid_ss")) {
          analysis_block.append("ss, val = " + "\""+
                                segreg4.getValue("resid_ss_val").
                                toString()+ "\"");
          analysis_block.append(", fixed = " + "\""+
                                segreg4.getValue("resid_ss_fixed").
                                toString() + "\""+ "\n");
        }

        analysis_block.append("}" + "\n");
      }

      if (segreg4.hasValue("geno_freq")) {
        analysis_block.append("geno_freq" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg4.getValue("geno_option").toString() + "\""+ "\n");
        if (segreg4.hasValue("geno_prob1")) {
          analysis_block.append("prob = " + "\""+ segreg4.getValue("geno_prob1").toString()+ "\"");
          if (segreg4.hasValue("geno_prob1_val")) {
            analysis_block.append(", val = " + "\""+ segreg4.getValue("geno_prob1_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }
        if (segreg4.hasValue("geno_prob2")) {
          analysis_block.append("prob = " + "\""+ segreg4.getValue("geno_prob2").toString()+ "\"");

          if (segreg4.hasValue("geno_prob2_val")) {
            analysis_block.append(", val = " + "\""+ segreg4.getValue("geno_prob2_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }

        analysis_block.append("probs_fixed = " + "\""+ segreg4.getValue("geno_prob_fixed").toString() + "\""+ "\n");

        if (segreg4.hasValue("geno_allele_freq_A")) {
          analysis_block.append("freq_A");
          if (segreg4.hasValue("geno_allele_freq_A_val")) {
            analysis_block.append(", val = " + "\""+ segreg4.getValue("geno_allele_freq_A_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }
        analysis_block.append("}" + "\n");
      }

      if (segreg4.hasValue("transmission")) {
        analysis_block.append("transmission" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg4.getValue("transmission_option").toString() + "\"" + "\n");
        if (segreg4.hasValue("transmission_no_bounds"))
          analysis_block.append("no_bounds" + "\n");
        if (segreg4.hasValue("transmission_info"))
          analysis_block.append(segreg4.getValue("transmission_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg4.hasValue("ascertainment")) {
        analysis_block.append("ascertainment" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("cond_set = " + "\""+ segreg4.getValue("ascer_cond_set").toString() + "\""+ "\n");
        if (segreg4.hasValue("ascer_psf_indic")) {
          analysis_block.append("psf_indic = " + "\""+ segreg4.getValue("ascer_psf_indic").toString() + "\""+ "\n");
          if (segreg4.hasValue("ascer_psf_indic_include")) {
            StringTokenizer st = new StringTokenizer(segreg4.getValue("ascer_psf_indic_include").toString(), ",");
            while (st.hasMoreTokens()) {
              analysis_block.append("psf_indic_include = " + "\""+ st.nextToken() + "\""+ "\n");
            }
          }
        }

        analysis_block.append("onset_option = " + "\""+ segreg4.getValue("ascer_onset_option").toString() + "\""+ "\n");
        if (segreg4.hasValue("ascer_thresh_indic")) {
          analysis_block.append("thresh_indic = " + "\""+ segreg4.getValue("ascer_thresh_indic").toString()+ "\"");
          if (segreg4.hasValue("ascer_thresh_indic_thresh")) {
            analysis_block.append(", thresh = " + "\""+
                                  segreg4.getValue("ascer_thresh_indic_thresh").
                                  toString()+ "\"");
          }
          analysis_block.append("\n");
        }

        analysis_block.append("cond_val = " + "\""+ segreg4.getValue("ascer_cond_val").toString()+ "\"");
        if (segreg4.hasValue("ascer_cond_val_thresh")) {
          analysis_block.append(", thresh = " + "\""+ segreg4.getValue("ascer_cond_val_thresh").toString()+ "\"");
        }

        if (segreg4.hasValue("ascer_cond_val_thresh_high")) {
          analysis_block.append(", thresh_indic_high = " + "\""+
                                segreg4.getValue("ascer_cond_val_thresh_high").toString()+ "\"");
        }

        if (segreg4.hasValue("ascer_cond_val_thresh_low")) {
          analysis_block.append(", thresh_indic_low = " + "\""+
                                segreg4.getValue("ascer_cond_val_thresh_low").toString()+ "\"");
        }

        analysis_block.append("\n}" + "\n");
      }

      if (segreg4.hasValue("prev_constraint")) {
        analysis_block.append("prev_constraints" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append(segreg4.getValue("prev_constraint"));
        analysis_block.append("\n}" + "\n");
      }

      if (segreg4.hasValue("prev_estimate")) {
        analysis_block.append("prev_estimate" + "\n");
        analysis_block.append("{" + "\n");

        if (segreg4.hasValue("prev_estimate_age"))
          analysis_block.append("age = " + "\""+ segreg4.getValue("prev_estimate_age").toString() + "\"" + "\n");

        if (segreg4.hasValue("prev_estimate_info"))
          analysis_block.append(segreg4.getValue("prev_estimate_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }
    }
//binary with variable age of onset
    else if (segregData.hasValue("binary_trait_age")) {
      if (segreg5.hasValue("class")) {
        analysis_block.append("class = " + "\""+ segreg5.getValue("class").toString() + "\"" + "\n");
      }
      if (segreg5.hasValue("type_mean")) {
        analysis_block.append("type_mean" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg5.getValue("type_mean_option").toString()+ "\"" + "\n");
        if (segreg5.hasValue("type_mean_info"))
          analysis_block.append(segreg5.getValue("type_mean_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("type_suscept")) {
        analysis_block.append("type_suscept" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg5.getValue("type_suscept_option").toString() + "\"" + "\n");
        if (segreg5.hasValue("type_suscept_info"))
          analysis_block.append(segreg5.getValue("type_suscept_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("mean_cov")) {
        analysis_block.append("mean_cov" + "\n");
        analysis_block.append("{" + "\n");
        if (segreg5.hasValue("mean_cov_info"))
          analysis_block.append(segreg5.getValue("mean_cov_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("suscept_cov")) {
        analysis_block.append("suscept_cov" + "\n");
        analysis_block.append("{" + "\n");
        if (segreg5.hasValue("suscept_cov_info"))
          analysis_block.append(segreg5.getValue("suscept_cov_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("type_var")) {
        analysis_block.append("type_var" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg5.getValue("type_var_option").toString() + "\"" + "\n");
        if (segreg5.hasValue("type_var_info"))
          analysis_block.append(segreg5.getValue("type_var_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }


      if (segreg5.hasValue("var_cov")) {
        analysis_block.append("var_cov" + "\n");
        analysis_block.append("{" + "\n");
        if (segreg5.hasValue("var_cov_info"))
          analysis_block.append(segreg5.getValue("var_cov_info").toString() + "\"" + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("fpmm")) {
        analysis_block.append("fpmm" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("loci = " + "\""+ segreg5.getValue("fpmm_loci").toString() + "\""+"\n");
        if(segreg5.hasValue("fpmm_freq"))
            analysis_block.append("freq = " + "\""+ segreg5.getValue("fpmm_freq").toString() + "\""+ "\n");

        if (segreg5.hasValue("fpmm_var")) {
          analysis_block.append("var = " + "\""+ segreg5.getValue("fpmm_var").toString()+ "\"");
          analysis_block.append(", fixed =  " + "\""+ segreg5.getValue("fpmm_fixed").toString()+ "\"");
        }

        if (segreg5.hasValue("fpmm_onset")) {
          analysis_block.append("onset" + "\n");
          analysis_block.append("{" + "\n");
          analysis_block.append("type_dependent = " + "\""+ segreg5.getValue(
              "fpmm_onset_type_dependent").toString()+ "\"" + "\n");
          if(segreg5.hasValue("fpmm_onset_multi_dependent"))
               analysis_block.append("multi_dependent = "+ "\"" + segreg5.getValue(
              "fpmm_onset_multi_dependent").toString()+ "\"" + "\n");
          analysis_block.append("age_onset = " + "\""+
                                segreg5.getValue("fpmm_onset_age_onset").
                                toString()+ "\"" + "\n");
          analysis_block.append("age_exam = " + "\""+
                                segreg5.getValue("fpmm_onset_age_exam").
                                toString() + "\""+ "\n");
          analysis_block.append("}" + "\n");
        }
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("transformation")) {
        analysis_block.append("transformation" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg5.getValue("transformation_option").toString()+ "\"" + "\n");

        if(segreg5.getValue("transformation_option").toString().compareTo("none")!=0)
        {
            if (segreg5.hasValue("transformation_lambda1")) {
              analysis_block.append("lambda1");
              if (segreg5.hasValue("transformation_lambda1_val")) {
                analysis_block.append(", val = " + "\""+ segreg5.getValue("transformation_lambda1_val").toString()+ "\"");
                analysis_block.append(", fixed = " + "\""+ segreg5.getValue("transformation_lambda1_fixed").toString()+ "\"");
              }
              if (segreg5.hasValue("transformation_lambda1_lb")) {
                analysis_block.append(" lower_bound = " + "\""+ segreg5.getValue("transformation_lambda1_lb").toString()+ "\"");
              }

              if (segreg5.hasValue("transformation_lambda1_ub")) {
                analysis_block.append(", upper_bound = "+ "\"" + segreg5.getValue("transformation_lambda1_ub").toString()+ "\"");
              }
              analysis_block.append("\n");
            }

            if (segreg5.hasValue("transformation_lambda2")) {
              analysis_block.append("lambda2");
              if (segreg5.hasValue("transformation_lambda1_val")) {
                analysis_block.append(", val = " + "\""+ segreg5.getValue("transformation_lambda2_val").toString()+ "\"");
                analysis_block.append(", fixed = " + "\""+ segreg5.getValue( "transformation_lambda2_fixed").toString()+ "\"");
              }
              analysis_block.append("\n");
            }
        }

        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("geno_freq")) {
        analysis_block.append("geno_freq" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg5.getValue("geno_option").toString()+ "\"" + "\n");
        if (segreg5.hasValue("geno_prob1")) {
          analysis_block.append("prob = " + "\""+ segreg5.getValue("geno_prob1").toString()+ "\"");
          if (segreg5.hasValue("geno_prob1_val")) {
            analysis_block.append(", val = " + "\""+ segreg5.getValue("geno_prob1_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }
        if (segreg5.hasValue("geno_prob2")) {
          analysis_block.append("prob = " + "\""+ segreg5.getValue("geno_prob2").toString()+ "\"");

          if (segreg5.hasValue("geno_prob2_val")) {
            analysis_block.append(", val = " + "\""+ segreg5.getValue("geno_prob2_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }

        analysis_block.append("probs_fixed = " + "\""+ segreg5.getValue("geno_prob_fixed").toString() + "\""+ "\n");

        if (segreg5.hasValue("geno_allele_freq_A")) {
          analysis_block.append("freq_A");
          if (segreg5.hasValue("geno_allele_freq_A_val")) {
            analysis_block.append(", val = " + "\""+ segreg5.getValue("geno_allele_freq_A_val").toString()+ "\"");
          }
          analysis_block.append("\n");
        }
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("transmission")) {
        analysis_block.append("transmission" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("option = " + "\""+ segreg5.getValue("transmission_option").toString() + "\"" + "\n");
        if (segreg5.hasValue("transmission_no_bounds"))
          analysis_block.append("no_bounds" + "\n");
        if (segreg5.hasValue("transmission_info"))
          analysis_block.append(segreg5.getValue("transmission_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("composite_trait")) {
        analysis_block.append("composite_trait" + "\n");
        analysis_block.append("{" + "\n");
        if (segreg5.hasValue("composite_trait_info"))
          analysis_block.append(segreg5.getValue("composite_trait_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }

      if (segreg5.hasValue("ascertainment")) {
        analysis_block.append("ascertainment" + "\n");
        analysis_block.append("{" + "\n");
        analysis_block.append("cond_set = " + "\""+ segreg5.getValue("ascer_cond_set").toString() + "\""+ "\n");
        if (segreg5.hasValue("ascer_psf_indic")) {
          analysis_block.append("psf_indic = " + "\""+ segreg5.getValue("ascer_psf_indic").toString() + "\""+ "\n");
          if (segreg5.hasValue("ascer_psf_indic_include")) {
            StringTokenizer st = new StringTokenizer(segreg5.getValue("ascer_psf_indic_include").toString(), ",");
            while (st.hasMoreTokens()) {
              analysis_block.append("psf_indic_include = " + "\""+ st.nextToken() + "\""+ "\n");
            }
          }
        }

        analysis_block.append("onset_option = " + "\""+segreg5.getValue("ascer_onset_option").toString() + "\""+ "\n");
        if (segreg5.hasValue("ascer_thresh_indic")) {
          analysis_block.append("thresh_indic = " + "\""+ segreg5.getValue("ascer_thresh_indic").toString()+ "\"");
          if (segreg5.hasValue("ascer_thresh_indic_thresh")) {
            analysis_block.append(", thresh = " + "\""+ segreg5.getValue("ascer_thresh_indic_thresh").toString()+ "\"");
          }
          analysis_block.append("\n");
        }

        analysis_block.append("cond_val = " + "\""+ segreg5.getValue("ascer_cond_val").toString()+ "\"");
        if (segreg5.hasValue("ascer_cond_val_thresh")) {
          analysis_block.append(", thresh = " + "\""+ segreg5.getValue("ascer_cond_val_thresh").toString()+ "\"");
        }

        if (segreg5.hasValue("ascer_cond_val_thresh_high")) {
          analysis_block.append(", thresh_indic_high = " + "\""+ segreg5.getValue("ascer_cond_val_thresh_high").toString()+ "\"");
        }

        if (segreg5.hasValue("ascer_cond_val_thresh_low")) {
          analysis_block.append(", thresh_indic_low = " + "\""+ segreg5.getValue("ascer_cond_val_thresh_low").toString()+ "\"");
        }

        analysis_block.append("\n"+ "}" + "\n");
      }

      if (segreg5.hasValue("prev_constraint")) {
    analysis_block.append("prev_constraints" + "\n");
    analysis_block.append("{" + "\n");
    analysis_block.append(segreg5.getValue("prev_constraint"));
    analysis_block.append("\n}" + "\n");

      }

      if (segreg5.hasValue("prev_estimate")) {
        analysis_block.append("prev_estimate" + "\n");
        analysis_block.append("{" + "\n");

        if (segreg5.hasValue("prev_estimate_age"))
          analysis_block.append("age = " + "\""+ segreg5.getValue("prev_estimate_age").toString() + "\"" + "\n");

        if (segreg5.hasValue("prev_estimate_info"))
          analysis_block.append(segreg5.getValue("prev_estimate_info").toString() + "\n");
        analysis_block.append("}" + "\n");
      }
    }

    //output_option sub-block
     if(segregData.hasValue("pen_func_out") || segregData.hasValue("type_prob"))
     {
         analysis_block.append("output_options {\n");
         if (segregData.hasValue("type_prob"))
             analysis_block.append("type_prob = " + "\""+ segregData.getValue("type_prob").toString() + "\"" +"\n");
         analysis_block.append("}" + "\n");

         analysis_block.append("}" + "\n");
     }

     analysis_block.append("}" + "\n");

    return analysis_block.toString();
  }

  public void RunDECIPHER(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final DECIPHER1 test1 = (DECIPHER1) currentnode.component_vector.get(0);
    final DECIPHER2 test2 = (DECIPHER2) currentnode.component_vector.get(1);
    final sage_analysis_info ganalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel decipherData = test1.Datamodel;

      final String Outputfilefilter;
      if (decipherData.hasValue("output_name"))
        Outputfilefilter = decipherData.getValue("output_name").toString();
      else
        Outputfilefilter = "decipher";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("DECIPHER Analysis");

      if (!decipherData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(ganalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!decipherData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(ganalysis.family_file_path);
        d1.pedLabel.setIcon(check);
      }

      if (decipherData.hasValue("locus_path")) {
        d1.jLocusLabel.setText(ganalysis.locus_file_path);
        d1.mlocusLabel.setIcon(check);
      }

      if (!decipherData.hasValue("genome_path")) {
        d1.jGenomeLabel.setText(ganalysis.genome_file_path);
        d1.genomeLabel.setIcon(check);
      }

      d1.setFileList(4);

      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetDECIPHERblock(decipherData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          final String block = d1.anaDefTextArea.getText();

          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(block);
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();

            Runnable setinfo = new Runnable() {
                public void run() {
                    frame1.setDECIPHERblock(block, test2);
                }
            };
             SwingUtilities.invokeLater(setinfo);

          }

          final Vector v = new Vector();
          final RunJobTask genibd_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          ganalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer assoc_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(genibd_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "decipher.exe";

            if (ganalysis.get_para_path() != null) {
              str = str + " " + "-p " +"\"" +
                  generate_run_filepath(ganalysis.get_para_path()) + "\"";
            }
            if (ganalysis.get_family_path() != null) {
              str = str + " " + "-d " +"\"" +
                  generate_run_filepath(ganalysis.get_family_path()) + "\"";
            }
            if (ganalysis.create_locus_file_node) {
              str = str + " " + "-l " +"\"" +
                  generate_run_filepath(ganalysis.locus_file_path) + "\"";
            }
            if (ganalysis.create_genome_file_node) {
              str = str + " " + "-g " +"\"" +
                  generate_run_filepath(ganalysis.genome_file_path) + "\"";
            }
          }
          else {
            str = "decipher";

            if (ganalysis.get_para_path() != null) {
              str = str + " " + "-p " +ganalysis.get_para_path();
            }
            if (ganalysis.get_family_path() != null) {
              str = str + " " + "-d " +ganalysis.get_family_path();
            }
            if (ganalysis.create_locus_file_node) {
              str = str + " " + "-l " +ganalysis.locus_file_path;
            }
            if (ganalysis.create_genome_file_node) {
              str = str + " " + "-g " +ganalysis.genome_file_path;
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          genibd_task.go(start);
          assoc_timer.setInitialDelay(0);
          assoc_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              assoc_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };

          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);
          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetDECIPHERblock(DataCollectionModel decipherData,
                               String Outputfilefilter, DECIPHER2 test2)
  {
    StringBuffer analysis_block = new StringBuffer();
    analysis_block.append("decipher, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

    if (decipherData.hasValue("Title"))
      analysis_block.append("title = " + "\"" +
                            decipherData.getValue("Title").toString() + "\"" +
                            "\n");

    if(decipherData.hasValue("region"))
      analysis_block.append(decipherData.getValue("region_info"));

    if (decipherData.hasValue("starting_points"))
      analysis_block.append("starting_points = " + "\"" +
                            decipherData.getValue("starting_points").toString() +
                            "\"" + "\n");

    if (decipherData.hasValue("epsilon"))
      analysis_block.append("epsilon = " + "\"" +
                            decipherData.getValue("epsilon").toString() + "\"" + "\n");

    if(test2.jComboBox3.isSelected())
    {
      analysis_block.append("dump = " +"\"true\"");
      if (decipherData.hasValue("dump_cutoff"))
        analysis_block.append(", cutoff = " +"\""+ decipherData.getValue("dump_cutoff").toString()+"\"");
    }

    analysis_block.append("\n");

    if(test2.jCheckBox1.isSelected() && test2.jCheckBox1.isEnabled())
    {
      analysis_block.append("filters\n{\n");
      analysis_block.append("maf_filter = " +"\""+ decipherData.getValue("maf_filter")+"\"");
      if(test2.jCheckBox1.isSelected())
        if (decipherData.hasValue("maf_threshold"))
          analysis_block.append(", threshold = " +"\""+ decipherData.getValue("maf_threshold")+"\"");
      analysis_block.append("\n}\n");
    }

    if(decipherData.hasValue("blocks_info"))
    {
      analysis_block.append("blocks\n{\n");
      analysis_block.append("sliding_window = " +"\""+ decipherData.getValue("sliding_window")+"\"");
      if(test2.dialog6.jCheckBox1.isSelected())
        if (decipherData.hasValue("sliding_window_width"))
          analysis_block.append(", width = " +"\""+ decipherData.getValue("sliding_window_width")+"\"");
      analysis_block.append("\n");

      analysis_block.append("four_gamete_rule = " +"\""+ decipherData.getValue("four_gamete_rule") +"\"");
      if(test2.dialog6.jCheckBox2.isSelected())
        if (decipherData.hasValue("four_gamete_rule_thresh"))
          analysis_block.append(", threshold = " +"\""+ decipherData.getValue("four_gamete_rule_thresh").toString()+"\"");
      analysis_block.append("\n");

      analysis_block.append("ld = " +"\""+ decipherData.getValue("linkage_dis")+"\"");
      if(test2.dialog6.jCheckBox3.isSelected())
        if (decipherData.hasValue("linkage_dis_thresh"))
          analysis_block.append(", threshold = " +"\""+ decipherData.getValue("linkage_dis_thresh")+"\"");
      analysis_block.append("\n}\n");
    }

    //data sub-block
    analysis_block.append("data");
    analysis_block.append("\n{\n");

    analysis_block.append("analysis_unit = "+"\""+decipherData.getValue("analysis_unit").toString()+"\"");
    analysis_block.append("\n");

    if(decipherData.hasValue("pools_locus"))
    {
      analysis_block.append("pools");
      analysis_block.append("\n{\n");

      if (decipherData.hasValue("pool_size"))
        analysis_block.append("pool_size = " + "\"" +
                              decipherData.getValue("pool_size").toString() + "\"" + "\n");

      if (decipherData.hasValue("pool_size_trait"))
        analysis_block.append("pool_size_trait = " + "\"" +
                              decipherData.getValue("pool_size_trait").toString() + "\"" + "\n");

      analysis_block.append(decipherData.getValue("pools_locus"));
      analysis_block.append("\n}\n");
    }

    if(test2.jComboBox2.getSelectedIndex()>0)
    {
      analysis_block.append("family_rep = "+ "\""+test2.jComboBox2.getSelectedItem().toString()+ "\"");
      if(test2.jComboBox4.getSelectedIndex()>0)
        analysis_block.append(", family_rep_value = "+"\""+test2.jComboBox4.getSelectedItem().toString()+"\"");
      analysis_block.append("\n");
    }

    if(decipherData.hasValue("partition2"))
    {
      analysis_block.append(decipherData.getValue("partition2_info"));
    }

    if(decipherData.hasValue("partition1"))
    {
      analysis_block.append(decipherData.getValue("partition1_info"));
    }

    analysis_block.append("}\n");

    //tasks sub-block
    if(decipherData.hasValue("tasks"))
    {
      analysis_block.append("tasks");
      analysis_block.append("\n{\n");

      analysis_block.append("pop_freq = "+"\""+decipherData.getValue("pop_freq")+"\"");
      if(decipherData.hasValue("pop_freq_cutoff"))
        analysis_block.append(", cutoff = "+"\""+decipherData.getValue("pop_freq_cutoff")+"\"");
      analysis_block.append("\n");

      analysis_block.append("all_possible_combinations_table = "+"\""+decipherData.getValue("all_possible_diplotypes_table")+"\""+"\n");
      analysis_block.append("most_likely_combinations = "+"\""+decipherData.getValue("most_likely_diplotypes")+"\"");
      if(decipherData.hasValue("most_likely_cutoff"))
        analysis_block.append(", cutoff = "+"\""+decipherData.getValue("most_likely_cutoff")+"\"");
      analysis_block.append("\n");

      analysis_block.append("likelihood_ratio_test = "+"\""+decipherData.getValue("likelihood_ratio_test")+"\""+"\n");

      String pvalue = decipherData.getValue("compute_p_value").toString();
      analysis_block.append("compute_empirical_pvalue = "+"\""+pvalue+"\"");

      if(pvalue.compareTo("true")==0)
      {
        if(decipherData.hasValue("permutations"))
          analysis_block.append(", permutations = "+"\""+decipherData.getValue("permutations")+"\"");
        if(decipherData.hasValue("max_permutations"))
          analysis_block.append(", max_permutations = "+"\""+decipherData.getValue("max_permutations")+"\"");
        if(decipherData.hasValue("width"))
          analysis_block.append(", width = "+"\""+decipherData.getValue("width")+"\"");
        if(decipherData.hasValue("confidence"))
          analysis_block.append(", confidence = "+"\""+decipherData.getValue("confidence")+"\"");
      }
      analysis_block.append("\n");
      analysis_block.append("}\n");
    }

    analysis_block.append("}" + "\n");
    return analysis_block.toString();
  }

  public void RunRELPAL(IconNode dnode, NodeInfo currentnode, String action) {
    final IconNode node = dnode;
    final RELPAL1 test1 = (RELPAL1) currentnode.component_vector.get(0);
    final RELPAL2 test2 = (RELPAL2) currentnode.component_vector.get(1);
    final sage_analysis_info lanalysis = currentnode.analysis_info;
    final IconNode outputf = test1.outputF_node;

    if (action.compareTo("Run") == 0) {
      DataCollectionModel lodpalData = test1.Datamodel;
      final String Outputfilefilter;
      if (lodpalData.hasValue("output_name"))
        Outputfilefilter = lodpalData.getValue("output_name").toString();
      else
        Outputfilefilter = "relpal";

      ShowRunInfo d1 = new ShowRunInfo(frame1, "Analysis Information", true);
      d1.analysisLabel.setText("RELPAL Analysis");

      if (!lodpalData.hasValue("para_path")) {
        d1.parLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jParaLabel.setText(lanalysis.para_file_path);
        d1.parLabel.setIcon(check);
      }

      if (!lodpalData.hasValue("pedi_path")) {
        d1.pedLabel.setIcon(error);
        d1.jButton1.setEnabled(false);
      }
      else {
        d1.jPediLabel.setText(lanalysis.family_file_path);
        d1.pedLabel.setIcon(check);
      }

      d1.mlocusLabel.setText("IBD sharing file..");

      d1.setFileList(3);

      try {

        if (currentnode.nodetype.compareTo("Analysis") == 0) {
          String analysis_block = GetRELPALblock(lodpalData, Outputfilefilter, test2);
          currentnode.analysis_block = analysis_block.toString();
          d1.anaDefTextArea.setText(currentnode.analysis_block);
        }
        else if (currentnode.nodetype.compareTo("Locked Analysis") == 0)
          d1.anaDefTextArea.setText(currentnode.analysis_block);

        d1.setLocationRelativeTo(this);
        d1.setVisible(true);

        if (d1.Run) {
          if (!frame1.isConsolViewExist) {
            frame1.jCheckBoxMenuItem3.setSelected(true);
          }

          if (!frame1.isJobViewExist) {
            frame1.jCheckBoxMenuItem2.setSelected(true);
          }

          NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
          if (paranodeinfo.infomodel.hasValue("Master_Information")) {
            String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

            File parameterfileforrun = File.createTempFile("TempFile", "tmp");
            FileWriter fos = new FileWriter(parameterfileforrun);
            fos.write(master_info);
            fos.write("\n");
            fos.write(d1.anaDefTextArea.getText());
            fos.write("\n");
            fos.close();

            File parameterfile = new File(test1.Analysis_object.get_para_path());
            MyInternalFrame.CopyFile(parameterfileforrun, parameterfile);
            parameterfileforrun.delete();
          }

          final Vector v = new Vector();
          final RunJobTask relpal_task = new RunJobTask(node.toString());

          Calendar start = Calendar.getInstance();
          lanalysis.start_time = start;

          String ampm = null;
          if (start.get(Calendar.AM_PM) == 0) {
            ampm = "AM";
          }
          else
            ampm = "PM";
          String start_time = d.format(start.get(Calendar.HOUR)) + ":" +
              d.format(start.get(Calendar.MINUTE)) + " " + ampm + " ";

          final Timer relpal_timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
              v.setElementAt(relpal_task.getMessage(), 3);
              jobframe.jTable1.updateUI();
            }
          });

          String str = new String();
          String os_type = System.getProperty("os.name");

          if (os_type.indexOf("Windows") >= 0 || os_type.indexOf("Mac")>=0) {
            str = "relpal.exe";

            if (lanalysis.get_para_path() != null) {
              str = str + " " + "-p " + "\"" + generate_run_filepath(lanalysis.get_para_path()) + "\"";
            }
            if (lanalysis.get_family_path() != null) {
              str = str + " " + "-d " + "\"" + generate_run_filepath(lanalysis.get_family_path()) + "\"";
            }
            if (lanalysis.create_locus_file_node) {
              str = str + " " + "-i " + "\"" + generate_run_filepath(lanalysis.ibd_file_path) + "\"";
            }

          }
          else {
            str = "relpal";

            if (lanalysis.get_para_path() != null) {
              str = str + " " + "-p " +lanalysis.get_para_path();
            }
            if (lanalysis.get_family_path() != null) {
              str = str + " " + "-d " +lanalysis.get_family_path();
            }
            if (lanalysis.create_locus_file_node) {
              str = str + " " + "-i " +lanalysis.ibd_file_path;
            }
          }

          str = str.trim();
          final String run_command = str;

          v.addElement(" " + node.toString());
          v.addElement(currentnode.analysis_type);
          v.addElement(start_time);
          v.addElement("0");
          v.addElement("Running");
          jobframe.v_data.addElement(v);

          relpal_task.go(start);
          relpal_timer.setInitialDelay(0);
          relpal_timer.start();

          JScrollPane FileView = new JScrollPane();
          final JTextPane console_area = new JTextPane();
          console_area.setEditable(false);
          consoleframe.jTabbedPane1.insertTab(node.toString(), null, FileView, "Right click to option",0);
          FileView.getViewport().add(console_area, null);
          consoleframe.setFocusable(true);

          SwingWorker worker = new SwingWorker() {
            public Object construct() {
              try {
                Run_Analysis(run_command, node, console_area, outputf, test1.stop_proecss);
              }
              catch (Exception excep) {
                excep.printStackTrace();
              }

              return "sss";
            }

            public void finished() {
              test1.stop_proecss = false;
              relpal_timer.stop();

              int v_index = jobframe.v_data.indexOf(v);
              Vector result_v = (Vector)jobframe.v_data.get(v_index);
              result_v.set(4, "Finished");
              jobframe.v_data.setElementAt(result_v, v_index);

              jobframe.jTable1.updateUI();
              worker_vector_model.removeValue(node.toString());
              test2.jRunButton.setText("Run");
              test2.jRunButton.setIcon(next);
            }
          };
          worker_vector_model.setValue(node.toString(),worker);
          test2.jRunButton.setText("Stop");
          test2.jRunButton.setIcon(stop);

          worker.start();

        }
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }
    else if (action.compareTo("Stop") == 0) {
      test1.stop_proecss = true;
      test2.jRunButton.setText("Run");
      test2.jRunButton.setIcon(next);
      SwingWorker worker_t = (SwingWorker) worker_vector_model.getValue(node.toString());
      worker_t.interrupt();
    }
  }

  public String GetRELPALblock(DataCollectionModel relpalData,
                               String Outputfilefilter,
                               RELPAL2 test2) {
    StringBuffer analysis_block = new StringBuffer();

    analysis_block.append("relpal, out = ");
    analysis_block.append("\"" + Outputfilefilter + "\"" + "\n");
    analysis_block.append("{" + "\n");

      // trait
      CheckableItem[] model = test2.traitComboBox.ListData;
      int n = model.length;
      for (int i = 0; i < n; i++) {
          CheckableItem item = (CheckableItem) model[i];
          if (item.isSelected()) {
              analysis_block.append("trait = " + "\"" + item.toString() + "\"" + "\n");
          }
      }

      //model
      analysis_block.append("model = " + "\""+
                            relpalData.getValue("model").
                            toString() + "\""+
                            "\n");


      //first_level
      if (relpalData.hasValue("first_level")) {
          analysis_block.append("first_level\n");
          analysis_block.append("{\n");

          analysis_block.append(relpalData.getValue("first_level").toString());

          if (relpalData.hasValue("first_level_batch"))
              analysis_block.append("\nbatch");

          if (relpalData.hasValue("first_level_normalize"))
              analysis_block.append("\nnormalize_residual = \"true\"");

          analysis_block.append("\n}\n");
      }

      //second_level
      if (relpalData.hasValue("second_level")) {
          analysis_block.append("second_level\n");
          analysis_block.append("{\n");
        analysis_block.append(relpalData.getValue("second_level").toString());

        if(relpalData.hasValue("second_level_naive"))
            analysis_block.append("\nnaive_variance = "+relpalData.getValue("second_level_naive").toString());

        if(relpalData.hasValue("second_level_sandwich"))
            analysis_block.append("\nsandwich_variance = "+relpalData.getValue("second_level_sandwich").toString());

        if(relpalData.hasValue("second_level_alter"))
            analysis_block.append("\nalternative_variance = "+relpalData.getValue("second_level_alter").toString());

        if(relpalData.hasValue("second_level_ibd"))
            analysis_block.append("\nibd_variance = "+relpalData.getValue("second_level_ibd").toString());

        analysis_block.append("\n}\n");
      }

      //Data options
      analysis_block.append("data_options\n");
      analysis_block.append("{\n");

      //subset
      model = test2.subsetComboBox.ListData;
      n = model.length;
      for (int i = 0; i < n; i++) {
          CheckableItem item = (CheckableItem) model[i];
          if (item.isSelected()) {
              analysis_block.append("subset = " + "\"" + item.toString() + "\"" +
                                    "\n");
          }
      }

      if (relpalData.hasValue("use_pair"))
          analysis_block.append("use_pairs = " + "\"" +
                            relpalData.getValue("use_pair").toString() + "\"" +
                            "\n");
      analysis_block.append("}\n");

      //output option
      if (relpalData.hasValue("detailed_out") ||relpalData.hasValue("export_output")  )
      {
          analysis_block.append("output_options\n");
          analysis_block.append("{\n");

          if (relpalData.hasValue("detailed_out"))
              analysis_block.append("detailed_out = " + "\"" +
                                    relpalData.getValue("detailed_out").
                                    toString() + "\"" + "\n");

          if (relpalData.hasValue("export_output"))
              analysis_block.append("export_output = " + "\"" +
                                    relpalData.getValue("export_output").toString() +
                                    "\"" +
                                    "\n");
          analysis_block.append("}\n");
      }


      //p-val
      if (relpalData.hasValue("pvalues_options")) {
        analysis_block.append(relpalData.getValue("pvalues_options").toString());
        analysis_block.append("\n");
      }

      analysis_block.append("}" + "\n");

    return analysis_block.toString();
  }

 public boolean CheckGenomeFile(String genomefilepath) throws Exception
 {
   String strFileLine="";
   boolean returnvalue=false;
   FileInputStream fin = new FileInputStream(genomefilepath);
   InputStreamReader isr = new InputStreamReader(fin);
   BufferedReader in = new BufferedReader(isr);

   while ( (strFileLine = in.readLine()) != null) {
     strFileLine = strFileLine.trim();
     if (strFileLine.startsWith("#"))
       strFileLine = in.readLine().trim();

     if (strFileLine.indexOf("marker") >= 0)
       if (strFileLine.indexOf("p-ter") >= 0) {
         returnvalue = true;
         break;
       }
   }
   fin.close();
   isr.close();
   in.close();
   return returnvalue;
 }

 public boolean CheckLocusFile(String locusfilepath) throws Exception
 {
   String strFileLine="";
   boolean returnvalue=false;
   FileInputStream fin = new FileInputStream(locusfilepath);
   InputStreamReader isr = new InputStreamReader(fin);
   BufferedReader in = new BufferedReader(isr);

   while ( (strFileLine = in.readLine()) != null) {
     strFileLine = strFileLine.trim();
     if (strFileLine.startsWith("#"))
       strFileLine = in.readLine().trim();

     if (strFileLine.indexOf("p-ter") >= 0)
     {
       returnvalue = true;
       break;
     }
   }
   fin.close();
   isr.close();
   in.close();
   return returnvalue;
 }

 public String ModifyLocusFile(String locusfilepath)
 {
   String returnvalue = "";
   String strFileLine="";

   try {
     File temprunfile = File.createTempFile("sage_locus","tmp");
     File originalfile = new File(locusfilepath);

     FileInputStream fin = new FileInputStream(originalfile);
     InputStreamReader isr = new InputStreamReader(fin);
     BufferedReader in = new BufferedReader(isr);
     FileWriter fos = new FileWriter(temprunfile);

     while ( (strFileLine = in.readLine()) != null) {
       fos.write(strFileLine+"\n");
     }

     fos.write("\n");
     fos.write("p-ter\n");
     fos.write("A\n");
     fos.write(";\n");
     fos.write(";\n");

     fin.close();
     isr.close();
     in.close();
     fos.close();

     returnvalue = temprunfile.getAbsolutePath();
     temprunfile.deleteOnExit();
   }
   catch (Exception e) {
     e.printStackTrace();
   }
   return returnvalue;
 }


  public void Run_Analysis(String run_command, final IconNode selectnode,
                           final JTextPane ConsoleArea, final IconNode outputfolder, final boolean stop) throws Exception
    {
      consoleframe.jTabbedPane1.setSelectedIndex(0);

      Runtime runtime;

      final String analysis_name = ( (NodeInfo) selectnode.getUserObject()).analysis_type;
      NodeInfo currentNodeObject = (NodeInfo) (selectnode.getUserObject());

      runtime = Runtime.getRuntime();

      String projectpath = projectdatamodel.getValue("info.DirPath").toString();
      String newfilepath = projectpath + System.getProperty("file.separator") +
          currentNodeObject + System.getProperty("file.separator");
      final File project = new File(newfilepath);

      File pp = new File(projectpath);
      File[] files = pp.listFiles();

      boolean exist_folder = false;
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory()) {
          if (files[i].getName().compareTo(currentNodeObject.getName()) == 0) { // user project name alrealy exist
            exist_folder=true;
          }
        }
      }

      if(exist_folder)
      {
        outputfolder.removeAllChildren();
      }
      else
      {
        project.mkdir();
      }

      String arg[] = new String[1];
      arg[0]="";

      final Process process = runtime.exec(run_command, arg, project);

      outputPrint(ConsoleArea, run_command+"\n\n", BLUE);

      // any output?
      OutputStreamGobbler outputGobbler = new OutputStreamGobbler(process.getInputStream(), "OUTPUT", ConsoleArea);

      //any error message?
      ErrorStreamGobbler errorGobbler = new ErrorStreamGobbler(process.getErrorStream(), "ERROR", ConsoleArea, outputGobbler);

      // kick them off
      outputGobbler.start();
      errorGobbler.start();

      try {
        process.waitFor();
        errorGobbler.join();
        outputGobbler.join();
      }
      catch (InterruptedException e) {
        process.destroy();
      }

      addOutputFileNode(analysis_name, outputfolder, project);
      addObject(outputfolder, selectnode, false);
    }

    class OutputStreamGobbler extends Thread {
      InputStream is;
      String type;
      JTextPane outputarea;

      OutputStreamGobbler(InputStream is, String type, JTextPane outputarea)
      {
        this.is = is;
        this.type = type;
        this.outputarea = outputarea;
      }

      public void run()
      {
        try {
          InputStreamReader isr = new InputStreamReader(is);
          BufferedReader br = new BufferedReader(isr);
          String line = null;

          while ( (line = br.readLine()) != null)
          {
              MyInternalFrame.this.outputPrint(outputarea, line+"\n", BLACK);
          }
        }
        catch (Exception ioe) {
        }
      }

      public synchronized  void waitforerror()
      {
          try {
           this.wait();
          }
          catch (Exception ioe) {
            ioe.printStackTrace();
          }
      }
    }

    class ErrorStreamGobbler extends Thread {
      InputStream is;
      String type;
      JTextPane outputarea;
      OutputStreamGobbler outputGobbler;

      ErrorStreamGobbler(InputStream is, String type, JTextPane outputarea, OutputStreamGobbler outputGobbler)
      {
        this.is = is;
        this.type = type;
        this.outputarea = outputarea;
        this.outputGobbler = outputGobbler;
      }

      public void run()
      {
        try {
          InputStreamReader isr = new InputStreamReader(is);
           BufferedReader br = new BufferedReader(isr);
          String line = null;

          int starti=0;
         while ( (line = br.readLine()) != null)
          {
            if(starti==0)
            {
              waitThread();
            }
            MyInternalFrame.this.outputPrint(outputarea, line+"\n", RED);

            starti++;
          }
          notifyAllThread();
        }
        catch (Exception ioe) {
        }
      }

      public synchronized void waitThread()
      {
          try {
           outputGobbler.wait();
          }
          catch (Exception ioe) {
          }
      }

      public synchronized void notifyAllThread()
      {
        notifyAll();
      }
    }

  public void outputPrint(final JTextPane out, final String print, final AttributeSet set)
  {
    Runnable DoPrint = new Runnable() {
      public void run() {
        setOutputPrint(out, print, set);
      }
    };
    SwingUtilities.invokeLater(DoPrint);
  }

  public void setOutputPrint(JTextPane out, String print, AttributeSet set) {
    try {
      out.getDocument().insertString(out.getDocument().getLength(), print, set);
    }
    catch (BadLocationException e) {
      //e.printStackTrace();
    }
    catch (Exception e) {
      //e.printStackTrace();
    }
  }

  public void addOutputFileNode(final String analysis_name,
                                final IconNode outputfolder, final File result) {
     create_output_dir(analysis_name, outputfolder, result);
  }

  public void create_output_dir(String analysis_name, IconNode outputfolder,
                                File resultpath) {
    File f1 = resultpath;
    if (f1.isDirectory()) {
      File templist[] = f1.listFiles();

      for (int i = 0; i < templist.length; i++) {
        File d = templist[i];

        if(d.getName().indexOf(".flexlmrc")<0)
        {
          if(d.getName().indexOf(".loc")>0)
          {
            NodeInfo filenode = new NodeInfo(d.getName(), "Marker Locus File", d);
            IconNode outputfilenode = new IconNode(filenode, "Marker Locus File");
            addObject(outputfilenode, outputfolder, false);
          }
          else if(d.getName().indexOf(".typ")>0)
          {
            NodeInfo filenode = new NodeInfo(d.getName(), "Type File", d);
            IconNode outputfilenode = new IconNode(filenode, "Type File");
            addObject(outputfilenode, outputfolder, false);
          }
          else if(d.getName().indexOf(".ibd")>0)
          {
            NodeInfo filenode = new NodeInfo(d.getName(), "IBD Sharing File", d);
            IconNode outputfilenode = new IconNode(filenode, "IBD Sharing File");
            addObject(outputfilenode, outputfolder, false);
          }
          else if(!((d.getName().endsWith(".r")) || (d.getName().endsWith(".pdf"))))
          {
            NodeInfo filenode = new NodeInfo(d.getName(), "Result File", d);
            IconNode outputfilenode = new IconNode(filenode, "Result File");
            addObject(outputfilenode, outputfolder, false);
          }
        }
      }
    }
  }

  public static void CopyFile(File source_file, File des_file) { //
    try {
      FileInputStream fis = new FileInputStream(source_file);
      FileOutputStream fos = new FileOutputStream(des_file);
      byte[] buf = new byte[1024];
      int i = 0;
      while ( (i = fis.read(buf)) != -1) {
        fos.write(buf, 0, i);
      }
      fis.close();
      fos.close();
    }
    catch (Exception x) {
      x.printStackTrace();
    }
  }

  public static String generate_run_filepath(String inpath) {
    StringTokenizer st = new StringTokenizer(inpath,
                                             System.getProperty("file.separator"));
    int count = 0;
    String temp = new String();
    while (st.hasMoreTokens()) {
      count = st.countTokens();
      temp = temp
          + System.getProperty("file.separator") +
          System.getProperty("file.separator")
          + st.nextToken();
    }
    temp = temp.substring(2);

    return temp;
  }

  public int getTraitType(String traitName)
  {
      NodeInfo paranodeinfo = (NodeInfo)parameterfilenode.getUserObject();
      if (paranodeinfo.infomodel.hasValue("Master_Information")) {
        String master_info = paranodeinfo.infomodel.getValue("Master_Information").toString();

        int traitIndex = master_info.indexOf(traitName);

        if(traitIndex>0)
        {
            String temp = master_info.substring(traitIndex, master_info.indexOf("\n",traitIndex));

            String[] lists = temp.split(",");

            for(int i=0;i<lists.length;i++)
            {
                if(lists[i].trim().toLowerCase().compareTo("binary")==0)
                    return BinaryType;
            }
        }

        traitIndex = master_info.indexOf("sex_field");
        String temp = master_info.substring(traitIndex, master_info.indexOf("\n", traitIndex));

        String[] lists = temp.split("=");

        for (int i = 0; i < lists.length; i++) {
            if (lists[i].trim().compareTo(traitName) == 0) {
                return BinaryType;
            }
        }
    }
      return QuantitativeType;
  }


  class FrameListener extends InternalFrameAdapter {

    public void internalFrameClosing(InternalFrameEvent e) {
     int confirm = JOptionPane.showInternalOptionDialog(MyInternalFrame.this,
          "Do you wish to close project " + getTitle() + "?",
          "Warning", JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE,
             null, null, null);
      if (confirm == 0) {
        MyInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        frame1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        closeproject();
      }
      else
        return;
    }

    public void internalFrameActivated(InternalFrameEvent e) {
      m_frameMenuItem.setSelected(true);
      frame1.activateInternalFrame(MyInternalFrame.this);
    }

    public void closeproject() {
      Runnable colserun = new Runnable()
      {
        public void run()
        {
          frame1.activateInternalFrame(MyInternalFrame.this);
          frame1.jMenuFileSave_actionPerformed();

         try{
            updateFrameUI();
          }catch(Exception es)
          {
            es.printStackTrace();
          }
        }
      };

      Thread closethread = new Thread(colserun, "colsethread");
      closethread.start();
    }

    void updateFrameUI() throws Exception
    {
      Runnable r = new Runnable()
      {
        public void run()
        {
          setCloseUI();
        }
      };

      SwingUtilities.invokeAndWait(r);
    }

    private void setCloseUI()
    {
      frame1.m_windowMenu.remove(m_frameMenuItem);
      frame1.FRAME_COUNTER--;
      frame1.desktop.remove(MyInternalFrame.this);
      Frame1.desktop.updateUI();
      MyInternalFrame.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      frame1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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

    public void mouseClicked(MouseEvent e)
    {
      int count = e.getClickCount();
      if(count == 2)
      {
        TreePath m_path = MyTree1.getPathForLocation(e.getX(), e.getY());

        if (m_path != null) {
          IconNode select_node = (IconNode) m_path.getLastPathComponent();
          NodeInfo cn = (NodeInfo) select_node.getUserObject();

          if (cn.file != null) {
              if (cn.nodetype.compareTo("R File") == 0) {

                  String name = cn.file.getName().substring(0, cn.file.getName().indexOf("."));
                  String newpath = cn.file.getParentFile()+ System.getProperty("file.separator")+name+".r";

                  if (frame1.pathRProg == null || frame1.pathRProg.length() <= 0) {
                            frame1.rdialog.setVisible(true);
                        }
                        else {
                            try {
                                String run_command = frame1.pathRProg + " \"" + cn.file.getPath() + "\"";
                                final Process process = Runtime.getRuntime().exec(run_command);
                            } catch (Exception ex) {
                                JOptionPane.showOptionDialog(frame1,
                                        "Please check the path for SnpClip.",
                                        "Error",
                                        JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                                        frame1.rdialog.jTextField1.requestFocus();
                                        frame1.rdialog.setVisible(true);
                            }
                        }
              }
               if (cn.nodetype.compareTo("PDF File") == 0) {
                  try {
                      Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL "+cn.file.getPath());

                  } catch (Exception ex) {
                      ex.printStackTrace();
                  }
              }
              else {
                  if (frame1.pathEdigorProg == null ||
                      frame1.pathEdigorProg.length() <= 0) {
                      jOpenFileMenuItem1_actionPerformed();
                  } else {
                      try {
                          Runtime.getRuntime().exec(frame1.pathEdigorProg + " \"" +
                                                    cn.file.getPath() + "\"");

                      } catch (Exception ex) {
                          ex.printStackTrace();
                      }
                  }
              }
          }
        }
      }
    }

    private void maybeShowPopup(MouseEvent e) {
      int treerow = MyTree1.getRowForLocation(e.getX(), e.getY());
      if ( (treerow != -1) && (e.isPopupTrigger())) {
        TreePath m_path = MyTree1.getPathForLocation(e.getX(), e.getY());
        MyTree1.setSelectionPath(m_path);
        IconNode select_node = (IconNode) m_path.getLastPathComponent();
        NodeInfo nodeInfo = (NodeInfo) select_node.getUserObject();

        if (nodeInfo.nodetype.indexOf("ErrorParameterNode") >= 0) {
          jPopupMenuPara.show(e.getComponent(), e.getX(), e.getY());
        }

        else if (nodeInfo.nodetype.indexOf("ErrorPedigreeNode") >= 0) {
          jPopupMenuPedigree.show(e.getComponent(), e.getX(), e.getY());
        }

        else if (nodeInfo.nodetype.indexOf("ErrorLocusNode") >= 0) {
          jPopupMenuLocus.show(e.getComponent(), e.getX(), e.getY());
        }

        else if (nodeInfo.nodetype.indexOf("ErrorIBDNode") >= 0) {
          jPopupMenuIBD.show(e.getComponent(), e.getX(), e.getY());
        }

        else if (nodeInfo.nodetype.indexOf("ErrorGenomeNode") >= 0) {
          jPopupMenuGenome.show(e.getComponent(), e.getX(), e.getY());
        }

        else if (nodeInfo.nodetype.indexOf("Project") >= 0) {
          jPopupMenuProject.show(e.getComponent(), e.getX(), e.getY());
        }

        else if (nodeInfo.nodetype.indexOf("Jobs") >= 0) {
          int analysiscount = GetAnalysisCounts(select_node, nodeInfo.nodetype);
          if (analysiscount == 0) {
            jRunAllMenuItem.setEnabled(false);
          }
          else
            jRunAllMenuItem.setEnabled(true);
          jPopupMenuJobs.show(e.getComponent(), e.getX(), e.getY());
        }

        else if (nodeInfo.nodetype.indexOf("Analysis") >= 0) {
          jPopupMenuAnalysis.show(e.getComponent(), e.getX(), e.getY());
        }

        if (nodeInfo.file!=null) {

            NodeInfo parent = (NodeInfo)((IconNode)select_node.getParent()).getUserObject();

            if (parent.nodetype.compareTo("Internal") == 0)
            {
                if (nodeInfo.nodetype.compareTo("Pedigree File") == 0)
                    jPopupMenuFile2.show(e.getComponent(), e.getX(), e.getY());

                else if (nodeInfo.nodetype.compareTo("Parameter File") == 0)
                    jPopupMenuFile1.show(e.getComponent(), e.getX(), e.getY());

                else
                    jPopupMenuFile3.show(e.getComponent(), e.getX(), e.getY());
            }
            else {
                if (nodeInfo.nodetype.compareTo("Parameter File") == 0)
                    jPopupMenuFile1.show(e.getComponent(), e.getX(), e.getY());

                else if (nodeInfo.nodetype.compareTo("Result File") == 0)
                    jPopupMenuFile5.show(e.getComponent(), e.getX(), e.getY());

                else
                    jPopupMenuFile4.show(e.getComponent(), e.getX(), e.getY());

            }
        }
      }
    }
  }

  class DeleteKeyListener extends KeyAdapter {
      public void keyTyped(KeyEvent e) {
          if (e.getKeyChar() == KeyEvent.VK_DELETE) {
              jDeleteFileMenuItem_actionPerformed();
          }
      }
  }
}
