package sage;

import java.awt.*;
import com.borland.jbcl.layout.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.*;
//import javax.help.CSH;
/**
 * <p>Title: NEW GUI for SAGE</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author suna
 * @version 1.0
 */

public class SIBPAL2
    extends SageAnalysisPanel
    implements DocumentListener, ActionListener, ItemListener {
  XYLayout xYLayout1 = new XYLayout();

  DataCollectionModel Datamodel;
  TitledBorder titledBorder1;

  SIBPAL1 sibpal1;
  NodeInfo sibpalnode;

  JLabel jLabel3 = new JLabel();

  ButtonGroup bp = new ButtonGroup();
    JPanel jPanel2 = new JPanel();
  XYLayout xYLayout3 = new XYLayout();
  JRadioButton jRadioButton3 = new JRadioButton();
  JRadioButton jRadioButton4 = new JRadioButton();

  public SIBPAL2(SIBPAL1 input) {
    this.Datamodel = input.Datamodel;
    sibpal1 = input;
    sibpalnode = (NodeInfo) input.analysis_node.getUserObject();

    try {
      jbInit();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    this.setLayout(xYLayout1);

    xYLayout1.setWidth(500);
    xYLayout1.setHeight(560);

    jRadioButton3.setFocusPainted(false);
    jRadioButton4.setFocusPainted(false);



    if (sibpal1.errorF_node != null) {
      jRunButton.setIcon(error_image);
    }
    else {
     jRunButton.setIcon(next_image);
    }

    //jLabel3.setToolTipText("<html>When true, trait single regression analyses will produce a " +
    //"compact <br>summary table of results. When false more verbose output " +
    //"is produced.");
    jLabel3.setText("Analysis");
        jPanel2.setBorder(titledBorder1);
    jPanel2.setLayout(xYLayout3);
    jRadioButton3.setText("Mean Tests");
    jRadioButton3.setToolTipText(
            "Specifies calculating and conducting tests on various mean IBD sharing values.");
    jRadioButton3.addMouseListener(new SIBPAL2_jRadioButton3_mouseAdapter(this));
    jRadioButton4.setText("Trait Regression");
    jRadioButton4.setToolTipText(
            "Specifies a Haseman and Elston type regression analysis.");

    jRadioButton4.addMouseListener(new SIBPAL2_jRadioButton4_mouseAdapter(this));
    this.add(jRunButton, new XYConstraints(420, 520, 60, 25));
    this.add(jLabel3, new XYConstraints(20, 20, 88, 20));
        jPanel2.add(jRadioButton3,  new XYConstraints(2, 2, 200, 20));
    jPanel2.add(jRadioButton4,   new XYConstraints(2, 32, 200, 20));

    jRunButton.addActionListener(this);
        jRadioButton3.addItemListener(this);
    jRadioButton4.addItemListener(this); //bp2.add(jRadioButton3);
    //bp2.add(jRadioButton4);

    //Datamodel.setValue("summary_default", "true");
        this.add(jPanel2,     new XYConstraints(155, 20, 320, 68));
        //Datamodel.setValue("trait_regression_default", "single");
  }

  public void jRunButton_actionPerformed(ActionEvent e) {

    MyInternalFrame mf = (MyInternalFrame) Frame1.mainFrame1.activeinframe;
    IconNode treenode = (IconNode) sibpal1.analysis_node;
    NodeInfo currentnode = (NodeInfo) treenode.getUserObject();
    mf.RunSIBPAL(treenode, currentnode, e.getActionCommand());
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();
        if (ob == jRadioButton3) {
      if(jRadioButton3.isSelected())
      {
        Datamodel.setValue("mean_tests", "use");
        if(Frame1.mainFrame1.activeinframe.jTabbedPane1.getTabCount()>2)
          Frame1.mainFrame1.activeinframe.jTabbedPane1.setSelectedIndex(2);
      }
      else
        Datamodel.removeValue("mean_tests");
    }
    if (ob == jRadioButton4) {
      if(jRadioButton4.isSelected())
      {
        Datamodel.setValue("trait_regression", "use");
        if(Frame1.mainFrame1.activeinframe.jTabbedPane1.getTabCount()>3)
            Frame1.mainFrame1.activeinframe.jTabbedPane1.setSelectedIndex(3);
      }
      else
        Datamodel.removeValue("trait_regression");
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();

    if (ob == jRunButton)
      jRunButton_actionPerformed(e);

  }

/*  void jRadioButton1_mouseClicked(MouseEvent e) {
// Frame1.viewer.setCurrentID("SUMMARY_DEFAULT_10");
    Frame1.mainFrame1.pdfframe.setTextonPage("true", false, 234);
  }

  void jRadioButton2_mouseClicked(MouseEvent e) {
// Frame1.viewer.setCurrentID("SUMMARY_DEFAULT_10");
    Frame1.mainFrame1.pdfframe.setTextonPage("false", false, 234);
  }
*/
  void jRadioButton3_mouseClicked(MouseEvent e) {
// Frame1.viewer.setCurrentID("MEAN_TEST_10");
    Frame1.mainFrame1.pdfframe.setTextonPage("mean_test", false, 250);
  }

  void jRadioButton4_mouseClicked(MouseEvent e) {
// Frame1.viewer.setCurrentID("TRAIT_REGRESSION_DEFAULT_10");
    Frame1.mainFrame1.pdfframe.setTextonPage("trait_regression", false, 250);
  }

}


class SIBPAL2_jRadioButton3_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL2 adaptee;

  SIBPAL2_jRadioButton3_mouseAdapter(SIBPAL2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton3_mouseClicked(e);
  }
}

class SIBPAL2_jRadioButton4_mouseAdapter extends java.awt.event.MouseAdapter {
  SIBPAL2 adaptee;

  SIBPAL2_jRadioButton4_mouseAdapter(SIBPAL2 adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jRadioButton4_mouseClicked(e);
  }
}
