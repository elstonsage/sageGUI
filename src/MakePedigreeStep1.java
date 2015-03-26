package sage;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.io.*;

public class MakePedigreeStep1 extends WizardPanel implements ActionListener, DocumentListener{
  JButton DirButton;
  JTextField pName;
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton3 = new JRadioButton();
  JRadioButton jCheck1 = new JRadioButton();
  JRadioButton jCheck2 = new JRadioButton();
  ButtonGroup bp = new ButtonGroup();
  JLabel jLabel1 = new JLabel();

  JRadioButton tab = new JRadioButton("tab");
  JRadioButton comma = new JRadioButton("comma");
  JRadioButton space = new JRadioButton("space");
  JRadioButton other = new JRadioButton("other:");
  JRadioButton single = new JRadioButton("single");
  JRadioButton multiple = new JRadioButton("multiple");

  JTextField delimiter = new JTextField("");

  ButtonGroup bp2 = new ButtonGroup();
  ButtonGroup bp3 = new ButtonGroup();

  NodeInfo nodeinfo = new NodeInfo("External", "External", "External", "External");
  IconNode externalnode = new IconNode(nodeinfo, "External");

  public MakePedigreeStep1()
  {
       super("Import Pedigree Data", "Select raw data file to make a pedigree data file.");

       jRadioButton1.setFocusPainted(false);
       jRadioButton3.setFocusPainted(false);

       jCheck1.setFocusPainted(false);
       jCheck2.setFocusPainted(false);
       tab.setFocusPainted(false);
       comma.setFocusPainted(false);
       space.setFocusPainted(false);
       other.setFocusPainted(false);

       single.setFocusPainted(false);
       multiple.setFocusPainted(false);

       JPanel top = new JPanel();
       JLabel pLabel = new JLabel("Path");
       pName = new JTextField();
       pName.setPreferredSize(new Dimension(120,20));
       pName.setEditable(false);
       pName.setBackground(Color.white);
       DirButton = new JButton("Browse...");
       DirButton.setPreferredSize(new Dimension(60,20));
       DirButton.setMargin(new Insets(2,2,2,2));
       DirButton.addActionListener(this);
       JLabel line = new JLabel();
       line.setBorder(BorderFactory.createEtchedBorder());
       line.setText("");
       line.setPreferredSize(new Dimension(358,2));

       top.setLayout(new BorderLayout(5,10));
       top.add(pLabel, BorderLayout.WEST);
       top.add(pName, BorderLayout.CENTER);
       top.add(DirButton, BorderLayout.EAST);
       top.add(line, BorderLayout.SOUTH);
       top.setBorder(new EmptyBorder(5,8,8,8));

       JPanel middle = new JPanel();
       middle.setLayout(new BorderLayout());
       jLabel1.setText("Select format of your file");
       jRadioButton1.setText("Text (character delimited)");
       jRadioButton1.setSelected(true);
       jRadioButton3.setText("Excel File");
       jRadioButton3.setSelected(false);
       jCheck1.setText("Yes");
       jCheck2.setText("No");
       jCheck1.setSelected(true);

       ButtonGroup check = new ButtonGroup();
       check.add(jCheck1);
       check.add(jCheck2);

       bp.add(jRadioButton1);
       bp.add(jRadioButton3);
       jRadioButton1.addActionListener(this);
       jRadioButton3.addActionListener(this);

       bp2.add(tab);
       tab.setSelected(true);
       bp2.add(comma);
       bp2.add(space);
       bp2.add(other);
       other.addActionListener(this);
       tab.addActionListener(this);
       comma.addActionListener(this);
       space.addActionListener(this);

       bp3.add(single);
       single.setSelected(true);
       bp3.add(multiple);
       single.addActionListener(this);
       multiple.addActionListener(this);

       JPanel delimiters = new JPanel();
       delimiters.setBorder(new EmptyBorder(0,20,0,0));
       delimiters.setPreferredSize(new Dimension(300,20));
       delimiters.setLayout(new GridLayout(1,5,3,0));
       delimiters.add(tab);
       delimiters.add(comma);
       delimiters.add(space);
       delimiters.add(other);
       delimiters.add(delimiter);

       delimiter.setEnabled(false);
       delimiter.setEditable(false);

       JPanel delimiter_mode = new JPanel();
       delimiter_mode.setBorder(new EmptyBorder(0,20,0,0));
       delimiter_mode.setLayout(new BorderLayout());
       single.setPreferredSize(new Dimension(62,20));
       delimiter_mode.add(single, BorderLayout.WEST);
       delimiter_mode.add(multiple, BorderLayout.CENTER);

       JPanel d = new JPanel();
       d.setPreferredSize(new Dimension(300, 50));
       d.setLayout(new BorderLayout());
       d.add(delimiters, BorderLayout.NORTH);
       d.add(delimiter_mode, BorderLayout.CENTER);

       JPanel d2 = new JPanel();
       d2.setLayout(new BorderLayout());
       d2.add(jRadioButton1, BorderLayout.NORTH);
       d2.add(d, BorderLayout.CENTER);

       JLabel label = new JLabel("Header");

       jRadioButton3.setPreferredSize(new Dimension(200, 20));
       label.setPreferredSize(new Dimension(200, 20));

       JPanel inmiddle = new JPanel();
       inmiddle.setLayout(new BorderLayout());
       inmiddle.add(d2, BorderLayout.NORTH);
       inmiddle.add(jRadioButton3, BorderLayout.CENTER);
       inmiddle.add(label, BorderLayout.SOUTH);
       inmiddle.setBorder(new EmptyBorder(6,20,3,1));

       JPanel headeroption = new JPanel();
       headeroption.setBorder(new EmptyBorder(0,40,0,0));
       headeroption.setLayout(new BorderLayout());
       jCheck1.setPreferredSize(new Dimension(62,20));
       headeroption.add(jCheck1, BorderLayout.WEST);
       headeroption.add(jCheck2, BorderLayout.CENTER);

       middle.add(jLabel1, BorderLayout.NORTH);
       middle.add(inmiddle, BorderLayout.CENTER);
       middle.add(headeroption, BorderLayout.SOUTH);
       middle.setPreferredSize(new Dimension(360,180));
       middle.setBorder(new EmptyBorder(5,20,8,5));

       JPanel bottom = new JPanel();
       bottom.setPreferredSize(new Dimension(360, 25));

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());
       panel.add("North",top);
       panel.add("Center",middle);
       panel.add("South", bottom);

