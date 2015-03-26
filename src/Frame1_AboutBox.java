package sage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;
import java.util.GregorianCalendar;
import com.borland.jbcl.layout.*;

public class Frame1_AboutBox extends JDialog implements ActionListener {

  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JPanel insetsPanel1 = new JPanel();
  JPanel insetsPanel2 = new JPanel();
  JPanel insetsPanel3 = new JPanel();
  JButton button1 = new JButton();
  JLabel imageLabel = new JLabel();
  JLabel label2 = new JLabel();
  JLabel label3 = new JLabel();
  JLabel label4 = new JLabel();
  ImageIcon image1;
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  String product = "S.A.G.E.";
  String version = "Version : S.A.G.E. 6.3";
  String copyright = "Copyright \u00A9 CWRU";

  Date today;
  String dateOut;
  DateFormat dateFormatter;
  JLabel jLabel1 = new JLabel();
  BorderLayout borderLayout3 = new BorderLayout();
  XYLayout xYLayout1 = new XYLayout();

  public Frame1_AboutBox(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception  {
    image1 = new ImageIcon(Frame1_AboutBox.class.getResource("about.png"));
    imageLabel.setFont(new java.awt.Font("Dialog", 1, 11));
    imageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    imageLabel.setIcon(new ImageIcon(Frame1_AboutBox.class.getResource("sage_about.png")));
    imageLabel.setText(product);
    imageLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    this.setSize(new Dimension(280,180));
    this.setResizable(false);
    this.setTitle("About");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    insetsPanel2.setLayout(borderLayout3);
    insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    label2.setText(version);
    label3.setText(copyright);

    dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
    today = new Date();
    dateOut = dateFormatter.format(today);
    label4.setText("Current date : "+dateOut);

    GregorianCalendar gc = new GregorianCalendar(2012 ,6 , 31);
    String buildout = dateFormatter.format(gc.getTime());
    jLabel1.setText("Date of build : "+ buildout);

    insetsPanel3.setLayout(xYLayout1);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    button1.setText("OK");
    button1.addActionListener(this);
    insetsPanel2.add(imageLabel, BorderLayout.CENTER);
    panel2.add(insetsPanel2, BorderLayout.WEST);
    this.getContentPane().add(panel1, null);
    insetsPanel3.add(label2,    new XYConstraints(0, 0, 180, 20));
    insetsPanel3.add(label3,     new XYConstraints(0, 20, 180, 20));
    insetsPanel3.add(jLabel1,     new XYConstraints(0, 40, 180, 20));
    insetsPanel3.add(label4,    new XYConstraints(0, 60, 180, 20));

    panel2.add(insetsPanel3, BorderLayout.CENTER);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2, BorderLayout.NORTH);
    setResizable(true);
  }

  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }

  void cancel() {
    dispose();
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button1) {
      cancel();
    }
  }
}
