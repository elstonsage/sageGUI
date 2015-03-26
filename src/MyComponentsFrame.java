package sage;

import javax.swing.*;
import java.awt.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;

public class MyComponentsFrame extends JInternalFrame implements VetoableChangeListener{
  JLabel ParameterIcon = new JLabel();
  JLabel PedigreeIcon = new JLabel();
  JLabel LocusIcon = new JLabel();
  JLabel MarkerIcon = new JLabel();
  JLabel GenomeIcon = new JLabel();
  JLabel ASSOCIcon = new JLabel();
  JLabel AGEONIcon = new JLabel();
  JLabel FCORIcon = new JLabel();
  JLabel FREQ = new JLabel();
  JLabel GENIBDIcon = new JLabel();
  JLabel LODLINKIcon = new JLabel();
  JLabel LODPALIcon = new JLabel();
  JLabel MLODIcon = new JLabel();
  JLabel PEDINFOIcon = new JLabel();
  JLabel RELTESTIcon = new JLabel();
  JLabel SEGREGIcon = new JLabel();
  JLabel SIBPALIcon = new JLabel();
  JLabel TDTEXIcon = new JLabel();
  JLabel RELPALIcon = new JLabel();
  Frame1 frame;

  LabelTransferHandler labelTH = new LabelTransferHandler();
  MouseListener listener = new DragMouseAdapter();

