package sage;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.*;
import javax.swing.*;

public class SelectJobPanel extends WizardPanel
{
        JRadioButton jRadioButton1 = new JRadioButton();
        JRadioButton jRadioButton2 = new JRadioButton();
        JRadioButton jRadioButton3 = new JRadioButton();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel3 = new JLabel();
        ButtonGroup bp = new ButtonGroup();

        public SelectJobPanel()
        {
             super("S.A.G.E. Project", "Select your project. What do you want to do?");

             jRadioButton1.setFocusPainted(false);
             jRadioButton2.setFocusPainted(false);
             jRadioButton3.setFocusPainted(false);

             jRadioButton1.setText("I am creating a new S.A.G.E. project from scratch.");
             jLabel1.setText("I may not have all pedigree data field required by S.A.G.E.");

             jRadioButton2.setText("I have all pedigree data required by S.A.G.E.");
             jLabel2.setText("but no parameter file.");

             jRadioButton3.setText("I have pedigree data in S.A.G.E.-ready format");
             jLabel3.setText("and one or more parameter files.");

             jLabel1.setBorder(new EmptyBorder(2,20,14,5));
             jLabel2.setBorder(new EmptyBorder(2,20,14,5));
             jLabel3.setBorder(new EmptyBorder(2,20,14,5));

             bp.add(jRadioButton1);
             bp.add(jRadioButton2);
             bp.add(jRadioButton3);

             jRadioButton1.setSelected(true);
             JPanel middle = new JPanel();
             middle.setLayout(new GridLayout(6,1,5,5));
             middle.setBorder(new EmptyBorder(10,0,8,8));
             middle.setPreferredSize(new Dimension(360,180));
             middle.add(jRadioButton1, null);
             middle.add(jLabel1,null);
             middle.add(jRadioButton2, null);
             middle.add(jLabel2, null);
             middle.add(jRadioButton3, null);
             middle.add(jLabel3, null);

             JPanel bottom = new JPanel();
             bottom.setPreferredSize(new Dimension(360,80));

             JPanel panel = new JPanel();
             panel.setLayout(new BorderLayout());
             panel.add("Center",middle);
             panel.add("South", bottom);

             JPanel center = new JPanel();
             center.setLayout(new UnitLayout());
             center.add(panel);
             add("Center", center);

             canMoveForward = true;
        }
}
