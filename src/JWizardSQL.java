package sage;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.awt.Image;
import javax.swing.ImageIcon;

public class JWizardSQL extends JFrame {
    static JWizardSQL wizardSQL;
    static JWizard3 wizard3;
    static Frame1 mainframe;
    static boolean canceled = false;
    Image frameiconimage = new ImageIcon(JWizardSQL.class.getResource("SAGE.PNG")).getImage();

    public static void initialize(Frame1 mainframe, String title) throws Exception {
        wizardSQL = new JWizardSQL(mainframe, title);
    }

    public static void showDialog() {
      if (wizardSQL != null) {
        wizardSQL.setVisible(true);
        mainframe.setEnabled(false);
      }
      else {
        System.err.println("JWizardSQL requires you to call initialize before calling showDialog.");
      }
    }

    public JWizardSQL(Frame1 mainframe, String title) throws Exception
   {
          this.setTitle(title);
           this.setSize(600,420);

           this.setIconImage(frameiconimage);

          this.mainframe = mainframe;
          int locationx = (mainframe.getWidth() - 600) / 2;
          int locationy = (mainframe.getHeight() - 420) / 2 - 50;
          setLocation(locationx, locationy);

          this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

           wizard3 = new JWizard3();

           wizard3.addPanel("makesql1", new MakeSQLStep1());
           wizard3.addPanel("makesql2", new MakeSQLStep2());
           wizard3.setFirst();

           getContentPane().setLayout(new BorderLayout());
           getContentPane().add("Center", wizard3);
   }

   protected void processWindowEvent(WindowEvent e) {
     super.processWindowEvent(e);
     if (e.getID() == WindowEvent.WINDOW_CLOSING) {
       MakeSQLStep2 m2 = (MakeSQLStep2) wizard3.nav.deck.getComponent("makesql2");
       m2.close_Database();
       mainframe.setEnabled(true);
       this.setVisible(false);
     }
  }
}
