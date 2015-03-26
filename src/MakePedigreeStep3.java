package sage;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.*;

public class MakePedigreeStep3 extends WizardPanel implements DocumentListener{
  JTextField pName;
  JPanel multi_mode = new JPanel();
  JPanel middle = new JPanel();

  public MakePedigreeStep3()
  {
       super("Set your filename", "Name your data file.");

       JPanel top = new JPanel();
       JLabel pLabel = new JLabel("Name");
       pName = new JTextField("pedigree.dat");
       pName.setPreferredSize(new Dimension(180,20));
       JLabel line = new JLabel();
       line.setBorder(BorderFactory.createEtchedBorder());
       line.setText("");
       line.setPreferredSize(new Dimension(358,2));

       top.setLayout(new BorderLayout(5,10));
       top.add(pLabel, BorderLayout.WEST);
       top.add(pName, BorderLayout.CENTER);
       top.add(line, BorderLayout.SOUTH);
       top.setBorder(new EmptyBorder(5,8,8,8));

       JPanel middle = new JPanel();
       middle.setPreferredSize(new Dimension(360,160));

       JPanel bottom = new JPanel();
       bottom.setPreferredSize(new Dimension(360, 45));

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());
       panel.add("North",top);
       panel.add("Center",middle);
       panel.add("South", bottom);

       JPanel center = new JPanel();
       center.setLayout(new UnitLayout());
       center.add(panel);
       add("Center", center);

       canMoveForward = true;
       pName.getDocument().addDocumentListener(this);
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == pName.getDocument()) {
      getDataModel().setValue("data.name0" , pName.getText());
    }
    if (getDataModel().hasValue("data.name0")) {
      canMoveForward = true;
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

}
