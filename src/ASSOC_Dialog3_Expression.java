package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.*;
import java.util.*;
import javax.swing.tree.*;
import javax.swing.text.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ASSOC_Dialog3_Expression extends JDialog implements ActionListener, ItemListener, DocumentListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  CompoundBorder Bottomborder = new CompoundBorder(new EmptyBorder(1,1,1,1), new BottomBorder());
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel4 = new JLabel();
  EdgeBorder lineborder = new EdgeBorder();

  Border selectedBorder = new EtchedBorder();
  Border unselectedBorder = new EmptyBorder(selectedBorder.getBorderInsets(new JLabel()));

  String vtype[] = {"constant","trait","covariate","phenotype"};
String[] missingoption = {"Dot (\".\")", "Blank (\"\")","Space (\" \")","Tab (\"\\t\")","other"};//,

  JScrollPane jScrollPane1 = new JScrollPane();
  //JTextArea Expression = new JTextArea();
  JTextPane Expression = new JTextPane();
  AbstractDocument doc;
  static final int MAX_CHARACTERS = 1000;

  ButtonGroup bg1=new ButtonGroup();
  ButtonGroup bg2=new ButtonGroup();

  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  XYLayout xYLayout2 = new XYLayout();



  String nameblock=new String();
  String expblock=new String();
  String optionblock=new String();
  String type=new String();
  String listoption=new String();


  boolean okclicked=false;
  JPopupMenu jPopupListMenu = new JPopupMenu();
    BorderLayout borderLayout2 = new BorderLayout();


  XYLayout xYLayout6 = new XYLayout();
  XYLayout xYLayout7 = new XYLayout();
  XYLayout xYLayout8 = new XYLayout();
  XYLayout xYLayout9 = new XYLayout();

  MouseListener listener = new DragMouseAdapter();
  BorderLayout borderLayout3 = new BorderLayout();
  XYLayout xYLayout10 = new XYLayout();
  XYLayout xYLayout11 = new XYLayout();
    JPanel jPanel3 = new JPanel();
    XYLayout xYLayout12 = new XYLayout();
    JLabel jLabel8 = new JLabel();
    JLabel jLabel9 = new JLabel();
    JLabel jLabel10 = new JLabel();
    JLabel jLabel11 = new JLabel();
    JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JLabel jLabel19 = new JLabel();
  Border border1;
  Border LoweredBevelBorder;
  Border RaisedBevelBorder;
  JPanel jPanel5 = new JPanel();
  XYLayout xYLayout15 = new XYLayout();
  JRadioButton jRadioButton3 = new JRadioButton();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  JPanel jPanel6 = new JPanel();
  JTextField jTextField3 = new JTextField();
  JLabel jLabel7 = new JLabel();
  JComboBox jComboBox3 = new JComboBox(missingoption);
  JLabel jLabel2 = new JLabel();
  JComboBox jComboBox2 = new JComboBox(vtype);
  JLabel jLabel6 = new JLabel();
  JTextField Name = new JTextField();
  JTextField jTextField4 = new JTextField();
  JRadioButton jContinueRButton = new JRadioButton();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField1 = new JTextField();
  JRadioButton jBinaryRButton = new JRadioButton();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel1 = new JLabel();
  XYLayout xYLayout5 = new XYLayout();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  JLabel jLabel20 = new JLabel();
  JPanel jCentertopPanel = new JPanel();
  JPanel jCenterbottomPanel = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JPanel jCentert1Panel = new JPanel();
  JPanel jCentert2Panel = new JPanel();
  JPanel jCentert3Panel = new JPanel();
  BorderLayout borderLayout6 = new BorderLayout();
  BorderLayout borderLayout7 = new BorderLayout();
  BorderLayout borderLayout8 = new BorderLayout();

  JPanel buttonPanel = new JPanel();
  BorderLayout borderLayout9 = new BorderLayout();
  Border border2;
  Border border3;
  Border border4;
  Border border5;
  BorderLayout borderLayout10 = new BorderLayout();
  JPanel jCenterb1Panel = new JPanel();
  JPanel jCenterb2Panel = new JPanel();
  BorderLayout borderLayout11 = new BorderLayout();
  BorderLayout borderLayout12 = new BorderLayout();
  JScrollPane jScrollPane2 = new JScrollPane();
  JScrollPane jScrollPane3 = new JScrollPane();
  JScrollPane jScrollPane4 = new JScrollPane();
  JList jList1 = new JList();
  JList jList2 = new JList();

  static Vector traitList=new Vector();
  static Vector phenoList=new Vector();
  static Vector covList=new Vector();
  static Vector markerList=new Vector();
  static Vector alleleList=new Vector();
  static Vector AllList = new Vector();

  Vector arithmetic = new Vector();
  Vector comparison = new Vector();
  Vector logical = new Vector();
  Vector elementary = new Vector();
  Vector marker = new Vector();
  Vector mean_var = new Vector();
  Vector outlier = new Vector();
  Vector tai = new Vector();

  OidNode currentNode;

  protected JTree  m_tree = null;
  protected DefaultTreeModel m_model = null;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JPanel jBottomPanel = new JPanel();
 JLabel jLabel21 = new JLabel();
  BorderLayout borderLayout13 = new BorderLayout();

  SimpleAttributeSet[] attrs;
  JLabel jLabel22 = new JLabel();

    public ASSOC_Dialog3_Expression(Frame frame, String title, boolean modal) {

      super(frame, title, modal);
      this.setResizable(true);
      try {
          jbInit();
          pack();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

  public ASSOC_Dialog3_Expression() {
      this(null, "", false);
  }
  private void jbInit() throws Exception {
    border1 = BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,SystemColor.control,new Color(115, 114, 105),SystemColor.control);
    LoweredBevelBorder = BorderFactory.createLoweredBevelBorder();
    RaisedBevelBorder = BorderFactory.createRaisedBevelBorder();

    titledBorder1 = new TitledBorder("List option");
    titledBorder2 = new TitledBorder("Variable");
    border2 = BorderFactory.createEmptyBorder(5,10,0,10);
    border3 = BorderFactory.createEmptyBorder(10,10,0,10);
    border4 = BorderFactory.createEmptyBorder(5,10,5,10);
    border5 = BorderFactory.createEmptyBorder(10,10,0,10);
    panel1.setLayout(borderLayout1);
      this.setSize(new Dimension(555, 370));
      this.setResizable(true);

      StyledDocument styledDoc = Expression.getStyledDocument();
      if (styledDoc instanceof AbstractDocument) {
        doc = (AbstractDocument) styledDoc;
        doc.setDocumentFilter(new DocumentSizeFilter(MAX_CHARACTERS));
      }
      else {
        //System.err.println("Text pane's document isn't an AbstractDocument!");
        System.exit( -1);
      }

      init_tree();

      jLabel4.setBorder(new EmptyBorder(0,15,0,0)); //top, left, bottom, right
    jLabel4.setText("Fill in the fields below for function variable will be created.");

    jLabel4.setBorder(new EmptyBorder(0,10,0,0)); //top, left, bottom, right

    jButton1.addActionListener(this);
    jButton2.addActionListener(this);
    jButton3.addActionListener(this);

    jTopPanel.setLayout(borderLayout2);
      jTopPanel.setBackground(Color.white);
    jTopPanel.setBorder(Bottomborder);
    jTopPanel.setPreferredSize(new Dimension(555, 35));
    jBottomPanel.setPreferredSize(new Dimension(555, 25));
    jBottomPanel.setLayout(borderLayout13);

    jBottomPanel.setBorder(new EmptyBorder(0,10,0,0)); //top, left, bottom, right

      //jCenterPanel.setBorder(Bottomborder);
      jCenterPanel.setLayout(borderLayout4);
      jCenterPanel.setPreferredSize(new Dimension(555,310));//327,220

      jCentertopPanel.setPreferredSize(new Dimension(555,110));
    jCentertopPanel.setLayout(borderLayout5);//327,220
      jCenterbottomPanel.setBorder(border5);
    jCenterbottomPanel.setPreferredSize(new Dimension(555,200));
    jCenterbottomPanel.setLayout(borderLayout10);//327,220
      jCentert1Panel.setBorder(border2);
    //jCentert1Panel.setPreferredSize(new Dimension(555,112));
    //jCentert1Panel.setLayout(borderLayout7);//327,220
      jCentert2Panel.setBorder(border3);
    jCentert2Panel.setPreferredSize(new Dimension(555,75));
    jCentert2Panel.setLayout(borderLayout6);//327,220
      jCentert3Panel.setPreferredSize(new Dimension(555,30));
    jCentert3Panel.setLayout(borderLayout9);//327,220


    buttonPanel.setLayout(borderLayout8);
    buttonPanel.setBorder(new EmptyBorder(0,1,0,2)); //top, left, bottom, right
      buttonPanel.setPreferredSize(new Dimension(60,75));


      jButton1.setPreferredSize(new Dimension(50,22));
      jButton2.setPreferredSize(new Dimension(50,22));
      jButton3.setPreferredSize(new Dimension(50,22));

      jCenterb1Panel.setPreferredSize(new Dimension(100, 200));
    jCenterb1Panel.setLayout(borderLayout12);
      jCenterb2Panel.setPreferredSize(new Dimension(300, 200));
    jCenterb2Panel.setLayout(borderLayout11);


        jPanel3.setLayout(xYLayout12);
    jLabel8.setBorder(border1);
    jLabel8.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel8.setText("+");
        jLabel9.setBorder(border1);
    jLabel9.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel9.setText("-");
        jLabel10.setBorder(border1);
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setText("/");
        jLabel11.setBorder(border1);
    jLabel11.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel11.setText("*");
        jLabel12.setBorder(border1);
    jLabel12.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel12.setText("==");
        jLabel13.setBorder(border1);
    jLabel13.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel13.setText(">");
    jLabel14.setBorder(border1);
    jLabel14.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel14.setText("<");
    jLabel15.setBorder(border1);
    jLabel15.setToolTipText("");
    jLabel15.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel15.setText("<>");
    jLabel16.setBorder(border1);
    jLabel16.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel16.setText("And");
    jLabel17.setBorder(border1);
    jLabel17.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel17.setText("Or");
    jLabel18.setBorder(border1);
    jLabel18.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel18.setText("Not");
    jLabel19.setBorder(border1);
    jLabel19.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel19.setText("(");
    jPanel5.setBorder(titledBorder1);
    jPanel5.setLayout(xYLayout15);
    jRadioButton3.setText("Traits");
    jRadioButton1.setSelected(true);
    jRadioButton1.setText("None");
    jRadioButton2.setText("Markers");
    jPanel6.setBorder(titledBorder2);
    jPanel6.setLayout(gridBagLayout1);
    jTextField3.setEnabled(false);
    jTextField3.setText("");
    jLabel7.setEnabled(false);
    jLabel7.setText("Time limit");
    jLabel2.setText("Name");
    jComboBox2.setEnabled(false);
    jComboBox2.setEditable(false);
    jComboBox2.setSelectedIndex(2);
    jComboBox2.addItemListener(this);
    jLabel6.setEnabled(false);
    jLabel6.setText("Missing value");
    Name.setText("");
    jTextField4.setEnabled(false);
    jTextField4.setText("30");
    jContinueRButton.setEnabled(false);
    jContinueRButton.setSelected(true);
    jContinueRButton.setText("Continuous");
    jContinueRButton.addItemListener(this);
    jContinueRButton.addItemListener(this);
    jLabel3.setEnabled(false);
    jLabel3.setText("Affected");
    jTextField2.setEnabled(false);
    jTextField2.setText("0");
    jTextField1.setEnabled(false);
    jTextField1.setText("1");
    jBinaryRButton.setEnabled(false);
    jBinaryRButton.setText("Binary");
    jBinaryRButton.addItemListener(this);
    jBinaryRButton.addItemListener(this);
    jLabel5.setEnabled(false);
    jLabel5.setText("Unaffected");
    jLabel1.setEnabled(false);
    jLabel1.setText("Type");
    jComboBox3.setEnabled(false);
    jComboBox3.addItemListener(this);
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("OK");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.setText("Cancel");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Reset");
    jLabel20.setBorder(border1);
    jLabel20.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel20.setText(")");
    jPanel3.setBorder(border4);
    borderLayout7.setHgap(3);
    borderLayout6.setHgap(5);
    borderLayout10.setHgap(5);
    jScrollPane4.setPreferredSize(new Dimension(150, 200));
    jScrollPane3.setPreferredSize(new Dimension(150, 200));
    borderLayout11.setHgap(5);
    jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
    panel1.setMinimumSize(new Dimension(555, 460));
    panel1.setPreferredSize(new Dimension(555, 460));
    borderLayout8.setVgap(3);
    Expression.setFont(new java.awt.Font("Dialog", 0, 12));
    Expression.setMargin(new Insets(0, 5, 0, 5));
    jLabel22.setBorder(border1);
    jLabel22.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel22.setIconTextGap(2);
    jLabel22.setText("$name$");
    getContentPane().add(panel1);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel4, BorderLayout.CENTER);
    panel1.add(jCenterPanel,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(Expression, null);
    Expression.getDocument().addDocumentListener(this);
    jPanel6.add(jLabel2,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(-3, 10, 0, 10), 43, 4));
    jPanel6.add(jComboBox3,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(8, 0, 0, 0), -5, 0));
    jPanel6.add(jLabel6,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 10, 0, 0), 15, 4));
    jPanel6.add(Name,  new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(-3, 0, 0, 0), 114, -1));
    jPanel6.add(jLabel7,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 10, 7, 0), 38, 4));
    jPanel6.add(jTextField4,  new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(8, 0, 7, 0), 102, -1));
    jPanel6.add(jTextField3,  new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(8, 8, 0, 0), 36, -1));
    jPanel6.add(jContinueRButton,  new GridBagConstraints(3, 2, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 15, 7, 0), 1, -5));
    jPanel6.add(jLabel3,  new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 0, 0, 48), 27, 4));
    jPanel6.add(jTextField2,  new GridBagConstraints(5, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(8, 60, 7, 8), 33, -1));
    jPanel6.add(jTextField1,  new GridBagConstraints(5, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(8, 60, 0, 8), 33, -1));
    jPanel6.add(jBinaryRButton,  new GridBagConstraints(3, 1, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 15, 0, 0), 23, -5));
    jPanel6.add(jLabel5,  new GridBagConstraints(5, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 0, 7, 48), 16, 4));
    jPanel6.add(jComboBox2,  new GridBagConstraints(4, 0, 2, 1, 1.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(-3, 15, 0, 8), 52, 0));
    jPanel6.add(jLabel1,  new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(-3, 15, 0, 0), 26, 4));
    jPanel5.add(jRadioButton3,         new XYConstraints(10, 53, 60, 20));
    jPanel5.add(jRadioButton1,       new XYConstraints(10, -3, 60, 20));
    jPanel5.add(jRadioButton2,         new XYConstraints(10, 25, 70, 20));

    buttonPanel.add(jButton1,  BorderLayout.NORTH);
    buttonPanel.add(jButton2,  BorderLayout.CENTER);
    buttonPanel.add(jButton3,  BorderLayout.SOUTH);

