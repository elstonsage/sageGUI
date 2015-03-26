package sage;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import java.io.*;

public class ResultPanel extends WizardPanel
{
         protected JLabel pdir = new JLabel();
         protected JLabel pname = new JLabel();
         protected JTextArea FileList= new JTextArea();
         JScrollPane jScrollPane1 = new JScrollPane();


         public ResultPanel()
         {
                 super("Project Information", "Check your project information.");

                 Font f = new java.awt.Font("SansSerif",1,11);

                 JPanel total = new JPanel();
                 total.setLayout(new BorderLayout());
                 total.setBorder(BorderFactory.createLoweredBevelBorder());

                 JPanel CenterPanel = new JPanel();
                 CenterPanel.setLayout(new BorderLayout(5,5));

                 JPanel TopPanel = new JPanel();
                 JPanel TopPanel_sub1 = new JPanel();
                 JPanel TopPanel_sub2 = new JPanel();

                 JLabel jLabel1 = new JLabel("Source files");
                 jLabel1.setFont(f);

                 FileList.setBackground(new Color(236, 233, 216));
                 FileList.setFont(new java.awt.Font("SansSerif", 0, 11));
                 JLabel jLabel2 = new JLabel("Project name");
                 jLabel2.setFont(f);
                 jLabel2.setPreferredSize(new Dimension(100, 15));

                 JLabel jLabel3 = new JLabel("Project directory");
                 jLabel3.setFont(f);
                 jLabel3.setPreferredSize(new Dimension(100, 15));

                 TopPanel_sub1.setPreferredSize(new Dimension(360, 20));
                 TopPanel_sub1.setLayout(new BorderLayout());
                 TopPanel_sub2.setPreferredSize(new Dimension(360, 20));
                 TopPanel_sub2.setLayout(new BorderLayout());

                 TopPanel.setLayout(new BorderLayout(5,5));
                 TopPanel.setBorder(new EmptyBorder(5,8,5,8)); //top, left, bottom, right
                 TopPanel.add(TopPanel_sub1, BorderLayout.NORTH);
                 TopPanel.add(TopPanel_sub2, BorderLayout.CENTER);

                 TopPanel_sub1.add(jLabel2, BorderLayout.WEST);
                 TopPanel_sub1.add(pname, BorderLayout.CENTER);
                 TopPanel_sub2.add(jLabel3, BorderLayout.WEST);
                 TopPanel_sub2.add(pdir, BorderLayout.CENTER);

                 CenterPanel.setPreferredSize(new Dimension(360, 200));
                 CenterPanel.setBorder(new EmptyBorder(5,8,8,8));
                 CenterPanel.add(jLabel1, BorderLayout.NORTH);
                 CenterPanel.add(jScrollPane1, BorderLayout.CENTER);
                 jScrollPane1.getViewport().add(FileList, null);
                 jScrollPane1.setBorder(new EmptyBorder(0,0,0,0));


                 total.add(CenterPanel, BorderLayout.CENTER);
                 total.add(TopPanel, BorderLayout.NORTH);

                 JPanel center = new JPanel();
                 center.setLayout(new UnitLayout());
                 center.add(total);
                 add("Center", center);

                 canMoveForward = true;
         }

         public void setInformation()
         {
           FileList.setText("");
           DataCollectionModel dm = (DataCollectionModel)((JWizard)((JPanel)getParent()).getParent()).getModel();

           String projectname = (String)dm.getValue("info.pName");
           String projectdir = (String)dm.getValue("info.DirPath");

           pname.setText(projectname);
           pdir.setText(projectdir);

           if(JWizardProject.wizard.nav.internal_pedilist.size()>0)
           {
             IconNode externalnode =  (IconNode)dm.getValue("externalnode");

             for(int i=0; i< externalnode.getChildCount();i++)
             {
               IconNode child = (IconNode)externalnode.getChildAt(i);
               NodeInfo child_nodeinfo = (NodeInfo)child.getUserObject();
               File child_file = child_nodeinfo.file;
               String child_filepath = child_file.getPath();
               FileList.append(child_filepath + "\n");
             }
           }

           else
           {
             if (dm.hasValue("datafilecounts")) {
               int fcount = (int) dm.getValue("datafilecounts").hashCode();
               int result = fcount - 48;

               for (int i = 0; i <= result; i++) {
                 Integer in = new Integer(i);
                 String index = in.toString();
                 String file = (String) dm.getValue("data.path" + index);

                 if(file!=null)
                   FileList.append(file + "\n");
               }
             }
           }
         }

}
