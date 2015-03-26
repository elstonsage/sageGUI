package sage;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;

public class WizardNavigator3 extends JPanel implements ActionListener, ChangeListener{
    JButton back, next;
    Icon backIcon, nextIcon;
    DeckPanel deck;
    boolean first = true;
    DefaultListModel listmodel;
    Vector column = new Vector();
    int column_size=0;
    Vector linelist = new Vector();
    Vector pid_list = new Vector();
    WarningDialog wd = new WarningDialog(JWizardSQL.wizardSQL, "Warning", true);


    public WizardNavigator3(DeckPanel deck) throws Exception{
      this.deck = deck;
      setOpaque(true);
      setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
      setBorder(new EdgeBorder(EdgeBorder.NORTH));
      JPanel nav = new JPanel();
      nav.setLayout(new BorderLayout());
      backIcon = new ImageIcon(WizardNavigator3.class.getResource("WestArrow.gif"));
      nav.add("West", back = new JButton("Back", backIcon));
      back.setMargin(new Insets(1, 5, 1, 10));
      back.setPreferredSize(new Dimension(80, 20));
      back.addActionListener(this);
      nextIcon = new ImageIcon(WizardNavigator3.class.getResource("EastArrow.gif"));
      nav.add("East", next = new JButton("Next", nextIcon));
      next.setHorizontalTextPosition(JButton.LEFT);
      next.setMargin(new Insets(1, 1, 1, 1));
      next.setPreferredSize(new Dimension(80, 20));
      next.addActionListener(this);
      add(nav);
    }

