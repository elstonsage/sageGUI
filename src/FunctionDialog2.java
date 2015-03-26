package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;
//import javax.help.HelpSet;
//import javax.help.HelpBroker;
//import javax.help.JHelpNavigator;
//import javax.help.JHelpContentViewer;
//import javax.help.CSH;
import java.net.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class FunctionDialog2 extends JDialog implements ActionListener, ItemListener, DocumentListener{
  // JavaHelp items
//HelpSet hs;
//HelpBroker hb;
//ActionListener helper;

// embedded help components
//JPanel helpPanel;
//JHelpNavigator nav;
//JHelpContentViewer viewer;

  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  CompoundBorder Bottomborder = new CompoundBorder(new EmptyBorder(1,1,1,1), new BottomBorder());
  XYLayout xYLayout1 = new XYLayout();
  JButton OKButton = new JButton();
  JButton jCancelButton = new JButton();
  XYLayout xYLayout3 = new XYLayout();
  JLabel jLabel4 = new JLabel();
  JTextField Name = new JTextField();
  EdgeBorder lineborder = new EdgeBorder();

  String vtype[] = {"constant","trait","covariate","phenotype"};

  JComboBox jComboBox1 = new JComboBox(vtype);
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea Expression = new JTextArea();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  ButtonGroup bg=new ButtonGroup();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  XYLayout xYLayout2 = new XYLayout();
  JLabel jLabel7 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JPanel jPanel2 = new JPanel();
  XYLayout xYLayout5 = new XYLayout();
  JTextField jTextField2 = new JTextField();
  JCheckBox jCheckBox2 = new JCheckBox();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JCheckBox jCheckBox1 = new JCheckBox();
  JLabel jLabel5 = new JLabel();

  String nameblock=new String();
  String expblock=new String();
  String optionblock=new String();
  String type=new String();


  boolean okclicked=false;
  JPopupMenu jPopupListMenu = new JPopupMenu();
  JButton jButton1 = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();

  public FunctionDialog2(Frame frame, String title, boolean modal) {
      super(frame, title, modal);
      try {
          jbInit();
          pack();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

  public FunctionDialog2() {
      this(null, "", false);
  }
  private void jbInit() throws Exception {
    // open HelpSet, send console message
/*    URL hsURL = new URL( (new File(".")).toURL(), "help/HelpSet.hs");
    hs = new HelpSet(null, hsURL);

    // create HelpBroker from HelpSet
    hb = hs.createHelpBroker();

    // enable function key F1
    hb.enableHelpKey(getRootPane(), "TEST", hs);

    // create an embedded help panel
    helpPanel = new JPanel(new GridLayout(1, 2, 5, 5));

    // add a content viewer
    viewer = new JHelpContentViewer(hs);
    viewer.setPreferredSize(new Dimension(400, 220));
    viewer.setCurrentID("TEST");

    // add a navigator with a table of contents view
    nav = (JHelpNavigator)
        hs.getNavigatorView("TOC").createNavigator(viewer.getModel());
    nav.setPreferredSize(new Dimension(400, 220));

    // add the components to the layout
    jTopPanel.setBackground(Color.white);
    helpPanel.add(nav);
    helpPanel.add(viewer);

    helper = new CSH.DisplayHelpFromSource(hb);
    jButton1.addActionListener(helper);
    CSH.setHelpIDString(jButton1, "USER_DEFINE_ FUNCTION_VARIABLE");
*/
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(borderLayout1);
      this.setSize(new Dimension(400, 350));
      this.setResizable(false);

jLabel4.setBorder(new EmptyBorder(0,15,0,0)); //top, left, bottom, right
    jLabel4.setText("Fill in the fields below for function variable will be created.");
    jTopPanel.setLayout(borderLayout2);
    OKButton.addActionListener(this);
    jCancelButton.addActionListener(this);
    Name.setText("");

      jTopPanel.setPreferredSize(new Dimension(400, 40));
      jCenterPanel.setBorder(Bottomborder);
      jCenterPanel.setLayout(xYLayout1);
      jCenterPanel.setPreferredSize(new Dimension(400,270));//327,220

      jBottomPanel.setPreferredSize(new Dimension(400, 40));
      jBottomPanel.setLayout(xYLayout3);
      jCenterPanel.setBorder(lineborder);
      jBottomPanel.setBorder(lineborder);

      OKButton.setEnabled(false);
    OKButton.setText("OK");
      jCancelButton.setText("Cancel");
      jCancelButton.setMargin(new Insets(2, 2, 2, 2));
    jLabel1.setText("Expression (Right click to get the variable list)");
    jLabel2.setText("Name");
    jPanel1.setBorder(titledBorder2);
    jPanel1.setLayout(xYLayout2);
    jLabel7.setText("Time limit");
    jPanel2.setLayout(xYLayout5);
    jPanel2.setBorder(titledBorder1);
    jTextField2.setEnabled(false);
    jTextField2.setText("0");
    jCheckBox2.setEnabled(false);
    jCheckBox2.setSelected(true);
    jCheckBox2.setText("Continuous");
    jCheckBox2.addItemListener(this);
    jLabel6.setEnabled(false);
    jLabel6.setDoubleBuffered(false);
    jLabel6.setText("Missing value");
    jTextField1.setEnabled(false);
    jTextField1.setText("1");
    jLabel3.setEnabled(false);
    jLabel3.setText("Affected");
    jTextField3.setEnabled(false);
    jTextField3.setText("");
    jCheckBox1.setEnabled(false);
    jCheckBox1.setText("Binary");
    jCheckBox1.addItemListener(this);
    jLabel5.setEnabled(false);
    jLabel5.setText("Unaffected");
    jTextField4.setText("30");
    Expression.setText("");
    jButton1.setText("Help");
    getContentPane().add(panel1);
      panel1.add(jTopPanel,  BorderLayout.NORTH);
    jTopPanel.add(jLabel4, BorderLayout.CENTER);
      panel1.add(jCenterPanel,  BorderLayout.CENTER);
    jCenterPanel.add(jScrollPane1,                 new XYConstraints(13, 80, 370, 65));
    jScrollPane1.getViewport().add(Expression, null);
      panel1.add(jBottomPanel, BorderLayout.SOUTH);
      jBottomPanel.add(jCancelButton,    new XYConstraints(242, 7, 65, 25));
      jBottomPanel.add(OKButton,     new XYConstraints(165, 7, 65, 25));
    jBottomPanel.add(jButton1,     new XYConstraints(319, 7, 65, 25));
    jCenterPanel.add(jLabel1,    new XYConstraints(15, 55, 366, 23));
    jCenterPanel.add(jLabel2,      new XYConstraints(15, 5, 50, 20));
    jCenterPanel.add(Name,           new XYConstraints(15, 30, 255, 20));
    jCenterPanel.add(jComboBox1,                new XYConstraints(280, 30, 100, 20));
    jCenterPanel.add(jPanel1,        new XYConstraints(15, 157, 100, 97));
    jPanel1.add(jLabel7,   new XYConstraints(5, 5, 80, 20));
    jPanel1.add(jTextField4,        new XYConstraints(3, 32, 82, 20));
    jCenterPanel.add(jPanel2,       new XYConstraints(128, 157, 255, 97));
    jPanel2.add(jTextField2,    new XYConstraints(160, 32, 80, 20));
    jPanel2.add(jCheckBox2,      new XYConstraints(2, 33, 82, 20));
    jPanel2.add(jLabel6,     new XYConstraints(90, 60, 70, 20));
    jPanel2.add(jTextField1,     new XYConstraints(160, 5, 80, 20));
    jPanel2.add(jLabel3,     new XYConstraints(90, 5, 70, 20));
    jPanel2.add(jTextField3,    new XYConstraints(160, 60, 80, 20));
    jPanel2.add(jCheckBox1,   new XYConstraints(2, 5, 80, 20));
    jPanel2.add(jLabel5,     new XYConstraints(90, 32, 70, 20));

    jCheckBox1.addItemListener(this);
    jCheckBox2.addItemListener(this);
    jComboBox1.addItemListener(this);

    Name.getDocument().addDocumentListener(this);
    Expression.getDocument().addDocumentListener(this);
    bg.add(jCheckBox1);
    bg.add(jCheckBox2);
    MouseListener popupListener = new PopupListener();
    Expression.addMouseListener(popupListener);
  }

  public void actionPerformed(ActionEvent e) {
    Object o = e.getSource();
    if (o == OKButton) {
      OKButton_actionPerformed(e);
    }
    else if (o == jCancelButton) {
      jCancelButton_actionPerformed(e);
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
      if(Name.getText().length()>0 && Expression.getText().length()>0)
      {
        OKButton.setEnabled(true);
      }
      else
        OKButton.setEnabled(false);
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }



  public void itemStateChanged(ItemEvent e) {
    Object o = e.getSource();
    if(o == jComboBox1)
    {
      int index = jComboBox1.getSelectedIndex();
      switch(index)
      {
          case 0://constant
            jTextField1.setEnabled(false);
            jTextField2.setEnabled(false);
            jTextField3.setEnabled(false);
            jCheckBox1.setEnabled(false);
            jCheckBox2.setEnabled(false);
            jLabel3.setEnabled(false);
            jLabel5.setEnabled(false);
            jLabel6.setEnabled(false);
            jTextField4.setEnabled(true);
            jLabel7.setEnabled(true);
            break;
          case 1:
          case 2:
          case 3:
            jTextField3.setEnabled(true);
            jCheckBox1.setEnabled(true);
            jCheckBox2.setEnabled(true);
            jLabel6.setEnabled(true);
            jTextField4.setEnabled(false);
            jLabel7.setEnabled(false);
            break;
      }

    }
    else if (o == jCheckBox1) {
      jTextField1.setEnabled(true);
      jTextField2.setEnabled(true);
      jLabel3.setEnabled(true);
      jLabel5.setEnabled(true);
      jTextField1.setEnabled(true);
      jTextField2.setEnabled(true);
    }
    else if (o == jCheckBox2) {
      jTextField1.setEnabled(false);
      jTextField2.setEnabled(false);
      jLabel3.setEnabled(false);
      jLabel5.setEnabled(false);
      jTextField1.setEnabled(false);
      jTextField2.setEnabled(false);
    }
  }

  void setInfo(Vector list)
  {
    init_state();
    String name = list.get(0).toString();
    String expression = list.get(1).toString();
    String type = list.get(2).toString();
    String option;

    jComboBox1.setSelectedItem(type);
    Name.setText(name);
    Expression.setText(expression);

    if(type.compareTo("constant")==0) // constant
    {
      jComboBox1.setSelectedIndex(0);
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
        jCheckBox2.setEnabled(true);
        jTextField3.setEnabled(true);
        jLabel6.setEnabled(true);

        jCheckBox2.setSelected(true);
        jTextField1.setText("1");
        jTextField2.setText("0");
        jTextField3.setText("");
      }
      else
      {
        while (st1.hasMoreTokens()) {
          String token = st1.nextToken();
          if (token.indexOf("binary") != -1) {
            jCheckBox1.setEnabled(true);
            jCheckBox1.setSelected(true);
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
    jComboBox1.setSelectedIndex(0);
    jTextField4.setEnabled(true);
    jTextField3.setText("30");
    jTextField1.setText("1");
    jTextField2.setText("0");
    jTextField3.setText("");
    jLabel7.setEnabled(true);
    jTextField1.setEnabled(false);
    jTextField2.setEnabled(false);
    jTextField3.setEnabled(false);
    jCheckBox1.setEnabled(false);
    jCheckBox2.setEnabled(false);
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

    type = jComboBox1.getSelectedItem().toString();
    nameblock = Name.getText();
    expblock = Expression.getText();

    if(jComboBox1.getSelectedIndex()==0) // constant
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
      if(jCheckBox1.isSelected())//binary
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


  class PopupListener extends MouseAdapter {

    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
      if (e.isPopupTrigger()) {
        jPopupListMenu.removeAll();
        for (Enumeration es = FunctionDialog1.TraitList.elements(); es.hasMoreElements(); ) {
                JMenuItem jMenulist = new JMenuItem();
                jMenulist.setText(es.nextElement().toString());
                jPopupListMenu.add(jMenulist);
                FunctionBlockHandler2 FBHandler = new FunctionBlockHandler2();
                jMenulist.addActionListener(FBHandler);
             }
        jPopupListMenu.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }

  class FunctionBlockHandler2 implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      Object ob = e.getSource();
      String action = e.getActionCommand();
      Expression.append(action);
    }
  }
}
