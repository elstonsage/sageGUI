package sage;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.*;
import java.net.URL;

public class TreeTransferHandler extends StringTransferHandler {
    IconNode childnode;
    Frame1 frame;
    MyInternalFrame inframe;
    MyTree tree;
    ImageIcon notefile;

    JFileChooser jFileChooser1 = new JFileChooser();

    TreeTransferHandler(Frame1 parent, MyInternalFrame in)
    {
      this.frame = parent;
      this.inframe = in;

      jFileChooser1.setCurrentDirectory(inframe.projectFile);
      frame.path_forFileChooser = inframe.projectPath;

      try{
          notefile = new ImageIcon(TreeTransferHandler.class.getResource("File.PNG"));
      }catch(Exception ex)
      {
          ex.printStackTrace();
      }
    }

    protected NodeInfo exportString(JComponent c) {
      tree = (MyTree)c;
      TreePath selectpath = tree.getSelectionPath();
      IconNode current = (IconNode)selectpath.getLastPathComponent();
      NodeInfo node = (NodeInfo)current.getUserObject();

      if(node.file instanceof File)
      {
        return node;
      }
      else
      {
        return null;
      }
    }

    protected void importString(JComponent c, NodeInfo ob) {
      MyTree tree = (MyTree)c;
      TreePath selectpath = tree.getSelectionPath();
      IconNode parent = (IconNode)selectpath.getLastPathComponent();
      NodeInfo parent_nodeinfo = (NodeInfo)parent.getUserObject();
      String parent_nodetype = parent_nodeinfo.nodetype;

      NodeInfo importnode = (NodeInfo)ob;
      String str = importnode.analysis_type;
      File importFile = (File)importnode.file;

      if (importFile instanceof File) {

        if (parent_nodetype.startsWith("ErrorPedigreeNode"))
        {
          if (importnode.nodetype.compareTo("Pedigree File") != 0)
          {
            return;
          }
          else
          {
            TreePath analysispath = selectpath.getParentPath().getParentPath();
            IconNode analysis = (IconNode) analysispath.getLastPathComponent();
            NodeInfo info = (NodeInfo) analysis.getUserObject();

            SageFilePanel test = (SageFilePanel) info.component_vector.get(0);
            test.insertpedigreefile(importnode);
          }
        }
        else if (parent_nodetype.startsWith("ErrorLocusNode"))
        {
          if (importnode.nodetype.indexOf("Marker Locus File") != 0)
          {
            return;
          }
          else
          {
            TreePath analysispath = selectpath.getParentPath().getParentPath();
            IconNode analysis = (IconNode) analysispath.getLastPathComponent();
            NodeInfo info = (NodeInfo) analysis.getUserObject();

            SageFilePanel test = (SageFilePanel) info.component_vector.get(0);
            test.insertlocusfile(importnode);
          }

        }
        else if(parent_nodetype.startsWith("ErrorTraitNode"))
        {
          if (importnode.nodetype.compareTo("Trait File") !=0)
          {
            return;
          }
          else
          {
            TreePath analysispath = selectpath.getParentPath().getParentPath();
            IconNode analysis = (IconNode) analysispath.getLastPathComponent();
            NodeInfo info = (NodeInfo) analysis.getUserObject();

            SageFilePanel test = (SageFilePanel) info.component_vector.get(0);
            test.inserttraitfile(importnode);
          }
        }
        else if (parent_nodetype.startsWith("ErrorIBDNode"))
        {
          if (importnode.nodetype.compareTo("IBD Sharing File") != 0)
          {
            return;
          }
          else
          {
            TreePath analysispath = selectpath.getParentPath().getParentPath();
            IconNode analysis = (IconNode)analysispath.getLastPathComponent();
            NodeInfo info = (NodeInfo) analysis.getUserObject();

            SageFilePanel test = (SageFilePanel) info.component_vector.get(0);
            test.insertibdfile(importnode);
          }
        }
        else if (parent_nodetype.startsWith("ErrorGenomeNode"))
        {
          if (importnode.nodetype.compareTo("Genome File") != 0)
          {
            return;
          }
          else
          {
            TreePath analysispath = selectpath.getParentPath().getParentPath();
            IconNode analysis = (IconNode) analysispath.getLastPathComponent();
            NodeInfo info = (NodeInfo) analysis.getUserObject();

            SageFilePanel test = (SageFilePanel) info.component_vector.get(0);
            test.insertgenomefile(importnode);
          }
        }
        else if (parent_nodetype.compareTo("Analysis") ==0 || parent_nodetype.compareTo("InputFolder")==0)
        {
          if (importnode.nodetype.compareTo("Pedigree File") == 0)
          {
            SageFilePanel test = (SageFilePanel) parent_nodeinfo.component_vector.get(0);

            if(test.Analysis_object.create_family_file_node==false)
              test.insertpedigreefile(importnode);
          }
          if (importnode.nodetype.compareTo("Marker Locus File") == 0)
          {
            SageFilePanel filepanel = (SageFilePanel)parent_nodeinfo.component_vector.get(0);
            filepanel.insertlocusfile(importnode);
          }

          if (importnode.nodetype.compareTo("Trait File") == 0)
          {
            SageFilePanel test = (SageFilePanel) parent_nodeinfo.component_vector.get(0);
            test.inserttraitfile(importnode);
          }

          if (importnode.nodetype.compareTo("IBD Sharing File") == 0)
          {
            SageFilePanel test = (SageFilePanel) parent_nodeinfo.component_vector.get(0);
            test.insertibdfile(importnode);
          }

          if (importnode.nodetype.compareTo("Genome File") == 0)
          {
            SageFilePanel test = (SageFilePanel) parent_nodeinfo.component_vector.get(0);
            test.insertgenomefile(importnode);
          }

          if (importnode.nodetype.compareTo("Type File") == 0)
          {
            SageFilePanel test = (SageFilePanel) parent_nodeinfo.component_vector.get(0);
            test.inserttypefile(importnode);
          }
        }

        else
          return;
      }

      else
      {
        if (str.indexOf("File")!=-1)
        {
          if (parent_nodetype.startsWith("ErrorParameterNode"))
          {
            if(str.compareTo("Parameter File")!=0)
            {
              return;
            }
            else
            {
              TreePath analysispath = selectpath.getParentPath().getParentPath();
              IconNode analysis = (IconNode) analysispath.getLastPathComponent();
              NodeInfo info = (NodeInfo) analysis.getUserObject();
              String a_name = info.analysis_type;

              AddNewParameterFile(a_name, info);
            }
          }
          else if (parent_nodetype.startsWith("ErrorPedigreeNode"))
          {
            if (str.compareTo("Pedigree File") != 0)
            {
              return;
            }
            else
            {
              TreePath analysispath = selectpath.getParentPath().getParentPath();
              IconNode analysis = (IconNode) analysispath.getLastPathComponent();
              NodeInfo info = (NodeInfo) analysis.getUserObject();
              String a_name = info.analysis_type;

              AddNewPedigreeFile(a_name, info);
            }
          }
          else if (parent_nodetype.startsWith("ErrorLocusNode"))
          {
            if (str.compareTo("Marker Locus File") != 0)
            {
              return;
            }
            else
            {
              TreePath analysispath = selectpath.getParentPath().getParentPath();
              IconNode analysis = (IconNode) analysispath.getLastPathComponent();
              NodeInfo info = (NodeInfo) analysis.getUserObject();
              String a_name = info.analysis_type;

              AddNewLocusFile(a_name, info);
            }

          }
          else if (parent_nodetype.startsWith("ErrorIBDNode"))
          {
            if (str.compareTo("IBD Sharing File") != 0)
            {
              return;
            }
            else
            {
              TreePath analysispath = selectpath.getParentPath().getParentPath();
              IconNode analysis = (IconNode)analysispath.getLastPathComponent();
              NodeInfo info = (NodeInfo) analysis.getUserObject();
              if (info.analysis_type.compareTo("LODPAL") == 0) {
                LODPAL1 test = (LODPAL1) info.component_vector.get(0);
                test.jButton3_actionPerformed();
              }
              else if (info.analysis_type.compareTo("SIBPAL") == 0) {
                SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
                test.jButton3_actionPerformed();
              }
            }
          }
          else if (parent_nodetype.startsWith("ErrorGenomeNode"))
          {
            if (str.compareTo("Genome File") != 0)
            {
              return;
            }
            else
            {
              TreePath analysispath = selectpath.getParentPath().getParentPath();
              IconNode analysis = (IconNode) analysispath.getLastPathComponent();
              NodeInfo info = (NodeInfo) analysis.getUserObject();
              if (info.analysis_type.compareTo("RELTEST") == 0) {
                RELTEST1 test = (RELTEST1) info.component_vector.get(0);
                test.jButton4_actionPerformed();
              }
              else if (info.analysis_type.compareTo("MLOD") == 0) {
                MLOD1 test = (MLOD1) info.component_vector.get(0);
                test.jButton5_actionPerformed();
              }
              else if (info.analysis_type.compareTo("GENIBD") == 0) {
                GENIBD1 test = (GENIBD1) info.component_vector.get(0);
                test.jButton4_actionPerformed();
              }
              else if (info.analysis_type.compareTo("DECIPHER") == 0) {
                DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
                test.jButton4_actionPerformed();
              }
            }
          }
          else if(parent_nodetype.startsWith("Internal"))
          {
            jFileChooser1.setDialogTitle("Add "+str);

            if(str.compareTo("Pedigree File")==0)
            {
              jFileChooser1.setFileFilter(frame.FamilyFilter);
            }
            if(str.compareTo("Marker Locus File")==0)
            {
              jFileChooser1.setFileFilter(frame.MarkerLocusFilter);
            }
            if(str.compareTo("Trait File")==0)
            {
              jFileChooser1.setFileFilter(frame.TraitLocusFilter);
            }
            if(str.compareTo("Genome File")==0)
            {
              jFileChooser1.setFileFilter(frame.GenomeFilter);
            }
            if(str.compareTo("IBD Sharing File")==0)
            {
              jFileChooser1.setFileFilter(frame.IBDFilter);
            }
            if(str.compareTo("Type File")==0)
            {
              jFileChooser1.setFileFilter(frame.TypeFilter);
            }

            if (jFileChooser1.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
              String filepath = jFileChooser1.getSelectedFile().getPath();
              String filename = jFileChooser1.getSelectedFile().getName();

              NodeInfo filend = null;
              IconNode filenode = null;

              if(str.compareTo("Pedigree File")==0)
              {
                filend = new NodeInfo(filename, "Pedigree File",new File(filepath));
                filenode = new IconNode(filend, "Pedigree File");
              }
              if(str.compareTo("Marker Locus File")==0)
              {
                filend = new NodeInfo(filename, "Marker Locus File", new File(filepath));
                filenode = new IconNode(filend,"Marker Locus File");
              }
              if(str.compareTo("Trait File")==0)
              {
                filend = new NodeInfo(filename, "Trait File", new File(filepath));
                filenode = new IconNode(filend,"Trait File");
              }
              if(str.compareTo("Genome File")==0)
              {
                filend = new NodeInfo(filename, "Genome File", new File(filepath));
                filenode = new IconNode(filend, "Genome File");
                FavoritesPanel.Parse_Genome_File(filend);
              }
              if(str.compareTo("IBD Sharing File")==0)
              {
                filend = new NodeInfo(filename, "IBD Sharing File", new File(filepath));
                filenode = new IconNode(filend, "IBD Sharing File");
                FavoritesPanel.Parse_IBD_File(filend);
              }
              if(str.compareTo("Type File")==0)
              {
                filend = new NodeInfo(filename, "Type File", new File(filepath));
                filenode = new IconNode(filend, "Type File");
              }

              if(filenode != null)
              {
                  inframe.internalnode.add(filenode);

                  jFileChooser1.setCurrentDirectory(jFileChooser1.getSelectedFile());
                  frame.path_forFileChooser = filepath;

                  inframe.treeModel.nodeStructureChanged(inframe.internalnode);
              }
            }
          }
          else
            return;
        }

        // for note icon
        else if(str.compareTo("Note")==0)
        {
          if (parent_nodetype.startsWith("Analysis"))
          {
            String parentpath = inframe.projectPath;
            NodeInfo parentnode = (NodeInfo)parent.getUserObject();
            String filename = parentnode.toString();

            String new_file_name = new String();
            JTextField region_name = new JTextField(filename+"_note.txt");

            Object[] message = new Object[2];
            message[0] = "Please enter the note file name.";
            message[1] = region_name;

            int input = JOptionPane.showOptionDialog(
                inframe,
                message,
                "Set File name",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                notefile,
                null,
                null);

            if(input==0 && (region_name.getText().trim().length()>0))
            {
              new_file_name = region_name.getText().trim();
            }
            else
              return;

            MakeNoteFile(parent, new_file_name);
          }
          else
            return;
        }

        //for analysis
        else
        {
          StringBuffer master_information = new StringBuffer();
          if (inframe.parameterfilenode != null)
          {
            IconNode Parameter_node = inframe.parameterfilenode;


            NodeInfo paranode = (NodeInfo) Parameter_node.getUserObject();
            DataCollectionModel dm = (DataCollectionModel) paranode.infomodel;

            if (dm.hasValue("Pedigree_array")) {
              Vector pedigree_array = (Vector) dm.getValue("Pedigree_array");
              for (Enumeration e = pedigree_array.elements(); e.hasMoreElements(); ) {
                String block = (String) e.nextElement();
                master_information.append(block);
              }
            }
            if (dm.hasValue("Function_array")) {
              Vector function_array = (Vector) dm.getValue("Function_array");
              for (Enumeration e = function_array.elements(); e.hasMoreElements(); ) {
                String block = (String) e.nextElement();
                master_information.append(block);
              }
            }
            if (dm.hasValue("MarkerBlock_array")) {
              Vector marker_array = (Vector) dm.getValue("MarkerBlock_array");
              for (Enumeration e = marker_array.elements(); e.hasMoreElements(); ) {
                String block = (String) e.nextElement();
                master_information.append(block);
              }
            }
          }
          if(str.compareTo("FREQ")==0)
          {
              Frame1.mainFrame1.jMenuFREQ_actionPerformed();
          }

          else if (str.compareTo("GENIBD") == 0)
          {
              Frame1.mainFrame1.jMenuGENIBD_actionPerformed();
          }

          else if (str.compareTo("LODPAL") == 0)
          {
              Frame1.mainFrame1.jMenuLODPAL_actionPerformed();
          }

          else if (str.compareTo("PEDINFO") == 0)
          {
              Frame1.mainFrame1.jMenuPEDINFO_actionPerformed();
          }

          else if (str.compareTo("MARKERINFO") == 0)
          {
              Frame1.mainFrame1.jMenuMARKERINFO_actionPerformed();
          }

          else if(str.compareTo("ASSOC")==0)
          {
              Frame1.mainFrame1.jMenuASSOC_actionPerformed();
          }

          else if(str.compareTo("AGEON")==0)
          {
              Frame1.mainFrame1.jMenuAGEON_actionPerformed();
          }

          else if(str.compareTo("RELTEST")==0)
          {
              Frame1.mainFrame1.jMenuRELTEST_actionPerformed();
          }

          else if (str.compareTo("FCOR")== 0)
          {
              Frame1.mainFrame1.jMenuFCOR_actionPerformed();
          }

          else if(str.compareTo("LODLINK")==0)
          {
              Frame1.mainFrame1.jMenuLODLINK_actionPerformed();
          }

          else if (str.compareTo("TDTEX")== 0)
          {
              Frame1.mainFrame1.jMenuTDTEX_actionPerformed();
          }

          else if(str.compareTo("MLOD")==0)
          {
              Frame1.mainFrame1.jMenuMLOD_actionPerformed();
          }

          else if(str.compareTo("SIBPAL")==0)
          {
              Frame1.mainFrame1.jMenuSIBPAL_actionPerformed();
          }

          else if(str.compareTo("SEGREG")==0)
          {
              Frame1.mainFrame1.jMenuSEGREG_actionPerformed();
          }

          else if(str.compareTo("DECIPHER")==0)
          {
              Frame1.mainFrame1.jMenuDECIPHER_actionPerformed();
          }
          else if(str.compareTo("RELPAL")==0)
          {
              Frame1.mainFrame1.jMenuRELPAL_actionPerformed();
          }
        }
      }

      tree.Refresh();
    }

