package sage;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import javax.swing.DefaultListModel;
import javax.swing.*;
import javax.swing.JPopupMenu;

import java.io.*;
import java.util.*;
import java.io.File;

public class FavoritesPanel extends WizardPanel implements ActionListener
{
        protected JButton RemoveButton;
        protected JButton AddButton;
        protected JList FileList;
        DefaultListModel listModel;
        int data_count=0;
        JPopupMenu jAddPopupMenu = new JPopupMenu();
        JMenuItem jAddLocus = new JMenuItem();
        JMenuItem jAddPedigree = new JMenuItem();
        JMenuItem jAddParameter = new JMenuItem();
        JMenuItem jAddIBD = new JMenuItem();
        JMenuItem jAddGenome = new JMenuItem();
        JMenuItem jAddType = new JMenuItem();
        static String parameter_file_info = new String();

        NodeInfo nodeinfo = new NodeInfo("Internal", "Internal", "Internal", "Internal");
        IconNode internalnode = new IconNode(nodeinfo, "Internal");

       boolean parameter_file = false;
       KeyListener keylistener = new DeleteKeyListener();

       ImageIcon data_image;
       ImageIcon arrow_null;
       ImageIcon arrow;

        public FavoritesPanel() throws Exception
        {
                super("Project Settings", "Create a list of S.A.G.E. source files."+
                      " At least one parameter file required."+
                      "\nIf files are not specified, you will have to specify them later.");

                jAddParameter.setText("Parameter file");
                jAddPedigree.setText("Data file");
                jAddLocus.setText("Locus file");
                jAddIBD.setText("IBD file");
                jAddGenome.setText("Genome file");
                jAddType.setText("Type file");
                jAddPopupMenu.add(jAddParameter);
                jAddPopupMenu.add(jAddPedigree);
               jAddPopupMenu.add(jAddLocus);
                jAddPopupMenu.add(jAddGenome);
                jAddPopupMenu.add(jAddIBD);
                jAddPopupMenu.add(jAddType);

                jAddParameter.addActionListener(this);
                jAddPedigree.addActionListener(this);
                jAddLocus.addActionListener(this);
                jAddGenome.addActionListener(this);
                jAddIBD.addActionListener(this);
                jAddType.addActionListener(this);

                data_image = new ImageIcon(FavoritesPanel.class.getResource("data_big.png"));
                arrow_null = new ImageIcon(FavoritesPanel.class.getResource("arrow_null.png"));
                arrow = new ImageIcon(FavoritesPanel.class.getResource("arrow.png"));

                JTabbedPane jTabbedPane = new JTabbedPane();
                JPanel TotalPanel = new JPanel();
                JPanel LeftPanel = new JPanel();
                JPanel RightPanel = new JPanel();
                JPanel TopPanel = new JPanel();
                JLabel jLabel1 = new JLabel("Source files path");
                FileList = new JList();
                listModel = new DefaultListModel();
                FileList.setModel(listModel);
                FileList.setCellRenderer(new MyListRenderer());

                FileList.setBorder(BorderFactory.createLoweredBevelBorder());
                JPanel BottomjPanel = new JPanel();
                AddButton = new JButton("Add");
                AddButton.setPreferredSize(new Dimension(80,20));
                AddButton.setHorizontalTextPosition(SwingConstants.LEFT);
                AddButton.setIcon(arrow_null);
                AddButton.setIconTextGap(2);

                RemoveButton = new JButton("Remove");
                RemoveButton.setEnabled(false);
                RemoveButton.setPreferredSize(new Dimension(80,20));
                RemoveButton.setHorizontalTextPosition(SwingConstants.LEFT);

                TopPanel.setLayout(new BorderLayout());
                TopPanel.setBorder(new EmptyBorder(0,5,0,5));
                TopPanel.add(jLabel1, BorderLayout.CENTER);

                RightPanel.setLayout(new GridLayout(6,1,10,10));
                RightPanel.add(AddButton);
                RightPanel.add(RemoveButton);

                LeftPanel.setLayout(new BorderLayout());
                LeftPanel.add(FileList,  BorderLayout.CENTER);
                LeftPanel.setBackground(Color.black);
                LeftPanel.setPreferredSize(new Dimension(260,200));

                TotalPanel.setLayout(new BorderLayout(5,5));
                TotalPanel.setPreferredSize(new Dimension(360, 220));
                TotalPanel.add(LeftPanel, BorderLayout.CENTER);
                TotalPanel.add(RightPanel, BorderLayout.EAST);
                TotalPanel.add(TopPanel, BorderLayout.NORTH);
                TotalPanel.add(BottomjPanel, BorderLayout.SOUTH);

                jTabbedPane.addTab("Source", data_image, TotalPanel,"Data source");

                JPanel center = new JPanel();
                center.setLayout(new UnitLayout());
                center.add(jTabbedPane);
                add("Center", center);

                MouseListener popupListener = new PopupListener();
                AddButton.addMouseListener(popupListener);
                RemoveButton.addActionListener(this);
                FileList.addKeyListener(keylistener);
        }

