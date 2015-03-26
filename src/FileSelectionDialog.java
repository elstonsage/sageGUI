package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class FileSelectionDialog extends JDialog implements ActionListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  JTextArea jTextArea1 = new JTextArea();
  JPanel jPanel3 = new JPanel();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  XYLayout xYLayout1 = new XYLayout();
  boolean ok_clicked;

  public FileSelectionDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public FileSelectionDialog() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    this.setSize(new Dimension(350, 250));
    this.setTitle("File Selection Dialog");
    jPanel1.setBorder(new EmptyBorder(10,10,10,10));
    jPanel1.setPreferredSize(new Dimension(350, 140));
    jPanel2.setPreferredSize(new Dimension(350, 45));
    jPanel2.setLayout(borderLayout3);
    jPanel2.setBorder(new EmptyBorder(10,10,10,10));
    jPanel3.setPreferredSize(new Dimension(350, 45));
    jPanel3.setLayout(xYLayout1);
    jPanel3.setBorder(new EmptyBorder(10,10,10,10));

    panel1.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    jTextArea1.setBackground(new Color(236, 233, 216));
    jTextArea1.setFont(new java.awt.Font("Dialog", 0, 11));
    jTextArea1.setEditable(false);
    jTextArea1.setText("Select a file ...");
    jButton1.setText("OK");
    jButton1.addActionListener(this);
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(this);
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.CENTER);
    panel1.add(jPanel2, BorderLayout.NORTH);
    jPanel2.add(jTextArea1, BorderLayout.CENTER);
    panel1.add(jPanel3,  BorderLayout.SOUTH);
    jPanel3.add(jButton1,  new XYConstraints(177, 3, 67, -1));
    jPanel3.add(jButton2,  new XYConstraints(253, 3, -1, -1));
  }

  public void actionPerformed(ActionEvent e)
 {
   Object source = e.getSource();
   if(source == jButton1)
   {
     ok_clicked=true;
     dispose();
   }
   else if(source == jButton2)
   {
     ok_clicked=false;
     dispose();
   }
 }
}
