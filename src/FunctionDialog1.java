package sage;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

public class FunctionDialog1 extends JDialog implements ActionListener{
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jTopPanel = new JPanel();
  JPanel jCenterPanel = new JPanel();
  JPanel jBottomPanel = new JPanel();

  BorderLayout borderLayout2 = new BorderLayout();
  JLabel jLabel2 = new JLabel();

  EdgeBorder eb= new EdgeBorder(EdgeBorder.SOUTH);
  BorderLayout borderLayout4 = new BorderLayout();
  JLabel jLabel1 = new JLabel();
  JPanel jPanel1 = new JPanel();

  Vector v_data = new Vector();

  ListSelectionModel rowSM;
  FunctionDialog3 fdialog3;
  static Vector originalfunlist=new Vector();
  JLabel jLabel3 = new JLabel();

  static String FilePath=new String();

  static File tempfile;
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JPanel jPanel2 = new JPanel();
  JButton jAddButton = new JButton();
  JButton jDeleteButton = new JButton();
  JButton jUpButton = new JButton();
  JButton jDownButton = new JButton();
  JButton jEditButton = new JButton();
  BorderLayout borderLayout3 = new BorderLayout();
  JPanel jPanel3 = new JPanel();
  JScrollPane jScrollPane2 = new JScrollPane();
  JTable jTable1 = new JTable();
  BorderLayout borderLayout5 = new BorderLayout();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();

  public FunctionDialog1(Frame frame, String title, boolean modal){
      super(frame, title, modal);
      try {
          jbInit();
          pack();
      }
      catch(Exception ex) {
      }
  }

  private void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    this.setSize(new Dimension(555, 350));

    fdialog3 = new FunctionDialog3(Frame1.mainFrame1,"Specification",true);
    fdialog3.setLocationRelativeTo(this);

    Vector v_field = new Vector();
    v_data = new Vector();

    v_field.addElement("Name");
    v_field.addElement("Expression");
    v_field.addElement("Type");

    jTable1.setFont(new java.awt.Font("Monospaced", 0, 11));
    jTable1.setAutoCreateColumnsFromModel(false);
    jTable1.setModel(new FunctionExp(v_data, v_field));
    jTable1.setRowHeight(20);
    jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


    for (int i = 0; i < FunctionExp.headers.length; i++) {
        DefaultTableCellRenderer tr = new DefaultTableCellRenderer();
        tr.setHorizontalAlignment(FunctionExp.alignment[i]);
        TableColumn column = new TableColumn(i, FunctionExp.columnWidth[i], tr, null);
        jTable1.addColumn(column);
    }

    rowSM = jTable1.getSelectionModel();
    rowSM.addListSelectionListener(new myListSelectionListener());

    jTopPanel.setBackground(Color.white);
    jTopPanel.setPreferredSize(new Dimension(555, 45));
    jTopPanel.setLayout(borderLayout2);
    jCenterPanel.setPreferredSize(new Dimension(555, 265));
    jCenterPanel.setLayout(borderLayout4);
    jBottomPanel.setPreferredSize(new Dimension(555, 40));
    jBottomPanel.setLayout(gridBagLayout2);

    jPanel2.setPreferredSize(new Dimension(90, 265));

    jPanel3.setLayout(borderLayout5);
    jPanel3.setPreferredSize(new Dimension(485, 265));
    jPanel3.setBorder(new EmptyBorder(20,20,20,20));

