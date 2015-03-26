package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class ShowRunInfo extends JDialog implements ActionListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jBottomPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel CenterInnerPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();

  boolean Run=false;
  JPanel jTopPanel = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JLabel analysisLabel = new JLabel();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout4 = new BorderLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea anaDefTextArea = new JTextArea();
  JLabel parLabel = new JLabel();
  JLabel jParaLabel = new JLabel();
  JLabel pedLabel = new JLabel();
  JLabel jPediLabel = new JLabel();
  JLabel mlocusLabel = new JLabel();
  JLabel jLocusLabel = new JLabel();
  JLabel genomeLabel = new JLabel();
  JLabel jGenomeLabel = new JLabel();
  JLabel typeLabel = new JLabel();
  JLabel jTypeLabel = new JLabel();
  JLabel testLabel = new JLabel();
  JLabel jLastLabel = new JLabel();

  ImageIcon check;
    JPanel jPanel3 = new JPanel();
    BorderLayout borderLayout5 = new BorderLayout();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel3 = new JLabel();
    private javax.swing.JSeparator jSeparator1;

  public ShowRunInfo(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public ShowRunInfo() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
      check = new ImageIcon(ShowRunInfo.class.getResource("check.png"));
      jSeparator1 = new javax.swing.JSeparator();

    this.setSize(new Dimension(400, 515));
    panel1.setLayout(borderLayout1);
    jTopPanel.setPreferredSize(new Dimension(400, 40));
    jTopPanel.setBackground(Color.white);
    jTopPanel.setBorder(new EmptyBorder(0,15,0,8));
    jBottomPanel.setPreferredSize(new Dimension(400, 40));
    jBottomPanel.setLayout(xYLayout1);
    CenterInnerPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jCenterPanel.setPreferredSize(new Dimension(400,430));
    jCenterPanel.setLayout(borderLayout3);
    jPanel1.setPreferredSize(new Dimension(400,100));

    jPanel2.setPreferredSize(new Dimension(400,190));
    jPanel2.setLayout(borderLayout4);

    CenterInnerPanel.setLayout(new BorderLayout());
    jCenterPanel.setBorder(new EmptyBorder(5,10,5,10));

    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("OK");
    jButton1.addActionListener(this);

    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(this);

    jTopPanel.setLayout(borderLayout2);
    analysisLabel.setFont(new java.awt.Font("Dialog", 1, 11));
    analysisLabel.setHorizontalAlignment(SwingConstants.LEFT);
    analysisLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    analysisLabel.setText("Analysis");
        borderLayout4.setVgap(5);
    parLabel.setIcon(check);
    parLabel.setText("Parameter file");
    jParaLabel.setFont(new java.awt.Font("Monospaced", 0, 11));
    pedLabel.setIcon(check);
    pedLabel.setText("Data file");
    jPediLabel.setFont(new java.awt.Font("Monospaced", 0, 11));
    jPediLabel.setText("");
    mlocusLabel.setIcon(check);
    mlocusLabel.setText("Marker locus file");
    jLocusLabel.setFont(new java.awt.Font("Monospaced", 0, 11));
    jLocusLabel.setText("");
    genomeLabel.setIcon(check);
    genomeLabel.setText("Genome description file");
    jGenomeLabel.setFont(new java.awt.Font("Monospaced", 0, 11));
    jGenomeLabel.setText("");
    anaDefTextArea.setFont(new java.awt.Font("Monospaced", 0, 11));
    typeLabel.setIcon(check);
    typeLabel.setText("Type file");
    jTypeLabel.setFont(new java.awt.Font("Monospaced", 0, 11));
    jTypeLabel.setText("");
    testLabel.setIcon(check);
    testLabel.setText("Test file");
        jPanel3.setLayout(borderLayout5);
        jLabel1.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
        jLabel1.setMaximumSize(new Dimension(291, 20));
        jLabel1.setMinimumSize(new Dimension(291, 20));
        jLabel1.setPreferredSize(new Dimension(291, 20));
        jLabel1.setText(
                "Analysis definition for internal S.A.G.E. parameter file");
        jLabel3.setFont(new java.awt.Font("Dialog", Font.BOLD, 11));
        jLabel3.setForeground(Color.blue);
        jLabel3.setMinimumSize(new Dimension(177, 20));
        jLabel3.setPreferredSize(new Dimension(177, 20));
        jLabel3.setToolTipText("");
        jLabel3.setText("Please review and edit if necessary.");
        jPanel3.setMinimumSize(new Dimension(291, 42));
        jPanel3.setPreferredSize(new Dimension(291, 42));
        borderLayout3.setVgap(10);
        getContentPane().add(panel1);
    panel1.add(jBottomPanel,  BorderLayout.SOUTH);
    panel1.add(CenterInnerPanel, BorderLayout.CENTER);
    panel1.add(jTopPanel, BorderLayout.NORTH);

    jBottomPanel.add(jButton1,      new XYConstraints(235, 7, 65, 25));
    jBottomPanel.add(jButton2,    new XYConstraints(310, 7, 65, 25));
    jCenterPanel.add(jPanel1,  BorderLayout.NORTH);
    CenterInnerPanel.add(jCenterPanel, BorderLayout.CENTER);

    jCenterPanel.add(jPanel2,  BorderLayout.CENTER);
        jPanel2.add(jScrollPane1, BorderLayout.CENTER);
        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);
        jScrollPane1.getViewport().add(anaDefTextArea, null);
    jTopPanel.add(analysisLabel,  BorderLayout.CENTER);
    jPanel3.add(jSeparator1, java.awt.BorderLayout.CENTER);
        jPanel3.add(jLabel1, java.awt.BorderLayout.NORTH);
        jPanel3.add(jLabel3, java.awt.BorderLayout.SOUTH);

    }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if(ob == jButton1)
      jButton1_actionPerformed(e);
    if(ob == jButton2)
      jButton2_actionPerformed(e);
 }

  void jButton1_actionPerformed(ActionEvent e) {
    Run = true;
    dispose();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    dispose();
  }

  void setFileList(int fileNum)
  {
      int height = fileNum*2*20+25;
      int numGrid = fileNum*2;

      jPanel1.setPreferredSize(new Dimension(400,height));
      GridLayout gridLayout1 = new GridLayout(numGrid,1);
      jPanel1.setLayout(gridLayout1);

      switch(fileNum)
      {
      case 2:
          jPanel1.add(parLabel, null);
          jPanel1.add(jParaLabel, null);
          jPanel1.add(pedLabel, null);
          jPanel1.add(jPediLabel, null);
          break;

      case 3:
          jPanel1.add(parLabel, null);
          jPanel1.add(jParaLabel, null);
          jPanel1.add(pedLabel, null);
          jPanel1.add(jPediLabel, null);
          jPanel1.add(mlocusLabel, null);
          jPanel1.add(jLocusLabel, null);
          break;

      case 4:
          jPanel1.add(parLabel, null);
          jPanel1.add(jParaLabel, null);
          jPanel1.add(pedLabel, null);
          jPanel1.add(jPediLabel, null);
          jPanel1.add(mlocusLabel, null);
          jPanel1.add(jLocusLabel, null);
          jPanel1.add(genomeLabel, null);
          jPanel1.add(jGenomeLabel, null);
          break;

      case 5:
          jPanel1.add(parLabel, null);
          jPanel1.add(jParaLabel, null);
          jPanel1.add(pedLabel, null);
          jPanel1.add(jPediLabel, null);
          jPanel1.add(mlocusLabel, null);
          jPanel1.add(jLocusLabel, null);
          jPanel1.add(genomeLabel, null);
          jPanel1.add(jGenomeLabel, null);
          jPanel1.add(typeLabel, null);
          jPanel1.add(jTypeLabel, null);
          break;

      case 6:
          jPanel1.add(parLabel, null);
          jPanel1.add(jParaLabel, null);
          jPanel1.add(pedLabel, null);
          jPanel1.add(jPediLabel, null);
          jPanel1.add(mlocusLabel, null);
          jPanel1.add(jLocusLabel, null);
          jPanel1.add(genomeLabel, null);
          jPanel1.add(jGenomeLabel, null);
          jPanel1.add(typeLabel, null);
          jPanel1.add(jTypeLabel, null);
          jPanel1.add(testLabel, null);
          jPanel1.add(jLastLabel, null);
          break;

      }

  }

}
