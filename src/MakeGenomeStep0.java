package sage;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.GridLayout;
import java.io.File;

public class MakeGenomeStep0 extends WizardPanel2 implements DocumentListener, ActionListener{
  JTextField pPath;

  JLabel jLabel1 = new JLabel();
  JRadioButton jRadioButton1 = new JRadioButton("Text (character delimited)");
  JRadioButton jRadioButton2 = new JRadioButton("Excel File");
  ButtonGroup bp = new ButtonGroup();
  JRadioButton jCheck1 = new JRadioButton();
  JRadioButton jCheck2 = new JRadioButton();

  JRadioButton tab = new JRadioButton("tab");
  JRadioButton comma = new JRadioButton("comma");
  JRadioButton space = new JRadioButton("space");
  JRadioButton other = new JRadioButton("other:");

  JTextField delimiter = new JTextField("");

  ButtonGroup bp2 = new ButtonGroup();

  JButton DirButton = new JButton("...");
  JFileChooser jFileChooser1 = new JFileChooser(System.getProperty("user.dir"));

  public MakeGenomeStep0() {
    super("Import Genome Data",
          "Select a genome data file to make a genome map file.");

    JPanel top1 = new JPanel();
    JLabel pLabel = new JLabel("Path");
    pLabel.setPreferredSize(new Dimension(85, 20));
    pPath = new JTextField();
    DirButton.setPreferredSize(new Dimension(30, 20));
    DirButton.setMargin(new Insets(2, 2, 2, 2));
    DirButton.addActionListener(this);

    jFileChooser1.setCurrentDirectory(new File(System.getProperty("user.dir")));
    JLabel line = new JLabel();
    line.setBorder(BorderFactory.createEtchedBorder());
    line.setText("");
    line.setPreferredSize(new Dimension(358, 2));

    top1.setLayout(new BorderLayout(5, 10));
    top1.add(pLabel, BorderLayout.WEST);
    top1.add(pPath, BorderLayout.CENTER);
    top1.add(DirButton, BorderLayout.EAST);
    top1.add(line, BorderLayout.SOUTH);
    top1.setBorder(new EmptyBorder(5, 8, 8, 8));

    ButtonGroup check = new ButtonGroup();
    check.add(jCheck1);
    check.add(jCheck2);

    bp.add(jRadioButton1);
    bp.add(jRadioButton2);
    jRadioButton1.addActionListener(this);
    jRadioButton2.addActionListener(this);

    bp2.add(tab);
    tab.setSelected(true);
    bp2.add(comma);
    bp2.add(space);
    bp2.add(other);
    other.addActionListener(this);
    tab.addActionListener(this);
    comma.addActionListener(this);
    space.addActionListener(this);

    JPanel middle = new JPanel();
    middle.setLayout(new BorderLayout());

    jLabel1.setText("Select format of your file");
    jRadioButton1.setText("Text (character delimited)");
    jRadioButton1.setSelected(true);
    jRadioButton2.setText("Excel File");
    jRadioButton2.setSelected(false);
    jCheck1.setText("Yes");
    jCheck2.setText("No");
    jCheck1.setSelected(true);

    JPanel delimiters = new JPanel();
    delimiters.setBorder(new EmptyBorder(3, 20, 6, 10));
    delimiters.setPreferredSize(new Dimension(300, 20));
    delimiters.setLayout(new GridLayout(1, 6, 3, 0));
    delimiters.add(tab);
    delimiters.add(comma);
    delimiters.add(space);
    delimiters.add(other);
    delimiters.add(delimiter);

    delimiter.setEnabled(false);
    delimiter.setEditable(false);

    jRadioButton1.setPreferredSize(new Dimension(200, 25));
    jRadioButton2.setPreferredSize(new Dimension(200, 25));

    JPanel d2 = new JPanel();
    d2.setBorder(new EmptyBorder(0, 0, 0, 0));
    d2.setLayout(new BorderLayout());
    d2.add(jRadioButton1, BorderLayout.NORTH);
    d2.add(delimiters, BorderLayout.CENTER);
    d2.add(jRadioButton2, BorderLayout.SOUTH);

    JLabel label = new JLabel("Header");

    label.setPreferredSize(new Dimension(200, 25));

    JPanel inmiddle = new JPanel();
    inmiddle.setLayout(new BorderLayout());
    inmiddle.add(d2, BorderLayout.CENTER);
    inmiddle.add(label, BorderLayout.SOUTH);
    inmiddle.setBorder(new EmptyBorder(5, 20, 0, 1));

    JPanel headeroption = new JPanel();
    headeroption.setBorder(new EmptyBorder(0, 40, 20, 0));
    headeroption.setLayout(new BorderLayout());
    jCheck1.setPreferredSize(new Dimension(62, 20));
    headeroption.add(jCheck1, BorderLayout.WEST);
    headeroption.add(jCheck2, BorderLayout.CENTER);

    middle.setPreferredSize(new Dimension(360, 180));
    middle.setBorder(new EmptyBorder(5, 8, 8, 8));
    middle.add(jLabel1, BorderLayout.NORTH);
    middle.add(inmiddle, BorderLayout.CENTER);
    middle.add(headeroption, BorderLayout.SOUTH);

    jCheck1.addActionListener(this);
    jCheck2.addActionListener(this);

    JPanel bottom = new JPanel();
    bottom.setPreferredSize(new Dimension(360, 25));

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add("North", top1);
    panel.add("Center", middle);
    panel.add("South", bottom);

    JPanel center = new JPanel();
    center.setLayout(new UnitLayout());
    center.add(panel);
    add("Center", center);

    pPath.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent ev)
  {
    Object ob = ev.getSource();
    if(ob == jRadioButton1)//text
    {
        tab.setEnabled(true);
        comma.setEnabled(true);
        other.setEnabled(true);
        space.setEnabled(true);
    }
    if(ob == jRadioButton2)//excel
    {
      tab.setEnabled(false);
      comma.setEnabled(false);
      space.setEnabled(false);
      other.setEnabled(false);
    }
    if(ob == other)
    {
      delimiter.setEditable(true);
      delimiter.setEnabled(true);
    }
    if(ob == tab || ob == comma || ob==space)
    {
      delimiter.setEditable(false);
      delimiter.setEnabled(false);
    }
    if(ob == DirButton)
    {
      if (jFileChooser1.APPROVE_OPTION == jFileChooser1.showOpenDialog(this))
      {
        String filepath = jFileChooser1.getSelectedFile().getPath();
        pPath.setText(filepath);
        getDataModel().setValue("genomepath" , filepath);
      }
    }

    else if (ob == jCheck1) { //header
      getDataModel().setValue("HeaderExist", "header");
    }

    else if (ob == jCheck2) { //no header
      if (getDataModel().hasValue("HeaderExist"))
        getDataModel().removeValue("HeaderExist");
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == pPath.getDocument()) {
      getDataModel().setValue("genomepath" , pPath.getText());
      canMoveForward = true;
      if(pPath.getText().length()<=0)
      {
        getDataModel().removeValue("genomepath");
        canMoveForward = false;
      }
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

}
