package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

public class ShowRunInfoAll extends JDialog implements ActionListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jBottomPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  EdgeBorder lineborder = new EdgeBorder();
  XYLayout xYLayout1 = new XYLayout();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JPanel jLabelbottom = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jResultPanel = new JPanel();
  GridLayout gridLayout1 = new GridLayout();
  JLabel jLabel1 = new JLabel();
  JPanel jTopPanel = new JPanel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  XYLayout xYLayout2 = new XYLayout();
  BorderLayout borderLayout3 = new BorderLayout();
  JLabel jLabel5 = new JLabel();

  IconNode jobnode;
  MyInternalFrame inframe;

  public ShowRunInfoAll(Frame frame, MyInternalFrame inframe, String title, boolean modal) {
    super(frame, title, modal);
    this.inframe = inframe;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public ShowRunInfoAll() {
    this(null, null,"", false);
  }
  private void jbInit() throws Exception {
    this.setSize(new Dimension(400, 350));
    panel1.setLayout(borderLayout1);
    jTopPanel.setBackground(Color.white);
    jTopPanel.setPreferredSize(new Dimension(60, 50));
    jBottomPanel.setPreferredSize(new Dimension(45, 40));
    jBottomPanel.setLayout(xYLayout1);
    jCenterPanel.setBorder(lineborder);
    jBottomPanel.setBorder(lineborder);
    jCenterPanel.setPreferredSize(new Dimension(400,250));
    jCenterPanel.setLayout(borderLayout2);
    jResultPanel.setPreferredSize(new Dimension(350,210));
    jLabelbottom.setPreferredSize(new Dimension(350,40));
    jLabelbottom.setLayout(borderLayout3);
    jLabel1.setPreferredSize(new Dimension(20,200));

    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.setText("OK");
    jButton1.addActionListener(this);

    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(this);

    gridLayout1.setRows(9);
    jResultPanel.setLayout(gridLayout1);
    jResultPanel.setBorder(new EmptyBorder(10,0,0,0));

    jTopPanel.setLayout(xYLayout2);
    jLabel3.setFont(new java.awt.Font("Dialog", 1, 12));
    jLabel3.setToolTipText("");
    jLabel3.setText("Your project");
    jLabel4.setText("Select analysis you want to run.");
    jLabel5.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel5.setToolTipText("");
    jLabel5.setText("     Do you want to run all selected analyses?");
    getContentPane().add(panel1);
    panel1.add(jBottomPanel,  BorderLayout.SOUTH);
    jBottomPanel.add(jButton1,     new XYConstraints(235, 7, 65, 25));
    jBottomPanel.add(jButton2,    new XYConstraints(310, 7, 65, 25));
    panel1.add(jCenterPanel, BorderLayout.CENTER);
    jCenterPanel.add(jLabelbottom, BorderLayout.SOUTH);
    jLabelbottom.add(jLabel5,  BorderLayout.CENTER);
    jCenterPanel.add(jResultPanel, BorderLayout.CENTER);
    jCenterPanel.add(jLabel1,  BorderLayout.WEST);
    panel1.add(jTopPanel, BorderLayout.NORTH);
    jTopPanel.add(jLabel4,      new XYConstraints(20, 20, 334, 29));
    jTopPanel.add(jLabel3,     new XYConstraints(15, 5, 377, 20));
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if(ob == jButton1)//OK
      jButton1_actionPerformed(e);
    if(ob == jButton2)//Cancel
      jButton2_actionPerformed(e);
 }

  void jButton1_actionPerformed(ActionEvent e) {
    final Component joblist[] = jResultPanel.getComponents();

    Thread runner = new Thread() {
      public void run() {
        for(int i=0;i<joblist.length;i++)
        {
          if(joblist[i] instanceof MyCheckBox)
          {
            MyCheckBox tempcheck = (MyCheckBox)joblist[i];

            if(tempcheck.isSelected())
            {
              String atype = tempcheck.tempnode.analysis_type;
              IconNode treenode = tempcheck.temp;
              NodeInfo currentnode = tempcheck.tempnode;

              if(atype.compareTo("FREQ")==0)
              {
                inframe.RunFREQ(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("GENIBD")==0)
              {
                inframe.RunGENIBD(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("LODPAL")==0)
              {
                inframe.RunLODPAL(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("PEDINFO")==0)
              {
                inframe.RunPEDINFO(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("MARKERINFO")==0)
              {
                inframe.RunMARKERINFO(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("ASSOC")==0)
              {
                inframe.RunASSOC(treenode, currentnode,"Run");
              }
              else if (atype.compareTo("AGEON") == 0)
              {
                inframe.RunAGEON(treenode, currentnode, "Run");
              }
              else if(atype.compareTo("RELTEST")==0)
              {
                inframe.RunRELTEST(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("FCOR")==0)
              {
                inframe.RunFCOR(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("LODLINK")==0)
              {
                inframe.RunLODLINK(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("TDTEX")==0)
              {
                inframe.RunTDTEX(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("MLOD")==0)
              {
                inframe.RunMLOD(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("SIBPAL")==0)
              {
                inframe.RunSIBPAL(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("SEGREG")==0)
              {
                inframe.RunSEGREG(treenode, currentnode,"Run");
              }
              else if(atype.compareTo("DECIPHER")==0)
              {
                inframe.RunDECIPHER(treenode, currentnode,"Run");
              }
            }
          }
        }
      }

    };
    runner.start();
    dispose();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    dispose();
  }


}
