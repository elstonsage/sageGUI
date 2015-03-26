package sage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.text.Document;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DECIPHER_Dialog3_Pools
    extends SageDialog implements DocumentListener, ItemListener, ActionListener {
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JComboBox jComboBox1 = new JComboBox();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;

  JButton jAddButton = new JButton();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  XYLayout xYLayout1 = new XYLayout();
  JLabel jLabel3 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JButton jButton3 = new JButton();
  JButton jButton4 = new JButton();
  JTextField jTextField2 = new JTextField();
  DECIPHER_Dialog3_Pools_sub dialog = new DECIPHER_Dialog3_Pools_sub(this, "Specification");
  DataCollectionModel Datamodel;
  DECIPHER2 decipher;

  JLabel jLabeltitle = new JLabel();

  public DECIPHER_Dialog3_Pools(DECIPHER2 parent, String title) {
    super(title);
    decipher = parent;
    try {
      jbInit();
      pack();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() {
    titledBorder1 = new TitledBorder("");

    jAddButton.addActionListener(this);
    jAddButton.setMargin(new Insets(2, 2, 2, 2));
    jAddButton.setText("Add locus");
    okButton.setText("OK");
    okButton.setEnabled(false);
    okButton.addActionListener(this);
    cancelButton.addActionListener(this);

    jMenuItemDelete.addActionListener(this);
    jLabeltitle.setText("Specifies the pools.");
    jButton3.addActionListener(this);
    jButton4.addActionListener(this);
    jLabel4.setText("Allele");
    jLabel4.setToolTipText("Name of one of the multiple allele of the locus.");

    jLabel5.setText("Last allele");
    jLabel5.setToolTipText("Name of the last allele at the locus.");

    jLabel1.setText("Fixed pool size");
    jLabel1.setToolTipText("<html>Specifies the number of individuals x ploidy in the pool,<br>the same for each pool.");
    jLabel2.setText("Variable pool size");
    jLabel2.setToolTipText("<html>Name of a trait that gives the size (individuals x ploidy)<br>for each pool.");

    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout1);
    jLabel3.setText("Locus");
    jLabel3.setToolTipText("Name of one of the multiple loci to be used for haplotype determination.");
    jTextField3.setEnabled(false);
    jTextField3.setEditable(false);
    jTextField3.setText("");
    jTextField4.setText("");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.setText("Define");
    jButton4.setMargin(new Insets(2, 2, 2, 2));
    jButton4.setText("Reset");
    jTextField2.setText("");

    jComboBox1.addMouseListener(new DECIPHER_Dialog3_Pools_jComboBox1_mouseAdapter(this));
    jTextField1.addMouseListener(new DECIPHER_Dialog3_Pools_jTextField1_mouseAdapter(this));
    jTextField2.addMouseListener(new DECIPHER_Dialog3_Pools_jTextField2_mouseAdapter(this));
    jTextField3.addMouseListener(new DECIPHER_Dialog3_Pools_jTextField3_mouseAdapter(this));
    jTextField4.addMouseListener(new DECIPHER_Dialog3_Pools_jTextField4_mouseAdapter(this));

    jTopPanel.add(jLabeltitle,  new XYConstraints(15, 3, 292, 30));
    jCenterPanel.add(jLabel1, new XYConstraints(15, 15, 90, 20));
    jCenterPanel.add(jTextField1,  new XYConstraints(120, 15, 195, 20));
    jCenterPanel.add(jLabel2,  new XYConstraints(15, 45, 90, 20));
    jCenterPanel.add(jComboBox1,   new XYConstraints(120, 45, 195, 20));

    jCenterPanel.add(jPanel1, new XYConstraints(15, 75, 300, 190));
    jPanel1.add(jLabel3, new XYConstraints(10, 5, 75, 20));
    jPanel1.add(jTextField2,   new XYConstraints(90, 5, 190, 20));

    jPanel1.add(jTextField3,    new XYConstraints(90, 35, 90, 20));
    jPanel1.add(jLabel4, new XYConstraints(30, 35, 50, 20));
    jPanel1.add(jButton3,         new XYConstraints(186, 35, 45, 20));
    jPanel1.add(jButton4,         new XYConstraints(235, 35, 45, 20));

    jPanel1.add(jTextField4,    new XYConstraints(90, 65, 90, 20));
    jPanel1.add(jAddButton,              new XYConstraints(185, 65, 95, 25));
    jPanel1.add(jLabel5,  new XYConstraints(30, 65, 60, 20));
    jPanel1.add(jScrollPane1, new XYConstraints(8, 101, 272, 71));

    jTextField1.getDocument().addDocumentListener(this);
    jComboBox1.addItemListener(this);
  }

  void set_dataModel(DataCollectionModel input) {
    this.Datamodel = input;
    dialog.set_dataModel(Datamodel);
  }

   public void save_init_state(){
     String resultinfo=new String();
     for(int i=0;i<listModel.size();i++)
     {
       resultinfo = resultinfo + listModel.get(i).toString()+"\n";
     }
     if(resultinfo.length()>0)
       Datamodel.setValue("pools_locus",resultinfo.trim());

     if (Datamodel.hasValue("pools_locus")) {
       decipher.jTextField3.setText("Specified");
       decipher.jLabel15.setFont(new java.awt.Font("Dialog", 1, 11));
     }
   }

  public void actionPerformed(ActionEvent e) {
    Object button = e.getSource();
    if(button == jButton3) // define allele
    {
      Point point = this.getLocation();
      Dimension d = this.getPreferredSize();

      int x_location = point.x + d.width;
      int y_location = point.y + 40;
      dialog.showDialog(x_location, y_location);
    }

    if(button == jButton4) // define reset
    {
      if(Datamodel.hasValue("allele_trait_info"))
      {
        jTextField3.setText("");
        jLabel4.setFont(new java.awt.Font("Dialog", 0, 11));
        Datamodel.removeValue("allele_trait_info");
        dialog.listModel.removeAllElements();
      }
    }
    if(button == jMenuItemDelete)
    {
      int index = infoList.getSelectedIndex();
        listModel.remove(index);
    }
    else if (button == okButton) {//ok
      save_init_state();
      dispose();
    }
    else if (button == cancelButton) {//cancel
      dispose();
      if(!Datamodel.hasValue("pools_locus"))
        listModel.removeAllElements();
      jComboBox1.setSelectedIndex(0);
    }
    else if (button == jAddButton) {
      if (jTextField2.getText().length() > 0) {
        String locus = "locus = "+"\""+ jTextField2.getText().trim()+"\"";
        locus = locus +"\n"+"{"+"\n";
        if(Datamodel.hasValue("allele_trait_info"))
        {
          locus = locus + Datamodel.getValue("allele_trait_info").toString()+"\n";
        }
        if (jTextField4.getText().length() > 0) {
          locus = locus + "last_allele = " + "\""+ jTextField4.getText().trim()+ "\""+"\n";
        }
        locus = locus + "}";

        listModel.addElement(locus);

        jTextField2.setText("");
        jTextField4.setText("");

        if(Datamodel.hasValue("allele_trait_info"))
        {
          jTextField3.setText("");
          jLabel4.setFont(new java.awt.Font("Dialog", 0, 11));
          Datamodel.removeValue("allele_trait_info");
          dialog.listModel.removeAllElements();
        }
      }
    }
  }

  public void changedUpdate(DocumentEvent event) {
    Document document = event.getDocument();
    if (document == jTextField1.getDocument()) {
      if (jTextField1.getText().length() > 0) {
        Datamodel.setValue("pool_size", jTextField1.getText().trim());
      } else {
        if(Datamodel.hasValue("pool_size"))
          Datamodel.removeValue("pool_size");
      }
    }
  }

  public void insertUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void removeUpdate(DocumentEvent event) {
    changedUpdate(event);
  }

  public void itemStateChanged(ItemEvent e) {
    Object ob = e.getSource();

    if (ob == jComboBox1) {
      if(jComboBox1.getSelectedIndex()>0)
        Datamodel.setValue("pool_size_trait", jComboBox1.getSelectedItem().toString());
      if(jComboBox1.getSelectedIndex()==0)
      {
        if(Datamodel.hasValue("pool_size_trait"))
          Datamodel.removeValue("pool_size_trait");
      }
    }
  }

  void onPressedF1() {
    Frame1.mainFrame1.jMenuHelpPDF_actionPerformed();
    Frame1.mainFrame1.pdfframe.setTextonPage("pool_size", false, 413);
  }

  void jComboBox1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pool_size_trai", false, 413);
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("pool_size", false, 413);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("locus", false, 413);
  }

  void jTextField3_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("allele", false, 414);
  }

  void jTextField4_mouseClicked(MouseEvent e) {
    Frame1.mainFrame1.pdfframe.setTextonPage("last_allele", false, 414);
  }
}