       JPanel center = new JPanel();
       center.setLayout(new UnitLayout());
       center.add(panel);
       add("Center", center);

       pName.getDocument().addDocumentListener(this);
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == pName.getDocument()) {
      getDataModel().setValue("path", pName.getText());
    }
    if (getDataModel().hasValue("path"))
    {
      canMoveForward = true;
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }


  public void actionPerformed(ActionEvent event)
  {
    Object source = event.getSource();
    if(source == DirButton)
    {
      String defaultpath = getDataModel().getValue("info.DirPath").toString();
      jFileChooser1.setCurrentDirectory(new File(defaultpath));

      if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this)) {
        String filepath = jFileChooser1.getSelectedFile().getPath();
        pName.setText(filepath);
        getDataModel().setValue("path" , filepath);
      }
    }

    if(source == jRadioButton1)
    {
        tab.setEnabled(true);
        comma.setEnabled(true);
        space.setEnabled(true);
        other.setEnabled(true);
        single.setEnabled(true);
        multiple.setEnabled(true);
        ((JWizard)getParent().getParent()).nav.IsExcel = false;
    }

    if(source == jRadioButton3)
    {
      tab.setEnabled(false);
      comma.setEnabled(false);
      space.setEnabled(false);
      other.setEnabled(false);
      single.setEnabled(false);
      multiple.setEnabled(false);
      ((JWizard)getParent().getParent()).nav.IsExcel = true;
    }

    if(source == other)
    {
      delimiter.setEditable(true);
      delimiter.setEnabled(true);
    }

    if(source == tab || source == comma || source==space)
    {
      delimiter.setEditable(false);
      delimiter.setEnabled(false);
    }
  }

  public void init_state()
  {
    pName.setText("");
    jRadioButton1.setSelected(true);
    tab.setSelected(true);
    single.setSelected(true);
    jCheck1.setSelected(true);
  }
}