jCentert1Panel.add(jPanel5,  BorderLayout.WEST);
jCentert1Panel.add(jPanel6,  BorderLayout.CENTER);

jCentert2Panel.add(jScrollPane1,  BorderLayout.CENTER);
jCentert2Panel.add(buttonPanel,  BorderLayout.EAST);

JLabel empty_label = new JLabel();
empty_label.setPreferredSize(new Dimension(100,20));
jCentert3Panel.add(jPanel3, BorderLayout.CENTER);
jCentert3Panel.add(empty_label, BorderLayout.EAST);

    jCenterPanel.add(jCentertopPanel,  BorderLayout.NORTH);
   // jCentertopPanel.add(jCentert1Panel, BorderLayout.NORTH);
    jCentertopPanel.add(jCentert2Panel, BorderLayout.CENTER);
    jCentertopPanel.add(jCentert3Panel,  BorderLayout.SOUTH);
    jCenterPanel.add(jCenterbottomPanel,  BorderLayout.CENTER);
    jCenterbottomPanel.add(jCenterb1Panel,  BorderLayout.CENTER);
    jCenterb1Panel.add(jScrollPane2, BorderLayout.CENTER);
    jScrollPane2.getViewport().add(m_tree, null);
    jCenterbottomPanel.add(jCenterb2Panel,  BorderLayout.EAST);
    jCenterb2Panel.add(jScrollPane3,  BorderLayout.CENTER);
    jScrollPane3.getViewport().add(jList1, null);
    jCenterb2Panel.add(jScrollPane4,  BorderLayout.EAST);
    panel1.add(jBottomPanel,  BorderLayout.SOUTH);
    jBottomPanel.add(jLabel21, BorderLayout.CENTER);
    jScrollPane4.getViewport().add(jList2, null);

    jPanel3.add(jLabel8,               new XYConstraints(0, 2, 20, 22));
    jPanel3.add(jLabel13,                 new XYConstraints(105, 2, 20, 22));
    jPanel3.add(jLabel14,                   new XYConstraints(125, 2, 20, 22));
    jPanel3.add(jLabel15,                    new XYConstraints(145, 2, 25, 22));
    jPanel3.add(jLabel16,                    new XYConstraints(175, 2, 35, 22));
    jPanel3.add(jLabel17,              new XYConstraints(210, 2, 35, 22));
    jPanel3.add(jLabel18,                 new XYConstraints(245, 2, 35, 22));
    jPanel3.add(jLabel19,                new XYConstraints(285, 2, 20, 22));
    jPanel3.add(jLabel20,            new XYConstraints(305, 2, 20, 22));
    jPanel3.add(jLabel12,      new XYConstraints(85, 2, 20, 22));
    jPanel3.add(jLabel11,    new XYConstraints(60, 2, 20, 22));
    jPanel3.add(jLabel10,    new XYConstraints(40, 2, 20, 22));
    jPanel3.add(jLabel9,    new XYConstraints(20, 2, 20, 22));
    jPanel3.add(jLabel22,      new XYConstraints(330, 2, 45, 22));

    Add_Mouse_Listener_Label_Border();

    jList1.setFixedCellHeight(15);
    jList2.setFixedCellHeight(15);

    init_operators();

    Name.getDocument().addDocumentListener(this);
    bg1.add(jRadioButton1);
    bg1.add(jRadioButton2);
    bg1.add(jRadioButton3);
    bg2.add(jBinaryRButton);
    bg2.add(jContinueRButton);

    attrs = initAttributes(2);


    Expression.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent e)
      {
        Expression.requestFocus(true);
        try {
          int offset = Expression.viewToModel(e.getPoint());
          //System.out.println("offset : "+offset);
          setWordSelection(offset);
        }
        catch (Exception e2) {}
      }
    });
  }

  protected void setWordSelection(int offset) throws Exception
  {
      int start = Utilities.getWordStart(Expression, offset);
      int end = Utilities.getWordEnd(Expression, offset);

      String text = Expression.getText();
      int textlength = text.length();
      StringBuffer textb = new StringBuffer(text);
      StringBuffer textb_rev = new StringBuffer(text);

      textb_rev.reverse();

      int right_p = textb.indexOf(">>",start);
      int left_p = textb_rev.indexOf("<<",textlength-end);

      if (right_p != -1 && left_p != -1)
      {
        String total_word = textb.substring(textlength-left_p- 2, right_p+2);

        if (total_word.indexOf("(") < 0 && total_word.indexOf(")") < 0
            && total_word.indexOf(",") < 0 && total_word.indexOf("'") < 0)
        {
          Expression.select(textlength - left_p - 2, right_p + 2);
          //System.out.println("word : "+total_word);
          Expression.getCaret().setSelectionVisible(true);

          //Highlighter hl = Expression.getHighlighter();
          //hl.addHighlight(textlength - left_p - 2, right_p + 2, new DefaultHighlighter.DefaultHighlightPainter( new Color(204, 204, 255) ) );
        }
      }
  }

  protected SimpleAttributeSet[] initAttributes(int length) {
          //Hard-code some attributes.
          SimpleAttributeSet[] attrs = new SimpleAttributeSet[length];

          attrs[0] = new SimpleAttributeSet();
          StyleConstants.setFontFamily(attrs[0], "Dialog");
          StyleConstants.setFontSize(attrs[0], 12);

          attrs[1] = new SimpleAttributeSet(attrs[0]);
          StyleConstants.setFontFamily(attrs[1], "Dialog");//Arial Narrow
          StyleConstants.setFontSize(attrs[1], 10);
          //StyleConstants.setBold(attrs[1], true);
          //StyleConstants.setItalic(attrs[1], true);

           return attrs;
      }

  void init_operators()
  {
    arithmetic.add(new ListData("-"," - ","Unary Negation/Subtraction"));
    arithmetic.add(new ListData("**"," ** ","Exponentiation"));
    arithmetic.add(new ListData("%"," % ","Modulus"));
    arithmetic.add(new ListData("/"," / ","Division"));
    arithmetic.add(new ListData("*"," * ","Multiplication"));
    arithmetic.add(new ListData("+"," + ","Addition"));

    comparison.add(new ListData("<"," < ","Less than"));
    comparison.add(new ListData("<="," <= ","Less than or equal to"));
    comparison.add(new ListData("<>"," <> ","not equal to"));
    comparison.add(new ListData("=="," == ","Equal to"));
    comparison.add(new ListData(">"," > ","Greater than"));
    comparison.add(new ListData(">="," >= ","Greater than or equal to"));

    logical.add(new ListData("not"," not ","Logical negation"));
    logical.add(new ListData("and"," and ","Logical AND"));
    logical.add(new ListData("or"," or ","Logical OR"));

    elementary.add(new ListData("exp","exp (<<x>>) ","exp(x)"));
    elementary.add(new ListData("log","log (<<x>>) ","log(x)"));
    elementary.add(new ListData("log10","log10 (<<x>>) ","log10(x)"));
    elementary.add(new ListData("pow","pow (<<x>>,<<y>>) ","pow(x,y)"));
    elementary.add(new ListData("sqrt","sqrt (<<x>>) ","sqrt(x)"));
    elementary.add(new ListData("fabs","fabs (<<x>>) ","fabs(x)"));
    elementary.add(new ListData("ceil","ceil (<<x>>) ","ceil(x)"));
    elementary.add(new ListData("floor","floor (<<x>>) ","floor(x)"));
    elementary.add(new ListData("min","min (<<x1>>,<<x2>>,...,<<xn>>) ","min(x1,x2,...,xn)"));
    elementary.add(new ListData("max","max (<<x1>>,<<x2>>,...,<<xn>>) ","min(x1,x2,...,xn)"));

    marker.add(new ListData("dominant","dominant (<<marker>>,'<<allele value>>') ","dominant(marker, 'Ai')"));
    marker.add(new ListData("recessive","recessive (<<marker>>,'<<allele value>>') ","recessive(marker, 'Ai')"));
    marker.add(new ListData("additive","additive (<<marker>>,'<<allele value>>') " ,"additive(marker, 'Ai')"));
    marker.add(new ListData("genotype","genotype (<<marker>>,'<<allele value>>','<<allele value>>') ","genotype(marker, 'Ai', 'Aj')"));

    mean_var.add(new ListData("mean_adj","mean_adj (<<var>>) ","mean_adj(var)"));
    mean_var.add(new ListData("var_adj","var_adj (<<var>>) ","var_adj(var)"));
    mean_var.add(new ListData("z_score","z_score (<<var>>) ","z_score(var)"));

    outlier.add(new ListData("trim","trim (<<var>>,<<value>>) ","trim(x, number)"));
    outlier.add(new ListData("winsor","winsor (<<var>>,<<value>>) ","winsor(x, number)"));

    tai.add(new ListData("tai","tai (<<var1>>,<<var2>>) ","tai(var, var)"));
    tai.add(new ListData("utai","utai (<<var1>>,<<var2>>) ","utai(var, var)"));
  }

  private void init_tree()
  {
    OidNode root = new OidNode(new DefaultListModel(), "Root");
    IconNode rootnode = new IconNode(root, "Root");
    currentNode = root;

    DefaultListModel listModel = new DefaultListModel();
    listModel.addElement("<All>");
    OidNode cons =  new OidNode(listModel, "Constants");
    IconNode Constants = new IconNode(cons, "Folder");
    rootnode.add(Constants);

    DefaultListModel listModel2 = new DefaultListModel();
    listModel2.addElement("<All>");
    listModel2.addElement("Arithmetic");
    listModel2.addElement("Comparison");
    listModel2.addElement("Logical");
    OidNode ope = new OidNode(listModel2, "Operators");
    IconNode Operators = new IconNode(ope, "Folder");
    rootnode.add(Operators);

    DefaultListModel listModel3 = new DefaultListModel();
    listModel3.addElement("<All>");
    listModel3.addElement("Trait");
    listModel3.addElement("Covariate");
    listModel3.addElement("Phenotype");
    listModel3.addElement("Marker");
    listModel3.addElement("Allele");
    OidNode exi = new OidNode(listModel3, "Existing Variables");
    IconNode Existing = new IconNode(exi, "Folder");
    rootnode.add(Existing);

    OidNode fun = new OidNode(new DefaultListModel(),"Functions");
    IconNode Functions = new IconNode(fun,"Folder_plus");
    rootnode.add(Functions);

    DefaultListModel listModel4 = new DefaultListModel();
    listModel4.addElement("<All>");
    OidNode ele = new OidNode(listModel4,"Elementary");
    IconNode Elementary = new IconNode(ele, "Folder");
    Functions.add(Elementary);

    DefaultListModel listModel5 = new DefaultListModel();
    listModel5.addElement("<All>");
    OidNode mar = new OidNode(listModel5,"Marker");
    IconNode Marker  = new IconNode(mar,"Folder");
    Functions.add(Marker);

    DefaultListModel listModel6 = new DefaultListModel();
    listModel6.addElement("<All>");
    OidNode mean_var = new OidNode(listModel6,"Mean_Var");
    IconNode Mean  = new IconNode(mean_var,"Folder");
    Functions.add(Mean);

    DefaultListModel listModel7 = new DefaultListModel();
    listModel7.addElement("<All>");
    OidNode out = new OidNode(listModel7,"Outlier");
    IconNode Outlier  = new IconNode(out,"Folder");
    Functions.add(Outlier);

    DefaultListModel listModel8 = new DefaultListModel();
    listModel8.addElement("<All>");
    OidNode tai = new OidNode(listModel8,"TAI");
    IconNode Transmitted  = new IconNode(tai,"Folder");
    Functions.add(Transmitted);

    m_model = new MyFunctionTreeModel(rootnode);
    m_tree = new JTree(m_model);

    m_tree.putClientProperty("JTree.icons", makeIcons());
    m_tree.setCellRenderer(new MyFunctionTreeRenderer());

    m_tree.setRootVisible(false);
    m_tree.setEditable(false);

    m_tree.addTreeSelectionListener(new OidSelectionListener());

    jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jList1.addListSelectionListener(new SharedListSelectionHandler());

    jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jList2.addMouseListener(new ActionMouseListener() );
  }

  class ActionMouseListener extends MouseAdapter
  {
    public void mouseClicked(MouseEvent e)
    {
      JList list = (JList) e.getSource();
      String value = "";

      try{
        ListData ld = (ListData)list.getModel().getElementAt(list.getSelectedIndex());
        jLabel21.setText(ld.m_tooltip);
        value = ld.m_exp;
      }
      catch(ClassCastException cc)
      {
        jLabel21.setText("");
        value = list.getModel().getElementAt(list.getSelectedIndex()).toString();
      }

      try {
        if (e.getClickCount() == 2) {

          //fine <<var>>
          if(value.indexOf("<<")>=0 && value.indexOf(">>")>=0)
          {
            boolean inside = false;

            for(int i=0;i<=value.length()-1;i++)
            {
               String eachc = value.substring(i, i+1);
               if(eachc.compareTo("<")==0 || eachc.compareTo(">")==0)
                 inside = true;

               if(inside)
                 doc.insertString(doc.getLength(), eachc, attrs[1]);
               else
                   doc.insertString(doc.getLength(), eachc, attrs[0]);

               if(eachc.compareTo(">")==0)
               {
                 if(value.substring(i+1, i+2).compareTo(">")!=0)
                   inside = false;
               }
            }
          }
          else
          {
              doc.insertString(doc.getLength(), value, attrs[0]);
          }

          Expression.requestFocus(true);
        }
      } catch (BadLocationException ble) {
        //System.err.println("Couldn't insert initial text.");
      }
    }
  }

  Hashtable makeIcons(){
    ImageIcon folder = new ImageIcon(MyInternalFrame.class.getResource("folder_small.png"));
    ImageIcon folder_plus = new ImageIcon(MyInternalFrame.class.getResource("folder_plus.png"));
    ImageIcon folder_minus = new ImageIcon(MyInternalFrame.class.getResource("folder_minus.png"));
    ImageIcon folder_open = new ImageIcon(MyInternalFrame.class.getResource("Folder_open.png"));

    Hashtable icons = new Hashtable();
    icons.put("Folder", folder);
    icons.put("Folder_plus", folder_plus);
    icons.put("Folder_minus", folder_minus);
    icons.put("Folder_open", folder_open);

    return icons;
  }

  class OidSelectionListener implements TreeSelectionListener
  {
    public void valueChanged(TreeSelectionEvent e) {
      TreePath path = e.getPath();
      IconNode node = (IconNode) path.getLastPathComponent();
      OidNode nodeInfo = (OidNode) node.getUserObject();
      currentNode = nodeInfo;

      ListModel model = nodeInfo.getModel();

      if(model != null)
        jList1.setModel(model);

      jList1.setSelectedIndex(0);  //'ALL' for default
    }
  }

  class OidNode {
          protected DefaultListModel list_model = new DefaultListModel();
          protected String m_name;

          public OidNode(DefaultListModel listmodel, String name) {
                  list_model = listmodel;
                  m_name = name;
          }

          public DefaultListModel getModel() {
                  return list_model;
          }

          public String getName() {
                  return m_name;
          }

          public String toString() {
                  return m_name;
          }
  }

  class ListData
  {
    protected String m_name;
    protected String m_exp;
    protected String m_tooltip;

    public ListData(String name, String exp, String tp)
    {
      m_name = name;
      m_exp = exp;
      m_tooltip = tp;
    }

    public String getName()
    {
      return m_name;
    }

    public String toString()
    {
      return m_name;
    }
  }

  private class DragMouseAdapter extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
      JComponent c = (JComponent) e.getSource();
      if(c.isEnabled())
        c.setBorder(LoweredBevelBorder);
    }

    public void mouseEntered(MouseEvent e) {
      JLabel c = (JLabel) e.getSource();
      if(c.isEnabled())
        c.setBorder(RaisedBevelBorder);
    }

    public void mouseExited(MouseEvent e) {

      JLabel c = (JLabel) e.getSource();
      if(c.isEnabled())
        c.setBorder(border1);
    }

    public void mouseClicked(MouseEvent e)
    {
      JLabel c = (JLabel) e.getSource();
      String text = c.getText();

      if(c.isEnabled())
      {
        try
        {
          int offset = Expression.getCaret().getDot();
          doc.insertString(offset, text, attrs[0]);
        }
        catch (BadLocationException ble)
        {
              //System.err.println("Couldn't insert initial text.");
        }
        Expression.requestFocus(true);
      }
    }
  }

  class SharedListSelectionHandler implements ListSelectionListener
  {
    public void valueChanged(ListSelectionEvent e)
    {
      DefaultListModel listModel = new DefaultListModel();

      if (currentNode.getName().compareTo("Constants") == 0) {
        listModel.addElement(new ListData("e","e","2.71828.."));
        listModel.addElement(new ListData("pi","pi","3.14159.."));
      }
      if (currentNode.getName().compareTo("Operators") == 0) {
        switch (jList1.getSelectedIndex())
        {
            case 0: //All
              for (Enumeration es = arithmetic.elements();es.hasMoreElements(); )
                listModel.addElement((ListData)es.nextElement());
              for (Enumeration es = comparison.elements(); es.hasMoreElements(); )
                listModel.addElement((ListData)es.nextElement());
              for (Enumeration es = logical.elements(); es.hasMoreElements(); )
                listModel.addElement((ListData)es.nextElement());
              break;
            case 1: //Arithmetic
              for (Enumeration es = arithmetic.elements(); es.hasMoreElements(); )
                listModel.addElement((ListData)es.nextElement());
              break;
            case 2: //Comparison
              for (Enumeration es = comparison.elements(); es.hasMoreElements(); )
                listModel.addElement((ListData)es.nextElement());
              break;
            case 3: //Logical
              for (Enumeration es = logical.elements(); es.hasMoreElements(); )
                listModel.addElement((ListData)es.nextElement());
              break;
        }
      }

      if (currentNode.getName().compareTo("Existing Variables") == 0) {
        switch (jList1.getSelectedIndex())
        {
            case 0: //All
              for (Enumeration es = AllList.elements();es.hasMoreElements(); )
                listModel.addElement(es.nextElement());
              break;
            case 1: //trait
              for (Enumeration es = traitList.elements(); es.hasMoreElements(); )
                listModel.addElement(es.nextElement());
              break;
            case 2: //covariate
              for (Enumeration es = covList.elements(); es.hasMoreElements(); )
                listModel.addElement(es.nextElement());
              break;
            case 3: //phenotype
              for (Enumeration es = phenoList.elements(); es.hasMoreElements(); )
                listModel.addElement(es.nextElement());
              break;
            case 4: //marker
              for (Enumeration es = markerList.elements(); es.hasMoreElements(); )
                listModel.addElement(es.nextElement());
              break;
            case 5: //allele
              for (Enumeration es = alleleList.elements(); es.hasMoreElements(); )
                listModel.addElement(es.nextElement());
              break;
        }
      }

      if (currentNode.getName().compareTo("Elementary") == 0)
      {
        for (Enumeration es = elementary.elements(); es.hasMoreElements(); )
          listModel.addElement((ListData)es.nextElement());
      }

      if (currentNode.getName().compareTo("Marker") == 0)
      {
        for (Enumeration es = marker.elements(); es.hasMoreElements(); )
          listModel.addElement((ListData)es.nextElement());
      }

      if (currentNode.getName().compareTo("Mean_Var") == 0)
      {
        for (Enumeration es = mean_var.elements(); es.hasMoreElements(); )
          listModel.addElement((ListData)es.nextElement());
      }

      if (currentNode.getName().compareTo("Outlier") == 0)
      {
        for (Enumeration es = outlier.elements(); es.hasMoreElements(); )
          listModel.addElement((ListData)es.nextElement());
      }

      if (currentNode.getName().compareTo("TAI") == 0)
      {
        for (Enumeration es = tai.elements(); es.hasMoreElements(); )
          listModel.addElement((ListData)es.nextElement());
      }

      jList2.setModel(listModel);
      jList2.setSelectedIndex(0);
    }
  }

  private void Add_Mouse_Listener_Label_Border()
  {
    jLabel8.addMouseListener(listener);
    jLabel9.addMouseListener(listener);
    jLabel10.addMouseListener(listener);
    jLabel11.addMouseListener(listener);
    jLabel12.addMouseListener(listener);
    jLabel13.addMouseListener(listener);
    jLabel14.addMouseListener(listener);
    jLabel15.addMouseListener(listener);
    jLabel16.addMouseListener(listener);
    jLabel17.addMouseListener(listener);
    jLabel18.addMouseListener(listener);
    jLabel19.addMouseListener(listener);
    jLabel20.addMouseListener(listener);
    jLabel22.addMouseListener(listener);
  }

  public void actionPerformed(ActionEvent e) {
    Object o = e.getSource();
    if (o == jButton1) {
      OKButton_actionPerformed(e);
    }
    else if (o == jButton2) {
      jCancelButton_actionPerformed(e);
    }
    else if (o == jButton3) {
      Expression.setText("");
    }
  }

  public void Set_var_panel(boolean val)
  {
    jLabel1.setEnabled(val);
    jLabel6.setEnabled(val);
    jLabel7.setEnabled(val);
    jLabel3.setEnabled(val);
    jLabel5.setEnabled(val);
    jBinaryRButton.setEnabled(val);
    jContinueRButton.setEnabled(val);
    jComboBox2.setEnabled(val);
    jComboBox3.setEnabled(val);
    jTextField4.setEnabled(val);

    if(val)
    {
      if(jComboBox3.getSelectedIndex()==4)
      {
        jTextField3.setEnabled(val);
      }

      if(jBinaryRButton.isSelected())
      {
        jTextField1.setEnabled(val);
        jTextField2.setEnabled(val);
      }
    }
    else
    {
        jTextField3.setEnabled(val);
        jTextField1.setEnabled(val);
        jTextField2.setEnabled(val);
    }

    Expression.setEnabled(val);
    m_tree.setEnabled(val);
    jList1.setEnabled(val);
    jList2.setEnabled(val);

    jLabel8.setEnabled(val);
    jLabel9.setEnabled(val);
    jLabel10.setEnabled(val);
    jLabel11.setEnabled(val);
    jLabel12.setEnabled(val);
    jLabel13.setEnabled(val);
    jLabel14.setEnabled(val);
    jLabel15.setEnabled(val);
    jLabel16.setEnabled(val);
    jLabel17.setEnabled(val);
    jLabel18.setEnabled(val);
    jLabel19.setEnabled(val);
    jLabel20.setEnabled(val);
    jLabel22.setFocusable(val);
    jLabel22.setEnabled(val);
  }


  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();

    if(document == Name.getDocument())
    {
      if (Name.getText().length() > 0)
        Set_var_panel(true);
      else
        Set_var_panel(false);
    }

      if(Name.getText().length()>0 && Expression.getText().length()>0)
      {
        jButton1.setEnabled(true);
      }
      else
        jButton1.setEnabled(false);
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }



  public void itemStateChanged(ItemEvent e) {
    Object o = e.getSource();
    if(o == jComboBox2)
    {
      int index = jComboBox2.getSelectedIndex();
      switch(index)
      {
          case 0://constant
            jTextField1.setEnabled(false);
            jTextField2.setEnabled(false);
            jTextField3.setEnabled(false);
            jBinaryRButton.setEnabled(false);
            jContinueRButton.setEnabled(false);
            jComboBox3.setEnabled(false);
            jLabel3.setEnabled(false);
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
            break;
          case 1:
          case 2:
          case 3:
            jLabel6.setEnabled(true);
            jComboBox3.setEnabled(true);
            if(jComboBox3.getSelectedIndex()==4)
            {
              jTextField3.setEnabled(true);
            }
            jBinaryRButton.setEnabled(true);
            jContinueRButton.setEnabled(true);

            if(jBinaryRButton.isSelected())
            {
              jTextField1.setEnabled(true);
              jTextField2.setEnabled(true);
            }
            break;
      }
    }
    else if(o == jComboBox3)
    {
      if(jComboBox3.getSelectedIndex()==4)
        jTextField3.setEnabled(true);
      else
        jTextField3.setEnabled(false);
    }

    else if (o == jBinaryRButton) {
      jLabel3.setEnabled(true);
      jLabel5.setEnabled(true);
      jTextField1.setEnabled(true);
      jTextField2.setEnabled(true);
    }
    else if (o == jContinueRButton) {
      jTextField1.setEnabled(false);
      jTextField2.setEnabled(false);
      jLabel3.setEnabled(false);
      jLabel5.setEnabled(false);
    }
  }

  void setInfo(Vector list)
  {
    init_state();
    String name = list.get(0).toString();
    String expression = list.get(1).toString();
    String type = list.get(2).toString();
    String option;
    String listoption = list.get(4).toString();

    if(listoption.compareTo("markers")==0)
      jRadioButton2.setSelected(true);
    else if(listoption.compareTo("traits")==0)
      jRadioButton3.setSelected(true);

    jComboBox2.setSelectedItem(type);
    Name.setText(name);
    Expression.setText(expression);

    if(type.compareTo("constant")==0) // constant
    {
      jComboBox2.setSelectedIndex(0);
      if(list.size()==4) // with time_limit option
      {
        String timelimit = list.get(3).toString();
        jTextField4.setText(timelimit);
      }
    }
    else // trait, phenotype,covraite
    {
      option = list.get(3).toString();
      StringTokenizer st1 = new StringTokenizer(option, ",");

      if(st1.countTokens()==0) // it's continuous
      {
        jTextField1.setEnabled(false);
        jLabel3.setEnabled(false);
        jTextField2.setEnabled(false);
        jLabel5.setEnabled(false);
        jContinueRButton.setEnabled(true);
        jTextField3.setEnabled(true);
        jLabel6.setEnabled(true);

        jContinueRButton.setSelected(true);
        jTextField1.setText("1");
        jTextField2.setText("0");
        jTextField3.setText("");
      }
      else
      {
        while (st1.hasMoreTokens()) {
          String token = st1.nextToken();
          if (token.indexOf("binary") != -1) {
            jBinaryRButton.setEnabled(true);
            jBinaryRButton.setSelected(true);
            jTextField1.setEnabled(true);
            jLabel3.setEnabled(true);
            jTextField2.setEnabled(true);
            jLabel5.setEnabled(true);
          }
          else if (token.indexOf("affected") != -1) { // affected, unaffected
            String temparr[] = new String[2];
            temparr = token.split("=");
            String temp = temparr[0].replaceAll("\"", "");
            temp = temp.replaceAll(",", "").trim();
            String temp1 = temparr[1].replaceAll("\"", "");
            temp1 = temp1.replaceAll(",", "").trim();

            if(temp.compareTo("affected")==0)
              jTextField1.setText(temp1);
            if(temp.compareTo("unaffected")==0)
              jTextField2.setText(temp1);
          }
          else if (token.indexOf("missing") != -1) {
            String temparr[] = new String[2];
            temparr = token.split("=");
            String temp = temparr[1].replaceAll("\"", "");
            temp = temp.replaceAll(",", "").trim();
            jTextField3.setText(temp);
          }
        }
      }
    }
  }

  void init_state()
  {
    Name.setText("");
    Expression.setText("");
    jComboBox2.setSelectedIndex(0);
    jTextField4.setEnabled(true);
    jTextField3.setText("30");
    jTextField1.setText("1");
    jTextField2.setText("0");
    jTextField3.setText("");
    jLabel7.setEnabled(true);
    jTextField1.setEnabled(false);
    jTextField2.setEnabled(false);
    jTextField3.setEnabled(false);
    jBinaryRButton.setEnabled(false);
    jContinueRButton.setEnabled(false);
    jLabel3.setEnabled(false);
    jLabel5.setEnabled(false);
    jLabel6.setEnabled(false);
    Name.setFocusable(true);
  }

  void OKButton_actionPerformed(ActionEvent e) {
    optionblock="";
    nameblock="";
    type = "";
    expblock = "";
    listoption = "";

    type = jComboBox2.getSelectedItem().toString();
    nameblock = Name.getText();
    expblock = Expression.getText();

    if(jRadioButton2.isSelected())
      listoption = "markers";

    else if(jRadioButton3.isSelected())
      listoption = "traits";

    if(jComboBox2.getSelectedIndex()==0) // constant
    {
      String time = jTextField4.getText();
      if(time.length()>0)
        optionblock = optionblock + "\n"+ jTextField4.getText();
      else
        optionblock = optionblock + "\n"+"30"; // default
      optionblock = optionblock.trim();
    }
    else
    {
      if(jBinaryRButton.isSelected())//binary
      {
        optionblock = optionblock + "binary";
        String affected = jTextField1.getText();
        String unaffected = jTextField2.getText();

        if(affected.length()>0)
          optionblock = optionblock + ", affected = " + jTextField1.getText();
        //else
        //  optionblock = optionblock + ", affected = 1, ";//default

        if(unaffected.length()>0)
          optionblock = optionblock + ", unaffected = " + jTextField2.getText();
        //else
        //  optionblock = optionblock + ", unaffected = 0, ";//default
      }

      String missing = jTextField3.getText();
      if(missing.length()>0)
        optionblock = optionblock + ", missing = " + jTextField3.getText();
      //else
      //  optionblock = optionblock + "missing = \"\"";//default
    }
    okclicked=true;
    init_state();
    dispose();
  }

  void jCancelButton_actionPerformed(ActionEvent e) {
    init_state();
    dispose();
  }

}
