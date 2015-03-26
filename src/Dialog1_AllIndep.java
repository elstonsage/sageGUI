package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class Dialog1_AllIndep extends JDialog implements ActionListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();

  TitledBorder titledBorder1;
  DataCollectionModel Datamodel;

  ButtonGroup bg = new ButtonGroup();
  JLabel jLabel3 = new JLabel();

    JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();

  FlowLayout flowLayout1 = new FlowLayout();

  int option=1;
    JLabel jLabel7 = new JLabel();
    BorderLayout borderLayout2 = new BorderLayout();
    JButton jButton2 = new JButton();
    JLabel jLabel4 = new JLabel();
    JLabel jLabel_PID = new JLabel();
    JLabel jLabel_PaID = new JLabel();
    JLabel jLabel_Sex = new JLabel();
    JLabel jLabel5 = new JLabel();
    JLabel jLabel6 = new JLabel();
    JLabel jLabel8 = new JLabel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextPane jTextPane1 = new JTextPane();

    public Dialog1_AllIndep(Frame frame, String title, boolean modal) {
      super(frame, title, modal);
      try {
          jbInit();
          pack();
      }
      catch(Exception ex) {
          ex.printStackTrace();
      }
  }

  public Dialog1_AllIndep() {
      this(null, "", false);
  }


  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");

    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(320, 320));
    this.setResizable(false);

      jTopPanel.setPreferredSize(new Dimension(320, 40));
      jTopPanel.setLayout(borderLayout2);
      jTopPanel.setBackground(Color.white);
      jCenterPanel.setLayout(xYLayout1);
      jCenterPanel.setPreferredSize(new Dimension(320,240));
      jCenterPanel.setBorder(lineborder);
      jBottomPanel.setBorder(lineborder);
      jBottomPanel.setPreferredSize(new Dimension(320, 40));
      jBottomPanel.setLayout(flowLayout1);

      jButton1.setText("OK");
      jButton1.addActionListener(this);
      jButton2.addActionListener(this);
      jButton1.setPreferredSize(new Dimension(60, 25));
        jLabel2.setText("Parent ID");
        jLabel7.setBorder(new EmptyBorder(0,15,0,0));
    jLabel7.setText("Combinations of pedigree field, parent field, sex field.");
        jButton2.setText("Cancel");
        jButton2.setPreferredSize(new Dimension(60, 25));
        jButton2.setMargin(new Insets(2, 2, 2, 2));
        jLabel4.setText("Sex Field");
        jLabel1.setText("Pedigree ID");
        jLabel_PID.setText("Absent");
        jLabel_PaID.setText("Absent");
        jLabel_Sex.setText("Absent");
        jLabel5.setText(":");
        jLabel6.setText(":");
        jLabel8.setText(":");
        jTextPane1.setEditable(false);
        jTextPane1.setText("");
        getContentPane().add(panel1);
      panel1.add(jTopPanel,  BorderLayout.NORTH);
        panel1.add(jCenterPanel,  BorderLayout.CENTER);
        panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1, null);
        jBottomPanel.add(jButton2);
        jTopPanel.add(jLabel7, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jTextPane1);
        jCenterPanel.add(jLabel5, new XYConstraints(120, 15, 10, 20));
        jCenterPanel.add(jLabel_PID, new XYConstraints(150, 15, 150, 20));
        jCenterPanel.add(jLabel6, new XYConstraints(120, 45, 10, 20));
        jCenterPanel.add(jLabel_PaID, new XYConstraints(150, 45, 150, 20));
        jCenterPanel.add(jLabel8, new XYConstraints(120, 75, 10, 20));
        jCenterPanel.add(jLabel_Sex, new XYConstraints(150, 75, 150, 20));
        jCenterPanel.add(jScrollPane1, new XYConstraints(20, 115, 280, 100));
        jCenterPanel.add(jLabel4, new XYConstraints(40, 75, 65, 20));
        jCenterPanel.add(jLabel2, new XYConstraints(40, 45, 65, 20));
        jCenterPanel.add(jLabel1, new XYConstraints(40, 15, 65, 20));
    }

  void set_dataModel(DataCollectionModel input)
  {
    this.Datamodel = input;
  }

  public void actionPerformed(ActionEvent e) {
      Object button = e.getSource();
      if (button == jButton1) { //OK
          option = 1;
      }
      if (button == jButton2) { //Cancel
          option = 2;
      }
      dispose();
  }
}