    jLabel2.setBorder(eb);
    jLabel1.setBorder(eb);
    jLabel2.setText("");
    jPanel1.setLayout(borderLayout3);
    jLabel3.setText("     User-defined function parameters");
    jButton1.setText("OK");
    jButton2.setText("Cancel");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jPanel2.setLayout(gridBagLayout1);
    jAddButton.setText("Add");
    jAddButton.addActionListener(this);
    jDeleteButton.setText("Delete");
    jDeleteButton.setEnabled(false);
    jDeleteButton.addActionListener(this);
    jUpButton.setHorizontalAlignment(SwingConstants.LEFT);
    jUpButton.setIcon(new ImageIcon(FunctionDialog1.class.getResource("up.png")));
    jUpButton.setMargin(new Insets(2, 2, 2, 2));
    jUpButton.setText("up");
    jUpButton.setEnabled(false);
    jUpButton.addActionListener(this);
    jDownButton.setHorizontalAlignment(SwingConstants.LEFT);
    jDownButton.setIcon(new ImageIcon(FunctionDialog1.class.getResource("down.png")));
    jDownButton.setMargin(new Insets(2, 2, 2, 2));
    jDownButton.setText("down");
    jDownButton.setEnabled(false);
    jDownButton.addActionListener(this);
    jEditButton.setEnabled(false);
    jEditButton.setText("Edit");
    jEditButton.addActionListener(this);
    jScrollPane2.getViewport().setBackground(Color.white);
    jScrollPane2.getViewport().add(jTable1, null);