  JPanel ComponentMainPanel = new JPanel();
  XYLayout xYLayout1 = new XYLayout();
  JLabel MARKERINFOIcon = new JLabel();
  JLabel NoteIcon = new JLabel();
  JLabel TypeIcon = new JLabel();
  JLabel DECIPHERIcon = new JLabel();
    JSeparator jSeparator2 = new JSeparator();
    JSeparator jSeparator1 = new JSeparator();
    public MyComponentsFrame(String title, Frame1 frame) {
    super(title,
          false, //resizable
          false, //closable
          false, //maximizable
          false);//iconifiable

    try {
      this.frame=frame;
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {

    this.setFrameIcon(null);
    setSize(new Dimension(105, 515));
    setPreferredSize(new Dimension(105, 515));
    ComponentMainPanel.setLayout(xYLayout1);

    PedigreeIcon.setPreferredSize(new Dimension(30, 30));
    PedigreeIcon.setToolTipText("Marker Locus File");
    PedigreeIcon.setHorizontalAlignment(SwingConstants.CENTER);
    PedigreeIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("MARKER.PNG")));
    PedigreeIcon.setText("");
    PedigreeIcon.setTransferHandler(labelTH);
    PedigreeIcon.addMouseListener(listener);

    MarkerIcon.setPreferredSize(new Dimension(30, 30));
    MarkerIcon.setToolTipText("Trait File");
    MarkerIcon.setHorizontalAlignment(SwingConstants.CENTER);
    MarkerIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("LOCUS.PNG")));
    MarkerIcon.setText("");
    MarkerIcon.setTransferHandler(labelTH);
    MarkerIcon.addMouseListener(listener);

    LocusIcon.setPreferredSize(new Dimension(30, 30));
    LocusIcon.setToolTipText("Genome File");
    LocusIcon.setHorizontalAlignment(SwingConstants.CENTER);
    LocusIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("GENOME.PNG")));
    LocusIcon.setText("");
    LocusIcon.setTransferHandler(labelTH);
    LocusIcon.addMouseListener(listener);

    GenomeIcon.setPreferredSize(new Dimension(30, 30));
    GenomeIcon.setToolTipText("IBD Sharing File");
    GenomeIcon.setHorizontalAlignment(SwingConstants.CENTER);
    GenomeIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("ibd.png")));
    GenomeIcon.setText("");
    GenomeIcon.setTransferHandler(labelTH);
    GenomeIcon.addMouseListener(listener);

    ParameterIcon.setPreferredSize(new Dimension(30, 30));
    ParameterIcon.setToolTipText("Pedigree File");
    ParameterIcon.setHorizontalAlignment(SwingConstants.CENTER);
    ParameterIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("PEDIGREE.PNG")));
    ParameterIcon.setText("");
    ParameterIcon.setTransferHandler(labelTH);
    ParameterIcon.addMouseListener(listener);

    TypeIcon.setPreferredSize(new Dimension(30, 30));
    TypeIcon.setToolTipText("Type File");
    TypeIcon.setHorizontalAlignment(SwingConstants.CENTER);
    TypeIcon.setHorizontalTextPosition(SwingConstants.TRAILING);
    TypeIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("type.png")));
    TypeIcon.setText("");
    TypeIcon.setTransferHandler(labelTH);
    TypeIcon.addMouseListener(listener);

    FCORIcon.setToolTipText("FCOR");
    FCORIcon.setHorizontalAlignment(SwingConstants.CENTER);
    FCORIcon.setPreferredSize(new Dimension(40, 35));
    FCORIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("fcor.png")));
    FCORIcon.setText("");
    FCORIcon.setTransferHandler(labelTH);
    FCORIcon.addMouseListener(listener);

    FREQ.setToolTipText("FREQ");
    FREQ.setHorizontalAlignment(SwingConstants.CENTER);
    FREQ.setPreferredSize(new Dimension(45, 40));
    FREQ.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("freq.png")));
    FREQ.setTransferHandler(labelTH);
    FREQ.addMouseListener(listener);

    GENIBDIcon.setToolTipText("GENIBD");
    GENIBDIcon.setHorizontalAlignment(SwingConstants.CENTER);
    GENIBDIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("genibd.png")));
    GENIBDIcon.setText("");
    GENIBDIcon.setTransferHandler(labelTH);
    GENIBDIcon.addMouseListener(listener);

    LODLINKIcon.setToolTipText("LODLINK");
    LODLINKIcon.setHorizontalAlignment(SwingConstants.CENTER);
    LODLINKIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("lodlink.png")));
    LODLINKIcon.setText("");
    LODLINKIcon.setTransferHandler(labelTH);
    LODLINKIcon.addMouseListener(listener);

    LODPALIcon.setToolTipText("LODPAL");
    LODPALIcon.setHorizontalAlignment(SwingConstants.CENTER);
    LODPALIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("lodpal.png")));
    LODPALIcon.setText("");
    LODPALIcon.setTransferHandler(labelTH);
    LODPALIcon.addMouseListener(listener);

    MLODIcon.setToolTipText("MLOD");
    MLODIcon.setHorizontalAlignment(SwingConstants.CENTER);
    MLODIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("mlod.png")));
    MLODIcon.setText("");
    MLODIcon.setTransferHandler(labelTH);
    MLODIcon.addMouseListener(listener);

    PEDINFOIcon.setToolTipText("PEDINFO");
    PEDINFOIcon.setHorizontalAlignment(SwingConstants.CENTER);
    PEDINFOIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("pedinfo.png")));
    PEDINFOIcon.setText("");
    PEDINFOIcon.setTransferHandler(labelTH);
    PEDINFOIcon.addMouseListener(listener);

    RELTESTIcon.setToolTipText("RELTEST");
    RELTESTIcon.setHorizontalAlignment(SwingConstants.CENTER);
    RELTESTIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("reltest.png")));
    RELTESTIcon.setText("");
    RELTESTIcon.setTransferHandler(labelTH);
    RELTESTIcon.addMouseListener(listener);

    SEGREGIcon.setToolTipText("SEGREG");
    SEGREGIcon.setHorizontalAlignment(SwingConstants.CENTER);
    SEGREGIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("segreg.png")));
    SEGREGIcon.setText("");
    SEGREGIcon.setTransferHandler(labelTH);
    SEGREGIcon.addMouseListener(listener);

    SIBPALIcon.setToolTipText("SIBPAL");
    SIBPALIcon.setHorizontalAlignment(SwingConstants.CENTER);
    SIBPALIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("sibpal.png")));
    SIBPALIcon.setText("");
    SIBPALIcon.setTransferHandler(labelTH);
    SIBPALIcon.addMouseListener(listener);

    TDTEXIcon.setToolTipText("TDTEX");
    TDTEXIcon.setHorizontalAlignment(SwingConstants.CENTER);
    TDTEXIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("tdtex.png")));
    TDTEXIcon.setText("");
    TDTEXIcon.setTransferHandler(labelTH);
    TDTEXIcon.addMouseListener(listener);

    ASSOCIcon.setToolTipText("ASSOC");
    ASSOCIcon.setHorizontalAlignment(SwingConstants.CENTER);
    ASSOCIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("assoc.png")));
    ASSOCIcon.setText("");
    ASSOCIcon.setTransferHandler(labelTH);
    ASSOCIcon.addMouseListener(listener);

    AGEONIcon.setToolTipText("AGEON");
    AGEONIcon.setHorizontalAlignment(SwingConstants.CENTER);
    AGEONIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("ageon.png")));
    AGEONIcon.setText("");
    AGEONIcon.setTransferHandler(labelTH);
    AGEONIcon.addMouseListener(listener);

    MARKERINFOIcon.setToolTipText("MARKERINFO");
    MARKERINFOIcon.setHorizontalAlignment(SwingConstants.CENTER);
    MARKERINFOIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("markerinfo.png")));
    MARKERINFOIcon.setText("");
    MARKERINFOIcon.setTransferHandler(labelTH);
    MARKERINFOIcon.addMouseListener(listener);

    NoteIcon.setFont(new java.awt.Font("Dialog", 0, 10));
    NoteIcon.setToolTipText("Note");
    NoteIcon.setHorizontalAlignment(SwingConstants.CENTER);
    NoteIcon.setHorizontalTextPosition(SwingConstants.CENTER);
    NoteIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource(
                "note.png")));
    NoteIcon.setIconTextGap(0);
    NoteIcon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    NoteIcon.setTransferHandler(labelTH);
    NoteIcon.addMouseListener(listener);

    DECIPHERIcon.setToolTipText("DECIPHER");
    DECIPHERIcon.setHorizontalAlignment(SwingConstants.CENTER);
    DECIPHERIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("decipher.png")));
    DECIPHERIcon.setText("");
    DECIPHERIcon.setTransferHandler(labelTH);
    DECIPHERIcon.addMouseListener(listener);

    RELPALIcon.setToolTipText("RELPAL");
    RELPALIcon.setHorizontalAlignment(SwingConstants.CENTER);
    RELPALIcon.setIcon(new ImageIcon(MyComponentsFrame.class.getResource("relpal.png")));
    RELPALIcon.setText("");
    RELPALIcon.setTransferHandler(labelTH);
    RELPALIcon.addMouseListener(listener);

    ComponentMainPanel.setBackground(Color.white);

        addVetoableChangeListener(this);
        this.getContentPane().add(ComponentMainPanel,  BorderLayout.CENTER);
        ComponentMainPanel.add(ParameterIcon, new XYConstraints(5, 3, 40, 35));
        ComponentMainPanel.add(PedigreeIcon, new XYConstraints(48, 3, 40, 35));
        ComponentMainPanel.add(MarkerIcon, new XYConstraints(5, 35, 40, 35));
        ComponentMainPanel.add(LocusIcon, new XYConstraints(48, 35, 40, 35));
        ComponentMainPanel.add(GenomeIcon, new XYConstraints(5, 67, 40, 35));
        ComponentMainPanel.add(TypeIcon, new XYConstraints(48, 67, 40, 35));
        ComponentMainPanel.add(AGEONIcon, new XYConstraints(48, 110, 40, 35));
        ComponentMainPanel.add(FCORIcon, new XYConstraints(48, 150, 40, 35));
        ComponentMainPanel.add(GENIBDIcon, new XYConstraints(48, 190, 40, 35));
        ComponentMainPanel.add(LODPALIcon, new XYConstraints(48, 230, 40, 35));
        ComponentMainPanel.add(MLODIcon, new XYConstraints(48, 270, 40, 35));
        ComponentMainPanel.add(RELTESTIcon, new XYConstraints(48, 310, 40, 35));
        ComponentMainPanel.add(SEGREGIcon, new XYConstraints(48, 350, 40, 35));
        ComponentMainPanel.add(TDTEXIcon, new XYConstraints(48, 390, 40, 35));
        ComponentMainPanel.add(jSeparator1, new XYConstraints(4, 430, 86, -1));
        ComponentMainPanel.add(jSeparator2, new XYConstraints(4, 106, 86, -1));
        ComponentMainPanel.add(ASSOCIcon, new XYConstraints(5, 110, 40, 35));
        ComponentMainPanel.add(DECIPHERIcon, new XYConstraints(5, 150, 40, 35));
        ComponentMainPanel.add(FREQ, new XYConstraints(5, 190, 40, 35));
        ComponentMainPanel.add(LODLINKIcon, new XYConstraints(5, 230, 40, 35));
        ComponentMainPanel.add(MARKERINFOIcon, new XYConstraints(5, 270, 40, 35));
        ComponentMainPanel.add(PEDINFOIcon, new XYConstraints(5, 310, 40, 35));
        ComponentMainPanel.add(RELPALIcon, new XYConstraints(5, 350, 40, 35));
        ComponentMainPanel.add(SIBPALIcon, new XYConstraints(5, 390, 40, 35));
        ComponentMainPanel.add(NoteIcon, new XYConstraints( -2, 430, 50, 40));
    }

  public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
    if (evt.getPropertyName().equals(IS_CLOSED_PROPERTY)) {
      frame.jCheckBoxMenuItem1.setSelected(false);
    }
  }


  private class DragMouseAdapter extends MouseAdapter{
    final Border selectedBorder = new EtchedBorder();
    final Border unselectedBorder = new EmptyBorder(selectedBorder.getBorderInsets(new JLabel()));

  public void mousePressed(MouseEvent e) {
    JComponent c = (JComponent) e.getSource();
    TransferHandler handler = c.getTransferHandler();
    handler.exportAsDrag(c, e, TransferHandler.COPY);
    c.setBorder(unselectedBorder);
  }

  public void mouseEntered(MouseEvent e) {
    JLabel c = (JLabel) e.getSource();
    c.setBorder(selectedBorder);
  }

  public void mouseExited(MouseEvent e) {
    JLabel c = (JLabel) e.getSource();
    c.setBorder(unselectedBorder);
  }
}

}