        public void actionPerformed(ActionEvent event)
        {
          Object source = event.getSource();

          if (source == jAddParameter) {
            jFileChooser1.setDialogTitle("Add Parameter File");

            jFileChooser1.setFileFilter(Frame1.mainFrame1.ParaFilter);

            Integer in = new Integer(data_count);
            String index = in.toString();

            String initpath = getDataModel().getValue("info.DirPath").toString();
            jFileChooser1.setCurrentDirectory(new File(initpath));

            if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
              canMoveForward = true;

              parameter_file = true;
              String filepath = jFileChooser1.getSelectedFile().getPath();
              String filename = jFileChooser1.getSelectedFile().getName();

              getDataModel().setValue("data.path" + index, filepath);
              getDataModel().setValue("data.name" + index, filename);
              getDataModel().setValue("datafilecounts", index);
              RemoveButton.setEnabled(true);
              data_count++;

              NodeInfo filend = new NodeInfo(filename, "Parameter File", new File(filepath));
              IconNode filenode = new IconNode(filend, "Parameter File");
              getDataModel().setValue("ParameterFileNode", (IconNode)filenode);
              internalnode.add(filenode);
              getDataModel().setValue("internalnode", (IconNode)internalnode);

              listModel.addElement(filenode);

              try {
                  Parse_Parameter_File(filend); //trait,phenotype,covariate
                  Parse_Parameter_File2(filend); // analysis list
                  Parse_Parameter_File3(filend); //trait,phenotype,covariate from function block
              } catch (Exception e) {
              }

              jFileChooser1.setCurrentDirectory(jFileChooser1.getSelectedFile());
              Frame1.mainFrame1.path_forFileChooser = filepath;
            }

          }
          else if (source == jAddPedigree) {
            jFileChooser1.setDialogTitle("Add Data File");
            jFileChooser1.setFileFilter(Frame1.mainFrame1.FamilyFilter);

            Integer in = new Integer(data_count);
            String index = in.toString();
            if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
              String filepath = jFileChooser1.getSelectedFile().getPath();
              String filename = jFileChooser1.getSelectedFile().getName();
              getDataModel().setValue("data.path" + index, filepath);
              getDataModel().setValue("data.name" + index, filename);
              getDataModel().setValue("datafilecounts", index);
              RemoveButton.setEnabled(true);
              data_count++;

              NodeInfo filend = new NodeInfo(filename, "Pedigree File", new File(filepath));
              IconNode filenode = new IconNode(filend, "Pedigree File");
              internalnode.add(filenode);
              getDataModel().setValue("internalnode", (IconNode)internalnode);

              listModel.addElement(filenode);

              jFileChooser1.setCurrentDirectory(jFileChooser1.getSelectedFile());
              Frame1.mainFrame1.path_forFileChooser = filepath;
            }

          }
          else if (source == jAddLocus) {
            jFileChooser1.setDialogTitle("Add Locus File");
            jFileChooser1.setFileFilter(Frame1.mainFrame1.MarkerLocusFilter);

            Integer in = new Integer(data_count);
            String index = in.toString();
            if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
              String filepath = jFileChooser1.getSelectedFile().getPath();
              String filename = jFileChooser1.getSelectedFile().getName();
              getDataModel().setValue("data.path" + index, filepath);
              getDataModel().setValue("data.name" + index, filename);
              getDataModel().setValue("datafilecounts", index);
              RemoveButton.setEnabled(true);
              data_count++;

              NodeInfo filend = new NodeInfo(filename, "Marker Locus File", new File(filepath));
              IconNode filenode = new IconNode(filend,"Marker Locus File");
              internalnode.add(filenode);
              getDataModel().setValue("internalnode", (IconNode)internalnode);

              listModel.addElement(filenode);
              jFileChooser1.setCurrentDirectory(jFileChooser1.getSelectedFile());
              Frame1.mainFrame1.path_forFileChooser = filepath;
            }
          }

          else if (source == jAddGenome) {
            jFileChooser1.setDialogTitle("Add Genome File");
            jFileChooser1.setFileFilter(Frame1.mainFrame1.GenomeFilter);

            Integer in = new Integer(data_count);
            String index = in.toString();
            if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
              String filepath = jFileChooser1.getSelectedFile().getPath();
              String filename = jFileChooser1.getSelectedFile().getName();
              getDataModel().setValue("data.path" + index, filepath);
              getDataModel().setValue("data.name" + index, filename);
              getDataModel().setValue("datafilecounts", index);
              RemoveButton.setEnabled(true);
              data_count++;

              NodeInfo filend = new NodeInfo(filename, "Genome File", new File(filepath));
              IconNode filenode = new IconNode(filend, "Genome File");
              internalnode.add(filenode);
              getDataModel().setValue("internalnode", (IconNode)internalnode);

              listModel.addElement(filenode);

              Parse_Genome_File(filend);
              jFileChooser1.setCurrentDirectory(jFileChooser1.getSelectedFile());
              Frame1.mainFrame1.path_forFileChooser = filepath;
            }

          }
          else if (source == jAddIBD) {
            jFileChooser1.setDialogTitle("Add IBD Sharing File");
            jFileChooser1.setFileFilter(Frame1.mainFrame1.IBDFilter);

            Integer in = new Integer(data_count);
            String index = in.toString();
            if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
              String filepath = jFileChooser1.getSelectedFile().getPath();
              String filename = jFileChooser1.getSelectedFile().getName();
              getDataModel().setValue("data.path" + index, filepath);
              getDataModel().setValue("data.name" + index, filename);
              getDataModel().setValue("datafilecounts", index);
              RemoveButton.setEnabled(true);
              data_count++;

              NodeInfo filend = new NodeInfo(filename, "IBD Sharing File", new File(filepath));
              IconNode filenode = new IconNode(filend, "IBD Sharing File");
              internalnode.add(filenode);
              getDataModel().setValue("internalnode", (IconNode)internalnode);

              listModel.addElement(filenode);

              Parse_IBD_File(filend);
              jFileChooser1.setCurrentDirectory(jFileChooser1.getSelectedFile());
              Frame1.mainFrame1.path_forFileChooser = filepath;
            }
          }
          else if (source == jAddType) {
            jFileChooser1.setDialogTitle("Add Type File");
            jFileChooser1.setFileFilter(Frame1.mainFrame1.TypeFilter);
            Integer in = new Integer(data_count);
            String index = in.toString();
            if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
              String filepath = jFileChooser1.getSelectedFile().getPath();
              String filename = jFileChooser1.getSelectedFile().getName();
              getDataModel().setValue("data.path" + index, filepath);
              getDataModel().setValue("data.name" + index, filename);
              getDataModel().setValue("datafilecounts", index);
              RemoveButton.setEnabled(true);
              data_count++;

              NodeInfo filend = new NodeInfo(filename, "Type File", new File(filepath));
              IconNode filenode = new IconNode(filend, "Type File");
              internalnode.add(filenode);
              getDataModel().setValue("internalnode", (IconNode)internalnode);

              listModel.addElement(filenode);
              jFileChooser1.setCurrentDirectory(jFileChooser1.getSelectedFile());
              Frame1.mainFrame1.path_forFileChooser = filepath;
            }
          }

          else if (source == RemoveButton) {
            removeFileList();
          }
          }
          public void removeFileList()
          {
            data_count--;
            Integer in = new Integer(data_count);
            String current_index = in.toString();

            int index = FileList.getSelectedIndex();
            getDataModel().removeValue("data.path"+index);
            getDataModel().removeValue("data.name"+index);
            getDataModel().setValue("datafilecounts", current_index);

            IconNode remove_node = (IconNode)FileList.getSelectedValue();
            if(remove_node.getIconName().compareTo("Parameter File")==0)
            {
              parameter_file = false;
              canMoveForward = false;
            }

            internalnode.remove(remove_node);
            getDataModel().setValue("internalnode", (IconNode)internalnode);
            listModel.remove(index);

            int size = listModel.getSize();
            if (size == 0) {
              RemoveButton.setEnabled(false);
            }
            else {
              if (index == listModel.getSize()) {
                index--;
              }
              FileList.setSelectedIndex(index);
              FileList.ensureIndexIsVisible(index);
            }
          }

          class DeleteKeyListener extends KeyAdapter {
            public void keyTyped(KeyEvent e) {
              if (e.getKeyChar() == KeyEvent.VK_DELETE) {
                removeFileList();
              }
            }
          }

          class PopupListener extends MouseAdapter {

            public void mousePressed(MouseEvent e) {
              maybeShowPopup(e);
            }

            public void mouseReleased(MouseEvent e) {
              maybeShowPopup(e);
            }

            public void mouseExited(MouseEvent e) {
              AddButton.setIcon(arrow_null);
            }

            private void maybeShowPopup(MouseEvent e) {
              AddButton.setIcon(arrow);
              if(parameter_file)
                jAddParameter.setEnabled(false);
              else
                jAddParameter.setEnabled(true);
              jAddPopupMenu.show(e.getComponent(), 80, 0);
            }
          }

          public static void Parse_Parameter_File2(NodeInfo parafilenode)
          {
            Vector assoc = new Vector();
            Vector freq = new Vector();
            Vector fcor = new Vector();
            Vector genibd = new Vector();
            Vector lodlink = new Vector();
            Vector lodpal = new Vector();
            Vector markerinfo = new Vector();
            Vector sibpal = new Vector();
            Vector sibpal_trait = new Vector();
            Vector sibpal_mean = new Vector();
            Vector segreg = new Vector();
            Vector reltest = new Vector();
            Vector tdtex = new Vector();
            Vector pedinfo = new Vector();
            Vector mlod = new Vector();
            Vector ageon = new Vector();
            Vector decipher = new Vector();

            Vector pedigree = new Vector();
            Vector markerblock = new Vector();
            Vector function = new Vector();

            File original_parafile = (File) parafilenode.file;
            parameter_file_info="";

            try {
              FileInputStream fin = new FileInputStream(original_parafile);
              InputStreamReader isr = new InputStreamReader(fin);
              BufferedReader in = new BufferedReader(isr);

              String strFileLine = new String();
              while ( (strFileLine = in.readLine()) != null) {
                strFileLine = strFileLine;
                parameter_file_info = parameter_file_info + strFileLine + "\n";
              }
              fin.close();
              isr.close();
              in.close();
            }
            catch (Exception ex) {
              ex.printStackTrace();
            }

            pedigree = Find_Match_Block2("pedigree");
            assoc = Find_Match_Block2("assoc");
            ageon = Find_Match_Block2("ageon");
            fcor = Find_Match_Block2("fcor");
            genibd = Find_Match_Block2("genibd");
            lodlink = Find_Match_Block2("lodlink");
            lodpal = Find_Match_Block2("lodpal");
            markerinfo = Find_Match_Block2("markerinfo");
            sibpal = Find_Match_Block2("sibpal");
            sibpal_trait = Find_OLDSIBPAL_Block("trait_regression");
            sibpal_mean = Find_OLDSIBPAL_Block("mean_test");
            segreg = Find_Match_Block2("segreg");
            freq = Find_Match_Block2("freq");
            reltest = Find_Match_Block2("reltest");
            tdtex = Find_Match_Block2("tdtex");
            pedinfo = Find_Match_Block2("pedinfo");
            mlod = Find_Match_Block2("mlod");
            decipher = Find_Match_Block2("decipher");
            markerblock = Find_Match_Block2("marker");
            function = Find_Match_Block2("function");

            StringBuffer master_information = new StringBuffer();

            if (pedigree.size() > 0) {
                for (int i = 0; i < pedigree.size(); i++) {
                    String block = (String) pedigree.get(i);
                     master_information.append(block);
                }
            }
            if (markerblock.size() > 0) {
                for (int i = 0; i < markerblock.size(); i++) {
                    String block = (String) markerblock.get(i);
                    master_information.append(block);
                }
            }
            if (function.size() > 0) {
                for (int i = 0; i < function.size(); i++) {
                    String block = (String) function.get(i);
                    master_information.append(block);
                }
            }

            parafilenode.infomodel.setValue("Assoc_array", assoc);
            parafilenode.infomodel.setValue("Ageon_array", ageon);
            parafilenode.infomodel.setValue("Freq_array", freq);
            parafilenode.infomodel.setValue("Fcor_array", fcor);
            parafilenode.infomodel.setValue("Genibd_array", genibd);
            parafilenode.infomodel.setValue("Lodlink_array", lodlink);
            parafilenode.infomodel.setValue("Lodpal_array", lodpal);
            parafilenode.infomodel.setValue("Markerinfo_array", markerinfo);
            parafilenode.infomodel.setValue("Sibpal_array", sibpal);
            parafilenode.infomodel.setValue("Sibpal_Trait_array", sibpal_trait);
            parafilenode.infomodel.setValue("Sibpal_Mean_array", sibpal_mean);
            parafilenode.infomodel.setValue("Segreg_array", segreg);
            parafilenode.infomodel.setValue("Reltest_array", reltest);
            parafilenode.infomodel.setValue("Tdtex_array", tdtex);
            parafilenode.infomodel.setValue("Pedinfo_array", pedinfo);
            parafilenode.infomodel.setValue("Mlod_array", mlod);
            parafilenode.infomodel.setValue("Decipher_array", decipher);
            parafilenode.infomodel.setValue("Pedigree_array", pedigree);
            parafilenode.infomodel.setValue("MarkerBlock_array", markerblock);
            parafilenode.infomodel.setValue("Function_array", function);
            parafilenode.infomodel.setValue("Master_Information", master_information);
          }

          public static Vector Find_Match_Block2(String matchstring) {

            boolean block = false;

            String temp_block = new String();
            Vector temp_vector = new Vector();

            String strFileLine_original = new String();
            String strFileLine_temp = new String();

            int block_count = 0;
            boolean block_count_start = false;

            int start_index = -1;
            int block_length=-1;

            String para_array[] = parameter_file_info.split("\n");

            for(int in=0;in<para_array.length;in++)
            {
              strFileLine_original = para_array[in];
              strFileLine_temp = strFileLine_original.toLowerCase().trim();

              if (strFileLine_temp.startsWith("#")) {
                if(!block)
                {
                  if(in+1 < para_array.length)
                  {
                    strFileLine_original = para_array[++in];
                    strFileLine_temp = strFileLine_original.toLowerCase().trim();
                  }
                }
              }

              if (strFileLine_temp.startsWith(matchstring)) {
                if (matchstring.compareTo("pedigree") == 0) {
                  if (strFileLine_temp.startsWith("pedigree_id") == false) {
                    block = true;
                    start_index = parameter_file_info.indexOf(matchstring);
                  }
                }
                if (matchstring.compareTo("marker") == 0) {

                  int index1 = strFileLine_temp.indexOf("=");
                  int index2 = strFileLine_temp.indexOf("#");

                  if(index1>=0)
                  {
                    if(index2>=0)
                    {
                      if (index1 > index2)
                        block = true;
                    }
                    else
                    {
                      start_index = parameter_file_info.indexOf(matchstring);
                      int brace_start = parameter_file_info.indexOf("{", start_index);
                      int brace_end = parameter_file_info.indexOf("}", start_index);

                      if (brace_end > brace_start)
                        block = true;
                    }
                  }
                  else
                    block = true;
                }
                else
                {
                  block = true;
                  start_index = parameter_file_info.indexOf(matchstring);
                }
              }

              if (block ){
                temp_block = temp_block + strFileLine_original + "\n";
                if (strFileLine_temp.indexOf("{") >= 0) {
                  block_count_start = true;
                  block_count++;
                }

                if (strFileLine_temp.indexOf("}") >= 0) {
                  block_count--;
                }

                if (block_count_start && block_count == 0) {
                  block = false;
                  block_count_start = false;
                  block_length=temp_block.length();
                  temp_vector.add(temp_block);
                  temp_block = "";

                  int end_index = start_index + block_length;
                  if(start_index >= 0 && end_index > start_index)
                  {
                    StringBuffer s = new StringBuffer(parameter_file_info);
                    s.delete(start_index, end_index);
                    parameter_file_info = s.toString();
                  }
                }
              }
            }

            return temp_vector;
          }

          public static Vector Find_OLDSIBPAL_Block(String matchstring) {

            boolean block = false;
            boolean block_start = false;
            boolean sub_block_start = false;

            String temp_block = new String();
            Vector temp_vector = new Vector();

            String strFileLine_original = new String();
            String strFileLine_temp = new String();

            int start_index = -1;
            int block_length=-1;

            String para_array[] = parameter_file_info.split("\n");

            for(int in=0;in<para_array.length;in++)
            {
              strFileLine_original = para_array[in];
              strFileLine_temp = strFileLine_original.toLowerCase().trim();

              if (strFileLine_temp.startsWith("#")) {
                if(!block)
                {
                  if(in+1 < para_array.length)
                  {
                    strFileLine_original = para_array[++in];
                    strFileLine_temp = strFileLine_original.toLowerCase().trim();
                  }
                }
              }

                if (strFileLine_temp.startsWith(matchstring)) {
                  block = true;
                  start_index = parameter_file_info.indexOf(matchstring);
                }

                if (block && strFileLine_temp.length() > 0) {
                  temp_block = temp_block + strFileLine_original + "\n";

                  if (strFileLine_temp.indexOf("{") >= 0) {
                    if (block_start) {
                      sub_block_start = true;
                    }
                    else
                      block_start = true;
                  }

                  if (strFileLine_temp.indexOf("}") >= 0) {
                    if (block_start) {
                      if (sub_block_start) {
                        sub_block_start = false;
                      }
                      else {
                        block_start = false;
                        block = false;
                        block_length=temp_block.length();
                        temp_vector.add(temp_block);
                        temp_block = "";

                        int end_index = start_index + block_length;

                        if(start_index >= 0 && end_index > start_index)
                        {
                          StringBuffer s = new StringBuffer(parameter_file_info);
                          s.delete(start_index, end_index);
                          parameter_file_info = s.toString();
                        }
                      }
                    }
                  }
                }

              }

            return temp_vector;
          }

          public static void Parse_Parameter_File3(NodeInfo parafilenode)
          {
            Vector Trait_Farray = new Vector();
            Vector Covariate_Farray = new Vector();
            Vector Phenotype_Farray = new Vector();

            Vector originalList = (Vector)parafilenode.infomodel.getValue("Function_array");

            Vector varList = FunctionDialog1.getFunctionVarList(originalList);

            for (Enumeration es = varList.elements(); es.hasMoreElements(); ) {
              Vector list = (Vector) es.nextElement();

              String vartype = list.get(2).toString();
              String varname = list.get(0).toString();

              if (vartype.compareTo("constant") != 0)
              {
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
            }
            parafilenode.infomodel.setValue("Trait_Farray", Trait_Farray);
            parafilenode.infomodel.setValue("Covariate_Farray", Covariate_Farray);
            parafilenode.infomodel.setValue("Phenotype_Farray", Phenotype_Farray);
          }

          public static void Parse_Parameter_File(NodeInfo parafilenode) throws Exception
          {
            parafilenode.setModel(new PropertyDataModel());

            Vector Covariate_array = new Vector();
            Vector Trait_array = new Vector();
            Vector Phenotype_array = new Vector();
            Vector Marker_array = new Vector();
            Vector Allele_array = new Vector();
            Vector TraitMarker_array = new Vector();
            Vector String_array = new Vector();

            Vector MList_Start_array = new Vector();
            Vector MList_End_array = new Vector();

            Vector CovList_Start_array = new Vector();
            Vector CovList_End_array = new Vector();

            File parafile = (File)parafilenode.file;

            String strFileLine = new String();
            boolean pedigree_block = false;

              FileInputStream fin = new FileInputStream(parafile);
              InputStreamReader isr = new InputStreamReader(fin, "utf-8");
              BufferedReader in = new BufferedReader(isr);

              while ((strFileLine = in.readLine()) != null) {
                  strFileLine = strFileLine.trim();
                  String strFileLine_lower = strFileLine.toLowerCase();

                  if (strFileLine_lower.startsWith("pedigree")) {
                      if (strFileLine_lower.startsWith("pedigree_id") == false) {
                          pedigree_block = true;
                      }
                  }

                  if (strFileLine.startsWith("#"))
                      continue;
                  if (pedigree_block && strFileLine.startsWith("}")) {
                      pedigree_block = false;
                      break;
                  }

                  if (pedigree_block) {
                    if(strFileLine.indexOf("#")>0)
                    {
                      strFileLine = strFileLine.substring(0, strFileLine.indexOf("#")).trim();
                    }

                      if(strFileLine.startsWith("marker_list")) {
                          if(strFileLine.indexOf("start") >= 0 && strFileLine.indexOf("end") >= 0)
                          {
                              StringTokenizer st = new StringTokenizer(strFileLine,",");
                              String start_name = new String();
                              String end_name = new String();

                              while (st.hasMoreTokens()) {
                                  String temp = st.nextToken();

                                  if (temp.indexOf("start") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          start_name = InputName[1].replaceAll("\"","").trim();
                                  }
                                  if (temp.indexOf("end") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          end_name = InputName[1].replaceAll("\"","").trim();
                                  }
                              }

                              if(start_name.length()>0 && end_name.length()>0)
                              {
                                  MList_Start_array.add(start_name);
                                  MList_End_array.add(end_name);
                              }
                          }
                      }

                      if (strFileLine.startsWith("marker") && !strFileLine.startsWith("marker_list")) {
                          boolean nameExist = false;
                          StringTokenizer st = new StringTokenizer(strFileLine, ",");

                          if (st.countTokens() == 1) {
                              String HeaderName[] = strFileLine.split("=");
                              if (HeaderName.length > 1)
                              {
                                  Marker_array.add(HeaderName[1].replaceAll("\"","").trim());
                              }
                          }

                          else {
                              String tempname[] = new String[st.countTokens()];
                              int i = 0;
                              while (st.hasMoreTokens()) {
                                  String temp = st.nextToken();
                                  tempname[i] = temp;
                                  if (temp.indexOf("name") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          Marker_array.add(InputName[1].replaceAll("\"","").trim());
                                      nameExist = true;
                                  }
                                  i++;
                              }
                              if (!nameExist) {
                                  String InputName[] = tempname[0].split("=");
                                  if (InputName.length > 1)
                                      Marker_array.add(InputName[1].replaceAll("\"","").trim());
                              }
                          }
                      }

                      if(strFileLine.startsWith("covariate_list")) {
                          if(strFileLine.indexOf("start") >= 0 && strFileLine.indexOf("end") >= 0)
                          {
                              StringTokenizer st = new StringTokenizer(strFileLine,",");
                              String start_name = new String();
                              String end_name = new String();

                              while (st.hasMoreTokens()) {
                                  String temp = st.nextToken();

                                  if (temp.indexOf("start") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          start_name = InputName[1].replaceAll("\"","").trim();
                                  }
                                  if (temp.indexOf("end") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          end_name = InputName[1].replaceAll("\"","").trim();
                                  }
                              }

                              if(start_name.length()>0 && end_name.length()>0)
                              {
                                  CovList_Start_array.add(start_name);
                                  CovList_End_array.add(end_name);
                              }
                          }
                      }

                      else if (strFileLine.startsWith("covariate")) {
                          boolean nameExist = false;
                          StringTokenizer st = new StringTokenizer(strFileLine, ",");

                          if (st.countTokens() == 1) {
                              String HeaderName[] = strFileLine.split("=");
                              if (HeaderName.length > 1)
                                  Covariate_array.add(HeaderName[1].replaceAll("\"","").trim());
                          }

                          else {
                              String tempname[] = new String[st.countTokens()];
                              int i = 0;
                              while (st.hasMoreTokens()) {
                                  String temp = st.nextToken();
                                  tempname[i] = temp;
                                  if (temp.indexOf("name") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          Covariate_array.add(InputName[1].replaceAll("\"","").trim());
                                      nameExist = true;
                                  }
                                  i++;
                              }
                              if (!nameExist) {
                                  String InputName[] = tempname[0].split("=");
                                  if (InputName.length > 1)
                                      Covariate_array.add(InputName[1].replaceAll("\"","").trim());
                              }
                          }
                      }

                      else if (strFileLine.startsWith("trait_marker")) {
                          boolean nameExist = false;
                          StringTokenizer st = new StringTokenizer(strFileLine, ",");

                          if (st.countTokens() == 1) {
                              String HeaderName[] = strFileLine.split("=");
                              if (HeaderName.length > 1)
                                  TraitMarker_array.add(HeaderName[1].replaceAll("\"","").trim());
                          }
                          else {
                             String tempname[] = new String[st.countTokens()];
                             int i = 0;
                             while (st.hasMoreTokens()) {
                                 String temp = st.nextToken();
                                 tempname[i] = temp;
                                 if (temp.indexOf("name") >= 0) {
                                     String InputName[] = temp.split("=");
                                     if (InputName.length > 1)
                                         TraitMarker_array.add(InputName[1].replaceAll("\"","").trim());
                                     nameExist = true;
                                 }
                                 i++;
                             }
                             if (!nameExist) {
                                 String InputName[] = tempname[0].split("=");
                                 if (InputName.length > 1)
                                     TraitMarker_array.add(InputName[1].replaceAll("\"","").trim());
                             }
                         }
                      }

                      else if (strFileLine.startsWith("trait")) {
                          boolean nameExist = false;
                          StringTokenizer st = new StringTokenizer(strFileLine, ",");

                          if (st.countTokens() == 1) {
                              String HeaderName[] = strFileLine.split("=");
                              if (HeaderName.length > 1)
                                  Trait_array.add(HeaderName[1].replaceAll("\"","").trim());
                          }

                          else {
                              String tempname[] = new String[st.countTokens()];
                              int i = 0;
                              while (st.hasMoreTokens()) {
                                  String temp = st.nextToken();
                                  tempname[i] = temp;
                                  if (temp.indexOf("name") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          Trait_array.add(InputName[1].replaceAll("\"","").trim());
                                      nameExist = true;
                                  }
                                  i++;
                              }
                              if (!nameExist) {
                                  String InputName[] = tempname[0].split("=");
                                  if (InputName.length > 1)
                                      Trait_array.add(InputName[1].replaceAll("\"","").trim());
                              }
                          }
                      }

                      else if (strFileLine.startsWith("phenotype")) {

                          boolean nameExist = false;
                          StringTokenizer st = new StringTokenizer(strFileLine, ",");

                          if (st.countTokens() == 1) {
                              String HeaderName[] = strFileLine.split("=");
                              if (HeaderName.length > 1)
                                  Phenotype_array.add(HeaderName[1].replaceAll("\"","").trim());
                          }

                          else {
                              String tempname[] = new String[st.countTokens()];
                              int i = 0;
                              while (st.hasMoreTokens()) {
                                  String temp = st.nextToken();
                                  tempname[i] = temp;
                                  if (temp.indexOf("name") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          Phenotype_array.add(InputName[1].replaceAll("\"","").trim());
                                      nameExist = true;
                                  }
                                  i++;
                              }
                              if (!nameExist) {
                                  String InputName[] = tempname[0].split("=");
                                  if (InputName.length > 1)
                                      Phenotype_array.add(InputName[1].replaceAll("\"","").trim());
                              }
                          }
                      }

                      else if (strFileLine.startsWith("allele")) {
                          boolean nameExist = false;
                          StringTokenizer st = new StringTokenizer(strFileLine, ",");

                          if (st.countTokens() == 1) {
                              String HeaderName[] = strFileLine.split("=");
                              if (HeaderName.length > 1)
                                  Allele_array.add(HeaderName[1].replaceAll("\"","").trim());
                          }

                          else {
                              String tempname[] = new String[st.countTokens()];
                              int i = 0;
                              while (st.hasMoreTokens()) {
                                  String temp = st.nextToken();
                                  tempname[i] = temp;
                                  if (temp.indexOf("name") >= 0) {
                                      String InputName[] = temp.split("=");
                                      if (InputName.length > 1)
                                          Allele_array.add(InputName[1].replaceAll("\"","").trim());
                                      nameExist = true;
                                  }
                                  i++;
                              }
                              if (!nameExist) {
                                  String InputName[] = tempname[0].split("=");
                                  if (InputName.length > 1)
                                      Allele_array.add(InputName[1].replaceAll("\"","").trim());
                              }
                          }
                      }

                      else if (strFileLine.startsWith("string")) {
                          String HeaderName[] = strFileLine.split("=");
                          if (HeaderName.length > 1)
                              String_array.add(HeaderName[1].replaceAll("\"","").trim());
                      }

                      else if (strFileLine.startsWith("pedigree_id")) {
                          String pid[] = strFileLine.split("=");
                          if (pid.length > 1) {
                              pid[1] = pid[1].replaceAll("\"", "").trim();
                              parafilenode.infomodel.setValue("pedigree_id_name", pid[1]);
                          }
                      }

                      else if (strFileLine.startsWith("delimiters")) {
                          String deli[] = strFileLine.split("=");
                          if (deli.length > 1) {
                              deli[1] = deli[1].trim().replaceAll("\"", "");
                              parafilenode.infomodel.setValue("delimiters_name", deli[1]);
                          }
                      }

                      else if (strFileLine.startsWith("delimiter_mode")) {
                          String deli[] = strFileLine.split("=");
                          if (deli.length > 1) {
                              deli[1] = deli[1].replaceAll("\"", "").trim();
                              parafilenode.infomodel.setValue("delimiter_mode", deli[1]);
                          }
                      }

                      else if (strFileLine.startsWith("format")) {
                          String format[] = strFileLine.split("=");
                          if (format.length > 1) {
                              format[1] = format[1].replaceAll("\"", "");
                              parafilenode.infomodel.setValue("format_name", format[1]);
                          }
                      }

                      else if (strFileLine.startsWith("sex_code")) {
                                  Trait_array.add("SEX_CODE");
                      }

                      else if (strFileLine.startsWith("sex_field")) {
                           if (strFileLine_lower.startsWith("sex_code") == false)
                           {
                               String sex[] = strFileLine.split("=");
                               if (sex.length > 1) {
                                   sex[1] = sex[1].replaceAll("\"", "").trim();
                                   Trait_array.add(sex[1]);
                               }
                           }
                      }
                  } else {
                      continue;
                  }

              }
              fin.close();
              isr.close();
              in.close();

            Vector new_Allele_array = Clear_Allele_array(Allele_array);

            parafilenode.infomodel.setValue("Covariate_array", Covariate_array);
            parafilenode.infomodel.setValue("Trait_array", Trait_array);
            parafilenode.infomodel.setValue("Phenotype_array", Phenotype_array);
            parafilenode.infomodel.setValue("Marker_array", Marker_array);
            parafilenode.infomodel.setValue("Allele_array", new_Allele_array);
            parafilenode.infomodel.setValue("TraitMarker_array", TraitMarker_array);
            parafilenode.infomodel.setValue("String_array",String_array);

            parafilenode.infomodel.setValue("MList_Start_array",MList_Start_array);
            parafilenode.infomodel.setValue("MList_End_array",MList_End_array);

            parafilenode.infomodel.setValue("CovList_Start_array",CovList_Start_array);
            parafilenode.infomodel.setValue("CovList_End_array",CovList_End_array);
          }

          public static Vector Clear_Allele_array(Vector in_array)
          {
              List sortedlist = in_array.subList(0, in_array.size());
              Collections.sort(sortedlist);

              Vector newAllele_array = new Vector();
              String strFileLine = new String();
              String strFileNextLine = new String();

              for (int i = 0; i < sortedlist.size(); i++) {
                  strFileLine = sortedlist.get(i).toString();
                  if (strFileLine.compareTo(strFileNextLine) != 0) {
                      newAllele_array.add(strFileLine);
                  }
                  strFileNextLine = strFileLine;
              }
              return newAllele_array;
          }

          public static void Parse_IBD_File(NodeInfo ibdfilenode)
          {
            ibdfilenode.setModel(new PropertyDataModel());

            Vector Marker_array = new Vector();
            Vector temp_marker_array = new Vector();

            File ibdfile = (File)ibdfilenode.file;
            String strFileLine = new String();

            try{
              FileInputStream fin = new FileInputStream(ibdfile);
              InputStreamReader isr = new InputStreamReader(fin);
              BufferedReader in = new BufferedReader(isr);

              boolean markerlist = false;

              while((strFileLine = in.readLine()) != null)
              {
                if (strFileLine.length()<=0 && markerlist)
                  break;

                if (strFileLine.compareTo("MARKERS")==0) {
                  markerlist = true;
                  in.readLine();
                  strFileLine = in.readLine();
                }
                if(markerlist)
                {
                    if(strFileLine.indexOf(", x_linked")>0)
                        strFileLine = strFileLine.substring(0, strFileLine.indexOf(", x_linked"));
                    temp_marker_array.add(strFileLine);
                }
              }

              fin.close();
              isr.close();
              in.close();

            }catch(Exception e)
            {
              e.printStackTrace();
            }

            for(int i=0;i<temp_marker_array.size();i++)
            {
                String each_line = temp_marker_array.get(i).toString();

                String each_token[] = each_line.split(" ");

                if(each_token.length==1) //only marker name
                {
                    Marker_array.add(each_token[0]);
                }
                else if(each_token.length==2)//marker name & distance ex)regon1 2.0
                {
                    Marker_array.add(each_token[0]);
                }
                else if (each_token.length>2)//there is spaces within marker name ex)regon 1 1 2.0
                {
                    int last_space = each_line.lastIndexOf(" ");
                    String sub_string = each_line.substring(0, last_space);
                    Marker_array.add(sub_string);
                }
            }

            ibdfilenode.infomodel.setValue("Marker_List", Marker_array);
          }

          public static void Parse_Genome_File(NodeInfo genomefilenode)
          {
            genomefilenode.setModel(new PropertyDataModel());

            Vector Region_array = new Vector();

            File parafile = (File)genomefilenode.file;

            String strFileLine = new String();

            try{
              FileInputStream fin = new FileInputStream(parafile);
              InputStreamReader isr = new InputStreamReader(fin);
              BufferedReader in = new BufferedReader(isr);

              while((strFileLine = in.readLine()) != null)
              {
                strFileLine = strFileLine.trim();

                if(strFileLine.startsWith("{"))
                {
                    strFileLine = strFileLine.substring(1, strFileLine.length()).trim();
                }
                if(strFileLine.startsWith("}"))
                {
                    strFileLine = strFileLine.substring(1, strFileLine.length()).trim();
                }

                if (strFileLine.startsWith("region")) {
                  StringTokenizer st = new StringTokenizer(strFileLine, "=");
                  if (st.countTokens() == 2) {
                    st.nextToken();
                    String region_name = st.nextToken();

                    if(region_name.indexOf("{")>0)
                    {
                        region_name = region_name.substring(0,region_name.indexOf("{")).trim();
                    }

                    StringTokenizer st2 = new StringTokenizer(region_name, ",");
                    if(st2.countTokens() == 2)
                    {
                      String region_name_result = st2.nextToken().replaceAll("\"","").trim();
                      Region_array.add(region_name_result);
                    }
                    else if(st2.countTokens() == 1)
                    {
                      String region_name_result = st2.nextToken().replaceAll("\"","").trim();
                      Region_array.add(region_name_result);
                    }
                  }
                }
              }

            }catch(Exception e)
            {
              e.printStackTrace();
            }
            genomefilenode.infomodel.setValue("Region_List", Region_array);
          }

}