    getContentPane().add(panel1);
    panel1.add(jTopPanel,  BorderLayout.NORTH);
    jTopPanel.add(jLabel2, BorderLayout.SOUTH);
    jTopPanel.add(jLabel3, BorderLayout.CENTER);
    panel1.add(jCenterPanel,  BorderLayout.CENTER);
    jCenterPanel.add(jLabel1, BorderLayout.SOUTH);
    jCenterPanel.add(jPanel1, BorderLayout.CENTER);
    panel1.add(jBottomPanel, BorderLayout.SOUTH);
    jBottomPanel.add(jButton1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(7, 385, 8, 0), 23, 2));
        jBottomPanel.add(jButton2,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(7, 15, 8, 15), 29, 2));
    jPanel1.add(jPanel2, BorderLayout.EAST);
    jPanel2.add(jAddButton,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(35, 0, 0, 20), 17, 2));
    jPanel2.add(jDownButton,  new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 0, 33, 20), 15, 2));
    jPanel2.add(jUpButton,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(46, 0, 0, 20), 31, 2));
    jPanel2.add(jDeleteButton,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 0, 0, 20), 7, 2));
    jPanel2.add(jEditButton,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(8, 0, 0, 20), 21, 2));
    jPanel1.add(jPanel3, BorderLayout.CENTER);
    jPanel3.add(jScrollPane2, BorderLayout.CENTER);

    jButton1.addActionListener(this);
    jButton2.addActionListener(this);

  }

  public void actionPerformed(ActionEvent e) {
    Object ob = e.getSource();
    if(ob == jDeleteButton)
    {
      Vector del = (Vector)v_data.get(jTable1.getSelectedRow());
      v_data.remove(del);
      jTable1.updateUI();

      if(v_data.size()<1 || rowSM.isSelectionEmpty())
      {
        jDeleteButton.setEnabled(false);
        jUpButton.setEnabled(false);
        jDownButton.setEnabled(false);
        jEditButton.setEnabled(false);
      }
    }
    else if(ob == jAddButton)
    {
      fdialog3.okclicked=false;
      fdialog3.Name.requestFocus(true);
      fdialog3.setVisible(true);

      if(fdialog3.okclicked)
      {
        Vector addv = new Vector();
        addv.add(fdialog3.nameblock);
        addv.add(fdialog3.expblock);
        addv.add(fdialog3.type);
        addv.add(fdialog3.optionblock);
        addv.add(fdialog3.listoption);
        v_data.add(addv);

        jTable1.updateUI();
      }
    }
    else if(ob == jEditButton)
    {
      int current = jTable1.getSelectedRow();
      Vector editv = (Vector)v_data.get(current);

      fdialog3.setInfo(editv);
      fdialog3.okclicked=false;
      fdialog3.setVisible(true);
      if(fdialog3.okclicked)
      {
        editv.setElementAt(fdialog3.nameblock,0);
        editv.setElementAt(fdialog3.expblock,1);
        editv.setElementAt(fdialog3.type,2);
        editv.setElementAt(fdialog3.optionblock,3);
        editv.setElementAt(fdialog3.listoption,4);
        jTable1.updateUI();
      }
    }
    else if(ob == jUpButton)
    {
      int current = jTable1.getSelectedRow();
      if(current > 0)
      {
        Vector movev = (Vector)v_data.get(current);
        Vector backup = (Vector)v_data.get(current-1);

        v_data.setElementAt(movev, current-1);
        v_data.setElementAt(backup, current);
        jTable1.updateUI();
      }
    }
    else if(ob == jDownButton)
    {
      int current = jTable1.getSelectedRow();
      if(current <v_data.size()-1)
      {
        Vector movev = (Vector)v_data.get(current);
        Vector backup = (Vector)v_data.get(current+1);

        v_data.setElementAt(movev, current+1);
        v_data.setElementAt(backup, current);
        jTable1.updateUI();
      }
    }
    else if(ob == jButton1)
    {
      WriteFBlockInParaFile();
      NodeInfo parafilenode = (NodeInfo)Frame1.mainFrame1.activeinframe.parameterfilenode.getUserObject();
      try{
        Frame1.mainFrame1.activeinframe.updateMasterParaFile(parafilenode.file);
      }catch(Exception ex)
      {
      }
      dispose();
    }
    else if(ob == jButton2)
    {
      dispose();
    }
  }

    void WriteFBlockInParaFile()
    {
      Vector Trait_Farray=new Vector();
      Vector Covariate_Farray=new Vector();
      Vector Phenotype_Farray=new Vector();
      Vector function = new Vector();

      String strFileLine = new String();
      boolean pedi = false;
      boolean pediblockend = false;
      try {
        FileInputStream fin = new FileInputStream(tempfile);
        InputStreamReader isr = new InputStreamReader(fin);
        BufferedReader in = new BufferedReader(isr);
        FileWriter fos1 = new FileWriter(FilePath);

        while ( (strFileLine = in.readLine()) != null) {
          fos1.write(strFileLine+"\n");

          if (strFileLine.indexOf("pedigree")>=0) {
            if (strFileLine.startsWith("pedigree_id") == false)
              pedi = true;
          }

          if (pedi) {
              if (strFileLine.indexOf("}") >= 0) {

                if (strFileLine.indexOf("#") >= 0) {
                  int index1 = strFileLine.indexOf("}");
                  int index2 = strFileLine.indexOf("#");

                  if (index2 > index1) {
                    pedi = false;
                    pediblockend = true;
                  }
                }
                else
                {
                    pedi = false;
                    pediblockend = true;
                }
              }
          }

          if(pedi==false && pediblockend==true)
          {
            fos1.write("\n");

            for (Enumeration es = v_data.elements(); es.hasMoreElements(); ) {
              StringBuffer functionarray=new StringBuffer();

              Vector list = (Vector) es.nextElement();
              functionarray.append("function");

              String listoption = list.get(4).toString();
              if(listoption.length()>0)
                functionarray.append(", list = "+listoption+" { \n");
              else
                functionarray.append(" { \n");

              String vartype = list.get(2).toString();
              String varname = list.get(0).toString();

              if (vartype.compareTo("constant") == 0)
              {
                if(list.get(3).toString().length()>0)
                functionarray.append("time_limit = " + list.get(3).toString() + "\n");
              }

              functionarray.append(vartype + " = " + varname);
              functionarray.append(", expression = \"" + list.get(1).toString().trim() + "\"");

              if (vartype.compareTo("constant") != 0)
              {
                if(list.get(3).toString().trim().length()>0)
                  functionarray.append(", " + list.get(3).toString());

                if(vartype.compareTo("trait")==0)
                {
                  Trait_Farray.add(varname);
                }
                if(vartype.compareTo("covariate")==0)
                {
                  Covariate_Farray.add(varname);
                }
                if(vartype.compareTo("phenotype")==0)
                {
                  Phenotype_Farray.add(varname);
                }
              }
              functionarray.append("\n}\n");
              function.add(functionarray.toString());

              fos1.write(functionarray.toString());
            }

            NodeInfo parafilenode = (NodeInfo)Frame1.mainFrame1.activeinframe.parameterfilenode.getUserObject();
            parafilenode.infomodel.setValue("Trait_Farray", Trait_Farray);
            parafilenode.infomodel.setValue("Covariate_Farray", Covariate_Farray);
            parafilenode.infomodel.setValue("Phenotype_Farray", Phenotype_Farray);
            parafilenode.infomodel.setValue("Function_array", function);
            break;
          }
        }

         while ( (strFileLine = in.readLine()) != null) {
          fos1.write(strFileLine+"\n");
        }

        fos1.close();
        fin.close();
        isr.close();
        in.close();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }

  static Vector getFunctionBlockList(String filepath)
  {
    FilePath = filepath;

    String list = new String();
    originalfunlist.removeAllElements();

    boolean block = false;

    String strFileLine = new String();

    try {
      tempfile = File.createTempFile("original", "tmp");
      FileWriter fos = new FileWriter(tempfile);

      FileInputStream fin = new FileInputStream(filepath);
      InputStreamReader isr = new InputStreamReader(fin);
      BufferedReader in = new BufferedReader(isr);

      while ((strFileLine = in.readLine()) != null) {

        if (strFileLine.trim().startsWith("#")) {
          if(!block)
          {
            fos.write(strFileLine);
            fos.write("\n");
            continue;
          }
        }

        if (strFileLine.trim().startsWith("function")) {
          block = true;
        }

        if (block) {
          if (strFileLine.length() > 0) {
            list = list + strFileLine + "\n";
            if (strFileLine.indexOf("}") >= 0) {
              originalfunlist.add(list);
              list = "";
              block = false;
            }
          }
        }
        else {
          fos.write(strFileLine);
          fos.write("\n");
        }
      }

      fos.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return getFunctionVarList(originalfunlist);
  }

  static Vector getFunctionVarList(Vector originalfunlist)
  {
    Vector funlist=new Vector();

    for(int i=0;i<originalfunlist.size();i++)
    {
      String temp = originalfunlist.get(i).toString();

      String list_part = "";
      String list_option="";

      int index_of_left_s = temp.indexOf("{");

      if(index_of_left_s >=0)
      {
        if(temp.substring(0, index_of_left_s).indexOf("list")>=0)
        {
          list_part = temp.substring(9, index_of_left_s);
          temp = temp.substring(index_of_left_s+1,temp.length()-2).trim();

          String listoption[] = list_part.split("=");
          if(listoption.length==2)
          {
            list_option = listoption[1].trim();
          }
        }
        else
        {
          temp = temp.substring(index_of_left_s+1,temp.length()-2).trim();
        }
      }

      String timelimit=new String();

      if (temp.indexOf("time_limit") != -1) {
        int index = temp.indexOf("time_limit");
        int newline = temp.indexOf("\n",index);

        if(newline==-1)
          newline = index+16;

        String subs = temp.substring(index, newline).trim();

        String traittemp[] = new String[2];
        traittemp = subs.split("=");
        timelimit = traittemp[1].replaceAll("\"", "").trim();

        if(index==0)
        {
          temp = temp.substring(newline+1,temp.length());
        }
        else if(index >1)
        {
          String temp1 = temp.substring(0,index-1);
          String temp2 = temp.substring(newline+1,temp.length());
          temp = temp1+temp2;
        }
      }

      if (temp.indexOf("constant") != -1)
      {
        boolean skip = false;
        String option = new String();
        Vector v = new Vector();

        int index = temp.indexOf("constant");
        String subs = temp.substring(index, temp.length());

        String subs2 = temp.substring(0, index);
        subs = subs + subs2;

        if(subs.indexOf("expression")!=-1)
        {
          int exindex = subs.indexOf("expression");
          int endindex = subs.lastIndexOf("expression");

           if(exindex==endindex)
          {
            String tempname = subs.substring(0, exindex-1);
            String tempexp = subs.substring(exindex, subs.length());

            StringTokenizer st1 = new StringTokenizer(tempname,"=");

            st1.nextToken();

            String name = st1.nextToken().replaceAll("\"","");
            String exp = tempexp.substring(tempexp.indexOf("\""), tempexp.lastIndexOf("\"")).replaceAll("\"", "");
            name = name.replaceAll(",","").trim();
            exp = exp.replaceAll(",","").trim();

            v.add(name);
            v.add(exp);
            v.add("constant");
            v.add(timelimit);
            v.add(list_option);
            funlist.add(v);
          }
          else
          {
            int conindex = 0;
            String type = new String();
            if (subs.indexOf("trait") != -1) {
              conindex = subs.indexOf("trait");
              type = "trait";
            }
            else if (subs.indexOf("covariate") != -1) {
              conindex = subs.indexOf("covariate");
              type = "covariate";
            }
            else if (subs.indexOf("phenotype") != -1) {
              conindex = subs.indexOf("phenotype");
              type = "phenotype";
            }

            String tempname = subs.substring(0, exindex);
            String tempexp = subs.substring(exindex, conindex);

            StringTokenizer st1 = new StringTokenizer(tempname, "=");
            st1.nextToken();

            String name = st1.nextToken().replaceAll("\"", "");
            String exp = tempexp.substring(tempexp.indexOf("\""), tempexp.lastIndexOf("\"")).replaceAll("\"", "");
            name = name.replaceAll(",", "").trim();
            exp = exp.replaceAll(",", "").trim();

            Vector vp = new Vector();
            vp.add(name);
            vp.add(exp);
            vp.add("constant");
            vp.add(timelimit);
            vp.add(list_option);
            funlist.add(vp);

            tempname = subs.substring(conindex, subs.length());
            int exindex2 = subs.lastIndexOf("expression");

            tempname = subs.substring(conindex, exindex2 );
            tempexp = subs.substring(exindex2, subs.length());

            StringTokenizer st11 = new StringTokenizer(tempname, ",");
            if (st11.countTokens() > 1) {
              while (st11.hasMoreTokens()) {
                String token = st11.nextToken();
                if (token.indexOf(type) != -1) {
                  String traittemp[] = token.split("=");
                  name = traittemp[1].replaceAll("\"", "");
                  name = name.replaceAll(",", "").trim();
                }
                else {
                  option = option + token + ", ";
                }
              }

              option = option.substring(0, option.length()-2);
              skip = true;
            }
            else {
              String traittemp2[] = tempname.split("=");
              name = traittemp2[1].replaceAll("\"", "");
              name = name.replaceAll(",", "").trim();
            }

            int q1 = tempexp.indexOf("\"");
            int q2 = tempexp.indexOf("\"", q1+1);

            if(q1 == -1 || q2==-1)
            {
                StringTokenizer stexp1 = new StringTokenizer(tempexp,",");
                String comexp = new String();
                while (stexp1.hasMoreTokens()) {
                  String token = stexp1.nextToken().trim();;
                  if (token.indexOf("expression") != -1) {
                    comexp = token;
                  }
                  else if(token.indexOf("affected")!=-1 ||token.indexOf("unaffected")!=-1
                          ||token.indexOf("missing")!=-1||token.indexOf("name")!=-1
                          ||token.indexOf("binary")!=-1){
                    if(skip)
                      option = option + ", "+token ;
                    else
                      option = option + token +", ";
                  }
                  else
                  {
                      comexp = comexp + ", "+ token;
                  }
                }
                String explasttemp[] = comexp.split("=");
                exp = explasttemp[1].replaceAll("\"", "").trim();
                if(option.length()>2 && !skip)
                  option = option.substring(0, option.length()-2);
            }
            else
            {
              exp = tempexp.substring(q1+1, q2);

              if(q2+1 < tempexp.length())
              {
                String lestoption = tempexp.substring(q2+1, tempexp.length());

                if(lestoption.length()>5)
                  option = option + tempexp.substring(q2+1, tempexp.length());
                if(option.length()>2 && !skip)
                  option = option.replaceFirst(",", "").trim();
              }
            }

            v.add(name);
            v.add(exp);
            v.add(type);
            v.add(option);
            v.add(list_option);
            funlist.add(v);
          }
        }
      }
      else if (temp.indexOf("trait") != -1)
      {
          Vector v = getEachOptionLists(temp, "trait");

          if(v.size()>0)
          {
              v.add(list_option);
          }
          funlist.add(v);
      }
      else if (temp.indexOf("covariate") != -1)
      {
          Vector v = getEachOptionLists(temp, "covariate");

          if(v.size()>0)
          {
              v.add(list_option);
          }
          funlist.add(v);
      }
      else if (temp.indexOf("phenotype") != -1)
      {
          Vector v = getEachOptionLists(temp, "phenotype");

          if(v.size()>0)
          {
              v.add(list_option);
          }
          funlist.add(v);
      }
    }

    return funlist;
  }

  static Vector getEachOptionLists(String temp, String type)
  {
      boolean skip = false;
      Vector v = new Vector();
      int index = temp.indexOf(type);
      String subs = temp.substring(index, temp.length());
      String option = new String();
      String name = new String();
      String exp = new String();

      if(subs.indexOf("expression")!=-1)
      {
        int exindex = subs.indexOf("expression");
        String tempname = subs.substring(0, exindex-1).trim();
        String tempexp = subs.substring(exindex, subs.length()).trim();

        StringTokenizer st1 = new StringTokenizer(tempname,",");
        if(st1.countTokens()>1)
        {
          while (st1.hasMoreTokens()) {
            String token = st1.nextToken();
            if (token.indexOf(type) != -1)
            {
              String traittemp[] = token.split("=");
              if(traittemp.length>1)
              {
                name = traittemp[1].replaceAll("\"","");
                name = name.replaceAll(",","").trim();
              }
            }
            else
            {
                option = option + token +", ";
            }
         }
         option = option.substring(0, option.length()-2);
         skip = true;
        }
        else
        {
          String traittemp2[] = tempname.split("=");
          name = traittemp2[1].replaceAll("\"", "");
          name = name.replaceAll(",","").trim();
        }

        int q1 = tempexp.indexOf("\"");
        int q2 = tempexp.indexOf("\"", q1+1);

        if(q1 == -1 || q2==-1)
        {
            StringTokenizer stexp1 = new StringTokenizer(tempexp,",");
            String comexp = new String();
            while (stexp1.hasMoreTokens()) {
              String token = stexp1.nextToken().trim();;
              if (token.indexOf("expression") != -1) {
                comexp = token;
              }
              else if(token.indexOf("affected")!=-1 ||token.indexOf("unaffected")!=-1
                      ||token.indexOf("missing")!=-1||token.indexOf("name")!=-1
                      ||token.indexOf("binary")!=-1){
                if(skip)
                  option = option + ", "+token ;
                else
                  option = option + token +", ";
              }
              else
              {
                  comexp = comexp + ", "+ token;
              }
            }
            String explasttemp[] = comexp.split("=");
            exp = explasttemp[1].replaceAll("\"", "").trim();
            if(option.length()>2 && !skip)
              option = option.substring(0, option.length()-2);
        }
        else
        {
          exp = tempexp.substring(q1+1, q2);

          if(q2+1 < tempexp.length())
          {
            option = option + tempexp.substring(q2+1, tempexp.length());
            if(option.length()>2 && !skip)
              option = option.replaceFirst(",", "").trim();
          }
        }
        v.add(name);
        v.add(exp);
        v.add(type);
        v.add(option);
        }

        return v;
  }

  class myListSelectionListener implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) {
      if (e.getValueIsAdjusting())
        return;

      if (rowSM.isSelectionEmpty()) {
        jDeleteButton.setEnabled(false);
        jUpButton.setEnabled(false);
        jDownButton.setEnabled(false);
        jEditButton.setEnabled(false);
      }
      else {
        jDeleteButton.setEnabled(true);
        jUpButton.setEnabled(true);
        jDownButton.setEnabled(true);
        jEditButton.setEnabled(true);
      }
    }
  }

}

class FunctionExp extends DefaultTableModel {
    public static String[] headers = new String[] {"Name", "Expression", "Type"};
    public static int[] columnWidth = new int[] {100, 250, 90};
    static int[] alignment = new int[] {JLabel.LEFT, JLabel.LEFT, JLabel.LEFT};
    public FunctionExp(Vector data, Vector field) {
        super(data, field);
    }
}
