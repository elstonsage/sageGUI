package sage;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.*;

public class MakeGenomeStep1 extends WizardPanel2 implements DocumentListener, ActionListener{
  JTextField pName2;

  JLabel jLabel1 = new JLabel();
  JRadioButton jRadioButton1 = new JRadioButton("The Haldane map function");
  JRadioButton jRadioButton2 = new JRadioButton("The Kosambi map function");

  public MakeGenomeStep1()
  {
       super("Set Your Genome Name", "Define your genome name and map function.");

       JPanel top2 = new JPanel();
       JLabel pLabel2 = new JLabel("Genome name");
       pLabel2.setPreferredSize(new Dimension(85,20));
       pName2 = new JTextField();
       JLabel line = new JLabel();
       line.setBorder(BorderFactory.createEtchedBorder());
       line.setText("");
       line.setPreferredSize(new Dimension(358,2));

       top2.setLayout(new BorderLayout(5,10));
       top2.add(pLabel2, BorderLayout.WEST);
       top2.add(pName2, BorderLayout.CENTER);
       top2.add(line, BorderLayout.SOUTH);
       top2.setBorder(new EmptyBorder(5,8,8,8));

       JPanel middle = new JPanel();
       middle.setLayout(new BorderLayout());

       jLabel1.setText("Select a map function");

       ButtonGroup bp = new ButtonGroup();
       jRadioButton1.setSelected(true);
       bp.add(jRadioButton1);
       bp.add(jRadioButton2);

       jRadioButton1.addActionListener(this);
       jRadioButton2.addActionListener(this);

       JPanel inmiddle = new JPanel();
       inmiddle.setLayout(new BorderLayout());
       inmiddle.add(jRadioButton1, BorderLayout.CENTER);
       inmiddle.add(jRadioButton2, BorderLayout.SOUTH);
       inmiddle.setBorder(new EmptyBorder(0,20,95,0));

       middle.setPreferredSize(new Dimension(360,180));
       middle.setBorder(new EmptyBorder(5,8,8,8));
       middle.add(jLabel1, BorderLayout.NORTH);
       middle.add(inmiddle, BorderLayout.CENTER);

       JPanel bottom = new JPanel();
       bottom.setPreferredSize(new Dimension(360, 25));

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());
       panel.add("North",top2);
       panel.add("Center",middle);
       panel.add("South", bottom);

       JPanel center = new JPanel();
       center.setLayout(new UnitLayout());
       center.add(panel);
       add("Center", center);

       pName2.getDocument().addDocumentListener(this);
  }

  public void actionPerformed(ActionEvent ev)
  {
    Object ob = ev.getSource();
    if(ob == jRadioButton1)
    {
      getDataModel().setValue("mapfunction" , "Haldane");
    }
    if(ob == jRadioButton2)
    {
      getDataModel().setValue("mapfunction" , "Kosambi");
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == pName2.getDocument()) {
      getDataModel().setValue("genomename" , pName2.getText());
      canMoveForward = true;
      if(pName2.getText().length()<=0)
      {
        getDataModel().removeValue("genomename");
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