    public void actionPerformed(ActionEvent event) {
      Object source = event.getSource();

      if (source == back) {
          if (deck.getCurrent().compareTo("makesql2") == 0)
          {
              MakeSQLStep2 m2 = (MakeSQLStep2) deck.getComponent("makesql2");
              m2.close_Database();

              m2.query_list.removeAllElements();
              m2.pQuary.setText("SELECT * FROM PEDIGREE;");
          }

        setPanel(deck.getPrevious());
      }
      if (source == next) {

          if (next.getText().compareTo("Finish") == 0)
          {
              JWizardSQL.mainframe.setEnabled(true);
              getTopLevelAncestor().setVisible(false);
              MakeSQLStep2 m2 = (MakeSQLStep2) deck.getComponent("makesql2");
              m2.close_Database();
          }

        if (deck.getCurrent().compareTo("makesql1") == 0) {
          MakeSQLStep1 m = (MakeSQLStep1) deck.getComponent("makesql1");
          final String filepath = m.pName.getText();

          column.removeAllElements();
          linelist.removeAllElements();

          if (m.jCheck1.isSelected()) {
              String source_file_path = m.pName.getText();
              String delimiter = new String();

              if (m.jRadioButton1.isSelected()) {
                  if (m.tab.isSelected())
                      delimiter = "\t";
                  if (m.comma.isSelected())
                      delimiter = ",";
                  if (m.space.isSelected())
                      delimiter = " ";
                  if (m.other.isSelected())
                      delimiter = m.delimiter.getText();
              }

              try {
                  if (m.single.isSelected()) {
                      FileReader fr = new FileReader(source_file_path);
                      BufferedReader br = new BufferedReader(fr);
                      String temp = br.readLine();
                      String[] t = temp.split(delimiter);
                      for (int ti = 0; ti < t.length; ti++) {
                          column.add(t[ti]);
                      }
                      column_size = column.size();
                  }
                  else if (m.multiple.isSelected()) {
                      FileReader fr = new FileReader(source_file_path);
                      BufferedReader br = new BufferedReader(fr);
                      String temp = br.readLine();
                      StringTokenizer st = new StringTokenizer(temp, delimiter);
                      while (st.hasMoreTokens()) {
                          column.add(st.nextToken());
                      }
                      column_size = column.size();
                  }
              } catch (Exception ex) {
                  JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                               "Exception occurred while a file is being read." +
                                               "\nPlease check your file.",
                                               "Error",
                                               JOptionPane.CLOSED_OPTION,
                                               JOptionPane.WARNING_MESSAGE, null, null, null);
                  return;
              }


              Vector original_name = new Vector();
              Vector replaced_name = new Vector();

              boolean tag = false;

              for (int i = 0; i < column.size(); i++)
              {
                  String cname1 = column.get(i).toString();

                  for(int j=0;j<cname1.length()-1;j++)
                  {
                      String each_name = cname1.substring(j,j+1);

                      if(each_name.matches("[^a-zA-Z0-9]"))
                      {
                          String new_name = cname1.replaceAll("[^a-zA-Z0-9]", "_");

                          if(new_name.compareTo(cname1)!=0)
                          {
                              original_name.add(cname1);
                              replaced_name.add(new_name);
                              column.set(i, new_name);
                              tag = true;
                              continue;
                          }
                      }
                  }
              }

              String message = new String();

              wd.jTextArea1.setText("");
              for(int i=0;i<original_name.size();i++)
              {
                  message = message + "The field named \""+original_name.get(i)
                            +"\" will be renamed to \""+replaced_name.get(i)
                            +"\" for SQL compatibility."+"\n";
              }

              wd.jTextArea1.setText(message);

              if (tag) {
                wd.setLocationRelativeTo(JWizardSQL.wizardSQL);
                wd.setVisible(true);

                switch (wd.option) {
                case 2:
                  return;
                }
              }

              for (int i = 0; i < column.size(); i++)
              {
                  int redun_count = 0;

                  for (int j = 0; j < column.size(); j++) {
                      if (i != j) {
                          String cname1 = column.get(i).toString();
                          String cname2 = column.get(j).toString();

                          if (cname1.compareTo(cname2) == 0) {
                              redun_count++;

                              cname2 = cname2 + "_" + redun_count;
                              column.set(j, cname2);
                          }
                      }
                  }
              }
          }

          Runnable lookupData = new Runnable() {
              public void run() {
                  try {
                      Make_Table_Data();
                  } catch (Exception excel) {
                      excel.printStackTrace();
                      JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                                   "I/O exception encountered while attempting to read file '" +
                                                   filepath +
                                                   "'.\nPlease check the file for correct formatting, file attributes, " +
                                                   "\nuser access privileges, possible data corruption, etc. ",
                                                   "Error",
                                                   JOptionPane.CLOSED_OPTION,
                                                   JOptionPane.WARNING_MESSAGE, null, null, null);
                      return;
                  }
              }
          };

          Thread lookupThread = new Thread(lookupData, "MyThread");
          lookupThread.start();
      }
        setPanel(deck.getNext());
      }
    }

    public void stateChanged(ChangeEvent event) {
      checkValidation(deck.getCurrent());
    }

    public void checkValidation(String name) {
      if (deck.getComponent(name) instanceof WizardValidator) {
        WizardValidator validator = ( (WizardValidator) deck.getComponent(name));
        next.setEnabled(validator.canMoveForward());
        back.setEnabled(validator.canMoveBackward());
        paintAll(getGraphics());
      }
    }

    public void setPanel(String name) {
      deck.setPanel(name);
      checkValidation(name);
      back.setVisible(!deck.getFirst().equals(name));
      back.setEnabled(!deck.getFirst().equals(name));
      if (deck.isLast(name)) {
        next.setText("Finish");
        next.setIcon(null);
      }
      else {

        next.setText("Next");
        next.setIcon(nextIcon);
      }
      paintAll(getGraphics());
  }

  public void Make_Table_Data() {
    MakeSQLStep2 m2 = (MakeSQLStep2) deck.getComponent("makesql2");
    m2.clear_table_data();


    MakeSQLStep1 m = (MakeSQLStep1) deck.getComponent("makesql1");

    String source_file_path = m.pName.getText();
    String delimiter = new String();

    if (m.jRadioButton1.isSelected()) {
      if (m.tab.isSelected())
        delimiter = "\t";
      if (m.comma.isSelected())
        delimiter = ",";
      if (m.space.isSelected())
        delimiter = " ";
      if (m.other.isSelected())
        delimiter = m.delimiter.getText();
    }

    boolean isHeaderExist = false;
    if (m.jCheck1.isSelected())
      isHeaderExist = true;

    if (m.single.isSelected()) {
      int i = 0;
      try {
        FileReader fr = new FileReader(source_file_path);
        BufferedReader br = new BufferedReader(fr);
        String temp = new String();
        while ( (temp = br.readLine()) != null) {
          String[] t = temp.split(delimiter, column_size);
          if (isHeaderExist) {
            if (i == 0) {
            }
            else if (i > 0) {
              Vector eachlinecontents = new Vector();
              for (int ti = 0; ti < t.length; ti++) {
                eachlinecontents.add(t[ti]);
              }
              linelist.add(eachlinecontents);
            }
            i++;
          }
          else {
            Vector eachlinecontents = new Vector();
            for (int ti = 0; ti < t.length; ti++) {
              eachlinecontents.add(t[ti]);
            }
            linelist.add(eachlinecontents);
          }
        }
      }
      catch (Exception ex) {
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                     "Exception occurred while a file is being read." +
                                     "\nPlease check your file.",
                                     "Error",
                                     JOptionPane.CLOSED_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null, null, null);
        return;
      }
    }
    else if (m.multiple.isSelected()) {
      int i = 0;

      try {
        FileReader fr = new FileReader(source_file_path);
        BufferedReader br = new BufferedReader(fr);
        String temp = new String();
        String temp2 = new String();
        while ( (temp = br.readLine()) != null) {
          StringTokenizer st = new StringTokenizer(temp, delimiter);

          int ct = st.countTokens();
          if (isHeaderExist) {
            if (i == 0) {
            } else if (i > 0) {
              Vector eachlinecontents = new Vector();
              for (int j = 0; j < ct; j++) {
                temp2 = st.nextToken();
                if (temp2 == null)
                  eachlinecontents.add(" ");
                else
                  eachlinecontents.add(temp2);
              }
              linelist.add(eachlinecontents);
            }
            i++;
          } else {
            Vector eachlinecontents = new Vector();
            for (int j = 0; j < ct; j++) {
              temp2 = st.nextToken();
              if (temp2 == null)
                eachlinecontents.add(" ");
              else
                eachlinecontents.add(temp2);
            }
            linelist.add(eachlinecontents);
          }
        }
      }
      catch (Exception ex) {
        JOptionPane.showOptionDialog(JWizardProject.wizardProject,
                                     "Exception occurred while a file is being read." +
                                     "\nPlease check your file.",
                                     "Error",
                                     JOptionPane.CLOSED_OPTION,
                                     JOptionPane.WARNING_MESSAGE, null, null, null);
        return;
      }
    }

    if (!isHeaderExist) {
      for (int c = 0; c < column_size; c++) {
        column.add("column " + c);
      }
    }

    linelist.trimToSize();
    column.trimToSize();

    m2.set_table_data(linelist, column);
  }

}
