package sage;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MakeGenomeStep4 extends WizardPanel2 implements DocumentListener{

  JTextField FileName;
  JLabel jLabel1 = new JLabel();

  public MakeGenomeStep4()
  {
       super("Set Your Filename", "Name your genome description.");

       JPanel top = new JPanel();
       JLabel pLabel2 = new JLabel("File name");
       pLabel2.setPreferredSize(new Dimension(85,20));
       FileName = new JTextField("genome");

       top.setLayout(new BorderLayout(5,10));
       top.add(pLabel2, BorderLayout.WEST);
       top.add(FileName, BorderLayout.CENTER);
       top.setBorder(new EmptyBorder(5,5,8,5));

       JPanel middle = new JPanel();
       middle.setPreferredSize(new Dimension(360,220));

       JPanel panel = new JPanel();
       panel.setLayout(new BorderLayout());
       panel.add("North",top);
       panel.add("Center",middle);

       JPanel center = new JPanel();
       center.setLayout(new UnitLayout());
       center.add(panel);
       add("Center", center);

       canMoveForward = true;
       FileName.getDocument().addDocumentListener(this);
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == FileName.getDocument()) {
      getDataModel().setValue("filename" , FileName.getText());
      if(FileName.getText().length()<=0)
      {
        getDataModel().removeValue("filename");
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
