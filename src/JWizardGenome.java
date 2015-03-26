package sage;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class JWizardGenome extends JDialog implements WindowListener
{
        static JWizardGenome wizardGenome;
        static JWizard2 wizard2;
        static Frame frame;
        static boolean canceled = false;


        public static void initialize(Component comp, String title) throws Exception {

            frame = JOptionPane.getFrameForComponent(comp);
            wizardGenome = new JWizardGenome(frame, title);
        }

        public static void showDialog(Component comp) {
          if (wizardGenome != null) {
            wizardGenome.setLocationRelativeTo(comp);
            wizardGenome.setVisible(true);
          }
          else {
            System.err.println("JWizardGenome requires you to call initialize before calling showDialog.");
          }
        }

        public JWizardGenome(Frame frame, String title) throws Exception
       {
               super(frame, title, true);
               this.setSize(500,500);

               wizard2 = new JWizard2();

               wizard2.addPanel("makegenome0", new MakeGenomeStep0());
               wizard2.addPanel("makegenome1", new MakeGenomeStep1());
               wizard2.addPanel("makegenome2", new MakeGenomeStep2());
               wizard2.addPanel("makegenome3", new MakeGenomeStep3());
               wizard2.addPanel("makegenome4", new MakeGenomeStep4());
               wizard2.setFirst();

               this.addWindowListener(this);

               getContentPane().setLayout(new BorderLayout());
               getContentPane().add("Center", wizard2);
       }

       public void windowOpened(WindowEvent w)
       {

       }
       public void windowClosing(WindowEvent w)
       {
         canceled=true;
       }
       public void windowClosed(WindowEvent w)
       {

       }
       public void windowIconified(WindowEvent w)
       {

       }
       public void windowDeiconified(WindowEvent w)
       {

       }
       public void windowActivated(WindowEvent w)
       {

       }
       public void windowDeactivated(WindowEvent w)
       {

       }
}