    void AddNewParameterFile(String name, NodeInfo info)
    {
      if (name.compareTo("FREQ") == 0) {
        FREQ1 test = (FREQ1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("GENIBD") == 0) {
        GENIBD1 test = (GENIBD1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("LODPAL") == 0) {
        LODPAL1 test = (LODPAL1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("PEDINFO") == 0) {
        PEDINFO1 test = (PEDINFO1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("MARKERINFO") == 0) {
        MARKERINFO1 test = (MARKERINFO1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }

      else if (name.compareTo("ASSOC") == 0) {
        ASSOC1 test = (ASSOC1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("RELTEST") == 0) {
        RELTEST1 test = (RELTEST1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("FCOR") == 0) {
        FCOR1 test = (FCOR1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("LODLINK") == 0) {
        LODLINK1 test = (LODLINK1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("TDTEX") == 0) {
        TDTEX1 test = (TDTEX1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("MLOD") == 0) {
        MLOD1 test = (MLOD1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("SIBPAL") == 0) {
        SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("SEGREG") == 0) {
        SEGREG1 test = (SEGREG1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("AGEON") == 0) {
        AGEON1 test = (AGEON1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
      else if (name.compareTo("DECIPHER") == 0) {
        DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
        test.jButton1_actionPerformed();
      }
    }

    void AddNewPedigreeFile(String name,  NodeInfo info)
    {
      if (name.compareTo("FREQ") == 0) {
        FREQ1 test = (FREQ1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("GENIBD") == 0) {
        GENIBD1 test = (GENIBD1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("LODPAL") == 0) {
        LODPAL1 test = (LODPAL1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("PEDINFO") == 0) {
        PEDINFO1 test = (PEDINFO1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("MARKERINFO") == 0) {
        MARKERINFO1 test = (MARKERINFO1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("ASSOC") == 0) {
        ASSOC1 test = (ASSOC1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("AGEON") == 0) {
        AGEON1 test = (AGEON1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("RELTEST") == 0) {
        RELTEST1 test = (RELTEST1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("FCOR") == 0) {
        FCOR1 test = (FCOR1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("LODLINK") == 0) {
        LODLINK1 test = (LODLINK1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("TDTEX") == 0) {
        TDTEX1 test = (TDTEX1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("MLOD") == 0) {
        MLOD1 test = (MLOD1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("SIBPAL") == 0) {
        SIBPAL1 test = (SIBPAL1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("SEGREG") == 0) {
        SEGREG1 test = (SEGREG1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
      else if (name.compareTo("DECIPHER") == 0) {
        DECIPHER1 test = (DECIPHER1) info.component_vector.get(0);
        test.jButton2_actionPerformed();
      }
    }

    void AddNewLocusFile(String name, NodeInfo info)
    {
      if (name.compareTo("GENIBD") == 0) {
        GENIBD1 test = (GENIBD1) info.component_vector.get(0);
        test.jButton3_actionPerformed();
      }
      else if (name.compareTo("RELTEST") == 0) {
        RELTEST1 test = (RELTEST1) info.component_vector.get(0);
        test.jButton3_actionPerformed();
      }
      else if (name.compareTo("LODLINK") == 0) {
        LODLINK1 test = (LODLINK1) info.component_vector.get(0);
        test.jButton3_actionPerformed();
      }
      else if (name.compareTo("MLOD") == 0) {
        MLOD1 test = (MLOD1) info.component_vector.get(0);
        test.jButton3_actionPerformed();
      }
    }


    void MakeNoteFile(IconNode parentanalysis, String new_filename)
    {
      String parentpath = inframe.projectPath;

      File notefile = new File(parentpath+System.getProperty("file.separator")+new_filename);
      try{
        notefile.createNewFile();
      }catch(Exception es)
      {
        es.printStackTrace();
      }
      NodeInfo nodeinfo = new NodeInfo(notefile.getName(), "Note File", notefile);
      IconNode notenode = new IconNode(nodeinfo, "Note File");
      inframe.treeModel.insertNodeInto(notenode, parentanalysis, 0);
    }

}

