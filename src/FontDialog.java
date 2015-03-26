package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import java.awt.event.*;

public class FontDialog extends JDialog{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jMainPanel = new JPanel();
  JPanel jButtonPanel = new JPanel();
  JPanel jSubPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jFontTypePanel = new JPanel();
  JPanel jFontStylePanel = new JPanel();
  JPanel jFontSizePanel = new JPanel();
  BorderLayout borderLayout3 = new BorderLayout();
  BorderLayout borderLayout4 = new BorderLayout();
  BorderLayout borderLayout5 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JTextField jFontName = new JTextField();
  JList jFontNameList = new JList();
  JLabel jLabel2 = new JLabel();
  JList jFontStyleList = new JList();
  JLabel jLabel3 = new JLabel();
  JList jFontSizeList = new JList();
  JTextField jFontStyle = new JTextField();
  JTextField jFontSize = new JTextField();
  JButton jOKButton = new JButton();
  JButton jButton2 = new JButton();

  BorderLayout borderLayout6 = new BorderLayout();
  JPanel jSamplePanel = new JPanel();
  JPanel jSampleLeftPanel = new JPanel();
  JPanel jSampleRightPanel = new JPanel();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  BorderLayout borderLayout7 = new BorderLayout();
  JTextField jSampleText = new JTextField();
  Border border1;

  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  JScrollPane jScrollPane3 = new JScrollPane();

  String setfontname = "Courier";
  String setfontsize = "11";
  int setfontstyle= java.awt.Font.PLAIN;

  boolean ok_clicked = false;

