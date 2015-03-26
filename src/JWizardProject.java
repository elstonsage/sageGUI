package sage;

import java.awt.BorderLayout;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class JWizardProject extends JInternalFrame implements VetoableChangeListener
{
         static JWizardProject wizardProject;
         static JWizard wizard;
         static Frame1 frame;
         static int locationx;
         static int locationy;

         static boolean canceled=false;

         public static void initialize(Component comp,String title, int width, int height) throws Exception{
             frame = (Frame1)comp;
             wizardProject = new JWizardProject(frame, title, width, height);
         }

         public static void showDialog() {
           if (wizardProject != null) {
             wizardProject.setVisible(true);
           }
           else {
             System.err.println("JWizardProject requires you to call initialize before calling showDialog.");
           }
         }

         public JWizardProject(Frame1 frame, String title, int width, int height) throws Exception
	{
            super(title,
                  true, //resizable
                  true, //closable
                  true, //maximizable
                  true); //iconifiable

                this.setSize(new Dimension(width,height));
                locationx = (frame.getWidth()-width)/2;
                locationy = (frame.getHeight()-height)/2-50;

                this.setFrameIcon(new ImageIcon(JWizardProject.class.getResource("project.png")));

                this.setLocation(locationx, locationy);

		wizard = new JWizard();
                ResultPanel r = new ResultPanel();
                InformationPanel i = new InformationPanel();
                FavoritesPanel f = new FavoritesPanel();
                SelectJobPanel selection = new SelectJobPanel();
                MakePedigreeStep1 makepedi1 =  new MakePedigreeStep1();
                MakePedigreeStep2 makepedi2 =  new MakePedigreeStep2();
                MakeParameterStep1 makepara1 = new MakeParameterStep1();
                MakeParameterStep2 makepara2 = new MakeParameterStep2();
                MakeParameterStep3 makepara3 = new MakeParameterStep3();
                MakePedigreeStep4 makepedi4 = new MakePedigreeStep4();

                wizard.addPanel("info", i);
                wizard.addPanel("selection", selection);
                wizard.addPanel("makepedi1", makepedi1);
                wizard.addPanel("makepedi2", makepedi2);
                wizard.addPanel("makepara1", makepara1);
                wizard.addPanel("makepara2", makepara2);
                wizard.addPanel("makepara3", makepara3);
                wizard.addPanel("makepedi4", makepedi4);
                wizard.addPanel("favorites", f);
		wizard.addPanel("result", r);
		wizard.setFirst();

                this.addVetoableChangeListener(this);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add("Center", wizard);
	}

        public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
          if(evt.getPropertyName().equals(IS_CLOSED_PROPERTY))
          {
             boolean changed = ((Boolean) evt.getNewValue()).booleanValue();
             if(changed)
             {
               int confirm = JOptionPane.showInternalOptionDialog(wizardProject,
                   "Warning!"+
                   "\nClosing the window before completing the project wizard will leave some required options unspecified."+
                   "\nDo you wish to close the window?",
                   "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                   null, null, null);
           if(confirm==1)
           {
               return;
           }
              else if(confirm==0)
               {
                 Frame1.desktop.remove(this);
                 Frame1.desktop.repaint();
                 JWizardProject.frame.jMenuFile.setEnabled(true);
                 JWizardProject.frame.jMenuView.setEnabled(true);
                 JWizardProject.frame.jMenuHelp.setEnabled(true);
                 JWizardProject.frame.m_windowMenu.setEnabled(true);
               }
             }
          }
        }

}