class DECIPHER_Dialog3_Pools_jComboBox1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog3_Pools adaptee;

  DECIPHER_Dialog3_Pools_jComboBox1_mouseAdapter(DECIPHER_Dialog3_Pools adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jComboBox1_mouseClicked(e);
  }
}

class DECIPHER_Dialog3_Pools_jTextField1_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog3_Pools adaptee;

  DECIPHER_Dialog3_Pools_jTextField1_mouseAdapter(DECIPHER_Dialog3_Pools adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField1_mouseClicked(e);
  }
}

class DECIPHER_Dialog3_Pools_jTextField2_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog3_Pools adaptee;

  DECIPHER_Dialog3_Pools_jTextField2_mouseAdapter(DECIPHER_Dialog3_Pools adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField2_mouseClicked(e);
  }
}

class DECIPHER_Dialog3_Pools_jTextField3_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog3_Pools adaptee;

  DECIPHER_Dialog3_Pools_jTextField3_mouseAdapter(DECIPHER_Dialog3_Pools adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField3_mouseClicked(e);
  }
}

class DECIPHER_Dialog3_Pools_jTextField4_mouseAdapter extends java.awt.event.MouseAdapter {
  DECIPHER_Dialog3_Pools adaptee;

  DECIPHER_Dialog3_Pools_jTextField4_mouseAdapter(DECIPHER_Dialog3_Pools adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.jTextField4_mouseClicked(e);
  }
}