  public FontDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public FontDialog() {
    this(null, "", true);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder(BorderFactory.createLineBorder(SystemColor.activeCaptionBorder,1),"Sample");
    border1 = BorderFactory.createCompoundBorder(titledBorder1,BorderFactory.createEmptyBorder(5,10,8,10));
    this.setSize(new Dimension(400,300));
    panel1.setLayout(borderLayout1);
    jMainPanel.setLayout(borderLayout2);
    jFontTypePanel.setLayout(borderLayout3);
    jFontStylePanel.setLayout(borderLayout4);
    jFontSizePanel.setLayout(borderLayout5);
    jLabel1.setText("Font:");
    jLabel2.setText("Font style:");
    jLabel3.setText("Size:");
    jFontName.setEnabled(false);
    jFontName.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption));
    jFontName.setDisabledTextColor(Color.black);
    jFontName.setEditable(true);
    jFontName.setText("");
    jFontStyle.setEnabled(false);
    jFontStyle.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption));
    jFontStyle.setDisabledTextColor(Color.black);
    jFontStyle.setText("");
    jFontSize.setEnabled(false);
    jFontSize.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption));
    jFontSize.setDisabledTextColor(Color.black);
    jFontSize.setEditable(true);
    jFontSize.setText("");
    borderLayout3.setVgap(3);
    borderLayout4.setVgap(3);
    borderLayout5.setVgap(3);

    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.getViewport().add(jFontNameList, null);
    jScrollPane2.getViewport().add(jFontStyleList, null);
    jScrollPane3.getViewport().add(jFontSizeList, null);

    jScrollPane1.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption));
    jFontNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jFontNameList_valueChanged(e);
      }
    });
    jScrollPane2.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption));
    jFontStyleList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jFontStyleList_valueChanged(e);
      }
    });
    jScrollPane3.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption));
    jFontSizeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
        jFontSizeList_valueChanged(e);
      }
    });
    jOKButton.setPreferredSize(new Dimension(65, 25));
    jOKButton.setText("OK");
    jOKButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jOKButton_actionPerformed(e);
      }
    });
    jButton2.setText("Cancel");
    jButton2.setPreferredSize(new Dimension(65, 25));
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });

    jSubPanel.setLayout(borderLayout6);
    jSamplePanel.setBorder(border1);
    jSamplePanel.setLayout(borderLayout7);
    jSampleText.setEnabled(true);
    jSampleText.setFont(new java.awt.Font("Dialog", 0, 12));
    jSampleText.setEditable(false);
    jSampleText.setText("AaBbYyZz");
    jSampleText.setHorizontalAlignment(SwingConstants.CENTER);
    getContentPane().add(panel1);
    jMainPanel.setPreferredSize(new Dimension(320, 210));
    jButtonPanel.setPreferredSize(new Dimension(80, 210));
    jSubPanel.setPreferredSize(new Dimension(400, 90));
    jSubPanel.setBorder(new EmptyBorder(0,12,8,3));
    panel1.add(jMainPanel, BorderLayout.CENTER);
    jFontTypePanel.setPreferredSize(new Dimension(150,210));
    jFontTypePanel.setBorder(new EmptyBorder(5,10,5,5));
    jFontStylePanel.setPreferredSize(new Dimension(100,210));
    jFontStylePanel.setBorder(new EmptyBorder(5,5,5,5));
    jFontSizePanel.setPreferredSize(new Dimension(70,210));
    jFontSizePanel.setBorder(new EmptyBorder(5,5,5,5));
    jButtonPanel.setBorder(new EmptyBorder(18,5,5,10));

    jFontName.setPreferredSize(new Dimension(150, 22));
    jScrollPane1.setPreferredSize(new Dimension(150, 156));
    GraphicsEnvironment g = GraphicsEnvironment.getLocalGraphicsEnvironment();
    String fontlist[] = g.getAvailableFontFamilyNames();
    jFontNameList.setListData(fontlist);
    jFontNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    jFontStyle.setPreferredSize(new Dimension(150, 22));
    jScrollPane2.setPreferredSize(new Dimension(100, 156));
    String fontstyle[] = {"Regular","Italic","Bold","Bold Italic"};
    jFontStyleList.setListData(fontstyle);
    jFontStyleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    jFontSize.setPreferredSize(new Dimension(150, 22));
    jScrollPane3.setPreferredSize(new Dimension(70, 156));
    String fontsize[] = {"8","9","10","11","12","14","16","18","20","22","24","26","28","36"};
    jFontSizeList.setListData(fontsize);
    jFontSizeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    jMainPanel.add(jFontTypePanel,  BorderLayout.WEST);
    jFontTypePanel.add(jLabel1, BorderLayout.NORTH);
    jFontTypePanel.add(jFontName, BorderLayout.CENTER);
    jFontTypePanel.add(jScrollPane1,  BorderLayout.SOUTH);
    jMainPanel.add(jFontStylePanel,  BorderLayout.CENTER);
    jFontStylePanel.add(jLabel2,  BorderLayout.NORTH);
    jFontStylePanel.add(jScrollPane2, BorderLayout.SOUTH);
    jFontStylePanel.add(jFontStyle,  BorderLayout.CENTER);
    jMainPanel.add(jFontSizePanel,  BorderLayout.EAST);
    jFontSizePanel.add(jLabel3,  BorderLayout.NORTH);
    jFontSizePanel.add(jScrollPane3, BorderLayout.SOUTH);
    jFontSizePanel.add(jFontSize,  BorderLayout.CENTER);
    panel1.add(jButtonPanel,  BorderLayout.EAST);
    jButtonPanel.add(jOKButton, null);
    jButtonPanel.add(jButton2, null);
    panel1.add(jSubPanel,  BorderLayout.SOUTH);
    jSubPanel.add(jSamplePanel, BorderLayout.CENTER);
    jSamplePanel.add(jSampleText, BorderLayout.CENTER);
    jSubPanel.add(jSampleLeftPanel,  BorderLayout.WEST);
    jSubPanel.add(jSampleRightPanel,  BorderLayout.EAST);
    jSampleLeftPanel.setPreferredSize(new Dimension(140, 90));
    jSampleRightPanel.setPreferredSize(new Dimension(80, 90));
    jSampleText.setBorder(BorderFactory.createLoweredBevelBorder());
    jSampleText.setFont(new java.awt.Font(setfontname, setfontstyle, Integer.parseInt(setfontsize)));
  }

  void jFontNameList_valueChanged(ListSelectionEvent e) {
    setfontname = jFontNameList.getSelectedValue().toString();
    jFontName.setText(setfontname);
    jSampleText.setFont(new java.awt.Font(setfontname, setfontstyle, Integer.parseInt(setfontsize)));

  }

  void jFontStyleList_valueChanged(ListSelectionEvent e) {
    switch(jFontStyleList.getSelectedIndex())
    {
      case 0:
        setfontstyle = java.awt.Font.PLAIN;
        break;
      case 1:
        setfontstyle = java.awt.Font.ITALIC;
        break;
      case 2:
        setfontstyle = java.awt.Font.BOLD;
        break;
      case 3:
        setfontstyle = java.awt.Font.BOLD|java.awt.Font.ITALIC;
        break;
    }

    jFontStyle.setText(jFontStyleList.getSelectedValue().toString());
    jSampleText.setFont(new java.awt.Font(setfontname, setfontstyle, Integer.parseInt(setfontsize)));
  }

  void jFontSizeList_valueChanged(ListSelectionEvent e) {
    setfontsize = jFontSizeList.getSelectedValue().toString();
    jFontSize.setText(setfontsize);
    jSampleText.setFont(new java.awt.Font(setfontname, setfontstyle, Integer.parseInt(setfontsize)));
  }

  void jOKButton_actionPerformed(ActionEvent e) {
    ok_clicked = true;
    dispose();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    dispose();
  }
}


